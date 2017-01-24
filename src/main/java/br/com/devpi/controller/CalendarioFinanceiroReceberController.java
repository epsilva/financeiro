package br.com.devpi.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.devpi.model.Calendario;
import br.com.devpi.model.CalendarioReceber;
import br.com.devpi.model.MesesEnum;
import br.com.devpi.model.ParametroEnum;
import br.com.devpi.model.Status;
import br.com.devpi.service.CalendarioFinanceiroReceberService;

@Controller
@RequestMapping("/calendario/receber")
public class CalendarioFinanceiroReceberController {
	private static String ATRIBUTO_MENSAGEM = "mensagem";
	private static String MSG_SALVO_CONSUCESSO = "Conta salvo com sucesso!!";
	private static String MSG_EXCLUSAO_CONSUCESSO = "Conta removida com sucesso!";
	
	@Autowired
	private CalendarioFinanceiroReceberService calendarioFinanceiroService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView calendario(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		List<CalendarioReceber> listaCalendario = calendarioFinanceiroService.findByAno(String.valueOf(calendar.get(Calendar.YEAR)));
		ModelAndView mv = modelCalendarioMeses(listaCalendario);
		mv.addObject(new CalendarioReceber());
		return mv;
	}
	
	@RequestMapping(value="ano/{ano}", method = RequestMethod.GET)
	public ModelAndView calendario(@PathVariable String ano, Calendario calendario){
		List<CalendarioReceber> listaCalendario = calendarioFinanceiroService.findByAno(ano);
		ModelAndView mv = modelCalendarioMeses(listaCalendario); 
		mv.addObject(calendario);
		return mv;
	}
	
	private ModelAndView modelCalendarioMeses(List<CalendarioReceber> listaCalendario){
		ModelAndView mv = new ModelAndView("CalendarioReceber");
		CalendarioReceber calendario = new CalendarioReceber();
		List<CalendarioReceber> listaCal = new ArrayList<CalendarioReceber>();
		if(!listaCalendario.isEmpty()){
			Collections.sort(listaCalendario);
		}
		for(CalendarioReceber cal : listaCalendario){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(cal.getData());
			if(calendario.getData() != null){
				Calendar calendarCalendario = Calendar.getInstance();
				calendarCalendario.setTime(calendario.getData());
				if(!(calendarCalendario.get(Calendar.MONTH) == calendar.get(Calendar.MONTH))){
					listaCal = new ArrayList<CalendarioReceber>();
					calendario = new CalendarioReceber();
				}
			}
			calendario.setAno(cal.getAno());
			calendario.setMes(cal.getMes());
			calendario.setCodigo(cal.getCodigo());
			calendario.setData(cal.getData());
			calendario.setDescricao(cal.getDescricao());
			if(!cal.isCancelado()){
				calendario.setValorTotalMes(cal.getValor().add(calendario.getValorTotalMes()));
			}
			if(cal.isPago()){
				calendario.setValorTotalPago(cal.getValor().add(calendario.getValorTotalPago()));
			}
			if(cal.isPendente()){
				calendario.setValorTotalPendente(cal.getValor().add(calendario.getValorTotalPendente()));
			}
			calendario.setValor(cal.getValor());
			listaCal.add(cal);
			calendario.setListaCalendario(listaCal);
			mv.addObject(MesesEnum.getDescricaoById(calendar.get(Calendar.MONTH)), calendario);
		}
		return mv;
	}
	
	@RequestMapping(value = "/novo")
	public ModelAndView novo(){
		ModelAndView mv = new ModelAndView("CadastroCalendarioReceber");
		mv.addObject(new CalendarioReceber());
		return mv;
	}
	
	@RequestMapping("/novo/{codigo}")
	public String edicao(@PathVariable("codigo") CalendarioReceber calendario, Model model){
		model.addAttribute(calendario);
		return "CadastroCalendarioReceber :: cadastroCalendarioReceber";
	}
	
	@RequestMapping("/detalhe/{ano}/{mes}")
	public String detalheCalendario(@PathVariable("ano") String ano,@PathVariable("mes") String mes , Model model, CalendarioReceber calendario){
		model.addAttribute("listaCalendario", calendarioFinanceiroService.findByAnoMes(ano, mes));
		model.addAttribute("mes", MesesEnum.getDescricaoById(Integer.parseInt(mes)));
		return "DetalhesCalendarioReceber :: listaCalendarioReceber";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvarCalendario(@Validated CalendarioReceber calendarioFinanceiro, Errors erros, RedirectAttributes attributes){
		if(erros.hasErrors()){
			return "CadastroCalendarioReceber :: cadastroCalendarioReceber";
		}
		try{
			calendarioFinanceiroService.save(calendarioFinanceiro);
			attributes.addFlashAttribute(ATRIBUTO_MENSAGEM, MSG_SALVO_CONSUCESSO);
			return "redirect:/calendario/receber";
		}catch (Exception e) {
			erros.rejectValue("data", null, e.getMessage());
			return "CadastroCalendarioReceber :: cadastroCalendarioReceber";
		}
	}
	
	@RequestMapping(value="{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes){
		calendarioFinanceiroService.delete(codigo);
		attributes.addFlashAttribute(ATRIBUTO_MENSAGEM, MSG_EXCLUSAO_CONSUCESSO);
		return "redirect:/calendario/receber";
	}
	
	@RequestMapping(value = "/{codigo}/receber", method = RequestMethod.PUT)
	public @ResponseBody String receber(@PathVariable Long codigo){
		return calendarioFinanceiroService.receber(codigo);
	}
	
	@ModelAttribute("anoEnum")
	public List<ParametroEnum> anoEnum(){
		return Arrays.asList(ParametroEnum.values());
	}
	
	@ModelAttribute("status")
	public List<Status> statusTitulo(){
		return Arrays.asList(Status.values());
	}
}
