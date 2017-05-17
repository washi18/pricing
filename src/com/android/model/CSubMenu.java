package com.android.model;

import java.util.ArrayList;

import com.android.dao.CSubMenuDAO;

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
	private boolean conElemento;
	private boolean sinElemento;
	private boolean estado_activo;
	private boolean estado_desactivo;
	private String color_btn_activo;
	private String color_btn_desactivo;
	public String COLOR_ACTIVO="background:#3BA420;";
	public String COLOR_DESACTIVO="background:#DA0613;";
	public String COLOR_TRANSPARENT="background:transparent;";
	private boolean editable;
	private boolean visibleEspanol;
	private boolean visibleIngles;
	private boolean visiblePortugues;
	private String nameMenu;
	private ArrayList<CItems> listaItems;
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
	public boolean isConElemento() {
		return conElemento;
	}
	public void setConElemento(boolean conElemento) {
		this.conElemento = conElemento;
	}
	public boolean isSinElemento() {
		return sinElemento;
	}
	public void setSinElemento(boolean sinElemento) {
		this.sinElemento = sinElemento;
	}
	public String getNameMenu() {
		return nameMenu;
	}
	public void setNameMenu(String nameMenu) {
		this.nameMenu = nameMenu;
	}
	public ArrayList<CItems> getListaItems() {
		return listaItems;
	}
	public void setListaItems(ArrayList<CItems> listaItems) {
		this.listaItems = listaItems;
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
		this.estado_activo=true;
		this.estado_desactivo=false;
		this.estado=true;
		this.elemento=true;
		this.conElemento=true;
		this.sinElemento=false;
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
		this.conElemento=elemento;
		this.sinElemento=!elemento;
		this.estado_activo=estado;
		this.estado_desactivo=!estado;
		this.editable=false;
		this.visibleEspanol=true;
		this.visibleIngles=false;
		this.visiblePortugues=false;
		//==========================
		obtenerNameMenu(cMenuCod);
		//===========================
		recuperarListaItems(cSubMenuCod);
	}
	public void obtenerNameMenu(int cMenuCod)
	{
		CSubMenuDAO subMenuDao=new CSubMenuDAO();
		subMenuDao.asignarNameMenu(subMenuDao.recuperarNombreMenu(cMenuCod));
		setNameMenu(subMenuDao.getNameMenu());
	}
	public void recuperarListaItems(int cSubMenuCod)
	{
		CSubMenuDAO submenuDao=new CSubMenuDAO();
		submenuDao.asignarListaItems_SubMenu(submenuDao.recuperarListaItemsBD_SubMenu(cSubMenuCod));
		setListaItems(submenuDao.getListaItems());
	}
}
