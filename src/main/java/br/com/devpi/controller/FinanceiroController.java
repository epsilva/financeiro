package br.com.devpi.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.devipi.service.DesafioSemanaService;
import br.com.devipi.service.MoedasService;
import br.com.devpi.model.DesafioSemana;
import br.com.devpi.model.Moeda;
import br.com.devpi.model.Pager;

@Controller
@RequestMapping("/financeiro")
public class FinanceiroController {
	
	private static final int BUTTONS_TO_SHOW = 5;
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 10;

	@Autowired
	private MoedasService moedasService;
	
	@Autowired
	private DesafioSemanaService desafioSemanaService;

	/**
	 * Pesquisa os depositos do desafio das 52 semanas para apresentar na tela com paginacao
	 * @param pageSize
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/desafio", method = RequestMethod.GET)
	public ModelAndView pesquisarDesafioSemana(@RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "page", required = false) Integer page){
		ModelAndView modelAndView = new ModelAndView("DesafioSemana");
		modelAndView.addObject(new DesafioSemana());

		int evalPageSize = pageSize == null ? 12 : pageSize;
		int evalPage = (page == null || page < 1) ? INITIAL_PAGE : page - 1;

		Page<DesafioSemana> desafioSemana = desafioSemanaService.findAllPageable(new PageRequest(evalPage, evalPageSize));
		somarValoresDesafioSemana(desafioSemana, modelAndView);
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
	@RequestMapping(value="/desafio/novo", method = RequestMethod.POST)
	public String salvarDesafioSemana(DesafioSemana desafioSemana){
		desafioSemanaService.save(Arrays.asList(desafioSemana));
		return "redirect:/financeiro/desafio";
	}
	
	/**
	 * Método que altera o objeto Moeda
	 * @param moeda
	 * @return
	 */
	@RequestMapping(value="/desafio/novo/{codigo}")
	public ModelAndView edicaoDesafioSemana(@PathVariable("codigo") DesafioSemana desafioSemana, @RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "page", required = false) Integer page){
		ModelAndView modelAndView = new ModelAndView("DesafioSemana");
		modelAndView.addObject(desafioSemana);
		
		int evalPageSize = pageSize == null ? 12 : pageSize;
		int evalPage = (page == null || page < 1) ? INITIAL_PAGE : page - 1;

		Page<DesafioSemana> desafio = desafioSemanaService.findAllPageable(new PageRequest(evalPage, evalPageSize));
		somarValoresDesafioSemana(desafio, modelAndView);
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
	@RequestMapping(value="/desafio/novo/{codigo}", method = RequestMethod.DELETE)
	public String excluirDesafioSemana(@PathVariable Long codigo){
		desafioSemanaService.delete(codigo);
		return "redirect:/financeiro/desafio";
	}
	
	private void somarValoresDesafioSemana(Page<DesafioSemana> desafioSemana, ModelAndView modelAndView){
		BigDecimal somaValores = new BigDecimal(0);
		Iterator<DesafioSemana> it = desafioSemana.iterator();
		List<DesafioSemana> listaDepositos = new ArrayList<DesafioSemana>();
		while(it.hasNext()){
			listaDepositos.add((DesafioSemana)it.next());
		}
		for(DesafioSemana moeda : listaDepositos){
			somaValores = somaValores.add(moeda.getValorDepositado());
		}
		modelAndView.addObject("somaValores", somaValores);
	}
	
	/**
	 * Pesquisa as moedas para apresentar na tela com paginacao
	 * @param pageSize
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/moedas", method = RequestMethod.GET)
	public ModelAndView pesquisarMoedas(@RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "page", required = false) Integer page){
		ModelAndView modelAndView = new ModelAndView("Moedas");
		modelAndView.addObject(new Moeda());

		int evalPageSize = pageSize == null ? INITIAL_PAGE_SIZE : pageSize;
		int evalPage = (page == null || page < 1) ? INITIAL_PAGE : page - 1;

		Page<Moeda> moedas = moedasService.findAllPageable(new PageRequest(evalPage, evalPageSize));
		Pager pager = new Pager(moedas.getTotalPages(), moedas.getNumber(), BUTTONS_TO_SHOW);
		somarValoresMoedas(moedas, modelAndView);
		modelAndView.addObject("moedas", moedas);
		modelAndView.addObject("selectedPageSize", evalPageSize);
		modelAndView.addObject("pager", pager);
		return modelAndView;
	}
	
	
	/**
	 * Chama a tela de cadastro de Moeda
	 * @return
	 */
	@RequestMapping("/novo")
	public ModelAndView novo(){
		ModelAndView mv = new ModelAndView("CadastroMoedas");
		mv.addObject(new Moeda());
		return mv;
	}
	
	/**
	 * Método para salvar objeto Moeda
	 * @param moeda
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(Moeda moeda){
		moedasService.save(Arrays.asList(moeda));
		return "redirect:/financeiro/novo";
	}
	
	/**
	 * Método que altera o objeto Moeda
	 * @param moeda
	 * @return
	 */
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Moeda moeda){
		ModelAndView mv = new ModelAndView("CadastroMoedas");
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
		return "redirect:/financeiro/moedas";
	}
	
	private void somarValoresMoedas(Page<Moeda> moedas, ModelAndView modelAndView){
		Double somaUm = 0.0;
		Double somaCinquenta = 0.0;
		Double somaVinteCinco = 0.0;
		Double somaDez = 0.0;
		Double somaCinco = 0.0;
		Iterator<Moeda> it = moedas.iterator();
		List<Moeda> listaMoedas = new ArrayList<Moeda>();
		while(it.hasNext()){
			listaMoedas.add((Moeda)it.next());
		}
		for(Moeda moeda : listaMoedas){
			somaUm += moeda.getUmReal();
			somaCinquenta += moeda.getCinquentaCentavos();
			somaVinteCinco += moeda.getVinteCincoCentavos();
			somaDez += moeda.getDezCentavos();
			somaCinco += moeda.getCincoCentavos();
		}
		DecimalFormat formato = new DecimalFormat("#.##");      
		modelAndView.addObject("somaUm", formato.format(somaUm));
		modelAndView.addObject("somaCinquenta", formato.format(somaCinquenta*0.5));
		modelAndView.addObject("somaVinteCinco", formato.format(somaVinteCinco*0.25));
		modelAndView.addObject("somaDez", formato.format(somaDez*0.10));
		modelAndView.addObject("somaCinco", formato.format(somaCinco*0.05));
	}
	
}
