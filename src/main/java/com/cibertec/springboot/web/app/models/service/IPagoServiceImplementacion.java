package com.cibertec.springboot.web.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cibertec.springboot.web.app.models.DAO.PagoRepository;
import com.cibertec.springboot.web.app.models.entity.Pago;

@Service
public class IPagoServiceImplementacion implements IPagoService {

	@Autowired
	private PagoRepository pagoRepository;
	
	@Override
	public List<Pago> findAll() {
		return pagoRepository.findAll();
	}

	@Override
	public Page<Pago> findAll(Pageable pageable) {
		return pagoRepository.findAll(pageable);
	}

	@Override
	public void save(Pago pago) {
		pagoRepository.save(pago);
	}

	@Override
	public Pago findOne(int id) {
		return pagoRepository.findById(id).orElse(null);
	}

}
