package com.tismart.hospital.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="distrito")
public class Distrito {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descDistrito;
	@Temporal(TemporalType.DATE)
	private Date fechaRegistro;
	@ManyToOne
	@JoinColumn(name = "id_provincia")
	private Provincia provincia;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescDistrito() {
		return descDistrito;
	}
	public void setDescDistrito(String descDistrito) {
		this.descDistrito = descDistrito;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	
}
