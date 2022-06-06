package com.masai.modelEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
@Data
@Entity
public class TripBooking {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer tripbookingId;
	private String fromLocation;
	private String toLocation;
	private String fromDate;
	private String toDate;
	private String status;
	private Integer bill;

}
