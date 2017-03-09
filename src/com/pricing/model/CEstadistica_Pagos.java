package com.pricing.model;

import java.sql.Timestamp;
import java.util.Date;

public class CEstadistica_Pagos {
	//======atributos=========
	private String FormaPago;
	private long numeroPagos;
	private Date fechaPago;
	//========getter and setter=====
	public String getFormaPago() {
		return FormaPago;
	}
	public void setFormaPago(String formaPago) {
		FormaPago = formaPago;
	}
	public long getNumeroPagos() {
		return numeroPagos;
	}
	public void setNumeroPagos(long numeroPagos) {
		this.numeroPagos = numeroPagos;
	}
	
	
	public Date getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	//========constructores====
	public CEstadistica_Pagos(String formaPago,Date fechaPago) {
		super();
		this.FormaPago = formaPago;
		this.fechaPago=fechaPago;
	}
	
}
