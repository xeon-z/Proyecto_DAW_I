package com.cibertec.springboot.web.app.models.DAO;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.cibertec.springboot.web.app.models.entity.Usuario;

public interface IUsuarioDAO extends PagingAndSortingRepository<Usuario, String> {

}
