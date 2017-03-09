package com.pricing.model;

public class CPaqueteActividad 
{
	private int nPaqueteActividad;// int not null,
	private String cPaqueteCod;// varchar(10),
	private int nActividadCod;// int,
	/*******************************/
	public int getnPaqueteActividad() {
		return nPaqueteActividad;
	}
	public void setnPaqueteActividad(int nPaqueteActividad) {
		this.nPaqueteActividad = nPaqueteActividad;
	}
	public String getcPaqueteCod() {
		return cPaqueteCod;
	}
	public void setcPaqueteCod(String cPaqueteCod) {
		this.cPaqueteCod = cPaqueteCod;
	}
	public int getnActividadCod() {
		return nActividadCod;
	}
	public void setnActividadCod(int nActividadCod) {
		this.nActividadCod = nActividadCod;
	}
	/************************************/
	public CPaqueteActividad() {
		// TODO Auto-generated constructor stub
	}
	public CPaqueteActividad(int nPaqueteActividad, String cPaqueteCod,
			int nActividadCod) {
		super();
		this.nPaqueteActividad = nPaqueteActividad;
		this.cPaqueteCod = cPaqueteCod;
		this.nActividadCod = nActividadCod;
	}
}
