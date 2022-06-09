package com.masai.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import com.masai.modelEntity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.masai.DTO.AdminDTO;
import com.masai.DTO.CustomerDTO;
import com.masai.DTO.DriverDTO;
import com.masai.DTO.LoginDTO;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.modelEntity.AdminSession;

import com.masai.repository.AdminDao;
import com.masai.repository.AdminSessionDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.masai.DTO.CustomerDTO;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.modelEntity.Customer;
import com.masai.modelEntity.Driver;
import com.masai.modelEntity.DriverSession;
import com.masai.modelEntity.UserSession;
import com.masai.repository.CustomerDAO;
import com.masai.repository.DriverDAO;
import com.masai.repository.DriverSessionDao;
import com.masai.repository.UserSessionDAO;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private AdminSessionDao adminSession;

	@Autowired
	private UserSessionDAO sessionDAO;

	@Autowired
	private CustomerDAO customerDAO;

	@Autowired
	private AdminDao adminDao;

	@Autowired
	DriverSessionDao driverSessionDao;

	@Autowired
	DriverDAO driverDAO;

	@Override
	public UserSession loginCustomer(LoginDTO customer) {

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
			String token = uuid.toString().split("-")[0];

			newSession.setUuid(token);

			return sessionDAO.save(newSession);
		} else {
			throw new LoginException("Password Incorrect. Try again.");
		}

	}

	@Override
	public AdminSession loginAdmin(LoginDTO dto) {
		Optional<Admin> res = adminDao.findByUserMobile(dto.getMobile());

		if (res.isEmpty()) {
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

	@Override
	public DriverSession loginDriver(LoginDTO driver) {
		Optional<Driver> res = driverDAO.findByUserMobile(driver.getMobile());

		if (!res.isPresent()) {
			System.out.println(res + " Data is empty");
			throw new CustomerException("Driver does not exist with the given mobile number");
		}

		Driver existingDriver = res.get();
		Optional<DriverSession> opt = driverSessionDao.findByDriverId(existingDriver.getDriverId());

		if (opt.isPresent())
			throw new LoginException("User already logged in");

		if (existingDriver.getUser().getPassword().equals(driver.getPassword())) {

			DriverSession newSession = new DriverSession();

			newSession.setDriverId(existingDriver.getDriverId());
			newSession.setUserType("Driver");
			newSession.setSessionStartTime(LocalDateTime.now());
			newSession.setSessionEndTime(LocalDateTime.now().plusHours(1));

			UUID uuid = UUID.randomUUID();
			String token = uuid.toString().split("-")[0];

			newSession.setUuid(token);

			return driverSessionDao.save(newSession);
		} else {
			throw new LoginException("Password Incorrect. Try again.");
		}
	}

}
