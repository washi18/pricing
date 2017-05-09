package com.android.viewModel;

import java.util.ArrayList;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Messagebox;

import com.android.dao.CElementosDAO;
import com.android.dao.CItemsDAO;
import com.android.model.CElementos;
import com.android.model.CItems;
import com.pricing.util.ScannUtil;

public class elementosVM {
	private CElementos oElemento;
	private CElementos oElementoUpdate;
	private ArrayList<CElementos> listaElementos;
	private ArrayList<CItems> listaItems;
	//=======================================
	public ArrayList<CItems> getListaItems() {
		return listaItems;
	}
	public void setListaItems(ArrayList<CItems> listaItems) {
		this.listaItems = listaItems;
	}
	public CElementos getoElemento() {
		return oElemento;
	}
	public void setoElemento(CElementos oElemento) {
		this.oElemento = oElemento;
	}
	public ArrayList<CElementos> getListaElementos() {
		return listaElementos;
	}
	public void setListaElementos(ArrayList<CElementos> listaElementos) {
		this.listaElementos = listaElementos;
	}
	//===========================
	@Init
	public void initVM()
	{
		oElemento=new CElementos();
		oElementoUpdate=new CElementos();
		listaElementos=new ArrayList<CElementos>();
		listaItems=new ArrayList<CItems>();
	}
	@GlobalCommand
	@NotifyChange({"listaElementos","listaItems"})
	public void cargarDatosElementos()
	{
		CElementosDAO elementosDao=new CElementosDAO();
		elementosDao.asignarListaElementos(elementosDao.recuperarListaElementosBD());
		setListaElementos(elementosDao.getListaElementos());
		//============================================
		CItemsDAO itemsDao=new CItemsDAO();
		itemsDao.asignarListaItems(itemsDao.recuperarListaItemsBD());
		setListaItems(itemsDao.getListaItems());
	}
	@Command
	public void selectItems(@BindingParam("codItem")String codItem,@BindingParam("elemento")CElementos elemento)
	{
		elemento.setcItemsCod(Integer.parseInt(codItem));
	}
	@Command
	@NotifyChange({"oElemento","listaElementos"})
	public void registrarElemento(@BindingParam("componente")Component comp)
	{
		if(!validoParaInsertar(comp))
			return;
		CElementosDAO elementosDao=new CElementosDAO();
		boolean correcto=elementosDao.isOperationCorrect(elementosDao.registrarElemento(oElemento));
		if(correcto)
		{
			oElemento=new CElementos();
			elementosDao.asignarListaElementos(elementosDao.recuperarListaElementosBD());
			setListaElementos(elementosDao.getListaElementos());
			Clients.showNotification("El registro del elemento fue correcto",Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",3000);
		}
	}
	public boolean validoParaInsertar(Component comp)
	{
		boolean valido=true;
		if(oElemento.getcItemsCod()==0)
		{
			valido=false;
			Clients.showNotification("Es necesario que seleccione a que item correspondera el elemento",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
		}else if(oElemento.getcNombre1Idioma1().equals(""))
		{
			valido=false;
			Clients.showNotification("El elemento debe de tener un nombre",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
		}else if(oElemento.getcImagen1().equals(""))
		{
			valido=false;
			Clients.showNotification("El elemento debe de contar al menos con una imagen",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
		}else if(oElemento.getcDirigidoIdioma1().equals(""))
		{
			valido=false;
			Clients.showNotification("El elemento debe de contar una descripcion a que tipo de cliente esta dirigido",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
		}
		return valido;
	}
	@Command
	public void actualizarElemento(@BindingParam("elemento")CElementos elemento,@BindingParam("componente")Component comp)
	{
		if(!validoParaActualizar(comp,elemento))
			return;
		CElementosDAO elementoDao=new CElementosDAO();
		boolean correcto=elementoDao.isOperationCorrect(elementoDao.modificarElemento(elemento));
		if(correcto)
		{
			elemento.setEditable(false);
			refrescaFilaTemplate(elemento);
			Clients.showNotification("La modificacion del elemento fue correcto",Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",3000);
		}
	}
	public boolean validoParaActualizar(Component comp,CElementos elemento)
	{
		boolean valido=true;
		if(elemento.getcNombre1Idioma1().equals(""))
		{
			valido=false;
			Clients.showNotification("El elemento debe de tener un titulo o nombre",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
		}else if(elemento.getcImagen1().equals(""))
		{
			valido=false;
			Clients.showNotification("El item debe de contar al menos con una imagen",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
		}else if(elemento.getcDirigidoIdioma1().equals(""))
		{
			valido=false;
			Clients.showNotification("El elemento debe de contar una descripcion a que tipo de cliente esta dirigido",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
		}
		return valido;
	}
	@Command
	public void uploadImagenes(@BindingParam("elemento")final CElementos elemento,@BindingParam("componente")final Component comp,
			@BindingParam("opcion")final int opcion)
	{
		Fileupload.get(100, new EventListener<UploadEvent>() {
			public void onEvent(UploadEvent event) {
				org.zkoss.util.media.Media[] listaMedias = event.getMedias();
				for (Media media : listaMedias) {
					if (media instanceof org.zkoss.image.Image) {
						org.zkoss.image.Image img = (org.zkoss.image.Image) media;
						// Con este metodo(uploadFile) de clase guardo la imagen
						// en la ruta del servidor
						boolean b = ScannUtil.uploadFileAndroid(img);
						// ================================
						// Una vez creado el nuevo nombre de archivo de imagen
						// se procede a cambiar el nombre
						String urlImagen = ScannUtil.getPathImagenAndroid() + img.getName();
						asignarRutaImagenElemento(img.getName(),elemento,opcion);
						Clients.showNotification(img.getName() + " Se subio al servidor.",
								Clients.NOTIFICATION_TYPE_INFO, comp, "before_start", 2700);
					} else {
						Messagebox.show("No es una imagen: " + media, "Error", Messagebox.OK, Messagebox.ERROR);
						break; // not to show too many errors
					}
				}
			}
		});
	}
	public void asignarRutaImagenElemento(String url,CElementos elemento,int opcion)
	{
		if(opcion==1)
		{
			elemento.setcImagen1("/img/android/"+url);
			BindUtils.postNotifyChange(null, null, elemento,"cImagen1");
		}else if(opcion==2)
		{
			elemento.setcImagen2("/img/android/"+url);
			BindUtils.postNotifyChange(null, null, elemento,"cImagen2");
		}else
		{
			elemento.setcImagen3("/img/android/"+url);
			BindUtils.postNotifyChange(null, null, elemento,"cImagen3");
		}
	}
	@Command
	public void cambioIdiomas(@BindingParam("idioma")String idioma,@BindingParam("elemento")CElementos elemento)
	{
		if (idioma.equals("Espanol")) {
			elemento.setVisibleEspanol(true);
			elemento.setVisibleIngles(false);
			elemento.setVisiblePortugues(false);
		} else if (idioma.equals("Ingles")) {
			elemento.setVisibleEspanol(false);
			elemento.setVisibleIngles(true);
			elemento.setVisiblePortugues(false);
		} else if (idioma.equals("Portugues")) {
			elemento.setVisibleEspanol(false);
			elemento.setVisibleIngles(false);
			elemento.setVisiblePortugues(true);
		}
		BindUtils.postNotifyChange(null, null, elemento, "visibleEspanol");
		BindUtils.postNotifyChange(null, null, elemento, "visibleIngles");
		BindUtils.postNotifyChange(null, null, elemento, "visiblePortugues");
	}
	@Command
	public void Editar(@BindingParam("elemento") CElementos e) {
		oElementoUpdate.setEditable(false);
		refrescaFilaTemplate(oElementoUpdate);
		oElementoUpdate = e;
		// le damos estado editable
		e.setEditable(!e.isEditable());
		// lcs.setEditingStatus(!lcs.getEditingStatus());
		refrescaFilaTemplate(e);
	}
	public void refrescaFilaTemplate(CElementos e) {
		BindUtils.postNotifyChange(null, null, e, "editable");
	}
}
