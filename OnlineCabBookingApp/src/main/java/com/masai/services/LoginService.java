package com.masai.services;

import com.masai.DTO.AdminDTO;
import com.masai.DTO.CustomerDTO;
import com.masai.DTO.DriverDTO;
import com.masai.DTO.LoginDTO;
import com.masai.modelEntity.AdminSession;
import com.masai.modelEntity.DriverSession;
import com.masai.DTO.CustomerDTO;
import com.masai.modelEntity.UserSession;

public interface LoginService {

	public AdminSession loginAdmin(LoginDTO dto);

	public UserSession loginCustomer(LoginDTO customer);
	
	public DriverSession loginDriver(LoginDTO dto);

}
