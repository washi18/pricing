package com.pricing.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pricing.model.CPais;

public class CPaisDAO extends CConexion
{
	private ArrayList<CPais> listaPaises;
	//=============
	public ArrayList<CPais> getListaPaises() {
		return listaPaises;
	}
	public void setListaPaises(ArrayList<CPais> listaPaises) {
		this.listaPaises = listaPaises;
	}
	//==============
	public CPaisDAO() {
		// TODO Auto-generated constructor stub
		super();
	}
	//==============
	public List recuperarPaisesBD()
	{
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarPaises");
	}
	public void asignarPaises(List lista)
	{
		listaPaises=new ArrayList<CPais>();
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			if((int)row.get("npaiscod")!=245)
				listaPaises.add(new CPais((int)row.get("npaiscod"),(String)row.get("cnombreidioma1"), 
						(String)row.get("cnombreidioma2"),(String)row.get("cnombreidioma3"),
						(String)row.get("cnombreidioma4"),(String)row.get("cnombreidioma5")));
		}
	}
}
