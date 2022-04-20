package com.DONALO.proyecto.servicios;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.DONALO.proyecto.entidades.Foto;
import com.DONALO.proyecto.entidades.Usuario;
import com.DONALO.proyecto.errores.ErrorServicio;
import com.DONALO.proyecto.repositorios.UsuarioRepositorio;



//Estos imports es para el envio de mail

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
@Service
public class UsuarioServicio implements UserDetailsService {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Autowired
	private FotoServicio fotoServicio;
	@Autowired
	private JavaMailSender mailsender;
	
	


	@Transactional
	public Usuario altaUsuario(String nombre, String apellido, String mail, String clave, String clave2,
			MultipartFile archivo) throws ErrorServicio {

		validar(nombre, apellido, mail, clave, clave2);

		Usuario usuario = new Usuario();

		usuario.setNombre(nombre);
		usuario.setApellido(apellido);
		usuario.setMail(mail);

		// Seguridad de la clave
		String encriptada = new BCryptPasswordEncoder().encode(clave);
		usuario.setClave(encriptada);
		usuario.setAlta(new Date());

		Foto foto = fotoServicio.guardar(archivo);
		usuario.setFoto(foto);
		
		
		//Envio de mail de confirmacion
		
//		mail(mensaje);
		
		
		usuarioRepositorio.save(usuario);
		
		
		 
		return usuario;

	}

	@Transactional
	public void validar(String nombre, String apellido, String mail, String clave, String clave2) throws ErrorServicio {

		if (nombre.isEmpty() || nombre == null) {
			throw new ErrorServicio("El nombre del usuario no puede estar vacío o ser nulo");
		}

		if (apellido.isEmpty() || apellido == null) {
			throw new ErrorServicio("El apellido del usuario no puede estar vacío o ser nulo");
		}

		if (mail.isEmpty() || mail == null) {
			throw new ErrorServicio("El mail del usuario no puede estar vacío o ser nulo");
		}

		if (clave.isEmpty() || clave == null || clave.length() <= 6) {
			throw new ErrorServicio(
					"La clave del usuario no puede estar vacío o ser nulo; y tiene que tener más de 6 dígitos");
		}

		if (!clave.equals(clave2)) {
			throw new ErrorServicio("Las claves deben ser iguales");
		}

	}

	@Transactional
	public void modificacionUsuario(MultipartFile archivo, String id, String nombre, String apellido, String mail,
			String clave, String clave2) throws ErrorServicio {
		validar(nombre, apellido, mail, clave, clave2);

		Optional<Usuario> respuesta = usuarioRepositorio.findById(id);

		if (respuesta.isPresent()) {
			Usuario usuario = respuesta.get();
			usuario.setNombre(nombre);
			usuario.setApellido(apellido);
			usuario.setMail(mail);

			String encriptada = new BCryptPasswordEncoder().encode(clave);
			usuario.setClave(encriptada);

			usuarioRepositorio.save(usuario);
			String idFoto = null;
			if (usuario.getFoto() != null) {
				idFoto = usuario.getFoto().getId();
			}

			Foto foto = fotoServicio.actualizar(idFoto, archivo);
			usuario.setFoto(foto);
			usuarioRepositorio.save(usuario);

		} else {
			throw new ErrorServicio("No se encontro el usuario solicitado");
		}

	}

	@Transactional
	public void bajaUsuario(String id) throws ErrorServicio {

		Optional<Usuario> respuesta = usuarioRepositorio.findById(id);

		if (respuesta.isPresent()) {

			Usuario usuario = respuesta.get();
			usuarioRepositorio.deleteById(id);
		} else {
			throw new ErrorServicio("El autor no pudo ser eliminado exitosamente");
		}

	}
//	@Async
//	@Transactional
//	public  SimpleMailMessage mail (String mail,String titulo,String cuerpo) {
//		   SimpleMailMessage mensaje = new SimpleMailMessage();
//	       mensaje.setTo(mail); 
//	       mensaje.setFrom("noreply@eggeducacion");
//	       mensaje.setSubject(titulo);
//	       mensaje.setText(cuerpo);
//	              
//	       mailsender.send(mensaje);
//	    
//	       
//	       return mensaje;
//	   }
	
	
	
	
	

	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepositorio.buscarPorMail(mail);
		if (usuario != null) {

			List<GrantedAuthority> permisos = new ArrayList<>();

			GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_USUARIO_REGISTRADO");
			permisos.add(p1);

			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession(true);
			session.setAttribute("usuariosession", usuario);

			User user = new User(usuario.getMail(), usuario.getClave(), permisos);
			return user;
		} else {
			return null;
		}
	}
	
	
	
	
	
	
	
	

	
	
	
	
}


//	@override
//	public userdetails loaduserbyusername(string id) throws usernamenotfoundexception{
//		
//		usuario usuario = usuariorepositorio.getone(id);
//		
//		if(usuario!=null) {
//		list <grantedauthority> permisos = new arraylist<>();
//		
//		grantedauthority p1= new simplegrantedauthority("role_usuario_registrado");
//		permisos.add(p1);
//		
//		servletrequestattributes attr = (servletrequestattributes) requestcontextholder.currentrequestattributes();
//		httpsession session=attr.getrequest().getsession(true);
//		session.setattribute("usuariosession", usuario);
//		
//		
//		user user = new user (usuario.getmail(),usuario.getclave(),permisos);
//		
//		return user;
//	}else {
//		return null;
//	}
//	
//}
