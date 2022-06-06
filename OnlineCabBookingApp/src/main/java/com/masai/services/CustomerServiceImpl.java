package com.masai.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import com.masai.Exception.CustomerException;

import com.masai.modelEntity.Customer;
import com.masai.modelEntity.ModelUser;
import com.masai.repository.CustomerDTO;
import com.masai.repository.CustomerDao;



@Service
public  class CustomerServiceImpl implements CustomerService{
    
	@Autowired
	CustomerDao cDao;
	
	@Override
	public Customer addNewCustomer(Customer customer) {
		Customer newCustomer = cDao.save(customer);
		return newCustomer;
	}
	@Override
	public Customer getCustomerById(Integer customerId) {
		
		Optional<Customer> opt= cDao.findById(customerId);
		
		if(opt.isPresent()) {
			
			Customer customer= opt.get();
			return customer;
			
		}
		return cDao.findById(customerId).orElseThrow(() -> new CustomerException("Customer does not exist with Roll :"+customerId));

	
}
	@Override
	public List<Customer> getAllCustomers() {
		
		List<Customer> customers = cDao.findAll();
		if(customers.size() > 0) {
			return customers;
		}
		return null;
	}
	@Override
	public Customer deleteStudentById(Integer customerId) {
		Customer existingCustomer = cDao.findById(customerId).orElseThrow(()-> new CustomerException("Customer Does Not Exist of Customer Id "+customerId));
		cDao.delete(existingCustomer);
		return existingCustomer;
	}

	@Override
	public Customer updateCustomer(Customer customer) throws CustomerException {
		Optional<Customer> opt = cDao.findById(customer.getCustomerId());
		if(opt.isPresent()) {
			
			Customer cust = opt.get();
			return cDao.save(customer);
		}
		else
			throw new CustomerException("Invalid Customer Details..");
	}
	
	@Override
	public Customer updateCustomerPassword(Integer customerId, String password) throws CustomerException {
		Optional<Customer> opt = cDao.findById(customerId);
		if(opt.isPresent()) {
			Customer cust = opt.get();
			ModelUser mo =cust.getUser();
			mo.setPassword(password);
			return cust;
		}
		else
			throw new CustomerException("Customer Does Not Exist of Customer Id "+customerId);
	}
	@Override
	public List<Customer> getCustomersByUserName(String userName) throws CustomerException {
		List<Customer> opt= cDao.findByUserUsername(userName);
		if(opt.isEmpty()) ;
		
		return opt;
		
		
	}
	

	
	
	
	
//	@Override
//	public Customer updateCustomerName(Integer customerId, String customerName) throws CustomerException {
//		
//		Optional<Customer> opt = cDao.findById(customerId);
//		if(opt.isPresent()) {
//			Customer cust = opt.get();
//			cust.setCustomerName(customerName);
//			return cust;
//		}
//		else
//			throw new CustomerException("Customer Does Not Exist of Customer Id "+customerId);
//	}
//	@Override
//	public Customer updateCustomerAddress(Integer customerId, ModelUser address) throws CustomerException {
//		Optional<Customer> opt = cDao.findById(customerId);
//		if(opt.isPresent()) {
//			Customer cust = opt.get();
//			cust.setUser(address);
//			return cust;
//		}
//		else
//			throw new CustomerException("Customer Does Not Exist of Customer Id "+customerId);
//	}
//	@Override
//	public Customer updateCustomerMobileNumber(Integer customerId, String mobile) throws CustomerException {
//		Optional<Customer> opt = cDao.findById(customerId);
//		if(opt.isPresent()) {
//			Customer cust = opt.get();
//			cust.setMobile(mobile);
//			return cust;
//		}
//		else
//			throw new CustomerException("Customer Does Not Exist of Customer Id "+customerId);
//	}
//	@Override
//	public Customer updateCustomerUserName(Integer customerId, String username) throws CustomerException {
//		Optional<Customer> opt = cDao.findById(customerId);
//		if(opt.isPresent()) {
//			Customer cust = opt.get();
//			cust.setUsername(username);
//			return cust;
//		}
//		else
//			throw new CustomerException("Customer Does Not Exist of Customer Id "+customerId);
//	}
//	@Override
//	public Customer updateCustomerEmail(Integer customerId, String email) throws CustomerException {
//		Optional<Customer> opt = cDao.findById(customerId);
//		if(opt.isPresent()) {
//			Customer cust = opt.get();
//			cust.setEmail(email);
//			return cust;
//		}
//		else
//			throw new CustomerException("Customer Does Not Exist of Customer Id "+customerId);
//	}
	
}
