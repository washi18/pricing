package com.pricing.model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

import com.pricing.dao.CActividadDAO;
import com.pricing.dao.CCalendarioPropioDAO;
import com.pricing.dao.CDestinoDAO;
import com.pricing.dao.CPaqueteActividadDAO;
import com.pricing.dao.CPaqueteDestinoDAO;
import com.pricing.dao.CPaqueteServicioDAO;
import com.pricing.dao.CServicioDAO;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class CPaquete 
{
	//====================
		private DecimalFormat df;
		private DecimalFormatSymbols simbolos;
	//====================
	private String cPaqueteCod;// varchar(10),			--codigo del paquete
	private String cTituloIdioma1;// varchar(200),			--titulo del paquete el el idioma 1
	private String cTituloIdioma2;// varchar(200),			--titulo del paquete el el idioma 2
	private String cTituloIdioma3;// varchar(200),			--titulo del paquete el el idioma 3
	private String cTituloIdioma4;// varchar(200),			--titulo del paquete el el idioma 4
	private String cTituloIdioma5;// varchar(200),			--titulo del paquete el el idioma 5
	private String cDescripcionIdioma1;// text,		        --Descripcion del paquete el el idioma 1
	private String cDescripcionIdioma2;// text,		        --Descripcion del paquete el el idioma 2
	private String cDescripcionIdioma3;// text,		        --Descripcion del paquete el el idioma 3
	private String cDescripcionIdioma4;// text,		        --Descripcion del paquete el el idioma 4
	private String cDescripcionIdioma5;// text,		        --Descripcion del paquete el el idioma 5
	private int nDias;// int,					--numero de dias
	private int nNoches;// int,					--numero de noches del paquete
	private Number nPrecioUno;// decimal(10,2),			--precio del paquete para una persona
	private Number nPrecioDos;// decimal(10,2),			--precio del paquete para dos personas
	private Number nPrecioTres;// decimal(10,2),			--precio del paquete para tres personas
	private Number nPrecioCuatro;// decimal(10,2),			--precio del paquete para cuatro personas
	private Number nPrecioCinco;// decimal(10,2),			--precio del paquete para cinco personas a mas
	private String cDisponibilidad;// varchar(100),                   --disponibilidad del que se requiere (CAMINO_INKA,MACHUPICCHU,NINGUNO)
	private boolean bEstado;// boolean,				--estado del paquete
	private int nDiaCaminoInka;//int,
	private String url_pricingPaquete;
	private String nPrecio1_text;
	private String nPrecio2_text;
	private String nPrecio3_text;
	private String nPrecio4_text;
	private String nPrecio5_text;
	private String Titulo;
	private String dias_noches;
	private boolean visibleEspanol;
	private boolean visibleIngles;
	private boolean visiblePortugues;
	private boolean manejo_camino_inca;
	private boolean manejo_propio;
	private boolean manejo_normal;
	private boolean manejoPropio_conCaminoInka;
	private boolean conDestino;
	private boolean sinDestino;
	private boolean conServicio;
	private boolean conActividad;
	private boolean conDescuento;
	private boolean sinDescuento;
	private int nroDestinosSelect;
	private int ordenDesSelect;
	private String color_btn_activo;
	private String color_btn_desactivo;
	public String COLOR_ACTIVO="background:#3BA420;";
	public String COLOR_DESACTIVO="background:#DA0613;";
	public String COLOR_TRANSPARENT="background:transparent;";
	private boolean estado_activo;
	private boolean estado_desactivo;
	private boolean editable;
	private boolean conCalendarioPropio;
	private ArrayList<CDestino> listaDestinos;
	private ArrayList<CServicio> listaServicios;
	private ArrayList<CSubServicio> listaSubServicios;
	private ArrayList<CActividad> listaActividades;
	private ArrayList<CCalendarioPropio> listaAniosCalendarioPropio;
	private ArrayList<CDiaPropio> listaDiasCalendarioPropio;
	//==========================
	public String getcPaqueteCod() {
		return cPaqueteCod;
	}
	public void setcPaqueteCod(String cPaqueteCod) {
		this.cPaqueteCod = cPaqueteCod;
	}
	public String getcTituloIdioma1() {
		return cTituloIdioma1;
	}
	public void setcTituloIdioma1(String cTituloIdioma1) {
		this.cTituloIdioma1 = cTituloIdioma1;
	}
	public String getcTituloIdioma2() {
		return cTituloIdioma2;
	}
	public void setcTituloIdioma2(String cTituloIdioma2) {
		this.cTituloIdioma2 = cTituloIdioma2;
	}
	public String getcTituloIdioma3() {
		return cTituloIdioma3;
	}
	public void setcTituloIdioma3(String cTituloIdioma3) {
		this.cTituloIdioma3 = cTituloIdioma3;
	}
	public String getcTituloIdioma4() {
		return cTituloIdioma4;
	}
	public void setcTituloIdioma4(String cTituloIdioma4) {
		this.cTituloIdioma4 = cTituloIdioma4;
	}
	public String getcTituloIdioma5() {
		return cTituloIdioma5;
	}
	public void setcTituloIdioma5(String cTituloIdioma5) {
		this.cTituloIdioma5 = cTituloIdioma5;
	}
	public String getcDescripcionIdioma1() {
		return cDescripcionIdioma1;
	}
	public void setcDescripcionIdioma1(String cDescripcionIdioma1) {
		this.cDescripcionIdioma1 = cDescripcionIdioma1;
	}
	public String getcDescripcionIdioma2() {
		return cDescripcionIdioma2;
	}
	public void setcDescripcionIdioma2(String cDescripcionIdioma2) {
		this.cDescripcionIdioma2 = cDescripcionIdioma2;
	}
	public String getcDescripcionIdioma3() {
		return cDescripcionIdioma3;
	}
	public void setcDescripcionIdioma3(String cDescripcionIdioma3) {
		this.cDescripcionIdioma3 = cDescripcionIdioma3;
	}
	public String getcDescripcionIdioma4() {
		return cDescripcionIdioma4;
	}
	public void setcDescripcionIdioma4(String cDescripcionIdioma4) {
		this.cDescripcionIdioma4 = cDescripcionIdioma4;
	}
	public String getcDescripcionIdioma5() {
		return cDescripcionIdioma5;
	}
	public void setcDescripcionIdioma5(String cDescripcionIdioma5) {
		this.cDescripcionIdioma5 = cDescripcionIdioma5;
	}
	public int getnDias() {
		return nDias;
	}
	public void setnDias(int nDias) {
		this.nDias = nDias;
	}
	public int getnNoches() {
		return nNoches;
	}
	public void setnNoches(int nNoches) {
		this.nNoches = nNoches;
	}
	public Number getnPrecioUno() {
		return nPrecioUno;
	}
	public void setnPrecioUno(Number nPrecioUno) {
		this.nPrecioUno = nPrecioUno;
	}
	public Number getnPrecioDos() {
		return nPrecioDos;
	}
	public void setnPrecioDos(Number nPrecioDos) {
		this.nPrecioDos = nPrecioDos;
	}
	public Number getnPrecioTres() {
		return nPrecioTres;
	}
	public void setnPrecioTres(Number nPrecioTres) {
		this.nPrecioTres = nPrecioTres;
	}
	public Number getnPrecioCuatro() {
		return nPrecioCuatro;
	}
	public void setnPrecioCuatro(Number nPrecioCuatro) {
		this.nPrecioCuatro = nPrecioCuatro;
	}
	public Number getnPrecioCinco() {
		return nPrecioCinco;
	}
	public void setnPrecioCinco(Number nPrecioCinco) {
		this.nPrecioCinco = nPrecioCinco;
	}
	public boolean isbEstado() {
		return bEstado;
	}
	public void setbEstado(boolean bEstado) {
		this.bEstado = bEstado;
	}
	public String getcDisponibilidad() {
		return cDisponibilidad;
	}
	public void setcDisponibilidad(String cDisponibilidad) {
		this.cDisponibilidad = cDisponibilidad;
	}
	public String getTitulo() {
		return Titulo;
	}
	public void setTitulo(String titulo) {
		Titulo = titulo;
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	
	public boolean isVisibleEspanol() {
		return visibleEspanol;
	}
	public void setVisibleEspanol(boolean visibleEspanol) {
		this.visibleEspanol = visibleEspanol;
	}
	public boolean isVisibleIngles() {
		return visibleIngles;
	}
	public void setVisibleIngles(boolean visibleIngles) {
		this.visibleIngles = visibleIngles;
	}
	public boolean isVisiblePortugues() {
		return visiblePortugues;
	}
	public void setVisiblePortugues(boolean visiblePortugues) {
		this.visiblePortugues = visiblePortugues;
	}
	public boolean isManejo_camino_inca() {
		return manejo_camino_inca;
	}
	public void setManejo_camino_inca(boolean manejo_camino_inca) {
		this.manejo_camino_inca = manejo_camino_inca;
	}
	public boolean isManejo_propio() {
		return manejo_propio;
	}
	public void setManejo_propio(boolean manejo_propio) {
		this.manejo_propio = manejo_propio;
	}
	public boolean isManejo_normal() {
		return manejo_normal;
	}
	public void setManejo_normal(boolean manejo_normal) {
		this.manejo_normal = manejo_normal;
	}
	public int getnDiaCaminoInka() {
		return nDiaCaminoInka;
	}
	public void setnDiaCaminoInka(int nDiaCaminoInka) {
		this.nDiaCaminoInka = nDiaCaminoInka;
	}
	public String getnPrecio1_text() {
		return nPrecio1_text;
	}
	public void setnPrecio1_text(String nPrecio1_text) {
		this.nPrecio1_text = nPrecio1_text;
	}
	public String getnPrecio2_text() {
		return nPrecio2_text;
	}
	public void setnPrecio2_text(String nPrecio2_text) {
		this.nPrecio2_text = nPrecio2_text;
	}
	public String getnPrecio3_text() {
		return nPrecio3_text;
	}
	public void setnPrecio3_text(String nPrecio3_text) {
		this.nPrecio3_text = nPrecio3_text;
	}
	public String getnPrecio4_text() {
		return nPrecio4_text;
	}
	public void setnPrecio4_text(String nPrecio4_text) {
		this.nPrecio4_text = nPrecio4_text;
	}
	public String getnPrecio5_text() {
		return nPrecio5_text;
	}
	public void setnPrecio5_text(String nPrecio5_text) {
		this.nPrecio5_text = nPrecio5_text;
	}
	public boolean isManejoPropio_conCaminoInka() {
		return manejoPropio_conCaminoInka;
	}
	public void setManejoPropio_conCaminoInka(boolean manejoPropio_conCaminoInka) {
		this.manejoPropio_conCaminoInka = manejoPropio_conCaminoInka;
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
	public ArrayList<CSubServicio> getListaSubServicios() {
		return listaSubServicios;
	}
	public void setListaSubServicios(ArrayList<CSubServicio> listaSubServicios) {
		this.listaSubServicios = listaSubServicios;
	}
	public ArrayList<CActividad> getListaActividades() {
		return listaActividades;
	}
	public void setListaActividades(ArrayList<CActividad> listaActividades) {
		this.listaActividades = listaActividades;
	}
	public boolean isConDestino() {
		return conDestino;
	}
	public void setConDestino(boolean conDestino) {
		this.conDestino = conDestino;
	}
	public boolean isSinDestino() {
		return sinDestino;
	}
	public void setSinDestino(boolean sinDestino) {
		this.sinDestino = sinDestino;
	}
	public int getNroDestinosSelect() {
		return nroDestinosSelect;
	}
	public void setNroDestinosSelect(int nroDestinosSelect) {
		this.nroDestinosSelect = nroDestinosSelect;
	}
	public int getOrdenDesSelect() {
		return ordenDesSelect;
	}
	public void setOrdenDesSelect(int ordenDesSelect) {
		this.ordenDesSelect = ordenDesSelect;
	}
	public boolean isConDescuento() {
		return conDescuento;
	}
	public void setConDescuento(boolean conDescuento) {
		this.conDescuento = conDescuento;
	}
	public boolean isSinDescuento() {
		return sinDescuento;
	}
	public void setSinDescuento(boolean sinDescuento) {
		this.sinDescuento = sinDescuento;
	}
	public String getDias_noches() {
		return dias_noches;
	}
	public void setDias_noches(String dias_noches) {
		this.dias_noches = dias_noches;
	}
	public String getColor_btn_activo() {
		return color_btn_activo;
	}
	public void setColor_btn_activo(String color_btn_activo) {
		this.color_btn_activo = color_btn_activo;
	}
	public String getColor_btn_desactivo() {
		return color_btn_desactivo;
	}
	public void setColor_btn_desactivo(String color_btn_desactivo) {
		this.color_btn_desactivo = color_btn_desactivo;
	}
	public boolean isEstado_activo() {
		return estado_activo;
	}
	public void setEstado_activo(boolean estado_activo) {
		this.estado_activo = estado_activo;
	}
	public boolean isEstado_desactivo() {
		return estado_desactivo;
	}
	public void setEstado_desactivo(boolean estado_desactivo) {
		this.estado_desactivo = estado_desactivo;
	}
	public boolean isConServicio() {
		return conServicio;
	}
	public void setConServicio(boolean conServicio) {
		this.conServicio = conServicio;
	}
	public boolean isConActividad() {
		return conActividad;
	}
	public void setConActividad(boolean conActividad) {
		this.conActividad = conActividad;
	}
	public ArrayList<CCalendarioPropio> getListaAniosCalendarioPropio() {
		return listaAniosCalendarioPropio;
	}
	public void setListaAniosCalendarioPropio(
			ArrayList<CCalendarioPropio> listaAniosCalendarioPropio) {
		this.listaAniosCalendarioPropio = listaAniosCalendarioPropio;
	}
	public ArrayList<CDiaPropio> getListaDiasCalendarioPropio() {
		return listaDiasCalendarioPropio;
	}
	public void setListaDiasCalendarioPropio(
			ArrayList<CDiaPropio> listaDiasCalendarioPropio) {
		this.listaDiasCalendarioPropio = listaDiasCalendarioPropio;
	}
	public boolean isConCalendarioPropio() {
		return conCalendarioPropio;
	}
	public void setConCalendarioPropio(boolean conCalendarioPropio) {
		this.conCalendarioPropio = conCalendarioPropio;
	}
	public String getUrl_pricingPaquete() {
		return url_pricingPaquete;
	}
	public void setUrl_pricingPaquete(String url_pricingPaquete) {
		this.url_pricingPaquete = url_pricingPaquete;
	}
	//=========================================
	public CPaquete() {
		// TODO Auto-generated constructor stub
		/*******************************/
		simbolos= new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		df=new DecimalFormat("########0.00",simbolos);
		/*******************************/
		Titulo="";
		dias_noches="";
		manejo_camino_inca=false;
		manejo_propio=false;
		manejo_normal=false;
		nDiaCaminoInka=0;
		cTituloIdioma1="";
		cTituloIdioma2="";
		cTituloIdioma3="";
		cTituloIdioma4="";
		cTituloIdioma5="";
		nPrecio1_text=df.format(0);
		nPrecio2_text=df.format(0);
		nPrecio3_text=df.format(0);
		nPrecio4_text=df.format(0);
		nPrecio5_text=df.format(0);
		nPrecioUno=0;
		nPrecioDos=0;
		nPrecioTres=0;
		nPrecioCuatro=0;
		nPrecioCinco=0;
		manejoPropio_conCaminoInka=false;
		conDestino=false;
		sinDestino=true;
		conDescuento=false;
		sinDescuento=true;
		nroDestinosSelect=0;
		ordenDesSelect=0;
	}
	public CPaquete(String cPaqueteCod, String cTituloIdioma1,
			String cTituloIdioma2, String cTituloIdioma3,
			String cTituloIdioma4, String cTituloIdioma5,
			String cDescripcionIdioma1, String cDescripcionIdioma2,
			String cDescripcionIdioma3, String cDescripcionIdioma4,
			String cDescripcionIdioma5, int nDias, int nNoches,
			Number nPrecioUno, Number nPrecioDos, Number nPrecioTres,
			Number nPrecioCuatro, Number nPrecioCinco, String cDisponibilidad,
			boolean bEstado) {
		/*******************************/
		simbolos= new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		df=new DecimalFormat("########0.00",simbolos);
		/*******************************/
		this.cPaqueteCod = cPaqueteCod;
		this.cTituloIdioma1 = cTituloIdioma1;
		this.Titulo=cTituloIdioma1;
		this.cTituloIdioma2 = cTituloIdioma2;
		this.cTituloIdioma3 = cTituloIdioma3;
		this.cTituloIdioma4 = cTituloIdioma4;
		this.cTituloIdioma5 = cTituloIdioma5;
		this.cDescripcionIdioma1 = cDescripcionIdioma1;
		this.cDescripcionIdioma2 = cDescripcionIdioma2;
		this.cDescripcionIdioma3 = cDescripcionIdioma3;
		this.cDescripcionIdioma4 = cDescripcionIdioma4;
		this.cDescripcionIdioma5 = cDescripcionIdioma5;
		this.nDias = nDias;
		this.nNoches = nNoches;
		this.dias_noches=nDias+" DIAS Y "+nNoches+" NOCHES";
		this.nPrecioUno = nPrecioUno;
		this.nPrecio1_text=df.format(nPrecioUno.doubleValue());
		this.nPrecioDos = nPrecioDos;
		this.nPrecio2_text=df.format(nPrecioDos.doubleValue());
		this.nPrecioTres = nPrecioTres;
		this.nPrecio3_text=df.format(nPrecioTres.doubleValue());
		this.nPrecioCuatro = nPrecioCuatro;
		this.nPrecio4_text=df.format(nPrecioCuatro.doubleValue());
		this.nPrecioCinco = nPrecioCinco;
		this.nPrecio5_text=df.format(nPrecioCinco.doubleValue());
		this.cDisponibilidad = cDisponibilidad;
		this.bEstado = bEstado;
		this.estado_activo=bEstado;
		this.estado_desactivo=!bEstado;
		this.editable=false;
		this.visibleEspanol=true;
		this.visibleIngles=false;
		this.visiblePortugues=false;
		this.conCalendarioPropio=false;
		this.url_pricingPaquete="https://www.e-ranti.com/pricing_pat/?var1="+cPaqueteCod;
		/***Recuperando lo que contiene el paquete***/
		listaDestinos=new ArrayList<CDestino>();
		listaServicios=new ArrayList<CServicio>();
		listaSubServicios=new ArrayList<CSubServicio>();
		listaActividades=new ArrayList<CActividad>();
		listaAniosCalendarioPropio=new ArrayList<CCalendarioPropio>();
		listaDiasCalendarioPropio=new ArrayList<CDiaPropio>();
		//RECUPERAR LISTA DESTINOS
		CDestinoDAO destinoDao=new CDestinoDAO();
		destinoDao.asignarListaDestinos(destinoDao.recuperarListaDestinosBD());
		setListaDestinos(destinoDao.getListaDestinos());
		//RECUPERAR LISTA SERVICIOS
		CServicioDAO servicioDao=new CServicioDAO();
		servicioDao.asignarListaServicios(servicioDao.recuperarServiciosBD());
		setListaServicios(servicioDao.getListaServicios());
		//RECUPERAR LISTA ACTIVIDADES
		CActividadDAO actividadDao=new CActividadDAO();
		actividadDao.asignarListaActividades(actividadDao.recuperarActividadesBD());
		setListaActividades(actividadDao.getListaActividades());
		//RECUPERAR LA LISTA DE SUBSERVICIOS
		recuperarListaSubServicios();
		//RECUPERAMOS EL CALENDARIO PROPIO
//		if(cDisponibilidad.equals("MANEJO_PROPIO"))
//		{
//			System.out.println("soy el paquete..... "+cPaqueteCod);
//			CCalendarioPropioDAO calendarioDao=new CCalendarioPropioDAO();
//			calendarioDao.asignarListaAniosCalendario(calendarioDao.recuperarAniosCalendarioBD(cPaqueteCod));
//			setListaAniosCalendarioPropio(calendarioDao.getListaAniosCalendario());
//			if(!listaAniosCalendarioPropio.isEmpty())
//			{
//				int codCalendar=listaAniosCalendarioPropio.get(0).getnCalendarioCod();
//				System.out.println("cod calendario: "+codCalendar);
//				calendarioDao.asignarListaDiasAnioCalendario(calendarioDao.recuperarDiasAnioCalendarioBD(codCalendar));
//				setListaDiasCalendarioPropio(calendarioDao.getListaDiasAnioCalendario());
//			}
//			System.out.println("tamaño dias--> "+listaDiasCalendarioPropio.size());
//			for(CDiaPropio d:listaDiasCalendarioPropio)
//				System.out.println("--> "+d.getnDia());
//			this.conCalendarioPropio=true;
//		}
		/***INICIALIZAMOS LOS ESTADOS DE LOS DESTINOS Y SERVICIOS DEL PAQUETE**/
		inicializarEstadosDeDestinosYServicios();
		determinarTipoDeManejoPaquete(cDisponibilidad);
		determinarSiHayDescuento();
		darColor_estado_paquete();
	}
	public void darColor_estado_paquete()
	{
		if(bEstado)
		{
			color_btn_activo=COLOR_ACTIVO;
			color_btn_desactivo=COLOR_TRANSPARENT;
		}
		else{
			color_btn_activo=COLOR_TRANSPARENT;
			color_btn_desactivo=COLOR_DESACTIVO;
		}
	}
	public void determinarSiHayDescuento()
	{
		if(nPrecioUno.doubleValue()!=nPrecioDos.doubleValue() ||
				nPrecioUno.doubleValue()!=nPrecioTres.doubleValue()||
				nPrecioUno.doubleValue()!=nPrecioCuatro.doubleValue()||
				nPrecioUno.doubleValue()!=nPrecioCinco.doubleValue())
		{
			sinDescuento=false;
			conDescuento=true;
		}else
		{
			sinDescuento=true;
			conDescuento=false;
		}
	}
	public void determinarTipoDeManejoPaquete(String manejo)
	{
		if(manejo.equals("CAMINO_INKA"))
		{
			manejo_camino_inca=true;
			manejo_propio=false;
			manejo_normal=false;
		}else if(manejo.equals("MANEJO_PROPIO"))
		{
			manejo_camino_inca=false;
			manejo_propio=true;
			manejo_normal=false;
		}else if(manejo.equals("MANEJO_NORMAL"))
		{
			manejo_camino_inca=false;
			manejo_propio=false;
			manejo_normal=true;
		}
	}
	public void inicializarEstadosDeDestinosYServicios()
	{
		//RECUPERAR LISTA PAQUETE-DESTINOS
		ArrayList<CPaqueteDestino> listaPaqueteDestinos=new ArrayList<CPaqueteDestino>();
		listaPaqueteDestinos=obtenerListaPaqueteDestino(cPaqueteCod);
		//RECUPERAR LISTA PAQUETE-SERVICIOS
		ArrayList<CPaqueteServicio> listaPaqueteServicios=new ArrayList<CPaqueteServicio>();
		listaPaqueteServicios=obtenerListaPaqueteServicio(cPaqueteCod);
		//RECUPERAR LISTA PAQUETE-ACTIVIDADES
		ArrayList<CPaqueteActividad> listaPaqueteActividades=new ArrayList<CPaqueteActividad>();
		listaPaqueteActividades=obtenerListaPaqueteActividad(cPaqueteCod);
		conDestino=false;
		sinDestino=true;
		manejoPropio_conCaminoInka=false;
		nroDestinosSelect=listaPaqueteDestinos.size();
		ordenDesSelect=0;
		for(CPaqueteDestino PDestino:listaPaqueteDestinos)
		{
			for(CDestino destino:listaDestinos)
			{
				if(PDestino.getnDestinoCod()==destino.getnDestinoCod())
				{
					conDestino=true;
					sinDestino=false;
					destino.setSeleccionado(true);
					destino.setnNoches(PDestino.getnNoches());
					destino.setnOrdenItinerario(PDestino.getnOrdenItinerario());
					if(PDestino.isbConCaminoInka())
					{
						destino.setConCaminoInka(true);
						destino.setSinCaminoInka(false);
						destino.setPuedeCaminoInka(true);
						manejoPropio_conCaminoInka=true;
					}
				}
			}
		}
		
		if(listaPaqueteServicios.size()>0)
		{
			conServicio=true;
			for(CPaqueteServicio PServicio:listaPaqueteServicios)
			{
				for(CServicio servicio:listaServicios)
				{
					if(PServicio.getnServicioCod()==servicio.getnServicioCod())
						servicio.setSeleccionado(true);
				}
			}
		}else
			conServicio=false;
		
		if(listaPaqueteActividades.size()>0)
		{
			conActividad=true;
			for(CPaqueteActividad PA:listaPaqueteActividades)
			{
				for(CActividad act:listaActividades)
				{
					if(PA.getnActividadCod()==act.getnActividadCod())
						act.setSeleccionado(true);
				}
			}
		}
		else
			conActividad=false;
	}
	public void recuperarListaSubServicios()
	{
		/**Recuperar la lista de subservicios**/
		CServicioDAO servicioDao=new CServicioDAO();
		servicioDao.asignarListaSubServicios(servicioDao.recuperarSubServiciosBD());
		/**Recuperar lista de subservicios en el paquete correspondiente a sus servicios**/
		for(CServicio s:listaServicios)
		{
			for(CSubServicio sub:servicioDao.getListaSubServicios())
			{
				if(s.getnServicioCod()==sub.getnServicioCod())
				{
					listaSubServicios.add(sub);
				}
			}
		}
	}
	public ArrayList<CPaqueteDestino> obtenerListaPaqueteDestino(String codPaquete)
	{
		CPaqueteDestinoDAO paqueteDestinoDao=new CPaqueteDestinoDAO();
		paqueteDestinoDao.asignarListaPaqueteDestinos(paqueteDestinoDao.recuperarPaqueteDestinos(codPaquete));
		return paqueteDestinoDao.getListaPaqueteDestinos();
	}
	public ArrayList<CPaqueteServicio> obtenerListaPaqueteServicio(String codPaquete)
	{
		CPaqueteServicioDAO paqueteServicioDao=new CPaqueteServicioDAO();
		paqueteServicioDao.asignarListaPaqueteServicios(paqueteServicioDao.recuperarPaqueteServiciosBD(codPaquete));
		return paqueteServicioDao.getListaPaqueteServicios();
	}
	public ArrayList<CPaqueteActividad> obtenerListaPaqueteActividad(String codPaquete)
	{
		CPaqueteActividadDAO paqueteActividadDao=new CPaqueteActividadDAO();
		paqueteActividadDao.asignarListaPaqueteActividaes(paqueteActividadDao.recuperarPaqueteActividades(codPaquete));
		return paqueteActividadDao.getListaPaqueteActividades();
	}
}
