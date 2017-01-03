package br.com.devpi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.devipi.service.DesafioSemanaService;
import br.com.devpi.model.DesafioSemana;
import br.com.devpi.repository.DesafioSemanaRepository;

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
	

}
