package se.kth.iv1201.recruitmentapplicationgroup5.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import se.kth.iv1201.recruitmentapplicationgroup5.model.dto.AccountDTO;
import se.kth.iv1201.recruitmentapplicationgroup5.model.dto.LoginDTO;
import se.kth.iv1201.recruitmentapplicationgroup5.service.AccountService;
import se.kth.iv1201.recruitmentapplicationgroup5.util.JwtUtil;

/**
 * Controller for the authentication endpoint of the REST-API
 *
 */
@RestController
@Validated
@RequestMapping("/api/v1")
public class AuthenticateController {
	private static final int TEN_HOURS_IN_SECONDS = 36000;
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private AccountService service;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	/**
	 * Creates a JWT from an account when receiving account credentials.
	 * @param req Credentials for an account
	 * @param res Java EE HTTP response object
	 * @return Response with JWT
	 * @throws BadCredentialsException When invalid user credentials are supplied
	 */
	@PostMapping(value = "/authenticate")
	public ResponseEntity<LoginDTO> createAuthenticationToken(
			@Valid @RequestBody AuthenticationRequest req, 
			HttpServletResponse res
			) throws BadCredentialsException {
		
		manager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
		
		final UserDetails user = service.loadUserByUsername(req.getUsername());
		final String jwt = jwtUtil.generateToken(user);
		
		final Cookie jwtCookie = new Cookie("Authorization", jwt);
		jwtCookie.setMaxAge(TEN_HOURS_IN_SECONDS);
		jwtCookie.setHttpOnly(false);
		res.addCookie(jwtCookie);
		
		AccountDTO accountInfo = service.findAccount(user.getUsername()).get(0);
		LoginDTO loggedInUserInfo = new LoginDTO(accountInfo.getId(), accountInfo.getUsername());
		loggedInUserInfo.add(linkTo(methodOn(AccountController.class).get(loggedInUserInfo.getId())).withSelfRel());
		
		return ResponseEntity.ok(loggedInUserInfo);
	}

}
