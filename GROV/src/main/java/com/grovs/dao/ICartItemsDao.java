package com.grovs.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grovs.model.CartItems;

@Repository
public interface ICartItemsDao extends JpaRepository<CartItems,String>{

}
