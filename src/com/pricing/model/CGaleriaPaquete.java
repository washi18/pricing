package com.pricing.model;

public class CGaleriaPaquete {
	private long ngaleriapaquetecod;
	private String cpaquetecod;
	private String cimage;
	private boolean bestado;
	private String cRutaImagen;
	private boolean visible;
	private String style_Select;
	//============
	public String getcRutaImagen() {
		return cRutaImagen;
	}
	public long getNgaleriapaquetecod() {
		return ngaleriapaquetecod;
	}
	public void setNgaleriapaquetecod(long ngaleriapaquetecod) {
		this.ngaleriapaquetecod = ngaleriapaquetecod;
	}
	public void setcRutaImagen(String cRutaImagen) {
		this.cRutaImagen = cRutaImagen;
	}
	public String getStyle_Select() {
		return style_Select;
	}
	public void setStyle_Select(String style_Select) {
		this.style_Select = style_Select;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public String getCpaquetecod() {
		return cpaquetecod;
	}
	public void setCpaquetecod(String cpaquetecod) {
		this.cpaquetecod = cpaquetecod;
	}
	public String getCimage() {
		return cimage;
	}
	public void setCimage(String cimage) {
		this.cimage = cimage;
	}
	public boolean isBestado() {
		return bestado;
	}
	public void setBestado(boolean bestado) {
		this.bestado = bestado;
	}
	//========contructores======
	public CGaleriaPaquete(){
		super();
		this.visible=false;
		this.style_Select="div_content_imageHotel";
	}
	public CGaleriaPaquete(long ngaleriapaquetecod, String cpaquetecod, String cRutaImagen, boolean bestado) {
		super();
		this.ngaleriapaquetecod = ngaleriapaquetecod;
		this.cpaquetecod = cpaquetecod;
		this.cRutaImagen = cRutaImagen;
		this.bestado = bestado;
		if(bestado)
			this.style_Select="div_content_imageHotel_selected";
		else
			this.style_Select="div_content_imageHotel";
		this.visible=true;
	}
	//==========otros metodos========
	
	
}
