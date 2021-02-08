package se.kth.iv1201.recruitmentapplicationgroup5.model.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Value
public class PersonDTO {
	private int id;
	private FullNameDTO name;
	private DateOfBirthDTO birthDate;
	private String email;
}
