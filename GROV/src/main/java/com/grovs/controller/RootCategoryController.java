package com.grovs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.grovs.model.RootCategory;
import com.grovs.service.RootCategoryService;

@RestController
public class RootCategoryController {
	@Autowired
	private RootCategoryService rootCategoryServiceObject;
	
	@GetMapping(path="getRootCategoryProducts/{id}")
	public Object getRootCategoryProducs(@PathVariable int id) {
		System.out.println(id);
		return rootCategoryServiceObject.fetchRootCategoryPrducts(id);
	}
	
	
	
}
