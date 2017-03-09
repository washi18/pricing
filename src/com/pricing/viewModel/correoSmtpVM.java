package com.pricing.viewModel;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.Clients;

import com.pricing.dao.CCorreoSmtpDAO;
import com.pricing.model.CCorreoSMTP;

public class correoSmtpVM {
	private CCorreoSMTP oCorreoSMTP;
	/**********************/

	public CCorreoSMTP getoCorreoSMTP() {
		return oCorreoSMTP;
	}

	public void setoCorreoSMTP(CCorreoSMTP oCorreoSMTP) {
		this.oCorreoSMTP = oCorreoSMTP;
	}
	/***********************/
	@Init
	public void initVM()
	{
		oCorreoSMTP=new CCorreoSMTP();
		/***********/
		CCorreoSmtpDAO correoSmtpDao=new CCorreoSmtpDAO();
		correoSmtpDao.asignarConfiguracionCorreoSMTP(correoSmtpDao.recuperarCorreoSmtpDB());
		setoCorreoSMTP(correoSmtpDao.getoCorreoSmtp());
	}
	@Command
	@NotifyChange({"oCorreoSMTP"})
	public void selectCifrado(@BindingParam("cifrado")String cifrado)
	{
		if(cifrado.equals("ssl"))
		{
			oCorreoSMTP.setbSSL(true);
			oCorreoSMTP.setbTLS(false);
			oCorreoSMTP.setCifrado("SSL");
		}
		else
		{
			oCorreoSMTP.setbSSL(false);
			oCorreoSMTP.setbTLS(true);
			oCorreoSMTP.setCifrado("TLS");
		}
	}
	@Command
	@NotifyChange({"oCorreoSMTP"})
	public void insert_update_CorreoSMTP(@BindingParam("componente")Component comp)
	{
		if(!validoParaInsert_Update(oCorreoSMTP,comp))
			return;
		CCorreoSmtpDAO correoSmtpDao=new CCorreoSmtpDAO();
		boolean correcto=correoSmtpDao.isOperationCorrect(correoSmtpDao.insertarCorreoSMTP(oCorreoSMTP));
		if(correcto)
		{
			oCorreoSMTP.setEditable(false);
			Clients.showNotification("Se guardaron los cambios correctamente", Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",3000);
		}
		else
			Clients.showNotification("No se pudieron guardar los cambios", Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
	}
	public boolean validoParaInsert_Update(CCorreoSMTP correo,Component comp)
	{
		boolean valido=true;
		if(correo.getcSMTPHost().equals(""))
		{
			valido=false;
			Clients.showNotification("Ingrese un valor para SMTP Host", Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
		}else if(correo.getnSMTPPort()==0)
		{
			valido=false;
			Clients.showNotification("Ingrese SMTP Port de su E-mail", Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
		}else if(!correo.isbSSL() && !correo.isbTLS())
		{
			valido=false;
			Clients.showNotification("Seleccione el tipo de cifrado que tiene su E-mail", Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
		}else if(correo.getcSMTPUserName().equals(""))
		{
			valido=false;
			Clients.showNotification("Ingrese su E-mail para SMTP Username", Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
		}else if(correo.getcSMTPPassword().equals(""))
		{
			valido=false;
			Clients.showNotification("Ingrese la contraseña de su E-mail para SMTP Password", Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
		}
		return valido;
	}
	@Command
	@NotifyChange({"oCorreoSMTP"})
	public void Cancelar()
	{
		CCorreoSmtpDAO correoSmtpDao=new CCorreoSmtpDAO();
		correoSmtpDao.asignarConfiguracionCorreoSMTP(correoSmtpDao.recuperarCorreoSmtpDB());
		setoCorreoSMTP(correoSmtpDao.getoCorreoSmtp());
	}
	@Command
	@NotifyChange({"oCorreoSMTP"})
	public void Editar()
	{
		oCorreoSMTP.setEditable(true);
	}
}
