package se.kth.iv1201.recruitmentapplicationgroup5.controller;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class ApplicantController {

	@PostMapping("/applicant")
	RegistrationForm newApplicant(@Valid @RequestBody RegistrationForm registerForm) {
		return registerForm;
	}

}
