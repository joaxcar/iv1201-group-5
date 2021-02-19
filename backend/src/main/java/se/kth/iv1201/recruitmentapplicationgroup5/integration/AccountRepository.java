package se.kth.iv1201.recruitmentapplicationgroup5.integration;

import org.springframework.data.repository.CrudRepository;

import se.kth.iv1201.recruitmentapplicationgroup5.model.Account;

/**
 * Interface containing database connection methods for
 *  {@link se.kth.iv1201.recruitmentapplicationgroup5.model.Account}.
 * @author Richard
 *
 */
public interface AccountRepository extends CrudRepository<Account, Integer>{

}
