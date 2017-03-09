package com.pricing.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CCupon {
	private int nCuponCod;// int,
	private String cCupon;// varchar(100),
	private int nPorcentajeDcto;// int,
	private Date dFechaInicio;// date,
	private Date dFechaFin;// date,
	private boolean editable;
	private String fechaInicio;
	private String fechaFin;
	private boolean inicioCupon;
	private boolean agregarCupon;
	private boolean cancelarCupon;
	private boolean eliminarCupon;
	private boolean aplicarCupon;
	private boolean errorCupon;
	private boolean okCupon;
	private boolean bCodCupon;
	private String totalAnterior;
	private String totalNuevo;
	private String totalAhorro;
	/**************/
	public int getnCuponCod() {
		return nCuponCod;
	}
	public void setnCuponCod(int nCuponCod) {
		this.nCuponCod = nCuponCod;
	}
	public String getcCupon() {
		return cCupon;
	}
	public void setcCupon(String cCupon) {
		this.cCupon = cCupon;
	}
	public int getnPorcentajeDcto() {
		return nPorcentajeDcto;
	}
	public void setnPorcentajeDcto(int nPorcentajeDcto) {
		this.nPorcentajeDcto = nPorcentajeDcto;
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
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public boolean isInicioCupon() {
		return inicioCupon;
	}
	public void setInicioCupon(boolean inicioCupon) {
		this.inicioCupon = inicioCupon;
	}
	public boolean isAgregarCupon() {
		return agregarCupon;
	}
	public void setAgregarCupon(boolean agregarCupon) {
		this.agregarCupon = agregarCupon;
	}
	public boolean isCancelarCupon() {
		return cancelarCupon;
	}
	public void setCancelarCupon(boolean cancelarCupon) {
		this.cancelarCupon = cancelarCupon;
	}
	public boolean isEliminarCupon() {
		return eliminarCupon;
	}
	public void setEliminarCupon(boolean eliminarCupon) {
		this.eliminarCupon = eliminarCupon;
	}
	public boolean isAplicarCupon() {
		return aplicarCupon;
	}
	public void setAplicarCupon(boolean aplicarCupon) {
		this.aplicarCupon = aplicarCupon;
	}
	public boolean isErrorCupon() {
		return errorCupon;
	}
	public void setErrorCupon(boolean errorCupon) {
		this.errorCupon = errorCupon;
	}
	public boolean isOkCupon() {
		return okCupon;
	}
	public void setOkCupon(boolean okCupon) {
		this.okCupon = okCupon;
	}
	public String getTotalAnterior() {
		return totalAnterior;
	}
	public void setTotalAnterior(String totalAnterior) {
		this.totalAnterior = totalAnterior;
	}
	public String getTotalNuevo() {
		return totalNuevo;
	}
	public void setTotalNuevo(String totalNuevo) {
		this.totalNuevo = totalNuevo;
	}
	public String getTotalAhorro() {
		return totalAhorro;
	}
	public void setTotalAhorro(String totalAhorro) {
		this.totalAhorro = totalAhorro;
	}
	public boolean isbCodCupon() {
		return bCodCupon;
	}
	public void setbCodCupon(boolean bCodCupon) {
		this.bCodCupon = bCodCupon;
	}
	/*****************/
	public CCupon() {
		// TODO Auto-generated constructor stub
		this.cCupon="";
		this.nPorcentajeDcto=0;
		this.totalAnterior="";
		this.totalNuevo="";
		this.totalAhorro="";
	}
	public CCupon(int nCuponCod, String cCupon, int nPorcentajeDcto, Date dFechaInicio, Date dFechaFin) {
		this.nCuponCod = nCuponCod;
		this.cCupon = cCupon;
		this.nPorcentajeDcto = nPorcentajeDcto;
		this.dFechaInicio = dFechaInicio;
		this.dFechaFin = dFechaFin;
		/*****************/
		this.editable=false;
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		fechaInicio=sdf.format(dFechaInicio);
		fechaFin=sdf.format(dFechaFin);
		totalAnterior="";
		totalNuevo="";
		totalAhorro="";
	}
	public void inicioAplicarCupon()
	{
		inicioCupon=true;
		agregarCupon=true;
		cancelarCupon=false;
		eliminarCupon=false;
		aplicarCupon=false;
		errorCupon=false;
		okCupon=false;
		bCodCupon=false;
	}
	public void agregarCupon()
	{
		agregarCupon=false;
		aplicarCupon=true;
		eliminarCupon=false;
		cancelarCupon=true;
		inicioCupon=true;
		errorCupon=false;
		okCupon=false;
		bCodCupon=true;
	}
	public void eliminarCupon()
	{
		inicioCupon=true;
		agregarCupon=true;
		cancelarCupon=false;
		eliminarCupon=false;
		aplicarCupon=false;
		errorCupon=false;
		okCupon=false;
		bCodCupon=false;
		cCupon="";
	}
	public void cancelarCupon()
	{
		cCupon="";
		bCodCupon=true;
	}
	public void aplicarError()
	{
		inicioCupon=true;
		agregarCupon=false;
		cancelarCupon=false;
		eliminarCupon=true;
		aplicarCupon=false;
		errorCupon=true;
		okCupon=false;
		bCodCupon=true;
	}
	public void aplicarOk()
	{
		inicioCupon=false;
		agregarCupon=false;
		cancelarCupon=false;
		eliminarCupon=false;
		aplicarCupon=false;
		errorCupon=false;
		okCupon=true;
		bCodCupon=false;
	}
}
