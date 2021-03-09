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

	List<Account> findByUsername(String username);

}
