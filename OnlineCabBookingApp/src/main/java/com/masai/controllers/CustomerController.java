package com.masai.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.masai.DTO.CustomerDTO;
import com.masai.DTO.LoginDTO;
import com.masai.exceptions.CustomerException;
import com.masai.modelEntity.CompletedTrips;
import com.masai.modelEntity.Customer;
import com.masai.modelEntity.Driver;
import com.masai.modelEntity.TripBooking;
import com.masai.modelEntity.UserSession;
import com.masai.services.CustomerService;
import com.masai.services.LoginService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@Autowired
	private LoginService loginService;

	@PostMapping("/login")
	public ResponseEntity<UserSession> loginCustomer(@RequestBody LoginDTO customerdto) {
		return new ResponseEntity<>(loginService.loginCustomer(customerdto), HttpStatus.ACCEPTED);
	}

	@PostMapping("/register")
	public Customer registerCustomer(@RequestBody Customer user) {
		Customer newUser = customerService.register(user);
		return newUser;

	}

	@GetMapping("/customerlist")
	public List<Customer> getAllCustomer() {
		List<Customer> list = customerService.getCustomer();
		if (list.isEmpty())
			throw new CustomerException("There is no customer found in the database.");
		return list;
	}

	@PatchMapping("/updatepassword/{mobile}")
	public Customer updateCustomerPasswordByMobile(@RequestBody CustomerDTO dto, @PathVariable("mobile") String mobile,
			@RequestParam String key) {
		return customerService.updatePassword(dto, mobile, key);
	}

	@DeleteMapping("/delete")
	public String deleteCustomer(@RequestBody CustomerDTO dto, @RequestParam String key) {
		return customerService.deleteCustomer(dto, key);

	}

	@PutMapping("/update/{mobile}")
	public Customer updateCustomerByMobile(@RequestBody Customer customer, @PathVariable("mobile") String mobile,
			@RequestParam String key) {
		return customerService.updateCustomer(customer, mobile, key);
	}

	@GetMapping("/availablecabs")
	public List<Driver> availableCabs() {

		return customerService.getAvailableDrivers();

	}

	@GetMapping("/allcabs")
	public List<Driver> getListForAllCabs() {
		return customerService.generalListOfDrivers();
	}

	@PostMapping("/booktrip")
	public ResponseEntity<TripBooking> bookTrip(@RequestBody TripBooking trip, @RequestParam String key) {

		TripBooking bookedTrip = customerService.bookTrip(trip, key);

		ResponseEntity<TripBooking> confirmed = new ResponseEntity<TripBooking>(bookedTrip, HttpStatus.CREATED);

		return confirmed;
	}

	@GetMapping("/logout")
	public String logoutCustomer(@RequestParam String key) {

		return customerService.logoutCustomer(key);
	}

	@DeleteMapping("/complete/{tripid}")
	public String completeTrip(@RequestParam String key, @PathVariable("tripid") Integer tripId) {
		return customerService.completeTrip(key, tripId);
	}

	@DeleteMapping("/canceltrip")
	public String cancelTrip(@RequestParam String key, @RequestParam Integer tripId) {
		return customerService.cancelTrip(key, tripId);
	}

	@GetMapping("/checkhistory")
	public List<CompletedTrips> getYourTripHistory(@RequestParam String key) {
		return customerService.alltripHistory(key);
	}

	@PostMapping("/updatetrip")
	public ResponseEntity<TripBooking> updateTrip(@RequestBody TripBooking trip, @RequestParam String key ){
		
		TripBooking updatedTrip =  customerService.updateTrip(trip,key);
		 return new ResponseEntity<>(updatedTrip, HttpStatus.OK);
		
	}
}
