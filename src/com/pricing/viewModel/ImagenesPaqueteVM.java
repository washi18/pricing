package com.pricing.viewModel;

import java.io.File;
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
import com.pricing.util.ScannUtil;

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
		System.out.println("Nombre de la imagen: "+galeria.getCimage());
		if(!validoParaCambiarImagen(galeria.isBestado()))
			return;
		update=true;
		if(galeria4.getGaleria1().equals(galeria))
		{
			System.out.println("Entre a cambiar estado de imagen");
			if(galeria4.getGaleria1().isBestado())
			{
				galeria4.getGaleria1().setBestado(false);
				galeria4.getGaleria1().setStyle_Select("div_content_imagePaquete");
				quitarImagen(galeria4.getGaleria1().getcRutaImagen());
			}else{
				galeria4.getGaleria1().setBestado(true);
				galeria4.getGaleria1().setStyle_Select("div_content_imagePaquete_selected");
				agregarImagen(galeria4.getGaleria1().getcRutaImagen());
			}
		}else if(galeria4.getGaleria2().equals(galeria))
		{
			if(galeria4.getGaleria2().isBestado())
			{
				galeria4.getGaleria2().setBestado(false);
				galeria4.getGaleria2().setStyle_Select("div_content_imagePaquete");
				quitarImagen(galeria4.getGaleria2().getcRutaImagen());
			}else{
				galeria4.getGaleria2().setBestado(true);
				galeria4.getGaleria2().setStyle_Select("div_content_imagePaquete_selected");
				agregarImagen(galeria4.getGaleria2().getcRutaImagen());
			}
		}else if(galeria4.getGaleria3().equals(galeria))
		{
			if(galeria4.getGaleria3().isBestado())
			{
				galeria4.getGaleria3().setBestado(false);
				galeria4.getGaleria3().setStyle_Select("div_content_imagePaquete");
				quitarImagen(galeria4.getGaleria3().getcRutaImagen());
			}else{
				galeria4.getGaleria3().setBestado(true);
				galeria4.getGaleria3().setStyle_Select("div_content_imagePaquete_selected");
				agregarImagen(galeria4.getGaleria3().getcRutaImagen());
			}
		}else if(galeria4.getGaleria4().equals(galeria))
		{
			if(galeria4.getGaleria4().isBestado())
			{
				galeria4.getGaleria4().setBestado(false);
				galeria4.getGaleria4().setStyle_Select("div_content_imagePaquete");
				quitarImagen(galeria4.getGaleria4().getcRutaImagen());
			}else{
				galeria4.getGaleria4().setBestado(true);
				galeria4.getGaleria4().setStyle_Select("div_content_imagePaquete_selected");
				agregarImagen(galeria4.getGaleria4().getcRutaImagen());
			}
		}
		refrescarCambios(galeria4);
	}
	public boolean validoParaCambiarImagen(boolean Marcado)
	{
		System.out.println("Estado del marcado es: "+Marcado);
		boolean valido=true;
		for(CGaleriaPaquete galeria:oPaquete.getListaImagenes())
		{
			System.out.println("Codigo del paquete en la gaeria: "+galeria.getCpaquetecod());
			if(galeria.getCpaquetecod()==null)
			{
				valido=false;
				break;
			}
		}
		System.out.println("Que paso con valido: "+valido);
		if(valido && !Marcado)
		{
			System.out.println("Fotos del paquete:\n "+oPaquete.getcFoto1()+"\n"+
					oPaquete.getcFoto2()+"\n"+
					oPaquete.getcFoto3()+"\n"+
					oPaquete.getcFoto4()+"\n"+
					oPaquete.getcFoto2()+"\n");
				if(!oPaquete.getcFoto5().equals("img/tours/tourxdefecto.png") && 
						!oPaquete.getcFoto2().equals("img/tours/tourxdefecto.png")&&
						!oPaquete.getcFoto3().equals("img/tours/tourxdefecto.png")&&
						!oPaquete.getcFoto4().equals("img/tours/tourxdefecto.png")&&
						!oPaquete.getcFoto5().equals("img/tours/tourxdefecto.png")){
					valido=false;
				}
		}
		System.out.println("validez de imagen: "+valido);
		return valido;
	}
	public boolean validoParaEliminarImagen()
	{
		boolean valido=true;
		for(CGaleriaPaquete galeria:oPaquete.getListaImagenes())
		{
			System.out.println("Codigo del paquete en la gaeria: "+galeria.getCpaquetecod());
			if(galeria.getCpaquetecod()==null)
			{
				valido=false;
				break;
			}
		}
		return valido;
	}
	public void quitarImagen(String rutaImagen)
	{
			System.out.println("la ruta de la imagen es->"+rutaImagen);
			System.out.print(oPaquete.getcFoto1()+" = "+rutaImagen);
			if(oPaquete.getcFoto1().equals(rutaImagen))
			{
				System.out.print(oPaquete.getcFoto1()+" = "+rutaImagen);
				oPaquete.setcFoto1("img/tours/tourxdefecto.png");
			}else if(oPaquete.getcFoto2().equals(rutaImagen))
			{
				System.out.print(oPaquete.getcFoto2()+" = "+rutaImagen);
				oPaquete.setcFoto2("img/tours/tourxdefecto.png");
			}else if(oPaquete.getcFoto3().equals(rutaImagen))
			{
				System.out.print(oPaquete.getcFoto3()+" = "+rutaImagen);
				oPaquete.setcFoto3("img/tours/tourxdefecto.png");
			}else if(oPaquete.getcFoto4().equals(rutaImagen))
			{
				System.out.print(oPaquete.getcFoto4()+" = "+rutaImagen);
				oPaquete.setcFoto4("img/tours/tourxdefecto.png");
			}else if(oPaquete.getcFoto5().equals(rutaImagen))
			{
				System.out.print(oPaquete.getcFoto5()+" = "+rutaImagen);
				oPaquete.setcFoto5("img/tours/tourxdefecto.png");
			}
	}
	
	public void agregarImagen(String rutaImagen)
	{
			System.out.print(oPaquete.getcFoto1()+" = "+rutaImagen);
			if(oPaquete.getcFoto1().equals("img/tours/tourxdefecto.png"))
			{
				System.out.print(oPaquete.getcFoto1()+" = "+rutaImagen);
				oPaquete.setcFoto1(rutaImagen);
			}else if(oPaquete.getcFoto2().equals("img/tours/tourxdefecto.png"))
			{
				System.out.print(oPaquete.getcFoto2()+" = "+rutaImagen);
				oPaquete.setcFoto2(rutaImagen);
			}else if(oPaquete.getcFoto3().equals("img/tours/tourxdefecto.png"))
			{
				System.out.print(oPaquete.getcFoto3()+" = "+rutaImagen);
				oPaquete.setcFoto3(rutaImagen);
			}else if(oPaquete.getcFoto4().equals("img/tours/tourxdefecto.png"))
			{
				System.out.print(oPaquete.getcFoto4()+" = "+rutaImagen);
				oPaquete.setcFoto4(rutaImagen);
			}else if(oPaquete.getcFoto5().equals("img/tours/tourxdefecto.png"))
			{
				System.out.print(oPaquete.getcFoto5()+" = "+rutaImagen);
				oPaquete.setcFoto5(rutaImagen);
			}
	}
	@Command
	@NotifyChange()
	public void eliminarImagenGaleriaPaquete(@BindingParam("galeria4")CGaleriaPaquete4 galeria4,@BindingParam("galeria")CGaleriaPaquete galeria,@BindingParam("comp")Component comp)
	{
		if(!validoParaEliminarImagen())
			return;
		galeriaPaqueteDAO=new CGaleriaPaqueteDAO();
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
							for(int i=0;i<oPaquete.getListaImagenes().size();i++){
								if(oPaquete.getListaImagenes().get(i).equals(galeria)){
									oPaquete.getListaImagenes().remove(galeria);
								}
							}
							galeria.setVisible(false);
							BindUtils.postNotifyChange(null, null, this,"listaImagenesPaquetes");
							BindUtils.postNotifyChange(null, null, galeria,"visible");
							refrescarCambios(galeria4);
							if(correcto && correcto2){
								borraFicheroServidor(galeria);
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
	public void borraFicheroServidor(CGaleriaPaquete galeria)
	{
		String url=ScannUtil.getPathImagenPaquetes();
		File fichero = new File(url+galeria.getCimage());
		if (fichero.delete())
			System.out.println("El fichero ha sido borrado satisfactoriamente");
		else
			System.out.println("El fichero no puede ser borrado");
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
