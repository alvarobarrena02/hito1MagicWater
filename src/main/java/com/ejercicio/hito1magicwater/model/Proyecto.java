package com.ejercicio.hito1magicwater.model;

import jakarta.persistence.*;

import java.sql.Date;


@Entity
@Table(name = "proyecto")
public class Proyecto {

    @Id
    @Column(name = "idproyecto")
    private int idproyecto;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "zona")
    private String zona;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha")
    private Date fecha;

	public int getIdproyecto() {
		return idproyecto;
	}

	public void setIdproyecto(int idproyecto) {
		this.idproyecto = idproyecto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}