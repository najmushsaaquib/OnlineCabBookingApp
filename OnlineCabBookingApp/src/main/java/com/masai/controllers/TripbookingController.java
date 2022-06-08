package com.masai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.masai.modelEntity.TripBooking;
import com.masai.services.TripBookingServices;
import com.masai.services.TripBookingServicesImpl;

@Controller
@RequestMapping("/trip")
public class TripbookingController {
	
	@Autowired
	TripBookingServicesImpl tripBookingService;
	
	
	@PostMapping("/booktrip")
	public ResponseEntity<TripBooking> newTrip(@RequestBody TripBooking trip) {
		
		TripBooking newtrip= tripBookingService.newTrip(trip);
		return new ResponseEntity<TripBooking>(newtrip,HttpStatus.CREATED);
	}
}
