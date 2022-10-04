package com.grovs.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grovs.model.User;

@Repository
public interface IUserDao extends JpaRepository<User, String>{

	User findByMail(String mail);

	User findByMobile(String mobile);

}
