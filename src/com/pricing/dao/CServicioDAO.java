package com.pricing.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pricing.model.CEtiqueta;
import com.pricing.model.CHotel;
import com.pricing.model.CServicio;
import com.pricing.model.CSubServicio;

public class CServicioDAO extends CConexion
{
	private CServicio oServicio;
	private ArrayList<CServicio> listaServicios;
	private ArrayList<CSubServicio> listaSubServicios;
	private ArrayList<CSubServicio> listaSubServiciosconServiciosNombre;
	//===================================
	public CServicio getoServicio() {
		return oServicio;
	}
	public void setoServicio(CServicio oServicio) {
		this.oServicio = oServicio;
	}
	public ArrayList<CServicio> getListaServicios() {
		return listaServicios;
	}
	public void setListaServicios(ArrayList<CServicio> listaServicios) {
		this.listaServicios = listaServicios;
	}
	public ArrayList<CSubServicio> getListaSubServicios() {
		return listaSubServicios;
	}
	public void setListaSubServicios(ArrayList<CSubServicio> listaSubServicios) {
		this.listaSubServicios = listaSubServicios;
	}
	
	public ArrayList<CSubServicio> getListaSubServiciosconServiciosNombre() {
		return listaSubServiciosconServiciosNombre;
	}
	public void setListaSubServiciosconServiciosNombre(
			ArrayList<CSubServicio> listaSubServiciosconServiciosNombre) {
		this.listaSubServiciosconServiciosNombre = listaSubServiciosconServiciosNombre;
	}
	//===================================
	public CServicioDAO() {
		// TODO Auto-generated constructor stub
		super();
		this.oServicio=new CServicio();
	}
	public CServicioDAO(CServicio servicio)
	{
		super();
		this.oServicio=servicio;
	}
	//==================
	public List recuperarServiciosBD()
	{
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarServicios");
	}
	public List recuperarTodosServiciosBD()
	{
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarTodosServicios");
	}
	public List recuperarTodosSubServiciosBD()
	{
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarTodosSuServicios");
	}
	public List recuperarServiciosconSubServiciosBD ()
	{
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarServiciosconSubServicios");
	}
	public void asignarListaServicios(List lista)
	{
		listaServicios=new ArrayList<CServicio>();
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaServicios.add(new CServicio((int)row.get("nserviciocod"),(String)row.get("cservicioindioma1"),
					(String)row.get("cservicioindioma2"), (String)row.get("cservicioindioma3"),
					(String)row.get("cservicioindioma4"), (String)row.get("cservicioindioma5"),
					(String)row.get("cdescripcionidioma1"), (String)row.get("cdescripcionidioma2"),
					(String)row.get("cdescripcionidioma3"),(String)row.get("cdescripcionidioma4"),
					(String)row.get("cdescripcionidioma5"),(int)row.get("crestriccionyesno"),
					(int)row.get("crestriccionnum"),(int)row.get("cincremento"),
					(String)row.get("curlimg"),(Number)row.get("nprecioservicio"),
					(boolean)row.get("bestado"),(String)row.get("clink")));
		}
	}
	public List recuperarSubServiciosBD()
	{
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarSubServicios");
	}
	public List buscarServiciosBD(String nombre){
		String[] values={nombre};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_BuscarServicios",values);
	}
	public List buscarSubServiciosBD(String nombre){
		String[] values={nombre};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_BuscarSubServicios",values);
	}
	public void asignarListaSubServicios(List lista)
	{
		listaSubServicios=new ArrayList<CSubServicio>();
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaSubServicios.add(new CSubServicio((int)row.get("nsubserviciocod"),
					(int)row.get("nserviciocod"),(String)row.get("csubservicioindioma1"),
					(String)row.get("csubservicioindioma2"), (String)row.get("csubservicioindioma3"),
					(String)row.get("csubservicioindioma4"), (String)row.get("csubservicioindioma5"),
					(String)row.get("cdescripcionidioma1"), (String)row.get("cdescripcionidioma2"),
					(String)row.get("cdescripcionidioma3"),(String)row.get("cdescripcionidioma4"),
					(String)row.get("cdescripcionidioma5"),(String)row.get("curlimg"),
					(String)row.get("clink"),(Number)row.get("nprecioservicio"),
					(boolean)row.get("bestado")));
		}
	}
	public List insertarServicio(CServicio servicio)
	{
		Object[] values={servicio.getcServicioIndioma1(),servicio.getcServicioIndioma2(),
				servicio.getcServicioIndioma3(),servicio.getcServicioIndioma4(),
				servicio.getcServicioIndioma5(),servicio.getcDescripcionIdioma1(),
				servicio.getcDescripcionIdioma2(),servicio.getcDescripcionIdioma3(),
				servicio.getcDescripcionIdioma4(),servicio.getcDescripcionIdioma5(),
				servicio.getcRestriccionYesNo(),servicio.getcRestriccionNum(),
				servicio.getcIncremento(),servicio.getcUrlImg(),servicio.getnPrecioServicio().doubleValue(),
				servicio.isbEstado(),servicio.getcLink()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_RegistrarServicio", values);
	}
	public List insertarSubServicio(CSubServicio subServicio)
	{
		Object[] values={subServicio.getnServicioCod(),subServicio.getcSubServicioIndioma1(),subServicio.getcSubServicioIndioma2(),subServicio.getcSubServicioIndioma3(),
				subServicio.getcSubServicioIndioma4(),subServicio.getcSubServicioIndioma5(),
				subServicio.getcDescripcionIdioma1(),subServicio.getcDescripcionIdioma2(),subServicio.getcDescripcionIdioma3(),
				subServicio.getcDescripcionIdioma4(),subServicio.getcDescripcionIdioma5(),
				subServicio.getcUrlImg(),subServicio.getcLink(),subServicio.getnPrecioServicio().doubleValue()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_RegistrarSubServicio", values);
	}
	
	public List modificarServicio(CServicio servicio)
	{
		Object[] values={servicio.getnServicioCod(),servicio.getcServicioIndioma1(),
				servicio.getcServicioIndioma2(),servicio.getcServicioIndioma3(),
				servicio.getcServicioIndioma4(),servicio.getcServicioIndioma5(),
				servicio.getcDescripcionIdioma1(),servicio.getcDescripcionIdioma2(),
				servicio.getcDescripcionIdioma3(),servicio.getcDescripcionIdioma4(),
				servicio.getcDescripcionIdioma5(),servicio.getcRestriccionYesNo(),
				servicio.getcRestriccionNum(),servicio.getcIncremento(),
				servicio.getcUrlImg(),servicio.getnPrecioServicio().doubleValue(),
				servicio.isbEstado(),servicio.getcLink()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_ModificarServicio", values);
	}
	public List modificarSubServicio(CSubServicio subServicio)
	{
		Object[] values={subServicio.getnSubServicioCod(),subServicio.getnServicioCod(),
				subServicio.getcSubServicioIndioma1(),
				subServicio.getcSubServicioIndioma2(),subServicio.getcSubServicioIndioma3(),
				subServicio.getcSubServicioIndioma4(),subServicio.getcSubServicioIndioma5(),
				subServicio.getcDescripcionIdioma1(),subServicio.getcDescripcionIdioma2(),
				subServicio.getcDescripcionIdioma3(),subServicio.getcDescripcionIdioma4(),
				subServicio.getcDescripcionIdioma5(),subServicio.getcUrlImg(),subServicio.getcLink(),
				subServicio.getnPrecioServicio().doubleValue(),subServicio.isbEstado()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_ModificarSubServicio", values);
	}
	public boolean isOperationCorrect(List lista)
	{
		Map row=(Map)lista.get(0);
		boolean correcto=row.get("resultado").toString().equals("correcto");
		if(correcto)return true;
		else return false;
	}
}
