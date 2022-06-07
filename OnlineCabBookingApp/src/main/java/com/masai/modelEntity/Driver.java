package com.masai.modelEntity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
public class Driver {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer driverId; 
	private Integer rating;
	private String licenceId;
	
	
	@Embedded
	private ModelUser user;
	
//	@OneToOne(mappedBy = "driver")
//	private Cab cab;
}
