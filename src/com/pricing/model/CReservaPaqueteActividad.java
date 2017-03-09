package com.pricing.model;

public class CReservaPaqueteActividad {
	private long codReservaPActividad;// bigint,
	private int codPaqueteActividad;// int,
	private long nReservaPaqueteCod;// bigint,
	private int nroPrestacionesActividad;// int,
	private Number precioPrestacionActividad;// decimal(10,2),
	/***************/
	public long getCodReservaPActividad() {
		return codReservaPActividad;
	}
	public void setCodReservaPActividad(long codReservaPActividad) {
		this.codReservaPActividad = codReservaPActividad;
	}
	public int getCodPaqueteActividad() {
		return codPaqueteActividad;
	}
	public void setCodPaqueteActividad(int codPaqueteActividad) {
		this.codPaqueteActividad = codPaqueteActividad;
	}
	public int getNroPrestacionesActividad() {
		return nroPrestacionesActividad;
	}
	public void setNroPrestacionesActividad(int nroPrestacionesActividad) {
		this.nroPrestacionesActividad = nroPrestacionesActividad;
	}
	public Number getPrecioPrestacionActividad() {
		return precioPrestacionActividad;
	}
	public void setPrecioPrestacionActividad(Number precioPrestacionActividad) {
		this.precioPrestacionActividad = precioPrestacionActividad;
	}
	public long getnReservaPaqueteCod() {
		return nReservaPaqueteCod;
	}
	public void setnReservaPaqueteCod(long nReservaPaqueteCod) {
		this.nReservaPaqueteCod = nReservaPaqueteCod;
	}
	/******************/
	public CReservaPaqueteActividad() {
		// TODO Auto-generated constructor stub
	}
}
