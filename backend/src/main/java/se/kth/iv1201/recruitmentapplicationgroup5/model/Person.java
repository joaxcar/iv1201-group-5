package se.kth.iv1201.recruitmentapplicationgroup5.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * Class representing a person that have created an account in the application.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Setter(AccessLevel.PROTECTED)
	private int id;
	
	@NotNull
	@JsonManagedReference
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(referencedColumnName = "id")
	private FullName name;
	
	@NotNull 
	@Past
	@JsonFormat(pattern = "YYYY-MM-DD")
	private LocalDate birthDate;
	
	@NotEmpty
	@Email
	private String email;
	
	@JsonBackReference
	@OneToOne(mappedBy = "person")
	private Account account;

}
