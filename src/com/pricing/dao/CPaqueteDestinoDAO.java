package com.pricing.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pricing.model.CPaqueteDestino;

public class CPaqueteDestinoDAO extends CConexion
{
	private CPaqueteDestino oPaqueteDestino;
	private ArrayList<CPaqueteDestino> listaPaqueteDestinos;
	//=====================================
	public CPaqueteDestino getoPaqueteDestino() {
		return oPaqueteDestino;
	}
	public void setoPaqueteDestino(CPaqueteDestino oPaqueteDestino) {
		this.oPaqueteDestino = oPaqueteDestino;
	}
	public ArrayList<CPaqueteDestino> getListaPaqueteDestinos() {
		return listaPaqueteDestinos;
	}
	public void setListaPaqueteDestinos(
			ArrayList<CPaqueteDestino> listaPaqueteDestinos) {
		this.listaPaqueteDestinos = listaPaqueteDestinos;
	}
	//================================
	public CPaqueteDestinoDAO() {
		// TODO Auto-generated constructor stub
		super();
		this.oPaqueteDestino=new CPaqueteDestino();
	}
	public CPaqueteDestinoDAO(CPaqueteDestino paquetdest)
	{
		super();
		this.oPaqueteDestino=paquetdest;
	}
	//================================
	public List recuperarPaqueteDestinos(String codPaquete)
	{
		Object[] value={codPaquete};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarPaqueteDestinos", value);
	}
	public void asignarListaPaqueteDestinos(List lista)
	{
		listaPaqueteDestinos=new ArrayList<CPaqueteDestino>();
		System.out.println("tamanio lista paquetedestino ->"+lista.size());
			for(int i=0;i<lista.size();i++)
			{
				Map row=(Map)lista.get(i);
				listaPaqueteDestinos.add(new CPaqueteDestino((int)row.get("codpaquetedestino"), 
						(String)row.get("cpaquetecod"),(int)row.get("ndestinocod"),
						(int)row.get("nnoches"),(int)row.get("nordenitinerario"),
						(boolean)row.get("bconcaminoinka")));
			}
	}
}
