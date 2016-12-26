package br.com.devpi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.devpi.model.Moeda;
import br.com.devpi.repository.Moedas;

@Controller
@RequestMapping("/financeiro")
public class FinanceiroController {
	
	@Autowired
	private Moedas moedas;

	@RequestMapping("/desafio")
	public ModelAndView desafioSemana(){
		ModelAndView mv = new ModelAndView("DesafioSemana");
		return mv;
	}
	
	@RequestMapping("/moedas")
	public ModelAndView moedas(){
		return pesquisar();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(Moeda moeda){
		moedas.save(moeda);
		return "redirect:/financeiro/moedas";
	}
	
	@RequestMapping
	public ModelAndView pesquisar(){
		List<Moeda> todasMoedas = moedas.findAll();
		ModelAndView mv = new ModelAndView("Moedas");
		mv.addObject(new Moeda());
		if(todasMoedas.isEmpty()){
			return mv;
		}
		mv.addObject("moedas", todasMoedas);
		return mv;
	}

}
