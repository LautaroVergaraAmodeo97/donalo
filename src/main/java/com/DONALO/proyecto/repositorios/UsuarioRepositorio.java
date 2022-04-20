package com.DONALO.proyecto.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.DONALO.proyecto.entidades.Usuario;

@Repository("usuarioRepositorio")
public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {


    @Query("SELECT c FROM Usuario c WHERE c.mail = :mail")
    public Usuario buscarPorMail(@Param("mail") String mail);
    
    
}
