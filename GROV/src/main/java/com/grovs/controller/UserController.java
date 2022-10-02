package com.grovs.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grovs.model.User;
import com.grovs.service.UserService;

@RestController
public class UserController {

		@Autowired
		private UserService UserServiceObject;
		
		@PostMapping("/signUp")
		public ResponseEntity<User> signUp(/* @Valid */ User user ){
			User userData=UserServiceObject.insertNewUser(user);
			System.out.println(user);
			return ResponseEntity.status(200).body(userData);
		}
}
