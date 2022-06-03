package com.masai.modelEntity;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Driver extends ModelUser{

	@Id
	private Integer driverId;
	private Double rating;
	private Integer licenseId;
	
	
	
	
}
