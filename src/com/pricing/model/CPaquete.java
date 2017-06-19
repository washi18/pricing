package com.pricing.model;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

import com.pricing.dao.CActividadDAO;
import com.pricing.dao.CCalendarioPropioDAO;
import com.pricing.dao.CDestinoDAO;
import com.pricing.dao.CGaleriaHotelDAO;
import com.pricing.dao.CGaleriaPaqueteDAO;
import com.pricing.dao.CPaqueteActividadDAO;
import com.pricing.dao.CPaqueteDestinoDAO;
import com.pricing.dao.CPaqueteServicioDAO;
import com.pricing.dao.CServicioDAO;
import com.pricing.dao.ConfAltoNivelDAO;
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
	private String cItinerarioIdioma1;// text,	
	private String cItinerarioIdioma2;// text,	
	private String cItinerarioIdioma3;// text,	
	private String cItinerarioIdioma4;// text,	
	private String cItinerarioIdioma5;// text,	
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
	private String cUrlReferenciaPaquete;//text
	private int nPorcentajeCobro;// int 
	private int nPagoMinimo;// int 
	private boolean bModoPorcentual;// boolean
	private boolean bModoMinimo;
	private boolean bModoPagoTotal;// boolean
	private boolean bModoPagoPartes;
	private Number nDescuentoMenor_Estudiante;// decimal(10,2) 
	private boolean bSubirDocPax;// boolean 
	private boolean bSubirDoc_Y_LlenarDatosPax;// boolean 
	private boolean bSubirDoc_O_LlenarDatosPax;// boolean 
	private boolean bLlenarDatosUnPax;// boolean 
	private boolean bHotelesConCamaAdicional;// boolean
	private boolean bConCupon;//boolean
	private boolean bSinCupon;
	private boolean bHotelesSinCamaAdicional;
	private String url_pricingPaquete;
	private String nPrecio1_text;
	private String nPrecio2_text;
	private String nPrecio3_text;
	private String nPrecio4_text;
	private String nPrecio5_text;
	private String Titulo;
	private String descripcion;
	private String itinerario;
	private String dias_noches;
	private boolean visibleEspanol;
	private boolean visibleIngles;
	private boolean visiblePortugues;
	private boolean manejo_camino_inca;
	private boolean manejo_propio;
	private boolean manejo_normal;
	private boolean manejoPropio_conCaminoInka;
	private boolean manejo_yourself;
	private boolean conDestino;
	private boolean sinDestino;
	private boolean conServicio;
	private boolean conActividad;
	private boolean conDescuento;
	private boolean sinDescuento;
	private boolean conFechaArribo;
	private boolean conDes_Iti;
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
	private String cFoto1;
	private String cFoto2;
	private String cFoto3;
	private String cFoto4;
	private String cFoto5;
	private ArrayList<CGaleriaPaquete> listaImagenes;
	private boolean abrirEditorItinerario;
	private boolean abrirEditorDescripcion;
	private String manejoSelectYourself;
	//==========================
	
	public String getcPaqueteCod() {
		return cPaqueteCod;
	}
	public String getManejoSelectYourself() {
		return manejoSelectYourself;
	}
	public void setManejoSelectYourself(String manejoSelectYourself) {
		this.manejoSelectYourself = manejoSelectYourself;
	}
	public boolean isAbrirEditorItinerario() {
		return abrirEditorItinerario;
	}
	public void setAbrirEditorItinerario(boolean abrirEditorItinerario) {
		this.abrirEditorItinerario = abrirEditorItinerario;
	}
	public boolean isAbrirEditorDescripcion() {
		return abrirEditorDescripcion;
	}
	public void setAbrirEditorDescripcion(boolean abrirEditorDescripcion) {
		this.abrirEditorDescripcion = abrirEditorDescripcion;
	}
	public String getcItinerarioIdioma1() {
		return cItinerarioIdioma1;
	}
	public void setcItinerarioIdioma1(String cItinerarioIdioma1) {
		this.cItinerarioIdioma1 = cItinerarioIdioma1;
	}
	public String getcItinerarioIdioma2() {
		return cItinerarioIdioma2;
	}
	public void setcItinerarioIdioma2(String cItinerarioIdioma2) {
		this.cItinerarioIdioma2 = cItinerarioIdioma2;
	}
	public String getcItinerarioIdioma3() {
		return cItinerarioIdioma3;
	}
	public void setcItinerarioIdioma3(String cItinerarioIdioma3) {
		this.cItinerarioIdioma3 = cItinerarioIdioma3;
	}
	public String getcItinerarioIdioma4() {
		return cItinerarioIdioma4;
	}
	public void setcItinerarioIdioma4(String cItinerarioIdioma4) {
		this.cItinerarioIdioma4 = cItinerarioIdioma4;
	}
	public String getcItinerarioIdioma5() {
		return cItinerarioIdioma5;
	}
	public void setcItinerarioIdioma5(String cItinerarioIdioma5) {
		this.cItinerarioIdioma5 = cItinerarioIdioma5;
	}
	public ArrayList<CGaleriaPaquete> getListaImagenes() {
		return listaImagenes;
	}
	public void setListaImagenes(ArrayList<CGaleriaPaquete> listaImagenes) {
		this.listaImagenes = listaImagenes;
	}
	public String getcFoto1() {
		return cFoto1;
	}
	public void setcFoto1(String cFoto1) {
		this.cFoto1 = cFoto1;
	}
	public String getcFoto2() {
		return cFoto2;
	}
	public void setcFoto2(String cFoto2) {
		this.cFoto2 = cFoto2;
	}
	public String getcFoto3() {
		return cFoto3;
	}
	public void setcFoto3(String cFoto3) {
		this.cFoto3 = cFoto3;
	}
	public String getcFoto4() {
		return cFoto4;
	}
	public void setcFoto4(String cFoto4) {
		this.cFoto4 = cFoto4;
	}
	public String getcFoto5() {
		return cFoto5;
	}
	public void setcFoto5(String cFoto5) {
		this.cFoto5 = cFoto5;
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
	
	public boolean isManejo_yourself() {
		return manejo_yourself;
	}
	public void setManejo_yourself(boolean manejo_yourself) {
		this.manejo_yourself = manejo_yourself;
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
	public boolean isConFechaArribo() {
		return conFechaArribo;
	}
	public void setConFechaArribo(boolean conFechaArribo) {
		this.conFechaArribo = conFechaArribo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getItinerario() {
		return itinerario;
	}
	public void setItinerario(String itinerario) {
		this.itinerario = itinerario;
	}
	public String getcUrlReferenciaPaquete() {
		return cUrlReferenciaPaquete;
	}
	public void setcUrlReferenciaPaquete(String cUrlReferenciaPaquete) {
		this.cUrlReferenciaPaquete = cUrlReferenciaPaquete;
	}
	public boolean isConDes_Iti() {
		return conDes_Iti;
	}
	public void setConDes_Iti(boolean conDes_Iti) {
		this.conDes_Iti = conDes_Iti;
	}
	public int getnPorcentajeCobro() {
		return nPorcentajeCobro;
	}
	public void setnPorcentajeCobro(int nPorcentajeCobro) {
		this.nPorcentajeCobro = nPorcentajeCobro;
	}
	public int getnPagoMinimo() {
		return nPagoMinimo;
	}
	public void setnPagoMinimo(int nPagoMinimo) {
		this.nPagoMinimo = nPagoMinimo;
	}
	public boolean isbModoPorcentual() {
		return bModoPorcentual;
	}
	public void setbModoPorcentual(boolean bModoPorcentual) {
		this.bModoPorcentual = bModoPorcentual;
	}
	public boolean isbModoPagoTotal() {
		return bModoPagoTotal;
	}
	public void setbModoPagoTotal(boolean bModoPagoTotal) {
		this.bModoPagoTotal = bModoPagoTotal;
	}
	public Number getnDescuentoMenor_Estudiante() {
		return nDescuentoMenor_Estudiante;
	}
	public void setnDescuentoMenor_Estudiante(Number nDescuentoMenor_Estudiante) {
		this.nDescuentoMenor_Estudiante = nDescuentoMenor_Estudiante;
	}
	public boolean isbSubirDocPax() {
		return bSubirDocPax;
	}
	public void setbSubirDocPax(boolean bSubirDocPax) {
		this.bSubirDocPax = bSubirDocPax;
	}
	public boolean isbSubirDoc_Y_LlenarDatosPax() {
		return bSubirDoc_Y_LlenarDatosPax;
	}
	public void setbSubirDoc_Y_LlenarDatosPax(boolean bSubirDoc_Y_LlenarDatosPax) {
		this.bSubirDoc_Y_LlenarDatosPax = bSubirDoc_Y_LlenarDatosPax;
	}
	public boolean isbSubirDoc_O_LlenarDatosPax() {
		return bSubirDoc_O_LlenarDatosPax;
	}
	public void setbSubirDoc_O_LlenarDatosPax(boolean bSubirDoc_O_LlenarDatosPax) {
		this.bSubirDoc_O_LlenarDatosPax = bSubirDoc_O_LlenarDatosPax;
	}
	public boolean isbLlenarDatosUnPax() {
		return bLlenarDatosUnPax;
	}
	public void setbLlenarDatosUnPax(boolean bLlenarDatosUnPax) {
		this.bLlenarDatosUnPax = bLlenarDatosUnPax;
	}
	public boolean isbHotelesConCamaAdicional() {
		return bHotelesConCamaAdicional;
	}
	public void setbHotelesConCamaAdicional(boolean bHotelesConCamaAdicional) {
		this.bHotelesConCamaAdicional = bHotelesConCamaAdicional;
	}
	public boolean isbHotelesSinCamaAdicional() {
		return bHotelesSinCamaAdicional;
	}
	public void setbHotelesSinCamaAdicional(boolean bHotelesSinCamaAdicional) {
		this.bHotelesSinCamaAdicional = bHotelesSinCamaAdicional;
	}
	public boolean isbConCupon() {
		return bConCupon;
	}
	public void setbConCupon(boolean bConCupon) {
		this.bConCupon = bConCupon;
	}
	public boolean isbSinCupon() {
		return bSinCupon;
	}
	public void setbSinCupon(boolean bSinCupon) {
		this.bSinCupon = bSinCupon;
	}
	public boolean isbModoMinimo() {
		return bModoMinimo;
	}
	public void setbModoMinimo(boolean bModoMinimo) {
		this.bModoMinimo = bModoMinimo;
	}
	public boolean isbModoPagoPartes() {
		return bModoPagoPartes;
	}
	public void setbModoPagoPartes(boolean bModoPagoPartes) {
		this.bModoPagoPartes = bModoPagoPartes;
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
		manejo_yourself=false;
		nDiaCaminoInka=0;
		cTituloIdioma1="";
		cTituloIdioma2="";
		cTituloIdioma3="";
		cTituloIdioma4="";
		cTituloIdioma5="";
		cDescripcionIdioma1="";
		cDescripcionIdioma2="";
		cDescripcionIdioma3="";
		cDescripcionIdioma4="";
		cDescripcionIdioma5="";
		cItinerarioIdioma1="";
		cItinerarioIdioma2="";
		cItinerarioIdioma3="";
		cItinerarioIdioma4="";
		cItinerarioIdioma5="";
		cUrlReferenciaPaquete="";
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
		abrirEditorItinerario=false;
		abrirEditorDescripcion=false;
		nroDestinosSelect=0;
		ordenDesSelect=0;
		//nuevas configuraciones
		this.nPorcentajeCobro=0;
		this.nPagoMinimo=0;
		this.bModoPorcentual=true;
		this.bModoMinimo=false;
		this.bModoPagoTotal=false;
		this.bModoPagoPartes=true;
		this.nDescuentoMenor_Estudiante=0;
		this.bSubirDocPax=false;
		this.bSubirDoc_Y_LlenarDatosPax=false;
		this.bSubirDoc_O_LlenarDatosPax=false;
		this.bLlenarDatosUnPax=true;
		this.bHotelesConCamaAdicional=false;
		this.bHotelesSinCamaAdicional=true;
		this.bConCupon=false;
		this.bSinCupon=true;
		//======================
		this.cFoto1="img/tours/tourxdefecto.png";
		this.cFoto2="img/tours/tourxdefecto.png";
		this.cFoto3="img/tours/tourxdefecto.png";
		this.cFoto4="img/tours/tourxdefecto.png";
		this.cFoto5="img/tours/tourxdefecto.png";
		manejoSelectYourself="";
		this.listaImagenes=new ArrayList<CGaleriaPaquete>();
	}
	public CPaquete(String cPaqueteCod, String cTituloIdioma1,
			String cTituloIdioma2, String cTituloIdioma3,
			String cTituloIdioma4, String cTituloIdioma5,
			String cDescripcionIdioma1, String cDescripcionIdioma2,
			String cDescripcionIdioma3, String cDescripcionIdioma4,
			String cDescripcionIdioma5, int nDias, int nNoches,
			Number nPrecioUno, Number nPrecioDos, Number nPrecioTres,
			Number nPrecioCuatro, Number nPrecioCinco, String cDisponibilidad,
			boolean bEstado,String cItinerarioIdioma1,String cItinerarioIdioma2,
			String cItinerarioIdioma3,String cItinerarioIdioma4,
			String cItinerarioIdioma5,String cUrlReferenciaPaquete,
			int nPorcentajeCobro,int nPagoMinimo,boolean bModoPorcentual,
			boolean bModoPagoTotal,Number nDescuentoMenor_Estudiante,
			boolean bSubirDocPax,boolean bSubirDoc_Y_LlenarDatosPax,
			boolean bSubirDoc_O_LlenarDatosPax,boolean bLlenarDatosUnPax,
			boolean bHotelesConCamaAdicional,boolean bConCupon)
	{
		this.cPaqueteCod=cPaqueteCod;
		this.cTituloIdioma1=cTituloIdioma1;
		this.cTituloIdioma2=cTituloIdioma2;
		this.cTituloIdioma3=cTituloIdioma3;
		this.cTituloIdioma4=cTituloIdioma4;
		this.cTituloIdioma5=cTituloIdioma5;
		this.cDescripcionIdioma1=cDescripcionIdioma1;
		this.cDescripcionIdioma2=cDescripcionIdioma2;
		this.cDescripcionIdioma3=cDescripcionIdioma3;
		this.cDescripcionIdioma4=cDescripcionIdioma4;
		this.cDescripcionIdioma5=cDescripcionIdioma5;
		this.nDias=nDias;
		this.nNoches=nNoches;
		this.nPrecioUno=nPrecioUno;
		this.nPrecioDos=nPrecioDos;
		this.nPrecioTres=nPrecioTres;
		this.nPrecioCuatro=nPrecioCuatro;
		this.nPrecioCinco=nPrecioCinco;
		this.cDisponibilidad=cDisponibilidad;
		this.bEstado=bEstado;
		this.cItinerarioIdioma1=cItinerarioIdioma1;
		this.cItinerarioIdioma2=cItinerarioIdioma2;
		this.cItinerarioIdioma3=cItinerarioIdioma3;
		this.cItinerarioIdioma4=cItinerarioIdioma4;
		this.cItinerarioIdioma5=cItinerarioIdioma5;
		this.descripcion="";
		this.itinerario="";
		this.cUrlReferenciaPaquete=cUrlReferenciaPaquete;
		//===================
		this.nPorcentajeCobro=nPorcentajeCobro;
		this.nPagoMinimo=nPagoMinimo;
		this.bModoPorcentual=bModoPorcentual;
		this.bModoPagoTotal=bModoPagoTotal;
		this.nDescuentoMenor_Estudiante=nDescuentoMenor_Estudiante;
		this.bSubirDocPax=bSubirDocPax;
		this.bSubirDoc_Y_LlenarDatosPax=bSubirDoc_Y_LlenarDatosPax;
		this.bSubirDoc_O_LlenarDatosPax=bSubirDoc_O_LlenarDatosPax;
		this.bLlenarDatosUnPax=bLlenarDatosUnPax;
		this.bHotelesConCamaAdicional=bHotelesConCamaAdicional;
		this.bHotelesSinCamaAdicional=!bHotelesConCamaAdicional;
		this.bConCupon=bConCupon;
		//===================
		listaDestinos=new ArrayList<CDestino>();
		listaServicios=new ArrayList<CServicio>();
		listaSubServicios=new ArrayList<CSubServicio>();
		listaActividades=new ArrayList<CActividad>();
		listaAniosCalendarioPropio=new ArrayList<CCalendarioPropio>();
		listaDiasCalendarioPropio=new ArrayList<CDiaPropio>();
		listaImagenes=new ArrayList<CGaleriaPaquete>();
		determinarSiHayDescuento();
		determinarSiEsConFechaArribo();
		determinarSiHayDes_Iti();
	}
	public CPaquete(String cPaqueteCod, String cTituloIdioma1,
			String cTituloIdioma2, String cTituloIdioma3,
			String cTituloIdioma4, String cTituloIdioma5,
			String cDescripcionIdioma1, String cDescripcionIdioma2,
			String cDescripcionIdioma3, String cDescripcionIdioma4,
			String cDescripcionIdioma5, int nDias, int nNoches,
			Number nPrecioUno, Number nPrecioDos, Number nPrecioTres,
			Number nPrecioCuatro, Number nPrecioCinco, String cDisponibilidad,
			boolean bEstado,String cFoto1,
			String cFoto2,String cFoto3,String cFoto4,String cFoto5,String cItinerarioIdioma1,String cItinerarioIdioma2,
			String cItinerarioIdioma3,String cItinerarioIdioma4,String cItinerarioIdioma5,
			String cUrlReferenciaPaquete,
			int nPorcentajeCobro,int nPagoMinimo,boolean bModoPorcentual,
			boolean bModoPagoTotal,Number nDescuentoMenor_Estudiante,
			boolean bSubirDocPax,boolean bSubirDoc_Y_LlenarDatosPax,
			boolean bSubirDoc_O_LlenarDatosPax,boolean bLlenarDatosUnPax,
			boolean bHotelesConCamaAdicional,boolean bConCupon) throws UnsupportedEncodingException {
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
		this.cItinerarioIdioma1=cItinerarioIdioma1;
		this.cItinerarioIdioma2=cItinerarioIdioma2;
		this.cItinerarioIdioma3=cItinerarioIdioma3;
		this.cItinerarioIdioma4=cItinerarioIdioma4;
		this.cItinerarioIdioma5=cItinerarioIdioma5;
		this.cUrlReferenciaPaquete=cUrlReferenciaPaquete;
		this.nPorcentajeCobro=nPorcentajeCobro;
		this.nPagoMinimo=nPagoMinimo;
		this.bModoPorcentual=bModoPorcentual;
		this.bModoMinimo=!bModoPorcentual;
		this.bModoPagoTotal=bModoPagoTotal;
		this.bModoPagoPartes=!bModoPagoTotal;
		this.nDescuentoMenor_Estudiante=nDescuentoMenor_Estudiante;
		this.bSubirDocPax=bSubirDocPax;
		this.bSubirDoc_Y_LlenarDatosPax=bSubirDoc_Y_LlenarDatosPax;
		this.bSubirDoc_O_LlenarDatosPax=bSubirDoc_O_LlenarDatosPax;
		this.bLlenarDatosUnPax=bLlenarDatosUnPax;
		this.bHotelesConCamaAdicional=bHotelesConCamaAdicional;
		this.bHotelesSinCamaAdicional=!bHotelesConCamaAdicional;
		this.bConCupon=bConCupon;
		this.bSinCupon=!bConCupon;
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
		this.cFoto1=cFoto1;
		this.cFoto2=cFoto2;
		this.cFoto3=cFoto3;
		this.cFoto4=cFoto4;
		this.cFoto5=cFoto5;
		this.editable=false;
		this.visibleEspanol=true;
		this.visibleIngles=false;
		this.visiblePortugues=false;
		this.conCalendarioPropio=false;
		this.abrirEditorItinerario=false;
		this.abrirEditorDescripcion=false;
		this.url_pricingPaquete="https://www.e-ranti.com/pricing_info/?var1="+cPaqueteCod;
		/***Recuperando lo que contiene el paquete***/
		listaDestinos=new ArrayList<CDestino>();
		listaServicios=new ArrayList<CServicio>();
		listaSubServicios=new ArrayList<CSubServicio>();
		listaActividades=new ArrayList<CActividad>();
		listaAniosCalendarioPropio=new ArrayList<CCalendarioPropio>();
		listaDiasCalendarioPropio=new ArrayList<CDiaPropio>();
		listaImagenes=new ArrayList<CGaleriaPaquete>();
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
		//recuperacion de imagenes
		CGaleriaPaqueteDAO galeriaPaqueteDao=new CGaleriaPaqueteDAO();
		galeriaPaqueteDao.asignarListaImagenesPaquete(galeriaPaqueteDao.recuperarImagenesPaqueteBD(cPaqueteCod));
		setListaImagenes(galeriaPaqueteDao.getListaImagenesPaquete());
		System.out.println("cual es el tamanio ....es->"+this.listaImagenes.size());
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
	public void cargarDatosRelacionadosAlPaquete() throws UnsupportedEncodingException
	{
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
				//recuperacion de imagenes
				CGaleriaPaqueteDAO galeriaPaqueteDao=new CGaleriaPaqueteDAO();
				galeriaPaqueteDao.asignarListaImagenesPaquete(galeriaPaqueteDao.recuperarImagenesPaqueteBD(cPaqueteCod));
				setListaImagenes(galeriaPaqueteDao.getListaImagenesPaquete());
				System.out.println("cual es el tamanio ....es->"+this.listaImagenes.size());
				inicializarEstadosDeDestinosYServicios();
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
	public void determinarSiEsConFechaArribo()
	{
		ConfAltoNivelDAO confAltoNivelDAO=new ConfAltoNivelDAO();
		confAltoNivelDAO.asignarListaConfAltoNivel(confAltoNivelDAO.recuperarconfAltoNivel("arribo"));
		if(confAltoNivelDAO.getoConfAltoNivel().isbEstado()){
			conFechaArribo=true;
		}else {
			conFechaArribo=false;
		}
	}
	public void determinarSiHayDes_Iti()
	{
		ConfAltoNivelDAO confAltoNivelDAO=new ConfAltoNivelDAO();
		confAltoNivelDAO.asignarListaConfAltoNivel(confAltoNivelDAO.recuperarconfAltoNivel("desc_iti"));
		if(confAltoNivelDAO.getoConfAltoNivel().isbEstado()){
			conDes_Iti=true;
		}else {
			conDes_Iti=false;
		}
	}
	public void determinarTipoDeManejoPaquete(String manejo)
	{
		if(manejo.equals("CAMINO_INKA_CLASICO"))
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
		}else if(manejo.equals("MACHUPICCHU")){
			manejo_camino_inca=false;
			manejo_propio=false;
			manejo_normal=false;
			manejo_yourself=true;
			manejoSelectYourself="Machupicchu";
		}else if(manejo.equals("MACHUPICCHU_HUAYNAPICCHU_1G"))
		{
			manejo_camino_inca=false;
			manejo_propio=false;
			manejo_normal=false;
			manejo_yourself=true;
			manejoSelectYourself="Machupicchu + Huaynapicchu 1G 7:00-8:00 a.m.";
		}else if(manejo.equals("MACHUPICCHU_HUAYNAPICCHU_2G")){
			manejo_camino_inca=false;
			manejo_propio=false;
			manejo_normal=false;
			manejo_yourself=true;
			manejoSelectYourself="Machupicchu + Huaynapicchu 2G 10:00-11:00 a.m.";
		}else if(manejo.equals("MACHUPICCHU_MONTANA_1G")){
			manejo_camino_inca=false;
			manejo_propio=false;
			manejo_normal=false;
			manejo_yourself=true;
			manejoSelectYourself="Machupicchu + Montaña 7:00 a.m.-8:00 a.m.";
		}else if(manejo.equals("MACHUPICCHU_MONTANA_2G")){
			manejo_camino_inca=false;
			manejo_propio=false;
			manejo_normal=false;
			manejo_yourself=true;
			manejoSelectYourself="Machupicchu + Montaña 9:00 a.m.-10:00 a.m.";
		}else if(manejo.equals("CAMINO_INKA_CLASICO_YOURSELF")){
			manejo_camino_inca=false;
			manejo_propio=false;
			manejo_normal=false;
			manejo_yourself=true;
			manejoSelectYourself="Camino Inka 4 dias 3 noche 7:00 a.m.-10:00 a.m.";
		}else if(manejo.equals("CAMINO_INKA_CORTO")){
			manejo_camino_inca=false;
			manejo_propio=false;
			manejo_normal=false;
			manejo_yourself=true;
			manejoSelectYourself="Camino Inka 2 dias 1 noche 7:00 a.m.-10:00 a.m.";
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
