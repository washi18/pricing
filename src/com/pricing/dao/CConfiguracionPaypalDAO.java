package com.pricing.dao;

import java.util.List;
import java.util.Map;

import com.pricing.model.CConfiguracionPaypal;

public class CConfiguracionPaypalDAO extends CConexion{
	//======atributos======
	private CConfiguracionPaypal datosConfigPaypal;
	//======getter an setter======
	public CConfiguracionPaypal getDatosConfigPaypal() {
		return datosConfigPaypal;
	}

	public void setDatosConfigPaypal(CConfiguracionPaypal datosConfigPaypal) {
		this.datosConfigPaypal = datosConfigPaypal;
	}
	//==============constructor======
	public CConfiguracionPaypalDAO(){
		super();
	}
	//=================otros metodos========
	public List registrarConfigPaypalBD(CConfiguracionPaypal configPaypal){
		String values[]={configPaypal.getcUserName(),configPaypal.getcPassword(),configPaypal.getcSignature(),configPaypal.getcCertKey(),
						configPaypal.getcCertName(),configPaypal.getCaccountId(),configPaypal.getCsellerUserName()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_RegistrarPaypalConfig",values);
	}
	public List recuperarConfigPaypalBD(){
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarPaypalConfig");
	}
	public void asignarConfigPaypal(List lista){
		if(!lista.isEmpty())
		{
			Map row=(Map)lista.get(0);
			datosConfigPaypal=new CConfiguracionPaypal((int)row.get("npaypalcod"),
					(String)row.get("cusername"),(String)row.get("cpassword"),
					(String)row.get("ccertkey"),(String)row.get("ccertname"),
					(String)row.get("caccountid"),(String)row.get("csellerusername"),
					(String)row.get("csignature"));
		}else
			datosConfigPaypal=new CConfiguracionPaypal();
	}
	public boolean isOperationCorrect(List lista)
	{
		Map row=(Map)lista.get(0);
		boolean correcto=row.get("resultado").toString().equals("correcto");
		if(correcto)return true;
		else return false;
	}
	
}
