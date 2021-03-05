package se.kth.iv1201.recruitmentapplicationgroup5.model.dto;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RegistrationDetails {
	@NotNull(message = "Must have field: name")
	@Valid
	FullNameDTO name;
	@NotNull(message = "Must have field: email")
	@Email(message = "Invalid email format") 
	String email;
	@NotNull(message = "Must have field: dateOfBirth")
	@Past(message = "dateOfBirth must be a past date")
	LocalDate dateOfBirth;
	@NotEmpty(message = "Field username must be atleast one letter")
	String username;
	@NotNull(message = "Must have field: password")
	@Size(min = 8, message = "password must be atleast 8 characters long")
	String password;
	
	@JsonProperty("dateOfBirth")
	@JsonFormat(pattern = "yyyy-mm-dd")
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = LocalDate.parse(dateOfBirth);
	}
}