package com.pricing.model;

public class CReservaPaquete {
	private long nReservaPaqueteCod;// bigint,
	private String cReservaCod;// varchar(12),
	private String cPaqueteCod;// varchar(10),
	private int nroPasajerosPaquete;// int,
	private Number nMontoTotalPaquete;// decimal(10,2),
	/******************/
	public long getnReservaPaqueteCod() {
		return nReservaPaqueteCod;
	}
	public void setnReservaPaqueteCod(long nReservaPaqueteCod) {
		this.nReservaPaqueteCod = nReservaPaqueteCod;
	}
	public String getcReservaCod() {
		return cReservaCod;
	}
	public void setcReservaCod(String cReservaCod) {
		this.cReservaCod = cReservaCod;
	}
	public String getcPaqueteCod() {
		return cPaqueteCod;
	}
	public void setcPaqueteCod(String cPaqueteCod) {
		this.cPaqueteCod = cPaqueteCod;
	}
	public int getNroPasajerosPaquete() {
		return nroPasajerosPaquete;
	}
	public void setNroPasajerosPaquete(int nroPasajerosPaquete) {
		this.nroPasajerosPaquete = nroPasajerosPaquete;
	}
	public Number getnMontoTotalPaquete() {
		return nMontoTotalPaquete;
	}
	public void setnMontoTotalPaquete(Number nMontoTotalPaquete) {
		this.nMontoTotalPaquete = nMontoTotalPaquete;
	}
	/**************/
	public CReservaPaquete() {
		// TODO Auto-generated constructor stub
	}
	public CReservaPaquete(long nReservaPaqueteCod, String cReservaCod, String cPaqueteCod, int nroPasajerosPaquete,
			Number nMontoTotalPaquete) {
		this.nReservaPaqueteCod = nReservaPaqueteCod;
		this.cReservaCod = cReservaCod;
		this.cPaqueteCod = cPaqueteCod;
		this.nroPasajerosPaquete = nroPasajerosPaquete;
		this.nMontoTotalPaquete = nMontoTotalPaquete;
	}
}
