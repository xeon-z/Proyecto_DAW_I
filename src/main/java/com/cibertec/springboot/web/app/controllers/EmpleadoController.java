package com.cibertec.springboot.web.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.springboot.web.app.models.entity.Empleado;
import com.cibertec.springboot.web.app.models.entity.Usuario;
import com.cibertec.springboot.web.app.models.service.IEmpleadoService;
import com.cibertec.springboot.web.app.models.service.IRolService;
import com.cibertec.springboot.web.app.models.service.IUsuarioService;
import com.cibertec.springboot.web.app.util.paginator.PageRender;

@Controller
@RequestMapping(value = "/empleado")
@SessionAttributes(names = {"empleado","usuario", "nuevo"})
public class EmpleadoController {
	
	@Autowired
	private IEmpleadoService empleadoService;
	
	@Autowired 
	private IUsuarioService usuarioService;
	
	@Autowired
	private IRolService rolService;
	
	@RequestMapping(value="/listado", method = RequestMethod.GET)
	public String listar(@RequestParam(name="page", defaultValue = "0") 
	int page, Model modeloListado) {
		Pageable pageRequest = PageRequest.of(page, 5);
		Page<Empleado> empleados = empleadoService.findAll(pageRequest);
		PageRender<Empleado> pageRender = new PageRender<>("/empleado/listado", empleados);
		
		modeloListado.addAttribute("titulo", "Listado de Empleados");
		modeloListado.addAttribute("empleados", empleados);
		modeloListado.addAttribute("page", pageRender);
		System.out.println(modeloListado.getAttribute("empleado"));
		return "empleado/listado";
	}
	
	@RequestMapping(value = "/registro")
	public String registrar(Model model) {
		model.addAttribute("titulo","Formulario de Empleado");
		model.addAttribute("empleado", new Empleado());
		return "empleado/registro";
	}
	
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar(Model model, @Valid @ModelAttribute Empleado empleado, BindingResult result,
			SessionStatus status, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Empleado");
			return "empleado/registro";
		}
		Usuario usuario;
		
		if (empleado.getId() == 0) {
			String nombre = empleado.getNombre().toLowerCase().charAt(0) + empleado.getApellido().toLowerCase();
			String contraseña = empleado.getNombre().toLowerCase() + "123";
			usuario = new Usuario();
			usuario.setNombre(nombre);
			usuario.setContraseña(contraseña);
			usuario.setRol(rolService.findOne(empleado.getUsuario().getId()));
			usuario.setIntentos(0);
			usuario.setEstado("Activo");
			usuarioService.save(usuario);
			usuario = usuarioService.findByNombre(usuario.getNombre());
			empleado.setUsuario(usuario);
		}
		 
		empleadoService.save(empleado);
		status.setComplete();
		
		attributes.addFlashAttribute("success", "Empleado guardado correctamente.");
		return "redirect:/empleado/listado";
	}
	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable int id, RedirectAttributes attributes) {
		empleadoService.delete(id);
		attributes.addFlashAttribute("success", "Empleado eliminado correctamente.");
		return "redirect:/empleado/listado";
	}
	
}
