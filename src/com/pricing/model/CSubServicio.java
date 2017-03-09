package com.pricing.model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

import com.pricing.dao.CServicioDAO;

public class CSubServicio 
{
	//====================
	private DecimalFormat df;
	private DecimalFormatSymbols simbolos;
	//====================
	private int nSubServicioCod;// int,
	private int nServicioCod;// int,
	private String cSubServicioIndioma1;// varchar(200),                   --nombre del servicio en el idioma 1
	private String cSubServicioIndioma2;// varchar(200),                   --nombre del servicio en el idioma 2
	private String cSubServicioIndioma3;// varchar(200),                   --nombre del servicio en el idioma 3
	private String cSubServicioIndioma4;// varchar(200),                   --nombre del servicio en el idioma 4
	private String cSubServicioIndioma5;// varchar(200),                   --nombre del servicio en el idioma 5
	private String cDescripcionIdioma1;// text,                         --descripcion de lo que ofrece el servicio en el idioma 1
	private String cDescripcionIdioma2;// text,                         --descripcion de lo que ofrece el servicio en el idioma 2
	private String cDescripcionIdioma3;// text,                         --descripcion de lo que ofrece el servicio en el idioma 3
	private String cDescripcionIdioma4;// text,                         --descripcion de lo que ofrece el servicio en el idioma 4
	private String cDescripcionIdioma5;// text,                         --descripcion de lo que ofrece el servicio en el idioma 5
	private String cUrlImg;// varchar(200),
	private String cLink;// text,
	private Number nPrecioServicio;// decimal(10,2),
	private String nPrecioServicio_text;
	private boolean bEstado;// boolean,
	private String tituloSubServicio;
	private String cDescripcion;
	private boolean visibleEspanol;
	private boolean visibleIngles;
	private boolean visiblePortugues;
	private boolean editable;
	private String cNombreServicio;
	private String color_btn_activo;
	private String color_btn_desactivo;
	public String COLOR_ACTIVO="background:#3BA420;";
	public String COLOR_DESACTIVO="background:#DA0613;";
	public String COLOR_TRANSPARENT="background:transparent;";
	private boolean estado_activo;
	private boolean estado_desactivo;
	private ArrayList<CServicio> listaServicios;
	private CServicioDAO servicioDao;
	//============================
	
	public int getnSubServicioCod() {
		return nSubServicioCod;
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
	public String getCOLOR_ACTIVO() {
		return COLOR_ACTIVO;
	}
	public void setCOLOR_ACTIVO(String cOLOR_ACTIVO) {
		COLOR_ACTIVO = cOLOR_ACTIVO;
	}
	public String getCOLOR_DESACTIVO() {
		return COLOR_DESACTIVO;
	}
	public void setCOLOR_DESACTIVO(String cOLOR_DESACTIVO) {
		COLOR_DESACTIVO = cOLOR_DESACTIVO;
	}
	public String getCOLOR_TRANSPARENT() {
		return COLOR_TRANSPARENT;
	}
	public void setCOLOR_TRANSPARENT(String cOLOR_TRANSPARENT) {
		COLOR_TRANSPARENT = cOLOR_TRANSPARENT;
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
	public void setnSubServicioCod(int nSubServicioCod) {
		this.nSubServicioCod = nSubServicioCod;
	}
	public int getnServicioCod() {
		return nServicioCod;
	}
	public void setnServicioCod(int nServicioCod) {
		this.nServicioCod = nServicioCod;
	}
	public String getcSubServicioIndioma1() {
		return cSubServicioIndioma1;
	}
	public void setcSubServicioIndioma1(String cSubServicioIndioma1) {
		this.cSubServicioIndioma1 = cSubServicioIndioma1;
	}
	public String getcSubServicioIndioma2() {
		return cSubServicioIndioma2;
	}
	public void setcSubServicioIndioma2(String cSubServicioIndioma2) {
		this.cSubServicioIndioma2 = cSubServicioIndioma2;
	}
	public String getcSubServicioIndioma3() {
		return cSubServicioIndioma3;
	}
	public void setcSubServicioIndioma3(String cSubServicioIndioma3) {
		this.cSubServicioIndioma3 = cSubServicioIndioma3;
	}
	public String getcSubServicioIndioma4() {
		return cSubServicioIndioma4;
	}
	public void setcSubServicioIndioma4(String cSubServicioIndioma4) {
		this.cSubServicioIndioma4 = cSubServicioIndioma4;
	}
	public String getcSubServicioIndioma5() {
		return cSubServicioIndioma5;
	}
	public void setcSubServicioIndioma5(String cSubServicioIndioma5) {
		this.cSubServicioIndioma5 = cSubServicioIndioma5;
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
	public Number getnPrecioServicio() {
		return nPrecioServicio;
	}
	public void setnPrecioServicio(Number nPrecioServicio) {
		this.nPrecioServicio = nPrecioServicio;
	}
	public String getTituloSubServicio() {
		return tituloSubServicio;
	}
	public void setTituloSubServicio(String tituloSubServicio) {
		this.tituloSubServicio = tituloSubServicio;
	}
	public String getcDescripcion() {
		return cDescripcion;
	}
	public void setcDescripcion(String cDescripcion) {
		this.cDescripcion = cDescripcion;
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	
	public String getnPrecioServicio_text() {
		return nPrecioServicio_text;
	}
	public void setnPrecioServicio_text(String nPrecioServicio_text) {
		this.nPrecioServicio_text = nPrecioServicio_text;
	}
	
	public String getcNombreServicio() {
		return cNombreServicio;
	}
	public void setcNombreServicio(String cNombreServicio) {
		this.cNombreServicio = cNombreServicio;
	}
	public ArrayList<CServicio> getListaServicios() {
		return listaServicios;
	}
	public void setListaServicios(ArrayList<CServicio> listaServicios) {
		this.listaServicios = listaServicios;
	}
	//============================
	public CSubServicio() {
		// TODO Auto-generated constructor stub
		cDescripcion="";
		tituloSubServicio="";
		this.cSubServicioIndioma1="";
		this.cSubServicioIndioma2="";
		this.cSubServicioIndioma3="";
		this.cSubServicioIndioma4="";
		this.cSubServicioIndioma5="";
		this.cDescripcionIdioma1 ="";
		this.cDescripcionIdioma2 ="";
		this.cDescripcionIdioma3 ="";
		this.cDescripcionIdioma4 ="";
		this.cDescripcionIdioma5 ="";
		this.cLink="";
		this.cUrlImg="";
		this.nPrecioServicio=0;
		this.nServicioCod=0;
		this.nPrecioServicio_text="0.00";
	}
	public CSubServicio(String cSubServicioIndioma1)
	{
		this.cSubServicioIndioma1=cSubServicioIndioma1;
	}
	public CSubServicio(String csubServicioIndioma1,Number nPrecioServicio){
		this.cSubServicioIndioma1=csubServicioIndioma1;
		this.nPrecioServicio=nPrecioServicio;
	}
	public CSubServicio(String csubServicioIndioma1,Number nPrecioServicio,String cservicioIndioma1)
	{
		this.cSubServicioIndioma1=csubServicioIndioma1;
		this.nPrecioServicio=nPrecioServicio;
		this.cNombreServicio=cservicioIndioma1;
	}
	public CSubServicio(int nSubServicioCod, int nServicioCod,
			String cSubServicioIndioma1, String cSubServicioIndioma2,
			String cSubServicioIndioma3, String cSubServicioIndioma4,
			String cSubServicioIndioma5, String cDescripcionIdioma1,
			String cDescripcionIdioma2, String cDescripcionIdioma3,
			String cDescripcionIdioma4, String cDescripcionIdioma5,
			String cUrlImg, String cLink, Number nPrecioServicio,
			boolean bEstado) {
		/*====================*/
		simbolos= new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		df=new DecimalFormat("########0.00",simbolos);
		/*====================*/
		this.nSubServicioCod = nSubServicioCod;
		this.nServicioCod = nServicioCod;
		this.cSubServicioIndioma1 = cSubServicioIndioma1;
		this.cSubServicioIndioma2 = cSubServicioIndioma2;
		this.cSubServicioIndioma3 = cSubServicioIndioma3;
		this.cSubServicioIndioma4 = cSubServicioIndioma4;
		this.cSubServicioIndioma5 = cSubServicioIndioma5;
		this.cDescripcionIdioma1 = cDescripcionIdioma1;
		this.cDescripcionIdioma2 = cDescripcionIdioma2;
		this.cDescripcionIdioma3 = cDescripcionIdioma3;
		this.cDescripcionIdioma4 = cDescripcionIdioma4;
		this.cDescripcionIdioma5 = cDescripcionIdioma5;
		this.cUrlImg = cUrlImg;
		this.cLink = cLink;
		this.nPrecioServicio = nPrecioServicio;
		this.bEstado = bEstado;
		/*********************/
		/**Recuperamos los servicios que tienen como restriccion subservicios**/
		this.listaServicios=new ArrayList<CServicio>();
		this.servicioDao=new CServicioDAO();
		servicioDao.asignarListaServicios(servicioDao.recuperarServiciosconSubServiciosBD());
		setListaServicios(servicioDao.getListaServicios());
		/**********************/
		this.cNombreServicio=recuperarNombreServicio(nServicioCod);
		this.estado_activo=bEstado;
		this.estado_desactivo=!bEstado;
		this.editable=false;
		this.visibleEspanol=true;
		this.visibleIngles=false;
		this.visiblePortugues=false;
		this.nPrecioServicio_text=df.format(nPrecioServicio.doubleValue());
		darColor_estado_SubServicio();
	}
	public void darColor_estado_SubServicio()
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
	public String recuperarNombreServicio(int nServicioCod)
	{
		String nombreServicio="";
		for(CServicio s:listaServicios)
		{
			if(s.getnServicioCod()==nServicioCod)
			{
				nombreServicio=s.getcServicioIndioma1();
				break;
			}
		}
		return nombreServicio;
	}
}
