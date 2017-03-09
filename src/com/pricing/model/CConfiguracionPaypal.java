package com.pricing.model;

public class CConfiguracionPaypal {
	//===========atributos=========
	private int nPaypalCod;
	private String cUserName;
	private String cPassword;
	private String cCertKey;
	private String cCertName;
	private String caccountId;
	private String csellerUserName;
	private String cSignature;
	private boolean editable;
	private boolean bCertificado;//Certificado
	private boolean bSignature;//Firma
	//=============getter and setter=====
	public int getnPaypalCod() {
		return nPaypalCod;
	}
	public void setnPaypalCod(int nPaypalCod) {
		this.nPaypalCod = nPaypalCod;
	}
	public String getcUserName() {
		return cUserName;
	}
	public void setcUserName(String cUserName) {
		this.cUserName = cUserName;
	}
	public String getcPassword() {
		return cPassword;
	}
	public void setcPassword(String cPassword) {
		this.cPassword = cPassword;
	}
	public String getcCertKey() {
		return cCertKey;
	}
	public void setcCertKey(String cCertKey) {
		this.cCertKey = cCertKey;
	}
	public String getcCertName() {
		return cCertName;
	}
	public void setcCertName(String cCertName) {
		this.cCertName = cCertName;
	}
	public String getCaccountId() {
		return caccountId;
	}
	public void setCaccountId(String caccountId) {
		this.caccountId = caccountId;
	}
	public String getCsellerUserName() {
		return csellerUserName;
	}
	public void setCsellerUserName(String csellerUserName) {
		this.csellerUserName = csellerUserName;
	}
	public String getcSignature() {
		return cSignature;
	}
	public void setcSignature(String cSignature) {
		this.cSignature = cSignature;
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	public boolean isbCertificado() {
		return bCertificado;
	}
	public void setbCertificado(boolean bCertificado) {
		this.bCertificado = bCertificado;
	}
	public boolean isbSignature() {
		return bSignature;
	}
	public void setbSignature(boolean bSignature) {
		this.bSignature = bSignature;
	}
	//====================contructores=========
	public CConfiguracionPaypal(){
		this.cUserName = "";
		this.cPassword = "";
		this.cCertKey = "";
		this.cCertName = "";
		this.caccountId = "";
		this.csellerUserName = "";
		this.cSignature = "";
		this.editable=false;
		this.bCertificado=false;
		this.bSignature=false;
	}
	public CConfiguracionPaypal(int nPaypalCod, String cUserName,
			String cPassword, String cCertKey, String cCertName,
			String caccountId, String csellerUserName, String cSignature) {
		this.nPaypalCod = nPaypalCod;
		this.cUserName = cUserName;
		this.cPassword = cPassword;
		this.cCertKey = cCertKey;
		this.cCertName = cCertName;
		this.caccountId = caccountId;
		this.csellerUserName = csellerUserName;
		this.cSignature = cSignature;
		/*****************/
		System.out.println("Signature... "+this.cSignature);
		this.editable=false;
		if(this.cSignature.equals(""))
		{
			System.out.println("soy certificado");
			this.bCertificado=true;
			this.bSignature=false;
		}else
		{
			System.out.println("soy signature");
			this.bCertificado=false;
			this.bSignature=true;
		}
	}
}
