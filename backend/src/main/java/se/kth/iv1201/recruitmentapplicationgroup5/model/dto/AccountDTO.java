/**
 * 
 */
package se.kth.iv1201.recruitmentapplicationgroup5.model.dto;

import org.springframework.hateoas.RepresentationModel;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Value;

/**
 * @author Johan Carlsson
 *
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Value
@NoArgsConstructor(force = true)
@EqualsAndHashCode(callSuper = false)
public class AccountDTO extends RepresentationModel<AccountDTO> {
	private int id;
	private PersonDTO person;
	private String username;
	private String password;
}
