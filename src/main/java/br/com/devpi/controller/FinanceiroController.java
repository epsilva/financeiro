package br.com.devpi.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.devipi.service.MoedasService;
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

	@RequestMapping("/desafio")
	public ModelAndView desafioSemana(){
		ModelAndView mv = new ModelAndView("DesafioSemana");
		return mv;
	}
	
	@RequestMapping(value = "/moedas", method = RequestMethod.GET)
	public ModelAndView pesquisarMoedas(@RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "page", required = false) Integer page){
		ModelAndView modelAndView = new ModelAndView("Moedas");
		modelAndView.addObject(new Moeda());

		int evalPageSize = pageSize == null ? INITIAL_PAGE_SIZE : pageSize;
		int evalPage = (page == null || page < 1) ? INITIAL_PAGE : page - 1;

		Page<Moeda> moedas = moedasService.findAllPageable(new PageRequest(evalPage, evalPageSize));
		Pager pager = new Pager(moedas.getTotalPages(), moedas.getNumber(), BUTTONS_TO_SHOW);

		modelAndView.addObject("moedas", moedas);
		modelAndView.addObject("selectedPageSize", evalPageSize);
		modelAndView.addObject("pager", pager);
		return modelAndView;
	}
	
	@RequestMapping(value = "/teste", method = RequestMethod.GET)
	public ModelAndView pesquisarTeste(@RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "page", required = false) Integer page){
		ModelAndView modelAndView = new ModelAndView("Teste");
		modelAndView.addObject("moedas", new Moeda());

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
	
	@RequestMapping("/novo")
	public ModelAndView novo(){
		ModelAndView mv = new ModelAndView("CadastroMoedas");
		mv.addObject("moedas", new Moeda());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(Moeda moeda){
		moedasService.save(Arrays.asList(moeda));
		return "redirect:/financeiro/teste";
	}
	
	private void somarValoresMoedas(Page<Moeda> moedas, ModelAndView modelAndView){
		Double somaUm = 0.0;
		Double somaCinquenta = 0.0;
		Double somaVinteCinco = 0.0;
		Double somaDez = 0.0;
		Double somaCinco = 0.0;
		Iterator it = moedas.iterator();
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
