package se.kth.iv1201.recruitmentapplicationgroup5.service;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.kth.iv1201.recruitmentapplicationgroup5.integration.AccountRepository;
import se.kth.iv1201.recruitmentapplicationgroup5.model.Account;
import se.kth.iv1201.recruitmentapplicationgroup5.model.dto.AccountDTO;
import se.kth.iv1201.recruitmentapplicationgroup5.model.dto.RegistrationDetails;

/**
 * {@link Service} for {@link Account} logic.
 *
 * @author Johan Carlsson
 */
@Service
public class AccountService {

	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	AccountRepository repository;

	/**
	 * Constructor that configures {@link ModelMapper}.
	 */
	public AccountService() {
		modelMapper.getConfiguration().setFieldMatchingEnabled(true)
				.setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
	}

	/**
	 * Add a new {@link Account} with the information from
	 * {@link RegistrationDetails}.
	 *
	 * @param registrationDetails - details for new account
	 */
	public AccountDTO addAccount(@Valid RegistrationDetails registrationDetails) {
		Account newAccount = registrationToAccount(registrationDetails);
		newAccount = repository.save(newAccount);
		AccountDTO newAccountDTO = accountToDTO(newAccount);
		return newAccountDTO;
	}

	private Account registrationToAccount(final RegistrationDetails details) {
		final Account account = modelMapper.map(details, Account.class);
		return account;
	}

	private AccountDTO accountToDTO(final Account account) {
		final AccountDTO accountDTO = modelMapper.map(account, AccountDTO.class);
		return accountDTO;
	}

}
