package se.kth.iv1201.recruitmentapplicationgroup5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication //(exclude = { SecurityAutoConfiguration.class }) TODO: ta bort denna om den inte behövs
public class RecruitmentApplicationGroup5Application {

	public static void main(String[] args) {
		SpringApplication.run(RecruitmentApplicationGroup5Application.class, args);
	}

}
