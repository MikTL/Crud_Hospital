package com.tismart.hospital.model.dto;

public class HospitalDTO {
	private Long id;
	private String nombre;
	private String distrito;
	private String gerente;
	
	public HospitalDTO(Long id, String nombre, String distrito, String gerente) {
		this.id=id;
		this.nombre=nombre;
		this.distrito=distrito;
		this.gerente=gerente;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public String getGerente() {
		return gerente;
	}

	public void setGerente(String gerente) {
		this.gerente = gerente;
	}
	
}
