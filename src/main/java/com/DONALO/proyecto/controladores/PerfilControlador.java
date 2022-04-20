package com.DONALO.proyecto.controladores;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.DONALO.proyecto.entidades.Mensaje;
import com.DONALO.proyecto.entidades.Usuario;
import com.DONALO.proyecto.repositorios.MensajeRepositorio;
import com.DONALO.proyecto.repositorios.UsuarioRepositorio;


@Controller
public class PerfilControlador {

@Autowired
private UsuarioRepositorio repo;


@Autowired MensajeRepositorio repo1;

	
//@PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
@GetMapping("/perfil")
public String perfil(ModelMap model) {	 
	

	 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      Usuario usuario =  repo.buscarPorMail(auth.getName());
	
      model.put("usuario", usuario);
//		model.put("nombre", usuario.getNombre());
//		model.put("apellido", usuario.getApellido());
//		model.put("mail", usuario.getMail());
//		model.put("foto", usuario.getFoto());
		
	return "perfil.html";
	
}

@GetMapping("mismensajes")
public String mismensajes(ModelMap modelo) {
	
	
	List <Mensaje> mensajes;
	List <Mensaje> mensajes1;
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      Usuario usuario =  repo.buscarPorMail(auth.getName());
    modelo.put("usuario", usuario);
	
    
    mensajes = repo1.buscarMensajesRecibidos(usuario.getId());
	
	mensajes1 = repo1.buscarMensajesPropios(usuario.getId());
	
	modelo.put("mensajes", mensajes);
	modelo.put("mensajes1", mensajes1);
	
	return "mismensajes.html";
	
		
	
	
	
	
	
	
	
}






}

