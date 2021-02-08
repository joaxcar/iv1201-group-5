package se.kth.iv1201.recruitmentapplicationgroup5.model.dto;

import lombok.Value;

@Value
public class PersonDTO {
	private int id;
	private FullNameDTO name;
	private DateOfBirthDTO birthDate;
	private String email;
}
