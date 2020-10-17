package com.eval.it.app.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eval.it.app.dao.InterviewDAO;
import com.eval.it.app.dao.UserDAO;
import com.eval.it.app.entity.InterviewEntity;
import com.eval.it.app.entity.UserEntity;
import com.eval.it.app.exception.InterviewTrackerException;
import com.eval.it.app.model.AttendeeModel;
import com.eval.it.app.model.InterviewModel;
import com.eval.it.app.model.UserModel;

@Service
public class InterviewServiceImpl implements InterviewService {

	@Autowired
	private InterviewDAO interviewRepo;
	
	@Autowired
	private UserDAO userRepo;

	//***************METHODS FOR CONVERSION FROM ENTITY TO MODEL AND VICE VERSA**********************//
	
	private UserModel userEntityToUserModel(UserEntity entity) {
		return new UserModel(entity.getUserId(), entity.getFirstName(), entity.getLastName(),entity.getEmail(), entity.getMobile());
	}
	
	private UserEntity userModelToUserEntity(UserModel model) {
		return new UserEntity(model.getUserId(), model.getFirstName(), model.getLastName(),model.getEmail(), model.getMobile());
	}
	
	private Set<UserModel> userEntitiesToUserModels(Set<UserEntity> userEntities) {
		Set<UserModel> models=null;
		models = userEntities.stream().map(e -> userEntityToUserModel(e)).collect(Collectors.toSet());
		return models;
	}
	
	private Set<UserEntity> userModelsToUserEntities(Set<UserModel> userModels) {
		Set<UserEntity> entities=null;
		entities = userModels.stream().map(e -> userModelToUserEntity(e)).collect(Collectors.toSet());
		return entities;
	}
	
	
	private InterviewEntity interviewModelToInterviewEntity(InterviewModel interviewModel) {
		if(interviewModel.getAttendee()==null)
			return new InterviewEntity(interviewModel.getInterviewId(), interviewModel.getInterviewerName(), interviewModel.getInterviewName(), interviewModel.getUsersSkills(), interviewModel.getTime(), interviewModel.getDate(), interviewModel.getInterviewStatus(), interviewModel.getRemarks());
		else
			return new InterviewEntity(interviewModel.getInterviewId(), interviewModel.getInterviewerName(), interviewModel.getInterviewName(), interviewModel.getUsersSkills(), interviewModel.getTime(), interviewModel.getDate(), interviewModel.getInterviewStatus(), interviewModel.getRemarks(),userModelsToUserEntities(interviewModel.getAttendee()));
	}
	
	private InterviewModel interviewEntityToInterviewModel(InterviewEntity interviewEntity) {		
		if(interviewEntity.getAttendees()==null)
			return new InterviewModel(interviewEntity.getInterviewId(), interviewEntity.getInterviewerName(), interviewEntity.getInterviewName(), interviewEntity.getUsersSkills(), interviewEntity.getTime(), interviewEntity.getDate(), interviewEntity.getInterviewStatus(), interviewEntity.getRemarks());
		else
			return new InterviewModel(interviewEntity.getInterviewId(), interviewEntity.getInterviewerName(), interviewEntity.getInterviewName(), interviewEntity.getUsersSkills(), interviewEntity.getTime(), interviewEntity.getDate(), interviewEntity.getInterviewStatus(), interviewEntity.getRemarks(), userEntitiesToUserModels(interviewEntity.getAttendees()));
	}
	
	//***********************************************************************88**********************//
	
	
	@Override
	@Transactional
	public InterviewModel add(InterviewModel interviewModel) throws InterviewTrackerException {
        if(interviewModel!=null) {
            if(interviewRepo.existsById(interviewModel.getInterviewId())) {
                throw new InterviewTrackerException("Interview with the ID:"+interviewModel.getInterviewId() +" already exists! Add a new interview.");
            }
        	interviewModel = interviewEntityToInterviewModel(interviewRepo.save(interviewModelToInterviewEntity(interviewModel)));
        }
        return interviewModel;
	}
	
	@Override
	public InterviewModel getInterviewById(int iId) {
        InterviewEntity entity = interviewRepo.findById(iId).orElse(null);
        return entity!=null?interviewEntityToInterviewModel(entity):null;
    }

	
	
	@Override
	public Set<InterviewModel> retrieveUsingInterviewName(String interviewName) {
		Set<InterviewEntity> entities=  new HashSet<InterviewEntity>(interviewRepo.findByInterviewName(interviewName));
		Set<InterviewModel> models=null;
		if(entities!=null && !entities.isEmpty()) {
			models = entities.stream().map(e -> getInterviewModel(e)).collect(Collectors.toSet());
		}
		return models;
	}

	@Override
	public Set<InterviewModel> retrieveUsingInterviewerName(String interviewerName) {
			Set<InterviewEntity> entities=  new HashSet<InterviewEntity>(interviewRepo.findByInterviewerName(interviewerName));
			Set<InterviewModel> models=null;
			if(entities!=null && !entities.isEmpty()) {
				models = entities.stream().map(e -> getInterviewModel(e)).collect(Collectors.toSet());
			}
			return models;
		}
	
	public UserModel getUserById(int userId) {
	    UserEntity userEntity = userRepo.findById(userId).orElse(null);
	    return userEntity!=null?userEntityToUserModel(userEntity):null;
	}
	
	@Override
	public InterviewModel updateStatus(Integer interviewId, String status) throws InterviewTrackerException {
		if(!interviewRepo.existsById(interviewId)) {
			throw new InterviewTrackerException("Interview Id Not Found!");
		}
		InterviewModel interview = getInterviewById(interviewId);
		interview.setInterviewStatus(status);
        interviewRepo.save(interviewModelToInterviewEntity(interview));
		return getInterviewModel(interviewModelToInterviewEntity(interview));
	}


	@Override
	public String getInterviewCount() {
		Set<InterviewEntity> entities=  new HashSet<InterviewEntity>(interviewRepo.findAll());
		if(entities!=null)
			return "No. of Interviews scheduled: " + entities.size();
		else
			return "No interviews have been scheduled";
	}

	@Override
	public Set<InterviewModel> getAllInterviewDetails() {
		Set<InterviewEntity> entities= new HashSet<InterviewEntity>(interviewRepo.findAll());
		Set<InterviewModel> models=null;
		if(entities!=null && !entities.isEmpty()) {
			models = entities.stream().map(e -> getInterviewModel(e)).collect(Collectors.toSet());
		}
		return models;
	}
	
	@Override
	public boolean deleteInterview(int interviewId) throws InterviewTrackerException {
		if(!interviewRepo.existsById(interviewId)) {
			throw new InterviewTrackerException("Interview Id Not Found!");
		}		
		interviewRepo.deleteById(interviewId);
		return false;
	}

	
	private InterviewModel getInterviewModel(InterviewEntity entity) {
		return new InterviewModel(entity.getInterviewId(), entity.getInterviewerName(), entity.getInterviewName(), entity.getUsersSkills(), entity.getTime(), entity.getDate(), entity.getInterviewStatus(), entity.getRemarks());
	}
	
	@Override
	@Transactional
	public String addAttendee(AttendeeModel attendee) throws InterviewTrackerException {
		if(attendee!=null) {
			if(!userRepo.existsById(attendee.getUserId())) {
				throw new InterviewTrackerException("User Not Found");
			}
	        if(!interviewRepo.existsById(attendee.getInterviewId())) {
	            throw new InterviewTrackerException("Interview Id Not Found!");
	        }
	        InterviewModel interview = getInterviewById(attendee.getInterviewId());
	        for(UserModel user: interview.getAttendee()) {
	        	if(user.getUserId() == attendee.getUserId()) {
	        		throw new InterviewTrackerException("User Id "+attendee.getUserId()+" is already added to this Interview "+attendee.getInterviewId()+"as an attendee!");
	        	}
	        }
	        Set<UserModel> users=interview.getAttendee();	        
	        users.add(getUserById(attendee.getUserId()));
	        interview.setAttendee(users);
	        interviewRepo.save(interviewModelToInterviewEntity(interview));
	        return "User Id "+attendee.getUserId()+" is added to this Interview "+attendee.getInterviewId()+" as an attendee!";
		}
		return "Error adding User with Id: " +  attendee.getUserId();
	}
	
	
	@Override
	public Set<UserModel> showAttendeesOfInterview(int interviewId) throws InterviewTrackerException {
		if(!interviewRepo.existsById(interviewId)) {
            throw new InterviewTrackerException("Interview Id Not Found!");
        }
		return userEntitiesToUserModels(interviewRepo.findById(interviewId).orElse(null).getAttendees());
	}

	
	
}
