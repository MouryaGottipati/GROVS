package com.grovs.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grovs.model.Address;
import com.grovs.model.Cart;

public interface IAddressDao extends JpaRepository<Address, String> {

	Optional<Cart> findByUserId(String userId);

}
