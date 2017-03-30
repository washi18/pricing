package com.pricing.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.pricing.model.CActividad;
import com.pricing.model.CDestino;
import com.pricing.model.CEstadistica_Pagos;
import com.pricing.model.CHotel;
import com.pricing.model.CPasajero;
import com.pricing.model.CReportePagos;
import com.pricing.model.CReporteReserva;
import com.pricing.model.CServicio;
import com.pricing.model.CSubServicio;

public class CReportePagosDAO  extends CConexion{
	//==============atributos===================
	private ArrayList<CReportePagos> listaReportePagos;
	private CReportePagos reportePagos;
	private ArrayList<CDestino> listaDestinosReserva;
	private ArrayList<CHotel> listaHotelesReserva;
	private ArrayList<CServicio> listaServiciosReserva;
	private ArrayList<CPasajero> listaPasajerosReserva;
	private ArrayList<CEstadistica_Pagos> listaformasPagos;
	private ArrayList<CActividad> listaActividadesReserva;
	private ArrayList<CSubServicio> listaSubServiciosReserva;
	//=================getter and setter=============
	
	public ArrayList<CReportePagos> getListaReportePagos() {
		return listaReportePagos;
	}
	public ArrayList<CActividad> getListaActividadesReserva() {
		return listaActividadesReserva;
	}
	public void setListaActividadesReserva(ArrayList<CActividad> listaActividadesReserva) {
		this.listaActividadesReserva = listaActividadesReserva;
	}
	public ArrayList<CSubServicio> getListaSubServiciosReserva() {
		return listaSubServiciosReserva;
	}
	public void setListaSubServiciosReserva(ArrayList<CSubServicio> listaSubServiciosReserva) {
		this.listaSubServiciosReserva = listaSubServiciosReserva;
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
	public List recuperarPagosBD(String fechaInicio,String fechaFinal,String estado)
	{
		String[] values={fechaInicio,fechaFinal,estado};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_BuscarReservasPagados",values);
	}
	public List recuperarPagosAmbosBD(String fechaInicio,String fechaFinal)
	{
		String[] values={fechaInicio,fechaFinal};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_BuscarReservasPagadosAmbos",values);
	}
	public List recuperarReportePagosInicialBD(String FechaActual)
	{
		String[] values={FechaActual};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_BuscarReservasPagadosInicial",values);
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
	public List recuperarSubServiciosReservaBD(String codReserva)
	{
		String[] values={codReserva};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_buscarsubserviciosreserva",values);
	}
	public List recuoperarActividadesReservaBD(String codReserva){
		String []values={codReserva};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_buscaractividadesreserva",values);
	}
	//=====este metodo asigna tanto para visa y tanto para paypal=====
	public void asignarListaReportePagos(List lista)
	{
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaReportePagos.add(new CReportePagos((String)row.get("creservacod"),(Date)row.get("dfechainicio"), 
					(Date)row.get("dfechafin"),(Date)row.get("dfecha"),(int)row.get("categoriahotelcod"),
					(String)row.get("ctituloidioma1"),(int)row.get("nnropersonas"),(String)row.get("cmetodopago"),
					(String)row.get("ccodtransaccion"),
					(String)row.get("capellidos"),
					(String)row.get("cnombres"),
					(String)row.get("csexo"),
					(String)row.get("cabrevtipodoc"),(String)row.get("cnrodoc"),
					(String)row.get("cnombreesp"),
					(String)row.get("cestado"),(Number)row.get("npreciopaquetepersona")));
		}
	}
	
	public void asignarListaPagos(List lista)
	{
		listaformasPagos=new ArrayList<CEstadistica_Pagos>();
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaformasPagos.add(new CEstadistica_Pagos((String)row.get("formapago"),(Date)row.get("fechapago")));
		}
	}
	
	public void asignarDestinosReserva(List lista)
	{
		listaDestinosReserva=new ArrayList<CDestino>();
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaDestinosReserva.add(new CDestino((String)row.get("cdestino"),(int)row.get("ndestinocod"),(int)row.get("ncodpostal")));
		}
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
		listaPasajerosReserva=new ArrayList<CPasajero>();
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaPasajerosReserva.add(new CPasajero((String)row.get("cabrevtipodoc"),(String)row.get("capellidos"),
					(String)row.get("cnombres"),(String)row.get("cnombreesp"),(int)row.get("nedad"),(String)row.get("cnrodoc"),(String)row.get("csexo")));
		}
	}
	public void asignarSubServiciosReserva(List lista)
	{
		listaSubServiciosReserva=new ArrayList<CSubServicio>();
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaSubServiciosReserva.add(new CSubServicio((String)row.get("csubservicioindioma1"),(Number)row.get("nprecioservicio"),(String)row.get("cservicioindioma1")));
		}
	}
	public void asignarActividadesReserva(List lista){
		listaActividadesReserva=new ArrayList<CActividad>();
		for(int i=0;i<lista.size();i++){
				Map row=(Map)lista.get(i);
				listaActividadesReserva.add(new CActividad((String)row.get("cactividadidioma1"),(Number)row.get("nprecioactividad")));
		}
	}
	public List modificarEstadoPago(String codReserva,String estado,String metodoPago,String codTransaccion){
		Object[]values={codReserva,estado,metodoPago,codTransaccion};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_ModificarEstadoPago",values);
	}
	public boolean isOperationCorrect(List lista)
	{
		Map row=(Map)lista.get(0);
		boolean correcto=row.get("resultado").toString().equals("correcto");
		if(correcto)return true;
		else return false;
	}
}
