package com.grovs.service;

import javax.validation.Valid;

import com.grovs.model.RequestUserModel;
import com.grovs.model.User;

public interface IUserService {


	User insertNewUser(RequestUserModel requestUser);

}
