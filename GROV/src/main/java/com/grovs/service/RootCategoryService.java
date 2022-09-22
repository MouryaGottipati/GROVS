package com.grovs.service;

import java.util.LinkedHashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grovs.dao.IRootCategoryDao;
import com.grovs.model.RootCategory;

@Service
public class RootCategoryService {
	@Autowired
	private IRootCategoryDao iRootCategoryDaoObject;
	public LinkedHashSet<RootCategory> getAllCategories(){
		return new  LinkedHashSet<>(iRootCategoryDaoObject.findAll());
	}
}
