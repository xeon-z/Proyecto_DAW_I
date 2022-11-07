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

import com.cibertec.springboot.web.app.models.entity.Libro;
import com.cibertec.springboot.web.app.models.service.ICategoriaService;
import com.cibertec.springboot.web.app.models.service.ILibroService;
import com.cibertec.springboot.web.app.util.paginator.PageRender;

@Controller
@SessionAttributes(names = {"libro", "empleado"})
@RequestMapping(value="/libro")
public class LibroController {

	@Autowired
	private ILibroService libroService;	
	
	@Autowired
	private ICategoriaService categoriaService;
	
	@RequestMapping(value="/listado", method = RequestMethod.GET)
	public String listado(@RequestParam(name="page", defaultValue = "0") 
	int page, Model modeloListado) {
		Pageable pageRequest = PageRequest.of(page, 5);
		Page<Libro> libros = libroService.findAll(pageRequest);
		PageRender<Libro> pageRender = new PageRender<>("/libro/listado", libros);
				
		modeloListado.addAttribute("titulo", "Listado de Libros");
		modeloListado.addAttribute("libros", libros);
		modeloListado.addAttribute("page", pageRender);
		
		System.out.println(modeloListado.getAttribute("empleado"));
		return "libro/listado";
	}
	
	@RequestMapping(value = "/registro")
	public String registrar(Model model) {
		model.addAttribute("titulo","Formulario de Libro");
		model.addAttribute("libro", new Libro());
		model.addAttribute("categorias",categoriaService.findAll());
		return "libro/registro";
	}
	
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar(Model model, @Valid @ModelAttribute Libro libro, BindingResult result,
			SessionStatus status, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Libro");
			model.addAttribute("categorias", categoriaService.findAll());
			return "libro/registro";
		}
		
		libroService.save(libro);
		status.setComplete();
		attributes.addFlashAttribute("success", "Libro guardado correctamente.");
		return "redirect:/libro/listado";
	}
	
	@RequestMapping(value = "/editar/{id}")
	public String editar(Model model, @PathVariable Long id) {
		model.addAttribute("titulo","Formulario de Libro");
		model.addAttribute("libro", libroService.findOne(id));
		model.addAttribute("categorias",categoriaService.findAll());
		return "libro/registro";
	}
	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable Long id, RedirectAttributes attributes) {
		libroService.delete(id);
		attributes.addFlashAttribute("success", "Libro eliminado correctamente.");
		return "redirect:/libro/listado";
	}
	
}