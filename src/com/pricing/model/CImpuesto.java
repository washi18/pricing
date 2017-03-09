package com.pricing.model;

public class CImpuesto 
{
	  private int codImpuesto;// int,
	  private String impuestoPaypal;// varchar(5),
	  private String impuestoVisa;// varchar(5),
	  private String impuestoMasterCard;// varchar(5),
	  private String impuestoDinnersClub;// varchar(5),
	  private String porcentajeCobro;// varchar(5),
	  private String pagoMinimo;// varchar(5),
	  private boolean modoPorcentual;// boolean,
	  private boolean modoMinimo;
	  private boolean editable;
	  /********************/
	public int getCodImpuesto() {
		return codImpuesto;
	}
	public void setCodImpuesto(int codImpuesto) {
		this.codImpuesto = codImpuesto;
	}
	public String getImpuestoPaypal() {
		return impuestoPaypal;
	}
	public void setImpuestoPaypal(String impuestoPaypal) {
		this.impuestoPaypal = impuestoPaypal;
	}
	public String getImpuestoVisa() {
		return impuestoVisa;
	}
	public void setImpuestoVisa(String impuestoVisa) {
		this.impuestoVisa = impuestoVisa;
	}
	public String getImpuestoMasterCard() {
		return impuestoMasterCard;
	}
	public void setImpuestoMasterCard(String impuestoMasterCard) {
		this.impuestoMasterCard = impuestoMasterCard;
	}
	public String getImpuestoDinnersClub() {
		return impuestoDinnersClub;
	}
	public void setImpuestoDinnersClub(String impuestoDinnersClub) {
		this.impuestoDinnersClub = impuestoDinnersClub;
	}
	public String getPorcentajeCobro() {
		return porcentajeCobro;
	}
	public void setPorcentajeCobro(String porcentajeCobro) {
		this.porcentajeCobro = porcentajeCobro;
	}
	public String getPagoMinimo() {
		return pagoMinimo;
	}
	public void setPagoMinimo(String pagoMinimo) {
		this.pagoMinimo = pagoMinimo;
	}
	public boolean isModoPorcentual() {
		return modoPorcentual;
	}
	public void setModoPorcentual(boolean modoPorcentual) {
		this.modoPorcentual = modoPorcentual;
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	public boolean isModoMinimo() {
		return modoMinimo;
	}
	public void setModoMinimo(boolean modoMinimo) {
		this.modoMinimo = modoMinimo;
	}
	/*****************************/
	public CImpuesto() {
		// TODO Auto-generated constructor stub
		this.impuestoPaypal ="0";
		this.impuestoVisa ="0";
		this.impuestoMasterCard ="0";
		this.impuestoDinnersClub ="0";
		this.porcentajeCobro = "0";
		this.pagoMinimo = "0";
		this.modoPorcentual =false;
		this.modoMinimo=false;
		this.editable=false;
	}
	public CImpuesto(int codImpuesto, String impuestoPaypal,
			String impuestoVisa, String impuestoMasterCard,
			String impuestoDinnersClub, String porcentajeCobro,
			String pagoMinimo, boolean modoPorcentual) {
		this.codImpuesto = codImpuesto;
		this.impuestoPaypal = impuestoPaypal;
		this.impuestoVisa = impuestoVisa;
		this.impuestoMasterCard = impuestoMasterCard;
		this.impuestoDinnersClub = impuestoDinnersClub;
		this.porcentajeCobro = porcentajeCobro;
		this.pagoMinimo = pagoMinimo;
		this.modoPorcentual = modoPorcentual;
		/*************/
		if(modoPorcentual)
			modoMinimo=false;
		else
			modoMinimo=true;
		this.editable=false;
	}
}
