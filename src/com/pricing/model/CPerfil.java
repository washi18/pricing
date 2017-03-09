package com.pricing.model;

public class CPerfil 
{
	private int nperfilcod;// integer NOT NULL,
	private String cperfil;// character varying(100),
	//=================
	public int getNperfilcod() {
		return nperfilcod;
	}
	public void setNperfilcod(int nperfilcod) {
		this.nperfilcod = nperfilcod;
	}
	public String getCperfil() {
		return cperfil;
	}
	public void setCperfil(String cperfil) {
		this.cperfil = cperfil;
	}
	//====================
	public CPerfil() {
		// TODO Auto-generated constructor stub
	}
	public CPerfil(int nperfilcod, String cperfil) {
		this.nperfilcod = nperfilcod;
		this.cperfil = cperfil;
	}
}
