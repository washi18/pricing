package com.pricing.model;

import java.util.ArrayList;

public class CDestino 
{
	private int nDestinoCod; //int,				--codigo del destino
	private String cDestino;// varchar(100),				--descripcion del destino
	private boolean bEstado;// boolean,
	private int nCodPostal;//int,
	private String nameDepartamento;
	private int nNoches;
	private int nOrdenItinerario;
	private boolean editable;
	private boolean conCaminoInka;
	private boolean sinCaminoInka;
	private boolean puedeCaminoInka;
	private String color_btn_activo;
	private String color_btn_desactivo;
	public String COLOR_ACTIVO="background:#3BA420;";
	public String COLOR_DESACTIVO="background:#DA0613;";
	public String COLOR_TRANSPARENT="background:transparent;";
	private boolean estado_activo;
	private boolean estado_desactivo;
	private boolean seleccionado;
	private String latitud;
	private String longitud;
	private String urlImagen;
	//======================
	
	public int getnDestinoCod() {
		return nDestinoCod;
	}
	
	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}

	public String getLatitud() {
		return latitud;
	}
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}
	public String getLongitud() {
		return longitud;
	}
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	public void setnDestinoCod(int nDestinoCod) {
		this.nDestinoCod = nDestinoCod;
	}
	public String getcDestino() {
		return cDestino;
	}
	public void setcDestino(String cDestino) {
		this.cDestino = cDestino;
	}
	public boolean isbEstado() {
		return bEstado;
	}
	public void setbEstado(boolean bEstado) {
		this.bEstado = bEstado;
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
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
	public boolean isSeleccionado() {
		return seleccionado;
	}
	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}
	public int getnNoches() {
		return nNoches;
	}
	public void setnNoches(int nNoches) {
		this.nNoches = nNoches;
	}
	public int getnCodPostal() {
		return nCodPostal;
	}
	public void setnCodPostal(int nCodPostal) {
		this.nCodPostal = nCodPostal;
	}
	public String getNameDepartamento() {
		return nameDepartamento;
	}
	public void setNameDepartamento(String nameDepartamento) {
		this.nameDepartamento = nameDepartamento;
	}
	public boolean isConCaminoInka() {
		return conCaminoInka;
	}
	public void setConCaminoInka(boolean conCaminoInka) {
		this.conCaminoInka = conCaminoInka;
	}
	public boolean isPuedeCaminoInka() {
		return puedeCaminoInka;
	}
	public void setPuedeCaminoInka(boolean puedeCaminoInka) {
		this.puedeCaminoInka = puedeCaminoInka;
	}
	public boolean isSinCaminoInka() {
		return sinCaminoInka;
	}
	public void setSinCaminoInka(boolean sinCaminoInka) {
		this.sinCaminoInka = sinCaminoInka;
	}
	public int getnOrdenItinerario() {
		return nOrdenItinerario;
	}
	public void setnOrdenItinerario(int nOrdenItinerario) {
		this.nOrdenItinerario = nOrdenItinerario;
	}
	//============================
	public CDestino() {
		// TODO Auto-generated constructor stub
		cDestino="";
		bEstado=false;
		seleccionado=false;
		nCodPostal=0;
		latitud="";
		longitud="";
		urlImagen="img/destinos/destinoxdefecto.png";
	}
	public CDestino(String cDestino){
		this.cDestino=cDestino;
	}
	
	public CDestino(String cDestino,int codDestino,int codPostal)
	{
		this.cDestino=cDestino;
		this.nDestinoCod=codDestino;
		this.nCodPostal=codPostal;
	}
	public CDestino(int nDestinoCod, String cDestino, boolean bEstado,int nCodPostal,String latitud,String longitud,String URLimagen) {
		this.nDestinoCod = nDestinoCod;
		this.cDestino = cDestino;
		this.bEstado = bEstado;
		this.nCodPostal=nCodPostal;
		this.latitud=latitud;
		this.longitud=longitud;
		this.editable=false;
		this.estado_activo=bEstado;
		this.estado_desactivo=!bEstado;
		this.nOrdenItinerario=0;
		this.nNoches=0;
		this.seleccionado=false;
		/**********/
		this.conCaminoInka=false;
		this.sinCaminoInka=true;
		this.puedeCaminoInka=false;
		this.urlImagen=URLimagen;
		this.nameDepartamento=obtenerNombreCodPostal(nCodPostal);
		darColor_estado();
	}
	
	public void darColor_estado()
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
	public void asignaPuedeCaminoInka(CDestino destino)
	{
		if(destino.getnCodPostal()==84)
			destino.setPuedeCaminoInka(true);
	}
	public String obtenerNombreCodPostal(int nCodPostal)
	{
		String name="";
		ArrayList<CCodigoPostal> lista=new ArrayList<CCodigoPostal>();
		lista=(new CCodigoPostal()).listaCodigosPostales();
		for(CCodigoPostal cod:lista)
		{
			if(cod.getCodPostal()==nCodPostal)
			{
				name=cod.getDepartamento();
				break;
			}
		}
		return name;
	}
}