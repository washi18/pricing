package com.pricing.viewModel;

import javax.servlet.http.HttpSession;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Div;
import org.zkoss.zul.Window;

import com.pricing.dao.ConfAltoNivelDAO;
import com.pricing.model.ConfAltoNivel;

public class indexVM 
{
	private boolean mostrarAdmin;
	private boolean mostrarPricing;
	private ConfAltoNivel confAltoNivel;
	private ConfAltoNivelDAO confAltoNivelDAO;
	//==========
	public boolean isMostrarAdmin() {
		return mostrarAdmin;
	}
	public void setMostrarAdmin(boolean mostrarAdmin) {
		this.mostrarAdmin = mostrarAdmin;
	}
	public boolean isMostrarPricing() {
		return mostrarPricing;
	}
	public void setMostrarPricing(boolean mostrarPricing) {
		this.mostrarPricing = mostrarPricing;
	}
	
	public ConfAltoNivel getConfAltoNivel() {
		return confAltoNivel;
	}
	public void setConfAltoNivel(ConfAltoNivel confAltoNivel) {
		this.confAltoNivel = confAltoNivel;
	}
	//=======================
	@Init
	public void inicializarIndex()
	{
		confAltoNivel=new ConfAltoNivel();
		confAltoNivelDAO=new ConfAltoNivelDAO();
		recuperarEstadoPricing();
		this.mostrarAdmin=false;
		this.mostrarPricing=false;
//		String perfil="ADMINISTRADOR";
		String perfil="";
		if(perfil.equals("ADMINISTRADOR"))
			this.mostrarAdmin=true;
		else
			this.mostrarPricing=true;
	}
	@GlobalCommand
	public void recuperarEstadoPricing(){
		confAltoNivelDAO.asignarListaConfAltoNivel(confAltoNivelDAO.recuperarconfAltoNivel("pricing"));
		setConfAltoNivel(confAltoNivelDAO.getoConfAltoNivel());
		if(this.confAltoNivel.isbEstado()){
			Div win_imagenes=(Div)Executions.createComponents("/pricingPasos.zul", null, null);
		}else {
			Div win_imagenes=(Div)Executions.createComponents("/pricing.zul", null, null);
		}
		BindUtils.postNotifyChange(null, null, this, "confAltoNivel");
		BindUtils.postNotifyChange(null, null, this, "visibleContenedorPasos");
		BindUtils.postNotifyChange(null, null, this, "visiblecontentBotonesPasos");
		BindUtils.postNotifyChange(null, null, this, "visiblePaso1");
		BindUtils.postNotifyChange(null, null, this, "paso2");
		BindUtils.postNotifyChange(null, null, this, "visiblePaso3");
	}
}
