package com.pricing.viewModel;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

public class itinerarioPaqueteVM {
	private String itinerario;
	//=========================
	public String getItinerario() {
		return itinerario;
	}
	public void setItinerario(String itinerario) {
		this.itinerario = itinerario;
	}
	//==========================
	@Init
	public void initVM()
	{
		itinerario="";
	}
	@GlobalCommand
	@NotifyChange({"itinerario"})
	public void mostrarItinerarioPaquete(@BindingParam("itinerario")String itinerario)
	{
		this.itinerario=itinerario;
	}
}
