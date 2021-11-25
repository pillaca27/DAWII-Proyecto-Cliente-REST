package com.cibertec.cliente.entity;
import java.util.Date;
import java.util.List;






public class Compra {
	private String codBol;
	private String tipoCompra;
	private String tipoPago;

	private String emision;

	private String reparto;

	private String vencim;
	private double monto;
	

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	private Proveedor proveedor;
	

	private Empleado empleado;	
	
    private List<CompraDetalle> detallesCompra;

	public String getCodBol() {
		return codBol;
	}

	public void setCodBol(String codBol) {
		this.codBol = codBol;
	}

	public String getTipoCompra() {
		return tipoCompra;
	}

	public void setTipoCompra(String tipoCompra) {
		this.tipoCompra = tipoCompra;
	}

	public String getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}

	

	
	public String getEmision() {
		return emision;
	}

	public void setEmision(String emision) {
		this.emision = emision;
	}

	public String getReparto() {
		return reparto;
	}

	public void setReparto(String reparto) {
		this.reparto = reparto;
	}

	public String getVencim() {
		return vencim;
	}

	public void setVencim(String vencim) {
		this.vencim = vencim;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public List<CompraDetalle> getDetallesCompra() {
		return detallesCompra;
	}

	public void setDetallesCompra(List<CompraDetalle> detallesCompra) {
		this.detallesCompra = detallesCompra;
	}

    
    
}
