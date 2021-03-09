package se.kth.iv1201.recruitmentapplicationgroup5.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import se.kth.iv1201.recruitmentapplicationgroup5.model.dto.AccountDTO;
import se.kth.iv1201.recruitmentapplicationgroup5.model.dto.RegistrationDetails;
import se.kth.iv1201.recruitmentapplicationgroup5.service.AccountService;

/**
 * REST controller handling user accounts.
 *
 */
@RestController
@Validated
@RequestMapping("/api/v1")
public class AccountController {

	@Autowired
	private AccountService service;

	/**
	 * REST endpoint for account creation.
	 *
	 * @param registrationDetails - registration details for a new account
	 */
	@PostMapping("/accounts")
	public ResponseEntity<?> newAccount(@Valid @RequestBody RegistrationDetails registrationDetails) {
		List<AccountDTO> existingAccount = service.findAccount(registrationDetails.getUsername());
		if (existingAccount.size() > 0) {
			return new ResponseEntity<>(null, HttpStatus.CONFLICT);
		}
		AccountDTO createdAccount = service.addAccount(registrationDetails);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdAccount.getId())
				.toUri();
		return ResponseEntity.created(uri).body(createdAccount);
	}

}
