package com.eval.it.app.service;

import java.util.Set;

import com.eval.it.app.exception.InterviewTrackerException;
import com.eval.it.app.model.AttendeeModel;
import com.eval.it.app.model.InterviewModel;
import com.eval.it.app.model.UserModel;

public interface InterviewService {

	InterviewModel add(InterviewModel interview) throws InterviewTrackerException;
	
	boolean deleteInterview(int interviewId) throws InterviewTrackerException;
	
	InterviewModel updateStatus(Integer interviewid, String status) throws InterviewTrackerException;
	
	String getInterviewCount();
	
	Set<InterviewModel> retrieveUsingInterviewName(String interviewName);

	Set<InterviewModel> retrieveUsingInterviewerName(String interviewerName);
	
	Set<InterviewModel> getAllInterviewDetails();
	
	Set<UserModel> showAttendeesOfInterview(int interviewId) throws InterviewTrackerException;

	String addAttendee(AttendeeModel attendee) throws InterviewTrackerException;

	InterviewModel getInterviewById(int interviewId);;
}
