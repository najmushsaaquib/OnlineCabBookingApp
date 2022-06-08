package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.modelEntity.Admin;
@Repository
public interface AdminDao extends JpaRepository<Admin, Integer>{
	
	public Optional<Admin> findByUserUsername(String username) ;
	
	public Optional<Admin> findByUserMobile(String username) ;
}
