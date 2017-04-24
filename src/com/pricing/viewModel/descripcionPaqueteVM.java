package com.pricing.viewModel;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

public class descripcionPaqueteVM {
	private String descripcion;
	//=========================
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	//==========================
	@Init
	public void initVM()
	{
		descripcion="";
	}
	@GlobalCommand
	@NotifyChange({"descripcion"})
	public void mostrarDescripcionPaquete(@BindingParam("descripcion")String descripcion)
	{
		this.descripcion=descripcion;
	}
}
