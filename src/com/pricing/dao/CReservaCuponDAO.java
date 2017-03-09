package com.pricing.dao;

import java.util.List;
import java.util.Map;

import com.pricing.model.CCupon;
import com.pricing.model.CReservaCupon;

public class CReservaCuponDAO extends CConexion{
	private CCupon oCupon;
	/************/

	public CCupon getoCupon() {
		return oCupon;
	}

	public void setoCupon(CCupon oCupon) {
		this.oCupon = oCupon;
	}
	/*************/
	public CReservaCuponDAO() {
		// TODO Auto-generated constructor stub
		super();
	}
	/*********************/
	public List insertarReservaCupon(CReservaCupon rc)
	{
		Object[] values={rc.getcReservaCod(),rc.getnCuponCod()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_RegistrarReservaCupon", values);
	}
	public boolean isOperationCorrect(List lista)
	{
		Map row=(Map)lista.get(0);
		boolean correcto=row.get("resultado").toString().equals("correcto");
		if(correcto)return true;
		else return false;
	}
}
