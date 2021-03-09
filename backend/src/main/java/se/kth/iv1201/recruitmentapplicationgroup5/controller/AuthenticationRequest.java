package se.kth.iv1201.recruitmentapplicationgroup5.controller;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class AuthenticationRequest {
	
	@NotEmpty(message = "Field username must have atleast 1 character.")
	String username;
	
	@NotEmpty(message = "Field password must have atleast 1 character.")
	String password;

}
