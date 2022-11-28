package com.cibertec.springboot.web.app.controllers;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.springboot.web.app.models.entity.Libro;
import com.cibertec.springboot.web.app.models.entity.Prestamo;
import com.cibertec.springboot.web.app.models.entity.Socio;
import com.cibertec.springboot.web.app.models.service.ILibroService;
import com.cibertec.springboot.web.app.models.service.IPrestamoService;

@Controller
@SessionAttributes(names = {"prestamo","libro","usuario"})
@RequestMapping(value = "/prestamo")
public class PrestamoController {

	@Autowired
	private HttpSession session;
	@Autowired
	private ILibroService libroService;
	@Autowired
	private IPrestamoService prestamoService;
	
	// Confirmación de Prestamos - Controllers
	
	@RequestMapping(value = "/solicitar/{idlibro}")
	public String solicitarPrestamo(@PathVariable(value = "idlibro") Long idlibro, Model model) {
		Libro libro = libroService.findOne(idlibro);
		model.addAttribute("titulo", "Préstamo de Libro");
		model.addAttribute("libro", libro);
		
		return "prestamo/solicitar";
	}
	
	@RequestMapping(value = "/confirmar", method = RequestMethod.POST)
	public String confirmarPrestamo(Model model, RedirectAttributes attributes, SessionStatus status) {
		Libro libro = (Libro) model.getAttribute("libro");
		Socio socio = (Socio) session.getAttribute("login");
		
		if (prestamoService.devolucionesPendientes(socio.getId()) >= 5) {
			attributes.addFlashAttribute("error", "No se pudo realizar el préstamo, tiene demasiadas devoluciones pendientes.");
			return "redirect:/libro/listado";
		}
		if (prestamoService.morasPendientes(socio.getId()) > 0) {
			attributes.addFlashAttribute("error", "No se pudo realizar el préstamo, primero debe cancelar todas sus moras.");
			return "redirect:/libro/listado";
		}
		
		Prestamo prestamo = new Prestamo();
		prestamo.setSocio(socio);
		prestamo.setLibro(libro);
		prestamo.setFecPrestamo(new Date());
		prestamo.setFecLimite(new Date(new Date().getTime() + 1728000000L));
		prestamo.setEstado("Pendiente");
		prestamo.setMora("No");
		try {
			prestamoService.save(prestamo);
			status.setComplete();
			attributes.addFlashAttribute("success", "Préstamo realizado correctamente.");
		} catch (Exception e) {
			attributes.addFlashAttribute("error", "No se pudo realizar el préstamo, asegúrese que haya stock suficiente.");
		}
		
		return "redirect:/libro/listado";
	}
	
	@RequestMapping(value = "/devolver/{idPrestamo}")
	public String solicitarDevolucion(@PathVariable(value = "idPrestamo") int idPrestamo, Model model) {
		Prestamo prestamo = prestamoService.findOne(idPrestamo);
		model.addAttribute("titulo", "Devolución de libro");
		model.addAttribute("prestamo", prestamo);
		
		return "prestamo/devolver";
	}
	
	@RequestMapping(value = "/devolver", method = RequestMethod.POST)
	public String confirmarDevolucion(Model model, RedirectAttributes attributes, SessionStatus status) {
		Prestamo prestamo = (Prestamo) model.getAttribute("prestamo");
		
		prestamo.setFecDevolucion(new Date());
		prestamo.setEstado("Devuelto");
		
		if (prestamo.getFecDevolucion().getTime() >= prestamo.getFecLimite().getTime())
			prestamo.setMora("Si");
		
		prestamoService.save(prestamo);
		status.setComplete();
		attributes.addFlashAttribute("success", "Devolución realizada correctamente.");

		return "redirect:/libro/listado";
	}
	
}
