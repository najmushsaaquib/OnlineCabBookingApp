package com.masai.services;

import java.util.List;

import com.masai.modelEntity.Driver;

public interface DriverServices {

		public Driver newDriver(Driver driver);
		public List<Driver> getAllDriver(String key);
		public String removeDriver(String name,String key);
		public Driver updateDriver(Driver driver,String key);
}
