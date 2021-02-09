package se.kth.iv1201.recruitmentapplicationgroup5.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Class representing the name of a person.
 */
@Entity
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

	public FullName() {

	}

	/**
	 * Creates an instance of name as firstName lastName
	 * 
	 * @param firstName First name
	 * @param lastName  Last name
	 */
	public FullName(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
