package com.cibertec.cliente.entity;

import java.util.Date;
import java.util.List;



public class Venta {

	private String codigo;
	
	private String fecha_ped;
	
	private double monto;
	

	private Cliente cliente;
	

	private Empleado empleado;

    private List<VentaDetalle> detallesVenta;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getFecha_ped() {
		return fecha_ped;
	}

	public void setFecha_ped(String fecha_ped) {
		this.fecha_ped = fecha_ped;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public List<VentaDetalle> getDetallesVenta() {
		return detallesVenta;
	}

	public void setDetallesVenta(List<VentaDetalle> detallesVenta) {
		this.detallesVenta = detallesVenta;
	}
 
    
    
}
