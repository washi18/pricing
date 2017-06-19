package com.pricing.model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class CActividad 
{
	//====================
	private DecimalFormat df;
	private DecimalFormatSymbols simbolos;
	//====================
	  private int nActividadCod;// int NOT NULL,
	  private String cActividadIdioma1;// varchar(200),
	  private String cActividadIdioma2;// varchar(200),
	  private String cActividadIdioma3;// varchar(200),
	  private String cActividadIdioma4;// varchar(200),
	  private String cActividadIdioma5;// varchar(200),
	  private String cDescripcionIdioma1;// text,
	  private String cDescripcionIdioma2;// text,
	  private String cDescripcionIdioma3;// text,
	  private String cDescripcionIdioma4;// text,
	  private String cDescripcionIdioma5;// text,
	  private String cUrlImg;// varchar(200),
	  private Number nPrecioActividad;// decimal(10,2),
	  private boolean bEstado;// boolean,
	  private String precioTotalActividad;
	  private String nombreActividad;
	  private String descripcionActividad;
	  private boolean visibleEspanol;
	  private boolean visibleIngles;
	  private boolean visiblePortugues;
	  private boolean editable;
	  private String nPrecioActividad_text;
	  private String color_btn_activo;
	  private String color_btn_desactivo;
	  public String COLOR_ACTIVO="background:#3BA420;";
	  public String COLOR_DESACTIVO="background:#DA0613;";
	  public String COLOR_TRANSPARENT="background:transparent;";
	  private boolean estado_activo;
	  private boolean estado_desactivo;
	  private boolean seleccionado;
	  private boolean bMostrarEnResumen;
	  private boolean comprado;
	  private boolean noComprado;
	  private int nroPersonasActividad;
	  private boolean mostrarInformacionActividad;
	  /************************/
	public int getnActividadCod() {
		return nActividadCod;
	}
	public void setnActividadCod(int nActividadCod) {
		this.nActividadCod = nActividadCod;
	}
	public String getcActividadIdioma1() {
		return cActividadIdioma1;
	}
	public void setcActividadIdioma1(String cActividadIdioma1) {
		this.cActividadIdioma1 = cActividadIdioma1;
	}
	public String getcActividadIdioma2() {
		return cActividadIdioma2;
	}
	public void setcActividadIdioma2(String cActividadIdioma2) {
		this.cActividadIdioma2 = cActividadIdioma2;
	}
	public String getcActividadIdioma3() {
		return cActividadIdioma3;
	}
	public void setcActividadIdioma3(String cActividadIdioma3) {
		this.cActividadIdioma3 = cActividadIdioma3;
	}
	public String getcActividadIdioma4() {
		return cActividadIdioma4;
	}
	public void setcActividadIdioma4(String cActividadIdioma4) {
		this.cActividadIdioma4 = cActividadIdioma4;
	}
	public String getcActividadIdioma5() {
		return cActividadIdioma5;
	}
	public void setcActividadIdioma5(String cActividadIdioma5) {
		this.cActividadIdioma5 = cActividadIdioma5;
	}
	public String getcDescripcionIdioma1() {
		return cDescripcionIdioma1;
	}
	public void setcDescripcionIdioma1(String cDescripcionIdioma1) {
		this.cDescripcionIdioma1 = cDescripcionIdioma1;
	}
	public String getcDescripcionIdioma2() {
		return cDescripcionIdioma2;
	}
	public void setcDescripcionIdioma2(String cDescripcionIdioma2) {
		this.cDescripcionIdioma2 = cDescripcionIdioma2;
	}
	public String getcDescripcionIdioma3() {
		return cDescripcionIdioma3;
	}
	public void setcDescripcionIdioma3(String cDescripcionIdioma3) {
		this.cDescripcionIdioma3 = cDescripcionIdioma3;
	}
	public String getcDescripcionIdioma4() {
		return cDescripcionIdioma4;
	}
	public void setcDescripcionIdioma4(String cDescripcionIdioma4) {
		this.cDescripcionIdioma4 = cDescripcionIdioma4;
	}
	public String getcDescripcionIdioma5() {
		return cDescripcionIdioma5;
	}
	public void setcDescripcionIdioma5(String cDescripcionIdioma5) {
		this.cDescripcionIdioma5 = cDescripcionIdioma5;
	}
	public String getcUrlImg() {
		return cUrlImg;
	}
	public void setcUrlImg(String cUrlImg) {
		this.cUrlImg = cUrlImg;
	}
	public Number getnPrecioActividad() {
		return nPrecioActividad;
	}
	public void setnPrecioActividad(Number nPrecioActividad) {
		this.nPrecioActividad = nPrecioActividad;
	}
	public boolean isbEstado() {
		return bEstado;
	}
	public void setbEstado(boolean bEstado) {
		this.bEstado = bEstado;
	}
	public boolean isVisibleEspanol() {
		return visibleEspanol;
	}
	public void setVisibleEspanol(boolean visibleEspanol) {
		this.visibleEspanol = visibleEspanol;
	}
	public boolean isVisibleIngles() {
		return visibleIngles;
	}
	public void setVisibleIngles(boolean visibleIngles) {
		this.visibleIngles = visibleIngles;
	}
	public boolean isVisiblePortugues() {
		return visiblePortugues;
	}
	public void setVisiblePortugues(boolean visiblePortugues) {
		this.visiblePortugues = visiblePortugues;
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	public String getnPrecioActividad_text() {
		return nPrecioActividad_text;
	}
	public void setnPrecioActividad_text(String nPrecioActividad_text) {
		this.nPrecioActividad_text = nPrecioActividad_text;
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
	public boolean isComprado() {
		return comprado;
	}
	public void setComprado(boolean comprado) {
		this.comprado = comprado;
	}
	public boolean isNoComprado() {
		return noComprado;
	}
	public void setNoComprado(boolean noComprado) {
		this.noComprado = noComprado;
	}
	public String getNombreActividad() {
		return nombreActividad;
	}
	public void setNombreActividad(String nombreActividad) {
		this.nombreActividad = nombreActividad;
	}
	public String getDescripcionActividad() {
		return descripcionActividad;
	}
	public void setDescripcionActividad(String descripcionActividad) {
		this.descripcionActividad = descripcionActividad;
	}
	public String getPrecioTotalActividad() {
		return precioTotalActividad;
	}
	public void setPrecioTotalActividad(String precioTotalActividad) {
		this.precioTotalActividad = precioTotalActividad;
	}
	public int getNroPersonasActividad() {
		return nroPersonasActividad;
	}
	public void setNroPersonasActividad(int nroPersonasActividad) {
		this.nroPersonasActividad = nroPersonasActividad;
	}
	public boolean isMostrarInformacionActividad() {
		return mostrarInformacionActividad;
	}
	public void setMostrarInformacionActividad(boolean mostrarInformacionActividad) {
		this.mostrarInformacionActividad = mostrarInformacionActividad;
	}
	public boolean isbMostrarEnResumen() {
		return bMostrarEnResumen;
	}
	public void setbMostrarEnResumen(boolean bMostrarEnResumen) {
		this.bMostrarEnResumen = bMostrarEnResumen;
	}
	/************************/
	public CActividad() {
		// TODO Auto-generated constructor stub
		this.cActividadIdioma1 ="";
		this.cActividadIdioma2 ="";
		this.cActividadIdioma3 ="";
		this.cActividadIdioma4 ="";
		this.cActividadIdioma5 = "";
		this.cDescripcionIdioma1 ="";
		this.cDescripcionIdioma2 = "";
		this.cDescripcionIdioma3 = "";
		this.cDescripcionIdioma4 = "";
		this.cDescripcionIdioma5 = "";
		this.cUrlImg ="";
		this.nPrecioActividad =0;
		this.bEstado =true;
		this.comprado=false;
		this.noComprado=true;
		//==========================
		this.nPrecioActividad_text="0.00";
	}
	
	public CActividad(String cActividadIdioma1,Number nPrecioActividad){
		this.cActividadIdioma1=cActividadIdioma1;
		this.nPrecioActividad=nPrecioActividad;
	}
	
	public CActividad(int nActividadCod, String cActividadIdioma1,
			String cActividadIdioma2, String cActividadIdioma3,
			String cActividadIdioma4, String cActividadIdioma5,
			String cDescripcionIdioma1, String cDescripcionIdioma2,
			String cDescripcionIdioma3, String cDescripcionIdioma4,
			String cDescripcionIdioma5, String cUrlImg,
			Number nPrecioActividad, boolean bEstado) {
		super();
		/*******************************/
		simbolos= new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		df=new DecimalFormat("########0.00",simbolos);
		/*******************************/
		this.nActividadCod = nActividadCod;
		this.cActividadIdioma1 = cActividadIdioma1;
		this.cActividadIdioma2 = cActividadIdioma2;
		this.cActividadIdioma3 = cActividadIdioma3;
		this.cActividadIdioma4 = cActividadIdioma4;
		this.cActividadIdioma5 = cActividadIdioma5;
		this.cDescripcionIdioma1 = cDescripcionIdioma1;
		this.cDescripcionIdioma2 = cDescripcionIdioma2;
		this.cDescripcionIdioma3 = cDescripcionIdioma3;
		this.cDescripcionIdioma4 = cDescripcionIdioma4;
		this.cDescripcionIdioma5 = cDescripcionIdioma5;
		this.cUrlImg = cUrlImg;
		this.nPrecioActividad = nPrecioActividad;
		this.bEstado = bEstado;
		/***************/
		this.estado_activo=bEstado;
		this.estado_desactivo=!bEstado;
		this.editable=false;
		this.bMostrarEnResumen=false;
		this.visibleEspanol=true;
		this.visibleIngles=false;
		this.visiblePortugues=false;
		this.nPrecioActividad_text=df.format(nPrecioActividad.doubleValue());
		this.comprado=false;
		this.noComprado=true;
		this.precioTotalActividad=df.format(0);
		this.nroPersonasActividad=0;
		this.mostrarInformacionActividad=false;
		/******************/
		darColor_estado_actividad();
	}
	public void darColor_estado_actividad()
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
