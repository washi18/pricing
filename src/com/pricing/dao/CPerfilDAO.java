package com.pricing.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pricing.model.CPerfil;

public class CPerfilDAO extends CConexion
{
	private ArrayList<CPerfil> listaPerfiles;
	
	public ArrayList<CPerfil> getListaPerfiles() {
		return listaPerfiles;
	}
	public void setListaPerfiles(ArrayList<CPerfil> listaPerfiles) {
		this.listaPerfiles = listaPerfiles;
	}
	//====================
	public CPerfilDAO() {
		// TODO Auto-generated constructor stub
		super();
	}
	//====================
	public List recuperarPerfilesBD()
	{
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarPerfiles");
	}
	public void asignarListaPerfiles(List lista)
	{
		listaPerfiles=new ArrayList<CPerfil>();
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaPerfiles.add(new CPerfil((int)row.get("nperfilcod"),(String)row.get("cperfilidioma1")));
		}
	}
	public List insertarPerfil(String nombrePerfil)
	{
		Object[] values={nombrePerfil};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_RegistrarPerfil", values);
	}
	public int obtenerCodPerfil(List lista)
	{
		Map row=(Map)lista.get(0);
		int cod=(int)row.get("codperfil");
		return cod;
	}
}
