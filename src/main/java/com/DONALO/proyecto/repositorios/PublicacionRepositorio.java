package com.DONALO.proyecto.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.DONALO.proyecto.entidades.Publicacion;


@Repository("publicacionRepositorio")
public interface PublicacionRepositorio extends JpaRepository<Publicacion, String> {
	
	@Query("SELECT c FROM Publicacion c WHERE c.titulo LIKE :q OR c.descripcion LIKE :q")
	public List<Publicacion> buscarPublicacion(@Param("q") String nombre);
  
	
//	@Query("SELECT c FROM Publicacion c WHERE c.usuario_id = :id")
//    public List<Publicacion> buscarPublicacionPorUsuarioId(@Param("id") String usuario_id);
public List <Publicacion> findByUsuario(String usuario_id);
}
