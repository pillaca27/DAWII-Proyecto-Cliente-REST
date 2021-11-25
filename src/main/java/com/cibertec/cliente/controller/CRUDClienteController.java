
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

import com.cibertec.cliente.entity.Cargo;
import com.cibertec.cliente.entity.Cliente;
import com.cibertec.cliente.entity.Distrito;
import com.google.gson.Gson;

@Controller
@RequestMapping("/CRUDcliente")
public class CRUDClienteController {
	private String URL = "http://localhost:8091";

	@RequestMapping("/CRUDcliente")
	public String index(Model model) {
		
		try {
			RestTemplate rt=new RestTemplate();
			List<Cliente[]> listaC=rt.getForObject(URL+"/cliente/listar", List.class);
			List<Distrito[]> listaDist=rt.getForObject(URL+"/distrito/listar", List.class);

			
			model.addAttribute("clientes",listaC);
			model.addAttribute("distritos",listaDist);
			model.addAttribute("cliente", new Cliente());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "empleado/CRUDCliente";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Cliente bean, RedirectAttributes redirect) {
		try {
			RestTemplate rt=new RestTemplate();
			Gson gson=new Gson();
			String json=gson.toJson(bean);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> response = new HttpEntity<String>(json, headers);
			
			rt.postForObject(URL+"/cliente/registrar", response, String.class);
			
			redirect.addFlashAttribute("MENSAJE","Cliente registrado");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/CRUDcliente/CRUDcliente";
	
		}
	@RequestMapping("/consulta")
	public String consulta(){

		return "consulta";
	}
	
}