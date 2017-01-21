package br.com.devpi.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devpi.model.Calendario;
import br.com.devpi.repository.CalendarioFinanceiroRepository;
import br.com.devpi.service.CalendarioFinanceiroService;

@Service
public class CalendarioFinanceiroServiceImpl implements CalendarioFinanceiroService {

	private CalendarioFinanceiroRepository calendarioFinanceriroRepository;
	
	@Autowired
	public CalendarioFinanceiroServiceImpl(CalendarioFinanceiroRepository calendarioFinanceriroRepository) {
		this.calendarioFinanceriroRepository = calendarioFinanceriroRepository;
	}
	
	@Override
	public Calendario save(Calendario calendarioFinanceiro) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(calendarioFinanceiro.getData());
		calendarioFinanceiro.setAno(String.valueOf(calendar.get(Calendar.YEAR)));
		calendarioFinanceiro.setMes(String.valueOf(calendar.get(Calendar.MONTH)));
		return calendarioFinanceriroRepository.save(calendarioFinanceiro) ;
	}

	@Override
	public void delete(Long codigo) {
		calendarioFinanceriroRepository.delete(codigo);
		
	}

	@Override
	public List<Calendario> findAll() {
		return calendarioFinanceriroRepository.findAll();
	}

	@Override
	public List<Calendario> findByAno(String ano) {
		return calendarioFinanceriroRepository.findByAnoContaining(ano);
	}

	@Override
	public List<Calendario> findByAnoMes(String ano, String mes) {
		return calendarioFinanceriroRepository.findByAnoAndMesContaining(ano, mes);
	}
	
	

}
