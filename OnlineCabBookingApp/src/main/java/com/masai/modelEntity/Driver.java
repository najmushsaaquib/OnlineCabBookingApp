package com.masai.modelEntity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
	@Id
	@SequenceGenerator(name="driver_generator", sequenceName = "driver_seq", allocationSize=10)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "driver_generator")
	private Integer driverId; 
	
	private Integer rating;
	
	@Column(unique = true)
	@NotNull
	private String licenceId;
	
	
	
	@Embedded
	private ModelUser user;

}
