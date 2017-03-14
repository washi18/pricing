package com.pricing.model;

public class CGaleriaPaquete4 {
	private CGaleriaPaquete galeria1;
	private CGaleriaPaquete galeria2;
	private CGaleriaPaquete galeria3;
	private CGaleriaPaquete galeria4;
	private CGaleriaPaquete galeria5;
	//===========getter and setter==========
	public CGaleriaPaquete getGaleria1() {
		return galeria1;
	}
	public void setGaleria1(CGaleriaPaquete galeria1) {
		this.galeria1 = galeria1;
	}
	public CGaleriaPaquete getGaleria2() {
		return galeria2;
	}
	public void setGaleria2(CGaleriaPaquete galeria2) {
		this.galeria2 = galeria2;
	}
	public CGaleriaPaquete getGaleria3() {
		return galeria3;
	}
	public void setGaleria3(CGaleriaPaquete galeria3) {
		this.galeria3 = galeria3;
	}
	public CGaleriaPaquete getGaleria4() {
		return galeria4;
	}
	public void setGaleria4(CGaleriaPaquete galeria4) {
		this.galeria4 = galeria4;
	}
	public CGaleriaPaquete getGaleria5() {
		return galeria5;
	}
	public void setGaleria5(CGaleriaPaquete galeria5) {
		this.galeria5 = galeria5;
	}
	//==========constructores====
	public CGaleriaPaquete4(){
		this.galeria1=new CGaleriaPaquete();
		this.galeria2=new CGaleriaPaquete();
		this.galeria3=new CGaleriaPaquete();
		this.galeria4=new CGaleriaPaquete();
		this.galeria5=new CGaleriaPaquete();
	}
}
