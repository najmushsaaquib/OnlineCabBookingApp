package com.masai.modelEntity;

import javax.persistence.Entity;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Login {

	private Integer loginId;
	private String username;
	private String password;

}
