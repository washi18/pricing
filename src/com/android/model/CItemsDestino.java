package com.android.model;

public class CItemsDestino {
	private int codItemsDestino;// integer,
	private int cItemsCod;// integer,
	private int nDestinoCod;// integer,
	//=================================
	public int getCodItemsDestino() {
		return codItemsDestino;
	}
	public void setCodItemsDestino(int codItemsDestino) {
		this.codItemsDestino = codItemsDestino;
	}
	public int getcItemsCod() {
		return cItemsCod;
	}
	public void setcItemsCod(int cItemsCod) {
		this.cItemsCod = cItemsCod;
	}
	public int getnDestinoCod() {
		return nDestinoCod;
	}
	public void setnDestinoCod(int nDestinoCod) {
		this.nDestinoCod = nDestinoCod;
	}
	//===================================
	public CItemsDestino() {
		// TODO Auto-generated constructor stub
		this.cItemsCod=0;
		this.nDestinoCod=0;
	}
	public CItemsDestino(int codItemsDestino, int cItemsCod, int nDestinoCod) {
		this.codItemsDestino = codItemsDestino;
		this.cItemsCod = cItemsCod;
		this.nDestinoCod = nDestinoCod;
	}
}
