package br.com.devpi.service;

import java.util.List;

import br.com.devpi.model.Calendario;

public interface CalendarioFinanceiroService {
	
	Calendario save(Calendario calendarioFinanceiro);
	
	List<Calendario> findAll();
	
	List<Calendario> findByAno(String ano);
	
	List<Calendario> findByAnoMes(String ano, String mes);
	
	void delete(Long codigo);
	
	String receber(Long codigo);
	
}
