package br.com.devpi.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devpi.model.CalendarioReceber;
import br.com.devpi.model.Status;
import br.com.devpi.repository.CalendarioFinanceiroReceberRepository;
import br.com.devpi.service.CalendarioFinanceiroReceberService;

@Service
public class CalendarioFinanceiroReceberServiceImpl implements CalendarioFinanceiroReceberService{
	
private CalendarioFinanceiroReceberRepository calendarioFinanceriroReceberRepository;
	
	@Autowired
	public CalendarioFinanceiroReceberServiceImpl(CalendarioFinanceiroReceberRepository calendarioFinanceriroReceberRepository) {
		this.calendarioFinanceriroReceberRepository = calendarioFinanceriroReceberRepository;
	}
	
	@Override
	public CalendarioReceber save(CalendarioReceber calendarioFinanceiro) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(calendarioFinanceiro.getData());
		calendarioFinanceiro.setAno(String.valueOf(calendar.get(Calendar.YEAR)));
		calendarioFinanceiro.setMes(String.valueOf(calendar.get(Calendar.MONTH)));
		return calendarioFinanceriroReceberRepository.save(calendarioFinanceiro) ;
	}

	@Override
	public void delete(Long codigo) {
		calendarioFinanceriroReceberRepository.delete(codigo);
		
	}

	@Override
	public List<CalendarioReceber> findAll() {
		return calendarioFinanceriroReceberRepository.findAll();
	}

	@Override
	public List<CalendarioReceber> findByAno(String ano) {
		return calendarioFinanceriroReceberRepository.findByAnoContaining(ano);
	}

	@Override
	public List<CalendarioReceber> findByAnoMes(String ano, String mes) {
		return calendarioFinanceriroReceberRepository.findByAnoAndMesContaining(ano, mes);
	}

	@Override
	public String receber(Long codigo) {
		CalendarioReceber calendario = calendarioFinanceriroReceberRepository.findOne(codigo);
		calendario.setStatus(Status.PAGO);
		calendarioFinanceriroReceberRepository.save(calendario);
		return Status.PAGO.getDescricao();
	}
	

}
