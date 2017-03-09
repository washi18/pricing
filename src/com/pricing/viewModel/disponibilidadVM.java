package com.pricing.viewModel;

import java.util.Date;
import java.util.Calendar;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpSession;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.A;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;

import com.pricing.dao.CDisponibilidadYourselfDAO;
import com.pricing.dao.CEtiquetaDAO;
import com.pricing.extras.lectorPDF;
import com.pricing.model.CDia;
import com.pricing.model.CDias7;
import com.pricing.util.Util;

public class disponibilidadVM 
{
	@Wire
	Combobox cbMes,cbAnio;
//	@Wire
//	Label lblUpdateDate;
	@Wire
	Comboitem cbAnioActual,cbAnioSig;
	//=======================================
	private ArrayList<CDias7> listaDias;
	private ArrayList<CDia> listaAnioActual;
	private ArrayList<CDia> listaAnioSig;
	private CDias7 dias7;
	private CDia antDia;
	private CDias7 antDias7;
	private CDia antDiaAlt;
	private CDias7 antDias7Alt;
	private ArrayList<String> listaUpdate;
	private ArrayList<String[]> listaFechasSeleccionadas;
	private String mesRecuperado;
	private int cdisponibilidad;
	/***************************/
	private CEtiquetaDAO etiquetaDao;
	private String[] etiqueta;
	private String language;
	/***************************/
	Session ses;
	HttpSession httpses;
	//=======================================
	public ArrayList<String[]> getListaFechasSeleccionadas() {
		return listaFechasSeleccionadas;
	}
	public void setListaFechasSeleccionadas(
			ArrayList<String[]> listaFechasSeleccionadas) {
		this.listaFechasSeleccionadas = listaFechasSeleccionadas;
	}
	public String getMesRecuperado() {
		return mesRecuperado;
	}
	public void setMesRecuperado(String mesRecuperado) {
		this.mesRecuperado = mesRecuperado;
	}
	public String[] getEtiqueta() {
		return etiqueta;
	}
	public void setEtiqueta(String[] etiqueta) {
		this.etiqueta = etiqueta;
	}
	public ArrayList<CDias7> getListaDias() {
		return listaDias;
	}
	public void setListaDias(ArrayList<CDias7> listaDias) {
		this.listaDias = listaDias;
	}
	public CDias7 getDias7() {
		return dias7;
	}
	public void setDias7(CDias7 dias7) {
		this.dias7 = dias7;
	}
	public int getCdisponibilidad() {
		return cdisponibilidad;
	}
	public void setCdisponibilidad(int cdisponibilidad) {
		this.cdisponibilidad = cdisponibilidad;
	}
	//=======================================
	@Init
	public void inicializarVM() throws IOException
	{
		etiquetaDao=new CEtiquetaDAO();
		etiquetaDao.asignarEtiquetaIdiomas(etiquetaDao.recuperarEtiquetasBD());
//		iniciarEtiquetas();
		etiqueta=(String[]) Sessions.getCurrent().getAttribute("etiqueta");
		/*******************/
		dias7=new CDias7();
		listaDias=new ArrayList<CDias7>();
		ses=Sessions.getCurrent();
		httpses=(HttpSession)Sessions.getCurrent().getNativeSession();
		ses.setAttribute("fechaPrioridad",1);
		ses.setAttribute("cantidadFechas",0);
		httpses.setAttribute("antDia", antDia);
		httpses.setAttribute("mes","");
		httpses.setAttribute("anio","");
		httpses.setAttribute("antDiaAlt", antDiaAlt);
		httpses.setAttribute("mesAlt","");
		httpses.setAttribute("anioAlt","");
		cdisponibilidad=Integer.valueOf(httpses.getAttribute("codDisponibilidad").toString());
		System.out.println("valor de dispo final->"+cdisponibilidad);
		//Inicializamos los dias y el numero de disponibilidades
		iniDiasYNumDisp();
		//===========================
		iniciarLosDiasAnio();
		//========================
		iniciarFechasSeleccionadas();
	}
	@GlobalCommand
	@NotifyChange("etiqueta")
	public void cambiarIdiomaDispo(@BindingParam("idioma")Object idioma)
	{
		if(idioma.toString().equals("1"))
			etiqueta=etiquetaDao.getIdioma().getIdioma1();
		else if(idioma.toString().equals("3"))
			etiqueta=etiquetaDao.getIdioma().getIdioma3();
		else
			etiqueta=etiquetaDao.getIdioma().getIdioma2();
	}
	public void iniciarLosDiasAnio() throws IOException
	{
		listaAnioActual=new ArrayList<CDia>();
		listaAnioSig=new ArrayList<CDia>();
		listaUpdate=new ArrayList<String>();
		Calendar cal=Calendar.getInstance();
		int nroDia=1;
		for(int i=0;i<365;i++)
		{
			CDia dia=new CDia();
			if(i==31 || i==59 || i==90 || i==120 || 
					i==151 || i==181 || i==212 || i==243 || i==273 || i==304 || i==334)
				nroDia=1;
			dia.setcNroDia(""+nroDia);
			listaAnioActual.add(dia);
			CDia dia1=new CDia();
			dia1.setcNroDia(""+nroDia);
//			dia1.setCantDisp("500");
//			dia1.setDisponible("/img/dispon/ok.png");
//			dia1.setDisponible("icon-checkmark");
			dia1.setColorDisp("chek_style");
//			dia1.setVisible(true);
			listaAnioSig.add(dia1);
			nroDia++;
		}
		int n=0;
		for(int i=0;i<12;i++)
		{
			System.out.println("pos:"+n);
			CDisponibilidadYourselfDAO dispoysDao=new CDisponibilidadYourselfDAO();
			dispoysDao.asignarDisponibilidadMes(dispoysDao.recuperarDispoMes(cal.get(Calendar.YEAR), i+1,cdisponibilidad));
			ArrayList<Integer> listDispoMesActual=new ArrayList<Integer>();
			listDispoMesActual=dispoysDao.getListaDispoMes();
			//if(i==1)continue;//mes de febrero
			String mes=mesAnio(i);
		    //Una vez obtenida las disponibilidades se almacena en la listaAnioActual
		    for(int r=0;r<listDispoMesActual.size();r++)
		    {
			    listaAnioActual.get(n).setCantDisp(String.valueOf(listDispoMesActual.get(r)));
			    listaAnioActual.get(n).setVisible(true);
			    if(listDispoMesActual.get(r)!=0)
			    {
			    	listaAnioActual.get(n).setDisponible("icon-checkmark");
			    	listaAnioActual.get(n).setColorDisp("chek_style");
			    	listaAnioActual.get(n).setImgPrioridad("background:rgba(25, 206, 61,0.3);border-radius:5px;");
			    }
//			    	listaAnioActual.get(n).setDisponible("/img/dispon/ok.png");
			    else
			    {
			    	listaAnioActual.get(n).setDisponible("icon-cross");
			    	listaAnioActual.get(n).setColorDisp("cross_style");
			    	listaAnioActual.get(n).setImgPrioridad("background:rgba(247, 87, 65,0.3);border-radius:5px;");
			    }
//			    	listaAnioActual.get(n).setDisponible("/img/dispon/x5.png");
			    n++;
		    }
		}
		n=0;
		for(int j=0;j<12;j++)
		{
			System.out.println("pos:"+n);
			CDisponibilidadYourselfDAO dispoysDao=new CDisponibilidadYourselfDAO();
			dispoysDao.asignarDisponibilidadMes(dispoysDao.recuperarDispoMes(cal.get(Calendar.YEAR)+1, j+1,cdisponibilidad));
			ArrayList<Integer> listDispoMesSig=new ArrayList<Integer>();
			listDispoMesSig=dispoysDao.getListaDispoMes();
			//if(j==1)continue;//mes de febrero
		    //Una vez obtenida las disponibilidades se almacena en la listaAnioActual
		    for(int t=0;t<listDispoMesSig.size();t++)
		    {
			    listaAnioSig.get(n).setCantDisp(String.valueOf(listDispoMesSig.get(t)));
			    listaAnioSig.get(n).setVisible(true);
			    if(listDispoMesSig.get(t)!=0)
			    {
			    	listaAnioSig.get(n).setDisponible("icon-checkmark");
			    	listaAnioSig.get(n).setColorDisp("chek_style");
			    	listaAnioSig.get(n).setImgPrioridad("background:rgba(25, 206, 61,0.3);border-radius:5px;");
			    }
//			    	listaAnioActual.get(n).setDisponible("/img/dispon/ok.png");
			    else
			    {
			    	listaAnioSig.get(n).setDisponible("icon-cross");
			    	listaAnioSig.get(n).setColorDisp("cross_style");
			    	listaAnioSig.get(n).setImgPrioridad("background:rgba(247, 87, 65,0.3);border-radius:5px;");
			    }
//			    	listaAnioActual.get(n).setDisponible("/img/dispon/x5.png");
			    n++;
		    }
		}
	}
	public void iniciarFechasSeleccionadas()
	{
		listaFechasSeleccionadas=new ArrayList<String[]>();
		for(int i=0;i<5;i++)
		{
			String[] fecha=new String[3];
			fecha[0]="";//dia
			fecha[1]="";//mes
			fecha[2]="";//año
			listaFechasSeleccionadas.add(fecha);
		}
	}
	public boolean selectMesValido(String mes,CDia oDia,String annio)
	{
		/*************Fecha Inicio*******************/
		Calendar calIni=Calendar.getInstance();
		calIni.set(Integer.parseInt(annio),nMesAnio(mes)-1,Integer.parseInt(oDia.getcNroDia()));
		/********Fecha Actual*******/
		Calendar calActual=Calendar.getInstance();
		/*****Comparando******/
		if(calIni.before(calActual))
			return false;
		else return true;
	}
	/**
	 * -Si hacemos click en un dia del calendario
	 * en el que la disponibilidad != 0 se debe de marcar
	 * la fecha con un color dependiendo de si la fecha es
	 * principal(1) o alterna(2)
	 * -Se puede elegir 1 fecha principal y max 4 fechas alternas
	 * @param fila: La fila al que pertenece el dia elegido
	 * @param dia: El dia elegido del mes
	 * @param cantDisp: La cantidad disponible en este dia
	 */
	@Command
	public void onDia(@BindingParam("dias")CDias7 dias7,@BindingParam("dia")CDia dia,@BindingParam("valueMes")String mes,@BindingParam("componente")Component comp)
	{
		if(mes==null)
			mes=mesRecuperado;
//		System.out.println("mes-> "+mes.toString());
		System.out.println("dia-> "+dia);
		System.out.println("Año-> "+cbAnio.getValue());
		System.out.println("Soy fecha principal: "+mes.toString()+" -- "+dia+" -- "+cbAnio.getValue());
		if(dia.getCantDisp().equals("0"))return;
		if(!selectMesValido(mes,dia,cbAnio.getValue()))
		{
			System.out.println("El dia del mes no es valido");
			Clients.showNotification(etiqueta[221], Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
			return;
		}
		System.out.println("jajaja.. entre y no se porque");
		CDia auxDia=(CDia)httpses.getAttribute("antDiaAlt");
		String auxMes=(String)httpses.getAttribute("mesAlt");
		String auxAnio=(String)httpses.getAttribute("anioAlt");
		listaFechasSeleccionadas.get(0)[0]="";
		listaFechasSeleccionadas.get(0)[1]="";
		listaFechasSeleccionadas.get(0)[2]="";
		if(auxDia!=null && auxMes!=null && auxAnio!=null)
		{
			if(dia.getcNroDia().equals(auxDia.getcNroDia())&& auxMes.equals(mes.toString()) && auxAnio.equals(cbAnio.getValue()))
			{
				System.out.println("Somos iguales");
				Clients.showNotification(etiqueta[220], Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",3000);
				return;
			}
		}
		if(antDia!=null)
		{
			antDia.setDescDiaElegido("");
			antDia.setImgPrioridad("background:rgba(25, 206, 61,0.3);border-radius:5px;");
			antDia.setPrioridad(0);
			antDia.setElegido(false);
			refrescarDia(antDias7,antDia);

			//Se quita la fecha principal
			eliminarFechaSeleccionada(antDia,1);
		}
		antDia=dia;
		antDias7=dias7;
		httpses.setAttribute("antDia", dia);
		httpses.setAttribute("mes", mes.toString());
		httpses.setAttribute("anio", cbAnio.getValue());
		/*********/
		String imagen="background:#6E96E2;border-radius:5px;border:1px solid black;";
		String Descripcion=etiqueta[44];
		dia.setElegido(true);
		dia.setDescDiaElegido(Descripcion);
		dia.setImgPrioridad(imagen);
		dia.setPrioridad(1);
		
		refrescarDia(dias7,dia);
		adicionarFechaSeleccionada(dia,1);
	}
	@Command
	public void onDiaAlterno(@BindingParam("dias")CDias7 dias7,@BindingParam("dia")CDia dia,@BindingParam("valueMes")String mes,@BindingParam("componente")Component comp)
	{
		if(mes==null)
			mes=mesRecuperado;
		System.out.println("Soy fecha alterna: "+mes.toString()+" -- "+dia+" -- "+cbAnio.getValue());
		if(dia.getCantDisp().equals("0"))return;
		if(!selectMesValido(mes,dia,cbAnio.getValue()))
		{
			Clients.showNotification(etiqueta[221], Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
			return;
		}
		CDia auxDia=(CDia)httpses.getAttribute("antDia");
		String auxMes=(String)httpses.getAttribute("mes");
		String auxAnio=(String)httpses.getAttribute("anio");
		listaFechasSeleccionadas.get(1)[0]="";
		listaFechasSeleccionadas.get(1)[1]="";
		listaFechasSeleccionadas.get(1)[2]="";
		if(auxDia!=null && auxMes!=null && auxAnio!=null)
		{
			if(dia.getcNroDia().equals(auxDia.getcNroDia())&& auxMes.equals(mes.toString()) && auxAnio.equals(cbAnio.getValue()))
			{
				System.out.println("Somos iguales");
				Clients.showNotification(etiqueta[219], Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",3000);
				return;
			}
		}
		System.out.println("No Somos iguales");
		if(antDiaAlt!=null)
		{
			antDiaAlt.setDescDiaElegido("");
			antDiaAlt.setImgPrioridad("background:rgba(25, 206, 61,0.3);border-radius:5px;");
			antDiaAlt.setPrioridad(0);
			antDiaAlt.setElegido(false);
			refrescarDia(antDias7Alt,antDiaAlt);
			//Se quita la fecha principal
			eliminarFechaSeleccionada(antDiaAlt,2);
		}
		antDiaAlt=dia;
		antDias7Alt=dias7;
		httpses.setAttribute("antDiaAlt", dia);
		httpses.setAttribute("mesAlt", mes.toString());
		httpses.setAttribute("anioAlt", cbAnio.getValue());
		/*********/
		String imagen="background:#C7D542;border-radius:5px;border:1px solid black;";
		String Descripcion=etiqueta[44];
		dia.setElegido(true);
		dia.setDescDiaElegido(Descripcion);
		dia.setImgPrioridad(imagen);
		dia.setPrioridad(2);
		
		refrescarDia(dias7,dia);
		adicionarFechaSeleccionada(dia,2);
	}

//	@Command
//	public void onDia(@BindingParam("dias")CDias7 dias7,@BindingParam("dia")CDia dia,@BindingParam("valueMes")Object mes)
//	{
//		if(!selectMesValido(mes.toString(),dia,cbAnio.getValue()))
//			return;
//		int n=(int)ses.getAttribute("cantidadFechas");
//		//n<5 -> 1 fecha principal y 4 alternativas
//		if(Integer.parseInt(dia.getCantDisp())!=0 && n<5)
//		{
//			int p=(int)ses.getAttribute("fechaPrioridad");
//			String imagen="";
//			String Descripcion="";
//			if(p==1)
//			{
////				imagen="/img/dispon/blue.jpg";
//				imagen="background:#6E96E2;border-radius:5px;border:1px solid black;";
//				Descripcion=etiqueta[44];
//			}
////			else
////			{
////				Descripcion=etiqueta[45];
//////				imagen="/img/dispon/green.png";
////				imagen="background:#C7D542;border-radius:5px;border:1px solid black;";
////			}
//					if(!dia.isElegido())
//					{
////						if(p==1)
////							ses.setAttribute("fechaPrioridad",2);
//						ses.setAttribute("fechaPrioridad",1);
//						dia.setElegido(true);
//						dia.setDescDiaElegido(Descripcion);
//						dia.setImgPrioridad(imagen);
//						dia.setPrioridad(p);
//						
//						refrescarDia(dias7,dia);
//						//Se adiciona la fecha
//						adicionarFechaSeleccionada(dia,p);
////						n++;
//					}
//					else
//					{
//						if(dia.getPrioridad()==1)
//						{
//							ses.setAttribute("fechaPrioridad",1);
//							dia.setElegido(false);
//							dia.setDescDiaElegido("");
//							dia.setImgPrioridad("background:rgba(25, 206, 61,0.3);border-radius:5px;");
//							dia.setPrioridad(0);
//							
//							refrescarDia(dias7,dia);
//							//Se quita la fecha principal
//							eliminarFechaSeleccionada(dia,1);
//							n--;
//						}
////						else
////						{
////							dia.setElegido(false);
////							dia.setDescDiaElegido("");
////							dia.setImgPrioridad("background:rgba(25, 206, 61,0.3);border-radius:5px;");
////							dia.setPrioridad(0);
////							
////							refrescarDia(dias7,dia);
////							//Se quita la fecha alterna
////							eliminarFechaSeleccionada(dia,2);
////							n--;
////						}
//					}
//			ses.setAttribute("cantidadFechas",n);
//		}
//		else
//		{
//			if(n>=5 && Integer.parseInt(dia.getCantDisp())!=0)
//			{
//				for(int i=0;i<7;i++)
//				{
//					if(dia.isElegido())
//					{
//						if(dia.getPrioridad()==1)
//						{
//							ses.setAttribute("fechaPrioridad",1);
//							dia.setElegido(false);
//							dia.setDescDiaElegido("");
//							dia.setImgPrioridad("background:rgba(25, 206, 61,0.3);border-radius:5px;");
//							dia.setPrioridad(0);
//							
//							refrescarDia(dias7,dia);
//							//Se quita la fecha principal
//							eliminarFechaSeleccionada(dia,1);
//							n--;
//						}
//						else
//						{
//							dia.setElegido(false);
//							dia.setDescDiaElegido("");
//							dia.setImgPrioridad("background:rgba(25, 206, 61,0.3);border-radius:5px;");
//							dia.setPrioridad(0);
//							
//							refrescarDia(dias7,dia);
//							//Se quita la fecha alterna
//							eliminarFechaSeleccionada(dia,2);
//							n--;
//						}
//					}
//				}
//				ses.setAttribute("cantidadFechas",n);
//			}
//		}
////		for(int i=0;i<listaFechasSeleccionadas.size();i++)
////			System.out.println("Fechas:"+listaFechasSeleccionadas.get(i)[0]+","+listaFechasSeleccionadas.get(i)[1]+","+listaFechasSeleccionadas.get(i)[2]);
////		System.out.println("soy la fila ->"+fila);
//	}
	public void refrescarDia(CDias7 dias7,CDia dia)
	{
		if(dia.getNro()==1)
			BindUtils.postNotifyChange(null, null, dias7,"dia_1");
		else if(dia.getNro()==2)
			BindUtils.postNotifyChange(null, null, dias7,"dia_2");
		else if(dia.getNro()==3)
			BindUtils.postNotifyChange(null, null, dias7,"dia_3");
		else if(dia.getNro()==4)
			BindUtils.postNotifyChange(null, null, dias7,"dia_4");
		else if(dia.getNro()==5)
			BindUtils.postNotifyChange(null, null, dias7,"dia_5");
		else if(dia.getNro()==6)
			BindUtils.postNotifyChange(null, null, dias7,"dia_6");
		else if(dia.getNro()==7)
			BindUtils.postNotifyChange(null, null, dias7,"dia_7");
	}
	public void adicionarFechaSeleccionada(CDia dia,int prioridad)
	{
		if(prioridad==1)
		{
			//Se agrega la fecha principal
			listaFechasSeleccionadas.get(0)[0]=dia.getcNroDia();
			listaFechasSeleccionadas.get(0)[1]=mesRecuperado;
			listaFechasSeleccionadas.get(0)[2]=cbAnio.getValue();
		}
		else
		{
			listaFechasSeleccionadas.get(1)[0]=dia.getcNroDia();
			listaFechasSeleccionadas.get(1)[1]=mesRecuperado;
			listaFechasSeleccionadas.get(1)[2]=cbAnio.getValue();
//			for(int i=1;i<5;i++)
//			{
//				if(listaFechasSeleccionadas.get(i)[0].equals(""))
//				{
//					listaFechasSeleccionadas.get(i)[0]=dia.getcNroDia();
//					listaFechasSeleccionadas.get(i)[1]=mesRecuperado;
//					listaFechasSeleccionadas.get(i)[2]=cbAnio.getValue();
//					break;
//				}
//			}
		}
		//-----------------------------
		BindUtils.postNotifyChange(null, null, this,"listaFechasSeleccionadas");
	}
	public void eliminarFechaSeleccionada(CDia dia,int prioridad)
	{
		if(prioridad==1)
		{
			//Se quita la fechaprincipal
			listaFechasSeleccionadas.get(0)[0]="";
			listaFechasSeleccionadas.get(0)[1]="";
			listaFechasSeleccionadas.get(0)[2]="";
		}
		else
		{
			listaFechasSeleccionadas.get(1)[0]="";
			listaFechasSeleccionadas.get(1)[1]="";
			listaFechasSeleccionadas.get(1)[2]="";
//			for(int i=1;i<5;i++)
//			{
//				if(listaFechasSeleccionadas.get(i)[0].equals(dia.getcNroDia()))
//				{
//					listaFechasSeleccionadas.get(i)[0]="";
//					listaFechasSeleccionadas.get(i)[1]="";
//					listaFechasSeleccionadas.get(i)[2]="";
//					break;
//				}
//			}
		}
		//-----------------------------
		BindUtils.postNotifyChange(null, null, this,"listaFechasSeleccionadas");
	}
	/**
	 * Obtiene el nombre del primer dia del mes en el año correspondiente
	 * @param anio
	 * @param mes
	 * @return
	 */
	public String obtenerPrimerDiaMes(int anio,int mes)
	{
		Calendar cal=Calendar.getInstance();
		cal.set(anio, mes-1,1);
		return cal.getTime().toString().substring(0, 3);
	}
	/**
	 * Obtiene el nro de dias del mes del anio correspondiente
	 * @param anio
	 * @param mes
	 * @return
	 */
	public int obtenerNroDiasMes(int anio,int mes)
	{
		Calendar cal=Calendar.getInstance();
		cal.set(anio,mes-1,1);
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	/**
	 * Inicializa el grid con los dias y numero de disponibilidades vacios
	 */
	public void iniDiasYNumDisp()
	{
		listaDias=new ArrayList<CDias7>();
		for(int i=0;i<6;i++)
		{
			dias7=new CDias7();
			listaDias.add(dias7);
		}
		BindUtils.postNotifyChange(null, null, this,"listaDias");
	}
	public Date sumarRestarDiasFecha(Date fecha, int dias)
	{
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(fecha); // Configuramos la fecha que se recibe
		 calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
		 return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
	}
	/**
	 * Retorna el nro de dia a partir de su nombre
	 * @param dia
	 * @return
	 */
	public int diaSemana(String dia)
	{
		int d=-1;
		switch(dia)
		{
			case "Mon":d=1;break;
			case "Tue":d=2;break;
			case "Wed":d=3;break;
			case "Thu":d=4;break;
			case "Fri":d=5;break;
			case "Sat":d=6;break;
			case "Sun":d=7;break;
		}
		return d;
	}
	/**
	 * La primera forma de como se mostrara la disponibilidad
	 * @throws WrongValueException
	 * @throws IOException
	 */
	@NotifyChange({"dias","numDisp"})
	public void iniDisponibilidades() throws WrongValueException, IOException
	{
		Calendar calIni=Calendar.getInstance();
		cbAnio.setValue(Integer.toString(calIni.get(Calendar.YEAR)));
		//----------------------------
		cbAnioActual.setLabel(Integer.toString(calIni.get(Calendar.YEAR)));
		cbAnioSig.setLabel(Integer.toString(calIni.get(Calendar.YEAR)+1));
		//----------------------------
		if((calIni.get(Calendar.MONTH)+1)==2)//Febrero
		{
			cbMes.setValue(mesAnioIdioma(calIni.get(Calendar.MONTH)+1));
			mesRecuperado=mesAnio(calIni.get(Calendar.MONTH)+1);
			//===================================
			mostrarCalendarDispAnioActual(cbAnio.getValue(),mesRecuperado);
		}else{
			cbMes.setValue(mesAnioIdioma(calIni.get(Calendar.MONTH)));
			mesRecuperado=mesAnio(calIni.get(Calendar.MONTH));
			//===================================
			mostrarCalendarDispAnioActual(cbAnio.getValue(),mesRecuperado);
		}
	}
	public int obtenerPosInicioMes(int mes)
	{
		int pos=-1;
		switch(mes)
		{
			case 1:pos=0;break;
			case 2:
			case 3:pos=31;break;
			case 4:pos=62;break;
			case 5:pos=92;break;
			case 6:pos=123;break;
			case 7:pos=153;break;
			case 8:pos=184;break;
			case 9:pos=215;break;
			case 10:pos=245;break;
			case 11:pos=276;break;
			case 12:pos=306;break;
		}
		return pos;
	}
	public int obtenerPosInicioMesOtro(int anio,int mes){
		int pos=-1;
		if((anio % 4==0) && ((anio % 100 !=0)||(anio%400==0))){
			switch(mes){
				case 1:pos=0;break;
				case 2:pos=31;break;
				case 3:pos=60;break;
				case 4:pos=91;break;
				case 5:pos=121;break;
				case 6:pos=152;break;
				case 7:pos=182;break;
				case 8:pos=213;break;
				case 9:pos=244;break;
				case 10:pos=274;break;
				case 11:pos=305;break;
				case 12:pos=335;break;
			}
		}
		else{
			switch(mes){
			case 1:pos=0;break;
			case 2:pos=31;break;
			case 3:pos=59;break;
			case 4:pos=90;break;
			case 5:pos=120;break;
			case 6:pos=151;break;
			case 7:pos=181;break;
			case 8:pos=212;break;
			case 9:pos=243;break;
			case 10:pos=273;break;
			case 11:pos=304;break;
			case 12:pos=334;break;
		}
		}
		return pos;
	}
	public int obtenerPosFinMesOtro(int anio,int mes)
	{
		int pos=-1;
		if((anio % 4==0)&& ((anio % 100 !=0)||(anio%400==0)) ){
			switch(mes)
			{
				case 1:pos=30;break;//31
				case 2:pos=59;break;//29
				case 3:pos=90;break;//31
				case 4:pos=120;break;//30
				case 5:pos=151;break;//31
				case 6:pos=181;break;//30
				case 7:pos=212;break;//31
				case 8:pos=243;break;//31
				case 9:pos=273;break;//30
				case 10:pos=304;break;//31
				case 11:pos=334;break;//30
				case 12:pos=365;break;//31
				
			}
		}else{
			switch(mes)
			{
				case 1:pos=30;break;//31
				case 2:pos=58;break;//28
				case 3:pos=89;break;//31
				case 4:pos=119;break;//30
				case 5:pos=150;break;//31
				case 6:pos=180;break;//30
				case 7:pos=211;break;//31
				case 8:pos=242;break;//31
				case 9:pos=272;break;//30
				case 10:pos=303;break;//31
				case 11:pos=333;break;//30
				case 12:pos=364;break;//31
			}
		}
		return pos;
	}
	public int obtenerPosFinMes(int mes)
	{
		int pos=-1;
		switch(mes)
		{
			case 1:pos=30;break;
			case 2:
			case 3:pos=61;break;
			case 4:pos=91;break;
			case 5:pos=122;break;
			case 6:pos=152;break;
			case 7:pos=183;break;
			case 8:pos=214;break;
			case 9:pos=244;break;
			case 10:pos=275;break;
			case 11:pos=305;break;
			case 12:pos=336;break;
		}
		return pos;
	}
	/**
	 * -Asigna los dias en su posicion correspondiente en el grid
	 * -Lee el pdf de disponibiidades del mes y año correspondiente
	 *  y asigna a numDisp el nro de disponibilidades
	 * -Todos los datos obtenidos son del año actual
	 * @param anio
	 * @param mes
	 * @throws IOException
	 */
	public void mostrarCalendarDispAnioActual(String anio,String mes) throws IOException
	{
		//Inicializamos los dias y nro disponibilidades en el grid todos en vacio
		iniDiasYNumDisp();
		//==================
		int m=nMesAnio(mes);
		int a=Integer.parseInt(anio);
		//Obtenemos el primer dia del mes seleccionado
		String primerDiaMes=obtenerPrimerDiaMes(a,m);
		int nroDiaSemana=diaSemana(primerDiaMes);
	    //Se escriben los datos en el grid a partir del primer dia obtenido anteriormente
	    int posInicioMes=obtenerPosInicioMesOtro(a,m);
	    int posFinMes=obtenerPosFinMesOtro(a,m);
	    int k=posInicioMes;
	    for(int i=0;i<listaDias.size();i++)
	    {
	    	System.out.println("posAdvance->"+k);
	    	if(k>posFinMes)break;
	    	if(i==0)
	    	{
	    		if(nroDiaSemana==1)
	    		{
	    			listaAnioActual.get(k).setNro(1);
	    			listaDias.get(i).setDia_1(listaAnioActual.get(k++));
	    			listaAnioActual.get(k).setNro(2);
	    			listaDias.get(i).setDia_2(listaAnioActual.get(k++));
	    			listaAnioActual.get(k).setNro(3);
	    			listaDias.get(i).setDia_3(listaAnioActual.get(k++));
	    			listaAnioActual.get(k).setNro(4);
	    			listaDias.get(i).setDia_4(listaAnioActual.get(k++));
	    			listaAnioActual.get(k).setNro(5);
	    			listaDias.get(i).setDia_5(listaAnioActual.get(k++));
	    			listaAnioActual.get(k).setNro(6);
	    			listaDias.get(i).setDia_6(listaAnioActual.get(k++));
	    			listaAnioActual.get(k).setNro(7);
	    			listaDias.get(i).setDia_7(listaAnioActual.get(k++));
	    		}
	    		else if(nroDiaSemana==2)
	    		{
	    			listaAnioActual.get(k).setNro(2);
	    			listaDias.get(i).setDia_2(listaAnioActual.get(k++));
	    			listaAnioActual.get(k).setNro(3);
	    			listaDias.get(i).setDia_3(listaAnioActual.get(k++));
	    			listaAnioActual.get(k).setNro(4);
	    			listaDias.get(i).setDia_4(listaAnioActual.get(k++));
	    			listaAnioActual.get(k).setNro(5);
	    			listaDias.get(i).setDia_5(listaAnioActual.get(k++));
	    			listaAnioActual.get(k).setNro(6);
	    			listaDias.get(i).setDia_6(listaAnioActual.get(k++));
	    			listaAnioActual.get(k).setNro(7);
	    			listaDias.get(i).setDia_7(listaAnioActual.get(k++));
	    		}
	    		else if(nroDiaSemana==3)
	    		{
	    			listaAnioActual.get(k).setNro(3);
	    			listaDias.get(i).setDia_3(listaAnioActual.get(k++));
	    			listaAnioActual.get(k).setNro(4);
	    			listaDias.get(i).setDia_4(listaAnioActual.get(k++));
	    			listaAnioActual.get(k).setNro(5);
	    			listaDias.get(i).setDia_5(listaAnioActual.get(k++));
	    			listaAnioActual.get(k).setNro(6);
	    			listaDias.get(i).setDia_6(listaAnioActual.get(k++));
	    			listaAnioActual.get(k).setNro(7);
	    			listaDias.get(i).setDia_7(listaAnioActual.get(k++));
	    		}
	    		else if(nroDiaSemana==4)
	    		{
	    			listaAnioActual.get(k).setNro(4);
	    			listaDias.get(i).setDia_4(listaAnioActual.get(k++));
	    			listaAnioActual.get(k).setNro(5);
	    			listaDias.get(i).setDia_5(listaAnioActual.get(k++));
	    			listaAnioActual.get(k).setNro(6);
	    			listaDias.get(i).setDia_6(listaAnioActual.get(k++));
	    			listaAnioActual.get(k).setNro(7);
	    			listaDias.get(i).setDia_7(listaAnioActual.get(k++));
	    		}
	    		else if(nroDiaSemana==5)
	    		{
	    			listaAnioActual.get(k).setNro(5);
	    			listaDias.get(i).setDia_5(listaAnioActual.get(k++));
	    			listaAnioActual.get(k).setNro(6);
	    			listaDias.get(i).setDia_6(listaAnioActual.get(k++));
	    			listaAnioActual.get(k).setNro(7);
	    			listaDias.get(i).setDia_7(listaAnioActual.get(k++));
	    		}
	    		else if(nroDiaSemana==6)
	    		{
	    			listaAnioActual.get(k).setNro(6);
	    			listaDias.get(i).setDia_6(listaAnioActual.get(k++));
	    			listaAnioActual.get(k).setNro(7);
	    			listaDias.get(i).setDia_7(listaAnioActual.get(k++));
	    		}
	    		else if(nroDiaSemana==7)
	    		{
	    			listaAnioActual.get(k).setNro(7);
	    			listaDias.get(i).setDia_7(listaAnioActual.get(k++));
	    		}
	    	}
	    	else
	    	{
	    		if(k>posFinMes)break;
	    		listaAnioActual.get(k).setNro(1);
	    		listaDias.get(i).setDia_1(listaAnioActual.get(k++));
	    		if(k>posFinMes)break;
	    		listaAnioActual.get(k).setNro(2);
	    		listaDias.get(i).setDia_2(listaAnioActual.get(k++));
	    		if(k>posFinMes)break;
	    		listaAnioActual.get(k).setNro(3);
	    		listaDias.get(i).setDia_3(listaAnioActual.get(k++));
	    		if(k>posFinMes)break;
	    		listaAnioActual.get(k).setNro(4);
	    		listaDias.get(i).setDia_4(listaAnioActual.get(k++));
	    		if(k>posFinMes)break;
	    		listaAnioActual.get(k).setNro(5);
	    		listaDias.get(i).setDia_5(listaAnioActual.get(k++));
	    		if(k>posFinMes)break;
	    		listaAnioActual.get(k).setNro(6);
	    		listaDias.get(i).setDia_6(listaAnioActual.get(k++));
	    		if(k>posFinMes)break;
	    		listaAnioActual.get(k).setNro(7);
	    		listaDias.get(i).setDia_7(listaAnioActual.get(k++));
	    	}
	    }
//	    if(m==1)
//	    	lblUpdateDate.setValue(listaUpdate.get(m-1));
//	    else
//	    	lblUpdateDate.setValue(listaUpdate.get(m-2));
		//-------------------------------------------------
		BindUtils.postNotifyChange(null, null, this,"listaDias");
	}
	/**
	 * -Se asignan datos por defecto en el grid correspondientes al año siguiente del actual
	 * -A cada disponibilidad se le asigna un valor por defecto de 500
	 * @param anio
	 * @param mes
	 */
	public void mostrarCalendarDispAnioSiguiente(String anio,String mes)
	{
		//Inicializamos los dias y nro disponibilidades en el grid todos en vacio
		//==================
		int m=nMesAnio(mes);
		int a=Integer.parseInt(anio);
		iniDiasYNumDisp();
		//Obtenemos el primer dia y el numero de dias del mes seleccionado
		String primerDiaMes=obtenerPrimerDiaMes(a,m);
		int nroDiaSemana=diaSemana(primerDiaMes);
		//===================================
//		lblUpdateDate.setValue("");
		//Se asignan las disponibilidades por defecto
		int posInicioMes=obtenerPosInicioMesOtro(a,m);
	    int posFinMes=obtenerPosFinMesOtro(a,m);
	    int k=posInicioMes;
	    for(int i=0;i<listaDias.size();i++)
	    {
	    	if(k>posFinMes)break;
	    	if(i==0)
	    	{
	    		if(nroDiaSemana==1)
	    		{
	    			listaAnioSig.get(k).setNro(1);
	    			listaDias.get(i).setDia_1(listaAnioSig.get(k++));
	    			listaAnioSig.get(k).setNro(2);
	    			listaDias.get(i).setDia_2(listaAnioSig.get(k++));
	    			listaAnioSig.get(k).setNro(3);
	    			listaDias.get(i).setDia_3(listaAnioSig.get(k++));
	    			listaAnioSig.get(k).setNro(4);
	    			listaDias.get(i).setDia_4(listaAnioSig.get(k++));
	    			listaAnioSig.get(k).setNro(5);
	    			listaDias.get(i).setDia_5(listaAnioSig.get(k++));
	    			listaAnioSig.get(k).setNro(6);
	    			listaDias.get(i).setDia_6(listaAnioSig.get(k++));
	    			listaAnioSig.get(k).setNro(7);
	    			listaDias.get(i).setDia_7(listaAnioSig.get(k++));
	    		}
	    		else if(nroDiaSemana==2)
	    		{
	    			listaAnioSig.get(k).setNro(2);
	    			listaDias.get(i).setDia_2(listaAnioSig.get(k++));
	    			listaAnioSig.get(k).setNro(3);
	    			listaDias.get(i).setDia_3(listaAnioSig.get(k++));
	    			listaAnioSig.get(k).setNro(4);
	    			listaDias.get(i).setDia_4(listaAnioSig.get(k++));
	    			listaAnioSig.get(k).setNro(5);
	    			listaDias.get(i).setDia_5(listaAnioSig.get(k++));
	    			listaAnioSig.get(k).setNro(6);
	    			listaDias.get(i).setDia_6(listaAnioSig.get(k++));
	    			listaAnioSig.get(k).setNro(7);
	    			listaDias.get(i).setDia_7(listaAnioSig.get(k++));
	    		}
	    		else if(nroDiaSemana==3)
	    		{
	    			listaAnioSig.get(k).setNro(3);
	    			listaDias.get(i).setDia_3(listaAnioSig.get(k++));
	    			listaAnioSig.get(k).setNro(4);
	    			listaDias.get(i).setDia_4(listaAnioSig.get(k++));
	    			listaAnioSig.get(k).setNro(5);
	    			listaDias.get(i).setDia_5(listaAnioSig.get(k++));
	    			listaAnioSig.get(k).setNro(6);
	    			listaDias.get(i).setDia_6(listaAnioSig.get(k++));
	    			listaAnioSig.get(k).setNro(7);
	    			listaDias.get(i).setDia_7(listaAnioSig.get(k++));
	    		}
	    		else if(nroDiaSemana==4)
	    		{
	    			listaAnioSig.get(k).setNro(4);
	    			listaDias.get(i).setDia_4(listaAnioSig.get(k++));
	    			listaAnioSig.get(k).setNro(5);
	    			listaDias.get(i).setDia_5(listaAnioSig.get(k++));
	    			listaAnioSig.get(k).setNro(6);
	    			listaDias.get(i).setDia_6(listaAnioSig.get(k++));
	    			listaAnioSig.get(k).setNro(7);
	    			listaDias.get(i).setDia_7(listaAnioSig.get(k++));
	    		}
	    		else if(nroDiaSemana==5)
	    		{
	    			listaAnioSig.get(k).setNro(5);
	    			listaDias.get(i).setDia_5(listaAnioSig.get(k++));
	    			listaAnioSig.get(k).setNro(6);
	    			listaDias.get(i).setDia_6(listaAnioSig.get(k++));
	    			listaAnioSig.get(k).setNro(7);
	    			listaDias.get(i).setDia_7(listaAnioSig.get(k++));
	    		}
	    		else if(nroDiaSemana==6)
	    		{
	    			listaAnioSig.get(k).setNro(6);
	    			listaDias.get(i).setDia_6(listaAnioSig.get(k++));
	    			listaAnioSig.get(k).setNro(7);
	    			listaDias.get(i).setDia_7(listaAnioSig.get(k++));
	    		}
	    		else if(nroDiaSemana==7)
	    		{
	    			listaAnioSig.get(k).setNro(7);
	    			listaDias.get(i).setDia_7(listaAnioSig.get(k++));
	    		}
	    	}
	    	else
	    	{
	    		if(k>posFinMes)break;
	    		listaAnioSig.get(k).setNro(1);
	    		listaDias.get(i).setDia_1(listaAnioSig.get(k++));
	    		if(k>posFinMes)break;
	    		listaAnioSig.get(k).setNro(2);
	    		listaDias.get(i).setDia_2(listaAnioSig.get(k++));
	    		if(k>posFinMes)break;
	    		listaAnioSig.get(k).setNro(3);
	    		listaDias.get(i).setDia_3(listaAnioSig.get(k++));
	    		if(k>posFinMes)break;
	    		listaAnioSig.get(k).setNro(4);
	    		listaDias.get(i).setDia_4(listaAnioSig.get(k++));
	    		if(k>posFinMes)break;
	    		listaAnioSig.get(k).setNro(5);
	    		listaDias.get(i).setDia_5(listaAnioSig.get(k++));
	    		if(k>posFinMes)break;
	    		listaAnioSig.get(k).setNro(6);
	    		listaDias.get(i).setDia_6(listaAnioSig.get(k++));
	    		if(k>posFinMes)break;
	    		listaAnioSig.get(k).setNro(7);
	    		listaDias.get(i).setDia_7(listaAnioSig.get(k++));
	    	}
	    }
		//-------------------------------------------------
		BindUtils.postNotifyChange(null, null, this,"listaDias");
	}
	/**
	 * Retorna el nombre del pdf correspondiente al parametro mes
	 * @param mes
	 * @return
	 */
	public String txtMesCorrespondiente(String mes)
	{
		String nameFile="";
		switch(mes)
		{
			case "Enero":nameFile="enero.txt";break;
			case "Marzo":nameFile="marzo.txt";break;
			case "Abril":nameFile="abril.txt";break;
			case "Mayo":nameFile="mayo.txt";break;
			case "Junio":nameFile="junio.txt";break;
			case "Julio":nameFile="julio.txt";break;
			case "Agosto":nameFile="agosto.txt";break;
			case "Setiembre":nameFile="setiembre.txt";break;
			case "Octubre":nameFile="octubre.txt";break;
			case "Noviembre":nameFile="noviembre.txt";break;
			case "Diciembre":nameFile="diciembre.txt";break;
		}
		return nameFile;
	}
	/**
	 * Obtiene el nro de mes a partir de su nombre
	 * @param mes
	 * @return
	 */
	public int nMesAnio(String mes)
	{
		int n=-1;
		switch(mes)
		{
			case "Enero":n=1;break;
			case "Febrero":n=2;break;
			case "Marzo":n=3;break;
			case "Abril":n=4;break;
			case "Mayo":n=5;break;
			case "Junio":n=6;break;
			case "Julio":n=7;break;
			case "Agosto":n=8;break;
			case "Setiembre":n=9;break;
			case "Octubre":n=10;break;
			case "Noviembre":n=11;break;
			case "Diciembre":n=12;break;
		}
		return n;
	}
	/**
	 * Obtiene el nombre del mes a partir de su nro de mes
	 * @param mes
	 * @return
	 */
	public String mesAnio(int mes)
	{
		String rMes="";
		switch(mes+1)
		{
			case 1:rMes="Enero";break;
			case 2:rMes="Febrero";break;
			case 3:rMes="Marzo";break;
			case 4:rMes="Abril";break;
			case 5:rMes="Mayo";break;
			case 6:rMes="Junio";break;
			case 7:rMes="Julio";break;
			case 8:rMes="Agosto";break;
			case 9:rMes="Setiembre";break;
			case 10:rMes="Octubre";break;
			case 11:rMes="Noviembre";break;
			case 12:rMes="Diciembre";break;
		}
		return rMes;
	}
	public String mesAnioIdioma(int mes)
	{
		String rMes="";
		switch(mes+1)
		{
			case 1:rMes=etiqueta[24];break;
			case 2:rMes=etiqueta[25];break;
			case 3:rMes=etiqueta[26];break;
			case 4:rMes=etiqueta[27];break;
			case 5:rMes=etiqueta[28];break;
			case 6:rMes=etiqueta[29];break;
			case 7:rMes=etiqueta[30];break;
			case 8:rMes=etiqueta[31];break;
			case 9:rMes=etiqueta[32];break;
			case 10:rMes=etiqueta[33];break;
			case 11:rMes=etiqueta[34];break;
			case 12:rMes=etiqueta[35];break;
		}
		return rMes;
	}
	@Command
	public void changeMonth(@BindingParam ("valueMes") Object valueMes) throws WrongValueException, IOException
	{
		String mes=valueMes.toString();
		mesRecuperado=mes;
		Calendar cal=Calendar.getInstance();
		//Si el año del cbAnio es = al año actual obtenido se procede a mostrar los datos reales
		if(Integer.toString(cal.get(Calendar.YEAR)).equals(cbAnio.getValue()))
			mostrarCalendarDispAnioActual(cbAnio.getValue(),mes);
		else
			mostrarCalendarDispAnioSiguiente(Integer.toString(cal.get(Calendar.YEAR)+1),mes);
	}
	@Command
	public void changeAnio(@BindingParam("valueAnio")String anio) throws WrongValueException, IOException
	{
		Calendar cal=Calendar.getInstance();
		System.out.println("El año se cambio a =>"+anio+"------->"+Integer.toString(cal.get(Calendar.YEAR)));
		//Si el año del cbAnio es = al año actual obtenido se procede a mostrar los datos reales
		if(Integer.toString(cal.get(Calendar.YEAR)).equals(anio))
		{
			mostrarCalendarDispAnioActual(anio,mesRecuperado);
		}
		else
			mostrarCalendarDispAnioSiguiente(anio,mesRecuperado);
	}
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) throws WrongValueException, IOException
	{
		Selectors.wireComponents(view, this, false);
		iniDisponibilidades();
	}
}
	