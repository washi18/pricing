package com.android.viewModel;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.Clients;

import com.android.dao.CDestinosMovilDAO;
import com.android.model.CDestinoMovil;

public class destinosMovilVM {
	/*====atributos=====*/
	private CDestinoMovil oDestinoMovilNuevo;
	private CDestinoMovil oDestinoMovilUpdate;
	private ArrayList<CDestinoMovil> listaDestinosMovil;
	/*=====getter and setter====*/
	public CDestinoMovil getoDestinoMovilNuevo() {
		return oDestinoMovilNuevo;
	}
	public void setoDestinoMovilNuevo(CDestinoMovil oDestinoMovilNuevo) {
		this.oDestinoMovilNuevo = oDestinoMovilNuevo;
	}
	public CDestinoMovil getoDestinoMovilUpdate() {
		return oDestinoMovilUpdate;
	}
	public void setoDestinoMovilUpdate(CDestinoMovil oDestinoMovilUpdate) {
		this.oDestinoMovilUpdate = oDestinoMovilUpdate;
	}
	public ArrayList<CDestinoMovil> getListaDestinosMovil() {
		return listaDestinosMovil;
	}
	public void setListaDestinosMovil(ArrayList<CDestinoMovil> listaDestinosMovil) {
		this.listaDestinosMovil = listaDestinosMovil;
	}
	/*======metodos=====*/
	@Init
	public void initVM()
	{
			oDestinoMovilNuevo=new CDestinoMovil();
			oDestinoMovilUpdate=new CDestinoMovil();
	}
	@GlobalCommand
	@NotifyChange({"listaDestinosMovil"})
	public void recuperarDestinosMovil()
	{
		HttpSession ses = (HttpSession)Sessions.getCurrent().getNativeSession();
		String user=(String)ses.getAttribute("usuario");
	    String pas=(String)ses.getAttribute("clave");
		if(user!=null && pas!=null)
		{
			CDestinosMovilDAO destinosMovilDao=new CDestinosMovilDAO();
			destinosMovilDao.asignarListaDestinosMovil(destinosMovilDao.recuperarListaTodosDestinosMovilBD());
			setListaDestinosMovil(destinosMovilDao.getListaDestinosMovil());
		}
	}
	@Command
	@NotifyChange("listaDestinosMovil")
	public void buscarDestinosMovil(@BindingParam("destino")String destino){
		CDestinosMovilDAO destinoMovilDao=new CDestinosMovilDAO();
		destinoMovilDao.asignarListaDestinosMovil(destinoMovilDao.buscarDestinosMovilBD(destino));
		setListaDestinosMovil(destinoMovilDao.getListaDestinosMovil());
	}
	@Command
	@NotifyChange({"listaDestinosMovil"})
	public void insertarDestinoMovil(@BindingParam("componente")Component comp)
	{
		if(!validoParaInsertar(comp))
			return;
		CDestinosMovilDAO destinoMovilDao=new CDestinosMovilDAO();
		boolean correcto=destinoMovilDao.isOperationCorrect(destinoMovilDao.insertarDestinoMovil(oDestinoMovilNuevo));
		oDestinoMovilNuevo=new CDestinoMovil();
		if(correcto)
		{
			destinoMovilDao.asignarListaDestinosMovil(destinoMovilDao.recuperarListaTodosDestinosMovilBD());
			setListaDestinosMovil(destinoMovilDao.getListaDestinosMovil());
			Clients.showNotification("El Destino se inserto correctamente", Clients.NOTIFICATION_TYPE_INFO, comp,"before_start", 2700);
		}
		else
			Clients.showNotification("El Destino no se inserto", Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start", 2700);
		BindUtils.postNotifyChange(null, null, this, "oDestinoMovilNuevo");
	}
	public boolean validoParaInsertar(Component comp)
	{
		oDestinoMovilNuevo.setcDestino(oDestinoMovilNuevo.getcDestino().toUpperCase());
		boolean valido=true;
		if(oDestinoMovilNuevo.getcDestino().equals(""))
		{
			valido=false;
			Clients.showNotification("El Destino siempre debe de tener un nombre", Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start", 2700);
		}
		return valido;
	}
	@Command
	public void clickMapaInsertar(@BindingParam("latitud")double latitud,@BindingParam("longitud")double longitud){
			oDestinoMovilNuevo.setcLatitud(String.valueOf(latitud));
			oDestinoMovilNuevo.setcLongitud(String.valueOf(longitud));
		BindUtils.postNotifyChange(null, null, this, "oDestinoMovilNuevo");
	}
	@Command
	public void clickMapaUpdate(@BindingParam("destino")CDestinoMovil destino,@BindingParam("latitud")double latitud,@BindingParam("longitud")double longitud){
			oDestinoMovilUpdate.setcLatitud(String.valueOf(latitud));
			oDestinoMovilUpdate.setcLongitud(String.valueOf(longitud));
			destino.setcLatitud(String.valueOf(latitud));
			destino.setcLongitud(String.valueOf(longitud));
		BindUtils.postNotifyChange(null, null, this, "oDestinoMovilUpdate");
		BindUtils.postNotifyChange(null, null, destino,"cLatitud");
		BindUtils.postNotifyChange(null, null, destino,"cLongitud");
	}
	@Command
	public void actualizarDestinoMovil(@BindingParam("destino")CDestinoMovil destino,@BindingParam("componente")Component comp)
	{
		if(!validoParaActualizar(destino,comp))
			return;
		CDestinosMovilDAO destinoMovilDao=new CDestinosMovilDAO();
		boolean correcto=destinoMovilDao.isOperationCorrect(destinoMovilDao.modificarDestinoMovil(destino));
		if(correcto)
		{
			Clients.showNotification("El Destino se actualizo correctamente", Clients.NOTIFICATION_TYPE_INFO, comp,"before_start", 2700);
		}
		else
			Clients.showNotification("El Destino no se actualizo", Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start", 2700);
		destino.setEditable(false);
		refrescaFilaTemplate(destino);
	}
	@GlobalCommand
	@NotifyChange({"listaDestinosMovil"})
	public void actualizarEstadoDestinosMovil()
	{
		listaDestinosMovil.clear();
		CDestinosMovilDAO destinoMovilDao=new CDestinosMovilDAO();
		destinoMovilDao.asignarListaDestinosMovil(destinoMovilDao.recuperarListaTodosDestinosMovilBD());
		setListaDestinosMovil(destinoMovilDao.getListaDestinosMovil());
	}
	public boolean validoParaActualizar(CDestinoMovil destino,Component comp)
	{
		destino.setcDestino(destino.getcDestino().toUpperCase());
		boolean valido=true;
		if(destino.getcDestino().equals(""))
		{
			valido=false;
			Clients.showNotification("El Destino siempre debe de tener un nombre", Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start", 2700);
		}
		return valido;
	}
	@Command
	public void Editar(@BindingParam("destino") CDestinoMovil d ) 
	{
		d.setEditable(false);
		oDestinoMovilUpdate.setEditable(false);
		refrescaFilaTemplate(oDestinoMovilUpdate);
		oDestinoMovilUpdate=d;
		//le damos estado editable
		d.setEditable(!d.isEditable());	
		//lcs.setEditingStatus(!lcs.getEditingStatus());
		refrescaFilaTemplate(d);
   }
	@Command
	public void Activar_Desactivar_destino(@BindingParam("destino")CDestinoMovil d,@BindingParam("texto")String texto)
	{
		if(texto.equals("activar"))
		{
			d.setColor_btn_activo(d.COLOR_ACTIVO);
			d.setColor_btn_desactivo(d.COLOR_TRANSPARENT);
			d.setEstado_activo(true);
			d.setEstado_desactivo(false);
			d.setbEstado(true);
		}else{
			d.setColor_btn_activo(d.COLOR_TRANSPARENT);
			d.setColor_btn_desactivo(d.COLOR_DESACTIVO);
			d.setEstado_activo(false);
			d.setEstado_desactivo(true);
			d.setbEstado(false);
		}
		BindUtils.postNotifyChange(null, null, d,"estado_activo");
		BindUtils.postNotifyChange(null, null, d,"estado_desactivo");
		BindUtils.postNotifyChange(null, null, d,"color_btn_activo");
		BindUtils.postNotifyChange(null, null, d,"color_btn_desactivo");
	}
	public void refrescaFilaTemplate(CDestinoMovil d)
	{
		BindUtils.postNotifyChange(null, null, d, "editable");
	}
}
