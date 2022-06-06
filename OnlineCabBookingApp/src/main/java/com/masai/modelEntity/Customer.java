package com.masai.modelEntity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Data
public class Customer   {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	@Embedded
	private ModelUser user;

	
	
	
	
	
	
	
}