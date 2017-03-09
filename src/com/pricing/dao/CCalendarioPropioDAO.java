package com.pricing.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pricing.model.CCalendarioPropio;
import com.pricing.model.CDiaPropio;

public class CCalendarioPropioDAO extends CConexion
{
	private ArrayList<CDiaPropio> listaDiasAnioCalendario;
	private ArrayList<CCalendarioPropio> listaAniosCalendario;
	/***************************/
	public ArrayList<CDiaPropio> getListaDiasAnioCalendario() {
		return listaDiasAnioCalendario;
	}
	public void setListaDiasAnioCalendario(
			ArrayList<CDiaPropio> listaDiasAnioCalendario) {
		this.listaDiasAnioCalendario = listaDiasAnioCalendario;
	}
	public ArrayList<CCalendarioPropio> getListaAniosCalendario() {
		return listaAniosCalendario;
	}
	public void setListaAniosCalendario(
			ArrayList<CCalendarioPropio> listaAniosCalendario) {
		this.listaAniosCalendario = listaAniosCalendario;
	}
	/****************************/
	public CCalendarioPropioDAO() {
		// TODO Auto-generated constructor stub
		super();
	}
	/***************************/
	public List recuperarAniosCalendarioBD(String codPaquete)
	{
		Object[] values={codPaquete};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarAniosCalendario", values);
	}
	public List recuperarDiasAnioCalendarioBD(int codCalendario)
	{
		Object[] values={codCalendario};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarDiasAnioCalendario", values);
	}
	public void asignarListaAniosCalendario(List lista)
	{
		listaAniosCalendario=new ArrayList<CCalendarioPropio>();
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaAniosCalendario.add(new CCalendarioPropio((int)row.get("ncalendariocod"), 
					(String)row.get("cpaquetecod"),(int)row.get("nanio")));
		}
	}
	public void asignarListaDiasAnioCalendario(List lista)
	{
		listaDiasAnioCalendario=new ArrayList<CDiaPropio>();
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaDiasAnioCalendario.add(new CDiaPropio((int)row.get("ncalendariocod"),
					(int)row.get("nmes"),(int)row.get("ndia"),
					(int)row.get("ndispo_pack"),(int)row.get("ndispo_inka")));
		}
	}
	public List generarNuevoCalendarioPropioPaquete(String codPaquete,int anio)
	{
		Object[] values={codPaquete,anio};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_RegistrarNuevoCalendarioPropio", values);
	}
	public boolean isOperationCorrect(List lista)
	{
		Map row=(Map)lista.get(0);
		boolean correcto=row.get("resultado").toString().equals("correcto");
		if(correcto)return true;
		else return false;
	}
}
