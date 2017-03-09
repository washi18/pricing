package com.pricing.dao;

import java.util.List;
import java.util.Map;

import com.pricing.model.CConfigURL;

public class CConfigUrlDAO extends CConexion 
{
	private CConfigURL oConfigUrl;
	/****************/

	public CConfigURL getoConfigUrl() {
		return oConfigUrl;
	}

	public void setoConfigUrl(CConfigURL oConfigUrl) {
		this.oConfigUrl = oConfigUrl;
	}
	/****************/
	public CConfigUrlDAO() {
		// TODO Auto-generated constructor stub
		super();
	}
	/****************/
	public List recuperarConfigUrlDB()
	{
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarConfigURL");
	}
	public void asignarConfigUrl(List lista)
	{
		if(!lista.isEmpty())
		{
			Map row=(Map)lista.get(0);
			oConfigUrl=new CConfigURL((int)row.get("nconfigurlcod"), 
					(String)row.get("urlreturnpaypal"),(String)row.get("urlpaginaweb"),
					(String)row.get("urllogoempresa"),(String)row.get("logoempresa"), 
					(String)row.get("urlservletpagoparcial"),(String)row.get("urlservletpagototal"),
					(String)row.get("urlterminosycondiciones"));
		}else
			oConfigUrl=new CConfigURL();
	}
	public List insertarConfigUrl(CConfigURL configUrl)
	{
		Object[] values={configUrl.getUrlReturnPaypal(),configUrl.getUrlPaginaWeb(),
				configUrl.getUrlLogoEmpresa(),configUrl.getLogoEmpresa(),
				configUrl.getUrlServletPagoParcial(),configUrl.getUrlServletPagoTotal(),
				configUrl.getUrlTerminosYCondiciones()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_RegistrarConfigURL", values);
	}
	public boolean isOperationCorrect(List lista)
	{
		Map row=(Map)lista.get(0);
		boolean correcto=row.get("resultado").toString().equals("correcto");
		if(correcto)return true;
		else return false;
	}
}
