package com.masai.controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.modelEntity.Customer;
import com.masai.repository.CustomerDTO;
import com.masai.services.CustomerService;
@RestController
@RequestMapping(value="/customer")
public class CustomerController  {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/register")
	public ResponseEntity<Customer> addNewCustomerHandler(@RequestBody Customer customer){
		
             Customer savedCustomer= customerService.addNewCustomer(customer);
	
		     return new ResponseEntity<Customer>(savedCustomer,HttpStatus.CREATED);
		
	}	
	
	@GetMapping("/{customerId}")
	public Customer getCustomerByIdHandler(@PathVariable("customerId") Integer customerId) {
		return customerService.getCustomerById(customerId);
		
	}

	
	@GetMapping()
	public List<Customer> getAllCustomerHandler(){
		List<Customer> customers = customerService.getAllCustomers();
		return customers;
		
	}
	
	@DeleteMapping("/delete/{customerId}")
	public Customer deleteCustomerHandler(@PathVariable("customerId") Integer customerId) {
		return customerService.deleteStudentById(customerId);
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<Customer> updateCustomerHandler( @RequestBody Customer customer){
		Customer updatedCustomer = customerService.updateCustomer(customer);
		return new ResponseEntity<Customer>(updatedCustomer,HttpStatus.ACCEPTED);
		
	} 

	@PutMapping("password/{customerId}/{password}")
	public ResponseEntity<Customer> updateCustomerPassword(@PathVariable Integer  customerId,@PathVariable String password ){
		
		Customer updatedCustomer = customerService.updateCustomerPassword(customerId, password);
		
		return new ResponseEntity<>(updatedCustomer,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/name/{username}")
	public List<Customer> getCustomerByUserName(@PathVariable  String username){
		List<Customer> cust = customerService.getCustomersByUserName(username);
		return cust;
		
	}
	
//	@PutMapping("customern/{customerId}/{customerName}")
//	public ResponseEntity<Customer> updateCustomerName(@PathVariable Integer  customerId,@PathVariable String customerName ){
//		
//		Customer updatedCustomer = customerService.updateCustomerName(customerId, customerName);
//		
//		return new ResponseEntity<>(updatedCustomer,HttpStatus.ACCEPTED);
//	}
//	
//	@PutMapping("customerm/{customerId}/{mobile}")
//	public ResponseEntity<Customer> updateCustomerMobileNumber(@PathVariable Integer  customerId,@PathVariable String mobile ){
//		
//		Customer updatedCustomer = customerService.updateCustomerMobileNumber(customerId, mobile);
//		
//		return new ResponseEntity<>(updatedCustomer,HttpStatus.ACCEPTED);
//	}
//	
//	@PutMapping("customeru/{customerId}/{username}")
//	public ResponseEntity<Customer> updateCustomerUserNeme(@PathVariable Integer  customerId,@PathVariable String username ){
//		
//		Customer updatedCustomer = customerService.updateCustomerUserName(customerId, username);
//		
//		return new ResponseEntity<>(updatedCustomer,HttpStatus.ACCEPTED);
//	}
//	

//	
//	@PutMapping("customere/{customerId}/{email}")
//	public ResponseEntity<Customer> updateCustomerEmail(@PathVariable Integer  customerId,@PathVariable String email ){
//		
//		Customer updatedCustomer = customerService.updateCustomerEmail(customerId, email);
//		
//		return new ResponseEntity<>(updatedCustomer,HttpStatus.ACCEPTED);
//	}
//	
//	@PutMapping("customera/{customerId}/{address}")
//	public ResponseEntity<Customer> updateCustomerAddress(@PathVariable Integer  customerId,@PathVariable String address ){
//		
//		Customer updatedCustomer = customerService.updateCustomerAddress(customerId, address);
//		
//		return new ResponseEntity<>(updatedCustomer,HttpStatus.ACCEPTED);
//	}
	
	
}
