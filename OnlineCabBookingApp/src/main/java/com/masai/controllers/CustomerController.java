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
import com.masai.exceptions.CustomerException;
import com.masai.modelEntity.Customer;
import com.masai.modelEntity.Driver;
import com.masai.modelEntity.TripBooking;
import com.masai.services.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@PostMapping("/register")
	public Customer registerCustomer(@RequestBody Customer user) {

		Customer newUser = customerService.register(user);

		return newUser;

	}

	@GetMapping("/all")
	public List<Customer> getAllCustomer() {

		List<Customer> list = customerService.getCustomer();

		if (list.isEmpty())
			throw new CustomerException("There is no customer found in the database.");

		return list;

	}

	@PatchMapping("/update/{mobile}")
	public Customer UserUpdatePassword(@RequestBody CustomerDTO dto, @PathVariable("mobile") String mobile,
			@RequestParam String key) {
		return customerService.updatePassword(dto, mobile, key);
	}

	@DeleteMapping("/delete")
	public String userDelete(@RequestBody CustomerDTO dto, @RequestParam String key) {
		return customerService.deleteCustomer(dto, key);

	}

	@PutMapping("/update/{mobile}")
	public Customer adminUpdate(@RequestBody Customer customer, @PathVariable("mobile") String mobile,
			@RequestParam String key) {
		return customerService.updateCustomer(customer, mobile, key);
	}

	@GetMapping("/availableCabs")
	public List<Driver> availableDrivers() {

		return customerService.getAvailableDrivers();

	}
	
	@GetMapping("/allcabs")
	public List<Driver> getListForAll(){
		return customerService.generalListOfDrivers();
	}

	@PostMapping("/booktrip")
	public ResponseEntity<TripBooking> bookTrip(@RequestBody TripBooking trip, @RequestParam String key) {

		TripBooking bookedTrip = customerService.bookTrip(trip, key);

		ResponseEntity<TripBooking> confirmed = new ResponseEntity<TripBooking>(bookedTrip, HttpStatus.CREATED);

		return confirmed;
	}

}
