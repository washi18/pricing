package com.pricing.viewModel;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Messagebox;

import com.pricing.dao.CConfigUrlDAO;
import com.pricing.model.CConfigURL;
import com.pricing.util.ScannUtil;

public class configUrlVM 
{
	private CConfigURL oConfigUrl;
	/***************/

	public CConfigURL getoConfigUrl() {
		return oConfigUrl;
	}

	public void setoConfigUrl(CConfigURL oConfigUrl) {
		this.oConfigUrl = oConfigUrl;
	}
	/**************/
	@Init
	public void initVM()
	{
		oConfigUrl=new CConfigURL();
		CConfigUrlDAO configUrlDao=new CConfigUrlDAO();
		configUrlDao.asignarConfigUrl(configUrlDao.recuperarConfigUrlDB());
		setoConfigUrl(configUrlDao.getoConfigUrl());
	}
	@Command
	@NotifyChange({"oConfigUrl"})
	public void insert_update_ConfigURL(@BindingParam("componente")Component comp)
	{
		CConfigUrlDAO configUrlDao=new CConfigUrlDAO();
		boolean correcto=configUrlDao.isOperationCorrect(configUrlDao.insertarConfigUrl(oConfigUrl));
		if(correcto)
		{
			oConfigUrl.setEditable(false);
			Clients.showNotification("Los cambios se guardaron correctamente",Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",3000);
		}else
			Clients.showNotification("Los cambios no se guardaron correctamente",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
	}
	@Command
	public void uploadLogo(@BindingParam("componente")final Component comp) {
			 Fileupload.get(new EventListener<UploadEvent>(){
					public void onEvent(UploadEvent event) {
						org.zkoss.util.media.Media media = event.getMedia();
						if (media instanceof org.zkoss.image.Image) {
							org.zkoss.image.Image img = (org.zkoss.image.Image) media;
							//Con este metodo(uploadFile) de clase guardo la imagen en la ruta del servidor
				            boolean b=ScannUtil.uploadFileLogo(img);
				            //================================
				            //Una vez creado el nuevo nombre de archivo de imagen se procede a cambiar el nombre
				            asignarLogoDirectory(img.getName());
				            Clients.showNotification(img.getName()+" Se inserto",Clients.NOTIFICATION_TYPE_INFO,comp,"before_start",2700);
						} else {
							Messagebox.show(media+"Error", "Error", Messagebox.OK, Messagebox.ERROR);
								}
					}
			     });
	}
	public void asignarLogoDirectory(String nameLogo)
	{
		oConfigUrl.setLogoEmpresa("/img/"+nameLogo);
		BindUtils.postNotifyChange(null, null, oConfigUrl,"logoEmpresa");
	}
	@Command
	@NotifyChange({"oConfigUrl"})
	public void Cancelar()
	{
		CConfigUrlDAO configUrlDao=new CConfigUrlDAO();
		configUrlDao.asignarConfigUrl(configUrlDao.recuperarConfigUrlDB());
		setoConfigUrl(configUrlDao.getoConfigUrl());
	}
	@Command
	@NotifyChange({"oConfigUrl"})
	public void Editar()
	{
		oConfigUrl.setEditable(true);
	}
}
