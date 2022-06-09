package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.modelEntity.CompletedTrips;

public interface CompletedTripsDao extends JpaRepository<CompletedTrips, Integer>{

}
