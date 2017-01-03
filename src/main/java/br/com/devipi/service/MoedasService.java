package br.com.devipi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.devpi.model.Moeda;

public interface MoedasService {
	
	/**
	 * Recupera uma "pagina" de moedas
	 * 
	 * @param pageable
	 * @return {@link Page} instance
	 */
	Page<Moeda> findAllPageable(Pageable pageable);

	/**
	 * Salva uma colecao de moedas
	 * 
	 * @param persons
	 * 
	 * @return collection of moedas
	 */
	Iterable<Moeda> save(Iterable<Moeda> persons);

}
