package com.cibertec.springboot.web.app.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_rol")
public class Rol implements Serializable {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_rol;
	private String des_rol;
	
	public int getId_rol() {
		return id_rol;
	}
	public void setId_rol(int id_rol) {
		this.id_rol = id_rol;
	}
	public String getDes_rol() {
		return des_rol;
	}
	public void setDes_rol(String des_rol) {
		this.des_rol = des_rol;
	}
	
	private static final long serialVersionUID = 1L;
}
