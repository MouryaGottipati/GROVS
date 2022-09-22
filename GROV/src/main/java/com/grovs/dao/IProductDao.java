package com.grovs.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grovs.model.Product;

public interface IProductDao extends JpaRepository<Product,Integer>{

}
