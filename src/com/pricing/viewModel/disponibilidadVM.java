package com.pricing.viewModel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

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

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.pricing.dao.CDisponibilidadYourselfDAO;
import com.pricing.dao.CEtiquetaDAO;
import com.pricing.dao.ConfAltoNivelDAO;
import com.pricing.extras.lectorPDF;
import com.pricing.model.CDia;
import com.pricing.model.CDias7;
import com.pricing.model.CCalendarioDisponibilidad;
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
	private ArrayList<CCalendarioDisponibilidad> listaDisponibilidad;
	private String mesRecuperado;
	private int cdisponibilidad;
	/***************************/
	private CEtiquetaDAO etiquetaDao;
	private String[] etiqueta;
	private String language;
	private boolean primeraVez;
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
	public void inicializarVM() throws Exception
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
		iniDiasYNumDisp();
		//========================
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
	public void iniciarLosDiasAnio() throws Exception
	{
		Calendar cal=Calendar.getInstance();
		int anio=cal.get(Calendar.YEAR);
		listaAnioActual=new ArrayList<CDia>();
		listaAnioSig=new ArrayList<CDia>();
		listaUpdate=new ArrayList<String>();
		if(isBisiesto(anio))
			iniciarListaAniosBisiesto(366);
		else
			iniciarListaAniosNormal(365);
		//Obteniendo la configuracion para la muestra de la disponibilidad del camino inka
//		ConfAltoNivelDAO confAltoNivelDAO=new ConfAltoNivelDAO();
//		confAltoNivelDAO.asignarListaConfAltoNivel(confAltoNivelDAO.recuperarconfAltoNivel("muestra_camino_inka"));
//		if(confAltoNivelDAO.getoConfAltoNivel().isbEstado())
//		{
//			if(cdisponibilidad==20)
//			{
//				//Recuperar datos del webservice
//			    listaDisponibilidad=new ArrayList<CCalendarioDisponibilidad>();
//				listaDisponibilidad.addAll(recuperarListaDispoJson());
////				ingresarDatosListaDispoCaminoInka(listaDisponibilidad);
//			}else
//				ingresarDatosListaDispoOtros();
//		}else
			ingresarDatosListaDispoOtros();
	}
	public void iniciarListaAniosNormal(int nroDias)
	{
		int nroDia=1;
		for(int i=0;i<nroDias;i++)
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
	public void iniciarListaAniosBisiesto(int nroDias)
	{
		int nroDia=1;
		for(int i=0;i<nroDias;i++)
		{
			CDia dia=new CDia();
			if(i==31 || i==60 || i==91 || i==121 || 
					i==152 || i==182 || i==213 || i==244 || i==274 || i==305 || i==335)
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
	public void ingresarDatosListaDispoOtros()
	{
		Calendar cal=Calendar.getInstance();
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
	public ArrayList<Integer> recuperarDispoMes(int anio,int mes,ArrayList<CCalendarioDisponibilidad> listaDisponibilidad)
	{
		ArrayList<Integer> lista=new ArrayList<Integer>();
		for(CCalendarioDisponibilidad cal:listaDisponibilidad)
		{
			if(cal.getNanio()==anio && cal.getNmes()==mes)
			{
				lista.add(cal.getNdia1());lista.add(cal.getNdia2());lista.add(cal.getNdia3());
				lista.add(cal.getNdia4());lista.add(cal.getNdia5());lista.add(cal.getNdia6());
				lista.add(cal.getNdia7());lista.add(cal.getNdia8());lista.add(cal.getNdia9());
				lista.add(cal.getNdia10());lista.add(cal.getNdia11());lista.add(cal.getNdia12());
				lista.add(cal.getNdia13());lista.add(cal.getNdia14());lista.add(cal.getNdia15());
				lista.add(cal.getNdia16());lista.add(cal.getNdia17());lista.add(cal.getNdia18());
				lista.add(cal.getNdia19());lista.add(cal.getNdia20());lista.add(cal.getNdia21());
				lista.add(cal.getNdia22());lista.add(cal.getNdia23());lista.add(cal.getNdia24());
				lista.add(cal.getNdia25());lista.add(cal.getNdia26());lista.add(cal.getNdia27());
				lista.add(cal.getNdia28());
				if(cal.getNmes()==2)
				{
					if(cal.getNanio()%4==0 && (cal.getNanio()%400==0 || cal.getNanio()%100!=0))
						lista.add(cal.getNdia29());
				}
				else
				{
					lista.add(cal.getNdia29());lista.add(cal.getNdia30());
				}
				if(cal.getNmes()==1 ||
						cal.getNmes()==3||
						cal.getNmes()==5||
						cal.getNmes()==7||
						cal.getNmes()==8||
						cal.getNmes()==10||
						cal.getNmes()==12)
					lista.add(cal.getNdia31());
				break;
			}
		}
		return lista;
	}
	public ArrayList<Integer> recuperarDispoMesUrl(int anio,int mes) throws Exception
	{
		Map<Integer, String> map = new HashMap<Integer,String>();
		ArrayList<Integer> listaDispoMes=new ArrayList<Integer>();
		String mesAux=mesAnio(mes-1);
		//=====Leemos el archivo del mes correspondiente=====
		String nameFileMes=txtMesCorrespondiente(mesAux);
		boolean correcto=leerPdfDesdeUrl(mes);
		if(correcto)
		{
			System.out.println("Es correcto: "+nameFileMes);
			FileReader f = new FileReader(Util.getPathDispActual()+nameFileMes);
	        BufferedReader b = new BufferedReader(f);
	        String contenidoTxt="";
	        String cadena;
	        while((cadena = b.readLine())!=null) 
	        	contenidoTxt+=cadena+"\n";
	        b.close();
	        //Se procede a obtener los dias disponibles
			String[] s=contenidoTxt.split("\n");
		    for(int j=6;j<s.length-4;j++)
		    {
		    	String[] aux=s[j].split(" ");
		    	map.put(Integer.parseInt(aux[0].trim()),aux[1].trim());
		    }
		    final TreeMap<Integer,String>treeSortedByValues1 = new TreeMap<Integer,String>(new Comparator<Integer>()
		    {
		        public int compare(Integer o1, Integer o2)
		        {
		            return o1.compareTo(o2);
		        }
		    });
		    treeSortedByValues1.putAll(map);
		    for ( Entry<Integer, String> e : treeSortedByValues1.entrySet() )
		    {
		        System.out.println(e.getKey() + ": " + e.getValue());
		        listaDispoMes.add(Integer.parseInt(e.getValue()));
		    }
		}
		return listaDispoMes;
	}
	public boolean leerPdfDesdeUrl(int mes){
		String mesTxt="";
		if(mes<10)
			mesTxt="0"+mes;
		else
			mesTxt=""+mes;
		System.out.println("Recupere el mes de: "+mesTxt);
		System.out.println("================================");
		Calendar cal=Calendar.getInstance();
		try {
			lectorPDF.descargarPdf("http://operadores.machupicchu.gob.pe/BoletoExtranet/servletReporteBoleto?reporte=600&idRuta=1&fechaIngreso=01-"+mesTxt+"-"+cal.get(Calendar.YEAR)+"&tipoRegistro=001",
					new lectorPDF().getPath()+"auxPdf.pdf");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
		String contenidoPdf=leerContenidoPdfDescargado();
		String mesNombre=obtenerMes(contenidoPdf);
		if(!mesNombre.equals(""))
		{
			String monthFileName=obtenerNombreArchivoDelMes(mesNombre);
			//Ahora se procede a sobreescribir el archivo txt correspodiente al
			//mes de Disponibilidad con el contenido obtenido del pdf
			try {
				sobreescribirFileTxt(contenidoPdf,monthFileName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}else
			return false;
	}
	public String obtenerNombreArchivoDelMes(String mes)
	{
		String nameFile="";
			//Esta condicional se hizo debido a que el nombre recuperado
			//de los meses de SEPTIEMBRE, NOVIEMBRE Y DICIEMBRE
			//tienen un espacio (" ") al final ejem. "DICIEMBRE " y no "DICIEMBRE"
			//imposibilitando la validacion en el switch
//		int ultimo=mes.length()-1;
//		if(mes.charAt(ultimo)<'A' || mes.charAt(ultimo)>'Z')
//		{
//			mes=mes.substring(0, ultimo);
//			System.out.println("----> "+mes);
//		}
		switch(mes)
		{
			case "ENERO":nameFile="enero.txt";break;
			case "FEBRERO":nameFile="febrero.txt";break;
			case "MARZO":nameFile="marzo.txt";break;
			case "ABRIL":nameFile="abril.txt";break;
			case "MAYO":nameFile="mayo.txt";break;
			case "JUNIO":nameFile="junio.txt";break;
			case "JULIO":nameFile="julio.txt";break;
			case "AGOSTO":nameFile="agosto.txt";break;
			case "SEPTIEMBRE":nameFile="setiembre.txt";break;
			case "OCTUBRE":nameFile="octubre.txt";break;
			case "NOVIEMBRE":nameFile="noviembre.txt";break;
			case "DICIEMBRE":nameFile="diciembre.txt";break;
		}
		return nameFile;
	}
	public void sobreescribirFileTxt(String contentFile,String monthFileName) throws IOException
	{
		System.out.println("Este es el contenido del pdf aqui en sobreescribir txt :) \n"+contentFile);
        Calendar cal=Calendar.getInstance();
        File archivo=null;
        archivo = new File(Util.getPathDispActual()+ monthFileName);
        BufferedWriter bw;
        if(archivo.exists()) {
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(contentFile);
        } else {
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(contentFile);
        }
        bw.close();
	}
	public String obtenerMes(String contenidoPDF)
	{
		String nameMes="";
			try
			{

				String[] datos=contenidoPDF.split("\n",5);
				String[] auxDatos=datos[3].split(" ");
				nameMes=auxDatos[0];
			}
			catch (Exception StringIndexOutOfBoundsException) {
				// TODO: handle exception
				return "";
			}
			return nameMes;
	}
	public String leerContenidoPdfDescargado()
	{
		String contenido="";
		//Luego se procede a leer el pdf guardado para obtener el nombre del mes
		lectorPDF lpdf=new lectorPDF();
		lpdf.setFilePath("auxPdf.pdf");
		try {
			contenido=lpdf.ToText();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contenido;
	}
	public ArrayList<CCalendarioDisponibilidad> recuperarListaDispoJson()
	{
		ArrayList<CCalendarioDisponibilidad> lista=new ArrayList<CCalendarioDisponibilidad>();
		try {
			Gson migs=new Gson();
			String jsoncadena=readUrl("https://www.e-ranti.com/RestDiponibilidadCaminoInka/services/ServicioDisponibilidad/obtenerListaDisponibilidad");
			//creamos una propiedad del gson jsonParser
			JsonParser jsonParser = new JsonParser();
			//Obtenemos el string json en un arreglo json
			JsonArray userArray = jsonParser.parse(jsoncadena).getAsJsonArray();
			      //
			      for ( JsonElement aUser : userArray )
			      {
			    	  CCalendarioDisponibilidad aPe = migs.fromJson(aUser, CCalendarioDisponibilidad.class);
			    	  lista.add(aPe);
			      }
			} catch (Exception e)
			{
			// TODO: handle exception
			}
		return lista;
	}
	private static String readUrl(String urlString) throws Exception {
	    BufferedReader reader = null;
	    try {
	        URL url = new URL(urlString);
	        reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuffer buffer = new StringBuffer();
	        int read;
	        char[] chars = new char[1024];
	        while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read); 

	        return buffer.toString();
	    } finally {
	        if (reader != null)
	            reader.close();
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
	 * @throws Exception 
	 */
	@NotifyChange({"dias","numDisp"})
	public void iniDisponibilidades() throws Exception
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
	 * @throws Exception 
	 */
	public void mostrarCalendarDispAnioActual(String anio,String mes) throws Exception
	{
		//Inicializamos los dias y nro disponibilidades en el grid todos en vacio
		iniDiasYNumDisp();
		//==================
		int m=nMesAnio(mes);
		int a=Integer.parseInt(anio);
		//Se escriben los datos en el grid a partir del primer dia obtenido anteriormente
	    int posInicioMes=obtenerPosInicioMesOtro(a,m);
	    int posFinMes=obtenerPosFinMesOtro(a,m);
	    int k=posInicioMes;
	    ConfAltoNivelDAO confAltoNivelDAO=new ConfAltoNivelDAO();
		confAltoNivelDAO.asignarListaConfAltoNivel(confAltoNivelDAO.recuperarconfAltoNivel("muestra_camino_inka"));
		if(confAltoNivelDAO.getoConfAltoNivel().isbEstado())
		{
			if(cdisponibilidad==20)
		    {
		    	ArrayList<Integer> listDispoMesActual=new ArrayList<Integer>();
				listDispoMesActual=recuperarDispoMesUrl(a,m);
				if(listDispoMesActual.isEmpty())
					listDispoMesActual=recuperarDispoMes(a,m, listaDisponibilidad);
				actualizarDispoMesAnioActual(a,m,k,listDispoMesActual);
//				int nro=1;
//				for(Integer dispo:listDispoMesActual)
//				{
//					System.out.println("dia-> "+nro+" dispo-> "+dispo);
//					nro++;
//				}
		    }
		}
		//Obtenemos el primer dia del mes seleccionado
		String primerDiaMes=obtenerPrimerDiaMes(a,m);
		int nroDiaSemana=diaSemana(primerDiaMes);
//	    //Se escriben los datos en el grid a partir del primer dia obtenido anteriormente
//	    int posInicioMes=obtenerPosInicioMesOtro(a,m);
//	    int posFinMes=obtenerPosFinMesOtro(a,m);
//	    int k=posInicioMes;
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
	public void actualizarDispoMesAnioActual(int anio,int mes,int pos,ArrayList<Integer> listDispoMesActual)
	{
		//Una vez obtenida las disponibilidades se almacena en la listaAnioActual
	    for(int r=0;r<listDispoMesActual.size();r++)
	    {
		    listaAnioActual.get(pos).setCantDisp(String.valueOf(listDispoMesActual.get(r)));
		    listaAnioActual.get(pos).setVisible(true);
		    if(listDispoMesActual.get(r)!=0)
		    {
		    	listaAnioActual.get(pos).setDisponible("icon-checkmark");
		    	listaAnioActual.get(pos).setColorDisp("chek_style");
		    	listaAnioActual.get(pos).setImgPrioridad("background:rgba(25, 206, 61,0.3);border-radius:5px;");
		    }
//		    	listaAnioActual.get(n).setDisponible("/img/dispon/ok.png");
		    else
		    {
		    	listaAnioActual.get(pos).setDisponible("icon-cross");
		    	listaAnioActual.get(pos).setColorDisp("cross_style");
		    	listaAnioActual.get(pos).setImgPrioridad("background:rgba(247, 87, 65,0.3);border-radius:5px;");
		    }
//		    	listaAnioActual.get(n).setDisponible("/img/dispon/x5.png");
		    pos++;
	    }
	}
	public void actualizarDispoMesAnioSig(int anio,int mes,int pos,ArrayList<Integer> listDispoMesSig)
	{
		//Una vez obtenida las disponibilidades se almacena en la listaAnioActual
	    for(int r=0;r<listDispoMesSig.size();r++)
	    {
		    listaAnioSig.get(pos).setCantDisp(String.valueOf(listDispoMesSig.get(r)));
		    listaAnioSig.get(pos).setVisible(true);
		    if(listDispoMesSig.get(r)!=0)
		    {
		    	listaAnioSig.get(pos).setDisponible("icon-checkmark");
		    	listaAnioSig.get(pos).setColorDisp("chek_style");
		    	listaAnioSig.get(pos).setImgPrioridad("background:rgba(25, 206, 61,0.3);border-radius:5px;");
		    }
//		    	listaAnioActual.get(n).setDisponible("/img/dispon/ok.png");
		    else
		    {
		    	listaAnioSig.get(pos).setDisponible("icon-cross");
		    	listaAnioSig.get(pos).setColorDisp("cross_style");
		    	listaAnioSig.get(pos).setImgPrioridad("background:rgba(247, 87, 65,0.3);border-radius:5px;");
		    }
//		    	listaAnioActual.get(n).setDisponible("/img/dispon/x5.png");
		    pos++;
	    }
	}
	/**
	 * -Se asignan datos por defecto en el grid correspondientes al año siguiente del actual
	 * -A cada disponibilidad se le asigna un valor por defecto de 500
	 * @param anio
	 * @param mes
	 * @throws Exception 
	 */
	public void mostrarCalendarDispAnioSiguiente(String anio,String mes) throws Exception
	{
		//Inicializamos los dias y nro disponibilidades en el grid todos en vacio
		//==================
		int m=nMesAnio(mes);
		int a=Integer.parseInt(anio);
		iniDiasYNumDisp();
		int posInicioMes=obtenerPosInicioMesOtro(a,m);
	    int posFinMes=obtenerPosFinMesOtro(a,m);
	    int k=posInicioMes;
	    ConfAltoNivelDAO confAltoNivelDAO=new ConfAltoNivelDAO();
		confAltoNivelDAO.asignarListaConfAltoNivel(confAltoNivelDAO.recuperarconfAltoNivel("muestra_camino_inka"));
		if(confAltoNivelDAO.getoConfAltoNivel().isbEstado())
		{
		    if(cdisponibilidad==20)
		    {
		    	ArrayList<Integer> listDispoMesSig=new ArrayList<Integer>();
				listDispoMesSig=recuperarDispoMesUrl(a,m);
				if(listDispoMesSig.isEmpty())
					listDispoMesSig=recuperarDispoMes(a,m, listaDisponibilidad);
				actualizarDispoMesAnioSig(a,m,k,listDispoMesSig);
				int nro=1;
		    }
		}
		//Obtenemos el primer dia y el numero de dias del mes seleccionado
		String primerDiaMes=obtenerPrimerDiaMes(a,m);
		int nroDiaSemana=diaSemana(primerDiaMes);
		//===================================
//		lblUpdateDate.setValue("");
		//Se asignan las disponibilidades por defecto
//		int posInicioMes=obtenerPosInicioMesOtro(a,m);
//	    int posFinMes=obtenerPosFinMesOtro(a,m);
//	    int k=posInicioMes;
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
			case "Febrero":nameFile="febrero.txt";break;
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
	public void changeMonth(@BindingParam ("valueMes") Object valueMes) throws Exception
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
	public void changeAnio(@BindingParam("valueAnio")String anio) throws Exception
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
	public boolean isBisiesto(int anio)
	{
		return (anio % 4 == 0) && ((anio % 100 != 0) || (anio % 400 == 0));
	}
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) throws Exception
	{
		Selectors.wireComponents(view, this, false);
		iniDisponibilidades();
	}
}
	