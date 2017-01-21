package br.com.devpi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.devpi.model.Calendario;

public interface CalendarioFinanceiroRepository extends JpaRepository<Calendario, Long> {
	
	public List<Calendario> findByAnoContaining(String ano);
	
	public List<Calendario> findByAnoAndMesContaining(String ano, String mes);

}
