package com.android.model;

import java.util.ArrayList;

import com.android.dao.CElementosDAO;
import com.android.dao.CItemsDAO;

public class CElementos {
	private int cElementosCod;// integer,
	private int cItemsCod;// integer,
	private int cSubMenuCod;// integer,
	private String cNombre1Idioma1;// varchar(200),
	private String cNombre1Idioma2;// varchar(200),
	private String cNombre1Idioma3;// varchar(200),
	private String cNombre1Idioma4;// varchar(200),
	private String cNombre1Idioma5;// varchar(200),
	private String cNombre2Idioma1;// varchar(200),
	private String cNombre2Idioma2;// varchar(200),
	private String cNombre2Idioma3;// varchar(200),
	private String cNombre2Idioma4;// varchar(200),
	private String cNombre2Idioma5;// varchar(200),
	private String cNombre3Idioma1;// varchar(200),
	private String cNombre3Idioma2;// varchar(200),
	private String cNombre3Idioma3;// varchar(200),
	private String cNombre3Idioma4;// varchar(200),
	private String cNombre3Idioma5;// varchar(200),
	private String cImagen1;// varchar(100),
	private String cImagen2;// varchar(100),
	private String cImagen3;// varchar(100),
	private String cDescripcionIdioma1;// text,
	private String cDescripcionIdioma2;// text,
	private String cDescripcionIdioma3;// text,
	private String cDescripcionIdioma4;// text,
	private String cDescripcionIdioma5;// text,
	private String cDirigidoIdioma1;// text,
	private String cDirigidoIdioma2;// text,
	private String cDirigidoIdioma3;// text,
	private String cDirigidoIdioma4;// text,
	private String cDirigidoIdioma5;// text,
	private ArrayList<CDestinoMovil> listaDestinosMovil;
	private ArrayList<CDatosGenerales> listaDatosGenerales;
	private String nameItem;
	private String nameSubMenu;
	private boolean update;
	private boolean vistaMobil;
	private boolean modoSubMenu;
	private boolean modoItem;
	private boolean abrirEditorDescripcion;
	private boolean abrirEditorDirigido;
	//======================================
	public int getcElementosCod() {
		return cElementosCod;
	}
	public void setcElementosCod(int cElementosCod) {
		this.cElementosCod = cElementosCod;
	}
	public int getcItemsCod() {
		return cItemsCod;
	}
	public void setcItemsCod(int cItemsCod) {
		this.cItemsCod = cItemsCod;
	}
	public int getcSubMenuCod() {
		return cSubMenuCod;
	}
	public void setcSubMenuCod(int cSubMenuCod) {
		this.cSubMenuCod = cSubMenuCod;
	}
	public String getcNombre1Idioma1() {
		return cNombre1Idioma1;
	}
	public void setcNombre1Idioma1(String cNombre1Idioma1) {
		this.cNombre1Idioma1 = cNombre1Idioma1;
	}
	public String getcNombre1Idioma2() {
		return cNombre1Idioma2;
	}
	public void setcNombre1Idioma2(String cNombre1Idioma2) {
		this.cNombre1Idioma2 = cNombre1Idioma2;
	}
	public String getcNombre1Idioma3() {
		return cNombre1Idioma3;
	}
	public void setcNombre1Idioma3(String cNombre1Idioma3) {
		this.cNombre1Idioma3 = cNombre1Idioma3;
	}
	public String getcNombre1Idioma4() {
		return cNombre1Idioma4;
	}
	public void setcNombre1Idioma4(String cNombre1Idioma4) {
		this.cNombre1Idioma4 = cNombre1Idioma4;
	}
	public String getcNombre1Idioma5() {
		return cNombre1Idioma5;
	}
	public void setcNombre1Idioma5(String cNombre1Idioma5) {
		this.cNombre1Idioma5 = cNombre1Idioma5;
	}
	public String getcNombre2Idioma1() {
		return cNombre2Idioma1;
	}
	public void setcNombre2Idioma1(String cNombre2Idioma1) {
		this.cNombre2Idioma1 = cNombre2Idioma1;
	}
	public String getcNombre2Idioma2() {
		return cNombre2Idioma2;
	}
	public void setcNombre2Idioma2(String cNombre2Idioma2) {
		this.cNombre2Idioma2 = cNombre2Idioma2;
	}
	public String getcNombre2Idioma3() {
		return cNombre2Idioma3;
	}
	public void setcNombre2Idioma3(String cNombre2Idioma3) {
		this.cNombre2Idioma3 = cNombre2Idioma3;
	}
	public String getcNombre2Idioma4() {
		return cNombre2Idioma4;
	}
	public void setcNombre2Idioma4(String cNombre2Idioma4) {
		this.cNombre2Idioma4 = cNombre2Idioma4;
	}
	public String getcNombre2Idioma5() {
		return cNombre2Idioma5;
	}
	public void setcNombre2Idioma5(String cNombre2Idioma5) {
		this.cNombre2Idioma5 = cNombre2Idioma5;
	}
	public String getcNombre3Idioma1() {
		return cNombre3Idioma1;
	}
	public void setcNombre3Idioma1(String cNombre3Idioma1) {
		this.cNombre3Idioma1 = cNombre3Idioma1;
	}
	public String getcNombre3Idioma2() {
		return cNombre3Idioma2;
	}
	public void setcNombre3Idioma2(String cNombre3Idioma2) {
		this.cNombre3Idioma2 = cNombre3Idioma2;
	}
	public String getcNombre3Idioma3() {
		return cNombre3Idioma3;
	}
	public void setcNombre3Idioma3(String cNombre3Idioma3) {
		this.cNombre3Idioma3 = cNombre3Idioma3;
	}
	public String getcNombre3Idioma4() {
		return cNombre3Idioma4;
	}
	public void setcNombre3Idioma4(String cNombre3Idioma4) {
		this.cNombre3Idioma4 = cNombre3Idioma4;
	}
	public String getcNombre3Idioma5() {
		return cNombre3Idioma5;
	}
	public void setcNombre3Idioma5(String cNombre3Idioma5) {
		this.cNombre3Idioma5 = cNombre3Idioma5;
	}
	public String getcImagen1() {
		return cImagen1;
	}
	public void setcImagen1(String cImagen1) {
		this.cImagen1 = cImagen1;
	}
	public String getcImagen2() {
		return cImagen2;
	}
	public void setcImagen2(String cImagen2) {
		this.cImagen2 = cImagen2;
	}
	public String getcImagen3() {
		return cImagen3;
	}
	public void setcImagen3(String cImagen3) {
		this.cImagen3 = cImagen3;
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
	public String getcDirigidoIdioma1() {
		return cDirigidoIdioma1;
	}
	public void setcDirigidoIdioma1(String cDirigidoIdioma1) {
		this.cDirigidoIdioma1 = cDirigidoIdioma1;
	}
	public String getcDirigidoIdioma2() {
		return cDirigidoIdioma2;
	}
	public void setcDirigidoIdioma2(String cDirigidoIdioma2) {
		this.cDirigidoIdioma2 = cDirigidoIdioma2;
	}
	public String getcDirigidoIdioma3() {
		return cDirigidoIdioma3;
	}
	public void setcDirigidoIdioma3(String cDirigidoIdioma3) {
		this.cDirigidoIdioma3 = cDirigidoIdioma3;
	}
	public String getcDirigidoIdioma4() {
		return cDirigidoIdioma4;
	}
	public void setcDirigidoIdioma4(String cDirigidoIdioma4) {
		this.cDirigidoIdioma4 = cDirigidoIdioma4;
	}
	public String getcDirigidoIdioma5() {
		return cDirigidoIdioma5;
	}
	public void setcDirigidoIdioma5(String cDirigidoIdioma5) {
		this.cDirigidoIdioma5 = cDirigidoIdioma5;
	}
	public String getNameItem() {
		return nameItem;
	}
	public void setNameItem(String nameItem) {
		this.nameItem = nameItem;
	}
	public String getNameSubMenu() {
		return nameSubMenu;
	}
	public void setNameSubMenu(String nameSubMenu) {
		this.nameSubMenu = nameSubMenu;
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
	public boolean isModoSubMenu() {
		return modoSubMenu;
	}
	public void setModoSubMenu(boolean modoSubMenu) {
		this.modoSubMenu = modoSubMenu;
	}
	public boolean isModoItem() {
		return modoItem;
	}
	public void setModoItem(boolean modoItem) {
		this.modoItem = modoItem;
	}
	public ArrayList<CDestinoMovil> getListaDestinosMovil() {
		return listaDestinosMovil;
	}
	public void setListaDestinosMovil(ArrayList<CDestinoMovil> listaDestinosMovil) {
		this.listaDestinosMovil = listaDestinosMovil;
	}
	public ArrayList<CDatosGenerales> getListaDatosGenerales() {
		return listaDatosGenerales;
	}
	public void setListaDatosGenerales(ArrayList<CDatosGenerales> listaDatosGenerales) {
		this.listaDatosGenerales = listaDatosGenerales;
	}
	public boolean isAbrirEditorDescripcion() {
		return abrirEditorDescripcion;
	}
	public void setAbrirEditorDescripcion(boolean abrirEditorDescripcion) {
		this.abrirEditorDescripcion = abrirEditorDescripcion;
	}
	public boolean isAbrirEditorDirigido() {
		return abrirEditorDirigido;
	}
	public void setAbrirEditorDirigido(boolean abrirEditorDirigido) {
		this.abrirEditorDirigido = abrirEditorDirigido;
	}
	//===========================================
	public CElementos() {
		// TODO Auto-generated constructor stub
		this.cItemsCod=0;// integer,
		this.cSubMenuCod=0;
		this.cNombre1Idioma1="";// varchar(200),
		this.cNombre1Idioma2="";// varchar(200),
		this.cNombre1Idioma3="";// varchar(200),
		this.cNombre1Idioma4="";// varchar(200),
		this.cNombre1Idioma5="";// varchar(200),
		this.cNombre2Idioma1="";// varchar(200),
		this.cNombre2Idioma2="";// varchar(200),
		this.cNombre2Idioma3="";// varchar(200),
		this.cNombre2Idioma4="";// varchar(200),
		this.cNombre2Idioma5="";// varchar(200),
		this.cNombre3Idioma1="";// varchar(200),
		this.cNombre3Idioma2="";// varchar(200),
		this.cNombre3Idioma3="";// varchar(200),
		this.cNombre3Idioma4="";// varchar(200),
		this.cNombre3Idioma5="";// varchar(200),
		this.cImagen1="";// varchar(100),
		this.cImagen2="";// varchar(100),
		this.cImagen3="";// varchar(100),
		this.cDescripcionIdioma1="";
		this.cDescripcionIdioma2="";
		this.cDescripcionIdioma3="";
		this.cDescripcionIdioma4="";
		this.cDescripcionIdioma5="";
		this.cDirigidoIdioma1="";// text,
		this.cDirigidoIdioma2="";// text,
		this.cDirigidoIdioma3="";// text,
		this.cDirigidoIdioma4="";// text,
		this.cDirigidoIdioma5="";
		this.update=false;
		this.vistaMobil=false;
		this.modoItem=false;
		this.modoSubMenu=false;
		this.abrirEditorDescripcion=false;
		this.abrirEditorDirigido=false;
	}
	public CElementos(int cElementosCod, int cItemsCod,int cSubMenuCod, String cNombre1Idioma1, String cNombre1Idioma2,
			String cNombre1Idioma3, String cNombre1Idioma4, String cNombre1Idioma5, String cNombre2Idioma1,
			String cNombre2Idioma2, String cNombre2Idioma3, String cNombre2Idioma4, String cNombre2Idioma5,
			String cNombre3Idioma1, String cNombre3Idioma2, String cNombre3Idioma3, String cNombre3Idioma4,
			String cNombre3Idioma5, String cImagen1, String cImagen2, String cImagen3, String cDescripcionIdioma1,
			String cDescripcionIdioma2,String cDescripcionIdioma3,String cDescripcionIdioma4,String cDescripcionIdioma5,
			String cDirigidoIdioma1,String cDirigidoIdioma2, String cDirigidoIdioma3, String cDirigidoIdioma4,
			String cDirigidoIdioma5) {
		this.cElementosCod = cElementosCod;
		this.cItemsCod = cItemsCod;
		this.cSubMenuCod = cSubMenuCod;
		this.cNombre1Idioma1 = cNombre1Idioma1;
		this.cNombre1Idioma2 = cNombre1Idioma2;
		this.cNombre1Idioma3 = cNombre1Idioma3;
		this.cNombre1Idioma4 = cNombre1Idioma4;
		this.cNombre1Idioma5 = cNombre1Idioma5;
		this.cNombre2Idioma1 = cNombre2Idioma1;
		this.cNombre2Idioma2 = cNombre2Idioma2;
		this.cNombre2Idioma3 = cNombre2Idioma3;
		this.cNombre2Idioma4 = cNombre2Idioma4;
		this.cNombre2Idioma5 = cNombre2Idioma5;
		this.cNombre3Idioma1 = cNombre3Idioma1;
		this.cNombre3Idioma2 = cNombre3Idioma2;
		this.cNombre3Idioma3 = cNombre3Idioma3;
		this.cNombre3Idioma4 = cNombre3Idioma4;
		this.cNombre3Idioma5 = cNombre3Idioma5;
		this.cImagen1 = cImagen1;
		this.cImagen2 = cImagen2;
		this.cImagen3 = cImagen3;
		this.cDescripcionIdioma1=cDescripcionIdioma1;
		this.cDescripcionIdioma2=cDescripcionIdioma2;
		this.cDescripcionIdioma3=cDescripcionIdioma3;
		this.cDescripcionIdioma4=cDescripcionIdioma4;
		this.cDescripcionIdioma5=cDescripcionIdioma5;
		this.cDirigidoIdioma1 = cDirigidoIdioma1;
		this.cDirigidoIdioma2 = cDirigidoIdioma2;
		this.cDirigidoIdioma3 = cDirigidoIdioma3;
		this.cDirigidoIdioma4 = cDirigidoIdioma4;
		this.cDirigidoIdioma5 = cDirigidoIdioma5;
		this.vistaMobil=false;
		//==========================
		this.update=true;
		this.abrirEditorDescripcion=false;
		this.abrirEditorDirigido=false;
		if(cItemsCod!=0)
		{
			obtenerNameItem(cItemsCod);
			this.modoItem=true;
			this.modoSubMenu=false;
		}
		else if(cSubMenuCod!=0)
		{
			obtenerNameSubMenu(cSubMenuCod);
			this.modoSubMenu=true;
			this.modoItem=false;
		}
		recuperarListaDestinosMovil(cElementosCod);
		recuperarListaDatosGenerales(cElementosCod);
	}
	public void obtenerNameItem(int cItemsCod)
	{
		CElementosDAO elementosDao=new CElementosDAO();
		elementosDao.asignarNameItem(elementosDao.recuperarNombreItem(cItemsCod));
		setNameItem(elementosDao.getNameItem());
	}
	public void obtenerNameSubMenu(int cSubMenuCod)
	{
		CElementosDAO elementosDao=new CElementosDAO();
		elementosDao.asignarNameSubMenu(elementosDao.recuperarNombreSubMenu(cSubMenuCod));
		setNameSubMenu(elementosDao.getNameSubMenu());
	}
	public void recuperarListaDestinosMovil(int cElementosCod)
	{
		listaDestinosMovil=new ArrayList<CDestinoMovil>();
		CElementosDAO elementoDao=new CElementosDAO();
		elementoDao.asignarListaDestinosElemento(elementoDao.recuperarListaDestinosBD_Elemento(cElementosCod));
		setListaDestinosMovil(elementoDao.getListaDestinosMovil());
	}
	public void recuperarListaDatosGenerales(int cElementosCod)
	{
		listaDatosGenerales=new ArrayList<CDatosGenerales>();
		CElementosDAO elementoDao=new CElementosDAO();
		elementoDao.asignarListaDatosGeneralesElemento(elementoDao.recuperarListaDatosGeneralesBD_Elemento(cElementosCod));
		setListaDatosGenerales(elementoDao.getListaDatosGenerales());
	}
}
