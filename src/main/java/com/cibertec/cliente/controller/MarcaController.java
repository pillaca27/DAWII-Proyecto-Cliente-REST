package com.cibertec.cliente.controller;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.cliente.entity.Marca;
import com.google.gson.Gson;

@Controller
@RequestMapping("/marca")

public class MarcaController {
	private String URL = "http://localhost:8091";

	@RequestMapping("/listadoMarca")
	public String listado(Model model) {
		try {
			RestTemplate rt=new RestTemplate();
			List<Marca[]> listaMarca=rt.getForObject(URL+"/marca/listar", List.class);

			
			model.addAttribute("marcas",listaMarca);
			model.addAttribute("marca", new Marca());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "empleado/listadoMarca";
	}
	
	@RequestMapping("/registroMarca")
	public String index(Model model) {
		
		try {
			RestTemplate rt=new RestTemplate();
			List<Marca[]> listaMarca=rt.getForObject(URL+"/marca/listar", List.class);

			
			model.addAttribute("marcas",listaMarca);
			model.addAttribute("marca", new Marca());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "empleado/registroMarca";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Marca bean, RedirectAttributes redirec) {
		try {
			RestTemplate rt=new RestTemplate();
			Gson gson=new Gson();
			String json=gson.toJson(bean);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> response = new HttpEntity<String>(json, headers);
			
			rt.postForObject(URL+"/marca/registrar", response, String.class);
			
			redirec.addFlashAttribute("MENSAJE","Marca registrada");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/marca/registroMarca";
	}

}
