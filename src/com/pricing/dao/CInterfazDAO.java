package com.pricing.dao;

import java.util.List;
import java.util.Map;

import com.pricing.model.CInterfaz;

public class CInterfazDAO extends CConexion
{
	private CInterfaz oInterfaz;
	/*************/

	public CInterfaz getoInterfaz() {
		return oInterfaz;
	}

	public void setoInterfaz(CInterfaz oInterfaz) {
		this.oInterfaz = oInterfaz;
	}
	/**************/
	public CInterfazDAO() {
		// TODO Auto-generated constructor stub
		super();
	}
	/***************/
	public List recuperarConfigInterfazDB()
	{
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarInterfaz");
	}
	public void asignarConfigInterfaz(List lista)
	{
		if(!lista.isEmpty())
		{
			Map row=(Map)lista.get(0);
			oInterfaz=new CInterfaz((int)row.get("ninterfazcod"), 
					(String)row.get("ccolor_div_titulopaquete"), 
					(String)row.get("ccolor_lbl_titulopaquete"), 
					(String)row.get("ccolor_caption_fondopasos"), 
					(String)row.get("ccolor_lbl_textofondopasos"), 
					(String)row.get("ccolor_lbl_circlefondopasos"),
					(String)row.get("ccolor_lbl_circlebordepasos"),
					(String)row.get("ccolor_lbl_circlenumpasos"),
					(String)row.get("ccolor_div_colfondodatospax"),
					(String)row.get("ccolor_div_colbordedatospax"),
					(String)row.get("ccolor_lbl_coldatospasajeros"),
					(String)row.get("ccolor_listheader_datoshotel"),
					(String)row.get("ccolor_lbl_datoshotel"),
					(String)row.get("ccolor_div_fondobanderas"),
					(String)row.get("ccolor_caption_ryc"),
					(String)row.get("ccolor_lbl_ryc"),
					(String)row.get("ccolor_div_borderyc"),
					(String)row.get("ccolor_div_fondotituloryc"),
					(String)row.get("ccolor_lbl_textotituloryc"),
					(String)row.get("ccolor_div_fondoimporte"),
					(String)row.get("ccolor_lbl_textimporte"),
					(boolean)row.get("bsubirdocpax"),
					(boolean)row.get("bsubirdoc_y_llenardatospax"),
					(boolean)row.get("bsubirdoc_o_llenardatospax"),
					(boolean)row.get("bllenardatosunpax"),
					(boolean)row.get("bhotelesconcamaadicional"));
		}else
			oInterfaz=new CInterfaz();
	}
	public List insertarConfigInterfazColores(CInterfaz interfaz)
	{
		Object[] values={
				interfaz.getcColor_div_TituloPaquete(),
				interfaz.getcColor_lbl_TituloPaquete(), 
				interfaz.getcColor_caption_FondoPasos(), 
				interfaz.getcColor_lbl_TextoFondoPasos(), 
				interfaz.getcColor_lbl_CircleFondoPasos(),
				interfaz.getcColor_lbl_CircleBordePasos(),
				interfaz.getcColor_lbl_CircleNumPasos(),
				interfaz.getcColor_div_ColFondoDatosPax(),
				interfaz.getcColor_div_ColBordeDatosPax(),
				interfaz.getcColor_lbl_ColDatosPasajeros(),
				interfaz.getcColor_listHeader_DatosHotel(),
				interfaz.getcColor_lbl_DatosHotel(),
				interfaz.getcColor_div_FondoBanderas(),
				interfaz.getcColor_caption_RyC(),
				interfaz.getcColor_lbl_RyC(),
				interfaz.getcColor_div_BordeRyC(),
				interfaz.getcColor_div_FondoTituloRyC(),
				interfaz.getcColor_lbl_TextoTituloRyC(),
				interfaz.getcColor_div_FondoImporte(),
				interfaz.getcColor_lbl_TextImporte(),
		};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_RegistrarInterfazColores", values);
	}
	public List insertarConfigInterfazFormaPricing(CInterfaz interfaz)
	{
		Object[] values={
				interfaz.isbSubirDocPax(),
				interfaz.isbSubirDoc_Y_llenarDatosPax(),
				interfaz.isbSubirDoc_O_llenarDatosPax(),
				interfaz.isbLlenarDatosUnPax(),
				interfaz.isbHotelesConCamaAdicional(),
		};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_RegistrarInterfazFormaPricing", values);
	}
	public boolean isOperationCorrect(List lista)
	{
		Map row=(Map)lista.get(0);
		boolean correcto=row.get("resultado").toString().equals("correcto");
		if(correcto)return true;
		else return false;
	} 
}
