package com.eval.it.app.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class InterviewModel {

	@NotNull(message = "Interview Id is mandatory")
	private Integer interviewId;
	
	@NotNull(message = "Interviewer Name is mandatory")
	@Size(min = 5, max = 30, message = "Interviewer Name must be between 5 and 30 characters")
	private String interviewerName;
	
	@NotNull(message = "Interview Name is mandatory")
	@Size(min = 3, max = 30, message = "Interview Name must be between 3 and 30 characters")
	private String interviewName;
	
	@NotNull(message = "Users Skills is mandatory")
	@Size(min = 5, max = 30, message = "Users Skills must be between 5 and 30 characters")
	private String usersSkills;
	
	private LocalTime time;
	
	private LocalDate date;
	
	@NotNull(message = "Interview Status is mandatory")
	@Size(min = 5, max = 100, message = "Interview Status must be between 5 and 100 characters")
	private String interviewStatus;
	
	@NotNull(message = "Remarks is mandatory")
	@Size(min = 5, max = 100, message = "Remarks must be between 5 and 100 characters")
	private String remarks;
	
	@Valid
	private Set<UserModel> attendee;	
	
	public Set<UserModel> getAttendee() {
		return attendee;
	}
	
	public void setAttendee(Set<UserModel> attendees) {
		this.attendee = attendees;
	}

	public InterviewModel() {
		//left unimplemented
	}	

	public InterviewModel(Integer interviewId,
			String interviewerName,
			String interviewName,
			String usersSkills,
			LocalTime time,
			LocalDate date,
			String interviewStatus,
			String remarks
			) {
		super();
		this.interviewId = interviewId;
		this.interviewerName = interviewerName;
		this.interviewName = interviewName;
		this.usersSkills = usersSkills;
		this.time = time;
		this.date = date;
		this.interviewStatus = interviewStatus;
		this.remarks = remarks;
	}
	
	

	public InterviewModel(Integer interviewId,
			String interviewerName,
			String interviewName,
			String usersSkills,
			LocalTime time, LocalDate date,
			String interviewStatus,
			String remarks,
			Set<UserModel> attendees) {
		super();
		this.interviewId = interviewId;
		this.interviewerName = interviewerName;
		this.interviewName = interviewName;
		this.usersSkills = usersSkills;
		this.time = time;
		this.date = date;
		this.interviewStatus = interviewStatus;
		this.remarks = remarks;
		this.attendee = attendees;
	}

	public Integer getInterviewId() {
		return interviewId;
	}

	public void setInterviewId(Integer interviewId) {
		this.interviewId = interviewId;
	}

	public String getInterviewerName() {
		return interviewerName;
	}

	public void setInterviewerName(String interviewerName) {
		this.interviewerName = interviewerName;
	}

	public String getInterviewName() {
		return interviewName;
	}

	public void setInterviewName(String interviewName) {
		this.interviewName = interviewName;
	}

	public String getUsersSkills() {
		return usersSkills;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setUsersSkills(String usersSkills) {
		this.usersSkills = usersSkills;
	}

	public String getInterviewStatus() {
		return interviewStatus;
	}

	public void setInterviewStatus(String interviewStatus) {
		this.interviewStatus = interviewStatus;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	@Override
	public String toString() {
		return "Interview [interviewId=" + interviewId + ", interviewerName=" + interviewerName + ", interviewName=" + 
				interviewName + ", usersSkills=" + usersSkills + ", InterviewTime=" + time+ ", InterviewDate=" + date
				+ ", interviewStatus=" + interviewStatus + ", remarks=" + remarks +"]";
	}
		
}