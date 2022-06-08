package com.masai.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.modelEntity.TripBooking;
@Repository
public interface TripbookingDao extends JpaRepository<TripBooking, Integer>{
	

}
