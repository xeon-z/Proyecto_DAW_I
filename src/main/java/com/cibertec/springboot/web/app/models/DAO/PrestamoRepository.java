package com.cibertec.springboot.web.app.models.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cibertec.springboot.web.app.models.entity.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {

	@Query("select count(1) from Prestamo p where p.socio.id=:idSocio and p.estado='Pendiente'")
	public int devolucionesPendientes(int idSocio);
	
	@Query("select count(1) from Prestamo p where p.socio.id=:idSocio and p.mora='Si'")
	public int morasPendientes(int idSocio);
	
}
