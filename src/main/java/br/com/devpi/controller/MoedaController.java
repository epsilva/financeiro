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

import br.com.devpi.model.Moeda;
import br.com.devpi.model.Pager;
import br.com.devpi.service.MoedasService;

@Controller
@RequestMapping("/moedas")
public class MoedaController {

	private static final int BUTTONS_TO_SHOW = 5;
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 10;

	@Autowired
	private MoedasService moedasService;

	/**
	 * Pesquisa as moedas para apresentar na tela com paginacao
	 * 
	 * @param pageSize
	 * @param page
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView pesquisarMoedas(@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "page", required = false) Integer page) {
		ModelAndView modelAndView = new ModelAndView("Moedas");
		modelAndView.addObject(new Moeda());

		int evalPageSize = pageSize == null ? INITIAL_PAGE_SIZE : pageSize;
		int evalPage = (page == null || page < 1) ? INITIAL_PAGE : page - 1;

		Page<Moeda> moedas = moedasService.findAllPageable(new PageRequest(evalPage, evalPageSize));
		Pager pager = new Pager(moedas.getTotalPages(), moedas.getNumber(), BUTTONS_TO_SHOW);
		moedasService.somarValoresMoedas(moedas, modelAndView);
		modelAndView.addObject("moedas", moedas);
		modelAndView.addObject("selectedPageSize", evalPageSize);
		modelAndView.addObject("pager", pager);
		return modelAndView;
	}
	
	/**
	 * Pesquisa as moedas para apresentar na tela com paginacao
	 * @param pageSize
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/cadastro", method = RequestMethod.GET)
	public ModelAndView pesquisarMoedasMesAno(@RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "page", required = false) Integer page){
		ModelAndView modelAndView = new ModelAndView("CadastroMoedas");
		modelAndView.addObject(new Moeda());

		int evalPageSize = pageSize == null ? 13 : pageSize;
		int evalPage = (page == null || page < 1) ? INITIAL_PAGE : page - 1;

		Page<Moeda> moedas = moedasService.findAllPageable(new PageRequest(evalPage, evalPageSize));
		moedasService.somarValorePorMes(moedas, modelAndView);
		Pager pager = new Pager(moedas.getTotalPages(), moedas.getNumber(), BUTTONS_TO_SHOW);
		modelAndView.addObject("moedas", moedas);
		modelAndView.addObject("selectedPageSize", evalPageSize);
		modelAndView.addObject("pager", pager);
		return modelAndView;
	}
	
	/**
	 * Método para salvar objeto Moeda
	 * @param moeda
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(Moeda moeda){
		moedasService.save(Arrays.asList(moeda));
		return "redirect:/moedas/cadastro";
	}
	
	/**
	 * Método que altera o objeto Moeda
	 * @param moeda
	 * @return
	 */
	@RequestMapping("/cadastro/{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Moeda moeda, @RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "page", required = false) Integer page){
		ModelAndView mv = pesquisarMoedasMesAno(pageSize, page);
		mv.addObject(moeda);
		return mv;
	}
	
	/**
	 * Método para remover um deposito
	 * @param codigo
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value="{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo){
		moedasService.delete(codigo);
		return "redirect:/moedas";
	}
}
