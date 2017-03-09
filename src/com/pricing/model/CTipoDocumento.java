package com.pricing.model;

public class CTipoDocumento 
{
	private int nTipoDoc;// int,					--codigo del tipo de documento
	private String cAbrevTipoDoc;// varchar(20),			--abreviatura del tipo de documento de identidad
	private String cTipoDoc;// varchar(50),				--descripcion completa del tipo de documento
	//=========================
	public int getnTipoDoc() {
		return nTipoDoc;
	}
	public void setnTipoDoc(int nTipoDoc) {
		this.nTipoDoc = nTipoDoc;
	}
	public String getcAbrevTipoDoc() {
		return cAbrevTipoDoc;
	}
	public void setcAbrevTipoDoc(String cAbrevTipoDoc) {
		this.cAbrevTipoDoc = cAbrevTipoDoc;
	}
	public String getcTipoDoc() {
		return cTipoDoc;
	}
	public void setcTipoDoc(String cTipoDoc) {
		this.cTipoDoc = cTipoDoc;
	}
	//================================
	public CTipoDocumento() {
		// TODO Auto-generated constructor stub
	}
	public CTipoDocumento(int nTipoDoc, String cAbrevTipoDoc, String cTipoDoc) {
		this.nTipoDoc = nTipoDoc;
		this.cAbrevTipoDoc = cAbrevTipoDoc;
		this.cTipoDoc = cTipoDoc;
	}
	
}
