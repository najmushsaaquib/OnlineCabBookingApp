package com.masai.modelEntity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Data
@Entity
public class CompletedTrips {
	@Id
	@SequenceGenerator(name = "trip_generator", sequenceName = "trip_seq", allocationSize = 50)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trip_generator")
	private Integer completedTripsId;
	private Integer customerId;
	private Integer driverId;
	private Integer tripbookingid;
	private String fromLocation;
	private String toLocation;
	private LocalDate fromDate;
	private LocalDate toDate;
	private Double bill;
	private Double distanceInKM;
	@Enumerated
	private TripStatus status;

}
