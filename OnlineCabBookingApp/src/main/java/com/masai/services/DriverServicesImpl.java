package com.masai.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.CustomerException;
import com.masai.modelEntity.Driver;
import com.masai.modelEntity.DriverSession;
import com.masai.repository.DriverDAO;
import com.masai.repository.DriverSessionDao;

@Service
public class DriverServicesImpl implements DriverServices {

	@Autowired
	DriverDAO driverDao;
	
	@Autowired
	DriverSessionDao driverSessionDao;

	@Override
	public Driver newDriver(Driver driver) {

		Driver newDriver = driverDao.save(driver);
		return newDriver;
	}

	@Override
	public List<Driver> getAllDriver(String key) {
		
		Optional<DriverSession> opt=driverSessionDao.findByUuid(key);
		if(opt.isEmpty()) {
			System.out.println("driver is not loged  in");
			
			throw new CustomerException("Driver is not loged in ,Please log in first");
		}
		
		
		List<Driver> driverlist = driverDao.findAll();
		if(driverlist.isEmpty()) {
			System.out.println("Ther  is No driver in the list");
			throw new CustomerException("Ther is no Driver in the list");
		}
		return driverlist;
	}

	@Override
	public String removeDriver(String name,String key) {
		Optional<DriverSession> opt=driverSessionDao.findByUuid(key);
		
		if(opt.isEmpty()) {
			System.out.println("Ther  is No driver in the list");
			throw new CustomerException("Ther is no Driver in the list");
		}

		Optional<Driver> driveropt = driverDao.findByUserUsername(name);
		if (driveropt == null) {
			System.out.println("Np suh driver exist");
			throw new CustomerException("Driver not found with this name");
		}
		Driver deletDriver = driveropt.get();

		driverDao.delete(deletDriver);
		return "Driver name :" + name + "deleted from the table";
	}

	@Override
	public Driver updateDriver(Driver driver,String key) {
	Optional<DriverSession> opt=driverSessionDao.findByUuid(key);
		
		if(opt.isEmpty()) {
			System.out.println("Ther  is No driver in the list");
			throw new CustomerException("Ther is no Driver in the list");
		}
		
		Driver updateddriver = driverDao.save(driver);
		return updateddriver;
	}

	

}
