package com.pricing.viewModel;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

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
}
