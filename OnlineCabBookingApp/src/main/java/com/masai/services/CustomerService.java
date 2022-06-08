package com.masai.services;


import java.util.List;

import org.springframework.http.ResponseEntity;

import com.masai.DTO.CustomerDTO;

import com.masai.modelEntity.Customer;
import com.masai.modelEntity.Driver;
import com.masai.modelEntity.TripBooking;


public interface CustomerService {

	public Customer register(Customer user);
	
	public List<Customer> getCustomer();
    
	public Customer updatePassword(CustomerDTO dto, String mobile, String key);
	
	public String deleteCustomer(CustomerDTO dto, String key);
	
	public Customer updateCustomer(Customer customer,String mobile, String key);
//	public TripBooking addTrip();
	
	public List<Driver> getAvailableDrivers();
	
	public List<Driver> generalListOfDrivers();
	
	public TripBooking bookTrip(TripBooking trip, String key  );  
	
	public String logoutCustomer(String key);
}
