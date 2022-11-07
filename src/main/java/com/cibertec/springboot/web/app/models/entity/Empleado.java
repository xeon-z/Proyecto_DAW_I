package com.cibertec.springboot.web.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="empleado")
public class Empleado implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_emp;
	private String nom_emp;
	private String cel_emp;
	private Date fec_contrato;
	private String cargo_emp;
	private double suel_emp;
	private String cor_emp;
	private int id_usu;
		
	
	public int getId_emp() {
		return id_emp;
	}



	public void setId_emp(int id_emp) {
		this.id_emp = id_emp;
	}



	public String getNom_emp() {
		return nom_emp;
	}



	public void setNom_emp(String nom_emp) {
		this.nom_emp = nom_emp;
	}



	public String getCel_emp() {
		return cel_emp;
	}



	public void setCel_emp(String cel_emp) {
		this.cel_emp = cel_emp;
	}



	public Date getFec_contrato() {
		return fec_contrato;
	}



	public void setFec_contrato(Date fec_contrato) {
		this.fec_contrato = fec_contrato;
	}



	public String getCargo_emp() {
		return cargo_emp;
	}



	public void setCargo_emp(String cargo_emp) {
		this.cargo_emp = cargo_emp;
	}



	public double getSuel_emp() {
		return suel_emp;
	}



	public void setSuel_emp(double suel_emp) {
		this.suel_emp = suel_emp;
	}



	public String getCor_emp() {
		return cor_emp;
	}



	public void setCor_emp(String cor_emp) {
		this.cor_emp = cor_emp;
	}



	public int getId_usu() {
		return id_usu;
	}



	public void setId_usu(int id_usu) {
		this.id_usu = id_usu;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
}
