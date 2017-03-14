package com.pricing.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pricing.model.CDestino;
import com.pricing.model.CHotel;

public class CDestinoDAO extends CConexion
{
	private CDestino oDestino;
	private ArrayList<CDestino> listaDestinos;
	private ArrayList<CDestino> listaDestinosBusqueda;
	private ArrayList<CHotel> listaHotelesDestino;
	//==============================
	public CDestino getoDestino() {
		return oDestino;
	}
	public void setoDestino(CDestino oDestino) {
		this.oDestino = oDestino;
	}
	public ArrayList<CDestino> getListaDestinos() {
		return listaDestinos;
	}
	public void setListaDestinos(ArrayList<CDestino> listaDestinos) {
		this.listaDestinos = listaDestinos;
	}
	public ArrayList<CHotel> getListaHotelesDestino() {
		return listaHotelesDestino;
	}
	public void setListaHotelesDestino(ArrayList<CHotel> listaHotelesDestino) {
		this.listaHotelesDestino = listaHotelesDestino;
	}
	public ArrayList<CDestino> getListaDestinosBusqueda() {
		return listaDestinosBusqueda;
	}
	public void setListaDestinosBusqueda(ArrayList<CDestino> listaDestinosBusqueda) {
		this.listaDestinosBusqueda = listaDestinosBusqueda;
	}
	//=====================================
	public CDestinoDAO() {
		// TODO Auto-generated constructor stub
		super();
		this.oDestino=new CDestino();
	}
	public CDestinoDAO(CDestino destino)
	{
		super();
		this.oDestino=destino;
	}
	//===================================
	public List recuperarListaDestinosBD()
	{
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarDestinos");
	}
	public List recuperarListaTodosDestinosBD()
	{
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarTodosDestinos");
	}
	
	public List buscarDestinosBD(String destino){
		String[] values={destino};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_BuscarDestinos",values);
	}
	public List recuperarListaHotelesDestino(int codDestino)
	{
		Object[] values={codDestino};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarHotelesDestino", values);
	}
	public List recuperarDestinoBD(int codDestino)
	{
		Object[] values={codDestino};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarDestino", values);
	}
	public void asignarDestino(List lista)
	{
		Map row=(Map)lista.get(0);
		oDestino=new CDestino((int)row.get("ndestinocod"),
				(String)row.get("cdestino"),(boolean)row.get("bestado"),
				(int)row.get("ncodpostal"),(String)row.get("clatitud"),(String)row.get("clongitud"),(String)row.get("cimagen"));
	}
	public void asignarListaHotelesPorDestino(List lista)
	{
		listaHotelesDestino=new ArrayList<CHotel>();
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaHotelesDestino.add(new CHotel((int)row.get("nhotelcod"),(String)row.get("chotel"), 
					(String)row.get("cdescripcionidioma1"),(String)row.get("cdescripcionidioma2"),
					(String)row.get("cdescripcionidioma3"),(String)row.get("cdescripcionidioma4"),
					(String)row.get("cdescripcionidioma5"),(String)row.get("curl"),
					(int)row.get("categoriahotelcod"),(Number) row.get("npreciosimple"),
					(Number)row.get("npreciodoble"),(Number)row.get("npreciotriple"),
					(boolean)row.get("bestado"),(Number)row.get("npreciocamaadicional"),
					(int)row.get("ndestinocod"),(String)row.get("cfoto1"),(String)row.get("cfoto2"),
					(String)row.get("cfoto3"),(String)row.get("cfoto4"),(String)row.get("cfoto5"),
					(String)row.get("cimagenubicacion")));
		}
	}
	public void asignarListaDestinos(List lista)
	{
		listaDestinos=new ArrayList<CDestino>();
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaDestinos.add(new CDestino((int)row.get("ndestinocod"),
					(String)row.get("cdestino"),(boolean)row.get("bestado"),
					(int)row.get("ncodpostal"),(String)row.get("clatitud"),(String)row.get("clongitud"),(String)row.get("cimagen")));
		}
	}
	public void asignarListaDestinosBusqueda(List lista)
	{
		listaDestinosBusqueda=new ArrayList<CDestino>();
		listaDestinosBusqueda.add(new CDestino(0,"Todo los destinos",true,1,"","",""));
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaDestinosBusqueda.add(new CDestino((int)row.get("ndestinocod"),
					(String)row.get("cdestino"),(boolean)row.get("bestado"),
					(int)row.get("ncodpostal"),(String)row.get("clatitud"),(String)row.get("clongitud"),(String)row.get("cimagen")));
		}
	}
	public List insertarDestino(CDestino destino)
	{
		Object[] values={destino.getcDestino(),destino.getnCodPostal(),destino.getLatitud(),destino.getLongitud(),destino.getUrlImagen()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_RegistrarDestino", values);
	}
	public List modificarDestino(CDestino destino)
	{
		Object[] values={destino.getnDestinoCod(),destino.getcDestino(),destino.isbEstado(),destino.getnCodPostal(),destino.getLatitud(),destino.getLongitud(),destino.getUrlImagen()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_ModificarDestino", values);
	}
	public boolean isOperationCorrect(List lista)
	{
		Map row=(Map)lista.get(0);
		boolean correcto=row.get("resultado").toString().equals("correcto");
		if(correcto)return true;
		else return false;
	}
	
}
