package br.com.devpi;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.devipi.service.PersonService;
import br.com.devpi.model.Person;

@Component
public class DatabaseInitializer {

	private PersonService personService;

	@Autowired
	public DatabaseInitializer(PersonService studentService) {
		this.personService = studentService;
	}

	@PostConstruct
	public void populateDatabase() {
		Person firstPerson = new Person("Barack", "Obama", 40);
		Person secondPerson = new Person("Vladimir", "Putin", 50);
		Person thirdPerson = new Person("Jong", "Kim Un", 30);
		Person fourthPerson = new Person("Angela", "Merkel", 60);
		Person fifthPerson = new Person("David", "Cameron", 60);
		Person sixthPerson = new Person(" Alexander", "Lukashenko", 60);

		personService.save(Arrays.asList(firstPerson, secondPerson, thirdPerson, fourthPerson, fifthPerson, sixthPerson));
	}

}
