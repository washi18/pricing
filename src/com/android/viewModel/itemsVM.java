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

import com.android.dao.CItemsDAO;
import com.android.dao.CSubMenuDAO;
import com.android.model.CItems;
import com.android.model.CSubMenu;
import com.pricing.util.ScannUtil;

public class itemsVM {
	private CItems oItems;
	private CItems oItemsUpdate;
	private ArrayList<CItems> listaItems;
	private ArrayList<CSubMenu> listaSubMenu;
	//=======================================
	public ArrayList<CSubMenu> getListaSubMenu(){
		return listaSubMenu;
	}
	public void setListaSubMenu(ArrayList<CSubMenu> listaSubMenu){
		this.listaSubMenu = listaSubMenu;
	}
	public CItems getoItems() {
		return oItems;
	}
	public void setoItems(CItems oItems) {
		this.oItems = oItems;
	}
	public ArrayList<CItems> getListaItems() {
		return listaItems;
	}
	public void setListaItems(ArrayList<CItems> listaItems) {
		this.listaItems = listaItems;
	}
	//===========================
	@Init
	public void initVM()
	{
		oItems=new CItems();
		oItemsUpdate=new CItems();
		listaItems=new ArrayList<CItems>();
		listaSubMenu=new ArrayList<CSubMenu>();
	}
	@GlobalCommand
	@NotifyChange({"listaSubMenu","listaItems"})
	public void cargarDatosItems()
	{
		CItemsDAO itemsDao=new CItemsDAO();
		itemsDao.asignarListaItems(itemsDao.recuperarListaItemsBD());
		setListaItems(itemsDao.getListaItems());
		//============================================
		CSubMenuDAO subMenuDao=new CSubMenuDAO();
		subMenuDao.asignarListaSubMenu(subMenuDao.recuperarListaSubMenusBD());
		setListaSubMenu(subMenuDao.getListaSubMenu());
	}
	@Command
	public void selectSubMenu(@BindingParam("codSubMenu")String codSubMenu,@BindingParam("item")CItems item)
	{
		item.setcSubMenuCod(Integer.parseInt(codSubMenu));
	}
	@Command
	@NotifyChange({"oItems","listaItems"})
	public void registrarItems(@BindingParam("componente")Component comp)
	{
		if(!validoParaInsertar(comp))
			return;
		CItemsDAO itemsDao=new CItemsDAO();
		boolean correcto=itemsDao.isOperationCorrect(itemsDao.registrarItem(oItems));
		if(correcto)
		{
			oItems=new CItems();
			itemsDao.asignarListaItems(itemsDao.recuperarListaItemsBD());
			setListaItems(itemsDao.getListaItems());
			Clients.showNotification("El registro del item fue correcto",Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",3000);
		}
	}
	public boolean validoParaInsertar(Component comp)
	{
		boolean valido=true;
		if(oItems.getcSubMenuCod()==0)
		{
			valido=false;
			Clients.showNotification("Es necesario que seleccione a que submenu correspondera el item",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
		}else if(oItems.getcTituloIdioma1().equals(""))
		{
			valido=false;
			Clients.showNotification("El item debe de tener un titulo o nombre",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
		}else if(oItems.getcImagen().equals(""))
		{
			valido=false;
			Clients.showNotification("El item debe de contar con una imagen",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
		}
		return valido;
	}
	@Command
	public void actualizarItem(@BindingParam("item")CItems item,@BindingParam("componente")Component comp)
	{
		if(!validoParaActualizar(comp,item))
			return;
		CItemsDAO itemsDao=new CItemsDAO();
		boolean correcto=itemsDao.isOperationCorrect(itemsDao.modificarItem(item));
		if(correcto)
		{
			item.setEditable(false);
			refrescaFilaTemplate(item);
			Clients.showNotification("La modificacion del item fue correcto",Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",3000);
		}
	}
	public boolean validoParaActualizar(Component comp,CItems item)
	{
		boolean valido=true;
		if(item.getcTituloIdioma1().equals(""))
		{
			valido=false;
			Clients.showNotification("El submenu debe de tener un titulo o nombre",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
		}else if(item.getcImagen().equals(""))
		{
			valido=false;
			Clients.showNotification("El item debe de contar con una imagen",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
		}
		return valido;
	}
	@Command
	public void uploadImagenes(@BindingParam("item")final CItems item,@BindingParam("componente")final Component comp)
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
						asignarRutaImagenItem(img.getName(), item);
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
	public void asignarRutaImagenItem(String url,CItems item)
	{
			item.setcImagen("/img/android/"+url);
			BindUtils.postNotifyChange(null, null, item,"cImagen");
	}
	@Command
	public void cambioIdiomas(@BindingParam("idioma")String idioma,@BindingParam("item")CItems item)
	{
		if (idioma.equals("Espanol")) {
			item.setVisibleEspanol(true);
			item.setVisibleIngles(false);
			item.setVisiblePortugues(false);
		} else if (idioma.equals("Ingles")) {
			item.setVisibleEspanol(false);
			item.setVisibleIngles(true);
			item.setVisiblePortugues(false);
		} else if (idioma.equals("Portugues")) {
			item.setVisibleEspanol(false);
			item.setVisibleIngles(false);
			item.setVisiblePortugues(true);
		}
		BindUtils.postNotifyChange(null, null, item, "visibleEspanol");
		BindUtils.postNotifyChange(null, null, item, "visibleIngles");
		BindUtils.postNotifyChange(null, null, item, "visiblePortugues");
	}
	@Command
	public void Editar(@BindingParam("item") CItems i) {
		oItemsUpdate.setEditable(false);
		refrescaFilaTemplate(oItemsUpdate);
		oItemsUpdate = i;
		// le damos estado editable
		i.setEditable(!i.isEditable());
		// lcs.setEditingStatus(!lcs.getEditingStatus());
		refrescaFilaTemplate(i);
	}
	public void refrescaFilaTemplate(CItems i) {
		BindUtils.postNotifyChange(null, null, i, "editable");
	}
}
