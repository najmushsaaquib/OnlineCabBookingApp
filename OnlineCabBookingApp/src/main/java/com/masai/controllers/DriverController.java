package com.masai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.modelEntity.Driver;
import com.masai.services.DriverServices;
@RestController
@RequestMapping(value = "/driver")
public class DriverController {
	
	@Autowired
	DriverServices  driverServices;
	
	@PostMapping(value = "/register")
	public Driver registerDriver(@RequestBody Driver driver) {
		
		Driver registeredDriver =driverServices.newDriver(driver);
		return registeredDriver;
	}

}
