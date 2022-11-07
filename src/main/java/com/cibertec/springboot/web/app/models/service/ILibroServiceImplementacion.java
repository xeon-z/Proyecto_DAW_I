package com.cibertec.springboot.web.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cibertec.springboot.web.app.models.DAO.LibroRepository;
import com.cibertec.springboot.web.app.models.entity.Libro;


@Service
public class ILibroServiceImplementacion implements ILibroService {

	@Autowired
	private LibroRepository libroDao;	
	
	@Override
	@Transactional(readOnly = true)
	public List<Libro> findAll() {
		return (List<Libro>) libroDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Libro> findAll(Pageable pageable) {
		return libroDao.findAll(pageable);
	}

	@Override
	@Transactional
	public void save(Libro libro) {
		libroDao.save(libro);
	}

	@Override
	@Transactional(readOnly = true)
	public Libro findOne(Long id) {
		return libroDao.findById(id).orElse(null);		
	}

	@Override
	@Transactional
	public void prestarLibro(Libro libro) {
		libro.setStock(libro.getStock()-1);
		libroDao.save(libro);
	}

	
}
