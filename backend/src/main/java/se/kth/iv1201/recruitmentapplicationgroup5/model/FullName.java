package se.kth.iv1201.recruitmentapplicationgroup5.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




/**
 * Class representing the name of a person, including firstName and lastName.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FullName {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	@NotEmpty
	private String firstName;
	
	@NotEmpty
	private String lastName;
	
	@JsonBackReference
	@OneToOne(mappedBy = "name")
	private Person person;
}
