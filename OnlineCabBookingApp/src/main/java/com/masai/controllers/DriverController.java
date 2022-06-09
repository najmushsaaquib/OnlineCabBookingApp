package com.masai.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.DTO.DriverDTO;
import com.masai.DTO.LoginDTO;
import com.masai.modelEntity.Driver;
import com.masai.modelEntity.DriverSession;
import com.masai.services.DriverServices;
import com.masai.services.LoginService;


@RestController
@RequestMapping(value = "/driver")
public class DriverController {
	
	@Autowired
	DriverServices  driverServices;
	

	@Autowired
	private LoginService loginService;
	
	
	@PostMapping("/login")
	public ResponseEntity<DriverSession> loginDriver(@RequestBody LoginDTO dto) {
		return new ResponseEntity<>(loginService.loginDriver(dto), HttpStatus.ACCEPTED);
	}
	
	
	@PostMapping(value = "/register")
	public Driver registerDriver(@RequestBody Driver driver) {
		
		Driver registeredDriver =driverServices.newDriver(driver);
		return registeredDriver;
	}
	@GetMapping(value = "/driverlist")
	public List<Driver> getAllDrivers(@RequestParam String key){
		
		List<Driver> driverlist=driverServices.getAllDriver(key);
		return driverlist;
	}
	
	@DeleteMapping(value = "/delete/{username}")
	public  String deleteDriverByUsername(@PathVariable("username") String name,@RequestParam String key) {
		
		String str=driverServices.removeDriver(name,key);
		
		return str;
	}
	
	@PutMapping(value = "/update")
	public Driver updateDriver(@RequestBody Driver driver,@RequestParam String key) {
		
		Driver updateddriver=driverServices.updateDriver(driver,key);
		return updateddriver;
	}
	
	@PatchMapping("/status/{?}")
	public String updateStatus(@RequestParam String key, @PathVariable("?") String newStatus) {
		return driverServices.updateStatus(newStatus, key);
		
	}
	
	@GetMapping("/logout")
	public String logoutDriver(@RequestParam String key) {

		return driverServices.logoutDriver(key);

	}
	
	@GetMapping(value ="/bestdriver")
	public List<Driver> getBestDriver(@RequestParam String key,@RequestParam  float rating){
		
		return driverServices.viewBestDriver(key,rating);
	}

}
