package br.com.devpi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.ModelAndView;

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
	
	/**
	 *Método para remover um deposito
	 * @param Long codigo
	 */
	void delete(Long codigo);
	
	/**
	 * Soma valor de todas as moedas
	 * @param moedas
	 * @param modelAndView
	 */
	void somarValoresMoedas(Page<Moeda> moedas, ModelAndView modelAndView);
	
	/**
	 * Soma valor de todas as moedas cadastradas no mes
	 * @param moedas
	 * @param modelAndView
	 */
	void somarValorePorMes(Page<Moeda> moedas, ModelAndView modelAndView);
	
}
