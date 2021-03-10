package se.kth.iv1201.recruitmentapplicationgroup5.model;

/*
 * Enum representing the different access-levels you can have in the system.
 */
public enum Authority {
	APPLICANT("APPLICANT"), RECRUITER("RECRUITER");
	
	private String string;
	
	private Authority(String string) {
		this.string = string;
	}
	
	public String getString() {
		return string;
	}
}
