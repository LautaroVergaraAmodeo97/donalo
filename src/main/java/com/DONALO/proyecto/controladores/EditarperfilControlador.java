package com.DONALO.proyecto.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import com.DONALO.proyecto.entidades.Usuario;
import com.DONALO.proyecto.errores.ErrorServicio;
import com.DONALO.proyecto.repositorios.UsuarioRepositorio;
import com.DONALO.proyecto.servicios.UsuarioServicio;

@Controller
public class EditarperfilControlador {

	@Autowired
     public UsuarioRepositorio repo;


	@Autowired
	private UsuarioServicio servicio;



@GetMapping("/editar_perfil")
public String cargar(ModelMap modelo) {

	 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    Usuario usuario = repo.buscarPorMail(auth.getName());
	
	modelo.put("nombre", usuario.getNombre());
	modelo.put("apellido", usuario.getApellido());
	modelo.put("mail", usuario.getMail());
	modelo.put("clave1", usuario.getClave());
	modelo.put("clave2", usuario.getClave());

	return "editar_perfil.html";

}


@PostMapping("editar_perfil/editar")
public RedirectView editar( ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String mail, @RequestParam String clave1, @RequestParam String clave2, MultipartFile archivo){
	
	 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
   Usuario usuario = repo.buscarPorMail(auth.getName());
	
	
	   try {
	        servicio.modificacionUsuario(archivo, usuario.getId() , nombre, apellido, mail, clave1, clave2);
	    } catch (ErrorServicio ex) {
	    	System.out.println("________________________________");
	    	System.out.println("ERROR ACA");
	    	ex.printStackTrace();
	        modelo.put("error", ex.getMessage());
	        modelo.put("nombre", nombre);
	        modelo.put("apellido", apellido);
	        modelo.put("mail", mail);
	        modelo.put("clave1", clave1);
	        modelo.put("clave2", clave2);
	      
	        return new RedirectView("/editar_perfil");
	    }
	    
	   
	   usuario = repo.buscarPorMail(auth.getName());
	   
	   
	   modelo.put("nombre", usuario.getNombre());
	   modelo.put("apellido", usuario.getApellido());
	   modelo.put("mail", usuario.getMail());
	   
	   return new RedirectView("/perfil");
	   
	   
	   
	
	   
	   
	}




}
	
	