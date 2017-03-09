package com.pricing.dao;

import java.util.List;
import java.util.Map;

import com.pricing.model.CGeneraOrdenReg;

public class CGeneraOrdenRegDAO extends CConexion{
	private CGeneraOrdenReg oGeneraOrdenReg;
	//=====================================

	public CGeneraOrdenReg getoGeneraOrdenReg() {
		return oGeneraOrdenReg;
	}

	public void setoGeneraOrdenReg(CGeneraOrdenReg oGeneraOrdenReg) {
		this.oGeneraOrdenReg = oGeneraOrdenReg;
	}
	//==================================
	public CGeneraOrdenRegDAO() {
		// TODO Auto-generated constructor stub
		super();
	}
	//==================================
	public List generarCodOrdeReg()
	{
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_GenerarOrdenReg");
	}
	public void asignarCodOrdenReg(List lista)
	{
		if(!lista.isEmpty())
		{
			Map row=(Map)lista.get(0);
			oGeneraOrdenReg=new CGeneraOrdenReg((Number)row.get("codgeneraordenreg"),(String)row.get("orden"),(String)row.get("registro"));
		}else{
			oGeneraOrdenReg=new CGeneraOrdenReg();
		}
	}
}
