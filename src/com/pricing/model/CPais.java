package com.pricing.model;

public class CPais 
{
	private int nPaisCod;// int,					--codigo del pais
	private String cNombreEsp;// varchar(60),				--nombre del pais en espanol
	private String cAbrevEsp;// varchar(3),				--abreviatura del nombre en espanol
	private String cNombreIng;// varchar(60),				--nombre del pais en ingles
	private String cAbrevIng;// varchar(3),				--abreviatura del pais en ingles
	//=============================
	public int getnPaisCod() {
		return nPaisCod;
	}
	public void setnPaisCod(int nPaisCod) {
		this.nPaisCod = nPaisCod;
	}
	public String getcNombreEsp() {
		return cNombreEsp;
	}
	public void setcNombreEsp(String cNombreEsp) {
		this.cNombreEsp = cNombreEsp;
	}
	public String getcAbrevEsp() {
		return cAbrevEsp;
	}
	public void setcAbrevEsp(String cAbrevEsp) {
		this.cAbrevEsp = cAbrevEsp;
	}
	public String getcNombreIng() {
		return cNombreIng;
	}
	public void setcNombreIng(String cNombreIng) {
		this.cNombreIng = cNombreIng;
	}
	public String getcAbrevIng() {
		return cAbrevIng;
	}
	public void setcAbrevIng(String cAbrevIng) {
		this.cAbrevIng = cAbrevIng;
	}
	//=========================
	public CPais() {
		// TODO Auto-generated constructor stub
	}
	public CPais(int nPaisCod, String cNombreEsp, String cAbrevEsp,
			String cNombreIng, String cAbrevIng) {
		this.nPaisCod = nPaisCod;
		this.cNombreEsp = cNombreEsp;
		this.cAbrevEsp = cAbrevEsp;
		this.cNombreIng = cNombreIng;
		this.cAbrevIng = cAbrevIng;
	}
	
}
