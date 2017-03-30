package com.pricing.viewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.Clients;

import com.pricing.dao.CReporteReservaDAO;
import com.pricing.model.CActividad;
import com.pricing.model.CDestino;
import com.pricing.model.CDestinoConHoteles;
import com.pricing.model.CHotel;
import com.pricing.model.CImpuesto;
import com.pricing.model.CPasajero;
import com.pricing.model.CReportePagos;
import com.pricing.model.CReporteReserva;
import com.pricing.model.CServicio;
import com.pricing.model.CServicioConSubServicios;
import com.pricing.model.CSubServicio;

import sun.security.jca.GetInstance;

public class reporteReservasVM {
	//======atributos=====
	private ArrayList<CReporteReserva> listaReporteReserva;
	private CReporteReservaDAO reporteReservaDAO;
	private String FechaInicio;
	private String FechaFinal;
	private ArrayList<CDestino> listaDestinos;
	private ArrayList<CHotel> listaHoteles;
	private ArrayList<CHotel> listaHotelesTemp;
	private ArrayList<CServicio> listaServicios;
	private ArrayList<CSubServicio> listasubServicios;
	private ArrayList<CSubServicio> listaSubServiciosTemp;
	private ArrayList<CDestinoConHoteles> listaDestinosconHoteles;
	private ArrayList<CServicioConSubServicios> listaServicioconSubServicios;
	private CReporteReserva reporteReservaAnterior;
	private ArrayList<CPasajero> listaPasajeros;
	private ArrayList<CActividad> listaActividades;
	private CReporteReserva oReporteReserva;
	private boolean pagoParte;
	private boolean pagoTotal;
	private boolean visibleMarcarPagado;
	private boolean visibleParcial;
	private boolean visibleTotal;
	private CImpuesto impuesto;
	private String valorParcial;
	private boolean esPorcentual;
	Date fecha=new Date();
	SimpleDateFormat formato=new SimpleDateFormat("yyyy-MM-dd");
	String fechaActual = formato.format(fecha);
	//=======getter and setter=====
	
	public ArrayList<CReporteReserva> getListaReporteReserva() {
		return listaReporteReserva;
	}

	public boolean isVisibleParcial() {
		return visibleParcial;
	}

	public void setVisibleParcial(boolean visibleParcial) {
		this.visibleParcial = visibleParcial;
	}

	public boolean isVisibleTotal() {
		return visibleTotal;
	}

	public void setVisibleTotal(boolean visibleTotal) {
		this.visibleTotal = visibleTotal;
	}

	public boolean isEsPorcentual() {
		return esPorcentual;
	}

	public void setEsPorcentual(boolean esPorcentual) {
		this.esPorcentual = esPorcentual;
	}

	public String getValorParcial() {
		return valorParcial;
	}

	public void setValorParcial(String valorParcial) {
		this.valorParcial = valorParcial;
	}

	public CImpuesto getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(CImpuesto impuesto) {
		this.impuesto = impuesto;
	}

	public boolean isVisibleMarcarPagado() {
		return visibleMarcarPagado;
	}

	public void setVisibleMarcarPagado(boolean visibleMarcarPagado) {
		this.visibleMarcarPagado = visibleMarcarPagado;
	}

	public boolean isPagoParte() {
		return pagoParte;
	}

	public void setPagoParte(boolean pagoParte) {
		this.pagoParte = pagoParte;
	}

	public boolean isPagoTotal() {
		return pagoTotal;
	}

	public void setPagoTotal(boolean pagoTotal) {
		this.pagoTotal = pagoTotal;
	}

	public ArrayList<CActividad> getListaActividades() {
		return listaActividades;
	}

	public void setListaActividades(ArrayList<CActividad> listaActividades) {
		this.listaActividades = listaActividades;
	}

	public ArrayList<CPasajero> getListaPasajeros() {
		return listaPasajeros;
	}

	public void setListaPasajeros(ArrayList<CPasajero> listaPasajeros) {
		this.listaPasajeros = listaPasajeros;
	}

	public void setListaReporteReserva(
			ArrayList<CReporteReserva> listaReporteReserva) {
		this.listaReporteReserva = listaReporteReserva;
	}

	public CReporteReservaDAO getReporteReservaDAO() {
		return reporteReservaDAO;
	}
	public void setReporteReservaDAO(CReporteReservaDAO reporteReservaDAO) {
		this.reporteReservaDAO = reporteReservaDAO;
	}
	public String getFechaInicio() {
		return FechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		FechaInicio = fechaInicio;
	}
	public String getFechaFinal() {
		return FechaFinal;
	}
	public void setFechaFinal(String fechaFinal) {
		FechaFinal = fechaFinal;
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

	public ArrayList<CSubServicio> getListasubServicios() {
		return listasubServicios;
	}

	public void setListasubServicios(ArrayList<CSubServicio> listasubServicios) {
		this.listasubServicios = listasubServicios;
	}
	
	public CReporteReserva getReporteReservaAnterior() {
		return reporteReservaAnterior;
	}

	public void setReporteReservaAnterior(CReporteReserva reporteReservaAnterior) {
		this.reporteReservaAnterior = reporteReservaAnterior;
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
	
	public ArrayList<CServicioConSubServicios> getListaServicioconSubServicios() {
		return listaServicioconSubServicios;
	}

	public void setListaServicioconSubServicios(
			ArrayList<CServicioConSubServicios> listaServicioconSubServicios) {
		this.listaServicioconSubServicios = listaServicioconSubServicios;
	}
	
	public ArrayList<CSubServicio> getListaSubServiciosTemp() {
		return listaSubServiciosTemp;
	}

	public void setListaSubServiciosTemp(
			ArrayList<CSubServicio> listaSubServiciosTemp) {
		this.listaSubServiciosTemp = listaSubServiciosTemp;
	}

	//======metodos=====
	@Init
	public void initVM()
	{
		/**Inicializando los objetos**/
		listaReporteReserva=new ArrayList<CReporteReserva>();
		FechaInicio="";
		FechaFinal="";
		pagoParte=false;
		pagoTotal=false;
		visibleMarcarPagado=false;
		visibleParcial=false;
		visibleTotal=false;
		reporteReservaDAO=new CReporteReservaDAO();
		reporteReservaAnterior=new CReporteReserva();
		oReporteReserva=new CReporteReserva();
		impuesto=new CImpuesto();
		/**Obtencion de las etiquetas de la base de datos**/
		/**Asignacion de las etiquetas a la listaEtiquetas**/
		actualizar_valores_impuesto();
	}
	
	/* recuperamos valores de timpuesto y treserva*/
	@GlobalCommand
	public void actualizar_valores_impuesto(){
		reporteReservaDAO.asignarValoresImpuesto(reporteReservaDAO.recuperarModoPago());
		setImpuesto(reporteReservaDAO.getImpuesto());
		reporteReservaDAO.asignarListaReporteReservas(reporteReservaDAO.recuperarReporteReservasInicialBD(fechaActual));
		this.setListaReporteReserva(reporteReservaDAO.getListaReporteReservas());
		if(impuesto.isModoPorcentual()){
			valorParcial=impuesto.getPorcentajeCobro()+"%";
			esPorcentual=true;
		}else{
			valorParcial=impuesto.getPagoMinimo()+" x persona";
			esPorcentual=false;
		}
		BindUtils.postNotifyChange(null, null, this,"impuesto");
		BindUtils.postNotifyChange(null, null, this,"listaReporteReserva");
		BindUtils.postNotifyChange(null, null, this,"valorParcial");
		BindUtils.postNotifyChange(null, null, this,"esPorcentual");
	}
	
	/* Recupera actividades para mostrar popup con la lista de actividades*/
	@Command
	@NotifyChange("listaActividades")
	public void habilitarActividadesPOP(@BindingParam("creserva") CReporteReserva actividades)
	{
		reporteReservaDAO.asignarActividadesReserva(reporteReservaDAO.recuoperarActividadesReservaBD(actividades.getCodReserva()));
		this.setListaActividades(reporteReservaDAO.getListaActividadesReserva());
		actividades.setListaActividades(this.getListaActividades());
		if(!actividades.getCodReserva().equals(reporteReservaAnterior.getCodReserva())){
			if(this.getListaActividades().isEmpty()){
				actividades.setVisibleActividadespop(false);
				actividades.setColornoExisteListaActividades("background: #DA0613;");
			}else{
				actividades.setVisibleActividadespop(true);
				actividades.setColornoExisteListaActividades("background: #3BA420;");
			}
			reporteReservaAnterior.setVisibleActividadespop(false);
			reporteReservaAnterior=actividades;
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
	
	/* Recupera pasajeros para mostrar popup con la lista de pasajeros*/
	@Command
	@NotifyChange("listaPasajeros")
	public void habilitarPasajerosPOP(@BindingParam("creserva") CReporteReserva pasajero)
	{
		reporteReservaDAO.asignarPasajerosReserva(reporteReservaDAO.recuperarPasajerosReservaBD(pasajero.getCodReserva()));
		this.setListaPasajeros(reporteReservaDAO.getListaPasajerosReserva());
		pasajero.setListaPasajeros(this.getListaPasajeros());
		if(!pasajero.getCodReserva().equals(reporteReservaAnterior.getCodReserva())){
			if(this.getListaPasajeros().isEmpty()){
				pasajero.setVisiblePasajerospop(false);
				pasajero.setColornoExisteListaPasajeros("background: #DA0613;");
			}
			else{
				pasajero.setVisiblePasajerospop(true);
				pasajero.setColornoExisteListaPasajeros("background: #3BA420;");
			}
			reporteReservaAnterior.setVisiblePasajerospop(false);
			reporteReservaAnterior=pasajero;
		}
		else{
			if(this.getListaPasajeros().isEmpty()){
				pasajero.setVisiblePasajerospop(false);
				pasajero.setColornoExisteListaPasajeros("background: #DA0613;");
			}
			else{
				pasajero.setVisiblePasajerospop(true);
				pasajero.setColornoExisteListaPasajeros("background: #3BA420;");
			}
		}
		BindUtils.postNotifyChange(null, null, pasajero,"visiblePasajerospop");
		BindUtils.postNotifyChange(null, null, pasajero,"listaPasajeros");
		BindUtils.postNotifyChange(null, null, pasajero,"colornoExisteListaPasajeros");
	}
	
	/* Recupera destinos para mostrar popup con la lista de destinos*/
	@Command
	@NotifyChange("listaDestinos")
	public void habilitarDestinosPOP(@BindingParam("creserva") CReporteReserva destinos)
	{
		reporteReservaDAO.asignarDestinosReserva(reporteReservaDAO.recuperarDestinosReservaBD(destinos.getCodReserva()));
		this.setListaDestinos(reporteReservaDAO.getListaDestinosReserva());
		destinos.setListaDestinos(this.getListaDestinos());
		if(!destinos.getCodReserva().equals(reporteReservaAnterior.getCodReserva()))
		{
			if(this.getListaDestinos().isEmpty()){
				destinos.setVisibleDestinospop(false);
				destinos.setColornoExisteListaDestinos("background: #DA0613;");
			}
			else{
				destinos.setVisibleDestinospop(true);
				destinos.setColornoExisteListaDestinos("background: #3BA420;");
			}
			reporteReservaAnterior.setVisibleDestinospop(false);
			reporteReservaAnterior=destinos;
		}
		else{
			if(this.getListaDestinos().isEmpty()){
				destinos.setVisibleDestinospop(false);
				destinos.setColornoExisteListaDestinos("background: #DA0613;");
			}
			else{
				destinos.setVisibleDestinospop(true);
				destinos.setColornoExisteListaDestinos("background: #3BA420;");
			}
		}
		BindUtils.postNotifyChange(null, null, destinos,"visibleDestinospop");
		BindUtils.postNotifyChange(null, null, destinos,"listaDestinos");
		BindUtils.postNotifyChange(null, null, destinos,"colornoExisteListaDestinos");
	}
	
	/* Recupera subServicios para mostrar popup con la lista de subservicios*/
	@Command
	@NotifyChange({"listaSubServicios","listaSubServiciosTemp","listaServiciosconSubServicios"})
	public void habilitarSubServiciosPOP(@BindingParam("creserva") CReporteReserva hoteles)
	{
		reporteReservaDAO.asignarSubServiciosReserva(reporteReservaDAO.recuperarSubServiciosReservaBD(hoteles.getCodReserva()));
		this.setListasubServicios(reporteReservaDAO.getListaSubServiciosReserva());
		int valorincremento;
		listaServicioconSubServicios=new ArrayList<CServicioConSubServicios>();
		for(int i=0; i<listasubServicios.size();i=i+valorincremento)
        {
        	String ServicioAnterior=listasubServicios.get(i).getcNombreServicio();
        	int contador=i;
        	valorincremento=0;
        	listaSubServiciosTemp=new ArrayList<CSubServicio>();
        	//==================determinamos los subservicios de cada servicio=========
        	while(contador<listasubServicios.size() && listasubServicios.get(contador).getcNombreServicio().equals(ServicioAnterior))
        	{
        		listaSubServiciosTemp.add(new CSubServicio(listasubServicios.get(contador).getcSubServicioIndioma1(),listasubServicios.get(contador).getnPrecioServicio()));
        		valorincremento++;
        		contador++;
        	}
        	listaServicioconSubServicios.add(new CServicioConSubServicios(listasubServicios.get(i).getcNombreServicio().toString(),listaSubServiciosTemp));
        }
		hoteles.setListaServicioConSubServicios(listaServicioconSubServicios);
		
		if(!hoteles.getCodReserva().equals(reporteReservaAnterior.getCodReserva()))
		{
			if(this.getListaServicioconSubServicios().isEmpty()){
				hoteles.setVisibleSubServiciopop(false);
				hoteles.setColornoExisteListaSubServicios("background: #DA0613;");
			}
			else{
				hoteles.setVisibleSubServiciopop(true);
				hoteles.setColornoExisteListaSubServicios("background: #3BA420;");
			}
			reporteReservaAnterior.setVisibleSubServiciopop(false);
			reporteReservaAnterior=hoteles;
		}
		else {
			hoteles.setVisibleHotelespop(true);
			if(this.getListaServicioconSubServicios().isEmpty()){
				hoteles.setVisibleSubServiciopop(false);
				hoteles.setColornoExisteListaSubServicios("background: #DA0613;");
			}
			else{
				hoteles.setVisibleSubServiciopop(true);
				hoteles.setColornoExisteListaSubServicios("background: #3BA420;");
			}
		}
		BindUtils.postNotifyChange(null, null, hoteles,"visibleSubServiciopop");
		BindUtils.postNotifyChange(null, null, hoteles,"colornoExisteListaSubServicios");
		BindUtils.postNotifyChange(null, null, hoteles,"listaServicioConSubServicios");
	}
	
	
	/* Recupera hoteles para mostrar popup con la lista de actividades*/
	@Command
	@NotifyChange({"listaHoteles","listaHotelesTemp","listaDestinosconHoteles"})
	public void habilitarHotelesPOP(@BindingParam("creserva") CReporteReserva reserva)
	{
		reporteReservaDAO.asignarHotelesReserva(reporteReservaDAO.recuperarHotelesReservaBD(reserva.getCodReserva(),reserva.getCodCategoria()));
		this.setListaHoteles(reporteReservaDAO.getListaHotelesReserva());
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
		
		if(!reserva.getCodReserva().equals(reporteReservaAnterior.getCodReserva()))
		{
			if(this.getListaDestinosconHoteles().isEmpty()){
				reserva.setVisibleHotelespop(false);
				reserva.setColornoExisteListaHoteles("background: #DA0613;");
			}
			else{
				reserva.setVisibleHotelespop(true);
				reserva.setColornoExisteListaHoteles("background: #3BA420;");
			}
			reporteReservaAnterior.setVisibleHotelespop(false);
			reporteReservaAnterior=reserva;
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
	
	/* Recupera servicios BD para mostrar popup con la lista de actividades*/
	@Command
	@NotifyChange("listaServicios")
	public void habilitarServiciosPOP(@BindingParam("creserva") CReporteReserva servicio)
	{
		reporteReservaDAO.asignarServiciosReserva(reporteReservaDAO.recuperarServiciosReservaBD(servicio.getCodReserva()));
		this.setListaServicios(reporteReservaDAO.getListaServiciosReserva());
		servicio.setListaServicios(this.getListaServicios());
		if(!servicio.getCodReserva().equals(reporteReservaAnterior.getCodReserva()))
		{
			if(this.getListaServicios().isEmpty()){
				servicio.setVisibleServiciospop(false);
				servicio.setColornoExisteListaServicios("background: #DA0613;");
			}
			else{
				servicio.setVisibleServiciospop(true);
				servicio.setColornoExisteListaServicios("background: #3BA420;");
			}
			reporteReservaAnterior.setVisibleServiciospop(false);
			reporteReservaAnterior=servicio;
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
	
	/* asignando fechas de busqueda desde-hasta*/
	@Command
	@NotifyChange({"FechaInicio","FechaFinal"})
	public void recuperarFechaDatebox(@BindingParam("fecha")Date fecha,@BindingParam("id")String id)
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		if(id.equals("db_desde"))
			FechaInicio=sdf.format(fecha);
		else
			FechaFinal=sdf.format(fecha);
	}
	
	/* modoficar el estado de pago en reserva*/
	@Command
	public void cambiarEstadoPago(@BindingParam("estado")String estado,@BindingParam("reporteReserva")CReporteReserva reporteReserva){
		if(estado.equals("parcial")){
			visibleParcial=true;
			visibleTotal=false;
			reporteReserva.setPagoParte(true);
			reporteReserva.setPagoTotal(false);
			reporteReserva.setEstado("PAGO PARCIAL");
		}else{
			visibleParcial=false;
			visibleTotal=true;
			reporteReserva.setPagoParte(false);
			reporteReserva.setPagoTotal(true);
			reporteReserva.setEstado("PAGO TOTAL");
		}
		
		BindUtils.postNotifyChange(null, null, reporteReserva, "pagoParte");
		BindUtils.postNotifyChange(null, null, reporteReserva, "pagoTotal");
		BindUtils.postNotifyChange(null, null, this, "visibleParcial");
		BindUtils.postNotifyChange(null, null, this, "visibleTotal");
	}
	
	@Command
	@NotifyChange({"listaReporteReserva","visibleParcial","visibleTotal"})
	public void ModificarReporteReserva(@BindingParam("reporteReserva")CReporteReserva reporteReserva,@BindingParam("comp")Component comp){
		if(!validoParaModificar(reporteReserva, comp))
			return;
		boolean correcto=reporteReservaDAO.isOperationCorrect(reporteReservaDAO.modificarEstadoReserva(reporteReserva.getCodReserva(), reporteReserva.getEstado(),reporteReserva.getMetodoPago(),reporteReserva.getCodTransaccion()));
		if(correcto)
			Clients.showNotification("La reserva fue marcado pagado satisfactoriamente", Clients.NOTIFICATION_TYPE_INFO, comp,"after_start",3700);
		else
			Clients.showNotification("La operacion fue fallida", Clients.NOTIFICATION_TYPE_ERROR, comp,"after_start",3700);
		if(FechaInicio.isEmpty() && FechaFinal.isEmpty()){
			reporteReservaDAO.asignarListaReporteReservas(reporteReservaDAO.recuperarReporteReservasInicialBD(fechaActual));
			this.setListaReporteReserva(reporteReservaDAO.getListaReporteReservas());
		}else{
			reporteReservaDAO.asignarListaReporteReservas(reporteReservaDAO.recuperarReporteReservasBD(FechaInicio,FechaFinal));
			this.setListaReporteReserva(reporteReservaDAO.getListaReporteReservas());
		}
		visibleParcial=false;
		visibleTotal=false;
	}
	
	@Command
	public void asignarNameMetodoPago(@BindingParam("reporteReserva")CReporteReserva reporteReserva){
		reporteReserva.setMetodoPago(reporteReserva.getMetodoPago().toUpperCase());
		BindUtils.postNotifyChange(null, null, reporteReserva, "metodoPago");
	}
	
	/* abrir contendor para marcar pagado una reserva*/
	@Command
	public void habilitarPagos(@BindingParam("reporteReserva")CReporteReserva reporteReserva){
		visibleParcial=false;
		visibleTotal=false;
		oReporteReserva.setVisibleMarcarPagado(false);
		BindUtils.postNotifyChange(null, null, oReporteReserva,"visibleMarcarPagado");
		oReporteReserva=reporteReserva;
		reporteReserva.setVisibleMarcarPagado(!reporteReserva.isVisibleMarcarPagado());
		reporteReserva.setPagoParte(false);
		reporteReserva.setPagoTotal(false);
		BindUtils.postNotifyChange(null, null, reporteReserva,"visibleMarcarPagado");
		BindUtils.postNotifyChange(null, null, reporteReserva,"pagoParte");
		BindUtils.postNotifyChange(null, null, reporteReserva,"pagoTotal");
		BindUtils.postNotifyChange(null, null, this,"visibleParcial");
		BindUtils.postNotifyChange(null, null, this,"visibleTotal");
	}
	
	public boolean validoParaModificar(CReporteReserva reserva,Component comp){
		boolean valido=true;
		if(!reserva.isPagoParte() && !reserva.isPagoTotal()){
			valido=false;
			Clients.showNotification("El Pago debe debe ser parcial o total", Clients.NOTIFICATION_TYPE_ERROR,
					comp, "before_start", 2700);
		}else if(reserva.getMetodoPago()==null){
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
	
	/* Busca reserva entre dos fechas*/
	@Command
	@NotifyChange({"listaReporteReserva","reporteReserva"})
	public void Buscar_Reservas(@BindingParam("componente")Component componente)
	{
		if(FechaInicio.isEmpty() || FechaFinal.isEmpty())
		{
			Clients.showNotification("Las fechas DESDE-HASTA son obligatorias ", Clients.NOTIFICATION_TYPE_INFO, componente,"after_start",3700);
		}
		else
		{
			/****Validando la fecha****/
			listaReporteReserva.clear();
			reporteReservaDAO.asignarListaReporteReservas(reporteReservaDAO.recuperarReporteReservasBD(FechaInicio,FechaFinal));
			this.setListaReporteReserva(reporteReservaDAO.getListaReporteReservas());
		}
	}
}
