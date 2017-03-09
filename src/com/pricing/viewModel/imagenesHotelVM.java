package com.pricing.viewModel;

import java.util.ArrayList;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

import com.pricing.dao.CGaleriaHotelDAO;
import com.pricing.dao.CHotelDAO;
import com.pricing.model.CGaleriaHotel;
import com.pricing.model.CGaleriasHotel4;
import com.pricing.model.CHotel;

public class imagenesHotelVM {
	
	private ArrayList<CGaleriasHotel4> listaImagenesHotel;
	private ArrayList<CGaleriasHotel4> listaUbicacionHotel;
	private CHotel oHotel;
	private boolean update;
	/******************/
	public ArrayList<CGaleriasHotel4> getListaImagenesHotel() {
		return listaImagenesHotel;
	}
	public void setListaImagenesHotel(ArrayList<CGaleriasHotel4> listaImagenesHotel) {
		this.listaImagenesHotel = listaImagenesHotel;
	}
	public ArrayList<CGaleriasHotel4> getListaUbicacionHotel() {
		return listaUbicacionHotel;
	}
	public void setListaUbicacionHotel(ArrayList<CGaleriasHotel4> listaUbicacionHotel) {
		this.listaUbicacionHotel = listaUbicacionHotel;
	}
	public CHotel getoHotel() {
		return oHotel;
	}
	public void setoHotel(CHotel oHotel) {
		this.oHotel = oHotel;
	}
	public boolean isUpdate() {
		return update;
	}
	public void setUpdate(boolean update) {
		this.update = update;
	}
	/***********************/
	@Init
	public void initVM()
	{
		listaImagenesHotel=new ArrayList<CGaleriasHotel4>();
		listaUbicacionHotel=new ArrayList<CGaleriasHotel4>();
		oHotel=new CHotel();
		update=false;
	}
	@GlobalCommand
	public void muestraImagenesSubidas(@BindingParam("cHotel")CHotel hotel)
	{
		ArrayList<CGaleriaHotel> listaImagenes=new ArrayList<CGaleriaHotel>();
		ArrayList<CGaleriaHotel> listaUbicacion=new ArrayList<CGaleriaHotel>();
		setoHotel(hotel);
		for(CGaleriaHotel galeria:hotel.getListaImagenes())
		{	
			if(galeria.getnTipoImagen()==1)
			{
				
				listaImagenes.add(galeria);
			}
			else if(galeria.getnTipoImagen()==2)
			{
				listaUbicacion.add(galeria);
			}
		}
		mostrarImagenes(listaImagenes,listaUbicacion);
	}
	public void mostrarImagenes(ArrayList<CGaleriaHotel> listaImagenes,
			ArrayList<CGaleriaHotel> listaUbicacion)
	{
		for(int i=0;i<listaImagenes.size();i+=4)
		{
			CGaleriasHotel4 imagenes=new CGaleriasHotel4();
			imagenes.setGaleria1(listaImagenes.get(i));
			if((i+1)<listaImagenes.size())
				imagenes.setGaleria2(listaImagenes.get(i+1));
			if((i+2)<listaImagenes.size())
				imagenes.setGaleria3(listaImagenes.get(i+2));
			if((i+3)<listaImagenes.size())
				imagenes.setGaleria4(listaImagenes.get(i+3));
			listaImagenesHotel.add(imagenes);
		}
		for(int i=0;i<listaUbicacion.size();i+=4)
		{
			CGaleriasHotel4 ubicaciones=new CGaleriasHotel4();
			ubicaciones.setGaleria1(listaUbicacion.get(i));
			if((i+1)<listaUbicacion.size())
				ubicaciones.setGaleria2(listaUbicacion.get(i+1));
			if((i+2)<listaUbicacion.size())
				ubicaciones.setGaleria3(listaUbicacion.get(i+2));
			if((i+3)<listaUbicacion.size())
				ubicaciones.setGaleria4(listaUbicacion.get(i+3));
			listaUbicacionHotel.add(ubicaciones);
		}
		BindUtils.postNotifyChange(null, null, this,"listaImagenesHotel");
		BindUtils.postNotifyChange(null, null, this,"listaUbicacionHotel");
	}
	@Command
	@NotifyChange({"update"})
	public void cambiosEnImagenHotel(@BindingParam("galeria4")CGaleriasHotel4 galeria4,@BindingParam("galeria")CGaleriaHotel galeria)
	{
		if(!validoParaCambiarImagen(galeria.isbEstado(),galeria.getnTipoImagen()))
			return;
		update=true;
		if(galeria4.getGaleria1().equals(galeria))
		{
			if(galeria4.getGaleria1().isbEstado())
			{
				galeria4.getGaleria1().setbEstado(false);
				galeria4.getGaleria1().setStyle_Select("div_content_imageHotel");
				quitarImagen(galeria4.getGaleria1().getcRutaImagen(),galeria.getnTipoImagen());
			}else{
				galeria4.getGaleria1().setbEstado(true);
				galeria4.getGaleria1().setStyle_Select("div_content_imageHotel_selected");
				agregarImagen(galeria4.getGaleria1().getcRutaImagen(),galeria.getnTipoImagen());
			}
		}else if(galeria4.getGaleria2().equals(galeria))
		{
			if(galeria4.getGaleria2().isbEstado())
			{
				galeria4.getGaleria2().setbEstado(false);
				galeria4.getGaleria2().setStyle_Select("div_content_imageHotel");
				quitarImagen(galeria4.getGaleria2().getcRutaImagen(),galeria.getnTipoImagen());
			}else{
				galeria4.getGaleria2().setbEstado(true);
				galeria4.getGaleria2().setStyle_Select("div_content_imageHotel_selected");
				agregarImagen(galeria4.getGaleria2().getcRutaImagen(),galeria.getnTipoImagen());
			}
		}else if(galeria4.getGaleria3().equals(galeria))
		{
			if(galeria4.getGaleria3().isbEstado())
			{
				galeria4.getGaleria3().setbEstado(false);
				galeria4.getGaleria3().setStyle_Select("div_content_imageHotel");
				quitarImagen(galeria4.getGaleria3().getcRutaImagen(),galeria.getnTipoImagen());
			}else{
				galeria4.getGaleria3().setbEstado(true);
				galeria4.getGaleria3().setStyle_Select("div_content_imageHotel_selected");
				agregarImagen(galeria4.getGaleria3().getcRutaImagen(),galeria.getnTipoImagen());
			}
		}else if(galeria4.getGaleria4().equals(galeria))
		{
			if(galeria4.getGaleria4().isbEstado())
			{
				galeria4.getGaleria4().setbEstado(false);
				galeria4.getGaleria4().setStyle_Select("div_content_imageHotel");
				quitarImagen(galeria4.getGaleria4().getcRutaImagen(),galeria.getnTipoImagen());
			}else{
				galeria4.getGaleria4().setbEstado(true);
				galeria4.getGaleria4().setStyle_Select("div_content_imageHotel_selected");
				agregarImagen(galeria4.getGaleria4().getcRutaImagen(),galeria.getnTipoImagen());
			}
		}
		refrescarCambios(galeria4);
	}
	public boolean validoParaCambiarImagen(boolean Marcado,int tipoImagen)
	{
		boolean valido=true;
		for(CGaleriaHotel galeria:oHotel.getListaImagenes())
		{
			if(galeria.getnHotelCod()==0)
			{
				valido=false;
				break;
			}
		}
		if(valido && !Marcado)
		{
			if(tipoImagen==1)
			{
				if(!oHotel.getcFoto1().equals("img/hoteles/hotelxdefecto.jpg") && 
						!oHotel.getcFoto2().equals("img/hoteles/hotelxdefecto.jpg")&&
						!oHotel.getcFoto3().equals("img/hoteles/hotelxdefecto.jpg")&&
						!oHotel.getcFoto4().equals("img/hoteles/hotelxdefecto.jpg")&&
						!oHotel.getcFoto5().equals("img/hoteles/hotelxdefecto.jpg")){
					valido=false;
				}
			}else{
				if(!oHotel.getcImagenUbicacion().equals(""))
					valido=false;
			}
		}
		return valido;
	}
	public void quitarImagen(String rutaImagen,int tipoImagen)
	{
		if(tipoImagen==1)
		{
			if(oHotel.getcFoto1().equals(rutaImagen))
			{
				oHotel.setcFoto1("img/hoteles/hotelxdefecto.jpg");
			}else if(oHotel.getcFoto2().equals(rutaImagen))
			{
				oHotel.setcFoto2("img/hoteles/hotelxdefecto.jpg");
			}else if(oHotel.getcFoto3().equals(rutaImagen))
			{
				oHotel.setcFoto3("img/hoteles/hotelxdefecto.jpg");
			}else if(oHotel.getcFoto4().equals(rutaImagen))
			{
				oHotel.setcFoto4("img/hoteles/hotelxdefecto.jpg");
			}else if(oHotel.getcFoto5().equals(rutaImagen))
			{
				oHotel.setcFoto5("img/hoteles/hotelxdefecto.jpg");
			}
		}else
			if(oHotel.getcImagenUbicacion().equals(rutaImagen))
				oHotel.setcImagenUbicacion("");
	}
	public void agregarImagen(String rutaImagen,int tipoImagen)
	{
		if(tipoImagen==1)
		{
			if(oHotel.getcFoto1().equals("img/hoteles/hotelxdefecto.jpg"))
			{
				oHotel.setcFoto1(rutaImagen);
			}else if(oHotel.getcFoto2().equals("img/hoteles/hotelxdefecto.jpg"))
			{
				oHotel.setcFoto2(rutaImagen);
			}else if(oHotel.getcFoto3().equals("img/hoteles/hotelxdefecto.jpg"))
			{
				oHotel.setcFoto3(rutaImagen);
			}else if(oHotel.getcFoto4().equals("img/hoteles/hotelxdefecto.jpg"))
			{
				oHotel.setcFoto4(rutaImagen);
			}else if(oHotel.getcFoto5().equals("img/hoteles/hotelxdefecto.jpg"))
			{
				oHotel.setcFoto5(rutaImagen);
			}
		}else
			if(oHotel.getcImagenUbicacion().equals(""))
				oHotel.setcImagenUbicacion(rutaImagen);
	}
	@Command
	@NotifyChange({"update"})
	public void guardarCambios(@BindingParam("componente")Component comp)
	{
		CHotelDAO hotelDao=new CHotelDAO();
		boolean correcto=hotelDao.isOperationCorrect(hotelDao.modificarImagenesHotel(oHotel));
		if(correcto)
		{
			update=false;
			for(CGaleriaHotel galeria:oHotel.getListaImagenes())
			{
				CGaleriaHotelDAO galeriaHotelDao=new CGaleriaHotelDAO();
				correcto=galeriaHotelDao.isOperationCorrect(galeriaHotelDao.modificarImagenHotel(galeria));
			}
			Clients.showNotification("Los cambios efectuados se guardaron correctamente",Clients.NOTIFICATION_TYPE_INFO,comp,"before_start",300);
		}
		else
			Clients.showNotification("No se pudieron guardar los cambios efectuados",Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",300);
	}
	public void refrescarCambios(CGaleriasHotel4 galeria4)
	{
		BindUtils.postNotifyChange(null, null, galeria4, "galeria1");
		BindUtils.postNotifyChange(null, null, galeria4, "galeria2");
		BindUtils.postNotifyChange(null, null, galeria4, "galeria3");
		BindUtils.postNotifyChange(null, null, galeria4, "galeria4");
	}
}
