package com.android.model;

public class CDestinoMovil {
	private int nDestinoCod;// integer,
	private String cDestino;// varchar(100),
	private boolean bEstado;// boolean,
	private String cLatitud;// varchar(200),
	private String cLongitud;// varchar(200),
	//=======================================
	public int getnDestinoCod() {
		return nDestinoCod;
	}
	public void setnDestinoCod(int nDestinoCod) {
		this.nDestinoCod = nDestinoCod;
	}
	public String getcDestino() {
		return cDestino;
	}
	public void setcDestino(String cDestino) {
		this.cDestino = cDestino;
	}
	public boolean isbEstado() {
		return bEstado;
	}
	public void setbEstado(boolean bEstado) {
		this.bEstado = bEstado;
	}
	public String getcLatitud() {
		return cLatitud;
	}
	public void setcLatitud(String cLatitud) {
		this.cLatitud = cLatitud;
	}
	public String getcLongitud() {
		return cLongitud;
	}
	public void setcLongitud(String cLongitud) {
		this.cLongitud = cLongitud;
	}
	//=====================================
	public CDestinoMovil() {
		// TODO Auto-generated constructor stub
		this.cDestino="";
		this.cLatitud="";
		this.cLongitud="";
	}
	public CDestinoMovil(int nDestinoCod, String cDestino, boolean bEstado, String cLatitud, String cLongitud) {
		this.nDestinoCod = nDestinoCod;
		this.cDestino = cDestino;
		this.bEstado = bEstado;
		this.cLatitud = cLatitud;
		this.cLongitud = cLongitud;
	}
}
