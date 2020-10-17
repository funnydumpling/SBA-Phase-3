package com.eval.it.app.controller;

import java.util.Set;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eval.it.app.exception.InterviewTrackerException;
import com.eval.it.app.model.AttendeeModel;
import com.eval.it.app.model.InterviewModel;
import com.eval.it.app.model.UserModel;
import com.eval.it.app.service.InterviewService;

@RestController
@RequestMapping("/interview")
public class InterviewRestController {

	@Autowired
	private InterviewService interviewService;
	
	@PostMapping
	public ResponseEntity<InterviewModel> createInterview(@RequestBody @Valid InterviewModel interview,BindingResult result) throws InterviewTrackerException{
		if(result.hasErrors()) {
			throw new InterviewTrackerException(GlobalExceptionController.errMsgFrom(result));
		}
		return new ResponseEntity<>(interviewService.add(interview),HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<Set<InterviewModel>> getAllInterviews(){
		return new ResponseEntity<Set<InterviewModel>>(interviewService.getAllInterviewDetails(),HttpStatus.OK);
	}
	
	@GetMapping("/getCount")
	public ResponseEntity<String> getCountOfInterviews(){
		return new ResponseEntity<>(interviewService.getInterviewCount(),HttpStatus.OK);
	}
	
	@GetMapping("/showByInterview/{InterviewName}")
	public ResponseEntity<Set<InterviewModel>> getInterviewName(@PathVariable("InterviewName")String interviewName){
		ResponseEntity<Set<InterviewModel>> resp=null;
		Set<InterviewModel> interview = interviewService.retrieveUsingInterviewName(interviewName);
		
		if(interview!=null) {
			resp =new ResponseEntity<>(interview,HttpStatus.OK);
		}else {
			resp =new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return resp;
	}
	
	@GetMapping("/showByName/{InterviewerName}")
	public ResponseEntity<Set<InterviewModel>> getInterviewerName(@PathVariable("InterviewerName")String interviewerName){
		ResponseEntity<Set<InterviewModel>> resp=null;
		Set<InterviewModel> interview = interviewService.retrieveUsingInterviewerName(interviewerName);
		
		if(interview!=null) {
			resp =new ResponseEntity<>(interview,HttpStatus.OK);
		}else {
			resp =new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return resp;
	}
	
	@PutMapping("/{interviewid}/{status}")
	public ResponseEntity<InterviewModel> updateStatus(@PathVariable("interviewid")int interviewId,@PathVariable("status")String status) throws InterviewTrackerException{
		return new ResponseEntity<>(interviewService.updateStatus(interviewId, status),HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteInterview(@PathVariable("id")int interviewId) throws InterviewTrackerException{
		interviewService.deleteInterview(interviewId);		
		return new ResponseEntity<>("Interview ID "+interviewId+ " is deleted Successfully",HttpStatus.OK);
	}
	
	@PutMapping("/addAttendee")
	public ResponseEntity<String> addAttendee(@RequestBody @Valid AttendeeModel attendee,BindingResult result) throws InterviewTrackerException{
		if(result.hasErrors()) {
			throw new InterviewTrackerException(GlobalExceptionController.errMsgFrom(result));
		}
		return new ResponseEntity<>(interviewService.addAttendee(attendee),HttpStatus.OK);
	}
	
	@GetMapping("/showAttendees/{id}")
	public ResponseEntity<Set<UserModel>> showAttendees(@PathVariable("id")int interviewId) throws InterviewTrackerException{
		return new ResponseEntity<>(interviewService.showAttendeesOfInterview(interviewId),HttpStatus.OK);
	}
	
	
}
