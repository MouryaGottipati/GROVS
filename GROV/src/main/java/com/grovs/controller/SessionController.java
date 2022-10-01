package com.grovs.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grovs.model.Cart;
import com.grovs.service.SessionService;

@RestController
public class SessionController {
	@Autowired
	private SessionService sessionServiceObject;
	
	@GetMapping("cartCookieCheck")
	public ResponseEntity<Cart> cartCookieCheck(HttpServletRequest request,HttpServletResponse response){
		Cart cart=sessionServiceObject.cartCookie(request,response);
		return ResponseEntity.status(HttpStatus.CREATED).body(cart);
	}
	
	
	
}
