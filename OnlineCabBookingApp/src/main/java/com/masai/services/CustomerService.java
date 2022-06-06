package com.masai.services;


import java.util.List;

import com.masai.modelEntity.Customer;


public interface CustomerService {

	public Customer register(Customer user);
	
	public List<Customer> getCustomer();

}
