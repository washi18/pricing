package com.pricing.model;

public class CReservaCupon {
	private int nReservaCuponCod;// int,
	private String cReservaCod;// varchar(12),
	private int nCuponCod;// int,
	/*****************/
	public int getnReservaCuponCod() {
		return nReservaCuponCod;
	}
	public void setnReservaCuponCod(int nReservaCuponCod) {
		this.nReservaCuponCod = nReservaCuponCod;
	}
	public String getcReservaCod() {
		return cReservaCod;
	}
	public void setcReservaCod(String cReservaCod) {
		this.cReservaCod = cReservaCod;
	}
	public int getnCuponCod() {
		return nCuponCod;
	}
	public void setnCuponCod(int nCuponCod) {
		this.nCuponCod = nCuponCod;
	}
	/**************/
	public CReservaCupon() {
		// TODO Auto-generated constructor stub
	}
	public CReservaCupon(int nReservaCuponCod, String cReservaCod, int nCuponCod) {
		this.nReservaCuponCod = nReservaCuponCod;
		this.cReservaCod = cReservaCod;
		this.nCuponCod = nCuponCod;
	}
}
