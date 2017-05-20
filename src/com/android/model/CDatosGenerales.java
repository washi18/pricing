package com.android.model;

import com.android.dao.CElementosDAO;

public class CDatosGenerales {
	private int cDatosGeneralesCod;// integer,
	private int cItemsCod;// integer,
	private String cTituloIdioma1;// varchar(200),
	private String cTituloIdioma2;// varchar(200),
	private String cTituloIdioma3;// varchar(200),
	private String cTituloIdioma4;// varchar(200),
	private String cTituloIdioma5;// varchar(200),
	private String cDescripcionIdioma1;// text,
	private String cDescripcionIdioma2;// text,
	private String cDescripcionIdioma3;// text,
	private String cDescripcionIdioma4;// text,
	private String cDescripcionIdioma5;// text,
	private String cImagen;// varchar(100),
	private String nameItem;
	private boolean update;
	//====================================
	public int getcDatosGeneralesCod() {
		return cDatosGeneralesCod;
	}
	public void setcDatosGeneralesCod(int cDatosGeneralesCod) {
		this.cDatosGeneralesCod = cDatosGeneralesCod;
	}
	public int getcItemsCod() {
		return cItemsCod;
	}
	public void setcItemsCod(int cItemsCod) {
		this.cItemsCod = cItemsCod;
	}
	public String getcTituloIdioma1() {
		return cTituloIdioma1;
	}
	public void setcTituloIdioma1(String cTituloIdioma1) {
		this.cTituloIdioma1 = cTituloIdioma1;
	}
	public String getcTituloIdioma2() {
		return cTituloIdioma2;
	}
	public void setcTituloIdioma2(String cTituloIdioma2) {
		this.cTituloIdioma2 = cTituloIdioma2;
	}
	public String getcTituloIdioma3() {
		return cTituloIdioma3;
	}
	public void setcTituloIdioma3(String cTituloIdioma3) {
		this.cTituloIdioma3 = cTituloIdioma3;
	}
	public String getcTituloIdioma4() {
		return cTituloIdioma4;
	}
	public void setcTituloIdioma4(String cTituloIdioma4) {
		this.cTituloIdioma4 = cTituloIdioma4;
	}
	public String getcTituloIdioma5() {
		return cTituloIdioma5;
	}
	public void setcTituloIdioma5(String cTituloIdioma5) {
		this.cTituloIdioma5 = cTituloIdioma5;
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
	public String getcImagen() {
		return cImagen;
	}
	public void setcImagen(String cImagen) {
		this.cImagen = cImagen;
	}
	public String getNameItem() {
		return nameItem;
	}
	public void setNameItem(String nameItem) {
		this.nameItem = nameItem;
	}
	public boolean isUpdate() {
		return update;
	}
	public void setUpdate(boolean update) {
		this.update = update;
	}
	//================================
	public CDatosGenerales() {
		// TODO Auto-generated constructor stub
		this.cItemsCod=0;// integer,
		this.cTituloIdioma1="";// varchar(200),
		this.cTituloIdioma2="";// varchar(200),
		this.cTituloIdioma3="";// varchar(200),
		this.cTituloIdioma4="";// varchar(200),
		this.cTituloIdioma5="";// varchar(200),
		this.cDescripcionIdioma1="";// text,
		this.cDescripcionIdioma2="";// text,
		this.cDescripcionIdioma3="";// text,
		this.cDescripcionIdioma4="";// text,
		this.cDescripcionIdioma5="";// text,
		this.cImagen="";
		this.update=false;
	}
	public CDatosGenerales(int cDatosGeneralesCod, int cItemsCod, String cTituloIdioma1, String cTituloIdioma2,
			String cTituloIdioma3, String cTituloIdioma4, String cTituloIdioma5, String cDescripcionIdioma1,
			String cDescripcionIdioma2, String cDescripcionIdioma3, String cDescripcionIdioma4,
			String cDescripcionIdioma5, String cImagen) {
		this.cDatosGeneralesCod = cDatosGeneralesCod;
		this.cItemsCod = cItemsCod;
		this.cTituloIdioma1 = cTituloIdioma1;
		this.cTituloIdioma2 = cTituloIdioma2;
		this.cTituloIdioma3 = cTituloIdioma3;
		this.cTituloIdioma4 = cTituloIdioma4;
		this.cTituloIdioma5 = cTituloIdioma5;
		this.cDescripcionIdioma1 = cDescripcionIdioma1;
		this.cDescripcionIdioma2 = cDescripcionIdioma2;
		this.cDescripcionIdioma3 = cDescripcionIdioma3;
		this.cDescripcionIdioma4 = cDescripcionIdioma4;
		this.cDescripcionIdioma5 = cDescripcionIdioma5;
		this.cImagen = cImagen;
		//=====================
		this.update=true;
		obtenerNameItem(cItemsCod);
	}
	public void obtenerNameItem(int cItemsCod)
	{
		CElementosDAO elementosDao=new CElementosDAO();
		elementosDao.asignarNameItem(elementosDao.recuperarNombreItem(cItemsCod));
		setNameItem(elementosDao.getNameItem());
	}
}
