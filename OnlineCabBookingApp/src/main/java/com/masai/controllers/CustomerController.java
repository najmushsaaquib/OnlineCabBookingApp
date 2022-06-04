package com.masai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.modelEntity.Customer;
import com.masai.services.CustomerService;
import com.masai.services.CustomerServiceImpl;

@RestController
public class CustomerController {
	
	@Autowired
	CustomerService customer;

	@PostMapping("/register")
	public Customer registerCustomer(@RequestBody Customer user) {

		Customer newUser = customer.register(user);

		return newUser;

	}

}
