package com.cibertec.springboot.web.app.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.springboot.web.app.models.entity.Empleado;
import com.cibertec.springboot.web.app.models.entity.Socio;
import com.cibertec.springboot.web.app.models.entity.Usuario;
import com.cibertec.springboot.web.app.models.service.IUsuarioService;
import com.cibertec.springboot.web.app.util.paginator.PageRender;


@Controller
@SessionAttributes("usuario")
public class UsuarioController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping({"/", "", "/login"})
	public String login() {		
		return "login";
	}
	
	@RequestMapping(value="/index")
	public String index(Model modeloUser, HttpSession session) {
		modeloUser.addAttribute("titulo", "Bienvenido a Biblos");
		modeloUser.addAttribute("Usuario", "");		
		return "index";
	}
	
	@RequestMapping(value="/index", method = RequestMethod.POST)
	public String index(@RequestParam(name="user", defaultValue = "") String nombre
			, Model modeloUser, 
			HttpSession session) {
		Usuario us = usuarioService.findByNombre(nombre);
		
		modeloUser.addAttribute("titulo", "Bienvenido a Biblos, ");
		
		session.setAttribute("usuario", us);
		modeloUser.addAttribute("Usuario", nombre);		
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
	
	@RequestMapping(value = "usuario/registro")
	public String registrar(Model model) {
		model.addAttribute("titulo","Formulario de Usuario");
		model.addAttribute("nuevo", new Usuario());
		return "usuario/registro";
	}
	
	@RequestMapping(value = "/usuario/guardar", method = RequestMethod.POST)
	public String guardar(Model model, @Valid @ModelAttribute Usuario usuario, BindingResult result, 
			SessionStatus status, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Usuario");
			model.addAttribute("nuevo", usuario);
			return "usuario/registro";
		}
		
		usuarioService.save(usuario);
		usuario = usuarioService.findByNombre(usuario.getNombre());
		
		if (usuario.getRol().getDescripcion().equals("Cliente")) {
			Socio socio = (Socio) model.getAttribute("socio");
			socio.setUsuario(usuario);
		} else {
			Empleado empleado = (Empleado) model.getAttribute("empleado");
			empleado.setUsuario(usuario);
		}
		
		return "usuario/registro";
	}

}