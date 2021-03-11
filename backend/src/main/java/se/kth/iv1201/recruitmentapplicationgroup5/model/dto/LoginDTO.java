package se.kth.iv1201.recruitmentapplicationgroup5.model.dto;

import org.springframework.hateoas.RepresentationModel;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Value;
import se.kth.iv1201.recruitmentapplicationgroup5.model.Account;

/**
 * DTO class for login details of {@link Account}.
 * 
 */
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Value
@NoArgsConstructor(force = true)
@EqualsAndHashCode(callSuper = false)
public class LoginDTO extends RepresentationModel<LoginDTO> {
	private int id;
	private String username;
}
