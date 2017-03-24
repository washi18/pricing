package com.pricing.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindUtils;

import com.pricing.model.CCalendarioDisponibilidad;

public class CDisponibilidadYourselfDAO extends CConexion {
	//======================atributos==============================
	private ArrayList<CCalendarioDisponibilidad> listaDisponibilidad;
	private ArrayList<Integer> listaDispoActual;
	private ArrayList<Integer> listaDispoSiguiente;
	private ArrayList<Integer> listaDispoMes;
	//=======================getter an setter====================
	public ArrayList<CCalendarioDisponibilidad> getListaDisponibilidad() {
		return listaDisponibilidad;
	}
	public void setListaDisponibilidad(ArrayList<CCalendarioDisponibilidad> listaDisponibilidad) {
		this.listaDisponibilidad = listaDisponibilidad;
	}
	
	public ArrayList<Integer> getListaDispoActual() {
		return listaDispoActual;
	}
	public void setListaDispoActual(ArrayList<Integer> listaDispoActual) {
		this.listaDispoActual = listaDispoActual;
	}
	public ArrayList<Integer> getListaDispoSiguiente() {
		return listaDispoSiguiente;
	}
	public void setListaDispoSiguiente(ArrayList<Integer> listaDispoSiguiente) {
		this.listaDispoSiguiente = listaDispoSiguiente;
	}
	public ArrayList<Integer> getListaDispoMes() {
		return listaDispoMes;
	}
	public void setListaDispoMes(ArrayList<Integer> listaDispoMes) {
		this.listaDispoMes = listaDispoMes;
	}
	//=======================constructores=======================
	public CDisponibilidadYourselfDAO() {
		// TODO Auto-generated constructor stub
		super();
	}
	//=======================otros metodos=======================
	public List recuperarDisponibilidadesBD(int cDisponibilidad)
	{
		Object[] values={cDisponibilidad};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_recuperardisponibilidad_yourself", values);
	}
	public List recuperarDispoMes(int anio,int mes,int codDispo)
	{
		Object[] values={anio,mes,codDispo};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarMesDisponibilidad_yourself", values);
	}
	public List crearDisponibilidad(int cdestino,int anio,boolean biciestoActual,boolean biciestoSig){
		Object[] values={cdestino,anio,biciestoActual,biciestoSig};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_creardisponibilidad_yourself",values);
	}
	public void asignarDisponibilidadMes(List lista)
	{
		listaDispoMes=new ArrayList<Integer>();
		if(!lista.isEmpty())
		{
			Map row=(Map)lista.get(0);
			listaDispoMes.add((int)row.get("ndia1"));
			listaDispoMes.add((int)row.get("ndia2"));
			listaDispoMes.add((int)row.get("ndia3"));
			listaDispoMes.add((int)row.get("ndia4"));
			listaDispoMes.add((int)row.get("ndia5"));
			listaDispoMes.add((int)row.get("ndia6"));
			listaDispoMes.add((int)row.get("ndia7"));
			listaDispoMes.add((int)row.get("ndia8"));
			listaDispoMes.add((int)row.get("ndia9"));
			listaDispoMes.add((int)row.get("ndia10"));
			listaDispoMes.add((int)row.get("ndia11"));
			listaDispoMes.add((int)row.get("ndia12"));
			listaDispoMes.add((int)row.get("ndia13"));
			listaDispoMes.add((int)row.get("ndia14"));
			listaDispoMes.add((int)row.get("ndia15"));
			listaDispoMes.add((int)row.get("ndia16"));
			listaDispoMes.add((int)row.get("ndia17"));
			listaDispoMes.add((int)row.get("ndia18"));
			listaDispoMes.add((int)row.get("ndia19"));
			listaDispoMes.add((int)row.get("ndia20"));
			listaDispoMes.add((int)row.get("ndia21"));
			listaDispoMes.add((int)row.get("ndia22"));
			listaDispoMes.add((int)row.get("ndia23"));
			listaDispoMes.add((int)row.get("ndia24"));
			listaDispoMes.add((int)row.get("ndia25"));
			listaDispoMes.add((int)row.get("ndia26"));
			listaDispoMes.add((int)row.get("ndia27"));
			listaDispoMes.add((int)row.get("ndia28"));
			if((int)row.get("nmes")==2)
			{
				if((int)row.get("nanio")%4==0 && ((int)row.get("nanio")%400==0 || (int)row.get("nanio")%100!=0))
					listaDispoMes.add((int)row.get("ndia29"));
			}
			else
			{
				listaDispoMes.add((int)row.get("ndia29"));
				listaDispoMes.add((int)row.get("ndia30"));
			}
			if((int)row.get("nmes")==1 ||
					(int)row.get("nmes")==3||
					(int)row.get("nmes")==5||
					(int)row.get("nmes")==7||
					(int)row.get("nmes")==8||
					(int)row.get("nmes")==10||
					(int)row.get("nmes")==12)
				listaDispoMes.add((int)row.get("ndia31"));
		}
	}
	public void asignarDisponibilidad(List lista)
	{
		Calendar calIni=Calendar.getInstance();
		int anio=calIni.get(Calendar.YEAR);
		int anioSiguiente=anio+1;
		listaDispoActual=new ArrayList<Integer>();
		listaDispoSiguiente=new ArrayList<Integer>();
		for(int i=0;i<lista.size();i++){
			Map row=(Map)lista.get(i);
			if((row.get("nanio").toString()).equals(String.valueOf(anio))){
				System.out.println("actual anio->"+row.get("nanio"));
				listaDispoActual.add((int)row.get("ndia1"));
				listaDispoActual.add((int)row.get("ndia2"));
				listaDispoActual.add((int)row.get("ndia3"));
				listaDispoActual.add((int)row.get("ndia4"));
				listaDispoActual.add((int)row.get("ndia5"));
				listaDispoActual.add((int)row.get("ndia6"));
				listaDispoActual.add((int)row.get("ndia7"));
				listaDispoActual.add((int)row.get("ndia8"));
				listaDispoActual.add((int)row.get("ndia9"));
				listaDispoActual.add((int)row.get("ndia10"));
				listaDispoActual.add((int)row.get("ndia11"));
				listaDispoActual.add((int)row.get("ndia12"));
				listaDispoActual.add((int)row.get("ndia13"));
				listaDispoActual.add((int)row.get("ndia14"));
				listaDispoActual.add((int)row.get("ndia15"));
				listaDispoActual.add((int)row.get("ndia16"));
				listaDispoActual.add((int)row.get("ndia17"));
				listaDispoActual.add((int)row.get("ndia18"));
				listaDispoActual.add((int)row.get("ndia19"));
				listaDispoActual.add((int)row.get("ndia20"));
				listaDispoActual.add((int)row.get("ndia21"));
				listaDispoActual.add((int)row.get("ndia22"));
				listaDispoActual.add((int)row.get("ndia23"));
				listaDispoActual.add((int)row.get("ndia24"));
				listaDispoActual.add((int)row.get("ndia25"));
				listaDispoActual.add((int)row.get("ndia26"));
				listaDispoActual.add((int)row.get("ndia27"));
				listaDispoActual.add((int)row.get("ndia28"));
				listaDispoActual.add((int)row.get("ndia29"));
				listaDispoActual.add((int)row.get("ndia30"));
				listaDispoActual.add((int)row.get("ndia31"));
			}
			else if((row.get("nanio").toString()).equals(String.valueOf(anioSiguiente))){
				System.out.println("next anio->"+row.get("nanio"));
				listaDispoSiguiente.add((int)row.get("ndia1"));
				listaDispoSiguiente.add((int)row.get("ndia2"));
				listaDispoSiguiente.add((int)row.get("ndia3"));
				listaDispoSiguiente.add((int)row.get("ndia4"));
				listaDispoSiguiente.add((int)row.get("ndia5"));
				listaDispoSiguiente.add((int)row.get("ndia6"));
				listaDispoSiguiente.add((int)row.get("ndia7"));
				listaDispoSiguiente.add((int)row.get("ndia8"));
				listaDispoSiguiente.add((int)row.get("ndia9"));
				listaDispoSiguiente.add((int)row.get("ndia10"));
				listaDispoSiguiente.add((int)row.get("ndia11"));
				listaDispoSiguiente.add((int)row.get("ndia12"));
				listaDispoSiguiente.add((int)row.get("ndia13"));
				listaDispoSiguiente.add((int)row.get("ndia14"));
				listaDispoSiguiente.add((int)row.get("ndia15"));
				listaDispoSiguiente.add((int)row.get("ndia16"));
				listaDispoSiguiente.add((int)row.get("ndia17"));
				listaDispoSiguiente.add((int)row.get("ndia18"));
				listaDispoSiguiente.add((int)row.get("ndia19"));
				listaDispoSiguiente.add((int)row.get("ndia20"));
				listaDispoSiguiente.add((int)row.get("ndia21"));
				listaDispoSiguiente.add((int)row.get("ndia22"));
				listaDispoSiguiente.add((int)row.get("ndia23"));
				listaDispoSiguiente.add((int)row.get("ndia24"));
				listaDispoSiguiente.add((int)row.get("ndia25"));
				listaDispoSiguiente.add((int)row.get("ndia26"));
				listaDispoSiguiente.add((int)row.get("ndia27"));
				listaDispoSiguiente.add((int)row.get("ndia28"));
				listaDispoSiguiente.add((int)row.get("ndia29"));
				listaDispoSiguiente.add((int)row.get("ndia30"));
				listaDispoSiguiente.add((int)row.get("ndia31"));
			}
		}
		for(int a=0;a<listaDispoActual.size();a++){
			if(String.valueOf(listaDispoActual.get(a)).equals("-1")){
				  listaDispoActual.remove(a);
				  a--;
			  }
			if(String.valueOf(listaDispoSiguiente.get(a)).equals("-1")){
				  listaDispoSiguiente.remove(a);
				  a--;
			}
		}
	}
	
	public List updateDisponibilidadBD(ArrayList<Integer> lista){
		Object[] values=lista.toArray();
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_ModificarDisponibilidad_yourself",values);
	}
	public boolean isOperationCorrect(List lista)
	{
		Map row=(Map)lista.get(0);
		boolean correcto=row.get("resultado").toString().equals("correcto");
		if(correcto)return true;
		else return false;
	}
}
