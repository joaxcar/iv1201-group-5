package se.kth.iv1201.recruitmentapplicationgroup5.model.dto;

import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;
import se.kth.iv1201.recruitmentapplicationgroup5.model.Person;

/**
 * DTO class for {@link Person}.
 * 
 * @author Johan Carlsson
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Value
@NoArgsConstructor(force = true)
public class PersonDTO {
	private int id;
	private FullNameDTO name;
	private LocalDate birthDate;
	private String email;
}
