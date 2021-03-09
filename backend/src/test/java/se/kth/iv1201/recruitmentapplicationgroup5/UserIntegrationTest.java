package se.kth.iv1201.recruitmentapplicationgroup5;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

	String name = "\"name\": {\"first\": \"Richard\", \"last\": \"Wallin\"}";
	String username = "\"username\":\"rillmeister\"";
	String password = "\"password\":\"password\"";
	String email = "\"email\":\"asd@hej.se\"";
	String dateOfBirth = "\"dateOfBirth\": \"1900-05-11\"";
	
	@BeforeEach
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders
				.webAppContextSetup(this.webApplicationContext)
				.build();
	}
	
	@AfterEach
	public void tearDown() {
		this.mockMvc = null;
	}

	@Test
	public void returnsUserDetails() throws Exception {
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
		mockMvc.perform(post("/api/v1/accounts")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{" + invalidName + ", " + username + ", " + password + ", " + email + ", " + dateOfBirth + "}"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.msg").isString())
				.andExpect(jsonPath("$.url").isString());
	}
	
	@Test
	public void rejectsInvalidEmail() throws Exception {
		var invalidEmail = "\"email\":\"asdhej.se\"";
		mockMvc.perform(post("/api/v1/accounts")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{" + name + ", " + username + ", " + password + ", " + invalidEmail + ", " + dateOfBirth + "}"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.msg").isString())
				.andExpect(jsonPath("$.url").isString());
	}
	
	@Test
	public void rejectsFutureDateOfBirth() throws Exception {
		var invalidDateOfBirth = "\"dateOfBirth\": \"2100-05-11\"";
		mockMvc.perform(post("/api/v1/accounts")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{" + name + ", " + username + ", " + password + ", " + email + ", " + invalidDateOfBirth + "}"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.msg").isString())
				.andExpect(jsonPath("$.url").isString());
	}

	@Test
	public void rejectsDuplicateUsers() throws Exception {
		username = "\"username\":\"duplicate\"";
		mockMvc.perform(post("/api/v1/accounts")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{" + name + ", " + username + ", " + password + ", " + email + ", " + dateOfBirth + "}"))
				.andExpect(status().isCreated());
		mockMvc.perform(post("/api/v1/accounts")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{" + name + ", " + username + ", " + password + ", " + email + ", " + dateOfBirth + "}"))
				.andExpect(status().isConflict());
	}
	
	@Test
	public void returnsAccountDetails() throws Exception {
		username = "\"username\":\"newuser\"";
		mockMvc.perform(post("/api/v1/accounts")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{" + name + ", " + username + ", " + password + ", " + email + ", " + dateOfBirth + "}"))
				.andExpect(status().isCreated());

		mockMvc.perform(get("/api/v1/account/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.person.name.first").value("Richard"))
				.andExpect(jsonPath("$.person.name.last").value("Wallin"))
				.andExpect(jsonPath("$.person.birthDate").value("1900-05-11"))
				.andExpect(jsonPath("$.person.email").value("asd@hej.se"))
				.andExpect(jsonPath("$.username").value("newuser"))
				.andExpect(jsonPath("$.password").value("password"));	
	}

	@Test
	public void returnsNotFoundWhenNoaccount() throws Exception {
		mockMvc.perform(get("/api/v1/account/1234"))
				.andExpect(status().isNotFound());
	}
}
