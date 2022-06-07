package com.masai.services;



import com.masai.DTO.AdminDTO;
import com.masai.DTO.CustomerDTO;
import com.masai.modelEntity.AdminSession;
import com.masai.DTO.CustomerDTO;
import com.masai.modelEntity.UserSession;


public interface LoginService {

	public AdminSession loginAdmin(AdminDTO dto); 
	public UserSession loginCustomer(CustomerDTO customer);




}
