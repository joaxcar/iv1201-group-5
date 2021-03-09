package se.kth.iv1201.recruitmentapplicationgroup5.service;

public class AccountNotFoundException extends Exception {
	
	/**
	 * Constructor for AccountNotFoundException.
	 * 
	 * @param message - error message
	 */
	public AccountNotFoundException(String message) {
        super(message);
    }
}
