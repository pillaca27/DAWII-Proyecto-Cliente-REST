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
import com.cibertec.cliente.entity.Marca;
import com.cibertec.cliente.entity.Producto;
import com.google.gson.Gson;

@Controller
@RequestMapping("/producto")
public class ProductoController {
	private String URL = "http://localhost:8091";

	@RequestMapping("/listadoProducto")
	public String listado(Model model) {
		try {
			RestTemplate rt=new RestTemplate();
			List<Producto[]> listaProdu=rt.getForObject(URL+"/producto/listar", List.class);
			List<Marca[]> listaMarca=rt.getForObject(URL+"/marca/listar", List.class);
			
			model.addAttribute("productos",listaProdu);
			model.addAttribute("marcas",listaMarca);
			model.addAttribute("producto", new Producto());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "empleado/listadoProducto";
	}
	
	@RequestMapping("/registroProducto")
	public String index(Model model) {
		try {
			RestTemplate rt=new RestTemplate();
			List<Producto[]> listaProdu=rt.getForObject(URL+"/producto/listar", List.class);
			List<Marca[]> listaMarca=rt.getForObject(URL+"/marca/listar", List.class);
			model.addAttribute("productos",listaProdu);
			model.addAttribute("marcas",listaMarca);
			model.addAttribute("producto", new Producto());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "empleado/registroProducto";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Producto bean, RedirectAttributes redirec) {
		try {
			RestTemplate rt=new RestTemplate();
			Gson gson=new Gson();
			String json=gson.toJson(bean);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> response = new HttpEntity<String>(json, headers);
			rt.postForObject(URL+"/producto/registrar", response, String.class);
			redirec.addFlashAttribute("MENSAJE","Producto registrado");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/producto/registroProducto";
	}
	
	

}