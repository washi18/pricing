package com.pricing.dao;

import java.util.List;
import java.util.Map;

import com.pricing.model.CPasajero;

public class CPasajeroDAO extends CConexion 
{
	private CPasajero oPasajero;
	//==========================
	public CPasajeroDAO() {
		// TODO Auto-generated constructor stub
		super();
		this.oPasajero=new CPasajero();
	}
	public CPasajeroDAO(CPasajero pasajero)
	{
		this.oPasajero=pasajero;
	}
	//==========================
	public List insertarPasajero(CPasajero pasajero)
	{
		Object[] values={pasajero.getcReservaCod(),pasajero.getnNro(),pasajero.getcNroDoc(),
				pasajero.getnTipoDoc(),pasajero.getcApellidos(),pasajero.getcNombres(),
				pasajero.getnPaisCod(),pasajero.getcSexo(),pasajero.getnEdad(),pasajero.getcUrlDocumento()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_RegistrarPasajero", values);
	}
	public boolean isOperationCorrect(List lista)
	{
		Map row=(Map)lista.get(0);
		boolean flag=row.get("resultado").toString().equals("correcto");
		if(flag)return true;
		else return false;
	}
}
