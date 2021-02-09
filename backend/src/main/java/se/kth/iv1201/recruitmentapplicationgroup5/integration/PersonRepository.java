package se.kth.iv1201.recruitmentapplicationgroup5.integration;

import org.springframework.data.repository.CrudRepository;

import se.kth.iv1201.recruitmentapplicationgroup5.model.Person;

/**
 * Interface containing database connection methods for
 *  {@link se.kth.iv1201.recruitmentapplicationgroup5.model.Person}.
 * @author Richard
 *
 */
public interface PersonRepository extends CrudRepository<Person, Integer>{

}
