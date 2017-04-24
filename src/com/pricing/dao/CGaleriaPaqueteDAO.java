package com.pricing.dao;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pricing.model.CGaleriaHotel;
import com.pricing.model.CGaleriaPaquete;

public class CGaleriaPaqueteDAO extends CConexion{
	private ArrayList<CGaleriaPaquete> listaImagenesPaquete;
	/********************/
	public CGaleriaPaqueteDAO() {
		// TODO Auto-generated constructor stub
		super();
	}
	public ArrayList<CGaleriaPaquete> getListaImagenesPaquete() {
		return listaImagenesPaquete;
	}
	public void setListaImagenesPaquete(ArrayList<CGaleriaPaquete> listaImagenesPaquete) {
		this.listaImagenesPaquete = listaImagenesPaquete;
	}
	/**********************/
	public List recuperarImagenesPaqueteBD(String codPaquete)
	{
		Object[] values={codPaquete};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarImagenesPaquete", values);
	}
	public void asignarListaImagenesPaquete(List lista) throws UnsupportedEncodingException
	{
		if(lista!=null)
		{
			listaImagenesPaquete=new ArrayList<CGaleriaPaquete>();
			for(int i=0;i<lista.size();i++)
			{
				Map row=(Map)lista.get(i);
				listaImagenesPaquete.add(new CGaleriaPaquete((long)row.get("ngaleriapaquetecod"), 
						(String)row.get("cpaquetecod"),(String)row.get("cimage"), 
						(boolean)row.get("bestado")));
			}
		}
	}
	public List insertarImagenPaquete(CGaleriaPaquete galeria)
	{
		System.out.println("VALOR CODIGO->"+galeria.getCpaquetecod());
		System.out.println("VALOR RUTA->"+galeria.getcRutaImagen());
		System.out.println("VALOR BOOLEANO->"+galeria.isBestado());
		Object[] values={galeria.getCpaquetecod(),
				galeria.getcRutaImagen(),galeria.isBestado()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_RegistrarGaleriaPaquete", values);
	}
	public List modificarImagenPaquete(CGaleriaPaquete galeria)
	{
		Object[] values={(int)galeria.getNgaleriapaquetecod(),galeria.isBestado()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_ModificarGaleriaPaquete", values);
	}
	
	
	public List eliminarImagenGaleriaPaquete(long codgaleriaImagenPaquete){
		Object[] values={codgaleriaImagenPaquete};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_EliminarImagenGaleriaPaquete",values);
	}
	public boolean isOperationCorrect(List lista)
	{
		Map row=(Map)lista.get(0);
		boolean correcto=row.get("resultado").toString().equals("correcto");
		if(correcto)return true;
		else return false;
	}
}
