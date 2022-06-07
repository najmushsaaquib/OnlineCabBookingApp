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

import com.masai.repository.AdminDao;
import com.masai.repository.AdminSessionDao;


@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private AdminSessionDao adminSession;
	
	@Autowired
	private AdminDao adminDao;

	
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
