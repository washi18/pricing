package com.pricing.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pricing.model.CAcceso;

public class CAccesoDAO extends CConexion
{
	private CAcceso oAcceso;
	private ArrayList<CAcceso> listaAccesos;
	/*******************/
	public CAcceso getoAcceso() {
		return oAcceso;
	}
	public void setoAcceso(CAcceso oAcceso) {
		this.oAcceso = oAcceso;
	}
	public ArrayList<CAcceso> getListaAccesos() {
		return listaAccesos;
	}
	public void setListaAccesos(ArrayList<CAcceso> listaAccesos) {
		this.listaAccesos = listaAccesos;
	}
	/*********************/
	public CAccesoDAO() {
		// TODO Auto-generated constructor stub
		super();
	}
	public List insertarAcceso(CAcceso acceso)
	{
		Object[] values={acceso.getnPerfilCod(),acceso.isAccesoIdiomas(),
				acceso.isAccesoUpdateDispo(),acceso.isAccesoEtiqueta(),
				acceso.isAccesoImpuesto(),acceso.isAccesoVisa(),
				acceso.isAccesoPaypal(),acceso.isAccesoMasterdCard(),
				acceso.isAccesoWesternUnion(),acceso.isAccesoRegUsuarios(),
				acceso.isAccesoCrearNuevoUser(),acceso.isAccesoPaquetes(),
				acceso.isAccesoServicios(),acceso.isAccesoSubServicios(),
				acceso.isAccesoActividades(),acceso.isAccesoHoteles(),
				acceso.isAccesoDestinos(),acceso.isAccesoReporPagos(),
				acceso.isAccesoReporReservas(),acceso.isAccesoEstadPagos(),
				acceso.isAccesoEstadPaquMasVendidos()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_RegistrarAcceso", values);
	}
	public List actualizarAcceso(CAcceso acceso)
	{
		Object[] values={acceso.getnAccesoCod(),acceso.isAccesoIdiomas(),
				acceso.isAccesoUpdateDispo(),acceso.isAccesoEtiqueta(),
				acceso.isAccesoImpuesto(),acceso.isAccesoVisa(),
				acceso.isAccesoPaypal(),acceso.isAccesoMasterdCard(),
				acceso.isAccesoWesternUnion(),acceso.isAccesoRegUsuarios(),
				acceso.isAccesoCrearNuevoUser(),acceso.isAccesoPaquetes(),
				acceso.isAccesoServicios(),acceso.isAccesoSubServicios(),
				acceso.isAccesoActividades(),acceso.isAccesoHoteles(),
				acceso.isAccesoDestinos(),acceso.isAccesoReporPagos(),
				acceso.isAccesoReporReservas(),acceso.isAccesoEstadPagos(),
				acceso.isAccesoEstadPaquMasVendidos()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_ModificarAcceso", values);
	}
	public List RecuperarTodosAccesos()
	{
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarTodosAccesos");
	}
	public List recuperarAcceso(int codPerfil)
	{
		Object[] values={codPerfil};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarAcceso", values);
	}
	public void asignarListaAccesos(List lista)
	{
		listaAccesos=new ArrayList<CAcceso>();
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaAccesos.add(new CAcceso((int)row.get("naccesocod"),
										(int)row.get("nperfilcod"),
										(boolean)row.get("accesoidiomas"),
										(boolean)row.get("accesoupdatedispo"),
										(boolean)row.get("accesoetiqueta"),
										(boolean)row.get("accesoimpuesto"),
										(boolean)row.get("accesovisa"),
										(boolean)row.get("accesopaypal"),
										(boolean)row.get("accesomasterdcard"),
										(boolean)row.get("accesowesternunion"),
										(boolean)row.get("accesoregusuarios"),
										(boolean)row.get("accesocrearnuevouser"),
										(boolean)row.get("accesopaquetes"),
										(boolean)row.get("accesoservicios"),
										(boolean)row.get("accesosubservicios"),
										(boolean)row.get("accesoactividades"),
										(boolean)row.get("accesohoteles"),
										(boolean)row.get("accesodestinos"),
										(boolean)row.get("accesoreporreservas"),
										(boolean)row.get("accesoreporpagos"),
										(boolean)row.get("accesoestadpagos"),
										(boolean)row.get("accesoestadpaqumasvendidos")));
		}
	}
	public void asignarAccesoBD(List lista)
	{
		Map row=(Map)lista.get(0);
		oAcceso=new CAcceso((int)row.get("naccesocod"),
			(int)row.get("nperfilcod"),
			(boolean)row.get("accesoidiomas"),
			(boolean)row.get("accesoupdatedispo"),
			(boolean)row.get("accesoetiqueta"),
			(boolean)row.get("accesoimpuesto"),
			(boolean)row.get("accesovisa"),
			(boolean)row.get("accesopaypal"),
			(boolean)row.get("accesomasterdcard"),
			(boolean)row.get("accesowesternunion"),
			(boolean)row.get("accesoregusuarios"),
			(boolean)row.get("accesocrearnuevouser"),
			(boolean)row.get("accesopaquetes"),
			(boolean)row.get("accesoservicios"),
			(boolean)row.get("accesosubservicios"),
			(boolean)row.get("accesoactividades"),
			(boolean)row.get("accesohoteles"),
			(boolean)row.get("accesodestinos"),
			(boolean)row.get("accesoreporreservas"),
			(boolean)row.get("accesoreporpagos"),
			(boolean)row.get("accesoestadpagos"),
			(boolean)row.get("accesoestadpaqumasvendidos"));
	}
	public boolean isOperationCorrect(List lista)
	{
		Map row=(Map)lista.get(0);
		boolean correcto=row.get("resultado").toString().equals("correcto");
		if(correcto)return true;
		else return false;
	}
}
