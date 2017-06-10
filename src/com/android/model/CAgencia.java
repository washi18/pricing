package com.android.model;

import java.util.Date;

public class CAgencia {
	private String cAgenciaCod;// varchar(20) not null,		--ruc de la agencia
	private String cRazonSocial;// varchar(150),			--razon social de la agencia
	private String cDireccion;// varchar(150),			--direccion de la agencia
	private String cTelefono;// varchar(50),				--telefonos de la agencia
	private String cPaginaWeb;// varchar(100),			--pagina web de la agencia
	private String cEmail;// varchar(100),				--email de la agencia
	private Date dFechaCreacion;// timestamp,
	private boolean editable;
	//========================
	public String getcAgenciaCod() {
		return cAgenciaCod;
	}
	public void setcAgenciaCod(String cAgenciaCod) {
		this.cAgenciaCod = cAgenciaCod;
	}
	public String getcRazonSocial() {
		return cRazonSocial;
	}
	public void setcRazonSocial(String cRazonSocial) {
		this.cRazonSocial = cRazonSocial;
	}
	public String getcDireccion() {
		return cDireccion;
	}
	public void setcDireccion(String cDireccion) {
		this.cDireccion = cDireccion;
	}
	public String getcTelefono() {
		return cTelefono;
	}
	public void setcTelefono(String cTelefono) {
		this.cTelefono = cTelefono;
	}
	public String getcPaginaWeb() {
		return cPaginaWeb;
	}
	public void setcPaginaWeb(String cPaginaWeb) {
		this.cPaginaWeb = cPaginaWeb;
	}
	public String getcEmail() {
		return cEmail;
	}
	public void setcEmail(String cEmail) {
		this.cEmail = cEmail;
	}
	public Date getdFechaCreacion() {
		return dFechaCreacion;
	}
	public void setdFechaCreacion(Date dFechaCreacion) {
		this.dFechaCreacion = dFechaCreacion;
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	//=====================
	public CAgencia() {
		// TODO Auto-generated constructor stub
		this.cRazonSocial="";
		this.cRazonSocial="";
		this.cDireccion="";
		this.cTelefono="";
		this.cPaginaWeb="";
		this.cEmail="";
		this.editable=false;
	}
	public CAgencia(String cAgenciaCod, String cRazonSocial, String cDireccion, String cTelefono, String cPaginaWeb,
			String cEmail, Date dFechaCreacion) {
		this.cAgenciaCod = cAgenciaCod;
		this.cRazonSocial = cRazonSocial;
		this.cDireccion = cDireccion;
		this.cTelefono = cTelefono;
		this.cPaginaWeb = cPaginaWeb;
		this.cEmail = cEmail;
		this.dFechaCreacion = dFechaCreacion;
		this.editable=false;
	}
}
