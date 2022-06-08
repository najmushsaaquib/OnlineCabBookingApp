package com.masai.services;

import java.util.Optional;

import com.masai.DTO.AdminDTO;
import com.masai.modelEntity.Admin;

public interface AdminService {
	
	public Admin adminRegister(Admin admin);
	public Admin updatePassword(AdminDTO dto, String username, String key);
	public Admin update(Admin admin, String Username, String key);
	public String deleteByUsername(AdminDTO dto, String key);
	public String logoutAdmin(String key);
}
