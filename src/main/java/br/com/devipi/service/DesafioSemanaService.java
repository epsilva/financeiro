package br.com.devipi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.devpi.model.DesafioSemana;

public interface DesafioSemanaService {
	
	/**
	 * Recupera uma "pagina" de moedas
	 * 
	 * @param pageable
	 * @return {@link Page} instance
	 */
	Page<DesafioSemana> findAllPageable(Pageable pageable);

	/**
	 * Salva uma colecao de moedas
	 * 
	 * @param persons
	 * 
	 * @return collection of moedas
	 */
	Iterable<DesafioSemana> save(Iterable<DesafioSemana> desafioSemana);
	
	
	/**
	 *Método para remover um deposito
	 * @param Long codigo
	 */
	void delete(Long codigo);

}
