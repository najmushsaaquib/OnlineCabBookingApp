package com.masai.services;

import java.util.List;
import java.util.Optional;

import com.masai.Exception.CustomerException;
import com.masai.modelEntity.Customer;
import com.masai.modelEntity.ModelUser;
import com.masai.repository.CustomerDTO;

public interface CustomerService {
    

	public Customer addNewCustomer(Customer customer);
	
	public Customer getCustomerById(Integer customerId) throws CustomerException;
	
	public List<Customer> getAllCustomers() throws CustomerException;

	public Customer deleteStudentById(Integer customerId) throws CustomerException;
	
	public Customer updateCustomer(Customer customer) throws CustomerException;

	public Customer updateCustomerPassword(Integer customerId,String password) throws CustomerException;

	public List<Customer> getCustomersByUserName(String userName) throws CustomerException ;
	
	

}
