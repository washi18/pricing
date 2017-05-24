package com.pricing.model;

public class CGaleriaImageExist {
	private String rutaImagen;
	private String style_Select;
	private boolean seleccionado;
	//==========================
	public String getRutaImagen() {
		return rutaImagen;
	}
	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}
	public String getStyle_Select() {
		return style_Select;
	}
	public void setStyle_Select(String style_Select) {
		this.style_Select = style_Select;
	}
	public boolean isSeleccionado() {
		return seleccionado;
	}
	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}
	//================================
	public CGaleriaImageExist() {
		// TODO Auto-generated constructor stub
		this.rutaImagen="";
		this.style_Select="div_content_imageHotel";
		this.seleccionado=false;
	}
}
