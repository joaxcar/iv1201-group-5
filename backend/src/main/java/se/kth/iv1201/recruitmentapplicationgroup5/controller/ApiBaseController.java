package se.kth.iv1201.recruitmentapplicationgroup5.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import se.kth.iv1201.recruitmentapplicationgroup5.model.dto.RegistrationDTO;

/**
 * REST HATEOAS base controller.
 *
 */
@RestController
@Validated
@RequestMapping("/api")
public class ApiBaseController {

	/**
	 * REST endpoint api information.
	 *
	 * @return - HTTP response, if successful the body contains api reference
	 */
	@GetMapping("/v1")
	public ResponseEntity<?> apiInfo() {
		RegistrationDTO base = new RegistrationDTO();

		// links
		base.add(linkTo(ApiBaseController.class).slash("v1").withSelfRel());
		base.add(linkTo(AccountController.class).slash("authenticate").withRel("login"));
		base.add(linkTo(AccountController.class).slash("accounts").withRel("register"));

		return ResponseEntity.ok().body(base);
	}

}
