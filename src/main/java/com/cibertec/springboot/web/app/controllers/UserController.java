package com.cibertec.springboot.web.app.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cibertec.springboot.web.app.models.entity.Usuario;
import com.cibertec.springboot.web.app.models.service.ILibroService;
import com.cibertec.springboot.web.app.models.service.IUsuarioService;
import com.cibertec.springboot.web.app.util.paginator.PageRender;


@Controller
@SessionAttributes("usersession")
public class UserController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private ILibroService libroService;	
	
	@GetMapping({"/", "", "/login"})
	public String login() {		
		return "login";
	}
	
	@RequestMapping(value="/index", method = RequestMethod.POST)
	public String index(@RequestParam(name="user", defaultValue = "") String email
			, Model modeloUser, 
			HttpSession session) {
		Usuario us = usuarioService.findByEmail(email);
		
		modeloUser.addAttribute("titulo", "Bienvenido a Biblos, ");
		
		session.setAttribute("usuario", us);
		modeloUser.addAttribute("Usuario", email);		
		return "index";
	}
	
	@RequestMapping(value="/admin/usuarios", method = RequestMethod.GET)
	public String listadoUsuarios(@RequestParam(name="page", defaultValue = "0") 
	int page, Model modeloListado) {
		Pageable pageRequest = PageRequest.of(page, 5);
		Page<Usuario> usuarios = usuarioService.findAll(pageRequest);
		PageRender<Usuario> pageRender = new PageRender<>("/admin/usuarios", usuarios);
		
		modeloListado.addAttribute("titulo", "Listado de Usuarios");
		modeloListado.addAttribute("usuarios", usuarios);
		modeloListado.addAttribute("page", pageRender);
		return "listado_usuario";
	}
		

}