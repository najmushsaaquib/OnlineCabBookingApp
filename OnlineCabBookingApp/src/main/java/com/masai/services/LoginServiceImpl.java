package com.masai.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import com.masai.modelEntity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.masai.DTO.AdminDTO;
import com.masai.DTO.CustomerDTO;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.modelEntity.AdminSession;
import com.masai.modelEntity.Customer;
import com.masai.modelEntity.UserSession;
import com.masai.repository.AdminDao;
import com.masai.repository.AdminSessionDao;
import com.masai.repository.CustomerDAO;
import com.masai.repository.UserSessionDAO;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserSessionDAO sessionDAO;

	@Autowired
	private CustomerDAO customerDAO;
	@Autowired
	private AdminSessionDao adminSession;
	
	@Autowired
	private AdminDao adminDao;

	@Override
	public UserSession loginCustomer(CustomerDTO customer) {

		Optional<Customer> res = customerDAO.findByUserMobile(customer.getMobile());

		if (!res.isPresent()) {
			System.out.println(res + " Data is empty");
			throw new CustomerException("Customer does not exist with the given mobile number");
		}


		Customer existingCustomer = res.get();
		Optional<UserSession> opt = sessionDAO.findByUserId(existingCustomer.getCustomerId());
		
		if (opt.isPresent())
			throw new LoginException("User already logged in");

		if (existingCustomer.getUser().getPassword().equals(customer.getPassword())) {

			UserSession newSession = new UserSession();

			newSession.setUserId(existingCustomer.getCustomerId());
			newSession.setUserType("Customer");
			newSession.setSessionStartTime(LocalDateTime.now());
			newSession.setSessionEndTime(LocalDateTime.now().plusHours(1));

			UUID uuid = UUID.randomUUID();
			String token = "customer_" + uuid.toString().split("-")[0];

			newSession.setUuid(token);

			return sessionDAO.save(newSession);
		} else {
			throw new LoginException("Password Incorrect. Try again.");
		}

	}

	
	@Override
	public AdminSession loginAdmin(AdminDTO dto) {
		
		Optional<Admin> res = adminDao.findByUserMobile(dto.getMobile()); 
		
		if(res.isEmpty()) {
			System.out.println("No Admin exist");
			throw new CustomerException("Admin was not there in the data base");
		}
		
		 Admin existingAdmin = res.get();
		Optional<AdminSession> opt = adminSession.findByAdminId(existingAdmin.getAdminId());       
		
		if (opt.isPresent())
			throw new LoginException("User already logged in");

		if (existingAdmin.getUser().getPassword().equals(dto.getPassword())) {

			AdminSession newSession = new AdminSession();

			newSession.setAdminId(existingAdmin.getAdminId());
			newSession.setUserType("Customer");
			newSession.setSessionStartTime(LocalDateTime.now());
			newSession.setSessionEndTime(LocalDateTime.now().plusHours(1));

			UUID uuid = UUID.randomUUID();
			String token = uuid.toString().split("-")[0];

			newSession.setUuid(token);

			return adminSession.save(newSession);
		} else {
			throw new LoginException("Password Incorrect. Try again.");
		}
	}



}
