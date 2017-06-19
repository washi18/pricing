package com.android.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.android.model.CDatosGenerales;
import com.android.model.CDestinoMovil;
import com.android.model.CElementos;
import com.android.model.CItems;
import com.pricing.dao.CConexion;

public class CElementosDAO extends CConexion{
	private ArrayList<CElementos> listaElementos;
	private ArrayList<CDestinoMovil> listaDestinosMovil;
	private ArrayList<CDatosGenerales> listaDatosGenerales;
	private String nameItem;
	private String nameSubMenu;
	//======================================
	
	public ArrayList<CElementos> getListaElementos() {
		return listaElementos;
	}

	public void setListaElementos(ArrayList<CElementos> listaElementos) {
		this.listaElementos = listaElementos;
	}

	public ArrayList<CDestinoMovil> getListaDestinosMovil() {
		return listaDestinosMovil;
	}

	public void setListaDestinosMovil(ArrayList<CDestinoMovil> listaDestinosMovil) {
		this.listaDestinosMovil = listaDestinosMovil;
	}

	public ArrayList<CDatosGenerales> getListaDatosGenerales() {
		return listaDatosGenerales;
	}

	public void setListaDatosGenerales(ArrayList<CDatosGenerales> listaDatosGenerales) {
		this.listaDatosGenerales = listaDatosGenerales;
	}

	public String getNameItem() {
		return nameItem;
	}

	public void setNameItem(String nameItem) {
		this.nameItem = nameItem;
	}
	public String getNameSubMenu() {
		return nameSubMenu;
	}

	public void setNameSubMenu(String nameSubMenu) {
		this.nameSubMenu = nameSubMenu;
	}
	//=========================================

	public CElementosDAO() {
		// TODO Auto-generated constructor stub
		super();
	}
	//=========================================
	public List registrarElementoSubmenu(CElementos elemento)
	{
		Object[] values={elemento.getcSubMenuCod(),
				elemento.getcNombre1Idioma1(),elemento.getcNombre1Idioma2(),elemento.getcNombre1Idioma3(),
				elemento.getcNombre1Idioma4(),elemento.getcNombre1Idioma5(),elemento.getcNombre2Idioma1(),
				elemento.getcNombre2Idioma2(),elemento.getcNombre2Idioma3(),elemento.getcNombre2Idioma4(),
				elemento.getcNombre2Idioma5(),elemento.getcNombre3Idioma1(),elemento.getcNombre3Idioma2(),
				elemento.getcNombre3Idioma3(),elemento.getcNombre3Idioma4(),elemento.getcNombre3Idioma5(),
				elemento.getcImagen1(),elemento.getcImagen2(),elemento.getcImagen3(),
				elemento.getcDescripcionIdioma1(),elemento.getcDescripcionIdioma2(),elemento.getcDescripcionIdioma3(),
				elemento.getcDescripcionIdioma4(),elemento.getcDescripcionIdioma5(),elemento.getcDirigidoIdioma1(),
				elemento.getcDirigidoIdioma2(),elemento.getcDirigidoIdioma3(),elemento.getcDirigidoIdioma4(),
				elemento.getcDirigidoIdioma5()};
		return getEjecutorSQL().ejecutarProcedimiento("Android_sp_RegistrarElementoSubMenu", values);
	}
	public List registrarElementoItem(CElementos elemento)
	{
		Object[] values={elemento.getcItemsCod(),
				elemento.getcNombre1Idioma1(),elemento.getcNombre1Idioma2(),elemento.getcNombre1Idioma3(),
				elemento.getcNombre1Idioma4(),elemento.getcNombre1Idioma5(),elemento.getcNombre2Idioma1(),
				elemento.getcNombre2Idioma2(),elemento.getcNombre2Idioma3(),elemento.getcNombre2Idioma4(),
				elemento.getcNombre2Idioma5(),elemento.getcNombre3Idioma1(),elemento.getcNombre3Idioma2(),
				elemento.getcNombre3Idioma3(),elemento.getcNombre3Idioma4(),elemento.getcNombre3Idioma5(),
				elemento.getcImagen1(),elemento.getcImagen2(),elemento.getcImagen3(),
				elemento.getcDescripcionIdioma1(),elemento.getcDescripcionIdioma2(),elemento.getcDescripcionIdioma3(),
				elemento.getcDescripcionIdioma4(),elemento.getcDescripcionIdioma5(),elemento.getcDirigidoIdioma1(),
				elemento.getcDirigidoIdioma2(),elemento.getcDirigidoIdioma3(),elemento.getcDirigidoIdioma4(),
				elemento.getcDirigidoIdioma5()};
		return getEjecutorSQL().ejecutarProcedimiento("Android_sp_RegistrarElementoItem", values);
	}
	public List modificarElemento_Item(CElementos elemento)
	{
		Object[] values={elemento.getcElementosCod(),elemento.getcItemsCod(),
				elemento.getcNombre1Idioma1(),elemento.getcNombre1Idioma2(),elemento.getcNombre1Idioma3(),
				elemento.getcNombre1Idioma4(),elemento.getcNombre1Idioma5(),elemento.getcNombre2Idioma1(),
				elemento.getcNombre2Idioma2(),elemento.getcNombre2Idioma3(),elemento.getcNombre2Idioma4(),
				elemento.getcNombre2Idioma5(),elemento.getcNombre3Idioma1(),elemento.getcNombre3Idioma2(),
				elemento.getcNombre3Idioma3(),elemento.getcNombre3Idioma4(),elemento.getcNombre3Idioma5(),
				elemento.getcImagen1(),elemento.getcImagen2(),elemento.getcImagen3(),
				elemento.getcDescripcionIdioma1(),elemento.getcDescripcionIdioma2(),elemento.getcDescripcionIdioma3(),
				elemento.getcDescripcionIdioma4(),elemento.getcDescripcionIdioma5(),elemento.getcDirigidoIdioma1(),
				elemento.getcDirigidoIdioma2(),elemento.getcDirigidoIdioma3(),elemento.getcDirigidoIdioma4(),
				elemento.getcDirigidoIdioma5()};
		return getEjecutorSQL().ejecutarProcedimiento("Android_sp_ModificarElemento_Item", values);
	}
	public List modificarElemento_Submenu(CElementos elemento)
	{
		Object[] values={elemento.getcElementosCod(),elemento.getcSubMenuCod(),
				elemento.getcNombre1Idioma1(),elemento.getcNombre1Idioma2(),elemento.getcNombre1Idioma3(),
				elemento.getcNombre1Idioma4(),elemento.getcNombre1Idioma5(),elemento.getcNombre2Idioma1(),
				elemento.getcNombre2Idioma2(),elemento.getcNombre2Idioma3(),elemento.getcNombre2Idioma4(),
				elemento.getcNombre2Idioma5(),elemento.getcNombre3Idioma1(),elemento.getcNombre3Idioma2(),
				elemento.getcNombre3Idioma3(),elemento.getcNombre3Idioma4(),elemento.getcNombre3Idioma5(),
				elemento.getcImagen1(),elemento.getcImagen2(),elemento.getcImagen3(),
				elemento.getcDescripcionIdioma1(),elemento.getcDescripcionIdioma2(),elemento.getcDescripcionIdioma3(),
				elemento.getcDescripcionIdioma4(),elemento.getcDescripcionIdioma5(),elemento.getcDirigidoIdioma1(),
				elemento.getcDirigidoIdioma2(),elemento.getcDirigidoIdioma3(),elemento.getcDirigidoIdioma4(),
				elemento.getcDirigidoIdioma5()};
		return getEjecutorSQL().ejecutarProcedimiento("Android_sp_ModificarElemento_Submenu", values);
	}
	public List recuperarListaDestinosBD_Elemento(int codElemento)
	{
		Object[] values={codElemento};
		return getEjecutorSQL().ejecutarProcedimiento("Android_sp_MostrarDestinosElemento", values);
	}
	public List recuperarListaDatosGeneralesBD_Elemento(int codElemento)
	{
		Object[] values={codElemento};
		return getEjecutorSQL().ejecutarProcedimiento("Android_sp_MostrarDatosGeneralesElemento", values);
	}
	public void asignarListaDestinosElemento(List lista)
	{
		listaDestinosMovil=new ArrayList<CDestinoMovil>();
		if(!lista.isEmpty())
		{
			for(int i=0;i<lista.size();i++)
			{
				Map row=(Map)lista.get(i);
				listaDestinosMovil.add(new CDestinoMovil((int)row.get("ndestinocod"),(int)row.get("celementoscod"),
						(String)row.get("cdestino"),(boolean)row.get("bestado"),
						(String)row.get("clatitud"),(String)row.get("clongitud")));
			}
		}
	}
	public void asignarListaDatosGeneralesElemento(List lista)
	{
		listaDatosGenerales=new ArrayList<CDatosGenerales>();
		if(!lista.isEmpty())
		{
			for(int i=0;i<lista.size();i++)
			{
				Map row=(Map)lista.get(i);
				listaDatosGenerales.add(new CDatosGenerales((int)row.get("cdatosgeneralescod"),(int)row.get("celementoscod"),
						(String)row.get("ctituloidioma1"),(String)row.get("ctituloidioma2"),
						(String)row.get("ctituloidioma3"),(String)row.get("ctituloidioma4"),
						(String)row.get("ctituloidioma5"),(String)row.get("cdescripcionidioma1"),
						(String)row.get("cdescripcionidioma2"),(String)row.get("cdescripcionidioma3"),
						(String)row.get("cdescripcionidioma4"),(String)row.get("cdescripcionidioma5"),
						(String)row.get("cimagen")));
			}
		}
	}
	public List recuperarNombreItem(int codItem)
	{
		Object[] values={codItem};
		return getEjecutorSQL().ejecutarProcedimiento("Android_sp_MostrarNombreItem", values);
	}
	public List recuperarNombreSubMenu(int codSubMenu)
	{
		Object[] values={codSubMenu};
		return getEjecutorSQL().ejecutarProcedimiento("Android_sp_MostrarNombreSubMenu", values);
	}
	public void asignarNameSubMenu(List lista)
	{
		if(!lista.isEmpty())
		{
			Map row=(Map)lista.get(0);
			setNameSubMenu((String)row.get("nombre"));
		}
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
				listaElementos.add(new CElementos((int)row.get("celementoscod"),(int)row.get("citemscod"), (int)row.get("csubmenucod"),
						(String)row.get("cnombre1idioma1"),(String)row.get("cnombre1idioma2"), 
						(String)row.get("cnombre1idioma3"),(String)row.get("cnombre1idioma4"), 
						(String)row.get("cnombre1idioma5"),(String)row.get("cnombre2idioma1"),
						(String)row.get("cnombre2idioma2"),(String)row.get("cnombre2idioma3"),
						(String)row.get("cnombre2idioma4"),(String)row.get("cnombre2idioma5"),
						(String)row.get("cnombre3idioma1"),(String)row.get("cnombre3idioma2"),
						(String)row.get("cnombre3idioma3"),(String)row.get("cnombre3idioma4"),
						(String)row.get("cnombre3idioma5"),(String)row.get("cimagen1"),(String)row.get("cimagen2"),
						(String)row.get("cimagen3"),(String)row.get("cdescripcionidioma1"),(String)row.get("cdescripcionidioma2"),
						(String)row.get("cdescripcionidioma3"),(String)row.get("cdescripcionidioma4"),
						(String)row.get("cdescripcionidioma5"),(String)row.get("cdirigidoidioma1"),
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
