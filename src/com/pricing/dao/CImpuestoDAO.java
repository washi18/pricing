package com.pricing.dao;

import java.util.List;
import java.util.Map;

import com.pricing.model.CEtiqueta;
import com.pricing.model.CImpuesto;

public class CImpuestoDAO extends CConexion
{
	private CImpuesto oImpuesto;
	//=================

	public CImpuesto getoImpuesto() {
		return oImpuesto;
	}
	public void setoImpuesto(CImpuesto oImpuesto) {
		this.oImpuesto = oImpuesto;
	}
	//================
	public CImpuestoDAO() {
		// TODO Auto-generated constructor stub
		super();
	}
	//==================
	public void recuperarImpuestosBD()
	{
		List lista=getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarImpuesto");
		if(!lista.isEmpty())
		{
			Map row=(Map)lista.get(0);
			oImpuesto=new CImpuesto(
			(int)row.get("codimpuesto"),
			(String)row.get("impuestopaypal"),
			(String)row.get("impuestovisa"),
			(String)row.get("impuestomastercard"),
			(String)row.get("impuestodinnersclub"),
			(String)row.get("porcentajecobro"),
			(String)row.get("pagominimo"),
			(boolean)row.get("modoporcentual"));
		}else
			oImpuesto=new CImpuesto();
	}
	public List insertarImpuesto(CImpuesto impuesto)
	{
		Object[] values={impuesto.getImpuestoPaypal(),impuesto.getImpuestoVisa(),impuesto.getImpuestoMasterCard(),
				impuesto.getImpuestoDinnersClub(),impuesto.getPorcentajeCobro(),impuesto.getPagoMinimo(),
				impuesto.isModoPorcentual()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_RegistrarImpuesto", values);
	}
	public boolean isOperationCorrect(List lista)
	{
		Map row=(Map)lista.get(0);
		boolean correcto=row.get("resultado").toString().equals("correcto");
		if(correcto)return true;
		else return false;
	}
}
