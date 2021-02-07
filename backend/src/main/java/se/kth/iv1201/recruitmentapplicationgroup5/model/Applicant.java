package se.kth.iv1201.recruitmentapplicationgroup5.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Applicant {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	public Applicant() {
	}

	public Applicant(String name) {
		this.name = name;
	}

	public Long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

}
