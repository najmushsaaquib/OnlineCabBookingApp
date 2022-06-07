package com.masai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.masai.DTO.DriverDTO;
import com.masai.modelEntity.DriverSession;
import com.masai.services.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	
	@Autowired
	private LoginService loginService;
	
	
	//Handler to login a customer
	
		
		
		@PostMapping("/driver")
		public ResponseEntity<DriverSession> loginAdminHandler(@RequestBody DriverDTO dto){
			return new ResponseEntity<>(loginService.loginDriver(dto), HttpStatus.ACCEPTED);
		}
		
	
		
		

}
