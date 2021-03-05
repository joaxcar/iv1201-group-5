package se.kth.iv1201.recruitmentapplicationgroup5.service;

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

//	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	AccountRepository repository;

	/**
	 * Basic constructor.
	 * 
	 * This constructor will configures {@link ModelMapper} to be able to work with
	 * Lombok value objects.
	 */
//	public AccountService() {
//		modelMapper.getConfiguration().setFieldMatchingEnabled(true)
//				.setFieldAccessLevel(Configuration.AccessLevel.PRIVATE).setMatchingStrategy(MatchingStrategies.LOOSE);
//		modelMapper.createTypeMap(String.class, LocalDate.class);
//		modelMapper.addConverter(stringToDate);
//	}

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

	// Custom converter to get a localdate out of a string with modelmapper
//	private Converter<DateOfBirthDTO, LocalDate> stringToDate = new AbstractConverter<DateOfBirthDTO, LocalDate>() {
//		@Override
//		protected LocalDate convert(DateOfBirthDTO date) {
//			return LocalDate.of(date.getYear(), date.getMonth(), date.getDay());
//		}
//	};

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
		//final AccountDTO accountDTO = modelMapper.map(account, AccountDTO.class);
		//return accountDTO;
		return new AccountDTO();
	}

}
