package com.masai.services;


import com.masai.DTO.CustomerDTO;
import com.masai.modelEntity.UserSession;


public interface LoginService {

	public UserSession loginCustomer(CustomerDTO customer);

}
