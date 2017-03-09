package com.pricing.model;

public class CCategoriaHotel 
{
	private int categoriaHotelCod;// int,                          --codigo de la categoria
	private String cCategoriaIdioma1;// varchar(200),                 --nombre de la categoria en el idioma 1
	private String cCategoriaIdioma2;// varchar(200),                 --nombre de la categoria en el idioma 2
	private String cCategoriaIdioma3;// varchar(200),                 --nombre de la categoria en el idioma 3
	private String cCategoriaIdioma4;// varchar(200),                 --nombre de la categoria en el idioma 4
	private String cCategoriaIdioma5;// varchar(200),                 --nombre de la categoria en el idioma 5
	//======================
	public int getCategoriaHotelCod() {
		return categoriaHotelCod;
	}
	public void setCategoriaHotelCod(int categoriaHotelCod) {
		this.categoriaHotelCod = categoriaHotelCod;
	}
	public String getcCategoriaIdioma1() {
		return cCategoriaIdioma1;
	}
	public void setcCategoriaIdioma1(String cCategoriaIdioma1) {
		this.cCategoriaIdioma1 = cCategoriaIdioma1;
	}
	public String getcCategoriaIdioma2() {
		return cCategoriaIdioma2;
	}
	public void setcCategoriaIdioma2(String cCategoriaIdioma2) {
		this.cCategoriaIdioma2 = cCategoriaIdioma2;
	}
	public String getcCategoriaIdioma3() {
		return cCategoriaIdioma3;
	}
	public void setcCategoriaIdioma3(String cCategoriaIdioma3) {
		this.cCategoriaIdioma3 = cCategoriaIdioma3;
	}
	public String getcCategoriaIdioma4() {
		return cCategoriaIdioma4;
	}
	public void setcCategoriaIdioma4(String cCategoriaIdioma4) {
		this.cCategoriaIdioma4 = cCategoriaIdioma4;
	}
	public String getcCategoriaIdioma5() {
		return cCategoriaIdioma5;
	}
	public void setcCategoriaIdioma5(String cCategoriaIdioma5) {
		this.cCategoriaIdioma5 = cCategoriaIdioma5;
	}
	//=======================================
	public CCategoriaHotel() {
		// TODO Auto-generated constructor stub
	}
	public CCategoriaHotel(int categoriaHotelCod, String cCategoriaIdioma1,
			String cCategoriaIdioma2, String cCategoriaIdioma3,
			String cCategoriaIdioma4, String cCategoriaIdioma5) {
		this.categoriaHotelCod = categoriaHotelCod;
		this.cCategoriaIdioma1 = cCategoriaIdioma1;
		this.cCategoriaIdioma2 = cCategoriaIdioma2;
		this.cCategoriaIdioma3 = cCategoriaIdioma3;
		this.cCategoriaIdioma4 = cCategoriaIdioma4;
		this.cCategoriaIdioma5 = cCategoriaIdioma5;
	}
	
}
