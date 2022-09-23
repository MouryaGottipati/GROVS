package com.grovs.service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grovs.dao.IRootCategoryDao;
import com.grovs.model.Category;
import com.grovs.model.RootCategory;

@Service
public class RootCategoryService {
	@Autowired
	private IRootCategoryDao iRootCategoryDaoObject;
	public LinkedHashSet<RootCategory> getAllCategories(){
		List <RootCategory> rootCategoryList =iRootCategoryDaoObject.findAll();
//		TreeSet<Category> categorySet=new TreeSet<>((p1,p2)->p1.getId()>p2.getId()?1:-1);
		rootCategoryList.forEach(p->p.setCategories(p.getCategories().parallelStream().collect(Collectors.toCollection(()->new TreeSet<>((p1,p2)->p1.getId()>p2.getId()?1:-1)))));
//		System.out.println(categorySet);
//		rootCategoryList.forEach((p)->p.setCategories(categorySet));
//		System.out.println(new LinkedHashSet<>(rootCategoryList));
		return new LinkedHashSet<>(rootCategoryList);
	}
}
