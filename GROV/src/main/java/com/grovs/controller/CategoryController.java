package com.grovs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.grovs.service.CategoryService;

@RestController
public class CategoryController {
	@Autowired
	private CategoryService categoryServiceObject;
	
	@GetMapping(path="getSubCategoryProducts/{id}")
	public Object getSubCategoryProducts(@PathVariable int id) {
		return  categoryServiceObject.fetchSubCategoryProducts(id);
	}
}
