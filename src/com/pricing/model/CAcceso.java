package com.pricing.model;

public class CAcceso 
{
	private int nAccesoCod;// int,
	private int nPerfilCod;// int,
	private boolean accesoIdiomas;// boolean,
	private boolean accesoUpdateDispo;// boolean,
	private boolean accesoEtiqueta;// boolean,
	private boolean accesoImpuesto;// boolean,
	private boolean accesoVisa;// boolean,
	private boolean accesoPaypal;// boolean,
	private boolean accesoMasterdCard;// boolean,
	private boolean accesoWesternUnion;// boolean,
	private boolean accesoRegUsuarios;// boolean,
	private boolean accesoCrearNuevoUser;// boolean,
	private boolean accesoPaquetes;// boolean,
	private boolean accesoServicios;// boolean,
	private boolean accesoSubServicios;// boolean,
	private boolean accesoActividades;// boolean,
	private boolean accesoHoteles;// boolean,
	private boolean accesoDestinos;// boolean,
	private boolean accesoReporReservas;// boolean,
	private boolean accesoReporPagos;// boolean,
	private boolean accesoEstadPagos;// boolean,
	private boolean accesoEstadPaquMasVendidos;// boolean,
	private boolean accesoExtras;
	private boolean visibleAcceso;
	private boolean visibleConfig;
	private boolean visibleUser;
	private boolean visiblePaquetes;
	private boolean visibleReportes;
	/***************************/
	public int getnAccesoCod() {
		return nAccesoCod;
	}
	public void setnAccesoCod(int nAccesoCod) {
		this.nAccesoCod = nAccesoCod;
	}
	public int getnPerfilCod() {
		return nPerfilCod;
	}
	public void setnPerfilCod(int nPerfilCod) {
		this.nPerfilCod = nPerfilCod;
	}
	public boolean isAccesoIdiomas() {
		return accesoIdiomas;
	}
	public void setAccesoIdiomas(boolean accesoIdiomas) {
		this.accesoIdiomas = accesoIdiomas;
	}
	public boolean isAccesoUpdateDispo() {
		return accesoUpdateDispo;
	}
	public void setAccesoUpdateDispo(boolean accesoUpdateDispo) {
		this.accesoUpdateDispo = accesoUpdateDispo;
	}
	public boolean isAccesoEtiqueta() {
		return accesoEtiqueta;
	}
	public void setAccesoEtiqueta(boolean accesoEtiqueta) {
		this.accesoEtiqueta = accesoEtiqueta;
	}
	public boolean isAccesoImpuesto() {
		return accesoImpuesto;
	}
	public void setAccesoImpuesto(boolean accesoImpuesto) {
		this.accesoImpuesto = accesoImpuesto;
	}
	public boolean isAccesoVisa() {
		return accesoVisa;
	}
	public void setAccesoVisa(boolean accesoVisa) {
		this.accesoVisa = accesoVisa;
	}
	public boolean isAccesoPaypal() {
		return accesoPaypal;
	}
	public void setAccesoPaypal(boolean accesoPaypal) {
		this.accesoPaypal = accesoPaypal;
	}
	public boolean isAccesoMasterdCard() {
		return accesoMasterdCard;
	}
	public void setAccesoMasterdCard(boolean accesoMasterdCard) {
		this.accesoMasterdCard = accesoMasterdCard;
	}
	public boolean isAccesoWesternUnion() {
		return accesoWesternUnion;
	}
	public void setAccesoWesternUnion(boolean accesoWesternUnion) {
		this.accesoWesternUnion = accesoWesternUnion;
	}
	public boolean isAccesoRegUsuarios() {
		return accesoRegUsuarios;
	}
	public void setAccesoRegUsuarios(boolean accesoRegUsuarios) {
		this.accesoRegUsuarios = accesoRegUsuarios;
	}
	public boolean isAccesoCrearNuevoUser() {
		return accesoCrearNuevoUser;
	}
	public void setAccesoCrearNuevoUser(boolean accesoCrearNuevoUser) {
		this.accesoCrearNuevoUser = accesoCrearNuevoUser;
	}
	public boolean isAccesoPaquetes() {
		return accesoPaquetes;
	}
	public void setAccesoPaquetes(boolean accesoPaquetes) {
		this.accesoPaquetes = accesoPaquetes;
	}
	public boolean isAccesoServicios() {
		return accesoServicios;
	}
	public void setAccesoServicios(boolean accesoServicios) {
		this.accesoServicios = accesoServicios;
	}
	public boolean isAccesoSubServicios() {
		return accesoSubServicios;
	}
	public void setAccesoSubServicios(boolean accesoSubServicios) {
		this.accesoSubServicios = accesoSubServicios;
	}
	public boolean isAccesoActividades() {
		return accesoActividades;
	}
	public void setAccesoActividades(boolean accesoActividades) {
		this.accesoActividades = accesoActividades;
	}
	public boolean isAccesoHoteles() {
		return accesoHoteles;
	}
	public void setAccesoHoteles(boolean accesoHoteles) {
		this.accesoHoteles = accesoHoteles;
	}
	public boolean isAccesoDestinos() {
		return accesoDestinos;
	}
	public void setAccesoDestinos(boolean accesoDestinos) {
		this.accesoDestinos = accesoDestinos;
	}
	public boolean isAccesoReporReservas() {
		return accesoReporReservas;
	}
	public void setAccesoReporReservas(boolean accesoReporReservas) {
		this.accesoReporReservas = accesoReporReservas;
	}
	public boolean isAccesoReporPagos() {
		return accesoReporPagos;
	}
	public void setAccesoReporPagos(boolean accesoReporPagos) {
		this.accesoReporPagos = accesoReporPagos;
	}
	public boolean isAccesoEstadPagos() {
		return accesoEstadPagos;
	}
	public void setAccesoEstadPagos(boolean accesoEstadPagos) {
		this.accesoEstadPagos = accesoEstadPagos;
	}
	public boolean isAccesoEstadPaquMasVendidos() {
		return accesoEstadPaquMasVendidos;
	}
	public void setAccesoEstadPaquMasVendidos(boolean accesoEstadPaquMasVendidos) {
		this.accesoEstadPaquMasVendidos = accesoEstadPaquMasVendidos;
	}
	public boolean isAccesoExtras() {
		return accesoExtras;
	}
	public void setAccesoExtras(boolean accesoExtras) {
		this.accesoExtras = accesoExtras;
	}
	public boolean isVisibleAcceso() {
		return visibleAcceso;
	}
	public void setVisibleAcceso(boolean visibleAcceso) {
		this.visibleAcceso = visibleAcceso;
	}
	public boolean isVisibleConfig() {
		return visibleConfig;
	}
	public void setVisibleConfig(boolean visibleConfig) {
		this.visibleConfig = visibleConfig;
	}
	public boolean isVisibleUser() {
		return visibleUser;
	}
	public void setVisibleUser(boolean visibleUser) {
		this.visibleUser = visibleUser;
	}
	public boolean isVisiblePaquetes() {
		return visiblePaquetes;
	}
	public void setVisiblePaquetes(boolean visiblePaquetes) {
		this.visiblePaquetes = visiblePaquetes;
	}
	public boolean isVisibleReportes() {
		return visibleReportes;
	}
	public void setVisibleReportes(boolean visibleReportes) {
		this.visibleReportes = visibleReportes;
	}
	/************************/
	public CAcceso() {
		// TODO Auto-generated constructor stub
		this.accesoIdiomas = false;
		this.accesoUpdateDispo = false;
		this.accesoEtiqueta = false;
		this.accesoImpuesto = false;
		this.accesoVisa = false;
		this.accesoPaypal = false;
		this.accesoMasterdCard = false;
		this.accesoWesternUnion = false;
		this.accesoRegUsuarios = false;
		this.accesoCrearNuevoUser = false;
		this.accesoPaquetes = false;
		this.accesoServicios = false;
		this.accesoSubServicios = false;
		this.accesoActividades = false;
		this.accesoHoteles = false;
		this.accesoDestinos = false;
		this.accesoReporReservas = false;
		this.accesoReporPagos = false;
		this.accesoEstadPagos = false;
		this.accesoEstadPaquMasVendidos = false;
		this.accesoExtras=false;
	}
	public CAcceso(int nAccesoCod, int nPerfilCod, boolean accesoIdiomas,
			boolean accesoUpdateDispo, boolean accesoEtiqueta,
			boolean accesoImpuesto, boolean accesoVisa, boolean accesoPaypal,
			boolean accesoMasterdCard, boolean accesoWesternUnion,
			boolean accesoRegUsuarios, boolean accesoCrearNuevoUser,
			boolean accesoPaquetes, boolean accesoServicios,
			boolean accesoSubServicios, boolean accesoActividades,
			boolean accesoHoteles, boolean accesoDestinos,
			boolean accesoReporReservas, boolean accesoReporPagos,
			boolean accesoEstadPagos, boolean accesoEstadPaquMasVendidos) {
		this.nAccesoCod = nAccesoCod;
		this.nPerfilCod = nPerfilCod;
		this.accesoIdiomas = accesoIdiomas;
		this.accesoUpdateDispo = accesoUpdateDispo;
		this.accesoEtiqueta = accesoEtiqueta;
		this.accesoImpuesto = accesoImpuesto;
		this.accesoVisa = accesoVisa;
		this.accesoPaypal = accesoPaypal;
		this.accesoMasterdCard = accesoMasterdCard;
		this.accesoWesternUnion = accesoWesternUnion;
		this.accesoRegUsuarios = accesoRegUsuarios;
		this.accesoCrearNuevoUser = accesoCrearNuevoUser;
		this.accesoPaquetes = accesoPaquetes;
		this.accesoServicios = accesoServicios;
		this.accesoSubServicios = accesoSubServicios;
		this.accesoActividades = accesoActividades;
		this.accesoHoteles = accesoHoteles;
		this.accesoDestinos = accesoDestinos;
		this.accesoReporReservas = accesoReporReservas;
		this.accesoReporPagos = accesoReporPagos;
		this.accesoEstadPagos = accesoEstadPagos;
		this.accesoEstadPaquMasVendidos = accesoEstadPaquMasVendidos;
		//===========================
		this.accesoExtras=true;
//		if(accesoImpuesto || accesoIdiomas)
//			this.accesoExtras=true;
//		else
//			this.accesoExtras=false;
		this.visibleAcceso=false;
		this.visibleConfig=false;
		this.visibleUser=false;
		this.visiblePaquetes=false;
		this.visibleReportes=false;
	}
}
