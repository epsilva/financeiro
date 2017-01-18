package br.com.devpi.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.devpi.model.DesafioSemana;
import br.com.devpi.model.Pager;
import br.com.devpi.service.DesafioSemanaService;

@Controller
@RequestMapping("/desafio")
public class DesafioController {
	
	private static final int BUTTONS_TO_SHOW = 5;
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 12;

	@Autowired
	private DesafioSemanaService desafioSemanaService;

	/**
	 * Pesquisa os depositos do desafio das 52 semanas para apresentar na tela com paginacao
	 * @param pageSize
	 * @param page
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView pesquisarDesafioSemana(@RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "page", required = false) Integer page){
		ModelAndView modelAndView = new ModelAndView("DesafioSemana");
		modelAndView.addObject(new DesafioSemana());

		int evalPageSize = pageSize == null ? 12 : pageSize;
		int evalPage = (page == null || page < 1) ? INITIAL_PAGE : page - 1;

		Page<DesafioSemana> desafioSemana = desafioSemanaService.findAllPageable(new PageRequest(evalPage, evalPageSize));
		desafioSemanaService.somarValoresDesafioSemana(desafioSemana, modelAndView);
		Pager pager = new Pager(desafioSemana.getTotalPages(), desafioSemana.getNumber(), BUTTONS_TO_SHOW);
		modelAndView.addObject("desafiosSemana", desafioSemana);
		modelAndView.addObject("selectedPageSize", evalPageSize);
		modelAndView.addObject("pager", pager);
		return modelAndView;
	}
	
	/**
	 * Método para salvar objeto DesafioSemana
	 * @param moeda
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String salvarDesafioSemana(DesafioSemana desafioSemana){
		desafioSemanaService.save(Arrays.asList(desafioSemana));
		return "redirect:/desafio";
	}
	
	/**
	 * Método que altera o objeto Moeda
	 * @param moeda
	 * @return
	 */
	@RequestMapping(value="/{codigo}")
	public ModelAndView edicaoDesafioSemana(@PathVariable("codigo") DesafioSemana desafioSemana, @RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "page", required = false) Integer page){
		ModelAndView modelAndView = new ModelAndView("DesafioSemana");
		modelAndView.addObject(desafioSemana);
		
		int evalPageSize = pageSize == null ? INITIAL_PAGE_SIZE : pageSize;
		int evalPage = (page == null || page < 1) ? INITIAL_PAGE : page - 1;

		Page<DesafioSemana> desafio = desafioSemanaService.findAllPageable(new PageRequest(evalPage, evalPageSize));
		desafioSemanaService.somarValoresDesafioSemana(desafio, modelAndView);
		Pager pager = new Pager(desafio.getTotalPages(), desafio.getNumber(), BUTTONS_TO_SHOW);
		modelAndView.addObject("desafiosSemana", desafio);
		modelAndView.addObject("selectedPageSize", evalPageSize);
		modelAndView.addObject("pager", pager);
		
		return modelAndView;
	}

	/**
	 * Método para remover um deposito
	 * @param codigo
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value="/{codigo}", method = RequestMethod.DELETE)
	public String excluirDesafioSemana(@PathVariable Long codigo){
		desafioSemanaService.delete(codigo);
		return "redirect:/desafio";
	}
	
}
