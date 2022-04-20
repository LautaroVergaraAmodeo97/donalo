package com.DONALO.proyecto.entidades;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Mensaje {
	
	@Id
	@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	@ManyToOne
	private Usuario id_Usuario1;
	
	@ManyToOne
	private Usuario id_Usuario2;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	
	private String contenido;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}



	public Usuario getId_Usuario1() {
		return id_Usuario1;
	}

	public void setId_Usuario1(Usuario id_Usuario1) {
		this.id_Usuario1 = id_Usuario1;
	}

	public Usuario getId_Usuario2() {
		return id_Usuario2;
	}

	public void setId_Usuario2(Usuario id_Usuario2) {
		this.id_Usuario2 = id_Usuario2;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	
	
	
}
