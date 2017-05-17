package com.android.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.android.model.CMenu;
import com.android.model.CSubMenu;
import com.pricing.dao.CConexion;

public class CMenuDAO extends CConexion{
	private ArrayList<CMenu> listaMenu;
	private ArrayList<CSubMenu> listaSubMenu;
	//================================
	public ArrayList<CMenu> getListaMenu() {
		return listaMenu;
	}
	public void setListaMenu(ArrayList<CMenu> listaMenu) {
		this.listaMenu = listaMenu;
	}
	public ArrayList<CSubMenu> getListaSubMenu() {
		return listaSubMenu;
	}
	public void setListaSubMenu(ArrayList<CSubMenu> listaSubMenu) {
		this.listaSubMenu = listaSubMenu;
	}
	//=================================
	public CMenuDAO() {
		// TODO Auto-generated constructor stub
		super();
	}
	public List registrarMenu(CMenu menu)
	{
		Object[] values={menu.getcNombreIdioma1(),menu.getcNombreIdioma2(),
				menu.getcNombreIdioma3(),menu.getcNombreIdioma4(),menu.getcNombreIdioma5(),
				menu.getcImagenIcono(),menu.getcImagenFondo()};
		return getEjecutorSQL().ejecutarProcedimiento("Android_sp_RegistrarMenu", values);
	}
	public List modificarMenu(CMenu menu)
	{
		Object[] values={menu.getcMenuCod(),menu.getcNombreIdioma1(),menu.getcNombreIdioma2(),
				menu.getcNombreIdioma3(),menu.getcNombreIdioma4(),menu.getcNombreIdioma5(),
				menu.getcImagenIcono(),menu.getcImagenFondo(),menu.isEstado()};
		return getEjecutorSQL().ejecutarProcedimiento("Android_sp_ModificarMenu", values);
	}
	public List recuperarListaMenuBD()
	{
		return getEjecutorSQL().ejecutarProcedimiento("Android_sp_MostrarTodosMenus");
	}
	public List recuperarListaSubMenuBD_Menu(int codMenu)
	{
		Object[] values={codMenu};
		return getEjecutorSQL().ejecutarProcedimiento("Android_sp_MostrarTodosSubMenus_Menu",values);
	}
	public void asignarListaMenu(List lista)
	{
		listaMenu=new ArrayList<CMenu>();
		if(!lista.isEmpty())
		{
			for(int i=0;i<lista.size();i++)
			{
				Map row=(Map)lista.get(i);
				listaMenu.add(new CMenu((int)row.get("cmenucod"),(String)row.get("cnombreidioma1"),
						(String)row.get("cnombreidioma2"),(String)row.get("cnombreidioma3"),(String)row.get("cnombreidioma4"),
						(String)row.get("cnombreidioma5"),(String)row.get("cimagenicono"),(String)row.get("cimagenfondo"),
						(boolean)row.get("estado")));
			}
		}
	}
	public void asignarListaSubMenuBD_Menu(List lista)
	{
		listaSubMenu=new ArrayList<CSubMenu>();
		if(!lista.isEmpty())
		{
			for(int i=0;i<lista.size();i++)
			{
				Map row=(Map)lista.get(i);
				listaSubMenu.add(new CSubMenu((int)row.get("csubmenucod"),(int)row.get("cmenucod"), 
						(String)row.get("cnombreidioma1"),(String)row.get("cnombreidioma2"),
						(String)row.get("cnombreidioma3"),(String)row.get("cnombreidioma4"),
						(String)row.get("cnombreidioma5"),(String)row.get("cimagen"),
						(boolean)row.get("estado"),(boolean)row.get("elemento")));
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
