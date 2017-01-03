package br.com.devpi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
