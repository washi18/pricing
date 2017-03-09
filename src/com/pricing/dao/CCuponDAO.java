package com.pricing.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.pricing.model.CCupon;

public class CCuponDAO extends CConexion{
	private CCupon oCupon;
	private ArrayList<CCupon> listaCupones;
	/*****************/
	public CCupon getoCupon() {
		return oCupon;
	}
	public void setoCupon(CCupon oCupon) {
		this.oCupon = oCupon;
	}
	public ArrayList<CCupon> getListaCupones() {
		return listaCupones;
	}
	public void setListaCupones(ArrayList<CCupon> listaCupones) {
		this.listaCupones = listaCupones;
	}
	/*****************/
	public CCuponDAO() {
		// TODO Auto-generated constructor stub
		super();
	}
	public List recuperarCuponBD(String cupon)
	{
		Object[] values={cupon};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarCupon", values);
	}
	public List recuperarCuponExistenteBD(String cupon)
	{
		Object[] values={cupon};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarCuponExistente", values);
	}
	public List recuperarCuponesBD()
	{
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarCupones");
	}
	public List insertarCupon(CCupon cupon)
	{
		Object[] values={cupon.getcCupon(),cupon.getnPorcentajeDcto(),
				cupon.getdFechaInicio(),cupon.getdFechaFin()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_RegistrarCupon", values);
	}
	public List actualizarCupon(CCupon cupon)
	{
		Object[] values={cupon.getnCuponCod(),cupon.getnPorcentajeDcto(),
				cupon.getdFechaInicio(),cupon.getdFechaFin()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_ModificarCupon", values);
	}
	public void asignarCupon(List lista)
	{
		if(!lista.isEmpty())
		{
			Map row=(Map)lista.get(0);
			oCupon=new CCupon((int)row.get("ncuponcod"), 
					(String)row.get("ccupon"),(int)row.get("nporcentajedcto"), 
					(Date)row.get("dfechainicio"),(Date)row.get("dfechafin"));
		}
	}
	public void asignarListaCupones(List lista)
	{
		listaCupones=new ArrayList<CCupon>();
		System.out.println("Entre a asignar a la lista cupon");
		if(!lista.isEmpty())
		{
			for(int i=0;i<lista.size();i++)
			{
				Map row=(Map)lista.get(i);
				listaCupones.add(new CCupon((int)row.get("ncuponcod"), 
						(String)row.get("ccupon"),(int)row.get("nporcentajedcto"), 
						(Date)row.get("dfechainicio"),(Date)row.get("dfechafin")));
			}
		}
	}
	public boolean isOperationCorrect(List lista)
	{
		Map row=(Map)lista.get(0);
		boolean correcto=row.get("resultado").toString().equals("correcto");
		if(correcto)return true;
		else return false;
	}
}
