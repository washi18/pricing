package com.pricing.viewModel;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.SignatureException;
import java.sql.Timestamp;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.DecimalFormat;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bouncycastle.pqc.math.linearalgebra.BigIntUtils;
import org.springframework.context.annotation.Bean;
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
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.A;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Html;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Space;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;
import com.lowagie.text.DocumentException;
import com.pricing.MasterDiners.Signature;
import com.pricing.dao.CActividadDAO;
import com.pricing.dao.CConfigUrlDAO;
import com.pricing.dao.CCuponDAO;
import com.pricing.dao.CDestinoDAO;
import com.pricing.dao.CEtiquetaDAO;
import com.pricing.dao.CFechaAlternaDAO;
import com.pricing.dao.CGeneraOrdenRegDAO;
import com.pricing.dao.CHotelDAO;
import com.pricing.dao.CImpuestoDAO;
import com.pricing.dao.CInterfazDAO;
import com.pricing.dao.CPaisDAO;
import com.pricing.dao.CPaqueteActividadDAO;
import com.pricing.dao.CPaqueteCategoriaHotelDAO;
import com.pricing.dao.CPaqueteDAO;
import com.pricing.dao.CPaqueteDestinoDAO;
import com.pricing.dao.CPaqueteServicioDAO;
import com.pricing.dao.CPasajeroDAO;
import com.pricing.dao.CReservaDAO;
import com.pricing.dao.CReservaPaqueteActividadDAO;
import com.pricing.dao.CReservaPaqueteCategoriaHotelDAO;
import com.pricing.dao.CReservaPaqueteDAO;
import com.pricing.dao.CReservaPaqueteServicioDAO;
import com.pricing.dao.CServicioDAO;
import com.pricing.dao.ConfAltoNivelDAO;
import com.pricing.extras.QRCode;
import com.pricing.model.CActividad;
import com.pricing.model.CCategoriaConHoteles;
import com.pricing.model.CCupon;
import com.pricing.model.CDestino;
import com.pricing.model.CDestinoConHoteles;
import com.pricing.model.CEtiqueta;
import com.pricing.model.CGeneraOrdenReg;
import com.pricing.model.CHotel;
import com.pricing.model.CImpuesto;
import com.pricing.model.CInterfaz;
import com.pricing.model.CPagos;
import com.pricing.model.CPais;
import com.pricing.model.CPaquete;
import com.pricing.model.CPaqueteActividad;
import com.pricing.model.CPaqueteCategoriaHotel;
import com.pricing.model.CPaqueteDestino;
import com.pricing.model.CPaqueteServicio;
import com.pricing.model.CPasajero;
import com.pricing.model.CReserva;
import com.pricing.model.CReservaPaquete;
import com.pricing.model.CReservaPaqueteActividad;
import com.pricing.model.CReservaPaqueteCategoriaHotel;
import com.pricing.model.CReservaPaqueteServicio;
import com.pricing.model.CServicio;
import com.pricing.model.CSubServicio;
import com.pricing.model.ConfAltoNivel;
import com.pricing.paypal.ExpressCheckout;
import com.pricing.util.CEmail;
import com.pricing.util.ScannUtil;
import com.pricing.util.Util;

public class pricingVM 
{
	@Wire
	Popup calendarPop,calendarAltPop;
	@Wire
	Button btn_reservar;
	@Wire
	Label lblFechaInicioPaso3,lblFechaFinPaso3,lblFechaArribo;
	HttpSession seshttp;
	@Wire
	Html htmlPagoMasterdCard;
	@Wire
	Combobox nroPersonas,nroPersonas2;
	@Wire
	Hbox hbCalAlt,hbCal;
	//====================
	private String urlPaypal;
	//====================
	private DecimalFormat df;
	private DecimalFormatSymbols simbolos;
	//====================
	private ArrayList<String> listaCodPaquetes;
	//====================
	private boolean paso1;
	private boolean paso2;
	private boolean paso3;
	private boolean paso4;
	private boolean acceptedTermsAndCond;
	private boolean payment;
	private boolean reservar;
	private boolean mostrarPaypal;
	private boolean mostrarCostoServicio;
	private boolean mostrarCostoActividades;
	private boolean mostrarServicios;
	private boolean mostrarPrecios;
	private boolean mostrarHoteles;
	private boolean mostrarDispIncaTrail;
	private boolean mostrarCalendarZK;
	private boolean btnHoteles;
	private boolean mostrarReservaHotel;
	private double montoTotal;
	private double TotalHabitaciones;
	private double TotalServicios;
	private double TotalPaquete;
	private double TotalActividades;
	private String lblMontoPaquete;
	private String tax;
	private int nroPasajeros;
	private int nroSimples;
	private int nroDobles;
	private int nroTriples;
	private ArrayList<String> restSimple;
	private ArrayList<String> restDoble;
	private ArrayList<String> restTriple;
	private String lblPrecioSimple;
	private String lblPrecioDoble;
	private String lblPrecioTriple;
	private String lblTotalPrecioSimple;
	private String lblTotalPrecioDoble;
	private String lblTotalPrecioTriple;
	private String lblMontoTotal;
	private String monto_Pagar_sin_impuesto;
	private String textoPorcentaje;
	private String totalConImpuestoPaypal;
	private String lblTotalPaquete;
	private String lblTotalHabitaciones;
	private String lblTotalServicios;
	private String lblTotalActividades;
	private String lblCategoriaSeleccionada;
	private String language;
	private String estiloPaso1;
	private String estiloPaso2;
	private String estiloPaso3;
	private boolean visiblePaso1;
	private boolean visiblePaso2;
	private boolean visiblePaso3;
	private boolean visibleBarraPaso1;
	private boolean visibleBarraPaso2;
	private boolean visibleBarraPaso3;
	private boolean visibleNroPaso1;
	private boolean visibleNroPaso2;
	private boolean visibleNroPaso3;
	private boolean visibleContenedorPasos;
	private boolean visiblecontentBotonesPasos;
	private boolean visibleBtnAtras;
	private boolean visibleBtnSiguiente;
	private boolean primeraVez;
	private boolean primeraVezCalendarALt;
	private boolean primeraVezCalendarPri;
	//=======================
	private CReservaPaqueteCategoriaHotel oReservaPaqCatHotel;
	private CReservaPaqueteCategoriaHotelDAO reservaPaqCatHotelDao;
	private ArrayList<CCategoriaConHoteles> listaCategoriaConHoteles;
	/****************************/
	private ArrayList<CReservaPaqueteServicio> listaReservaPaqServ;
	private CReservaPaqueteServicioDAO reservaPaqServDao;
	/****************************/
	private ArrayList<CReservaPaqueteActividad> listaReservaPaqActi;
	/****************************/
	private CReserva oReservar;
	private ArrayList<String> listacFechasAlternas;
	private ArrayList<String[]> listaSeparadaFechasAlternas;
	private CFechaAlternaDAO fechaAlternaDao;
	private String fecha;
	private String fechaAlterna;
	/***************************/
	private CPagos pagos;
	private String codigoPaquete;
	private CImpuesto oImpuesto;
	private CImpuestoDAO impuestoDao;
	private CInterfaz oInterfaz;
	private ArrayList<CPais> listaPaises;
	/**************************/
	private ArrayList<CDestinoConHoteles> listaDestinoConHoteles;
	/***************************/
	private CPaqueteCategoriaHotelDAO paqueteCatHotelDao;
	private ArrayList<CPaqueteCategoriaHotel> listaPaqueteCatHotel;
	/***************************/
	private CEtiquetaDAO etiquetaDao;
	private String[] etiqueta;
	private CConfigUrlDAO configUrlDao;
	/***********************/
	private String nombreDoc;
	private ArrayList<CPasajero> listaPasajeros;
	private ArrayList<String> listaEdades;
	private ArrayList<String> listaUrlImages;
	private String urlPdf;
	private String[] SECResult;
	private String codDisponibilidad;
	private ConfAltoNivel confAltoNivel;
	private ConfAltoNivelDAO confAltoNivelDAO;
	private String integrantes;
	private String paquetes;
	private String textoParcial;
	private String textoTotal;
	private boolean edadSuperada;
	//======METODOS===============
	@Init
	public void inicializarVM() throws IOException
	{
		//===Recuperando los datos del APP
		integrantes=(String)Executions.getCurrent().getAttribute("integrantes");
		paquetes=(String)Executions.getCurrent().getAttribute("paquetes");
		//================================
		confAltoNivel=new ConfAltoNivel();
		confAltoNivelDAO=new ConfAltoNivelDAO();
		visiblePaso1=true;
		visiblePaso2=false;
		visiblePaso3=false;
		visibleBarraPaso1=true;
		visibleBarraPaso2=false;
		visibleBarraPaso3=false;
		visibleNroPaso1=false;
		visibleNroPaso2=false;
		visibleNroPaso3=false;
		visibleBtnAtras=false;
		visibleBtnSiguiente=true;
		edadSuperada=false;
		/**Inicializamos las sessiones**/
		seshttp=(HttpSession)Sessions.getCurrent().getNativeSession();
		/*******************************/
		simbolos= new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		df=new DecimalFormat("########0.00",simbolos);
		urlPdf=Util.getPathReservas()+"reservas.pdf";
		/**RECUPERAMOS LA CONFIGURACION DE URLs**/
		configUrlDao=new CConfigUrlDAO();
		configUrlDao.asignarConfigUrl(configUrlDao.recuperarConfigUrlDB());
		/*******************/
		/**RECUPERAMOS LA CONFIGURACION DE LA INTERFAZ DE PRICING**/
		CInterfazDAO interfazDao=new CInterfazDAO();
		interfazDao.asignarConfigInterfaz(interfazDao.recuperarConfigInterfazDB());
		setoInterfaz(interfazDao.getoInterfaz());
		/*************************/
		//============
		this.nombreDoc=generarNombreImagen();
		this.acceptedTermsAndCond=false;
		//=================
		this.nroPasajeros=0;
		this.montoTotal=0.0;
		//==========================
		this.pagos=new CPagos();
		this.oImpuesto=new CImpuesto();
		this.impuestoDao=new CImpuestoDAO();
		this.listaPaises=new ArrayList<CPais>();
		/****************************************/
		listacFechasAlternas=new ArrayList<String>();
		//primeramente recuperamos de la sesion el codigo del paquete
		try
		{
			Execution exec = Executions.getCurrent();
		    this.codigoPaquete=exec.getParameter("var1");
		}
		catch(Exception e)
		{
			System.out.println("Error: "+e.getMessage());
		}
		if(codigoPaquete!=null)
		{
//			listaCodPaquetes.add(codigoPaquete);
			oReservar=new CReserva(codigoPaquete);
			codDisponibilidad=obtenerCodDisponibilidad(oReservar.getoPaquete());
			iniciarEtiquetas();
			//=================
			if(oReservar.getoPaquete().isbModoPorcentual())
			{
				textoParcial=oReservar.getoPaquete().getnPorcentajeCobro()+" %";
				textoTotal="100 %";
			}else
			{
				textoParcial=etiqueta[102];
				textoTotal=etiqueta[103];
			}
			//Recuperamos los impuestos
			impuestoDao.recuperarImpuestosBD();
			oImpuesto=impuestoDao.getoImpuesto();
			/****************/
			TotalPaquete=0;
			montoTotal=0;
			lblMontoTotal=df.format(montoTotal);
			lblMontoPaquete=df.format(0);
			lblTotalPaquete=df.format(TotalPaquete);
			tax="";
			if(!codDisponibilidad.equals("0"))
			{
				this.mostrarDispIncaTrail=true;
				this.mostrarCalendarZK=false;
			}
			else
			{
				this.mostrarCalendarZK=true;
				this.mostrarDispIncaTrail=false;
			}
			if(oReservar.getoPaquete().isSinDescuento())
			{
				oReservar.getoCupon().inicioAplicarCupon();
			}
//			//Luego sse procede a inicializar los hoteles si es que hubiese
//			iniciarHoteles();
//			//Luego se procede a iniciar los servicios del paquete
//			iniciarServicios();
//			iniciarActividades();
//			//==================
			iniciarEdades();
			iniciarReserva();
			if(!oReservar.getoPaquete().isbLlenarDatosUnPax())
				iniciarPasajeros();
			//===========================
			this.paso1=true;
			this.paso2=false;
			this.paso3=false;
			this.paso4=false;
			this.mostrarPaypal=false;
			this.fecha="";
			this.primeraVez=true;
			this.primeraVezCalendarALt=true;
			this.primeraVezCalendarPri=true;
			//Inicializamos paypal express checkout
			urlPaypal="";
			textoPorcentaje="";
			monto_Pagar_sin_impuesto="";
			payment=false;
			reservar=false;
			seshttp=(HttpSession)Sessions.getCurrent().getNativeSession();
		    seshttp.setAttribute("codDisponibilidad",this.codDisponibilidad);
		    System.out.println("Codigo Disponibilidad: "+codDisponibilidad);
		}
	}
	public String obtenerCodDisponibilidad(CPaquete paquete)
	{
		String cod="0";
		if(paquete.getcDisponibilidad().equals("MACHUPICCHU"))cod="1";
		else if(paquete.getcDisponibilidad().equals("MACHUPICCHU_HUAYNAPICCHU_1G"))cod="2";
		else if(paquete.getcDisponibilidad().equals("MACHUPICCHU_HUAYNAPICCHU_2G"))cod="3";
		else if(paquete.getcDisponibilidad().equals("MACHUPICCHU_MONTANA_1G"))cod="4";
		else if(paquete.getcDisponibilidad().equals("MACHUPICCHU_MONTANA_2G"))cod="5";
		else if(paquete.getcDisponibilidad().equals("CAMINO_INKA_CLASICO") ||
				paquete.getcDisponibilidad().equals("CAMINO_INKA_YOURSELF") )cod="20";
		else if(paquete.getcDisponibilidad().equals("CAMINO_INKA_CORTO"))cod="21";
		return cod;
	}
	public void iniciarEdades()
	{
		listaEdades=new ArrayList<String>();
		for(int i=8;i<=99;i++)
			listaEdades.add(Integer.toString(i));
	}
	
	public void iniciarPasajeros()
	{
		if(!oReservar.getoPaquete().isbLlenarDatosUnPax())
		{
			for(int i=1;i<=16;i++)
			{
				CPasajero pas=new CPasajero();
				pas.setnNro(i);
				if(oReservar.getoPaquete().isbSubirDocPax())
				{
					System.out.println("Entre a activar subida de doc");
					pas.setEsEdit(true);
				}
				listaPasajeros.add(pas);
			}
		}
	}
	public void updateListaPasajeros()
	{
		if(!oReservar.getoPaquete().isbLlenarDatosUnPax())
		{
			for(CPasajero pax:listaPasajeros)
			{
				if(pax.getnNro()<=nroPasajeros && !pax.isSelectPasajero())
				{
					pax.setSelectPasajero(true);
					if(pax.isEsEdit() && !oReservar.getoPaquete().isbSubirDocPax())pax.setEsEdit(false);
					BindUtils.postNotifyChange(null, null, pax,"selectPasajero");
					BindUtils.postNotifyChange(null, null, pax,"esEdit");
				}else if(pax.getnNro()>nroPasajeros && pax.isSelectPasajero())
				{
					pax.setSelectPasajero(false);
					BindUtils.postNotifyChange(null, null, pax,"selectPasajero");
				}else if(pax.getnNro()>nroPasajeros && !pax.isSelectPasajero())
					break;
			}
		}
	}
	@Command
	public void cargarDescripcionPaquete()
	{
		Window win_descripcion_paquete=(Window) Executions.createComponents("/descripcionPaquete.zul", null, null);
		win_descripcion_paquete.doModal();
	}
	@Command
	public void cargarItinerarioPaquete()
	{
		Window win_iti_paquete=(Window) Executions.createComponents("/itinerarioPaquete.zul", null, null);
		win_iti_paquete.doModal();
	}
	@Command
	public void cargarCalendarioAlterno()
	{
		if(primeraVezCalendarALt)
		{
			primeraVezCalendarALt=false;
			calendarAltPop.open(hbCalAlt, "after_start");
			Include dispon =new Include();
			dispon.setSrc("calendarFechaAlterna.zul");
			dispon.setParent(calendarAltPop);
		}else
		{
			calendarAltPop.open(hbCalAlt, "after_start");
		}
	}
	@Command
	public void cargarCalendarioPrincipal()
	{
		if(primeraVezCalendarPri)
		{
			primeraVezCalendarPri=false;
			calendarPop.open(hbCal, "after_start");
			Include dispon =new Include();
			dispon.setSrc("disponibilidad.zul");
			dispon.setParent(calendarPop);
		}else{
			calendarPop.open(hbCal, "after_start");
		}
	}
	public void iniciarReserva()
	{
		oReservar.setnNroPersonas(nroPasajeros);
		oReservar.setnPrecioPaquetePersona(TotalPaquete);
		listaSeparadaFechasAlternas=new ArrayList<String[]>();
		fechaAlternaDao=new CFechaAlternaDAO();
		oReservaPaqCatHotel=new CReservaPaqueteCategoriaHotel();
		reservaPaqCatHotelDao=new CReservaPaqueteCategoriaHotelDAO();
		listaReservaPaqServ=new ArrayList<CReservaPaqueteServicio>();
		listaReservaPaqActi=new ArrayList<CReservaPaqueteActividad>();
		listaPasajeros=new ArrayList<CPasajero>();
		reservaPaqServDao=new CReservaPaqueteServicioDAO();
	}
	public void iniciarEtiquetas()
	{
		etiquetaDao=new CEtiquetaDAO();
		etiquetaDao.asignarEtiquetaIdiomas(etiquetaDao.recuperarEtiquetasBD());
		etiqueta=new String[etiquetaDao.getIdioma().getIdioma1().length];//Se asigna el tamaño de etiqueta
		
		language=Executions.getCurrent().getHeader("accept-language").split(",")[0];
//		System.out.println(Executions.getCurrent().getHeader("accept-language"));
		//Recuperar la lista de  paises
		CPaisDAO paisDao=new CPaisDAO();
		paisDao.asignarPaises(paisDao.recuperarPaisesBD());
		listaPaises=new ArrayList<CPais>();
		setListaPaises(paisDao.getListaPaises());
		if(language.equals("es-ES"))
		{
			etiqueta=etiquetaDao.getIdioma().getIdioma1();
			oReservar.getoPaquete().setTitulo(oReservar.getoPaquete().getcTituloIdioma1());
			oReservar.getoPaquete().setDescripcion(oReservar.getoPaquete().getcDescripcionIdioma1());
			oReservar.getoPaquete().setItinerario(oReservar.getoPaquete().getcItinerarioIdioma1());
			for(CPais pais:listaPaises)
			{
				pais.setNamePais(pais.getcNombreIdioma1());
			}
		}
		else if(language.equals("pt-BR") || language.equals("pt-PT"))
		{
			etiqueta=etiquetaDao.getIdioma().getIdioma3();
			oReservar.getoPaquete().setTitulo(oReservar.getoPaquete().getcTituloIdioma3());
			oReservar.getoPaquete().setDescripcion(oReservar.getoPaquete().getcDescripcionIdioma3());
			oReservar.getoPaquete().setItinerario(oReservar.getoPaquete().getcItinerarioIdioma3());
			for(CPais pais:listaPaises)
			{
				pais.setNamePais(pais.getcNombreIdioma3());
			}
		}
		else
		{
			etiqueta=etiquetaDao.getIdioma().getIdioma2();
			oReservar.getoPaquete().setTitulo(oReservar.getoPaquete().getcTituloIdioma2());
			oReservar.getoPaquete().setDescripcion(oReservar.getoPaquete().getcDescripcionIdioma2());
			oReservar.getoPaquete().setItinerario(oReservar.getoPaquete().getcItinerarioIdioma2());
			//Recuperar la lista de  paises
			for(CPais pais:listaPaises)
			{
				pais.setNamePais(pais.getcNombreIdioma2());
			}
		}
		Sessions.getCurrent().setAttribute("etiqueta", etiqueta);
		Sessions.getCurrent().setAttribute("language", language);
	}
	@Command
	@NotifyChange({"etiqueta","textoParcial","textoTotal","listaPaises"})
	public void cambiarIdioma(@BindingParam("idioma")Object idioma)
	{
		if(idioma.toString().equals("1"))
		{
			if(language.equals("es-ES"))return;
			language="es-ES";
			etiqueta=etiquetaDao.getIdioma().getIdioma1();
			oReservar.getoPaquete().setTitulo(oReservar.getoPaquete().getcTituloIdioma1());
			oReservar.getoPaquete().setDescripcion(oReservar.getoPaquete().getcDescripcionIdioma1());
			oReservar.getoPaquete().setItinerario(oReservar.getoPaquete().getcItinerarioIdioma1());
			//Recuperar la lista de  paises
			for(CPais pais:listaPaises)
			{
				pais.setNamePais(pais.getcNombreIdioma1());
			}
			if(oReservar.getoPaquete().isConActividad())
			{
				for(CActividad acti:oReservar.getoPaquete().getListaActividades())
				{
					acti.setNombreActividad(acti.getcActividadIdioma1());
					acti.setDescripcionActividad(acti.getcDescripcionIdioma1());
					BindUtils.postNotifyChange(null, null, acti, "nombreActividad");
					BindUtils.postNotifyChange(null, null, acti, "descripcionActividad");
				}
			}
			if(oReservar.getoPaquete().isConServicio())
			{
				for(CServicio servicio:oReservar.getoPaquete().getListaServicios())
				{
					servicio.setServicio(servicio.getcServicioIndioma1());
					servicio.setDescripcionVisible(servicio.getcDescripcionIdioma1());
					servicio.getListaOpcionServicios().get(0)[0]=etiqueta[162];
					if(servicio.getcRestriccionYesNo()==1)//Servicio con restriccion yes/no
					{
						servicio.getListaOpcionServicios().get(0)[0]=etiqueta[48];
						servicio.getListaOpcionServicios().get(1)[0]=etiqueta[49];
					}
					if(servicio.getcRestriccionNum()==0 && servicio.getcRestriccionYesNo()==0)
					{
						for(CSubServicio subServicio:oReservar.getoPaquete().getListaSubServicios())
						{
							subServicio.setTituloSubServicio(subServicio.getcSubServicioIndioma1());
							subServicio.setcDescripcion(subServicio.getcDescripcionIdioma1());
							if(servicio.getnServicioCod()==subServicio.getnServicioCod())
							{
								for(int i=0;i<servicio.getListaOpcionServicios().size();i++)
								{
									int cod=Integer.parseInt(servicio.getListaOpcionServicios().get(i)[1]);
									if(cod==subServicio.getnSubServicioCod())
									{
										servicio.getListaOpcionServicios().get(i)[0]=subServicio.getTituloSubServicio();
									}
								}
							}
						}
					}
					BindUtils.postNotifyChange(null, null, servicio, "servicio");
					BindUtils.postNotifyChange(null, null, servicio, "descripcionVisible");
					BindUtils.postNotifyChange(null, null, servicio, "listaOpcionServicios");
				}
			}
		}
		else if(idioma.toString().equals("3"))
		{
			if(language.equals("pt-BR"))return;
			language="pt-BR";
			etiqueta=etiquetaDao.getIdioma().getIdioma3();
			oReservar.getoPaquete().setTitulo(oReservar.getoPaquete().getcTituloIdioma3());
			oReservar.getoPaquete().setDescripcion(oReservar.getoPaquete().getcDescripcionIdioma3());
			oReservar.getoPaquete().setItinerario(oReservar.getoPaquete().getcItinerarioIdioma3());
			//Recuperar la lista de  paises
			for(CPais pais:listaPaises)
			{
				pais.setNamePais(pais.getcNombreIdioma3());
			}
			if(oReservar.getoPaquete().isConActividad())
			{
				for(CActividad acti:oReservar.getoPaquete().getListaActividades())
				{
					acti.setNombreActividad(acti.getcActividadIdioma3());
					acti.setDescripcionActividad(acti.getcDescripcionIdioma3());
					BindUtils.postNotifyChange(null, null, acti, "nombreActividad");
					BindUtils.postNotifyChange(null, null, acti, "descripcionActividad");
				}
			}
			if(oReservar.getoPaquete().isConServicio())
			{
				for(CServicio servicio:oReservar.getoPaquete().getListaServicios())
				{
					servicio.setServicio(servicio.getcServicioIndioma3());
					servicio.setDescripcionVisible(servicio.getcDescripcionIdioma3());
					servicio.getListaOpcionServicios().get(0)[0]=etiqueta[162];
					if(servicio.getcRestriccionYesNo()==1)//Servicio con restriccion yes/no
					{
						servicio.getListaOpcionServicios().get(0)[0]=etiqueta[48];
						servicio.getListaOpcionServicios().get(1)[0]=etiqueta[49];
					}
					if(servicio.getcRestriccionNum()==0 && servicio.getcRestriccionYesNo()==0)
					{
						for(CSubServicio subServicio:oReservar.getoPaquete().getListaSubServicios())
						{
							subServicio.setTituloSubServicio(subServicio.getcSubServicioIndioma3());
							subServicio.setcDescripcion(subServicio.getcDescripcionIdioma3());
							if(servicio.getnServicioCod()==subServicio.getnServicioCod())
							{
								for(int i=0;i<servicio.getListaOpcionServicios().size();i++)
								{
									int cod=Integer.parseInt(servicio.getListaOpcionServicios().get(i)[1]);
									if(cod==subServicio.getnSubServicioCod())
									{
										servicio.getListaOpcionServicios().get(i)[0]=subServicio.getTituloSubServicio();
									}
								}
							}
						}
					}
					BindUtils.postNotifyChange(null, null, servicio, "servicio");
					BindUtils.postNotifyChange(null, null, servicio, "descripcionVisible");
					BindUtils.postNotifyChange(null, null, servicio, "listaOpcionServicios");
				}
			}
		}
		else
		{
			if(language.equals("en-US"))return;
			language="en-US";
			etiqueta=etiquetaDao.getIdioma().getIdioma2();
			oReservar.getoPaquete().setTitulo(oReservar.getoPaquete().getcTituloIdioma2());
			oReservar.getoPaquete().setDescripcion(oReservar.getoPaquete().getcDescripcionIdioma2());
			oReservar.getoPaquete().setItinerario(oReservar.getoPaquete().getcItinerarioIdioma2());
			//Recuperar la lista de  paises
			for(CPais pais:listaPaises)
			{
				pais.setNamePais(pais.getcNombreIdioma2());
			}
			if(oReservar.getoPaquete().isConActividad())
			{
				for(CActividad acti:oReservar.getoPaquete().getListaActividades())
				{
					acti.setNombreActividad(acti.getcActividadIdioma2());
					acti.setDescripcionActividad(acti.getcDescripcionIdioma2());
					BindUtils.postNotifyChange(null, null, acti, "nombreActividad");
					BindUtils.postNotifyChange(null, null, acti, "descripcionActividad");
				}
			}
			if(oReservar.getoPaquete().isConServicio())
			{
				for(CServicio servicio:oReservar.getoPaquete().getListaServicios())
				{
					servicio.setServicio(servicio.getcServicioIndioma2());
					servicio.setDescripcionVisible(servicio.getcDescripcionIdioma2());
					servicio.getListaOpcionServicios().get(0)[0]=etiqueta[162];
					if(servicio.getcRestriccionYesNo()==1)//Servicio con restriccion yes/no
					{
						servicio.getListaOpcionServicios().get(0)[0]=etiqueta[48];
						servicio.getListaOpcionServicios().get(1)[0]=etiqueta[49];
					}
					if(servicio.getcRestriccionNum()==0 && servicio.getcRestriccionYesNo()==0)
					{
						for(CSubServicio subServicio:oReservar.getoPaquete().getListaSubServicios())
						{
							subServicio.setTituloSubServicio(subServicio.getcSubServicioIndioma2());
							subServicio.setcDescripcion(subServicio.getcDescripcionIdioma2());
							if(servicio.getnServicioCod()==subServicio.getnServicioCod())
							{
								for(int i=0;i<servicio.getListaOpcionServicios().size();i++)
								{
									int cod=Integer.parseInt(servicio.getListaOpcionServicios().get(i)[1]);
									if(cod==subServicio.getnSubServicioCod())
									{
										servicio.getListaOpcionServicios().get(i)[0]=subServicio.getTituloSubServicio();
									}
								}
							}
						}
					}
					BindUtils.postNotifyChange(null, null, servicio, "servicio");
					BindUtils.postNotifyChange(null, null, servicio, "descripcionVisible");
					BindUtils.postNotifyChange(null, null, servicio, "listaOpcionServicios");
				}
			}
		}
		if(!oReservar.getoPaquete().isbModoPorcentual())
		{
			textoParcial=etiqueta[102];
			textoTotal=etiqueta[103];
		}
		if(listaCategoriaConHoteles!=null)
			if(!listaCategoriaConHoteles.isEmpty())
			{
				int i=53;
				for(CCategoriaConHoteles CCH:listaCategoriaConHoteles)
				{
					CCH.setNameCategory(etiqueta[i++]);
					BindUtils.postNotifyChange(null, null, CCH, "nameCategory");
				}
			}
		BindUtils.postNotifyChange(null, null, oReservar, "oPaquete");
		Sessions.getCurrent().setAttribute("language", language);
	}
	public void iniciarActividades()
	{
		//Recuperamos los datos de paquete actividades
		if(oReservar.getoPaquete().isConActividad())
		{
			for(CActividad a:oReservar.getoPaquete().getListaActividades())
			{
				if(language.equals("es-ES"))
				{
					a.setNombreActividad(a.getcActividadIdioma1());
					a.setDescripcionActividad(a.getcDescripcionIdioma1());
				}
				else if(language.equals("pt-BR") || language.equals("pt-PT"))
				{
					a.setNombreActividad(a.getcActividadIdioma3());
					a.setDescripcionActividad(a.getcDescripcionIdioma3());
				}else
				{
					a.setNombreActividad(a.getcActividadIdioma2());
					a.setDescripcionActividad(a.getcDescripcionIdioma2());
				}
			}
		}
	}
	@Command
	@NotifyChange({"oActividad","mostrarVentanaActividad","lblMontoTotal","mostrarCostoActividades","lblTotalActividades"})
	public void actividadSeleccionada(@BindingParam("actividad")CActividad actividad,@BindingParam("opcion")String opcion,@BindingParam("cod")int cod)
	{
		ArrayList<CPaqueteActividad> listaPaqueteActividades=new ArrayList<CPaqueteActividad>();
		listaPaqueteActividades=oReservar.getoPaquete().obtenerListaPaqueteActividad(oReservar.getoPaquete().getcPaqueteCod());
		System.out.println("Entre a comprar la actividad");
		if(opcion.equals("si"))
		{
			mostrarCostoActividades=true;
			actividad.setbMostrarEnResumen(true);
			if(actividad.isNoComprado())
			{
				TotalActividades=TotalActividades+actividad.getnPrecioActividad().doubleValue()*nroPasajeros;
				montoTotal=TotalActividades+TotalHabitaciones+TotalPaquete+TotalServicios;
			}
			lblMontoTotal=df.format(montoTotal);
			actividad.setMostrarInformacionActividad(true);
			actividad.setComprado(true);
			actividad.setNoComprado(false);
			actividad.setNroPersonasActividad(nroPasajeros);
			actividad.setPrecioTotalActividad(df.format(actividad.getnPrecioActividad().doubleValue()*nroPasajeros));
			/****************************/
			CReservaPaqueteActividad oReservaPA=new CReservaPaqueteActividad();
			int codPA=obtenerCodPaqueteActividad(cod);
			oReservaPA.setCodPaqueteActividad(codPA);
			oReservaPA.setNroPrestacionesActividad(actividad.getNroPersonasActividad());
			oReservaPA.setPrecioPrestacionActividad(Double.parseDouble(actividad.getPrecioTotalActividad()));
			listaReservaPaqActi.add(oReservaPA);
		}else
		{
			if(actividad.isComprado())
			{
				TotalActividades=TotalActividades-actividad.getnPrecioActividad().doubleValue()*nroPasajeros;
				montoTotal=TotalActividades+TotalHabitaciones+TotalPaquete+TotalServicios;
			}
			lblMontoTotal=df.format(montoTotal);
			actividad.setMostrarInformacionActividad(false);
			actividad.setComprado(false);
			actividad.setNoComprado(true);
			actividad.setNroPersonasActividad(0);
			actividad.setPrecioTotalActividad(df.format(0));
			actividad.setbMostrarEnResumen(false);
			/*********/
			eliminarReservaPaqueteActividad(cod);
		}
		lblTotalActividades=df.format(TotalActividades);
		BindUtils.postNotifyChange(null, null, actividad, "bMostrarEnResumen");
		BindUtils.postNotifyChange(null, null, actividad, "precioTotalActividad");
		BindUtils.postNotifyChange(null, null, actividad, "nroPersonasActividad");
		BindUtils.postNotifyChange(null, null, actividad, "mostrarInformacionActividad");
	}
	public int obtenerCodPaqueteActividad(int codActi)
	{
		ArrayList<CPaqueteActividad> listaPaqueteActividad=new ArrayList<CPaqueteActividad>();
		listaPaqueteActividad=oReservar.getoPaquete().obtenerListaPaqueteActividad(oReservar.getoPaquete().getcPaqueteCod());
		int codPA=0;
		for(CPaqueteActividad pa:listaPaqueteActividad)
		{
			if(pa.getnActividadCod()==codActi)
			{
				codPA=pa.getnPaqueteActividad();
				break;
			}
		}
		return codPA;
	}
	public void eliminarReservaPaqueteActividad(int codActi)
	{
		ArrayList<CPaqueteActividad> listaPaqueteActividad=new ArrayList<CPaqueteActividad>();
		listaPaqueteActividad=oReservar.getoPaquete().obtenerListaPaqueteActividad(oReservar.getoPaquete().getcPaqueteCod());
		int codPA=0;
		for(CPaqueteActividad pa:listaPaqueteActividad)
		{
			if(pa.getnActividadCod()==codActi)
			{
				codPA=pa.getnPaqueteActividad();
				break;
			}
		}
		for(CReservaPaqueteActividad rpa:listaReservaPaqActi)
		{
			if(rpa.getCodPaqueteActividad()==codPA)
			{
				listaReservaPaqActi.remove(rpa);
				break;
			}
		}
	}
	@Command
	public void cerrarVentanaActividad(@BindingParam("actividad")CActividad actividad)
	{
		actividad.setMostrarInformacionActividad(false);
		BindUtils.postNotifyChange(null, null, actividad, "mostrarInformacionActividad");
	}
	public void iniciarServicios()
	{
		this.mostrarServicios=false;
		this.mostrarCostoServicio=false;
		if(oReservar.getoPaquete().isConServicio())
		{
			//Se muestran los servicios en la vista
			this.mostrarServicios=true;
			TotalServicios=0.0;
			lblTotalServicios=df.format(TotalServicios);
			//=========================
			/**Ahora se procede a recuperar la lista de servicios correspondientes al paquete**/
			for(CServicio servicio:oReservar.getoPaquete().getListaServicios())
			{
				if(language.equals("es-ES"))
				{
					servicio.setServicio(servicio.getcServicioIndioma1());
					servicio.setDescripcionVisible(servicio.getcDescripcionIdioma1());
				}
				else if(language.equals("pt-BR")|| language.equals("pt-PT"))
				{
					servicio.setServicio(servicio.getcServicioIndioma3());
					servicio.setDescripcionVisible(servicio.getcDescripcionIdioma3());
				}
				else
				{
					servicio.setServicio(servicio.getcServicioIndioma2());
					servicio.setDescripcionVisible(servicio.getcDescripcionIdioma2());
				}
			}
			/**Recuperar lista de subservicios en el paquete correspondiente a sus servicios**/
			for(CSubServicio subServicio:oReservar.getoPaquete().getListaSubServicios())
			{
				if(language.equals("es-ES"))
				{
					subServicio.setTituloSubServicio(subServicio.getcSubServicioIndioma1());
					subServicio.setcDescripcion(subServicio.getcDescripcionIdioma1());
				}
				else if(language.equals("pt-BR")|| language.equals("pt-PT"))
				{
					subServicio.setTituloSubServicio(subServicio.getcSubServicioIndioma3());
					subServicio.setcDescripcion(subServicio.getcDescripcionIdioma3());
				}else
				{
					subServicio.setTituloSubServicio(subServicio.getcSubServicioIndioma2());
					subServicio.setcDescripcion(subServicio.getcDescripcionIdioma2());
				}
			}
			/**Una vez recuperada la informacion necesaria se inicializa
			 *  la listaOpcionServicio de cada servicio o restricciones**/
			inicializarRestriccionesServicios();
		}
	}
	public void inicializarRestriccionesServicios()
	{
		/**Para cada servicio se procede a inicializar sus restricciones**/
		for(CServicio servicio:oReservar.getoPaquete().getListaServicios())
		{
			if(servicio.getcRestriccionYesNo()==1)//Servicio con restriccion yes/no
			{
				String[] opcionesYes=new String[2];
				opcionesYes[0]=etiqueta[48];
				opcionesYes[1]="yes";
				String[] opcionesNo=new String[2];
				opcionesNo[0]=etiqueta[49];
				opcionesNo[1]="no";
				servicio.getListaOpcionServicios().add(opcionesNo);
				servicio.getListaOpcionServicios().add(opcionesYes);
			}
			else 
			{
				if(servicio.getcRestriccionNum()>0)//Servicio con restriccion numerica
				{
					String[] s=new String[2];
					s[0]=etiqueta[162];
					s[1]="0";
					servicio.getListaOpcionServicios().add(s);
//					if(listaServicios.get(i).getnServicioCod()==2)
//					{
//						String[] st=new String[2];
//						st[0]="0.5";
//						st[1]="0.5";
//						listaServicios.get(i).getListaOpcionServicios().add(st);
//					}
					int inc=servicio.getcIncremento();
					while(inc<=nroPasajeros*servicio.getcRestriccionNum())
					{
						String[] res=new String[2];
						res[0]=res[1]=""+inc;
						servicio.getListaOpcionServicios().add(res);
						inc+=servicio.getcIncremento();
					}
				}
				else//Servicio con subservicios
				{
					String[] aux=new String[2];
					aux[0]=etiqueta[162];
					aux[1]="0";
					servicio.getListaOpcionServicios().add(aux);
					for(CSubServicio subServicio:oReservar.getoPaquete().getListaSubServicios())
					{
						if(servicio.getnServicioCod()==subServicio.getnServicioCod())
						{
							String[] rest=new String[2];
							rest[0]=subServicio.getTituloSubServicio();
							rest[1]=""+subServicio.getnSubServicioCod();
							servicio.getListaOpcionServicios().add(rest);
						}
					}
				}
			}
		}
	}
	@Command
	@NotifyChange({"lblTotalServicios","lblMontoTotal","mostrarCostoServicio"})
	public void SelectOpcionService(@BindingParam("servicio")CServicio servicio,@BindingParam("cod")int cod,@BindingParam("opcion")Object opcion,@BindingParam("pos")int pos)
	{
		System.out.println("Tamaño del servico del paquete 2: "+oReservar.getoPaquete().getListaServicios().size());
		ArrayList<CPaqueteServicio> listaPaqueteServicios=new ArrayList<CPaqueteServicio>();
		listaPaqueteServicios=oReservar.getoPaquete().obtenerListaPaqueteServicio(oReservar.getoPaquete().getcPaqueteCod());
		mostrarCostoServicio=true;
		if(opcion.toString().equals("0") || opcion.toString().equals("no"))
		{
			servicio.setbMostrarEnResumen(false);
			servicio.setMostrarDescripcion(false);
			servicio.setCantidadServicio(0);
			servicio.setPrecioTotalServicio(df.format(0.0));
			servicio.setOpcionValue("0");
			//=======================
			eliminarReservaPaqueteServicio(cod);
		}
		else
		{
			servicio.setbMostrarEnResumen(true);
			servicio.setMostrarDescripcion(true);
			//Si restriccionYesNo=0 y restriccionNum=0 significa
			//que se trata de un servicio con subservicios
			if(servicio.getcRestriccionYesNo()==0 && servicio.getcRestriccionNum()==0)
			{
				CSubServicio subServicio=encontrarSubServicio(Integer.parseInt(opcion.toString()));
				servicio.setDescripcionVisible(subServicio.getcDescripcion());
				servicio.setUrlImageVisible(subServicio.getcUrlImg());
				servicio.setCantidadServicio(nroPasajeros);
				servicio.setPrecioTotalServicio(df.format(subServicio.getnPrecioServicio().doubleValue()*nroPasajeros));
				servicio.setOpcionValue(""+nroPasajeros);
				servicio.setLink(subServicio.getcLink());
				servicio.setnPrecioServicio(subServicio.getnPrecioServicio());
				//========================================================
				CReservaPaqueteServicio oReservaPS=new CReservaPaqueteServicio();
				int codPS=obtenerCodPaqueteServicio(cod);
				oReservaPS.setCodPaqueteServicio(codPS);
				oReservaPS.setNroPrestacionServicio(nroPasajeros);
				oReservaPS.setPrecioPrestacionServicio(subServicio.getnPrecioServicio().doubleValue()*nroPasajeros);
				oReservaPS.setnSubServicioCod(subServicio.getnSubServicioCod());
				/******Luego se agrega a la lista de reservas del paquete servicio*******/
				listaReservaPaqServ.add(oReservaPS);
			}
			else
			{
				if(opcion.toString().equals("yes"))
				{
					servicio.setUrlImageVisible(servicio.getcUrlImg());
					servicio.setCantidadServicio(nroPasajeros);
					servicio.setPrecioTotalServicio(df.format(servicio.getnPrecioServicio().doubleValue()*nroPasajeros));
					servicio.setOpcionValue(""+nroPasajeros);
					//========================================================
					CReservaPaqueteServicio oReservaPS=new CReservaPaqueteServicio();
					int codPS=obtenerCodPaqueteServicio(cod);
					oReservaPS.setCodPaqueteServicio(codPS);
					oReservaPS.setNroPrestacionServicio(nroPasajeros);
					oReservaPS.setPrecioPrestacionServicio(servicio.getnPrecioServicio().doubleValue()*nroPasajeros);
					/******Luego se agrega a la lista de reservas del paquete servicio*******/
					listaReservaPaqServ.add(oReservaPS);
				}
				else
				{
					servicio.setUrlImageVisible(servicio.getcUrlImg());
					servicio.setCantidadServicio(Float.parseFloat(opcion.toString()));
					//========================================================
					CReservaPaqueteServicio oReservaPS=new CReservaPaqueteServicio();
					int codPS=obtenerCodPaqueteServicio(cod);
					oReservaPS.setCodPaqueteServicio(codPS);
					oReservaPS.setNroPrestacionServicio(Float.parseFloat(opcion.toString()));
					
					servicio.setPrecioTotalServicio(df.format(servicio.getnPrecioServicio().doubleValue()*Float.parseFloat(opcion.toString())));
					oReservaPS.setPrecioPrestacionServicio(servicio.getnPrecioServicio().doubleValue()*Float.parseFloat(opcion.toString()));
					
					servicio.setOpcionValue(servicio.getListaOpcionServicios().get(pos)[0]);
					/******Luego se agrega a la lista de reservas del paquete servicio*******/
					listaReservaPaqServ.add(oReservaPS);
				}
			}
		}
		BindUtils.postNotifyChange(null, null, servicio,"bMostrarEnResumen");
		BindUtils.postNotifyChange(null, null, servicio,"mostrarDescripcion");
		BindUtils.postNotifyChange(null, null, servicio,"cantidadServicio");
		BindUtils.postNotifyChange(null, null, servicio,"precioTotalServicio");
		BindUtils.postNotifyChange(null, null, servicio,"opcionValue");
		BindUtils.postNotifyChange(null, null, servicio, "urlImageVisible");
		BindUtils.postNotifyChange(null, null, servicio, "descripcionVisible");
		
		/**Volvemos a calcular el precio total de los servicios**/
		TotalServicios=0.0;
		for(CServicio service:oReservar.getoPaquete().getListaServicios())
		{
			TotalServicios+=Float.parseFloat(service.getPrecioTotalServicio());
		}
		lblTotalServicios=""+df.format(TotalServicios);
		montoTotal=TotalActividades+TotalHabitaciones+TotalPaquete+TotalServicios;
		lblMontoTotal=""+df.format(montoTotal);
		System.out.println("Tamaño del servico del paquete: "+oReservar.getoPaquete().getListaServicios().size());
	}
	@Command
	public void redirectUrl(@BindingParam("servicio")int servicio)
	{
		try
		{
			Execution exec = Executions.getCurrent();
			HttpSession ses = (HttpSession)Sessions.getCurrent().getNativeSession();
			HttpServletResponse response=(HttpServletResponse)exec.getNativeResponse();
		    exec.sendRedirect(oReservar.getoPaquete().getListaServicios().get(servicio).getLink(),"_blank");
			exec.setVoided(true);
		}
		catch(Exception e)
		{
			
		}
	}
	public int obtenerCodPaqueteServicio(int cod)
	{
		ArrayList<CPaqueteServicio> listaPaqueteServicio=new ArrayList<CPaqueteServicio>();
		listaPaqueteServicio=oReservar.getoPaquete().obtenerListaPaqueteServicio(oReservar.getoPaquete().getcPaqueteCod());
		int codPS=0;
		for(CPaqueteServicio ps:listaPaqueteServicio)
		{
			if(ps.getnServicioCod()==cod)
			{
				codPS=ps.getCodPaqueteServicio();
				break;
			}
		}
		return codPS;
	}
	public void eliminarReservaPaqueteServicio(int codServicio)
	{
		ArrayList<CPaqueteServicio> listaPaqueteServicio=new ArrayList<CPaqueteServicio>();
		listaPaqueteServicio=oReservar.getoPaquete().obtenerListaPaqueteServicio(oReservar.getoPaquete().getcPaqueteCod());
		int codPS=0;
		for(CPaqueteServicio ps:listaPaqueteServicio)
		{
			if(ps.getnServicioCod()==codServicio)
			{
				codPS=ps.getCodPaqueteServicio();
				break;
			}
		}
		for(CReservaPaqueteServicio rps:listaReservaPaqServ)
		{
			if(rps.getCodPaqueteServicio()==codPS)
			{
				listaReservaPaqServ.remove(rps);
				break;
			}
		}
	}
	public CSubServicio encontrarSubServicio(int codSubServicio)
	{
		CSubServicio aux=null;
		for(CSubServicio subServicio:oReservar.getoPaquete().getListaSubServicios())
		{
			if(subServicio.getnSubServicioCod()==codSubServicio)
			{
				aux=subServicio;
				break;
			}
		}
		return aux;
	}
	public void iniciarHoteles()
	{
		this.mostrarPrecios=false;
		this.btnHoteles=false;
		this.mostrarHoteles=false;
		this.mostrarReservaHotel=false;
		this.TotalHabitaciones=0.0;
		this.lblTotalHabitaciones=df.format(TotalHabitaciones);
		listaCategoriaConHoteles=new ArrayList<CCategoriaConHoteles>();
		listaDestinoConHoteles=new ArrayList<CDestinoConHoteles>();
		if(oReservar.getoPaquete().isConDestino())
		{
			/******************************************/
			paqueteCatHotelDao=new CPaqueteCategoriaHotelDAO();
			listaPaqueteCatHotel=new ArrayList<CPaqueteCategoriaHotel>();
			/*****************************************/
			oReservaPaqCatHotel=new CReservaPaqueteCategoriaHotel();
			reservaPaqCatHotelDao=new CReservaPaqueteCategoriaHotelDAO();
			//=======================
			this.btnHoteles=true;
			this.mostrarReservaHotel=true;
			lblTotalPrecioSimple=lblTotalPrecioDoble=lblTotalPrecioTriple=df.format(0);
			//===========================================================
			/*******Recuperamos las categorias de hotel por paquete*****/
			//===========================================================
			paqueteCatHotelDao.asignarListaPaqueteCatHoteles(paqueteCatHotelDao.recuperarPaqueteCategoriaHotelesBD(codigoPaquete));
			setListaPaqueteCatHotel(paqueteCatHotelDao.getListaPaqueteCatHotel());
			//===========================================================
			/*******Recuperamos los hoteles por destino******************/
			//===========================================================
			CDestinoDAO destinoDao=new CDestinoDAO();
			ArrayList<CPaqueteDestino> listaPaqueteDestinos=new ArrayList<CPaqueteDestino>();
			listaPaqueteDestinos=oReservar.getoPaquete().obtenerListaPaqueteDestino(oReservar.getoPaquete().getcPaqueteCod());
			for(CPaqueteDestino pd:listaPaqueteDestinos)
			{
				destinoDao.asignarListaHotelesPorDestino(destinoDao.recuperarListaHotelesDestino(pd.getnDestinoCod()));
				destinoDao.asignarDestino(destinoDao.recuperarDestinoBD(pd.getnDestinoCod()));
				listaDestinoConHoteles.add(new CDestinoConHoteles(destinoDao.getoDestino(),destinoDao.getListaHotelesDestino()));
			}
			iniciarPreciosHabitacionesPorCategorias();
		}
		else
		{
			this.mostrarHoteles=false;
			System.out.println("Este paquete no tiene destinos");
		}
	}
	public void iniciarPreciosHabitacionesPorCategorias()
	{
		//Para cada categoria se procede a obtener los datos necesarios
		for(int cat=1;cat<=7;cat++)
		{
			CCategoriaConHoteles newCatConHoteles=new CCategoriaConHoteles();
			newCatConHoteles.setCodCat(cat);
			String categoria="";
			String estrellas="";
			if(cat==1){categoria=etiqueta[53];estrellas="**";}//Economico
			else if(cat==2){categoria=etiqueta[54];estrellas="***";}//Turistico
			else if(cat==3){categoria=etiqueta[55];estrellas="***";}//Turistico Superior
			else if(cat==4){categoria=etiqueta[56];estrellas="****";}//Primera
			else if(cat==5){categoria=etiqueta[57];estrellas="****";}//Primera Superior
			else if(cat==6){categoria=etiqueta[58];estrellas="*****";}//Lujo
			else if(cat==7){categoria=etiqueta[59];estrellas="*****";}//Lujo Superior
			newCatConHoteles.setEstrellas(estrellas);
			newCatConHoteles.setNameCategory(categoria);
			ArrayList<CDestinoConHoteles> catlistaDCH=new ArrayList<CDestinoConHoteles>();
			//Se recuperan todos los hoteles con la categoria en cada destino
			//ejemplo: tendremos para el destino X todos los hoteles que tienen
			//la categoria=1(economico)
			for(CDestinoConHoteles CDH:listaDestinoConHoteles)
			{
				ArrayList<CHotel> catDestHoteles=new ArrayList<CHotel>();
				for(CHotel hotel:CDH.getListaDestinosHoteles())
				{
					if(hotel.getCategoriaHotelCod()==cat)
						catDestHoteles.add(hotel);
				}
				catlistaDCH.add(new CDestinoConHoteles(CDH.getoDestino(), catDestHoteles));
			}
			newCatConHoteles.setListaCategoriaDestinosHoteles(catlistaDCH);
			//Se quiere saber si todos los destinos tienen la categoria
			//En caso de que un destino no presente hoteles con la categoria
			//no se muestra en la interfaz
			System.out.println("Este es el tamaño de lista categoria hoteles: "+catlistaDCH.size());
			for(CDestinoConHoteles auxCDH:catlistaDCH)
			{
				if(auxCDH.getListaDestinosHoteles().isEmpty())
				{
					newCatConHoteles.setEstadoCategoria(false);
					break;
				}
			}
			//una vez obtenido el estado de la categoria para ser mostrado
			//procedemos a calcular los precios
			ArrayList<CPaqueteDestino> listaPaqueteDestinos=new ArrayList<CPaqueteDestino>();
			listaPaqueteDestinos=oReservar.getoPaquete().obtenerListaPaqueteDestino(oReservar.getoPaquete().getcPaqueteCod());
			if(newCatConHoteles.isEstadoCategoria())//si la categoria será visible
			{
				//Para cada destino en el paquete se procede a calcular
				//el maximo precio en esta categoria y multiplicarlo
				//por el numero de noches que se ofrece en este paquete
				for(CPaqueteDestino PD:listaPaqueteDestinos)
				{
					for(CDestinoConHoteles dch:catlistaDCH)
					{
						if(PD.getnDestinoCod()==dch.getoDestino().getnDestinoCod())
						{
							double maxCosto=0;
							CHotel costoMaxHotel=new CHotel();
							for(CHotel auxHotel:dch.getListaDestinosHoteles())
							{
								if(auxHotel.getnPrecioSimple().doubleValue()>maxCosto)
								{
									maxCosto=auxHotel.getnPrecioSimple().doubleValue();
									costoMaxHotel=auxHotel;
								}
							}
							//una vez obtenido el hotel con el costo maximo
							//se procede a sumar los datos a los costos de la categoria
							//multiplicando por el numero de noches que se quedara en cada
							//destino
							if(dch.getoDestino().getnCodPostal()==84 && PD.isbConCaminoInka())
							{
								newCatConHoteles.setPrecioSimple(df.format(Double.parseDouble(newCatConHoteles.getPrecioSimple())+costoMaxHotel.getnPrecioSimple().doubleValue()*(PD.getnNoches()+1)));
								newCatConHoteles.setPrecioDoble(df.format(Double.parseDouble(newCatConHoteles.getPrecioDoble())+costoMaxHotel.getnPrecioDoble().doubleValue()*(PD.getnNoches()+1)));
								newCatConHoteles.setPrecioTriple(df.format(Double.parseDouble(newCatConHoteles.getPrecioTriple())+costoMaxHotel.getnPrecioTriple().doubleValue()*(PD.getnNoches()+1)));
								newCatConHoteles.setPrecioCamaAdicional(df.format(Double.parseDouble(newCatConHoteles.getPrecioCamaAdicional())+costoMaxHotel.getnPrecioCamaAdicional().doubleValue()*(PD.getnNoches()+1)));
							}else
							{
								newCatConHoteles.setPrecioSimple(df.format(Double.parseDouble(newCatConHoteles.getPrecioSimple())+costoMaxHotel.getnPrecioSimple().doubleValue()*PD.getnNoches()));
								newCatConHoteles.setPrecioDoble(df.format(Double.parseDouble(newCatConHoteles.getPrecioDoble())+costoMaxHotel.getnPrecioDoble().doubleValue()*PD.getnNoches()));
								newCatConHoteles.setPrecioTriple(df.format(Double.parseDouble(newCatConHoteles.getPrecioTriple())+costoMaxHotel.getnPrecioTriple().doubleValue()*PD.getnNoches()));
								newCatConHoteles.setPrecioCamaAdicional(df.format(Double.parseDouble(newCatConHoteles.getPrecioCamaAdicional())+costoMaxHotel.getnPrecioCamaAdicional().doubleValue()*PD.getnNoches()));
							}
						}
					}
				}
			}
			listaCategoriaConHoteles.add(newCatConHoteles);
			BindUtils.postNotifyChange(null, null, this,"listaCategoriaConHoteles");
		}
	}
	@Command
	public void detFechaArribo(@BindingParam("fecha")Date fecha)
	{
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		String Fecha=sdf.format(fecha);
		System.out.println("fecha es:"+Fecha);
		String dia=Fecha.substring(0,2);
		String mes=Fecha.substring(3,5);
		String anio=Fecha.substring(6,10);
		/*************Fecha Inicio*******************/
		Calendar calArribo=Calendar.getInstance();
		calArribo.set(Integer.parseInt(anio),Integer.parseInt(mes)-1,Integer.parseInt(dia));
		/*************Fecha Arribo***********************/
		oReservar.setdFechaArribo(calArribo.getTime());
		//===============================
		lblFechaArribo.setValue(dia+" "+etiqueta[158]+" "+obtenerMesText(Integer.parseInt(mes)-1)+", "+ anio);
		//==================
	}
	@Command
	@NotifyChange({"visibleDateReserva"})
	public void detFechaInicioSinCaminoInka(@BindingParam("fecha")Date fecha)
	{
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		String Fecha=sdf.format(fecha);
		System.out.println("fecha es:"+Fecha);
		String dia=Fecha.substring(0,2);
		String mes=Fecha.substring(3,5);
		String anio=Fecha.substring(6,10);
		/*************Fecha Inicio*******************/
		Calendar calIni=Calendar.getInstance();
		calIni.set(Integer.parseInt(anio),Integer.parseInt(mes)-1,Integer.parseInt(dia));
		/****Validando la fecha****/
		Calendar calActual=Calendar.getInstance();
		if(calIni.before(calActual))//Si la fecha es menor que la actual no se hace nada
			return;
		/*************Fecha Fin***********************/
		Calendar calFin=Calendar.getInstance();
		calFin.setTime(calIni.getTime());
		calFin.add(Calendar.DAY_OF_YEAR,oReservar.getoPaquete().getnNoches());
		/*********************************************/
		if(!oReservar.getoPaquete().isConFechaArribo())
			oReservar.setdFechaArribo(calIni.getTime());
		oReservar.setdFechaInicio(calIni.getTime());
		oReservar.setdFechaFin(calFin.getTime());
		//===============================
		lblFechaInicioPaso3.setValue(etiqueta[163]+" "+dia+" "+etiqueta[158]+" "+obtenerMesText(Integer.parseInt(mes)-1)+", "+ anio);
		//==================
		lblFechaFinPaso3.setValue(etiqueta[164]+" "+calFin.getTime().getDate()+" "+etiqueta[158]+" "+obtenerMesText(calFin.getTime().getMonth())+", "+ (calFin.getTime().getYear()+1900));
	}
	@GlobalCommand
	@NotifyChange({"disp","visibleDateReserva","lblDisp","listaFechasAlternas","fecha","fechaAlterna"})
	public void detFechaReservaCaminoInca(@BindingParam("cantDisp")String cantDispo,@BindingParam("fechas")ArrayList<String[]> listaFechas,@BindingParam("opcion")int opcion)
	{
		if(Integer.parseInt(cantDispo)!=0)
		{
			if(opcion==1)
			{
				if(listaFechas.get(0)[0].equals("")&& listaFechas.get(0)[1].equals("") && listaFechas.get(0)[2].equals(""))
					return;
				/***********Fecha Inicio****************/
				Calendar calIni=Calendar.getInstance();
				calIni.set(Integer.parseInt(listaFechas.get(0)[2]),Integer.parseInt(obtenerNroMes(listaFechas.get(0)[1]))-1,Integer.parseInt(listaFechas.get(0)[0]));
				/***********Fecha Fin*********************/
				Calendar calFin=Calendar.getInstance();
				calFin.setTime(calIni.getTime());
				calFin.add(Calendar.DAY_OF_YEAR,oReservar.getoPaquete().getnNoches());
				/*****************************************/
				if(!oReservar.getoPaquete().isConFechaArribo())
					oReservar.setdFechaArribo(calIni.getTime());
				oReservar.setdFechaInicio(calIni.getTime());
				oReservar.setdFechaFin(calFin.getTime());
				//======================
				lblFechaInicioPaso3.setValue(etiqueta[163]+" "+listaFechas.get(0)[0]+" "+etiqueta[158]+" "+convertirMesAidioma(listaFechas.get(0)[1])+", "+ listaFechas.get(0)[2]);
				fecha=listaFechas.get(0)[0]+" "+etiqueta[158]+" "+convertirMesAidioma(listaFechas.get(0)[1])+", "+ listaFechas.get(0)[2];
				//==================
				lblFechaFinPaso3.setValue(etiqueta[164]+" "+calFin.getTime().getDate()+" "+etiqueta[158]+" "+obtenerMesText(calFin.getTime().getMonth())+", "+ (calFin.getTime().getYear()+1900));
				
			}else{
				if(listaFechas.get(1)[0].equals("")&& listaFechas.get(1)[1].equals("") && listaFechas.get(1)[2].equals(""))
					return;
				//++++++Esta lista se usara para la reserva de Fechas Alternas+++++++++++++
				listaSeparadaFechasAlternas=listaFechas;
				//---------------------------------------
				listacFechasAlternas=new ArrayList<String>();
				String cad=listaFechas.get(1)[0]+" "+etiqueta[158]+" "+convertirMesAidioma(listaFechas.get(1)[1])+", "+listaFechas.get(1)[2];
				listacFechasAlternas.add(cad);
				fechaAlterna=cad;
			}
		}
		calendarPop.close();
		calendarAltPop.close();
	}
	@Command
	public void clickMostrarListaHoteles()
	{
		Window window = (Window)Executions.createComponents(
                "/hotelesPorCategoria.zul", null, null);
        window.doModal();
	}
	public String convertirMesAidioma(String mes)
	{
		String rMes="";
		switch(mes)
		{
			case "Enero":rMes=etiqueta[24].toLowerCase();break;
			case "Marzo":rMes=etiqueta[26].toLowerCase();break;
			case "Abril":rMes=etiqueta[27].toLowerCase();break;
			case "Mayo":rMes=etiqueta[28].toLowerCase();break;
			case "Junio":rMes=etiqueta[29].toLowerCase();break;
			case "Julio":rMes=etiqueta[30].toLowerCase();break;
			case "Agosto":rMes=etiqueta[31].toLowerCase();break;
			case "Setiembre":rMes=etiqueta[32].toLowerCase();break;
			case "Octubre":rMes=etiqueta[33].toLowerCase();break;
			case "Noviembre":rMes=etiqueta[34].toLowerCase();break;
			case "Diciembre":rMes=etiqueta[35].toLowerCase();break;
		}
		return rMes;
	}
	public Date sumarRestarDiasFecha(Date fecha, int dias)
	{
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(fecha); // Configuramos la fecha que se recibe
		 calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
		 return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
	}
	public String cambiarFormatoMes(String mes)
	{
		String nuevo="";
		switch(mes)
		{
			case "ene":nuevo="01";break;
			case "feb":nuevo="02";break;
			case "mar":nuevo="03";break;
			case "abr":nuevo="04";break;
			case "may":nuevo="05";break;
			case "jun":nuevo="06";break;
			case "jul":nuevo="07";break;
			case "ago":nuevo="08";break;
			case "sep":nuevo="09";break;
			case "oct":nuevo="10";break;
			case "nov":nuevo="11";break;
			case "dic":nuevo="12";break;
		}
		return nuevo;
	}
	public String obtenerNroMes(String mes)
	{
		String nuevo="";
		switch(mes)
		{
			case "Enero":nuevo="01";break;
			case "Marzo":nuevo="03";break;
			case "Abril":nuevo="04";break;
			case "Mayo":nuevo="05";break;
			case "Junio":nuevo="06";break;
			case "Julio":nuevo="07";break;
			case "Agosto":nuevo="08";break;
			case "Setiembre":nuevo="09";break;
			case "Octubre":nuevo="10";break;
			case "Noviembre":nuevo="11";break;
			case "Diciembre":nuevo="12";break;
		}
		return nuevo;
	}
	public String obtenerMesText(int mes)
	{
		String rMes="";
		switch(mes+1)
		{
			case 1:rMes=etiqueta[24].toLowerCase();break;
			case 2:rMes=etiqueta[25].toLowerCase();break;
			case 3:rMes=etiqueta[26].toLowerCase();break;
			case 4:rMes=etiqueta[27].toLowerCase();break;
			case 5:rMes=etiqueta[28].toLowerCase();break;
			case 6:rMes=etiqueta[29].toLowerCase();break;
			case 7:rMes=etiqueta[30].toLowerCase();break;
			case 8:rMes=etiqueta[31].toLowerCase();break;
			case 9:rMes=etiqueta[32].toLowerCase();break;
			case 10:rMes=etiqueta[33].toLowerCase();break;
			case 11:rMes=etiqueta[34].toLowerCase();break;
			case 12:rMes=etiqueta[35].toLowerCase();break;
		}
		return rMes;
	}
	
	/*************Al hacer click en pasos arriba**************/
	@Command
	@NotifyChange({"visiblePaso1","visiblePaso2","visiblePaso3","visibleBarraPaso1","visibleBarraPaso2","visibleBarraPaso3",
		"estiloPaso1","estiloPaso2","estiloPaso3","visibleNroPaso1","visibleNroPaso2","visibleNroPaso3","paso2",
		"visibleBtnSiguiente","visibleBtnAtras"})
	public void cambioBarraPaso(@BindingParam("nroPaso")String nroPaso,@BindingParam("comp")Component comp){
		if(nroPaso.equals("1")){
				visibleBtnAtras=false;
				visibleBtnSiguiente=true;
				setVisiblePaso1(true);/***********visibilidad de contenido de paso****************/
				setVisiblePaso2(false);
				paso2=false;
				setVisiblePaso3(false);
				visibleBarraPaso1=true;
				visibleBarraPaso2=false;
				visibleBarraPaso3=false;
		}else if(nroPaso.equals("2")){
			if(validoPaso1_pricingPasos(comp)){
				visibleBtnAtras=true;
				visibleBtnSiguiente=true;
				setVisiblePaso1(false);
				setVisiblePaso2(true);
				paso2=true;
				setVisiblePaso3(false);
				visibleBarraPaso1=false;
				visibleBarraPaso2=true;
				visibleBarraPaso3=false;
				visibleNroPaso1=true;/***********Aparece el check de paso terminado correctamente****************/
			}else return;
		}else if(nroPaso.equals("3")){
			if(validoPaso2_pricing_pasos(comp)){
				visibleBtnAtras=true;
				visibleBtnSiguiente=false;
				setVisiblePaso1(false);
				setVisiblePaso2(false);
				paso2=false;
				setVisiblePaso3(true);
				visibleBarraPaso1=false;
				visibleBarraPaso2=false;
				visibleBarraPaso3=true;
				visibleNroPaso1=true;/***********Aparece el check de paso terminado correctamente****************/
				visibleNroPaso2=true;
			}else{
				visibleNroPaso1=true;/***********Aparece el check de paso terminado correctamente****************/
				visibleNroPaso2=false;
				return;
			}
		}
		
	}
	
	@Command
	@NotifyChange({"visiblePaso1","visiblePaso2","visiblePaso3","visibleBarraPaso1","visibleBarraPaso2","visibleBarraPaso3",
		"estiloPaso1","estiloPaso2","estiloPaso3","visibleNroPaso1","visibleNroPaso2","visibleNroPaso3","paso2",
		"visibleBtnSiguiente","visibleBtnAtras"})
	public void SiguientePaso(@BindingParam("nroPaso")String nroPaso,@BindingParam("comp")Component comp){
		if(nroPaso.equals("1")){
			if(validoPaso1_pricingPasos(comp)){
				visibleBtnAtras=true;
				setVisiblePaso1(false);
				setVisiblePaso2(true);
				paso2=true;
				setVisiblePaso3(false);
				visibleBarraPaso1=false;
				visibleBarraPaso2=true;
				visibleBarraPaso3=false;
				visibleNroPaso1=true;/***********Aparece el check de paso terminado correctamente****************/
				visibleNroPaso2=false;
				visibleNroPaso3=false;
			}else{return;}
		}else if(nroPaso.equals("2")){
			if(validoPaso2_pricing_pasos(comp)){
				setVisiblePaso1(false);
				setVisiblePaso2(false);
				paso2=false;
				setVisiblePaso3(true);
				visibleBarraPaso1=false;
				visibleBarraPaso2=false;
				visibleBarraPaso3=true;
				visibleNroPaso1=true;/***********Aparece el check de paso terminado correctamente****************/
				visibleNroPaso2=true;
				visibleNroPaso3=false;
				visibleBtnSiguiente=false;
			}else{
				visibleNroPaso1=true;/***********Aparece el check de paso terminado correctamente****************/
				visibleNroPaso2=false;
				visibleNroPaso3=false;
				return;
				}
		}
		
	}
	@Command
	@NotifyChange({"visiblePaso1","visiblePaso2","visiblePaso3","visibleBarraPaso1","visibleBarraPaso2","visibleBarraPaso3",
		"estiloPaso1","estiloPaso2","estiloPaso3","visibleNroPaso1","visibleNroPaso2","visibleNroPaso3","paso2",
		"visibleBtnSiguiente","visibleBtnAtras"})
	public void RetornarPaso(@BindingParam("nroPaso")String nroPaso,@BindingParam("comp")Component comp){
		 if(nroPaso.equals("2")){
				this.setVisiblePaso1(true);
				this.setVisiblePaso2(false);
				this.paso2=false;
				this.setVisiblePaso3(false);
				this.visibleBarraPaso1=true;
				this.visibleBarraPaso2=false;
				this.visibleBarraPaso3=false;
				visibleBtnSiguiente=true;
				visibleBtnAtras=false;
		}else if(nroPaso.equals("3")){
			this.setVisiblePaso1(false);
			this.setVisiblePaso2(true);
			this.paso2=true;
			this.setVisiblePaso3(false);
			this.visibleBarraPaso1=false;
			this.visibleBarraPaso2=true;
			this.visibleBarraPaso3=false;
			visibleBtnAtras=true;
			visibleBtnSiguiente=true;
		}
	}
	//================================
	public void reiniciarHoteles()
	{
		nroSimples=0;nroDobles=0;nroTriples=0;
		lblTotalPrecioSimple=df.format(0);lblTotalPrecioDoble=df.format(0);lblTotalPrecioTriple=df.format(0);
		TotalHabitaciones=0.0;
		oReservaPaqCatHotel.setConCamaAdicional(false);
		oReservaPaqCatHotel.setSinCamaAdicional(true);
//		oReservaPaqCatHotel.setPrecioCamaAdicional(df.format(0));
		montoTotal=TotalActividades+TotalHabitaciones+TotalServicios+TotalPaquete;
		lblMontoTotal=df.format(montoTotal);
		lblTotalHabitaciones=df.format(TotalHabitaciones);
		lblCategoriaSeleccionada="";
		validarNroHabPosibles();
		BindUtils.postNotifyChange(null, null, this,"lblTotalPrecioSimple");
		BindUtils.postNotifyChange(null, null, this,"lblTotalPrecioDoble");
		BindUtils.postNotifyChange(null, null, this,"lblTotalPrecioTriple");
		BindUtils.postNotifyChange(null, null, this,"lblTotalHabitaciones");
		BindUtils.postNotifyChange(null, null, this,"lblMontoTotal");
		BindUtils.postNotifyChange(null, null, this,"lblCategoriaSeleccionada");
		BindUtils.postNotifyChange(null, null, oReservaPaqCatHotel,"conCamaAdicional");
		BindUtils.postNotifyChange(null, null, oReservaPaqCatHotel,"sinCamaAdicional");
	}
	@Command
	@NotifyChange({"lblMonto","nroSimples",
		"nroDobles","nroTriples","lblMontoTotal",
		"lblTotalPaquete","paso2"})
	public void changeNroPersonas(@BindingParam("nroPersonas")Object nroPer) throws UnsupportedEncodingException
	{
		if(primeraVez)
		{
			primeraVez=false;
			oReservar.getoPaquete().cargarDatosRelacionadosAlPaquete();
			//Luego sse procede a inicializar los hoteles si es que hubiese
			iniciarHoteles();
			//Luego se procede a iniciar los servicios del paquete
			iniciarServicios();
			iniciarActividades();
			BindUtils.postNotifyChange(null, null, oReservar, "oPaquete");
		}
		confAltoNivelDAO.asignarListaConfAltoNivel(confAltoNivelDAO.recuperarconfAltoNivel("pricing"));
		setConfAltoNivel(confAltoNivelDAO.getoConfAltoNivel());
		if(confAltoNivel.isbEstado())
			paso2=false;
		else
			paso2=true;
		//============================================
		int n=Integer.parseInt(nroPer.toString());
		nroPasajeros=n;//actualizamos el nro de pasajeros
		if(n==1)
		{
			TotalPaquete=oReservar.getoPaquete().getnPrecioUno().doubleValue();
			lblMontoPaquete=df.format(oReservar.getoPaquete().getnPrecioUno().doubleValue());
		}
		if(n==2)
		{
			TotalPaquete=oReservar.getoPaquete().getnPrecioDos().doubleValue()*n;
			lblMontoPaquete=df.format(oReservar.getoPaquete().getnPrecioDos().doubleValue());
		}
		if(n==3)
		{
			TotalPaquete=oReservar.getoPaquete().getnPrecioTres().doubleValue()*n;
			lblMontoPaquete=df.format(oReservar.getoPaquete().getnPrecioTres().doubleValue());
		}
		if(n==4)
		{
			TotalPaquete=oReservar.getoPaquete().getnPrecioCuatro().doubleValue()*n;
			lblMontoPaquete=df.format(oReservar.getoPaquete().getnPrecioCuatro().doubleValue());
		}
		if(n>=5)
		{
			TotalPaquete=oReservar.getoPaquete().getnPrecioCinco().doubleValue()*n;
			lblMontoPaquete=df.format(oReservar.getoPaquete().getnPrecioCinco().doubleValue());
		}
		if(oReservaPaqCatHotel!=null)
			if(oReservaPaqCatHotel.isConHotel())
				for(CCategoriaConHoteles oCCH:listaCategoriaConHoteles)
				{
					oCCH.setColor(oCCH.COLOR_NO_SELECT);
					oCCH.setSelect(false);
					BindUtils.postNotifyChange(null, null, oCCH, "color");
				}
		nroSimples=0;nroDobles=0;nroTriples=0;
		reiniciarHoteles();
		reiniciarServicios();
		reiniciarActividades();
		lblTotalPaquete=df.format(TotalPaquete);
		//=======================
		System.out.println("-->"+TotalPaquete+"-->"+TotalHabitaciones+"-->"+TotalServicios);
		montoTotal=TotalActividades+TotalPaquete+TotalHabitaciones+TotalServicios;
		lblMontoTotal=""+df.format(montoTotal);
		//====================
		reiniciarReserva();
		updateRestricciones();
		validarNroHabPosibles();
		if(!oReservar.getoPaquete().isbLlenarDatosUnPax())
			updateListaPasajeros();
		if(oReservar.getoPaquete().isbSubirDoc_Y_LlenarDatosPax())
		{
			for(CPasajero pax:listaPasajeros)
			{
				if(pax.isSelectPasajero())
				{
					if(pax.isEsEdit())
					{
						montoTotal-=oReservar.getoPaquete().getnDescuentoMenor_Estudiante().doubleValue();
					}
				}
			}
			lblMontoTotal=df.format(montoTotal);
		}
		//====================
	}
	/**
	 * Metodo que realiza la actualizacion
	 * de las nuevas restricciones luego
	 * de realizar un evento de cambio
	 * en el numero de pasajeros
	 */
	public void updateRestricciones()
	{
		for(CServicio servicio:oReservar.getoPaquete().getListaServicios())
		{
			/**Si existe restriccion numerica se procede a modificar las restricciones**/
			if(servicio.getcRestriccionNum()>0)
			{
				/**Se limpian las restricciones anteriores**/
				servicio.getListaOpcionServicios().clear();
				/**Se procede a dar las nuevas restricciones**/
				String[] s=new String[2];
				s[0]=etiqueta[162];
				s[1]="0";
				servicio.getListaOpcionServicios().add(s);
//				if(listaServicios.get(i).getnServicioCod()==2)
//				{
//					String[] st=new String[2];
//					st[0]="0.5";
//					st[1]="0.5";
//					listaServicios.get(i).getListaOpcionServicios().add(st);
//				}
				int inc=servicio.getcIncremento();
				while(inc<=nroPasajeros*servicio.getcRestriccionNum())
				{
					String[] res=new String[2];
					res[0]=res[1]=""+inc;
					servicio.getListaOpcionServicios().add(res);
					inc+=servicio.getcIncremento();
				}
			}
		}
	}
	public void reiniciarServicios()
	{
		/**El monto total de los servicios vuelve a ser 0.0**/
		TotalServicios=0.0;
		lblTotalServicios=""+df.format(TotalServicios);
		/**Se reinicia la seleccion de las opciones**/
		/**Se cierran las descripciones de los servicios seleccionados**/
		for(CServicio servicio:oReservar.getoPaquete().getListaServicios())
		{
			servicio.setSelectOpcion("");
			servicio.setMostrarDescripcion(false);
			servicio.setCantidadServicio(0);
			servicio.setPrecioTotalServicio(df.format(0.0));
			servicio.setOpcionValue("0");
			BindUtils.postNotifyChange(null, null, servicio,"selectOpcion");
			BindUtils.postNotifyChange(null, null, servicio,"mostrarDescripcion");
			BindUtils.postNotifyChange(null, null, servicio,"cantidadServicio");
			BindUtils.postNotifyChange(null, null, servicio,"precioTotalServicio");
			BindUtils.postNotifyChange(null, null, servicio,"opcionValue");
		}
		BindUtils.postNotifyChange(null, null, this, "lblTotalServicios");
	}
	public void reiniciarActividades()
	{
		/**El monto total de los servicios vuelve a ser 0.0**/
		TotalActividades=0.0;
		lblTotalActividades=""+df.format(TotalActividades);
		/**Se reinicia la seleccion de las opciones**/
		/**Se cierran las descripciones de los servicios seleccionados**/
		
		for(CActividad acti:oReservar.getoPaquete().getListaActividades())
		{
			acti.setNroPersonasActividad(0);
			acti.setPrecioTotalActividad(df.format(0));
			BindUtils.postNotifyChange(null, null, acti,"nroPersonasActividad");
			BindUtils.postNotifyChange(null, null, acti,"precioTotalActividad");
		}
		BindUtils.postNotifyChange(null, null, this, "lblTotalActividades");
	}
	public void reiniciarReserva()
	{
		oReservar.setnNroPersonas(nroPasajeros);
		oReservar.setnPrecioPaquetePersona(TotalPaquete);
		listaReservaPaqServ=new ArrayList<CReservaPaqueteServicio>();
		//==============================================
		oReservaPaqCatHotel=new CReservaPaqueteCategoriaHotel();
		mostrarHoteles=false;
		BindUtils.postNotifyChange(null, null, this,"oReservar");
		BindUtils.postNotifyChange(null, null, this,"mostrarHoteles");
	}
	public boolean validoPaso1(Component comp)
	{
		boolean valido=true;
		if(oReservar.getoPaquete().getcDisponibilidad().equals("CAMINO_INKA_CLASICO"))
		{
			if(oReservar.getoPaquete().isConFechaArribo())
			{
				if(lblFechaArribo.getValue().equals(""))
				{
					valido=false;
					Clients.showNotification(etiqueta[245],Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
				}
			}
			else if(lblFechaInicioPaso3.getValue().equals(""))
			{
				valido=false;
				Clients.showNotification(etiqueta[165],Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
			}
			else if(listacFechasAlternas.isEmpty())
			{
				valido=false;
				Clients.showNotification(etiqueta[166],Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
			}
		}
		else
		{
			if(oReservar.getoPaquete().isConFechaArribo())
			{
				if(lblFechaArribo.getValue().equals(""))
				{
					valido=false;
					Clients.showNotification(etiqueta[245],Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
				}
			}
			else if(lblFechaInicioPaso3.getValue().equals(""))
			{
				valido=false;
				Clients.showNotification(etiqueta[183],Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
			}
			int nroPasajerosHab=oReservaPaqCatHotel.getnNroPersonasSimple()+
					oReservaPaqCatHotel.getnNroPersonasDoble()+
					oReservaPaqCatHotel.getnNroPersonasTriple();
			if(oReservaPaqCatHotel.isConHotel())
			{
				if(nroPasajerosHab<nroPasajeros)
				{
					valido=false;
					Clients.showNotification(etiqueta[167],Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
				}
			}
		}
		return valido;
	}
	public boolean validoPaso1_pricingPasos(Component comp)
	{
		boolean valido=true;
		if(oReservar.getoPaquete().getcDisponibilidad().equals("CAMINO_INKA_CLASICO"))
		{
			if(oReservar.getoPaquete().isConFechaArribo())
			{
				if(lblFechaArribo.getValue().equals(""))
				{
					valido=false;
					Clients.showNotification(etiqueta[245],Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
				}
			}
			if(valido)
			{
				if(lblFechaInicioPaso3.getValue().equals(""))
				{
					valido=false;
					Clients.showNotification(etiqueta[165],Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
				}
				else if(listacFechasAlternas.isEmpty())
				{
					valido=false;
					Clients.showNotification(etiqueta[166],Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
				}
				if(nroPersonas2.getValue().isEmpty()){
					valido=false;
					Clients.showNotification(etiqueta[240],Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
				}
			}
		}
		else if(oReservar.getoPaquete().getcDisponibilidad().equals("MANEJO_NORMAL"))
		{
			if(oReservar.getoPaquete().isConFechaArribo())
			{
				if(lblFechaArribo.getValue().equals(""))
				{
					valido=false;
					Clients.showNotification(etiqueta[245],Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
				}
			}
			if(valido)
			{
				if(lblFechaInicioPaso3.getValue().equals(""))
				{
					valido=false;
					Clients.showNotification(etiqueta[183],Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
				}
				int nroPasajerosHab=oReservaPaqCatHotel.getnNroPersonasSimple()+
						oReservaPaqCatHotel.getnNroPersonasDoble()+
						oReservaPaqCatHotel.getnNroPersonasTriple();
				if(oReservaPaqCatHotel.isConHotel())
				{
					if(nroPasajerosHab<nroPasajeros)
					{
						valido=false;
						Clients.showNotification(etiqueta[167],Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
					}
				}
				if(nroPersonas.getValue().isEmpty()){
					valido=false;
					Clients.showNotification(etiqueta[240],Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
				}
			}
		}else{
			if(oReservar.getoPaquete().isConFechaArribo())
			{
				if(lblFechaArribo.getValue().equals(""))
				{
					valido=false;
					Clients.showNotification(etiqueta[245],Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
				}
			}
			if(valido)
			{
				if(lblFechaInicioPaso3.getValue().equals(""))
				{
					valido=false;
					Clients.showNotification(etiqueta[165],Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
				}
				else if(listacFechasAlternas.isEmpty())
				{
					valido=false;
					Clients.showNotification(etiqueta[166],Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
				}
				if(nroPersonas2.getValue().isEmpty()){
					valido=false;
					Clients.showNotification(etiqueta[240],Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
				}
			}
		}
		return valido;
	}
	public boolean validoPaso2(Component comp)
	{
		boolean valido=true;
		if(nroPasajeros>0)
		{
			if(oReservar.getoPaquete().isbSubirDocPax())
			{
				valido=analizarSoloSubirDoc(comp);
			}else if(oReservar.getoPaquete().isbSubirDoc_Y_LlenarDatosPax())
			{
				valido=analizarLLenadoDatosYsubirDoc(comp);
			}else if(oReservar.getoPaquete().isbSubirDoc_O_LlenarDatosPax()){
				valido=analizarLLenadoDatos(comp);
			}
			if(valido)
				valido=analizarInformacionAdicional(comp);
		}
		else
		{
			valido=false;
			Clients.showNotification(etiqueta[210],Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
		}
		return valido;
	}
	public boolean validoPaso2_pricing_pasos(Component comp)
	{
		boolean valido=true;
		if(nroPasajeros>0)
		{
			if(oReservar.getoPaquete().isbSubirDocPax())
			{
				valido=analizarSoloSubirDoc(comp);
			}else if(oReservar.getoPaquete().isbSubirDoc_Y_LlenarDatosPax())
			{
				valido=analizarLLenadoDatosYsubirDoc(comp);
			}else if(oReservar.getoPaquete().isbSubirDoc_O_LlenarDatosPax()){
				valido=analizarLLenadoDatos(comp);
			}
			if(valido)
				valido=analizarInformacionAdicional(comp);
		}
		else
		{
			valido=false;
			Clients.showNotification(etiqueta[210],Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
		}
		int nroPasajerosHab=oReservaPaqCatHotel.getnNroPersonasSimple()+
				oReservaPaqCatHotel.getnNroPersonasDoble()+
				oReservaPaqCatHotel.getnNroPersonasTriple();
		if(oReservaPaqCatHotel.isConHotel())
		{
			if(nroPasajerosHab<nroPasajeros)
			{
				valido=false;
				Clients.showNotification(etiqueta[167],Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
			}
		}
		
		if(edadSuperada){
			valido=false;
			Clients.showNotification(etiqueta[243],Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
		}
		
		return valido;
	}
	/***
	 * analizarSoloSubirDoc es una funcion
	 * que verifica si se subio la imagen
	 * de documento de todos los pasajeros
	 * @param comp
	 * @return true si se subieron todos los documentos
	 */
	public boolean analizarSoloSubirDoc(Component comp){
		boolean valido=true;
		for(CPasajero pax:listaPasajeros)
		{
			if(pax.getnNro()>nroPasajeros)break;
			if(pax.getcUrlDocumento().equals(""))
			{
				valido=false;
				Clients.showNotification(etiqueta[174]+pax.getnNro(),Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
				break;
			}
		}
		return valido;
	}
	public boolean analizarLLenadoDatosYsubirDoc(Component comp)
	{
		boolean valido=true;
		for(CPasajero pax:listaPasajeros)
		{
			if(pax.getnNro()>nroPasajeros)break;
			String namePax=pax.getcNombres()+" "+pax.getcApellidos();
			if(pax.isEsEdit())//Es niño o estudiante
			{
				if(pax.getcUrlDocumento().equals(""))
				{
					valido=false;
					Clients.showNotification(etiqueta[174]+pax.getnNro()+": "+namePax,Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",3000);
					break;
				}
			}
			if(valido)
			{
//				if(pax.getnTipoDoc()==8)
//				{//No escogio ningun tipo de documento
//					valido=false;
//					Clients.showNotification(etiqueta[175]+pax.getnNro()+": "+namePax,Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",3000);
//					break;
//				}
				if(pax.getcNroDoc().equals(""))
				{
					valido=false;
					Clients.showNotification(etiqueta[177]+pax.getnNro()+": "+namePax,Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",3000);
					break;
				}
				else if(!nroDocValido(pax.getcNroDoc()))
				{
					valido=false;
					Clients.showNotification(etiqueta[177]+pax.getnNro()+": "+namePax,Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",3000);
					break;
				}
				else if(pax.getcApellidos().equals("") || pax.getcNombres().equals(""))
				{
					valido=false;
					Clients.showNotification(etiqueta[178]+pax.getnNro()+": "+namePax,Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",3000);
					break;
				}
				else if(!nombreApellidoValido(pax.getcNombres()) || !nombreApellidoValido(pax.getcApellidos()))
				{
					valido=false;
					Clients.showNotification(etiqueta[179]+pax.getnNro()+": "+namePax,Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",3000);
					break;
				}
				else if(pax.getcSexo().equals(""))
				{
					valido=false;
					Clients.showNotification(etiqueta[180]+pax.getnNro()+": "+namePax,Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",3000);
					break;
				}
				else if(!esNumero(pax.getTextoEdad()))
				{
					valido=false;
					Clients.showNotification(etiqueta[181]+pax.getnNro()+": "+namePax,Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",3000);
					break;
				}
				else if(Integer.parseInt(pax.getTextoEdad())<18)
				{
					if(!pax.isEsEdit())//No escogio como niño
					{
						valido=false;
						Clients.showNotification(etiqueta[67]+pax.getnNro()+": "+namePax,Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",3000);
						break;
					}
				}
				else if(pax.getnPaisCod()==245)//No se escogio ningun pais
				{
					valido=false;
					Clients.showNotification(etiqueta[182]+pax.getnNro()+": "+namePax,Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",3000);
					break;
				}
			}
		}
		return valido;
	}
	public boolean analizarLLenadoDatos(Component comp)
	{
		boolean valido=true;
		for(CPasajero pax:listaPasajeros)
		{
			if(pax.getnNro()>nroPasajeros)break;
			String namePax=pax.getcNombres()+" "+pax.getcApellidos();
//			if(pax.getnTipoDoc()==8)
//			{//No escogio ningun tipo de documento
//				valido=false;
//				Clients.showNotification(etiqueta[175]+pax.getnNro()+": "+namePax,Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
//				break;
//			}
			if(pax.getcNroDoc().equals(""))
			{
				valido=false;
				Clients.showNotification(etiqueta[177]+pax.getnNro()+": "+namePax,Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
				break;
			}
			else if(!nroDocValido(pax.getcNroDoc()))
			{
				valido=false;
				Clients.showNotification(etiqueta[177]+pax.getnNro()+": "+namePax,Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
				break;
			}
			else if(pax.getcApellidos().equals("") || pax.getcNombres().equals(""))
			{
				valido=false;
				Clients.showNotification(etiqueta[178]+pax.getnNro()+": "+namePax,Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
				break;
			}
			else if(!nombreApellidoValido(pax.getcNombres()) || !nombreApellidoValido(pax.getcApellidos()))
			{
				valido=false;
				Clients.showNotification(etiqueta[179]+pax.getnNro()+": "+namePax,Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
				break;
			}
			else if(pax.getcSexo().equals(""))
			{
				valido=false;
				Clients.showNotification(etiqueta[180]+pax.getnNro()+": "+namePax,Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
				break;
			}
			else if(!esNumero(pax.getTextoEdad()))
			{
				valido=false;
				Clients.showNotification(etiqueta[181]+pax.getnNro()+": "+namePax,Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
				break;
			}
			else if(pax.getnPaisCod()==245)
			{
				valido=false;
				Clients.showNotification(etiqueta[182]+pax.getnNro()+": "+namePax,Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
				break;
			}
		}
		return valido;
	}
	public boolean analizarInformacionAdicional(Component comp)
	{
		boolean valido=true;
		if(oReservar.getcContacto().equals("") || oReservar.getcEmail().equals(""))
		{
			valido=false;
			Clients.showNotification(etiqueta[169],Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
		}
		else if(!nombreApellidoValido(oReservar.getcContacto()))
		{
			valido=false;
			Clients.showNotification(etiqueta[170],Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
		}else if(!mailValido(oReservar.getcEmail()))
		{
			valido=false;
			Clients.showNotification(etiqueta[171],Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
		}else if(oReservar.getoPaquete().isbLlenarDatosUnPax())
		{
			if(oReservar.getoPasajeroReservante().getnPaisCod()==245)
			{
				valido=false;
				Clients.showNotification(etiqueta[182],Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
			}
		}
		return valido;
	}
	public boolean esNumero(String n)
	{
		try {
			Integer.parseInt(n);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	public boolean nroDocValido(String num)
	{
		boolean correcto=true;
		 for(int i=0;i<num.length();i++)
		 {
			 if((num.charAt(i)<'A' || num.charAt(i)>'Z') && (num.charAt(i)>'9' || num.charAt(i)<'0') && num.charAt(i)!='-' )
			 {
				 correcto=false;
				 break;
			 }
		 }
		 return correcto;
	}
	public boolean nombreApellidoValido(String nombre)
	{
		System.out.println("Este es el nombre del contacto--> "+nombre);
		boolean correcto=true;
		for(int i=0;i<nombre.length();i++)
		{
			if((nombre.charAt(i)<'A' || nombre.charAt(i)>'Z') && nombre.charAt(i)!=' ' &&
					nombre.charAt(i)!='\u00c7' && nombre.charAt(i)!='\u00c3' && 
					nombre.charAt(i)!='\u00c2' && nombre.charAt(i)!='\u00c1' &&
					nombre.charAt(i)!='\u00c0' && nombre.charAt(i)!='\u00d5' &&
					nombre.charAt(i)!='\u00d4' && nombre.charAt(i)!='\u00d3' &&
					nombre.charAt(i)!='\u00ca' && nombre.charAt(i)!='\u00c9' &&
					nombre.charAt(i)!='\u00cd' && nombre.charAt(i)!='\u00c3' &&
					nombre.charAt(i)!='\u00da')
			{
				correcto=false;
				break;
			}
		}
		return correcto;
	}
	public boolean mailValido(String mail)
	{
		 Pattern pat = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	       Matcher mat = pat.matcher(mail);
	       if(mat.find()){
	          return true;
	       }else
	          return false;
	}
	public boolean telfValido(String telf)
	{
		boolean correcto=true;
		for(int i=0;i<telf.length();i++)
		{
			if((telf.charAt(i)<'0' || telf.charAt(i)>'9') && telf.charAt(i)!=' ' && telf.charAt(i)!='-')
			{
				correcto=false;
				break;
			}
		}
		return correcto;
	}
	@Command
	public void irATerminos_Condiciones()
	{
		try
		{
			Execution exec = Executions.getCurrent();
			exec.sendRedirect(etiqueta[83],"_blank");
			exec.setVoided(true);
		}
		catch(Exception e)
		{
			
		}
	}
	@Command
	public void irAEddyOnSoft()
	{
		try
		{
			Execution exec = Executions.getCurrent();
			exec.sendRedirect("http://www.eddyonsoft.com","_blank");
			exec.setVoided(true);
		}
		catch(Exception e)
		{
			
		}
	}
	@Command
	@NotifyChange({"acceptedTermsAndCond","txtTerminosCond","payment","mostrarPaypal"})
	public void checkTermsAndCond()
	{
		acceptedTermsAndCond=!acceptedTermsAndCond;
	}
	@Command
	@NotifyChange({"paso4"})
	public void next4()
	{
		paso4=true;
	}
	@Command
	@NotifyChange({"payment","reservar","lblMontoTotal"})
	public void aceptar_Reservar_Pagar(@BindingParam("componente")Component comp,@BindingParam("opcion")int opcion)
	{
//		CEmail mail=new CEmail();
//		mail.enviarCorreoImages();
		if(validoPaso1(comp) && validoPaso2(comp))
		{
			if(acceptedTermsAndCond)
			{
				if(opcion==1)
				{
					payment=false;
					reservar=true;
					if(oReservar.getoCupon().isOkCupon())
					{
						lblMontoTotal=oReservar.getoCupon().getTotalAnterior();
						montoTotal=Double.parseDouble(lblMontoTotal);
						oReservar.setoCupon(new CCupon());
						oReservar.getoCupon().inicioAplicarCupon();
						BindUtils.postNotifyChange(null, null, oReservar, "oCupon");
					}
				}else
				{
					payment=true;
					reservar=false;
				}
			}
			else
				Clients.showNotification(etiqueta[2],Clients.NOTIFICATION_TYPE_WARNING, comp,"before_start",2700);
		}
	}
	@Command
	@NotifyChange({"paso4","oReservar"})
	public void Reservar() throws IOException, DocumentException
	{
		CReservaDAO reservaDao=new CReservaDAO();
		String[] resultado=reservaDao.recuperarResultados(reservaDao.registrarReserva(oReservar));
		//Se asigna el codigo de reserva al objeto oReserva
		oReservar.setcReservaCod(resultado[1]);
		
		listaUrlImages=new ArrayList<String>();
		for(CPasajero p:listaPasajeros)
		{
			if(p.isEsEdit() && p.isSelectPasajero())
			{
				listaUrlImages.add(ScannUtil.getPath()+p.getcUrlDocumento());
			}
		}
		/**CALCULANDO LOS MONTOS A PAGAR**/
		String pagoParcial="";
		if(oReservar.getoPaquete().isbModoPorcentual())
			pagoParcial=df.format(Double.parseDouble(lblMontoTotal)*((double)oReservar.getoPaquete().getnPorcentajeCobro()/100));
		else
		{
			pagoParcial=df.format(TotalServicios+TotalHabitaciones+TotalActividades+(oReservar.getoPaquete().getnPagoMinimo()*nroPasajeros));
		}
		/**ENVIAR CORREO**/
		CEmail mail=new CEmail();
		boolean b=mail.enviarCorreoSinPago(etiqueta[184],language,oImpuesto,etiqueta,oReservaPaqCatHotel,lblFechaInicioPaso3.getValue(),lblFechaFinPaso3.getValue(),lblFechaArribo.getValue(),oReservar,listacFechasAlternas,lblMontoTotal,pagoParcial,urlPdf,listaUrlImages,listaPasajeros);
		/********Insertamos la ReservaPaquete*********/
		CReservaPaquete oReservaPaquete=new CReservaPaquete();
		oReservaPaquete.setcReservaCod(oReservar.getcReservaCod());
		oReservaPaquete.setcPaqueteCod(codigoPaquete);
		oReservaPaquete.setNroPasajerosPaquete(nroPasajeros);
		oReservaPaquete.setnMontoTotalPaquete(TotalPaquete);
		
		CReservaPaqueteDAO reservaPaqueteDao=new CReservaPaqueteDAO();
		Number codRP=reservaPaqueteDao.obtenerCodigoReservaPaquete(reservaPaqueteDao.insertarReservaPaquete(oReservaPaquete));
		/*****************/
		if(oReservaPaqCatHotel!=null)
			if(oReservaPaqCatHotel.isConHotel())
			{
				if(!oReservaPaqCatHotel.isConCamaAdicional())
					oReservaPaqCatHotel.setPrecioCamaAdicional("0.00");
				oReservaPaqCatHotel.setnReservaPaqueteCod(codRP.longValue());
				b=reservaPaqCatHotelDao.isCorrectOperation(reservaPaqCatHotelDao.insertarReservaPaqueteCatHotel(oReservaPaqCatHotel));
			}
		if(!listaReservaPaqServ.isEmpty())
		{
			for(CReservaPaqueteServicio rps:listaReservaPaqServ)
			{
				rps.setnReservaPaqueteCod(codRP.longValue());
				boolean correct=reservaPaqServDao.isCorrectOperation(reservaPaqServDao.insertarReservaPaqueteServicio(rps));
			}
		}
		if(!listaReservaPaqActi.isEmpty())
		{
			for(CReservaPaqueteActividad rpa:listaReservaPaqActi)
			{
				CReservaPaqueteActividadDAO rpaDao=new CReservaPaqueteActividadDAO();
				rpa.setnReservaPaqueteCod(codRP.longValue());
				boolean correcto=rpaDao.isCorrectOperation(rpaDao.insertarReservaPaqueteActividad(rpa));
			}
		}
		CPasajeroDAO pasajeroDao=new CPasajeroDAO();
		if(!oReservar.getoPaquete().isbLlenarDatosUnPax())
		{
			int i=1;
			for(CPasajero p:listaPasajeros)
			{
				if(i>nroPasajeros)break;
				p.setcReservaCod(oReservar.getcReservaCod());
				boolean f=pasajeroDao.isOperationCorrect(pasajeroDao.insertarPasajero(p));
				i++;
			}
		}else{
			oReservar.getoPasajeroReservante().setcNombres(oReservar.getcContacto());
			oReservar.getoPasajeroReservante().setcReservaCod(oReservar.getcReservaCod());
			boolean f=pasajeroDao.isOperationCorrect(pasajeroDao.insertarPasajero(oReservar.getoPasajeroReservante()));
		}
		if(!listaSeparadaFechasAlternas.isEmpty())
		{
			for(String[] fecha:listaSeparadaFechasAlternas)
			{
				if(!fecha[0].equals(""))
				{
					Calendar calendar=Calendar.getInstance();
					calendar.set(Integer.parseInt(fecha[2]),Integer.parseInt(obtenerNroMes(fecha[1]))-1,Integer.parseInt(fecha[0]));
					//Reservar
					fechaAlternaDao.isOperationCorrect(fechaAlternaDao.insertarFechaAlterna(oReservar.getcReservaCod(), calendar.getTime(),calendar.getTime()));
				}
			}
		}
		paso4=true;
	}
	@Command
	public void regresarPaginaPrincipal()
	{
		try
		{	
			Execution exec = Executions.getCurrent();
			exec.sendRedirect(configUrlDao.getoConfigUrl().getUrlPaginaWeb(),false);
		    exec.setVoided(true);
		}
		catch(Exception e)
		{
			
		}
	}
	@Command
	@NotifyChange({"lblMontoTotal"})
	public void selectSubirDocScanneado(@BindingParam("val")Object val,@BindingParam("pasajero")CPasajero pasajero)
	{
		String scan=val.toString();
		if(scan.equals("yes"))
		{
			if(oReservar.getoPaquete().isbSubirDoc_Y_LlenarDatosPax())
			{
				montoTotal-=oReservar.getoPaquete().getnDescuentoMenor_Estudiante().doubleValue();
				lblMontoTotal=df.format(montoTotal);
			}
			pasajero.setEsEdit(true);
			BindUtils.postNotifyChange(null, null, pasajero, "esEdit");
		}
		else
		{
			if(oReservar.getoPaquete().isbSubirDoc_Y_LlenarDatosPax())
			{
				montoTotal+=oReservar.getoPaquete().getnDescuentoMenor_Estudiante().doubleValue();
				lblMontoTotal=df.format(montoTotal);
			}
			pasajero.setEsEdit(false);
			pasajero.setcUrlDocumento("");
			BindUtils.postNotifyChange(null, null, pasajero, "cUrlDocumento");
			BindUtils.postNotifyChange(null, null, pasajero, "esEdit");
		}
	}
	@Command
	public void selectOpcionSexo(@BindingParam("sexo")Object val,@BindingParam("pasajero")CPasajero pasajero)
	{
		String sexo=val.toString();
		pasajero.setcSexo(sexo);
		System.out.println("este es el sexo-> "+pasajero.getcSexo()+" y "+pasajero.getnNro());
	}
	@Command
	public void changeEdad(@BindingParam("edad")String edad,@BindingParam("pasajero")CPasajero p,@BindingParam("idCbEdad")Component comp)
	{
		boolean correcto=true;
		for(int i=0;i<edad.length();i++)
		{
			if(edad.charAt(i)!='0' && edad.charAt(i)!='1' && edad.charAt(i)!='2'
					&& edad.charAt(i)!='3' && edad.charAt(i)!='4' && edad.charAt(i)!='5'
					&& edad.charAt(i)!='6' && edad.charAt(i)!='7' && edad.charAt(i)!='8'
					&& edad.charAt(i)!='9')
			{
				edadSuperada=true;
				Clients.showNotification(etiqueta[181],Clients.NOTIFICATION_TYPE_ERROR,comp,"end_before",2700);
				correcto=false;	
			}
		}
		if(correcto)
		{
			int n=Integer.parseInt(edad);
			if(n<11 || n>99)
			{
				edadSuperada=true;
				Clients.showNotification(etiqueta[185],Clients.NOTIFICATION_TYPE_ERROR,comp,"end_before",2700);
				correcto=false;
			}
			if(correcto){
					edadSuperada=false;
					p.setnEdad(Integer.parseInt(edad));
				}
		}
	}
	@Command
	public void selectCodPais(@BindingParam("codPais")Object cod,@BindingParam("pasajero")CPasajero p,@BindingParam("idCbPais")Component comp)
	{
		if(cod==null)
		{
			p.setnPaisCod(245);//codigo por defecto de pais
			Clients.showNotification(etiqueta[182],Clients.NOTIFICATION_TYPE_ERROR,comp,"end_before",2700);
		}
		else
		{
			System.out.println("este es el codigo del pais: "+cod.toString()+"-->");
			int codPais=Integer.parseInt(cod.toString());
			p.setnPaisCod(codPais);
		}
	}
	@Command
	public void selectPaisReservante(@BindingParam("codPais")Object cod,
			@BindingParam("idCbPais")Component comp)
	{
		if(cod==null)
		{
			oReservar.getoPasajeroReservante().setnPaisCod(245);//codigo por defecto de pais
			Clients.showNotification(etiqueta[182],Clients.NOTIFICATION_TYPE_ERROR,comp,"end_before",2700);
		}
		else
		{
			int codPais=Integer.parseInt(cod.toString());
			oReservar.getoPasajeroReservante().setnPaisCod(codPais);
		}
	}
	@Command
	public void selectTipoDoc(@BindingParam("codTipoDoc")Object cod,@BindingParam("nameDoc")String nombreDoc,@BindingParam("pasajero")CPasajero p,@BindingParam("idCbTipoDoc")Component comp)
	{
		if(cod==null)
		{
			p.setnTipoDoc(8);//Codigo por defecto del tipo de documento
			Clients.showNotification(etiqueta[175],Clients.NOTIFICATION_TYPE_ERROR,comp,"end_before",2700);
		}
		else
		{
			int codTipoDoc=Integer.parseInt(cod.toString());
			p.setTipoDocumento(nombreDoc);
			p.setnTipoDoc(codTipoDoc);
		}
	}
	@Command
	public void changeNroDoc(@BindingParam("nro")String nroDoc,@BindingParam("idTbNroDoc")Component comp,@BindingParam("pasajero")CPasajero p)
	{
		nroDoc=nroDoc.toUpperCase();
		System.out.println("Este es el nuro del doc->"+nroDoc);
		if(!nroDocValido(nroDoc))
		{
			Clients.showNotification(etiqueta[177],Clients.NOTIFICATION_TYPE_ERROR,comp,"end_before",2500);
		}
		else
			p.setcNroDoc(nroDoc);
	}
	@Command
	public void changeApellido(@BindingParam("valor")String nombre,@BindingParam("idTbNombre")Component comp,@BindingParam("pasajero")CPasajero p)
	{
		nombre=nombre.toUpperCase();
		if(!nombreApellidoValido(nombre))
		{
			Clients.showNotification(etiqueta[186],Clients.NOTIFICATION_TYPE_ERROR,comp,"end_before",2500);
		}
		else
		{
			p.setcApellidos(nombre);
			p.setNombres_Apellidos(p.getcNombres()+" "+p.getcApellidos());
			BindUtils.postNotifyChange(null, null, p, "nombres_Apellidos");
		}
	}
	@Command
	public void changeNombre(@BindingParam("valor")String nombre,@BindingParam("idTbNombre")Component comp,@BindingParam("pasajero")CPasajero p)
	{
		nombre=nombre.toUpperCase();
		if(!nombreApellidoValido(nombre))
		{
			Clients.showNotification(etiqueta[187],Clients.NOTIFICATION_TYPE_ERROR,comp,"end_before",2500);
		}
		else
		{
			p.setcNombres(nombre);
			p.setNombres_Apellidos(p.getcNombres()+" "+p.getcApellidos());
			BindUtils.postNotifyChange(null, null, p, "nombres_Apellidos");
		}
	}
	@Command
	public void changeContacto(@BindingParam("contac")String Contacto,@BindingParam("idTbContacto")Component comp)
	{
		Contacto=Contacto.toUpperCase();
		if(!nombreApellidoValido(Contacto))
		{
			Clients.showNotification(etiqueta[188],Clients.NOTIFICATION_TYPE_ERROR,comp,"end_before",2500);
		}
		else
			oReservar.setcContacto(Contacto);
	}
	@Command
	public void changeMail(@BindingParam("mail")String mail,@BindingParam("idTbMail")Component comp)
	{
		if(!mailValido(mail))
		{
			Clients.showNotification(etiqueta[171],Clients.NOTIFICATION_TYPE_ERROR,comp,"end_before",2500);	
		}
	}
	@Command
	public void changeTelefono(@BindingParam("telefono")String telf,@BindingParam("idTbTelf")Component comp)
	{
		if(!telfValido(telf))
		{
			Clients.showNotification(etiqueta[172],Clients.NOTIFICATION_TYPE_ERROR,comp,"end_before",2500);	
		}
	}
	/**
	 * CARGAR LA IMAGEN SCANNEADA DEL DOCUMENTO DE IDENTIFICACION DEL PASAJERO
	 * @param nroPasajero
	 */
	@Command
	public void uploadFile(@BindingParam("pasajero")final CPasajero pasajero,@BindingParam("compUpload")final Component componente) {
			 Fileupload.get(new EventListener<UploadEvent>(){
					public void onEvent(UploadEvent event) {
						org.zkoss.util.media.Media media = event.getMedia();
						if (media instanceof org.zkoss.image.Image) {
							org.zkoss.image.Image img = (org.zkoss.image.Image) media;
							//Con este metodo(uploadFile) de clase guardo la imagen en la ruta del servidor
				            boolean b=ScannUtil.uploadFile(img);
				            //================================
				            String nuevoNombreGenerado=nombreDoc+(pasajero.getnNro())+"."+media.getFormat();
				            //Una vez creado el nuevo nombre de archivo de imagen se procede a cambiar el nombre
				            File imgGuardado=new File(ScannUtil.getPath()+img.getName());
				            File imgAux=new File(ScannUtil.getPath()+nuevoNombreGenerado);
				            if(imgAux.exists())
				            {	System.out.println("El archivo existe");
				            	if(imgAux.delete())System.out.println("Se elimino el archivo existente");
				            }
				            File imgNewName=new File(ScannUtil.getPath()+nuevoNombreGenerado);
				            if(imgGuardado.renameTo(imgNewName))
				            	System.out.println("Se cambio correctaente el nombre-->");
				            else
				            	System.out.println("No se guardo correctamente el  nombre-->");
				            asignarUrlDocumentoPasajero(nuevoNombreGenerado,pasajero);
				            Clients.showNotification(etiqueta[189]+img.getName()+etiqueta[190],Clients.NOTIFICATION_TYPE_INFO,componente,"before_start",3000);
						} else {
							Messagebox.show(etiqueta[191]+media, "Error", Messagebox.OK, Messagebox.ERROR);
								}
					}
			     });
	}
	public void asignarUrlDocumentoPasajero(String url,CPasajero pasajero)
	{
		System.out.println("==>:::"+url);
		pasajero.setcUrlDocumento(url);
		pasajero.setcUrlMostrarDocumento("/DocumentosScanneados/"+url);
		BindUtils.postNotifyChange(null, null, pasajero,"cUrlDocumento");
		BindUtils.postNotifyChange(null, null, pasajero,"cUrlMostrarDocumento");
	}
	public String generarNombreImagen()
	{
		//=========================
		File directorio=new File(ScannUtil.getPath());
		String[] imagenes=directorio.list();
		//=================================
		String nuevoNombre="";
		if(imagenes!=null)
			nuevoNombre="Doc_"+imagenes.length+"_";
		return nuevoNombre;
	}
	//========================================================
	@Command
	@NotifyChange({"mostrarPrecios"})
	public void cerrarPreciosHotel()
	{
		mostrarPrecios=false;
	}
	@Command
	@NotifyChange({"mostrarHoteles"})
	public void desglozarHoteles(@BindingParam("opcion")Object opcion)
	{
		reiniciarHoteles();
		if(opcion.toString().equals("si"))
		{
			mostrarHoteles=true;
			//========================
			oReservaPaqCatHotel=new CReservaPaqueteCategoriaHotel();
			oReservaPaqCatHotel.setConHotel(true);
		}
		else
		{
			mostrarHoteles=false;
			oReservaPaqCatHotel=new CReservaPaqueteCategoriaHotel();
			oReservaPaqCatHotel.setConHotel(false);
		}
	}
	@Command
	@NotifyChange({"mostrarPrecios","nroSimples","nroDobles","nroTriples"})
	public void cancelarNroPersonasHab()
	{
		nroSimples=0;nroDobles=0;nroTriples=0;
		reiniciarHoteles();
		mostrarPrecios=false;
	}
	@Command
	@NotifyChange({"mostrarPrecios"})
	public void aceptarNroPersonasHab()
	{
		int nroPersonas=nroSimples+nroDobles*2+nroTriples*3;
		if(oReservaPaqCatHotel.isConCamaAdicional())
			nroPersonas++;
		if(nroPersonas>=nroPasajeros)
			mostrarPrecios=false;
		else
			Clients.showNotification(etiqueta[167], Clients.NOTIFICATION_TYPE_ERROR, null,"top_center", 2700);
	}
	@Command
	@NotifyChange({"lblTotalHabitaciones","lblMontoTotal","lblTotalPrecioSimple",
		"lblTotalPrecioDoble","lblTotalPrecioTriple","oReservaPaqCatHotel"})
	public void changePersonHabitacion(@BindingParam("hab")int habitacion,
									   @BindingParam("nroPer")Object nroPersonas)
	{
		if(habitacion==1)nroSimples=Integer.parseInt(nroPersonas.toString());
		if(habitacion==2)
		{
			nroDobles=Integer.parseInt(nroPersonas.toString());
			if(nroDobles>0)
			{
				oReservaPaqCatHotel.setDisableCamaAdicional(false);
				oReservaPaqCatHotel.setColorCamaAdicional(oReservaPaqCatHotel.COLOR_ACTIVO);
			}
			else if(nroDobles==0)
			{
				oReservaPaqCatHotel.setDisableCamaAdicional(true);
				oReservaPaqCatHotel.setConCamaAdicional(false);
				oReservaPaqCatHotel.setColorCamaAdicional(oReservaPaqCatHotel.COLOR_DESACTIVO);
			}
		}
		if(habitacion==3)nroTriples=Integer.parseInt(nroPersonas.toString());
		//=======
		validarNroHabPosibles();
		//===================
		lblTotalPrecioSimple=df.format(Double.parseDouble(lblPrecioSimple)*nroSimples);
		lblTotalPrecioDoble=df.format(Double.parseDouble(lblPrecioDoble)*nroDobles*2);
		lblTotalPrecioTriple=df.format(Double.parseDouble(lblPrecioTriple)*nroTriples*3);
		//===================
		if(oReservaPaqCatHotel.isConCamaAdicional())
			TotalHabitaciones=Double.parseDouble(lblTotalPrecioSimple)+Double.parseDouble(oReservaPaqCatHotel.getPrecioCamaAdicional())+
				Double.parseDouble(lblTotalPrecioDoble)+Double.parseDouble(lblTotalPrecioTriple);
		else
			TotalHabitaciones=Double.parseDouble(lblTotalPrecioSimple)+
				Double.parseDouble(lblTotalPrecioDoble)+Double.parseDouble(lblTotalPrecioTriple);
		lblTotalHabitaciones=df.format(TotalHabitaciones);
		//==============
		montoTotal=TotalActividades+TotalHabitaciones+TotalPaquete+TotalServicios;
		lblMontoTotal=df.format(montoTotal);
		//===============
		oReservaPaqCatHotel.setnNroPersonasSimple(nroSimples);
		oReservaPaqCatHotel.setnPrecioTotalSimple(Double.parseDouble(lblTotalPrecioSimple));
		oReservaPaqCatHotel.setnNroPersonasDoble(nroDobles*2);
		oReservaPaqCatHotel.setnPrecioTotalDoble(Double.parseDouble(lblTotalPrecioDoble));
		oReservaPaqCatHotel.setnNroPersonasTriple(nroTriples*3);
		oReservaPaqCatHotel.setnPrecioTotalTriple(Double.parseDouble(lblTotalPrecioTriple));
	}
	public void recuperarPreciosHabitaciones(int codCate)
	{
		lblPrecioSimple=listaCategoriaConHoteles.get(codCate-1).getPrecioSimple();
		lblPrecioDoble=listaCategoriaConHoteles.get(codCate-1).getPrecioDoble();
		lblPrecioTriple=listaCategoriaConHoteles.get(codCate-1).getPrecioTriple();
		BindUtils.postNotifyChange(null, null, this, "lblPrecioSimple");
		BindUtils.postNotifyChange(null, null, this, "lblPrecioDoble");
		BindUtils.postNotifyChange(null, null, this, "lblPrecioTriple");
	}
	public String recuperarCodPaqueteCategoria(int i)
	{
		String cod="";
		System.out.println("aqui otro---> "+listaPaqueteCatHotel.size());
		for(int k=0;k<listaPaqueteCatHotel.size();k++)
		{
			if(listaPaqueteCatHotel.get(k).getCategoriaHotelCod()==i)
			{
				cod=listaPaqueteCatHotel.get(k).getCodPaqueteCategoriaH();
				System.out.println("aqui---> "+cod);
				break;
			}
		}
		return cod;
	}
	@Command
	@NotifyChange({"lblMontoTotal","lblTotalHabitaciones","oReservaPaqCatHotel",
		"restSimple","restDoble","restTriple"})
	public void selectCamaAdicional(@BindingParam("val")Object valor)
	{
		double aux=Double.parseDouble(oReservaPaqCatHotel.getPrecioCamaAdicional());
		if(valor.toString().equals("si"))
		{
			montoTotal+=aux;
			TotalHabitaciones+=aux;
			lblTotalHabitaciones=df.format(TotalHabitaciones);
			lblMontoTotal=df.format(montoTotal);
			oReservaPaqCatHotel.setConCamaAdicional(true);
			oReservaPaqCatHotel.setSinCamaAdicional(false);
		}
		else
		{
			montoTotal-=aux;
			TotalHabitaciones-=aux;
			lblMontoTotal=df.format(montoTotal);
			lblTotalHabitaciones=df.format(TotalHabitaciones);
			oReservaPaqCatHotel.setConCamaAdicional(false);
			oReservaPaqCatHotel.setSinCamaAdicional(true);
		}
	}
	@Command
	@NotifyChange({"oReservaPaqCatHotel","mostrarPrecios","nroSimples","nroDobles","nroTriples"})
	public void clickOpcionCategoria(@BindingParam("CConHoteles")CCategoriaConHoteles CCH)
	{
		//===========================
		if(!CCH.isSelect())
		{
			nroSimples=0;nroDobles=0;nroTriples=0;
			reiniciarHoteles();
			CCH.setSelect(true);
			lblCategoriaSeleccionada=CCH.getNameCategory();
			recuperarPreciosHabitaciones(CCH.getCodCat());
			oReservaPaqCatHotel.setCodPaqueteCategoriaH(recuperarCodPaqueteCategoria(CCH.getCodCat()));
			oReservaPaqCatHotel.setCategoria(CCH.getNameCategory());
			oReservaPaqCatHotel.setListaCategoriaDestinosHoteles(CCH.getListaCategoriaDestinosHoteles());
			oReservaPaqCatHotel.setPrecioCamaAdicional(CCH.getPrecioCamaAdicional());
			CCH.setColor(CCH.COLOR_SELECT);
			BindUtils.postNotifyChange(null, null, CCH, "color");
			for(CCategoriaConHoteles oCCH:listaCategoriaConHoteles)
			{
				if(!oCCH.equals(CCH))
				{
					oCCH.setColor(oCCH.COLOR_NO_SELECT);
					oCCH.setSelect(false);
					BindUtils.postNotifyChange(null, null, oCCH, "color");
				}
			}
		}
		//===========================
		mostrarPrecios=true;
	}
	public void validarNroHabPosibles()
	{
		restSimple=new ArrayList<String>();
		restDoble=new ArrayList<String>();
		restTriple=new ArrayList<String>();
		int totalPersonas=nroSimples+nroDobles*2+nroTriples*3;
		System.out.println("Se tiene nro personas xhab.--> "+totalPersonas);
		if((nroSimples+nroPasajeros-totalPersonas)==0)
			restSimple.add("0");
		else
		{
			restSimple.add("0");
			restSimple.add(""+(nroSimples+nroPasajeros-totalPersonas));
		}
//		for(int i=0;i<=(nroSimples+nroPasajeros-totalPersonas);i++)
//			restSimple.add(""+i);
		for(int i=0;i<=(nroDobles+(nroPasajeros-totalPersonas)/2);i++)
			restDoble.add(""+i);
		for(int i=0;i<=(nroTriples+(nroPasajeros-totalPersonas)/3);i++)
			restTriple.add(""+i);
		//===================================
		if(restSimple.size()==1)
		{
			oReservaPaqCatHotel.setColorSimple(oReservaPaqCatHotel.COLOR_DESACTIVO);
			oReservaPaqCatHotel.setDisableSimple(true);
		}
		else
		{
			oReservaPaqCatHotel.setColorSimple(oReservaPaqCatHotel.COLOR_ACTIVO);
			oReservaPaqCatHotel.setDisableSimple(false);
		}
		if(restDoble.size()==1)
		{
			oReservaPaqCatHotel.setColorDoble(oReservaPaqCatHotel.COLOR_DESACTIVO);
			oReservaPaqCatHotel.setDisableDoble(true);
		}
		else
		{
			oReservaPaqCatHotel.setColorDoble(oReservaPaqCatHotel.COLOR_ACTIVO);
			oReservaPaqCatHotel.setDisableDoble(false);
		}
		if(restTriple.size()==1)
		{
			oReservaPaqCatHotel.setColorTriple(oReservaPaqCatHotel.COLOR_DESACTIVO);
			oReservaPaqCatHotel.setDisableTriple(true);
		}
		else
		{
			oReservaPaqCatHotel.setColorTriple(oReservaPaqCatHotel.COLOR_ACTIVO);
			oReservaPaqCatHotel.setDisableTriple(false);
		}
		//===================================
		BindUtils.postNotifyChange(null, null, this,"restSimple");
		BindUtils.postNotifyChange(null, null, this,"restDoble");
		BindUtils.postNotifyChange(null, null, this,"restTriple");
		BindUtils.postNotifyChange(null, null, this,"oReservaPaqCatHotel");
	}
	public String recuperarNombreDestino(int codDestino)
	{
		String name="";
		for(CDestino destino:oReservar.getoPaquete().getListaDestinos())
		{
			if(destino.getnDestinoCod()==codDestino)
			{
				name=destino.getcDestino();
				break;
			}
		}
		return name;
	}
	@Command
	public void agregarCupon()
	{
		oReservar.getoCupon().agregarCupon();
		BindUtils.postNotifyChange(null, null, oReservar, "oCupon");
	}
	@Command
	public void eliminarCupon()
	{
		oReservar.getoCupon().eliminarCupon();
		BindUtils.postNotifyChange(null, null, oReservar, "oCupon");
	}
	@Command
	public void cancelarCupon()
	{
		oReservar.getoCupon().cancelarCupon();
		BindUtils.postNotifyChange(null, null, oReservar, "oCupon");
	}
	@Command
	public void aplicarCupon()
	{
		CCuponDAO cuponDao=new CCuponDAO();
		List lista=cuponDao.recuperarCuponBD(oReservar.getoCupon().getcCupon());
		if(!lista.isEmpty())
		{
			cuponDao.asignarCupon(lista);
			oReservar.setoCupon(cuponDao.getoCupon());
			aplicarDescuento();
			oReservar.getoCupon().aplicarOk();
		}else
		{
			oReservar.getoCupon().aplicarError();
		}
		BindUtils.postNotifyChange(null, null, oReservar, "oCupon");
	}
	public void aplicarDescuento()
	{
		oReservar.getoCupon().setTotalAnterior(lblMontoTotal);
		oReservar.getoCupon().setTotalAhorro(df.format((montoTotal*oReservar.getoCupon().getnPorcentajeDcto()/100)));
		oReservar.getoCupon().setTotalNuevo(df.format(montoTotal-Double.parseDouble(oReservar.getoCupon().getTotalAhorro())));
		lblMontoTotal=oReservar.getoCupon().getTotalNuevo();
		montoTotal=Double.parseDouble(oReservar.getoCupon().getTotalNuevo());
		BindUtils.postNotifyChange(null, null, this,"lblMontoTotal");
	}
	@Command
	@NotifyChange({"mostrarPaypal","textoPorcentaje","urlPaypal","montoTotalPorcentual",
		"tax","totalConImpuestoPaypal","pagos"})
	public void selectPaypal(@BindingParam("valor")Object valor) throws IOException 
	{
		/**Se procede a asignar los valores a la session que se requeriran posteriormente**/
		seshttp.setAttribute("paquete",oReservar.getoPaquete());
		seshttp.setAttribute("fechaInicio",lblFechaInicioPaso3.getValue());
		seshttp.setAttribute("fechaFin",lblFechaFinPaso3.getValue());
		seshttp.setAttribute("fechaArribo",lblFechaArribo.getValue());
		seshttp.setAttribute("reserva", oReservar);
		seshttp.setAttribute("listaFechasAlternas",listacFechasAlternas);
		seshttp.setAttribute("listaReservaPaqServ",listaReservaPaqServ);
		seshttp.setAttribute("listaReservaPaqActi",listaReservaPaqActi);
		seshttp.setAttribute("listaPasajeros", listaPasajeros);
		seshttp.setAttribute("listaSeparadaFechasAlternas", listaSeparadaFechasAlternas);
		seshttp.setAttribute("oReservaPaqCatHotel", oReservaPaqCatHotel);
		seshttp.setAttribute("porcentajePago",valor.toString());
		seshttp.setAttribute("montoTotalSinImpuesto", lblMontoTotal);
		seshttp.setAttribute("formaPago",0);
		seshttp.setAttribute("etiqueta",etiqueta);
		seshttp.setAttribute("oImpuesto", oImpuesto);
		seshttp.setAttribute("codigoPaquete", codigoPaquete);
		seshttp.setAttribute("TotalPaquete", TotalPaquete);
		/**********************************************************************************/
		pagos.selectPaypal();
		seshttp.setAttribute("pagos",pagos);
		if(valor.toString().equals("1"))//Pago parcial ya sea minimo o porcentual
		{
			pagos.setPagoParcialPaypal(true);
			pagos.setPagoTotalPaypal(false);
			if(oReservar.getoPaquete().isbModoPorcentual())
				monto_Pagar_sin_impuesto=df.format(Double.parseDouble(lblMontoTotal)*((double)oReservar.getoPaquete().getnPorcentajeCobro()/100));
			else
			{
				monto_Pagar_sin_impuesto=df.format(TotalServicios+TotalHabitaciones+TotalActividades+oReservar.getoPaquete().getnPagoMinimo()*nroPasajeros);
			}
			if(oReservar.getoPaquete().isbModoPorcentual())
				textoPorcentaje=oReservar.getoPaquete().getnPorcentajeCobro()+" %";
			else
				textoPorcentaje=etiqueta[102];
		}
		else//al 100%
		{
			pagos.setPagoTotalPaypal(true);
			pagos.setPagoParcialPaypal(false);
			monto_Pagar_sin_impuesto=lblMontoTotal;
			if(oReservar.getoPaquete().isbModoPorcentual())
				textoPorcentaje="100 %";
			else
				textoPorcentaje=etiqueta[103];
		}
		seshttp.setAttribute("montoPagarSinImpuesto", monto_Pagar_sin_impuesto);
		
		pagos.setTaxPaypal(df.format(Double.parseDouble(monto_Pagar_sin_impuesto)*(Double.parseDouble(oImpuesto.getImpuestoPaypal())/100)));
		pagos.setTotalConImpuestoPaypal(df.format(Double.parseDouble(monto_Pagar_sin_impuesto)+Double.parseDouble(pagos.getTaxPaypal())));
		SECResult=new String[2];
		/*************************/
		ExpressCheckout paypal=new ExpressCheckout();
		SECResult=paypal.setExpressCheckoutTest(pagos.getTotalConImpuestoPaypal(),oReservar.getoPaquete().getcTituloIdioma1());
		/*************************/
		pagos.setUrlPaypal(SECResult[0]);
		System.out.println("La url de paypal-> "+urlPaypal);
		seshttp.setAttribute("token",SECResult[1]);
		Window win_paypal=(Window)Executions.createComponents("/montoPaymentPaypal.zul", null, null);
		win_paypal.doModal();
	}
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) throws WrongValueException, IOException
	{
		Selectors.wireComponents(view, this, false);
	}
	//=========ATRIBUTOS===========
		public boolean isPaso1() {
			return paso1;
		}
		public void setPaso1(boolean paso1) {
			this.paso1 = paso1;
		}
		public boolean isPaso2() {
			return paso2;
		}
		public void setPaso2(boolean paso2) {
			this.paso2 = paso2;
		}
		public boolean isPaso3() {
			return paso3;
		}
		public void setPaso3(boolean paso3) {
			this.paso3 = paso3;
		}
		public boolean isPaso4() {
			return paso4;
		}
		public void setPaso4(boolean paso4) {
			this.paso4 = paso4;
		}
		public String getCodigoPaquete() {
			return codigoPaquete;
		}
		public void setCodigoPaquete(String codigoPaquete) {
			this.codigoPaquete = codigoPaquete;
		}
		public boolean isMostrarHoteles() {
			return mostrarHoteles;
		}
		public void setMostrarHoteles(boolean mostrarHoteles) {
			this.mostrarHoteles = mostrarHoteles;
		}
		public boolean isMostrarDispIncaTrail() {
			return mostrarDispIncaTrail;
		}
		public void setMostrarDispIncaTrail(boolean mostrarDispIncaTrail) {
			this.mostrarDispIncaTrail = mostrarDispIncaTrail;
		}
		public String getLblMonto() {
			return lblMontoPaquete;
		}
		public void setLblMonto(String lblMonto) {
			this.lblMontoPaquete = lblMonto;
		}
		public int getNroPasajeros() {
			return nroPasajeros;
		}
		public void setNroPasajeros(int nroPasajeros) {
			this.nroPasajeros = nroPasajeros;
		}
		public boolean isMostrarPrecios() {
			return mostrarPrecios;
		}
		public void setMostrarPrecios(boolean mostrarPrecios) {
			this.mostrarPrecios = mostrarPrecios;
		}
		public String getLblPrecioSimple() {
			return lblPrecioSimple;
		}
		public void setLblPrecioSimple(String lblPrecioSimple) {
			this.lblPrecioSimple = lblPrecioSimple;
		}
		public String getLblPrecioDoble() {
			return lblPrecioDoble;
		}
		public void setLblPrecioDoble(String lblPrecioDoble) {
			this.lblPrecioDoble = lblPrecioDoble;
		}
		public String getLblPrecioTriple() {
			return lblPrecioTriple;
		}
		public void setLblPrecioTriple(String lblPrecioTriple) {
			this.lblPrecioTriple = lblPrecioTriple;
		}
		public String getLblTotalHabitaciones() {
			return lblTotalHabitaciones;
		}
		public void setLblTotalHabitaciones(String lblTotalHabitaciones) {
			this.lblTotalHabitaciones = lblTotalHabitaciones;
		}
		public int getNroSimples() {
			return nroSimples;
		}
		public void setNroSimples(int nroSimples) {
			this.nroSimples = nroSimples;
		}
		public int getNroDobles() {
			return nroDobles;
		}
		public void setNroDobles(int nroDobles) {
			this.nroDobles = nroDobles;
		}
		public int getNroTriples() {
			return nroTriples;
		}
		public void setNroTriples(int nroTriples) {
			this.nroTriples = nroTriples;
		}
		public boolean isBtnHoteles() {
			return btnHoteles;
		}
		public void setBtnHoteles(boolean btnHoteles) {
			this.btnHoteles = btnHoteles;
		}
		public String getLblCategoriaSeleccionada() {
			return lblCategoriaSeleccionada;
		}
		public void setLblCategoriaSeleccionada(String lblCategoriaSeleccionada) {
			this.lblCategoriaSeleccionada = lblCategoriaSeleccionada;
		}
		public double getMontoTotal() {
			return montoTotal;
		}
		public void setMontoTotal(Double montoTotal) {
			this.montoTotal = montoTotal;
		}
		public double getTotalHabitaciones() {
			return TotalHabitaciones;
		}
		public void setTotalHabitaciones(Double totalHabitaciones) {
			TotalHabitaciones = totalHabitaciones;
		}
		public double getTotalServicios() {
			return TotalServicios;
		}
		public void setTotalServicios(Double totalServicios) {
			TotalServicios = totalServicios;
		}
		public String getLblTotalServicios() {
			return lblTotalServicios;
		}
		public void setLblTotalServicios(String lblTotalServicios) {
			this.lblTotalServicios = lblTotalServicios;
		}
		public double getTotalPaquete() {
			return TotalPaquete;
		}
		public void setTotalPaquete(Double totalPaquete) {
			TotalPaquete = totalPaquete;
		}
		public String getLblTotalPaquete() {
			return lblTotalPaquete;
		}
		public void setLblTotalPaquete(String lblTotalPaquete) {
			this.lblTotalPaquete = lblTotalPaquete;
		}
		public String getLblMontoTotal() {
			return lblMontoTotal;
		}
		public void setLblMontoTotal(String lblMontoTotal) {
			this.lblMontoTotal = lblMontoTotal;
		}
		public String getNombreDoc() {
			return nombreDoc;
		}
		public void setNombreDoc(String nombreDoc) {
			this.nombreDoc = nombreDoc;
		}
		public boolean isMostrarCalendarZK() {
			return mostrarCalendarZK;
		}
		public void setMostrarCalendarZK(boolean mostrarCalendarZK) {
			this.mostrarCalendarZK = mostrarCalendarZK;
		}
		public CReservaPaqueteCategoriaHotel getoReservaPaqCatHotel() {
			return oReservaPaqCatHotel;
		}
		public void setoReservaPaqCatHotel(
				CReservaPaqueteCategoriaHotel oReservaPaqCatHotel) {
			this.oReservaPaqCatHotel = oReservaPaqCatHotel;
		}
		public ArrayList<CPaqueteCategoriaHotel> getListaPaqueteCatHotel() {
			return listaPaqueteCatHotel;
		}
		public void setListaPaqueteCatHotel(
				ArrayList<CPaqueteCategoriaHotel> listaPaqueteCatHotel) {
			this.listaPaqueteCatHotel = listaPaqueteCatHotel;
		}
		public String getLblTotalPrecioSimple() {
			return lblTotalPrecioSimple;
		}
		public void setLblTotalPrecioSimple(String lblTotalPrecioSimple) {
			this.lblTotalPrecioSimple = lblTotalPrecioSimple;
		}
		public String getLblTotalPrecioDoble() {
			return lblTotalPrecioDoble;
		}
		public void setLblTotalPrecioDoble(String lblTotalPrecioDoble) {
			this.lblTotalPrecioDoble = lblTotalPrecioDoble;
		}
		public String getLblTotalPrecioTriple() {
			return lblTotalPrecioTriple;
		}
		public void setLblTotalPrecioTriple(String lblTotalPrecioTriple) {
			this.lblTotalPrecioTriple = lblTotalPrecioTriple;
		}
		public boolean isMostrarServicios() {
			return mostrarServicios;
		}
		public void setMostrarServicios(boolean mostrarServicios) {
			this.mostrarServicios = mostrarServicios;
		}
		public boolean isMostrarReservaHotel() {
			return mostrarReservaHotel;
		}
		public void setMostrarReservaHotel(boolean mostrarReservaHotel) {
			this.mostrarReservaHotel = mostrarReservaHotel;
		}
		public ArrayList<String> getListaFechasAlternas() {
			return listacFechasAlternas;
		}
		public void setListaFechasAlternas(ArrayList<String> listaFechasAlternas) {
			this.listacFechasAlternas = listaFechasAlternas;
		}
		public boolean isMostrarCostoServicio() {
			return mostrarCostoServicio;
		}
		public void setMostrarCostoServicio(boolean mostrarCostoServicio) {
			this.mostrarCostoServicio = mostrarCostoServicio;
		}
		public boolean isAcceptedTermsAndCond() {
			return acceptedTermsAndCond;
		}
		public void setAcceptedTermsAndCond(boolean acceptedTermsAndCond) {
			this.acceptedTermsAndCond = acceptedTermsAndCond;
		}
		public boolean isPayment() {
			return payment;
		}
		public void setPayment(boolean payment) {
			this.payment = payment;
		}
		public ArrayList<String> getListaEdades() {
			return listaEdades;
		}
		public void setListaEdades(ArrayList<String> listaEdades) {
			this.listaEdades = listaEdades;
		}
		public boolean isMostrarPaypal() {
			return mostrarPaypal;
		}
		public void setMostrarPaypal(boolean mostrarPaypal) {
			this.mostrarPaypal = mostrarPaypal;
		}
		public String getUrlPaypal() {
			return urlPaypal;
		}
		public void setUrlPaypal(String urlPaypal) {
			this.urlPaypal = urlPaypal;
		}
		public String getTotalConImpuestoPaypal() {
			return totalConImpuestoPaypal;
		}
		public void setTotalConImpuestoPaypal(String totalConImpuestoPaypal) {
			this.totalConImpuestoPaypal = totalConImpuestoPaypal;
		}
		public String getMontoTotalPorcentual() {
			return monto_Pagar_sin_impuesto;
		}
		public void setMontoTotalPorcentual(String montoTotalPorcentual) {
			this.monto_Pagar_sin_impuesto = montoTotalPorcentual;
		}
		public String getTextoPorcentaje() {
			return textoPorcentaje;
		}
		public void setTextoPorcentaje(String textoPorcentaje) {
			this.textoPorcentaje = textoPorcentaje;
		}
		public ArrayList<String> getListaUrlImages() {
			return listaUrlImages;
		}
		public void setListaUrlImages(ArrayList<String> listaUrlImages) {
			this.listaUrlImages = listaUrlImages;
		}
		public String getUrlPdf() {
			return urlPdf;
		}
		public void setUrlPdf(String urlPdf) {
			this.urlPdf = urlPdf;
		}
		public String[] getEtiqueta() {
			return etiqueta;
		}
		public void setEtiqueta(String[] etiqueta) {
			this.etiqueta = etiqueta;
		}
		public ArrayList<String> getRestSimple() {
			return restSimple;
		}
		public void setRestSimple(ArrayList<String> restSimple) {
			this.restSimple = restSimple;
		}
		public ArrayList<String> getRestDoble() {
			return restDoble;
		}
		public void setRestDoble(ArrayList<String> restDoble) {
			this.restDoble = restDoble;
		}
		public ArrayList<String> getRestTriple() {
			return restTriple;
		}
		public void setRestTriple(ArrayList<String> restTriple) {
			this.restTriple = restTriple;
		}
		public String getFecha() {
			return fecha;
		}
		public void setFecha(String fecha) {
			this.fecha = fecha;
		}
		public ArrayList<CCategoriaConHoteles> getListaCategoriaConHoteles() {
			return listaCategoriaConHoteles;
		}
		public void setListaCategoriaConHoteles(
				ArrayList<CCategoriaConHoteles> listaCategoriaConHoteles) {
			this.listaCategoriaConHoteles = listaCategoriaConHoteles;
		}
		public ArrayList<CDestinoConHoteles> getListaDestinoConHoteles() {
			return listaDestinoConHoteles;
		}
		public void setListaDestinoConHoteles(
				ArrayList<CDestinoConHoteles> listaDestinoConHoteles) {
			this.listaDestinoConHoteles = listaDestinoConHoteles;
		}
		public String getTax() {
			return tax;
		}
		public void setTax(String tax) {
			this.tax = tax;
		}
		public CConfigUrlDAO getConfigUrlDao() {
			return configUrlDao;
		}
		public void setConfigUrlDao(CConfigUrlDAO configUrlDao) {
			this.configUrlDao = configUrlDao;
		}
		public String getFechaAlterna() {
			return fechaAlterna;
		}
		public void setFechaAlterna(String fechaAlterna) {
			this.fechaAlterna = fechaAlterna;
		}
		public boolean isReservar() {
			return reservar;
		}
		public void setReservar(boolean reservar) {
			this.reservar = reservar;
		}
		public CInterfaz getoInterfaz() {
			return oInterfaz;
		}
		public void setoInterfaz(CInterfaz oInterfaz) {
			this.oInterfaz = oInterfaz;
		}
		public ArrayList<CPais> getListaPaises() {
			return listaPaises;
		}
		public void setListaPaises(ArrayList<CPais> listaPaises) {
			this.listaPaises = listaPaises;
		}
		public CReserva getoReservar() {
			return oReservar;
		}
		public void setoReservar(CReserva oReservar) {
			this.oReservar = oReservar;
		}
		public boolean isMostrarCostoActividades() {
			return mostrarCostoActividades;
		}
		public void setMostrarCostoActividades(boolean mostrarCostoActividades) {
			this.mostrarCostoActividades = mostrarCostoActividades;
		}
		public String getLblTotalActividades() {
			return lblTotalActividades;
		}
		public void setLblTotalActividades(String lblTotalActividades) {
			this.lblTotalActividades = lblTotalActividades;
		}
		public ArrayList<CPasajero> getListaPasajeros() {
			return listaPasajeros;
		}
		public void setListaPasajeros(ArrayList<CPasajero> listaPasajeros) {
			this.listaPasajeros = listaPasajeros;
		}
		public CPagos getPagos() {
			return pagos;
		}
		public void setPagos(CPagos pagos) {
			this.pagos = pagos;
		}
		public String getEstiloPaso1() {
			return estiloPaso1;
		}
		public void setEstiloPaso1(String estiloPaso1) {
			this.estiloPaso1 = estiloPaso1;
		}
		public String getEstiloPaso2() {
			return estiloPaso2;
		}
		public void setEstiloPaso2(String estiloPaso2) {
			this.estiloPaso2 = estiloPaso2;
		}
		public String getEstiloPaso3() {
			return estiloPaso3;
		}
		public void setEstiloPaso3(String estiloPaso3) {
			this.estiloPaso3 = estiloPaso3;
		}
		public boolean isVisiblePaso1() {
			return visiblePaso1;
		}
		public void setVisiblePaso1(boolean visiblePaso1) {
			this.visiblePaso1 = visiblePaso1;
		}
		public boolean isVisiblePaso2() {
			return visiblePaso2;
		}
		public void setVisiblePaso2(boolean visiblePaso2) {
			this.visiblePaso2 = visiblePaso2;
		}
		public boolean isVisiblePaso3() {
			return visiblePaso3;
		}
		public void setVisiblePaso3(boolean visiblePaso3) {
			this.visiblePaso3 = visiblePaso3;
		}
		public boolean isVisibleBarraPaso1() {
			return visibleBarraPaso1;
		}
		public void setVisibleBarraPaso1(boolean visibleBarraPaso1) {
			this.visibleBarraPaso1 = visibleBarraPaso1;
		}
		public boolean isVisibleBarraPaso2() {
			return visibleBarraPaso2;
		}
		public void setVisibleBarraPaso2(boolean visibleBarraPaso2) {
			this.visibleBarraPaso2 = visibleBarraPaso2;
		}
		public boolean isVisibleBarraPaso3() {
			return visibleBarraPaso3;
		}
		public void setVisibleBarraPaso3(boolean visibleBarraPaso3) {
			this.visibleBarraPaso3 = visibleBarraPaso3;
		}
		public boolean isVisibleNroPaso1() {
			return visibleNroPaso1;
		}
		public void setVisibleNroPaso1(boolean visibleNroPaso1) {
			this.visibleNroPaso1 = visibleNroPaso1;
		}
		public boolean isVisibleNroPaso2() {
			return visibleNroPaso2;
		}
		public void setVisibleNroPaso2(boolean visibleNroPaso2) {
			this.visibleNroPaso2 = visibleNroPaso2;
		}
		public boolean isVisibleNroPaso3() {
			return visibleNroPaso3;
		}
		public void setVisibleNroPaso3(boolean visibleNroPaso3) {
			this.visibleNroPaso3 = visibleNroPaso3;
		}
		public boolean isVisibleContenedorPasos() {
			return visibleContenedorPasos;
		}
		public void setVisibleContenedorPasos(boolean visibleContenedorPasos) {
			this.visibleContenedorPasos = visibleContenedorPasos;
		}
		public boolean isVisiblecontentBotonesPasos() {
			return visiblecontentBotonesPasos;
		}
		public void setVisiblecontentBotonesPasos(boolean visiblecontentBotonesPasos) {
			this.visiblecontentBotonesPasos = visiblecontentBotonesPasos;
		}
		public ConfAltoNivel getConfAltoNivel() {
			return confAltoNivel;
		}
		public void setConfAltoNivel(ConfAltoNivel confAltoNivel) {
			this.confAltoNivel = confAltoNivel;
		}
		public ConfAltoNivelDAO getConfAltoNivelDAO() {
			return confAltoNivelDAO;
		}
		public void setConfAltoNivelDAO(ConfAltoNivelDAO confAltoNivelDAO) {
			this.confAltoNivelDAO = confAltoNivelDAO;
		}
		public boolean isVisibleBtnAtras() {
			return visibleBtnAtras;
		}
		public void setVisibleBtnAtras(boolean visibleBtnAtras) {
			this.visibleBtnAtras = visibleBtnAtras;
		}
		public boolean isVisibleBtnSiguiente() {
			return visibleBtnSiguiente;
		}
		public void setVisibleBtnSiguiente(boolean visibleBtnSiguiente) {
			this.visibleBtnSiguiente = visibleBtnSiguiente;
		}
		public String getTextoParcial() {
			return textoParcial;
		}
		public void setTextoParcial(String textoParcial) {
			this.textoParcial = textoParcial;
		}
		public String getTextoTotal() {
			return textoTotal;
		}
		public void setTextoTotal(String textoTotal) {
			this.textoTotal = textoTotal;
		}
}
