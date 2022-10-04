package com.grovs.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grovs.dao.ICartDao;
import com.grovs.dao.ICartItemsDao;
import com.grovs.dao.IUserDao;
import com.grovs.enums.CartUserType;
import com.grovs.enums.UserRole;
import com.grovs.exception.AgeInsufficientException;
import com.grovs.exception.DataAlreadyExists;
import com.grovs.exception.DataMisMatchException;
import com.grovs.exception.GlobalExceptionHandler;
import com.grovs.model.Cart;
import com.grovs.model.CartItems;
import com.grovs.model.LoginUserModel;
import com.grovs.model.RequestUserModel;
import com.grovs.model.User;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserDao userDaoObject;

	@Autowired
	private ICartDao cartDaoObject;

	@Autowired
	private ICartItemsDao cartItemsDaoObject;
	@Autowired
	private CartService cartServiceObject;

	@Override
	public User insertNewUser(RequestUserModel requestUser) {
		// TODO Auto-generated method stub
		boolean mailCheck = userDaoObject.findByMail(requestUser.getMail()) == null;
		boolean mobileCheck = userDaoObject.findByMobile(requestUser.getMobile()) == null;
//		System.out.println( mailCheck);
		if (mailCheck && mobileCheck) {
			int age=LocalDate.now().getYear() - LocalDate.parse(requestUser.getDateOfBirth()).getYear();
			if(age<13) {
				throw new AgeInsufficientException("602","Minimum age should be 13years");
			}
			User user = new User();
			user.setDateOfBirth(LocalDate.parse(requestUser.getDateOfBirth()));
			user.setFirstName(requestUser.getFirstName());
			if (requestUser.getMiddleName() == null) {
				user.setMiddleName(" ");
			} else {
				user.setMiddleName(requestUser.getMiddleName());
			}

			user.setLastName(requestUser.getLastName());
			user.setMail(requestUser.getMail());
			user.setMobile(requestUser.getMobile());
			user.setPassword(requestUser.getPassword());
			user.setCreationTime(LocalDateTime.now());
			user.setRole(UserRole.USER);
			Cart cart = new Cart();
			cart.setCreationTime(LocalDateTime.now());
			cart.setUpdatedTime(LocalDateTime.now());
			cart.setUserType(CartUserType.EXISTING_USER);
			cart.setCartItems(null);

			user.setCartId(cartDaoObject.save(cart));
			user.setAge(age);
			return userDaoObject.save(user);
		} else {
			if (userDaoObject.findByMail(requestUser.getMail()) != null) {
				// throw mail exists exception
				throw new DataAlreadyExists("600", "Mail already exists");
			}
			if (userDaoObject.findByMobile(requestUser.getMobile()) != null) {
				// throw mobile exists exception
				throw new DataAlreadyExists("601", "Mobile already exists");
			}
		}
		return null;
	}

	public User login(  @Valid LoginUserModel user, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		User userData = userDaoObject.findByMail(user.getMail());
		if (userData == null) {
			throw new DataMisMatchException("603", "Mail doesn't exist");
		} else if (!userData.getPassword().equals(user.getPassword())) {
			throw new DataMisMatchException("604", "Password doesn't match");
		} else {
			
			HttpSession session = request.getSession();
			Cookie userIdCookie, cartIdCookie;
			Cart userCart = cartDaoObject.findById(userData.getCartId().getId()).orElse(new Cart());
			Set<CartItems> cartItemsSet = new TreeSet<>(
					(p1, p2) -> p1.getUpdateTime().isAfter(p2.getUpdateTime()) ? 1 : -1);
			if (userCart.getCartItems() != null) {
				userCart.getCartItems().forEach(p -> cartItemsSet.add(p));
			}
			userCart.setCartItems(cartItemsSet);
			session.setAttribute("cart", userCart);

			final String cartId = userCart.getId();

			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("cartId") && !cookie.getValue().equals(cartId)) {
					Cart existingCart = cartDaoObject.findById(cookie.getValue()).orElse(new Cart());
					Set<CartItems> existingCartItemsSet = new TreeSet<>(
							(p1, p2) -> p1.getUpdateTime().isAfter(p2.getUpdateTime()) ? 1 : -1);
					if (existingCart.getCartItems() != null) {
						existingCart.getCartItems().forEach(p -> existingCartItemsSet.add(p));
					}
					existingCart.setCartItems(existingCartItemsSet);

					if (existingCart.getCartItems() != null) {
						existingCart.getCartItems().forEach(p -> {p.setCartId(cartId);
							cartServiceObject.addProductToCart(p.getProduct().getId(), p.getQuantity(), request,
									response);
						});
					}

					cartDaoObject.deleteById(existingCart.getId());

				}
			}
			userIdCookie = new Cookie("userId", userData.getId());
			userIdCookie.setMaxAge(2147483647);
			cartIdCookie = new Cookie("cartId", cartId);
			cartIdCookie.setMaxAge(2147483647);
			response.addCookie(cartIdCookie);
			response.addCookie(userIdCookie);
			userData.setCartId((Cart)session.getAttribute("cart"));
			return userData;
		}

	}

	public boolean existingUserCheck(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Cookie[] cookies=request.getCookies();
		for(Cookie cookie:cookies) {
			if(cookie.getName().equals("userId") && cookie.getValue()!=null) {
				return userDaoObject.findById(cookie.getValue()).isPresent();
			}
		}
		return false;
	}

	public User getUserDetails(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Cookie[] cookies=request.getCookies();
		for(Cookie cookie:cookies) {
			if(cookie.getName().equals("userId") && cookie.getValue()!=null) {
				return userDaoObject.findById(cookie.getValue()).orElse(new User());
			}
		}
		return null;
	}

}
