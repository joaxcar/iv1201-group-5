package se.kth.iv1201.recruitmentapplicationgroup5.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import se.kth.iv1201.recruitmentapplicationgroup5.model.dto.AccountDTO;
import se.kth.iv1201.recruitmentapplicationgroup5.model.dto.RegistrationDetails;
import se.kth.iv1201.recruitmentapplicationgroup5.service.AccountService;

@RestController
@Validated
public class AccountController {

	@Autowired
	private AccountService service;

	@PostMapping("/api/v1/accounts")
	public ResponseEntity<AccountDTO> newAccount(@Valid @RequestBody RegistrationDetails registrationDetails) {
		AccountDTO createdAccount = service.addAccount(registrationDetails);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdAccount.getId())
				.toUri();
		String uname = createdAccount.getUsername();
		return ResponseEntity.created(uri).body(createdAccount);
	}

}
