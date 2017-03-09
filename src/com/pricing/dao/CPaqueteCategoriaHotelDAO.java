package com.pricing.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pricing.model.CPaqueteCategoriaHotel;

public class CPaqueteCategoriaHotelDAO extends CConexion
{
	private CPaqueteCategoriaHotel oPaqueteCatHotel;
	private ArrayList<CPaqueteCategoriaHotel> listaPaqueteCatHotel;
	//==============================
	public CPaqueteCategoriaHotel getoPaqueteCatHotel() {
		return oPaqueteCatHotel;
	}
	public void setoPaqueteCatHotel(CPaqueteCategoriaHotel oPaqueteCatHotel) {
		this.oPaqueteCatHotel = oPaqueteCatHotel;
	}
	public ArrayList<CPaqueteCategoriaHotel> getListaPaqueteCatHotel() {
		return listaPaqueteCatHotel;
	}
	public void setListaPaqueteCatHotel(
			ArrayList<CPaqueteCategoriaHotel> listaPaqueteCatHotel) {
		this.listaPaqueteCatHotel = listaPaqueteCatHotel;
	}
	//==================================
	public CPaqueteCategoriaHotelDAO() {
		// TODO Auto-generated constructor stub
		super();
		this.oPaqueteCatHotel=new CPaqueteCategoriaHotel();
	}
	public CPaqueteCategoriaHotelDAO(CPaqueteCategoriaHotel pchotel)
	{
		super();
		this.oPaqueteCatHotel=pchotel;
	}
	//==================================
	public List recuperarPaqueteCategoriaHotelesBD(String codPaquete)
	{
		Object[] values={codPaquete};
		return getEjecutorSQL().ejecutarProcedimiento("Princing_sp_MostrarCategoriasPorPaquete", values);
	}
	public void asignarListaPaqueteCatHoteles(List lista)
	{
		listaPaqueteCatHotel=new ArrayList<CPaqueteCategoriaHotel>();
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaPaqueteCatHotel.add(new CPaqueteCategoriaHotel((String)row.get("codpaquetecategoriah"),
					(String)row.get("cpaquetecod"),(int)row.get("categoriahotelcod")));
		}
	}
}
