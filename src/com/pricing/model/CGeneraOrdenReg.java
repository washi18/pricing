package com.pricing.model;

public class CGeneraOrdenReg {
	private long nGeneraOrdenRegCod;// bigint,
	private String cOrden;// varchar(15),
	private String cRegistro;// varchar(20),
	//================================
	public long getnGeneraOrdenRegCod() {
		return nGeneraOrdenRegCod;
	}
	public void setnGeneraOrdenRegCod(long nGeneraOrdenRegCod) {
		this.nGeneraOrdenRegCod = nGeneraOrdenRegCod;
	}
	public String getcOrden() {
		return cOrden;
	}
	public void setcOrden(String cOrden) {
		this.cOrden = cOrden;
	}
	public String getcRegistro() {
		return cRegistro;
	}
	public void setcRegistro(String cRegistro) {
		this.cRegistro = cRegistro;
	}
	//==========================
	public CGeneraOrdenReg() {
		// TODO Auto-generated constructor stub
	}
	public CGeneraOrdenReg(Number nGeneraOrdenRegCod, String cOrden, String cRegistro) {
		this.nGeneraOrdenRegCod = nGeneraOrdenRegCod.longValue();
		this.cOrden = cOrden;
		this.cRegistro = cRegistro;
	}
}
