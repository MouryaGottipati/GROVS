package com.grovs.service;

import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grovs.dao.IProductDao;
import com.grovs.model.Product;
import com.grovs.model.Review;

@Service
public class ProductService implements IProductService{
	@Autowired
	private IProductDao iProductDaoObject;
	public Set<Product> getAllProducts(){
		Set<Product> productsSetObject=new TreeSet<Product>((p1,p2)->(p1.getCategoryId()>p2.getCategoryId()?-1:(p1.getCategoryId()<p2.getCategoryId()?1:0)));
		productsSetObject.addAll(iProductDaoObject.findAll());
		return productsSetObject;
	}
	
	public Product fetchSingleProduct(String id) {
		// TODO Auto-generated method stub
//		System.out.println(iProductDaoObject.findById(id).get());
		TreeSet<Review> reviewSet=new TreeSet<>((p1,p2)->p1.getUpdatedTime().isBefore(p2.getUpdatedTime())?1:-1);
		Product product=iProductDaoObject.findById(id).get();
		System.out.println(product);
		product.getReview().forEach(p->reviewSet.add(p));
		product.setReview(reviewSet);
//		product.getReview().parallelStream().limit(5).map(p->reviewSet.add(p)).close();
//		product.setReview(reviewSet);
		return product;
	}
	
}
