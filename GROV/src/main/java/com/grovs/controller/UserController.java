package com.grovs.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grovs.model.LoginUserModel;
import com.grovs.model.RequestUserModel;
import com.grovs.model.User;
import com.grovs.service.UserService;

@RestController
public class UserController {

		@Autowired
		private UserService UserServiceObject;
		
		
		@GetMapping(value="/existingUserCheck")
		public ResponseEntity<Boolean> existingUserCheck(
		   HttpServletRequest request){
			Boolean status=UserServiceObject.existingUserCheck(request);
			
			return ResponseEntity.status(200).body(status);
		}
		
		@GetMapping(value="/userDetails")
		public ResponseEntity<User> getUserDetails(
				 HttpServletRequest request){
			User userData=UserServiceObject.getUserDetails(request);
			
			return ResponseEntity.status(200).body(userData);
		}
		
		@PostMapping(value="/signUp")
		public ResponseEntity<User> signUp(
				 @Valid   @RequestBody  RequestUserModel user){
			System.out.println(user);
			User userData=UserServiceObject.insertNewUser(user);
			
			return ResponseEntity.status(200).body(userData);
		}
		
		@PostMapping(value="/login")
		public ResponseEntity<User> login(
				 @Valid   @RequestBody  LoginUserModel user,HttpServletRequest request,HttpServletResponse response){
			User userData=UserServiceObject.login(user,request, response);
			
			return ResponseEntity.status(200).body(userData);
		}

	
}
