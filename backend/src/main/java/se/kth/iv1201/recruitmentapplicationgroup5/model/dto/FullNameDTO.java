/**
 * 
 */
package se.kth.iv1201.recruitmentapplicationgroup5.model.dto;

import javax.validation.constraints.NotEmpty;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
import se.kth.iv1201.recruitmentapplicationgroup5.model.FullName;

/**
 * DTO class for {@link FullName}.
 * 
 * @author Johan Carlsson
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Value
public class FullNameDTO {
	@NotEmpty
	String first;
	@NotEmpty
	String last;
}
