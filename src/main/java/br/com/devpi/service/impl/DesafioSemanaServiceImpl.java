package br.com.devpi.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import br.com.devpi.model.DesafioSemana;
import br.com.devpi.repository.DesafioSemanaRepository;
import br.com.devpi.service.DesafioSemanaService;

@Service
public class DesafioSemanaServiceImpl implements DesafioSemanaService{

	private DesafioSemanaRepository desafioSemanaRepository;

	@Autowired
	public DesafioSemanaServiceImpl(DesafioSemanaRepository desafioSemanaRepository) {
		this.desafioSemanaRepository = desafioSemanaRepository;
	}

	@Override
	public Page<DesafioSemana> findAllPageable(Pageable pageable) {
		return desafioSemanaRepository.findAll(pageable);
	}

	@Override
	public Iterable<DesafioSemana> save(Iterable<DesafioSemana> desafioSemana) {
		return desafioSemanaRepository.save(desafioSemana);
	}

	@Override
	public void delete(Long codigo) {
		desafioSemanaRepository.delete(codigo);
	}

	@Override
	public void somarValoresDesafioSemana(Page<DesafioSemana> desafioSemana, ModelAndView modelAndView) {
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
	

}
