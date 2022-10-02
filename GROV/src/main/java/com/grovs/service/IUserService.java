package com.grovs.service;

import javax.validation.Valid;

import com.grovs.model.User;

public interface IUserService {

	User insertNewUser(@Valid User user);

}
