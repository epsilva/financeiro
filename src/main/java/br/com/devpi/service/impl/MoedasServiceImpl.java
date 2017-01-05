package br.com.devpi.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import br.com.devipi.service.MoedasService;
import br.com.devpi.model.Moeda;
import br.com.devpi.repository.MoedaRepository;

@Service
public class MoedasServiceImpl implements MoedasService  {
	
	private MoedaRepository moedaRepository;

	@Autowired
	public MoedasServiceImpl(MoedaRepository moedaRepository) {
		this.moedaRepository = moedaRepository;
	}

	@Transactional
	@Override
	public Page<Moeda> findAllPageable(Pageable pageable) {
		return moedaRepository.findAll(pageable);
	}

	@Transactional
	@Override
	public Iterable<Moeda> save(Iterable<Moeda> moeda) {
		return moedaRepository.save(moeda);
	}

	@Override
	public void delete(Long codigo) {
		moedaRepository.delete(codigo);
		
	}

	@Override
	public void somarValoresMoedas(Page<Moeda> moedas, ModelAndView modelAndView) {
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

	@Override
	public void somarValorePorMes(Page<Moeda> moedas, ModelAndView modelAndView) {
		Iterator<Moeda> it = moedas.iterator();
		Format formatMesAno = new SimpleDateFormat("MM/YYYY");
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		while(it.hasNext()){
			BigDecimal somaUm = new BigDecimal(0);
			BigDecimal somaCinquenta = new BigDecimal(0);
			BigDecimal somaVinteCinco = new BigDecimal(0);
			BigDecimal somaDez = new BigDecimal(0);
			BigDecimal somaCinco = new BigDecimal(0);
			
			BigDecimal valor = new BigDecimal(0);
			
			Moeda moedaObject = (Moeda)it.next();
			
			somaUm = new BigDecimal(moedaObject.getUmReal());
			somaCinquenta = new BigDecimal(moedaObject.getCinquentaCentavos()*0.5).setScale(2, RoundingMode.HALF_EVEN);
			somaVinteCinco = new BigDecimal(moedaObject.getVinteCincoCentavos()*0.25).setScale(2, RoundingMode.HALF_EVEN);
			somaDez = new BigDecimal(moedaObject.getDezCentavos()*0.10).setScale(2, RoundingMode.HALF_EVEN);
			somaCinco = new BigDecimal(moedaObject.getCincoCentavos()*0.05).setScale(2, RoundingMode.HALF_EVEN);
			
			valor = somaUm.add(somaCinquenta).add(somaVinteCinco).add(somaDez).add(somaCinco).setScale(2, RoundingMode.HALF_EVEN);
			
			if(map.get(formatMesAno.format(moedaObject.getDataDeposito())) != null){
				BigDecimal valorSoma = map.get(formatMesAno.format(moedaObject.getDataDeposito())).add(valor);
				map.put(formatMesAno.format(moedaObject.getDataDeposito()), valorSoma);
			}else{
				map.put(formatMesAno.format(moedaObject.getDataDeposito()), valor);
			}
			
			List<Moeda> listaValorMoedasMesAno = new ArrayList<Moeda>();
			BigDecimal valorTotal = new BigDecimal(0);
			for(Map.Entry<String, BigDecimal> entry : map.entrySet()){
				Moeda moedaMesAno = new Moeda();
				moedaMesAno.setDataDepositoMesAno(entry.getKey());
				moedaMesAno.setValorDepositado(entry.getValue().setScale(2, RoundingMode.HALF_EVEN));
				listaValorMoedasMesAno.add(moedaMesAno);
				valorTotal = valorTotal.add(entry.getValue().setScale(2, RoundingMode.HALF_EVEN));
			}
			
			Collections.sort(listaValorMoedasMesAno);
			
			modelAndView.addObject("listaValorMesAno", listaValorMoedasMesAno);
			modelAndView.addObject("totalValorMesAno", valorTotal);
		}
	}

}
