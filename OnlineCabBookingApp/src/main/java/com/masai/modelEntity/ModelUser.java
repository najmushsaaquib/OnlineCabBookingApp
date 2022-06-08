package com.masai.modelEntity;


import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ModelUser {
	
	@NotNull(message = "Username cannot be NULL")
	@Pattern(regexp = "^[a-zA-Z0-9]{3,25}", message = "length must be >=3")
	private String username;

	
	@NotNull(message = "Please enter the password")
	@Pattern(regexp = "[A-Za-z0-9!@#$%^&*_]{8,15}", message = "Password must be 8-15 characters in length and can include A-Z, a-z, 0-9, or special characters !@#$%^&*_")
	private String password;
	private String address;
	
	@NotNull(message = "Please enter the mobile Number")
	@Column(unique = true)
	@Pattern(regexp = "[6789]{1}[0-9]{9}", message = "Enter valid 10 digit mobile number")
	private String mobile;
	
	@NotNull(message = "Please enter the emaild id")
	@Column(unique = true)
	@Email
	private String email;
	
	
	
}
