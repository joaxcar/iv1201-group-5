/**
 * 
 */
package se.kth.iv1201.recruitmentapplicationgroup5.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Value;

/**
 * @author johan
 *
 */
@Value
public class FullNameDTO {
	@NotNull
	@Size(min = 1)
	String first;
	@NotNull
	@Size(min = 1)
	String last;
}
