package com.masai.services;


import com.masai.DTO.AdminDTO;
import com.masai.DTO.CustomerDTO;
import com.masai.modelEntity.AdminSession;
import com.masai.modelEntity.UserSession;


public interface LoginService {

	public UserSession loginCustomer(CustomerDTO customer);
	public AdminSession loginAdmin(AdminDTO dto); 

}
