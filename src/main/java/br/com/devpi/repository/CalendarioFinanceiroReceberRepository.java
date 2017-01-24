package br.com.devpi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.devpi.model.CalendarioReceber;

public interface CalendarioFinanceiroReceberRepository extends JpaRepository<CalendarioReceber, Long> {
	
	public List<CalendarioReceber> findByAnoContaining(String ano);
	
	public List<CalendarioReceber> findByAnoAndMesContaining(String ano, String mes);

}
