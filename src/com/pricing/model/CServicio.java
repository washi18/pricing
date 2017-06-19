package com.pricing.model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

public class CServicio 
{
	//====================
			private DecimalFormat df;
			private DecimalFormatSymbols simbolos;
	//====================
	private int nServicioCod;// int,
	private String cServicioIndioma1;// varchar(200),                   --nombre del servicio en el idioma 1
	private String cServicioIndioma2;// varchar(200),                   --nombre del servicio en el idioma 2
	private String cServicioIndioma3;// varchar(200),                   --nombre del servicio en el idioma 3
	private String cServicioIndioma4;// varchar(200),                   --nombre del servicio en el idioma 4
	private String cServicioIndioma5;// varchar(200),                   --nombre del servicio en el idioma 5
	private String cDescripcionIdioma1;// text,                         --descripcion de lo que ofrece el servicio en el idioma 1
	private String cDescripcionIdioma2;// text,                         --descripcion de lo que ofrece el servicio en el idioma 2
	private String cDescripcionIdioma3;// text,               --descripcion de lo que ofrece el servicio en el idioma 3
	private String cDescripcionIdioma4;// text,               --descripcion de lo que ofrece el servicio en el idioma 4
	private String cDescripcionIdioma5;// text,               --descripcion de lo que ofrece el servicio en el idioma 5
	private int cRestriccionYesNo;// int,			--Restriccion yes/no (0=no se toma restriccion,1=si se toma restriccion)
	private int cRestriccionNum;// int,			--Restriccion numerica (0=no se toma restriccion, n>=1 se toma restriccion y restriccion proporcional a n*nroPasajeros)
	private int cIncremento;// int,		--De cuanto en cuanto se puede llegar a la restriccion numerica
	private String cUrlImg;// varchar(200),
	private Number nPrecioServicio;// decimal(10,2),
	private boolean bEstado;// boolean,
	private String cLink;//text,
	private ArrayList<String[]> listaOpcionServicios;
	private String DescripcionVisible;//La descripcion que sera visible al usuario
	private String urlImageVisible;//Imagen que sera visible al usuario
	private String selectOpcion;
	private String opcionValue;
	private boolean mostrarDescripcion;
	private float cantidadServicio;
	private String precioTotalServicio;
	private String link;
	private String Servicio;
	private boolean visibleEspanol;
	private boolean visibleIngles;
	private boolean visiblePortugues;
	private boolean editable;
	private String nPrecioServicio_text;
	private boolean disabledConSubServicio;
	public String COLOR_DISABLED="background:gray;";
	public String COLOR_NO_DISABLED="background:white;";
	private String color_disabled;
	private boolean escogioRestriccion;
	private String nameRestriccion;
	private boolean selectResNumeric;
	private boolean selectResYesNo;
	private boolean selectResSubServ;
	private String color_btn_activo;
	private String color_btn_desactivo;
	public String COLOR_ACTIVO="background:#3BA420;";
	public String COLOR_DESACTIVO="background:#DA0613;";
	public String COLOR_TRANSPARENT="background:transparent;";
	private boolean estado_activo;
	private boolean estado_desactivo;
	private boolean seleccionado;
	private boolean bMostrarEnResumen;
	//=============================
	public int getnServicioCod() {
		return nServicioCod;
	}
	public void setnServicioCod(int nServicioCod) {
		this.nServicioCod = nServicioCod;
	}
	public String getcServicioIndioma1() {
		return cServicioIndioma1;
	}
	public void setcServicioIndioma1(String cServicioIndioma1) {
		this.cServicioIndioma1 = cServicioIndioma1;
	}
	public String getcServicioIndioma2() {
		return cServicioIndioma2;
	}
	public void setcServicioIndioma2(String cServicioIndioma2) {
		this.cServicioIndioma2 = cServicioIndioma2;
	}
	public String getcServicioIndioma3() {
		return cServicioIndioma3;
	}
	public void setcServicioIndioma3(String cServicioIndioma3) {
		this.cServicioIndioma3 = cServicioIndioma3;
	}
	public String getcServicioIndioma4() {
		return cServicioIndioma4;
	}
	public void setcServicioIndioma4(String cServicioIndioma4) {
		this.cServicioIndioma4 = cServicioIndioma4;
	}
	public String getcServicioIndioma5() {
		return cServicioIndioma5;
	}
	public void setcServicioIndioma5(String cServicioIndioma5) {
		this.cServicioIndioma5 = cServicioIndioma5;
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
	public int getcRestriccionYesNo() {
		return cRestriccionYesNo;
	}
	public void setcRestriccionYesNo(int cRestriccionYesNo) {
		this.cRestriccionYesNo = cRestriccionYesNo;
	}
	public int getcRestriccionNum() {
		return cRestriccionNum;
	}
	public void setcRestriccionNum(int cRestriccionNum) {
		this.cRestriccionNum = cRestriccionNum;
	}
	public int getcIncremento() {
		return cIncremento;
	}
	public void setcIncremento(int cIncremento) {
		this.cIncremento = cIncremento;
	}
	public Number getnPrecioServicio() {
		return nPrecioServicio;
	}
	public void setnPrecioServicio(Number nPrecioServicio) {
		this.nPrecioServicio = nPrecioServicio;
	}
	public String getcUrlImg() {
		return cUrlImg;
	}
	public void setcUrlImg(String cUrlImg) {
		this.cUrlImg = cUrlImg;
	}
	public boolean isbEstado() {
		return bEstado;
	}
	public void setbEstado(boolean bEstado) {
		this.bEstado = bEstado;
	}
	public String getcLink() {
		return cLink;
	}
	public void setcLink(String cLink) {
		this.cLink = cLink;
	}
	public ArrayList<String[]> getListaOpcionServicios() {
		return listaOpcionServicios;
	}
	public void setListaOpcionServicios(ArrayList<String[]> listaOpcionServicios) {
		this.listaOpcionServicios = listaOpcionServicios;
	}
	public String getDescripcionVisible() {
		return DescripcionVisible;
	}
	public void setDescripcionVisible(String descripcionVisible) {
		DescripcionVisible = descripcionVisible;
	}
	public String getUrlImageVisible() {
		return urlImageVisible;
	}
	public void setUrlImageVisible(String urlImageVisible) {
		this.urlImageVisible = urlImageVisible;
	}
	public boolean isMostrarDescripcion() {
		return mostrarDescripcion;
	}
	public void setMostrarDescripcion(boolean mostrarDescripcion) {
		this.mostrarDescripcion = mostrarDescripcion;
	}
	public String getSelectOpcion() {
		return selectOpcion;
	}
	public void setSelectOpcion(String selectOpcion) {
		this.selectOpcion = selectOpcion;
	}
	public float getCantidadServicio() {
		return cantidadServicio;
	}
	public void setCantidadServicio(float cantidadServicio) {
		this.cantidadServicio = cantidadServicio;
	}
	public String getOpcionValue() {
		return opcionValue;
	}
	public void setOpcionValue(String opcionValue) {
		this.opcionValue = opcionValue;
	}
	public String getPrecioTotalServicio() {
		return precioTotalServicio;
	}
	public void setPrecioTotalServicio(String precioTotalServicio) {
		this.precioTotalServicio = precioTotalServicio;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getServicio() {
		return Servicio;
	}
	public void setServicio(String servicio) {
		Servicio = servicio;
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
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
	public String getnPrecioServicio_text() {
		return nPrecioServicio_text;
	}
	public void setnPrecioServicio_text(String nPrecioServicio_text) {
		this.nPrecioServicio_text = nPrecioServicio_text;
	}
	public boolean isDisabledConSubServicio() {
		return disabledConSubServicio;
	}
	public void setDisabledConSubServicio(boolean disabledConSubServicio) {
		this.disabledConSubServicio = disabledConSubServicio;
	}
	public String getColor_disabled() {
		return color_disabled;
	}
	public void setColor_disabled(String color_disabled) {
		this.color_disabled = color_disabled;
	}
	public boolean isEscogioRestriccion() {
		return escogioRestriccion;
	}
	public void setEscogioRestriccion(boolean escogioRestriccion) {
		this.escogioRestriccion = escogioRestriccion;
	}
	public String getNameRestriccion() {
		return nameRestriccion;
	}
	public void setNameRestriccion(String nameRestriccion) {
		this.nameRestriccion = nameRestriccion;
	}
	public boolean isSelectResNumeric() {
		return selectResNumeric;
	}
	public void setSelectResNumeric(boolean selectResNumeric) {
		this.selectResNumeric = selectResNumeric;
	}
	public boolean isSelectResYesNo() {
		return selectResYesNo;
	}
	public void setSelectResYesNo(boolean selectResYesNo) {
		this.selectResYesNo = selectResYesNo;
	}
	public boolean isSelectResSubServ() {
		return selectResSubServ;
	}
	public void setSelectResSubServ(boolean selectResSubServ) {
		this.selectResSubServ = selectResSubServ;
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
	public boolean isbMostrarEnResumen() {
		return bMostrarEnResumen;
	}
	public void setbMostrarEnResumen(boolean bMostrarEnResumen) {
		this.bMostrarEnResumen = bMostrarEnResumen;
	}
	//========================================
	public CServicio(String cServicioIndioma1)
	{
		this.cServicioIndioma1=cServicioIndioma1;
	}
	
	public CServicio(String cServicioIndioma1,Number precioServicio)
	{
		this.cServicioIndioma1=cServicioIndioma1;
		this.nPrecioServicio=precioServicio;
	}
	public CServicio() {
		// TODO Auto-generated constructor stub
		this.listaOpcionServicios=new ArrayList<String[]>();
		Servicio="";
		this.cServicioIndioma1 ="";
		this.cServicioIndioma2 ="";
		this.cServicioIndioma3 ="";
		this.cServicioIndioma4 ="";
		this.cServicioIndioma5 = "";
		this.cDescripcionIdioma1 ="";
		this.cDescripcionIdioma2 = "";
		this.cDescripcionIdioma3 = "";
		this.cDescripcionIdioma4 = "";
		this.cDescripcionIdioma5 = "";
		this.cUrlImg ="";
		this.nPrecioServicio =0;
		this.bEstado =true;
		this.cLink="";
		//==========================
		this.nPrecioServicio_text="0.00";
		this.color_disabled=COLOR_NO_DISABLED;
		this.disabledConSubServicio=false;
		this.escogioRestriccion=false;
		this.selectResNumeric=false;
		this.selectResYesNo=false;
		this.selectResSubServ=false;
	}
	public CServicio(int nServicioCod, String cServicioIndioma1,
			String cServicioIndioma2, String cServicioIndioma3,
			String cServicioIndioma4, String cServicioIndioma5,
			String cDescripcionIdioma1, String cDescripcionIdioma2,
			String cDescripcionIdioma3, String cDescripcionIdioma4,
			String cDescripcionIdioma5, int cRestriccionYesNo,
			int cRestriccionNum, int cIncremento, String cUrlImg,
			Number nPrecioServicio, boolean bEstado,String cLink) {
		/*******************************/
		simbolos= new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		df=new DecimalFormat("########0.00",simbolos);
		/*******************************/
		this.nServicioCod = nServicioCod;
		this.cServicioIndioma1 = cServicioIndioma1;
		this.cServicioIndioma2 = cServicioIndioma2;
		this.cServicioIndioma3 = cServicioIndioma3;
		this.cServicioIndioma4 = cServicioIndioma4;
		this.cServicioIndioma5 = cServicioIndioma5;
		this.cDescripcionIdioma1 = cDescripcionIdioma1;
		this.cDescripcionIdioma2 = cDescripcionIdioma2;
		this.cDescripcionIdioma3 = cDescripcionIdioma3;
		this.cDescripcionIdioma4 = cDescripcionIdioma4;
		this.cDescripcionIdioma5 = cDescripcionIdioma5;
		this.cRestriccionYesNo = cRestriccionYesNo;
		this.cRestriccionNum = cRestriccionNum;
		this.nameRestriccion=asignarNombreRestriccion(cRestriccionYesNo,cRestriccionNum);
		this.cIncremento = cIncremento;
		this.cUrlImg = cUrlImg;
		this.nPrecioServicio = nPrecioServicio;
		this.bEstado = bEstado;
		this.cLink=cLink;
		/*********************/
		this.listaOpcionServicios=new ArrayList<String[]>();
		this.urlImageVisible="";
		this.DescripcionVisible="";
		this.mostrarDescripcion=false;
		this.selectOpcion="";
		this.opcionValue="0";
		this.cantidadServicio=0;
		this.precioTotalServicio=df.format(0);
		this.link=cLink;
		this.editable=false;
		this.visibleEspanol=true;
		this.visibleIngles=false;
		this.visiblePortugues=false;
		this.disabledConSubServicio=false;
		this.nPrecioServicio_text=df.format(nPrecioServicio.doubleValue());
		this.estado_activo=bEstado;
		this.estado_desactivo=!bEstado;
		this.seleccionado=false;
		this.bMostrarEnResumen=false;
		/******************/
		darColor_estado_servicio();
		activarRestriccion();
	}
	public String asignarNombreRestriccion(int cRestriccionYesNo,int cRestriccionNum)
	{
		String rest="";
		if(cRestriccionYesNo==1 && cRestriccionNum==0)
		{
			rest="RESTRICCION YES/NO";
		}else if(cRestriccionNum>0 && cRestriccionYesNo==0)
		{
			rest="RESTRICCION NUMERICA: "+cRestriccionNum+" POR PASAJERO";
		}else if(cRestriccionYesNo==0 && cRestriccionNum==0)
		{
			rest="SUB SERVICIO";
		}
		return rest;
	}
	public void darColor_estado_servicio()
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
	public void activarRestriccion()
	{	selectResNumeric=selectResYesNo=selectResSubServ=disabledConSubServicio=false;
		if(cRestriccionNum>0 && cRestriccionYesNo==0)
		{
			selectResNumeric=true;
			disabledConSubServicio=false;
		}
		else if(cRestriccionNum==0 && cRestriccionYesNo==1)
		{
			selectResYesNo=true;
			disabledConSubServicio=false;
		}
		else if(cRestriccionNum==0 && cRestriccionYesNo==0)
		{
			selectResSubServ=true;
			disabledConSubServicio=true;
		}
	}
}
