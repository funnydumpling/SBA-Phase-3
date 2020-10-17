package com.eval.it.app.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eval.it.app.dao.InterviewDAO;
import com.eval.it.app.dao.UserDAO;
import com.eval.it.app.entity.InterviewEntity;
import com.eval.it.app.entity.UserEntity;
import com.eval.it.app.exception.InterviewTrackerException;
import com.eval.it.app.model.UserModel;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userRepo;

	@Autowired
	private InterviewDAO intwRepo;
	
	private UserEntity userModelToUserEntity(UserModel model) {
		return new UserEntity(model.getUserId(), model.getFirstName(), model.getLastName(),model.getEmail(), model.getMobile());
	}
	
	private UserModel userEntityToUserModel(UserEntity entity) {
		return new UserModel(entity.getUserId(),entity.getFirstName(), entity.getLastName(), entity.getEmail(), entity.getMobile());
	}

	
	@Override
	@Transactional
	public UserModel add(UserModel userModel) throws InterviewTrackerException {
        if(userModel!=null) {
            if(userRepo.existsById(userModel.getUserId())) {
                throw new InterviewTrackerException("User Id already used!");
            }
            
            userModel = userEntityToUserModel(userRepo.save(userModelToUserEntity(userModel)));
        }
        return userModel;
	}

	@Override
	@Transactional
	public boolean deleteUser(int userId) throws InterviewTrackerException {
		if(!userRepo.existsById(userId)) {
			throw new InterviewTrackerException("User Not Found");
		}
		UserEntity userEntity = userRepo.findById(userId).orElse(null);
		userEntity.removeInterviews();
		userRepo.flush();
		userRepo.delete(userEntity);
		return true;
	}

	@Override
	public UserModel getUser(int userId) {
        UserEntity userEntity = userRepo.findById(userId).orElse(null);
        return userEntity!=null?userEntityToUserModel(userEntity):null;
	}

	@Override
	public List<UserModel> getAllUsers() {
		List<UserEntity> userEntityList= userRepo.findAll();
		List<UserModel> userModelList=null;
		if(userEntityList!=null && !userEntityList.isEmpty()) {
			userModelList = userEntityList.stream().map(e -> userEntityToUserModel(e)).collect(Collectors.toList());
		}
		return userModelList;
	}

}
