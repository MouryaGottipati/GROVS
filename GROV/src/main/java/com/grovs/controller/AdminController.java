package com.grovs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.grovs.model.Product;
import com.grovs.service.AdminService;

@RestController
public class AdminController {
	@Autowired
	private AdminService adminServiceObject;


	@PostMapping("productUpload") 
	  public ResponseEntity<String>uploadProduct(@RequestParam("name") String name,@RequestParam("base_price") double basePrice,/*@RequestParam("root_category") String rootCategory,*/
			  @RequestParam("category") int category,@RequestParam("description") String description,
			  @RequestParam("discount") double discount,@RequestParam("gst") double gst,
			  @RequestParam("image") MultipartFile multipartFile,@RequestParam("return_period") int returnPeriod,
			  @RequestParam("stock") int stock){
	  adminServiceObject.saveProduct(name,basePrice,category,description,discount,gst,multipartFile,returnPeriod,stock);
	  adminServiceObject.saveImage(category,multipartFile);
	  
	  return new ResponseEntity<>(HttpStatus.ACCEPTED); 
	  
	}

}
