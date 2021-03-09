package se.kth.iv1201.recruitmentapplicationgroup5.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import se.kth.iv1201.recruitmentapplicationgroup5.service.AccountService;
import se.kth.iv1201.recruitmentapplicationgroup5.util.JwtUtil;

@RestController
@Validated
@RequestMapping("/api/v1")
public class AuthenticateController {
	private final int TEN_HOURS_IN_SECONDS = 36000;
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private AccountService service;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping(value = "/authenticate")
	public ResponseEntity<String> createAuthenticationToken(@Valid @RequestBody AuthenticationRequest req, HttpServletResponse res) throws Exception {
		
		//TODO: adda felhantering för BadCredentialsException som denna slänger.
		manager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
		final UserDetails user = service.loadUserByUsername(req.getUsername());
		final String jwt = jwtUtil.generateToken(user);
		final Cookie jwtCookie = new Cookie("Authorization", jwt);
		jwtCookie.setMaxAge(TEN_HOURS_IN_SECONDS);
		jwtCookie.setHttpOnly(true);
		res.addCookie(jwtCookie);
		return new ResponseEntity<String>("Successful login", HttpStatus.OK); 
	}
	

}
