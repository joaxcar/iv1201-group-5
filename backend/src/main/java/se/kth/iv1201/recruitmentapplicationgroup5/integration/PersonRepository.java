package se.kth.iv1201.recruitmentapplicationgroup5.integration;

import org.springframework.data.repository.CrudRepository;

import se.kth.iv1201.recruitmentapplicationgroup5.model.Person;

public interface PersonRepository extends CrudRepository<Person, Integer>{

}
