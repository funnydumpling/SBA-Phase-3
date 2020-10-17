package com.eval.it.app.service;

import java.util.List;

import com.eval.it.app.exception.InterviewTrackerException;
import com.eval.it.app.model.UserModel;

public interface UserService {

	UserModel add(UserModel user) throws InterviewTrackerException;
	
	boolean deleteUser(int userId) throws InterviewTrackerException;
	
	UserModel getUser(int userId);
	
	List<UserModel> getAllUsers();

}
