package com.cibertec.cliente.entity;



public class CompraDetalle {
	private CompraDetallePK compraDetallePK;
	private double precio;
	private int cantidad;
	private double descuento;
	private double subtotal;	

	private Compra compra;


	private Producto producto;


	public CompraDetallePK getCompraDetallePK() {
		return compraDetallePK;
	}


	public void setCompraDetallePK(CompraDetallePK compraDetallePK) {
		this.compraDetallePK = compraDetallePK;
	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public double getDescuento() {
		return descuento;
	}


	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}


	public double getSubtotal() {
		return subtotal;
	}


	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}


	public Compra getCompra() {
		return compra;
	}


	public void setCompra(Compra compra) {
		this.compra = compra;
	}


	public Producto getProducto() {
		return producto;
	}


	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	
	
	
}
