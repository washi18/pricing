package com.pricing.viewModel;

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
import com.pricing.model.CEtiqueta;
import com.pricing.model.CHotel;
import com.pricing.model.CServicio;
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
				}else if(oServicioNuevo.getnPrecioServicio().doubleValue()==0)
				{
					valido=false;
					Clients.showNotification("El precio del servicio no puede ser $ 0.00",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
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
				}else if(servicio.getnPrecioServicio().doubleValue()==0)
				{
					valido=false;
					Clients.showNotification("El precio del servicio no puede ser $ 0.00",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
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
				            asignarUrlImagenServicio(img.getName());
				            Clients.showNotification(img.getName()+" Se inserto",Clients.NOTIFICATION_TYPE_INFO,comp,"before_start",2700);
						} else {
							Messagebox.show(media+"Error", "Error", Messagebox.OK, Messagebox.ERROR);
								}
					}
			     });
	}
	public void asignarUrlImagenServicio(String url)
	{
		oServicioNuevo.setcUrlImg("/img/servicios/"+url);
		BindUtils.postNotifyChange(null, null, oServicioNuevo,"cUrlImg");
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
				            asignarUrlImagenUpdateServicio(img.getName(),serv);
				            Clients.showNotification(img.getName()+" Se inserto",Clients.NOTIFICATION_TYPE_INFO,comp,"before_start",2700);
						} else {
							Messagebox.show(media+"Error", "Error", Messagebox.OK, Messagebox.ERROR);
								}
					}
			     });
	}
	public void asignarUrlImagenUpdateServicio(String url,CServicio servicio)
	{
		servicio.setcUrlImg("/img/servicios/"+url);
		BindUtils.postNotifyChange(null, null, servicio,"cUrlImg");
	}
	public void refrescaFilaTemplate(CServicio s)
	{
		BindUtils.postNotifyChange(null, null, s, "editable");
	}
}
