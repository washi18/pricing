package com.android.model;

public class CSubMenu {
	private int cSubMenuCod;// integer,
	private int cMenuCod;// integer,
	private String cNombreIdioma1;// varchar(200),
	private String cNombreIdioma2;// varchar(200),
	private String cNombreIdioma3;// varchar(200),
	private String cNombreIdioma4;// varchar(200),
	private String cNombreIdioma5;// varchar(200),
	private String cImagen;// varchar(100),
	private boolean estado;// boolean,
	private boolean elemento;// boolean,
	//==================================
	public int getcSubMenuCod() {
		return cSubMenuCod;
	}
	public void setcSubMenuCod(int cSubMenuCod) {
		this.cSubMenuCod = cSubMenuCod;
	}
	public int getcMenuCod() {
		return cMenuCod;
	}
	public void setcMenuCod(int cMenuCod) {
		this.cMenuCod = cMenuCod;
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
	public String getcImagen() {
		return cImagen;
	}
	public void setcImagen(String cImagen) {
		this.cImagen = cImagen;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public boolean isElemento() {
		return elemento;
	}
	public void setElemento(boolean elemento) {
		this.elemento = elemento;
	}
	//===========================
	public CSubMenu() {
		// TODO Auto-generated constructor stub
		this.cMenuCod=0;
		this.cNombreIdioma1="";
		this.cNombreIdioma2="";
		this.cNombreIdioma3="";
		this.cNombreIdioma4="";
		this.cNombreIdioma5="";
		this.cImagen="";
	}
	public CSubMenu(int cSubMenuCod, int cMenuCod, String cNombreIdioma1, String cNombreIdioma2, String cNombreIdioma3,
			String cNombreIdioma4, String cNombreIdioma5, String cImagen, boolean estado, boolean elemento) {
		this.cSubMenuCod = cSubMenuCod;
		this.cMenuCod = cMenuCod;
		this.cNombreIdioma1 = cNombreIdioma1;
		this.cNombreIdioma2 = cNombreIdioma2;
		this.cNombreIdioma3 = cNombreIdioma3;
		this.cNombreIdioma4 = cNombreIdioma4;
		this.cNombreIdioma5 = cNombreIdioma5;
		this.cImagen = cImagen;
		this.estado = estado;
		this.elemento = elemento;
	}
}
