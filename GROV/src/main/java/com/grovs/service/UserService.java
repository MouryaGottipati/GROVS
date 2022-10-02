package com.grovs.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grovs.dao.IUserDao;
import com.grovs.enums.CartUserType;
import com.grovs.enums.UserRole;
import com.grovs.model.Cart;
import com.grovs.model.User;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserDao userDaoObject;

	@Override
	public User insertNewUser(@Valid User user) {
		// TODO Auto-generated method stub
		if (userDaoObject.findById(user.getMail()).orElse(null) == null
				&& userDaoObject.findById(user.getMobile()).orElse(null) == null) {
			user.setCreationTime(LocalDateTime.now());
			user.setRole(UserRole.USER);
			user.setId(null);
			Cart cart = new Cart();
			cart.setCreationTime(LocalDateTime.now());
			cart.setUpdatedTime(LocalDateTime.now());
			cart.setUserType(CartUserType.EXISTING_USER);
			cart.setId(null);
			cart.setCartItems(null);
			user.setCartId(cart);
			user.setAge(LocalDate.now().getYear() - user.getDateOfBirth().getYear());
			return userDaoObject.save(user);
		} else {
			if (userDaoObject.findById(user.getMail()).orElse(null) != null) {
				// throw mail exists exception
			} else {
				// throw mobile exists exception
			}
		}
		return null;
	}

}
