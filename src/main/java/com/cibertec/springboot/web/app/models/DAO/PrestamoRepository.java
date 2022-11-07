package com.cibertec.springboot.web.app.models.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.springboot.web.app.models.entity.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {

}
