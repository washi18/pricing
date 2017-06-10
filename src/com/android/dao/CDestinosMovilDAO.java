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
	private String nameElemento;
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
	public String getNameElemento() {
		return nameElemento;
	}
	public void setNameElemento(String nameElemento) {
		this.nameElemento = nameElemento;
	}
	//=====================================
	public CDestinosMovilDAO() {
		// TODO Auto-generated constructor stub
		super();
	}
	//===================================
	public List recuperarListaDestinosMovilBD(int cElementoCod)
	{
		Object[] values={cElementoCod};
		return getEjecutorSQL().ejecutarProcedimiento("Android_sp_MostrarDestinosElemento",values);
	}
	public void asignarListaDestinosMovil(List lista)
	{
		listaDestinosMovil=new ArrayList<CDestinoMovil>();
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaDestinosMovil.add(new CDestinoMovil((int)row.get("ndestinocod"),(int)row.get("celementoscod"),
					(String)row.get("cdestino"),(boolean)row.get("bestado"),
					(String)row.get("clatitud"),(String)row.get("clongitud")));
		}
	}
	public List insertarDestinoMovil(CDestinoMovil destino)
	{
		Object[] values={destino.getcElementosCod(),destino.getcDestino(),destino.getcLatitud(),destino.getcLongitud()};
		return getEjecutorSQL().ejecutarProcedimiento("Android_sp_RegistrarDestinoMovil", values);
	}
	public List modificarDestinoMovil(CDestinoMovil destino)
	{
		Object[] values={destino.getnDestinoCod(),destino.getcElementosCod(),destino.getcDestino(),destino.isbEstado(),destino.getcLatitud(),destino.getcLongitud()};
		return getEjecutorSQL().ejecutarProcedimiento("Android_sp_ModificarDestinoMovil", values);
	}
	public List recuperarNombreElemento(int codElemento)
	{
		Object[] values={codElemento};
		return getEjecutorSQL().ejecutarProcedimiento("Android_sp_MostrarNombreElemento", values);
	}
	public void asignarNameElemento(List lista)
	{
		if(!lista.isEmpty())
		{
			Map row=(Map)lista.get(0);
			setNameElemento((String)row.get("nombre"));
		}
	}
	public boolean isOperationCorrect(List lista)
	{
		Map row=(Map)lista.get(0);
		boolean correcto=row.get("resultado").toString().equals("correcto");
		if(correcto)return true;
		else return false;
	}
}
