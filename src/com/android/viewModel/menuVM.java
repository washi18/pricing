package com.android.viewModel;

import java.io.File;
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
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Fileupload;
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
import com.android.model.CMenu;
import com.android.model.CSubMenu;
import com.pricing.extras.KMP;
import com.pricing.model.CGaleriaImageExist;
import com.pricing.model.CGaleriaImageExist4;
import com.pricing.model.Nro;
import com.pricing.util.ScannUtil;

public class menuVM {
	private CMenu oMenu;
	private CSubMenu oSubMenu;
	private CItems oItem;
	private CElementos oElemento;
	private CDatosGenerales oDatoGeneral;
	private CDestinoMovil oDestino;
	private ArrayList<CMenu> listaMenu;
	private boolean visibleMenu;
	private boolean visibleSubMenu;
	private boolean visibleItem;
	private boolean visibleElemento;
	private boolean visibleDatoGeneral;
	private boolean visibleDestino;
	private ArrayList<CGaleriaImageExist> listaImagenesExistentes;
	private ArrayList<CGaleriaImageExist4> lista4ImagenesExistentes;
	private CGaleriaImageExist4 galeria4Aux;
	private boolean mostrarImagenesExistentes;
	private boolean mostrarTextImgSeleccionado;
	private int opcionImgExist;
	private int nroImgElemento;
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
	public CDestinoMovil getoDestino() {
		return oDestino;
	}
	public void setoDestino(CDestinoMovil oDestino) {
		this.oDestino = oDestino;
	}
	public boolean isVisibleDestino() {
		return visibleDestino;
	}
	public void setVisibleDestino(boolean visibleDestino) {
		this.visibleDestino = visibleDestino;
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
	public boolean isMostrarTextImgSeleccionado() {
		return mostrarTextImgSeleccionado;
	}
	public void setMostrarTextImgSeleccionado(boolean mostrarTextImgSeleccionado) {
		this.mostrarTextImgSeleccionado = mostrarTextImgSeleccionado;
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
		visibleDestino=false;
		//===============
		mostrarImagenesExistentes=false;
		mostrarTextImgSeleccionado=false;
		galeria4Aux=new CGaleriaImageExist4();
		opcionImgExist=0;
		nroImgElemento=0;
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
			@BindingParam("galeria")CGaleriaImageExist galeria)
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
				if(opcionImgExist==1)
					asignarRutaImagenSubMenu(galeria4.getGaleria1().getRutaImagen(), oSubMenu,true);
				else if(opcionImgExist==2)
					asignarRutaImagenItem(galeria4.getGaleria1().getRutaImagen(), oItem,true);
				else if(opcionImgExist==3)
					asignarRutaImagenElemento(galeria4.getGaleria1().getRutaImagen(), oElemento, nroImgElemento,true);
				else if(opcionImgExist==4)
					asignarRutaImagenDatoGeneral(galeria4.getGaleria1().getRutaImagen(), oDatoGeneral,true);
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
				if(opcionImgExist==1)
					asignarRutaImagenSubMenu(galeria4.getGaleria2().getRutaImagen(), oSubMenu,true);
				else if(opcionImgExist==2)
					asignarRutaImagenItem(galeria4.getGaleria2().getRutaImagen(), oItem,true);
				else if(opcionImgExist==3)
					asignarRutaImagenElemento(galeria4.getGaleria2().getRutaImagen(), oElemento, nroImgElemento,true);
				else if(opcionImgExist==4)
					asignarRutaImagenDatoGeneral(galeria4.getGaleria2().getRutaImagen(), oDatoGeneral,true);
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
				if(opcionImgExist==1)
					asignarRutaImagenSubMenu(galeria4.getGaleria3().getRutaImagen(), oSubMenu,true);
				else if(opcionImgExist==2)
					asignarRutaImagenItem(galeria4.getGaleria3().getRutaImagen(), oItem,true);
				else if(opcionImgExist==3)
					asignarRutaImagenElemento(galeria4.getGaleria3().getRutaImagen(), oElemento, nroImgElemento,true);
				else if(opcionImgExist==4)
					asignarRutaImagenDatoGeneral(galeria4.getGaleria3().getRutaImagen(), oDatoGeneral,true);
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
				if(opcionImgExist==1)
					asignarRutaImagenSubMenu(galeria4.getGaleria4().getRutaImagen(), oSubMenu,true);
				else if(opcionImgExist==2)
					asignarRutaImagenItem(galeria4.getGaleria4().getRutaImagen(), oItem,true);
				else if(opcionImgExist==3)
					asignarRutaImagenElemento(galeria4.getGaleria4().getRutaImagen(), oElemento, nroImgElemento,true);
				else if(opcionImgExist==4)
					asignarRutaImagenDatoGeneral(galeria4.getGaleria4().getRutaImagen(), oDatoGeneral,true);
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
	@NotifyChange({"mostrarImagenesExistentes"})
	public void cerrarImagenesExistentes()
	{
		mostrarImagenesExistentes=false;
	}
	@Command
	@NotifyChange({"mostrarImagenesExistentes","mostrarTextImgSeleccionado"})
	public void invocaImagenesExistentes(@BindingParam("opcionImgExist")int opcionImgExist,
			@BindingParam("nroImgElemento")int nroImgElemento)
	{
		System.out.println("Entre a invocar las imagenes existentes");
		Nro.inicializarNroImagenes();
		this.opcionImgExist=opcionImgExist;
		this.nroImgElemento=nroImgElemento;
		mostrarImagenesExistentes=true;
		mostrarTextImgSeleccionado=false;
		recuperarTodasImagenesExistentes();
	}
	@Command
	public void selectDestinoMovil(@BindingParam("destino") CDestinoMovil destino) {
		if (destino.isSeleccionado())
			destino.setSeleccionado(false);
		else
			destino.setSeleccionado(true);
	}
	@Command
	@NotifyChange({"oMenu","visibleMenu","visibleSubMenu","visibleItem","visibleElemento","visibleDatoGeneral","visibleDestino"})
	public void mostrarNuevoMenu()
	{
		oMenu=new CMenu();
		visibleMenu=true;
		visibleSubMenu=false;
		visibleItem=false;
		visibleElemento=false;
		visibleDatoGeneral=false;
		visibleDestino=false;
	}
	@Command
	@NotifyChange({"oSubMenu","visibleMenu","visibleSubMenu","visibleItem","visibleElemento","visibleDatoGeneral","visibleDestino"})
	public void mostrarNuevoSubMenu(@BindingParam("menu")CMenu menu)
	{
		oSubMenu=new CSubMenu();
		oSubMenu.setcMenuCod(menu.getcMenuCod());
		visibleMenu=false;
		visibleSubMenu=true;
		visibleItem=false;
		visibleElemento=false;
		visibleDatoGeneral=false;
		visibleDestino=false;
	}
	@Command
	@NotifyChange({"oItem","visibleMenu","visibleSubMenu","visibleItem","visibleElemento","visibleDatoGeneral","visibleDestino"})
	public void mostrarNuevoItem(@BindingParam("submenu")CSubMenu submenu)
	{
		oItem=new CItems();
		oItem.setcSubMenuCod(submenu.getcSubMenuCod());
		visibleMenu=false;
		visibleSubMenu=false;
		visibleItem=true;
		visibleElemento=false;
		visibleDatoGeneral=false;
		visibleDestino=false;
	}
	@Command
	@NotifyChange({"oDatoGeneral","visibleMenu","visibleSubMenu","visibleItem","visibleElemento","visibleDatoGeneral","visibleDestino"})
	public void mostrarNuevoDatoGeneral(@BindingParam("elemento")CElementos elemento)
	{
		oDatoGeneral=new CDatosGenerales();
		oDatoGeneral.setcElementosCod(elemento.getcElementosCod());
		visibleMenu=false;
		visibleSubMenu=false;
		visibleItem=false;
		visibleElemento=false;
		visibleDatoGeneral=true;
		visibleDestino=false;
	}
	@Command
	@NotifyChange({"oElemento","visibleMenu","visibleSubMenu","visibleItem","visibleElemento","visibleDatoGeneral","visibleDestino"})
	public void mostrarNuevoElementoItem(@BindingParam("item")CItems item)
	{
		oElemento=new CElementos();
		oElemento.setcItemsCod(item.getcItemsCod());
		oElemento.setModoSubMenu(false);
		oElemento.setModoItem(true);
		visibleMenu=false;
		visibleSubMenu=false;
		visibleItem=false;
		visibleElemento=true;
		visibleDatoGeneral=false;
		visibleDestino=false;
	}
	@Command
	@NotifyChange({"oElemento","visibleMenu","visibleSubMenu","visibleItem","visibleElemento","visibleDatoGeneral","visibleDestino"})
	public void mostrarNuevoElementoSubMenu(@BindingParam("submenu")CSubMenu submenu)
	{
		oElemento=new CElementos();
		oElemento.setcSubMenuCod(submenu.getcSubMenuCod());
		oElemento.setModoSubMenu(true);
		oElemento.setModoItem(false);
		visibleMenu=false;
		visibleSubMenu=false;
		visibleItem=false;
		visibleElemento=true;
		visibleDatoGeneral=false;
		visibleDestino=false;
	}
	@Command
	@NotifyChange({"oElemento","visibleMenu","visibleSubMenu","visibleItem","visibleElemento","visibleDatoGeneral","visibleDestino"})
	public void mostrarNuevoDestino(@BindingParam("elemento")CElementos elemento)
	{
		oDestino=new CDestinoMovil();
		oDestino.setcElementosCod(elemento.getcElementosCod());
		visibleMenu=false;
		visibleSubMenu=false;
		visibleItem=false;
		visibleElemento=false;
		visibleDatoGeneral=false;
		visibleDestino=true;
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
		boolean correcto=itemsDao.isOperationCorrect(itemsDao.registrarItem(oItem));
		if(correcto)
		{
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
	public void registrarElementoSubmenu(@BindingParam("componente")Component comp)
	{
		if(!validoParaInsertar_elemento_Submenu(comp))
			return;
		CElementosDAO elementosDao=new CElementosDAO();
		CMenuDAO menuDao=new CMenuDAO();
		boolean correcto=elementosDao.isOperationCorrect(elementosDao.registrarElementoSubmenu(oElemento));
		if(correcto)
		{
			oElemento=new CElementos();
			menuDao.asignarListaMenu(menuDao.recuperarListaMenuBD());
			setListaMenu(menuDao.getListaMenu());
			Clients.showNotification("El registro del elemento fue correcto",Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",3000);
		}
	}
	@Command
	@NotifyChange({"oElemento","listaMenu"})
	public void registrarElementoItem(@BindingParam("componente")Component comp)
	{
		if(!validoParaInsertar_elemento_Item(comp))
			return;
		CElementosDAO elementosDao=new CElementosDAO();
		CMenuDAO menuDao=new CMenuDAO();
		boolean correcto=elementosDao.isOperationCorrect(elementosDao.registrarElementoItem(oElemento));
		if(correcto)
		{
			oElemento=new CElementos();
			menuDao.asignarListaMenu(menuDao.recuperarListaMenuBD());
			setListaMenu(menuDao.getListaMenu());
			Clients.showNotification("El registro del elemento fue correcto",Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",3000);
		}
	}
	@Command
	@NotifyChange({"oDestino","listaMenu"})
	public void registrarDestinoMovil(@BindingParam("componente")Component comp)
	{
		if(!validoParaInsertar_destino(comp))
			return;
		CDestinosMovilDAO destinoMovilDao=new CDestinosMovilDAO();
		CMenuDAO menuDao=new CMenuDAO();
		boolean correcto=destinoMovilDao.isOperationCorrect(destinoMovilDao.insertarDestinoMovil(oDestino));
		if(correcto)
		{
			oDestino=new CDestinoMovil();
			menuDao.asignarListaMenu(menuDao.recuperarListaMenuBD());
			setListaMenu(menuDao.getListaMenu());
			Clients.showNotification("El Destino se inserto correctamente", Clients.NOTIFICATION_TYPE_INFO, comp,"before_start", 2700);
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
		if(oDatoGeneral.getcElementosCod()==0)
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
	public boolean validoParaInsertar_elemento_Item(Component comp)
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
	public boolean validoParaInsertar_elemento_Submenu(Component comp)
	{
		boolean valido=true;
		if(oElemento.getcSubMenuCod()==0)
		{
			valido=false;
			Clients.showNotification("Es necesario que seleccione a que submenu correspondera el elemento",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
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
	public boolean validoParaInsertar_destino(Component comp)
	{
		boolean valido=true;
		if(oDestino.getcDestino().equals(""))
		{
			valido=false;
			Clients.showNotification("El Destino siempre debe de tener un nombre", Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start", 2700);
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
			Clients.showNotification("La modificacion del dato general fue correcto",Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",3000);
		}
	}
	@Command
	public void actualizarElemento_Item(@BindingParam("elemento")CElementos elemento,@BindingParam("componente")Component comp)
	{
		if(!validoParaActualizar_elemento(comp,elemento))
			return;
		CElementosDAO elementoDao=new CElementosDAO();
		boolean correcto=elementoDao.isOperationCorrect(elementoDao.modificarElemento_Item(elemento));
		if(correcto)
		{
			Clients.showNotification("La modificacion del elemento fue correcto",Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",3000);
		}
	}
	@Command
	public void actualizarElemento_Submenu(@BindingParam("elemento")CElementos elemento,@BindingParam("componente")Component comp)
	{
		if(!validoParaActualizar_elemento(comp,elemento))
			return;
		CElementosDAO elementoDao=new CElementosDAO();
		boolean correcto=elementoDao.isOperationCorrect(elementoDao.modificarElemento_Submenu(elemento));
		if(correcto)
		{
			Clients.showNotification("La modificacion del elemento fue correcto",Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",3000);
		}
	}
	@Command
	public void actualizarDestinoMovil(@BindingParam("destino")CDestinoMovil destino,@BindingParam("componente")Component comp)
	{
		if(!validoParaActualizar_destino(destino,comp))
			return;
		CDestinosMovilDAO destinoMovilDao=new CDestinosMovilDAO();
		boolean correcto=destinoMovilDao.isOperationCorrect(destinoMovilDao.modificarDestinoMovil(destino));
		if(correcto)
		{
			Clients.showNotification("El Destino se actualizo correctamente", Clients.NOTIFICATION_TYPE_INFO, comp,"before_start", 2700);
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
	public boolean validoParaActualizar_destino(CDestinoMovil destino,Component comp)
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
			menu.setcImagenIcono("img/android/"+url);
			BindUtils.postNotifyChange(null, null, menu,"cImagenIcono");
		}
		else
		{
			menu.setcImagenFondo("img/android/"+url);
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
						asignarRutaImagenSubMenu(img.getName(), submenu,false);
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
	public void asignarRutaImagenSubMenu(String url,CSubMenu submenu,boolean imgExist)
	{
			if(imgExist)
				submenu.setcImagen(url);
			else
				submenu.setcImagen("img/android/"+url);
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
						asignarRutaImagenItem(img.getName(), item,false);
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
	public void asignarRutaImagenItem(String url,CItems item,boolean imgExist)
	{
			if(imgExist)
				item.setcImagen(url);
			else
				item.setcImagen("img/android/"+url);
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
						asignarRutaImagenDatoGeneral(img.getName(), datoGeneral,false);
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
	public void asignarRutaImagenDatoGeneral(String url,CDatosGenerales datoGeneral,boolean imgExist)
	{
			if(imgExist)
				datoGeneral.setcImagen(url);
			else
				datoGeneral.setcImagen("img/android/"+url);
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
						asignarRutaImagenElemento(img.getName(),elemento,opcion,false);
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
	public void asignarRutaImagenElemento(String url,CElementos elemento,int opcion,boolean imgExist)
	{
		if(opcion==1)
		{
			if(imgExist)
				elemento.setcImagen1(url);
			else
				elemento.setcImagen1("img/android/"+url);
			BindUtils.postNotifyChange(null, null, elemento,"cImagen1");
		}else if(opcion==2)
		{
			if(imgExist)
				elemento.setcImagen2(url);
			else
				elemento.setcImagen2("img/android/"+url);
			BindUtils.postNotifyChange(null, null, elemento,"cImagen2");
		}else
		{
			if(imgExist)
				elemento.setcImagen3(url);
			else
				elemento.setcImagen3("img/android/"+url);
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
	@Command
	@NotifyChange({"oElemento"})
	public void select_submenu_conElemento(@BindingParam("opcion") String opcion,@BindingParam("submenu")CSubMenu submenu) {
		CSubMenuDAO submenuDao=new CSubMenuDAO();
		if (opcion.equals("con_elemento")) {
			submenu.setConElemento(true);
			submenu.setSinElemento(false);
			submenu.setElemento(true);
			submenu.setListaItems(new ArrayList<CItems>());
			submenu.recuperarListaElementos(submenu.getcSubMenuCod());
			submenuDao.isOperationCorrect(submenuDao.modificarElementoSubmenu(submenu.getcSubMenuCod(),true));
		} else {
			submenu.setConElemento(false);
			submenu.setSinElemento(true);
			submenu.setElemento(false);
			submenu.setListaElementos(new ArrayList<CElementos>());
			submenu.recuperarListaItems(submenu.getcSubMenuCod());
			submenuDao.isOperationCorrect(submenuDao.modificarElementoSubmenu(submenu.getcSubMenuCod(),false));
		}
		BindUtils.postNotifyChange(null, null, submenu,"conElemento");
		BindUtils.postNotifyChange(null, null, submenu,"sinElemento");
		BindUtils.postNotifyChange(null, null, submenu,"listaItems");
		BindUtils.postNotifyChange(null, null, submenu,"listaElementos");
	}
	@Command
	@NotifyChange({"oMenu","visibleMenu","visibleSubMenu","visibleItem","visibleElemento","visibleDatoGeneral","visibleDestino"})
	public void mostrarSubMenus(@BindingParam("menu")CMenu menu)
	{
		menu.setVisibleSubMenu(!menu.isVisibleSubMenu());
		setoMenu(menu);
		visibleMenu=true;
		visibleSubMenu=false;
		visibleItem=false;
		visibleElemento=false;
		visibleDatoGeneral=false;
		visibleDestino=false;
		BindUtils.postNotifyChange(null, null, menu, "visibleSubMenu");
	}
	@Command
	@NotifyChange({"oSubMenu","visibleMenu","visibleSubMenu","visibleItem","visibleElemento","visibleDatoGeneral","visibleDestino"})
	public void mostrarItems(@BindingParam("submenu")CSubMenu submenu)
	{
		submenu.setVisibleItem(!submenu.isVisibleItem());
		setoSubMenu(submenu);
		visibleMenu=false;
		visibleSubMenu=true;
		visibleItem=false;
		visibleElemento=false;
		visibleDatoGeneral=false;
		visibleDestino=false;
		BindUtils.postNotifyChange(null, null, submenu, "visibleItem");
	}
	@Command
	@NotifyChange({"oItem","visibleMenu","visibleSubMenu","visibleItem","visibleElemento","visibleDatoGeneral","visibleDestino"})
	public void mostrarContentItems(@BindingParam("item")CItems item)
	{
		item.setVisibleContent(!item.isVisibleContent());
		setoItem(item);
		visibleMenu=false;
		visibleSubMenu=false;
		visibleItem=true;
		visibleElemento=false;
		visibleDatoGeneral=false;
		visibleDestino=false;
		BindUtils.postNotifyChange(null, null, item, "visibleContent");
	}
	@Command
	@NotifyChange({"oElemento","visibleMenu","visibleSubMenu","visibleItem","visibleElemento","visibleDatoGeneral","visibleDestino"})
	public void mostrarElemento_submenu(@BindingParam("elemento")CElementos elemento)
	{
		setoElemento(elemento);
		visibleMenu=false;
		visibleSubMenu=false;
		visibleItem=false;
		visibleElemento=true;
		visibleDatoGeneral=false;
		visibleDestino=false;
	}
	@Command
	@NotifyChange({"oElemento","visibleMenu","visibleSubMenu","visibleItem","visibleElemento","visibleDatoGeneral","visibleDestino"})
	public void mostrarElemento_item(@BindingParam("elemento")CElementos elemento)
	{
		setoElemento(elemento);
		visibleMenu=false;
		visibleSubMenu=false;
		visibleItem=false;
		visibleElemento=true;
		visibleDatoGeneral=false;
		visibleDestino=false;
	}
	@Command
	@NotifyChange({"oDatoGeneral","visibleMenu","visibleSubMenu","visibleItem","visibleElemento","visibleDatoGeneral","visibleDestino"})
	public void mostrarDatoGeneral(@BindingParam("datoGeneral")CDatosGenerales datoGeneral)
	{
		setoDatoGeneral(datoGeneral);
		visibleMenu=false;
		visibleSubMenu=false;
		visibleItem=false;
		visibleElemento=false;
		visibleDatoGeneral=true;
		visibleDestino=false;
	}
	@Command
	@NotifyChange({"oDestino","visibleMenu","visibleSubMenu","visibleItem","visibleElemento","visibleDatoGeneral","visibleDestino"})
	public void mostrarDestino(@BindingParam("destino")CDestinoMovil destino)
	{
		setoDestino(destino);
		visibleMenu=false;
		visibleSubMenu=false;
		visibleItem=false;
		visibleElemento=false;
		visibleDatoGeneral=false;
		visibleDestino=true;
	}
	@Command
	public void clickMapaInsertar(@BindingParam("latitud")double latitud,@BindingParam("longitud")double longitud){
			oDestino.setcLatitud(String.valueOf(latitud));
			oDestino.setcLongitud(String.valueOf(longitud));
		BindUtils.postNotifyChange(null, null, this, "oDestino");
	}
	@Command
	public void vistaMobile_menu(@BindingParam("menu")CMenu menu)
	{
		menu.setVistaMobil(true);
		BindUtils.postNotifyChange(null, null, menu, "vistaMobil");
	}
	@Command
	public void vistaMobile_submenu(@BindingParam("submenu")CSubMenu submenu)
	{
		submenu.setVistaMobil(true);
		BindUtils.postNotifyChange(null, null, submenu, "vistaMobil");
	}
	@Command
	public void vistaMobile_item(@BindingParam("item")CItems item)
	{
		item.setVistaMobil(true);
		BindUtils.postNotifyChange(null, null, item, "vistaMobil");
	}
	@Command
	public void vistaMobile_elemento(@BindingParam("elemento")CElementos elemento)
	{
		elemento.setVistaMobil(true);
		BindUtils.postNotifyChange(null, null, elemento, "vistaMobil");
	}
	@Command
	@NotifyChange({"oMenu","oSubMenu","oItem","oElemento","oDatoGeneral"})
	public void cerrarVistaMobile()
	{
		oMenu.setVistaMobil(false);
		oSubMenu.setVistaMobil(false);
		oItem.setVistaMobil(false);
		oElemento.setVistaMobil(false);
		oDatoGeneral.setVistaMobil(false);
	}
	@Command
	public void abrirEditorDescripcionItem(@BindingParam("item")CItems item)
	{
		item.setAbrirEditorDescripcion(true);
		BindUtils.postNotifyChange(null, null, item, "abrirEditorDescripcion");
	}
	@Command
	public void abrirEditorDescripcionElemento(@BindingParam("elemento")CElementos elemento)
	{
		elemento.setAbrirEditorDescripcion(true);
		BindUtils.postNotifyChange(null, null, elemento, "abrirEditorDescripcion");
	}
	@Command
	public void abrirEditorDirigidoElemento(@BindingParam("elemento")CElementos elemento)
	{
		elemento.setAbrirEditorDirigido(true);
		BindUtils.postNotifyChange(null, null, elemento, "abrirEditorDirigido");
	}
	@Command
	public void abrirEditorDescripcionDatoGeneral(@BindingParam("datoGeneral")CDatosGenerales dg)
	{
		dg.setAbrirEditorDescripcion(true);
		BindUtils.postNotifyChange(null, null, dg, "abrirEditorDescripcion");
	}
	@Command
	public void cerrarEditorDescripcionItem(@BindingParam("item")CItems item)
	{
		item.setAbrirEditorDescripcion(false);
		BindUtils.postNotifyChange(null, null, item, "abrirEditorDescripcion");
	}
	@Command
	public void cerrarEditorDescripcionElemento(@BindingParam("elemento")CElementos elemento)
	{
		elemento.setAbrirEditorDescripcion(false);
		BindUtils.postNotifyChange(null, null, elemento, "abrirEditorDescripcion");
	}
	@Command
	public void cerrarEditorDirigidoElemento(@BindingParam("elemento")CElementos elemento)
	{
		elemento.setAbrirEditorDirigido(false);
		BindUtils.postNotifyChange(null, null, elemento, "abrirEditorDirigido");
	}
	@Command
	public void cerrarEditorDescripcionDatoGeneral(@BindingParam("datoGeneral")CDatosGenerales dg)
	{
		dg.setAbrirEditorDescripcion(false);
		BindUtils.postNotifyChange(null, null, dg, "abrirEditorDescripcion");
	}
	@Command
	public void vistaMobile_datogeneral(@BindingParam("datogeneral")CDatosGenerales datogeneral)
	{
		datogeneral.setVistaMobil(true);
		BindUtils.postNotifyChange(null, null, datogeneral, "vistaMobil");
	}
	public void refrescaFilaTemplate(CMenu m) {
		BindUtils.postNotifyChange(null, null, m, "editable");
	}
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view)
	{
		Selectors.wireComponents(view, this, false);
	}
	public void refrescarSelect(CGaleriaImageExist4 galeria4)
	{
		BindUtils.postNotifyChange(null, null, galeria4, "galeria1");
		BindUtils.postNotifyChange(null, null, galeria4, "galeria2");
		BindUtils.postNotifyChange(null, null, galeria4, "galeria3");
		BindUtils.postNotifyChange(null, null, galeria4, "galeria4");
	}
}
