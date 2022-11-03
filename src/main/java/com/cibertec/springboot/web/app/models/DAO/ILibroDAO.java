package com.cibertec.springboot.web.app.models.DAO;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.cibertec.springboot.web.app.models.entity.Libro;

public interface ILibroDAO extends PagingAndSortingRepository<Libro, Long> {

}
