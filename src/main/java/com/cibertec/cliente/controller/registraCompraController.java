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

import com.cibertec.cliente.entity.Compra;
import com.cibertec.cliente.entity.CompraDetalle;
import com.cibertec.cliente.entity.CompraDetallePK;
import com.cibertec.cliente.entity.Seleccion;
import com.cibertec.cliente.entity.Seleccion2;
import com.cibertec.cliente.entity.Venta;
import com.cibertec.cliente.entity.VentaDetalle;
import com.cibertec.cliente.entity.VentaDetallePK;
import com.google.gson.Gson;

@Controller
@RequestMapping("/Compra")
public class registraCompraController {
	private String URL = "http://localhost:8091";

	private List<Seleccion2> seleccionados= new ArrayList<Seleccion2>();

	@RequestMapping("/agregarSeleccion")
	@ResponseBody
	public List<Seleccion2> agregar(Seleccion2 obj){
		seleccionados.add(obj);
		return seleccionados;
	}
	
	@RequestMapping("/listaSeleccion")
	@ResponseBody
	public List<Seleccion2> lista(){
		return seleccionados;
	}
	
	@RequestMapping("/eliminaSeleccion")
	@ResponseBody
	public List<Seleccion2> eliminar(String idProducto){
		for(Seleccion2 x : seleccionados) {
			if(x.getIdProducto().equals(idProducto)) {
				seleccionados.remove(x);
				break;
			}
		}
		return seleccionados;
	}
	
	@RequestMapping("/registro")
	public String index(Model model) {
	    model.addAttribute("compra", new Compra());
		return "empleado/registroCompra";
	}
	
	@RequestMapping("/registraPedido")
	public String registrar(@ModelAttribute Compra bean, RedirectAttributes redirec) {
		double monto = 0;

		RestTemplate rt=new RestTemplate();
		Gson gson=new Gson();
		for(Seleccion2 a : seleccionados) {
			monto += a.getCantidad() * a.getPrecio()*a.getDescuento();
        }
		try {
			
			bean.setMonto(monto);
			String json=gson.toJson(bean);
			System.out.println(json);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> response = new HttpEntity<String>(json, headers);
			rt.postForObject(URL+"/compra/registrar", response, String.class);
			redirec.addFlashAttribute("MENSAJE","Compra registrado");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(Seleccion2 x : seleccionados) {
			try {
				

			CompraDetalle dt = new CompraDetalle();
			dt.setDescuento(x.getDescuento());
			dt.setCantidad(x.getCantidad());
			dt.setPrecio(x.getPrecio());
			dt.setSubtotal(x.getCantidad() * x.getPrecio()*x.getDescuento());
			CompraDetallePK pk= new CompraDetallePK();
			pk.setCod_bo(bean.getCodBol());
			pk.setCod_pro(x.getIdProducto());
			dt.setCompraDetallePK(pk);
			String json1 = gson.toJson(dt);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> response = new HttpEntity<String>(json1, headers);
			rt.postForObject(URL+"/compradetalle/registrar", response, String.class);
			
			
			rt.postForObject(URL+"/producto/actualizastockCompra/"+ x.getIdProducto() + "/" + x.getCantidad(), "", String.class);
			
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		seleccionados.clear();
		return "redirect:/Compra/registro";
	
	}
	

}