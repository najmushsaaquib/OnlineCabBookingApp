package com.masai.modelEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
public class Cab {
 
	@Id
	@SequenceGenerator(name="cab_generator", sequenceName = "cab_seq", allocationSize=50)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cab_generator")
	private Integer Id;
	
	private Double perKmRate;
	
	@NotNull
	private CabType cabType;
	
	private String available;
}
