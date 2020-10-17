package com.eval.it.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eval.it.app.entity.UserEntity;

@Repository
public interface UserDAO extends JpaRepository<UserEntity, Integer>{

}
