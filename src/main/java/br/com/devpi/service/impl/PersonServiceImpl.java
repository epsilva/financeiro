package br.com.devpi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.devipi.service.PersonService;
import br.com.devpi.model.Person;
import br.com.devpi.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {

	private PersonRepository personRepository;

	@Autowired
	public PersonServiceImpl(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@Transactional
	@Override
	public Page<Person> findAllPageable(Pageable pageable) {
		return personRepository.findAll(pageable);
	}

	@Transactional
	@Override
	public Iterable<Person> save(Iterable<Person> persons) {
		return personRepository.save(persons);
	}

}
