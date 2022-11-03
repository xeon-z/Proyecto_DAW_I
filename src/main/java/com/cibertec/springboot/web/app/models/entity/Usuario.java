package com.cibertec.springboot.web.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name="tb_usuario")
public class Usuario implements Serializable{
	
	@Id
	private String Id;
	@Column(name="nom_usuario")
	private String nombres;
	@Column(name="ape_usuario")
	private String apellidos;
	@Column(name="email_usuario")
	private String email;
	@Column(name="password")
	private String password;
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	private static final long serialVersionUID = 1L;
}
