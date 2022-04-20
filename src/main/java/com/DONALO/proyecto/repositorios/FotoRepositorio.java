package com.DONALO.proyecto.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DONALO.proyecto.entidades.Foto;



@Repository("fotoRepositorio")
public interface FotoRepositorio extends JpaRepository<Foto, String>{

	

}
