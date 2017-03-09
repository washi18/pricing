package com.pricing.viewModel;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import com.pricing.dao.CDestinoDAO;
import com.pricing.dao.CGaleriaHotelDAO;
import com.pricing.dao.CHotelDAO;
import com.pricing.model.CCategoriaHotel;
import com.pricing.model.CDestino;
import com.pricing.model.CGaleriaHotel;
import com.pricing.model.CHotel;
import com.pricing.util.ScannUtil;

public class hotelesVM 
{
	//====================
	private DecimalFormat df;
	private DecimalFormatSymbols simbolos;
	//====================
	@Wire
	Div div_llenar_hoteles;
	/**=============**/
	/**==ATRIBUTOS==**/
	/**=============**/
	private CHotel oHotel;
	private CHotel oHotelUpdate;//Funciona como variable auxiliar
	private ArrayList<CHotel> listaHoteles;
	private CHotelDAO hotelDao;
	private CDestinoDAO destinoDao;
	private ArrayList<CDestino> listaDestinos;
	private ArrayList<CCategoriaHotel> listaCategoriasHotel;
	private ArrayList<CDestino> listaDestinosBusqueda;
	private ArrayList<CCategoriaHotel> listaCategoriasBusqueda;
	private String destinoBuscar;
	private String categoriaBuscar;
	private String hotelBuscar;
	/**=====================**/
	/**==GETTER AND SETTER==**/
	/**=====================**/
	public CHotel getoHotel() {
		return oHotel;
	}
	public void setoHotel(CHotel oHotel) {
		this.oHotel = oHotel;
	}
	public ArrayList<CHotel> getListaHoteles() {
		return listaHoteles;
	}
	public void setListaHoteles(ArrayList<CHotel> listaHoteles) {
		this.listaHoteles = listaHoteles;
	}
	public CHotelDAO getHotelDao() {
		return hotelDao;
	}
	public void setHotelDao(CHotelDAO hotelDao) {
		this.hotelDao = hotelDao;
	}
	public CHotel getoHotelUpdate() {
		return oHotelUpdate;
	}
	public void setoHotelUpdate(CHotel oHotelUpdate) {
		this.oHotelUpdate = oHotelUpdate;
	}
	public ArrayList<CDestino> getListaDestinos() {
		return listaDestinos;
	}
	public void setListaDestinos(ArrayList<CDestino> listaDestinos) {
		this.listaDestinos = listaDestinos;
	}
	public ArrayList<CCategoriaHotel> getListaCategoriasHotel() {
		return listaCategoriasHotel;
	}
	public void setListaCategoriasHotel(
			ArrayList<CCategoriaHotel> listaCategoriasHotel) {
		this.listaCategoriasHotel = listaCategoriasHotel;
	}
	public String getDestinoBuscar() {
		return destinoBuscar;
	}
	public void setDestinoBuscar(String destinoBuscar) {
		this.destinoBuscar = destinoBuscar;
	}
	public String getCategoriaBuscar() {
		return categoriaBuscar;
	}
	public void setCategoriaBuscar(String categoriaBuscar) {
		this.categoriaBuscar = categoriaBuscar;
	}
	public String getHotelBuscar() {
		return hotelBuscar;
	}
	public void setHotelBuscar(String hotelBuscar) {
		this.hotelBuscar = hotelBuscar;
	}
	
	public ArrayList<CDestino> getListaDestinosBusqueda() {
		return listaDestinosBusqueda;
	}
	public void setListaDestinosBusqueda(ArrayList<CDestino> listaDestinosBusqueda) {
		this.listaDestinosBusqueda = listaDestinosBusqueda;
	}
	public ArrayList<CCategoriaHotel> getListaCategoriasBusqueda() {
		return listaCategoriasBusqueda;
	}
	public void setListaCategoriasBusqueda(
			ArrayList<CCategoriaHotel> listaCategoriasBusqueda) {
		this.listaCategoriasBusqueda = listaCategoriasBusqueda;
	}
	/**===========**/
	/**==METODOS==**/
	/**===========**/
	/**
	 * Inicializa el view model de hoteles
	 */
	@Init
	public void inicializarVM()
	{
		/*******************************/
		simbolos= new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		df=new DecimalFormat("########0.00",simbolos);
		/*******************************/
		oHotel=new CHotel();
		oHotelUpdate=new CHotel();
		hotelDao=new CHotelDAO();
		destinoDao=new CDestinoDAO();
		listaDestinos=new ArrayList<CDestino>();
		listaHoteles=new ArrayList<CHotel>();
		listaCategoriasHotel=new ArrayList<CCategoriaHotel>();
		listaDestinosBusqueda=new ArrayList<CDestino>();
		listaCategoriasBusqueda=new ArrayList<CCategoriaHotel>();
		destinoBuscar="";
		categoriaBuscar="";
		hotelBuscar="";
		/*****************************/
		Encryptar encrip= new Encryptar();
//		System.out.println("Aqui esta la contraseņa desencriptada-->"+encrip.decrypt("cyS249O3OHZTsG0ww1rYrw=="));
		Execution exec = Executions.getCurrent();
		HttpSession ses = (HttpSession)Sessions.getCurrent().getNativeSession();
	    String user=(String)ses.getAttribute("usuario");
	    String pas=(String)ses.getAttribute("clave");
	    if(user!=null && pas!=null)
	    	recuperarHoteles();
	}
	public void recuperarHoteles()
	{
		/*Asignacion de hoteles*/
		hotelDao.asignarListaHoteles(hotelDao.recuperarHotelesBD());
		setListaHoteles(hotelDao.getListaHoteles());
		/*Asignacion de destinos*/
		destinoDao.asignarListaDestinos(destinoDao.recuperarListaTodosDestinosBD());
		setListaDestinos(destinoDao.getListaDestinos());
		/*Asignacion de categorioashoteles*/
		hotelDao.asignarListaCategoriashotel(hotelDao.recuperarCategoriasHotelBD());
		setListaCategoriasHotel(hotelDao.getListaCategorias());
		
		destinoDao.asignarListaDestinosBusqueda(destinoDao.recuperarListaTodosDestinosBD());
		setListaDestinosBusqueda(destinoDao.getListaDestinosBusqueda());
		
		hotelDao.asignarListaCategoriashotelBusqueda(hotelDao.recuperarCategoriasHotelBD());
		setListaCategoriasBusqueda(hotelDao.getListaCategoriasBusqueda());
	}
	@Command
	@NotifyChange("destinoBuscar")
	public void asignarDestino(@BindingParam("destino")String destino){
		this.destinoBuscar=destino;
	}
	@Command
	@NotifyChange("categoriaBuscar")
	public void asignarCategoria(@BindingParam("categoria")String categoria){
		this.categoriaBuscar=categoria;
	}
	
	@Command
	@NotifyChange({"listaHoteles","destinoBuscar","categoriaBuscar"})
	public void buscarHotelesxDestinoxCategoria(@BindingParam("nombreHotel")String buscarHotel,@BindingParam("destino")String buscarDestino,@BindingParam("categoria")String buscarCategoria){
		if(buscarDestino.equals("Todo los destinos")){
			setDestinoBuscar("");
		}else if(buscarCategoria.equals("Toda las categorias")){
			setCategoriaBuscar("");
		}
		hotelDao.asignarHotelesxDestinoxCategoriaHotel(hotelDao.recuperarHotelesxDestinoxCategoriaHotelBD(buscarHotel, buscarDestino, buscarCategoria));
		setListaHoteles(hotelDao.getListaHoteles());
	}
	/**
	 * Funcion que permite asignar la categoria
	 * al nuevo hotel a insertar en la BD
	 * @param categoria
	 */
	@Command
	public void selectCategoria(@BindingParam("categoria")String categoria)
	{
		if(categoria.equals("economico"))
			oHotel.setCategoriaHotelCod(1);
		else if(categoria.equals("turistico"))
			oHotel.setCategoriaHotelCod(2);
		else if(categoria.equals("turistico_superior"))
			oHotel.setCategoriaHotelCod(3);
		else if(categoria.equals("primera"))
			oHotel.setCategoriaHotelCod(4);
		else if(categoria.equals("primera_superior"))
			oHotel.setCategoriaHotelCod(5);
		else if(categoria.equals("lujo"))
			oHotel.setCategoriaHotelCod(6);
		else if(categoria.equals("lujo_superior"))
			oHotel.setCategoriaHotelCod(7);
	}
	@Command
	public void selectDestino(@BindingParam("codDestino")String codDestino)
	{
		oHotel.setnDestinoCod(Integer.parseInt(codDestino));
	}
	@Command
	public void selectDestino_update(@BindingParam("codDestino")String codDestino,@BindingParam("hotel")CHotel hotel)
	{
		hotel.setnDestinoCod(Integer.parseInt(codDestino));
	}
	@Command
	@NotifyChange({"oHotel","listaHoteles"})
	public void InsertarHotel(@BindingParam("componente")Component componente)
	{
		if(!validoParaInsertar(componente))
			return;
		/**Una vez validado los datos necesarios se procede a insertar el nuevo Hotel**/
		List listaResultInsert=hotelDao.insertarHotel(oHotel);
		Map row=(Map)listaResultInsert.get(0);
		boolean correcto=row.get("resultado").toString().equals("correcto");		
		if(correcto)
		{ 
			if(!oHotel.getListaImagenes().isEmpty())
			{
				for(CGaleriaHotel imagenes:oHotel.getListaImagenes())
				{
					imagenes.setnHotelCod((int)row.get("codhotel"));
					CGaleriaHotelDAO galeriaHotelDao=new CGaleriaHotelDAO();
					correcto=galeriaHotelDao.isOperationCorrect(galeriaHotelDao.insertarImagenHotel(imagenes));
				}
			}
			oHotel=new CHotel();
			/*para recuperar inmediatamente el dato insertado*/
			listaHoteles.clear();
			hotelDao.asignarListaHoteles(hotelDao.recuperarHotelesBD());
			setListaHoteles(hotelDao.getListaHoteles());
			/*************************************************/
			Clients.showNotification("El Nuevo Hotel fue insertado correctamente", Clients.NOTIFICATION_TYPE_INFO, componente,"before_start",2700);
		}
		else
			Clients.showNotification("El Nuevo Hotel no fue insertado", Clients.NOTIFICATION_TYPE_INFO, componente,"before_start",2700);
	}
	@GlobalCommand
	@NotifyChange({"listaDestinos","listaHoteles"})
	public void actualizarDestinoInsertado()
	{
		listaDestinos.clear();
		listaHoteles.clear();
		/*Asignacion de destinos*/
		destinoDao.asignarListaDestinos(destinoDao.recuperarListaTodosDestinosBD());
		setListaDestinos(destinoDao.getListaDestinos());
		/*Asignacion de hoteles*/
		hotelDao.asignarListaHoteles(hotelDao.recuperarHotelesBD());
		setListaHoteles(hotelDao.getListaHoteles());
	}
	
	@Command
	@NotifyChange({"",""})
	public void buscarHoteles(@BindingParam("hotel")String hotel,@BindingParam("destino")String destino,@BindingParam("categoria")String Categoria){
		
	}
	public boolean validoParaInsertar(Component componente)
	{
		oHotel.setcHotel(oHotel.getcHotel().toUpperCase());
		boolean valido=true;
		/**Empezamos realizando las validaciones respectivas**/
		if(oHotel.getnDestinoCod()==0)
		{
			Clients.showNotification("Se tiene que elegir un destino para el hotel", Clients.NOTIFICATION_TYPE_ERROR, componente,"before_start",2700);
			valido=false;
		}
		if(valido)
		{
			if(oHotel.getcHotel().equals(""))//Nombre del Hotel
			{
				Clients.showNotification("El nuevo hotel no tiene nombre", Clients.NOTIFICATION_TYPE_ERROR, componente,"before_start",2700);
				valido=false;
			}else if(oHotel.getCategoriaHotelCod()==0)
			{
				Clients.showNotification("Debe seleccionar una categoria para el hotel", Clients.NOTIFICATION_TYPE_ERROR, componente,"before_start",2700);
				valido=false;
			}else if(oHotel.getnPrecioSimple().doubleValue()==0)
			{
				Clients.showNotification("El precio de una habitacion simple no puede ser $ 0.00", Clients.NOTIFICATION_TYPE_ERROR, componente,"before_start",2700);
				valido=false;
			}else if(oHotel.getnPrecioDoble().doubleValue()==0)
			{
				Clients.showNotification("El precio de una habitacion doble no puede ser $ 0.00", Clients.NOTIFICATION_TYPE_ERROR, componente,"before_start",2700);
				valido=false;
			}else if(oHotel.getnPrecioTriple().doubleValue()==0)
			{
				Clients.showNotification("El precio de una habitacion Triple no puede ser $ 0.00", Clients.NOTIFICATION_TYPE_ERROR, componente,"before_start",2700);
				valido=false;
			}
		}
		return valido;
	}
	@Command
	public void actualizarHotel(@BindingParam("hotel")CHotel hotel,@BindingParam("componente")Component comp)
	{	
		if(!validoPoderActualizar(hotel,comp))
			return;
		/**Actualizar datos de la etiqueta en la BD**/
		boolean correcto=hotelDao.isOperationCorrect(hotelDao.modificarHotel(hotel));
		if(correcto)
		{
			if(!hotel.getListaImagenes().isEmpty())
			{
				for(CGaleriaHotel galeria:hotel.getListaImagenes())
				{
					if(galeria.getnHotelCod()==0)
					{
						CGaleriaHotelDAO galeriaHotelDao=new CGaleriaHotelDAO();
						galeria.setnHotelCod(hotel.getnHotelCod());
						correcto=galeriaHotelDao.isOperationCorrect(galeriaHotelDao.insertarImagenHotel(galeria));
					}
				}
			}
			Clients.showNotification("El Hotel se actualizo correctamente", Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",2700);
		}
		else
			Clients.showNotification("El Hotel no se pudo actualizar", Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",2700);
		hotel.setEditable(false);
		refrescaFilaTemplate(hotel);
	}
	public boolean validoPoderActualizar(CHotel hotel,Component comp)
	{
		hotel.setcHotel(hotel.getcHotel().toUpperCase());
		boolean valido=true;
		if(hotel.getcHotel().equals(""))
		{
			valido=false;
			Clients.showNotification("El hotel debe tener siempre un nombre",Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",2700);
		}else if(hotel.getCategoriaHotelCod()==0)
		{
			Clients.showNotification("Debe seleccionar una categoria para el hotel", Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
			valido=false;
		}else if(hotel.getnPrecioSimple().doubleValue()==0)
		{
			Clients.showNotification("El precio de una habitacion simple no puede ser $ 0.00", Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
			valido=false;
		}else if(hotel.getnPrecioDoble().doubleValue()==0)
		{
			Clients.showNotification("El precio de una habitacion doble no puede ser $ 0.00", Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
			valido=false;
		}else if(hotel.getnPrecioTriple().doubleValue()==0)
		{
			Clients.showNotification("El precio de una habitacion Triple no puede ser $ 0.00", Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
			valido=false;
		}
		return valido;
	}
	@Command
	public void cerrarEditorDescripcion(@BindingParam("chotel")CHotel hotel)
	{
		hotel.setAbrirEditor(false);
		BindUtils.postNotifyChange(null, null, hotel, "abrirEditor");
	}
	@Command
	public void abrirEditorDescripcion(@BindingParam("chotel")CHotel hotel)
	{
		hotel.setAbrirEditor(true);
		BindUtils.postNotifyChange(null, null, hotel, "abrirEditor");
	}
	/**
	 * Funcion que permite Editar un hotel para actualizarlo
	 * desactivando previamente su su funcion editable del 
	 * anterior hotel editable "oHotelUpdate"
	 * @param h: Es el hotel que se va a actualizar
	 */
	@Command
	public void Editar(@BindingParam("hotel") CHotel h ) 
	{
		div_llenar_hoteles.setVisible(false);
		afterCompose(div_llenar_hoteles);
		//oHotelUpdate viene a ser una variable auxiliar
		//que almacena el hotel previo editado y actualizado
		//para luego almacenar el hotel seleccionado para editar
		oHotelUpdate.setEditable(false);
		refrescaFilaTemplate(oHotelUpdate);
		oHotelUpdate=h;
		//le damos estado editable
		h.setEditable(true);	
		//lcs.setEditingStatus(!lcs.getEditingStatus());
		refrescaFilaTemplate(h);
   }
	@Command
	 public void Activar_Desactivar(@BindingParam("hotel")CHotel h,@BindingParam("texto")String texto)
	{
		if(texto.equals("activar"))
		{
			h.setColor_btn_activo(h.COLOR_ACTIVO);
			h.setColor_btn_desactivo(h.COLOR_TRANSPARENT);
			h.setbEstado(true);
		}else{
			h.setColor_btn_activo(h.COLOR_TRANSPARENT);
			h.setColor_btn_desactivo(h.COLOR_DESACTIVO);
			h.setbEstado(false);
		}
		BindUtils.postNotifyChange(null, null, h,"color_btn_activo");
		BindUtils.postNotifyChange(null, null, h,"color_btn_desactivo");
	}
	@Command
	public void cambioIdiomas(@BindingParam("idioma")String idIdioma,@BindingParam("hotel")CHotel hotel)
	{
		if(idIdioma.equals("Espanol"))
		{
				hotel.setVisibleEspanol(true);
				hotel.setVisibleIngles(false);
				hotel.setVisiblePortugues(false);
		}
		else if(idIdioma.equals("Ingles"))
		{
				hotel.setVisibleEspanol(false);
				hotel.setVisibleIngles(true);
				hotel.setVisiblePortugues(false);
		}
		else if(idIdioma.equals("Portugues"))
		{
				hotel.setVisibleEspanol(false);
				hotel.setVisibleIngles(false);
				hotel.setVisiblePortugues(true);
		}
		BindUtils.postNotifyChange(null, null, hotel, "visibleEspanol");
		BindUtils.postNotifyChange(null, null, hotel, "visibleIngles");
		BindUtils.postNotifyChange(null, null, hotel, "visiblePortugues");
	}
	@Command
	public void cambiarCategoriaHotel(@BindingParam("categoria")String categoria,@BindingParam("hotel")CHotel oHotel)
	{
		if(categoria.equals("economico"))
			oHotel.setCategoriaHotelCod(1);
		else if(categoria.equals("turistico"))
			oHotel.setCategoriaHotelCod(2);
		else if(categoria.equals("turistico_superior"))
			oHotel.setCategoriaHotelCod(3);
		else if(categoria.equals("primera"))
			oHotel.setCategoriaHotelCod(4);
		else if(categoria.equals("primera_superior"))
			oHotel.setCategoriaHotelCod(5);
		else if(categoria.equals("lujo"))
			oHotel.setCategoriaHotelCod(6);
		else if(categoria.equals("lujo_superior"))
			oHotel.setCategoriaHotelCod(7);
	}
	@Command
	@NotifyChange({"oHotel"})
	public void changePrecios_nuevo(@BindingParam("precio")String precio,@BindingParam("hab")String hab,@BindingParam("componente")Component componente)
	{
		if(!isNumberDouble(precio))
		{
			if(hab.equals("simple"))
				oHotel.setnPrecioSimple_text(df.format(0));
			if(hab.equals("doble"))
				oHotel.setnPrecioDoble_text(df.format(0));
			if(hab.equals("triple"))
				oHotel.setnPrecioTriple_text(df.format(0));
			if(hab.equals("camaAd"))
				oHotel.setnPrecioCamaAdicional_text(df.format(0));
			Clients.showNotification("Debe ser un numero de la forma ####.##",Clients.NOTIFICATION_TYPE_ERROR, componente,"before_start", 2700);
		}
		else
		{
			if(hab.equals("simple"))
				oHotel.setnPrecioSimple(Double.parseDouble(df.format(Double.parseDouble(precio))));
			if(hab.equals("doble"))
				oHotel.setnPrecioDoble(Double.parseDouble(df.format(Double.parseDouble(precio))));
			if(hab.equals("triple"))
				oHotel.setnPrecioTriple(Double.parseDouble(df.format(Double.parseDouble(precio))));
			if(hab.equals("camaAd"))
				oHotel.setnPrecioCamaAdicional(Double.parseDouble(df.format(Double.parseDouble(precio))));
		}
	}
	@Command
	public void changePrecios_update(@BindingParam("precio")String precio,@BindingParam("hab")String hab,@BindingParam("componente")Component comp,@BindingParam("hotel")CHotel hotel)
	{
		if(!isNumberDouble(precio))
		{
			if(hab.equals("simple"))
				hotel.setnPrecioSimple_text(df.format(hotel.getnPrecioSimple().doubleValue()));
			if(hab.equals("doble"))
				hotel.setnPrecioDoble_text(df.format(hotel.getnPrecioDoble().doubleValue()));
			if(hab.equals("triple"))
				hotel.setnPrecioTriple_text(df.format(hotel.getnPrecioTriple().doubleValue()));
			if(hab.equals("camaAd"))
				hotel.setnPrecioCamaAdicional_text(df.format(hotel.getnPrecioCamaAdicional().doubleValue()));
			Clients.showNotification("Para que se efectue el cambio debe digitar valores numericos",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start", 2700);
		}
		else
		{
			if(hab.equals("simple"))
				hotel.setnPrecioSimple(Double.parseDouble(df.format(Double.parseDouble(precio))));
			if(hab.equals("doble"))
				hotel.setnPrecioDoble(Double.parseDouble(df.format(Double.parseDouble(precio))));
			if(hab.equals("triple"))
				hotel.setnPrecioTriple(Double.parseDouble(df.format(Double.parseDouble(precio))));
			if(hab.equals("camaAd"))
				hotel.setnPrecioCamaAdicional(Double.parseDouble(df.format(Double.parseDouble(precio))));
		}
		BindUtils.postNotifyChange(null, null, hotel, "nPrecioSimple_text");
		BindUtils.postNotifyChange(null, null, hotel, "nPrecioDoble_text");
		BindUtils.postNotifyChange(null, null, hotel, "nPrecioTriple_text");
		BindUtils.postNotifyChange(null, null, hotel, "nPrecioCamaAdicional_text");
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
	public void invocaImagenesSubidas()
	{
		Window win_imagenes=(Window)Executions.createComponents("/imagenesHotel.zul", null, null);
		win_imagenes.doModal();
	}
	@Command
	public void uploadImagenes(@BindingParam("cHotel")final CHotel hotel,@BindingParam("tipoImagen")final int tipoImagen,@BindingParam("componente")final Component comp) {
       System.out.print("entre a subir imagen");
       Fileupload.get(100,new EventListener<UploadEvent>(){
			public void onEvent(UploadEvent event) {
			org.zkoss.util.media.Media[] listaMedias = event.getMedias();
			for(Media media:listaMedias)
        	{
        		if(media instanceof org.zkoss.image.Image)
        		{
        			org.zkoss.image.Image img = (org.zkoss.image.Image) media;
        			//Con este metodo(uploadFile) de clase guardo la imagen en la ruta del servidor
		            boolean b=ScannUtil.uploadFileHoteles(img);
		            //================================
		            //Una vez creado el nuevo nombre de archivo de imagen se procede a cambiar el nombre
		            String urlImagen=ScannUtil.getPathImagenHoteles()+img.getName();
		            asignarRutaImagenHotel(img.getName(),tipoImagen,hotel);
		            Clients.showNotification(img.getName()+" Se subio al servidor.",Clients.NOTIFICATION_TYPE_INFO,comp,"before_start",2700);
        		} else {
                    Messagebox.show("No es una imagen: " + media, "Error",
                            Messagebox.OK, Messagebox.ERROR);
                    break; //not to show too many errors
                }
        	}
		  }
       });
    }
	public void asignarRutaImagenHotel(String nombreImagen,int tipoImagen,CHotel hotel)
	{
		if(estaEnLaListaImagenes("img/hoteles/"+nombreImagen,hotel))
			return;
		CGaleriaHotel oGaleriaHotel=new CGaleriaHotel();
		oGaleriaHotel.setnTipoImagen(tipoImagen);
		oGaleriaHotel.setcRutaImagen("img/hoteles/"+nombreImagen);
		oGaleriaHotel.setVisible(true);
		if(tipoImagen==1 &&
				(hotel.getcFoto1().equals("img/hoteles/hotelxdefecto.jpg")||
				hotel.getcFoto2().equals("img/hoteles/hotelxdefecto.jpg")||
				hotel.getcFoto3().equals("img/hoteles/hotelxdefecto.jpg")||
				hotel.getcFoto4().equals("img/hoteles/hotelxdefecto.jpg")||
				hotel.getcFoto5().equals("img/hoteles/hotelxdefecto.jpg")))
		{
			System.out.println("Soy la imagen del hotel: 1");
			oGaleriaHotel.setbEstado(true);
			oGaleriaHotel.setStyle_Select("div_content_imageHotel_selected");
			if(hotel.getcFoto1().equals("img/hoteles/hotelxdefecto.jpg"))
				hotel.setcFoto1("img/hoteles/"+nombreImagen);
			else if(hotel.getcFoto2().equals("img/hoteles/hotelxdefecto.jpg"))
				hotel.setcFoto2("img/hoteles/"+nombreImagen);
			else if(hotel.getcFoto3().equals("img/hoteles/hotelxdefecto.jpg"))
				hotel.setcFoto3("img/hoteles/"+nombreImagen);
			else if(hotel.getcFoto4().equals("img/hoteles/hotelxdefecto.jpg"))
				hotel.setcFoto4("img/hoteles/"+nombreImagen);
			else if(hotel.getcFoto5().equals("img/hoteles/hotelxdefecto.jpg"))
				hotel.setcFoto5("img/hoteles/"+nombreImagen);
		}else if(tipoImagen==2 && hotel.getcImagenUbicacion().equals(""))
		{
			System.out.println("Soy la ubicacion del hotel: 2");
			oGaleriaHotel.setbEstado(true);
			oGaleriaHotel.setStyle_Select("div_content_imageHotel_selected");
			hotel.setcImagenUbicacion("img/hoteles/"+nombreImagen);
		}
		System.out.println("Estoy en estado: "+oGaleriaHotel.isbEstado());
		hotel.getListaImagenes().add(oGaleriaHotel);
	}
	public boolean estaEnLaListaImagenes(String nameImagen,CHotel hotel)
	{
		boolean esta=false;
		for(CGaleriaHotel galeria:hotel.getListaImagenes())
		{
			if(nameImagen.equals(galeria.getcRutaImagen()))
			{
				esta=true;
				break;
			}
		}
		return esta;
	}
	public void refrescaFilaTemplate(CHotel h)
	{
		BindUtils.postNotifyChange(null, null, h, "editable");
	}
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view)
	{
		Selectors.wireComponents(view, this, false);
	}
}
