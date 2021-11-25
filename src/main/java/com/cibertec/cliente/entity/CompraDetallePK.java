package com.cibertec.cliente.entity;

import java.io.Serializable;

public class CompraDetallePK implements Serializable{
	private static final long serialVersionUID = 1L;

	private String cod_bo;
	private String cod_pro;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cod_bo == null) ? 0 : cod_bo.hashCode());
		result = prime * result + ((cod_pro == null) ? 0 : cod_pro.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompraDetallePK other = (CompraDetallePK) obj;
		if (cod_bo == null) {
			if (other.cod_bo != null)
				return false;
		} else if (!cod_bo.equals(other.cod_bo))
			return false;
		if (cod_pro == null) {
			if (other.cod_pro != null)
				return false;
		} else if (!cod_pro.equals(other.cod_pro))
			return false;
		return true;
	}
	public String getCod_bo() {
		return cod_bo;
	}
	public void setCod_bo(String cod_bo) {
		this.cod_bo = cod_bo;
	}
	public String getCod_pro() {
		return cod_pro;
	}
	public void setCod_pro(String cod_pro) {
		this.cod_pro = cod_pro;
	}
	
	
}
