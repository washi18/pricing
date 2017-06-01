package com.android.model;

import java.util.ArrayList;

import com.android.dao.CMenuDAO;
import com.android.dao.CSubMenuDAO;

public class CMenu {
	private int cMenuCod;// int,
	private String cNombreIdioma1;// varchar(200),
	private String cNombreIdioma2;// varchar(200),
	private String cNombreIdioma3;// varchar(200),
	private String cNombreIdioma4;// varchar(200),
	private String cNombreIdioma5;// varchar(200),
	private String cImagenIcono;// varchar(100),
	private String cImagenFondo;// varchar(100),
	private boolean estado;// boolean,
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
	private ArrayList<CSubMenu> listaSubMenus;
	private boolean visibleSubMenu;
	private boolean update;
	private boolean vistaMobil;
	//===============================
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
	public String getcImagenIcono() {
		return cImagenIcono;
	}
	public void setcImagenIcono(String cImagenIcono) {
		this.cImagenIcono = cImagenIcono;
	}
	public String getcImagenFondo() {
		return cImagenFondo;
	}
	public void setcImagenFondo(String cImagenFondo) {
		this.cImagenFondo = cImagenFondo;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
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
	public ArrayList<CSubMenu> getListaSubMenus() {
		return listaSubMenus;
	}
	public void setListaSubMenus(ArrayList<CSubMenu> listaSubMenus) {
		this.listaSubMenus = listaSubMenus;
	}
	public boolean isVisibleSubMenu() {
		return visibleSubMenu;
	}
	public void setVisibleSubMenu(boolean visibleSubMenu) {
		this.visibleSubMenu = visibleSubMenu;
	}
	public boolean isUpdate() {
		return update;
	}
	public void setUpdate(boolean update) {
		this.update = update;
	}
	public boolean isVistaMobil() {
		return vistaMobil;
	}
	public void setVistaMobil(boolean vistaMobil) {
		this.vistaMobil = vistaMobil;
	}
	//========================
	public CMenu() {
		// TODO Auto-generated constructor stub
		this.cNombreIdioma1="";
		this.cNombreIdioma2="";
		this.cNombreIdioma3="";
		this.cNombreIdioma4="";
		this.cNombreIdioma5="";
		this.cImagenIcono="";
		this.cImagenFondo="";
		this.estado_activo=true;
		this.estado=true;
		this.estado_desactivo=false;
		this.update=false;
		this.vistaMobil=false;
	}
	public CMenu(int cMenuCod, String cNombreIdioma1, String cNombreIdioma2, String cNombreIdioma3,
			String cNombreIdioma4, String cNombreIdioma5, String cImagenIcono, String cImagenFondo, boolean estado) {
		this.cMenuCod = cMenuCod;
		this.cNombreIdioma1 = cNombreIdioma1;
		this.cNombreIdioma2 = cNombreIdioma2;
		this.cNombreIdioma3 = cNombreIdioma3;
		this.cNombreIdioma4 = cNombreIdioma4;
		this.cNombreIdioma5 = cNombreIdioma5;
		this.cImagenIcono = cImagenIcono;
		this.cImagenFondo = cImagenFondo;
		this.estado = estado;
		this.estado_activo=estado;
		this.estado_desactivo=!estado;
		this.editable=false;
		this.visibleEspanol=true;
		this.visibleIngles=false;
		this.visiblePortugues=false;
		//===========================
		this.visibleSubMenu=false;
		this.update=true;
		this.vistaMobil=false;
		recuperarListaSubMenus(cMenuCod);
		darColor_estado();
	}
	public void recuperarListaSubMenus(int cMenuCod)
	{
		listaSubMenus=new ArrayList<CSubMenu>();
		CMenuDAO menuDao=new CMenuDAO();
		menuDao.asignarListaSubMenuBD_Menu(menuDao.recuperarListaSubMenuBD_Menu(cMenuCod));
		setListaSubMenus(menuDao.getListaSubMenu());
	}
	public void darColor_estado()
	{
		if(estado)
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
