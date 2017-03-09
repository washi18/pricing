package com.pricing.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pricing.model.CPaqueteServicio;

public class CPaqueteServicioDAO extends CConexion
{
	private CPaqueteServicio oPaqueteServicio;
	private ArrayList<CPaqueteServicio> listaPaqueteServicios;
	//==========================================
	public CPaqueteServicio getoPaqueteServicio() {
		return oPaqueteServicio;
	}
	public void setoPaqueteServicio(CPaqueteServicio oPaqueteServicio) {
		this.oPaqueteServicio = oPaqueteServicio;
	}
	public ArrayList<CPaqueteServicio> getListaPaqueteServicios() {
		return listaPaqueteServicios;
	}
	public void setListaPaqueteServicios(
			ArrayList<CPaqueteServicio> listaPaqueteServicios) {
		this.listaPaqueteServicios = listaPaqueteServicios;
	}
	//=================================
	public CPaqueteServicioDAO() {
		// TODO Auto-generated constructor stub
		super();
		this.oPaqueteServicio=new CPaqueteServicio();
	}
	public CPaqueteServicioDAO(CPaqueteServicio paqService)
	{
		super();
		this.oPaqueteServicio=paqService;
	}
	//===================================
	public List recuperarPaqueteServiciosBD(String codPaquete)
	{
		Object[] values={codPaquete};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarPaqueteServicios", values);
	}
	public void asignarListaPaqueteServicios(List lista)
	{
		listaPaqueteServicios=new ArrayList<CPaqueteServicio>();
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaPaqueteServicios.add(new CPaqueteServicio((int)row.get("codpaqueteservicio"),
					(String)row.get("cpaquetecod"),(int)row.get("nserviciocod")));
			
		}
	}
}
