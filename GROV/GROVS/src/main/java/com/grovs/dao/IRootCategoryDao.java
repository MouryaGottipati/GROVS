package com.grovs.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grovs.model.RootCategory;

public interface IRootCategoryDao extends JpaRepository<RootCategory, Integer>{

}
