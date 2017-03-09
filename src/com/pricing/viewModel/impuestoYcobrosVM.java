package com.pricing.viewModel;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.Clients;

import pe.com.erp.crypto.Encryptar;

import com.pricing.dao.CEtiquetaDAO;
import com.pricing.dao.CImpuestoDAO;
import com.pricing.model.CEtiqueta;
import com.pricing.model.CImpuesto;

public class impuestoYcobrosVM 
{
	private CImpuesto oImpuesto;
	/****************/

	public CImpuesto getoImpuesto() {
		return oImpuesto;
	}

	public void setoImpuesto(CImpuesto oImpuesto) {
		this.oImpuesto = oImpuesto;
	}
	/*****************/
	@Init
	public void initVM()
	{
		oImpuesto=new CImpuesto();
		CImpuestoDAO impuestoDao=new CImpuestoDAO();
		impuestoDao.recuperarImpuestosBD();
		setoImpuesto(impuestoDao.getoImpuesto());
	}
	@Command
	@NotifyChange({"oImpuesto"})
	public void insert_update_impuesto(@BindingParam("componente")Component comp)
	{
		if(!validoParaInsert_Update(oImpuesto,comp))
			return;
		CImpuestoDAO impuestoDao=new CImpuestoDAO();
		boolean correcto=impuestoDao.isOperationCorrect(impuestoDao.insertarImpuesto(oImpuesto));
		if(correcto)
		{
			oImpuesto.setEditable(false);
			Clients.showNotification("Se guardaron los cambios de manera satisfactoria",Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",3000);
		}else
			Clients.showNotification("No se pudieron guardar los cambios efectuados",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
	}
	public boolean validoParaInsert_Update(CImpuesto impuesto,Component comp)
	{
		boolean valido=true;
		if(!impuesto.isModoMinimo() && !impuesto.isModoPorcentual())
		{
			valido=false;
			Clients.showNotification("Debe seleccionar un modo de cobro.",Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",3000);
		}
		return valido;
	}
	@Command
	@NotifyChange({"oImpuesto"})
	public void changeValorCobro(@BindingParam("valor")int valor,@BindingParam("componente")Component comp)
	{
		if(valor==1)
		{
			if(!isNumberDouble(oImpuesto.getImpuestoPaypal()))
			{
				oImpuesto.setImpuestoPaypal("0");
				Clients.showNotification("Digite valores numericos",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
			}
		}else if(valor==2)
		{
			if(!isNumberDouble(oImpuesto.getImpuestoVisa()))
			{
				oImpuesto.setImpuestoVisa("0");
				Clients.showNotification("Digite valores numericos",Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",3000);
			}
		}else if(valor==3)
		{
			if(!isNumberDouble(oImpuesto.getImpuestoMasterCard()))
			{
				oImpuesto.setImpuestoMasterCard("0");
				Clients.showNotification("Digite valores numericos",Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",3000);
			}
		}else if(valor==4)
		{
			if(!isNumberDouble(oImpuesto.getImpuestoDinnersClub()))
			{
				oImpuesto.setImpuestoDinnersClub("0");
				Clients.showNotification("Digite valores numericos",Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",3000);
			}
		}else if(valor==5)
		{
			if(!isNumberDouble(oImpuesto.getPorcentajeCobro()))
			{
				oImpuesto.setPorcentajeCobro("0");
				Clients.showNotification("Digite valores numericos",Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",3000);
			}
		}else if(valor==6)
		{
			if(!isNumberDouble(oImpuesto.getPagoMinimo()))
			{
				oImpuesto.setPagoMinimo("0");
				Clients.showNotification("Digite valores numericos",Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",3000);
			}
		}
	}
	public boolean isNumberDouble(String cad)
	{
		try
		 {
		   Double.parseDouble(cad);
		   return true;
		 }
		 catch(NumberFormatException nfe)
		 {
		   return false;
		 }
	}
	@Command
	@NotifyChange({"oImpuesto"})
	public void selectModoDeCobro(@BindingParam("modo")String modo)
	{
		if(modo.equals("porcentaje"))
		{
			oImpuesto.setModoPorcentual(true);
			oImpuesto.setModoMinimo(false);
		}else
		{
			oImpuesto.setModoPorcentual(false);
			oImpuesto.setModoMinimo(true);
		}
	}
	@Command
	@NotifyChange({"oImpuesto"})
	public void Cancelar()
	{
		CImpuestoDAO impuestoDao=new CImpuestoDAO();
		impuestoDao.recuperarImpuestosBD();
		setoImpuesto(impuestoDao.getoImpuesto());
	}
	@Command
	@NotifyChange({"oImpuesto"})
	public void Editar()
	{
		oImpuesto.setEditable(true);
	}
}
