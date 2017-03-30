package com.pricing.viewModel;
import org.zkoss.gmaps.Gmarker;
import org.zkoss.gmaps.event.MapMouseEvent;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
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
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Messagebox;

import pe.com.erp.crypto.Encryptar;

import com.pricing.dao.CDestinoDAO;
import com.pricing.model.CCodigoPostal;
import com.pricing.model.CDestino;
import com.pricing.model.CServicio;
import com.pricing.util.ScannUtil;

import org.zkoss.gmaps.Gmarker;
import org.zkoss.gmaps.event.MapMouseEvent;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;

public class DestinosVM{
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
		oDestinoNuevo=new CDestino();
		if(correcto)
		{
			listaDestinos.clear();
			destinoDao.asignarListaDestinos(destinoDao.recuperarListaTodosDestinosBD());
			setListaDestinos(destinoDao.getListaDestinos());
			Clients.showNotification("El Destino se inserto correctamente", Clients.NOTIFICATION_TYPE_INFO, comp,"before_start", 2700);
		}
		else
			Clients.showNotification("El Destino no se inserto", Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start", 2700);
		BindUtils.postNotifyChange(null, null, this, "oDestinoNuevo");
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
	public void clickMapaInsertar(@BindingParam("latitud")double latitud,@BindingParam("longitud")double longitud){
			oDestinoNuevo.setLatitud(String.valueOf(latitud));
			oDestinoNuevo.setLongitud(String.valueOf(longitud));
		BindUtils.postNotifyChange(null, null, this, "oDestinoNuevo");
	}
	@Command
	public void clickMapaUpdate(@BindingParam("destino")CDestino destino,@BindingParam("latitud")double latitud,@BindingParam("longitud")double longitud){
			oDestinoUpdate.setLatitud(String.valueOf(latitud));
			oDestinoUpdate.setLongitud(String.valueOf(longitud));
			destino.setLatitud(String.valueOf(latitud));
			destino.setLongitud(String.valueOf(longitud));
		BindUtils.postNotifyChange(null, null, this, "oDestinoUpdate");
		BindUtils.postNotifyChange(null, null, destino,"latitud");
		BindUtils.postNotifyChange(null, null, destino,"longitud");
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
	@Command
	public void uploadImagen(@BindingParam("componente")final Component comp) {
			 Fileupload.get(new EventListener<UploadEvent>(){
					public void onEvent(UploadEvent event) {
						org.zkoss.util.media.Media media = event.getMedia();
						if (media instanceof org.zkoss.image.Image) {
							org.zkoss.image.Image img = (org.zkoss.image.Image) media;
							//Con este metodo(uploadFile) de clase guardo la imagen en la ruta del servidor
				            boolean b=ScannUtil.uploadFileDestinos(img);
				            //================================
				            //Una vez creado el nuevo nombre de archivo de imagen se procede a cambiar el nombre
				            String urlImagen=ScannUtil.getPathImagenDestinos()+img.getName();
				            asignarUrlImagenDestino(img.getName());
				            Clients.showNotification(img.getName()+" Se inserto",Clients.NOTIFICATION_TYPE_INFO,comp,"before_start",2700);
						} else {
							Messagebox.show(media+"Error", "Error", Messagebox.OK, Messagebox.ERROR);
								}
					}
			     });
	}
	public void asignarUrlImagenDestino(String url)
	{
		oDestinoNuevo.setUrlImagen("img/destinos/"+url);
		BindUtils.postNotifyChange(null, null, oDestinoNuevo,"urlImagen");
	}
	@Command
	public void changeImagen(@BindingParam("componente")final Component comp,@BindingParam("destino")final CDestino destino) {
			 Fileupload.get(new EventListener<UploadEvent>(){
					public void onEvent(UploadEvent event) {
						org.zkoss.util.media.Media media = event.getMedia();
						if (media instanceof org.zkoss.image.Image) {
							org.zkoss.image.Image img = (org.zkoss.image.Image) media;
							//Con este metodo(uploadFile) de clase guardo la imagen en la ruta del servidor
				            boolean b=ScannUtil.uploadFileDestinos(img);
				            //================================
				            //Una vez creado el nuevo nombre de archivo de imagen se procede a cambiar el nombre
				            String urlImagen=ScannUtil.getPathImagenDestinos()+img.getName();
				            asignarUrlImagenUpdateDestino(img.getName(),destino);
				            Clients.showNotification(img.getName()+" Se inserto",Clients.NOTIFICATION_TYPE_INFO,comp,"before_start",2700);
						} else {
							Messagebox.show(media+"Error", "Error", Messagebox.OK, Messagebox.ERROR);
								}
					}
			     });
	}
	public void asignarUrlImagenUpdateDestino(String url,CDestino destino)
	{
		destino.setUrlImagen("img/destinos/"+url);
		BindUtils.postNotifyChange(null, null, destino,"urlImagen");
	}
	public void refrescaFilaTemplate(CDestino d)
	{
		BindUtils.postNotifyChange(null, null, d, "editable");
	}
}
