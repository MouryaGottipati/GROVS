package com.grovs.service;

import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grovs.dao.IProductDao;
import com.grovs.model.Product;

@Service
public class ProductService implements IProductService{
	@Autowired
	private IProductDao iProductDaoObject;
	public Set<Product> getAllProducts(){
		Set<Product> productsSetObject=new TreeSet<Product>((p1,p2)->(p1.getCategoryId()>p2.getCategoryId()?-1:(p1.getCategoryId()<p2.getCategoryId()?1:0)));
		productsSetObject.addAll(iProductDaoObject.findAll());
		return productsSetObject;
	}
	
}
