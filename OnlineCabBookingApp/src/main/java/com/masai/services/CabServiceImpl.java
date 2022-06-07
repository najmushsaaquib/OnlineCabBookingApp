package com.masai.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.CabException;
import com.masai.modelEntity.Cab;
import com.masai.repository.CabDao;



@Service
public class CabServiceImpl implements CabService{

	@Autowired
	private CabDao cDao;
	
	@Override
	public Cab saveCab(Cab cab) {
		
		return cDao.save(cab);
	}

//	@Override
//	public Long countCabsOfType(String cabType) {
//		
//		return cDao.count();
//	}

	
	@Override
	public List<Cab> getAllCabs() throws CabException {
		
		List<Cab> cabs = cDao.findAll();
		
		if(cabs.size()>0) {
			return cabs;
		}else {
			throw new CabException("No cabs found..");
		}
	}

	@Override
	public Cab deleteCabByCabId(Integer cabId) throws CabException {
		
		Cab existingCab =  cDao.findById(cabId).orElseThrow(()->new CabException("Cab does not exist with cabId :"+cabId));
		
	    cDao.delete(existingCab);
	    
	    return existingCab;
	}

	@Override
	public Cab updateCab(Cab cab) throws CabException {
		
	   Optional<Cab> opt = cDao.findById(cab.getCabId());
	
	   if(opt.isPresent()) {
		  Cab existingcab =  opt.get();
		  
		  return cDao.save(cab);
	   }else
		   throw new CabException("Invalid cab details..");
	
	
	}

	
	
}
