package br.com.devpi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class IndexController {

	@RequestMapping("/")
	public ModelAndView paginaInicial(){
		ModelAndView mv = new ModelAndView("Index");
		return mv;
	}
	
}
