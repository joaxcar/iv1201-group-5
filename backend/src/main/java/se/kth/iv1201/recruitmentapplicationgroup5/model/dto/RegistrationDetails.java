package se.kth.iv1201.recruitmentapplicationgroup5.model.dto;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Value;

@Value
public class RegistrationDetails {
	@NotNull(message = "Must have field: name")
	@Valid
	FullNameDTO name;
	@NotNull(message = "Must have field: email")
	@Email(message = "Invalid email format") 
	String email;
	@NotNull(message = "Must have field: dateOfBirth")
	@Valid
	DateOfBirthDTO dateOfBirth;
	@NotEmpty(message = "Field username must be atleast one letter")
	String username;
	@NotNull(message = "Must have field: password")
	@Size(min = 8, message = "password must be atleast 8 characters long")
	String password;
}