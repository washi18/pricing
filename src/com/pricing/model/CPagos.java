package com.pricing.model;

public class CPagos {
	private boolean pagoParcialPaypal;
	private boolean pagoTotalPaypal;
	private boolean pagoParcialVisa;
	private boolean pagoTotalVisa;
	private boolean pagoParcialMasterCard;
	private boolean pagoTotalMasterCard;
	private boolean pagoParcialDinersClub;
	private boolean pagoTotalDinersClub;
	private boolean selectPaypal;
	private boolean selectVisa;
	private boolean selectMasterCard;
	private boolean selectDinersClub;
	private String taxPaypal;
	private String taxMC;
	private String taxDiners;
	private String totalConImpuestoMC;
	private String totalConImpuestoPaypal;
	private String totalConImpuestoDiners;
	private String urlPaypal;
	private String urlMasterCard;
	private String urlDiners;
	/**************/
	public boolean isPagoParcialPaypal() {
		return pagoParcialPaypal;
	}
	public void setPagoParcialPaypal(boolean pagoParcialPaypal) {
		this.pagoParcialPaypal = pagoParcialPaypal;
	}
	public boolean isPagoTotalPaypal() {
		return pagoTotalPaypal;
	}
	public void setPagoTotalPaypal(boolean pagoTotalPaypal) {
		this.pagoTotalPaypal = pagoTotalPaypal;
	}
	public boolean isPagoParcialVisa() {
		return pagoParcialVisa;
	}
	public void setPagoParcialVisa(boolean pagoParcialVisa) {
		this.pagoParcialVisa = pagoParcialVisa;
	}
	public boolean isPagoTotalVisa() {
		return pagoTotalVisa;
	}
	public void setPagoTotalVisa(boolean pagoTotalVisa) {
		this.pagoTotalVisa = pagoTotalVisa;
	}
	public boolean isPagoParcialMasterCard() {
		return pagoParcialMasterCard;
	}
	public void setPagoParcialMasterCard(boolean pagoParcialMasterCard) {
		this.pagoParcialMasterCard = pagoParcialMasterCard;
	}
	public boolean isPagoTotalMasterCard() {
		return pagoTotalMasterCard;
	}
	public void setPagoTotalMasterCard(boolean pagoTotalMasterCard) {
		this.pagoTotalMasterCard = pagoTotalMasterCard;
	}
	public boolean isPagoParcialDinersClub() {
		return pagoParcialDinersClub;
	}
	public void setPagoParcialDinersClub(boolean pagoParcialDinersClub) {
		this.pagoParcialDinersClub = pagoParcialDinersClub;
	}
	public boolean isPagoTotalDinersClub() {
		return pagoTotalDinersClub;
	}
	public void setPagoTotalDinersClub(boolean pagoTotalDinersClub) {
		this.pagoTotalDinersClub = pagoTotalDinersClub;
	}
	public boolean isSelectPaypal() {
		return selectPaypal;
	}
	public void setSelectPaypal(boolean selectPaypal) {
		this.selectPaypal = selectPaypal;
	}
	public boolean isSelectVisa() {
		return selectVisa;
	}
	public void setSelectVisa(boolean selectVisa) {
		this.selectVisa = selectVisa;
	}
	public boolean isSelectMasterCard() {
		return selectMasterCard;
	}
	public void setSelectMasterCard(boolean selectMasterCard) {
		this.selectMasterCard = selectMasterCard;
	}
	public boolean isSelectDinersClub() {
		return selectDinersClub;
	}
	public void setSelectDinersClub(boolean selectDinersClub) {
		this.selectDinersClub = selectDinersClub;
	}
	public String getTaxPaypal() {
		return taxPaypal;
	}
	public void setTaxPaypal(String taxPaypal) {
		this.taxPaypal = taxPaypal;
	}
	public String getTaxMC() {
		return taxMC;
	}
	public void setTaxMC(String taxMC) {
		this.taxMC = taxMC;
	}
	public String getTaxDiners() {
		return taxDiners;
	}
	public void setTaxDiners(String taxDiners) {
		this.taxDiners = taxDiners;
	}
	public String getTotalConImpuestoMC() {
		return totalConImpuestoMC;
	}
	public void setTotalConImpuestoMC(String totalConImpuestoMC) {
		this.totalConImpuestoMC = totalConImpuestoMC;
	}
	public String getTotalConImpuestoPaypal() {
		return totalConImpuestoPaypal;
	}
	public void setTotalConImpuestoPaypal(String totalConImpuestoPaypal) {
		this.totalConImpuestoPaypal = totalConImpuestoPaypal;
	}
	public String getTotalConImpuestoDiners() {
		return totalConImpuestoDiners;
	}
	public void setTotalConImpuestoDiners(String totalConImpuestoDiners) {
		this.totalConImpuestoDiners = totalConImpuestoDiners;
	}
	public String getUrlPaypal() {
		return urlPaypal;
	}
	public void setUrlPaypal(String urlPaypal) {
		this.urlPaypal = urlPaypal;
	}
	public String getUrlMasterCard() {
		return urlMasterCard;
	}
	public void setUrlMasterCard(String urlMasterCard) {
		this.urlMasterCard = urlMasterCard;
	}
	public String getUrlDiners() {
		return urlDiners;
	}
	public void setUrlDiners(String urlDiners) {
		this.urlDiners = urlDiners;
	}
	/*****************/
	public CPagos() {
		// TODO Auto-generated constructor stub
		pagoParcialPaypal=false;
		pagoTotalPaypal=false;
		pagoParcialVisa=false;
		pagoTotalVisa=false;
		pagoParcialMasterCard=false;
		pagoTotalMasterCard=false;
		pagoParcialDinersClub=false;
		pagoTotalDinersClub=false;
		selectPaypal=false;
		selectVisa=false;
		selectMasterCard=false;
		selectDinersClub=false;
	}
	public void selectPaypal()
	{
		selectPaypal=true;
		selectVisa=false;
		selectMasterCard=false;
		selectDinersClub=false;
	}
	public void selectVisa()
	{
		selectPaypal=false;
		selectVisa=true;
		selectMasterCard=false;
		selectDinersClub=false;
	}
	public void selectMasterCard()
	{
		selectPaypal=false;
		selectVisa=false;
		selectMasterCard=true;
		selectDinersClub=false;
	}
	public void selectDinersClub()
	{
		selectPaypal=false;
		selectVisa=false;
		selectMasterCard=false;
		selectDinersClub=true;
	}
}
