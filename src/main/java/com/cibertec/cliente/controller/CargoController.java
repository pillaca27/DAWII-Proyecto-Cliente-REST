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

import com.cibertec.cliente.entity.Cargo;
import com.cibertec.cliente.entity.Usuario;
import com.google.gson.Gson;


@Controller
@RequestMapping("/cargo")
public class CargoController {
	/*Hola gdgd*/
	private String URL = "http://localhost:8091";

	@RequestMapping("/listadoCargo")
	public String listado(Model model) {
		try {
			RestTemplate rt=new RestTemplate();
			List<Cargo[]> listaCargo=rt.getForObject(URL+"/cargo/listar", List.class);

			
			model.addAttribute("cargos",listaCargo);
			model.addAttribute("cargo", new Cargo());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "empleado/listadoCargo";
	}
	
	@RequestMapping("/registroCargo")
	public String index(Model model) {
		
		try {
			RestTemplate rt=new RestTemplate();
			List<Cargo[]> listaCargo=rt.getForObject(URL+"/cargo/listar", List.class);

			
			model.addAttribute("cargos",listaCargo);
			model.addAttribute("cargo", new Cargo());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "empleado/registroCargo";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Cargo bean, RedirectAttributes redirec) {
		try {
			RestTemplate rt=new RestTemplate();
			Gson gson=new Gson();
			String json=gson.toJson(bean);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> response = new HttpEntity<String>(json, headers);
			
			rt.postForObject(URL+"/cargo/registrar", response, String.class);
			
			redirec.addFlashAttribute("MENSAJE","Cargo registrado");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/cargo/registroCargo";
	}
	
}