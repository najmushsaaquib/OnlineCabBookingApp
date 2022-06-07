package com.masai.services;

import java.util.List;

import com.masai.exceptions.CabException;
import com.masai.modelEntity.Cab;

public interface CabService {

	public Cab saveCab(Cab cab);
	
	//public Long countCabsOfType(String cabType);
	
	public List<Cab> getAllCabs() throws CabException;
	
	public Cab deleteCabByCabId(Integer cabId)throws CabException;

	public Cab updateCab(Cab cab)throws CabException;
}
