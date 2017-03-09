package com.pricing.model;

public class CEtiqueta
{
	private int codEtiqueta;// int default nextval('seq_etiqueta'),
	private String cIdioma1;// text,
	private String cIdioma2;// text,
	private String cIdioma3;// text,
	private String cIdioma4;// text,
	private String cIdioma5;// text,
	public String COLOR_SELECT="background:#e7ecf1;";
	public String COLOR_NO_SELECT="background:white;";
	private boolean editable;
	private String color;
	private boolean visibleEspanol;
	private boolean visibleIngles;
	private boolean visiblePortugues;
	//GETTER AND SETTER
	
	public int getCodEtiqueta() {
		return codEtiqueta;
	}
	public String getCOLOR_SELECT() {
		return COLOR_SELECT;
	}
	public void setCOLOR_SELECT(String cOLOR_SELECT) {
		COLOR_SELECT = cOLOR_SELECT;
	}
	public String getCOLOR_NO_SELECT() {
		return COLOR_NO_SELECT;
	}
	public void setCOLOR_NO_SELECT(String cOLOR_NO_SELECT) {
		COLOR_NO_SELECT = cOLOR_NO_SELECT;
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
	public void setCodEtiqueta(int codEtiqueta) {
		this.codEtiqueta = codEtiqueta;
	}
	public String getcIdioma1() {
		return cIdioma1;
	}
	public void setcIdioma1(String cIdioma1) {
		this.cIdioma1 = cIdioma1;
	}
	public String getcIdioma2() {
		return cIdioma2;
	}
	public void setcIdioma2(String cIdioma2) {
		this.cIdioma2 = cIdioma2;
	}
	public String getcIdioma3() {
		return cIdioma3;
	}
	public void setcIdioma3(String cIdioma3) {
		this.cIdioma3 = cIdioma3;
	}
	public String getcIdioma4() {
		return cIdioma4;
	}
	public void setcIdioma4(String cIdioma4) {
		this.cIdioma4 = cIdioma4;
	}
	public String getcIdioma5() {
		return cIdioma5;
	}
	public void setcIdioma5(String cIdioma5) {
		this.cIdioma5 = cIdioma5;
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	//===METODOS===
	public CEtiqueta()
	{
		this.cIdioma1 = "";
		this.cIdioma2 = "";
		this.cIdioma3 = "";
		this.cIdioma4 = "";
		this.cIdioma5 = "";
	}
	public CEtiqueta(int codEtiqueta, String cIdioma1, String cIdioma2,
			String cIdioma3, String cIdioma4, String cIdioma5) {
		this.codEtiqueta = codEtiqueta;
		this.cIdioma1 = cIdioma1;
		this.cIdioma2 = cIdioma2;
		this.cIdioma3 = cIdioma3;
		this.cIdioma4 = cIdioma4;
		this.cIdioma5 = cIdioma5;
		this.editable=false;
		this.color=COLOR_NO_SELECT;
		this.visibleEspanol=true;
		this.visibleIngles=false;
		this.visiblePortugues=false;
	}
	
}
