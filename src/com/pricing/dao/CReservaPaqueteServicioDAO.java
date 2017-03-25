package com.pricing.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pricing.model.CReservaPaqueteServicio;

public class CReservaPaqueteServicioDAO extends CConexion
{
	private CReservaPaqueteServicio oReservaPaqServ;
	private ArrayList<CReservaPaqueteServicio> listaReservasPaqServ;
	//==========================
	public CReservaPaqueteServicio getoReservaPaqServ() {
		return oReservaPaqServ;
	}
	public void setoReservaPaqServ(CReservaPaqueteServicio oReservaPaqServ) {
		this.oReservaPaqServ = oReservaPaqServ;
	}
	public ArrayList<CReservaPaqueteServicio> getListaReservasPaqServ() {
		return listaReservasPaqServ;
	}
	public void setListaReservasPaqServ(
			ArrayList<CReservaPaqueteServicio> listaReservasPaqServ) {
		this.listaReservasPaqServ = listaReservasPaqServ;
	}
	//==================
	public CReservaPaqueteServicioDAO() {
		// TODO Auto-generated constructor stub
		super();
		this.oReservaPaqServ=new CReservaPaqueteServicio();
	}
	public CReservaPaqueteServicioDAO(CReservaPaqueteServicio reservaPaqServ)
	{
		super();
		this.oReservaPaqServ=reservaPaqServ;
	}
	//====================
	public List insertarReservaPaqueteServicio(CReservaPaqueteServicio r)
	{
		Object[] values={r.getCodPaqueteServicio(),(Number)r.getnReservaPaqueteCod(),(Double)r.getNroPrestacionServicio(),
				r.getPrecioPrestacionServicio(),r.getnSubServicioCod()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_RegistrarReservaPS", values);
	}
	public boolean isCorrectOperation(List lista)
	{
		Map row=(Map) lista.get(0);
		boolean flag=row.get("resultado").toString().equals("correcto");
		if(flag)return true;
		else return false;
	}
}
