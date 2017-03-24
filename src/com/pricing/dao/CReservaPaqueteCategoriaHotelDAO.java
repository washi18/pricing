package com.pricing.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pricing.model.CReservaPaqueteCategoriaHotel;

public class CReservaPaqueteCategoriaHotelDAO extends CConexion
{
	private CReservaPaqueteCategoriaHotel oReservaPaqCatHotel;
	private ArrayList<CReservaPaqueteCategoriaHotel> listaReservasPaqCatHotel;
	//=========================================
	public CReservaPaqueteCategoriaHotel getoReservaPaqCatHotel() {
		return oReservaPaqCatHotel;
	}
	public void setoReservaPaqCatHotel(
			CReservaPaqueteCategoriaHotel oReservaPaqCatHotel) {
		this.oReservaPaqCatHotel = oReservaPaqCatHotel;
	}
	public ArrayList<CReservaPaqueteCategoriaHotel> getListaReservasPaqCatHotel() {
		return listaReservasPaqCatHotel;
	}
	public void setListaReservasPaqCatHotel(
			ArrayList<CReservaPaqueteCategoriaHotel> listaReservasPaqCatHotel) {
		this.listaReservasPaqCatHotel = listaReservasPaqCatHotel;
	}
	//==========================
	public CReservaPaqueteCategoriaHotelDAO() {
		// TODO Auto-generated constructor stub
		super();
		this.oReservaPaqCatHotel=new CReservaPaqueteCategoriaHotel();
	}
	public CReservaPaqueteCategoriaHotelDAO(CReservaPaqueteCategoriaHotel RPCHotel)
	{
		super();
		this.oReservaPaqCatHotel=RPCHotel;
	}
	//===========================
	public List insertarReservaPaqueteCatHotel(CReservaPaqueteCategoriaHotel r)
	{
		System.out.println("este es el codigo--> "+r.getCodPaqueteCategoriaH());
		Object[] values={(Number)r.getnReservaPaqueteCod(),r.getCodPaqueteCategoriaH(),
				r.getnNroPersonasSimple(),r.getnPrecioTotalSimple(),
				r.getnNroPersonasDoble(),r.getnPrecioTotalDoble(),
				r.getnNroPersonasTriple(),r.getnPrecioTotalTriple()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_RegistrarReservaPCH", values);
	}
	public boolean isCorrectOperation(List lista)
	{
		Map row=(Map)lista.get(0);
		boolean flag=row.get("resultado").toString().equals("correcto");
		if(flag)return true;
		else return false;
	}
}
