package com.pricing.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pricing.model.CPaqueteActividad;

public class CPaqueteActividadDAO extends CConexion
{
	private CPaqueteActividad oPaqueteActividad;
	private ArrayList<CPaqueteActividad> listaPaqueteActividades;
	/**************************/
	public CPaqueteActividad getoPaqueteActividad() {
		return oPaqueteActividad;
	}
	public void setoPaqueteActividad(CPaqueteActividad oPaqueteActividad) {
		this.oPaqueteActividad = oPaqueteActividad;
	}
	public ArrayList<CPaqueteActividad> getListaPaqueteActividades() {
		return listaPaqueteActividades;
	}
	public void setListaPaqueteActividades(
			ArrayList<CPaqueteActividad> listaPaqueteActividades) {
		this.listaPaqueteActividades = listaPaqueteActividades;
	}
	/*****************************/
	public CPaqueteActividadDAO() {
		// TODO Auto-generated constructor stub
		super();
	}
	/******************************/
	public List recuperarPaqueteActividades(String codPaquete)
	{
		Object[] values={codPaquete};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarPaqueteActividades", values);
	}
	public void asignarListaPaqueteActividaes(List lista)
	{
		listaPaqueteActividades=new ArrayList<CPaqueteActividad>();
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaPaqueteActividades.add(new CPaqueteActividad((int)row.get("npaqueteactividad"),
					(String)row.get("cpaquetecod"),(int)row.get("nactividadcod")));
		}
	}
}
