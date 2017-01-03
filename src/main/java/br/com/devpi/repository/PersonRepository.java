package br.com.devpi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.devpi.model.Person;

@Repository
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

}
