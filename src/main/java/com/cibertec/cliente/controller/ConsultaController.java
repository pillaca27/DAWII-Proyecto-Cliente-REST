package com.cibertec.cliente.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.cibertec.cliente.entity.Cargo;
import com.cibertec.cliente.entity.Distrito;

@Controller
@RequestMapping("/consulta")
public class ConsultaController {
	
	private String URL = "http://localhost:8091";
	
	// CONSULTA DE CARGO
	@RequestMapping("/Cargo")
	public String consultacargo(){
		return "empleado/consultaCargo";
	}
	
	@RequestMapping("/Cliente")
	public String consultacliente(Model model){
		try {
			RestTemplate rt=new RestTemplate();
			List<Distrito[]> listaDistri=rt.getForObject(URL+"/distrito/listar", List.class);
			
			model.addAttribute("distritos",listaDistri);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "empleado/consultaCliente";
	}
	
	@RequestMapping("/Empleado")
	public String consultaempleado(Model model){
		try {
			RestTemplate rt=new RestTemplate();
			List<Cargo[]> listaCargo=rt.getForObject(URL+"/cargo/listar", List.class);
			//List<Distrito[]> listaDistri=rt.getForObject(URL+"/distrito/listar", List.class);

			model.addAttribute("cargos",listaCargo);
			//model.addAttribute("distritos",listaDistri);


		} catch (Exception e) {
			e.printStackTrace();
		}
		return "empleado/consultaEmpleado";
	}

}
