package br.com.devpi.service;

import java.util.List;

import br.com.devpi.model.CalendarioReceber;

public interface CalendarioFinanceiroReceberService {

	CalendarioReceber save(CalendarioReceber calendarioFinanceiro);
	
	List<CalendarioReceber> findAll();
	
	List<CalendarioReceber> findByAno(String ano);
	
	List<CalendarioReceber> findByAnoMes(String ano, String mes);
	
	void delete(Long codigo);
	
	String receber(Long codigo);
	
	
}
