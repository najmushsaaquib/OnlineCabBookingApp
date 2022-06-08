package com.masai.modelEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;
@Data
@Entity
public class TripBooking {
	@Id
	@SequenceGenerator(name="trip_generator", sequenceName = "trip_seq", allocationSize=10)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trip_generator")
	private Integer tripbookingId;
	private String fromLocation;
	private String toLocation;
	private String fromDate;
	private String toDate;
	private String status;
	private Integer bill;
	
//	private Customer customer;
//	
//	private Cab cab;

}
