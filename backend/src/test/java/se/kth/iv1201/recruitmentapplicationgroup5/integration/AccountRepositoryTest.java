package se.kth.iv1201.recruitmentapplicationgroup5.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import se.kth.iv1201.recruitmentapplicationgroup5.model.Account;
import se.kth.iv1201.recruitmentapplicationgroup5.model.FullName;
import se.kth.iv1201.recruitmentapplicationgroup5.model.Person;

@DataJpaTest
class AccountRepositoryTest {

	@Autowired
	private AccountRepository repo;
	
	@Test
	void shouldSaveAValidAccount() {
		FullName fullName = new FullName();
		fullName.setFirstName("Joe");
		fullName.setLastName("Doe");
		Person person = new Person();
		person.setEmail("test@test.com");
		person.setName(fullName);
		person.setBirthDate(LocalDate.parse("1988-01-01"));
		Account account = new Account();
		account.setPassword("abcdefgh");
		account.setPerson(person);
		person.setAccount(account);
		Account savedAccount = repo.save(account);
		Assertions.assertEquals(savedAccount.getPerson(), person);
		Assertions.assertEquals(savedAccount.getPassword(), "abcdefgh");
	}

}
