package se.kth.iv1201.recruitmentapplicationgroup5.controller;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NameDTO {

	@NotNull
	@Size(min = 1)
	private String first;
	@NotNull
	@Size(min = 1)
	private String last;

	public String getFirst() {
		return first;
	}

	public String getLast() {
		return last;
	}

}
