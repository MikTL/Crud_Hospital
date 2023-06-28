package com.tismart.hospital.model.dto;

public class EditarHospitalDTO {
	private Long id;
	private String nombre;
	private int antiguedad;
	private String area;
	private String idDistrito;
	private String fechaRegistro;
	private String idSede;
	private String idGerente;
	private String idCondicion;
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
	public int getAntiguedad() {
		return antiguedad;
	}
	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getIdDistrito() {
		return idDistrito;
	}
	public void setIdDistrito(String idDistrito) {
		this.idDistrito = idDistrito;
	}
	public String getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	public String getIdSede() {
		return idSede;
	}
	public void setIdSede(String idSede) {
		this.idSede = idSede;
	}
	public String getIdGerente() {
		return idGerente;
	}
	public void setIdGerente(String idGerente) {
		this.idGerente = idGerente;
	}
	public String getIdCondicion() {
		return idCondicion;
	}
	public void setIdCondicion(String idCondicion) {
		this.idCondicion = idCondicion;
	}
	
	
}
