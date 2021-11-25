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

import com.cibertec.cliente.entity.Distrito;
import com.cibertec.cliente.entity.Proveedor;
import com.google.gson.Gson;

@Controller
@RequestMapping("/proveedor")
public class ProveedorController {
	private String URL = "http://localhost:8091";

	@RequestMapping("/listadoProveedor")
	public String listado(Model model) {
		try {
			RestTemplate rt=new RestTemplate();
			List<Proveedor[]> listaProvee=rt.getForObject(URL+"/proveedor/listar", List.class);
			List<Distrito[]> listaDistritos=rt.getForObject(URL+"/distrito/listar", List.class);
			
			model.addAttribute("proveedores",listaProvee);
			model.addAttribute("distritos",listaDistritos);
			model.addAttribute("proveedor", new Proveedor());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "empleado/listadoProveedor";
	}
	
	@RequestMapping("/registroProveedor")
	public String index(Model model) {
		try {
			RestTemplate rt=new RestTemplate();
			List<Proveedor[]> listaProvee=rt.getForObject(URL+"/proveedor/listar", List.class);
			List<Distrito[]> listaDistritos=rt.getForObject(URL+"/distrito/listar", List.class);
			
			model.addAttribute("proveedores",listaProvee);
			model.addAttribute("distritos",listaDistritos);
			model.addAttribute("proveedor", new Proveedor());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "empleado/registroProveedor";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Proveedor bean, RedirectAttributes redirec) {
		try {
			RestTemplate rt=new RestTemplate();
			Gson gson=new Gson();
			String json=gson.toJson(bean);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> response = new HttpEntity<String>(json, headers);
			rt.postForObject(URL+"/proveedor/registrar", response, String.class);
			redirec.addFlashAttribute("MENSAJE","Proveedor registrado");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/proveedor/registroProveedor";
	}
	

}