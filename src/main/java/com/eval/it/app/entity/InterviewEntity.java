package com.eval.it.app.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="interview")
public class InterviewEntity implements Serializable{

	@Id
	@Column(name="interviewId")
	private Integer interviewId;
	
	@Column(name="interviewerName")
	private String interviewerName;
	
	@Column(name="interviewName")
	private String interviewName;
	
	@Column(name="usersSkills")
	private String usersSkills;
	
	@Column(name="time")
	private LocalTime time;

	@Column(name="date")
	private LocalDate date;
	
	@Column(name="interviewStatus")
	private String interviewStatus;

	@Column(name="remarks")
	private String remarks;
	
	
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(name = "Schedule", 
      joinColumns = @JoinColumn(name = "interviewId", referencedColumnName = "interviewId"), 
      inverseJoinColumns = @JoinColumn(name = "userId", referencedColumnName = "userId"))
	private Set<UserEntity> attendees=new HashSet<>();
	
	public InterviewEntity() {
		//left unimplemented
	}

	public InterviewEntity(Integer interviewId, String interviewerName, String interviewName, String usersSkills,
			LocalTime time, LocalDate date, String interviewStatus, String remarks, Set<UserEntity> attendees) {
		super();
		this.interviewId = interviewId;
		this.interviewerName = interviewerName;
		this.interviewName = interviewName;
		this.usersSkills = usersSkills;
		this.time = time;
		this.date = date;
		this.interviewStatus = interviewStatus;
		this.remarks = remarks;
		this.attendees =attendees;
	}

	public InterviewEntity(Integer interviewId, String interviewerName, String interviewName, String usersSkills,
			LocalTime time, LocalDate date, String interviewStatus, String remarks) {
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

	public void setUsersSkills(String usersSkills) {
		this.usersSkills = usersSkills;
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


	public Set<UserEntity> getAttendees() {
		return attendees;
	}

	public void setAttendees(Set<UserEntity> attendees) {
		this.attendees = attendees;
	}

	@Override
	public String toString() {
		return "Interview [interviewId=" + interviewId + ", interviewerName=" + interviewerName + ", interviewName=" 
				+ interviewName + ", usersSkills=" + usersSkills + ", Interviewtime=" + time + ", Interviewdate=" + date +
				", interviewStatus=" + interviewStatus + ", remarks=" + remarks + "]";
	}

}
