package com.pricing.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pricing.model.CCategoriaHotel;
import com.pricing.model.CHotel;
import com.pricing.model.CServicio;

public class CHotelDAO extends CConexion
{
	private CHotel oHotel;
	private ArrayList<CHotel> listaHoteles;
	private ArrayList<CCategoriaHotel> listaCategorias;
	private ArrayList<CCategoriaHotel> listaCategoriasBusqueda;
	//============================
	public CHotel getoHotel() {
		return oHotel;
	}
	public void setoHotel(CHotel oHotel) {
		this.oHotel = oHotel;
	}
	public ArrayList<CHotel> getListaHoteles() {
		return listaHoteles;
	}
	public void setListaHoteles(ArrayList<CHotel> listaHoteles) {
		this.listaHoteles = listaHoteles;
	}
	public ArrayList<CCategoriaHotel> getListaCategorias() {
		return listaCategorias;
	}
	public void setListaCategorias(ArrayList<CCategoriaHotel> listaCategorias) {
		this.listaCategorias = listaCategorias;
	}
	public ArrayList<CCategoriaHotel> getListaCategoriasBusqueda() {
		return listaCategoriasBusqueda;
	}
	public void setListaCategoriasBusqueda(
			ArrayList<CCategoriaHotel> listaCategoriasBusqueda) {
		this.listaCategoriasBusqueda = listaCategoriasBusqueda;
	}
	//=================================
	public CHotelDAO() {
		// TODO Auto-generated constructor stub
		super();
		this.oHotel=new CHotel();
	}
	public CHotelDAO(CHotel hotel)
	{
		super();
		this.oHotel=hotel;
	}
	//==================================
	public List recuperarHotelBD(int codHotel)
	{
		Object[] value={codHotel};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarHotel",value);
	}
	
	public List recuperarCategoriasHotelBD(){
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarTodasCategoria");
	}
	
	public List recuperarHotelesxDestinoxCategoriaHotelBD(String hotel,String destino,String categoria){
		String[] values={hotel,destino,categoria};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_BuscarHotelesxDestinoxCategoria",values);
	}	
	public void asignarHotelesxDestinoxCategoriaHotel(List lista){
		listaHoteles=new ArrayList<CHotel>();
		System.out.println("esta entrando aqui.."+lista.size());
		for(int i=0;i<lista.size();i++){
			Map row=(Map)lista.get(i);
			listaHoteles.add(new CHotel((int)row.get("nhotelcod"),(String)row.get("chotel"), 
					(String)row.get("cdescripcionidioma1"),(String)row.get("cdescripcionidioma2"),
					(String)row.get("cdescripcionidioma3"),(String)row.get("cdescripcionidioma4"),
					(String)row.get("cdescripcionidioma5"),(String)row.get("curl"),
					(int)row.get("categoriahotelcod"),(Number) row.get("npreciosimple"),
					(Number)row.get("npreciodoble"),(Number)row.get("npreciotriple"),
					(boolean)row.get("bestado"),(Number)row.get("npreciocamaadicional"),(int)row.get("ndestinocod"),
					(String)row.get("cfoto1"),(String)row.get("cfoto2"),(String)row.get("cfoto3"),(String)row.get("cfoto4"),
					(String)row.get("cfoto5"),(String)row.get("cimagenubicacion")));
		 }
	}
	public void asignarListaCategoriashotel(List lista){
		listaCategorias=new ArrayList<CCategoriaHotel>();
		for(int i=0;i<lista.size();i++){
			Map row=(Map)lista.get(i);
			listaCategorias.add(new CCategoriaHotel((int)row.get("categoriahotelcod"),(String)row.get("ccategoriaidioma1"),
					(String)row.get("ccategoriaidioma2"),(String)row.get("ccategoriaidioma3"),
					(String)row.get("ccategoriaidioma4"),(String)row.get("ccategoriaidioma5")));
		}
	}
	public void asignarListaCategoriashotelBusqueda(List lista){
		listaCategoriasBusqueda=new ArrayList<CCategoriaHotel>();
		listaCategoriasBusqueda.add(new CCategoriaHotel(0,"Toda las categorias","","","",""));
		for(int i=0;i<lista.size();i++){
			Map row=(Map)lista.get(i);
			listaCategoriasBusqueda.add(new CCategoriaHotel((int)row.get("categoriahotelcod"),(String)row.get("ccategoriaidioma1"),
					(String)row.get("ccategoriaidioma2"),(String)row.get("ccategoriaidioma3"),
					(String)row.get("ccategoriaidioma4"),(String)row.get("ccategoriaidioma5")));
		}
	}
	public void asignarHotel(List lista)
	{
		Map row=(Map) lista.get(0);
		oHotel=new CHotel((int)row.get("nhotelcod"),(String)row.get("chotel"), 
				(String)row.get("cdescripcionidioma1"),(String)row.get("cdescripcionidioma2"),
				(String)row.get("cdescripcionidioma3"),(String)row.get("cdescripcionidioma4"),
				(String)row.get("cdescripcionidioma5"),(String)row.get("curl"),
				(int)row.get("categoriahotelcod"),(Number) row.get("npreciosimple"),
				(Number)row.get("npreciodoble"),(Number)row.get("npreciotriple"),
				(boolean)row.get("bestado"),(Number)row.get("npreciocamaadicional"),
				(int)row.get("ndestinocod"),(String)row.get("cfoto1"),(String)row.get("cfoto2"),
				(String)row.get("cfoto3"),(String)row.get("cfoto4"),(String)row.get("cfoto5"),
				(String)row.get("cimagenubicacion")); 
	}
	public List recuperarHotelesBD()
	{
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarTodosHoteles");
	}
	public void asignarListaHoteles(List lista)
	{
		listaHoteles=new ArrayList<CHotel>();
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaHoteles.add(new CHotel((int)row.get("nhotelcod"),(String)row.get("chotel"), 
					(String)row.get("cdescripcionidioma1"),(String)row.get("cdescripcionidioma2"),
					(String)row.get("cdescripcionidioma3"),(String)row.get("cdescripcionidioma4"),
					(String)row.get("cdescripcionidioma5"),(String)row.get("curl"),
					(int)row.get("categoriahotelcod"),(Number) row.get("npreciosimple"),
					(Number)row.get("npreciodoble"),(Number)row.get("npreciotriple"),
					(boolean)row.get("bestado"),(Number)row.get("npreciocamaadicional"),
					(int)row.get("ndestinocod"),(String)row.get("cfoto1"),(String)row.get("cfoto2"),
					(String)row.get("cfoto3"),(String)row.get("cfoto4"),(String)row.get("cfoto5"),
					(String)row.get("cimagenubicacion")));
		}
	}
	public List insertarHotel(CHotel hotel)
	{
		Object[] values={hotel.getcHotel(),hotel.getcDescripcionIdioma1(),hotel.getcDescripcionIdioma2(),
				hotel.getcDescripcionIdioma3(),hotel.getcDescripcionIdioma4(),hotel.getcDescripcionIdioma5(),
				hotel.getcUrl(),hotel.getCategoriaHotelCod(),hotel.getnPrecioSimple().doubleValue(),hotel.getnPrecioDoble().doubleValue(),
				hotel.getnPrecioTriple().doubleValue(),hotel.getnPrecioCamaAdicional().doubleValue(),
				hotel.getnDestinoCod(),hotel.getcFoto1(),hotel.getcFoto2(),hotel.getcFoto3(),
				hotel.getcFoto4(),hotel.getcFoto5(),hotel.getcImagenUbicacion()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_RegistrarHotel", values);
	}
	public List modificarHotel(CHotel hotel)
	{
		Object[] values={hotel.getnHotelCod(),hotel.getcHotel(),hotel.getcDescripcionIdioma1(),hotel.getcDescripcionIdioma2(),
				hotel.getcDescripcionIdioma3(),hotel.getcDescripcionIdioma4(),hotel.getcDescripcionIdioma5(),
				hotel.getcUrl(),hotel.getCategoriaHotelCod(),hotel.getnPrecioSimple().doubleValue(),hotel.getnPrecioDoble().doubleValue(),
				hotel.getnPrecioTriple().doubleValue(),hotel.isbEstado(),
				hotel.getnPrecioCamaAdicional().doubleValue(),hotel.getnDestinoCod(),hotel.getcFoto1(),hotel.getcFoto2(),hotel.getcFoto3(),
				hotel.getcFoto4(),hotel.getcFoto5(),hotel.getcImagenUbicacion()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_ModificarHotel", values);
	}
	public List modificarImagenesHotel(CHotel hotel)
	{
		Object[] values={hotel.getnHotelCod(),hotel.getcFoto1(),hotel.getcFoto2(),
				hotel.getcFoto3(),hotel.getcFoto4(),hotel.getcFoto5(),
				hotel.getcImagenUbicacion()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_ModificarImagenesHotel", values);
	}
	public boolean isOperationCorrect(List lista)
	{
		Map row=(Map)lista.get(0);
		boolean correcto=row.get("resultado").toString().equals("correcto");
		if(correcto)return true;
		else return false;
	}
}
