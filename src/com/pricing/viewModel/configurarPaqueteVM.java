package com.pricing.viewModel;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.Clients;

import com.pricing.model.CPaquete;

public class configurarPaqueteVM {

	private CPaquete oPaquete;
	//========================
	public CPaquete getoPaquete() {
		return oPaquete;
	}
	public void setoPaquete(CPaquete oPaquete) {
		this.oPaquete = oPaquete;
	}
	//=========================
	@Init
	public void initVM()
	{
		oPaquete=new CPaquete();
	}
	@GlobalCommand
	@NotifyChange({"oPaquete"})
	public void obtenerDatosConfigPaquete(@BindingParam("paquete")CPaquete paquete)
	{
		setoPaquete(paquete);
	}
	@Command
	@NotifyChange({"oPaquete"})
	public void selectModoLlenadoPax(@BindingParam("opcion")String opcion)
	{
		if(opcion.toString().equals("1"))//Solo subir documentos
		{
			oPaquete.setbSubirDocPax(true);
			oPaquete.setbSubirDoc_O_LlenarDatosPax(false);
			oPaquete.setbSubirDoc_Y_LlenarDatosPax(false);
			oPaquete.setbLlenarDatosUnPax(false);
		}else if(opcion.toString().equals("2"))//Subir doc.. y llenar datos
		{
			oPaquete.setbSubirDocPax(false);
			oPaquete.setbSubirDoc_O_LlenarDatosPax(false);
			oPaquete.setbSubirDoc_Y_LlenarDatosPax(true);
			oPaquete.setbLlenarDatosUnPax(false);
		}else if(opcion.toString().equals("3"))//Llenar unicamente los datos de los pasajeros
		{
			oPaquete.setbSubirDocPax(false);
			oPaquete.setbSubirDoc_O_LlenarDatosPax(true);
			oPaquete.setbSubirDoc_Y_LlenarDatosPax(false);
			oPaquete.setbLlenarDatosUnPax(false);
		}else//Llenado de datos unicamente del pasajero que reserva
		{
			oPaquete.setbSubirDocPax(false);
			oPaquete.setbSubirDoc_O_LlenarDatosPax(false);
			oPaquete.setbSubirDoc_Y_LlenarDatosPax(false);
			oPaquete.setbLlenarDatosUnPax(true);
		}
	}
	@Command
	@NotifyChange({"oPaquete"})
	public void selectConfigHoteles(@BindingParam("opcion")String opcion)
	{
		if(opcion.toString().equals("1"))//Hoteles con cama adicional
		{
			oPaquete.setbHotelesConCamaAdicional(true);
			oPaquete.setbHotelesSinCamaAdicional(false);
		}else//Hoteles sin cama adicional
		{
			oPaquete.setbHotelesSinCamaAdicional(true);
			oPaquete.setbHotelesConCamaAdicional(false);
		}
	}
	@Command
	@NotifyChange({"oPaquete"})
	public void selectConfigCupon(@BindingParam("opcion")String opcion)
	{
		if(opcion.toString().equals("1"))//Paquete con cupon de descuento
		{
			oPaquete.setbConCupon(true);
			oPaquete.setbSinCupon(false);
		}else//Paquete sin cupon de descuento
		{
			oPaquete.setbConCupon(false);
			oPaquete.setbSinCupon(true);
		}
	}
	@Command
	@NotifyChange({"oPaquete"})
	public void selectConfigParcialTotal(@BindingParam("opcion")String opcion)
	{
		if(opcion.toString().equals("1"))//Hoteles con cama adicional
		{
			oPaquete.setbModoPagoPartes(true);
			oPaquete.setbModoPagoTotal(false);
		}else//Hoteles sin cama adicional
		{
			oPaquete.setbModoPagoPartes(false);
			oPaquete.setbModoPagoTotal(true);
		}
	}
	@Command
	@NotifyChange({"oPaquete"})
	public void selectModoDeCobro(@BindingParam("modo")String modo)
	{
		if(modo.equals("porcentaje"))
		{
			oPaquete.setbModoPorcentual(true);
			oPaquete.setbModoMinimo(false);
		}else
		{
			oPaquete.setbModoPorcentual(false);
			oPaquete.setbModoMinimo(true);
		}
	}
	@Command
	@NotifyChange({"oPaquete"})
	public void changeValorCobro(@BindingParam("valor")int valor,@BindingParam("componente")Component comp)
	{
		if(valor==1)
		{
			if(oPaquete.getnDescuentoMenor_Estudiante().doubleValue()<0)
			{
				oPaquete.setnDescuentoMenor_Estudiante(0);
				Clients.showNotification("Digite valores numericos no negativos",Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",3000);
			}
		}else if(valor==5)
		{
			if(oPaquete.getnPorcentajeCobro()<0)
			{
				oPaquete.setnPorcentajeCobro(0);
				Clients.showNotification("Digite valores numericos no negativos",Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",3000);
			}
		}else if(valor==6)
		{
			if(oPaquete.getnPagoMinimo()<0)
			{
				oPaquete.setnPagoMinimo(0);
				Clients.showNotification("Digite valores numericos no negativos",Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",3000);
			}
		}
	}
}
