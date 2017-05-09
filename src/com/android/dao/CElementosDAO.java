package com.android.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.android.model.CElementos;
import com.android.model.CItems;
import com.pricing.dao.CConexion;

public class CElementosDAO extends CConexion{
	private ArrayList<CElementos> listaElementos;
	private String nameItem;
	//======================================
	
	public ArrayList<CElementos> getListaElementos() {
		return listaElementos;
	}

	public void setListaElementos(ArrayList<CElementos> listaElementos) {
		this.listaElementos = listaElementos;
	}

	public String getNameItem() {
		return nameItem;
	}

	public void setNameItem(String nameItem) {
		this.nameItem = nameItem;
	}
	//=========================================

	public CElementosDAO() {
		// TODO Auto-generated constructor stub
		super();
	}
	//=========================================
	public List registrarElemento(CElementos elemento)
	{
		Object[] values={elemento.getcItemsCod(),
				elemento.getcNombre1Idioma1(),elemento.getcNombre1Idioma2(),elemento.getcNombre1Idioma3(),
				elemento.getcNombre1Idioma4(),elemento.getcNombre1Idioma5(),elemento.getcNombre2Idioma1(),
				elemento.getcNombre2Idioma2(),elemento.getcNombre2Idioma3(),elemento.getcNombre2Idioma4(),
				elemento.getcNombre2Idioma5(),elemento.getcNombre3Idioma1(),elemento.getcNombre3Idioma2(),
				elemento.getcNombre3Idioma3(),elemento.getcNombre3Idioma4(),elemento.getcNombre3Idioma5(),
				elemento.getcImagen1(),elemento.getcImagen2(),elemento.getcImagen3(),elemento.getcDirigidoIdioma1(),
				elemento.getcDirigidoIdioma2(),elemento.getcDirigidoIdioma3(),elemento.getcDirigidoIdioma4(),
				elemento.getcDirigidoIdioma5()};
		return getEjecutorSQL().ejecutarProcedimiento("Android_sp_RegistrarElemento", values);
	}
	public List modificarElemento(CElementos elemento)
	{
		Object[] values={elemento.getcElementosCod(),elemento.getcItemsCod(),
				elemento.getcNombre1Idioma1(),elemento.getcNombre1Idioma2(),elemento.getcNombre1Idioma3(),
				elemento.getcNombre1Idioma4(),elemento.getcNombre1Idioma5(),elemento.getcNombre2Idioma1(),
				elemento.getcNombre2Idioma2(),elemento.getcNombre2Idioma3(),elemento.getcNombre2Idioma4(),
				elemento.getcNombre2Idioma5(),elemento.getcNombre3Idioma1(),elemento.getcNombre3Idioma2(),
				elemento.getcNombre3Idioma3(),elemento.getcNombre3Idioma4(),elemento.getcNombre3Idioma5(),
				elemento.getcImagen1(),elemento.getcImagen2(),elemento.getcImagen3(),elemento.getcDirigidoIdioma1(),
				elemento.getcDirigidoIdioma2(),elemento.getcDirigidoIdioma3(),elemento.getcDirigidoIdioma4(),
				elemento.getcDirigidoIdioma5()};
		return getEjecutorSQL().ejecutarProcedimiento("Android_sp_ModificarElemento", values);
	}
	public List recuperarListaElementosBD()
	{
		return getEjecutorSQL().ejecutarProcedimiento("Android_sp_MostrarTodosElementos");
	}
	public List recuperarNombreItem(int codItem)
	{
		Object[] values={codItem};
		return getEjecutorSQL().ejecutarProcedimiento("Android_sp_MostrarNombreItem", values);
	}
	public void asignarNameItem(List lista)
	{
		if(!lista.isEmpty())
		{
			Map row=(Map)lista.get(0);
			setNameItem((String)row.get("nombre"));
		}
	}
	public void asignarListaElementos(List lista)
	{
		listaElementos=new ArrayList<CElementos>();
		if(!lista.isEmpty())
		{
			for(int i=0;i<lista.size();i++)
			{
				Map row=(Map)lista.get(i);
				listaElementos.add(new CElementos((int)row.get("celementoscod"),(int)row.get("citemscod"), 
						(String)row.get("cnombre1idioma1"),(String)row.get("cnombre1idioma2"), 
						(String)row.get("cnombre1idioma3"),(String)row.get("cnombre1idioma4"), 
						(String)row.get("cnombre1idioma5"),(String)row.get("cnombre2idioma1"),
						(String)row.get("cnombre2idioma2"),(String)row.get("cnombre2idioma3"),
						(String)row.get("cnombre2idioma4"),(String)row.get("cnombre2idioma5"),
						(String)row.get("cnombre3idioma1"),(String)row.get("cnombre3idioma2"),
						(String)row.get("cnombre3idioma3"),(String)row.get("cnombre3idioma4"),
						(String)row.get("cnombre3idioma5"),(String)row.get("cimagen1"),(String)row.get("cimagen2"),
						(String)row.get("cimagen3"),(String)row.get("cdirigidoidioma1"),
						(String)row.get("cdirigidoidioma2"),(String)row.get("cdirigidoidioma3"),
						(String)row.get("cdirigidoidioma4"),(String)row.get("cdirigidoidioma5")));
			}
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
