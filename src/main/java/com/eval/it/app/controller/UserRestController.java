package com.eval.it.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eval.it.app.exception.InterviewTrackerException;
import com.eval.it.app.model.UserModel;
import com.eval.it.app.service.UserService;

@RestController
@RequestMapping("/user")
public class UserRestController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<UserModel> createUser(@RequestBody @Valid UserModel user,BindingResult result) throws InterviewTrackerException{
		if(result.hasErrors()) {
			throw new InterviewTrackerException(GlobalExceptionController.errMsgFrom(result));
		}
		return new ResponseEntity<>(userService.add(user),HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<UserModel>> getAllUsers(){
		return new ResponseEntity<List<UserModel>>(userService.getAllUsers(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserModel> getUsers(@PathVariable("id")int userId){
		ResponseEntity<UserModel> resp=null;
		
		UserModel user = userService.getUser(userId);
		
		if(user!=null) {
			resp =new ResponseEntity<>(user,HttpStatus.OK);
		}else {
			resp =new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return resp;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id")int userId) throws InterviewTrackerException{
		userService.deleteUser(userId);		
		return new ResponseEntity<String>("User ID " +userId+ " is removed Successfully",HttpStatus.OK);
	}
}
