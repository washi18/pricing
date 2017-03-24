package com.pricing.dao;

import java.util.List;
import java.util.Map;

import com.pricing.model.CReservaPaqueteActividad;

public class CReservaPaqueteActividadDAO extends CConexion{
	public CReservaPaqueteActividadDAO() {
		// TODO Auto-generated constructor stub
		super();
	}
	/***********************/
	public List insertarReservaPaqueteActividad(CReservaPaqueteActividad rpa)
	{
		Object[] values={rpa.getCodPaqueteActividad(),(Number)rpa.getnReservaPaqueteCod(),
				rpa.getNroPrestacionesActividad(),rpa.getPrecioPrestacionActividad().doubleValue()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_RegistrarReservaPaqueteActividad", values);
	}
	public boolean isCorrectOperation(List lista)
	{
		Map row=(Map)lista.get(0);
		boolean correcto=row.get("resultado").toString().equals("correcto");
		if(correcto)return true;
		else return false;
	}
}
