package com.pricing.viewModel;

import java.util.ArrayList;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Popup;

import com.pricing.model.CDestinoConHoteles;
import com.pricing.model.CHotel;

public class hotelesPorCategoriaVM 
{
	ArrayList<CDestinoConHoteles> listaCategoriaDestinoHoteles;
	private String language;
	/*****************/
	public ArrayList<CDestinoConHoteles> getListaCategoriaDestinoHoteles() {
		return listaCategoriaDestinoHoteles;
	}
	public void setListaCategoriaDestinoHoteles(ArrayList<CDestinoConHoteles> listaCategoriaDestinoHoteles) {
		this.listaCategoriaDestinoHoteles = listaCategoriaDestinoHoteles;
	}
	/******************/
	@Init
	public void initVM(){
		listaCategoriaDestinoHoteles=new ArrayList<CDestinoConHoteles>();
	}
	@GlobalCommand
	@NotifyChange({"listaCategoriaDestinoHoteles"})
	public void mostrarHotelesPorCategoria(@BindingParam("listaDestinoHoteles")ArrayList<CDestinoConHoteles> listaDestinoHoteles)
	{
		listaCategoriaDestinoHoteles.clear();
		language=(String)Sessions.getCurrent().getAttribute("language");
		for(CDestinoConHoteles Dhotel:listaDestinoHoteles)
		{
			for(CHotel hotel:Dhotel.getListaDestinosHoteles())
			{
				hotel.setDescripcion(hotel.getcDescripcionIdioma1());
				if(language.equals("es-ES"))
				{
					hotel.setDescripcion(hotel.getcDescripcionIdioma1());
				}
				else if(language.equals("pt-BR") || language.equals("pt-PT"))
				{
					hotel.setDescripcion(hotel.getcDescripcionIdioma3());
				}
				else
				{
					hotel.setDescripcion(hotel.getcDescripcionIdioma2());
				}
			}
		}
		setListaCategoriaDestinoHoteles(listaDestinoHoteles);
	}
	@Command
	public void mostrarDescripcionHotel(@BindingParam("componente")Component comp)
	{
		Popup pop = (Popup)Executions.createComponents(
                "/descripcionHotel.zul", null, null);
        pop.open(comp,"after_start");
	}
	@Command
	public void imagePrev(@BindingParam("chotel")CHotel hotel)
	{
		if(hotel.getImagenHotel().equals(hotel.getcFoto1()))
			hotel.setImagenHotel(hotel.getcFoto5());
		else if(hotel.getImagenHotel().equals(hotel.getcFoto2()))
			hotel.setImagenHotel(hotel.getcFoto1());
		else if(hotel.getImagenHotel().equals(hotel.getcFoto3()))
			hotel.setImagenHotel(hotel.getcFoto2());
		else if(hotel.getImagenHotel().equals(hotel.getcFoto4()))
			hotel.setImagenHotel(hotel.getcFoto3());
		else if(hotel.getImagenHotel().equals(hotel.getcFoto5()))
			hotel.setImagenHotel(hotel.getcFoto4());
		BindUtils.postNotifyChange(null, null, hotel, "imagenHotel");
	}
	@Command
	public void imageNext(@BindingParam("chotel")CHotel hotel)
	{
		if(hotel.getImagenHotel().equals(hotel.getcFoto1()))
			hotel.setImagenHotel(hotel.getcFoto2());
		else if(hotel.getImagenHotel().equals(hotel.getcFoto2()))
			hotel.setImagenHotel(hotel.getcFoto3());
		else if(hotel.getImagenHotel().equals(hotel.getcFoto3()))
			hotel.setImagenHotel(hotel.getcFoto4());
		else if(hotel.getImagenHotel().equals(hotel.getcFoto4()))
			hotel.setImagenHotel(hotel.getcFoto5());
		else if(hotel.getImagenHotel().equals(hotel.getcFoto5()))
			hotel.setImagenHotel(hotel.getcFoto1());
		BindUtils.postNotifyChange(null, null, hotel, "imagenHotel");
	}
}
