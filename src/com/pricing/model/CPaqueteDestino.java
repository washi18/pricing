package com.pricing.model;

public class CPaqueteDestino
{
	private int codPaqueteDestino;// int,
	private String cPaqueteCod;// varchar(10),
	private int nDestinoCod;// int,
	private int nNoches;//int,
	private int nOrdenItinerario;//int,
	private boolean bConCaminoInka;//boolean,
	//=====================
	public int getCodPaqueteDestino() {
		return codPaqueteDestino;
	}
	public void setCodPaqueteDestino(int codPaqueteDestino) {
		this.codPaqueteDestino = codPaqueteDestino;
	}
	public String getcPaqueteCod() {
		return cPaqueteCod;
	}
	public void setcPaqueteCod(String cPaqueteCod) {
		this.cPaqueteCod = cPaqueteCod;
	}
	public int getnDestinoCod() {
		return nDestinoCod;
	}
	public void setnDestinoCod(int nDestinoCod) {
		this.nDestinoCod = nDestinoCod;
	}
	public int getnNoches() {
		return nNoches;
	}
	public void setnNoches(int nNoches) {
		this.nNoches = nNoches;
	}
	public int getnOrdenItinerario() {
		return nOrdenItinerario;
	}
	public void setnOrdenItinerario(int nOrdenItinerario) {
		this.nOrdenItinerario = nOrdenItinerario;
	}
	public boolean isbConCaminoInka() {
		return bConCaminoInka;
	}
	public void setbConCaminoInka(boolean bConCaminoInka) {
		this.bConCaminoInka = bConCaminoInka;
	}
	//=============================
	public CPaqueteDestino() {
		// TODO Auto-generated constructor stub
	}
	public CPaqueteDestino(int codPaqueteDestino, String cPaqueteCod,
			int nDestinoCod, int nNoches, int nOrdenItinerario,boolean bConCaminoInka) {
		this.codPaqueteDestino = codPaqueteDestino;
		this.cPaqueteCod = cPaqueteCod;
		this.nDestinoCod = nDestinoCod;
		this.nNoches = nNoches;
		this.nOrdenItinerario = nOrdenItinerario;
		this.bConCaminoInka=bConCaminoInka;
	}
	
}
