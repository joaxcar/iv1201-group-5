package se.kth.iv1201.recruitmentapplicationgroup5.controller;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import se.kth.iv1201.recruitmentapplicationgroup5.model.dto.RegistrationDetails;

@RestController
@Validated
public class ApplicantController {

	@PostMapping("/api/v1/applicant")
	RegistrationDetails newApplicant(@Valid @RequestBody RegistrationDetails registerForm) {
		return registerForm;
	}

}