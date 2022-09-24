package com.grovs.service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.grovs.model.Product;
import com.grovs.model.RootCategory;

public interface IRootCategoryService {
	public LinkedHashSet<RootCategory> getAllCategories();
	public List fetchRootCategoryPrducts(int id);
	
}
