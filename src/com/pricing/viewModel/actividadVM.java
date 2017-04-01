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
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Messagebox;

import pe.com.erp.crypto.Encryptar;

import com.pricing.dao.CActividadDAO;
import com.pricing.dao.CServicioDAO;
import com.pricing.model.CActividad;
import com.pricing.model.CServicio;
import com.pricing.util.ScannUtil;

public class actividadVM 
{
	//====================
			private DecimalFormat df;
			private DecimalFormatSymbols simbolos;
		//====================
		/**
		 * ATRIBUTOS
		 */
		private CActividad oActividadNuevo;
		private CActividad oActividadUpdate;
		private CActividadDAO actividadDao;
		private ArrayList<CActividad> listaActividades;
		/**
		 * GETTER AND SETTER
		 */
		public CActividad getoActividadNuevo() {
			return oActividadNuevo;
		}
		public void setoActividadNuevo(CActividad oActividadNuevo) {
			this.oActividadNuevo = oActividadNuevo;
		}
		public ArrayList<CActividad> getListaActividades() {
			return listaActividades;
		}
		public void setListaActividades(ArrayList<CActividad> listaActividades) {
			this.listaActividades = listaActividades;
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
			oActividadNuevo=new CActividad();
			oActividadUpdate=new CActividad();
			actividadDao=new CActividadDAO();
			listaActividades=new ArrayList<CActividad>();
			/*****************************/
			Encryptar encrip= new Encryptar();
//			System.out.println("Aqui esta la contraseña desencriptada-->"+encrip.decrypt("cyS249O3OHZTsG0ww1rYrw=="));
			
		}
		@GlobalCommand
		public void recuperarActividades()
		{
			HttpSession ses = (HttpSession)Sessions.getCurrent().getNativeSession();
		    String user=(String)ses.getAttribute("usuario");
		    String pas=(String)ses.getAttribute("clave");
		    if(user!=null && pas!=null)
		    {
		    	/**Obtencion de las etiquetas de la base de datos**/
				actividadDao.asignarListaActividades(actividadDao.recuperarTodasActividadesBD());
				/**Asignacion de las etiquetas a la listaEtiquetas**/
				setListaActividades(actividadDao.getListaActividades());
		    }
		    BindUtils.postNotifyChange(null, null, this, "listaActividades");
		}
		@Command
		public void buscarActividades(@BindingParam("nombre")String nombre){
			CActividadDAO actividadDao=new CActividadDAO();
			actividadDao.asignarListaActividades(actividadDao.buscarActividadesBD(nombre));
			setListaActividades(actividadDao.getListaActividades());
			BindUtils.postNotifyChange(null, null, this, "listaActividades");
		}
		@Command
		@NotifyChange({"oActividadNuevo","listaActividades"})
		public void insertarActividad(@BindingParam("componente")Component comp)
		{
			oActividadNuevo.setcActividadIdioma1(oActividadNuevo.getcActividadIdioma1().toUpperCase());
			oActividadNuevo.setcActividadIdioma2(oActividadNuevo.getcActividadIdioma2().toUpperCase());
			oActividadNuevo.setcActividadIdioma3(oActividadNuevo.getcActividadIdioma3().toUpperCase());
			oActividadNuevo.setcActividadIdioma4(oActividadNuevo.getcActividadIdioma4().toUpperCase());
			oActividadNuevo.setcActividadIdioma5(oActividadNuevo.getcActividadIdioma5().toUpperCase());
			if(!validoParaInsertar(comp))
				return;
			boolean correcto=actividadDao.isCorrectOperation(actividadDao.insertarActividadBD(oActividadNuevo));
			if(correcto)
			{
				oActividadNuevo=new CActividad();
				/*=Bindeamos la lista de actividades para ver la actividad insertada=*/
				/**Obtencion de las etiquetas de la base de datos**/
				actividadDao.asignarListaActividades(actividadDao.recuperarTodasActividadesBD());
				/**Asignacion de las etiquetas a la listaEtiquetas**/
				setListaActividades(actividadDao.getListaActividades());
				/*================================================================*/
				Clients.showNotification("La Actividad se inserto correctamente",Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",2700);
			}else{
				Clients.showNotification("La Actividad no se inserto",Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",2700);
			}
		}
		public boolean validoParaInsertar(Component comp)
		{
			boolean valido=true;
			System.out.println(oActividadNuevo.getcDescripcionIdioma1());
			if(oActividadNuevo.getcActividadIdioma1().equals("")){
				valido=false;
				Clients.showNotification("Debe de existir un nombre para la actividad",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
			}else if(oActividadNuevo.getcDescripcionIdioma1().equals(""))
					{
						valido=false;
						Clients.showNotification("La Actividad debe de tener una descripcion",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
					}else if(oActividadNuevo.getnPrecioActividad().doubleValue()==0)
					{
						valido=false;
						Clients.showNotification("El precio de la actividad no puede ser $ 0.00",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
					}
			return valido;
		}
		@Command
		public void actualizarActividad(@BindingParam("actividad")CActividad actividad,@BindingParam("componente")Component comp)
		{	
			if(!validoParaActualizar(actividad,comp))
				return;
			/**Actualizar datos de la etiqueta en la BD**/
			boolean correcto=actividadDao.isCorrectOperation(actividadDao.actualizarActividadBD(actividad));
			if(correcto)
				Clients.showNotification("La Actividad se actualizo correctamente", Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",2700);
			else
				Clients.showNotification("La Actividad no se pudo actualizar", Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",2700);
			actividad.setEditable(false);
			refrescaFilaTemplate(actividad);
			
		}
		public boolean validoParaActualizar(CActividad actividad,Component comp)
		{
			boolean valido=true;
			if(actividad.getcActividadIdioma1().equals("")){
				valido=false;
				Clients.showNotification("Debe de existir un nombre para la actividad",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
			}else if(actividad.getcDescripcionIdioma1().equals(""))
					{
						valido=false;
						Clients.showNotification("La Actividad debe de tener una descripcion",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
					}else if(actividad.getnPrecioActividad().doubleValue()==0)
					{
						valido=false;
						Clients.showNotification("El precio de la actividad no puede ser $ 0.00",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
					}
			return valido;
		}
		@Command
		public void Editar(@BindingParam("actividad") CActividad a ) 
		{
			a.setEditable(false);
			oActividadUpdate.setEditable(false);
			refrescaFilaTemplate(oActividadUpdate);
			oActividadUpdate=a;
			//le damos estado editable
			a.setEditable(!a.isEditable());	
			//lcs.setEditingStatus(!lcs.getEditingStatus());
			refrescaFilaTemplate(a);
	   }
		@Command
		 public void Activar_Desactivar_actividad(@BindingParam("actividad")CActividad a,@BindingParam("texto")String texto)
		{
			if(texto.equals("activar"))
			{
				a.setColor_btn_activo(a.COLOR_ACTIVO);
				a.setColor_btn_desactivo(a.COLOR_TRANSPARENT);
				a.setbEstado(true);
			}else{
				a.setColor_btn_activo(a.COLOR_TRANSPARENT);
				a.setColor_btn_desactivo(a.COLOR_DESACTIVO);
				a.setbEstado(false);
			}
			BindUtils.postNotifyChange(null, null, a,"color_btn_activo");
			BindUtils.postNotifyChange(null, null, a,"color_btn_desactivo");
		}
		@Command
		@NotifyChange({"oActividadNuevo"})
		public void changePrecios_nuevo(@BindingParam("precio")String precio,@BindingParam("componente")Component comp)
		{
			if(!isNumberDouble(precio))
			{
				oActividadNuevo.setnPrecioActividad_text(df.format(0));
				Clients.showNotification("Debe ser un numero de la forma ####.##",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start", 2700);
			}
			else
			{
				oActividadNuevo.setnPrecioActividad(Double.parseDouble(df.format(Double.parseDouble(precio))));
			}
		}
		@Command
		public void changePrecios_update(@BindingParam("precio")String precio,@BindingParam("componente")Component comp,@BindingParam("actividad")CActividad actividad)
		{
			if(!isNumberDouble(precio))
			{
				actividad.setnPrecioActividad_text(df.format(actividad.getnPrecioActividad().doubleValue()));
				Clients.showNotification("Ingrese valores numericos para poder modificar el precio",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start", 2700);
			}
			else
			{
				actividad.setnPrecioActividad(Double.parseDouble(df.format(Double.parseDouble(precio))));
			}
		}
		@Command
		public void cambioIdiomas(@BindingParam("idioma")String idIdioma,@BindingParam("actividad")CActividad actividad)
		{
			if(idIdioma.equals("Espanol"))
			{
					actividad.setVisibleEspanol(true);
					actividad.setVisibleIngles(false);
					actividad.setVisiblePortugues(false);
			}
			else if(idIdioma.equals("Ingles"))
			{
					actividad.setVisibleEspanol(false);
					actividad.setVisibleIngles(true);
					actividad.setVisiblePortugues(false);
			}
			else if(idIdioma.equals("Portugues"))
			{
					actividad.setVisibleEspanol(false);
					actividad.setVisibleIngles(false);
					actividad.setVisiblePortugues(true);
			}
			BindUtils.postNotifyChange(null, null, actividad, "visibleEspanol");
			BindUtils.postNotifyChange(null, null, actividad, "visibleIngles");
			BindUtils.postNotifyChange(null, null, actividad, "visiblePortugues");
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
		public void refrescaFilaTemplate(CActividad a)
		{
			BindUtils.postNotifyChange(null, null, a, "editable");
		}
}
