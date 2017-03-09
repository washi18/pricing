package com.pricing.model;

public class CPaqueteCategoriaHotel 
{
	private String codPaqueteCategoriaH;// varchar(10),                --codigo de la relacion entre el paquete y la categoria del hotel
	private String cPaqueteCod;// varchar(10),                         --codigo del paquete seleccionado
	private int categoriaHotelCod;// int,                           --codigo de la catgeoria de hotel incluidos en el paquete
	//=========================
	public String getCodPaqueteCategoriaH() {
		return codPaqueteCategoriaH;
	}
	public void setCodPaqueteCategoriaH(String codPaqueteCategoriaH) {
		this.codPaqueteCategoriaH = codPaqueteCategoriaH;
	}
	public String getcPaqueteCod() {
		return cPaqueteCod;
	}
	public void setcPaqueteCod(String cPaqueteCod) {
		this.cPaqueteCod = cPaqueteCod;
	}
	public int getCategoriaHotelCod() {
		return categoriaHotelCod;
	}
	public void setCategoriaHotelCod(int categoriaHotelCod) {
		this.categoriaHotelCod = categoriaHotelCod;
	}
	//================================
	public CPaqueteCategoriaHotel() {
		// TODO Auto-generated constructor stub
	}
	public CPaqueteCategoriaHotel(String codPaqueteCategoriaH,
			String cPaqueteCod, int categoriaHotelCod) {
		this.codPaqueteCategoriaH = codPaqueteCategoriaH;
		this.cPaqueteCod = cPaqueteCod;
		this.categoriaHotelCod = categoriaHotelCod;
	}
	
}
