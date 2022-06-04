package com.masai.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.modelEntity.Customer;
import com.masai.repository.CustomerDAO;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDAO customer;

	@Override
	public Customer register(Customer user) {

		Customer newUser = customer.save(user);

		return newUser;
	}

}
