package com.cibertec.cliente.entity;



public class VentaDetalle {
	private VentaDetallePK	ventaDetallePK;
	private int cantidad;
	private double subtotal;


	private Venta venta;


	private Producto producto;


	public VentaDetallePK getVentaDetallePK() {
		return ventaDetallePK;
	}


	public void setVentaDetallePK(VentaDetallePK ventaDetallePK) {
		this.ventaDetallePK = ventaDetallePK;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public double getSubtotal() {
		return subtotal;
	}


	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}


	public Venta getVenta() {
		return venta;
	}


	public void setVenta(Venta venta) {
		this.venta = venta;
	}


	public Producto getProducto() {
		return producto;
	}


	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	
	
}
