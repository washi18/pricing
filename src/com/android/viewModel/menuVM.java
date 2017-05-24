package com.android.viewModel;

import java.io.IOException;
import java.util.ArrayList;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Div;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Include;
import org.zkoss.zul.Messagebox;

import com.android.dao.CDatosGeneralesDAO;
import com.android.dao.CDestinosMovilDAO;
import com.android.dao.CElementosDAO;
import com.android.dao.CItemsDAO;
import com.android.dao.CMenuDAO;
import com.android.dao.CSubMenuDAO;
import com.android.model.CDatosGenerales;
import com.android.model.CDestinoMovil;
import com.android.model.CElementos;
import com.android.model.CItems;
import com.android.model.CItemsDestino;
import com.android.model.CMenu;
import com.android.model.CSubMenu;
import com.pricing.model.CPaquete;
import com.pricing.model.CPaqueteServicio;
import com.pricing.model.CServicio;
import com.pricing.util.ScannUtil;

public class menuVM {
	private CMenu oMenu;
	private CSubMenu oSubMenu;
	private CItems oItem;
	private CElementos oElemento;
	private CDatosGenerales oDatoGeneral;
	private ArrayList<CMenu> listaMenu;
	private ArrayList<CDestinoMovil> listaDestinosMovil;
	private boolean visibleMenu;
	private boolean visibleSubMenu;
	private boolean visibleItem;
	private boolean visibleElemento;
	private boolean visibleDatoGeneral;
	//====================
	public CMenu getoMenu() {
		return oMenu;
	}
	public void setoMenu(CMenu oMenu) {
		this.oMenu = oMenu;
	}
	public ArrayList<CMenu> getListaMenu() {
		return listaMenu;
	}
	public void setListaMenu(ArrayList<CMenu> listaMenu) {
		this.listaMenu = listaMenu;
	}
	public boolean isVisibleMenu() {
		return visibleMenu;
	}
	public void setVisibleMenu(boolean visibleMenu) {
		this.visibleMenu = visibleMenu;
	}
	public CSubMenu getoSubMenu() {
		return oSubMenu;
	}
	public void setoSubMenu(CSubMenu oSubMenu) {
		this.oSubMenu = oSubMenu;
	}
	public boolean isVisibleSubMenu() {
		return visibleSubMenu;
	}
	public void setVisibleSubMenu(boolean visibleSubMenu) {
		this.visibleSubMenu = visibleSubMenu;
	}
	public CItems getoItem() {
		return oItem;
	}
	public void setoItem(CItems oItem) {
		this.oItem = oItem;
	}
	public boolean isVisibleItem() {
		return visibleItem;
	}
	public void setVisibleItem(boolean visibleItem) {
		this.visibleItem = visibleItem;
	}
	public CElementos getoElemento() {
		return oElemento;
	}
	public void setoElemento(CElementos oElemento) {
		this.oElemento = oElemento;
	}
	public boolean isVisibleElemento() {
		return visibleElemento;
	}
	public void setVisibleElemento(boolean visibleElemento) {
		this.visibleElemento = visibleElemento;
	}
	public CDatosGenerales getoDatoGeneral() {
		return oDatoGeneral;
	}
	public void setoDatoGeneral(CDatosGenerales oDatoGeneral) {
		this.oDatoGeneral = oDatoGeneral;
	}
	public boolean isVisibleDatoGeneral() {
		return visibleDatoGeneral;
	}
	public void setVisibleDatoGeneral(boolean visibleDatoGeneral) {
		this.visibleDatoGeneral = visibleDatoGeneral;
	}
	public ArrayList<CDestinoMovil> getListaDestinosMovil() {
		return listaDestinosMovil;
	}
	public void setListaDestinosMovil(ArrayList<CDestinoMovil> listaDestinosMovil) {
		this.listaDestinosMovil = listaDestinosMovil;
	}
	//====================
	@Init
	public void initVM()
	{
		oMenu=new CMenu();
		oSubMenu=new CSubMenu();
		oItem=new CItems();
		oElemento=new CElementos();
		oDatoGeneral=new CDatosGenerales();
		listaMenu=new ArrayList<CMenu>();
		//==============================
		visibleMenu=false;
		visibleSubMenu=false;
		visibleItem=false;
		visibleElemento=false;
		visibleDatoGeneral=false;
	}
	@GlobalCommand
	@NotifyChange("listaMenu")
	public void cargarDatosMenu()
	{
		CMenuDAO menuDao=new CMenuDAO();
		menuDao.asignarListaMenu(menuDao.recuperarListaMenuBD());
		setListaMenu(menuDao.getListaMenu());
		System.out.println("Aqui toy: "+listaMenu.size());
	}
	@Command
	public void selectDestinoMovil(@BindingParam("destino") CDestinoMovil destino) {
		if (destino.isSeleccionado())
			destino.setSeleccionado(false);
		else
			destino.setSeleccionado(true);
	}
	@Command
	@NotifyChange({"oMenu","visibleMenu","visibleSubMenu","visibleItem","visibleElemento","visibleDatoGeneral"})
	public void mostrarNuevoMenu()
	{
		oMenu=new CMenu();
		visibleMenu=true;
		visibleSubMenu=false;
		visibleItem=false;
		visibleElemento=false;
		visibleDatoGeneral=false;
	}
	@Command
	@NotifyChange({"oSubMenu","visibleMenu","visibleSubMenu","visibleItem","visibleElemento","visibleDatoGeneral"})
	public void mostrarNuevoSubMenu(@BindingParam("menu")CMenu menu)
	{
		oSubMenu=new CSubMenu();
		oSubMenu.setcMenuCod(menu.getcMenuCod());
		visibleMenu=false;
		visibleSubMenu=true;
		visibleItem=false;
		visibleElemento=false;
		visibleDatoGeneral=false;
	}
	@Command
	@NotifyChange({"oItem","visibleMenu","visibleSubMenu","visibleItem","visibleElemento","visibleDatoGeneral",
		"listaDestinosMovil"})
	public void mostrarNuevoItem(@BindingParam("submenu")CSubMenu submenu)
	{
		oItem=new CItems();
		oItem.setcSubMenuCod(submenu.getcSubMenuCod());
		visibleMenu=false;
		visibleSubMenu=false;
		visibleItem=true;
		visibleElemento=false;
		visibleDatoGeneral=false;
		CDestinosMovilDAO destinosMovilDao=new CDestinosMovilDAO();
		destinosMovilDao.asignarListaDestinosMovil(destinosMovilDao.recuperarListaDestinosMovilBD());
		setListaDestinosMovil(destinosMovilDao.getListaDestinosMovil());
	}
	@Command
	@NotifyChange({"oDatoGeneral","visibleMenu","visibleSubMenu","visibleItem","visibleElemento","visibleDatoGeneral"})
	public void mostrarNuevoDatoGeneral(@BindingParam("item")CItems item)
	{
		oDatoGeneral=new CDatosGenerales();
		oDatoGeneral.setcItemsCod(item.getcItemsCod());;
		visibleMenu=false;
		visibleSubMenu=false;
		visibleItem=false;
		visibleElemento=false;
		visibleDatoGeneral=true;
	}
	@Command
	@NotifyChange({"oElemento","visibleMenu","visibleSubMenu","visibleItem","visibleElemento","visibleDatoGeneral"})
	public void mostrarNuevoElemento(@BindingParam("item")CItems item)
	{
		oElemento=new CElementos();
		oElemento.setcItemsCod(item.getcItemsCod());;
		visibleMenu=false;
		visibleSubMenu=false;
		visibleItem=false;
		visibleElemento=true;
		visibleDatoGeneral=false;
	}
	@Command
	@NotifyChange({"oMenu","listaMenu"})
	public void registrarMenu(@BindingParam("componente")Component comp)
	{
		if(!validoParaInsertar_menu(comp))
			return;
		CMenuDAO menuDao=new CMenuDAO();
		boolean correcto=menuDao.isOperationCorrect(menuDao.registrarMenu(oMenu));
		if(correcto)
		{
			oMenu=new CMenu();
			menuDao.asignarListaMenu(menuDao.recuperarListaMenuBD());
			setListaMenu(menuDao.getListaMenu());
			Clients.showNotification("El registro del menu fue correcto",Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",3000);
		}
	}
	@Command
	@NotifyChange({"oSubMenu","listaMenu"})
	public void registrarSubMenu(@BindingParam("componente")Component comp)
	{
		if(!validoParaInsertar_submenu(comp))
			return;
		CSubMenuDAO subMenuDao=new CSubMenuDAO();
		CMenuDAO menuDao=new CMenuDAO();
		boolean correcto=subMenuDao.isOperationCorrect(subMenuDao.registrarSubMenu(oSubMenu));
		if(correcto)
		{
			oSubMenu=new CSubMenu();
			menuDao.asignarListaMenu(menuDao.recuperarListaMenuBD());
			setListaMenu(menuDao.getListaMenu());
			Clients.showNotification("El registro del submenu fue correcto",Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",3000);
		}
	}
	@Command
	@NotifyChange({"oItem","listaMenu"})
	public void registrarItems(@BindingParam("componente")Component comp)
	{
		if(!validoParaInsertar_item(comp))
			return;
		CItemsDAO itemsDao=new CItemsDAO();
		CMenuDAO menuDao=new CMenuDAO();
		int codItem=itemsDao.recuperarCodigoItem(itemsDao.registrarItem(oItem));
		if(codItem!=0)
		{
			for(CDestinoMovil destino:listaDestinosMovil)
			{
				if(destino.isSeleccionado())
				{
					boolean b = itemsDao.isOperationCorrect(
							itemsDao.insertarItemDestinoMovil(codItem,destino.getnDestinoCod()));
				}
			}
			oItem=new CItems();
			menuDao.asignarListaMenu(menuDao.recuperarListaMenuBD());
			setListaMenu(menuDao.getListaMenu());
			Clients.showNotification("El registro del item fue correcto",Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",3000);
		}
	}
	@Command
	@NotifyChange({"oDatoGeneral","listaMenu"})
	public void registrarDatoGeneral(@BindingParam("componente")Component comp)
	{
		if(!validoParaInsertar_datoGeneral(comp))
			return;
		CDatosGeneralesDAO datoGeneralDao=new CDatosGeneralesDAO();
		CMenuDAO menuDao=new CMenuDAO();
		boolean correcto=datoGeneralDao.isOperationCorrect(datoGeneralDao.registrarDatoGeneral(oDatoGeneral));
		if(correcto)
		{
			oDatoGeneral=new CDatosGenerales();
			menuDao.asignarListaMenu(menuDao.recuperarListaMenuBD());
			setListaMenu(menuDao.getListaMenu());
			Clients.showNotification("El registro del dato general fue correcto",Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",3000);
		}
	}
	@Command
	@NotifyChange({"oElemento","listaMenu"})
	public void registrarElemento(@BindingParam("componente")Component comp)
	{
		if(!validoParaInsertar_elemento(comp))
			return;
		CElementosDAO elementosDao=new CElementosDAO();
		CMenuDAO menuDao=new CMenuDAO();
		boolean correcto=elementosDao.isOperationCorrect(elementosDao.registrarElemento(oElemento));
		if(correcto)
		{
			oElemento=new CElementos();
			menuDao.asignarListaMenu(menuDao.recuperarListaMenuBD());
			setListaMenu(menuDao.getListaMenu());
			Clients.showNotification("El registro del elemento fue correcto",Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",3000);
		}
	}
	public boolean validoParaInsertar_menu(Component comp)
	{
		boolean valido=true;
		if(oMenu.getcNombreIdioma1().equals(""))
		{
			valido=false;
			Clients.showNotification("El menu debe de tener un nombre",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
		}else if(oMenu.getcImagenIcono().equals(""))
		{
			valido=false;
			Clients.showNotification("El menu debe de contar con un icono",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
		}else if(oMenu.getcImagenFondo().equals(""))
		{
			valido=false;
			Clients.showNotification("El menu debe de contar con una imagen de fondo",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
		}
		return valido;
	}
	public boolean validoParaInsertar_submenu(Component comp)
	{
		boolean valido=true;
		if(oSubMenu.getcMenuCod()==0)
		{
			valido=false;
			Clients.showNotification("Es necesario que seleccione a que menu correspondera el submenu",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
		}else if(oSubMenu.getcNombreIdioma1().equals(""))
		{
			valido=false;
			Clients.showNotification("El submenu debe de tener un nombre",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
		}else if(oSubMenu.getcImagen().equals(""))
		{
			valido=false;
			Clients.showNotification("El submenu debe de contar con una imagen",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
		}
		return valido;
	}
	public boolean validoParaInsertar_item(Component comp)
	{
		boolean valido=true;
		if(oItem.getcSubMenuCod()==0)
		{
			valido=false;
			Clients.showNotification("Es necesario que seleccione a que submenu correspondera el item",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
		}else if(oItem.getcTituloIdioma1().equals(""))
		{
			valido=false;
			Clients.showNotification("El item debe de tener un titulo o nombre",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
		}else if(oItem.getcImagen().equals(""))
		{
			valido=false;
			Clients.showNotification("El item debe de contar con una imagen",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
		}
		return valido;
	}
	public boolean validoParaInsertar_datoGeneral(Component comp)
	{
		boolean valido=true;
		if(oDatoGeneral.getcItemsCod()==0)
		{
			valido=false;
			Clients.showNotification("Es necesario que seleccione a que item correspondera el item",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
		}else if(oDatoGeneral.getcTituloIdioma1().equals(""))
		{
			valido=false;
			Clients.showNotification("El dato general debe de tener un titulo o nombre",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
		}else if(oDatoGeneral.getcImagen().equals(""))
		{
			valido=false;
			Clients.showNotification("El dato general debe de contar con una imagen",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
		}
		return valido;
	}
	public boolean validoParaInsertar_elemento(Component comp)
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
	public void actualizarMenu(@BindingParam("menu")CMenu menu,@BindingParam("componente")Component comp)
	{
		if(!validoParaActualizar_menu(comp,menu))
			return;
		CMenuDAO menuDao=new CMenuDAO();
		boolean correcto=menuDao.isOperationCorrect(menuDao.modificarMenu(menu));
		if(correcto)
		{
			menu.setEditable(false);
			refrescaFilaTemplate(menu);
			Clients.showNotification("La modificacion del menu fue correcto",Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",3000);
		}
	}
	@Command
	public void actualizarSubMenu(@BindingParam("submenu")CSubMenu submenu,@BindingParam("componente")Component comp)
	{
		if(!validoParaActualizar_submenu(comp,submenu))
			return;
		CSubMenuDAO subMenuDao=new CSubMenuDAO();
		boolean correcto=subMenuDao.isOperationCorrect(subMenuDao.modificarSubMenu(submenu));
		if(correcto)
		{
			Clients.showNotification("La modificacion del submenu fue correcto",Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",3000);
		}
	}
	@Command
	@NotifyChange({"listaMenu"})
	public void actualizarItem(@BindingParam("item")CItems item,@BindingParam("componente")Component comp)
	{
		if(!validoParaActualizar_item(comp,item))
			return;
		CItemsDAO itemsDao=new CItemsDAO();
		boolean correcto=itemsDao.isOperationCorrect(itemsDao.modificarItem(item));
		if(correcto)
		{
			for (CDestinoMovil destino : listaDestinosMovil) {
				boolean estaRegistrado = false;
				for (CItemsDestino id : item.getListaItemsDestino()) {
					if (id.getnDestinoCod() == destino.getnDestinoCod()) {
						estaRegistrado = true;
						if (!destino.isSeleccionado()) 
							correcto = itemsDao
									.isOperationCorrect(itemsDao.eliminarItemDestino(id.getcItemsCod()));
						break;
					}
				}
				if (destino.isSeleccionado() && !estaRegistrado)
					correcto = itemsDao.isOperationCorrect(
							itemsDao.insertarItemDestinoMovil(item.getcItemsCod(), destino.getnDestinoCod()));
			}
			CMenuDAO menuDao=new CMenuDAO();
			menuDao.asignarListaMenu(menuDao.recuperarListaMenuBD());
			setListaMenu(menuDao.getListaMenu());
			Clients.showNotification("La modificacion del item fue correcto",Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",3000);
		}
	}
	@Command
	public void actualizarDatoGeneral(@BindingParam("datoGeneral")CDatosGenerales datoGeneral,@BindingParam("componente")Component comp)
	{
		if(!validoParaActualizar_datoGeneral(comp,datoGeneral))
			return;
		CItemsDAO itemsDao=new CItemsDAO();
		CDatosGeneralesDAO datosGeneralesDao=new CDatosGeneralesDAO();
		boolean correcto=datosGeneralesDao.isOperationCorrect(datosGeneralesDao.modificarDatoGeneral(datoGeneral));
		if(correcto)
		{
			Clients.showNotification("La modificacion del item fue correcto",Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",3000);
		}
	}
	@Command
	public void actualizarElemento(@BindingParam("elemento")CElementos elemento,@BindingParam("componente")Component comp)
	{
		if(!validoParaActualizar_elemento(comp,elemento))
			return;
		CElementosDAO elementoDao=new CElementosDAO();
		boolean correcto=elementoDao.isOperationCorrect(elementoDao.modificarElemento(elemento));
		if(correcto)
		{
			Clients.showNotification("La modificacion del elemento fue correcto",Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",3000);
		}
	}
	public boolean validoParaActualizar_menu(Component comp,CMenu menu)
	{
		boolean valido=true;
		if(menu.getcNombreIdioma1().equals(""))
		{
			valido=false;
			Clients.showNotification("El menu debe de tener un nombre",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
		}else if(menu.getcImagenIcono().equals(""))
		{
			valido=false;
			Clients.showNotification("El menu debe de contar con un icono",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
		}else if(menu.getcImagenFondo().equals(""))
		{
			valido=false;
			Clients.showNotification("El menu debe de contar con una imagen de fondo",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
		}
		return valido;
	}
	public boolean validoParaActualizar_submenu(Component comp,CSubMenu submenu)
	{
		boolean valido=true;
		if(submenu.getcNombreIdioma1().equals(""))
		{
			valido=false;
			Clients.showNotification("El submenu debe de tener un nombre",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
		}else if(submenu.getcImagen().equals(""))
		{
			valido=false;
			Clients.showNotification("El submenu debe de contar con una imagen",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
		}
		return valido;
	}
	public boolean validoParaActualizar_item(Component comp,CItems item)
	{
		boolean valido=true;
		if(item.getcTituloIdioma1().equals(""))
		{
			valido=false;
			Clients.showNotification("El item debe de tener un titulo o nombre",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
		}else if(item.getcImagen().equals(""))
		{
			valido=false;
			Clients.showNotification("El item debe de contar con una imagen",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
		}
		return valido;
	}
	public boolean validoParaActualizar_datoGeneral(Component comp,CDatosGenerales datoGeneral)
	{
		boolean valido=true;
		if(datoGeneral.getcTituloIdioma1().equals(""))
		{
			valido=false;
			Clients.showNotification("El dato general debe de tener un titulo o nombre",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
		}else if(datoGeneral.getcImagen().equals(""))
		{
			valido=false;
			Clients.showNotification("El dato general debe de contar con una imagen",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
		}
		return valido;
	}
	public boolean validoParaActualizar_elemento(Component comp,CElementos elemento)
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
	public void uploadImagenes_menu(@BindingParam("menu")final CMenu menu,@BindingParam("componente")final Component comp,
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
						asignarRutaImagenMenu(img.getName(), menu,opcion);
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
	public void asignarRutaImagenMenu(String url,CMenu menu,int opcion)
	{
		if(opcion==1)
		{
			menu.setcImagenIcono("/img/android/"+url);
			BindUtils.postNotifyChange(null, null, menu,"cImagenIcono");
		}
		else
		{
			menu.setcImagenFondo("/img/android/"+url);
			BindUtils.postNotifyChange(null, null, menu,"cImagenFondo");
		}
	}
	@Command
	public void uploadImagenes_submenu(@BindingParam("submenu")final CSubMenu submenu,@BindingParam("componente")final Component comp)
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
						asignarRutaImagenSubMenu(img.getName(), submenu);
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
	public void asignarRutaImagenSubMenu(String url,CSubMenu submenu)
	{
			submenu.setcImagen("/img/android/"+url);
			BindUtils.postNotifyChange(null, null, submenu,"cImagen");
	}
	@Command
	public void uploadImagenes_item(@BindingParam("item")final CItems item,@BindingParam("componente")final Component comp)
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
	public void uploadImagenes_datoGeneral(@BindingParam("datoGeneral")final CDatosGenerales datoGeneral,@BindingParam("componente")final Component comp)
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
						asignarRutaImagenDatoGeneral(img.getName(), datoGeneral);
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
	public void asignarRutaImagenDatoGeneral(String url,CDatosGenerales datoGeneral)
	{
			datoGeneral.setcImagen("/img/android/"+url);
			BindUtils.postNotifyChange(null, null, datoGeneral,"cImagen");
	}
	@Command
	public void uploadImagenes_elemento(@BindingParam("elemento")final CElementos elemento,@BindingParam("componente")final Component comp,
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
	public void cambioIdiomas(@BindingParam("idioma")String idioma,@BindingParam("menu")CMenu menu)
	{
		if (idioma.equals("Espanol")) {
			menu.setVisibleEspanol(true);
			menu.setVisibleIngles(false);
			menu.setVisiblePortugues(false);
		} else if (idioma.equals("Ingles")) {
			menu.setVisibleEspanol(false);
			menu.setVisibleIngles(true);
			menu.setVisiblePortugues(false);
		} else if (idioma.equals("Portugues")) {
			menu.setVisibleEspanol(false);
			menu.setVisibleIngles(false);
			menu.setVisiblePortugues(true);
		}
		BindUtils.postNotifyChange(null, null, menu, "visibleEspanol");
		BindUtils.postNotifyChange(null, null, menu, "visibleIngles");
		BindUtils.postNotifyChange(null, null, menu, "visiblePortugues");
	}
	@Command
	public void Activar_Desactivar_menu(@BindingParam("menu") CMenu m, @BindingParam("texto") String texto) {
		if (texto.equals("activar")) {
			m.setColor_btn_activo(m.COLOR_ACTIVO);
			m.setColor_btn_desactivo(m.COLOR_TRANSPARENT);
			m.setEstado_activo(true);
			m.setEstado_desactivo(false);
			m.setEstado(true);
		} else {
			m.setColor_btn_activo(m.COLOR_TRANSPARENT);
			m.setColor_btn_desactivo(m.COLOR_DESACTIVO);
			m.setEstado_activo(false);
			m.setEstado_desactivo(true);
			m.setEstado(false);
		}
		BindUtils.postNotifyChange(null, null, m, "color_btn_activo");
		BindUtils.postNotifyChange(null, null, m, "color_btn_desactivo");
		BindUtils.postNotifyChange(null, null, m, "estado_desactivo");
		BindUtils.postNotifyChange(null, null, m, "estado_activo");
	}
	@Command
	public void Activar_Desactivar_submenu(@BindingParam("submenu") CSubMenu sm, @BindingParam("texto") String texto) {
		if (texto.equals("activar")) {
			sm.setColor_btn_activo(sm.COLOR_ACTIVO);
			sm.setColor_btn_desactivo(sm.COLOR_TRANSPARENT);
			sm.setEstado_activo(true);
			sm.setEstado_desactivo(false);
			sm.setEstado(true);
		} else {
			sm.setColor_btn_activo(sm.COLOR_TRANSPARENT);
			sm.setColor_btn_desactivo(sm.COLOR_DESACTIVO);
			sm.setEstado_activo(false);
			sm.setEstado_desactivo(true);
			sm.setEstado(false);
		}
		BindUtils.postNotifyChange(null, null, sm, "color_btn_activo");
		BindUtils.postNotifyChange(null, null, sm, "color_btn_desactivo");
		BindUtils.postNotifyChange(null, null, sm, "estado_desactivo");
		BindUtils.postNotifyChange(null, null, sm, "estado_activo");
	}
	@Command
	public void select_submenu_conElemento(@BindingParam("opcion") String opcion,@BindingParam("submenu")CSubMenu submenu) {
		if (opcion.equals("con_elemento")) {
			submenu.setConElemento(true);
			submenu.setSinElemento(false);
			submenu.setElemento(true);
		} else {
			submenu.setConElemento(false);
			submenu.setSinElemento(true);
			submenu.setElemento(false);
		}
		BindUtils.postNotifyChange(null, null, submenu,"conElemento");
		BindUtils.postNotifyChange(null, null, submenu,"sinElemento");
	}
	@Command
	@NotifyChange({"oMenu","visibleMenu","visibleSubMenu","visibleItem","visibleElemento","visibleDatoGeneral"})
	public void mostrarSubMenus(@BindingParam("menu")CMenu menu)
	{
		menu.setVisibleSubMenu(!menu.isVisibleSubMenu());
		setoMenu(menu);
		visibleMenu=true;
		visibleSubMenu=false;
		visibleItem=false;
		visibleElemento=false;
		visibleDatoGeneral=false;
		BindUtils.postNotifyChange(null, null, menu, "visibleSubMenu");
	}
	@Command
	@NotifyChange({"oSubMenu","visibleMenu","visibleSubMenu","visibleItem","visibleElemento","visibleDatoGeneral"})
	public void mostrarItems(@BindingParam("submenu")CSubMenu submenu)
	{
		submenu.setVisibleItem(!submenu.isVisibleItem());
		setoSubMenu(submenu);
		visibleMenu=false;
		visibleSubMenu=true;
		visibleItem=false;
		visibleElemento=false;
		visibleDatoGeneral=false;
		BindUtils.postNotifyChange(null, null, submenu, "visibleItem");
	}
	@Command
	@NotifyChange({"oItem","visibleMenu","visibleSubMenu","visibleItem","visibleElemento","visibleDatoGeneral",
		"listaDestinosMovil"})
	public void mostrarContentItems(@BindingParam("item")CItems item)
	{
		item.setVisibleContent(!item.isVisibleContent());
		setoItem(item);
		visibleMenu=false;
		visibleSubMenu=false;
		visibleItem=true;
		visibleElemento=false;
		visibleDatoGeneral=false;
		setListaDestinosMovil(item.getListaDestinosMovil());
		BindUtils.postNotifyChange(null, null, item, "visibleContent");
	}
	@Command
	@NotifyChange({"oElemento","visibleMenu","visibleSubMenu","visibleItem","visibleElemento","visibleDatoGeneral"})
	public void mostrarElemento(@BindingParam("elemento")CElementos elemento)
	{
		setoElemento(elemento);
		visibleMenu=false;
		visibleSubMenu=false;
		visibleItem=false;
		visibleElemento=true;
		visibleDatoGeneral=false;
	}
	@Command
	@NotifyChange({"oDatoGeneral","visibleMenu","visibleSubMenu","visibleItem","visibleElemento","visibleDatoGeneral"})
	public void mostrarDatoGeneral(@BindingParam("datoGeneral")CDatosGenerales datoGeneral)
	{
		setoDatoGeneral(datoGeneral);
		visibleMenu=false;
		visibleSubMenu=false;
		visibleItem=false;
		visibleElemento=false;
		visibleDatoGeneral=true;
	}
	public void refrescaFilaTemplate(CMenu m) {
		BindUtils.postNotifyChange(null, null, m, "editable");
	}
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view)
	{
		Selectors.wireComponents(view, this, false);
	}
}
