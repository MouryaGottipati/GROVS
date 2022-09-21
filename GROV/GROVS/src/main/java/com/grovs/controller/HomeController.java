package com.grovs.controller;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.grovs.model.Product;
import com.grovs.model.RootCategory;
import com.grovs.service.HomeService;
import com.grovs.service.ProductService;
import com.grovs.service.RootCategoryService;

@Controller
public class HomeController {
	
	@Autowired
	private HomeService homeService;
	@Autowired
	private ProductService productServiceObject;
	@Autowired
	private RootCategoryService rootCategoryServiceObject;
	
	@RequestMapping("home")
	public String homeData(Model m) {
		Set<RootCategory> rootCategorySet=rootCategoryServiceObject.getAllCategories();
		Set<Product> productsSet=productServiceObject.getAllProducts();
		homeService.iterateRootCatogory(rootCategorySet,productsSet);
		return "home";
	}
}