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
	private Number importe;
	private Number porcentaje;
	private String formaPago;
	private String estado;
	private Date fechayhoraTransaccion;
	private String codTransaccion;
	private String nombreCliente;
	private String apellidos;
	private String nombres;
	private String sexo;
	private int edad;
	private String tipoDocumento;
	private String nroDoc;
	private String nombrePais;
	private String nroTarjeta;
	private String estadoReserva;
	private String impuesto;
	private ArrayList<CPasajero> listaPasajeros;
	private int codCategoria;
	private String colornoExisteListaDestinos;
	private String colornoExisteListaHoteles;
	private String colornoExisteListaServicios;
	private String colornoExisteListaPasajeros;
	private ArrayList<CDestino> listaDestinos;
	private ArrayList<CHotel> listaHoteles;
	private ArrayList<CServicio> listaServicios;
	private Double montoTotal;
	private Double valorImpuesto;
	private boolean visiblepasajerospop=false;
	private boolean visibleDestinospop=false;
	private boolean visibleHotelespop=false;
	private boolean visibleServiciospop=false;
	private ArrayList<CDestinoConHoteles> listaDestinosconHoteles;
	//===============getter and setter=======
	public String getCodPago() {
		return codPago;
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
	public Number getImporte() {
		return importe;
	}
	public void setImporte(Number importe) {
		this.importe = importe;
	}
	public Number getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(Number porcentaje) {
		this.porcentaje = porcentaje;
	}
	public String getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getFechayhoraTransaccion() {
		return fechayhoraTransaccion;
	}
	public void setFechayhoraTransaccion(Date fechayhoraTransaccion) {
		this.fechayhoraTransaccion = fechayhoraTransaccion;
	}
	public String getCodTransaccion() {
		return codTransaccion;
	}
	public void setCodTransaccion(String codTransaccion) {
		this.codTransaccion = codTransaccion;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
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
	public String getNroTarjeta() {
		return nroTarjeta;
	}
	public void setNroTarjeta(String nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
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
	
	public String getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(String impuesto) {
		this.impuesto = impuesto;
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

	//==================constructores==================
	public CReportePagos()
	{
		this.codPago = "";
		this.codReserva = "";
		this.nombrePaquete = "";
		this.nroPersonas = 0;
		this.importe = 0;
		this.porcentaje =0;
		this.formaPago = "";
		this.estado = "";
		this.codTransaccion = "";
		this.nombreCliente = "";
		this.apellidos = "";
		this.nombres = "";
		this.edad=0;
		this.sexo="";
		this.tipoDocumento = "";
		this.nombrePais = "";
		this.nroTarjeta = "";
		this.nroDoc="";
		this.estadoReserva="";
		this.impuesto="";
	}

	public CReportePagos(String codReserva, Date fechaInicio,
			Date fechaFin, Date fecha,int codCategoria,String nombrePaquete, int nroPersonas,
			Number importe, Number porcentaje, String formaPago, String estado,
			Date fechayhoraTransaccion,
			String codTransaccion, String nombreCliente,
			String apellidos, String nombres, String sexo,
			String tipoDocumento,String nroDoc, String nombrePais, String nroTarjeta,String estadoReserva,String impuesto) {
		super();
		this.codReserva = codReserva;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.fecha = fecha;
		this.codCategoria=codCategoria;
		this.nombrePaquete = nombrePaquete;
		this.nroPersonas = nroPersonas;
		this.importe = importe;
		this.porcentaje = porcentaje;
		this.formaPago = formaPago;
		this.estado = estado;
		this.fechayhoraTransaccion = fechayhoraTransaccion;
		this.codTransaccion = codTransaccion;
		this.nombreCliente = nombreCliente;
		this.apellidos = apellidos;
		this.nombres = nombres;
		this.sexo = sexo;
		this.tipoDocumento = tipoDocumento;
		this.nroDoc=nroDoc;
		this.nombrePais = nombrePais;
		this.nroTarjeta = nroTarjeta;
		this.estadoReserva=estadoReserva;
		this.impuesto=impuesto;
		this.valorImpuesto=(Double.valueOf(impuesto)*importe.doubleValue())/100;
	    this.montoTotal=importe.doubleValue()+this.valorImpuesto;
	}
	
	
}
