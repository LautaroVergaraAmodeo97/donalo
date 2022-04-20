package com.DONALO.proyecto.entidades;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.DONALO.proyecto.enumeraciones.Seleccion;


@Entity 
public class Publicacion {

	@Id
	@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	private String titulo;
	
	private String descripcion;
	
	
	 @OneToOne
	 private Foto foto;
	 
	 @ManyToOne
	 private Usuario usuario;
	
	  @Enumerated(EnumType.STRING)
		private Seleccion seleccion;
	  
	 @Temporal(TemporalType.TIMESTAMP)
	    private Date alta;

	    @Temporal(TemporalType.TIMESTAMP)
	    private Date baja;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
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

		public Foto getFoto() {
			return foto;
		}

		public void setFoto(Foto foto) {
			this.foto = foto;
		}

		

		public Usuario getUsuario() {
			return usuario;
		}

		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}

		public Seleccion getSeleccion() {
			return seleccion;
		}

		public void setSeleccion(Seleccion seleccion) {
			this.seleccion = seleccion;
		}

		public Date getAlta() {
			return alta;
		}

		public void setAlta(Date alta) {
			this.alta = alta;
		}

		public Date getBaja() {
			return baja;
		}

		public void setBaja(Date baja) {
			this.baja = baja;
		} 
	
  

   
    
    
    
    
    
    
	 
}
