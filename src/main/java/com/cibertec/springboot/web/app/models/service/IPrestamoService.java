package com.cibertec.springboot.web.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cibertec.springboot.web.app.models.entity.Prestamo;

public interface IPrestamoService {

	public List<Prestamo> findAll();
	
	public Page<Prestamo> findAll(Pageable pageable);
	
	public void save(Prestamo prestamo);
	
	public Prestamo findOne(int id);
	
}
