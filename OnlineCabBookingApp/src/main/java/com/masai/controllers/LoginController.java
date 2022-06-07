package com.masai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.masai.DTO.AdminDTO;
import com.masai.DTO.CustomerDTO;
import com.masai.modelEntity.AdminSession;

import com.masai.services.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	
	@Autowired
	private LoginService loginService;
	
	
	//Handler to login a customer
	
		
		
		@PostMapping("/admin")
		public ResponseEntity<AdminSession> loginAdminHandler(@RequestBody AdminDTO dto){
			return new ResponseEntity<>(loginService.loginAdmin(dto), HttpStatus.ACCEPTED);
		}
	
		
		

}
