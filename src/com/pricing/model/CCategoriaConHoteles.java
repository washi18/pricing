package com.pricing.model;

import java.util.ArrayList;

public class CCategoriaConHoteles 
{
	private String precioSimple;
	private String precioDoble;
	private String precioTriple;
	private String precioCamaAdicional;
	private int codCat;
	private String nameCategory;
	private String estrellas;
	private String color;
	private String idPopup;
	private String icono_btn;
	private boolean estadoCategoria;
	private boolean mostrarListaHoteles;
	private boolean select;
	public String COLOR_SELECT="background:#4b9ec9;";
	public String COLOR_NO_SELECT="background:white;";
	private ArrayList<CDestinoConHoteles> listaCategoriaDestinosHoteles;
	//==============================
	public String getPrecioSimple() {
		return precioSimple;
	}
	public void setPrecioSimple(String precioSimple) {
		this.precioSimple = precioSimple;
	}
	public String getPrecioDoble() {
		return precioDoble;
	}
	public void setPrecioDoble(String precioDoble) {
		this.precioDoble = precioDoble;
	}
	public String getPrecioTriple() {
		return precioTriple;
	}
	public void setPrecioTriple(String precioTriple) {
		this.precioTriple = precioTriple;
	}
	public String getPrecioCamaAdicional() {
		return precioCamaAdicional;
	}
	public void setPrecioCamaAdicional(String precioCamaAdicional) {
		this.precioCamaAdicional = precioCamaAdicional;
	}
	public String getNameCategory() {
		return nameCategory;
	}
	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}
	public String getEstrellas() {
		return estrellas;
	}
	public void setEstrellas(String estrellas) {
		this.estrellas = estrellas;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getIdPopup() {
		return idPopup;
	}
	public void setIdPopup(String idPopup) {
		this.idPopup = idPopup;
	}
	public ArrayList<CDestinoConHoteles> getListaCategoriaDestinosHoteles() {
		return listaCategoriaDestinosHoteles;
	}
	public void setListaCategoriaDestinosHoteles(
			ArrayList<CDestinoConHoteles> listaCategoriaDestinosHoteles) {
		this.listaCategoriaDestinosHoteles = listaCategoriaDestinosHoteles;
	}
	public int getCodCat() {
		return codCat;
	}
	public void setCodCat(int codCat) {
		this.codCat = codCat;
	}
	public boolean isMostrarListaHoteles() {
		return mostrarListaHoteles;
	}
	public void setMostrarListaHoteles(boolean mostrarListaHoteles) {
		this.mostrarListaHoteles = mostrarListaHoteles;
	}
	public String getIcono_btn() {
		return icono_btn;
	}
	public void setIcono_btn(String icono_btn) {
		this.icono_btn = icono_btn;
	}
	public boolean isSelect() {
		return select;
	}
	public void setSelect(boolean select) {
		this.select = select;
	}
	public boolean isEstadoCategoria() {
		return estadoCategoria;
	}
	public void setEstadoCategoria(boolean estadoCategoria) {
		this.estadoCategoria = estadoCategoria;
	}
	//============================
	public CCategoriaConHoteles() {
		// TODO Auto-generated constructor stub
		this.precioSimple="0";
		this.precioDoble="0";
		this.precioTriple="0";
		this.precioCamaAdicional="0";
		this.codCat=0;
		this.nameCategory="";
		this.estrellas="";
		this.idPopup="";
		this.icono_btn="icon-circle-down";
		this.mostrarListaHoteles=false;
		this.select=false;
		this.color=COLOR_NO_SELECT;
		this.listaCategoriaDestinosHoteles=new ArrayList<CDestinoConHoteles>();
		this.estadoCategoria=true;
	}
}
