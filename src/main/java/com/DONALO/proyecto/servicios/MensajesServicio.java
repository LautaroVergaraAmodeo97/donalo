package com.DONALO.proyecto.servicios;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DONALO.proyecto.entidades.Mensaje;
import com.DONALO.proyecto.entidades.Publicacion;
import com.DONALO.proyecto.entidades.Usuario;
import com.DONALO.proyecto.errores.ErrorServicio;
import com.DONALO.proyecto.repositorios.MensajeRepositorio;
import com.DONALO.proyecto.repositorios.PublicacionRepositorio;
import com.DONALO.proyecto.repositorios.UsuarioRepositorio;

@Service
public class MensajesServicio {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	
	//@Autowired 
//private MailNotificacionServicio notificacionServicio;
	@Autowired
	private PublicacionRepositorio publicacionRepositorio;
	@Autowired
	private MensajeRepositorio mensajeRepositorio;
	
	@Transactional
	public void mensaje ( String idp,String idUsuario, String id_Usuario2,String contenido,Date fecha ) throws ErrorServicio {
		
		
		System.out.println("holis");
		
		Mensaje mensaje = new Mensaje();	
		mensaje.setFecha(new Date ());
		
		
		
		Optional<Publicacion> respuesta= publicacionRepositorio.findById(idp);
	//Es el que recibe
		 if (respuesta.isPresent()){
			    
			    Publicacion publicacion1 = respuesta.get();
			    if(publicacion1.getUsuario().getId().equals(id_Usuario2)){
			        mensaje.setContenido(contenido);
			        
			        mensaje.setId_Usuario2(publicacion1.getUsuario());
			        
			    }else{
			        throw new ErrorServicio("No tiene permisos para realizar la operacion solicitada");
			    } 
			    }else{
			            throw new ErrorServicio("No existe una publicacion identificada con ese indentificador");
			            } 
		
		 Optional<Usuario>respuesta2= usuarioRepositorio.findById(idUsuario);
         //Es el que envía
		    if (respuesta2.isPresent()){
		    
		    Usuario usuario2 = respuesta2.get();
		    
		    if(usuario2.getId().equals(idUsuario)){
		    	
		    	mensaje.setId_Usuario1(usuario2);
		      
		        //notificacionServicio.enviar("Tu mensaje se ha enviado ", "Donalo.com", usuario2.getMail());
		    }else{
		        throw new ErrorServicio("No tiene permisos para realizar la operacion solicitada");
		    } 
		    }else{
		            throw new ErrorServicio("No existe una mascota identificada con ese indentificador");
		            }
		    
		  
		    
		    if (idUsuario.equals(id_Usuario2)){
		        
		        throw new ErrorServicio("No puede mensajearse a si mismo"); 
		            }
		                  
		            mensajeRepositorio.save(mensaje);  
		    
		                              
	}

//	      
//		  @Transactional
//		  public void responder (String idUsuario,String mensaje) throws ErrorServicio{
//			     Optional<Mensaje> respuesta = mensajeRepositorio.findById(idUsuario);
//			       
//			     if(respuesta.isPresent()){
//			       Mensaje mensaje1 = respuesta.get();
//			       mensaje1.setFecha(new Date());
//			       
//			      
//			      if (mensaje1.getId_Usuario2().getNombre().equals(idUsuario)){
//			               notificacionServicio.enviar("Tu mensaje fue correspondido ", "Donalo.com", mensaje1.getId_Usuario1().getNombre());
//			           mensajeRepositorio.save(mensaje1); 
//			       }else{
//			      throw new ErrorServicio("no tiene Permiso para realizar la operacion");} 
//			    
//			     }else{
//			         throw new ErrorServicio("No existe el mensaje solicitado");
//			     }
//	
//		    
//	}
		  
		  }
//	
//	
//	
//	
//
