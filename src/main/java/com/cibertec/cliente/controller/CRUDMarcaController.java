
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
import com.cibertec.cliente.entity.Distrito;
import com.cibertec.cliente.entity.Empleado;
import com.cibertec.cliente.entity.Marca;
import com.google.gson.Gson;

@Controller
@RequestMapping("/CRUDmarca")
public class CRUDMarcaController {
	private String URL = "http://localhost:8091";

	@RequestMapping("/CRUDmarca")
	public String index(Model model) {
		
		try {
			RestTemplate rt=new RestTemplate();
			List<Marca[]> listaC=rt.getForObject(URL+"/marca/listar", List.class);


			model.addAttribute("marcas",listaC);

			model.addAttribute("marca", new Marca());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "empleado/CRUDMarca";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Marca bean, RedirectAttributes redirect) {
		try {
			RestTemplate rt=new RestTemplate();
			Gson gson=new Gson();
			String json=gson.toJson(bean);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> response = new HttpEntity<String>(json, headers);
			
			rt.postForObject(URL+"/marca/registrar", response, String.class);
			
			redirect.addFlashAttribute("MENSAJE","Marca registrada");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/CRUDmarca/CRUDmarca";
	
		}
	@RequestMapping("/consulta")
	public String consulta(){

		return "consulta";
	}
	
}