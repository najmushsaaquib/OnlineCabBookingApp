package com.masai.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.CustomerException;
import com.masai.modelEntity.Customer;
import com.masai.repository.CustomerDAO;
import com.masai.DTO.CustomerDTO;
import com.masai.exceptions.CustomerException;
import com.masai.modelEntity.Customer;
import com.masai.modelEntity.ModelUser;
import com.masai.modelEntity.UserSession;
import com.masai.repository.CustomerDAO;
import com.masai.repository.UserSessionDAO;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDAO customerDAO;

	@Autowired
	UserSessionDAO userSessionDao;
	@Override
	public Customer register(Customer user) {

		Optional<Customer> existing = customerDAO.findByUserMobile(user.getUser().getMobile());

		if (existing.isPresent()) {

			System.out.println("Customer is already present");
			throw new CustomerException("A Customer already exist with this mobile number in the Database");
		}

		return customerDAO.save(user);

	}

	@Override
	public List<Customer> getCustomer() {

		List<Customer> list = customerDAO.findAll();

		return list;
	}

	@Override
	public Customer updatePassword(CustomerDTO dto, String mobile, String key) {
		Optional<UserSession> otp = userSessionDao.findByUuid(key);
		Customer updated= null;
		if(otp.isEmpty()) throw new CustomerException("User is not logged in, Please login first!");
		Optional<Customer> opt = customerDAO.findByUserMobile(mobile);
		if (opt.isEmpty()) throw new CustomerException("Username not found");
		else {
			Customer toUpdate = opt.get();
			Integer id = toUpdate.getCustomerId();
			ModelUser user = toUpdate.getUser();
			user.setPassword(dto.getPassword());
			Customer newOne = new Customer(user, id);
			updated= customerDAO.save(newOne);
		}
		return updated;
		
	}

	@Override
	public String deleteCustomer(CustomerDTO dto, String key) {
		
		Optional<UserSession> otp=userSessionDao.findByUuid(key);
		if(otp.isEmpty()) throw new CustomerException("User is not logged in, Please login first!");
		else {
		Optional<Customer> opt= customerDAO.findByUserMobile(dto.getMobile());
		if(opt.isEmpty())  throw new CustomerException("Username not found");
		else {
			Customer toBeDelete=opt.get();
			customerDAO.delete( toBeDelete);
		}
		}
		return "Your Id with Username "+dto.getMobile()+" is Deleted.";
	}

	@Override
	public Customer updateCustomer(Customer customer,String mobile, String key) {
         
		Customer updated =null;
		
		Optional<UserSession> otp=userSessionDao.findByUuid(key);
		
		if(otp.isEmpty()) throw new CustomerException("User is not logged in, Please login first!");
		else {
		Optional<Customer> opt=customerDAO.findByUserMobile(mobile);
		if(opt.isEmpty()) throw new CustomerException("Username not found");
		else{
			Customer toUpdate = opt.get();
			Integer id = toUpdate.getCustomerId();
			Customer newOne = new Customer(customer.getUser(),id);
			updated= customerDAO.save(newOne);
		}
		}
		return updated;     
	}

	

}
