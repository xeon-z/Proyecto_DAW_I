package com.cibertec.springboot.web.app.controllers;

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
import com.cibertec.springboot.web.app.models.entity.Libro;
import com.cibertec.springboot.web.app.models.entity.Usuario;
import com.cibertec.springboot.web.app.models.service.ILibroService;
import com.cibertec.springboot.web.app.models.service.IUsuarioService;
import com.cibertec.springboot.web.app.util.paginator.PageRender;


@Controller
@SessionAttributes("user")
public class UserController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private ILibroService libroService;	
	
	@GetMapping({"/", "", "/login"})
	public String login() {
		Usuario user = null;		
		return "login";
	}
	
	@RequestMapping(value="/admin/usuarios", method = RequestMethod.GET)
	public String listadoUsuarios(@RequestParam(name="page", defaultValue = "0") int page, Model modeloListado) {
		Pageable pageRequest = PageRequest.of(page, 5);
		Page<Usuario> usuarios = usuarioService.findAll(pageRequest);
		PageRender<Usuario> pageRender = new PageRender<>("/admin/usuarios", usuarios);
		
		modeloListado.addAttribute("titulo", "Listado de Usuarios");
		modeloListado.addAttribute("usuarios", usuarios);
		modeloListado.addAttribute("page", pageRender);
		return "listado_usuario";
	}
	
	@RequestMapping(value="/listado_libros", method = RequestMethod.GET)
	public String listadoLibros(@RequestParam(name="page", defaultValue = "0") int page,Model modeloListado) {
		Pageable pageRequest = PageRequest.of(page, 5);
		Page<Libro> libros = libroService.findAll(pageRequest);
		PageRender<Libro> pageRender = new PageRender<>("/listado_libros", libros);
		
		modeloListado.addAttribute("titulo", "Listado de Libros");
		modeloListado.addAttribute("libros", libros);
		modeloListado.addAttribute("page", pageRender);
		return "listado_libros";
	}
}
