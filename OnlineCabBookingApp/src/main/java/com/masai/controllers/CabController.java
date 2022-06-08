package com.masai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.modelEntity.Cab;
import com.masai.services.CabServices;

@RestController
@RequestMapping("/cab")
public class CabController {
	
	@Autowired
	CabServices cabServices;
	
	@PostMapping("/addcab")
	public Cab newCab(@RequestBody Cab cab) {
		
		Cab newCab=cabServices.addNewcab(cab);
		
		return newCab;
	}
	
}
