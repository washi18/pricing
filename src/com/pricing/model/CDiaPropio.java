package com.pricing.model;

public class CDiaPropio 
{
	private int nCalendarioCod;// int,
	private int nMes;// int,
	private int nDia;// int,
	private int nDispo_Pack;// int,
	private int nDispo_Inka;// int,
	/*************************/
	public int getnCalendarioCod() {
		return nCalendarioCod;
	}
	public void setnCalendarioCod(int nCalendarioCod) {
		this.nCalendarioCod = nCalendarioCod;
	}
	public int getnMes() {
		return nMes;
	}
	public void setnMes(int nMes) {
		this.nMes = nMes;
	}
	public int getnDia() {
		return nDia;
	}
	public void setnDia(int nDia) {
		this.nDia = nDia;
	}
	public int getnDispo_Pack() {
		return nDispo_Pack;
	}
	public void setnDispo_Pack(int nDispo_Pack) {
		this.nDispo_Pack = nDispo_Pack;
	}
	public int getnDispo_Inka() {
		return nDispo_Inka;
	}
	public void setnDispo_Inka(int nDispo_Inka) {
		this.nDispo_Inka = nDispo_Inka;
	}
	/************************/
	public CDiaPropio() {
		// TODO Auto-generated constructor stub
	}
	public CDiaPropio(int nCalendarioCod, int nMes, int nDia, int nDispo_Pack,
			int nDispo_Inka) {
		this.nCalendarioCod = nCalendarioCod;
		this.nMes = nMes;
		this.nDia = nDia;
		this.nDispo_Pack = nDispo_Pack;
		this.nDispo_Inka = nDispo_Inka;
	}
}