package com.android.model;

public class CDestinoMovil {
	private int nDestinoCod;// integer,
	private String cDestino;// varchar(100),
	private boolean bEstado;// boolean,
	private String cLatitud;// varchar(200),
	private String cLongitud;// varchar(200),
	private boolean editable;
	private String color_btn_activo;
	private String color_btn_desactivo;
	public String COLOR_ACTIVO="background:#3BA420;";
	public String COLOR_DESACTIVO="background:#DA0613;";
	public String COLOR_TRANSPARENT="background:transparent;";
	private boolean estado_activo;
	private boolean estado_desactivo;
	private boolean seleccionado;
	//=======================================
	public int getnDestinoCod() {
		return nDestinoCod;
	}
	public void setnDestinoCod(int nDestinoCod) {
		this.nDestinoCod = nDestinoCod;
	}
	public String getcDestino() {
		return cDestino;
	}
	public void setcDestino(String cDestino) {
		this.cDestino = cDestino;
	}
	public boolean isbEstado() {
		return bEstado;
	}
	public void setbEstado(boolean bEstado) {
		this.bEstado = bEstado;
	}
	public String getcLatitud() {
		return cLatitud;
	}
	public void setcLatitud(String cLatitud) {
		this.cLatitud = cLatitud;
	}
	public String getcLongitud() {
		return cLongitud;
	}
	public void setcLongitud(String cLongitud) {
		this.cLongitud = cLongitud;
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	public String getColor_btn_activo() {
		return color_btn_activo;
	}
	public void setColor_btn_activo(String color_btn_activo) {
		this.color_btn_activo = color_btn_activo;
	}
	public String getColor_btn_desactivo() {
		return color_btn_desactivo;
	}
	public void setColor_btn_desactivo(String color_btn_desactivo) {
		this.color_btn_desactivo = color_btn_desactivo;
	}
	public boolean isEstado_activo() {
		return estado_activo;
	}
	public void setEstado_activo(boolean estado_activo) {
		this.estado_activo = estado_activo;
	}
	public boolean isEstado_desactivo() {
		return estado_desactivo;
	}
	public void setEstado_desactivo(boolean estado_desactivo) {
		this.estado_desactivo = estado_desactivo;
	}
	public boolean isSeleccionado() {
		return seleccionado;
	}
	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}
	//=====================================
	public CDestinoMovil() {
		// TODO Auto-generated constructor stub
		this.cDestino="";
		this.cLatitud="";
		this.cLongitud="";
		bEstado=false;
		seleccionado=false;
	}
	public CDestinoMovil(int nDestinoCod, String cDestino, boolean bEstado, String cLatitud, String cLongitud) {
		this.nDestinoCod = nDestinoCod;
		this.cDestino = cDestino;
		this.bEstado = bEstado;
		this.cLatitud = cLatitud;
		this.cLongitud = cLongitud;
		this.editable=false;
		this.estado_activo=bEstado;
		this.estado_desactivo=!bEstado;
		this.seleccionado=false;
		darColor_estado();
	}
	public void darColor_estado()
	{
		if(bEstado)
		{
			color_btn_activo=COLOR_ACTIVO;
			color_btn_desactivo=COLOR_TRANSPARENT;
		}
		else{
			color_btn_activo=COLOR_TRANSPARENT;
			color_btn_desactivo=COLOR_DESACTIVO;
		}
	}
}
