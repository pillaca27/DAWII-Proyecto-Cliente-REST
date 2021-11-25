package com.cibertec.cliente.entity;

public class Proveedor {

	private String ruc;

	private String nombre;

	private String nombre_cont;

	private String telefono;

	private String direccion;

	private Distrito distrito;

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre_cont() {
		return nombre_cont;
	}

	public void setNombre_cont(String nombre_cont) {
		this.nombre_cont = nombre_cont;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

}
