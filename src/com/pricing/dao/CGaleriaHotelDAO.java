package com.pricing.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pricing.model.CGaleriaHotel;

public class CGaleriaHotelDAO extends CConexion{
	private ArrayList<CGaleriaHotel> listaImagenesHotel;
	/********************/

	public ArrayList<CGaleriaHotel> getListaImagenesHotel() {
		return listaImagenesHotel;
	}

	public void setListaImagenesHotel(ArrayList<CGaleriaHotel> listaImagenesHotel) {
		this.listaImagenesHotel = listaImagenesHotel;
	}
	/*********************/
	public CGaleriaHotelDAO() {
		// TODO Auto-generated constructor stub
		super();
	}
	/**********************/
	public List recuperarImagenesHotelBD(int codHotel)
	{
		Object[] values={codHotel};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarImagenesHotel", values);
	}
	public void asignarListaImagenesHotel(List lista)
	{
		if(lista!=null)
		{
			listaImagenesHotel=new ArrayList<CGaleriaHotel>();
			for(int i=0;i<lista.size();i++)
			{
				Map row=(Map)lista.get(i);
				listaImagenesHotel.add(new CGaleriaHotel((int)row.get("ngaleriahotelcod"), 
						(int)row.get("nhotelcod"),(int)row.get("ntipoimagen"), 
						(String)row.get("crutaimagen"),(boolean)row.get("bestado")));
			}
		}
	}
	public List insertarImagenHotel(CGaleriaHotel galeria)
	{
		Object[] values={galeria.getnHotelCod(),galeria.getnTipoImagen(),
				galeria.getcRutaImagen(),galeria.isbEstado()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_RegistrarGaleriaHotel", values);
	}
	public List modificarImagenHotel(CGaleriaHotel galeria)
	{
		Object[] values={galeria.getnGaleriaHotelCod(),galeria.isbEstado()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_ModificarGaleriaHotel", values);
	}
	public List eliminarImagenGaleriaHotel(int codgaleriaImagenHotel){
		Object[] values={codgaleriaImagenHotel};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_EliminarImagenGaleriaHotel",values);
	}
	public boolean isOperationCorrect(List lista)
	{
		Map row=(Map)lista.get(0);
		boolean correcto=row.get("resultado").toString().equals("correcto");
		if(correcto)return true;
		else return false;
	}
}
