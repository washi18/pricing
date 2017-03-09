package com.pricing.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class CFechaAlternaDAO extends CConexion 
{
	public CFechaAlternaDAO() {
		// TODO Auto-generated constructor stub
		super();
	}
	//==========================
	public List insertarFechaAlterna(String codReserva,Date fechaInicio,Date FechaFin)
	{
		Object[] values={codReserva,fechaInicio,FechaFin};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_RegistrarFechaAlterna", values);
	}
	public boolean isOperationCorrect(List lista)
	{
		Map row=(Map)lista.get(0);
		boolean flag=row.get("resultado").toString().equals("correcto");
		if(flag)return true;
		else return false;
	}
}
