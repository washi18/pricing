package com.pricing.model;

import java.util.Date;

public class CUsuario {
	//==========atributos============
	private String cusuariocod;
	private String cnombreperfil;
	private String imgusuario;
	private String cnrodoc;
	private String cnombres;
	private String csexo;
	private Date fechaNacimiento;
	private String ccelular;
	private String ccorreo;
	private boolean bestado;
	private String color_activo="background:#3BA420;";
	private String color_desactivo="background:#DA0613;";
	private boolean estadoActivo;
	private boolean estadoDesactivo;
	//===========getter and setter======
	public String getCusuariocod() {
		return cusuariocod;
	}
	public void setCusuariocod(String cusuariocod) {
		this.cusuariocod = cusuariocod;
	}
	public String getCnombreperfil() {
		return cnombreperfil;
	}
	public void setCnombreperfil(String cnombreperfil) {
		this.cnombreperfil = cnombreperfil;
	}
	public String getImgusuario() {
		return imgusuario;
	}
	public void setImgusuario(String imgusuario) {
		this.imgusuario = imgusuario;
	}
	public String getCnrodoc() {
		return cnrodoc;
	}
	public void setCnrodoc(String cnrodoc) {
		this.cnrodoc = cnrodoc;
	}
	public String getCnombres() {
		return cnombres;
	}
	public void setCnombres(String cnombres) {
		this.cnombres = cnombres;
	}
	
	public String getCsexo() {
		return csexo;
	}
	public void setCsexo(String csexo) {
		this.csexo = csexo;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getCcelular() {
		return ccelular;
	}
	public void setCcelular(String ccelular) {
		this.ccelular = ccelular;
	}
	public String getCcorreo() {
		return ccorreo;
	}
	public void setCcorreo(String ccorreo) {
		this.ccorreo = ccorreo;
	}
	public boolean isBestado() {
		return bestado;
	}
	public void setBestado(boolean bestado) {
		this.bestado = bestado;
	}
	public String getColor_activo() {
		return color_activo;
	}
	public void setColor_activo(String color_activo) {
		this.color_activo = color_activo;
	}
	public String getColor_desactivo() {
		return color_desactivo;
	}
	public void setColor_desactivo(String color_desactivo) {
		this.color_desactivo = color_desactivo;
	}
	
	public boolean isEstadoActivo() {
		return estadoActivo;
	}
	public void setEstadoActivo(boolean estadoActivo) {
		this.estadoActivo = estadoActivo;
	}
	public boolean isEstadoDesactivo() {
		return estadoDesactivo;
	}
	public void setEstadoDesactivo(boolean estadoDesactivo) {
		this.estadoDesactivo = estadoDesactivo;
	}
	//=============constructores==========
	public CUsuario(String cusuariocod, String cnombreperfil,
			String imgusuario, String cnrodoc, String cnombres, String csexo,
			Date fechaNacimiento, String ccelular, String ccorreo,
			boolean bestado) {
		super();
		this.cusuariocod = cusuariocod;
		this.cnombreperfil = cnombreperfil;
		this.imgusuario = imgusuario;
		this.cnrodoc = cnrodoc;
		this.cnombres = cnombres;
		this.csexo = csexo;
		this.fechaNacimiento = fechaNacimiento;
		this.ccelular = ccelular;
		this.ccorreo = ccorreo;
		this.bestado = bestado;
		this.estadoActivo=bestado;
		this.estadoDesactivo=!bestado;
		colorEstado();
	}
	
	public void colorEstado()
	{
		if(this.bestado){
			color_activo="background:#3BA420;";
			color_desactivo="background:transparent;";
		}else
		{
			color_activo="background:transparent;";
			color_desactivo="background:#DA0613;";
		}
	}
}
