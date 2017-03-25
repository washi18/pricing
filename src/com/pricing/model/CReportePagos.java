package com.pricing.model;

import java.util.ArrayList;
import java.util.Date;

public class CReportePagos {
	//=======atributos======
	private String codPago;
	private String codReserva;
	private Date fechaInicio;
	private Date fechaFin;
	private Date fecha;
	private String nombrePaquete;
	private int nroPersonas;
	private String formaPago;
	private String codTransaccion;
	private String apellidos;
	private String nombres;
	private String sexo;
	private int edad;
	private String tipoDocumento;
	private String nroDoc;
	private String nombrePais;
	private String estadoReserva;
	private ArrayList<CPasajero> listaPasajeros;
	private int codCategoria;
	private String colornoExisteListaDestinos;
	private String colornoExisteListaHoteles;
	private String colornoExisteListaServicios;
	private String colornoExisteListaPasajeros;
	private String colornoExisteListaSubServicios;
	private String colornoExisteListaActividades;
	private ArrayList<CDestino> listaDestinos;
	private ArrayList<CHotel> listaHoteles;
	private ArrayList<CServicio> listaServicios;
	private ArrayList<CSubServicio> listasubServicios;
	private ArrayList<CActividad> listaActividades;
	private Double montoTotal;
	private Double valorImpuesto;
	private boolean visiblepasajerospop=false;
	private boolean visibleDestinospop=false;
	private boolean visibleHotelespop=false;
	private boolean visibleServiciospop=false;
	private boolean visibleActividadespop=false;
	private boolean visibleSubServiciopop=false;
	private ArrayList<CDestinoConHoteles> listaDestinosconHoteles;
	private ArrayList<CServicioConSubServicios> listaServicioConSubServicios;
	private boolean isParcial;
	private boolean isTotal;
	private boolean visibleMarcarPagado;
	private Number nPrecioPaquetePersona;
	//===============getter and setter=======
	
	public String getCodPago() {
		return codPago;
	}
	
	public Number getnPrecioPaquetePersona() {
		return nPrecioPaquetePersona;
	}

	public void setnPrecioPaquetePersona(Number nPrecioPaquetePersona) {
		this.nPrecioPaquetePersona = nPrecioPaquetePersona;
	}

	public boolean isVisibleMarcarPagado() {
		return visibleMarcarPagado;
	}

	public void setVisibleMarcarPagado(boolean visibleMarcarPagado) {
		this.visibleMarcarPagado = visibleMarcarPagado;
	}

	public boolean isParcial() {
		return isParcial;
	}

	public void setParcial(boolean isParcial) {
		this.isParcial = isParcial;
	}

	public boolean isTotal() {
		return isTotal;
	}

	public void setTotal(boolean isTotal) {
		this.isTotal = isTotal;
	}

	public ArrayList<CServicioConSubServicios> getListaServicioConSubServicios() {
		return listaServicioConSubServicios;
	}

	public void setListaServicioConSubServicios(ArrayList<CServicioConSubServicios> listaServicioConSubServicios) {
		this.listaServicioConSubServicios = listaServicioConSubServicios;
	}

	public ArrayList<CSubServicio> getListasubServicios() {
		return listasubServicios;
	}

	public void setListasubServicios(ArrayList<CSubServicio> listasubServicios) {
		this.listasubServicios = listasubServicios;
	}

	public ArrayList<CActividad> getListaActividades() {
		return listaActividades;
	}

	public void setListaActividades(ArrayList<CActividad> listaActividades) {
		this.listaActividades = listaActividades;
	}

	public boolean isVisibleActividadespop() {
		return visibleActividadespop;
	}

	public void setVisibleActividadespop(boolean visibleActividadespop) {
		this.visibleActividadespop = visibleActividadespop;
	}

	public boolean isVisibleSubServiciopop() {
		return visibleSubServiciopop;
	}

	public void setVisibleSubServiciopop(boolean visibleSubServiciopop) {
		this.visibleSubServiciopop = visibleSubServiciopop;
	}

	public void setCodPago(String codPago) {
		this.codPago = codPago;
	}
	public String getCodReserva() {
		return codReserva;
	}
	public void setCodReserva(String codReserva) {
		this.codReserva = codReserva;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getNombrePaquete() {
		return nombrePaquete;
	}
	public void setNombrePaquete(String nombrePaquete) {
		this.nombrePaquete = nombrePaquete;
	}
	public int getNroPersonas() {
		return nroPersonas;
	}
	public void setNroPersonas(int nroPersonas) {
		this.nroPersonas = nroPersonas;
	}
	public String getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	public String getCodTransaccion() {
		return codTransaccion;
	}
	public void setCodTransaccion(String codTransaccion) {
		this.codTransaccion = codTransaccion;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	
	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getNombrePais() {
		return nombrePais;
	}
	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}
	
	public String getNroDoc() {
		return nroDoc;
	}

	public void setNroDoc(String nroDoc) {
		this.nroDoc = nroDoc;
	}

	public String getEstadoReserva() {
		return estadoReserva;
	}

	public void setEstadoReserva(String estadoReserva) {
		this.estadoReserva = estadoReserva;
	}
	
	public ArrayList<CPasajero> getListaPasajeros() {
		return listaPasajeros;
	}

	public void setListaPasajeros(ArrayList<CPasajero> listaPasajeros) {
		this.listaPasajeros = listaPasajeros;
	}

	public int getCodCategoria() {
		return codCategoria;
	}

	public void setCodCategoria(int codCategoria) {
		this.codCategoria = codCategoria;
	}

	public String getColornoExisteListaDestinos() {
		return colornoExisteListaDestinos;
	}

	public void setColornoExisteListaDestinos(String colornoExisteListaDestinos) {
		this.colornoExisteListaDestinos = colornoExisteListaDestinos;
	}

	public String getColornoExisteListaHoteles() {
		return colornoExisteListaHoteles;
	}

	public void setColornoExisteListaHoteles(String colornoExisteListaHoteles) {
		this.colornoExisteListaHoteles = colornoExisteListaHoteles;
	}

	public String getColornoExisteListaServicios() {
		return colornoExisteListaServicios;
	}

	public void setColornoExisteListaServicios(String colornoExisteListaServicios) {
		this.colornoExisteListaServicios = colornoExisteListaServicios;
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

	public Double getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(Double montoTotal) {
		this.montoTotal = montoTotal;
	}

	public Double getValorImpuesto() {
		return valorImpuesto;
	}

	public void setValorImpuesto(Double valorImpuesto) {
		this.valorImpuesto = valorImpuesto;
	}

	public boolean isVisiblepasajerospop() {
		return visiblepasajerospop;
	}

	public void setVisiblepasajerospop(boolean visiblepasajerospop) {
		this.visiblepasajerospop = visiblepasajerospop;
	}

	public boolean isVisibleDestinospop() {
		return visibleDestinospop;
	}

	public void setVisibleDestinospop(boolean visibleDestinospop) {
		this.visibleDestinospop = visibleDestinospop;
	}

	public boolean isVisibleHotelespop() {
		return visibleHotelespop;
	}

	public void setVisibleHotelespop(boolean visibleHotelespop) {
		this.visibleHotelespop = visibleHotelespop;
	}

	public boolean isVisibleServiciospop() {
		return visibleServiciospop;
	}

	public void setVisibleServiciospop(boolean visibleServiciospop) {
		this.visibleServiciospop = visibleServiciospop;
	}

	public String getColornoExisteListaPasajeros() {
		return colornoExisteListaPasajeros;
	}

	public void setColornoExisteListaPasajeros(String colornoExisteListaPasajeros) {
		this.colornoExisteListaPasajeros = colornoExisteListaPasajeros;
	}
	
	public ArrayList<CDestinoConHoteles> getListaDestinosconHoteles() {
		return listaDestinosconHoteles;
	}

	public void setListaDestinosconHoteles(
			ArrayList<CDestinoConHoteles> listaDestinosconHoteles) {
		this.listaDestinosconHoteles = listaDestinosconHoteles;
	}
	

	public String getColornoExisteListaSubServicios() {
		return colornoExisteListaSubServicios;
	}

	public void setColornoExisteListaSubServicios(String colornoExisteListaSubServicios) {
		this.colornoExisteListaSubServicios = colornoExisteListaSubServicios;
	}

	public String getColornoExisteListaActividades() {
		return colornoExisteListaActividades;
	}

	public void setColornoExisteListaActividades(String colornoExisteListaActividades) {
		this.colornoExisteListaActividades = colornoExisteListaActividades;
	}

	//==================constructores==================
	public CReportePagos()
	{
		this.codPago = "";
		this.codReserva = "";
		this.nombrePaquete = "";
		this.nroPersonas = 0;
		this.formaPago = "";
		this.codTransaccion = "";
		this.apellidos = "";
		this.nombres = "";
		this.edad=0;
		this.sexo="";
		this.tipoDocumento = "";
		this.nombrePais = "";
		this.nroDoc="";
		this.estadoReserva="";
		isParcial=false;
		isTotal=false;
		visibleMarcarPagado=false;
	}

	public CReportePagos(String codReserva, Date fechaInicio,
			Date fechaFin, Date fecha,int codCategoria,String nombrePaquete, int nroPersonas,
			String formaPago,
			String codTransaccion,
			String apellidos, String nombres, String sexo,
			String tipoDocumento,String nroDoc, String nombrePais,String estadoReserva,Number nPrecioPaquetePersona) {
		super();
		this.codReserva = codReserva;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.fecha = fecha;
		this.codCategoria=codCategoria;
		this.nombrePaquete = nombrePaquete;
		this.nroPersonas = nroPersonas;
		this.formaPago = formaPago;
		this.codTransaccion = codTransaccion;
		this.apellidos = apellidos;
		this.nombres = nombres;
		this.sexo = sexo;
		this.tipoDocumento = tipoDocumento;
		this.nroDoc=nroDoc;
		this.nombrePais = nombrePais;
		this.estadoReserva=estadoReserva;
		this.nPrecioPaquetePersona=nPrecioPaquetePersona;
		this.montoTotal=nroPersonas*nPrecioPaquetePersona.doubleValue();
		if(estadoReserva.equals("PAGO PARCIAL")){
			isParcial=true;
			isTotal=false;
		}else {
			isParcial=false;
			isTotal=true;
		}
//		this.valorImpuesto=(Double.valueOf(impuesto)*importe.doubleValue())/100;
//	    this.montoTotal=importe.doubleValue()+this.valorImpuesto;
	}
	
	
}
