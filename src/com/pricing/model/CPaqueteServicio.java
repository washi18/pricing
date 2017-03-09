package com.pricing.model;

public class CPaqueteServicio 
{
	private int codPaqueteServicio;// int,
	private String cPaqueteCod;// varchar(10),
	private int nServicioCod;// int,
	//==========================
	
	public String getcPaqueteCod() {
		return cPaqueteCod;
	}
	public void setcPaqueteCod(String cPaqueteCod) {
		this.cPaqueteCod = cPaqueteCod;
	}
	public int getnServicioCod() {
		return nServicioCod;
	}
	public void setnServicioCod(int nServicioCod) {
		this.nServicioCod = nServicioCod;
	}
	public int getCodPaqueteServicio() {
		return codPaqueteServicio;
	}
	public void setCodPaqueteServicio(int codPaqueteServicio) {
		this.codPaqueteServicio = codPaqueteServicio;
	}
	//========================================
	public CPaqueteServicio() {
		// TODO Auto-generated constructor stub
	}
	public CPaqueteServicio(int codPaqueteServicio, String cPaqueteCod,
			int nServicioCod) {
		this.codPaqueteServicio = codPaqueteServicio;
		this.cPaqueteCod = cPaqueteCod;
		this.nServicioCod = nServicioCod;
	}
	
}
