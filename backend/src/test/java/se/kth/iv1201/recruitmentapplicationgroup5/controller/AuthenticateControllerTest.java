package se.kth.iv1201.recruitmentapplicationgroup5.controller;

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
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "/application-dev.properties")
class AuthenticateControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;

	String nameJson = "\"name\": {\"first\": \"Richard\", \"last\": \"Wallin\"}";
	String usernameJson = "\"username\":\"rilltester\"";
	String passwordJson = "\"password\":\"password\"";
	String emailJson = "\"email\":\"asd@hej.se\"";
	String dateOfBirthJson = "\"dateOfBirth\": \"1900-05-11\"";
	String username = "rilltester";
	String password = "password";
	
	@BeforeEach
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(this.webApplicationContext)
				.apply(SecurityMockMvcConfigurers.springSecurity())
				.build();
	}
	
	@AfterEach
	public void tearDown() {
		this.mockMvc = null;
	}

	@Test
	void addingUser() throws Exception {
		addCorrectUser();
	}
	
	private void addCorrectUser() throws Exception {
		mockMvc.perform(post("/api/v1/accounts")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{" + nameJson + ", " + usernameJson + ", " + passwordJson + ", " + emailJson + ", " + dateOfBirthJson + "}"))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.person.name.first").value("Richard"))
				.andExpect(jsonPath("$.person.name.last").value("Wallin"))
				.andExpect(jsonPath("$.person.birthDate").value("1900-05-11"))
				.andExpect(jsonPath("$.person.email").value("asd@hej.se"))
				.andExpect(jsonPath("$.username").value(username))
				.andExpect(jsonPath("$.password").value(password));	
	}

}
