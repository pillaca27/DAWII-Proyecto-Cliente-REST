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
import com.cibertec.cliente.entity.Cliente;
import com.cibertec.cliente.entity.Distrito;
import com.google.gson.Gson;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	private String URL = "http://localhost:8091";

	@RequestMapping("/listadoCliente")
	public String listado(Model model) {
		try {
			RestTemplate rt=new RestTemplate();
			List<Distrito[]> listaDistri=rt.getForObject(URL+"/distrito/listar", List.class);
			List<Cliente[]> listaClient=rt.getForObject(URL+"/cliente/listar", List.class);
			
			model.addAttribute("distritos",listaDistri);
			model.addAttribute("clientes",listaClient);
			model.addAttribute("cliente", new Cliente());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "empleado/listadoCliente";
	}
	
	@RequestMapping("/registroCliente")
	public String index(Model model) {
		try {
			RestTemplate rt=new RestTemplate();
			List<Distrito[]> listaDistri=rt.getForObject(URL+"/distrito/listar", List.class);
			List<Cliente[]> listaClient=rt.getForObject(URL+"/cliente/listar", List.class);
			
			model.addAttribute("distritos",listaDistri);
			model.addAttribute("clientes",listaClient);
			model.addAttribute("cliente", new Cliente());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "empleado/registroCliente";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Cliente bean, RedirectAttributes redirec) {
		try {
			RestTemplate rt=new RestTemplate();
			Gson gson=new Gson();
			String json=gson.toJson(bean);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> response = new HttpEntity<String>(json, headers);
			rt.postForObject(URL+"/cliente/registrar", response, String.class);
			redirec.addFlashAttribute("MENSAJE","Cliente registrado");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/cliente/registroCliente";
	}
	

}