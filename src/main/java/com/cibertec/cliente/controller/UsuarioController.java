package com.cibertec.cliente.controller;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.cliente.entity.TipoUsuario;
import com.cibertec.cliente.entity.Usuario;
import com.google.gson.Gson;


@SessionAttributes("usuario")

@Controller
public class UsuarioController {

	private String URL = "http://localhost:8091";
	
	@RequestMapping("/login")
	public String login() {
		
		return "login";
	}
	
	@RequestMapping("/menu")
	public String menu(Authentication auth, Model model) {
		RestTemplate rt=new RestTemplate();
		Usuario u = rt.getForObject(URL+"/usuario/lista/"+auth.getName(), Usuario.class);
		
		//atributo
		model.addAttribute("usuario",u);
		
		return "empleado/index";
	}
	
	/*@RequestMapping("/crear-cuenta")
	public String registrarUsuario(Model model) {
		
		RestTemplate rt=new RestTemplate();
		List<TipoUsuario[]> listatipoUsuario=rt.getForObject(URL+"/tipousuario/lista", List.class);
		
		model.addAttribute("tipousuarios",listatipoUsuario);
		model.addAttribute("cliente", new Usuario());
		
		
		return "crearCuenta";
	}
	
	@RequestMapping("/registrarUsuario")
	public String registrar(@ModelAttribute Usuario bean, RedirectAttributes redirec) {
		try {
			RestTemplate rt=new RestTemplate();
			Gson gson=new Gson();
			String json=gson.toJson(bean);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> response = new HttpEntity<String>(json, headers);
			rt.postForObject(URL+"/usuario/registrar", response, String.class);
			redirec.addFlashAttribute("MENSAJE","Usuario registrado");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/login";
	}*/
}
