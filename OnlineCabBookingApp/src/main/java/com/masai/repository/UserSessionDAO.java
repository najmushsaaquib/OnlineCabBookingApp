package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.modelEntity.UserSession;


@Repository
public interface UserSessionDAO extends JpaRepository<UserSession, Integer> {

	public Optional<UserSession> findByUserId(Integer userId);
	
	public Optional<UserSession> findByUuid(String  uuid);

}
