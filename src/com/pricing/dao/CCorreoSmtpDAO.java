package com.pricing.dao;

import java.util.List;
import java.util.Map;

import com.pricing.model.CCorreoSMTP;

public class CCorreoSmtpDAO extends CConexion
{
	private CCorreoSMTP oCorreoSmtp;
	/*****************/
	public CCorreoSMTP getoCorreoSmtp() {
		return oCorreoSmtp;
	}
	public void setoCorreoSmtp(CCorreoSMTP oCorreoSmtp) {
		this.oCorreoSmtp = oCorreoSmtp;
	}
	/*****************/
	public CCorreoSmtpDAO() {
		// TODO Auto-generated constructor stub
		super();
	}
	/*****************/
	public List insertarCorreoSMTP(CCorreoSMTP correo)
	{
		Object[] values={correo.getcSMTPHost(),correo.getnSMTPPort(),
				correo.isbSSL(),correo.isbTLS(),correo.getcSMTPUserName(),
				correo.getcSMTPPassword()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_RegistrarCorreoSMTP", values);
	}
	public List recuperarCorreoSmtpDB()
	{
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarCorreoSMTP");
	}
	public void asignarConfiguracionCorreoSMTP(List lista)
	{
		if(!lista.isEmpty())
		{
			Map row=(Map)lista.get(0);
			oCorreoSmtp=new CCorreoSMTP((int)row.get("ncorreosmtpcod"),
					(String)row.get("csmtphost"),(int)row.get("nsmtpport"),
					(boolean)row.get("bssl"),(boolean)row.get("btls"),
					(String)row.get("csmtpusername"),(String)row.get("csmtppassword"));
		}else
			oCorreoSmtp=new CCorreoSMTP();
	}
	public boolean isOperationCorrect(List lista)
	{
		Map row=(Map)lista.get(0);
		boolean correcto=row.get("resultado").toString().equals("correcto");
		if(correcto)return true;
		else return false;
	}
}
