package se.kth.iv1201.recruitmentapplicationgroup5.integration;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import se.kth.iv1201.recruitmentapplicationgroup5.model.FullName;
import se.kth.iv1201.recruitmentapplicationgroup5.model.Person;

@TestPropertySource(locations = "/integration-test.properties")
@SpringBootTest
class PersonRepositoryTest {

	@Autowired
	private PersonRepository repo;
	
	@Test
	void shouldSaveAValidPerson() {
		var firstName = "Joe";
		var lastName = "Doe";
		var email = "test@test.com";
		var birthDate = "1988-01-01";
		var wrongFirstName = "Johnny";
		FullName fullName = new FullName();
		fullName.setFirstName(firstName);
		fullName.setLastName(lastName);
		
		Person person = new Person();
		person.setEmail(email);
		person.setName(fullName);
		person.setBirthDate(LocalDate.parse(birthDate));
		Person savedPerson = repo.save(person);
		Integer id = savedPerson.getId();
		Person p = repo.findById(id).get();
		Assertions.assertEquals(p.getEmail(), email);
		Assertions.assertNotEquals(p.getName().getFirstName(), wrongFirstName);
	}
	
	@Test
	void shouldNotSaveAPersonWithoutAName() {
		var email = "test@test.com";
		var birthDate = "1988-01-01";
		Person person = new Person();
		person.setEmail(email);
		person.setName(null);
		person.setBirthDate(LocalDate.parse(birthDate));
		Assertions.assertThrows(Exception.class, () -> repo.save(person));
	}

}
