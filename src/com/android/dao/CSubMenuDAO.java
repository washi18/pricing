package com.android.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.android.model.CElementos;
import com.android.model.CItems;
import com.android.model.CSubMenu;
import com.pricing.dao.CConexion;

public class CSubMenuDAO extends CConexion{
	private ArrayList<CSubMenu> listaSubMenu;
	private ArrayList<CItems> listaItems;
	private ArrayList<CElementos> listaElementos;
	private String nameMenu;
	//======================================

	public ArrayList<CSubMenu> getListaSubMenu() {
		return listaSubMenu;
	}

	public void setListaSubMenu(ArrayList<CSubMenu> listaSubMenu) {
		this.listaSubMenu = listaSubMenu;
	}
	public String getNameMenu() {
		return nameMenu;
	}

	public void setNameMenu(String nameMenu) {
		this.nameMenu = nameMenu;
	}

	public ArrayList<CItems> getListaItems() {
		return listaItems;
	}

	public void setListaItems(ArrayList<CItems> listaItems) {
		this.listaItems = listaItems;
	}

	public ArrayList<CElementos> getListaElementos() {
		return listaElementos;
	}

	public void setListaElementos(ArrayList<CElementos> listaElementos) {
		this.listaElementos = listaElementos;
	}

	//=========================================
	public CSubMenuDAO() {
		// TODO Auto-generated constructor stub
		super();
	}
	//=========================================
	public List registrarSubMenu(CSubMenu subMenu)
	{
		Object[] values={subMenu.getcMenuCod(),subMenu.getcNombreIdioma1(),subMenu.getcNombreIdioma2(),
				subMenu.getcNombreIdioma3(),subMenu.getcNombreIdioma4(),subMenu.getcNombreIdioma5(),
				subMenu.getcImagen(),subMenu.isElemento()};
		return getEjecutorSQL().ejecutarProcedimiento("Android_sp_RegistrarSubMenu", values);
	}
	public List modificarSubMenu(CSubMenu subMenu)
	{
		Object[] values={subMenu.getcSubMenuCod(),subMenu.getcMenuCod(),subMenu.getcNombreIdioma1(),
				subMenu.getcNombreIdioma2(),subMenu.getcNombreIdioma3(),subMenu.getcNombreIdioma4(),
				subMenu.getcNombreIdioma5(),subMenu.getcImagen(),subMenu.isEstado(),subMenu.isElemento()};
		return getEjecutorSQL().ejecutarProcedimiento("Android_sp_ModificarSubMenu", values);
	}
	public List modificarElementoSubmenu(int codSubmenu,boolean bElemento)
	{
		Object[] values={codSubmenu,bElemento};
		return getEjecutorSQL().ejecutarProcedimiento("Android_sp_ModificarbElementoSubMenu", values);
	}
	public List recuperarListaItemsBD_SubMenu(int codSubMenu)
	{
		Object[] values={codSubMenu};
		return getEjecutorSQL().ejecutarProcedimiento("Android_sp_MostrarTodosItems_SubMenu",values);
	}
	public List recuperarListaElementosBD_SubMenu(int codSubMenu)
	{
		Object[] values={codSubMenu};
		return getEjecutorSQL().ejecutarProcedimiento("Android_sp_MostrarTodosElementos_SubMenu",values);
	}
	public List recuperarNombreMenu(int codMenu)
	{
		Object[] values={codMenu};
		return getEjecutorSQL().ejecutarProcedimiento("Android_sp_MostrarNombreMenu", values);
	}
	public void asignarNameMenu(List lista)
	{
		if(!lista.isEmpty())
		{
			Map row=(Map)lista.get(0);
			setNameMenu((String)row.get("nombre"));
		}
	}
	public void asignarListaSubMenu(List lista)
	{
		listaSubMenu=new ArrayList<CSubMenu>();
		if(!lista.isEmpty())
		{
			for(int i=0;i<lista.size();i++)
			{
				Map row=(Map)lista.get(i);
				listaSubMenu.add(new CSubMenu((int)row.get("csubmenucod"),(int)row.get("cmenucod"), 
						(String)row.get("cnombreidioma1"),(String)row.get("cnombreidioma2"),(String)row.get("cnombreidioma3"),
						(String)row.get("cnombreidioma4"),(String)row.get("cnombreidioma5"),(String)row.get("cimagen"),
						(boolean)row.get("estado"),(boolean)row.get("elemento")));
			}
		}
	}
	public void asignarListaItems_SubMenu(List lista)
	{
		listaItems= new ArrayList<CItems>();
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
	public void asignarListaElementos_SubMenu(List lista)
	{
		listaElementos=new ArrayList<CElementos>();
		if(!lista.isEmpty())
		{
			for(int i=0;i<lista.size();i++)
			{
				Map row=(Map)lista.get(i);
				listaElementos.add(new CElementos((int)row.get("celementoscod"),0,
						(int)row.get("csubmenucod"),(String)row.get("cnombre1idioma1"),
						(String)row.get("cnombre1idioma2"),(String)row.get("cnombre1idioma3"),
						(String)row.get("cnombre1idioma4"),(String)row.get("cnombre1idioma5"),
						(String)row.get("cnombre2idioma1"),(String)row.get("cnombre2idioma2"),
						(String)row.get("cnombre2idioma3"),(String)row.get("cnombre2idioma4"),
						(String)row.get("cnombre2idioma5"),(String)row.get("cnombre3idioma1"),
						(String)row.get("cnombre3idioma2"),(String)row.get("cnombre3idioma3"),
						(String)row.get("cnombre3idioma4"),(String)row.get("cnombre3idioma5"),
						(String)row.get("cimagen1"),(String)row.get("cimagen2"),
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
