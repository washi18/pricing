package com.pricing.viewModel;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
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
	private ConfAltoNivel oConfAltoNivelFechaArribo;
	private ConfAltoNivel oConfAltoNivelDesIti;
	private ConfAltoNivel oConfAltoNivelMuestraCaminoInka;
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

	public ConfAltoNivel getoConfAltoNivelFechaArribo() {
		return oConfAltoNivelFechaArribo;
	}

	public void setoConfAltoNivelFechaArribo(ConfAltoNivel oConfAltoNivelFechaArribo) {
		this.oConfAltoNivelFechaArribo = oConfAltoNivelFechaArribo;
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

	public ConfAltoNivel getoConfAltoNivelDesIti() {
		return oConfAltoNivelDesIti;
	}

	public void setoConfAltoNivelDesIti(ConfAltoNivel oConfAltoNivelDesIti) {
		this.oConfAltoNivelDesIti = oConfAltoNivelDesIti;
	}

	public ConfAltoNivel getoConfAltoNivelMuestraCaminoInka() {
		return oConfAltoNivelMuestraCaminoInka;
	}

	public void setoConfAltoNivelMuestraCaminoInka(ConfAltoNivel oConfAltoNivelMuestraCaminoInka) {
		this.oConfAltoNivelMuestraCaminoInka = oConfAltoNivelMuestraCaminoInka;
	}
	//===========constructores=====
	@Init
	public void Inicializa(){
		oConfAltoNievelYourSelf=new ConfAltoNivel();
		oConfAltoNievelPricing=new ConfAltoNivel();
		oConfAltoNivelFechaArribo=new ConfAltoNivel();
		oConfAltoNivelDesIti=new ConfAltoNivel();
		oConfAltoNivelMuestraCaminoInka=new ConfAltoNivel();
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
		confAltoNivelDAO.asignarListaConfAltoNivel(confAltoNivelDAO.recuperarconfAltoNivel("arribo"));
		setoConfAltoNivelFechaArribo(confAltoNivelDAO.getoConfAltoNivel());
		confAltoNivelDAO.asignarListaConfAltoNivel(confAltoNivelDAO.recuperarconfAltoNivel("desc_iti"));
		setoConfAltoNivelDesIti(confAltoNivelDAO.getoConfAltoNivel());
		confAltoNivelDAO.asignarListaConfAltoNivel(confAltoNivelDAO.recuperarconfAltoNivel("muestra_camino_inka"));
		setoConfAltoNivelMuestraCaminoInka(confAltoNivelDAO.getoConfAltoNivel());
		BindUtils.postNotifyChange(null, null, this,"oConfAltoNievelYourself");
		BindUtils.postNotifyChange(null, null, this,"oConfAltoNievelPricing");
		BindUtils.postNotifyChange(null, null, this,"oConfAltoNivelFechaArribo");
		BindUtils.postNotifyChange(null, null, this,"oConfAltoNivelDesIti");
		BindUtils.postNotifyChange(null, null, this,"oConfAltoNivelMuestraCaminoInka");
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
	
	@Command
	@NotifyChange({"oConfAltoNivelFechaArribo"})
	public void cambiarEstadoFechaArribo(@BindingParam("estado")String estado){
		oConfAltoNivelFechaArribo.setCnombreEntidad("arribo");
		if(estado.equals("CON")){
			oConfAltoNivelFechaArribo.setbEstado(true);
			oConfAltoNivelFechaArribo.setEstadoConEntidad(true);
			oConfAltoNivelFechaArribo.setEstadoSinEntidad(false);
		}else{
			oConfAltoNivelFechaArribo.setbEstado(false);
			oConfAltoNivelFechaArribo.setEstadoConEntidad(false);
			oConfAltoNivelFechaArribo.setEstadoSinEntidad(true);
		}
		boolean correcto=confAltoNivelDAO.isOperationCorrect(confAltoNivelDAO.modificarConfAltoNivel(oConfAltoNivelFechaArribo));
		if(correcto){
			Clients.showNotification("Estado de la fecha de arribo fue modificado satisfactoriamente",Clients.NOTIFICATION_TYPE_INFO,null,"before_start",2700);
		}else {
			Clients.showNotification("Error al modificar estado de la fecha de arribo",Clients.NOTIFICATION_TYPE_ERROR,null,"before_start",2700);
		}
	}
	@Command
	@NotifyChange({"oConfAltoNivelDesIti"})
	public void cambiarEstadoDesIti(@BindingParam("estado")String estado){
		oConfAltoNivelDesIti.setCnombreEntidad("desc_iti");
		if(estado.equals("CON")){
			oConfAltoNivelDesIti.setbEstado(true);
			oConfAltoNivelDesIti.setEstadoConEntidad(true);
			oConfAltoNivelDesIti.setEstadoSinEntidad(false);
		}else{
			oConfAltoNivelDesIti.setbEstado(false);
			oConfAltoNivelDesIti.setEstadoConEntidad(false);
			oConfAltoNivelDesIti.setEstadoSinEntidad(true);
		}
		boolean correcto=confAltoNivelDAO.isOperationCorrect(confAltoNivelDAO.modificarConfAltoNivel(oConfAltoNivelDesIti));
		if(correcto){
			Clients.showNotification("Estado de vista descripcion e itinerario fue modificado satisfactoriamente",Clients.NOTIFICATION_TYPE_INFO,null,"before_start",2700);
		}else {
			Clients.showNotification("Error al modificar estado de la vista descripcion e itinerario",Clients.NOTIFICATION_TYPE_ERROR,null,"before_start",2700);
		}
	}
	@Command
	@NotifyChange({"oConfAltoNivelMuestraCaminoInka"})
	public void cambiarEstadoMuestraDispoCaminoInka(@BindingParam("estado")String estado){
		oConfAltoNivelMuestraCaminoInka.setCnombreEntidad("muestra_camino_inka");
		if(estado.equals("CON")){
			oConfAltoNivelMuestraCaminoInka.setbEstado(true);
			oConfAltoNivelMuestraCaminoInka.setEstadoConEntidad(true);
			oConfAltoNivelMuestraCaminoInka.setEstadoSinEntidad(false);
		}else{
			oConfAltoNivelMuestraCaminoInka.setbEstado(false);
			oConfAltoNivelMuestraCaminoInka.setEstadoConEntidad(false);
			oConfAltoNivelMuestraCaminoInka.setEstadoSinEntidad(true);
		}
		boolean correcto=confAltoNivelDAO.isOperationCorrect(confAltoNivelDAO.modificarConfAltoNivel(oConfAltoNivelMuestraCaminoInka));
		if(correcto){
			Clients.showNotification("Estado de la muestra de la disponibilidad de camino inka fue modificado satisfactoriamente",Clients.NOTIFICATION_TYPE_INFO,null,"before_start",2700);
		}else {
			Clients.showNotification("Error al modificar el estado de la muestra de la disponibilidad de camino inka",Clients.NOTIFICATION_TYPE_ERROR,null,"before_start",2700);
		}
	}
}
