package com.pricing.viewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.Clients;

import com.pricing.dao.CReservaDAO;
import com.pricing.model.CReserva;

public class ReservasVM {
	//====atributos=====
	private ArrayList<CReserva> listaReservas;
	private CReservaDAO reservaDao;
	private boolean estadoPagoPendiente;
	private boolean estadoPagoParcial;
	private boolean estadoPagoTotal;
	private String FechaInicio;
	private String FechaFinal;
	
	//=====getter and setterr=====

	public ArrayList<CReserva> getListaReservas() {
		return listaReservas;
	}

	public void setListaReservas(ArrayList<CReserva> listaReservas) {
		this.listaReservas = listaReservas;
	}
	
	public CReservaDAO getReservaDao() {
		return reservaDao;
	}

	public void setReservaDao(CReservaDAO reservaDao) {
		this.reservaDao = reservaDao;
	}

	public boolean isEstadoPagoPendiente() {
		return estadoPagoPendiente;
	}

	public void setEstadoPagoPendiente(boolean estadoPagoPendiente) {
		this.estadoPagoPendiente = estadoPagoPendiente;
	}

	public boolean isEstadoPagoParcial() {
		return estadoPagoParcial;
	}

	public void setEstadoPagoParcial(boolean estadoPagoParcial) {
		this.estadoPagoParcial = estadoPagoParcial;
	}

	public boolean isEstadoPagoTotal() {
		return estadoPagoTotal;
	}

	public void setEstadoPagoTotal(boolean estadoPagoTotal) {
		this.estadoPagoTotal = estadoPagoTotal;
	}
	
	public String getFechaInicio() {
		return FechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		FechaInicio = fechaInicio;
	}

	public String getFechaFinal() {
		return FechaFinal;
	}

	public void setFechaFinal(String fechaFinal) {
		FechaFinal = fechaFinal;
	}

	//=====metodos=========
	
}
