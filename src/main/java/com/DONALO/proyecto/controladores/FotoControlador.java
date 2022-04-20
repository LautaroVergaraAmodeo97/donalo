package com.DONALO.proyecto.controladores;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.DONALO.proyecto.repositorios.FotoRepositorio;
import com.DONALO.proyecto.servicios.FotoServicio;

@Controller
@RequestMapping("/")
public class FotoControlador {
 
	@Autowired
	FotoServicio fotoServicio;
	@Autowired
	FotoRepositorio fotoRepositorio;

	@GetMapping("/img/{id}")
	public ResponseEntity<byte[]> traerFoto (@PathVariable("id") String id) {
	       byte[] foto = fotoRepositorio.findById(id).get().getContenido();
	       HttpHeaders header = new HttpHeaders();
	       header.setContentType(MediaType.IMAGE_PNG);
	       return new ResponseEntity<>(foto, header, HttpStatus.OK);
	       
	       
	}


}


