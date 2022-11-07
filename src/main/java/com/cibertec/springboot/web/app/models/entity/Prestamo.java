package com.cibertec.springboot.web.app.models.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "prestamo")
public class Prestamo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pres")
	private int id;
	@ManyToOne
	@JoinColumn(name = "id_soc")
	@NotNull(message = "Campo obligatorio")
	private Socio socio;
	@ManyToOne
	@JoinColumn(name = "id_lib")
	@NotNull(message = "Campo obligatorio")
	private Libro libro;
	@Column(name = "fec_pres")
	@NotNull(message = "Campo obligatorio")
	private Date fecPrestamo;
	@Column(name = "fec_limit")
	private Date fecLimite;
	@Column(name = "fec_devol")
	private Date fecDevolucion;
	@Column(name = "cantidad")
	@NotNull(message = "Campo obligatorio")
	private int cantidad;
	@Column(name = "estado")
	private String estado;
	@Column(name = "mora")
	private String mora;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Socio getSocio() {
		return socio;
	}

	public void setSocio(Socio socio) {
		this.socio = socio;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public Date getFecPrestamo() {
		return fecPrestamo;
	}

	public void setFecPrestamo(Date fecPrestamo) {
		this.fecPrestamo = fecPrestamo;
	}

	public Date getFecLimite() {
		return fecLimite;
	}

	public void setFecLimite(Date fecLimite) {
		this.fecLimite = fecLimite;
	}

	public Date getFecDevolucion() {
		return fecDevolucion;
	}

	public void setFecDevolucion(Date fecDevolucion) {
		this.fecDevolucion = fecDevolucion;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getMora() {
		return mora;
	}

	public void setMora(String mora) {
		this.mora = mora;
	}

	private static final long serialVersionUID = 1L;

}
