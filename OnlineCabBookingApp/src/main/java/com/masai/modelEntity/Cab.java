package com.masai.modelEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
public class Cab {
 
	@Id
	@SequenceGenerator(name="cab_generator", sequenceName = "cab_seq", allocationSize=10)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cab_generator")
	private Integer cabId;
	private Double perKmRate;
	private String cabType;
	
	@OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "driverId")
	private  Driver driver;
}
