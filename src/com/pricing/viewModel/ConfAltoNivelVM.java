package com.pricing.viewModel;

import javax.servlet.http.HttpSession;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.Clients;

import com.pricing.dao.ConfAltoNivelDAO;
import com.pricing.model.ConfAltoNivel;

public class ConfAltoNivelVM {
	//=========atributos=======
	private ConfAltoNivel oConfAltoNievel;
	private ConfAltoNivelDAO confAltoNivelDAO;
	//======getter an setter=====
	
	public ConfAltoNivel getoConfAltoNievel() {
		return oConfAltoNievel;
	}

	public ConfAltoNivelDAO getConfAltoNivelDAO() {
		return confAltoNivelDAO;
	}

	public void setConfAltoNivelDAO(ConfAltoNivelDAO confAltoNivelDAO) {
		this.confAltoNivelDAO = confAltoNivelDAO;
	}

	public void setoConfAltoNievel(ConfAltoNivel oConfAltoNievel) {
		this.oConfAltoNievel = oConfAltoNievel;
	}
	//===========constructores=====
	@Init
	public void Inicializa(){
		oConfAltoNievel=new ConfAltoNivel();
		confAltoNivelDAO=new ConfAltoNivelDAO();
		Execution exec = Executions.getCurrent();
		HttpSession ses = (HttpSession)Sessions.getCurrent().getNativeSession();
	    String user=(String)ses.getAttribute("usuario");
	    String pas=(String)ses.getAttribute("clave");
	    if(user!=null && pas!=null)
	    	recuperarEstadoYourself();
	}
	public void recuperarEstadoYourself(){
		confAltoNivelDAO.asignarListaConfAltoNivel(confAltoNivelDAO.recuperarconfAltoNivel("yourself"));
		setoConfAltoNievel(confAltoNivelDAO.getoConfAltoNivel());
		BindUtils.postNotifyChange(null, null, this, "oConfAltoNievel");
	}
	
	@Command
	@NotifyChange({"oConfAltoNievel"})
	public void cambiarEstadoYourself(@BindingParam("estado")String estado){
		oConfAltoNievel.setCnombreEntidad("yourself");
		if(estado.equals("CON")){
			oConfAltoNievel.setbEstado(true);
			oConfAltoNievel.setEstadoConYourself(true);
			oConfAltoNievel.setEstadoSinYourself(false);
		}else{
			oConfAltoNievel.setbEstado(false);
			oConfAltoNievel.setEstadoConYourself(false);
			oConfAltoNievel.setEstadoSinYourself(true);
		}
		boolean correcto=confAltoNivelDAO.isOperationCorrect(confAltoNivelDAO.modificarConfAltoNivel(oConfAltoNievel));
		if(correcto){
			Clients.showNotification("Estado yourself modificado satisfactoriamente",Clients.NOTIFICATION_TYPE_INFO,null,"before_start",2700);
		}else {
			Clients.showNotification("Error al modificar estado yourself",Clients.NOTIFICATION_TYPE_ERROR,null,"before_start",2700);
		}
	}
}
