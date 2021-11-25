
package com.cibertec.cliente.controller;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.cliente.entity.TipoUsuario;
import com.cibertec.cliente.entity.Usuario;
import com.google.gson.Gson;

@Controller
@RequestMapping("/CRUDusuario")
public class CRUDUsuarioController {
	private String URL = "http://localhost:8091";

	@RequestMapping("/CRUDusuario")
	public String index(Model model) {
		
		try {
			RestTemplate rt=new RestTemplate();
			List<Usuario[]> listaC=rt.getForObject(URL+"/usuario/lista", List.class);
			List<TipoUsuario[]> listaDist=rt.getForObject(URL+"/tipousuario/lista", List.class);
			model.addAttribute("usuarios",listaC);
			model.addAttribute("tipos",listaDist);
			model.addAttribute("usuario", new Usuario());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "empleado/CRUDUsuario";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Usuario bean, RedirectAttributes redirect) {
		try {
			RestTemplate rt=new RestTemplate();
			Gson gson=new Gson();
			String json=gson.toJson(bean);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> response = new HttpEntity<String>(json, headers);
			
			rt.postForObject(URL+"/usuario/registrar", response, String.class);
			
			redirect.addFlashAttribute("MENSAJE","Usuario registrado");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/CRUDusuario/CRUDusuario";
	
		}
	@RequestMapping("/consulta")
	public String consulta(){

		return "consulta";
	}
	
}