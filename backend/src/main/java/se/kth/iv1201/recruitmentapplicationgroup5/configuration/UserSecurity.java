package se.kth.iv1201.recruitmentapplicationgroup5.configuration;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import se.kth.iv1201.recruitmentapplicationgroup5.model.Account;

/**
 * Implements security check for users trying to access endpoints
 *
 */
@Component("userSecurity")
public class UserSecurity {

	/**
	 * Check if user has access to resource.
	 * 
	 * @param authentication current authenticated user for session
	 * @param id of requesting user
	 * @return true if user has access
	 */
	public boolean hasUserId(Authentication authentication, int id) {
		Account account = (Account) authentication.getPrincipal();
		System.out.println(id);
		System.out.println(account.getId());
		return account.getId() == id;
	}
}
