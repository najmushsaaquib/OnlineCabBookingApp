package com.masai.services;


import java.util.List;

import com.masai.DTO.CustomerDTO;

import com.masai.modelEntity.Customer;


public interface CustomerService {

	public Customer register(Customer user);
	
	public List<Customer> getCustomer();
    
	public Customer updatePassword(CustomerDTO dto, String mobile, String key);
	
	public String deleteCustomer(CustomerDTO dto, String key);
	
	public Customer updateCustomer(Customer customer,String mobile, String key);
	
}
