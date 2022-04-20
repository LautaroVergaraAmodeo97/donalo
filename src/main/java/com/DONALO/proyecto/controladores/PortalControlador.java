package com.DONALO.proyecto.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.DONALO.proyecto.entidades.Usuario;
import com.DONALO.proyecto.errores.ErrorServicio;
import com.DONALO.proyecto.repositorios.UsuarioRepositorio;
import com.DONALO.proyecto.servicios.UsuarioServicio;

@Controller
public class PortalControlador {

	@Autowired
	private UsuarioServicio usuarioServicio;
	@Autowired
	private UsuarioRepositorio repo;

	@GetMapping("/")

	public String home() {

		return "home.html";
	}

	
	@GetMapping("/inicio")
	public String inicio() {
		return "inicio.html";
	}

	@GetMapping("/login")
	public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout,
			ModelMap model) {
		if (error != null) {
			model.put("error", "Nombre de usuario o clave incorrectos.");
		}

		if (logout != null) {
			model.put("logout", "Ha salido correctamente de la plataforma.");
		}

		return "login.html";
	}

	@PostMapping("/editaperfil/editar")
	public String editar(ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido,
			@RequestParam String mail, @RequestParam String clave1, @RequestParam String clave2,
			MultipartFile archivo) {

		System.out.println("Entre al metodo bajo mapping editar");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = repo.buscarPorMail(auth.getName());

		try {
			usuarioServicio.modificacionUsuario(archivo, usuario.getId(), nombre, apellido, mail, clave1, clave2);
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

			return "editar_perfil.html";
		}

		return "perfil.html";
	}

	@GetMapping("/editar_perfil/perfil")
	public String perfil(ModelMap modelo) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Usuario usuario = repo.buscarPorMail(auth.getName());

		modelo.put("nombre", usuario.getNombre());
		modelo.put("apellido", usuario.getApellido());
		modelo.put("mail", usuario.getMail());

		return "perfil.html";
	}

}
