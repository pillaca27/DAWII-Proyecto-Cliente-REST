package com.cibertec.cliente.controller;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

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
	
	@RequestMapping("/PDF")
	public ResponseEntity<byte[]> index(@RequestParam String codigo, @RequestParam String proveedor, 
			                            @RequestParam String empleado, @RequestParam String fecha_emision, 
			                            @RequestParam String fecha_reparto, @RequestParam String fecha_vencimiento,
			                            @RequestParam String tipo_compra, @RequestParam String tipo_pago) throws Exception, JRException {
		double monto = 0;
		for(Seleccion2 a : seleccionados) {
			monto += a.getCantidad() * a.getPrecio();
		}
		
		JRBeanCollectionDataSource jb = new JRBeanCollectionDataSource(seleccionados);
		JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/compra.jrxml"));
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("monto", monto);
		map.put("codigo", codigo);
		map.put("proveedor", proveedor);
		map.put("empleado", empleado);
		map.put("fecha_emision",fecha_emision);
		map.put("fecha_reparto",fecha_reparto);
		map.put("fecha_vencimiento",fecha_vencimiento);
		map.put("tipo_compra",tipo_compra);
		map.put("tipo_pago",tipo_pago);
		
		JasperPrint repot = JasperFillManager.fillReport(compileReport, map, jb);
		JasperExportManager.exportReportToPdfFile(repot, "compra_"+codigo+".pdf");
		
		byte[] data = JasperExportManager.exportReportToPdf(repot);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=compra_"+codigo+".pdf");
		
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
	

}