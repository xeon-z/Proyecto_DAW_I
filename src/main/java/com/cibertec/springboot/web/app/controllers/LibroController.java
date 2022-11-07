package com.cibertec.springboot.web.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cibertec.springboot.web.app.models.entity.Libro;
import com.cibertec.springboot.web.app.models.service.ICategoriaService;
import com.cibertec.springboot.web.app.models.service.ILibroService;
import com.cibertec.springboot.web.app.util.paginator.PageRender;

@Controller
@SessionAttributes("libro")
@RequestMapping(value="/libro")
public class LibroController {

	@Autowired
	private ILibroService libroService;	
	
	@Autowired
	private ICategoriaService categoriaService;
	
	@RequestMapping(value="/listado", method = RequestMethod.GET)
	public String listadoLibros(@RequestParam(name="page", defaultValue = "0") 
	int page, Model modeloListado) {
		Pageable pageRequest = PageRequest.of(page, 5);
		Page<Libro> libros = libroService.findAll(pageRequest);
		PageRender<Libro> pageRender = new PageRender<>("/libro/listado", libros);
				
		modeloListado.addAttribute("titulo", "Listado de Libros");
		modeloListado.addAttribute("libros", libros);
		modeloListado.addAttribute("page", pageRender);
		return "libro/listado";
	}
	
	
}