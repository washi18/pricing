package com.pricing.viewModel;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Sessions;

import com.pricing.model.CHotel;

public class descripcionHotelVM 
{
	private String descripcion;
	private String language;
	/****************/
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/***************/
	@Init
	public void initVM()
	{
		descripcion="";
	}
	@GlobalCommand
	@NotifyChange({"descripcion"})
	public void descripcionHotel(@BindingParam("cHotel")CHotel hotel)
	{
		language=(String)Sessions.getCurrent().getAttribute("language");
		System.out.println("soy el lenguaje-> "+language);
		if(language.equals("es-ES"))
		{
			descripcion=hotel.getcDescripcionIdioma1();
		}
		else if(language.equals("pt-BR") || language.equals("pt-PT"))
		{
			descripcion=hotel.getcDescripcionIdioma3();
		}
		else
		{
			descripcion=hotel.getcDescripcionIdioma2();
		}
	}
}
