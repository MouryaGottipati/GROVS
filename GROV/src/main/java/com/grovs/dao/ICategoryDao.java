package com.grovs.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grovs.model.Category;

public interface ICategoryDao extends JpaRepository<Category,Integer>{

public 	List<Category> findAllByRootCategoryId(int id);

}
