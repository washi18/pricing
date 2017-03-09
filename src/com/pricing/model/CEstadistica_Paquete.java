package com.pricing.model;

import java.util.Date;

public class CEstadistica_Paquete {
	//=========atributos======
	private String nombrePaquete;
	private long nroVentas;
	private Date fechaPago;
	//=========getter and setter====
	public String getNombrePaquete() {
		return nombrePaquete;
	}
	public void setNombrePaquete(String nombrePaquete) {
		this.nombrePaquete = nombrePaquete;
	}
	
	public long getNroVentas() {
		return nroVentas;
	}
	public void setNroVentas(long nroVentas) {
		this.nroVentas = nroVentas;
	}
	
	public Date getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	//======constructores====
	public CEstadistica_Paquete(String nombrePaquete, long nroVentas) {
		super();
		this.nombrePaquete = nombrePaquete;
		this.nroVentas = nroVentas;
	}
	
	public CEstadistica_Paquete(String nombrePaquete, long nroVentas,Date fecha) {
		super();
		this.nombrePaquete = nombrePaquete;
		this.nroVentas = nroVentas;
		this.fechaPago=fecha;
	}
	//====metodos=========
	
}
