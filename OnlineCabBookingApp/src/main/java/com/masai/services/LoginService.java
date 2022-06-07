package com.masai.services;


import com.masai.DTO.AdminDTO;
import com.masai.DTO.CustomerDTO;
import com.masai.modelEntity.AdminSession;



public interface LoginService {

	public AdminSession loginAdmin(AdminDTO dto); 

}
