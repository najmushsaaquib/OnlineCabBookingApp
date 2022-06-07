package com.masai.modelEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cab {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cabId;
	private String cabType;
	private Float perkmRate;
	
	public Cab() {
		// TODO Auto-generated constructor stub
	}

	public Cab(Integer cabId, String cabType, Float perkmRate) {
		super();
		this.cabId = cabId;
		this.cabType = cabType;
		this.perkmRate = perkmRate;
	}

	public Integer getCabId() {
		return cabId;
	}

	public void setCabId(Integer cabId) {
		this.cabId = cabId;
	}

	public String getCabType() {
		return cabType;
	}

	public void setCabType(String cabType) {
		this.cabType = cabType;
	}

	public Float getPerkmRate() {
		return perkmRate;
	}

	public void setPerkmRate(Float perkmRate) {
		this.perkmRate = perkmRate;
	}
	
	
}
