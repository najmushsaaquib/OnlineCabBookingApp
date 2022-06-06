package com.masai.repository;





import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.masai.modelEntity.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {

	
//	public Optional<Customer> findById(Integer customerId);
	public List<Customer> findByUserUsername(String username);
//	Customer getCustomerById(Integer customerId);
	
}
