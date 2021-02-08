package se.kth.iv1201.recruitmentapplicationgroup5.model.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record DateOfBirthDTO(int year, int month, int day) {

}
