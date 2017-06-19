package com.pricing.viewModel;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpSession;

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
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Div;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import pe.com.erp.crypto.Encryptar;

import com.pricing.dao.CActividadDAO;
import com.pricing.dao.CCalendarioPropioDAO;
import com.pricing.dao.CDestinoDAO;
import com.pricing.dao.CGaleriaHotelDAO;
import com.pricing.dao.CGaleriaPaqueteDAO;
import com.pricing.dao.CPaqueteDAO;
import com.pricing.dao.CServicioDAO;
import com.pricing.dao.ConfAltoNivelDAO;
import com.pricing.extras.KMP;
import com.pricing.model.CActividad;
import com.pricing.model.CDestino;
import com.pricing.model.CGaleriaHotel;
import com.pricing.model.CGaleriaImageExist;
import com.pricing.model.CGaleriaImageExist4;
import com.pricing.model.CGaleriaPaquete;
import com.pricing.model.CHotel;
import com.pricing.model.CPaquete;
import com.pricing.model.CPaqueteActividad;
import com.pricing.model.CPaqueteDestino;
import com.pricing.model.CPaqueteServicio;
import com.pricing.model.CServicio;
import com.pricing.model.ConfAltoNivel;
import com.pricing.model.Nro;
import com.pricing.util.ScannUtil;

public class paquetesVM {
	// ====================
	private DecimalFormat df;
	private DecimalFormatSymbols simbolos;
	// ====================
	@Wire
	Div div_select_destinos;
	/**
	 * ATRIBUTOS
	 */
	private CPaquete oPaquete;
	private CPaquete oPaqueteUpdate;
	private ArrayList<CPaquete> listaPaquetes;
	private boolean select_manejo;
	private ArrayList<CDestino> listaDestinos;
	private ArrayList<CServicio> listaServicios;
	private ArrayList<CActividad> listaActividades;
	private ArrayList<CGaleriaImageExist> listaImagenesExistentes;
	private ArrayList<CGaleriaImageExist4> lista4ImagenesExistentes;
	private boolean visible_caminoinka_manejopropio;
	private boolean visibilidadYourself;
	private ConfAltoNivel confAltoNivel;
	private ConfAltoNivelDAO confAltoNivelDAO;
	private boolean mostrarImagenesExistentes;
	private boolean mostrarImagenesExistentesUpdate;
	private boolean mostrarTextImgSeleccionado;

	/**
	 * GETTER AND SETTER
	 */
	
	public CPaquete getoPaquete() {
		return oPaquete;
	}

	public boolean isVisibilidadYourself() {
		return visibilidadYourself;
	}

	public void setVisibilidadYourself(boolean visibilidadYourself) {
		this.visibilidadYourself = visibilidadYourself;
	}

	public boolean isVisible_caminoinka_manejopropio() {
		return visible_caminoinka_manejopropio;
	}

	public void setVisible_caminoinka_manejopropio(boolean visible_caminoinka_manejopropio) {
		this.visible_caminoinka_manejopropio = visible_caminoinka_manejopropio;
	}

	public void setoPaquete(CPaquete oPaquete) {
		this.oPaquete = oPaquete;
	}

	public ArrayList<CPaquete> getListaPaquetes() {
		return listaPaquetes;
	}

	public void setListaPaquetes(ArrayList<CPaquete> listaPaquetes) {
		this.listaPaquetes = listaPaquetes;
	}

	public ArrayList<CDestino> getListaDestinos() {
		return listaDestinos;
	}

	public void setListaDestinos(ArrayList<CDestino> listaDestinos) {
		this.listaDestinos = listaDestinos;
	}

	public ArrayList<CServicio> getListaServicios() {
		return listaServicios;
	}

	public void setListaServicios(ArrayList<CServicio> listaServicios) {
		this.listaServicios = listaServicios;
	}

	public boolean isSelect_manejo() {
		return select_manejo;
	}

	public void setSelect_manejo(boolean select_manejo) {
		this.select_manejo = select_manejo;
	}

	public ArrayList<CActividad> getListaActividades() {
		return listaActividades;
	}

	public void setListaActividades(ArrayList<CActividad> listaActividades) {
		this.listaActividades = listaActividades;
	}
	

	public ConfAltoNivel getConfAltoNivel() {
		return confAltoNivel;
	}

	public void setConfAltoNivel(ConfAltoNivel confAltoNivel) {
		this.confAltoNivel = confAltoNivel;
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

	/**
	 * METODOS Y FUNCIONES DE LA CLASE
	 */
	@Init
	public void initVM() {
		/*******************************/
		simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		df = new DecimalFormat("########0.00", simbolos);
		/*******************************/
		select_manejo = false;
		visible_caminoinka_manejopropio = false;
		/** Inicializando los objetos **/
		oPaquete = new CPaquete();
		oPaqueteUpdate = new CPaquete();
		listaPaquetes = new ArrayList<CPaquete>();
		listaDestinos = new ArrayList<CDestino>();
		listaServicios = new ArrayList<CServicio>();
		listaActividades = new ArrayList<CActividad>();
		confAltoNivel=new ConfAltoNivel();
		confAltoNivelDAO=new ConfAltoNivelDAO();
		mostrarImagenesExistentes=false;
		mostrarImagenesExistentesUpdate=false;
		mostrarTextImgSeleccionado=false;
		/*****************************/
		Encryptar encrip = new Encryptar();
		// System.out.println("Aqui esta la contraseña
		// desencriptada-->"+encrip.decrypt("cyS249O3OHZTsG0ww1rYrw=="));
		Execution exec = Executions.getCurrent();
	}
	@GlobalCommand
	public void cargarDatosPaquetes() throws UnsupportedEncodingException
	{
		HttpSession ses = (HttpSession) Sessions.getCurrent().getNativeSession();
		String user = (String) ses.getAttribute("usuario");
		String pas = (String) ses.getAttribute("clave");
		if (user != null && pas != null){
			recuperarPaquetes();
			recuperarEstadoYourself();
		}
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
			@BindingParam("galeria")CGaleriaImageExist galeria,@BindingParam("paquete")CPaquete paquete)
	{
		if(galeria4.getGaleria1().equals(galeria))
		{
			if(galeria4.getGaleria1().isSeleccionado())
			{
				if(Nro.nroImagenes>0)Nro.decrementarNroImagenes();
				galeria4.getGaleria1().setSeleccionado(false);
				galeria4.getGaleria1().setStyle_Select("div_content_imageHotel");
				quitarImagen(galeria4.getGaleria1().getRutaImagen(),paquete);
			}else{
				Nro.incrementarNroImagenes();
				galeria4.getGaleria1().setSeleccionado(true);
				galeria4.getGaleria1().setStyle_Select("div_content_imageHotel_selected");
				asignarRutaImagenPaquete(galeria4.getGaleria1().getRutaImagen(), paquete,true);
			}
		}else if(galeria4.getGaleria2().equals(galeria))
		{
			if(galeria4.getGaleria2().isSeleccionado())
			{
				if(Nro.nroImagenes>0)Nro.decrementarNroImagenes();
				galeria4.getGaleria2().setSeleccionado(false);
				galeria4.getGaleria2().setStyle_Select("div_content_imageHotel");
				quitarImagen(galeria4.getGaleria2().getRutaImagen(),paquete);
			}else{
				Nro.incrementarNroImagenes();
				galeria4.getGaleria2().setSeleccionado(true);
				galeria4.getGaleria2().setStyle_Select("div_content_imageHotel_selected");
				asignarRutaImagenPaquete(galeria4.getGaleria2().getRutaImagen(), paquete,true);
			}
		}else if(galeria4.getGaleria3().equals(galeria))
		{
			if(galeria4.getGaleria3().isSeleccionado())
			{
				if(Nro.nroImagenes>0)Nro.decrementarNroImagenes();
				galeria4.getGaleria3().setSeleccionado(false);
				galeria4.getGaleria3().setStyle_Select("div_content_imageHotel");
				quitarImagen(galeria4.getGaleria3().getRutaImagen(),paquete);
			}else{
				Nro.incrementarNroImagenes();
				galeria4.getGaleria3().setSeleccionado(true);
				galeria4.getGaleria3().setStyle_Select("div_content_imageHotel_selected");
				asignarRutaImagenPaquete(galeria4.getGaleria3().getRutaImagen(), paquete,true);
			}
		}else if(galeria4.getGaleria4().equals(galeria))
		{
			if(galeria4.getGaleria4().isSeleccionado())
			{
				if(Nro.nroImagenes>0)Nro.decrementarNroImagenes();
				galeria4.getGaleria4().setSeleccionado(false);
				galeria4.getGaleria4().setStyle_Select("div_content_imageHotel");
				quitarImagen(galeria4.getGaleria4().getRutaImagen(),paquete);
			}else{
				Nro.incrementarNroImagenes();
				galeria4.getGaleria4().setSeleccionado(true);
				galeria4.getGaleria4().setStyle_Select("div_content_imageHotel_selected");
				asignarRutaImagenPaquete(galeria4.getGaleria4().getRutaImagen(), paquete,true);
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
	@GlobalCommand
	public void recuperarEstadoYourself(){
		confAltoNivelDAO.asignarListaConfAltoNivel(confAltoNivelDAO.recuperarconfAltoNivel("yourself"));
		setConfAltoNivel(confAltoNivelDAO.getoConfAltoNivel());
		if(confAltoNivel.isbEstado()){
			setVisibilidadYourself(true);
		}else {
			setVisibilidadYourself(false);
		}
		BindUtils.postNotifyChange(null, null, this, "confAltoNivel");
		BindUtils.postNotifyChange(null, null, this, "visibilidadYourself");
	}

	public void recuperarPaquetes() throws UnsupportedEncodingException {
		/** Obtencion de los paquetes existente desde la base de datos **/
		CPaqueteDAO paqueteDao = new CPaqueteDAO();
		paqueteDao.asignarListaPaquetes(paqueteDao.recuperarPaquetesBD());
		setListaPaquetes(paqueteDao.getListaPaquetes());
		/** Obtencion de los servcios desde la base de datos **/
		CServicioDAO servicioDao = new CServicioDAO();
		servicioDao.asignarListaServicios(servicioDao.recuperarServiciosBD());
		setListaServicios(servicioDao.getListaServicios());
		/** Obtencion de los destinos desde la base de datos **/
		CDestinoDAO destinoDao = new CDestinoDAO();
		destinoDao.asignarListaDestinos(destinoDao.recuperarListaDestinosBD());
		setListaDestinos(destinoDao.getListaDestinos());
		/** Obtencion de las actividades desde la base de datos **/
		CActividadDAO actividadDao = new CActividadDAO();
		actividadDao.asignarListaActividades(actividadDao.recuperarActividadesBD());
		setListaActividades(actividadDao.getListaActividades());
		
		BindUtils.postNotifyChange(null, null, this,"listaPaquetes");
		BindUtils.postNotifyChange(null, null, this,"listaServicios");
		BindUtils.postNotifyChange(null, null, this,"listaDestinos");
		BindUtils.postNotifyChange(null, null, this,"listaActividades");
	}
	@Command
	public void buscarPaquetes(@BindingParam("nombre") String nombre) throws UnsupportedEncodingException {
		CPaqueteDAO paqueteDao = new CPaqueteDAO();
		paqueteDao.asignarListaPaquetes(paqueteDao.buscarPaquetesBD(nombre));
		setListaPaquetes(paqueteDao.getListaPaquetes());
		BindUtils.postNotifyChange(null, null, this, "listaPaquetes");
	}

	@Command
	@NotifyChange("oPaquete")
	public void insertarPaquete(@BindingParam("componente") Component comp) throws WrongValueException, IOException {
		CPaqueteDAO paqueteDao = new CPaqueteDAO();
		if (!validoParaInsertar(comp))
			return;
		// System.out.println("hay "+nroDestinosSelect+" destinos");
		if (oPaquete.isSinDescuento()) {
			oPaquete.setnPrecioDos(oPaquete.getnPrecioUno().doubleValue());
			oPaquete.setnPrecioTres(oPaquete.getnPrecioUno().doubleValue());
			oPaquete.setnPrecioCuatro(oPaquete.getnPrecioUno().doubleValue());
			oPaquete.setnPrecioCinco(oPaquete.getnPrecioUno().doubleValue());
		}
		if (oPaquete.isManejo_camino_inca()) {
			oPaquete.setcDisponibilidad("CAMINO_INKA_CLASICO");
			oPaquete.setnDiaCaminoInka(0);
			/** Se procede a insertar el paquete **/
			String codPaquete = paqueteDao.recuperarCodigoPaquete(paqueteDao.insertarPaquete(oPaquete));
			for (CServicio servicio : listaServicios) {
				if (servicio.isSeleccionado()) {
					boolean b = paqueteDao.isOperationCorrect(
							paqueteDao.insertarPaqueteServicio(codPaquete, servicio.getnServicioCod()));
				}
			}
			for (CActividad act : listaActividades) {
				if (act.isSeleccionado()) {
					boolean b = paqueteDao.isOperationCorrect(
							paqueteDao.insertarPaqueteActividad(codPaquete, act.getnActividadCod()));
				}
			}
			if(!oPaquete.getListaImagenes().isEmpty())
			{
				for(CGaleriaPaquete imagenes:oPaquete.getListaImagenes())
				{
					imagenes.setCpaquetecod(codPaquete);
					CGaleriaPaqueteDAO galeriaPaqueteDao=new CGaleriaPaqueteDAO();
					boolean correcto=galeriaPaqueteDao.isOperationCorrect(galeriaPaqueteDao.insertarImagenPaquete(imagenes));
				}
			}
		} else if (oPaquete.isManejo_propio()) {
			oPaquete.setcDisponibilidad("MANEJO_PROPIO");
			if (oPaquete.isConDestino()) {
				if (oPaquete.isManejoPropio_conCaminoInka()) {
					// Calculamos en que dia se efectuará el camino inka
					int iter = 1;
					int nroNoches = 0;
					boolean seEncontroCaminoInka = false;
					while (iter <= oPaquete.getNroDestinosSelect()) {
						for (CDestino dest : listaDestinos) {
							if (dest.isSeleccionado() && dest.getnOrdenItinerario() == iter) {
								if (dest.getnCodPostal() == 84 && dest.isConCaminoInka()) {
									nroNoches += dest.getnNoches();
									seEncontroCaminoInka = true;
								} else
									nroNoches += dest.getnNoches();
								break;
							}
						}
						if (seEncontroCaminoInka)
							break;
						iter++;
					}
					oPaquete.setnDiaCaminoInka(nroNoches + 1);
				} else
					oPaquete.setnDiaCaminoInka(0);
			} else
				oPaquete.setnDiaCaminoInka(0);
			/** Se procede a insertar el paquete **/
			String codPaquete = paqueteDao.recuperarCodigoPaquete(paqueteDao.insertarPaquete(oPaquete));
			for (CDestino destino : listaDestinos) {
				if (destino.isSeleccionado()) {
					boolean b = paqueteDao
							.isOperationCorrect(paqueteDao.insertarPaqueteDestino(codPaquete, destino.getnDestinoCod(),
									destino.getnNoches(), destino.getnOrdenItinerario(), destino.isConCaminoInka()));
				}
			}
			for (CServicio servicio : listaServicios) {
				if (servicio.isSeleccionado()) {
					boolean b = paqueteDao.isOperationCorrect(
							paqueteDao.insertarPaqueteServicio(codPaquete, servicio.getnServicioCod()));
				}
			}
			for (CActividad act : listaActividades) {
				if (act.isSeleccionado()) {
					boolean b = paqueteDao.isOperationCorrect(
							paqueteDao.insertarPaqueteActividad(codPaquete, act.getnActividadCod()));
				}
			}
			boolean r = paqueteDao.isOperationCorrect(paqueteDao.insertarPaqueteCatHotel(codPaquete));
			/**
			 * SOLO PARA EL MANEJO PROPIO HAY QUE AGREGAR UN CALENDARIO PROPIO
			 **/
			if(!oPaquete.getListaImagenes().isEmpty())
			{
				for(CGaleriaPaquete imagenes:oPaquete.getListaImagenes())
				{
					imagenes.setCpaquetecod(codPaquete);
					CGaleriaPaqueteDAO galeriaPaqueteDao=new CGaleriaPaqueteDAO();
					boolean correcto=galeriaPaqueteDao.isOperationCorrect(galeriaPaqueteDao.insertarImagenPaquete(imagenes));
				}
			}
			CCalendarioPropioDAO calendarioDao = new CCalendarioPropioDAO();
			Calendar cal = Calendar.getInstance();
			r = calendarioDao.isOperationCorrect(
					calendarioDao.generarNuevoCalendarioPropioPaquete(codPaquete, cal.get(Calendar.YEAR)));
		} else if (oPaquete.isManejo_normal()) {
			oPaquete.setcDisponibilidad("MANEJO_NORMAL");
			oPaquete.setnDiaCaminoInka(0);
			/** Se procede a insertar el paquete **/
			String codPaquete = paqueteDao.recuperarCodigoPaquete(paqueteDao.insertarPaquete(oPaquete));
			for (CDestino destino : listaDestinos) {
				if (destino.isSeleccionado()) {
					boolean b = paqueteDao
							.isOperationCorrect(paqueteDao.insertarPaqueteDestino(codPaquete, destino.getnDestinoCod(),
									destino.getnNoches(), destino.getnOrdenItinerario(), destino.isConCaminoInka()));
				}
			}
			for (CServicio servicio : listaServicios) {
				if (servicio.isSeleccionado()) {
					boolean b = paqueteDao.isOperationCorrect(
							paqueteDao.insertarPaqueteServicio(codPaquete, servicio.getnServicioCod()));
				}
			}
			for (CActividad act : listaActividades) {
				if (act.isSeleccionado()) {
					System.out.println("E sido seleccionado");
					boolean b = paqueteDao.isOperationCorrect(
							paqueteDao.insertarPaqueteActividad(codPaquete, act.getnActividadCod()));
				}
			}
			boolean r = paqueteDao.isOperationCorrect(paqueteDao.insertarPaqueteCatHotel(codPaquete));
			if(!oPaquete.getListaImagenes().isEmpty())
			{
				for(CGaleriaPaquete imagenes:oPaquete.getListaImagenes())
				{
					imagenes.setCpaquetecod(codPaquete);
					CGaleriaPaqueteDAO galeriaPaqueteDao=new CGaleriaPaqueteDAO();
					boolean correcto=galeriaPaqueteDao.isOperationCorrect(galeriaPaqueteDao.insertarImagenPaquete(imagenes));
				}
			}
		} else if (oPaquete.isManejo_yourself()) {
			if(oPaquete.getManejoSelectYourself().equals("Machupicchu")){
				oPaquete.setcDisponibilidad("MACHUPICCHU");
			}else if(oPaquete.getManejoSelectYourself().equals("Machupicchu + Huaynapicchu 1G 7:00-8:00 a.m.")){
				oPaquete.setcDisponibilidad("MACHUPICCHU_HUAYNAPICCHU_1G");
			}else if(oPaquete.getManejoSelectYourself().equals("Machupicchu + Huaynapicchu 2G 10:00-11:00 a.m.")){
				oPaquete.setcDisponibilidad("MACHUPICCHU_HUAYNAPICCHU_2G");
			}else if(oPaquete.getManejoSelectYourself().equals("Machupicchu + Montaña 7:00 a.m.-8:00 a.m.")){
				oPaquete.setcDisponibilidad("MACHUPICCHU_MONTANA_1G");
			}else if(oPaquete.getManejoSelectYourself().equals("Machupicchu + Montaña 9:00 a.m.-10:00 a.m.")){
				oPaquete.setcDisponibilidad("MACHUPICCHU_MONTANA_2G");
			}else if(oPaquete.getManejoSelectYourself().equals("Camino Inka 4 dias 3 noche 7:00 a.m.-10:00 a.m.")){
				oPaquete.setcDisponibilidad("CAMINO_INKA_CLASICO_YOURSELF");
			}else if(oPaquete.getManejoSelectYourself().equals("Camino Inka 2 dias 1 noche 7:00 a.m.-10:00 a.m.")){
				oPaquete.setcDisponibilidad("CAMINO_INKA_CORTO");
			}
			oPaquete.setnDiaCaminoInka(0);
			/** se procede a insertar el paquete **/
			String codPaquete = paqueteDao.recuperarCodigoPaquete(paqueteDao.insertarPaquete(oPaquete));
			for (CDestino destino : listaDestinos) {
				if (destino.isSeleccionado()) {
					boolean b = paqueteDao
							.isOperationCorrect(paqueteDao.insertarPaqueteDestino(codPaquete, destino.getnDestinoCod(),
									destino.getnNoches(), destino.getnOrdenItinerario(), destino.isConCaminoInka()));
				}
			}
			for (CServicio servicio : listaServicios) {
				if (servicio.isSeleccionado()) {
					boolean b = paqueteDao.isOperationCorrect(
							paqueteDao.insertarPaqueteServicio(codPaquete, servicio.getnServicioCod()));
				}
			}
			for (CActividad act : listaActividades) {
				if (act.isSeleccionado()) {
					System.out.println("E sido seleccionado");
					boolean b = paqueteDao.isOperationCorrect(
							paqueteDao.insertarPaqueteActividad(codPaquete, act.getnActividadCod()));
				}
			}
			boolean r = paqueteDao.isOperationCorrect(paqueteDao.insertarPaqueteCatHotel(codPaquete));
			if(!oPaquete.getListaImagenes().isEmpty())
			{
				for(CGaleriaPaquete imagenes:oPaquete.getListaImagenes())
				{
					imagenes.setCpaquetecod(codPaquete);
					CGaleriaPaqueteDAO galeriaPaqueteDao=new CGaleriaPaqueteDAO();
					boolean correcto=galeriaPaqueteDao.isOperationCorrect(galeriaPaqueteDao.insertarImagenPaquete(imagenes));
				}
			}
		}
		Clients.showNotification("El paquete se inserto correctamente", Clients.NOTIFICATION_TYPE_INFO, comp,
				"before_start", 2700);
		/** Inicializando los objetos **/
		oPaquete = new CPaquete();
		/** Obtencion de los paquetes existente desde la base de datos **/
		paqueteDao.asignarListaPaquetes(paqueteDao.recuperarPaquetesBD());
		setListaPaquetes(paqueteDao.getListaPaquetes());
		/** Obtencion de los servcios desde la base de datos **/
		CServicioDAO servicioDao = new CServicioDAO();
		servicioDao.asignarListaServicios(servicioDao.recuperarServiciosBD());
		setListaServicios(servicioDao.getListaServicios());
		/** Obtencion de los destinos desde la base de datos **/
		CDestinoDAO destinoDao = new CDestinoDAO();
		destinoDao.asignarListaDestinos(destinoDao.recuperarListaDestinosBD());
		setListaDestinos(destinoDao.getListaDestinos());
		/** Obtencion de las actividades desde la base de datos **/
		CActividadDAO actividadDao = new CActividadDAO();
		actividadDao.asignarListaActividades(actividadDao.recuperarActividadesBD());
		setListaActividades(actividadDao.getListaActividades());
		refrescarSistema();
		div_select_destinos.setVisible(false);
		afterCompose(div_select_destinos);
	}

	public void refrescarSistema() {
		BindUtils.postNotifyChange(null, null, this, "oPaquete");
		BindUtils.postNotifyChange(null, null, this, "listaDestinos");
		BindUtils.postNotifyChange(null, null, this, "listaServicios");
		BindUtils.postNotifyChange(null, null, this, "listaPaquetes");
		BindUtils.postNotifyChange(null, null, this, "listaActividades");
	}

	public boolean validoParaInsertar(Component comp) {
		boolean valido = true;
		if (!oPaquete.isManejo_camino_inca() && !oPaquete.isManejo_propio() && !oPaquete.isManejo_normal() && !oPaquete.isManejo_yourself()) {
			valido = false;
			Clients.showNotification("Debe de Seleccionar algun tipo de manejo para el paquete.",
					Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
		} else if (oPaquete.isManejo_camino_inca()) {
			if (oPaquete.getcTituloIdioma1().equals("")) {
				valido = false;
				Clients.showNotification("El paquete debe de tener un nombre", Clients.NOTIFICATION_TYPE_ERROR, comp,
						"before_start", 2700);
			} else if (oPaquete.getnDias() == 0) {
				valido = false;
				Clients.showNotification("El paquete debe de tener un numero de dias", Clients.NOTIFICATION_TYPE_ERROR,
						comp, "before_start", 2700);
			} else {
				if (oPaquete.isConDescuento()) {
					if (oPaquete.getnPrecioUno().doubleValue() == 0 || oPaquete.getnPrecioDos().doubleValue() == 0
							|| oPaquete.getnPrecioTres().doubleValue() == 0
							|| oPaquete.getnPrecioCuatro().doubleValue() == 0
							|| oPaquete.getnPrecioCinco().doubleValue() == 0) {
						valido = false;
						Clients.showNotification("Ningun precio del paquete puede ser $ 0.00",
								Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
					}
				} else if (oPaquete.getnPrecioUno().doubleValue() == 0) {
					valido = false;
					Clients.showNotification("El precio del paquete no puede ser $ 0.00",
							Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
				}
			}
		}else if(oPaquete.isManejo_yourself()){
			if (oPaquete.getcTituloIdioma1().equals("")) {
				valido = false;
				Clients.showNotification("El paquete debe de tener un nombre", Clients.NOTIFICATION_TYPE_ERROR, comp,
						"before_start", 2700);
			}else if (oPaquete.getnPrecioUno().doubleValue() == 0) {
				valido = false;
				Clients.showNotification("El precio del paquete no puede ser $ 0.00",
						Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
			}
		} else {
			if (oPaquete.getcTituloIdioma1().equals("")) {
				valido = false;
				Clients.showNotification("El paquete debe de tener un nombre", Clients.NOTIFICATION_TYPE_ERROR, comp,
						"before_start", 2700);
			} else {
				if (oPaquete.isConDescuento()) {
					if (oPaquete.getnPrecioUno().doubleValue() == 0 || oPaquete.getnPrecioDos().doubleValue() == 0
							|| oPaquete.getnPrecioTres().doubleValue() == 0
							|| oPaquete.getnPrecioCuatro().doubleValue() == 0
							|| oPaquete.getnPrecioCinco().doubleValue() == 0) {
						valido = false;
						Clients.showNotification("Ningun precio del paquete puede ser $ 0.00",
								Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
					}
				} else if (oPaquete.getnPrecioUno().doubleValue() == 0) {
					valido = false;
					Clients.showNotification("El precio del paquete no puede ser $ 0.00",
							Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
				}
			}
			if (valido) {
				if (oPaquete.isConDestino()) {
					for (CDestino destino : listaDestinos) {
						if (destino.isSeleccionado() && destino.getnNoches() == 0) {
							valido = false;
							Clients.showNotification(
									"Debe especificar Cuantas noches se quedará en " + destino.getcDestino(),
									Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
							break;
						}
					}
				} else if (oPaquete.getnDias() == 0) {
					valido = false;
					Clients.showNotification("El paquete debe tener un numero de dias y noches",
							Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
				}
			}
		}
		return valido;
	}

	@GlobalCommand
	@NotifyChange({ "listaServicios" })
	public void actualizarServicioInsertado() throws UnsupportedEncodingException {
		listaServicios.clear();
		CServicioDAO servicioDao = new CServicioDAO();
		servicioDao.asignarListaServicios(servicioDao.recuperarServiciosBD());
		setListaServicios(servicioDao.getListaServicios());
		refrescarListaPaquetes();
	}

	@GlobalCommand
	@NotifyChange({ "listaDestinos" })
	public void actualizarDestinoInsertado() throws UnsupportedEncodingException {
		listaDestinos.clear();
		/* Asignacion de destinos */
		CDestinoDAO destinoDao = new CDestinoDAO();
		destinoDao.asignarListaDestinos(destinoDao.recuperarListaDestinosBD());
		setListaDestinos(destinoDao.getListaDestinos());
		refrescarListaPaquetes();
	}

	@GlobalCommand
	@NotifyChange({ "listaActividades" })
	public void actualizarActividadInsertado() throws UnsupportedEncodingException {
		listaActividades.clear();
		/* Asignacion de destinos */
		CActividadDAO actividadDao = new CActividadDAO();
		actividadDao.asignarListaActividades(actividadDao.recuperarActividadesBD());
		setListaActividades(actividadDao.getListaActividades());
		refrescarListaPaquetes();
	}

	public void refrescarListaPaquetes() throws UnsupportedEncodingException {
		listaPaquetes.clear();
		CPaqueteDAO paqueteDao = new CPaqueteDAO();
		paqueteDao.asignarListaPaquetes(paqueteDao.recuperarPaquetesBD());
		setListaPaquetes(paqueteDao.getListaPaquetes());
		BindUtils.postNotifyChange(null, null, this, "listaPaquetes");
	}

	@Command
	public void actualizarPaquete(@BindingParam("componente") Component comp,
			@BindingParam("paquete") CPaquete paquete) throws UnsupportedEncodingException {
		CPaqueteDAO paqueteDao = new CPaqueteDAO();
		// RECUPERAR LISTA PAQUETE-DESTINOS
		ArrayList<CPaqueteDestino> listaPaqueteDestinos = new ArrayList<CPaqueteDestino>();
		listaPaqueteDestinos = paquete.obtenerListaPaqueteDestino(paquete.getcPaqueteCod());
		// RECUPERAR LISTA PAQUETE-SERVICIOS
		ArrayList<CPaqueteServicio> listaPaqueteServicios = new ArrayList<CPaqueteServicio>();
		listaPaqueteServicios = paquete.obtenerListaPaqueteServicio(paquete.getcPaqueteCod());
		// RECUPERAR LISTA PAQUETE-ACTIVIDADES
		ArrayList<CPaqueteActividad> listaPaqueteActividades = new ArrayList<CPaqueteActividad>();
		listaPaqueteActividades = paquete.obtenerListaPaqueteActividad(paquete.getcPaqueteCod());
		/*******************/
		boolean hayNuevasImagenes=false;
		
		if (!validoParaActualizar(paquete, comp))
			return;
		if (paquete.isSinDescuento()) {
			paquete.setnPrecioDos(paquete.getnPrecioUno().doubleValue());
			paquete.setnPrecioTres(paquete.getnPrecioUno().doubleValue());
			paquete.setnPrecioCuatro(paquete.getnPrecioUno().doubleValue());
			paquete.setnPrecioCinco(paquete.getnPrecioUno().doubleValue());
		}
		if (paquete.isManejo_camino_inca()) {
			paquete.setcDisponibilidad("CAMINO_INKA_CLASICO");
			paquete.setnDiaCaminoInka(0);
			/** Se procede a actualizar el paquete **/
			boolean b = paqueteDao.isOperationCorrect(paqueteDao.modificarPaquete(paquete));
			for (CServicio servicio : paquete.getListaServicios()) {
				boolean estaRegistrado = false;
				for (CPaqueteServicio ps : listaPaqueteServicios) {
					if (ps.getnServicioCod() == servicio.getnServicioCod()) {
						estaRegistrado = true;
						if (!servicio.isSeleccionado()) {
							/** ELIMINAR **/
							paquete.setConServicio(false);
							b = paqueteDao
									.isOperationCorrect(paqueteDao.eliminarPaqueteServicio(ps.getCodPaqueteServicio()));
						}
						break;
					}
				}
				if (servicio.isSeleccionado() && !estaRegistrado) {
					paquete.setConServicio(true);
					b = paqueteDao.isOperationCorrect(
							paqueteDao.insertarPaqueteServicio(paquete.getcPaqueteCod(), servicio.getnServicioCod()));
				}
			}
			for (CActividad actividad : paquete.getListaActividades()) {
				boolean estaRegistrado = false;
				for (CPaqueteActividad pa : listaPaqueteActividades) {
					if (pa.getnActividadCod() == actividad.getnActividadCod()) {
						estaRegistrado = true;
						if (!actividad.isSeleccionado()) {
							/** ELIMINAR **/
							paquete.setConActividad(false);
							b = paqueteDao
									.isOperationCorrect(paqueteDao.eliminarPaqueteActividad(pa.getnPaqueteActividad()));
						}
						break;
					}
				}
				if (actividad.isSeleccionado() && !estaRegistrado) {
					paquete.setConActividad(true);
					b = paqueteDao.isOperationCorrect(paqueteDao.insertarPaqueteActividad(paquete.getcPaqueteCod(),
							actividad.getnActividadCod()));
				}
			}
			if (paquete.isSinDestino()) {
				for (CPaqueteDestino pd : listaPaqueteDestinos)
					b = paqueteDao.isOperationCorrect(paqueteDao.eliminarPaqueteDestino(pd.getCodPaqueteDestino()));
			}
			if(!paquete.getListaImagenes().isEmpty())
			{
				for(CGaleriaPaquete imagenes:paquete.getListaImagenes())
				{
					if(imagenes.getCpaquetecod()==null)
					{
						hayNuevasImagenes=true;
						imagenes.setCpaquetecod(paquete.getcPaqueteCod());
						CGaleriaPaqueteDAO galeriaPaqueteDao=new CGaleriaPaqueteDAO();
						boolean correcto=galeriaPaqueteDao.isOperationCorrect(galeriaPaqueteDao.insertarImagenPaquete(imagenes));
					}
				}
			}
		} else if (paquete.isManejo_propio()) {
			paquete.setcDisponibilidad("MANEJO_PROPIO");
			if (paquete.isConDestino()) {
				if (paquete.isManejoPropio_conCaminoInka()) {
					// Calculamos en que dia se efectuará el camino inka
					int iter = 1;
					int nroNoches = 0;
					boolean seEncontroCaminoInka = false;
					while (iter <= paquete.getNroDestinosSelect()) {
						for (CDestino dest : paquete.getListaDestinos()) {
							if (dest.isSeleccionado() && dest.getnOrdenItinerario() == iter) {
								if (dest.getnCodPostal() == 84 && dest.isConCaminoInka()) {
									nroNoches += dest.getnNoches();
									seEncontroCaminoInka = true;
								} else
									nroNoches += dest.getnNoches();
								break;
							}
						}
						if (seEncontroCaminoInka)
							break;
						iter++;
					}
					paquete.setnDiaCaminoInka(nroNoches + 1);
				} else
					paquete.setnDiaCaminoInka(0);
			} else
				paquete.setnDiaCaminoInka(0);
			/** Se procede a actualizar el paquete **/
			boolean b = paqueteDao.isOperationCorrect(paqueteDao.modificarPaquete(paquete));
			if (paquete.isConDestino()) {
				for (CDestino destino : paquete.getListaDestinos()) {
					boolean estaRegistrado = false;
					for (CPaqueteDestino pd : listaPaqueteDestinos) {
						if (pd.getnDestinoCod() == destino.getnDestinoCod()) {
							estaRegistrado = true;
							if (!destino.isSeleccionado()) {
								/** ELIMINAR **/
								b = paqueteDao.isOperationCorrect(
										paqueteDao.eliminarPaqueteDestino(pd.getCodPaqueteDestino()));
							} else
								b = paqueteDao.isOperationCorrect(paqueteDao.modificarPaqueteDestino(
										pd.getCodPaqueteDestino(), destino.getnNoches(), destino.getnOrdenItinerario(),
										destino.isConCaminoInka()));
							break;
						}
					}
					if (destino.isSeleccionado() && !estaRegistrado) {
						b = paqueteDao.isOperationCorrect(paqueteDao.insertarPaqueteDestino(paquete.getcPaqueteCod(),
								destino.getnDestinoCod(), destino.getnNoches(), destino.getnOrdenItinerario(),
								destino.isConCaminoInka()));
					}
				}
			}
			if (paquete.isSinDestino()) {
				for (CPaqueteDestino pd : listaPaqueteDestinos)
					b = paqueteDao.isOperationCorrect(paqueteDao.eliminarPaqueteDestino(pd.getCodPaqueteDestino()));
			}
			for (CServicio servicio : paquete.getListaServicios()) {
				boolean estaRegistrado = false;
				for (CPaqueteServicio ps : listaPaqueteServicios) {
					if (ps.getnServicioCod() == servicio.getnServicioCod()) {
						estaRegistrado = true;
						if (!servicio.isSeleccionado()) {
							/** ELIMINAR **/
							paquete.setConServicio(false);
							b = paqueteDao
									.isOperationCorrect(paqueteDao.eliminarPaqueteServicio(ps.getCodPaqueteServicio()));
						}
						break;
					}
				}
				if (servicio.isSeleccionado() && !estaRegistrado) {
					paquete.setConServicio(true);
					b = paqueteDao.isOperationCorrect(
							paqueteDao.insertarPaqueteServicio(paquete.getcPaqueteCod(), servicio.getnServicioCod()));
				}
			}
			for (CActividad actividad : paquete.getListaActividades()) {
				boolean estaRegistrado = false;
				for (CPaqueteActividad pa : listaPaqueteActividades) {
					if (pa.getnActividadCod() == actividad.getnActividadCod()) {
						estaRegistrado = true;
						if (!actividad.isSeleccionado()) {
							/** ELIMINAR **/
							paquete.setConActividad(false);
							b = paqueteDao
									.isOperationCorrect(paqueteDao.eliminarPaqueteActividad(pa.getnPaqueteActividad()));
						}
						break;
					}
				}
				if (actividad.isSeleccionado() && !estaRegistrado) {
					paquete.setConActividad(true);
					b = paqueteDao.isOperationCorrect(paqueteDao.insertarPaqueteActividad(paquete.getcPaqueteCod(),
							actividad.getnActividadCod()));
				}
			}
			boolean r = paqueteDao.isOperationCorrect(paqueteDao.insertarPaqueteCatHotel(paquete.getcPaqueteCod()));
			if(!paquete.getListaImagenes().isEmpty())
			{
				for(CGaleriaPaquete imagenes:paquete.getListaImagenes())
				{
					if(imagenes.getCpaquetecod()==null)
					{
						hayNuevasImagenes=true;
						imagenes.setCpaquetecod(paquete.getcPaqueteCod());
						CGaleriaPaqueteDAO galeriaPaqueteDao=new CGaleriaPaqueteDAO();
						boolean correcto=galeriaPaqueteDao.isOperationCorrect(galeriaPaqueteDao.insertarImagenPaquete(imagenes));
					}
				}
			}
			/**
			 * SOLO PARA EL MANEJO PROPIO HAY QUE AGREGAR UN CALENDARIO PROPIO
			 **/
			CCalendarioPropioDAO calendarioDao = new CCalendarioPropioDAO();
			Calendar cal = Calendar.getInstance();
			r = calendarioDao.isOperationCorrect(calendarioDao
					.generarNuevoCalendarioPropioPaquete(paquete.getcPaqueteCod(), cal.get(Calendar.YEAR)));
		} else if (paquete.isManejo_normal()) {
			paquete.setcDisponibilidad("MANEJO_NORMAL");
			paquete.setnDiaCaminoInka(0);
			/** Se procede a actualizar el paquete **/
			boolean b = paqueteDao.isOperationCorrect(paqueteDao.modificarPaquete(paquete));
			if (paquete.isConDestino()) {
				for (CDestino destino : paquete.getListaDestinos()) {
					boolean estaRegistrado = false;
					for (CPaqueteDestino pd : listaPaqueteDestinos) {
						if (pd.getnDestinoCod() == destino.getnDestinoCod()) {
							estaRegistrado = true;
							if (!destino.isSeleccionado()) {
								/** ELIMINAR **/
								b = paqueteDao.isOperationCorrect(
										paqueteDao.eliminarPaqueteDestino(pd.getCodPaqueteDestino()));
							} else
								b = paqueteDao.isOperationCorrect(paqueteDao.modificarPaqueteDestino(
										pd.getCodPaqueteDestino(), destino.getnNoches(), destino.getnOrdenItinerario(),
										destino.isConCaminoInka()));
							break;
						}
					}
					if (destino.isSeleccionado() && !estaRegistrado) {
						b = paqueteDao.isOperationCorrect(paqueteDao.insertarPaqueteDestino(paquete.getcPaqueteCod(),
								destino.getnDestinoCod(), destino.getnNoches(), destino.getnOrdenItinerario(),
								destino.isConCaminoInka()));
					}
				}
			}
			if (paquete.isSinDestino()) {
				for (CPaqueteDestino pd : listaPaqueteDestinos)
					b = paqueteDao.isOperationCorrect(paqueteDao.eliminarPaqueteDestino(pd.getCodPaqueteDestino()));
			}
			for (CServicio servicio : paquete.getListaServicios()) {
				boolean estaRegistrado = false;
				for (CPaqueteServicio ps : listaPaqueteServicios) {
					if (ps.getnServicioCod() == servicio.getnServicioCod()) {
						estaRegistrado = true;
						if (!servicio.isSeleccionado()) {
							/** ELIMINAR **/
							paquete.setConServicio(false);
							b = paqueteDao
									.isOperationCorrect(paqueteDao.eliminarPaqueteServicio(ps.getCodPaqueteServicio()));
						}
						break;
					}
				}
				if (servicio.isSeleccionado() && !estaRegistrado) {
					paquete.setConServicio(true);
					b = paqueteDao.isOperationCorrect(
							paqueteDao.insertarPaqueteServicio(paquete.getcPaqueteCod(), servicio.getnServicioCod()));
				}
			}
			for (CActividad actividad : paquete.getListaActividades()) {
				boolean estaRegistrado = false;
				for (CPaqueteActividad pa : listaPaqueteActividades) {
					if (pa.getnActividadCod() == actividad.getnActividadCod()) {
						estaRegistrado = true;
						if (!actividad.isSeleccionado()) {
							/** ELIMINAR **/
							paquete.setConActividad(false);
							b = paqueteDao
									.isOperationCorrect(paqueteDao.eliminarPaqueteActividad(pa.getnPaqueteActividad()));
						}
						break;
					}
				}
				if (actividad.isSeleccionado() && !estaRegistrado) {
					paquete.setConActividad(true);
					b = paqueteDao.isOperationCorrect(paqueteDao.insertarPaqueteActividad(paquete.getcPaqueteCod(),
							actividad.getnActividadCod()));
				}
			}
			boolean r = paqueteDao.isOperationCorrect(paqueteDao.insertarPaqueteCatHotel(paquete.getcPaqueteCod()));
			if(!paquete.getListaImagenes().isEmpty())
			{
				for(CGaleriaPaquete imagenes:paquete.getListaImagenes())
				{
					if(imagenes.getCpaquetecod()==null)
					{
						hayNuevasImagenes=true;
						imagenes.setCpaquetecod(paquete.getcPaqueteCod());
						CGaleriaPaqueteDAO galeriaPaqueteDao=new CGaleriaPaqueteDAO();
						boolean correcto=galeriaPaqueteDao.isOperationCorrect(galeriaPaqueteDao.insertarImagenPaquete(imagenes));
					}
				}
			}
		} else if (paquete.isManejo_yourself()) {
			if(paquete.getManejoSelectYourself().equals("Machupicchu")){
				paquete.setcDisponibilidad("MACHUPICCHU");
			}else if(paquete.getManejoSelectYourself().equals("Machupicchu + Huaynapicchu 1G 7:00-8:00 a.m.")){
				paquete.setcDisponibilidad("MACHUPICCHU_HUAYNAPICCHU_1G");
			}else if(paquete.getManejoSelectYourself().equals("Machupicchu + Huaynapicchu 2G 10:00-11:00 a.m.")){
				paquete.setcDisponibilidad("MACHUPICCHU_HUAYNAPICCHU_2G");
			}else if(paquete.getManejoSelectYourself().equals("Machupicchu + Montaña 7:00 a.m.-8:00 a.m.")){
				paquete.setcDisponibilidad("MACHUPICCHU_MONTANA_1G");
			}else if(paquete.getManejoSelectYourself().equals("Machupicchu + Montaña 9:00 a.m.-10:00 a.m.")){
				paquete.setcDisponibilidad("MACHUPICCHU_MONTANA_2G");
			}else if(paquete.getManejoSelectYourself().equals("Camino Inka 4 dias 3 noche 7:00 a.m.-10:00 a.m.")){
				paquete.setcDisponibilidad("CAMINO_INKA_CLASICO_YOURSELF");
			}else if(paquete.getManejoSelectYourself().equals("Camino Inka 2 dias 1 noche 7:00 a.m.-10:00 a.m.")){
				paquete.setcDisponibilidad("CAMINO_INKA_CORTO");
			}
			paquete.setnDiaCaminoInka(0);
			/** Se procede a actualizar el paquete **/
			boolean b = paqueteDao.isOperationCorrect(paqueteDao.modificarPaquete(paquete));
			for (CServicio servicio : paquete.getListaServicios()) {
				boolean estaRegistrado = false;
				for (CPaqueteServicio ps : listaPaqueteServicios) {
					if (ps.getnServicioCod() == servicio.getnServicioCod()) {
						estaRegistrado = true;
						if (!servicio.isSeleccionado()) {
							/** ELIMINAR **/
							paquete.setConServicio(false);
							b = paqueteDao
									.isOperationCorrect(paqueteDao.eliminarPaqueteServicio(ps.getCodPaqueteServicio()));
						}
						break;
					}
				}
				if (servicio.isSeleccionado() && !estaRegistrado) {
					paquete.setConServicio(true);
					b = paqueteDao.isOperationCorrect(
							paqueteDao.insertarPaqueteServicio(paquete.getcPaqueteCod(), servicio.getnServicioCod()));
				}
			}
			for (CActividad actividad : paquete.getListaActividades()) {
				boolean estaRegistrado = false;
				for (CPaqueteActividad pa : listaPaqueteActividades) {
					if (pa.getnActividadCod() == actividad.getnActividadCod()) {
						estaRegistrado = true;
						if (!actividad.isSeleccionado()) {
							/** ELIMINAR **/
							paquete.setConActividad(false);
							b = paqueteDao
									.isOperationCorrect(paqueteDao.eliminarPaqueteActividad(pa.getnPaqueteActividad()));
						}
						break;
					}
				}
				if (actividad.isSeleccionado() && !estaRegistrado) {
					paquete.setConActividad(true);
					b = paqueteDao.isOperationCorrect(paqueteDao.insertarPaqueteActividad(paquete.getcPaqueteCod(),
							actividad.getnActividadCod()));
				}
			}
			if(!paquete.getListaImagenes().isEmpty())
			{
				for(CGaleriaPaquete imagenes:paquete.getListaImagenes())
				{
					if(imagenes.getCpaquetecod()==null)
					{
						hayNuevasImagenes=true;
						CGaleriaPaqueteDAO galeriaPaqueteDao=new CGaleriaPaqueteDAO();
						imagenes.setCpaquetecod(paquete.getcPaqueteCod());
						boolean correcto=galeriaPaqueteDao.isOperationCorrect(galeriaPaqueteDao.insertarImagenPaquete(imagenes));
					}
				}
			}
		}
		//===RECUPERANDO LAS LISTA DE GAELRIA PAQUETE
		if(hayNuevasImagenes){
			CGaleriaPaqueteDAO galeriaPaqueteDao=new CGaleriaPaqueteDAO();
			galeriaPaqueteDao.asignarListaImagenesPaquete(galeriaPaqueteDao.recuperarImagenesPaqueteBD(paquete.getcPaqueteCod()));
			paquete.setListaImagenes(galeriaPaqueteDao.getListaImagenesPaquete());
		}
		// RECUPERAR LISTA PAQUETE-DESTINOS
		listaPaqueteDestinos = paquete.obtenerListaPaqueteDestino(paquete.getcPaqueteCod());
		// RECUPERAR LISTA PAQUETE-SERVICIOS
		listaPaqueteServicios = paquete.obtenerListaPaqueteServicio(paquete.getcPaqueteCod());
		// RECUPERAR LISTA PAQUETE-ACTIVIDADES
		listaPaqueteActividades = paquete.obtenerListaPaqueteActividad(paquete.getcPaqueteCod());
		if (listaPaqueteActividades.size() > 0)
			paquete.setConActividad(true);
		else
			paquete.setConActividad(false);
		if (listaPaqueteServicios.size() > 0)
			paquete.setConServicio(true);
		else
			paquete.setConServicio(false);
		Clients.showNotification("El paquete se actualizó correctamente", Clients.NOTIFICATION_TYPE_INFO, comp,
				"before_start", 2700);
		paquete.setEditable(false);
		refrescaFilaTemplate(paquete);

	}

	public boolean validoParaActualizar(CPaquete paquete, Component comp) {
		boolean valido = true;
		if (paquete.isManejo_camino_inca()) {
			if (paquete.getcTituloIdioma1().equals("")) {
				valido = false;
				Clients.showNotification("El paquete debe de tener un nombre", Clients.NOTIFICATION_TYPE_ERROR, comp,
						"before_start", 2700);
			} else if (paquete.getnDias() == 0) {
				valido = false;
				Clients.showNotification("El paquete debe de tener un numero de dias", Clients.NOTIFICATION_TYPE_ERROR,
						comp, "before_start", 2700);
			} else {
				if (paquete.isConDescuento()) {
					if (paquete.getnPrecioUno().doubleValue() == 0 || paquete.getnPrecioDos().doubleValue() == 0
							|| paquete.getnPrecioTres().doubleValue() == 0
							|| paquete.getnPrecioCuatro().doubleValue() == 0
							|| paquete.getnPrecioCinco().doubleValue() == 0) {
						valido = false;
						Clients.showNotification("Ningun precio del paquete puede ser $ 0.00",
								Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
					}
				} else if (paquete.getnPrecioUno().doubleValue() == 0) {
					valido = false;
					Clients.showNotification("El precio del paquete no puede ser $ 0.00",
							Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
				}
			}
		}else if(paquete.isManejo_yourself()){
			if (paquete.getcTituloIdioma1().equals("")) {
				valido = false;
				Clients.showNotification("El paquete debe de tener un nombre", Clients.NOTIFICATION_TYPE_ERROR, comp,
						"before_start", 2700);
			}else if (paquete.getnPrecioUno().doubleValue() == 0) {
				valido = false;
				Clients.showNotification("El precio del paquete no puede ser $ 0.00",
						Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
			}
		}
		else {
			if (paquete.getcTituloIdioma1().equals("")) {
				valido = false;
				Clients.showNotification("El paquete debe de tener un nombre", Clients.NOTIFICATION_TYPE_ERROR, comp,
						"before_start", 2700);
			} else {
				if (paquete.isConDescuento()) {
					if (paquete.getnPrecioUno().doubleValue() == 0 || paquete.getnPrecioDos().doubleValue() == 0
							|| paquete.getnPrecioTres().doubleValue() == 0
							|| paquete.getnPrecioCuatro().doubleValue() == 0
							|| paquete.getnPrecioCinco().doubleValue() == 0) {
						valido = false;
						Clients.showNotification("Ningun precio del paquete puede ser $ 0.00",
								Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
					}
				} else if (paquete.getnPrecioUno().doubleValue() == 0) {
					valido = false;
					Clients.showNotification("El precio del paquete no puede ser $ 0.00",
							Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
				}
			}
			if (valido) {
				if (paquete.isConDestino()) {
					for (CDestino destino : paquete.getListaDestinos()) {
						if (destino.isSeleccionado() && destino.getnNoches() == 0) {
							valido = false;
							Clients.showNotification(
									"Debe especificar Cuantas noches se quedará en " + destino.getcDestino(),
									Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
							break;
						}
					}
				} else if (paquete.getnDias() == 0) {
					valido = false;
					Clients.showNotification("El paquete debe tener un numero de dias y noches",
							Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
				}
			}
		}
		return valido;
	}
	@Command
	@NotifyChange({"mostrarImagenesExistentes","mostrarImagenesExistentesUpdate"})
	public void cerrarImagenesExistentes()
	{
		mostrarImagenesExistentes=false;
		mostrarImagenesExistentesUpdate=false;
	}
	@Command
	public void cerrarEditorItinerario(@BindingParam("cPaquete")CPaquete paquete)
	{
		paquete.setAbrirEditorItinerario(false);
		BindUtils.postNotifyChange(null, null, paquete, "abrirEditorItinerario");
	}
	@Command
	public void abrirEditorItinerario(@BindingParam("cPaquete")CPaquete paquete)
	{
		paquete.setAbrirEditorItinerario(true);
		BindUtils.postNotifyChange(null, null, paquete, "abrirEditorItinerario");
	}
	
	@Command
	public void cerrarEditorDescripcion(@BindingParam("cPaquete")CPaquete paquete)
	{
		paquete.setAbrirEditorDescripcion(false);
		BindUtils.postNotifyChange(null, null, paquete, "abrirEditorDescripcion");
	}
	@Command
	public void abrirEditorDescripcion(@BindingParam("cPaquete")CPaquete paquete)
	{
		paquete.setAbrirEditorDescripcion(true);
		BindUtils.postNotifyChange(null, null, paquete, "abrirEditorDescripcion");
	}
	@Command
	public void abrirConfigurarPaquete()
	{
		Window win_configPaquete = (Window) Executions.createComponents("/configurarPaquete.zul", null, null);
		win_configPaquete.doModal();
	}
	@Command
	public void Editar(@BindingParam("paquete") CPaquete p) {
		oPaqueteUpdate.setEditable(false);
		refrescaFilaTemplate(oPaqueteUpdate);
		oPaqueteUpdate = p;
		// le damos estado editable
		p.setEditable(!p.isEditable());
		// lcs.setEditingStatus(!lcs.getEditingStatus());
		refrescaFilaTemplate(p);
	}

	@Command
	public void Activar_Desactivar_paquete(@BindingParam("paquete") CPaquete p, @BindingParam("texto") String texto) {
		if (texto.equals("activar")) {
			p.setColor_btn_activo(p.COLOR_ACTIVO);
			p.setColor_btn_desactivo(p.COLOR_TRANSPARENT);
			p.setEstado_activo(true);
			p.setEstado_desactivo(false);
			p.setbEstado(true);
		} else {
			p.setColor_btn_activo(p.COLOR_TRANSPARENT);
			p.setColor_btn_desactivo(p.COLOR_DESACTIVO);
			p.setEstado_activo(false);
			p.setEstado_desactivo(true);
			p.setbEstado(false);
		}
		BindUtils.postNotifyChange(null, null, p, "color_btn_activo");
		BindUtils.postNotifyChange(null, null, p, "color_btn_desactivo");
		BindUtils.postNotifyChange(null, null, p, "estado_desactivo");
		BindUtils.postNotifyChange(null, null, p, "estado_activo");
	}

	@Command
	@NotifyChange({ "oPaquete" })
	public void manejo_propio_CaminoInka(@BindingParam("opcion") String opcion,
			@BindingParam("destino") CDestino destino) {
		if (opcion.equals("con_camino_inka")) {
			oPaquete.setManejoPropio_conCaminoInka(true);
			oPaquete.setnNoches(oPaquete.getnNoches() + 4);
			oPaquete.setnDias(oPaquete.getnNoches() + 1);
			destino.setConCaminoInka(true);
			destino.setSinCaminoInka(false);
			for (CDestino dest : listaDestinos) {
				if (!dest.equals(destino) && dest.isSeleccionado() && dest.getnCodPostal() == 84) {
					dest.setConCaminoInka(false);
					dest.setSinCaminoInka(true);
					dest.setPuedeCaminoInka(false);
					BindUtils.postNotifyChange(null, null, dest, "conCaminoInka");
					BindUtils.postNotifyChange(null, null, dest, "sinCaminoInka");
					BindUtils.postNotifyChange(null, null, dest, "puedeCaminoInka");
				}
			}
			BindUtils.postNotifyChange(null, null, destino, "conCaminoInka");
			BindUtils.postNotifyChange(null, null, destino, "sinCaminoInka");
		} else {
			oPaquete.setManejoPropio_conCaminoInka(false);
			oPaquete.setnNoches(oPaquete.getnNoches() - 4);
			if (oPaquete.getnNoches() == 0)
				oPaquete.setnDias(0);
			else
				oPaquete.setnDias(oPaquete.getnNoches() + 1);
			oPaquete.setnDiaCaminoInka(0);
			for (CDestino dest : listaDestinos) {
				if (dest.isSeleccionado() && dest.getnCodPostal() == 84) {
					dest.setConCaminoInka(false);
					dest.setSinCaminoInka(true);
					dest.setPuedeCaminoInka(true);
					BindUtils.postNotifyChange(null, null, dest, "conCaminoInka");
					BindUtils.postNotifyChange(null, null, dest, "sinCaminoInka");
					BindUtils.postNotifyChange(null, null, dest, "puedeCaminoInka");
				}
			}
		}
		oPaquete.setDias_noches(oPaquete.getnDias() + " DIAS Y " + oPaquete.getnNoches() + " NOCHES");
	}

	@Command
	public void manejo_propio_CaminoInka_update(@BindingParam("opcion") String opcion,
			@BindingParam("dest") CDestino destino, @BindingParam("paquete") CPaquete paquete) {
		if (opcion.equals("con_camino_inka")) {
			paquete.setManejoPropio_conCaminoInka(true);
			paquete.setnNoches(paquete.getnNoches() + 4);
			paquete.setnDias(paquete.getnNoches() + 1);
			destino.setConCaminoInka(true);
			destino.setSinCaminoInka(false);
			for (CDestino dest : paquete.getListaDestinos()) {
				if (!dest.equals(destino) && dest.isSeleccionado() && dest.getnCodPostal() == 84) {
					dest.setConCaminoInka(false);
					dest.setSinCaminoInka(true);
					dest.setPuedeCaminoInka(false);
					BindUtils.postNotifyChange(null, null, dest, "conCaminoInka");
					BindUtils.postNotifyChange(null, null, dest, "sinCaminoInka");
					BindUtils.postNotifyChange(null, null, dest, "puedeCaminoInka");
				}
			}
			BindUtils.postNotifyChange(null, null, destino, "conCaminoInka");
			BindUtils.postNotifyChange(null, null, destino, "sinCaminoInka");
		} else {
			paquete.setManejoPropio_conCaminoInka(false);
			paquete.setnNoches(paquete.getnNoches() - 4);
			if (paquete.getnNoches() == 0)
				paquete.setnDias(0);
			else
				paquete.setnDias(paquete.getnNoches() + 1);
			paquete.setnDiaCaminoInka(0);
			for (CDestino dest : paquete.getListaDestinos()) {
				if (dest.isSeleccionado() && dest.getnCodPostal() == 84) {
					dest.setConCaminoInka(false);
					dest.setSinCaminoInka(true);
					dest.setPuedeCaminoInka(true);
					BindUtils.postNotifyChange(null, null, dest, "conCaminoInka");
					BindUtils.postNotifyChange(null, null, dest, "sinCaminoInka");
					BindUtils.postNotifyChange(null, null, dest, "puedeCaminoInka");
				}
			}
		}
		paquete.setDias_noches(paquete.getnDias() + " DIAS Y " + paquete.getnNoches() + " NOCHES");
		BindUtils.postNotifyChange(null, null, paquete, "dias_noches");
		BindUtils.postNotifyChange(null, null, paquete, "manejoPropio_conCaminoInka");
		BindUtils.postNotifyChange(null, null, paquete, "conCaminoInka");
		BindUtils.postNotifyChange(null, null, paquete, "sinCaminoInka");
	}

	@Command
	@NotifyChange({ "oPaquete" })
	public void changePrecios_nuevo(@BindingParam("precio") String precio, @BindingParam("descuento") String descuento,
			@BindingParam("componente") Component comp) {
		if (descuento.equals("precio1")) {
			if (!isNumberDouble(precio)) {
				oPaquete.setnPrecio1_text(df.format(0));
				Clients.showNotification("Debe ser un numero de la forma ####.##", Clients.NOTIFICATION_TYPE_ERROR,
						comp, "before_start", 2700);
			} else
				oPaquete.setnPrecioUno(Double.parseDouble(df.format(Double.parseDouble(precio))));
		} else if (descuento.equals("precio2")) {
			if (!isNumberDouble(precio)) {
				oPaquete.setnPrecio2_text(df.format(0));
				Clients.showNotification("Debe ser un numero de la forma ####.##", Clients.NOTIFICATION_TYPE_ERROR,
						comp, "before_start", 2700);
			} else
				oPaquete.setnPrecioDos(Double.parseDouble(df.format(Double.parseDouble(precio))));
		} else if (descuento.equals("precio3")) {
			if (!isNumberDouble(precio)) {
				oPaquete.setnPrecio3_text(df.format(0));
				Clients.showNotification("Debe ser un numero de la forma ####.##", Clients.NOTIFICATION_TYPE_ERROR,
						comp, "before_start", 2700);
			} else
				oPaquete.setnPrecioTres(Double.parseDouble(df.format(Double.parseDouble(precio))));
		} else if (descuento.equals("precio4")) {
			if (!isNumberDouble(precio)) {
				oPaquete.setnPrecio4_text(df.format(0));
				Clients.showNotification("Debe ser un numero de la forma ####.##", Clients.NOTIFICATION_TYPE_ERROR,
						comp, "before_start", 2700);
			} else
				oPaquete.setnPrecioCuatro(Double.parseDouble(df.format(Double.parseDouble(precio))));
		} else if (descuento.equals("precio5")) {
			if (!isNumberDouble(precio)) {
				oPaquete.setnPrecio5_text(df.format(0));
				Clients.showNotification("Debe ser un numero de la forma ####.##", Clients.NOTIFICATION_TYPE_ERROR,
						comp, "before_start", 2700);
			} else
				oPaquete.setnPrecioCinco(Double.parseDouble(df.format(Double.parseDouble(precio))));
		}
	}

	@Command
	public void changePrecios_update(@BindingParam("precio") String precio, @BindingParam("descuento") String descuento,
			@BindingParam("componente") Component comp, @BindingParam("paquete") CPaquete paquete) {
		if (descuento.equals("precio1")) {
			if (!isNumberDouble(precio)) {
				paquete.setnPrecio1_text(df.format(0));
				Clients.showNotification("Debe ser un numero de la forma ####.##", Clients.NOTIFICATION_TYPE_ERROR,
						comp, "before_start", 2700);
			} else
				paquete.setnPrecioUno(Double.parseDouble(df.format(Double.parseDouble(precio))));
		} else if (descuento.equals("precio2")) {
			if (!isNumberDouble(precio)) {
				paquete.setnPrecio2_text(df.format(0));
				Clients.showNotification("Debe ser un numero de la forma ####.##", Clients.NOTIFICATION_TYPE_ERROR,
						comp, "before_start", 2700);
			} else
				paquete.setnPrecioDos(Double.parseDouble(df.format(Double.parseDouble(precio))));
		} else if (descuento.equals("precio3")) {
			if (!isNumberDouble(precio)) {
				paquete.setnPrecio3_text(df.format(0));
				Clients.showNotification("Debe ser un numero de la forma ####.##", Clients.NOTIFICATION_TYPE_ERROR,
						comp, "before_start", 2700);
			} else
				paquete.setnPrecioTres(Double.parseDouble(df.format(Double.parseDouble(precio))));
		} else if (descuento.equals("precio4")) {
			if (!isNumberDouble(precio)) {
				paquete.setnPrecio4_text(df.format(0));
				Clients.showNotification("Debe ser un numero de la forma ####.##", Clients.NOTIFICATION_TYPE_ERROR,
						comp, "before_start", 2700);
			} else
				paquete.setnPrecioCuatro(Double.parseDouble(df.format(Double.parseDouble(precio))));
		} else if (descuento.equals("precio5")) {
			if (!isNumberDouble(precio)) {
				paquete.setnPrecio5_text(df.format(0));
				Clients.showNotification("Debe ser un numero de la forma ####.##", Clients.NOTIFICATION_TYPE_ERROR,
						comp, "before_start", 2700);
			} else
				paquete.setnPrecioCinco(Double.parseDouble(df.format(Double.parseDouble(precio))));
		}
		BindUtils.postNotifyChange(null, null, paquete, "nPrecio1_text");
		BindUtils.postNotifyChange(null, null, paquete, "nPrecio2_text");
		BindUtils.postNotifyChange(null, null, paquete, "nPrecio3_text");
		BindUtils.postNotifyChange(null, null, paquete, "nPrecio4_text");
		BindUtils.postNotifyChange(null, null, paquete, "nPrecio5_text");
	}

	public boolean isNumberDouble(String cad) {
		try {
			Double.parseDouble(cad);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	@Command
	@NotifyChange({ "oPaquete" })
	public void selectDestinos(@BindingParam("destino") CDestino destino) {
		if (destino.isSeleccionado()) {
			destino.setSeleccionado(false);
			if (oPaquete.isManejoPropio_conCaminoInka()) {
				oPaquete.setnNoches(oPaquete.getnNoches() - destino.getnNoches());
				if (oPaquete.getnNoches() != 0)
					oPaquete.setnDias(oPaquete.getnNoches() + 1);
				else
					oPaquete.setnDias(0);
			} else {
				oPaquete.setnNoches(oPaquete.getnNoches() - destino.getnNoches());
				if (oPaquete.getnNoches() != 0)
					oPaquete.setnDias(oPaquete.getnNoches() + 1);
				else
					oPaquete.setnDias(0);
			}
			if (oPaquete.isManejoPropio_conCaminoInka() && destino.getnCodPostal() == 84 && destino.isConCaminoInka()) {
				oPaquete.setnNoches(oPaquete.getnNoches() - 4);
				if (oPaquete.getnNoches() != 0)
					oPaquete.setnDias(oPaquete.getnNoches() + 1);
				else
					oPaquete.setnDias(0);
				destino.setConCaminoInka(false);
				destino.setSinCaminoInka(true);
				oPaquete.setManejoPropio_conCaminoInka(false);
			}
			for (CDestino dest : listaDestinos) {
				if (dest.isSeleccionado() && dest.getnCodPostal() == 84 && oPaquete.isManejo_propio()) {
					dest.setPuedeCaminoInka(true);
					BindUtils.postNotifyChange(null, null, dest, "puedeCaminoInka");
				}
			}
			oPaquete.setDias_noches(oPaquete.getnDias() + " DIAS Y " + oPaquete.getnNoches() + " NOCHES");
			destino.setnNoches(0);
			// Recupero el orden de itinerario des seleccionado
			oPaquete.setOrdenDesSelect(destino.getnOrdenItinerario());
			// Disminuyo el numero de destinos seleccionados
			oPaquete.setNroDestinosSelect(oPaquete.getNroDestinosSelect() - 1);
			// Actualizo los itinerarios
			while (oPaquete.getOrdenDesSelect() <= oPaquete.getNroDestinosSelect()) {
				for (CDestino dest : listaDestinos) {
					if ((oPaquete.getOrdenDesSelect() + 1) == dest.getnOrdenItinerario()) {
						dest.setnOrdenItinerario(oPaquete.getOrdenDesSelect());
						BindUtils.postNotifyChange(null, null, dest, "nOrdenItinerario");
						break;
					}
				}
				oPaquete.setOrdenDesSelect(oPaquete.getOrdenDesSelect() + 1);
			}
			oPaquete.setOrdenDesSelect(0);
			destino.setnOrdenItinerario(0);
			destino.setPuedeCaminoInka(false);
		} else {
			destino.setSeleccionado(true);
			if (oPaquete.isManejo_propio() && !oPaquete.isManejoPropio_conCaminoInka())
				destino.asignaPuedeCaminoInka(destino);
			oPaquete.setNroDestinosSelect(oPaquete.getNroDestinosSelect() + 1);
			destino.setnOrdenItinerario(oPaquete.getNroDestinosSelect());
		}
		BindUtils.postNotifyChange(null, null, destino, "seleccionado");
		BindUtils.postNotifyChange(null, null, destino, "puedeCaminoInka");
		BindUtils.postNotifyChange(null, null, destino, "nOrdenItinerario");
		BindUtils.postNotifyChange(null, null, destino, "conCaminoInka");
		BindUtils.postNotifyChange(null, null, destino, "sinCaminoInka");
		BindUtils.postNotifyChange(null, null, destino, "nNoches");
	}

	@Command
	public void selectDestinos_update(@BindingParam("dest") CDestino destino,
			@BindingParam("paquete") CPaquete paquete) {
		if (destino.isSeleccionado()) {
			destino.setSeleccionado(false);
			if (paquete.isManejoPropio_conCaminoInka()) {
				paquete.setnNoches(paquete.getnNoches() - destino.getnNoches());
				if (paquete.getnNoches() != 0)
					paquete.setnDias(paquete.getnNoches() + 1);
				else
					paquete.setnDias(0);
			} else {
				paquete.setnNoches(paquete.getnNoches() - destino.getnNoches());
				if (paquete.getnNoches() != 0)
					paquete.setnDias(paquete.getnNoches() + 1);
				else
					paquete.setnDias(0);
			}
			if (paquete.isManejoPropio_conCaminoInka() && destino.getnCodPostal() == 84 && destino.isConCaminoInka()) {
				paquete.setnNoches(paquete.getnNoches() - 4);
				if (paquete.getnNoches() != 0)
					paquete.setnDias(paquete.getnNoches() + 1);
				else
					paquete.setnDias(0);
				destino.setConCaminoInka(false);
				destino.setSinCaminoInka(true);
				paquete.setManejoPropio_conCaminoInka(false);
			}
			for (CDestino dest : paquete.getListaDestinos()) {
				if (dest.isSeleccionado() && dest.getnCodPostal() == 84 && paquete.isManejo_propio()) {
					dest.setPuedeCaminoInka(true);
					BindUtils.postNotifyChange(null, null, dest, "puedeCaminoInka");
				}
			}
			paquete.setDias_noches(paquete.getnDias() + " DIAS Y " + paquete.getnNoches() + " NOCHES");
			destino.setnNoches(0);
			// Recupero el orden de itinerario des seleccionado
			paquete.setOrdenDesSelect(destino.getnOrdenItinerario());
			// Disminuyo el numero de destinos seleccionados
			paquete.setNroDestinosSelect(paquete.getNroDestinosSelect() - 1);
			// Actualizo los itinerarios
			while (paquete.getOrdenDesSelect() <= paquete.getNroDestinosSelect()) {
				for (CDestino dest : paquete.getListaDestinos()) {
					if ((paquete.getOrdenDesSelect() + 1) == dest.getnOrdenItinerario()) {
						dest.setnOrdenItinerario(paquete.getOrdenDesSelect());
						// System.out.println("Orden:
						// "+dest.getnOrdenItinerario());
						BindUtils.postNotifyChange(null, null, dest, "nOrdenItinerario");
						break;
					}
				}
				paquete.setOrdenDesSelect(paquete.getOrdenDesSelect() + 1);
			}
			// System.out.println("<---------------------->");
			paquete.setOrdenDesSelect(0);
			destino.setnOrdenItinerario(0);
			destino.setPuedeCaminoInka(false);
		} else {
			destino.setSeleccionado(true);
			if (paquete.isManejo_propio() && !paquete.isManejoPropio_conCaminoInka())
				destino.asignaPuedeCaminoInka(destino);
			paquete.setNroDestinosSelect(paquete.getNroDestinosSelect() + 1);
			destino.setnOrdenItinerario(paquete.getNroDestinosSelect());
		}
		BindUtils.postNotifyChange(null, null, destino, "seleccionado");
		BindUtils.postNotifyChange(null, null, destino, "puedeCaminoInka");
		BindUtils.postNotifyChange(null, null, destino, "nOrdenItinerario");
		BindUtils.postNotifyChange(null, null, destino, "conCaminoInka");
		BindUtils.postNotifyChange(null, null, destino, "sinCaminoInka");
		BindUtils.postNotifyChange(null, null, destino, "nNoches");
		BindUtils.postNotifyChange(null, null, paquete, "manejoPropio_conCaminoInka");
		BindUtils.postNotifyChange(null, null, paquete, "dias_noches");
		// for(CDestino dest:paquete.getListaDestinos())
		// if(dest.isSeleccionado())
		// System.out.println("Iti: "+dest.getnOrdenItinerario());
		// System.out.println("===========================");
	}

	@Command
	@NotifyChange("oPaquete")
	public void determinarNroNochesDestino() {
		oPaquete.setnNoches(0);
		for (CDestino destino : listaDestinos) {
			System.out.println("Nro de noches en " + destino.getcDestino() + ": " + destino.getnNoches());
			if (destino.isSeleccionado())
				oPaquete.setnNoches(oPaquete.getnNoches() + destino.getnNoches());
		}
		if (oPaquete.getnNoches() != 0)
			oPaquete.setnDias(oPaquete.getnNoches() + 1);
		else
			oPaquete.setnDias(0);
		if (oPaquete.isManejo_propio() && oPaquete.isManejoPropio_conCaminoInka()) {
			oPaquete.setnNoches(oPaquete.getnNoches() + 4);
			oPaquete.setnDias(oPaquete.getnNoches() + 1);
		}
		oPaquete.setDias_noches(oPaquete.getnDias() + " DIAS Y " + oPaquete.getnNoches() + " NOCHES");
	}

	@Command
	public void determinarNroNochesDestino_update(@BindingParam("paquete") CPaquete paquete) {
		paquete.setnNoches(0);
		for (CDestino destino : paquete.getListaDestinos()) {
			System.out.println("Nro de noches en " + destino.getcDestino() + ": " + destino.getnNoches());
			if (destino.isSeleccionado())
				paquete.setnNoches(paquete.getnNoches() + destino.getnNoches());
		}
		if (paquete.getnNoches() != 0)
			paquete.setnDias(paquete.getnNoches() + 1);
		else
			paquete.setnDias(0);
		if (paquete.isManejo_propio() && paquete.isManejoPropio_conCaminoInka()) {
			paquete.setnNoches(paquete.getnNoches() + 4);
			paquete.setnDias(paquete.getnNoches() + 1);
		}
		paquete.setDias_noches(paquete.getnDias() + " DIAS Y " + paquete.getnNoches() + " NOCHES");
		BindUtils.postNotifyChange(null, null, paquete, "dias_noches");
	}

	@Command
	public void selectServicios(@BindingParam("servicio") CServicio servicio) {
		if (servicio.isSeleccionado())
			servicio.setSeleccionado(false);
		else
			servicio.setSeleccionado(true);
	}

	@Command
	public void selectServicios_update(@BindingParam("serv") CServicio servicio) {
		if (servicio.isSeleccionado())
			servicio.setSeleccionado(false);
		else
			servicio.setSeleccionado(true);
	}

	@Command
	public void selectActividades(@BindingParam("actividad") CActividad actividad) {
		if (actividad.isSeleccionado())
			actividad.setSeleccionado(false);
		else
			actividad.setSeleccionado(true);
	}

	@Command
	public void selectActividades_update(@BindingParam("act") CActividad actividad) {
		if (actividad.isSeleccionado())
			actividad.setSeleccionado(false);
		else
			actividad.setSeleccionado(true);
	}

	@Command
	@NotifyChange({ "select_manejo", "oPaquete" })
	public void select_manejo_paquete(@BindingParam("opcion") String opcion) {
		select_manejo = true;
		if (opcion.equals("CAMINO_INKA_CLASICO")) {
			oPaquete.setManejo_camino_inca(true);
			oPaquete.setManejo_propio(false);
			oPaquete.setManejo_normal(false);
			oPaquete.setManejo_yourself(false);
			oPaquete.setConDestino(false);
			oPaquete.setSinDestino(true);
		} else if (opcion.equals("MANEJO_PROPIO")) {
			oPaquete.setManejo_camino_inca(false);
			oPaquete.setManejo_propio(true);
			oPaquete.setManejo_normal(false);
			oPaquete.setManejo_yourself(false);
			if (oPaquete.getNroDestinosSelect() > 0)
				for (CDestino dest : listaDestinos) {
					if (oPaquete.isManejoPropio_conCaminoInka()) {
						if (dest.isConCaminoInka() && dest.isSeleccionado() && dest.getnCodPostal() == 84)
							dest.setPuedeCaminoInka(true);
						else
							dest.setPuedeCaminoInka(false);
					} else if (dest.isSeleccionado() && dest.getnCodPostal() == 84)
						dest.setPuedeCaminoInka(true);
					BindUtils.postNotifyChange(null, null, dest, "puedeCaminoInka");
				}
		} else if (opcion.equals("MANEJO_NORMAL")) {
			oPaquete.setManejo_camino_inca(false);
			oPaquete.setManejo_propio(false);
			oPaquete.setManejo_normal(true);
			oPaquete.setManejo_yourself(false);
			if (oPaquete.getNroDestinosSelect() > 0)
				for (CDestino dest : listaDestinos) {
					if (dest.isSeleccionado() && dest.getnCodPostal() == 84) {
						dest.setPuedeCaminoInka(false);
						BindUtils.postNotifyChange(null, null, dest, "puedeCaminoInka");
					}
				}
		} else if (opcion.equals("MANEJO_YOURSELF")) {
			System.out.println("entra a yourself");
			oPaquete.setManejo_camino_inca(false);
			oPaquete.setManejo_normal(false);
			oPaquete.setManejo_propio(false);
			oPaquete.setManejo_yourself(true);
		}
		inicializarOpcionesPaquete();
	}

	@Command
	public void select_manejo_paquete_update(@BindingParam("opcion") String opcion,
			@BindingParam("paquete") CPaquete paquete) {
		if (opcion.equals("CAMINO_INKA_CLASICO")) {
			paquete.setManejo_camino_inca(true);
			paquete.setManejo_propio(false);
			paquete.setManejo_normal(false);
			paquete.setConDestino(false);
			paquete.setManejo_yourself(false);
			paquete.setSinDestino(true);
		} else if (opcion.equals("MANEJO_PROPIO")) {
			paquete.setManejo_camino_inca(false);
			paquete.setManejo_propio(true);
			paquete.setManejo_normal(false);
			paquete.setManejo_yourself(false);
			if (paquete.getNroDestinosSelect() > 0)
				for (CDestino dest : paquete.getListaDestinos()) {
					if (paquete.isManejoPropio_conCaminoInka()) {
						if (dest.isConCaminoInka() && dest.isSeleccionado() && dest.getnCodPostal() == 84)
							dest.setPuedeCaminoInka(true);
						else
							dest.setPuedeCaminoInka(false);
					} else if (dest.isSeleccionado() && dest.getnCodPostal() == 84)
						dest.setPuedeCaminoInka(true);
					BindUtils.postNotifyChange(null, null, dest, "puedeCaminoInka");
				}
		} else if (opcion.equals("MANEJO_NORMAL")) {
			paquete.setManejo_camino_inca(false);
			paquete.setManejo_propio(false);
			paquete.setManejo_normal(true);
			paquete.setManejo_yourself(false);
			if (paquete.getNroDestinosSelect() > 0)
				for (CDestino dest : paquete.getListaDestinos()) {
					if (dest.isSeleccionado() && dest.getnCodPostal() == 84) {
						dest.setPuedeCaminoInka(false);
						BindUtils.postNotifyChange(null, null, dest, "puedeCaminoInka");
					}
				}
		} else if (opcion.equals("MANEJO_YOURSELF")) {
			paquete.setManejo_camino_inca(false);
			paquete.setManejo_normal(false);
			paquete.setManejo_propio(false);
			paquete.setManejo_yourself(true);
		}
		BindUtils.postNotifyChange(null, null, paquete, "manejo_camino_inca");
		BindUtils.postNotifyChange(null, null, paquete, "manejo_propio");
		BindUtils.postNotifyChange(null, null, paquete, "manejo_normal");
		BindUtils.postNotifyChange(null, null, paquete, "manejo_yourself");
		BindUtils.postNotifyChange(null, null, paquete, "conDestino");
		BindUtils.postNotifyChange(null, null, paquete, "sinDestino");
		inicializarOpcionesPaquete_update(paquete);
	}

	public void inicializarOpcionesPaquete() {
		oPaquete.setnDias(0);
		oPaquete.setnNoches(0);
		if (oPaquete.isConDestino()) {
			if (oPaquete.isManejo_propio()) {
				for (CDestino destino : listaDestinos) {
					if (destino.isSeleccionado())
						if (destino.getnNoches() != 0)
							oPaquete.setnNoches(oPaquete.getnNoches() + destino.getnNoches());
				}
				if (oPaquete.getnNoches() != 0)
					oPaquete.setnDias(oPaquete.getnNoches() + 1);
				if (oPaquete.isManejoPropio_conCaminoInka()) {
					oPaquete.setnNoches(oPaquete.getnNoches() + 4);
					oPaquete.setnDias(oPaquete.getnNoches() + 1);
				}
			} else if (oPaquete.isManejo_normal()) {
				for (CDestino destino : listaDestinos) {
					if (destino.isSeleccionado())
						if (destino.getnNoches() != 0)
							oPaquete.setnNoches(oPaquete.getnNoches() + destino.getnNoches());
				}
				if (oPaquete.getnNoches() != 0)
					oPaquete.setnDias(oPaquete.getnNoches() + 1);
			}
		}
		oPaquete.setDias_noches(oPaquete.getnDias() + " DIAS Y " + oPaquete.getnNoches() + " NOCHES");
	}

	public void inicializarOpcionesPaquete_update(CPaquete paquete) {
		paquete.setnDias(0);
		paquete.setnNoches(0);
		if (paquete.isConDestino()) {
			if (paquete.isManejo_propio()) {
				for (CDestino destino : paquete.getListaDestinos()) {
					if (destino.isSeleccionado())
						if (destino.getnNoches() != 0)
							paquete.setnNoches(paquete.getnNoches() + destino.getnNoches());
				}
				if (paquete.getnNoches() != 0)
					paquete.setnDias(paquete.getnNoches() + 1);
				if (paquete.isManejoPropio_conCaminoInka()) {
					paquete.setnNoches(paquete.getnNoches() + 4);
					paquete.setnDias(paquete.getnNoches() + 1);
				}
			} else if (paquete.isManejo_normal()) {
				for (CDestino destino : paquete.getListaDestinos()) {
					if (destino.isSeleccionado())
						if (destino.getnNoches() != 0)
							paquete.setnNoches(paquete.getnNoches() + destino.getnNoches());
				}
				if (paquete.getnNoches() != 0)
					paquete.setnDias(paquete.getnNoches() + 1);
			}
		}
		paquete.setDias_noches(paquete.getnDias() + " DIAS Y " + paquete.getnNoches() + " NOCHES");
		BindUtils.postNotifyChange(null, null, paquete, "dias_noches");
		BindUtils.postNotifyChange(null, null, paquete, "nDias");
	}

	@Command
	@NotifyChange({ "oPaquete" })
	public void select_paquete_conDestino(@BindingParam("opcion") String opcion) {
		oPaquete.setnNoches(0);
		oPaquete.setnDias(0);
		if (opcion.equals("sin_destino")) {
			oPaquete.setConDestino(false);
			oPaquete.setSinDestino(true);
		} else {
			oPaquete.setConDestino(true);
			oPaquete.setSinDestino(false);
			for (CDestino destino : listaDestinos) {
				if (destino.isSeleccionado())
					if (destino.getnNoches() != 0)
						oPaquete.setnNoches(oPaquete.getnNoches() + destino.getnNoches());
			}
			if (oPaquete.getnNoches() != 0)
				oPaquete.setnDias(oPaquete.getnNoches() + 1);
			if (oPaquete.isManejo_propio() && oPaquete.isManejoPropio_conCaminoInka()) {
				oPaquete.setnNoches(oPaquete.getnNoches() + 4);
				oPaquete.setnDias(oPaquete.getnNoches() + 1);
			}

		}
		oPaquete.setDias_noches(oPaquete.getnDias() + " DIAS Y " + oPaquete.getnNoches() + " NOCHES");
	}

	@Command
	public void select_paquete_conDestino_update(@BindingParam("opcion") String opcion,
			@BindingParam("paquete") CPaquete paquete) {
		CDestinoDAO destinoDao = new CDestinoDAO();
		paquete.setnNoches(0);
		paquete.setnDias(0);
		if (opcion.equals("sin_destino")) {
			paquete.setConDestino(false);
			paquete.setSinDestino(true);
			paquete.setNroDestinosSelect(0);
			paquete.setOrdenDesSelect(0);
			destinoDao.asignarListaDestinos(destinoDao.recuperarListaDestinosBD());
			paquete.setListaDestinos(destinoDao.getListaDestinos());
		} else {
			paquete.setConDestino(true);
			paquete.setSinDestino(false);
			for (CDestino destino : paquete.getListaDestinos()) {
				if (destino.isSeleccionado())
					if (destino.getnNoches() != 0)
						paquete.setnNoches(paquete.getnNoches() + destino.getnNoches());
			}
			if (paquete.getnNoches() != 0)
				paquete.setnDias(paquete.getnNoches() + 1);
			if (paquete.isManejo_propio() && paquete.isManejoPropio_conCaminoInka()) {
				paquete.setnNoches(paquete.getnNoches() + 4);
				paquete.setnDias(paquete.getnNoches() + 1);
			}

		}
		paquete.setDias_noches(paquete.getnDias() + " DIAS Y " + paquete.getnNoches() + " NOCHES");
		BindUtils.postNotifyChange(null, null, paquete, "dias_noches");
		BindUtils.postNotifyChange(null, null, paquete, "conDestino");
		BindUtils.postNotifyChange(null, null, paquete, "sinDestino");
		BindUtils.postNotifyChange(null, null, paquete, "nDias");
		BindUtils.postNotifyChange(null, null, paquete, "listaDestinos");
		BindUtils.postNotifyChange(null, null, paquete, "ordenDesSelect");
		BindUtils.postNotifyChange(null, null, paquete, "nroDestinosSelect");
	}

	@Command
	public void select_paquete_conDescuento(@BindingParam("opcion") String opcion) {
		if (opcion.equals("sin_descuento")) {
			oPaquete.setConDescuento(false);
			oPaquete.setSinDescuento(true);
		} else {
			oPaquete.setConDescuento(true);
			oPaquete.setSinDescuento(false);
			oPaquete.setbConCupon(false);
		}
	}

	@Command
	public void select_paquete_conDescuento_update(@BindingParam("opcion") String opcion,
			@BindingParam("paquete") CPaquete paquete) {
		if (opcion.equals("sin_descuento")) {
			paquete.setConDescuento(false);
			paquete.setSinDescuento(true);
			paquete.setbConCupon(oPaqueteUpdate.isbConCupon());
		} else {
			paquete.setConDescuento(true);
			paquete.setSinDescuento(false);
			paquete.setbConCupon(false);
		}
		BindUtils.postNotifyChange(null, null, paquete, "conDescuento");
		BindUtils.postNotifyChange(null, null, paquete, "sinDescuento");
	}

	@Command
	@NotifyChange({ "oPaquete" })
	public void diasCaminoInka(@BindingParam("dias") int dias) {
		oPaquete.setnDias(dias);
		oPaquete.setnNoches(dias - 1);
		oPaquete.setDias_noches(oPaquete.getnDias() + " DIAS Y " + oPaquete.getnNoches() + " NOCHES");
	}

	@Command
	public void diasCaminoInka_update(@BindingParam("dias") int dias, @BindingParam("paquete") CPaquete paquete) {
		paquete.setnDias(dias);
		paquete.setnNoches(dias - 1);
		paquete.setDias_noches(paquete.getnDias() + " DIAS Y " + paquete.getnNoches() + " NOCHES");
		BindUtils.postNotifyChange(null, null, paquete, "dias_noches");
	}

	@Command
	@NotifyChange({ "oPaquete" })
	public void diasSinDestino(@BindingParam("dias") int dias) {
		oPaquete.setnDias(dias);
		oPaquete.setnNoches(dias - 1);
		oPaquete.setDias_noches(oPaquete.getnDias() + " DIAS Y " + oPaquete.getnNoches() + " NOCHES");
	}

	@Command
	public void diasSinDestino_update(@BindingParam("dias") int dias, @BindingParam("paquete") CPaquete paquete) {
		paquete.setnDias(dias);
		paquete.setnNoches(dias - 1);
		paquete.setDias_noches(paquete.getnDias() + " DIAS Y " + paquete.getnNoches() + " NOCHES");
		BindUtils.postNotifyChange(null, null, paquete, "dias_noches");
	}

	@Command
	@NotifyChange({ "oPaquete" })
	public void asignarNamePaquete() {
		oPaquete.setTitulo(oPaquete.getcTituloIdioma1());
	}

	@Command
	public void asignarNamePaquete_update(@BindingParam("paquete") CPaquete paquete) {
		paquete.setTitulo(paquete.getcTituloIdioma1());
		BindUtils.postNotifyChange(null, null, paquete, "titulo");
	}

	@Command
	public void cambioIdiomas(@BindingParam("idioma") String idIdioma, @BindingParam("paquete") CPaquete paquete) {
		if (idIdioma.equals("Espanol")) {
			paquete.setVisibleEspanol(true);
			paquete.setVisibleIngles(false);
			paquete.setVisiblePortugues(false);
		} else if (idIdioma.equals("Ingles")) {
			paquete.setVisibleEspanol(false);
			paquete.setVisibleIngles(true);
			paquete.setVisiblePortugues(false);
		} else if (idIdioma.equals("Portugues")) {
			paquete.setVisibleEspanol(false);
			paquete.setVisibleIngles(false);
			paquete.setVisiblePortugues(true);
		}
		BindUtils.postNotifyChange(null, null, paquete, "visibleEspanol");
		BindUtils.postNotifyChange(null, null, paquete, "visibleIngles");
		BindUtils.postNotifyChange(null, null, paquete, "visiblePortugues");
	}

	public void refrescaFilaTemplate(CPaquete p) {
		BindUtils.postNotifyChange(null, null, p, "editable");
		BindUtils.postNotifyChange(null, null, p, "listaServicios");
		BindUtils.postNotifyChange(null, null, p, "listaDestinos");
		BindUtils.postNotifyChange(null, null, p, "listaActividades");
		BindUtils.postNotifyChange(null, null, p, "conServicio");
		BindUtils.postNotifyChange(null, null, p, "conActividad");
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) throws WrongValueException, IOException {
		Selectors.wireComponents(view, this, false);
	}
	@Command
	@NotifyChange({"mostrarImagenesExistentes","mostrarImagenesExistentesUpdate","mostrarTextImgSeleccionado"})
	public void invocaImagenesExistentes(@BindingParam("hotel")CHotel hotel,@BindingParam("opcion")int opcion)
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
	public void invocaImagenesSubidas() {
		Window win_imagenes = (Window) Executions.createComponents("/imagenesPaquetes.zul", null, null);
		win_imagenes.doModal();
	}

	@Command
	public void uploadImagenes(@BindingParam("cPaquete") final CPaquete paquete,
			@BindingParam("componente") final Component comp) {
		System.out.print("entre a subir imagen verdadera");
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
						String urlImagen = ScannUtil.getPathImagenPaquetes() + img.getName();
						asignarRutaImagenPaquete(img.getName(), paquete,false);
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

	@Command
	public void seleccionardestinoInsertar(@BindingParam("codDestino") String codDestino,@BindingParam("manejoYourself") String manejoYourself) {
//		oPaquete.setcTituloIdioma1(codDestino);
		if(manejoYourself.equals("MACHUPICCHU")){
			oPaquete.setManejoSelectYourself(codDestino);
		}else if(manejoYourself.equals("MACHUPICCHU_HUAYNAPICCHU_1G")){
			oPaquete.setManejoSelectYourself(codDestino);
		}else if(manejoYourself.equals("MACHUPICCHU_HUAYNAPICCHU_2G")){
			oPaquete.setManejoSelectYourself(codDestino);
		}else if(manejoYourself.equals("MACHUPICCHU_MONTANA_1G")){
			oPaquete.setManejoSelectYourself(codDestino);
		}else if(manejoYourself.equals("MACHUPICCHU_MONTANA_2G")){
			oPaquete.setManejoSelectYourself(codDestino);
		}else if(manejoYourself.equals("CAMINO_INKA_CLASICO")){
			oPaquete.setManejoSelectYourself(codDestino);
		}else if(manejoYourself.equals("CAMINO_INKA_CORTO")){
			oPaquete.setManejoSelectYourself(codDestino);
		}
		BindUtils.postNotifyChange(null, null, oPaquete, "cTituloIdioma1");
		BindUtils.postNotifyChange(null, null, oPaquete, "manejoSelectYourself");
	}
	@Command
	public void seleccionardestinoUpdate(@BindingParam("codDestino") String codDestino,@BindingParam("manejoYourself") String manejoYourself,@BindingParam("paquete") CPaquete paquete) {
		paquete.setcTituloIdioma1(codDestino);
		if(manejoYourself.equals("MACHUPICCHU")){
			paquete.setManejoSelectYourself(codDestino);
		}else if(manejoYourself.equals("MACHUPICCHU_HUAYNAPICCHU_1G")){
			paquete.setManejoSelectYourself(codDestino);
		}else if(manejoYourself.equals("MACHUPICCHU_HUAYNAPICCHU_2G")){
			paquete.setManejoSelectYourself(codDestino);
		}else if(manejoYourself.equals("MACHUPICCHU_MONTANA_1G")){
			paquete.setManejoSelectYourself(codDestino);
		}else if(manejoYourself.equals("MACHUPICCHU_MONTANA_2G")){
			paquete.setManejoSelectYourself(codDestino);
		}else if(manejoYourself.equals("CAMINO_INKA_CLASICO")){
			paquete.setManejoSelectYourself(codDestino);
		}else if(manejoYourself.equals("CAMINO_INKA_CORTO")){
			paquete.setManejoSelectYourself(codDestino);
		}
		BindUtils.postNotifyChange(null, null, paquete, "cTituloIdioma1");
		BindUtils.postNotifyChange(null, null, paquete, "manejoSelectYourself");
	}
	public void asignarRutaImagenPaquete(String nombreImagen, CPaquete paquete,boolean imgExist)// ===paquete																																						// hotel=====
	{
		if(imgExist && estaEnLaListaImagenes(nombreImagen,paquete))return;
		else if(estaEnLaListaImagenes("img/tours/"+nombreImagen,paquete)){
			return;
		}
		CGaleriaPaquete oGaleriaPaquete = new CGaleriaPaquete();
		if(imgExist)
			oGaleriaPaquete.setcRutaImagen(nombreImagen);
		else
			oGaleriaPaquete.setcRutaImagen("img/tours/" + nombreImagen);
		oGaleriaPaquete.setVisible(true);
		if (paquete.getcFoto1().equals("img/tours/tourxdefecto.png")
				|| paquete.getcFoto2().equals("img/tours/tourxdefecto.png")
				|| paquete.getcFoto3().equals("img/tours/tourxdefecto.png")
				|| paquete.getcFoto4().equals("img/tours/tourxdefecto.png")
				|| paquete.getcFoto5().equals("img/tours/tourxdefecto.png")) {
			oGaleriaPaquete.setBestado(true);
			oGaleriaPaquete.setStyle_Select("div_content_imageHotel_selected");
			if (paquete.getcFoto1().equals("img/tours/tourxdefecto.png"))
				paquete.setcFoto1("img/tours/" + nombreImagen);
			else if (paquete.getcFoto2().equals("img/tours/tourxdefecto.png"))
				paquete.setcFoto2("img/tours/" + nombreImagen);
			else if (paquete.getcFoto3().equals("img/tours/tourxdefecto.png"))
				paquete.setcFoto3("img/tours/" + nombreImagen);
			else if (paquete.getcFoto4().equals("img/tours/tourxdefecto.png"))
				paquete.setcFoto4("img/tours/" + nombreImagen);
			else if (paquete.getcFoto5().equals("img/tours/tourxdefecto.png"))
				paquete.setcFoto5("img/tours/" + nombreImagen);
		}
		paquete.getListaImagenes().add(oGaleriaPaquete);
	}
	public void quitarImagen(String rutaImagen,CPaquete paquete)
	{
		for(CGaleriaPaquete galeria:paquete.getListaImagenes())
		{
			if(rutaImagen.equals(galeria.getcRutaImagen()))
			{
				paquete.getListaImagenes().remove(galeria);
				break;
			}
		}
	}
	public boolean estaEnLaListaImagenes(String nameImagen,CPaquete paquete)
	{
		boolean esta=false;
		for(CGaleriaPaquete galeria:paquete.getListaImagenes())
		{
			if(nameImagen.equals(galeria.getcRutaImagen()))
			{
				esta=true;
				break;
			}
		}
		return esta;
	}
	public void refrescarSelect(CGaleriaImageExist4 galeria4)
	{
		BindUtils.postNotifyChange(null, null, galeria4, "galeria1");
		BindUtils.postNotifyChange(null, null, galeria4, "galeria2");
		BindUtils.postNotifyChange(null, null, galeria4, "galeria3");
		BindUtils.postNotifyChange(null, null, galeria4, "galeria4");
	}
}
