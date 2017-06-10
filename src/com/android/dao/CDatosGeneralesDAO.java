package com.android.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.android.model.CDatosGenerales;
import com.android.model.CElementos;
import com.pricing.dao.CConexion;

public class CDatosGeneralesDAO extends CConexion{
	private ArrayList<CDatosGenerales> listaDatosGenerales;
	private String nameElemento;
	//======================================
	public ArrayList<CDatosGenerales> getListaDatosGenerales() {
		return listaDatosGenerales;
	}
	public void setListaDatosGenerales(ArrayList<CDatosGenerales> listaDatosGenerales) {
		this.listaDatosGenerales = listaDatosGenerales;
	}
	public String getNameElemento() {
		return nameElemento;
	}
	public void setNameElemento(String nameElemento) {
		this.nameElemento = nameElemento;
	}
	//=========================================

	public CDatosGeneralesDAO() {
		// TODO Auto-generated constructor stub
		super();
	}
	//=========================================
	public List registrarDatoGeneral(CDatosGenerales datoGeneral)
	{
		Object[] values={datoGeneral.getcElementosCod(),datoGeneral.getcTituloIdioma1(),
				datoGeneral.getcTituloIdioma2(),datoGeneral.getcTituloIdioma3(),
				datoGeneral.getcTituloIdioma4(),datoGeneral.getcTituloIdioma5(),
				datoGeneral.getcDescripcionIdioma1(),datoGeneral.getcDescripcionIdioma2(),
				datoGeneral.getcDescripcionIdioma3(),datoGeneral.getcDescripcionIdioma4(),
				datoGeneral.getcDescripcionIdioma5(),datoGeneral.getcImagen()};
		return getEjecutorSQL().ejecutarProcedimiento("Android_sp_RegistrarDatoGeneral", values);
	}
	public List modificarDatoGeneral(CDatosGenerales datoGeneral)
	{
		Object[] values={datoGeneral.getcDatosGeneralesCod(),datoGeneral.getcElementosCod(),
				datoGeneral.getcTituloIdioma1(),
				datoGeneral.getcTituloIdioma2(),datoGeneral.getcTituloIdioma3(),
				datoGeneral.getcTituloIdioma4(),datoGeneral.getcTituloIdioma5(),
				datoGeneral.getcDescripcionIdioma1(),datoGeneral.getcDescripcionIdioma2(),
				datoGeneral.getcDescripcionIdioma3(),datoGeneral.getcDescripcionIdioma4(),
				datoGeneral.getcDescripcionIdioma5(),datoGeneral.getcImagen()};
		return getEjecutorSQL().ejecutarProcedimiento("Android_sp_ModificarDatoGeneral", values);
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
	public void asignarListaDatosGenerales(List lista)
	{
		listaDatosGenerales=new ArrayList<CDatosGenerales>();
		if(!lista.isEmpty())
		{
			for(int i=0;i<lista.size();i++)
			{
				Map row=(Map)lista.get(i);
				listaDatosGenerales.add(new CDatosGenerales((int)row.get("cdatosgeneralescod"),(int)row.get("citemscod"), 
						(String)row.get("ctituloidioma1"),(String)row.get("ctituloidioma2"), 
						(String)row.get("ctituloidioma3"),(String)row.get("ctituloidioma4"), 
						(String)row.get("ctituloidioma5"),(String)row.get("cdescripcionidioma1"), 
						(String)row.get("cdescripcionidioma2"),(String)row.get("cdescripcionidioma3"), 
						(String)row.get("cdescripcionidioma4"),(String)row.get("cdescripcionidioma5"), 
						(String)row.get("cimagen")));
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
