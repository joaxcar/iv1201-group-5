package se.kth.iv1201.recruitmentapplicationgroup5.model.dto;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Value;

@Value
public class RegistrationDetails {
	@NotNull
	@Valid
	FullNameDTO name;
	@NotNull
	@Email
	String email;
	@NotNull
	@Valid
	DateOfBirthDTO dateOfBirth;
	@NotNull
	@Size(min = 1)
	String username;
	@NotNull
	@Size(min = 8)
	String password;
}