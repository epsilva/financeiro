package br.com.devpi.service;

import java.util.List;

import br.com.devpi.model.Calendario;

public interface CalendarioFinanceiroService {
	
	Calendario save(Calendario calendarioFinanceiro);
	
	List<Calendario> findAll();
	
	List<Calendario> findByAno(String ano);
	
	void delete(Long codigo);
	
}
