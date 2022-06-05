package com.masai.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.modelEntity.Driver;
import com.masai.repository.DriverDAO;
@Service
public class DriverServicesImpl implements DriverServices{

	@Autowired
	DriverDAO driverDao;
	@Override
	public Driver newDriver(Driver driver) {
		
		Driver newDriver=driverDao.save(driver);
		return newDriver;
	}

}
