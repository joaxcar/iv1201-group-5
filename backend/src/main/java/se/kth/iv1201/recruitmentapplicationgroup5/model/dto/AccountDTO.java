/**
 * 
 */
package se.kth.iv1201.recruitmentapplicationgroup5.model.dto;

import org.springframework.hateoas.RepresentationModel;

import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * @author Johan Carlsson
 *
 */
@Value
@EqualsAndHashCode(callSuper = false)
public class AccountDTO extends RepresentationModel<AccountDTO> {
	public AccountDTO(String username) {
		this.username = username;
		person = null;
		password = "hej";
		id = 1;
	}

	private int id;
	private PersonDTO person;
	private String username;
	private String password;
}
