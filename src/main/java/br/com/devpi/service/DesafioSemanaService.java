package br.com.devpi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.ModelAndView;

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
	
	/**
	 * Soma os valores depositados
	 * @param desafioSemana
	 * @param modelAndView
	 */
	void somarValoresDesafioSemana(Page<DesafioSemana> desafioSemana, ModelAndView modelAndView);

}
