package com.android.viewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Messagebox;

import com.android.dao.CAgenciaDAO;
import com.android.model.CAgencia;
import com.pricing.dao.CConfigUrlDAO;
import com.pricing.model.CConfigURL;
import com.pricing.util.ScannUtil;

public class agenciaVM {
	private CAgencia oAgencia;
	private String fecha;
	/***************/
	public CAgencia getoAgencia() {
		return oAgencia;
	}

	public void setoAgencia(CAgencia oAgencia) {
		this.oAgencia = oAgencia;
	}
	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**************/
	@Init
	public void initVM()
	{
		oAgencia=new CAgencia();
		CAgenciaDAO agenciaDao=new CAgenciaDAO();
		agenciaDao.asignarAgencia(agenciaDao.recuperarAgenciaDB());
		setoAgencia(agenciaDao.getoAgencia());
		fecha=oAgencia.getdFechaCreacion().toString();
	}
	@Command
	@NotifyChange({"oAgencia"})
	public void insert_update_Agencia(@BindingParam("componente")Component comp)
	{
		CAgenciaDAO agenciaDao=new CAgenciaDAO();
		boolean correcto=agenciaDao.isOperationCorrect(agenciaDao.insertarAgencia(oAgencia));
		if(correcto)
		{
			oAgencia.setEditable(false);
			Clients.showNotification("Los cambios se guardaron correctamente",Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",3000);
		}else
			Clients.showNotification("Los cambios no se guardaron correctamente",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
	}
	@Command
	@NotifyChange({"fecha"})
	public void detFechaCreacion(@BindingParam("fecha")Date fech)
	{
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		String Fecha=sdf.format(fech);
		System.out.println("fecha es:"+Fecha);
		String dia=Fecha.substring(0,2);
		String mes=Fecha.substring(3,5);
		String anio=Fecha.substring(6,10);
		/*************Fecha Inicio*******************/
		Calendar cal=Calendar.getInstance();
		cal.set(Integer.parseInt(anio),Integer.parseInt(mes)-1,Integer.parseInt(dia));
		oAgencia.setdFechaCreacion(cal.getTime());
		fecha=oAgencia.getdFechaCreacion().toString();
	}
	@Command
	@NotifyChange({"oAgencia"})
	public void Cancelar()
	{
		CAgenciaDAO agenciaDao=new CAgenciaDAO();
		agenciaDao.asignarAgencia(agenciaDao.recuperarAgenciaDB());
		setoAgencia(agenciaDao.getoAgencia());
	}
	@Command
	@NotifyChange({"oAgencia"})
	public void Editar()
	{
		oAgencia.setEditable(true);
	}
}
