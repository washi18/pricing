package com.pricing.model;

import java.util.ArrayList;

import com.pricing.model.CDestinoConHoteles;

public class CReservaPaqueteCategoriaHotel 
{
	private long codReservaPCategoria;// bigint,               --codigo de la reserva de categoria por paquete
	private long nReservaPaqueteCod;// bigint,                        --codigo de reserva
	private String codPaqueteCategoriaH;// varchar(10),               --codigo categoria hotel por paquete
	private int nNroPersonasSimple; //int,				--numero de personas que ocuparan una habitacion simple
	private Number nPrecioTotalSimple;// decimal(10,2),	        --precio del nro de habitaciones simples reservadas
	private int nNroPersonasDoble;// int,				--numero de personas que ocuparan una habitacion doble
	private Number nPrecioTotalDoble;// decimal(10,2),		--precio del nro de habitaciones dobles reservadas
	private int nNroPersonasTriple;// int,				--numero de personas que ocuparan una habitacion triple
	private Number nPrecioTotalTriple;// decimal(10,2),		--precio del nro de habitaciones triples reservadas
	private String precioCamaAdicional;
	private String Categoria;
	private boolean conHotel;
	private boolean conCamaAdicional;
	private boolean sinCamaAdicional;
	private boolean disableSimple;
	private boolean disableDoble;
	private boolean disableTriple;
	private boolean disableCamaAdicional;
	private String colorSimple;
	private String colorDoble;
	private String colorTriple;
	private String colorCamaAdicional;
	public String COLOR_ACTIVO="background: #13B2D9;border-radius:5px;padding:10px;";
	public String COLOR_DESACTIVO="background: rgba(0,0,0,0.2);border-radius: 5px;padding:10px;";
	private ArrayList<CDestinoConHoteles> listaCategoriaDestinosHoteles;
	//=================================
	public String getCodPaqueteCategoriaH() {
		return codPaqueteCategoriaH;
	}
	public void setCodPaqueteCategoriaH(String codPaqueteCategoriaH) {
		this.codPaqueteCategoriaH = codPaqueteCategoriaH;
	}
	public long getnReservaPaqueteCod() {
		return nReservaPaqueteCod;
	}
	public void setnReservaPaqueteCod(long nReservaPaqueteCod) {
		this.nReservaPaqueteCod = nReservaPaqueteCod;
	}
	public int getnNroPersonasSimple() {
		return nNroPersonasSimple;
	}
	public void setnNroPersonasSimple(int nNroPersonasSimple) {
		this.nNroPersonasSimple = nNroPersonasSimple;
	}
	public Number getnPrecioTotalSimple() {
		return nPrecioTotalSimple;
	}
	public void setnPrecioTotalSimple(Number nPrecioTotalSimple) {
		this.nPrecioTotalSimple = nPrecioTotalSimple;
	}
	public int getnNroPersonasDoble() {
		return nNroPersonasDoble;
	}
	public void setnNroPersonasDoble(int nNroPersonasDoble) {
		this.nNroPersonasDoble = nNroPersonasDoble;
	}
	public Number getnPrecioTotalDoble() {
		return nPrecioTotalDoble;
	}
	public void setnPrecioTotalDoble(Number nPrecioTotalDoble) {
		this.nPrecioTotalDoble = nPrecioTotalDoble;
	}
	public int getnNroPersonasTriple() {
		return nNroPersonasTriple;
	}
	public void setnNroPersonasTriple(int nNroPersonasTriple) {
		this.nNroPersonasTriple = nNroPersonasTriple;
	}
	public Number getnPrecioTotalTriple() {
		return nPrecioTotalTriple;
	}
	public void setnPrecioTotalTriple(Number nPrecioTotalTriple) {
		this.nPrecioTotalTriple = nPrecioTotalTriple;
	}
	public long getCodReservaPCategoria() {
		return codReservaPCategoria;
	}
	public void setCodReservaPCategoria(long codReservaPCategoria) {
		this.codReservaPCategoria = codReservaPCategoria;
	}
	public String getCategoria() {
		return Categoria;
	}
	public void setCategoria(String categoria) {
		Categoria = categoria;
	}
	public boolean isConHotel() {
		return conHotel;
	}
	public void setConHotel(boolean conHotel) {
		this.conHotel = conHotel;
	}
	public String getColorSimple() {
		return colorSimple;
	}
	public void setColorSimple(String colorSimple) {
		this.colorSimple = colorSimple;
	}
	public String getColorDoble() {
		return colorDoble;
	}
	public void setColorDoble(String colorDoble) {
		this.colorDoble = colorDoble;
	}
	public String getColorTriple() {
		return colorTriple;
	}
	public void setColorTriple(String colorTriple) {
		this.colorTriple = colorTriple;
	}
	public boolean isDisableSimple() {
		return disableSimple;
	}
	public void setDisableSimple(boolean disableSimple) {
		this.disableSimple = disableSimple;
	}
	public boolean isDisableDoble() {
		return disableDoble;
	}
	public void setDisableDoble(boolean disableDoble) {
		this.disableDoble = disableDoble;
	}
	public boolean isDisableTriple() {
		return disableTriple;
	}
	public void setDisableTriple(boolean disableTriple) {
		this.disableTriple = disableTriple;
	}
	public String getPrecioCamaAdicional() {
		return precioCamaAdicional;
	}
	public void setPrecioCamaAdicional(String precioCamaAdicional) {
		this.precioCamaAdicional = precioCamaAdicional;
	}
	public boolean isConCamaAdicional() {
		return conCamaAdicional;
	}
	public void setConCamaAdicional(boolean conCamaAdicional) {
		this.conCamaAdicional = conCamaAdicional;
	}
	public boolean isSinCamaAdicional() {
		return sinCamaAdicional;
	}
	public void setSinCamaAdicional(boolean sinCamaAdicional) {
		this.sinCamaAdicional = sinCamaAdicional;
	}
	public boolean isDisableCamaAdicional() {
		return disableCamaAdicional;
	}
	public void setDisableCamaAdicional(boolean disableCamaAdicional) {
		this.disableCamaAdicional = disableCamaAdicional;
	}
	public String getColorCamaAdicional() {
		return colorCamaAdicional;
	}
	public void setColorCamaAdicional(String colorCamaAdicional) {
		this.colorCamaAdicional = colorCamaAdicional;
	}
	public ArrayList<CDestinoConHoteles> getListaCategoriaDestinosHoteles() {
		return listaCategoriaDestinosHoteles;
	}
	public void setListaCategoriaDestinosHoteles(
			ArrayList<CDestinoConHoteles> listaCategoriaDestinosHoteles) {
		this.listaCategoriaDestinosHoteles = listaCategoriaDestinosHoteles;
	}
	//================================
	public CReservaPaqueteCategoriaHotel() {
		// TODO Auto-generated constructor stub
		this.nReservaPaqueteCod=0;
		this.codPaqueteCategoriaH ="";
		this.nNroPersonasSimple =0;
		this.nPrecioTotalSimple =0.0;
		this.nNroPersonasDoble = 0;
		this.nPrecioTotalDoble =0.0;
		this.nNroPersonasTriple =0;
		this.nPrecioTotalTriple =0.0;
		this.Categoria="";
		this.conHotel=false;
		this.conCamaAdicional=false;
		this.sinCamaAdicional=true;
		this.disableSimple=false;
		this.disableDoble=false;
		this.disableTriple=false;
		this.disableCamaAdicional=true;
		this.colorSimple=COLOR_ACTIVO;
		this.colorDoble=COLOR_ACTIVO;
		this.colorTriple=COLOR_ACTIVO;
		this.colorCamaAdicional=COLOR_DESACTIVO;
		this.precioCamaAdicional="0.0";
		this.listaCategoriaDestinosHoteles=new ArrayList<CDestinoConHoteles>();
	}
	public CReservaPaqueteCategoriaHotel(long codReservaPCategoria,
			long nReservaPaqueteCod, String codPaqueteCategoriaH,
			int nNroPersonasSimple, Number nPrecioTotalSimple,
			int nNroPersonasDoble, Number nPrecioTotalDoble,
			int nNroPersonasTriple, Number nPrecioTotalTriple) {
		this.codReservaPCategoria = codReservaPCategoria;
		this.nReservaPaqueteCod = nReservaPaqueteCod;
		this.codPaqueteCategoriaH = codPaqueteCategoriaH;
		this.nNroPersonasSimple = nNroPersonasSimple;
		this.nPrecioTotalSimple = nPrecioTotalSimple;
		this.nNroPersonasDoble = nNroPersonasDoble;
		this.nPrecioTotalDoble = nPrecioTotalDoble;
		this.nNroPersonasTriple = nNroPersonasTriple;
		this.nPrecioTotalTriple = nPrecioTotalTriple;
	}
	
}