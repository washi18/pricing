package com.pricing.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.pricing.model.CReserva;
import com.pricing.model.CServicio;

public class CReservaDAO extends CConexion 
{
	private CReserva oReserva;
	private ArrayList<CReserva> listaReservas;
	//=======================

	public CReserva getoReserva() {
		return oReserva;
	}

	public void setoReserva(CReserva oReserva) {
		this.oReserva = oReserva;
	}
	//==================
	public CReservaDAO() {
		// TODO Auto-generated constructor stub
		super();
		this.oReserva=new CReserva();
	}
	public CReservaDAO(CReserva reserva)
	{
		super();
		this.oReserva=reserva;
	}
	
	public ArrayList<CReserva> getListaReservas() {
		return listaReservas;
	}

	public void setListaReservas(ArrayList<CReserva> listaReservas) {
		this.listaReservas = listaReservas;
	}

	//====OTROS METODOS====
	public List registrarReserva(CReserva reserva)
	{
		Object[] values={(Date)reserva.getdFechaInicio(),(Date)reserva.getdFechaFin(),reserva.getcContacto(),
				reserva.getcEmail(),reserva.getcTelefono(),reserva.getnPrecioPaquetePersona(),
				reserva.getnNroPersonas(),reserva.getcInformacionAdicional(),
				(Date)reserva.getdFechaArribo()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_RegistroReserva", values);
	}
	public String[] recuperarResultados(List lista)
	{
		Map row=(Map)lista.get(0);
		String[] r={row.get("resultado").toString(),row.get("creservacod").toString()};
		return r;
	}
	public List buscarReservasEntreFechasBD( String FechaInicio,String FechaFin,String EstadoPago)
	{
		Object[] values={FechaInicio,FechaFin,EstadoPago};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_BuscarReservas",values);
	}
	public void asignarListaReservas(List lista)
	{
		listaReservas=new ArrayList<CReserva>();
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaReservas.add(new CReserva((String)row.get("creservacod"),(Date)row.get("dfechainicio"),
					(Date)row.get("dfechafin"), (Date)row.get("dfecha"),
					(String)row.get("ccontacto"), (String)row.get("cemail"),
					(String)row.get("ctelefono"), (Number)row.get("npreciopaquetepersona"),
					(int)row.get("nnropersonas"),(String)row.get("cinformacionadicional"),
					(String)row.get("cestado"),
					(String)row.get("cmetodopago"),
					(String)row.get("ccodtransaccion"),
					(Date)row.get("dfechaarribo")));
		}
	}
	public List modificarMetodoPago(String codReserva,String estado,String metodoPago,String codTransac)
	{
		Object[] values={codReserva,estado,metodoPago,codTransac};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_ModificarEstadoPago", values);
	}
	public List modificarEstadoDePago(String codReserva,String estado)
	{
		Object[] values={codReserva,estado};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_ModificarEstadoPagoReserva", values);
	}
	public List insertarCupon(String codReserva,int codCupon){
		Object[] values={codReserva,codCupon};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_registrarcupon",values);
	}
	public boolean isCorrectOperation(List lista)
	{
		Map row=(Map)lista.get(0);
		boolean correcto=row.get("resultado").toString().equals("correcto");
		if(correcto)return true;
		else return false;
	}
}
