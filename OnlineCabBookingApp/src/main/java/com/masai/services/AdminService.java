package com.masai.services;

import java.util.List;
import java.util.Optional;

import com.masai.DTO.AdminDTO;
import com.masai.exceptions.AdminException;
import com.masai.exceptions.LoginException;
import com.masai.modelEntity.Admin;
import com.masai.modelEntity.CompletedTrips;

public interface AdminService {

	public Admin adminRegister(Admin admin) throws AdminException;

	public Admin updatePassword(AdminDTO dto, String username, String key);

	public Admin update(Admin admin, String Username, String key);

	public String deleteByUsername(AdminDTO dto, String key);

	public String logoutAdmin(String key) throws LoginException;
	
	public List<CompletedTrips> getTripsByCustomerId(Integer customerId, String key);
}
