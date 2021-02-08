/**
 * 
 */
package se.kth.iv1201.recruitmentapplicationgroup5.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * @author johan
 *
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record FullNameDTO(@NotNull @Size(min = 1) String first, @NotNull @Size(min = 1) String last) {
}
