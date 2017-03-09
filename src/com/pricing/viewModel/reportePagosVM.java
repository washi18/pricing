package com.pricing.viewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.Clients;

import com.pricing.dao.CReportePagosDAO;
import com.pricing.dao.CReporteReservaDAO;
import com.pricing.model.CDestino;
import com.pricing.model.CDestinoConHoteles;
import com.pricing.model.CHotel;
import com.pricing.model.CPasajero;
import com.pricing.model.CReportePagos;
import com.pricing.model.CReporteReserva;
import com.pricing.model.CServicio;
import com.pricing.model.CSubServicio;

public class reportePagosVM {
	
	//===============atributos======
	private ArrayList<CReportePagos> listaReportePagos;
	private CReportePagosDAO reportePagosDAO;
	private boolean estadoPagoPendiente;
	private boolean estadoPagoParcial;
	private boolean estadoPagoTotal;
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
	//===============getter and setter=======
	public String getFechaInicio() {
		return fechaInicio;
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
		visiblePagoParcial=true;
		/**Inicializando los objetos**/
		listaReportePagos=new ArrayList<CReportePagos>();
		fechaInicio="";
		fechaFinal="";
		reportePagosDAO=new CReportePagosDAO();
		reportePagosAnterior=new CReportePagos();
		/**Obtencion de las etiquetas de la base de datos**/
		/**Asignacion de las etiquetas a la listaEtiquetas**/
	}
	//====================metodos============
	
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
	@NotifyChange({"listaHoteles","listaHotelesTemp","listaDestinosconHoteles"})
	public void habilitarHotelesPOP(@BindingParam("chotel") CReportePagos reserva)
	{
		System.out.println("el valor de codCategoria es:"+reserva.getCodCategoria());
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
        		System.out.println("el valor de contador es:"+contador);
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
	@NotifyChange("listaPasajeros")
	public void habilitarPasajerosPOP(@BindingParam("cpasajero") CReportePagos pasajero)
	{
		System.out.println("entro a pasajeroVM");
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
		System.out.println("entro a pasajeroVM fin");
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
	@NotifyChange({"estadoPagoParcial","estadoPagoTotal"})
	public void seleccion_radio(@BindingParam("radio")String idRadio)
	{
		if(idRadio.equals("rdPagoParcial"))
		{
			estadoPagoParcial=true;
			estadoPagoTotal=false;
		}else if(idRadio.equals("rdPagoTotal"))
		{
			estadoPagoTotal=true;
			estadoPagoParcial=false;
		}
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
			System.out.println("el valor de pago es:"+NombrePago);
			reportePagosDAO.asignarVisaListaReportePagos(reportePagosDAO.recuperarPagosVisaBD(fechaInicio, fechaFinal,NombrePago));
			reportePagosDAO.asignarVisaListaReportePagos(reportePagosDAO.recuperarPagosPaypalBD(fechaInicio,fechaFinal,NombrePago));
			this.setListaReportePagos(reportePagosDAO.getListaReportePagos());
		}else{
			Clients.showNotification("Eliga un ESTADO DE PAGO", Clients.NOTIFICATION_TYPE_INFO, componente,"after_start",3700);
		}
	}
}
