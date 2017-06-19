package com.pricing.dao;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pricing.model.CEtiqueta;
import com.pricing.model.CHotel;
import com.pricing.model.CPaquete;

public class CPaqueteDAO extends CConexion 
{
	private CPaquete oPaquete;
	private ArrayList<CPaquete> listaPaquetes;
	//======================
	public CPaquete getoPaquete() {
		return oPaquete;
	}
	public void setoPaquete(CPaquete oPaquete) {
		this.oPaquete = oPaquete;
	}
	public ArrayList<CPaquete> getListaPaquetes() {
		return listaPaquetes;
	}
	public void setListaPaquetes(ArrayList<CPaquete> listaPaquetes) {
		this.listaPaquetes = listaPaquetes;
	}
	//=========================
	public CPaqueteDAO() {
		// TODO Auto-generated constructor stub
		super();
		this.oPaquete=new CPaquete();
	}
	public CPaqueteDAO(CPaquete paquete)
	{
		super();
		this.oPaquete=paquete;
	}
	/**METODOS DE PAQUETE**/
	public List recuperarPaqueteBD(String codigoPaquete)
	{
		Object[] value={codigoPaquete};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarPaquete", value);
	}
	public List recuperarPaquetesBD()
	{
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarPaquetes");
	}
	public void asignarPaquete(List lista)
	{
		Map row=(Map)lista.get(0);
		oPaquete=new CPaquete((String)row.get("cpaquetecod"),(String)row.get("ctituloidioma1"),
				(String)row.get("ctituloidioma2"),(String)row.get("ctituloidioma3"),
				(String)row.get("ctituloidioma4"),(String)row.get("ctituloidioma5"),
				(String)row.get("cdescripcionidioma1"), (String)row.get("cdescripcionidioma2"),
				(String)row.get("cdescripcionidioma3"), (String)row.get("cdescripcionidioma4"), 
				(String)row.get("cdescripcionidioma5"),
				(int)row.get("ndias"),(int)row.get("nnoches"), 
				(Number)row.get("npreciouno"),(Number)row.get("npreciodos"),
				(Number)row.get("npreciotres"),(Number)row.get("npreciocuatro"), 
				(Number)row.get("npreciocinco"),(String)row.get("cdisponibilidad"), 
				(boolean)row.get("bestado"),(String)row.get("citinerarioidioma1"),(String)row.get("citinerarioidioma2")
				,(String)row.get("citinerarioidioma3"),(String)row.get("citinerarioidioma4"),(String)row.get("citinerarioidioma5"),
				(String)row.get("curlreferenciapaquete"),(int)row.get("nporcentajecobro"),
				(int)row.get("npagominimo"),(boolean)row.get("bmodoporcentual"),(boolean)row.get("bmodopagototal"),
				(Number)row.get("ndescuentomenor_estudiante"),(boolean)row.get("bsubirdocpax"),
				(boolean)row.get("bsubirdoc_y_llenardatospax"),(boolean)row.get("bsubirdoc_o_llenardatospax"),
				(boolean)row.get("bllenardatosunpax"),(boolean)row.get("bhotelesconcamaadicional"),
				(boolean)row.get("bconcupon"));
	}
	public void asignarListaPaquetes(List lista) throws UnsupportedEncodingException
	{
		listaPaquetes=new ArrayList<CPaquete>();
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaPaquetes.add(new CPaquete((String)row.get("cpaquetecod"),(String)row.get("ctituloidioma1"),
					(String)row.get("ctituloidioma2"),(String)row.get("ctituloidioma3"),
					(String)row.get("ctituloidioma4"),(String)row.get("ctituloidioma5"),
					(String)row.get("cdescripcionidioma1"), (String)row.get("cdescripcionidioma2"),
					(String)row.get("cdescripcionidioma3"), (String)row.get("cdescripcionidioma4"), 
					(String)row.get("cdescripcionidioma5"),
					(int)row.get("ndias"),(int)row.get("nnoches"), 
					(Number)row.get("npreciouno"),(Number)row.get("npreciodos"),
					(Number)row.get("npreciotres"),(Number)row.get("npreciocuatro"), 
					(Number)row.get("npreciocinco"),(String)row.get("cdisponibilidad"), 
					(boolean)row.get("bestado"),(String)row.get("cfoto1"),(String)row.get("cfoto2"),(String)row.get("cfoto3"),
					(String)row.get("cfoto4"),(String)row.get("cfoto5"),(String)row.get("citinerarioidioma1"),(String)row.get("citinerarioidioma2")
					,(String)row.get("citinerarioidioma3"),(String)row.get("citinerarioidioma4"),(String)row.get("citinerarioidioma5"),
					(String)row.get("curlreferenciapaquete"),(int)row.get("nporcentajecobro"),
					(int)row.get("npagominimo"),(boolean)row.get("bmodoporcentual"),(boolean)row.get("bmodopagototal"),
					(Number)row.get("ndescuentomenor_estudiante"),(boolean)row.get("bsubirdocpax"),
					(boolean)row.get("bsubirdoc_y_llenardatospax"),(boolean)row.get("bsubirdoc_o_llenardatospax"),
					(boolean)row.get("bllenardatosunpax"),(boolean)row.get("bhotelesconcamaadicional"),
					(boolean)row.get("bconcupon")));
		}
	}
	public List buscarPaquetesBD(String nombre){
		String[] values={nombre};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_BuscarPaquetes",values);
	}
	public List insertarPaquete(CPaquete paquete)
	{
		Object[] values={paquete.getcTituloIdioma1(),paquete.getcTituloIdioma2(),
				paquete.getcTituloIdioma3(),paquete.getcTituloIdioma4(),
				paquete.getcTituloIdioma5(),paquete.getcDescripcionIdioma1(),paquete.getcDescripcionIdioma2(),
				paquete.getcDescripcionIdioma3(),paquete.getcDescripcionIdioma4(),paquete.getcDescripcionIdioma5(),paquete.getnDias(),paquete.getnNoches(),
				paquete.getnPrecioUno().doubleValue(),paquete.getnPrecioDos().doubleValue(),
				paquete.getnPrecioTres().doubleValue(),paquete.getnPrecioCuatro().doubleValue(),
				paquete.getnPrecioCinco().doubleValue(),paquete.getcDisponibilidad(),
				paquete.getnDiaCaminoInka(),paquete.getcFoto1(),paquete.getcFoto2(),paquete.getcFoto3(),paquete.getcFoto4(),paquete.getcFoto5(),
				paquete.getcItinerarioIdioma1(),paquete.getcItinerarioIdioma2(),paquete.getcItinerarioIdioma3(),paquete.getcItinerarioIdioma4(),paquete.getcItinerarioIdioma5(),
				paquete.getcUrlReferenciaPaquete(),paquete.getnPorcentajeCobro(),paquete.getnPagoMinimo(),
				paquete.isbModoPorcentual(),paquete.isbModoPagoTotal(),paquete.getnDescuentoMenor_Estudiante().doubleValue(),
				paquete.isbSubirDocPax(),paquete.isbSubirDoc_Y_LlenarDatosPax(),paquete.isbSubirDoc_O_LlenarDatosPax(),
				paquete.isbLlenarDatosUnPax(),paquete.isbHotelesConCamaAdicional(),paquete.isbConCupon()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_RegistrarPaquete", values);
	}
	/**METODOS DE PAQUETE SERVICIO**/
	public List insertarPaqueteServicio(String codPaquete,int codServicio)
	{
		Object[] values={codPaquete,codServicio};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_RegistrarPaqueteServicio", values);
	}
	public List eliminarPaqueteServicio(int codPS)
	{
		Object[] values={codPS};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_EliminarPaqueteServicio", values);
	}
	/**METODOS DE PAQUETE ACTIVIDAD**/
	public List insertarPaqueteActividad(String codPaquete,int codActividad)
	{
		Object[] values={codPaquete,codActividad};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_RegistrarPaqueteActividad", values);
	}
	public List eliminarPaqueteActividad(int codPA)
	{
		Object[] values={codPA};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_EliminarPaqueteActividad", values);
	}
	/**METODOS DE PAQUETE DESTINO**/
	public List eliminarPaqueteDestino(int codPD)
	{
		Object[] values={codPD};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_EliminarPaqueteDestino", values);
	}
	public List insertarPaqueteDestino(String codPaquete,int codDestino,int noches,int ordenItinerario,boolean conCaminoInka)
	{
		Object[] values={codPaquete,codDestino,noches,ordenItinerario,conCaminoInka};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_RegistrarPaqueteDestino", values);
	}
	public List modificarPaqueteDestino(int codpd,int noches,int ordenItinerario,boolean conCaminoInka)
	{
		Object[] values={codpd,noches,ordenItinerario,conCaminoInka};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_ModificarPaqueteDestino", values);
	}
	/**METODOS DE PAQUETE CATEGORIA HOTEL**/
	public List insertarPaqueteCatHotel(String codPaquete)
	{
		Object[] values={codPaquete};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_RegistrarPaqueteCatHotel", values);
	}
	public String recuperarCodigoPaquete(List lista)
	{
		Map row=(Map)lista.get(0);
		String cod=row.get("codpaquete").toString();
		return cod;
	}
	public List modificarPaquete(CPaquete paquete)
	{
		Object[] values={paquete.getcPaqueteCod(),paquete.getcTituloIdioma1(),
				paquete.getcTituloIdioma2(),paquete.getcTituloIdioma3(),
				paquete.getcTituloIdioma4(),paquete.getcTituloIdioma5(),
				paquete.getcDescripcionIdioma1(),paquete.getcDescripcionIdioma2(),
				paquete.getcDescripcionIdioma3(),paquete.getcDescripcionIdioma4(),
				paquete.getcDescripcionIdioma5(),paquete.getnDias(),paquete.getnNoches(),
				paquete.getnPrecioUno().doubleValue(),paquete.getnPrecioDos().doubleValue(),
				paquete.getnPrecioTres().doubleValue(),paquete.getnPrecioCuatro().doubleValue(),
				paquete.getnPrecioCinco().doubleValue(),paquete.getcDisponibilidad(),
				paquete.getnDiaCaminoInka(),paquete.isbEstado(),paquete.getcFoto1(),paquete.getcFoto2(),
				paquete.getcFoto3(),paquete.getcFoto4(),paquete.getcFoto5(),paquete.getcItinerarioIdioma1(),
				paquete.getcItinerarioIdioma2(),paquete.getcItinerarioIdioma3(),
				paquete.getcItinerarioIdioma4(),paquete.getcItinerarioIdioma5(),
				paquete.getcUrlReferenciaPaquete(),paquete.getnPorcentajeCobro(),paquete.getnPagoMinimo(),
				paquete.isbModoPorcentual(),paquete.isbModoPagoTotal(),paquete.getnDescuentoMenor_Estudiante().doubleValue(),
				paquete.isbSubirDocPax(),paquete.isbSubirDoc_Y_LlenarDatosPax(),paquete.isbSubirDoc_O_LlenarDatosPax(),
				paquete.isbLlenarDatosUnPax(),paquete.isbHotelesConCamaAdicional(),paquete.isbConCupon()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_ModificarPaquetes", values);
	}
	public List modificarImagenesPaquete(CPaquete paquete)
	{
		Object[] values={paquete.getcPaqueteCod(),paquete.getcFoto1(),paquete.getcFoto2(),
				paquete.getcFoto3(),paquete.getcFoto4(),paquete.getcFoto5()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_ModificarImagenesPaquete", values);
	}
	public boolean isOperationCorrect(List lista)
	{
		Map row=(Map)lista.get(0);
		boolean correcto=row.get("resultado").toString().equals("correcto");
		if(correcto)return true;
		else return false;
	}
}
