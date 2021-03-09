package se.kth.iv1201.recruitmentapplicationgroup5.integration;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import se.kth.iv1201.recruitmentapplicationgroup5.model.Account;
import se.kth.iv1201.recruitmentapplicationgroup5.model.FullName;
import se.kth.iv1201.recruitmentapplicationgroup5.model.Person;

@TestPropertySource(locations = "/integration-test.properties")
@SpringBootTest
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
		account.setUsername("gurka");
		account.setPerson(person);
		person.setAccount(account);
		Account savedAccount = repo.save(account);
		Account loadedAccount = repo.findById(savedAccount.getId()).get();
		Assertions.assertEquals(loadedAccount.getPassword(), "abcdefgh");
		Assertions.assertEquals(loadedAccount.getPerson().getName().getFirstName(), "Joe");
	}
	
	@Test
	void shouldNotSaveAnAccountWithoutAPerson() {
		Account account = new Account();
		account.setPassword("abcdefgh");
		account.setUsername("gurka");
		account.setPerson(null);
		Assertions.assertThrows(Exception.class, () -> repo.save(account));
		
	}
	

}
