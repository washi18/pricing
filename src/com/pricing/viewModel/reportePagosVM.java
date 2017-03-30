package com.pricing.viewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.Clients;

import com.pricing.dao.CReportePagosDAO;
import com.pricing.dao.CReporteReservaDAO;
import com.pricing.model.CActividad;
import com.pricing.model.CDestino;
import com.pricing.model.CDestinoConHoteles;
import com.pricing.model.CHotel;
import com.pricing.model.CPasajero;
import com.pricing.model.CReportePagos;
import com.pricing.model.CReporteReserva;
import com.pricing.model.CServicio;
import com.pricing.model.CServicioConSubServicios;
import com.pricing.model.CSubServicio;

public class reportePagosVM {
	
	//===============atributos======
	private ArrayList<CReportePagos> listaReportePagos;
	private CReportePagosDAO reportePagosDAO;
	private CReportePagos oReportePago;
	private boolean estadoPagoPendiente;
	private boolean estadoPagoParcial;
	private boolean estadoPagoTotal;
	private boolean estadoAmbos;
	private boolean visiblePagoParcial;
	private String fechaInicio;
	private String fechaFinal;
	private ArrayList<CPasajero> listaPasajeros;
	private ArrayList<CDestino> listaDestinos;
	private ArrayList<CHotel> listaHoteles;
	private ArrayList<CServicio> listaServicios;
	private CReportePagos reportePagosAnterior;
	private ArrayList<CDestinoConHoteles> listaDestinosconHoteles;
	private ArrayList<CHotel> listaHotelesTemp;
	private ArrayList<CActividad> listaActividades;
	private ArrayList<CSubServicio> listasubServicios;
	private ArrayList<CSubServicio> listaSubServiciosTemp;
	private ArrayList<CServicioConSubServicios> listaServicioconSubServicios;
	private boolean deshabilitarMetodoPago;
	private boolean desabilitarCodTransaccion;
	Date fecha=new Date();
	SimpleDateFormat formato=new SimpleDateFormat("yyyy-MM-dd");
	String fechaActual = formato.format(fecha);
	//===============getter and setter=======
	
	public String getFechaInicio() {
		return fechaInicio;
	}
	public boolean isDeshabilitarMetodoPago() {
		return deshabilitarMetodoPago;
	}
	public void setDeshabilitarMetodoPago(boolean deshabilitarMetodoPago) {
		this.deshabilitarMetodoPago = deshabilitarMetodoPago;
	}
	public boolean isDesabilitarCodTransaccion() {
		return desabilitarCodTransaccion;
	}
	public void setDesabilitarCodTransaccion(boolean desabilitarCodTransaccion) {
		this.desabilitarCodTransaccion = desabilitarCodTransaccion;
	}
	public CReportePagos getoReportePago() {
		return oReportePago;
	}
	public void setoReportePago(CReportePagos oReportePago) {
		this.oReportePago = oReportePago;
	}
	public boolean isEstadoAmbos() {
		return estadoAmbos;
	}
	public void setEstadoAmbos(boolean estadoAmbos) {
		this.estadoAmbos = estadoAmbos;
	}
	public ArrayList<CSubServicio> getListaSubServiciosTemp() {
		return listaSubServiciosTemp;
	}
	public void setListaSubServiciosTemp(ArrayList<CSubServicio> listaSubServiciosTemp) {
		this.listaSubServiciosTemp = listaSubServiciosTemp;
	}
	public ArrayList<CServicioConSubServicios> getListaServicioconSubServicios() {
		return listaServicioconSubServicios;
	}
	public void setListaServicioconSubServicios(ArrayList<CServicioConSubServicios> listaServicioconSubServicios) {
		this.listaServicioconSubServicios = listaServicioconSubServicios;
	}
	public ArrayList<CActividad> getListaActividades() {
		return listaActividades;
	}
	public void setListaActividades(ArrayList<CActividad> listaActividades) {
		this.listaActividades = listaActividades;
	}
	public ArrayList<CSubServicio> getListasubServicios() {
		return listasubServicios;
	}
	public void setListasubServicios(ArrayList<CSubServicio> listasubServicios) {
		this.listasubServicios = listasubServicios;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	public ArrayList<CReportePagos> getListaReportePagos() {
		return listaReportePagos;
	}
	public void setListaReportePagos(ArrayList<CReportePagos> listaReportePagos) {
		this.listaReportePagos = listaReportePagos;
	}
	public CReportePagosDAO getReportePagosDAO() {
		return reportePagosDAO;
	}
	public void setReportePagosDAO(CReportePagosDAO reportePagosDAO) {
		this.reportePagosDAO = reportePagosDAO;
	}
	
	public ArrayList<CPasajero> getListaPasajeros() {
		return listaPasajeros;
	}
	public void setListaPasajeros(ArrayList<CPasajero> listaPasajeros) {
		this.listaPasajeros = listaPasajeros;
	}
	
	public boolean isEstadoPagoPendiente() {
		return estadoPagoPendiente;
	}
	public void setEstadoPagoPendiente(boolean estadoPagoPendiente) {
		this.estadoPagoPendiente = estadoPagoPendiente;
	}
	public boolean isEstadoPagoParcial() {
		return estadoPagoParcial;
	}
	public void setEstadoPagoParcial(boolean estadoPagoParcial) {
		this.estadoPagoParcial = estadoPagoParcial;
	}
	public boolean isEstadoPagoTotal() {
		return estadoPagoTotal;
	}
	public void setEstadoPagoTotal(boolean estadoPagoTotal) {
		this.estadoPagoTotal = estadoPagoTotal;
	}
	public ArrayList<CDestino> getListaDestinos() {
		return listaDestinos;
	}
	public void setListaDestinos(ArrayList<CDestino> listaDestinos) {
		this.listaDestinos = listaDestinos;
	}
	public ArrayList<CHotel> getListaHoteles() {
		return listaHoteles;
	}
	public void setListaHoteles(ArrayList<CHotel> listaHoteles) {
		this.listaHoteles = listaHoteles;
	}
	public ArrayList<CServicio> getListaServicios() {
		return listaServicios;
	}
	public void setListaServicios(ArrayList<CServicio> listaServicios) {
		this.listaServicios = listaServicios;
	}
	
	public CReportePagos getReportePagosAnterior() {
		return reportePagosAnterior;
	}
	public void setReportePagosAnterior(CReportePagos reportePagosAnterior) {
		this.reportePagosAnterior = reportePagosAnterior;
	}
	
	public ArrayList<CDestinoConHoteles> getListaDestinosconHoteles() {
		return listaDestinosconHoteles;
	}
	public void setListaDestinosconHoteles(
			ArrayList<CDestinoConHoteles> listaDestinosconHoteles) {
		this.listaDestinosconHoteles = listaDestinosconHoteles;
	}
	public ArrayList<CHotel> getListaHotelesTemp() {
		return listaHotelesTemp;
	}
	public void setListaHotelesTemp(ArrayList<CHotel> listaHotelesTemp) {
		this.listaHotelesTemp = listaHotelesTemp;
	}
	
	public boolean isVisiblePagoParcial() {
		return visiblePagoParcial;
	}
	public void setVisiblePagoParcial(boolean visiblePagoParcial) {
		this.visiblePagoParcial = visiblePagoParcial;
	}
	//=====================constructores======
	@Init
	public void initVM()
	{
		estadoPagoParcial=false;
		estadoPagoPendiente=false;
		estadoPagoTotal=false;
		estadoAmbos=false;
		visiblePagoParcial=true;
		desabilitarCodTransaccion=false;
		deshabilitarMetodoPago=false;
		/**Inicializando los objetos**/
		listaReportePagos=new ArrayList<CReportePagos>();
		fechaInicio="";
		fechaFinal="";
		reportePagosDAO=new CReportePagosDAO();
		reportePagosAnterior=new CReportePagos();
		oReportePago=new CReportePagos();
		/**Obtencion de las etiquetas de la base de datos**/
		/**Asignacion de las etiquetas a la listaEtiquetas**/
//		reportePagosDAO.asignarValoresImpuesto(reporteReservaDAO.recuperarModoPago());
//		setImpuesto(reporteReservaDAO.getImpuesto());
		reportePagosDAO.asignarListaReportePagos(reportePagosDAO.recuperarReportePagosInicialBD(fechaActual));
		this.setListaReportePagos(reportePagosDAO.getListaReportePagos());
//		if(impuesto.isModoPorcentual()){
//			valorParcial=impuesto.getPorcentajeCobro()+"%";
//			esPorcentual=true;
//		}else{
//			valorParcial=impuesto.getPagoMinimo()+" x persona";
//			esPorcentual=false;
//		}
	}
	//====================metodos============
	
	@GlobalCommand
	public void actualizar_lista_pagos(){
		listaReportePagos.clear();
		String NombrePago="";
		if(estadoPagoParcial){
			NombrePago="PAGO PARCIAL";
		}else if(estadoPagoTotal){
			NombrePago="PAGO TOTAL";
		}
		if(fechaInicio.isEmpty() && fechaFinal.isEmpty()){
			reportePagosDAO.asignarListaReportePagos(reportePagosDAO.recuperarReportePagosInicialBD(fechaActual));
			this.setListaReportePagos(reportePagosDAO.getListaReportePagos());
		}else if(estadoAmbos){
			reportePagosDAO.asignarListaReportePagos(reportePagosDAO.recuperarPagosAmbosBD(fechaInicio,fechaFinal));
			this.setListaReportePagos(reportePagosDAO.getListaReportePagos());
		}else{
			reportePagosDAO.asignarListaReportePagos(reportePagosDAO.recuperarPagosBD(fechaInicio,fechaFinal,NombrePago));
			this.setListaReportePagos(reportePagosDAO.getListaReportePagos());
		}
		BindUtils.postNotifyChange(null, null, this, "listaReportePagos");
	}
	
	@Command
	@NotifyChange("listaDestinos")
	public void habilitarDestinosPOP(@BindingParam("cdestino") CReportePagos destino)
	{
		reportePagosDAO.asignarDestinosReserva(reportePagosDAO.recuperarDestinosReservaBD(destino.getCodReserva()));
		this.setListaDestinos(reportePagosDAO.getListaDestinosReserva());
		destino.setListaDestinos(this.getListaDestinos());
		if(!destino.getCodReserva().equals(reportePagosAnterior.getCodReserva()))
		{
			if(this.getListaDestinos().isEmpty()){
				destino.setVisibleDestinospop(false);
				destino.setColornoExisteListaDestinos("background: #DA0613;");
			}
			else{
				destino.setVisibleDestinospop(true);
				destino.setColornoExisteListaDestinos("background: #3BA420;");
			}
			reportePagosAnterior.setVisibleDestinospop(false);
			reportePagosAnterior=destino;
		}
		else{
			if(this.getListaDestinos().isEmpty()){
				destino.setVisibleDestinospop(false);
				destino.setColornoExisteListaDestinos("background: #DA0613;");
			}
			else{
				destino.setVisibleDestinospop(true);
				destino.setColornoExisteListaDestinos("background: #3BA420;");
			}
		}
		BindUtils.postNotifyChange(null, null, destino,"visibleDestinospop");
		BindUtils.postNotifyChange(null, null, destino,"listaDestinos");
		BindUtils.postNotifyChange(null, null, destino,"colornoExisteListaDestinos");
	}
	
	@Command
	public void asignarNameMetodoPago(@BindingParam("reporteReserva")CReportePagos reportePagos){
		reportePagos.setFormaPago(reportePagos.getFormaPago().toUpperCase());
		BindUtils.postNotifyChange(null, null, reportePagos, "metodoPago");
	}
	@Command
	@NotifyChange({"listaHoteles","listaHotelesTemp","listaDestinosconHoteles"})
	public void habilitarHotelesPOP(@BindingParam("chotel") CReportePagos reserva)
	{
		reportePagosDAO.asignarHotelesReserva(reportePagosDAO.recuperarHotelesReservaBD(reserva.getCodReserva(),reserva.getCodCategoria()));
		this.setListaHoteles(reportePagosDAO.getListaHotelesReserva());
		int valorincremento;
		listaDestinosconHoteles=new ArrayList<CDestinoConHoteles>();
		for(int i=0; i<listaHoteles.size();i=i+valorincremento)
        {
        	String DestinoAnterior=listaHoteles.get(i).getNombreDestino();
        	int contador=i;
        	valorincremento=0;
        	listaHotelesTemp=new ArrayList<CHotel>();
        	while(contador<listaHoteles.size() && listaHoteles.get(contador).getNombreDestino().equals(DestinoAnterior))
        	{
        		listaHotelesTemp.add(new CHotel(listaHoteles.get(contador).getcHotel(),listaHoteles.get(contador).getnPrecioSimple()));
        		valorincremento++;
        		contador++;
        	}
        	listaDestinosconHoteles.add(new CDestinoConHoteles(listaHoteles.get(i).getNombreDestino().toString(),listaHotelesTemp));
        }
		reserva.setListaDestinosconHoteles(listaDestinosconHoteles);
		
		if(!reserva.getCodReserva().equals(reportePagosAnterior.getCodReserva()))
		{
			if(this.getListaDestinosconHoteles().isEmpty()){
				reserva.setVisibleHotelespop(false);
				reserva.setColornoExisteListaHoteles("background: #DA0613;");
			}
			else{
				reserva.setVisibleHotelespop(true);
				reserva.setColornoExisteListaHoteles("background: #3BA420;");
			}
			reportePagosAnterior.setVisibleHotelespop(false);
			reportePagosAnterior=reserva;
		}
		else {
			if(this.getListaDestinosconHoteles().isEmpty()){
				reserva.setVisibleHotelespop(false);
				reserva.setColornoExisteListaHoteles("background: #DA0613;");
			}
			else{
				reserva.setVisibleHotelespop(true);
				reserva.setColornoExisteListaHoteles("background: #3BA420;");
			}
		}
		BindUtils.postNotifyChange(null, null, reserva,"visibleHotelespop");
		BindUtils.postNotifyChange(null, null, reserva,"colornoExisteListaHoteles");
		BindUtils.postNotifyChange(null, null, reserva,"listaDestinosconHoteles");
	}
	@Command
	@NotifyChange("listaServicios")
	public void habilitarServiciosPOP(@BindingParam("cservicio") CReportePagos servicio)
	{
		reportePagosDAO.asignarServiciosReserva(reportePagosDAO.recuperarServiciosReservaBD(servicio.getCodReserva()));
		this.setListaServicios(reportePagosDAO.getListaServiciosReserva());
		servicio.setListaServicios(this.getListaServicios());
		if(!servicio.getCodReserva().equals(reportePagosAnterior.getCodReserva()))
		{
			if(this.getListaServicios().isEmpty()){
				servicio.setVisibleServiciospop(false);
				servicio.setColornoExisteListaServicios("background: #DA0613;");
			}
			else{
				servicio.setVisibleServiciospop(true);
				servicio.setColornoExisteListaServicios("background: #3BA420;");
			}
			reportePagosAnterior.setVisibleServiciospop(false);
			reportePagosAnterior=servicio;
		}else{
			if(this.getListaServicios().isEmpty()){
				servicio.setVisibleServiciospop(false);
				servicio.setColornoExisteListaServicios("background: #DA0613;");
			}
			else{
				servicio.setVisibleServiciospop(true);
				servicio.setColornoExisteListaServicios("background: #3BA420;");
			}
		}
		BindUtils.postNotifyChange(null, null, servicio,"visibleServiciospop");
		BindUtils.postNotifyChange(null, null, servicio,"listaServicios");
		BindUtils.postNotifyChange(null, null, servicio,"colornoExisteListaServicios");
	}
	@Command
	@NotifyChange({"listaSubServicios","listaSubServiciosTemp","listaServiciosconSubServicios"})
	public void habilitarSubServiciosPOP(@BindingParam("creserva") CReportePagos subServicio)
	{
		reportePagosDAO.asignarSubServiciosReserva(reportePagosDAO.recuperarSubServiciosReservaBD(subServicio.getCodReserva()));
		this.setListasubServicios(reportePagosDAO.getListaSubServiciosReserva());
		int valorincremento;
		listaServicioconSubServicios=new ArrayList<CServicioConSubServicios>();
		for(int i=0; i<listasubServicios.size();i=i+valorincremento)
        {
        	String ServicioAnterior=listasubServicios.get(i).getcNombreServicio();
        	int contador=i;
        	valorincremento=0;
        	listaSubServiciosTemp=new ArrayList<CSubServicio>();
        	while(contador<listasubServicios.size() && listasubServicios.get(contador).getcNombreServicio().equals(ServicioAnterior))
        	{
        		listaSubServiciosTemp.add(new CSubServicio(listasubServicios.get(contador).getcSubServicioIndioma1(),listasubServicios.get(contador).getnPrecioServicio()));
        		valorincremento++;
        		contador++;
        	}
        	listaServicioconSubServicios.add(new CServicioConSubServicios(listasubServicios.get(i).getcNombreServicio().toString(),listaSubServiciosTemp));
        }
		subServicio.setListaServicioConSubServicios(listaServicioconSubServicios);
		
		if(!subServicio.getCodReserva().equals(reportePagosAnterior.getCodReserva()))
		{
			if(this.getListaServicioconSubServicios().isEmpty()){
				subServicio.setVisibleSubServiciopop(false);
				subServicio.setColornoExisteListaSubServicios("background: #DA0613;");
			}
			else{
				subServicio.setVisibleSubServiciopop(true);
				subServicio.setColornoExisteListaSubServicios("background: #3BA420;");
			}
			reportePagosAnterior.setVisibleSubServiciopop(false);
			reportePagosAnterior=subServicio;
		}
		else {
			subServicio.setVisibleHotelespop(true);
			if(this.getListaServicioconSubServicios().isEmpty()){
				subServicio.setVisibleSubServiciopop(false);
				subServicio.setColornoExisteListaSubServicios("background: #DA0613;");
			}
			else{
				subServicio.setVisibleSubServiciopop(true);
				subServicio.setColornoExisteListaSubServicios("background: #3BA420;");
			}
		}
		BindUtils.postNotifyChange(null, null, subServicio,"visibleSubServiciopop");
		BindUtils.postNotifyChange(null, null, subServicio,"colornoExisteListaSubServicios");
		BindUtils.postNotifyChange(null, null, subServicio,"listaServicioConSubServicios");
	}
	
	@Command
	@NotifyChange("listaActividades")
	public void habilitarActividadesPOP(@BindingParam("creserva") CReportePagos actividades)
	{
		reportePagosDAO.asignarActividadesReserva(reportePagosDAO.recuoperarActividadesReservaBD(actividades.getCodReserva()));
		this.setListaActividades(reportePagosDAO.getListaActividadesReserva());
		actividades.setListaActividades(this.getListaActividades());
		if(!actividades.getCodReserva().equals(reportePagosAnterior.getCodReserva())){
			if(this.getListaActividades().isEmpty()){
				actividades.setVisibleActividadespop(false);
				actividades.setColornoExisteListaActividades("background: #DA0613;");
			}else{
				actividades.setVisibleActividadespop(true);
				actividades.setColornoExisteListaActividades("background: #3BA420;");
			}
			reportePagosAnterior.setVisibleActividadespop(false);
			reportePagosAnterior=actividades;
		}else{
			if(this.getListaActividades().isEmpty()){
				actividades.setVisibleActividadespop(false);
				actividades.setColornoExisteListaActividades("background: #DA0613;");
			}else{
				actividades.setVisibleActividadespop(true);
				actividades.setColornoExisteListaActividades("background: #3BA420;");
			}
		}
		BindUtils.postNotifyChange(null, null, actividades,"visibleActividadespop");
		BindUtils.postNotifyChange(null, null, actividades,"listaActividades");
		BindUtils.postNotifyChange(null, null, actividades,"colornoExisteListaActividades");
	}
	
	@Command
	@NotifyChange("listaPasajeros")
	public void habilitarPasajerosPOP(@BindingParam("cpasajero") CReportePagos pasajero)
	{
		reportePagosDAO.asignarPasajerosReserva(reportePagosDAO.recuperarPasajerosReservaBD(pasajero.getCodReserva()));
		this.setListaPasajeros(reportePagosDAO.getListaPasajerosReserva());
		pasajero.setListaPasajeros(this.getListaPasajeros());
		if(!pasajero.getCodReserva().equals(reportePagosAnterior.getCodReserva())){
			if(this.getListaPasajeros().isEmpty()){
				pasajero.setVisiblepasajerospop(false);
				pasajero.setColornoExisteListaPasajeros("background: #DA0613;");
			}
			else{
				pasajero.setVisiblepasajerospop(true);
				pasajero.setColornoExisteListaPasajeros("background: #3BA420;");
			}
			reportePagosAnterior.setVisiblepasajerospop(false);
			reportePagosAnterior=pasajero;
		}
		else{
			if(this.getListaPasajeros().isEmpty()){
				pasajero.setVisiblepasajerospop(false);
				pasajero.setColornoExisteListaPasajeros("background: #DA0613;");
			}
			else{
				pasajero.setVisiblepasajerospop(true);
				pasajero.setColornoExisteListaPasajeros("background: #3BA420;");
			}
		}
		BindUtils.postNotifyChange(null, null, pasajero,"visiblepasajerospop");
		BindUtils.postNotifyChange(null, null, pasajero,"listaPasajeros");
		BindUtils.postNotifyChange(null, null, pasajero,"colornoExisteListaPasajeros");
	}
	@Command
	public void recuperarFechaDatebox(@BindingParam("fecha")Date fecha,@BindingParam("id")String id)
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		if(id.equals("db_desde"))
			fechaInicio=sdf.format(fecha);
		else
			fechaFinal=sdf.format(fecha);
	}
	
	@Command
	@NotifyChange({"estadoPagoParcial","estadoPagoTotal","estadoAmbos"})
	public void seleccion_radio(@BindingParam("radio")String idRadio)
	{
		if(idRadio.equals("rdPagoParcial"))
		{
			estadoPagoParcial=true;
			estadoPagoTotal=false;
			estadoAmbos=false;
		}else if(idRadio.equals("rdPagoTotal"))
		{
			estadoPagoTotal=true;
			estadoPagoParcial=false;
			estadoAmbos=false;
		}else if(idRadio.equals("rdAmbos"))
		{
			estadoPagoTotal=false;
			estadoPagoParcial=false;
			estadoAmbos=true;
		}
	}
	
	@Command
	@NotifyChange({"listaReportePagos","visibleParcial","visibleTotal"})
	public void ModificarReportePago(@BindingParam("reportePagos")CReportePagos reportePagos,@BindingParam("comp")Component comp){
		String NombrePago="";
		if(estadoPagoParcial){
			NombrePago="PAGO PARCIAL";
		}else if(estadoPagoTotal){
			visiblePagoParcial=false;
			NombrePago="PAGO TOTAL";
		}
		if(!validoParaModificar(reportePagos, comp))
			return;
		if(reportePagos.getEstadoReserva().equals("PENDIENTE DE PAGO")){
			reportePagos.setFormaPago("");
			reportePagos.setCodTransaccion("");
		}
		boolean correcto=reportePagosDAO.isOperationCorrect(reportePagosDAO.modificarEstadoPago(reportePagos.getCodReserva(), reportePagos.getEstadoReserva(),reportePagos.getFormaPago(),reportePagos.getCodTransaccion()));
		if(correcto)
			Clients.showNotification("La reserva fue marcado pagado satisfactoriamente", Clients.NOTIFICATION_TYPE_INFO, comp,"after_start",3700);
		else
			Clients.showNotification("La operacion fue fallida", Clients.NOTIFICATION_TYPE_ERROR, comp,"after_start",3700);
		listaReportePagos.clear();
		if(fechaInicio.isEmpty() && fechaFinal.isEmpty()){
			reportePagosDAO.asignarListaReportePagos(reportePagosDAO.recuperarReportePagosInicialBD(fechaActual));
			this.setListaReportePagos(reportePagosDAO.getListaReportePagos());
		}else if(estadoAmbos){
			reportePagosDAO.asignarListaReportePagos(reportePagosDAO.recuperarPagosAmbosBD(fechaInicio,fechaFinal));
			this.setListaReportePagos(reportePagosDAO.getListaReportePagos());
		}else{
			reportePagosDAO.asignarListaReportePagos(reportePagosDAO.recuperarPagosBD(fechaInicio,fechaFinal,NombrePago));
			this.setListaReportePagos(reportePagosDAO.getListaReportePagos());
		}
		reportePagos.setVisibleMarcarPagado(false);
		desabilitarCodTransaccion=false;
		deshabilitarMetodoPago=false;
		BindUtils.postNotifyChange(null, null,reportePagos,"visibleMarcarPagado");
		BindUtils.postNotifyChange(null, null,this,"desabilitarCodTransaccion");
		BindUtils.postNotifyChange(null, null,this,"deshabilitarMetodoPago");
	}
	
	public boolean validoParaModificar(CReportePagos reserva,Component comp){
		boolean valido=true;
		if(!reserva.isParcial() && !reserva.isTotal()){
			valido=false;
			Clients.showNotification("El Pago debe debe ser parcial o total", Clients.NOTIFICATION_TYPE_ERROR,
					comp, "before_start", 2700);
		}else if(reserva.getFormaPago()==null){
			valido=false;
			Clients.showNotification("El pago debe de tener un metodo de pago", Clients.NOTIFICATION_TYPE_ERROR,
					comp, "before_start", 2700);
		}else if(reserva.getCodTransaccion()==null){
			valido=false;
			Clients.showNotification("El pago debe de tener un codigo de transaccion", Clients.NOTIFICATION_TYPE_ERROR,
					comp, "before_start", 2700);
		}
		return valido;
	}
	@Command
	public void cambiarEstadoPago(@BindingParam("estado")String estado,@BindingParam("reportePago")CReportePagos reportePago){
		if(estado.equals("parcial")){
			reportePago.setEstadoReserva("PAGO PARCIAL");
			desabilitarCodTransaccion=false;
			deshabilitarMetodoPago=false;
		}else if(estado.equals("total")){
			reportePago.setEstadoReserva("PAGO TOTAL");
			desabilitarCodTransaccion=false;
			deshabilitarMetodoPago=false;
		}else if(estado.equals("pendiente")){
			reportePago.setEstadoReserva("PENDIENTE DE PAGO");
			reportePago.setFormaPago("");
			reportePago.setCodTransaccion("");
			desabilitarCodTransaccion=true;
			deshabilitarMetodoPago=true;
		}
		
		BindUtils.postNotifyChange(null, null, reportePago, "estadoReserva");
		BindUtils.postNotifyChange(null, null, reportePago, "formaPago");
		BindUtils.postNotifyChange(null, null, reportePago, "codTransaccion");
		BindUtils.postNotifyChange(null, null, this, "desabilitarCodTransaccion");
		BindUtils.postNotifyChange(null, null, this, "deshabilitarMetodoPago");
	}
	
	@Command
	public void habilitarPagos(@BindingParam("reportePago")CReportePagos reportePago){
		oReportePago.setVisibleMarcarPagado(false);
		BindUtils.postNotifyChange(null, null, oReportePago,"visibleMarcarPagado");
		oReportePago=reportePago;
		reportePago.setVisibleMarcarPagado(!reportePago.isVisibleMarcarPagado());
		desabilitarCodTransaccion=false;
		deshabilitarMetodoPago=false;
		BindUtils.postNotifyChange(null, null, reportePago,"visibleMarcarPagado");
		BindUtils.postNotifyChange(null, null, this,"desabilitarCodTransaccion");
		BindUtils.postNotifyChange(null, null, this,"deshabilitarMetodoPago");
	}
	
	@Command
	@NotifyChange({"listaReportePagos","visiblePagoParcial"})
	public void Buscar_Pagos(@BindingParam("componente")Component componente)
	{
		if(fechaInicio.isEmpty() || fechaFinal.isEmpty())
		{
			Clients.showNotification("Las fechas DESDE-HASTA son obligatorias ", Clients.NOTIFICATION_TYPE_INFO, componente,"after_start",3700);
		}
		else if(estadoPagoParcial || estadoPagoTotal)
		{
			/****Validando la fecha****/
			String NombrePago="";
			if(estadoPagoParcial){
				NombrePago="PAGO PARCIAL";
			}else if(estadoPagoTotal){
				visiblePagoParcial=false;
				NombrePago="PAGO TOTAL";
			}
			listaReportePagos.clear();
				reportePagosDAO.asignarListaReportePagos(reportePagosDAO.recuperarPagosBD(fechaInicio, fechaFinal,NombrePago));
				this.setListaReportePagos(reportePagosDAO.getListaReportePagos());
		}else if(estadoAmbos){
			listaReportePagos.clear();
			reportePagosDAO.asignarListaReportePagos(reportePagosDAO.recuperarPagosAmbosBD(fechaInicio, fechaFinal));
			this.setListaReportePagos(reportePagosDAO.getListaReportePagos());
		}else{
			Clients.showNotification("Eliga un ESTADO DE PAGO", Clients.NOTIFICATION_TYPE_INFO, componente,"after_start",3700);
		}
	}
}
