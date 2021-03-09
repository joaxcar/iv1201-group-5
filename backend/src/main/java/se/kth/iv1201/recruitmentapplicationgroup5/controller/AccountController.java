package se.kth.iv1201.recruitmentapplicationgroup5.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import se.kth.iv1201.recruitmentapplicationgroup5.model.dto.AccountDTO;
import se.kth.iv1201.recruitmentapplicationgroup5.model.dto.RegistrationDetails;
import se.kth.iv1201.recruitmentapplicationgroup5.service.AccountNotFoundException;
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
	 * @return - HTTP response, if successful the body contains the created account
	 */
	@PostMapping("/accounts")
	public ResponseEntity<?> newAccount(@Valid @RequestBody RegistrationDetails registrationDetails) {
		List<AccountDTO> existingAccount = service.findAccount(registrationDetails.getUsername());
		if (existingAccount.size() > 0) {
			return new ResponseEntity<>(null, HttpStatus.CONFLICT);
		}
		AccountDTO createdAccount = service.addAccount(registrationDetails);

		// links
		createdAccount.add(linkTo(methodOn(AccountController.class).get(createdAccount.getId())).withSelfRel());

		// location
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdAccount.getId())
				.toUri();
		return ResponseEntity.created(uri).body(createdAccount);
	}

	/**
	 * REST endpoint for retrieving an account.
	 *
	 * @param id - id of account
	 * @return - HTTP response, if successful the body will contain the account
	 */
	@GetMapping("account/{id}")
	public ResponseEntity<?> get(@PathVariable final int id) {
		try {
			AccountDTO account = service.getAccount(id);

			// links
			account.add(linkTo(methodOn(AccountController.class).get(account.getId())).withSelfRel());

			return ResponseEntity.ok(account);
		} catch (AccountNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
