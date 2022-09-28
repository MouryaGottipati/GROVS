package com.grovs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grovs.dao.ICategoryDao;

@Service
public class CategoryService implements ICategoryService {
	
	@Autowired
	private ICategoryDao CategoryDaoObject;
		
	public List fetchSubCategoryProducts(int id) {
		// TODO Auto-generated method stub
		return CategoryDaoObject.fetchSubCategoryProducts(id);
	}
	
}
