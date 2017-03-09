package com.pricing.model;

public class CGaleriaHotel {
	private int nGaleriaHotelCod;// int,
	private int nHotelCod;// int,
	private int nTipoImagen;// int,
	private String cRutaImagen;// varchar(100),
	private boolean bEstado;// boolean,
	private boolean visible;
	private String style_Select;
	/*******************/
	public int getnGaleriaHotelCod() {
		return nGaleriaHotelCod;
	}
	public void setnGaleriaHotelCod(int nGaleriaHotelCod) {
		this.nGaleriaHotelCod = nGaleriaHotelCod;
	}
	public int getnHotelCod() {
		return nHotelCod;
	}
	public void setnHotelCod(int nHotelCod) {
		this.nHotelCod = nHotelCod;
	}
	public int getnTipoImagen() {
		return nTipoImagen;
	}
	public void setnTipoImagen(int nTipoImagen) {
		this.nTipoImagen = nTipoImagen;
	}
	public String getcRutaImagen() {
		return cRutaImagen;
	}
	public void setcRutaImagen(String cRutaImagen) {
		this.cRutaImagen = cRutaImagen;
	}
	public boolean isbEstado() {
		return bEstado;
	}
	public void setbEstado(boolean bEstado) {
		this.bEstado = bEstado;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public String getStyle_Select() {
		return style_Select;
	}
	public void setStyle_Select(String style_Select) {
		this.style_Select = style_Select;
	}
	/*********************/
	public CGaleriaHotel() {
		// TODO Auto-generated constructor stub
		this.visible=false;
		this.style_Select="div_content_imageHotel";
	}
	public CGaleriaHotel(int nGaleriaHotelCod, int nHotelCod, int nTipoImagen, String cRutaImagen, boolean bEstado) {
		this.nGaleriaHotelCod = nGaleriaHotelCod;
		this.nHotelCod = nHotelCod;
		this.nTipoImagen = nTipoImagen;
		this.cRutaImagen = cRutaImagen;
		this.bEstado = bEstado;
		if(bEstado)
			this.style_Select="div_content_imageHotel_selected";
		else
			this.style_Select="div_content_imageHotel";
		this.visible=true;
	}
}
