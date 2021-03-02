package se.kth.iv1201.recruitmentapplicationgroup5.integration;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import se.kth.iv1201.recruitmentapplicationgroup5.model.Account;

/**
 * Interface containing database connection methods for
 *  {@link se.kth.iv1201.recruitmentapplicationgroup5.model.Account}.
 *
 */
public interface AccountRepository extends CrudRepository<Account, Integer>{

	@Query("SELECT a FROM Account a WHERE a.username = :username")
	public Account findByUsername(@Param("username") String username);
}
