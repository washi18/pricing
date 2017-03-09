package com.pricing.model;

public class CReservaPaqueteServicio 
{
	private long codReservaPServicio;//bigint,
	private int codPaqueteServicio;// int,
	private long nReservaPaqueteCod;// bigint,
	private double nroPrestacionServicio;// int,
	private Number precioPrestacionServicio;// decimal(10,2),
	private int nSubServicioCod;//int
	//===========================
	public Number getPrecioPrestacionServicio() {
		return precioPrestacionServicio;
	}
	public void setPrecioPrestacionServicio(Number precioPrestacionServicio) {
		this.precioPrestacionServicio = precioPrestacionServicio;
	}
	public long getCodReservaPServicio() {
		return codReservaPServicio;
	}
	public void setCodReservaPServicio(long codReservaPServicio) {
		this.codReservaPServicio = codReservaPServicio;
	}
	public double getNroPrestacionServicio() {
		return nroPrestacionServicio;
	}
	public void setNroPrestacionServicio(double nroPrestacionServicio) {
		this.nroPrestacionServicio = nroPrestacionServicio;
	}
	public int getCodPaqueteServicio() {
		return codPaqueteServicio;
	}
	public void setCodPaqueteServicio(int codPaqueteServicio) {
		this.codPaqueteServicio = codPaqueteServicio;
	}
	public int getnSubServicioCod() {
		return nSubServicioCod;
	}
	public void setnSubServicioCod(int nSubServicioCod) {
		this.nSubServicioCod = nSubServicioCod;
	}
	public long getnReservaPaqueteCod() {
		return nReservaPaqueteCod;
	}
	public void setnReservaPaqueteCod(long nReservaPaqueteCod) {
		this.nReservaPaqueteCod = nReservaPaqueteCod;
	}
	//===================================
	public CReservaPaqueteServicio() {
		// TODO Auto-generated constructor stub
	}
	public CReservaPaqueteServicio(long codReservaPServicio,
			int codPaqueteServicio, long nReservaPaqueteCod,
			double nroPrestacionServicio, Number precioPrestacionServicio,
			int nSubServicioCod) {
		this.codReservaPServicio = codReservaPServicio;
		this.codPaqueteServicio = codPaqueteServicio;
		this.nReservaPaqueteCod = nReservaPaqueteCod;
		this.nroPrestacionServicio = nroPrestacionServicio;
		this.precioPrestacionServicio = precioPrestacionServicio;
		this.nSubServicioCod=nSubServicioCod;
	}
	
}
