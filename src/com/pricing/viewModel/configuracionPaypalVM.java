package com.pricing.viewModel;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Messagebox;

import com.pricing.dao.CConfiguracionPaypalDAO;
import com.pricing.model.CConfiguracionPaypal;
import com.pricing.util.ScannUtil;

public class configuracionPaypalVM {
	//========atributos====
	private CConfiguracionPaypal configuracionPaypal;
	//========getter an setter=====
	public CConfiguracionPaypal getConfiguracionPaypal() {
		return configuracionPaypal;
	}
	public void setConfiguracionPaypal(CConfiguracionPaypal configuracionPaypal) {
		this.configuracionPaypal = configuracionPaypal;
	}
	//==========metodos==========
	@Init
	public void initVM()
	{
		configuracionPaypal=new CConfiguracionPaypal();
		/****/
	}
	@GlobalCommand
	public void cargarConfiguracionPaypal()
	{
		CConfiguracionPaypalDAO configPaypalDao=new CConfiguracionPaypalDAO();
		configPaypalDao.asignarConfigPaypal(configPaypalDao.recuperarConfigPaypalBD());
		setConfiguracionPaypal(configPaypalDao.getDatosConfigPaypal());
		BindUtils.postNotifyChange(null, null, this,"configuracionPaypal");
	}
	@Command
	@NotifyChange({"configuracionPaypal"})
	public void selectConfigPaypal(@BindingParam("configPaypal")String configPaypal){
		if(configPaypal.equals("Signature")){
			configuracionPaypal.setbSignature(true);
			configuracionPaypal.setbCertificado(false);
		}else
		{
			configuracionPaypal.setbSignature(false);
			configuracionPaypal.setbCertificado(true);
		}
	}
	@Command
	@NotifyChange("configuracionPaypal")
	public void guardarConfigPaypal(@BindingParam("componente")Component comp){
		if(!validoParaInsertar(comp))
			return;
		if(configuracionPaypal.isbCertificado())
		{
			configuracionPaypal.setcSignature("");
		}else{
			configuracionPaypal.setcCertKey("");
		}
		CConfiguracionPaypalDAO configuracionPaypalDAO=new CConfiguracionPaypalDAO();
		boolean correcto=configuracionPaypalDAO.isOperationCorrect(configuracionPaypalDAO.registrarConfigPaypalBD(configuracionPaypal));
		if(correcto)
		{
			configuracionPaypal.setEditable(false);
			Clients.showNotification("Datos de configuracion paypal correctamente actualizados", Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",2700);
		}
		else
			Clients.showNotification("Error al actualizar datos de paypal", Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",2700);
	}
	public boolean validoParaInsertar(Component comp)
	{
		boolean valido=true;
		if(configuracionPaypal.isbCertificado())
		{
			if(configuracionPaypal.getcUserName().equals(""))
			{
				valido=false;
				Clients.showNotification("Ingrese un USER NAME valido, obtenido de paypal.",Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",3000);
			}else if(configuracionPaypal.getcPassword().equals(""))
			{
				valido=false;
				Clients.showNotification("Ingrese un PASSWORD valido, obtenido de paypal.",Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",3000);
			}else if(configuracionPaypal.getcCertKey().equals(""))
			{
				valido=false;
				Clients.showNotification("Ingrese un CERT KEY valido que haya generado ud. al momento de encriptar el certificado.",Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",3000);
			}else if(configuracionPaypal.getCaccountId().equals(""))
			{
				valido=false;
				Clients.showNotification("Ingrese un ACCOUNT ID valido, que viene a ser el usuario con que accede a paypal.",Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",3000);
			}else if(configuracionPaypal.getCsellerUserName().equals(""))
			{
				valido=false;
				Clients.showNotification("Ingrese un SELLER USER NAME que viene a ser el nombre de la empresa.",Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",3000);
			}else if(configuracionPaypal.getcCertName().equals(""))
			{
				valido=false;
				Clients.showNotification("Debe de subir el certificado generado por paypal y encriptado.",Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",3000);
			}
		}else{
			if(configuracionPaypal.getcUserName().equals(""))
			{
				valido=false;
				Clients.showNotification("Ingrese un USER NAME valido, obtenido de paypal.",Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",3000);
			}else if(configuracionPaypal.getcPassword().equals(""))
			{
				valido=false;
				Clients.showNotification("Ingrese un PASSWORD valido, obtenido de paypal.",Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",3000);
			}else if(configuracionPaypal.getcSignature().equals(""))
			{
				valido=false;
				Clients.showNotification("Ingrese un SIGNATURE valido, obtenido de paypal.",Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",3000);
			}else if(configuracionPaypal.getCaccountId().equals(""))
			{
				valido=false;
				Clients.showNotification("Ingrese un ACCOUNT ID valido, que viene a ser el usuario con que accede a paypal.",Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",3000);
			}else if(configuracionPaypal.getCsellerUserName().equals(""))
			{
				valido=false;
				Clients.showNotification("Ingrese un SELLER USER NAME que viene a ser el nombre de la empresa.",Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",3000);
			}
		}
		return valido;
	}
	@Command
	public void uploadImagen(@BindingParam("componente")final Component comp) {
			 Fileupload.get(new EventListener<UploadEvent>(){
					public void onEvent(UploadEvent event) {
						org.zkoss.util.media.Media media = event.getMedia();
						if (media instanceof org.zkoss.util.media.Media) {
							org.zkoss.util.media.Media doc = (org.zkoss.util.media.Media) media;
							//Con este metodo(uploadFile) de clase guardo la imagen en la ruta del servidor
				            boolean b=ScannUtil.uploadFileCertificados(doc);
				            //================================
				            //Una vez creado el nuevo nombre de archivo de imagen se procede a cambiar el nombre
				            String urlImagen=ScannUtil.getPathCertificados()+doc.getName();
				            System.out.println("el url es:"+urlImagen);
				            asignarNombreArchivoCert(doc.getName());
				            Clients.showNotification(doc.getName()+" Se inserto",Clients.NOTIFICATION_TYPE_INFO,comp,"before_start",2700);
						} else {
							Messagebox.show(media+"Error", "Error", Messagebox.OK, Messagebox.ERROR);
								}
					}
			     });
	}
	public void asignarNombreArchivoCert(String url)
	{
		System.out.println("==>:::"+url);
		configuracionPaypal.setcCertName(url);
		BindUtils.postNotifyChange(null, null, configuracionPaypal,"cCertName");
	}
	@Command
	@NotifyChange({"configuracionPaypal"})
	public void Editar()
	{
		configuracionPaypal.setEditable(true);
	}
	@Command
	@NotifyChange({"configuracionPaypal"})
	public void Cancelar()
	{
		CConfiguracionPaypalDAO configuracionPaypalDAO=new CConfiguracionPaypalDAO();
		configuracionPaypalDAO.asignarConfigPaypal(configuracionPaypalDAO.recuperarConfigPaypalBD());
		setConfiguracionPaypal(configuracionPaypalDAO.getDatosConfigPaypal());
	}
}
