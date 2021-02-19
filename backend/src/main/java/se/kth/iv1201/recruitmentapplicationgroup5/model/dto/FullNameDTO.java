/**
 * 
 */
package se.kth.iv1201.recruitmentapplicationgroup5.model.dto;

import javax.validation.constraints.NotEmpty;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;
import se.kth.iv1201.recruitmentapplicationgroup5.model.FullName;

/**
 * DTO class for {@link FullName}.
 * 
 * @author Johan Carlsson
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Value
@NoArgsConstructor(force = true)
public class FullNameDTO {
	@NotEmpty(message = "Field first in field name must be atleast one letter")
	String first;
	@NotEmpty(message = "Field last in field name must be atleast one letter")
	String last;
}
