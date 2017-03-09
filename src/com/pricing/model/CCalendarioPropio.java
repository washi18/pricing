package com.pricing.model;

import java.util.ArrayList;

import com.pricing.dao.CCalendarioPropioDAO;

public class CCalendarioPropio 
{
	private int nCalendarioCod;// int,
	private String cPaqueteCod;// varchar(10),
	private int nAnio;// int,
	private ArrayList<CDiaPropio> listaDiasAnioCalendario;
	private CCalendarioPropioDAO calendarioDao;
	/***********************/
	public int getnCalendarioCod() {
		return nCalendarioCod;
	}
	public void setnCalendarioCod(int nCalendarioCod) {
		this.nCalendarioCod = nCalendarioCod;
	}
	public String getcPaqueteCod() {
		return cPaqueteCod;
	}
	public void setcPaqueteCod(String cPaqueteCod) {
		this.cPaqueteCod = cPaqueteCod;
	}
	public int getnAnio() {
		return nAnio;
	}
	public void setnAnio(int nAnio) {
		this.nAnio = nAnio;
	}
	public ArrayList<CDiaPropio> getListaDiasAnioCalendario() {
		return listaDiasAnioCalendario;
	}
	public void setListaDiasAnioCalendario(
			ArrayList<CDiaPropio> listaDiasAnioCalendario) {
		this.listaDiasAnioCalendario = listaDiasAnioCalendario;
	}
	/***************************/
	public CCalendarioPropio() {
		// TODO Auto-generated constructor stub
	}
	public CCalendarioPropio(int nCalendarioCod, String cPaqueteCod, int nAnio) {
		this.nCalendarioCod = nCalendarioCod;
		this.cPaqueteCod = cPaqueteCod;
		this.nAnio = nAnio;
		/*****************/
		calendarioDao=new CCalendarioPropioDAO();
		calendarioDao.asignarListaDiasAnioCalendario(calendarioDao.recuperarDiasAnioCalendarioBD(nCalendarioCod));
		setListaDiasAnioCalendario(calendarioDao.getListaDiasAnioCalendario());
	}
}
