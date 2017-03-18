package com.pricing.viewModel;

import java.util.ArrayList;

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
	private ConfAltoNivel oConfAltoNievelYourSelf;
	private ConfAltoNivel oConfAltoNievelPricing;
	private ConfAltoNivelDAO confAltoNivelDAO;
	private ArrayList<ConfAltoNivel> listaConfAltoNivel;
	//======getter an setter=====

	public ArrayList<ConfAltoNivel> getListaConfAltoNivel() {
		return listaConfAltoNivel;
	}

	public ConfAltoNivel getoConfAltoNievelYourSelf() {
		return oConfAltoNievelYourSelf;
	}

	public void setoConfAltoNievelYourSelf(ConfAltoNivel oConfAltoNievelYourSelf) {
		this.oConfAltoNievelYourSelf = oConfAltoNievelYourSelf;
	}

	public ConfAltoNivel getoConfAltoNievelPricing() {
		return oConfAltoNievelPricing;
	}

	public void setoConfAltoNievelPricing(ConfAltoNivel oConfAltoNievelPricing) {
		this.oConfAltoNievelPricing = oConfAltoNievelPricing;
	}

	public void setListaConfAltoNivel(ArrayList<ConfAltoNivel> listaConfAltoNivel) {
		this.listaConfAltoNivel = listaConfAltoNivel;
	}

	public ConfAltoNivelDAO getConfAltoNivelDAO() {
		return confAltoNivelDAO;
	}

	public void setConfAltoNivelDAO(ConfAltoNivelDAO confAltoNivelDAO) {
		this.confAltoNivelDAO = confAltoNivelDAO;
	}

	//===========constructores=====
	@Init
	public void Inicializa(){
		oConfAltoNievelYourSelf=new ConfAltoNivel();
		oConfAltoNievelPricing=new ConfAltoNivel();
		confAltoNivelDAO=new ConfAltoNivelDAO();
		listaConfAltoNivel=new ArrayList<ConfAltoNivel>();
		Execution exec = Executions.getCurrent();
		HttpSession ses = (HttpSession)Sessions.getCurrent().getNativeSession();
	    String user=(String)ses.getAttribute("usuario");
	    String pas=(String)ses.getAttribute("clave");
	    if(user!=null && pas!=null)
	    	recuperarEstadosConfAltoNivel();
	}
	public void recuperarEstadosConfAltoNivel(){
		confAltoNivelDAO.asignarListaConfAltoNivel(confAltoNivelDAO.recuperarconfAltoNivel("yourself"));
		setoConfAltoNievelYourSelf(confAltoNivelDAO.getoConfAltoNivel());
		confAltoNivelDAO.asignarListaConfAltoNivel(confAltoNivelDAO.recuperarconfAltoNivel("pricing"));
		setoConfAltoNievelPricing(confAltoNivelDAO.getoConfAltoNivel());
		BindUtils.postNotifyChange(null, null, this,"oConfAltoNievelYourself");
		BindUtils.postNotifyChange(null, null, this,"oConfAltoNievelPricing");
	}
	
	@Command
	@NotifyChange({"oConfAltoNievelYourself"})
	public void cambiarEstadoYourself(@BindingParam("estado")String estado){
		oConfAltoNievelYourSelf.setCnombreEntidad("yourself");
		if(estado.equals("CON")){
			oConfAltoNievelYourSelf.setbEstado(true);
			oConfAltoNievelYourSelf.setEstadoConEntidad(true);
			oConfAltoNievelYourSelf.setEstadoSinEntidad(false);
		}else{
			oConfAltoNievelYourSelf.setbEstado(false);
			oConfAltoNievelYourSelf.setEstadoConEntidad(false);
			oConfAltoNievelYourSelf.setEstadoSinEntidad(true);
		}
		boolean correcto=confAltoNivelDAO.isOperationCorrect(confAltoNivelDAO.modificarConfAltoNivel(oConfAltoNievelYourSelf));
		if(correcto){
			Clients.showNotification("Estado yourself modificado satisfactoriamente",Clients.NOTIFICATION_TYPE_INFO,null,"before_start",2700);
		}else {
			Clients.showNotification("Error al modificar estado yourself",Clients.NOTIFICATION_TYPE_ERROR,null,"before_start",2700);
		}
	}
	
	@Command
	@NotifyChange({"oConfAltoNievelPricing"})
	public void cambiarEstadoPricingPasos(@BindingParam("estado")String estado){
		oConfAltoNievelPricing.setCnombreEntidad("pricing");
		if(estado.equals("CON")){
			oConfAltoNievelPricing.setbEstado(true);
			oConfAltoNievelPricing.setEstadoConEntidad(true);
			oConfAltoNievelPricing.setEstadoSinEntidad(false);
		}else{
			oConfAltoNievelPricing.setbEstado(false);
			oConfAltoNievelPricing.setEstadoConEntidad(false);
			oConfAltoNievelPricing.setEstadoSinEntidad(true);
		}
		boolean correcto=confAltoNivelDAO.isOperationCorrect(confAltoNivelDAO.modificarConfAltoNivel(oConfAltoNievelPricing));
		if(correcto){
			Clients.showNotification("Estado pricing modificado satisfactoriamente",Clients.NOTIFICATION_TYPE_INFO,null,"before_start",2700);
		}else {
			Clients.showNotification("Error al modificar estado pricing",Clients.NOTIFICATION_TYPE_ERROR,null,"before_start",2700);
		}
	}
}
