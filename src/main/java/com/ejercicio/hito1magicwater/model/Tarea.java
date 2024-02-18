package com.ejercicio.hito1magicwater.model;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "tarea")
public class Tarea {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Ajuste para generación automática del ID
	@Column(name = "idtarea")
	private int idtarea;

	@Column(name = "titulo")
	private String titulo;

	@Column(name = "descripcion")
	private String descripcion;

	@Temporal(TemporalType.DATE)
	@Column(name = "inicioprevisto")
	private Date inicioprevisto;

	@Temporal(TemporalType.DATE)
	@Column(name = "finprevisto")
	private Date finprevisto;

	@Temporal(TemporalType.DATE)
	@Column(name = "inicioreal")
	private Date inicioreal;

	@Temporal(TemporalType.DATE)
	@Column(name = "finreal")
	private Date finreal;

	@Column(name = "estado")
	private String estado;

	@ManyToOne
	@JoinColumn(name = "nif")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "idproyecto")
	private Proyecto proyecto;

	// Getters y Setters
	public int getIdtarea() {
		return idtarea;
	}

	public void setIdtarea(int idtarea) {
		this.idtarea = idtarea;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getInicioprevisto() {
		return inicioprevisto;
	}

	public void setInicioprevisto(Date inicioprevisto) {
		this.inicioprevisto = inicioprevisto;
	}

	public Date getFinprevisto() {
		return finprevisto;
	}

	public void setFinprevisto(Date finprevisto) {
		this.finprevisto = finprevisto;
	}

	public Date getInicioreal() {
		return inicioreal;
	}

	public void setInicioreal(Date inicioreal) {
		this.inicioreal = inicioreal;
	}

	public Date getFinreal() {
		return finreal;
	}

	public void setFinreal(Date finreal) {
		this.finreal = finreal;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}
}
