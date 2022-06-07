package com.masai.modelEntity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;
@Data
@Entity
public class AdminSession {
	@Id
	@SequenceGenerator(name="admin_generator", sequenceName = "admin_seq", allocationSize=10)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_generator")
	private Integer sessionId;
	private Integer adminId;
	private String uuid;
	private String userType;
	private LocalDateTime sessionStartTime;
	private LocalDateTime sessionEndTime;

}
