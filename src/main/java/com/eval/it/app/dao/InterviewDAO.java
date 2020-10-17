package com.eval.it.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eval.it.app.entity.InterviewEntity;

@Repository
public interface InterviewDAO extends JpaRepository<InterviewEntity, Integer>{

	@Query("SELECT i FROM InterviewEntity i WHERE i.interviewId =:iId")
	List<InterviewEntity> findAllById(Integer iId);
	
	@Query("SELECT i FROM InterviewEntity i WHERE i.interviewName =:interviewName")
	List<InterviewEntity> findByInterviewName(String interviewName);
	
	@Query("SELECT i FROM InterviewEntity i WHERE i.interviewerName=:interviewerName")
	List<InterviewEntity> findByInterviewerName(String interviewerName);
}
