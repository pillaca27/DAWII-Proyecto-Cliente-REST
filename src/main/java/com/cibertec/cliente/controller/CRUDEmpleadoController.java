
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
import com.google.gson.Gson;

@Controller
@RequestMapping("/CRUDempleado")
public class CRUDEmpleadoController {
	private String URL = "http://localhost:8091";

	@RequestMapping("/CRUDempleado")
	public String index(Model model) {
		
		try {
			RestTemplate rt=new RestTemplate();
			List<Empleado[]> listaC=rt.getForObject(URL+"/empleado/listar", List.class);
			List<Distrito[]> listaDist=rt.getForObject(URL+"/distrito/listar", List.class);
			List<Cargo[]> listaCarg=rt.getForObject(URL+"/cargo/listar", List.class);

			model.addAttribute("empleados",listaC);
			model.addAttribute("distritos",listaDist);
			model.addAttribute("cargos",listaCarg);
			model.addAttribute("empleado", new Empleado());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "empleado/CRUDEmpleado";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Empleado bean, RedirectAttributes redirect) {
		try {
			RestTemplate rt=new RestTemplate();
			Gson gson=new Gson();
			String json=gson.toJson(bean);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> response = new HttpEntity<String>(json, headers);
			
			rt.postForObject(URL+"/empleado/registrar", response, String.class);
			
			redirect.addFlashAttribute("MENSAJE","Empleado registrado");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/CRUDempleado/CRUDempleado";
	
		}
	@RequestMapping("/consulta")
	public String consulta(){

		return "consulta";
	}
	
}