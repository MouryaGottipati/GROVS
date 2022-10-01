package com.grovs.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grovs.model.User;

public interface IAdminDao extends JpaRepository<User,String>{

}
