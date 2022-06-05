package com.masai.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.modelEntity.Customer;
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

		return list;

	}

}
