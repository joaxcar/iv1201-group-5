package se.kth.iv1201.recruitmentapplicationgroup5.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import se.kth.iv1201.recruitmentapplicationgroup5.integration.AccountRepository;
import se.kth.iv1201.recruitmentapplicationgroup5.model.Account;
import se.kth.iv1201.recruitmentapplicationgroup5.model.FullName;
import se.kth.iv1201.recruitmentapplicationgroup5.model.Person;
import se.kth.iv1201.recruitmentapplicationgroup5.model.dto.AccountDTO;
import se.kth.iv1201.recruitmentapplicationgroup5.model.dto.FullNameDTO;
import se.kth.iv1201.recruitmentapplicationgroup5.model.dto.PersonDTO;
import se.kth.iv1201.recruitmentapplicationgroup5.model.dto.RegistrationDetails;

/**
 * Service layer for user accounts.
 * 
 * Implements business logic for
 * {@link se.kth.iv1201.recruitmentapplicationgroup5.model.Account}.
 */
@Service
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
public class AccountService {

	@Autowired
	AccountRepository repository;

	/**
	 * Add a new account.
	 * 
	 * Account created from the information in {@link RegistrationDetails}.
	 *
	 * @param registrationDetails - details for new account
	 */
	public AccountDTO addAccount(@Valid RegistrationDetails registrationDetails) {
		Account newAccount = registrationToAccount(registrationDetails);
		newAccount = repository.save(newAccount);
		AccountDTO newAccountDTO = accountToDTO(newAccount);
		return newAccountDTO;
	}

	public List<AccountDTO> findAccount(String username) {
		List<Account> account = repository.findByUsername(username);
		return account.stream().map(acc -> this.accountToDTO(acc)).collect(Collectors.toList());
	}

	private Account registrationToAccount(final RegistrationDetails details) {
		FullName fullName = new FullName();
		fullName.setFirstName(details.getName().getFirst());
		fullName.setLastName(details.getName().getLast());
		
		Person person = new Person();
		person.setName(fullName);
		person.setEmail(details.getEmail());
		person.setBirthDate(details.getDateOfBirth());
		
		Account account = new Account();
		account.setPerson(person);
		account.setUsername(details.getUsername());
		account.setPassword(details.getPassword());
		
		return account;
	}

	private AccountDTO accountToDTO(final Account account) {
		var firstName = account.getPerson().getName().getFirstName();
		var lastName = account.getPerson().getName().getLastName();
		FullNameDTO fullName = new FullNameDTO(firstName, lastName);
		
		var personId = account.getPerson().getId();
		var birthDate = account.getPerson().getBirthDate();
		var email = account.getPerson().getEmail();
		PersonDTO person = new PersonDTO(personId, fullName, birthDate, email);
		
		var accountId = account.getId();
		var username = account.getUsername();
		var password = account.getPassword();
		
		return new AccountDTO(accountId, person, username, password);
	}

}
