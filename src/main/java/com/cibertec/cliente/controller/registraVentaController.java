package com.cibertec.cliente.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.cliente.entity.Seleccion;
import com.cibertec.cliente.entity.Venta;
import com.cibertec.cliente.entity.VentaDetalle;
import com.cibertec.cliente.entity.VentaDetallePK;
import com.google.gson.Gson;

@Controller
@RequestMapping("/Venta")
public class registraVentaController {
	private String URL = "http://localhost:8091";

	private List<Seleccion> seleccionados= new ArrayList<Seleccion>();

	@RequestMapping("/agregarSeleccion")
	@ResponseBody
	public List<Seleccion> agregar(Seleccion obj){
		seleccionados.add(obj);
		return seleccionados;
	}
	
	@RequestMapping("/listaSeleccion")
	@ResponseBody
	public List<Seleccion> lista(){
		return seleccionados;
	}
	
	@RequestMapping("/eliminaSeleccion")
	@ResponseBody
	public List<Seleccion> eliminar(String idProducto){
		for(Seleccion x : seleccionados) {
			if(x.getIdProducto().equals(idProducto)) {
				seleccionados.remove(x);
				break;
			}
		}
		return seleccionados;
	}
	
	@RequestMapping("/registro")
	public String index(Model model) {
		model.addAttribute("venta", new Venta());
		return "empleado/registroVenta";
	}
	
	@RequestMapping("/registraPedido")
	public String registrar(@ModelAttribute Venta bean, RedirectAttributes redirec) {
		double monto = 0;

		RestTemplate rt=new RestTemplate();
		Gson gson=new Gson();
		for(Seleccion a : seleccionados) {
			monto += a.getCantidad() * a.getPrecio();
		}
		try {
			
			bean.setMonto(monto);
			String json=gson.toJson(bean);
			System.out.println(json);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> response = new HttpEntity<String>(json, headers);
			rt.postForObject(URL+"/venta/registrar", response, String.class);
			redirec.addFlashAttribute("MENSAJE","VENTA registrado");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(Seleccion x : seleccionados) {
			try {
				

			VentaDetalle dt = new VentaDetalle();
			dt.setCantidad(x.getCantidad());
			dt.setSubtotal(x.getCantidad() * x.getPrecio());
			VentaDetallePK pk= new VentaDetallePK();
			pk.setCod_notaped(bean.getCodigo());
			pk.setCod_pro(x.getIdProducto());
			dt.setVentaDetallePK(pk);
			
			
			String json1 = gson.toJson(dt);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> response = new HttpEntity<String>(json1, headers);
			rt.postForObject(URL+"/ventadetalle/registrar", response, String.class);
			
			
			rt.postForObject(URL+"/producto/actualizastock/"+ x.getIdProducto() + "/" + x.getCantidad(), "", String.class);
			
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		seleccionados.clear();
		return "redirect:/Venta/registro";
	
	}
	

}