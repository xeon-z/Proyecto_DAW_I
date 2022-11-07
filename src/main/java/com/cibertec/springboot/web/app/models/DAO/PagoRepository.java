package com.cibertec.springboot.web.app.models.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.springboot.web.app.models.entity.Pago;

public interface PagoRepository extends JpaRepository<Pago, Integer> {

}
