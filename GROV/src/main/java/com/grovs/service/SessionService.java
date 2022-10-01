package com.grovs.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grovs.dao.ICartDao;
import com.grovs.enums.CartUserType;
import com.grovs.model.Cart;
import com.grovs.model.CartItems;

@Service
public class SessionService {
	@Autowired
	private ICartDao cartDaoObject;

	public Cart cartCookie(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		if (session==null || session.getAttribute("cart") == null) {
			session=request.getSession(true);
			System.out.println("In cookie check session not exists or cart in session is null");
			Cookie[] cookies = request.getCookies();
			Cart cart = new Cart();
			if (cookies != null) {
				String cartId = null;
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("cartId")) {
						cartId = cookie.getValue();
						break;
					}
				}
				if (cartId != null) {
					cart = cartDaoObject.findById(cartId).orElse(new Cart());

					session.setAttribute("cart", cart);
				System.out.println("Cookie already exists"+session.getAttribute("cart"));
				return cart;
				} else {
					
					cart.setCreationTime(LocalDateTime.now());
					cart.setUpdatedTime(LocalDateTime.now());
					cart.setCartItems(new LinkedHashSet<CartItems>());
					cart.setUserType(CartUserType.ANONYMOUS_USER);
					Cart newCart = cartDaoObject.save(cart);
					session.setAttribute("cart", newCart);
					System.out.println("Cookie doesn't exists"+session.getAttribute("cart"));
					Cookie cookie = new Cookie("cartId", newCart.getId());
					cookie.setMaxAge(2147483647);
					response.addCookie(cookie);
					// System.out.println(newCart);
					return newCart;

				}

			} else {
				System.out.println("Else ");
				cart.setCreationTime(LocalDateTime.now());
				cart.setUpdatedTime(LocalDateTime.now());
				cart.setUserType(CartUserType.ANONYMOUS_USER);
				Cart newCart = cartDaoObject.save(cart);
				System.out.println("Doesn't have any Cookie already "+session.getAttribute("cart"));
				session.setAttribute("cart", newCart);
				Cookie cookie = new Cookie("cartId", newCart.getId());
				cookie.setMaxAge(2147483647);
				response.addCookie(cookie);
			}
			
		}
		
		return (Cart) session.getAttribute("cart");
	}
}
