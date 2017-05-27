package com.pricing.viewModel;

import java.io.File;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
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
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;

import pe.com.erp.crypto.Encryptar;

import com.pricing.dao.CEtiquetaDAO;
import com.pricing.dao.CPaqueteDAO;
import com.pricing.dao.CServicioDAO;
import com.pricing.extras.KMP;
import com.pricing.model.CEtiqueta;
import com.pricing.model.CGaleriaImageExist;
import com.pricing.model.CGaleriaImageExist4;
import com.pricing.model.CHotel;
import com.pricing.model.CServicio;
import com.pricing.model.Nro;
import com.pricing.util.ScannUtil;

public class servicioVM {
	//====================
		private DecimalFormat df;
		private DecimalFormatSymbols simbolos;
	//====================
	/**
	 * ATRIBUTOS
	 */
	private CServicio oServicioNuevo;
	private CServicio oServicioUpdate;
	private CServicioDAO servicioDao;
	private ArrayList<CServicio> listaServicios;
	private ArrayList<CGaleriaImageExist> listaImagenesExistentes;
	private ArrayList<CGaleriaImageExist4> lista4ImagenesExistentes;
	private CGaleriaImageExist4 galeria4Aux;
	private boolean mostrarImagenesExistentes;
	private boolean mostrarImagenesExistentesUpdate;
	private boolean mostrarTextImgSeleccionado;
	/**
	 * GETTER AND SETTER
	 */
	public CServicioDAO getServicioDao() {
		return servicioDao;
	}
	public void setServicioDao(CServicioDAO servicioDao) {
		this.servicioDao = servicioDao;
	}
	public ArrayList<CServicio> getListaServicios() {
		return listaServicios;
	}
	public void setListaServicios(ArrayList<CServicio> listaServicios) {
		this.listaServicios = listaServicios;
	}
	public CServicio getoServicioNuevo() {
		return oServicioNuevo;
	}
	public void setoServicioNuevo(CServicio oServicioNuevo) {
		this.oServicioNuevo = oServicioNuevo;
	}
	public CServicio getoServicioUpdate() {
		return oServicioUpdate;
	}
	public void setoServicioUpdate(CServicio oServicioUpdate) {
		this.oServicioUpdate = oServicioUpdate;
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
		/*******************************/
		simbolos= new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		df=new DecimalFormat("########0.00",simbolos);
		/*******************************/
		/**Inicializando los objetos**/
		oServicioNuevo=new CServicio();
		oServicioUpdate=new CServicio();
		servicioDao=new CServicioDAO();
		listaServicios=new ArrayList<CServicio>();
		mostrarImagenesExistentes=false;
		mostrarImagenesExistentesUpdate=false;
		mostrarTextImgSeleccionado=false;
		galeria4Aux=new CGaleriaImageExist4();
		/*****************************/
		Encryptar encrip= new Encryptar();
//		System.out.println("Aqui esta la contraseña desencriptada-->"+encrip.decrypt("cyS249O3OHZTsG0ww1rYrw=="));
	}
	@GlobalCommand
	public void recuperarServicios()
	{
		HttpSession ses = (HttpSession)Sessions.getCurrent().getNativeSession();
	    String user=(String)ses.getAttribute("usuario");
	    String pas=(String)ses.getAttribute("clave");
	    if(user!=null && pas!=null)
	    {
	    	/**Obtencion de las etiquetas de la base de datos**/
			servicioDao.asignarListaServicios(servicioDao.recuperarTodosServiciosBD());
			/**Asignacion de las etiquetas a la listaEtiquetas**/
			setListaServicios(servicioDao.getListaServicios());
	    }
	    BindUtils.postNotifyChange(null, null, this,"listaServicios");
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
			@BindingParam("galeria")CGaleriaImageExist galeria,@BindingParam("servicio")CServicio servicio)
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
				asignarUrlImagenServicio(galeria4.getGaleria1().getRutaImagen(),servicio,true);
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
				asignarUrlImagenServicio(galeria4.getGaleria2().getRutaImagen(),servicio,true);
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
				asignarUrlImagenServicio(galeria4.getGaleria3().getRutaImagen(),servicio,true);
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
				asignarUrlImagenServicio(galeria4.getGaleria4().getRutaImagen(),servicio,true);
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
	public void buscarServicios(@BindingParam("nombre")String nombre){
		CServicioDAO servicioDao=new CServicioDAO();
		servicioDao.asignarListaServicios(servicioDao.buscarServiciosBD(nombre));
		setListaServicios(servicioDao.getListaServicios());
		BindUtils.postNotifyChange(null, null, this, "listaServicios");
	}
	@Command
	@NotifyChange({"oServicioNuevo","listaServicios"})
	public void insertarServicio(@BindingParam("componente")Component comp)
	{

		if(!validoParaInsertar(comp))
			return;
		oServicioNuevo.setcServicioIndioma1(oServicioNuevo.getcServicioIndioma1().toUpperCase());
		oServicioNuevo.setcServicioIndioma2(oServicioNuevo.getcServicioIndioma2().toUpperCase());
		oServicioNuevo.setcServicioIndioma3(oServicioNuevo.getcServicioIndioma3().toUpperCase());
		boolean correcto=servicioDao.isOperationCorrect(servicioDao.insertarServicio(oServicioNuevo));
		if(correcto)
		{
			oServicioNuevo=new CServicio();
			/*=Bindeamos la lista de servicios para ver el servicio insertado=*/
			/**Obtencion de las etiquetas de la base de datos**/
			servicioDao.asignarListaServicios(servicioDao.recuperarTodosServiciosBD());
			/**Asignacion de las etiquetas a la listaEtiquetas**/
			setListaServicios(servicioDao.getListaServicios());
			/*================================================================*/
			Clients.showNotification("El servicio se inserto correctamente",Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",2700);
		}else{
			Clients.showNotification("El servicio no se inserto",Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",2700);
		}
	}
	public boolean validoParaInsertar(Component comp)
	{
		boolean valido=true;
		if(!oServicioNuevo.isEscogioRestriccion())//no escogio ninguna restriccion
		{
			valido=false;
			Clients.showNotification("Debe de escoger alguna restriccion",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
		}else if(oServicioNuevo.getcServicioIndioma1().equals("")){
			valido=false;
			Clients.showNotification("Debe de existir un nombre de servicio",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
		}else if(oServicioNuevo.isSelectResNumeric() || oServicioNuevo.getcRestriccionYesNo()==1)
		{
			if(oServicioNuevo.isSelectResNumeric())
			{
				if(oServicioNuevo.getcRestriccionNum()==0)
				{
					valido=false;
					Clients.showNotification("Es necesario que ingrese las unidades/pasajero del servicio",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
				}
			}
			if(valido)
			{
				if(oServicioNuevo.getcUrlImg().equals(""))
				{
					valido=false;
					Clients.showNotification("El Servicio debe tener una imagen",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
				}else if(oServicioNuevo.getcDescripcionIdioma1().equals(""))
				{
					valido=false;
					Clients.showNotification("Debe de existir una descripcion del servicio",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
				}else if(oServicioNuevo.getnPrecioServicio().doubleValue()<0)
				{
					valido=false;
					Clients.showNotification("El precio del servicio no puede ser negativo",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
				}
			}
		}
		return valido;
	}
	@Command
	public void actualizarServicio(@BindingParam("servicio")CServicio servicio,@BindingParam("componente")Component comp)
	{	
		if(!validoParaActualizar(servicio,comp))
			return;
		servicio.setcServicioIndioma1(servicio.getcServicioIndioma1().toUpperCase());
		servicio.setcServicioIndioma2(servicio.getcServicioIndioma2().toUpperCase());
		servicio.setcServicioIndioma3(servicio.getcServicioIndioma3().toUpperCase());
		/**Actualizar datos de la etiqueta en la BD**/
		boolean correcto=servicioDao.isOperationCorrect(servicioDao.modificarServicio(servicio));
		if(correcto)
			Clients.showNotification("El Servicio se actualizo correctamente", Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",2700);
		else
			Clients.showNotification("El Servicio no se pudo actualizar", Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",2700);
		servicio.setEditable(false);
		refrescaFilaTemplate(servicio);
		
	}
	public boolean validoParaActualizar(CServicio servicio,Component comp)
	{
		boolean valido=true;
		
		if(servicio.getcServicioIndioma1().equals("")){
			valido=false;
			Clients.showNotification("Debe de existir un nombre de servicio",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
		}else if(servicio.isSelectResNumeric() || servicio.getcRestriccionYesNo()==1)
		{
			if(servicio.isSelectResNumeric())
			{
				if(servicio.getcRestriccionNum()==0)
				{
					valido=false;
					Clients.showNotification("Es necesario que ingrese las unidades/pasajero del servicio",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
				}else{
					servicio.setNameRestriccion("RESTRICCION NUMERICA: "+servicio.getcRestriccionNum()+" POR PASAJERO");
					BindUtils.postNotifyChange(null, null, servicio, "nameRestriccion");
				}
			}
			if(valido)
			{
				if(servicio.getcUrlImg().equals(""))
				{
					valido=false;
					Clients.showNotification("El Servicio debe tener una imagen",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
				}else if(servicio.getcDescripcionIdioma1().equals(""))
				{
					valido=false;
					Clients.showNotification("Debe de existir una descripcion del servicio",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
				}else if(servicio.getcDescripcionIdioma1().equals("Tiene Sub Servicios")){
					valido=false;
					Clients.showNotification("La descripcion debe tener un contenido acorde al servicio ofrecido",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
				}else if(servicio.getnPrecioServicio().doubleValue()<0)
				{
					valido=false;
					Clients.showNotification("El precio del servicio no puede ser negativo",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
				}
			}
		}
		return valido;
	}
	@GlobalCommand
	@NotifyChange({"listaServicios"})
	public void actualizarEstadoServicios()
	{
		listaServicios.clear();
		servicioDao.asignarListaServicios(servicioDao.recuperarTodosServiciosBD());
		setListaServicios(servicioDao.getListaServicios());
	}
	@Command
	public void Editar(@BindingParam("servicio") CServicio s ) 
	{
		s.setEditable(false);
		oServicioUpdate.setEditable(false);
		refrescaFilaTemplate(oServicioUpdate);
		oServicioUpdate=s;
		//le damos estado editable
		s.setEditable(!s.isEditable());	
		//lcs.setEditingStatus(!lcs.getEditingStatus());
		refrescaFilaTemplate(s);
   }
	@Command
	 public void Activar_Desactivar_servicio(@BindingParam("servicio")CServicio s,@BindingParam("texto")String texto)
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
	}
	@Command
	@NotifyChange({"oServicioNuevo"})
	public void selectRestricciones(@BindingParam("restriccion")String rest)
	{
		oServicioNuevo.setEscogioRestriccion(true);
		if(rest.equals("sub_servicios"))
		{
			oServicioNuevo.setbEstado(false);
			oServicioNuevo.setSelectResNumeric(false);
			oServicioNuevo.setSelectResYesNo(false);
			oServicioNuevo.setSelectResSubServ(true);
			oServicioNuevo.setcRestriccionNum(0);
			oServicioNuevo.setcIncremento(0);
			oServicioNuevo.setcRestriccionYesNo(0);
			oServicioNuevo.setnPrecioServicio(0);
			oServicioNuevo.setnPrecioServicio_text(df.format(0));
			oServicioNuevo.setcDescripcionIdioma1("Tiene Sub Servicios");
			oServicioNuevo.setcDescripcionIdioma2("Tiene Sub Servicios");
			oServicioNuevo.setcDescripcionIdioma3("Tiene Sub Servicios");
			oServicioNuevo.setDisabledConSubServicio(true);
			oServicioNuevo.setColor_disabled(oServicioNuevo.COLOR_DISABLED);
			oServicioNuevo.setcUrlImg("");
			BindUtils.postNotifyChange(null, null, oServicioNuevo,"cUrlImg");
		}else if(rest.equals("si_no")){
			oServicioNuevo.setbEstado(false);
			oServicioNuevo.setSelectResNumeric(false);
			oServicioNuevo.setSelectResYesNo(true);
			oServicioNuevo.setSelectResSubServ(false);
			oServicioNuevo.setcRestriccionNum(0);
			oServicioNuevo.setcRestriccionYesNo(1);
			oServicioNuevo.setcIncremento(0);
			oServicioNuevo.setDisabledConSubServicio(false);
			oServicioNuevo.setColor_disabled(oServicioNuevo.COLOR_NO_DISABLED);
		}else if(rest.equals("numerica")){
			oServicioNuevo.setbEstado(true);
			oServicioNuevo.setSelectResNumeric(true);
			oServicioNuevo.setSelectResYesNo(false);
			oServicioNuevo.setSelectResSubServ(false);
			oServicioNuevo.setcRestriccionYesNo(0);
			oServicioNuevo.setcIncremento(1);
			oServicioNuevo.setDisabledConSubServicio(false);
			oServicioNuevo.setColor_disabled(oServicioNuevo.COLOR_NO_DISABLED);
		}
	}
	@Command
	public void restNum_numUnidadesServ_pasajero(@BindingParam("uni_pax")int uni_pax)
	{
		oServicioNuevo.setcRestriccionNum(uni_pax);
	}
	@Command
	@NotifyChange({"oServicioNuevo"})
	public void changePrecios_nuevo(@BindingParam("precio")String precio,@BindingParam("componente")Component comp)
	{
		if(!isNumberDouble(precio))
		{
			oServicioNuevo.setnPrecioServicio_text(df.format(0));
			Clients.showNotification("Debe ser un numero de la forma ####.##",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start", 2700);
		}
		else
		{
			oServicioNuevo.setnPrecioServicio(Double.parseDouble(df.format(Double.parseDouble(precio))));
		}
	}
	@Command
	public void changePrecios_update(@BindingParam("precio")String precio,@BindingParam("componente")Component comp,@BindingParam("servicio")CServicio servicio)
	{
		if(!isNumberDouble(precio))
		{
			servicio.setnPrecioServicio_text(df.format(servicio.getnPrecioServicio().doubleValue()));
			Clients.showNotification("Ingrese valores numericos para poder modificar el precio",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start", 2700);
		}
		else
		{
			servicio.setnPrecioServicio(Double.parseDouble(df.format(Double.parseDouble(precio))));
		}
	}
	@Command
	public void cambioIdiomas(@BindingParam("idioma")String idIdioma,@BindingParam("servicio")CServicio servicio)
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
	public void cambiarRestriccion(@BindingParam("restriccion")String rest,@BindingParam("servicio")CServicio servicio)
	{
		oServicioNuevo.setEscogioRestriccion(true);
		if(rest.equals("sub_servicios"))
		{
			servicio.setbEstado(false);
			servicio.setcRestriccionNum(0);
			servicio.setcIncremento(0);
			servicio.setcRestriccionYesNo(0);
			servicio.setnPrecioServicio(0);
			servicio.setnPrecioServicio_text(df.format(0));
			servicio.setcDescripcionIdioma1("Tiene Sub Servicios");
			servicio.setcDescripcionIdioma2("Tiene Sub Servicios");
			servicio.setcDescripcionIdioma3("Tiene Sub Servicios");
			servicio.setDisabledConSubServicio(true);
			servicio.setColor_disabled(oServicioNuevo.COLOR_DISABLED);
			servicio.setColor_btn_activo(servicio.COLOR_TRANSPARENT);
			servicio.setColor_btn_desactivo(servicio.COLOR_DESACTIVO);
			servicio.setEstado_activo(false);
			servicio.setEstado_desactivo(true);
			servicio.setNameRestriccion("SUB SERVICIO");
			servicio.setSelectResSubServ(true);
			servicio.setSelectResNumeric(false);
			servicio.setSelectResYesNo(false);
			servicio.setcUrlImg("");
			BindUtils.postNotifyChange(null, null, oServicioNuevo,"cUrlImg");
		}else if(rest.equals("si_no")){
			servicio.setbEstado(true);
			servicio.setcRestriccionNum(0);
			servicio.setcRestriccionYesNo(1);
			servicio.setcIncremento(0);
			servicio.setDisabledConSubServicio(false);
			servicio.setColor_disabled(oServicioNuevo.COLOR_NO_DISABLED);
			servicio.setColor_btn_activo(servicio.COLOR_ACTIVO);
			servicio.setColor_btn_desactivo(servicio.COLOR_TRANSPARENT);
			servicio.setEstado_activo(true);
			servicio.setEstado_desactivo(false);
			servicio.setNameRestriccion("RESTRICCION YES/NO");
			servicio.setSelectResSubServ(false);
			servicio.setSelectResNumeric(false);
			servicio.setSelectResYesNo(true);
		}else if(rest.equals("numerica")){
			servicio.setbEstado(true);
			servicio.setcRestriccionYesNo(0);
			servicio.setcIncremento(1);
			servicio.setDisabledConSubServicio(false);
			servicio.setColor_disabled(oServicioNuevo.COLOR_NO_DISABLED);
			servicio.setColor_btn_activo(servicio.COLOR_ACTIVO);
			servicio.setColor_btn_desactivo(servicio.COLOR_TRANSPARENT);
			servicio.setEstado_activo(true);
			servicio.setEstado_desactivo(false);
			servicio.setSelectResSubServ(false);
			servicio.setSelectResNumeric(true);
			servicio.setSelectResYesNo(false);
		}
		BindUtils.postNotifyChange(null, null, servicio, "cDescripcionIdioma1");
		BindUtils.postNotifyChange(null, null, servicio, "cDescripcionIdioma2");
		BindUtils.postNotifyChange(null, null, servicio, "cDescripcionIdioma3");
		BindUtils.postNotifyChange(null, null, servicio, "bEstado");
		BindUtils.postNotifyChange(null, null, servicio, "selectResNumeric");
		BindUtils.postNotifyChange(null, null, servicio, "cRestriccionNum");
		BindUtils.postNotifyChange(null, null, servicio, "disabledConSubServicio");
		BindUtils.postNotifyChange(null, null, servicio, "color_disabled");
		BindUtils.postNotifyChange(null, null, servicio, "color_btn_activo");
		BindUtils.postNotifyChange(null, null, servicio, "color_btn_desactivo");
		BindUtils.postNotifyChange(null, null, servicio, "estado_activo");
		BindUtils.postNotifyChange(null, null, servicio, "estado_desactivo");
		BindUtils.postNotifyChange(null, null, servicio, "nameRestriccion");
		BindUtils.postNotifyChange(null, null, servicio, "selectRestNumeric");
		BindUtils.postNotifyChange(null, null, servicio, "selectRestYesNo");
		BindUtils.postNotifyChange(null, null, servicio, "selectRestSubServ");
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
				            asignarUrlImagenServicio(img.getName(),oServicioNuevo,false);
				            Clients.showNotification(img.getName()+" Se inserto",Clients.NOTIFICATION_TYPE_INFO,comp,"before_start",2700);
						} else {
							Messagebox.show(media+"Error", "Error", Messagebox.OK, Messagebox.ERROR);
								}
					}
			     });
	}
	@Command
	public void changeImagen(@BindingParam("componente")final Component comp,@BindingParam("servicio")final CServicio serv) {
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
				            asignarUrlImagenServicio(img.getName(),serv,false);
				            Clients.showNotification(img.getName()+" Se inserto",Clients.NOTIFICATION_TYPE_INFO,comp,"before_start",2700);
						} else {
							Messagebox.show(media+"Error", "Error", Messagebox.OK, Messagebox.ERROR);
								}
					}
			     });
	}
	public void asignarUrlImagenServicio(String url,CServicio servicio,boolean imgExist)
	{
		if(imgExist)
			servicio.setcUrlImg(url);
		else
			servicio.setcUrlImg("img/servicios/"+url);
		BindUtils.postNotifyChange(null, null, servicio,"cUrlImg");
	}
	public void refrescarSelect(CGaleriaImageExist4 galeria4)
	{
		BindUtils.postNotifyChange(null, null, galeria4, "galeria1");
		BindUtils.postNotifyChange(null, null, galeria4, "galeria2");
		BindUtils.postNotifyChange(null, null, galeria4, "galeria3");
		BindUtils.postNotifyChange(null, null, galeria4, "galeria4");
	}
	public void refrescaFilaTemplate(CServicio s)
	{
		BindUtils.postNotifyChange(null, null, s, "editable");
	}
}
