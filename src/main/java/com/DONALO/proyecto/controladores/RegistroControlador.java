package com.DONALO.proyecto.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.DONALO.proyecto.entidades.Usuario;
import com.DONALO.proyecto.errores.ErrorServicio;
import com.DONALO.proyecto.servicios.UsuarioServicio;

@Controller

public class RegistroControlador {

	@Autowired
	private UsuarioServicio usuarioServicio;

	@GetMapping("registro/formulario")
	public String formulario() {

		return "registro.html";
	}

	@PostMapping("registro/registrar")
	public String registrar(ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido,
			@RequestParam String mail, @RequestParam String clave1, @RequestParam String clave2,
			MultipartFile archivo) {
		Usuario nuevoUsuario = new Usuario();
		try {
			nuevoUsuario = usuarioServicio.altaUsuario(nombre, apellido, mail, clave1, clave2, archivo);
		} catch (ErrorServicio ex) {

			modelo.put("error", ex.getMessage());
			modelo.put("nombre", nombre);
			modelo.put("apellido", apellido);
			modelo.put("mail", mail);
			modelo.put("clave1", clave1);
			modelo.put("clave2", clave2);

			return "registro.html";
		}

		modelo.put("nombre", nombre);
		modelo.put("apellido", apellido);
		modelo.put("mail", mail);
		modelo.put("clave1", clave1);
		modelo.put("clave2", clave2);

		modelo.put("titulo", "Bienvenido a Donalo!!.");
		modelo.put("descripcion", "Tu usuario fue registrado de manera satisfactoria");
//
//		return "redirect:/perfil?id=" + nuevoUsuario.getId();
		return "redirect:/inicio";
	}

}
