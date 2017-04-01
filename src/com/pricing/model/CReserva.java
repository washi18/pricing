package com.pricing.model;

import java.util.ArrayList;
import java.util.Date;

import com.pricing.dao.CPaqueteDAO;

public class CReserva 
{
	private String cReservaCod;// VARCHAR(10),			--codigo de la reserva que se pretende pagar
	private Date dFechaInicio;// date,				--fecha de inicio del tour
	private Date dFechaFin;// date,					--fecha que culmina el tour
	private Date dFecha;// timestamp,				--fecha y hora de realizacion de la reserva
	private String cContacto;// varchar(100),		--Nombre de la persona que realiza la reserva
	private String cEmail;// varchar(100),				--email del pasajero que genera la reserva
	private String cTelefono;// varchar(50),				--telefono del pasajero que genera la reserva
	private Number nPrecioPaquetePersona;// decimal(10,2),            --precio del paquete de acuerdo a la cantidad de personas en la reserva
	private int nNroPersonas;// int,				--numero de personas que integran el tour
	private String cInformacionAdicional;// varchar(300),		--informacion adicional registrada por el cliente
	private String cEstado;// varchar(20),				--estado de una reserva: PENDIENTE DE PAGO,PAGO PARCIAL, PAGO TOTAL
	private String cMetodoPago;//varchar(20)
	private String cCodTransaccion;//varchar(20)
	private Date dFechaArribo;//Date
	/**Otros Atributos de reserva ajenos a TReserva**/
	private CPaquete oPaquete;
	private ArrayList<CPaquete> listaPaquetes;
	private CPasajero oPasajeroReservante;
	private CCupon oCupon;
	//============================
	public String getcReservaCod() {
		return cReservaCod;
	}
	public void setcReservaCod(String cReservaCod) {
		this.cReservaCod = cReservaCod;
	}
	public Date getdFecha() {
		return dFecha;
	}
	public void setdFecha(Date dFecha) {
		this.dFecha = dFecha;
	}
	public String getcEmail() {
		return cEmail;
	}
	public void setcEmail(String cEmail) {
		this.cEmail = cEmail;
	}
	public String getcTelefono() {
		return cTelefono;
	}
	public void setcTelefono(String cTelefono) {
		this.cTelefono = cTelefono;
	}
	public Number getnPrecioPaquetePersona() {
		return nPrecioPaquetePersona;
	}
	public void setnPrecioPaquetePersona(Number nPrecioPaquetePersona) {
		this.nPrecioPaquetePersona = nPrecioPaquetePersona;
	}
	public int getnNroPersonas() {
		return nNroPersonas;
	}
	public void setnNroPersonas(int nNroPersonas) {
		this.nNroPersonas = nNroPersonas;
	}
	public String getcInformacionAdicional() {
		return cInformacionAdicional;
	}
	public void setcInformacionAdicional(String cInformacionAdicional) {
		this.cInformacionAdicional = cInformacionAdicional;
	}
	public String getcEstado() {
		return cEstado;
	}
	public void setcEstado(String cEstado) {
		this.cEstado = cEstado;
	}
	public String getcContacto() {
		return cContacto;
	}
	public void setcContacto(String cContacto) {
		this.cContacto = cContacto;
	}
	public Date getdFechaInicio() {
		return dFechaInicio;
	}
	public void setdFechaInicio(Date dFechaInicio) {
		this.dFechaInicio = dFechaInicio;
	}
	public Date getdFechaFin() {
		return dFechaFin;
	}
	public void setdFechaFin(Date dFechaFin) {
		this.dFechaFin = dFechaFin;
	}
	public Date getdFechaArribo() {
		return dFechaArribo;
	}
	public void setdFechaArribo(Date dFechaArribo) {
		this.dFechaArribo = dFechaArribo;
	}
	public ArrayList<CPaquete> getListaPaquetes() {
		return listaPaquetes;
	}
	public void setListaPaquetes(ArrayList<CPaquete> listaPaquetes) {
		this.listaPaquetes = listaPaquetes;
	}
	public CPaquete getoPaquete() {
		return oPaquete;
	}
	public void setoPaquete(CPaquete oPaquete) {
		this.oPaquete = oPaquete;
	}
	public CPasajero getoPasajeroReservante() {
		return oPasajeroReservante;
	}
	public void setoPasajeroReservante(CPasajero oPasajeroReservante) {
		this.oPasajeroReservante = oPasajeroReservante;
	}
	public CCupon getoCupon() {
		return oCupon;
	}
	public void setoCupon(CCupon oCupon) {
		this.oCupon = oCupon;
	}
	public String getcMetodoPago() {
		return cMetodoPago;
	}
	public void setcMetodoPago(String cMetodoPago) {
		this.cMetodoPago = cMetodoPago;
	}
	public String getcCodTransaccion() {
		return cCodTransaccion;
	}
	public void setcCodTransaccion(String cCodTransaccion) {
		this.cCodTransaccion = cCodTransaccion;
	}
	//=============================
	public CReserva() {
		// TODO Auto-generated constructor stub
		this.cReservaCod="";
		this.cTelefono ="";
		this.cInformacionAdicional ="";
		this.cContacto="";
		this.cEmail="";
		this.cMetodoPago="";
		this.cCodTransaccion="";
	}
	public CReserva(String cReservaCod, Date dFechaInicio, Date dFechaFin,
			Date dFecha, String cContacto, String cEmail, String cTelefono,
			Number nPrecioPaquetePersona, int nNroPersonas,
			String cInformacionAdicional, String cEstado,
			String cMetodoPago,String cCodTransaccion,Date dFechaArribo) {
		this.cReservaCod = cReservaCod;
		this.dFechaInicio = dFechaInicio;
		this.dFechaFin = dFechaFin;
		this.dFecha = dFecha;
		this.dFechaArribo=dFechaArribo;
		this.cContacto = cContacto;
		this.cEmail = cEmail;
		this.cTelefono = cTelefono;
		this.nPrecioPaquetePersona = nPrecioPaquetePersona;
		this.nNroPersonas = nNroPersonas;
		this.cInformacionAdicional = cInformacionAdicional;
		this.cEstado = cEstado;
		this.cMetodoPago=cMetodoPago;
		this.cCodTransaccion=cCodTransaccion;
	}
	public CReserva(String codPaquete)
	{
		// TODO Auto-generated constructor stub
		this.cTelefono ="";
		this.cInformacionAdicional ="";
		this.cContacto="";
		this.cEmail="";
		/**RECUPERAR PAQUETES**/
		CPaqueteDAO paqueteDao=new CPaqueteDAO();
		oPaquete=new CPaquete();
//		for(String codPaquete:listaCodPaquetes)
//		{
			paqueteDao.asignarPaquete(paqueteDao.recuperarPaqueteBD(codPaquete));
			setoPaquete(paqueteDao.getoPaquete());
//			listaPaquetes.add(paqueteDao.getoPaquete());
//		}
		oPasajeroReservante=new CPasajero();
		oCupon=new CCupon();
	}
}
