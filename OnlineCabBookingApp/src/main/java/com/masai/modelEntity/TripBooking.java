package com.masai.modelEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class TripBooking {
	@Id
	@SequenceGenerator(name = "trip_generator", sequenceName = "trip_seq", allocationSize = 50)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trip_generator")
	private Integer tripbookingId;

//	@NotNull
	private String fromLocation;
//	@NotNull
	private String toLocation;
	private String fromDate;
	private String toDate;

//	@NotNull
	private TripStatus status;

	private Double distanceInKm;

	private Double bill;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@NotNull
	private Customer customer;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@NotNull
	private Driver driver;

}
