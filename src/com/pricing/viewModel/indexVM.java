package com.pricing.viewModel;

import org.zkoss.bind.annotation.Init;

public class indexVM 
{
	private boolean mostrarAdmin;
	private boolean mostrarPricing;
	//==========
	public boolean isMostrarAdmin() {
		return mostrarAdmin;
	}
	public void setMostrarAdmin(boolean mostrarAdmin) {
		this.mostrarAdmin = mostrarAdmin;
	}
	public boolean isMostrarPricing() {
		return mostrarPricing;
	}
	public void setMostrarPricing(boolean mostrarPricing) {
		this.mostrarPricing = mostrarPricing;
	}
	//=======================
	@Init
	public void inicializarIndex()
	{
		this.mostrarAdmin=false;
		this.mostrarPricing=false;
//		String perfil="ADMINISTRADOR";
		String perfil="";
		if(perfil.equals("ADMINISTRADOR"))
			this.mostrarAdmin=true;
		else
			this.mostrarPricing=true;
	}
}
