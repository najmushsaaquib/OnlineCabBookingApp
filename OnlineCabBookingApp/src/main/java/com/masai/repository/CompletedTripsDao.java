package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.modelEntity.CompletedTrips;

public interface CompletedTripsDao extends JpaRepository<CompletedTrips, Integer>{
	
	 public List<CompletedTrips> findByCustomerId(Integer customerId); 
	
}
