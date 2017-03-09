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
import org.zkoss.zul.Div;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Image;
import org.zkoss.zul.Messagebox;

import pe.com.erp.crypto.Encryptar;

import com.pricing.dao.CServicioDAO;
import com.pricing.model.CHotel;
import com.pricing.model.CServicio;
import com.pricing.model.CSubServicio;
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
		/*****************************/
		Encryptar encrip= new Encryptar();
//		System.out.println("Aqui esta la contraseña desencriptada-->"+encrip.decrypt("cyS249O3OHZTsG0ww1rYrw=="));
		Execution exec = Executions.getCurrent();
		HttpSession ses = (HttpSession)Sessions.getCurrent().getNativeSession();
	    String user=(String)ses.getAttribute("usuario");
	    String pas=(String)ses.getAttribute("clave");
	    if(user!=null && pas!=null)
	    	recuperarSubServicios();
	}
	public void recuperarSubServicios()
	{
		/**Obtencion de las etiquetas de la base de datos**/
		servicioDao.asignarListaSubServicios(servicioDao.recuperarTodosSubServiciosBD());
		servicioDao.asignarListaServicios(servicioDao.recuperarServiciosconSubServiciosBD());
		/**Asignacion de las etiquetas a la listaEtiquetas**/
		setListaSubServicios(servicioDao.getListaSubServicios());
		setListaServiciosNew(servicioDao.getListaServicios());
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
		}else if(oSubServicioNew.getnPrecioServicio().doubleValue()==0)//los precios tbn puenden ser negativos ya que pueden ser un descuento
		{
			Clients.showNotification("El precio de un sub servicio no puede ser $ 0.00", Clients.NOTIFICATION_TYPE_ERROR, componente,"before_start",2700);
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
		}else if(subServicio.getnPrecioServicio().doubleValue()==0)//los precios tbn puenden ser negativos ya que pueden ser un descuento
		{
			Clients.showNotification("El precio de un sub servicio no puede ser $ 0.00", Clients.NOTIFICATION_TYPE_ERROR, componente,"before_start",2700);
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
				            asignarUrlImagenSubServicio(img.getName());
				            Clients.showNotification(img.getName()+" Se inserto",Clients.NOTIFICATION_TYPE_INFO,comp,"before_start",2700);

						} else {
							Messagebox.show(media+"Error", "Error", Messagebox.OK, Messagebox.ERROR);
								}
					}
			     });
	}
	public void asignarUrlImagenSubServicio(String url)
	{
		System.out.println("==>:::"+url);
		oSubServicioNew.setcUrlImg("/img/servicios/"+url);
		BindUtils.postNotifyChange(null, null, oSubServicioNew,"cUrlImg");
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
				            asignarUrlImagenSubServicio_update(img.getName(),subServ);
				            Clients.showNotification(img.getName()+" Se cambio",Clients.NOTIFICATION_TYPE_INFO,comp,"before_start",2700);

						} else {
							Messagebox.show(media+"Error", "Error", Messagebox.OK, Messagebox.ERROR);
								}
					}
			     });
	}
	public void asignarUrlImagenSubServicio_update(String url,CSubServicio subServ)
	{
		System.out.println("==>:::"+url);
		subServ.setcUrlImg("/img/servicios/"+url);
		BindUtils.postNotifyChange(null, null, subServ,"cUrlImg");
	}
	public void refrescaFilaTemplate(CSubServicio s)
	{
		BindUtils.postNotifyChange(null, null, s, "editable");
	}
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view)
	{
		Selectors.wireComponents(view, this, false);
	}
}
