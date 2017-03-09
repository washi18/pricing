package com.pricing.model;

public class CDia 
{
	private String cNroDia;
	private String CantDisp;
	private String Disponible;
	private boolean Visible;
	private int Prioridad;
	private boolean Elegido;
	private String ImgPrioridad;
	private String DescDiaElegido;
	private int nro;
	private String colorDisp;
	private boolean editable;
	//===================
	
	public String getcNroDia() {
		return cNroDia;
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	public void setcNroDia(String cNroDia) {
		this.cNroDia = cNroDia;
	}
	public String getCantDisp() {
		return CantDisp;
	}
	public void setCantDisp(String cantDisp) {
		CantDisp = cantDisp;
	}
	public int getPrioridad() {
		return Prioridad;
	}
	public void setPrioridad(int prioridad) {
		Prioridad = prioridad;
	}
	public boolean isVisible() {
		return Visible;
	}
	public void setVisible(boolean visible) {
		Visible = visible;
	}
	public String getDisponible() {
		return Disponible;
	}
	public void setDisponible(String disponible) {
		Disponible = disponible;
	}
	public boolean isElegido() {
		return Elegido;
	}
	public void setElegido(boolean elegido) {
		Elegido = elegido;
	}
	public String getImgPrioridad() {
		return ImgPrioridad;
	}
	public void setImgPrioridad(String imgPrioridad) {
		ImgPrioridad = imgPrioridad;
	}
	public String getDescDiaElegido() {
		return DescDiaElegido;
	}
	public void setDescDiaElegido(String descDiaElegido) {
		DescDiaElegido = descDiaElegido;
	}
	public int getNro() {
		return nro;
	}
	public void setNro(int nro) {
		this.nro = nro;
	}
	public String getColorDisp() {
		return colorDisp;
	}
	public void setColorDisp(String colorDisp) {
		this.colorDisp = colorDisp;
	}
	//=======================
	public CDia(){
		// TODO Auto-generated constructor stub
		cNroDia="";
		CantDisp="";
		Disponible="";
		Prioridad=0;//fecha(0->no elegido ,1->principal ,2->alterna);
		Visible=false;
		ImgPrioridad="";
		Elegido=false;
		DescDiaElegido="";
		nro=0;
		colorDisp="";
		editable=true;
	}
	public CDia(String cNroDia, String cantDisp, String disponible, int prioridad) {
		this.cNroDia = cNroDia;
		CantDisp = cantDisp;
		Disponible = disponible;
		Prioridad = prioridad;
	}
}
