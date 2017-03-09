package com.pricing.model;

public class CConfigURL 
{
	private int nConfigUrlCod;// int,
	private String urlReturnPaypal;// text,
	private String urlPaginaWeb;// text,
	private String urlLogoEmpresa;// text,
	private String logoEmpresa;// text,
	private String urlServletPagoParcial;// text,
	private String urlServletPagoTotal;// text,
	private String urlTerminosYCondiciones;// text,
	private boolean editable;
	/****************/
	public int getnConfigUrlCod() {
		return nConfigUrlCod;
	}
	public void setnConfigUrlCod(int nConfigUrlCod) {
		this.nConfigUrlCod = nConfigUrlCod;
	}
	public String getUrlReturnPaypal() {
		return urlReturnPaypal;
	}
	public void setUrlReturnPaypal(String urlReturnPaypal) {
		this.urlReturnPaypal = urlReturnPaypal;
	}
	public String getUrlPaginaWeb() {
		return urlPaginaWeb;
	}
	public void setUrlPaginaWeb(String urlPaginaWeb) {
		this.urlPaginaWeb = urlPaginaWeb;
	}
	public String getUrlLogoEmpresa() {
		return urlLogoEmpresa;
	}
	public void setUrlLogoEmpresa(String urlLogoEmpresa) {
		this.urlLogoEmpresa = urlLogoEmpresa;
	}
	public String getLogoEmpresa() {
		return logoEmpresa;
	}
	public void setLogoEmpresa(String logoEmpresa) {
		this.logoEmpresa = logoEmpresa;
	}
	public String getUrlServletPagoParcial() {
		return urlServletPagoParcial;
	}
	public void setUrlServletPagoParcial(String urlServletPagoParcial) {
		this.urlServletPagoParcial = urlServletPagoParcial;
	}
	public String getUrlServletPagoTotal() {
		return urlServletPagoTotal;
	}
	public void setUrlServletPagoTotal(String urlServletPagoTotal) {
		this.urlServletPagoTotal = urlServletPagoTotal;
	}
	public String getUrlTerminosYCondiciones() {
		return urlTerminosYCondiciones;
	}
	public void setUrlTerminosYCondiciones(String urlTerminosYCondiciones) {
		this.urlTerminosYCondiciones = urlTerminosYCondiciones;
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	/****************/
	public CConfigURL() {
		// TODO Auto-generated constructor stub
		this.urlReturnPaypal = "";
		this.urlPaginaWeb = "";
		this.urlLogoEmpresa = "";
		this.logoEmpresa = "/img/logo_default.png";
		this.urlServletPagoParcial = "";
		this.urlServletPagoTotal = "";
		this.urlTerminosYCondiciones="";
		this.editable=false;
	}
	public CConfigURL(int nConfigUrlCod, String urlReturnPaypal,
			String urlPaginaWeb, String urlLogoEmpresa, String logoEmpresa,
			String urlServletPagoParcial, String urlServletPagoTotal,
			String urlTerminosYCondiciones) {
		this.nConfigUrlCod = nConfigUrlCod;
		this.urlReturnPaypal = urlReturnPaypal;
		this.urlPaginaWeb = urlPaginaWeb;
		this.urlLogoEmpresa = urlLogoEmpresa;
		this.logoEmpresa = logoEmpresa;
		this.urlServletPagoParcial = urlServletPagoParcial;
		this.urlServletPagoTotal = urlServletPagoTotal;
		this.urlTerminosYCondiciones = urlTerminosYCondiciones;
		this.editable = false;
	}
}
