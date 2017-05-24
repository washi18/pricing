package com.android.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.android.model.CDestinoMovil;
import com.pricing.dao.CConexion;
import com.pricing.model.CDestino;
import com.pricing.model.CHotel;

public class CDestinosMovilDAO extends CConexion{
	private CDestinoMovil oDestinoMovil;
	private ArrayList<CDestinoMovil> listaDestinosMovil;
	private ArrayList<CDestinoMovil> listaDestinosMovilBusqueda;
	//==============================
	public CDestinoMovil getoDestinoMovil() {
		return oDestinoMovil;
	}
	public void setoDestinoMovil(CDestinoMovil oDestinoMovil) {
		this.oDestinoMovil = oDestinoMovil;
	}
	public ArrayList<CDestinoMovil> getListaDestinosMovil() {
		return listaDestinosMovil;
	}
	public void setListaDestinosMovil(ArrayList<CDestinoMovil> listaDestinosMovil) {
		this.listaDestinosMovil = listaDestinosMovil;
	}
	public ArrayList<CDestinoMovil> getListaDestinosMovilBusqueda() {
		return listaDestinosMovilBusqueda;
	}
	public void setListaDestinosMovilBusqueda(ArrayList<CDestinoMovil> listaDestinosMovilBusqueda) {
		this.listaDestinosMovilBusqueda = listaDestinosMovilBusqueda;
	}
	//=====================================
	public CDestinosMovilDAO() {
		// TODO Auto-generated constructor stub
		super();
	}
	//===================================
	public List recuperarListaDestinosMovilBD()
	{
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarDestinosMovil");
	}
	public List recuperarListaTodosDestinosMovilBD()
	{
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarTodosDestinosMovil");
	}
	
	public List buscarDestinosMovilBD(String destino){
		String[] values={destino};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_BuscarDestinosMovil",values);
	}
	public void asignarListaDestinosMovil(List lista)
	{
		listaDestinosMovil=new ArrayList<CDestinoMovil>();
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaDestinosMovil.add(new CDestinoMovil((int)row.get("ndestinocod"),
					(String)row.get("cdestino"),(boolean)row.get("bestado"),
					(String)row.get("clatitud"),(String)row.get("clongitud")));
		}
	}
	public void asignarListaDestinosMovilBusqueda(List lista)
	{
		listaDestinosMovilBusqueda=new ArrayList<CDestinoMovil>();
		listaDestinosMovilBusqueda.add(new CDestinoMovil(0,"Todo los destinos",true,"",""));
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaDestinosMovilBusqueda.add(new CDestinoMovil((int)row.get("ndestinocod"),
					(String)row.get("cdestino"),(boolean)row.get("bestado"),
					(String)row.get("clatitud"),(String)row.get("clongitud")));
		}
	}
	public List insertarDestinoMovil(CDestinoMovil destino)
	{
		Object[] values={destino.getcDestino(),destino.getcLatitud(),destino.getcLongitud()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_RegistrarDestinoMovil", values);
	}
	public List modificarDestinoMovil(CDestinoMovil destino)
	{
		Object[] values={destino.getnDestinoCod(),destino.getcDestino(),destino.isbEstado(),destino.getcLatitud(),destino.getcLongitud()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_ModificarDestinoMovil", values);
	}
	public boolean isOperationCorrect(List lista)
	{
		Map row=(Map)lista.get(0);
		boolean correcto=row.get("resultado").toString().equals("correcto");
		if(correcto)return true;
		else return false;
	}
}
