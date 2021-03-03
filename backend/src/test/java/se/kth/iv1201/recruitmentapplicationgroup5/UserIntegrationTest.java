package se.kth.iv1201.recruitmentapplicationgroup5;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "/integration-test.properties")
class UserIntegrationTest {
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}
	
	@AfterEach
	public void tearDown() {
		this.mockMvc = null;
	}

	@Test
	public void returnsUserDetails() throws Exception {
		var name = "\"name\": {\"first\": \"Richard\", \"last\": \"Wallin\"}";
		var username = "\"username\":\"rillmeister\"";
		var password = "\"password\":\"password\"";
		var email = "\"email\":\"asd@hej.se\"";
		var dateOfBirth = "\"dateOfBirth\": {\"year\": 1900, \"month\": 5, \"day\": 11}";
		
		mockMvc.perform(post("/api/v1/accounts")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{" + name + ", " + username + ", " + password + ", " + email + ", " + dateOfBirth + "}"))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.person.name.first").value("Richard"))
				.andExpect(jsonPath("$.person.name.last").value("Wallin"))
				.andExpect(jsonPath("$.person.birthDate").value("1900-05-11"))
				.andExpect(jsonPath("$.person.email").value("asd@hej.se"))
				.andExpect(jsonPath("$.username").value("rillmeister"))
				.andExpect(jsonPath("$.password").value("password"));	
	}
	
	@Test
	public void rejectsInvalidJson() throws Exception {
		var invalidName = "\"name\": \"first\": \"Richard\", \"last\": \"Wallin\"}";
		var username = "\"username\":\"rillmeister\"";
		var password = "\"password\":\"password\"";
		var email = "\"email\":\"asd@hej.se\"";
		var dateOfBirth = "\"dateOfBirth\": {\"year\": 1900, \"month\": 5, \"day\": 11}";
		mockMvc.perform(post("/api/v1/accounts")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{" + invalidName + ", " + username + ", " + password + ", " + email + ", " + dateOfBirth + "}"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.msg").isString())
				.andExpect(jsonPath("$.url").isString());
	}
	
	@Test
	public void rejectsInvalidEmail() throws Exception {
		var invalidName = "\"name\": {\"first\": \"Richard\", \"last\": \"Wallin\"}";
		var username = "\"username\":\"rillmeister\"";
		var password = "\"password\":\"password\"";
		var invalidEmail = "\"email\":\"asdhej.se\"";
		var dateOfBirth = "\"dateOfBirth\": {\"year\": 1900, \"month\": 5, \"day\": 11}";
		mockMvc.perform(post("/api/v1/accounts")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{" + invalidName + ", " + username + ", " + password + ", " + invalidEmail + ", " + dateOfBirth + "}"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.msg").isString())
				.andExpect(jsonPath("$.url").isString());
	}
	
	@Test
	public void rejectsFutureDateOfBirth() throws Exception {
		var invalidName = "\"name\": {\"first\": \"Richard\", \"last\": \"Wallin\"}";
		var username = "\"username\":\"rillmeister\"";
		var password = "\"password\":\"password\"";
		var invalidEmail = "\"email\":\"asd@hej.se\"";
		var dateOfBirth = "\"dateOfBirth\": {\"year\": 2100, \"month\": 5, \"day\": 11}";
		mockMvc.perform(post("/api/v1/accounts")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{" + invalidName + ", " + username + ", " + password + ", " + invalidEmail + ", " + dateOfBirth + "}"))
				.andExpect(status().isInternalServerError()) // Change here when date bug is fixed!
				.andExpect(jsonPath("$.msg").isString())
				.andExpect(jsonPath("$.url").isString());
	}

}
