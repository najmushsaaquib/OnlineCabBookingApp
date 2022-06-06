package com.masai.controllers;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.DTO.AdminDTO;
import com.masai.modelEntity.Admin;
import com.masai.services.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@PostMapping("/register")
	public Admin adminRegister(@RequestBody Admin admin) {
		return adminService.adminRegister(admin);
		
	}
	@PatchMapping("/update/{username}")
	public Admin adminUpdatePassword(@RequestBody AdminDTO dto, @PathVariable ("username" ) String username, @RequestParam String key) {
		return adminService.updatePassword(dto, username, key);
	}
	
	@PutMapping("/update/{username}")
	public Admin adminUpdate(@RequestBody Admin admin, @PathVariable ("username") String username, @RequestParam String key) {
		return adminService.update(admin, username, key);
	}
	
	@DeleteMapping("/delete")
	public String adminDelete(@RequestBody AdminDTO dto, @RequestParam String key) {
		return adminService.deleteByUsername(dto, key);
		
	}
	
	
	

}
