package com.grovs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.grovs.service.ProductService;

@RestController
public class ProductController {
	@Autowired
	private ProductService productServiceObject;
	
	@GetMapping(path="fetchProductDetails/{productId}")
	public Object fetchProductDetails(@PathVariable String productId) {
		return productServiceObject.fetchSingleProduct(productId);
	}
}
