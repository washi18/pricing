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
import com.android.model.CMenu;
import com.pricing.model.CPaquete;
import com.pricing.util.ScannUtil;

public class menuVM {
	private CMenu oMenu;
	private CMenu oMenuUpdate;
	private ArrayList<CMenu> listaMenu;
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
	//====================
	@Init
	public void initVM()
	{
		oMenu=new CMenu();
		oMenuUpdate=new CMenu();
		listaMenu=new ArrayList<CMenu>();
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
	@NotifyChange({"oMenu","listaMenu"})
	public void registrarMenu(@BindingParam("componente")Component comp)
	{
		if(!validoParaInsertar(comp))
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
	public boolean validoParaInsertar(Component comp)
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
	@Command
	public void actualizarMenu(@BindingParam("menu")CMenu menu,@BindingParam("componente")Component comp)
	{
		if(!validoParaActualizar(comp,menu))
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
	public boolean validoParaActualizar(Component comp,CMenu menu)
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
	@Command
	public void uploadImagenes(@BindingParam("menu")final CMenu menu,@BindingParam("componente")final Component comp,
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
						boolean b = ScannUtil.uploadFilePaquetes(img);
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
	public void Editar(@BindingParam("menu") CMenu m) {
		oMenuUpdate.setEditable(false);
		refrescaFilaTemplate(oMenuUpdate);
		oMenuUpdate = m;
		// le damos estado editable
		m.setEditable(!m.isEditable());
		// lcs.setEditingStatus(!lcs.getEditingStatus());
		refrescaFilaTemplate(m);
	}
	public void refrescaFilaTemplate(CMenu m) {
		BindUtils.postNotifyChange(null, null, m, "editable");
	}
}
