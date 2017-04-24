package com.pricing.viewModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
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
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.util.Clients;

import com.pricing.dao.CDisponibilidadYourselfDAO;
import com.pricing.dao.CEtiquetaDAO;
import com.pricing.model.CDestino;
import com.pricing.model.CDia;
import com.pricing.model.CDias7;
import com.pricing.model.CCalendarioDisponibilidad;
import com.pricing.model.CReserva;
import com.pricing.util.Util;

import sun.security.jca.GetInstance;
import sun.security.jca.GetInstance.Instance;

public class CDisponibilidadYourselfVM {
	//==============atributos===========
	private ArrayList<CCalendarioDisponibilidad> listaDisponibilidad;
	private ArrayList<Integer> listaDispoActual;
	private ArrayList<Integer> listaDispoSiguiente;
	private CDisponibilidadYourselfDAO cdisponibilidadDAO;
	private CCalendarioDisponibilidad listaDiasMes;
	private String Mes;
	//==================getter and setter=========
	private ArrayList<String> listaDispActual;
	private ArrayList<String> listaDispSiguiente;
	
	private ArrayList<String> listDispMes;
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
	/***************************/
	private CEtiquetaDAO etiquetaDao;
	private String[] etiqueta;
	private String language;
	private String valorcmbAnio;
	private String valorcmbAnioSiguiente;
	private String valorcmbAnios;
	private String valorcmbmeses;
	private CReserva oReservar;
	private String codigoPaquete;
	private ArrayList<String> mesesModificadasActual;
	private ArrayList<String> mesesModificadasSig;
	private boolean Actualizar;
	private ArrayList<Integer> listaMesUpdate;
	private String ColorBtnCrear="background:#1A5276;";
	private String ColorBtnEditar="background:#1A5276;";
	private boolean deshabilitarBtnCrear=false;
	private boolean estaVacio=false;
	private boolean desabilitarFebrero=false;
	private String anioSeleccionado;

	/***************************/
	Session ses;
	HttpSession httpses;
	//=======================================
	
	
	public ArrayList<String[]> getListaFechasSeleccionadas() {
		return listaFechasSeleccionadas;
	}
	public ArrayList<String> getMesesModificadasActual() {
		return mesesModificadasActual;
	}
	public void setMesesModificadasActual(ArrayList<String> mesesModificadasActual) {
		this.mesesModificadasActual = mesesModificadasActual;
	}
	public ArrayList<String> getMesesModificadasSig() {
		return mesesModificadasSig;
	}
	public void setMesesModificadasSig(ArrayList<String> mesesModificadasSig) {
		this.mesesModificadasSig = mesesModificadasSig;
	}
	public String getAnioSeleccionado() {
		return anioSeleccionado;
	}
	public void setAnioSeleccionado(String anioSeleccionado) {
		this.anioSeleccionado = anioSeleccionado;
	}
	public boolean isDesabilitarFebrero() {
		return desabilitarFebrero;
	}
	public void setDesabilitarFebrero(boolean desabilitarFebrero) {
		this.desabilitarFebrero = desabilitarFebrero;
	}
	public boolean isDeshabilitarBtnCrear() {
		return deshabilitarBtnCrear;
	}
	public void setDeshabilitarBtnCrear(boolean deshabilitarBtnCrear) {
		this.deshabilitarBtnCrear = deshabilitarBtnCrear;
	}
	public String getColorBtnCrear() {
		return ColorBtnCrear;
	}
	public void setColorBtnCrear(String colorBtnCrear) {
		ColorBtnCrear = colorBtnCrear;
	}
	public String getColorBtnEditar() {
		return ColorBtnEditar;
	}
	public void setColorBtnEditar(String colorBtnEditar) {
		ColorBtnEditar = colorBtnEditar;
	}
	public ArrayList<Integer> getListaMesUpdate() {
		return listaMesUpdate;
	}
	public void setListaMesUpdate(ArrayList<Integer> listaMesUpdate) {
		this.listaMesUpdate = listaMesUpdate;
	}
	public boolean isActualizar() {
		return Actualizar;
	}
	public void setActualizar(boolean actualizar) {
		Actualizar = actualizar;
	}
	public CReserva getoReservar() {
		return oReservar;
	}
	public void setoReservar(CReserva oReservar) {
		this.oReservar = oReservar;
	}
	public String getValorcmbAnio() {
		return valorcmbAnio;
	}
	public void setValorcmbAnio(String valorcmbAnio) {
		this.valorcmbAnio = valorcmbAnio;
	}
	public String getValorcmbAnioSiguiente() {
		return valorcmbAnioSiguiente;
	}
	public void setValorcmbAnioSiguiente(String valorcmbAnioSiguiente) {
		this.valorcmbAnioSiguiente = valorcmbAnioSiguiente;
	}
	public String getValorcmbAnios() {
		return valorcmbAnios;
	}
	public void setValorcmbAnios(String valorcmbAnios) {
		this.valorcmbAnios = valorcmbAnios;
	}
	public String getValorcmbmeses() {
		return valorcmbmeses;
	}
	public void setValorcmbmeses(String valorcmbmeses) {
		this.valorcmbmeses = valorcmbmeses;
	}
	public ArrayList<Integer> getListaDispoActual() {
		return listaDispoActual;
	}
	public void setListaDispoActual(ArrayList<Integer> listaDispoActual) {
		this.listaDispoActual = listaDispoActual;
	}
	public ArrayList<Integer> getListaDispoSiguiente() {
		return listaDispoSiguiente;
	}
	public void setListaDispoSiguiente(ArrayList<Integer> listaDispoSiguiente) {
		this.listaDispoSiguiente = listaDispoSiguiente;
	}
	public ArrayList<String> getListaDispActual() {
		return listaDispActual;
	}
	public void setListaDispActual(ArrayList<String> listaDispActual) {
		this.listaDispActual = listaDispActual;
	}
	public ArrayList<String> getListaDispSiguiente() {
		return listaDispSiguiente;
	}
	public void setListaDispSiguiente(ArrayList<String> listaDispSiguiente) {
		this.listaDispSiguiente = listaDispSiguiente;
	}
	public ArrayList<CCalendarioDisponibilidad> getListaDisponibilidad() {
		return listaDisponibilidad;
	}
	public void setListaDisponibilidad(ArrayList<CCalendarioDisponibilidad> listaDisponibilidad) {
		this.listaDisponibilidad = listaDisponibilidad;
	}
	public String getMes() {
		return Mes;
	}
	public void setMes(String mes) {
		Mes = mes;
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
	//=======================================
	@Init
	@NotifyChange({"valorcmbAnio","valorcmbAnioSiguiente"})
	public void inicializarVM() throws IOException
	{
		Mes=new String();
		etiquetaDao=new CEtiquetaDAO();
		etiquetaDao.asignarEtiquetaIdiomas(etiquetaDao.recuperarEtiquetasBD());
		cdisponibilidadDAO=new CDisponibilidadYourselfDAO();
		listaDisponibilidad=new ArrayList<CCalendarioDisponibilidad>();
		//cdisponibilidadDAO.setListaDisponibilidad(cdisponibilidadDAO.getListaDisponibilidad());
		//separarAnioActual_AnioSiguiente(listaDisponibilidad);
		etiqueta=(String[]) Sessions.getCurrent().getAttribute("etiqueta");
		/*******************/
		dias7=new CDias7();
		listaDias=new ArrayList<CDias7>();
		listDispMes=new ArrayList<String>();
		listaAnioActual=new ArrayList<CDia>();
		listaAnioSig=new ArrayList<CDia>();
		ses=Sessions.getCurrent();
		httpses=(HttpSession)Sessions.getCurrent().getNativeSession();
		ses.setAttribute("fechaPrioridad",1);
		ses.setAttribute("cantidadFechas",0);
		httpses.setAttribute("antDia", antDia);
		httpses.setAttribute("mes", "");
		httpses.setAttribute("anio", "");
		httpses.setAttribute("antDiaAlt", antDiaAlt);
		httpses.setAttribute("mesAlt", "");
		httpses.setAttribute("anioAlt", "");
		Calendar calIni=Calendar.getInstance();
		valorcmbAnio=Integer.toString(calIni.get(Calendar.YEAR));
		valorcmbAnioSiguiente=Integer.toString(calIni.get(Calendar.YEAR)+1);
		mesesModificadasActual=new ArrayList<String>();
		mesesModificadasSig=new ArrayList<String>();
		/*try
		{
			Execution exec = Executions.getCurrent();
		    this.codigoPaquete=exec.getParameter("var1");
		}
		catch(Exception e)
		{
			System.out.println("Error: "+e.getMessage());
		}
		oReservar=new CReserva(codigoPaquete);
		iniciarEtiquetas();
		*/
		//----------------------------
		//Inicializamos los dias y el numero de disponibilidades
		iniDiasYNumDisp();
		//===========================
		iniciarLosDiasAnio();
		//========================
		iniciarFechasSeleccionadas();
		
		iniDisponibilidades();
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
	//============================Separara la lista extraida de la BD en anio actual y siguiente=======
	public void iniciarEtiquetas()
	{
		etiquetaDao=new CEtiquetaDAO();
		etiquetaDao.asignarEtiquetaIdiomas(etiquetaDao.recuperarEtiquetasBD());
		etiqueta=new String[etiquetaDao.getIdioma().getIdioma1().length];//Se asigna el tamaño de etiqueta
		
		language=Executions.getCurrent().getHeader("accept-language").split(",")[0];
//		System.out.println(Executions.getCurrent().getHeader("accept-language"));
		if(language.equals("es-ES"))
		{
			etiqueta=etiquetaDao.getIdioma().getIdioma1();
			oReservar.getoPaquete().setTitulo(oReservar.getoPaquete().getcTituloIdioma1());
		}
		else if(language.equals("pt-BR") || language.equals("pt-PT"))
		{
			etiqueta=etiquetaDao.getIdioma().getIdioma3();
			oReservar.getoPaquete().setTitulo(oReservar.getoPaquete().getcTituloIdioma3());
		}
		else
		{
			etiqueta=etiquetaDao.getIdioma().getIdioma2();
			oReservar.getoPaquete().setTitulo(oReservar.getoPaquete().getcTituloIdioma2());
		}
		Sessions.getCurrent().setAttribute("etiqueta", etiqueta);
	}
	
	public void iniciarLosDiasAnio() throws IOException
	{
		int nroDia=1;
		for(int i=0;i<365;i++)//====aquie era el 337
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
	}
	
//	@Command
//	public void modificacionDisponibilidadMes() throws IOException{
//		for(int i=0;i<listaAnioActual.size();i++){
//			listaAnioActual.get(i).setEditable(false);
//		}
//		for(int i=0;i<listaAnioSig.size();i++){
//			listaAnioSig.get(i).setEditable(false);
//		}
//		this.setActualizar(true);
//		BindUtils.postNotifyChange(null, null, this, "actualizar");
//		BindUtils.postNotifyChange(null, null, this,"listaDias");
//		BindUtils.postNotifyChange(null, null, "listaAnioActual", "editable");
//		BindUtils.postNotifyChange(null, null, "listaAnioSig", "editable");
//		
//	}
	
	@Command
	public void actualizarCantDispo(@BindingParam("nAnio")String nAnio,@BindingParam("cDestino")String cDestino,@BindingParam("componente")Component componente){
		System.out.println("entro a update");
		boolean correcto1=false;
		boolean correcto2=false;
		Calendar cal=Calendar.getInstance();
		String anioActual=String.valueOf(cal.get(Calendar.YEAR));
		String anioSiguiente=String.valueOf(cal.get(Calendar.YEAR)+1);
		for(int i=0;i<mesesModificadasActual.size();i++){
			 recuperarDiasMes(anioActual, mesesModificadasActual.get(i), cDestino);
				 correcto1=cdisponibilidadDAO.isOperationCorrect(cdisponibilidadDAO.updateDisponibilidadBD(listaMesUpdate)); 
		 }
		for(int i=0;i<mesesModificadasSig.size();i++){
			 recuperarDiasMes(anioSiguiente, mesesModificadasSig.get(i), cDestino);
				 correcto2=cdisponibilidadDAO.isOperationCorrect(cdisponibilidadDAO.updateDisponibilidadBD(listaMesUpdate)); 
		 }
		if(correcto1 || correcto2 ||(correcto1 && correcto2)){
			Clients.showNotification("Las disponibilidades fueron actualizadas correctamente", Clients.NOTIFICATION_TYPE_INFO, componente,"before_start",2700);
		}
		else{
			Clients.showNotification("Error al actualizar disponibilidades", Clients.NOTIFICATION_TYPE_ERROR, componente,"before_start",2700);
		}
		mesesModificadasActual.clear();
		mesesModificadasSig.clear();
		BindUtils.postNotifyChange(null, null,  listaAnioActual, "colorDisp");
		BindUtils.postNotifyChange(null, null,  listaAnioSig, "colorDisp");
	}
	
	public void recuperarDiasMes(String Anio,String Mes,String Destino){
		listaMesUpdate=new ArrayList<Integer>();
		Calendar cad=Calendar.getInstance();
		int anio=cad.get(Calendar.YEAR);
		//======esto es para el anio actual=====
		int m=nMesAnio(Mes);
		int a=Integer.parseInt(Anio);
		//Obtenemos el primer dia del mes seleccionado
		String primerDiaMes=obtenerPrimerDiaMes(a,m);
		int nroDiaSemana=diaSemana(primerDiaMes);
	    //Se escriben los datos en el grid a partir del primer dia obtenido anteriormente
	    //int posInicioMes=obtenerPosInicioMes(m);
	    int posInicioMes=obtenerPosInicioMesOtro(a, m);
		//int posFinMes=obtenerPosFinMes(m);
	    int posFinMes=obtenerPosFinMesOtro(a, m);
	    int k=posInicioMes;
		if(Anio.equals(String.valueOf(anio))){
			listaMesUpdate.add(Integer.valueOf(Destino));
			listaMesUpdate.add(a);
			listaMesUpdate.add(m);
			for(int i=k;i<=posFinMes;i++){
					listaMesUpdate.add(Integer.valueOf(listaAnioActual.get(i).getCantDisp()));
			}
			if(m==4 || m==6 || m==9 || m==11){
				listaMesUpdate.add(-1);
			}else if(m==2 && EsBiciesto(a)){
				listaMesUpdate.add(-1);
				listaMesUpdate.add(-1);
			}else if(m==2 && !EsBiciesto(a)){
				listaMesUpdate.add(-1);
				listaMesUpdate.add(-1);
				listaMesUpdate.add(-1);
			}
		}//======esto es para el anio siguiente=====
		else{
			listaMesUpdate.add(Integer.valueOf(Destino));
			listaMesUpdate.add(a);
			listaMesUpdate.add(m);
			for(int i=k;i<=posFinMes;i++){
					listaMesUpdate.add(Integer.valueOf(listaAnioSig.get(i).getCantDisp()));
			}
			if(m==4 || m==6 || m==9 || m==11){
				listaMesUpdate.add(-1);
			}else if(m==2 && EsBiciesto(a)){
				listaMesUpdate.add(-1);
				listaMesUpdate.add(-1);
			}else if(m==2 && !EsBiciesto(a)){
				listaMesUpdate.add(-1);
				listaMesUpdate.add(-1);
				listaMesUpdate.add(-1);
			}
		}
		BindUtils.postNotifyChange(null, null, this, "listaMesUpdate");
		BindUtils.postNotifyChange(null, null, this, "listaDias");
	}
	
	public boolean EsBiciesto(int anio){
		boolean biciesto=false;
		if((anio % 4==0) && ((anio % 100 !=0)||(anio%400==0))){
			biciesto=true;
		}
		return biciesto;
	}
	
//	@Command
//	public void crearDisponibilidad(@BindingParam("cDestino")String cDestino,@BindingParam("componente")Component componente) throws IOException{
//		boolean biciestoActual=false;
//		boolean biciestoSig=false;
//		Calendar cal=Calendar.getInstance();
//		int anio=cal.get(Calendar.YEAR);
//		int anioSig=anio+1;
//		if((anio % 4==0) && ((anio % 100 !=0)||(anio%400==0))){
//			biciestoActual=true;
//		}
//		if((anioSig % 4==0) && ((anioSig % 100 !=0)||(anioSig%400==0))){
//			biciestoSig=true;
//		}
//		boolean correcto=cdisponibilidadDAO.isOperationCorrect(cdisponibilidadDAO.crearDisponibilidad(Integer.valueOf(cDestino), cal.get(Calendar.YEAR),biciestoActual,biciestoSig));
//		seleccionardestino(cDestino);
//		if(correcto){
//			Clients.showNotification("Las disponibilidades fueron creadas", Clients.NOTIFICATION_TYPE_INFO, componente,"before_start",2700);
//		}
//		else
//			Clients.showNotification("Error al crear las disponbilidades", Clients.NOTIFICATION_TYPE_INFO, componente,"before_start",2700);
//		}
	
	@Command
	@NotifyChange({"listaDispoActual","listaDispoSiguiente","listaAnioActual","listaAnioSig"})
	public void seleccionardestino(@BindingParam("codDestino")String codDestino) throws IOException{
		listaDispoActual=new ArrayList<Integer>();
		listaDispoSiguiente=new ArrayList<Integer>();
		cdisponibilidadDAO.asignarDisponibilidad(cdisponibilidadDAO.recuperarDisponibilidadesBD(Integer.valueOf(codDestino)));
		this.setListaDispoActual(cdisponibilidadDAO.getListaDispoActual());
		this.setListaDispoSiguiente(cdisponibilidadDAO.getListaDispoSiguiente());
		if(listaDispoActual.isEmpty() && listaDispoSiguiente.isEmpty()){
			this.setColorBtnCrear("background:green;");
			//this.setDeshabilitarBtnCrear(false);
			this.estaVacio=true;
			listaDias.clear();
		}
		else{
			this.setColorBtnEditar("background:green;");
			this.setDeshabilitarBtnCrear(true);
			this.estaVacio=false;
		}
		
		for(int cont=0;cont<listaDispoActual.size();cont++){
			  System.out.println("contador original->"+cont);
	    	  listaAnioActual.get(cont).setCantDisp(String.valueOf(listaDispoActual.get(cont)));
	    	  listaAnioActual.get(cont).setVisible(true);
	    	  listaAnioActual.get(cont).setEditable(true);
	    	  if(listaDispoActual.get(cont)!=0)
			    {
			    	listaAnioActual.get(cont).setDisponible("icon-checkmark");
			    	listaAnioActual.get(cont).setColorDisp("chek_style");
			    	listaAnioActual.get(cont).setImgPrioridad("background:rgba(25, 206, 61,0.3);border-radius:5px;");
			    }
			    else
			    {
			    	listaAnioActual.get(cont).setDisponible("icon-cross");
			    	listaAnioActual.get(cont).setColorDisp("cross_style");
			    	listaAnioActual.get(cont).setImgPrioridad("background:rgba(247, 87, 65,0.3);border-radius:5px;");
			    }
			  
	    }
	    for(int count=0;count<listaDispoSiguiente.size();count++){
	    	  listaAnioSig.get(count).setCantDisp(String.valueOf(listaDispoSiguiente.get(count)));
	    	  listaAnioSig.get(count).setVisible(true);
	    	  listaAnioSig.get(count).setEditable(true);
	    	  if(listaDispoSiguiente.get(count)!=0)
			    {
			    	listaAnioSig.get(count).setDisponible("icon-checkmark");
			    	listaAnioSig.get(count).setColorDisp("chek_style");
			    	listaAnioSig.get(count).setImgPrioridad("background:rgba(25, 206, 61,0.3);border-radius:5px;");
			    }
			    else
			    {
			    	listaAnioSig.get(count).setDisponible("icon-cross");
			    	listaAnioSig.get(count).setColorDisp("cross_style");
			    	listaAnioSig.get(count).setImgPrioridad("background:rgba(247, 87, 65,0.3);border-radius:5px;");
			    }
			 
	    }
	    if(Integer.valueOf(codDestino)>=20){
			System.out.println("entro a condicion");
			this.setDesabilitarFebrero(true);
			Calendar cal=Calendar.getInstance();
			int actual=cal.get(Calendar.YEAR);
			int nroDiasActual_febrero=obtenerPosFinMesOtro(actual,02);
			int nroDiasSig_febrero=obtenerPosFinMesOtro(actual+1,02);
			for(int i=31;i<nroDiasActual_febrero+1;i++){
				listaAnioActual.get(i).setVisible(false);
			}
			for(int j=31;j<nroDiasSig_febrero+1;j++){
				listaAnioSig.get(j).setVisible(false);
			}
		}else
			this.setDesabilitarFebrero(false);
		BindUtils.postNotifyChange(null, null, listaAnioActual, "cantDisp");
	    BindUtils.postNotifyChange(null, null,  listaAnioActual, "disponible");
	    BindUtils.postNotifyChange(null, null,  listaAnioActual, "colorDisp");
	    BindUtils.postNotifyChange(null, null,  listaAnioActual, "imgPrioridad");
	    BindUtils.postNotifyChange(null, null, listaAnioActual, "editable");
	    BindUtils.postNotifyChange(null, null, listaAnioActual, "visible");
	    BindUtils.postNotifyChange(null, null, listaAnioSig, "cantDisp");
	    BindUtils.postNotifyChange(null, null,  listaAnioSig, "disponible");
	    BindUtils.postNotifyChange(null, null,  listaAnioSig, "colorDisp");
	    BindUtils.postNotifyChange(null, null,  listaAnioSig, "imgPrioridad");
	    BindUtils.postNotifyChange(null, null, listaAnioSig, "editable");
	    BindUtils.postNotifyChange(null, null, listaAnioSig, "visible");
	    BindUtils.postNotifyChange(null, null, this, "deshabilitarBtnCrear");
	    BindUtils.postNotifyChange(null, null, this, "desabilitarFebrero");
	    BindUtils.postNotifyChange(null, null, this, "ColorBtnCrear");
	    BindUtils.postNotifyChange(null, null, this, "ColoBtnEditar");
	    BindUtils.postNotifyChange(null, null, this, "listaDias");
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
		System.out.println("Año-> "+valorcmbAnios);
		System.out.println("Soy fecha principal: "+mes.toString()+" -- "+dia+" -- "+valorcmbAnios);
		if(!selectMesValido(mes,dia,valorcmbAnios))
			return;
		CDia auxDia=(CDia)httpses.getAttribute("antDiaAlt");
		String auxMes=(String)httpses.getAttribute("mesAlt");
		String auxAnio=(String)httpses.getAttribute("anioAlt");
		listaFechasSeleccionadas.get(0)[0]="";
		listaFechasSeleccionadas.get(0)[1]="";
		listaFechasSeleccionadas.get(0)[2]="";
		if(auxDia!=null && auxMes!=null && auxAnio!=null)
		{
			if(dia.getcNroDia().equals(auxDia.getcNroDia())&& auxMes.equals(mes.toString()) && auxAnio.equals(valorcmbAnios))
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
		httpses.setAttribute("anio", valorcmbAnios);
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
		System.out.println("Soy fecha alterna: "+mes.toString()+" -- "+dia+" -- "+valorcmbAnios);
		if(!selectMesValido(mes,dia,valorcmbAnios))
			return;
		CDia auxDia=(CDia)httpses.getAttribute("antDia");
		String auxMes=(String)httpses.getAttribute("mes");
		String auxAnio=(String)httpses.getAttribute("anio");
		listaFechasSeleccionadas.get(1)[0]="";
		listaFechasSeleccionadas.get(1)[1]="";
		listaFechasSeleccionadas.get(1)[2]="";
		if(auxDia!=null && auxMes!=null && auxAnio!=null)
		{
			if(dia.getcNroDia().equals(auxDia.getcNroDia())&& auxMes.equals(mes.toString()) && auxAnio.equals(valorcmbAnios))
			{
				System.out.println("Somos iguales");
				Clients.showNotification(etiqueta[219], Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",3000);
				return;
			}
		}
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
		httpses.setAttribute("anioAlt", valorcmbAnios);
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
			listaFechasSeleccionadas.get(0)[2]=valorcmbAnios;
		}
		else
		{
			listaFechasSeleccionadas.get(1)[0]=dia.getcNroDia();
			listaFechasSeleccionadas.get(1)[1]=mesRecuperado;
			listaFechasSeleccionadas.get(1)[2]=valorcmbAnios;
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

	public void iniDisponibilidades() throws WrongValueException, IOException
	{
		Calendar calIni=Calendar.getInstance();
		valorcmbAnios=Integer.toString(calIni.get(Calendar.YEAR));
		//----------------------
		valorcmbAnio=Integer.toString(calIni.get(Calendar.YEAR));
		valorcmbAnioSiguiente=Integer.toString(calIni.get(Calendar.YEAR)+1);
		//----------------------------
		valorcmbmeses=mesAnioIdioma(calIni.get(Calendar.MONTH));
		mesRecuperado=mesAnio(calIni.get(Calendar.MONTH));
		//===================================
		mostrarCalendarDispAnioActual(valorcmbAnio,mesRecuperado);
		BindUtils.postNotifyChange(null, null, this, "valorcmbAnios");
		BindUtils.postNotifyChange(null, null, this, "valorcmbAnioSiguiente");
		BindUtils.postNotifyChange(null, null, this, "valorcmbAnio");
		BindUtils.postNotifyChange(null, null, this, "valorcmbmeses");
	}
	
	public int obtenerPosInicioMes(int mes)
	{
		
		int pos=-1;
		switch(mes)
		{
			case 1:pos=0;break;
			case 2:pos=31;break;
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
	public int obtenerPosFinMes(int mes)
	{
		int pos=-1;
		switch(mes)
		{
			case 1:pos=30;break;
			case 2:pos=61;break;
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
	    //int posInicioMes=obtenerPosInicioMes(m);
	    int posInicioMes=obtenerPosInicioMesOtro(a, m);
		//int posFinMes=obtenerPosFinMes(m);
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
		//int posInicioMes=obtenerPosInicioMes(m);
	    int posInicioMes=obtenerPosInicioMesOtro(a,m);
		//int posFinMes=obtenerPosFinMes(m);
	    int posFinMes=obtenerPosFinMesOtro(a, m);
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
	//===retornar estos valores====
	/*public String mesAnioIdioma1(int mes)
	{
		String rMes="";
		switch(mes+1)
		{
			case 1:rMes=etiqueta[24];break;
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
	}*/
	public String mesAnioIdioma(int mes)
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
	@Command
	public void changeMonth(@BindingParam("valueAnio")String valueAnio,@BindingParam ("valueMes") String valueMes) throws WrongValueException, IOException
	{
		String mes=valueMes;
		mesRecuperado=mes;
		Calendar cal=Calendar.getInstance();
		//Si el año del cbAnio es = al año actual obtenido se procede a mostrar los datos reales
		if(Integer.toString(cal.get(Calendar.YEAR)).equals(valueAnio) && !estaVacio){
			mostrarCalendarDispAnioActual(valueAnio,mes);
		}
		else if(Integer.toString(cal.get(Calendar.YEAR)+1).equals(valueAnio) && !estaVacio)
			mostrarCalendarDispAnioSiguiente(Integer.toString(cal.get(Calendar.YEAR)+1),mes);
	}
	@Command
	public void changeAnio(@BindingParam("valueAnio")String anio) throws WrongValueException, IOException
	{
		Calendar cal=Calendar.getInstance();
		this.setValorcmbAnio(Integer.toString(cal.get(Calendar.YEAR)));
		System.out.println("El año se cambio a =>"+anio+"------->"+Integer.toString(cal.get(Calendar.YEAR)));
		//Si el año del cbAnio es = al año actual obtenido se procede a mostrar los datos reales
		if(Integer.toString(cal.get(Calendar.YEAR)).equals(anio) && !estaVacio)
		{
			mostrarCalendarDispAnioActual(anio,mesRecuperado);
		}
		else if(Integer.toString(cal.get(Calendar.YEAR)+1).equals(anio) && !estaVacio)
			mostrarCalendarDispAnioSiguiente(anio,mesRecuperado);
		BindUtils.postNotifyChange(null, null, this, "valorcmbAnio");
	}
	
	
	@Command
	public void AnioSeleccionado(@BindingParam("nAnio")String nAnio){
		this.setAnioSeleccionado(nAnio);
		BindUtils.postNotifyChange(null, null, this, "anioSeleccionado");
	}
	
	@Command
	@NotifyChange({"mesesModificadasActual","mesesModificadasSig"})
	public void huboModificacion(@BindingParam("mes")String mes,@BindingParam("anio")String anio,@BindingParam("objetoDispo")CDia objetoDispo){
		Calendar cal=Calendar.getInstance();
		String anioActual=String.valueOf(cal.get(Calendar.YEAR));
		String anioSig=String.valueOf(cal.get(Calendar.YEAR)+1);
		if(!mesesModificadasActual.contains(mes) && anio.equals(anioActual)){
				mesesModificadasActual.add(mes);
			}
		else if(!mesesModificadasSig.contains(mes) && anio.equals(anioSig)){
			mesesModificadasSig.add(mes);
		}
		if(objetoDispo.getCantDisp().isEmpty() || !isInteger(objetoDispo.getCantDisp())){
			objetoDispo.setCantDisp("0");
		}
		BindUtils.postNotifyChange(null, null, objetoDispo, "cantDisp");
	}
	public boolean isInteger(String n)
	{
		try {
			Integer.parseInt(n);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
}
