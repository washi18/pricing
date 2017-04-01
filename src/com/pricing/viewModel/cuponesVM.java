package com.pricing.viewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.Clients;

import com.pricing.dao.CCuponDAO;
import com.pricing.model.CCupon;
import com.pricing.model.CHotel;

public class cuponesVM {
	private CCupon oCupon;
	private CCupon oCuponUpdate;
	private ArrayList<CCupon> listaCupones;
	/*****************/
	public CCupon getoCupon() {
		return oCupon;
	}
	public void setoCupon(CCupon oCupon) {
		this.oCupon = oCupon;
	}
	public ArrayList<CCupon> getListaCupones() {
		return listaCupones;
	}
	public void setListaCupones(ArrayList<CCupon> listaCupones) {
		this.listaCupones = listaCupones;
	}
	/***************/
	@Init
	public void inicializarVM()
	{
		oCupon=new CCupon();
		oCuponUpdate=new CCupon();
		/**Inicializamos la lista de cupones**/
		listaCupones=new ArrayList<CCupon>();
	}
	@GlobalCommand
	public void cargarDatosCupones()
	{
		CCuponDAO cuponDao=new CCuponDAO();
		cuponDao.asignarListaCupones(cuponDao.recuperarCuponesBD());
		setListaCupones(cuponDao.getListaCupones());
		BindUtils.postNotifyChange(null, null, this,"listaCupones");
	}
	@Command
	@NotifyChange({"oCupon","listaCupones"})
	public void insertarCupon(@BindingParam("componente")Component comp)
	{
		if(!validoParaInsertar_update(oCupon,comp,1))
			return;
		CCuponDAO cuponDao=new CCuponDAO();
		boolean correcto=cuponDao.isOperationCorrect(cuponDao.insertarCupon(oCupon));
		if(correcto)
		{	oCupon=new CCupon();
			listaCupones.clear();
			cuponDao.asignarListaCupones(cuponDao.recuperarCuponesBD());
			setListaCupones(cuponDao.getListaCupones());
			Clients.showNotification("El cupon se inserto correctamente",Clients.NOTIFICATION_TYPE_INFO,comp,"before_start",3000);
		}else
			Clients.showNotification("El cupon no se inserto",Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",3000);
	}
	@Command
	@NotifyChange({"listaCupones"})
	public void actualizarCupon(@BindingParam("cupon")CCupon cupon,@BindingParam("componente")Component comp)
	{
		if(!validoParaInsertar_update(cupon,comp,2))
			return;
		CCuponDAO cuponDao=new CCuponDAO();
		boolean correcto=cuponDao.isOperationCorrect(cuponDao.actualizarCupon(cupon));
		if(correcto)
		{
			listaCupones.clear();
			cuponDao.asignarListaCupones(cuponDao.recuperarCuponesBD());
			setListaCupones(cuponDao.getListaCupones());
			Clients.showNotification("El cupon se actualizo correctamente",Clients.NOTIFICATION_TYPE_INFO,comp,"before_start",3000);
		}else
			Clients.showNotification("El cupon no se actualizo (quizas ya ha sido usado)",Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",3000);
	}
	public boolean validoParaInsertar_update(CCupon cupon,Component comp,int opcion)
	{
		//opcion=1 ... insertar
		//opcion=2 ... update
		cupon.setcCupon(cupon.getcCupon().toUpperCase());
		boolean valido=true;
			if(opcion==1 && (cupon.getcCupon().length()<10 || cupon.getcCupon().length()>10))
			{
				valido=false;
				Clients.showNotification("El codigo de cupon debe tener 10 carateres",Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",3000);
			}else if(opcion==1 && existeCupon(cupon.getcCupon()))
			{
				valido=false;
				Clients.showNotification("El codigo de cupon ya existe",Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",3000);
			}else if(cupon.getnPorcentajeDcto()<0 && cupon.getnPorcentajeDcto()>100)
			{
				valido=false;
				Clients.showNotification("El descuento no puede ser >100 ni <0",Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",3000);
			}else if(cupon.getdFechaInicio()==null || cupon.getdFechaFin()==null)
			{
				valido=false;
				Clients.showNotification("Debe seleccionar las fechas inicio y fin",Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",3000);
			}else if(!validoFechas(cupon))
			{
				valido=false;
				Clients.showNotification("La fecha fin < fecha de inicio",Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",3000);
			}
		return valido;
	}
	public boolean existeCupon(String cupon)
	{
		CCuponDAO cuponDao=new CCuponDAO();
		List lista=cuponDao.recuperarCuponExistenteBD(cupon);
		if(lista.isEmpty())
			return false;
		else
			return true;
	}
	public boolean validoFechas(CCupon oCupon)
	{
		boolean valido=true;
			/**Inicializamos los objetos Calendar pa fecha inicio y fin**/
			/**Inicio**/
			Calendar calIni=Calendar.getInstance();
			calIni.setTime(oCupon.getdFechaInicio());
			/**Fin**/
			Calendar calFin=Calendar.getInstance();
			calFin.setTime(oCupon.getdFechaFin());
			/**Finalmente comparamos las fechas**/
			if(calFin.before(calIni))//Si la fecha fin es menor que la fecha inicio
				valido=false;
		return valido;
	}
	@Command
	public void recuperarFechas(@BindingParam("fecha")Date fecha,@BindingParam("cupon")CCupon cupon,@BindingParam("opcion")int opcion)
	{
		System.out.println("La fecha es: "+fecha.toString());
		if(opcion==1)//Se selecciono la fecha de inicio
		{
			cupon.setdFechaInicio(fecha);
		}else//se selecciono la fecha fin
		{
			cupon.setdFechaFin(fecha);
		}
	}
	@Command
	public void Editar(@BindingParam("cupon")CCupon cupon)
	{
		oCuponUpdate.setEditable(false);
		refrescaFilaTemplate(oCuponUpdate);
		oCuponUpdate=cupon;
		//le damos estado editable
		cupon.setEditable(true);	
		//lcs.setEditingStatus(!lcs.getEditingStatus());
		refrescaFilaTemplate(cupon);
	}
	public void refrescaFilaTemplate(CCupon cupon)
	{
		BindUtils.postNotifyChange(null, null, cupon, "editable");
	}
}
