package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.modelEntity.AdminSession;
@Repository
public interface AdminSessionDao extends JpaRepository<AdminSession, Integer>{
	
	public Optional<AdminSession> findByAdminId(Integer userId) ;
	
	public Optional<AdminSession> findByUuid(String  uuid);

}
