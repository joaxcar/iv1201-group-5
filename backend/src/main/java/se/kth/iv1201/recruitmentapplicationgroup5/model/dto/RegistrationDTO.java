package se.kth.iv1201.recruitmentapplicationgroup5.model.dto;

import org.springframework.hateoas.RepresentationModel;

import lombok.EqualsAndHashCode;
import lombok.Value;
import se.kth.iv1201.recruitmentapplicationgroup5.model.Account;

/**
 * DTO class for login details of {@link Account}.
 * 
 */
@Value
@EqualsAndHashCode(callSuper = false)
public class RegistrationDTO extends RepresentationModel<RegistrationDTO> {
}
