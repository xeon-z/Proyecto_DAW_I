package com.cibertec.springboot.web.app.models.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.springboot.web.app.models.entity.Socio;

public interface SocioRepository extends JpaRepository<Socio, Integer> {

}
