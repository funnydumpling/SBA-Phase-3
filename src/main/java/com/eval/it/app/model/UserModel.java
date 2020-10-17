package com.eval.it.app.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class UserModel {

	@NotNull(message = "User Id is mandatory")
	private Integer userId;
	
	@NotNull(message = "First Name is mandatory")
	@Size(min = 5, max = 30, message = "First Name must be between 5 and 30 characters")
	private String firstName;
	
	@Size(min = 5, max = 25, message = "Last Name must be between 5 and 25 characters")
	private String lastName;
	
	@NotNull(message = "E-mail is mandatory")
	@Email(message="Please provide a valid email address")
	private String email;
	
	@NotNull(message = "Mobile number is mandatory")
	@Pattern(regexp="\\d{10}", message="Please provide a valid mobile number with 10 digits")
	private String mobile;
	

	public UserModel() {
		//left unimplemented
	}	
	
	public UserModel(Integer loanId,
			String firstName,
			String lastName,
			String email,
			String mobile) {
		super();
		this.userId = loanId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
	}
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer loanId) {
		this.userId = loanId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "UserModel [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", mobile=" + mobile + "]";
	}


	
}
