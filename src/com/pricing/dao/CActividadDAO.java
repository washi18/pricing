package com.pricing.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pricing.model.CActividad;

public class CActividadDAO extends CConexion
{
	private CActividad oActividad;
	private ArrayList<CActividad> listaActividades;
	/********************/
	public CActividad getoActividad() {
		return oActividad;
	}
	public void setoActividad(CActividad oActividad) {
		this.oActividad = oActividad;
	}
	public ArrayList<CActividad> getListaActividades() {
		return listaActividades;
	}
	public void setListaActividades(ArrayList<CActividad> listaActividades) {
		this.listaActividades = listaActividades;
	}
	/*************************/
	public CActividadDAO() {
		// TODO Auto-generated constructor stub
		super();
	}
	/*************************/
	public List recuperarActividadesBD()
	{
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarActividades");
	}
	public List buscarActividadesBD(String nombre){
		String[] values={nombre};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_BuscarActividades",values);
	}
	public List recuperarTodasActividadesBD()
	{
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarTodasActividades");
	}
	public void asignarListaActividades(List lista)
	{
		listaActividades=new ArrayList<CActividad>();
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaActividades.add(new CActividad((int)row.get("nactividadcod"),
					(String)row.get("cactividadidioma1"),(String)row.get("cactividadidioma2"),
					(String)row.get("cactividadidioma3"),(String)row.get("cactividadidioma4"), 
					(String)row.get("cactividadidioma5"),(String)row.get("cdescripcionidioma1"),
					(String)row.get("cdescripcionidioma2"),(String)row.get("cdescripcionidioma3"),
					(String)row.get("cdescripcionidioma4"),(String)row.get("cdescripcionidioma5"), 
					(String)row.get("curlimg"),(Number)row.get("nprecioactividad"),
					(boolean)row.get("bestado")));
		}
	}
	public List insertarActividadBD(CActividad actividad)
	{
		Object[] values={actividad.getcActividadIdioma1(),actividad.getcActividadIdioma2(),
				actividad.getcActividadIdioma3(),actividad.getcActividadIdioma4(),
				actividad.getcActividadIdioma5(),actividad.getcDescripcionIdioma1(),
				actividad.getcDescripcionIdioma2(),actividad.getcDescripcionIdioma3(),
				actividad.getcDescripcionIdioma4(),actividad.getcDescripcionIdioma5(),
				actividad.getcUrlImg(),actividad.getnPrecioActividad().doubleValue()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_RegistrarActividad", values);
	}
	public List actualizarActividadBD(CActividad actividad)
	{
		Object[] values={actividad.getnActividadCod(),actividad.getcActividadIdioma1(),actividad.getcActividadIdioma2(),
				actividad.getcActividadIdioma3(),actividad.getcActividadIdioma4(),
				actividad.getcActividadIdioma5(),actividad.getcDescripcionIdioma1(),
				actividad.getcDescripcionIdioma2(),actividad.getcDescripcionIdioma3(),
				actividad.getcDescripcionIdioma4(),actividad.getcDescripcionIdioma5(),
				actividad.getcUrlImg(),actividad.getnPrecioActividad().doubleValue(),
				actividad.isbEstado()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_ModificarActividad", values);
	}
	public boolean isCorrectOperation(List lista)
	{
		Map row=(Map)lista.get(0);
		boolean correcto=row.get("resultado").toString().equals("correcto");
		if(correcto)return true;
		else return false;
	}
}
