package se.kth.iv1201.recruitmentapplicationgroup5.integration;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import se.kth.iv1201.recruitmentapplicationgroup5.model.Account;

/**
 * Interface containing database connection methods for
 *  {@link se.kth.iv1201.recruitmentapplicationgroup5.model.Account}.
 *
 */
public interface AccountRepository extends CrudRepository<Account, Integer>{

	/**
	 * Search for accounts by username.
	 * 
	 * Returns a list of {@link se.kth.iv1201.recruitmentapplicationgroup5.model.Account}
	 * matching given username string given as parameter.
	 *
	 * @param username - username to search for
	 * @return - list of Accounts matching username
	 */
	List<Account> findByUsername(String username);

}
