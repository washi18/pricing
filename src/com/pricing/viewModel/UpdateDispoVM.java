package com.pricing.viewModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import com.pricing.dao.CDisponibilidadYourselfDAO;
import com.pricing.extras.lectorPDF;
import com.pricing.util.Util;

public class UpdateDispoVM 
{
	@Wire
	Textbox txtNombreArchivo1,txtNombreArchivo2;
	@Wire
	Label lblMensaje1,lblMensaje2;
	@Wire
	Combobox cbAnio,cbAnio_pdf,cbDispo;
	@Wire
	Comboitem cbAnioActual,cbAnioSig,cbAnioActual_pdf,cbAnioSig_pdf;
	//==============CARGAR PDF====================
		@Command
		 public void uploadFilePDF(@BindingParam("idTb")final String tb,@BindingParam("idLbl") final String lbl,@BindingParam("dispo")final String dispo) {
			if(dispo==null)
			{
				Clients.showNotification("Seleccione Un Destino",Clients.NOTIFICATION_TYPE_INFO,null,"top_center",3000);
				return;
			}
			Fileupload.get(new EventListener<UploadEvent>(){
					public void onEvent(UploadEvent event) 
					{
						org.zkoss.util.media.Media media = event.getMedia();
						//Se procede a validar la extension del archivo
						if(media.getFormat().equals("pdf"))
						{
							String contentPdf=obtenerContenidoPDF(media);
							String[] cad=contentPdf.split("\n");
							int i=0;
							for(String s:cad)
							{
								System.out.println(" pos:"+i+" --->"+s);
								i++;
							}
							String mes=obtenerMes(contentPdf,lbl);
							if(!validoContenido(mes))
							{
								Clients.showNotification("El archivo no corresponde a la DRC CUSCO",Clients.NOTIFICATION_TYPE_INFO,null,"top_center",3000);
								return;
							}
							System.out.println("Nombre del mes: "+mes+" tamanio: "+mes.length());
							int mMes=obtenerNumeroDelMes(mes);
//							System.out.println("Nombre del archivo: "+monthFileName+" tamanio: "+monthFileName.length());
							//Ahora se procede a sobreescribir el archivo txt correspodiente al
							//mes de Disponibilidad con el contenido obtenido del pdf
							try {
								sobreescribirFileTxt(contentPdf,mMes,dispo);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							//=================================
							asignarNombreFile(media.getName(),tb);
						} else {
							Messagebox.show("Not an PDF File: "+media, "Error", Messagebox.OK, Messagebox.ERROR);
								}
					}
			     });
		 }
//		//==============CARGAR TXT====================
//				@Command
//				 public void uploadFileTXT(@BindingParam("idTb")final String tb,@BindingParam("idLbl") final String lbl,@BindingParam("opcion")final int opcion) {
//					 Fileupload.get(new EventListener<UploadEvent>(){
//							public void onEvent(UploadEvent event) 
//							{
//								org.zkoss.util.media.Media media = event.getMedia();
//								if(media.getFormat().equals("txt"))
//								{
//									String contentTxt=media.getStringData();
//									System.out.println("Este es el texto del TXT-> \n"+contentTxt);
//									String mes=obtenerMes(contentTxt,lbl);
//									if(!validoContenido(mes))
//										return;
//									if(!validoNroDias(mes,contentTxt))
//										return;
//									String monthFileName=obtenerNombreArchivoDelMes(mes);
//									try {
//										sobreescribirFileTxt(contentTxt,monthFileName,opcion);
//									} catch (IOException e) {
//										// TODO Auto-generated catch block
//										e.printStackTrace();
//									}
//									//=================================
//									asignarNombreFile(media.getName(),tb);
//								} else {
//									Messagebox.show("Not an TXT File: "+media, "Error", Messagebox.OK, Messagebox.ERROR);
//										}
//							}
//					     });
//				 }
		public boolean validoContenido(String nombreMes)
		{
			boolean valido=true;
			if(!existeNombreMes(nombreMes))
			{
				Clients.showNotification("El archivo no corresponde a la DRC Cusco",Clients.NOTIFICATION_TYPE_ERROR,null,"top_center",3000);
				valido=false;
			}
			return valido;
		}
		public boolean existeNombreMes(String nombreMes)
		{
			boolean existe=false;
			if(nombreMes.equals("ENERO") ||
					nombreMes.equals("FEBRERO")||
					nombreMes.equals("MARZO")||
					nombreMes.equals("ABRIL")||
					nombreMes.equals("MAYO")||
					nombreMes.equals("JUNIO")||
					nombreMes.equals("JULIO")||
					nombreMes.equals("AGOSTO")||
					nombreMes.equals("SEPTIEMBRE")||
					nombreMes.equals("OCTUBRE")||
					nombreMes.equals("NOVIEMBRE")||
					nombreMes.equals("DICIEMBRE"))
				existe=true;
			return true;
		}
		public boolean validoNroDias(String mes,String contenidoFile)
		{
			/****************/
//			int ultimo=mes.length()-1;
//			if(mes.charAt(ultimo)<'A' || mes.charAt(ultimo)>'Z')
//			{
//				mes=mes.substring(0, ultimo);
//				System.out.println("----> "+mes);
//			}
			/*****************/
			String[] contenido=contenidoFile.split("\n");
//			for(String cad:contenido)
//				System.out.println("--> dias: "+cad);
			boolean valido=true;
//			System.out.println("tamanio-> "+mes.length()+", "+nroDiasMes(mes)+"<->"+(contenido.length-10));
			if(nroDiasMes(mes)!=(contenido.length-10))
			{
				valido=false;
				Clients.showNotification("El nro de dias del mes de "+mes+" no es "+(contenido.length-10),Clients.NOTIFICATION_TYPE_ERROR,null,"top_center",3000);
			}
			return valido;
		}
		public int nroDiasMes(String mes)
		{
			int n=-1;
			Calendar cal=Calendar.getInstance();
			int anio;
			if(Integer.parseInt(cbAnio_pdf.getValue())==cal.get(Calendar.YEAR)||
					Integer.parseInt(cbAnio.getValue())==cal.get(Calendar.YEAR))
				anio=cal.get(Calendar.YEAR);
			else
				anio=cal.get(Calendar.YEAR)+1;
			switch(mes)
			{
				case "ENERO":n=31;break;
				case "FEBRERO":
					if(anio%4==0 && (anio%100==0 || anio%400==0))n=29;
					else n=28;
					break;
				case "MARZO":n=31;break;
				case "ABRIL":n=30;break;
				case "MAYO":n=31;break;
				case "JUNIO":n=30;break;
				case "JULIO":n=31;break;
				case "AGOSTO":n=31;break;
				case "SEPTIEMBRE":n=30;break;
				case "OCTUBRE":n=31;break;
				case "NOVIEMBRE":n=30;break;
				case "DICIEMBRE":n=31;break;
			}
			return n;
		}
		public void sobreescribirFileTxt(String contentFile,int mes,String dispo) throws IOException
		{
			System.out.println("Este es el contenido del pdf aqui en sobreescribir txt :) \n"+contentFile);
	        Calendar cal=Calendar.getInstance();
	        File archivo=null;
	   
	        	if(Integer.parseInt(cbAnio_pdf.getValue())==cal.get(Calendar.YEAR))
				{
	        		int anioActual=cal.get(Calendar.YEAR);
	        		ArrayList<Integer> listaDispMes=new ArrayList<Integer>();
	        		ArrayList<Integer> listaDispMesAux=new ArrayList<Integer>();
	        		Map<Integer, String> map = new HashMap<Integer,String>();
	        		listaDispMes.add(Integer.parseInt(dispo));
	        		listaDispMes.add(anioActual);
	        		listaDispMes.add(mes);
	        		String[] s=contentFile.split("\n");
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
	    		        listaDispMesAux.add(Integer.parseInt(e.getValue()));
	    		        
	    		    }
	    		    for(Integer m:listaDispMesAux){
	    		    	listaDispMes.add(m);
	    		    }
	    		    if(mes==4 || mes==6 || mes==9 || mes==11){
	    		    	listaDispMes.add(-1);
	    			}else if(mes==2 && EsBiciesto(anioActual)){
	    				listaDispMes.add(-1);
	    				listaDispMes.add(-1);
	    			}else if(mes==2 && !EsBiciesto(anioActual)){
	    				listaDispMes.add(-1);
	    				listaDispMes.add(-1);
	    				listaDispMes.add(-1);
	    			}
	    		    CDisponibilidadYourselfDAO dispoYourDao=new CDisponibilidadYourselfDAO();
	    		    boolean correcto=dispoYourDao.isOperationCorrect(dispoYourDao.updateDisponibilidadBD(listaDispMes));
				}
				else
				{
					int anioSig=cal.get(Calendar.YEAR)+1;
	        		ArrayList<Integer> listaDispMes=new ArrayList<Integer>();
	        		ArrayList<Integer> listaDispMesAux=new ArrayList<Integer>();
	        		Map<Integer, String> map = new HashMap<Integer,String>();
	        		listaDispMes.add(Integer.parseInt(dispo));
	        		listaDispMes.add(anioSig);
	        		listaDispMes.add(mes);
	        		String[] s=contentFile.split("\n");
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
	    		        listaDispMesAux.add(Integer.parseInt(e.getValue()));
	    		        
	    		    }
	    		    for(Integer m:listaDispMesAux){
	    		    	listaDispMes.add(m);
	    		    }
	    		    if(mes==4 || mes==6 || mes==9 || mes==11){
	    		    	listaDispMes.add(-1);
	    			}else if(mes==2 && EsBiciesto(anioSig)){
	    				listaDispMes.add(-1);
	    				listaDispMes.add(-1);
	    			}else if(mes==2 && !EsBiciesto(anioSig)){
	    				listaDispMes.add(-1);
	    				listaDispMes.add(-1);
	    				listaDispMes.add(-1);
	    			}
	    		    CDisponibilidadYourselfDAO dispoYourDao=new CDisponibilidadYourselfDAO();
	    		    boolean correcto=dispoYourDao.isOperationCorrect(dispoYourDao.updateDisponibilidadBD(listaDispMes));
				}
		}
		public boolean EsBiciesto(int anio){
			boolean biciesto=false;
			if((anio % 4==0) && ((anio % 100 !=0)||(anio%400==0))){
				biciesto=true;
			}
			return biciesto;
		}
		public int obtenerNumeroDelMes(String mes)
		{
			int n=-1;
				//Esta condicional se hizo debido a que el nombre recuperado
				//de los meses de SEPTIEMBRE, NOVIEMBRE Y DICIEMBRE
				//tienen un espacio (" ") al final ejem. "DICIEMBRE " y no "DICIEMBRE"
				//imposibilitando la validacion en el switch
//			int ultimo=mes.length()-1;
//			if(mes.charAt(ultimo)<'A' || mes.charAt(ultimo)>'Z')
//			{
//				mes=mes.substring(0, ultimo);
//				System.out.println("----> "+mes);
//			}
			switch(mes)
			{
				case "ENERO":n=1;break;
				case "FEBRERO":n=2;break;
				case "MARZO":n=3;break;
				case "ABRIL":n=4;break;
				case "MAYO":n=5;break;
				case "JUNIO":n=6;break;
				case "JULIO":n=7;break;
				case "AGOSTO":n=8;break;
				case "SEPTIEMBRE":n=9;break;
				case "OCTUBRE":n=10;break;
				case "NOVIEMBRE":n=11;break;
				case "DICIEMBRE":n=12;break;
			}
			return n;
		}
		public String obtenerContenidoPDF(Media media)
		{
			String contenido="";
			//primeramente subimos el archivo al proyecto
			boolean b=Util.uploadFileAux(media);
			//Luego se procede a leer el pdf guardado para obtener el nombre del mes
			lectorPDF lpdf=new lectorPDF();
			lpdf.setFilePath(media.getName());
			try {
				contenido=lpdf.ToText();
				//Una vez obtenido el contenido del pdf se procede a eliminar este
				File fileDelete=new File(Util.getPathAuxDisp()+media.getName());
				if(fileDelete.delete())
					System.out.println("se elimino el pdf auxiliar");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return contenido;
		}
		public String obtenerMes(String contenidoPDF,String lbl)
		{
			String[] datos=contenidoPDF.split("\n",5);
			String[] auxDatos=datos[3].split(" ");
			for(int i=0;i<datos.length;i++)
			{
				System.out.println("====>:) "+datos[i]);
			}
			for(int i=0;i<auxDatos.length;i++)
			{
				System.out.println("====> "+auxDatos[i]);
			}
			//=================================
			if(lbl.equals("lblMensaje1"))
				lblMensaje1.setValue(auxDatos[0]);
			else
				lblMensaje2.setValue(auxDatos[0]);
			return auxDatos[0];
		}
		public void asignarNombreFile(String nombreFile,String tb)
		{
			if(tb.equals("txtNombreArchivo1"))
				txtNombreArchivo1.setValue(nombreFile);
			else
				txtNombreArchivo2.setValue(nombreFile);
			Clients.showNotification("El archivo "+nombreFile+" se subio correctamente",
					Clients.NOTIFICATION_TYPE_INFO, null,"top_center",3000);
		}
		public void inicializarAnios()
		{
			Calendar calIni=Calendar.getInstance();
//			cbDispo.setValue("Camino Inka 4 dias 3 noche 7:00 a.m.-10:00 a.m.");//Camino inka 4D 3N
			cbAnio_pdf.setValue(Integer.toString(calIni.get(Calendar.YEAR)));
			//----------------------------
			cbAnioActual_pdf.setLabel(Integer.toString(calIni.get(Calendar.YEAR)));
			cbAnioSig_pdf.setLabel(Integer.toString(calIni.get(Calendar.YEAR)+1));
		}
		@AfterCompose
		public void afterCompose(@ContextParam(ContextType.VIEW) Component view) throws WrongValueException, IOException
		{
			Selectors.wireComponents(view, this, false);
			inicializarAnios();
		}
}
