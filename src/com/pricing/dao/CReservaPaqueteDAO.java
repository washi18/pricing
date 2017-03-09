package com.pricing.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pricing.model.CReservaPaquete;

public class CReservaPaqueteDAO extends CConexion{
	private CReservaPaquete oReservaPaquete;
	private ArrayList<CReservaPaquete> listaReservaPaquete;
	/*****************/
	public CReservaPaquete getoReservaPaquete() {
		return oReservaPaquete;
	}
	public void setoReservaPaquete(CReservaPaquete oReservaPaquete) {
		this.oReservaPaquete = oReservaPaquete;
	}
	public ArrayList<CReservaPaquete> getListaReservaPaquete() {
		return listaReservaPaquete;
	}
	public void setListaReservaPaquete(ArrayList<CReservaPaquete> listaReservaPaquete) {
		this.listaReservaPaquete = listaReservaPaquete;
	}
	/*******************/
	public CReservaPaqueteDAO() {
		// TODO Auto-generated constructor stub
		super();
	}
	/*******************/
	public List insertarReservaPaquete(CReservaPaquete rp)
	{
		Object[] values={rp.getcReservaCod(),rp.getcPaqueteCod(),rp.getNroPasajerosPaquete(),
				rp.getnMontoTotalPaquete()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_RegistrarReservaPaquete", values);
	}
	public Number obtenerCodigoReservaPaquete(List lista)
	{
		Map row=(Map)lista.get(0);
		boolean correcto=row.get("resultado").toString().equals("correcto");
		if(correcto)return (Number)row.get("codrp");
		else return 0;
	}
	public boolean isOperationCorrect(List lista)
	{
		Map row=(Map)lista.get(0);
		boolean correcto=row.get("resultado").toString().equals("correcto");
		if(correcto)return true;
		else return false;
	}
}
