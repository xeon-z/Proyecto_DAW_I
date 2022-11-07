package com.cibertec.springboot.web.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cibertec.springboot.web.app.models.DAO.PrestamoRepository;
import com.cibertec.springboot.web.app.models.entity.Prestamo;

@Service
public class IPrestamoServiceImplementacion implements IPrestamoService {

	@Autowired
	private PrestamoRepository prestamoRepository;
	
	@Override
	public List<Prestamo> findAll() {
		return prestamoRepository.findAll();
	}

	@Override
	public Page<Prestamo> findAll(Pageable pageable) {
		return prestamoRepository.findAll(pageable);
	}

	@Override
	public void save(Prestamo prestamo) {
		prestamoRepository.save(prestamo);
	}

	@Override
	public Prestamo findOne(int id) {
		return prestamoRepository.findById(id).orElse(null);
	}

}
