package com.pricing.model;

import java.util.ArrayList;

public class CServicioConSubServicios {
	//=======atributos======
	private String nombreServicio;
	private ArrayList<CSubServicio> listaServiciosconSubservicios;
	//==========getter and setter======
	public String getNombreServicio() {
		return nombreServicio;
	}
	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}
	public ArrayList<CSubServicio> getListaServiciosconSubservicios() {
		return listaServiciosconSubservicios;
	}
	public void setListaServiciosconSubservicios(
			ArrayList<CSubServicio> listaServiciosconSubservicios) {
		this.listaServiciosconSubservicios = listaServiciosconSubservicios;
	}
	//=================constructores========
	public CServicioConSubServicios()
	{
		super();
		this.nombreServicio="";
		this.listaServiciosconSubservicios=null;
	}
	public CServicioConSubServicios(String nombreServicio,
			ArrayList<CSubServicio> listaServiciosconSubservicios) {
		this.nombreServicio = nombreServicio;
		this.listaServiciosconSubservicios = listaServiciosconSubservicios;
	}
	
}
