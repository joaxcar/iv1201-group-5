package se.kth.iv1201.recruitmentapplicationgroup5.service;

import java.time.LocalDate;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import se.kth.iv1201.recruitmentapplicationgroup5.model.dto.AccountDTO;
import se.kth.iv1201.recruitmentapplicationgroup5.model.dto.FullNameDTO;
import se.kth.iv1201.recruitmentapplicationgroup5.model.dto.RegistrationDetails;

@SpringBootTest
@TestPropertySource(locations = "/integration-test.properties")
class AccountServiceTest {
	
	@Autowired
	AccountService accountService;
	
	@Test
	public void shouldNotThrowExceptionWithValidRegistrationDetails() {
		RegistrationDetails details = getValidRegistrationDetails();

		accountService.addAccount(details);
	}
	
	@Test
	public void shouldReturnInstanceOfAccountDTOByCreate() {
		RegistrationDetails details = getValidRegistrationDetails();

		Object result = accountService.addAccount(details);
		Assertions.assertTrue(result instanceof AccountDTO); 
	}
	
	@Test
	public void shouldReturnInstanceOfAccountDTOByGetIfExists() throws AccountNotFoundException {
		RegistrationDetails details = getValidRegistrationDetails();

		AccountDTO test = accountService.addAccount(details);
		Object result = accountService.getAccount(test.getId());
		Assertions.assertTrue(result instanceof AccountDTO); 
	}

	@Test
	public void shouldThrowExceptionByGetIfNotExists() {
		Assertions.assertThrows(AccountNotFoundException.class, () -> {
			accountService.getAccount(1234);
		});
	}
	
	@Test
	public void shouldHaveMirroredRegistrationDetailsInReturnedAccountDTO() {
		RegistrationDetails details = getValidRegistrationDetails();

		AccountDTO result = accountService.addAccount(details);
		Assertions.assertEquals(result.getPerson().getName().getFirst(), details.getName().getFirst());
		Assertions.assertEquals(result.getPerson().getName().getLast(), details.getName().getLast());
		Assertions.assertEquals(result.getPassword(), details.getPassword());
		Assertions.assertEquals(result.getUsername(), details.getUsername());
		Assertions.assertEquals(result.getPerson().getBirthDate(), details.getDateOfBirth());
		Assertions.assertEquals(result.getPerson().getEmail(), details.getEmail());
		
	}
	
	@Test
	public void shouldThrowConstraintViolationExceptionWhenDateNotPast() {
		RegistrationDetails details = getValidRegistrationDetails();
		details.setDateOfBirth(LocalDate.now().plusDays(1).toString());
		Assertions.assertThrows(ConstraintViolationException.class, () -> {
			accountService.addAccount(details);
		});
	}
	
	@Test
	public void shouldThrowConstraintViolationExceptionWhenUsernameIsEmptyString() {
		RegistrationDetails details = getValidRegistrationDetails();
		details.setUsername("");
		Assertions.assertThrows(ConstraintViolationException.class, () -> {
			accountService.addAccount(details);
		});
	}
	
	@Test
	public void shouldThrowConstraintViolationExceptionWhenEmailIsNotValid() {
		RegistrationDetails details = getValidRegistrationDetails();
		details.setEmail("email@");
		Assertions.assertThrows(ConstraintViolationException.class, () -> {
			accountService.addAccount(details);
		});
	}
	
	
	@Test
	public void shouldThrowConstraintViolationExceptionWhenNoUsernameSpecified() {
		RegistrationDetails details = getValidRegistrationDetails();
		details.setUsername(null);
		Assertions.assertThrows(ConstraintViolationException.class, () -> {
			accountService.addAccount(details);
		});
	}
	
	@Test
	public void shouldThrowConstraintViolationExceptionWhenPasswordIsLessThanEightCharacters() {
		RegistrationDetails details = getValidRegistrationDetails();
		details.setPassword("abcdefg");
		Assertions.assertThrows(ConstraintViolationException.class, () -> {
			accountService.addAccount(details);
		});
	}
	
	private RegistrationDetails getValidRegistrationDetails() {
		FullNameDTO fullNameDTO = new FullNameDTO("Joe", "Doe");
		String email = "joe@mail.com";
		LocalDate dateOfBirth = LocalDate.parse("1987-04-21");
		String username = "joey";
		String password = "joeyjoey";
		return new RegistrationDetails(fullNameDTO, email, dateOfBirth, username, password);
	}

}
