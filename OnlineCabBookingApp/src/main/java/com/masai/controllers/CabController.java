package com.masai.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.masai.modelEntity.Cab;
import com.masai.services.CabService;

@RestController
public class CabController {

	@Autowired
	private CabService cService;
	
	@PostMapping("/cabs")
	public ResponseEntity<Cab> saveCabHandler(@RequestBody Cab cab){
		
		Cab savedCab =  cService.saveCab(cab);
	
		return new ResponseEntity<Cab>(savedCab,HttpStatus.CREATED);
	}
	
//	@GetMapping("/cabs/{cabType}")
//	public ResponseEntity<Long> countCabsOfTypeHandler(@PathVariable("cabType") String cabType){
//		
//		Long count = cService.countCabsOfType(cabType);
//		
//		return new ResponseEntity<Long>(count,HttpStatus.OK);
//	}
	

	@GetMapping("/cabs")
	public ResponseEntity<List<Cab>> getAllCabsHandler(){
		
		List<Cab> cabs = cService.getAllCabs();
		
		return new ResponseEntity<List<Cab>>(cabs,HttpStatus.OK);
	}
	
	@DeleteMapping("/cabs/{cabId}")
	public Cab deleteCabHandler(@PathVariable("cabId") Integer cabId) {
		
		return cService.deleteCabByCabId(cabId);
		
		
	}
	
	@PutMapping("/cabs")
	public ResponseEntity<Cab> updateCabHandler(@RequestBody Cab cab){
		
		Cab updatedCab = cService.updateCab(cab);
	
		return new ResponseEntity<Cab>(updatedCab,HttpStatus.ACCEPTED);
	}

}
