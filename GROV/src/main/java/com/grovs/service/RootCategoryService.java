package com.grovs.service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grovs.dao.ICategoryDao;
import com.grovs.dao.IProductDao;
import com.grovs.dao.IRootCategoryDao;
import com.grovs.model.Category;
import com.grovs.model.Product;
import com.grovs.model.RootCategory;

@Service
public class RootCategoryService implements IRootCategoryService{
	@Autowired
	private IRootCategoryDao iRootCategoryDaoObject;
	@Autowired
	private ICategoryDao iCategoryDaoObject;
	@Autowired
	private IProductDao iProductDaoObject;
	public LinkedHashSet<RootCategory> getAllCategories(){
		List <RootCategory> rootCategoryList =iRootCategoryDaoObject.findAll();
//		TreeSet<Category> categorySet=new TreeSet<>((p1,p2)->p1.getId()>p2.getId()?1:-1);
		rootCategoryList.forEach(p->p.setCategories(p.getCategories().parallelStream().collect(Collectors.toCollection(()->new TreeSet<>((p1,p2)->p1.getId()>p2.getId()?1:-1)))));
//		System.out.println(categorySet);
//		rootCategoryList.forEach((p)->p.setCategories(categorySet));
//		System.out.println(new LinkedHashSet<>(rootCategoryList));
		return new LinkedHashSet<>(rootCategoryList);
	}
	public List fetchRootCategoryPrducts(int id) {
		
		System.out.println(id);
		// TODO Auto-generated method stub
//		List<Category> subCategoryList=iCategoryDaoObject.findAllByRootCategoryId(id);
		
		List l=iRootCategoryDaoObject.queryToRootCategoryProducts(id);
		System.out.println(l);
		l.forEach(p->System.out.println(p));
//		Set<Product> setOfAllProducts=iProductDaoObject.findByCategoryId(null);
		return l;
	}
}
