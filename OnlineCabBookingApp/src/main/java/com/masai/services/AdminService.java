package com.masai.services;

import java.util.Optional;

import com.masai.DTO.AdminDTO;
import com.masai.modelEntity.Admin;

public interface AdminService {
	
	public Admin adminRegister(Admin admin);
	public Admin updatePassword(Admin admin, String username);
	public Admin update(Admin admin, String Username);
	public String deleteByUsername(AdminDTO dto);

}
