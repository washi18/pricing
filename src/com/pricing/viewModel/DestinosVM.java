package com.pricing.viewModel;
import org.zkoss.gmaps.Gmarker;
import org.zkoss.gmaps.event.MapMouseEvent;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;

import java.io.File;
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
import com.pricing.extras.KMP;
import com.pricing.model.CCodigoPostal;
import com.pricing.model.CDestino;
import com.pricing.model.CGaleriaImageExist;
import com.pricing.model.CGaleriaImageExist4;
import com.pricing.model.CServicio;
import com.pricing.model.CSubServicio;
import com.pricing.model.Nro;
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
	private ArrayList<CGaleriaImageExist> listaImagenesExistentes;
	private ArrayList<CGaleriaImageExist4> lista4ImagenesExistentes;
	private CGaleriaImageExist4 galeria4Aux;
	private boolean mostrarImagenesExistentes;
	private boolean mostrarImagenesExistentesUpdate;
	private boolean mostrarTextImgSeleccionado;
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
	public ArrayList<CGaleriaImageExist> getListaImagenesExistentes() {
		return listaImagenesExistentes;
	}
	public void setListaImagenesExistentes(ArrayList<CGaleriaImageExist> listaImagenesExistentes) {
		this.listaImagenesExistentes = listaImagenesExistentes;
	}
	public ArrayList<CGaleriaImageExist4> getLista4ImagenesExistentes() {
		return lista4ImagenesExistentes;
	}
	public void setLista4ImagenesExistentes(ArrayList<CGaleriaImageExist4> lista4ImagenesExistentes) {
		this.lista4ImagenesExistentes = lista4ImagenesExistentes;
	}
	public boolean isMostrarImagenesExistentes() {
		return mostrarImagenesExistentes;
	}
	public void setMostrarImagenesExistentes(boolean mostrarImagenesExistentes) {
		this.mostrarImagenesExistentes = mostrarImagenesExistentes;
	}
	public boolean isMostrarImagenesExistentesUpdate() {
		return mostrarImagenesExistentesUpdate;
	}
	public void setMostrarImagenesExistentesUpdate(boolean mostrarImagenesExistentesUpdate) {
		this.mostrarImagenesExistentesUpdate = mostrarImagenesExistentesUpdate;
	}
	public boolean isMostrarTextImgSeleccionado() {
		return mostrarTextImgSeleccionado;
	}
	public void setMostrarTextImgSeleccionado(boolean mostrarTextImgSeleccionado) {
		this.mostrarTextImgSeleccionado = mostrarTextImgSeleccionado;
	}
	/*======metodos=====*/
	@Init
	public void initVM()
	{
			Encryptar encrip= new Encryptar();
//			System.out.println("Aqui esta la contraseña desencriptada-->"+encrip.decrypt("cyS249O3OHZTsG0ww1rYrw=="));
			
			destinoDao=new CDestinoDAO();
			oDestinoNuevo=new CDestino();
			oDestinoUpdate=new CDestino();
			mostrarImagenesExistentes=false;
			mostrarImagenesExistentesUpdate=false;
			mostrarTextImgSeleccionado=false;
			galeria4Aux=new CGaleriaImageExist4();
	}
	@GlobalCommand
	public void recuperarDestinos()
	{
		HttpSession ses = (HttpSession)Sessions.getCurrent().getNativeSession();
		String user=(String)ses.getAttribute("usuario");
	    String pas=(String)ses.getAttribute("clave");
		if(user!=null && pas!=null)
		{
			destinoDao.asignarListaDestinos(destinoDao.recuperarListaTodosDestinosBD());
			setListaDestinos(destinoDao.getListaDestinos());
			/**Iniciar codigos postales**/
			setListaCodigosPostales((new CCodigoPostal()).listaCodigosPostales());
		}
		BindUtils.postNotifyChange(null, null, this,"listaDestinos");
		BindUtils.postNotifyChange(null, null, this,"listaCodigosPostales");
	}
	@Command
	public void buscarImagen(@BindingParam("texto")String texto)
	{
		ubicarTodosImagenes();
		ArrayList<CGaleriaImageExist> listaAuxImagenesExistentes=new ArrayList<CGaleriaImageExist>();
		for(CGaleriaImageExist galeria:listaImagenesExistentes)
		{
			if(KMP.KMPSearch(texto, galeria.getRutaImagen()))
				listaAuxImagenesExistentes.add(galeria);
		}
		setListaImagenesExistentes(listaAuxImagenesExistentes);
		rellenarImagenesExistentes();
	}
	public void recuperarTodasImagenesExistentes()
	{
		ubicarTodosImagenes();
		//====Rellenando las imagenes para mostraren la interfaz==
		rellenarImagenesExistentes();
	}
	public void rellenarImagenesExistentes()
	{
		for(int i=0;i<listaImagenesExistentes.size();i+=4)
		{
			CGaleriaImageExist4 images=new CGaleriaImageExist4();
			images.setGaleria1(listaImagenesExistentes.get(i));
			if((i+1)<listaImagenesExistentes.size())
				images.setGaleria2(listaImagenesExistentes.get(i+1));
			if((i+2)<listaImagenesExistentes.size())
				images.setGaleria3(listaImagenesExistentes.get(i+2));
			if((i+3)<listaImagenesExistentes.size())
				images.setGaleria4(listaImagenesExistentes.get(i+3));
			lista4ImagenesExistentes.add(images);
		}
		BindUtils.postNotifyChange(null, null, this, "lista4ImagenesExistentes");
	}
	public void ubicarTodosImagenes()
	{
		listaImagenesExistentes=new ArrayList<CGaleriaImageExist>();
		lista4ImagenesExistentes=new ArrayList<CGaleriaImageExist4>();
		//====HOTELES====
		File directorio=new File(ScannUtil.getPathImagenHoteles());
		String[] imagenes=directorio.list();
		for(int i=0;i<imagenes.length;i++)
		{
			CGaleriaImageExist galeria=new CGaleriaImageExist();
			galeria.setRutaImagen("img/hoteles/"+imagenes[i]);
			galeria.setVisible(true);
			listaImagenesExistentes.add(galeria);
		}
		//====TOURS======
		directorio=new File(ScannUtil.getPathImagenPaquetes());
		imagenes=directorio.list();
		for(int i=0;i<imagenes.length;i++)
		{
			CGaleriaImageExist galeria=new CGaleriaImageExist();
			galeria.setRutaImagen("img/tours/"+imagenes[i]);
			galeria.setVisible(true);
			listaImagenesExistentes.add(galeria);
		}
		//====SERVICIOS===
		directorio=new File(ScannUtil.getPathImagensSubServicios());
		imagenes=directorio.list();
		for(int i=0;i<imagenes.length;i++)
		{
			CGaleriaImageExist galeria=new CGaleriaImageExist();
			galeria.setRutaImagen("img/servicios/"+imagenes[i]);
			galeria.setVisible(true);
			listaImagenesExistentes.add(galeria);
		}
		//====DESTINOS====
		directorio=new File(ScannUtil.getPathImagenDestinos());
		imagenes=directorio.list();
		for(int i=0;i<imagenes.length;i++)
		{
			CGaleriaImageExist galeria=new CGaleriaImageExist();
			galeria.setRutaImagen("img/destinos/"+imagenes[i]);
			galeria.setVisible(true);
			listaImagenesExistentes.add(galeria);
		}
		//====ANDROID====
		directorio=new File(ScannUtil.getPathImagenAndroid());
		imagenes=directorio.list();
		for(int i=0;i<imagenes.length;i++)
		{
			CGaleriaImageExist galeria=new CGaleriaImageExist();
			galeria.setRutaImagen("img/android/"+imagenes[i]);
			galeria.setVisible(true);
			listaImagenesExistentes.add(galeria);
		}
	}
	public void ubicarHotelesImagenes(){
		File directorio=new File(ScannUtil.getPathImagenHoteles());
		String[] imagenes=directorio.list();
		for(int i=0;i<imagenes.length;i++)
		{
			CGaleriaImageExist galeria=new CGaleriaImageExist();
			galeria.setRutaImagen("img/hoteles/"+imagenes[i]);
			galeria.setVisible(true);
			listaImagenesExistentes.add(galeria);
		}
	}
	public void ubicarToursImagenes()
	{
		File directorio=new File(ScannUtil.getPathImagenPaquetes());
		String[] imagenes=directorio.list();
		for(int i=0;i<imagenes.length;i++)
		{
			CGaleriaImageExist galeria=new CGaleriaImageExist();
			galeria.setRutaImagen("img/tours/"+imagenes[i]);
			galeria.setVisible(true);
			listaImagenesExistentes.add(galeria);
		}
	}
	public void ubicarServiciosImagenes()
	{
		File directorio=new File(ScannUtil.getPathImagensSubServicios());
		String[] imagenes=directorio.list();
		for(int i=0;i<imagenes.length;i++)
		{
			CGaleriaImageExist galeria=new CGaleriaImageExist();
			galeria.setRutaImagen("img/servicios/"+imagenes[i]);
			galeria.setVisible(true);
			listaImagenesExistentes.add(galeria);
		}
	}
	public void ubicarDestinosImagenes()
	{
		File directorio=new File(ScannUtil.getPathImagenDestinos());
		String[] imagenes=directorio.list();
		for(int i=0;i<imagenes.length;i++)
		{
			CGaleriaImageExist galeria=new CGaleriaImageExist();
			galeria.setRutaImagen("img/destinos/"+imagenes[i]);
			galeria.setVisible(true);
			listaImagenesExistentes.add(galeria);
		}
	}
	public void ubicarAndroidImagenes()
	{
		File directorio=new File(ScannUtil.getPathImagenAndroid());
		String[] imagenes=directorio.list();
		for(int i=0;i<imagenes.length;i++)
		{
			CGaleriaImageExist galeria=new CGaleriaImageExist();
			galeria.setRutaImagen("img/android/"+imagenes[i]);
			galeria.setVisible(true);
			listaImagenesExistentes.add(galeria);
		}
	}
	@Command
	@NotifyChange({"mostrarTextImgSeleccionado"})
	public void selectImagenExist(@BindingParam("galeria4")CGaleriaImageExist4 galeria4,
			@BindingParam("galeria")CGaleriaImageExist galeria,@BindingParam("destino")CDestino destino)
	{
		galeria4Aux.getGaleria1().setSeleccionado(false);
		galeria4Aux.getGaleria1().setStyle_Select("div_content_imageHotel");
		galeria4Aux.getGaleria2().setSeleccionado(false);
		galeria4Aux.getGaleria2().setStyle_Select("div_content_imageHotel");
		galeria4Aux.getGaleria3().setSeleccionado(false);
		galeria4Aux.getGaleria3().setStyle_Select("div_content_imageHotel");
		galeria4Aux.getGaleria4().setSeleccionado(false);
		galeria4Aux.getGaleria4().setStyle_Select("div_content_imageHotel");
		if(Nro.nroImagenes>0)Nro.decrementarNroImagenes();
		refrescarSelect(galeria4Aux);
		galeria4Aux=galeria4;
		if(galeria4.getGaleria1().equals(galeria))
		{
			if(galeria4.getGaleria1().isSeleccionado())
			{
				if(Nro.nroImagenes>0)Nro.decrementarNroImagenes();
				galeria4.getGaleria1().setSeleccionado(false);
				galeria4.getGaleria1().setStyle_Select("div_content_imageHotel");
			}else{
				Nro.incrementarNroImagenes();
				galeria4.getGaleria1().setSeleccionado(true);
				galeria4.getGaleria1().setStyle_Select("div_content_imageHotel_selected");
				asignarUrlImagenDestino(galeria4.getGaleria1().getRutaImagen(),destino,true);
			}
		}else if(galeria4.getGaleria2().equals(galeria))
		{
			if(galeria4.getGaleria2().isSeleccionado())
			{
				if(Nro.nroImagenes>0)Nro.decrementarNroImagenes();
				galeria4.getGaleria2().setSeleccionado(false);
				galeria4.getGaleria2().setStyle_Select("div_content_imageHotel");
			}else{
				Nro.incrementarNroImagenes();
				galeria4.getGaleria2().setSeleccionado(true);
				galeria4.getGaleria2().setStyle_Select("div_content_imageHotel_selected");
				asignarUrlImagenDestino(galeria4.getGaleria2().getRutaImagen(),destino,true);
			}
		}else if(galeria4.getGaleria3().equals(galeria))
		{
			if(galeria4.getGaleria3().isSeleccionado())
			{
				if(Nro.nroImagenes>0)Nro.decrementarNroImagenes();
				galeria4.getGaleria3().setSeleccionado(false);
				galeria4.getGaleria3().setStyle_Select("div_content_imageHotel");
			}else{
				Nro.incrementarNroImagenes();
				galeria4.getGaleria3().setSeleccionado(true);
				galeria4.getGaleria3().setStyle_Select("div_content_imageHotel_selected");
				asignarUrlImagenDestino(galeria4.getGaleria3().getRutaImagen(),destino,true);
			}
		}else if(galeria4.getGaleria4().equals(galeria))
		{
			if(galeria4.getGaleria4().isSeleccionado())
			{
				if(Nro.nroImagenes>0)Nro.decrementarNroImagenes();
				galeria4.getGaleria4().setSeleccionado(false);
				galeria4.getGaleria4().setStyle_Select("div_content_imageHotel");
			}else{
				Nro.incrementarNroImagenes();
				galeria4.getGaleria4().setSeleccionado(true);
				galeria4.getGaleria4().setStyle_Select("div_content_imageHotel_selected");
				asignarUrlImagenDestino(galeria4.getGaleria4().getRutaImagen(),destino,true);
			}
		}
		if(Nro.nroImagenes>0)mostrarTextImgSeleccionado=true;
		else if(Nro.nroImagenes==0)mostrarTextImgSeleccionado=false;
		refrescarSelect(galeria4);
	}
	@Command
	public void selectTipoImagenExistente(@BindingParam("tipo")String tipo)
	{
		listaImagenesExistentes=new ArrayList<CGaleriaImageExist>();
		lista4ImagenesExistentes=new ArrayList<CGaleriaImageExist4>();
		if(tipo.equals("todos"))ubicarTodosImagenes();
		else if(tipo.equals("hoteles"))ubicarHotelesImagenes();
		else if(tipo.equals("tours"))ubicarToursImagenes();
		else if(tipo.equals("servicios"))ubicarServiciosImagenes();
		else if(tipo.equals("destinos"))ubicarDestinosImagenes();
		else if(tipo.equals("android"))ubicarAndroidImagenes();
		rellenarImagenesExistentes();
	}
	@Command
	@NotifyChange({"mostrarImagenesExistentes","mostrarImagenesExistentesUpdate"})
	public void cerrarImagenesExistentes()
	{
		mostrarImagenesExistentes=false;
		mostrarImagenesExistentesUpdate=false;
	}
	@Command
	@NotifyChange({"mostrarImagenesExistentes","mostrarImagenesExistentesUpdate","mostrarTextImgSeleccionado"})
	public void invocaImagenesExistentes(@BindingParam("opcion")int opcion)
	{
		Nro.inicializarNroImagenes();
		if(opcion==1)
		{
			mostrarImagenesExistentes=true;
			mostrarImagenesExistentesUpdate=false;
		}else{
			mostrarImagenesExistentes=false;
			mostrarImagenesExistentesUpdate=true;
		}
		mostrarTextImgSeleccionado=false;
		recuperarTodasImagenesExistentes();
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
				            asignarUrlImagenDestino(img.getName(),oDestinoNuevo,false);
				            Clients.showNotification(img.getName()+" Se inserto",Clients.NOTIFICATION_TYPE_INFO,comp,"before_start",2700);
						} else {
							Messagebox.show(media+"Error", "Error", Messagebox.OK, Messagebox.ERROR);
								}
					}
			     });
	}
	public void asignarUrlImagenDestino(String url,CDestino destino,boolean imgEXist)
	{
		if(imgEXist)
			destino.setUrlImagen(url);
		else
			destino.setUrlImagen("img/destinos/"+url);
		BindUtils.postNotifyChange(null, null, destino,"urlImagen");
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
				            asignarUrlImagenDestino(img.getName(),destino,false);
				            Clients.showNotification(img.getName()+" Se inserto",Clients.NOTIFICATION_TYPE_INFO,comp,"before_start",2700);
						} else {
							Messagebox.show(media+"Error", "Error", Messagebox.OK, Messagebox.ERROR);
								}
					}
			     });
	}
	public void refrescaFilaTemplate(CDestino d)
	{
		BindUtils.postNotifyChange(null, null, d, "editable");
	}
	public void refrescarSelect(CGaleriaImageExist4 galeria4)
	{
		BindUtils.postNotifyChange(null, null, galeria4, "galeria1");
		BindUtils.postNotifyChange(null, null, galeria4, "galeria2");
		BindUtils.postNotifyChange(null, null, galeria4, "galeria3");
		BindUtils.postNotifyChange(null, null, galeria4, "galeria4");
	}
}
