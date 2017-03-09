package com.pricing.viewModel;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Window;

import com.pricing.dao.CInterfazDAO;
import com.pricing.model.CInterfaz;

public class pricing_vistaPreviaVM 
{
	@Wire
	Button configColores;
	private CInterfaz oInterfaz;
	/******************/
	public CInterfaz getoInterfaz() {
		return oInterfaz;
	}

	public void setoInterfaz(CInterfaz oInterfaz) {
		this.oInterfaz = oInterfaz;
	}
	/*******************/
	@Init
	public void initVM()
	{
		oInterfaz=new CInterfaz();
		CInterfazDAO interfazDao=new CInterfazDAO();
		interfazDao.asignarConfigInterfaz(interfazDao.recuperarConfigInterfazDB());
		setoInterfaz(interfazDao.getoInterfaz());
	}
	@Command
	@NotifyChange({"oInterfaz"})
	public void AsignarEstilos(@BindingParam("color")String color,@BindingParam("opcion")int opcion)
	{
		System.out.println("opcion: "+opcion+" color: "+color);
		if(opcion==1)oInterfaz.setcColor_div_TituloPaquete(color);
		else if(opcion==2)oInterfaz.setcColor_lbl_TituloPaquete(color);
		else if(opcion==3)oInterfaz.setcColor_caption_FondoPasos(color);
		else if(opcion==4)oInterfaz.setcColor_lbl_TextoFondoPasos(color);
		else if(opcion==5)oInterfaz.setcColor_lbl_CircleFondoPasos(color);
		else if(opcion==6)oInterfaz.setcColor_lbl_CircleBordePasos(color);
		else if(opcion==7)oInterfaz.setcColor_lbl_CircleNumPasos(color);
		else if(opcion==8)oInterfaz.setcColor_div_ColFondoDatosPax(color);
		else if(opcion==9)oInterfaz.setcColor_div_ColBordeDatosPax(color);
		else if(opcion==10)oInterfaz.setcColor_lbl_ColDatosPasajeros(color);
		else if(opcion==11)oInterfaz.setcColor_listHeader_DatosHotel(color);
		else if(opcion==12)oInterfaz.setcColor_lbl_DatosHotel(color);
		else if(opcion==13)oInterfaz.setcColor_div_FondoBanderas(color);
		else if(opcion==14)oInterfaz.setcColor_caption_RyC(color);
		else if(opcion==15)oInterfaz.setcColor_lbl_RyC(color);
		else if(opcion==16)oInterfaz.setcColor_div_FondoTituloRyC(color);
		else if(opcion==17)oInterfaz.setcColor_div_BordeRyC(color);
		else if(opcion==18)oInterfaz.setcColor_lbl_TextoTituloRyC(color);
		else if(opcion==19)oInterfaz.setcColor_div_FondoImporte(color);
		else if(opcion==20)oInterfaz.setcColor_lbl_TextImporte(color);
		oInterfaz.asignar_estilos();
	}
	@GlobalCommand
	public void guardarCambiosInterfaz(@BindingParam("window")Window window)
	{
		CInterfazDAO interfazDao=new CInterfazDAO();
		boolean correcto=interfazDao.isOperationCorrect(interfazDao.insertarConfigInterfazColores(oInterfaz));
		if(correcto)
		{
			window.onClose();
			Clients.showNotification("Los cambios se guardaron correctamente",Clients.NOTIFICATION_TYPE_INFO,configColores,"before_start", 3000);
		}else
			Clients.showNotification("Los cambios no se guardaron correctamente",Clients.NOTIFICATION_TYPE_INFO,window,"before_start", 3000);
	}
	@GlobalCommand
	@NotifyChange({"oInterfaz"})
	public void cancelarCambiosInterfaz(@BindingParam("window")Window window)
	{
		System.out.println("Hola entre a cerrar config colores");
		CInterfazDAO interfazDao=new CInterfazDAO();
		interfazDao.asignarConfigInterfaz(interfazDao.recuperarConfigInterfazDB());
		setoInterfaz(interfazDao.getoInterfaz());
		window.onClose();
	}
}
