package com.cibertec.cliente.entity;

/**
 * @author ARTURO CHEA
 *
 */
public class Usuario {

	private int codUsuario;
	

	private String usuario;
	

	private String password;
	
	private TipoUsuario tipo;

	
	public int getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(int codUsuario) {
		this.codUsuario = codUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

	
	
	

}
