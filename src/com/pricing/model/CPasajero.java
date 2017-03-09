package com.pricing.model;

public class CPasajero 
{
	private long codPasajero;// int,                                --codigo del pasajero que realiza la reserva
	private String cReservaCod;// varchar(10),			--codigo de la reserva
	private int nNro;// int,					--numero de pasajero
	private String cNroDoc;// varchar(12),				--numero de documento de identidad del pasajero
	private int nTipoDoc;// int,					--codigo del tipo de documento
	private String TipoDocumento;
	private String cApellidos;// varchar(100),			--apellidos del pasajero
	private String cNombres;// varchar(100),				--nombres del pasajero
	private int nPaisCod;// int,					--codigo del pais
	private String nombrePais;
	private String cSexo;// char(1),					--sexo del pasajero M,F
	private int nEdad;// int,
	private String textoEdad;
	private String Nombres_Apellidos;
	private boolean esEdit;
	private String cUrlDocumento;
	private String cUrlMostrarDocumento;
	private boolean selectM;
	private boolean selectF;
	private boolean SelectPasajero;
	//===========================
	public String getcReservaCod() {
		return cReservaCod;
	}
	public void setcReservaCod(String cReservaCod) {
		this.cReservaCod = cReservaCod;
	}
	public int getnNro() {
		return nNro;
	}
	public void setnNro(int nNro) {
		this.nNro = nNro;
	}
	public String getcNroDoc() {
		return cNroDoc;
	}
	public void setcNroDoc(String cNroDoc) {
		this.cNroDoc = cNroDoc;
	}
	public int getnTipoDoc() {
		return nTipoDoc;
	}
	public void setnTipoDoc(int nTipoDoc) {
		this.nTipoDoc = nTipoDoc;
	}
	public String getcApellidos() {
		return cApellidos;
	}
	public void setcApellidos(String cApellidos) {
		this.cApellidos = cApellidos;
	}
	public String getcNombres() {
		return cNombres;
	}
	public void setcNombres(String cNombres) {
		this.cNombres = cNombres;
	}
	public int getnPaisCod() {
		return nPaisCod;
	}
	public void setnPaisCod(int nPaisCod) {
		this.nPaisCod = nPaisCod;
	}
	public String getcSexo() {
		return cSexo;
	}
	public void setcSexo(String cSexo) {
		this.cSexo = cSexo;
	}
	public int getnEdad() {
		return nEdad;
	}
	public void setnEdad(int nEdad) {
		this.nEdad = nEdad;
	}
	public String getTipoDocumento() {
		return TipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		TipoDocumento = tipoDocumento;
	}
	public String getNombrePais() {
		return nombrePais;
	}
	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}
	public boolean isEsEdit() {
		return esEdit;
	}
	public void setEsEdit(boolean esEdit) {
		this.esEdit = esEdit;
	}
	public String getcUrlDocumento() {
		return cUrlDocumento;
	}
	public void setcUrlDocumento(String cUrlDocumento) {
		this.cUrlDocumento = cUrlDocumento;
	}
	public boolean isSelectM() {
		return selectM;
	}
	public void setSelectM(boolean selectM) {
		this.selectM = selectM;
	}
	public boolean isSelectF() {
		return selectF;
	}
	public void setSelectF(boolean selectF) {
		this.selectF = selectF;
	}
	public long getCodPasajero() {
		return codPasajero;
	}
	public void setCodPasajero(long codPasajero) {
		this.codPasajero = codPasajero;
	}
	public String getTextoEdad() {
		return textoEdad;
	}
	public void setTextoEdad(String textoEdad) {
		this.textoEdad = textoEdad;
	}
	public String getcUrlMostrarDocumento() {
		return cUrlMostrarDocumento;
	}
	public void setcUrlMostrarDocumento(String cUrlMostrarDocumento) {
		this.cUrlMostrarDocumento = cUrlMostrarDocumento;
	}
	public boolean isSelectPasajero() {
		return SelectPasajero;
	}
	public void setSelectPasajero(boolean selectPasajero) {
		SelectPasajero = selectPasajero;
	}
	public String getNombres_Apellidos() {
		return Nombres_Apellidos;
	}
	public void setNombres_Apellidos(String nombres_Apellidos) {
		Nombres_Apellidos = nombres_Apellidos;
	}
	//==================================
	public CPasajero() {
		// TODO Auto-generated constructor stub
		this.cReservaCod ="";
		this.nNro =0;
		this.cNroDoc ="";
		this.nTipoDoc =8;//valor por defecto
		this.TipoDocumento ="";
		this.cApellidos ="";
		this.cNombres ="";
		this.nPaisCod =245;//valor por defecto
		this.nombrePais ="";
		this.cSexo ="";
		this.nEdad =11;
		this.textoEdad="11";
		this.cUrlDocumento ="";
		this.cUrlMostrarDocumento="";
		this.selectM=false;
		this.selectF=false;
		this.SelectPasajero=false;
		this.esEdit=false;
		this.Nombres_Apellidos="";
	}
	public CPasajero(long codPasajero, String cReservaCod, int nNro,
			String cNroDoc, int nTipoDoc, String tipoDocumento,
			String cApellidos, String cNombres, int nPaisCod,
			String nombrePais, String cSexo, int nEdad, boolean esEdit,
			String cUrlDocumento) {
		this.codPasajero = codPasajero;
		this.cReservaCod = cReservaCod;
		this.nNro = nNro;
		this.cNroDoc = cNroDoc;
		this.nTipoDoc = nTipoDoc;
		TipoDocumento = tipoDocumento;
		this.cApellidos = cApellidos;
		this.cNombres = cNombres;
		this.nPaisCod = nPaisCod;
		this.nombrePais = nombrePais;
		this.cSexo = cSexo;
		this.nEdad = nEdad;
		this.esEdit = esEdit;
		this.cUrlDocumento = cUrlDocumento;
	}
	public CPasajero(String tipoDocumento,
			String cApellidos, String cNombres,
			String nombrePais, int nEdad,String nrodoc,String csexo){
		this.TipoDocumento=tipoDocumento;
		this.cApellidos=cApellidos;
		this.cNombres=cNombres;
		this.nombrePais=nombrePais;
		this.nEdad=nEdad;
		this.cNroDoc=nrodoc;
		this.cSexo=csexo;
	}
}
