package com.android.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.android.model.CAgencia;
import com.pricing.dao.CConexion;

public class CAgenciaDAO extends CConexion{
	private CAgencia oAgencia;
	/****************/
	public CAgencia getoAgencia() {
		return oAgencia;
	}

	public void setoAgencia(CAgencia oAgencia) {
		this.oAgencia = oAgencia;
	}

	/****************/
	public CAgenciaDAO() {
		// TODO Auto-generated constructor stub
		super();
	}
	/****************/
	public List recuperarAgenciaDB()
	{
		return getEjecutorSQL().ejecutarProcedimiento("Android_sp_MostrarAgencia");
	}
	public void asignarAgencia(List lista)
	{
		if(!lista.isEmpty())
		{
			Map row=(Map)lista.get(0);
			oAgencia=new CAgencia((String)row.get("cagenciacod"), 
					(String)row.get("crazonsocial"),(String)row.get("cdireccion"), 
					(String)row.get("ctelefono"),(String)row.get("cpaginaweb"),(String)row.get("cemail"), 
					(Date)row.get("dfechacreacion"));
		}else
			oAgencia=new CAgencia();
	}
	public List insertarAgencia(CAgencia agencia)
	{
		Object[] values={agencia.getcRazonSocial(),agencia.getcDireccion(),
				agencia.getcTelefono(),agencia.getcPaginaWeb(),agencia.getcEmail(),agencia.getdFechaCreacion()};
		return getEjecutorSQL().ejecutarProcedimiento("Android_sp_RegistrarAgencia", values);
	}
	public boolean isOperationCorrect(List lista)
	{
		Map row=(Map)lista.get(0);
		boolean correcto=row.get("resultado").toString().equals("correcto");
		if(correcto)return true;
		else return false;
	}
}
