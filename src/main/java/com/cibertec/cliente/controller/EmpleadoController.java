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
@RequestMapping("/empleado")
public class EmpleadoController {
	/*COmentario de Crystal*/
	private String URL="http://localhost:8091"; 
	
	@RequestMapping("/listadoEmpleado")
	public String listado(Model model) {
		try {
			RestTemplate rt=new RestTemplate();
			List<Distrito[]> listaDistri=rt.getForObject(URL+"/distrito/listar", List.class);
			List<Cargo[]> listaCargo=rt.getForObject(URL+"/cargo/listar", List.class);
			List<Empleado[]> listaClient=rt.getForObject(URL+"/empleado/listar", List.class);
			
			model.addAttribute("distritos",listaDistri);
			model.addAttribute("cargos",listaCargo);
			model.addAttribute("empleados",listaClient);
			model.addAttribute("empleado", new Empleado());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "empleado/listadoEmpleado";
	}
	
	
	@RequestMapping("/registroEmpleado")
	public String registro(Model model) {
		try {
			RestTemplate rt=new RestTemplate();
			List<Distrito[]> listaDistri=rt.getForObject(URL+"/distrito/listar", List.class);
			List<Cargo[]> listaCargo=rt.getForObject(URL+"/cargo/listar", List.class);
			List<Empleado[]> listaClient=rt.getForObject(URL+"/empleado/listar", List.class);
			
			model.addAttribute("distritos",listaDistri);
			model.addAttribute("cargos",listaCargo);
			model.addAttribute("empleados",listaClient);
			model.addAttribute("empleado", new Empleado());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "empleado/registroEmpleado";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Empleado bean, RedirectAttributes redirec) {
		try {
			RestTemplate rt=new RestTemplate();
			Gson gson=new Gson();
			String json=gson.toJson(bean);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> response = new HttpEntity<String>(json, headers);
			rt.postForObject(URL+"/empleado/registrar", response, String.class);
			redirec.addFlashAttribute("MENSAJE","Empleado registrado");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/empleado/registroEmpleado";
	}	
	
	
}
