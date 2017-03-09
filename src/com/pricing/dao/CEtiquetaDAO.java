package com.pricing.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pricing.model.CEtiqueta;
import com.pricing.model.CEtiquetaIdiomas;

public class CEtiquetaDAO extends CConexion
{
	private CEtiquetaIdiomas idioma;
	private ArrayList<CEtiqueta> listaEtiquetas;
	//==================
	public CEtiquetaIdiomas getIdioma() {
		return idioma;
	}
	public void setIdioma(CEtiquetaIdiomas idioma) {
		this.idioma = idioma;
	}
	public ArrayList<CEtiqueta> getListaEtiquetas() {
		return listaEtiquetas;
	}
	public void setListaEtiquetas(ArrayList<CEtiqueta> listaEtiquetas) {
		this.listaEtiquetas = listaEtiquetas;
	}
	//=================
	public CEtiquetaDAO() {
		// TODO Auto-generated constructor stub
		super();
		idioma=new CEtiquetaIdiomas();
	}
	public CEtiquetaDAO(CEtiqueta etiqueta)
	{
		super();
		idioma=new CEtiquetaIdiomas();
	}
	//=====================
	public List recuperarEtiquetasBD()
	{
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarEtiquetas");
	}
	
	public List buscarEtiquetasBD(String nombre){
		String[] values={nombre};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_BuscarEtiquetas",values);
	}
	/**
	 * Este metodo es para asignar a la lista de etiquetas y 
	 * puedan ser modificados por el administrador
	 * @param lista
	 */
	public void asignarListaEtiquetas(List lista)
	{
		listaEtiquetas=new ArrayList<CEtiqueta>();
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaEtiquetas.add(new CEtiqueta((int)row.get("codetiqueta"),
					(String)row.get("cidioma1"),(String)row.get("cidioma2"),
					(String)row.get("cidioma3"), (String)row.get("cidioma4"),
					(String)row.get("cidioma5")));
		}
	}
	public void asignarEtiquetaIdiomas(List lista)
	{
		 String[] idioma1= new String[lista.size()];
		 String[] idioma2= new String[lista.size()];
		 String[] idioma3= new String[lista.size()];
		 String[] idioma4= new String[lista.size()];
		 String[] idioma5= new String[lista.size()];
		 for(int i=0;i<lista.size();i++)
		 {
			 Map row= (Map)lista.get(i) ;
			 idioma1[i]= row.get("cidioma1").toString();
			 idioma2[i]= row.get("cidioma2").toString();
			 idioma3[i]= row.get("cidioma3").toString();
			 idioma4[i]= row.get("cidioma4").toString();
			 idioma5[i]= row.get("cidioma5").toString();
		 }
		 idioma=new CEtiquetaIdiomas(idioma1, idioma2, idioma3, idioma4, idioma5);
	}
	public List agregarNuevaEtiqueta(CEtiqueta etiqueta)
	{
		Object[] value={etiqueta.getcIdioma1(),etiqueta.getcIdioma2(),etiqueta.getcIdioma3(),
				etiqueta.getcIdioma4(),etiqueta.getcIdioma5()};
		return getEjecutorSQL().ejecutarProcedimiento("",value);
	}
	public List modificarEtiqueta(CEtiqueta etiqueta)
	{
		Object[] values={etiqueta.getCodEtiqueta(),etiqueta.getcIdioma1(),
				etiqueta.getcIdioma2(),etiqueta.getcIdioma3(),etiqueta.getcIdioma4(),
				etiqueta.getcIdioma5()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_ModificarEtiqueta", values);
	}
	public boolean isOperationCorrect(List lista)
	{
		Map row=(Map)lista.get(0);
		boolean correcto=row.get("resultado").toString().equals("correcto");
		if(correcto)return true;
		else return false;
	}
}
