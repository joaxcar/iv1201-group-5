package se.kth.iv1201.recruitmentapplicationgroup5.integration;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import se.kth.iv1201.recruitmentapplicationgroup5.model.FullName;
import se.kth.iv1201.recruitmentapplicationgroup5.model.Person;

@DataJpaTest
@TestPropertySource(locations = "/integration-test.properties")
class PersonRepositoryTest {

	@Autowired
	private PersonRepository repo;
	
	@Test
	void shouldSaveAValidPerson() {
		FullName fullName = new FullName();
		fullName.setFirstName("Joe");
		fullName.setLastName("Doe");
		Person person = new Person();
		person.setEmail("test@test.com");
		person.setName(fullName);
		person.setBirthDate(LocalDate.parse("1987-02-01"));
		Person savedPerson = repo.save(person);
		Integer id = savedPerson.getId();
		Person p = repo.findById(id).get();
		Assertions.assertEquals(p.getEmail(), "test@test.com");
		Assertions.assertNotEquals(p.getName().getFirstName(), "Johnny");
	}

}
