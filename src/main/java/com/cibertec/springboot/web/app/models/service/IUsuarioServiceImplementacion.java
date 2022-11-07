package com.cibertec.springboot.web.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cibertec.springboot.web.app.models.DAO.IUsuarioDAO;
import com.cibertec.springboot.web.app.models.DAO.UsuarioRepository;
import com.cibertec.springboot.web.app.models.entity.Usuario;

@Service
public class IUsuarioServiceImplementacion implements IUsuarioService {

	@Autowired
	private IUsuarioDAO usuarioDao;
	
	@Autowired
	private UsuarioRepository userRepo;
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Usuario> findAll(Pageable pageable) {
		return usuarioDao.findAll(pageable);
	}

	@Override
	@Transactional
	public void save(Usuario usuario) {
		usuarioDao.save(usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findOne(String Id) {
		return usuarioDao.findById(Id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findByEmail(String email) {		
		return userRepo.findByEmail(email) ;
	}

}
