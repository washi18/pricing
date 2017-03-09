package com.pricing.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.pricing.model.CDestino;
import com.pricing.model.CEstadistica_Pagos;
import com.pricing.model.CHotel;
import com.pricing.model.CPasajero;
import com.pricing.model.CReportePagos;
import com.pricing.model.CReporteReserva;
import com.pricing.model.CServicio;

public class CReportePagosDAO  extends CConexion{
	//==============atributos===================
	private ArrayList<CReportePagos> listaReportePagos;
	private CReportePagos reportePagos;
	private ArrayList<CDestino> listaDestinosReserva;
	private ArrayList<CHotel> listaHotelesReserva;
	private ArrayList<CServicio> listaServiciosReserva;
	private ArrayList<CPasajero> listaPasajerosReserva;
	private ArrayList<CEstadistica_Pagos> listaformasPagos;
	//=================getter and setter=============
	public ArrayList<CReportePagos> getListaReportePagos() {
		return listaReportePagos;
	}
	public ArrayList<CEstadistica_Pagos> getListaformasPagos() {
		return listaformasPagos;
	}
	public void setListaformasPagos(ArrayList<CEstadistica_Pagos> listaformasPagos) {
		this.listaformasPagos = listaformasPagos;
	}
	public void setListaReportePagos(ArrayList<CReportePagos> listaReportePagos) {
		this.listaReportePagos = listaReportePagos;
	}
	public CReportePagos getReportePagos() {
		return reportePagos;
	}
	public void setReportePagos(CReportePagos reportePagos) {
		this.reportePagos = reportePagos;
	}
	
	public ArrayList<CDestino> getListaDestinosReserva() {
		return listaDestinosReserva;
	}
	public void setListaDestinosReserva(ArrayList<CDestino> listaDestinosReserva) {
		this.listaDestinosReserva = listaDestinosReserva;
	}
	public ArrayList<CHotel> getListaHotelesReserva() {
		return listaHotelesReserva;
	}
	public void setListaHotelesReserva(ArrayList<CHotel> listaHotelesReserva) {
		this.listaHotelesReserva = listaHotelesReserva;
	}
	public ArrayList<CServicio> getListaServiciosReserva() {
		return listaServiciosReserva;
	}
	public void setListaServiciosReserva(ArrayList<CServicio> listaServiciosReserva) {
		this.listaServiciosReserva = listaServiciosReserva;
	}
	
	public ArrayList<CPasajero> getListaPasajerosReserva() {
		return listaPasajerosReserva;
	}
	public void setListaPasajerosReserva(ArrayList<CPasajero> listaPasajerosReserva) {
		this.listaPasajerosReserva = listaPasajerosReserva;
	}
	
	//=====================constructores==========
	public CReportePagosDAO()
	{
		super();
		this.listaReportePagos = new ArrayList<CReportePagos>();
	}
	public CReportePagosDAO(ArrayList<CReportePagos> listaReportePagos,
			CReportePagos reportePagos) {
		super();
		this.listaReportePagos = listaReportePagos;
		this.reportePagos = reportePagos;
	}
	//====================metodos====================
	public List recuperarPagosVisaBD(String fechaInicio,String fechaFinal,String estado)
	{
		System.out.println("aparece esto?");
		String[] values={fechaInicio,fechaFinal,estado};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_BuscarPagosVisaEntreFechasBD",values);
	}
	public List recuperarPagosPaypalBD(String fechaInicio,String fechaFinal,String estado)
	{
		String[] values={fechaInicio,fechaFinal,estado};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_BuscarPagosPaypalEntreFechasBD",values);
	}
	public List recuperarDestinosReservaBD(String codReserva)
	{
		String[] values={codReserva};
		return getEjecutorSQL().ejecutarProcedimiento("pricing_sp_buscardestinosreserva",values);
	}
	
	public List recuperarListaFormasPagosBD(String anio)
	{
		String[] values={anio};
		return getEjecutorSQL().ejecutarProcedimiento("pricing_sp_listaFormaPagos",values);
	}
	
	public List recuperarHotelesReservaBD(String codReserva,int codCategoriaHotel)
	{
		Object[] values={codReserva,codCategoriaHotel};
		return getEjecutorSQL().ejecutarProcedimiento("pricing_sp_buscarhotelesreserva",values);
	}
	
	public List recuperarServiciosReservaBD(String codReserva)
	{
		String[] values={codReserva};
		return getEjecutorSQL().ejecutarProcedimiento("pricing_sp_buscarserviciosreserva",values);
	}
	public List recuperarPasajerosReservaBD(String codReserva)
	{
		String []values={codReserva};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_buscarpasajerosreserva",values);
	}
	//=====este metodo asigna tanto para visa y tanto para paypal=====
	public void asignarVisaListaReportePagos(List lista)
	{
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaReportePagos.add(new CReportePagos((String)row.get("creservacod"),(Date)row.get("dfechainicio"), 
					(Date)row.get("dfechafin"),(Date)row.get("dfecha"),(int)row.get("categoriahotelcod"),
					(String)row.get("ctituloidioma1"),(int)row.get("nnropersonas"),
					(Number)row.get("nimporte"),(Number)row.get("nporcentaje"),(String)row.get("formapago"),
					(String)row.get("estado"),(Date)row.get("fechayhora_initx"),(String)row.get("codtransaccion"),
					(String)row.get("nom_th"),(String)row.get("capellidos"),
					(String)row.get("cnombres"),
					(String)row.get("csexo"),
					(String)row.get("cabrevtipodoc"),(String)row.get("cnrodoc"),
					(String)row.get("cnombreesp"),
					(String)row.get("nro_tarjeta"),(String)row.get("cestado"),(String)row.get("impuesto")));
		}
	}
	
	public void asignarListaPagos(List lista)
	{
		listaformasPagos=new ArrayList<CEstadistica_Pagos>();
		System.out.println("entra aqui..?");
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaformasPagos.add(new CEstadistica_Pagos((String)row.get("formapago"),(Date)row.get("fechapago")));
		}
	}
	
	public void asignarDestinosReserva(List lista)
	{
		System.out.println("entro aqui DAO 2");
		listaDestinosReserva=new ArrayList<CDestino>();
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaDestinosReserva.add(new CDestino((String)row.get("cdestino"),(int)row.get("ndestinocod"),(int)row.get("ncodpostal")));
		}
		System.out.println("termino aqui DAO 2");
	}
	public void asignarServiciosReserva(List lista)
	{
		listaServiciosReserva=new ArrayList<CServicio>();
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaServiciosReserva.add(new CServicio((String)row.get("cservicioindioma1"),(Number)row.get("nprecioservicio")));
		}
	}
	public void asignarHotelesReserva(List lista)
	{
		listaHotelesReserva=new ArrayList<CHotel>();
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaHotelesReserva.add(new CHotel((String)row.get("chotel"),(Number)row.get("npreciosimple"),(Number)row.get("npreciodoble"),(Number)row.get("npreciotriple"),(String)row.get("cdestino")));
		}
	}
	public void asignarPasajerosReserva(List lista)
	{
		System.out.println("entro a pasajero");
		listaPasajerosReserva=new ArrayList<CPasajero>();
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaPasajerosReserva.add(new CPasajero((String)row.get("cabrevtipodoc"),(String)row.get("capellidos"),
					(String)row.get("cnombres"),(String)row.get("cnombreesp"),(int)row.get("nedad"),(String)row.get("cnrodoc"),(String)row.get("csexo")));
		}
		System.out.println("entro a pasajero fin");
	}
}
