package com.android.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.android.model.CElementos;
import com.android.model.CItems;
import com.pricing.dao.CConexion;

public class CItemsDAO extends CConexion{
	private ArrayList<CItems> listaItems;
	private ArrayList<CElementos> listaElementos;
	private String nameSubMenu;
	//======================================
	public ArrayList<CItems> getListaItems() {
		return listaItems;
	}

	public void setListaItems(ArrayList<CItems> listaItems) {
		this.listaItems = listaItems;
	}

	public String getNameSubMenu() {
		return nameSubMenu;
	}

	public void setNameSubMenu(String nameSubMenu) {
		this.nameSubMenu = nameSubMenu;
	}

	public ArrayList<CElementos> getListaElementos() {
		return listaElementos;
	}

	public void setListaElementos(ArrayList<CElementos> listaElementos) {
		this.listaElementos = listaElementos;
	}

	//=========================================
	public CItemsDAO() {
		// TODO Auto-generated constructor stub
		super();
	}
	//=========================================
	public List registrarItem(CItems items)
	{
		Object[] values={items.getcSubMenuCod(),items.getcTituloIdioma1(),
				items.getcTituloIdioma2(),items.getcTituloIdioma3(),items.getcTituloIdioma4(),
				items.getcTituloIdioma5(),items.getcDescripcionIdioma1(),items.getcDescripcionIdioma2(),
				items.getcDescripcionIdioma3(),items.getcDescripcionIdioma4(),items.getcDescripcionIdioma5(),
				items.getcImagen()};
		return getEjecutorSQL().ejecutarProcedimiento("Android_sp_RegistrarItem", values);
	}
	public List modificarItem(CItems items)
	{
		Object[] values={items.getcItemsCod(),items.getcSubMenuCod(),items.getcTituloIdioma1(),
				items.getcTituloIdioma2(),items.getcTituloIdioma3(),items.getcTituloIdioma4(),
				items.getcTituloIdioma5(),items.getcDescripcionIdioma1(),items.getcDescripcionIdioma2(),
				items.getcDescripcionIdioma3(),items.getcDescripcionIdioma4(),items.getcDescripcionIdioma5(),
				items.getcImagen()};
		return getEjecutorSQL().ejecutarProcedimiento("Android_sp_ModificarItem", values);
	}
	public List recuperarListaElementosBD_Item(int codItem)
	{
		Object[] values={codItem};
		return getEjecutorSQL().ejecutarProcedimiento("Android_sp_MostrarTodosElementos_Item",values);
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
	public void asignarListaItems(List lista)
	{
		listaItems=new ArrayList<CItems>();
		if(!lista.isEmpty())
		{
			for(int i=0;i<lista.size();i++)
			{
				Map row=(Map)lista.get(i);
				listaItems.add(new CItems((int)row.get("citemscod"),(int)row.get("csubmenucod"), 
						(String)row.get("ctituloidioma1"),(String)row.get("ctituloidioma2"),
						(String)row.get("ctituloidioma3"),(String)row.get("ctituloidioma4"),
						(String)row.get("ctituloidioma5"),(String)row.get("cdescripcionidioma1"),
						(String)row.get("cdescripcionidioma2"),(String)row.get("cdescripcionidioma3"),
						(String)row.get("cdescripcionidioma4"),(String)row.get("cdescripcionidioma5"),
						(String)row.get("cimagen")));
			}
		}
	}
	public void asignarListaElementos_Item(List lista)
	{
		listaElementos=new ArrayList<CElementos>();
		if(!lista.isEmpty())
		{
			for(int i=0;i<lista.size();i++)
			{
				Map row=(Map)lista.get(i);
				listaElementos.add(new CElementos((int)row.get("celementoscod"),(int)row.get("citemscod"),0, 
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
