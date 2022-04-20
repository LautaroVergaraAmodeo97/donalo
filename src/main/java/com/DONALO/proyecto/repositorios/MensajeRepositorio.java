package com.DONALO.proyecto.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.DONALO.proyecto.entidades.Mensaje;
import com.DONALO.proyecto.entidades.Publicacion;
//
@Repository("mensajeRepositorio")
public interface MensajeRepositorio extends JpaRepository<Mensaje, String> {


//public static final Mensaje mensaje = new Mensaje();
	
@Query("SELECT c FROM Mensaje c Where c.id_Usuario1.id = :id ORDER BY c.fecha DESC")
public List<Mensaje> buscarMensajesPropios (@Param("id")String id);
//
@Query("SELECT c FROM Mensaje c Where c.id_Usuario2.id= :id ORDER BY c.fecha DESC")
public List<Mensaje> buscarMensajesRecibidos (@Param ("id")String id);
	
//	
//	public void send1(String id_Usuario1, String id_Usuario2, String contenido);
//	
//	//File... attachments
//	public void send2(String id_Usuario1, String id_Usuario2, String contenido);
	
	
}
