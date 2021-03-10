package se.kth.iv1201.recruitmentapplicationgroup5.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import se.kth.iv1201.recruitmentapplicationgroup5.integration.AccountRepository;
import se.kth.iv1201.recruitmentapplicationgroup5.model.Account;
import se.kth.iv1201.recruitmentapplicationgroup5.model.Authority;
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
@Validated
@Transactional(rollbackFor = Exception.class)
public class AccountService implements UserDetailsService{

	@Autowired
	AccountRepository repository;

	/**
	 * Add a new account.
	 * 
	 * Account created from the information in {@link RegistrationDetails}.
	 *
	 * @param registrationDetails - details for new account
	 * @return - created account as DTO
	 */
	public AccountDTO addAccount(@Valid RegistrationDetails registrationDetails) {
		Account newAccount = registrationToAccount(registrationDetails);
		newAccount = repository.save(newAccount);
		AccountDTO newAccountDTO = accountToDTO(newAccount);
		return newAccountDTO;
	}

	/**
	 * List accounts with matching username.
	 * 
	 * Returns a list of {@link se.kth.iv1201.recruitmentapplicationgroup5.model.dto.AccountDTO}
	 * matching username given as parameter.
	 *
	 * @param username - username to search for
	 * @return - list of matching accounts as DTOs
	 */
	public List<AccountDTO> findAccount(String username) {
		List<Account> account = repository.findByUsername(username);
		return account.stream().map(acc -> this.accountToDTO(acc)).collect(Collectors.toList());
	}
	
	/**
	 * Get account by id.
	 * 
	 * Returns a {@link se.kth.iv1201.recruitmentapplicationgroup5.model.dto.AccountDTO}
	 * matching id given as parameter.
	 *
	 * @param id - account id
	 * @return - matching account as DTOs
	 * @throws AccountNotFoundException - if no Account exists with given id
	 */
	public AccountDTO getAccount(int id) throws AccountNotFoundException {
		Optional<Account> account = repository.findById(id);
		if (account.isPresent()) {
			return accountToDTO(account.get());
		} else {
			throw new AccountNotFoundException("No account with id: " + id);
		}
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
		account.setAuthority(Authority.APPLICANT);
		
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

	/**
	 * Loads a user from the DB by username. Implementation of userdetailservice funciton needed
	 * for spring security authentication.
	 * 
	 * @param username Username of the account to fetch.
	 * 
	 * @return Account details of account with username username
	 * 
	 * @throws UsernameNotFoundException Thrown if no account found with username username
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		return repository.findByUsername(username).stream()
				.findFirst()
				.orElseThrow(() -> new UsernameNotFoundException("Invalid user credentials."));
	}
}
