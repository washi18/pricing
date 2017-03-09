package com.pricing.model;

import java.util.ArrayList;

public class CDestinoConHoteles 
{
	private CDestino oDestino;
	private String destino;
	private ArrayList<CHotel> listaDestinosHoteles;
	
	
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public ArrayList<CHotel> getListaDestinosHoteles() {
		return listaDestinosHoteles;
	}
	public CDestino getoDestino() {
		return oDestino;
	}

	public void setoDestino(CDestino oDestino) {
		this.oDestino = oDestino;
	}

	public void setListaDestinosHoteles(ArrayList<CHotel> listaDestinosHoteles) {
		this.listaDestinosHoteles = listaDestinosHoteles;
	}
	//=================
	public CDestinoConHoteles() {
		// TODO Auto-generated constructor stub
		this.destino="";
		this.oDestino=new CDestino();
		this.listaDestinosHoteles=new ArrayList<CHotel>();
	}
	/*
	public CDestinoConHoteles(String destino,
			ArrayList<String[]> listaHotelesDestino) {
		this.destino = destino;
		this.listaHotelesDestino = listaHotelesDestino;
	}
	*/
	
	public CDestinoConHoteles(CDestino destino, ArrayList<CHotel> listaDestinoHoteles)
	{
		this.oDestino = destino;
		this.listaDestinosHoteles=listaDestinoHoteles;
	}
	
	public CDestinoConHoteles(String destino, ArrayList<CHotel> listaDestinoHoteles)
	{
		this.destino = destino;
		this.listaDestinosHoteles=listaDestinoHoteles;
	}
}
