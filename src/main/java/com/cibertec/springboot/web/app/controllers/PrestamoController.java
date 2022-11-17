package com.cibertec.springboot.web.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.springboot.web.app.models.entity.Libro;
import com.cibertec.springboot.web.app.models.service.ILibroService;
import com.cibertec.springboot.web.app.models.service.IUsuarioService;

@Controller
@SessionAttributes(names = {"prestamo","libro","usuario"})
@RequestMapping(value = "/prestamo")
public class PrestamoController {

	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private ILibroService libroService;	
	
	// Confirmación de Prestamos - Controllers
	
	@RequestMapping(value = "/realizar/{idlibro}")
	public String realizar(@PathVariable(value = "idlibro") Long idlibro, Model model) {
		Libro libro = libroService.findOne(idlibro);
		model.addAttribute("titulo", "Préstamo de Libro");
		model.addAttribute("libro", libro);
		return "prestamo/realizar";
	}
	
	@RequestMapping(value = "/confirmar", method = RequestMethod.POST)
	public String confirmar(@PathVariable(value = "idlibro") Long idlibro, Model model,
			RedirectAttributes flash, SessionStatus status, BindingResult result) {
		Libro libro = libroService.findOne(idlibro);
		model.addAttribute("titulo", "Préstamo de Libro");
		model.addAttribute("libro", libro);
		return "prestamo/realizar";
	}
	
	@RequestMapping(value="/confirma_prestamo/{id}")
	public String confirmaPrestamo(@PathVariable(value="id") Long id
			,Map<String, Object> modelo) {
		Libro libro = null;
		libro = libroService.findOne(id);
		modelo.put("libro", libro);
		modelo.put("titulo", "Confirmar Préstamo de Libro");
		return "confirma_prestamo";
	}
	
	@RequestMapping(value = "/confirma_prestamo", method = RequestMethod.POST)
	public String actualizarPrestamoUsuario(@Valid Libro libro, RedirectAttributes flash,
			SessionStatus status, BindingResult result, Model modelo) {
		if (result.hasErrors()) {
			modelo.addAttribute("titulo", "Confirmar Préstamo de Libro");
			modelo.addAttribute("libro", libro);
			return "confirma_prestamo";
		}
		String mensajeFlash = "Libro solicitado con éxito";
		libroService.prestarLibro(libro);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/libro/listado";
	}
}
