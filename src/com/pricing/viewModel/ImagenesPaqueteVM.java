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

import com.pricing.dao.CGaleriaHotelDAO;
import com.pricing.dao.CGaleriaPaqueteDAO;
import com.pricing.dao.CHotelDAO;
import com.pricing.dao.CPaqueteDAO;
import com.pricing.model.CGaleriaHotel;
import com.pricing.model.CGaleriaPaquete;
import com.pricing.model.CGaleriaPaquete4;
import com.pricing.model.CGaleriasHotel4;
import com.pricing.model.CHotel;
import com.pricing.model.CPaquete;

public class ImagenesPaqueteVM {
	private ArrayList<CGaleriaPaquete4> listaImagenesPaquetes;
	private CPaquete oPaquete;
	private boolean update;
	//===========getter and setter====
	
	public CPaquete getoPaquete() {
		return oPaquete;
	}
	public ArrayList<CGaleriaPaquete4> getListaImagenesPaquetes() {
		return listaImagenesPaquetes;
	}
	public void setListaImagenesPaquetes(ArrayList<CGaleriaPaquete4> listaImagenesPaquetes) {
		this.listaImagenesPaquetes = listaImagenesPaquetes;
	}
	public void setoPaquete(CPaquete oPaquete) {
		this.oPaquete = oPaquete;
	}
	public boolean isUpdate() {
		return update;
	}
	public void setUpdate(boolean update) {
		this.update = update;
	}
	//===============contructores====
	@Init
	public void initVM()
	{
		listaImagenesPaquetes=new ArrayList<CGaleriaPaquete4>();
		oPaquete=new CPaquete();
		update=false;
	}
	@GlobalCommand
	public void muestraImagenesSubidasPaquete(@BindingParam("cPaquete")CPaquete paquete)
	{
		ArrayList<CGaleriaPaquete> listaImagenes=new ArrayList<CGaleriaPaquete>();
		setoPaquete(paquete);
		System.out.println("tamanio de lista->"+paquete.getListaImagenes().size()+"paquete"+this.oPaquete.getcPaqueteCod());
		for(CGaleriaPaquete galeria:paquete.getListaImagenes())
		{	
				listaImagenes.add(galeria);
		}
		mostrarImagenes(listaImagenes);
	}
	public void mostrarImagenes(ArrayList<CGaleriaPaquete> listaImagenes)
	{
		System.out.println("Entra a mostrar imagenes");
		System.out.println("RUTA DE LISTA IMAGENES->"+listaImagenes.size());
		for(int i=0;i<listaImagenes.size();i+=4)
		{
			CGaleriaPaquete4 imagenes=new CGaleriaPaquete4();
			imagenes.setGaleria1(listaImagenes.get(i));
			if((i+1)<listaImagenes.size())
				imagenes.setGaleria2(listaImagenes.get(i+1));
			if((i+2)<listaImagenes.size())
				imagenes.setGaleria3(listaImagenes.get(i+2));
			if((i+3)<listaImagenes.size())
				imagenes.setGaleria4(listaImagenes.get(i+3));
			listaImagenesPaquetes.add(imagenes);
		}
		BindUtils.postNotifyChange(null, null, this,"listaImagenesPaquetes");
	}
	@Command
	@NotifyChange({"update"})
	public void cambiosEnImagenPaquete(@BindingParam("galeria4")CGaleriaPaquete4 galeria4,@BindingParam("galeria")CGaleriaPaquete galeria)
	{
		if(!validoParaCambiarImagen(galeria.isBestado()))
			return;
		update=true;
		if(galeria4.getGaleria1().equals(galeria))
		{
			if(galeria4.getGaleria1().isBestado())
			{
				galeria4.getGaleria1().setBestado(false);
				galeria4.getGaleria1().setStyle_Select("div_content_imageHotel");
				quitarImagen(galeria4.getGaleria1().getcRutaImagen());
			}else{
				galeria4.getGaleria1().setBestado(true);
				galeria4.getGaleria1().setStyle_Select("div_content_imageHotel_selected");
				agregarImagen(galeria4.getGaleria1().getcRutaImagen());
			}
		}else if(galeria4.getGaleria2().equals(galeria))
		{
			if(galeria4.getGaleria2().isBestado())
			{
				galeria4.getGaleria2().setBestado(false);
				galeria4.getGaleria2().setStyle_Select("div_content_imageHotel");
				quitarImagen(galeria4.getGaleria2().getcRutaImagen());
			}else{
				galeria4.getGaleria2().setBestado(true);
				galeria4.getGaleria2().setStyle_Select("div_content_imageHotel_selected");
				agregarImagen(galeria4.getGaleria2().getcRutaImagen());
			}
		}else if(galeria4.getGaleria3().equals(galeria))
		{
			if(galeria4.getGaleria3().isBestado())
			{
				galeria4.getGaleria3().setBestado(false);
				galeria4.getGaleria3().setStyle_Select("div_content_imageHotel");
				quitarImagen(galeria4.getGaleria3().getcRutaImagen());
			}else{
				galeria4.getGaleria3().setBestado(true);
				galeria4.getGaleria3().setStyle_Select("div_content_imageHotel_selected");
				agregarImagen(galeria4.getGaleria3().getcRutaImagen());
			}
		}else if(galeria4.getGaleria4().equals(galeria))
		{
			if(galeria4.getGaleria4().isBestado())
			{
				galeria4.getGaleria4().setBestado(false);
				galeria4.getGaleria4().setStyle_Select("div_content_imageHotel");
				quitarImagen(galeria4.getGaleria4().getcRutaImagen());
			}else{
				galeria4.getGaleria4().setBestado(true);
				galeria4.getGaleria4().setStyle_Select("div_content_imageHotel_selected");
				agregarImagen(galeria4.getGaleria4().getcRutaImagen());
			}
		}
		refrescarCambios(galeria4);
	}
	public boolean validoParaCambiarImagen(boolean Marcado)
	{
		boolean valido=true;
		System.out.println("tamanio lista imagens>"+oPaquete.getListaImagenes().size());
		for(CGaleriaPaquete galeria:oPaquete.getListaImagenes())
		{
			if(galeria.getCpaquetecod()==null)
			{
				valido=false;
				break;
			}
		}
		if(valido && !Marcado)
		{
				if(!oPaquete.getcFoto1().equals("img/tours/hotelxdefecto.jpg") && 
						!oPaquete.getcFoto2().equals("img/tours/hotelxdefecto.jpg")&&
						!oPaquete.getcFoto3().equals("img/tours/hotelxdefecto.jpg")&&
						!oPaquete.getcFoto4().equals("img/tours/hotelxdefecto.jpg")&&
						!oPaquete.getcFoto5().equals("img/tours/hotelxdefecto.jpg")){
					valido=false;
				}
		}
		return valido;
	}
	public void quitarImagen(String rutaImagen)
	{
			if(oPaquete.getcFoto1().equals(rutaImagen))
			{
				oPaquete.setcFoto1("img/tours/hotelxdefecto.jpg");
			}else if(oPaquete.getcFoto2().equals(rutaImagen))
			{
				oPaquete.setcFoto2("img/tours/hotelxdefecto.jpg");
			}else if(oPaquete.getcFoto3().equals(rutaImagen))
			{
				oPaquete.setcFoto3("img/tours/hotelxdefecto.jpg");
			}else if(oPaquete.getcFoto4().equals(rutaImagen))
			{
				oPaquete.setcFoto4("img/tours/hotelxdefecto.jpg");
			}else if(oPaquete.getcFoto5().equals(rutaImagen))
			{
				oPaquete.setcFoto5("img/tours/hotelxdefecto.jpg");
			}
	}
	public void agregarImagen(String rutaImagen)
	{
			if(oPaquete.getcFoto1().equals("img/tours/hotelxdefecto.jpg"))
			{
				oPaquete.setcFoto1(rutaImagen);
			}else if(oPaquete.getcFoto2().equals("img/tours/hotelxdefecto.jpg"))
			{
				oPaquete.setcFoto2(rutaImagen);
			}else if(oPaquete.getcFoto3().equals("img/tours/hotelxdefecto.jpg"))
			{
				oPaquete.setcFoto3(rutaImagen);
			}else if(oPaquete.getcFoto4().equals("img/tours/hotelxdefecto.jpg"))
			{
				oPaquete.setcFoto4(rutaImagen);
			}else if(oPaquete.getcFoto5().equals("img/tours/hotelxdefecto.jpg"))
			{
				oPaquete.setcFoto5(rutaImagen);
			}
	}
	@Command
	@NotifyChange({"update"})
	public void guardarCambios(@BindingParam("componente")Component comp)
	{
		CPaqueteDAO paqueteDao=new CPaqueteDAO();
		boolean correcto=paqueteDao.isOperationCorrect(paqueteDao.modificarImagenesPaquete(oPaquete));
		if(correcto)
		{
			update=false;
			for(CGaleriaPaquete galeria:oPaquete.getListaImagenes())
			{
				CGaleriaPaqueteDAO galeriaHotelDao=new CGaleriaPaqueteDAO();
				correcto=galeriaHotelDao.isOperationCorrect(galeriaHotelDao.modificarImagenPaquete(galeria));
			}
			Clients.showNotification("Los cambios efectuados se guardaron correctamente",Clients.NOTIFICATION_TYPE_INFO,comp,"before_start",300);
		}
		else
			Clients.showNotification("No se pudieron guardar los cambios efectuados",Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",300);
	}
	public void refrescarCambios(CGaleriaPaquete4 galeria4)
	{
		BindUtils.postNotifyChange(null, null, galeria4, "galeria1");
		BindUtils.postNotifyChange(null, null, galeria4, "galeria2");
		BindUtils.postNotifyChange(null, null, galeria4, "galeria3");
		BindUtils.postNotifyChange(null, null, galeria4, "galeria4");
	}
}
