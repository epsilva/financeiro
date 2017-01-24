
package br.com.devpi;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.devpi.model.Calendario;
import br.com.devpi.model.Status;
import br.com.devpi.service.CalendarioFinanceiroService;

@Component
public class DatabaseInitializer {

	private CalendarioFinanceiroService personService;

	@Autowired
	public DatabaseInitializer(CalendarioFinanceiroService studentService) {
		this.personService = studentService;
	}

	@PostConstruct
	public void populateDatabase() {
		Calendario janeiro = new Calendario();
		janeiro.setData(new Date());
		janeiro.setDescricao("Janeiro");
		janeiro.setValor(new BigDecimal("100"));
		janeiro.setAno("2017");
		janeiro.setStatus(Status.PENDENTE);
		
		Calendario janeiro2 = new Calendario();
		janeiro2.setData(new Date());
		janeiro2.setDescricao("Janeiro");
		janeiro2.setValor(new BigDecimal("100"));
		janeiro2.setAno("2017");
		janeiro2.setStatus(Status.PENDENTE);
		
		Calendario fevereiro = new Calendario();
		Calendar c = Calendar.getInstance();
		c.set(2017, 1, 2);
		fevereiro.setData(c.getTime());
		fevereiro.setDescricao("Fevereiro");
		fevereiro.setValor(new BigDecimal("100"));
		fevereiro.setAno("2017");
		fevereiro.setStatus(Status.PENDENTE);
		
		Calendario marco = new Calendario();
		Calendar cm = Calendar.getInstance();
		cm.set(2018, 2, 2);
		marco.setData(c.getTime());
		marco.setDescricao("Fevereiro");
		marco.setValor(new BigDecimal("100"));
		marco.setAno("2018");
		marco.setStatus(Status.PENDENTE);
		
		
//		personService.save(janeiro);
//		personService.save(janeiro2);
//		personService.save(fevereiro);
//		personService.save(marco);
	}

}
