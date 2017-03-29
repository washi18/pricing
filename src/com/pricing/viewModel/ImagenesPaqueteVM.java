package com.pricing.viewModel;

import java.util.ArrayList;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;

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
	private CGaleriaPaqueteDAO galeriaPaqueteDAO;
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
		galeriaPaqueteDAO=new CGaleriaPaqueteDAO();
		oPaquete=new CPaquete();
		update=false;
	}
	@GlobalCommand
	public void muestraImagenesSubidasPaquete(@BindingParam("cPaquete")CPaquete paquete)
	{
		ArrayList<CGaleriaPaquete> listaImagenes=new ArrayList<CGaleriaPaquete>();
		setoPaquete(paquete);
		for(CGaleriaPaquete galeria:paquete.getListaImagenes())
		{	
				listaImagenes.add(galeria);
		}
		mostrarImagenes(listaImagenes);
	}
	public void mostrarImagenes(ArrayList<CGaleriaPaquete> listaImagenes)
	{
		listaImagenesPaquetes.clear();
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
			System.out.println("termino de hacer esta parte imagenes");
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
			System.out.println("entro a galeria 1");
			if(galeria4.getGaleria1().isBestado())
			{
				System.out.println("entro a galeria 1 true");
				galeria4.getGaleria1().setBestado(false);
				galeria4.getGaleria1().setStyle_Select("div_content_imageHotel");
				quitarImagen(galeria4.getGaleria1().getcRutaImagen());
			}else{
				System.out.println("entro a galeria 1 false");
				galeria4.getGaleria1().setBestado(true);
				galeria4.getGaleria1().setStyle_Select("div_content_imageHotel_selected");
				agregarImagen(galeria4.getGaleria1().getcRutaImagen());
			}
		}else if(galeria4.getGaleria2().equals(galeria))
		{
			System.out.println("entro a galeria 2");
			if(galeria4.getGaleria2().isBestado())
			{
				System.out.println("entro a galeria 1 true");
				galeria4.getGaleria2().setBestado(false);
				galeria4.getGaleria2().setStyle_Select("div_content_imageHotel");
				quitarImagen(galeria4.getGaleria2().getcRutaImagen());
			}else{
				System.out.println("entro a galeria 1 false");
				galeria4.getGaleria2().setBestado(true);
				galeria4.getGaleria2().setStyle_Select("div_content_imageHotel_selected");
				agregarImagen(galeria4.getGaleria2().getcRutaImagen());
			}
		}else if(galeria4.getGaleria3().equals(galeria))
		{
			System.out.println("entro a galeria 3");
			if(galeria4.getGaleria3().isBestado())
			{
				System.out.println("entro a galeria 1 true");
				galeria4.getGaleria3().setBestado(false);
				galeria4.getGaleria3().setStyle_Select("div_content_imageHotel");
				quitarImagen(galeria4.getGaleria3().getcRutaImagen());
			}else{
				System.out.println("entro a galeria 1 false");
				galeria4.getGaleria3().setBestado(true);
				galeria4.getGaleria3().setStyle_Select("div_content_imageHotel_selected");
				agregarImagen(galeria4.getGaleria3().getcRutaImagen());
			}
		}else if(galeria4.getGaleria4().equals(galeria))
		{
			System.out.println("entro a galeria 4");
			if(galeria4.getGaleria4().isBestado())
			{
				System.out.println("entro a galeria 1 true");
				galeria4.getGaleria4().setBestado(false);
				galeria4.getGaleria4().setStyle_Select("div_content_imageHotel");
				quitarImagen(galeria4.getGaleria4().getcRutaImagen());
			}else{
				System.out.println("entro a galeria 1 false");
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
				if(!oPaquete.getcFoto1().equals("img/tours/tourxdefecto.png") && 
						!oPaquete.getcFoto2().equals("img/tours/tourxdefecto.png")&&
						!oPaquete.getcFoto3().equals("img/tours/tourxdefecto.png")&&
						!oPaquete.getcFoto4().equals("img/tours/tourxdefecto.png")&&
						!oPaquete.getcFoto5().equals("img/tours/tourxdefecto.png")){
					valido=false;
				}
		}
		return valido;
	}
	public void quitarImagen(String rutaImagen)
	{
			System.out.println("la ruta de la imagen es->"+rutaImagen);
			if(oPaquete.getcFoto1().equals(rutaImagen))
			{
				oPaquete.setcFoto1("img/tours/tourxdefecto.png");
			}else if(oPaquete.getcFoto2().equals(rutaImagen))
			{
				oPaquete.setcFoto2("img/tours/tourxdefecto.png");
			}else if(oPaquete.getcFoto3().equals(rutaImagen))
			{
				oPaquete.setcFoto3("img/tours/tourxdefecto.png");
			}else if(oPaquete.getcFoto4().equals(rutaImagen))
			{
				oPaquete.setcFoto4("img/tours/tourxdefecto.png");
			}else if(oPaquete.getcFoto5().equals(rutaImagen))
			{
				oPaquete.setcFoto5("img/tours/tourxdefecto.png");
			}
	}
	
	public void agregarImagen(String rutaImagen)
	{
			if(oPaquete.getcFoto1().equals("img/tours/tourxdefecto.png"))
			{
				oPaquete.setcFoto1(rutaImagen);
			}else if(oPaquete.getcFoto2().equals("img/tours/tourxdefecto.png"))
			{
				oPaquete.setcFoto2(rutaImagen);
			}else if(oPaquete.getcFoto3().equals("img/tours/tourxdefecto.png"))
			{
				oPaquete.setcFoto3(rutaImagen);
			}else if(oPaquete.getcFoto4().equals("img/tours/tourxdefecto.png"))
			{
				oPaquete.setcFoto4(rutaImagen);
			}else if(oPaquete.getcFoto5().equals("img/tours/tourxdefecto.png"))
			{
				oPaquete.setcFoto5(rutaImagen);
			}
	}
	@Command
	@NotifyChange()
	public void eliminarImagenGaleriaPaquete(@BindingParam("galeria4")CGaleriaPaquete4 galeria4,@BindingParam("galeria")CGaleriaPaquete galeria,@BindingParam("comp")Component comp)
	{
		Messagebox.show("Esta seguro de eliminar esta imagen?", "Question", Messagebox.OK|Messagebox.CANCEL,
				Messagebox.QUESTION, new EventListener<Event>(){
					
					@Override
					public void onEvent(Event event) throws Exception {
						// TODO Auto-generated method stub
						if(Messagebox.ON_OK.equals(event.getName()))
						{
							if(galeria4.getGaleria1().equals(galeria))
							{
								System.out.println("ENTRA A GALERIA UNO");
									quitarImagen(galeria4.getGaleria1().getcRutaImagen());
							}else if(galeria4.getGaleria2().equals(galeria))
							{
								System.out.println("ENTRA A GALERIA DOS");
									quitarImagen(galeria4.getGaleria2().getcRutaImagen());
							}else if(galeria4.getGaleria3().equals(galeria))
							{
								System.out.println("ENTRA A GALERIA TRES");
									quitarImagen(galeria4.getGaleria3().getcRutaImagen());
							}else if(galeria4.getGaleria4().equals(galeria))
							{
								System.out.println("ENTRA A GALERIA CUATRO");
									quitarImagen(galeria4.getGaleria4().getcRutaImagen());
							}
							boolean correcto=galeriaPaqueteDAO.isOperationCorrect(galeriaPaqueteDAO.eliminarImagenGaleriaPaquete(galeria.getNgaleriapaquetecod()));
							CPaqueteDAO paqueteDao=new CPaqueteDAO();
							boolean correcto2=paqueteDao.isOperationCorrect(paqueteDao.modificarImagenesPaquete(oPaquete));
							CGaleriaPaqueteDAO galeriaPaqueteDao=new CGaleriaPaqueteDAO();
							ArrayList<CGaleriaPaquete> listaImgs=new ArrayList<CGaleriaPaquete>();
							galeriaPaqueteDao.asignarListaImagenesPaquete(galeriaPaqueteDao.recuperarImagenesPaqueteBD(oPaquete.getcPaqueteCod()));
							for(CGaleriaPaquete galeria:galeriaPaqueteDao.getListaImagenesPaquete())
							 {	
									listaImgs.add(galeria);
							 }
							mostrarImagenes(listaImgs);
							for(int i=0;i<oPaquete.getListaImagenes().size();i++){
								if(oPaquete.getListaImagenes().get(i).equals(galeria)){
									oPaquete.getListaImagenes().remove(galeria);
								}
							}
							if(correcto && correcto2){
								Clients.showNotification("Imagen eliminado satisfactoriamente", Clients.NOTIFICATION_TYPE_INFO, comp, "top_center", 2000);
							}else{
								Clients.showNotification("Fallo al eliminar imagen", Clients.NOTIFICATION_TYPE_ERROR, comp, "top_center", 2000);
							}
						}
						else if(Messagebox.ON_CANCEL.equals(event.getName()))
						{
							
						}
					}
				});
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
				System.out.println("nombre->"+galeria.getNgaleriapaquetecod()+"y estado->"+galeria.isBestado());
				CGaleriaPaqueteDAO galeriaPaqueteDao=new CGaleriaPaqueteDAO();
				correcto=galeriaPaqueteDao.isOperationCorrect(galeriaPaqueteDao.modificarImagenPaquete(galeria));
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
		BindUtils.postNotifyChange(null, null, oPaquete, "listaImagenes");
	}
}
