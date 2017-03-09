package com.pricing.viewModel;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.Clients;

import pe.com.erp.crypto.Encryptar;

import com.pricing.dao.CDestinoDAO;
import com.pricing.model.CCodigoPostal;
import com.pricing.model.CDestino;

public class DestinosVM {
	/*====atributos=====*/
	private CDestinoDAO destinoDao;
	private CDestino oDestinoNuevo;
	private CDestino oDestinoUpdate;
	private ArrayList<CDestino> listaDestinos;
	private ArrayList<CCodigoPostal> listaCodigosPostales;
	/*=====getter and setter====*/
	public ArrayList<CDestino> getListaDestinos() {
		return listaDestinos;
	}
	public void setListaDestinos(ArrayList<CDestino> listaDestinos) {
		this.listaDestinos = listaDestinos;
	}
	public CDestinoDAO getDestinoDao() {
		return destinoDao;
	}
	public void setDestinoDao(CDestinoDAO destinoDao) {
		this.destinoDao = destinoDao;
	}
	public CDestino getoDestinoNuevo() {
		return oDestinoNuevo;
	}
	public void setoDestinoNuevo(CDestino oDestinoNuevo) {
		this.oDestinoNuevo = oDestinoNuevo;
	}
	public CDestino getoDestinoUpdate() {
		return oDestinoUpdate;
	}
	public void setoDestinoUpdate(CDestino oDestinoUpdate) {
		this.oDestinoUpdate = oDestinoUpdate;
	}
	public ArrayList<CCodigoPostal> getListaCodigosPostales() {
		return listaCodigosPostales;
	}
	public void setListaCodigosPostales(
			ArrayList<CCodigoPostal> listaCodigosPostales) {
		this.listaCodigosPostales = listaCodigosPostales;
	}
	/*======metodos=====*/
	@Init
	public void initVM()
	{
			Encryptar encrip= new Encryptar();
//			System.out.println("Aqui esta la contraseña desencriptada-->"+encrip.decrypt("cyS249O3OHZTsG0ww1rYrw=="));
			Execution exec = Executions.getCurrent();
			HttpSession ses = (HttpSession)Sessions.getCurrent().getNativeSession();
		    String user=(String)ses.getAttribute("usuario");
		    String pas=(String)ses.getAttribute("clave");
		    
			destinoDao=new CDestinoDAO();
			oDestinoNuevo=new CDestino();
			oDestinoUpdate=new CDestino();
			if(user!=null && pas!=null)
				recuperarDestinos();
	}
	public void recuperarDestinos()
	{
		destinoDao.asignarListaDestinos(destinoDao.recuperarListaTodosDestinosBD());
		setListaDestinos(destinoDao.getListaDestinos());
		/**Iniciar codigos postales**/
		setListaCodigosPostales((new CCodigoPostal()).listaCodigosPostales());
	}
	@Command
	public void selectCodPostal(@BindingParam("codPostal")String cod)
	{
		oDestinoNuevo.setnCodPostal(Integer.parseInt(cod));
	}
	
	@Command
	@NotifyChange("listaDestinos")
	public void buscarDestinos(@BindingParam("destino")String destino){
		destinoDao.asignarListaDestinos(destinoDao.buscarDestinosBD(destino));
		setListaDestinos(destinoDao.getListaDestinos());
	}
	
	@Command
	public void selectCodPostal_update(@BindingParam("codPostal")String cod,@BindingParam("destino")CDestino destino)
	{
		destino.setnCodPostal(Integer.parseInt(cod));
	}
	@Command
	@NotifyChange({"listaDestinos"})
	public void insertarDestino(@BindingParam("componente")Component comp)
	{
		if(!validoParaInsertar(comp))
			return;
		boolean correcto=destinoDao.isOperationCorrect(destinoDao.insertarDestino(oDestinoNuevo));
		if(correcto)
		{
			listaDestinos.clear();
			destinoDao.asignarListaDestinos(destinoDao.recuperarListaTodosDestinosBD());
			setListaDestinos(destinoDao.getListaDestinos());
			Clients.showNotification("El Destino se inserto correctamente", Clients.NOTIFICATION_TYPE_INFO, comp,"before_start", 2700);
		}
		else
			Clients.showNotification("El Destino no se inserto", Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start", 2700);
	}
	public boolean validoParaInsertar(Component comp)
	{
		oDestinoNuevo.setcDestino(oDestinoNuevo.getcDestino().toUpperCase());
		boolean valido=true;
		if(oDestinoNuevo.getnCodPostal()==0)
		{
			valido=false;
			Clients.showNotification("Seleccionar un departamento para el destino", Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start", 2700);
		}
		else if(oDestinoNuevo.getcDestino().equals(""))
		{
			valido=false;
			Clients.showNotification("El Destino siempre debe de tener un nombre", Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start", 2700);
		}
		return valido;
	}
	@Command
	public void actualizarDestino(@BindingParam("destino")CDestino destino,@BindingParam("componente")Component comp)
	{
		if(!validoParaActualizar(destino,comp))
			return;
		boolean correcto=destinoDao.isOperationCorrect(destinoDao.modificarDestino(destino));
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
	@NotifyChange({"listaDestinos"})
	public void actualizarEstadoDestinos()
	{
		listaDestinos.clear();
		destinoDao.asignarListaDestinos(destinoDao.recuperarListaTodosDestinosBD());
		setListaDestinos(destinoDao.getListaDestinos());
	}
	public boolean validoParaActualizar(CDestino destino,Component comp)
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
	public void Editar(@BindingParam("destino") CDestino d ) 
	{
		d.setEditable(false);
		oDestinoUpdate.setEditable(false);
		refrescaFilaTemplate(oDestinoUpdate);
		oDestinoUpdate=d;
		//le damos estado editable
		d.setEditable(!d.isEditable());	
		//lcs.setEditingStatus(!lcs.getEditingStatus());
		refrescaFilaTemplate(d);
   }
	@Command
	public void Activar_Desactivar_destino(@BindingParam("destino")CDestino d,@BindingParam("texto")String texto)
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
	public void refrescaFilaTemplate(CDestino d)
	{
		BindUtils.postNotifyChange(null, null, d, "editable");
	}
}
