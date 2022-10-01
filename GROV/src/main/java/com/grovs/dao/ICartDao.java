package com.grovs.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grovs.model.Cart;

public interface ICartDao extends JpaRepository<Cart,String>{

}
