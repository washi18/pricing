package com.pricing.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pricing.model.ConfAltoNivel;

public class ConfAltoNivelDAO  extends CConexion{
	//=====atributos====
	private ConfAltoNivel oConfAltoNivel;
	private ArrayList<ConfAltoNivel> listaConfAltoNivel;
	//=====getter an setter====
	
	public ConfAltoNivel getoConfAltoNivel() {
		return oConfAltoNivel;
	}

	public ArrayList<ConfAltoNivel> getListaConfAltoNivel() {
		return listaConfAltoNivel;
	}

	public void setListaConfAltoNivel(ArrayList<ConfAltoNivel> listaConfAltoNivel) {
		this.listaConfAltoNivel = listaConfAltoNivel;
	}

	public void setoConfAltoNivel(ConfAltoNivel oConfAltoNivel) {
		this.oConfAltoNivel = oConfAltoNivel;
	}
	
	//==========constructor=====
	public ConfAltoNivelDAO(){
		super();
	}
	//========metodos====
	public List recuperarconfAltoNivel(String entidad){
		Object[]values={entidad};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarConfAltoNivel",values);
	}
	
	public void asignarListaConfAltoNivel(List lista){
				Map row=(Map)lista.get(0);
				oConfAltoNivel=new ConfAltoNivel((int)row.get("codaltonivel"), (int)row.get("nperfilcod"),
						(String)row.get("cnombreentidad"),(boolean)row.get("bestado"));
	}
	public List modificarConfAltoNivel(ConfAltoNivel config){
		Object[] values={config.getCnombreEntidad(),config.isbEstado()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_ModificarConfAltoNivel",values);
	}
	
	public boolean isOperationCorrect(List lista)
	{
		Map row=(Map)lista.get(0);
		boolean correcto=row.get("resultado").toString().equals("correcto");
		if(correcto)return true;
		else return false;
	}
}
