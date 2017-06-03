package com.pricing.viewModel;

import java.io.File;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

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
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Image;
import org.zkoss.zul.Messagebox;

import pe.com.erp.crypto.Encryptar;

import com.pricing.dao.CServicioDAO;
import com.pricing.extras.KMP;
import com.pricing.model.CGaleriaImageExist;
import com.pricing.model.CGaleriaImageExist4;
import com.pricing.model.CHotel;
import com.pricing.model.CServicio;
import com.pricing.model.CSubServicio;
import com.pricing.model.Nro;
import com.pricing.util.ScannUtil;

public class subServicioVM 
{
	/**
	 * ATRIBUTOS
	 */
	private CSubServicio oSubServicioNew;
	private CSubServicio oSubServicioUpdate;
	private ArrayList<CServicio> listaServiciosNew;
	private CServicioDAO servicioDao;
	private ArrayList<CSubServicio> listaSubServicios;
	private DecimalFormat df;
	private DecimalFormatSymbols simbolos;
	private String NombreServicio;
	private ArrayList<CGaleriaImageExist> listaImagenesExistentes;
	private ArrayList<CGaleriaImageExist4> lista4ImagenesExistentes;
	private CGaleriaImageExist4 galeria4Aux;
	private boolean mostrarImagenesExistentes;
	private boolean mostrarImagenesExistentesUpdate;
	private boolean mostrarTextImgSeleccionado;
	private int codServicioNew;
	@Wire
	Div div_llenar_subservicios;
	/**
	 * GETTER AND SETTER
	 */
	public CServicioDAO getServicioDao() {
		return servicioDao;
	}
	public String getNombreServicio() {
		return NombreServicio;
	}
	public void setNombreServicio(String nombreServicio) {
		NombreServicio = nombreServicio;
	}
	public ArrayList<CServicio> getListaServiciosNew() {
		return listaServiciosNew;
	}
	public void setListaServiciosNew(ArrayList<CServicio> listaServiciosNew) {
		this.listaServiciosNew = listaServiciosNew;
	}
	public CSubServicio getoSubServicioNew() {
		return oSubServicioNew;
	}
	public void setoSubServicioNew(CSubServicio oSubServicioNew) {
		this.oSubServicioNew = oSubServicioNew;
	}
	public CSubServicio getoSubServicioUpdate() {
		return oSubServicioUpdate;
	}
	public void setoSubServicioUpdate(CSubServicio oSubServicioUpdate) {
		this.oSubServicioUpdate = oSubServicioUpdate;
	}
	public void setServicioDao(CServicioDAO servicioDao) {
		this.servicioDao = servicioDao;
	}
	public ArrayList<CSubServicio> getListaSubServicios() {
		return listaSubServicios;
	}
	public void setListaSubServicios(ArrayList<CSubServicio> listaSubServicios) {
		this.listaSubServicios = listaSubServicios;
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
	public void initVM()
	{
		/**Inicializando los objetos**/
		simbolos= new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		df=new DecimalFormat("########0.00",simbolos);
		oSubServicioNew=new CSubServicio();
		oSubServicioUpdate=new CSubServicio();
		servicioDao=new CServicioDAO();
		listaServiciosNew=new ArrayList<CServicio>();
		listaSubServicios=new ArrayList<CSubServicio>();
		mostrarImagenesExistentes=false;
		mostrarImagenesExistentesUpdate=false;
		mostrarTextImgSeleccionado=false;
		galeria4Aux=new CGaleriaImageExist4();
		codServicioNew=0;
		/*****************************/
		Encryptar encrip= new Encryptar();
		Execution exec = Executions.getCurrent();
	}
	@GlobalCommand
	public void recuperarSubServicios()
	{
		HttpSession ses = (HttpSession)Sessions.getCurrent().getNativeSession();
	    String user=(String)ses.getAttribute("usuario");
	    String pas=(String)ses.getAttribute("clave");
	    if(user!=null && pas!=null)
	    {
	    	/**Obtencion de las etiquetas de la base de datos**/
			servicioDao.asignarListaSubServicios(servicioDao.recuperarTodosSubServiciosBD());
			servicioDao.asignarListaServicios(servicioDao.recuperarServiciosconSubServiciosBD());
			/**Asignacion de las etiquetas a la listaEtiquetas**/
			setListaSubServicios(servicioDao.getListaSubServicios());
			setListaServiciosNew(servicioDao.getListaServicios());
	    }
	    BindUtils.postNotifyChange(null, null, this, "listaSubServicios");
	    BindUtils.postNotifyChange(null, null, this, "listaServiciosNew");
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
			@BindingParam("galeria")CGaleriaImageExist galeria,@BindingParam("subservicio")CSubServicio subservicio)
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
				asignarUrlImagenSubServicio(galeria4.getGaleria1().getRutaImagen(),subservicio,true);
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
				asignarUrlImagenSubServicio(galeria4.getGaleria2().getRutaImagen(),subservicio,true);
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
				asignarUrlImagenSubServicio(galeria4.getGaleria3().getRutaImagen(),subservicio,true);
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
				asignarUrlImagenSubServicio(galeria4.getGaleria4().getRutaImagen(),subservicio,true);
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
	public void buscarSubServicios(@BindingParam("nombre")String nombre){
		CServicioDAO servicioDao=new CServicioDAO();
		servicioDao.asignarListaSubServicios(servicioDao.buscarSubServiciosBD(nombre));
		setListaSubServicios(servicioDao.getListaSubServicios());
		BindUtils.postNotifyChange(null, null, this, "listaSubServicios");
	}
	@Command
	@NotifyChange({"oSubServicioNew","listaSubServicios"})
	public void InsertarSubServicio(@BindingParam("componente")Component componente)
	{
		if(!validoParaInsertar(componente))
			return;
		/**Una vez validado los datos necesarios se procede a insertar el nuevo sub Servicio**/
		boolean correcto=servicioDao.isOperationCorrect(servicioDao.insertarSubServicio(oSubServicioNew));
		if(correcto)
		{ 
			oSubServicioNew=new CSubServicio();
			servicioDao.asignarListaSubServicios(servicioDao.recuperarTodosSubServiciosBD());
			setListaSubServicios(servicioDao.getListaSubServicios());
			Clients.showNotification("El Nuevo Sub Servicio fue insertado correctamente", Clients.NOTIFICATION_TYPE_INFO, componente,"before_start",2700);
		}
		else
			Clients.showNotification("El Nuevo Sub Servicio fue insertado", Clients.NOTIFICATION_TYPE_INFO, componente,"before_start",2700);
		/**RECUPERAR NUEVAMENTE LA LISTA DE SUBSERVICIOS**/
	}
	public boolean validoParaInsertar(Component componente)
	{
		if(codServicioNew!=0)
			oSubServicioNew.setnServicioCod(codServicioNew);
		oSubServicioNew.setcSubServicioIndioma1(oSubServicioNew.getcSubServicioIndioma1().toUpperCase());
		oSubServicioNew.setcSubServicioIndioma2(oSubServicioNew.getcSubServicioIndioma2().toUpperCase());
		oSubServicioNew.setcSubServicioIndioma3(oSubServicioNew.getcSubServicioIndioma3().toUpperCase());
		oSubServicioNew.setcSubServicioIndioma4(oSubServicioNew.getcSubServicioIndioma4().toUpperCase());
		oSubServicioNew.setcSubServicioIndioma5(oSubServicioNew.getcSubServicioIndioma5().toUpperCase());
		boolean valido=true;
		/**Empezamos realizando las validaciones respectivas**/
		if(oSubServicioNew.getnServicioCod()==0)
		{
			Clients.showNotification("Es necesario escoger el servicio al que pertenecerá el subservicio", Clients.NOTIFICATION_TYPE_ERROR, componente,"before_start",2700);
			valido=false;
		}else if(oSubServicioNew.getcSubServicioIndioma1().equals(""))//Nombre del subServicio
		{
			Clients.showNotification("Es necesario escribir el nombre del sub servicio", Clients.NOTIFICATION_TYPE_ERROR, componente,"before_start",2700);
			valido=false;
		}else if(oSubServicioNew.getcDescripcionIdioma1().equals(""))
		{
			Clients.showNotification("Es necesario escribir la descripcion del sub servicio", Clients.NOTIFICATION_TYPE_ERROR, componente,"before_start",2700);
			valido=false;
		}else if(oSubServicioNew.getnServicioCod()==0)
		{
			Clients.showNotification("Debe seleccionar un servicio al cual pertenecera", Clients.NOTIFICATION_TYPE_ERROR, componente,"before_start",2700);
			valido=false;
		}else if(oSubServicioNew.getcUrlImg().equals(""))
		{
			Clients.showNotification("Es necesario insertar una imagen del sub servicio", Clients.NOTIFICATION_TYPE_ERROR, componente,"before_start",2700);
			valido=false;
		}else if(oSubServicioNew.getnPrecioServicio().doubleValue()<0)//los precios tbn puenden ser negativos ya que pueden ser un descuento
		{
			Clients.showNotification("El precio de un sub servicio no puede ser negativo", Clients.NOTIFICATION_TYPE_ERROR, componente,"before_start",2700);
			valido=false;
		}
		return valido;
	}
	@Command
	public void actualizarSubServicio(@BindingParam("subServicio")CSubServicio subServicio,@BindingParam("componente")Component comp)
	{	if(!validoParaActualizar(subServicio,comp))
			return;
		subServicio.setEditable(false);
		refrescaFilaTemplate(subServicio);
		/**Actualizar datos de la etiqueta en la BD**/
		boolean b=servicioDao.isOperationCorrect(servicioDao.modificarSubServicio(subServicio));
	}
	public boolean validoParaActualizar(CSubServicio subServicio,Component componente)
	{
		subServicio.setcSubServicioIndioma1(subServicio.getcSubServicioIndioma1().toUpperCase());
		subServicio.setcSubServicioIndioma2(subServicio.getcSubServicioIndioma2().toUpperCase());
		subServicio.setcSubServicioIndioma3(subServicio.getcSubServicioIndioma3().toUpperCase());
		subServicio.setcSubServicioIndioma4(subServicio.getcSubServicioIndioma4().toUpperCase());
		subServicio.setcSubServicioIndioma5(subServicio.getcSubServicioIndioma5().toUpperCase());
		boolean valido=true;
		if(subServicio.getcSubServicioIndioma1().equals(""))//Nombre del subServicio
		{
			Clients.showNotification("Es necesario poner el nombre del sub servicio", Clients.NOTIFICATION_TYPE_ERROR, componente,"before_start",2700);
			valido=false;
		}else if(subServicio.getcDescripcionIdioma1().equals(""))
		{
			Clients.showNotification("Es necesario poner la descripcion del sub servicio", Clients.NOTIFICATION_TYPE_ERROR, componente,"before_start",2700);
			valido=false;
		}else if(subServicio.getnServicioCod()==0)
		{
			Clients.showNotification("Debe seleccionar un servicio al cual pertenecera", Clients.NOTIFICATION_TYPE_ERROR, componente,"before_start",2700);
			valido=false;
		}else if(subServicio.getcUrlImg().equals(""))
		{
			Clients.showNotification("Es necesario insertar una imagen del sub servicio", Clients.NOTIFICATION_TYPE_ERROR, componente,"before_start",2700);
			valido=false;
		}else if(subServicio.getnPrecioServicio().doubleValue()<0)//los precios tbn puenden ser negativos ya que pueden ser un descuento
		{
			Clients.showNotification("El precio de un sub servicio no puede ser negativo", Clients.NOTIFICATION_TYPE_ERROR, componente,"before_start",2700);
			valido=false;
		}
		return valido;
	}
	@GlobalCommand
	@NotifyChange({"listaServiciosNew","listaSubServicios"})
	public void actualizarServicioInsertado()
	{
		listaServiciosNew.clear();
		listaSubServicios.clear();
		/**Obtencion de las etiquetas de la base de datos**/
		servicioDao.asignarListaSubServicios(servicioDao.recuperarTodosSubServiciosBD());
		servicioDao.asignarListaServicios(servicioDao.recuperarServiciosconSubServiciosBD());
		/**Asignacion de las etiquetas a la listaEtiquetas**/
		setListaSubServicios(servicioDao.getListaSubServicios());
		setListaServiciosNew(servicioDao.getListaServicios());
	}
	@Command
	@NotifyChange("oSubServicioUpdate")
	 public void Editar(@BindingParam("subServicio") CSubServicio s ) 
	{	
		div_llenar_subservicios.setVisible(false);
		afterCompose(div_llenar_subservicios);
		s.setEditable(false);
		oSubServicioUpdate.setEditable(false);
		refrescaFilaTemplate(oSubServicioUpdate);
		oSubServicioUpdate=s;
		//le damos estado editable
		s.setEditable(!s.isEditable());	
		//lcs.setEditingStatus(!lcs.getEditingStatus());
		refrescaFilaTemplate(s);
   }
	@Command
	 public void Activar_Desactivar(@BindingParam("subServicio")CSubServicio s,@BindingParam("texto")String texto)
	{
		if(texto.equals("activar"))
		{
			s.setColor_btn_activo(s.COLOR_ACTIVO);
			s.setColor_btn_desactivo(s.COLOR_TRANSPARENT);
			s.setbEstado(true);
		}else{
			s.setColor_btn_activo(s.COLOR_TRANSPARENT);
			s.setColor_btn_desactivo(s.COLOR_DESACTIVO);
			s.setbEstado(false);
		}
		BindUtils.postNotifyChange(null, null, s,"color_btn_activo");
		BindUtils.postNotifyChange(null, null, s,"color_btn_desactivo");
		BindUtils.postNotifyChange(null, null, s,"bEstado");
	}
	@Command
	public void cambioIdiomas(@BindingParam("idioma")String idIdioma,@BindingParam("subServicio")CSubServicio servicio)
	{
		if(idIdioma.equals("Espanol"))
		{
				servicio.setVisibleEspanol(true);
				servicio.setVisibleIngles(false);
				servicio.setVisiblePortugues(false);
		}
		else if(idIdioma.equals("Ingles"))
		{
				servicio.setVisibleEspanol(false);
				servicio.setVisibleIngles(true);
				servicio.setVisiblePortugues(false);
		}
		else if(idIdioma.equals("Portugues"))
		{
				servicio.setVisibleEspanol(false);
				servicio.setVisibleIngles(false);
				servicio.setVisiblePortugues(true);
		}
		BindUtils.postNotifyChange(null, null, servicio, "visibleEspanol");
		BindUtils.postNotifyChange(null, null, servicio, "visibleIngles");
		BindUtils.postNotifyChange(null, null, servicio, "visiblePortugues");
	}
	
	@Command
	@NotifyChange({"oSubServicioNew"})
	public void changePrecios_nuevo(@BindingParam("precio")String precio,@BindingParam("componente")Component componente)
	{
		if(!isNumberDouble(precio))
		{
			oSubServicioNew.setnPrecioServicio_text(df.format(0.00));
			Clients.showNotification("Debe ser un numero de la forma ####.##",Clients.NOTIFICATION_TYPE_ERROR, componente,"before_start", 2700);
		}
		else
		{
				oSubServicioNew.setnPrecioServicio(Double.parseDouble(df.format(Double.parseDouble(precio))));
		}
	}
	
	
	@Command
	@NotifyChange({"oSubServicioUpdate"})
	public void changePrecios_actualizar(@BindingParam("precio")String precio,@BindingParam("componente")Component componente)
	{
		if(!isNumberDouble(precio))
		{
			oSubServicioUpdate.setnPrecioServicio_text(df.format(oSubServicioUpdate.getnPrecioServicio().doubleValue()));
			Clients.showNotification("Debe ser un numero de la forma ####.##",Clients.NOTIFICATION_TYPE_ERROR, componente,"before_start", 2700);
		}
		else
		{
				oSubServicioUpdate.setnPrecioServicio(Double.parseDouble(df.format(Double.parseDouble(precio))));
		}
	}
	public boolean isNumberDouble(String cad)
	{
		try
		 {
		   Double.parseDouble(cad);
		   return true;
		 }
		 catch(NumberFormatException nfe)
		 {
		   return false;
		 }
	}
	
	@Command
	@NotifyChange("oSubServicioNew")
	public void  asignacion_servicio(@BindingParam("servicio")int codServicio){
		codServicioNew=codServicio;
		oSubServicioNew.setnServicioCod(codServicio);
	}
	@Command
	public void  asignacion_servicio_update(@BindingParam("servicio")int codServicio,@BindingParam("subServicio")CSubServicio sub){
		sub.setnServicioCod(codServicio);
	}
	@Command
	public void uploadImagen(@BindingParam("componente")final Component comp) {
			 Fileupload.get(new EventListener<UploadEvent>(){
					public void onEvent(UploadEvent event) {
						org.zkoss.util.media.Media media = event.getMedia();
						if (media instanceof org.zkoss.image.Image) {
							org.zkoss.image.Image img = (org.zkoss.image.Image) media;
							//Con este metodo(uploadFile) de clase guardo la imagen en la ruta del servidor
				            boolean b=ScannUtil.uploadFileServicios(img);
				            //================================
				            //Una vez creado el nuevo nombre de archivo de imagen se procede a cambiar el nombre
				            String urlImagen=ScannUtil.getPathImagensSubServicios()+img.getName();
				            asignarUrlImagenSubServicio(img.getName(),oSubServicioNew,false);
				            Clients.showNotification(img.getName()+" Se inserto",Clients.NOTIFICATION_TYPE_INFO,comp,"before_start",2700);

						} else {
							Messagebox.show(media+"Error", "Error", Messagebox.OK, Messagebox.ERROR);
								}
					}
			     });
	}
	public void asignarUrlImagenSubServicio(String url,CSubServicio subServ,boolean imgExist)
	{
		if(imgExist)
			subServ.setcUrlImg(url);
		else
			subServ.setcUrlImg("img/servicios/"+url);
		BindUtils.postNotifyChange(null, null, subServ,"cUrlImg");
	}
	@Command
	public void cambiarImagen(@BindingParam("subServicio")final CSubServicio subServ,@BindingParam("componente")final Component comp) {
			 Fileupload.get(new EventListener<UploadEvent>(){
					public void onEvent(UploadEvent event) {
						org.zkoss.util.media.Media media = event.getMedia();
						if (media instanceof org.zkoss.image.Image) {
							org.zkoss.image.Image img = (org.zkoss.image.Image) media;
							//Con este metodo(uploadFile) de clase guardo la imagen en la ruta del servidor
				            boolean b=ScannUtil.uploadFileServicios(img);
				            //================================
				            //Una vez creado el nuevo nombre de archivo de imagen se procede a cambiar el nombre
				            String urlImagen=ScannUtil.getPathImagensSubServicios()+img.getName();
				            asignarUrlImagenSubServicio(img.getName(),subServ,false);
				            Clients.showNotification(img.getName()+" Se cambio",Clients.NOTIFICATION_TYPE_INFO,comp,"before_start",2700);

						} else {
							Messagebox.show(media+"Error", "Error", Messagebox.OK, Messagebox.ERROR);
								}
					}
			     });
	}
	public void refrescaFilaTemplate(CSubServicio s)
	{
		BindUtils.postNotifyChange(null, null, s, "editable");
	}
	public void refrescarSelect(CGaleriaImageExist4 galeria4)
	{
		BindUtils.postNotifyChange(null, null, galeria4, "galeria1");
		BindUtils.postNotifyChange(null, null, galeria4, "galeria2");
		BindUtils.postNotifyChange(null, null, galeria4, "galeria3");
		BindUtils.postNotifyChange(null, null, galeria4, "galeria4");
	}
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view)
	{
		Selectors.wireComponents(view, this, false);
	}
}
