package com.pricing.model;

public class CPais 
{
	private int nPaisCod;// int,					--codigo del pais
	private String cNombreIdioma1;// varchar(60),				--nombre del pais en espanol
	private String cNombreIdioma2;// varchar(3),				--abreviatura del nombre en espanol
	private String cNombreIdioma3;// varchar(60),				--nombre del pais en ingles
	private String cNombreIdioma4;// varchar(3),				--abreviatura del pais en ingles
	private String cNombreIdioma5;
	private String namePais;
	//=============================
	public int getnPaisCod() {
		return nPaisCod;
	}
	public void setnPaisCod(int nPaisCod) {
		this.nPaisCod = nPaisCod;
	}
	public String getcNombreIdioma1() {
		return cNombreIdioma1;
	}
	public void setcNombreIdioma1(String cNombreIdioma1) {
		this.cNombreIdioma1 = cNombreIdioma1;
	}
	public String getcNombreIdioma2() {
		return cNombreIdioma2;
	}
	public void setcNombreIdioma2(String cNombreIdioma2) {
		this.cNombreIdioma2 = cNombreIdioma2;
	}
	public String getcNombreIdioma3() {
		return cNombreIdioma3;
	}
	public void setcNombreIdioma3(String cNombreIdioma3) {
		this.cNombreIdioma3 = cNombreIdioma3;
	}
	public String getcNombreIdioma4() {
		return cNombreIdioma4;
	}
	public void setcNombreIdioma4(String cNombreIdioma4) {
		this.cNombreIdioma4 = cNombreIdioma4;
	}
	public String getcNombreIdioma5() {
		return cNombreIdioma5;
	}
	public void setcNombreIdioma5(String cNombreIdioma5) {
		this.cNombreIdioma5 = cNombreIdioma5;
	}
	public String getNamePais() {
		return namePais;
	}
	public void setNamePais(String namePais) {
		this.namePais = namePais;
	}
	//=========================
	public CPais() {
		// TODO Auto-generated constructor stub
	}
	public CPais(int nPaisCod, String cNombreIdioma1, String cNombreIdioma2, String cNombreIdioma3,
			String cNombreIdioma4, String cNombreIdioma5) {
		this.nPaisCod = nPaisCod;
		this.cNombreIdioma1 = cNombreIdioma1;
		this.cNombreIdioma2 = cNombreIdioma2;
		this.cNombreIdioma3 = cNombreIdioma3;
		this.cNombreIdioma4 = cNombreIdioma4;
		this.cNombreIdioma5 = cNombreIdioma5;
	}
}
