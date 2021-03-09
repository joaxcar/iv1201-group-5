package se.kth.iv1201.recruitmentapplicationgroup5.integration;

import java.time.LocalDate;
import java.util.List;

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
		Account validAccount = getValidAccount();
		Account savedAccount = repo.save(validAccount);
		Account loadedAccount = repo.findById(savedAccount.getId()).get();
		Assertions.assertEquals(loadedAccount.getPassword(), validAccount.getPassword());
		Assertions.assertEquals(loadedAccount.getPerson().getName().getFirstName(), validAccount.getPerson().getName().getFirstName());
	}
	
	@Test
	void shouldNotSaveAnAccountWithoutAPerson() {
		Account account = getValidAccount();
		account.setPerson(null);
		Assertions.assertThrows(Exception.class, () -> repo.save(account));
		
	}
	
	@Test
	void shouldFindASavedAccountByUsername() {
		Account account = getValidAccount();
		var username = "Superman";
		account.setUsername(username);
		repo.save(account);
		List<Account> loadedAccounts = repo.findByUsername(username);
		Assertions.assertTrue(!loadedAccounts.isEmpty());
		Assertions.assertEquals(loadedAccounts.get(0).getUsername(), username);
	}
	
	private Account getValidAccount() {
		var firstName = "Joe";
		var lastName = "Doe";
		var email = "test@test.com";
		var birthDate = "1988-01-01";
		var password = "abcdefgh";
		var username = "gurka";
		
		FullName fullName = new FullName();
		fullName.setFirstName(firstName);
		fullName.setLastName(lastName);
		Person person = new Person();
		person.setEmail(email);
		person.setName(fullName);
		person.setBirthDate(LocalDate.parse(birthDate));
		Account account = new Account();
		account.setPassword(password);
		account.setUsername(username);
		account.setPerson(person);
		person.setAccount(account);
		return account;
	}
}
