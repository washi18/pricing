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

import com.android.dao.CMenuDAO;
import com.android.dao.CSubMenuDAO;
import com.android.model.CMenu;
import com.android.model.CSubMenu;
import com.pricing.util.ScannUtil;

public class submenuVM {
	private CSubMenu oSubMenu;
	private CSubMenu oSubMenuUpdate;
	private ArrayList<CSubMenu> listaSubMenu;
	private ArrayList<CMenu> listaMenu;
	//=======================================
	public CSubMenu getoSubMenu() {
		return oSubMenu;
	}
	public void setoSubMenu(CSubMenu oSubMenu){
		this.oSubMenu = oSubMenu;
	}
	public ArrayList<CSubMenu> getListaSubMenu(){
		return listaSubMenu;
	}
	public void setListaSubMenu(ArrayList<CSubMenu> listaSubMenu){
		this.listaSubMenu = listaSubMenu;
	}
	public ArrayList<CMenu> getListaMenu() {
		return listaMenu;
	}
	public void setListaMenu(ArrayList<CMenu> listaMenu) {
		this.listaMenu = listaMenu;
	}
	//===========================
	@Init
	public void initVM()
	{
		oSubMenu=new CSubMenu();
		oSubMenuUpdate=new CSubMenu();
		listaSubMenu=new ArrayList<CSubMenu>();
		listaMenu=new ArrayList<CMenu>();
	}
	@GlobalCommand
	@NotifyChange({"listaSubMenu","listaMenu"})
	public void cargarDatosSubMenu()
	{
		CSubMenuDAO subMenuDao=new CSubMenuDAO();
		subMenuDao.asignarListaSubMenu(subMenuDao.recuperarListaSubMenusBD());
		setListaSubMenu(subMenuDao.getListaSubMenu());
		//============================================
		CMenuDAO menuDao=new CMenuDAO();
		menuDao.asignarListaMenu(menuDao.recuperarListaMenuBD());
		setListaMenu(menuDao.getListaMenu());
	}
	@Command
	public void selectMenu(@BindingParam("codMenu")String codMenu,@BindingParam("submenu")CSubMenu submenu)
	{
		submenu.setcMenuCod(Integer.parseInt(codMenu));
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
	@NotifyChange({"oSubMenu","listaSubMenu"})
	public void registrarSubMenu(@BindingParam("componente")Component comp)
	{
		if(!validoParaInsertar(comp))
			return;
		CSubMenuDAO subMenuDao=new CSubMenuDAO();
		boolean correcto=subMenuDao.isOperationCorrect(subMenuDao.registrarSubMenu(oSubMenu));
		if(correcto)
		{
			oSubMenu=new CSubMenu();
			subMenuDao.asignarListaSubMenu(subMenuDao.recuperarListaSubMenusBD());
			setListaSubMenu(subMenuDao.getListaSubMenu());
			Clients.showNotification("El registro del submenu fue correcto",Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",3000);
		}
	}
	public boolean validoParaInsertar(Component comp)
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
	@Command
	public void actualizarSubMenu(@BindingParam("submenu")CSubMenu submenu,@BindingParam("componente")Component comp)
	{
		if(!validoParaActualizar(comp,submenu))
			return;
		CSubMenuDAO subMenuDao=new CSubMenuDAO();
		boolean correcto=subMenuDao.isOperationCorrect(subMenuDao.modificarSubMenu(submenu));
		if(correcto)
		{
			submenu.setEditable(false);
			refrescaFilaTemplate(submenu);
			Clients.showNotification("La modificacion del submenu fue correcto",Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",3000);
		}
	}
	public boolean validoParaActualizar(Component comp,CSubMenu submenu)
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
	@Command
	public void uploadImagenes(@BindingParam("submenu")final CSubMenu submenu,@BindingParam("componente")final Component comp)
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
						asignarRutaImagenMenu(img.getName(), submenu);
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
	public void asignarRutaImagenMenu(String url,CSubMenu submenu)
	{
			submenu.setcImagen("/img/android/"+url);
			BindUtils.postNotifyChange(null, null, submenu,"cImagen");
	}
	@Command
	public void cambioIdiomas(@BindingParam("idioma")String idioma,@BindingParam("submenu")CSubMenu submenu)
	{
		if (idioma.equals("Espanol")) {
			submenu.setVisibleEspanol(true);
			submenu.setVisibleIngles(false);
			submenu.setVisiblePortugues(false);
		} else if (idioma.equals("Ingles")) {
			submenu.setVisibleEspanol(false);
			submenu.setVisibleIngles(true);
			submenu.setVisiblePortugues(false);
		} else if (idioma.equals("Portugues")) {
			submenu.setVisibleEspanol(false);
			submenu.setVisibleIngles(false);
			submenu.setVisiblePortugues(true);
		}
		BindUtils.postNotifyChange(null, null, submenu, "visibleEspanol");
		BindUtils.postNotifyChange(null, null, submenu, "visibleIngles");
		BindUtils.postNotifyChange(null, null, submenu, "visiblePortugues");
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
	public void Editar(@BindingParam("submenu") CSubMenu sm) {
		oSubMenuUpdate.setEditable(false);
		refrescaFilaTemplate(oSubMenuUpdate);
		oSubMenuUpdate = sm;
		// le damos estado editable
		sm.setEditable(!sm.isEditable());
		// lcs.setEditingStatus(!lcs.getEditingStatus());
		refrescaFilaTemplate(sm);
	}
	public void refrescaFilaTemplate(CSubMenu sm) {
		BindUtils.postNotifyChange(null, null, sm, "editable");
	}
}
