package com.pricing.viewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.SimpleCategoryModel;

import com.pricing.dao.CReporteReservaDAO;
import com.pricing.model.CEstadistica_Paquete;

public class CEstadisticaReservaVM {
	SimpleDateFormat df=new SimpleDateFormat("MM");
	private ArrayList<String> listaAnios;
	private boolean visibleGrafico;
	private String anio="";
	private long[] top1Paquetes={0,0,0,0,0,0,0,0,0,0,0,0};
	private long[] top2Paquetes={0,0,0,0,0,0,0,0,0,0,0,0};
	private long[] top3Paquetes={0,0,0,0,0,0,0,0,0,0,0,0};
	private String[] nombrestop1Paquetes={"","","","","","","","","","","",""};
	private String[] nombrestop2Paquetes={"","","","","","","","","","","",""};
	private String[] nombrestop3Paquetes={"","","","","","","","","","","",""};
	private CReporteReservaDAO reporteReservaDao;
    private ArrayList<CEstadistica_Paquete> listaPaquetesMasVendidos;
       //===============getter and setter======
   	
   	public CReporteReservaDAO getReporteReservaDao() {
   		return reporteReservaDao;
   	}

   	public void setReporteReservaDao(CReporteReservaDAO reporteReservaDao) {
   		this.reporteReservaDao = reporteReservaDao;
   	}

   	public ArrayList<CEstadistica_Paquete> getListaPaquetesMasVendidos() {
   		return listaPaquetesMasVendidos;
   	}

   	public void setListaPaquetesMasVendidos(
   			ArrayList<CEstadistica_Paquete> listaPaquetesMasVendidos) {
   		this.listaPaquetesMasVendidos = listaPaquetesMasVendidos;
   	}
   	
   	
	public boolean isVisibleGrafico() {
		return visibleGrafico;
	}

	public void setVisibleGrafico(boolean visibleGrafico) {
		this.visibleGrafico = visibleGrafico;
	}

	//=================getter and setter======
	public ArrayList<String> getListaAnios() {
		return listaAnios;
	}

	public void setListaAnios(ArrayList<String> listaAnios) {
		this.listaAnios = listaAnios;
	}
	
	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}
	

	public long[] getTop1Paquetes() {
		return top1Paquetes;
	}

	public void setTop1Paquetes(long[] top1Paquetes) {
		this.top1Paquetes = top1Paquetes;
	}

	public long[] getTop2Paquetes() {
		return top2Paquetes;
	}

	public void setTop2Paquetes(long[] top2Paquetes) {
		this.top2Paquetes = top2Paquetes;
	}

	public long[] getTop3Paquetes() {
		return top3Paquetes;
	}

	public void setTop3Paquetes(long[] top3Paquetes) {
		this.top3Paquetes = top3Paquetes;
	}

	public String[] getNombrestop1Paquetes() {
		return nombrestop1Paquetes;
	}

	public void setNombrestop1Paquetes(String[] nombrestop1Paquetes) {
		this.nombrestop1Paquetes = nombrestop1Paquetes;
	}

	public String[] getNombrestop2Paquetes() {
		return nombrestop2Paquetes;
	}

	public void setNombrestop2Paquetes(String[] nombrestop2Paquetes) {
		this.nombrestop2Paquetes = nombrestop2Paquetes;
	}

	public String[] getNombrestop3Paquetes() {
		return nombrestop3Paquetes;
	}

	public void setNombrestop3Paquetes(String[] nombrestop3Paquetes) {
		this.nombrestop3Paquetes = nombrestop3Paquetes;
	}

	//==============metodos=======
	@Init
	public void initVM()
	{
		/**Inicializando los objetos**/
		listaAnios=new ArrayList<String>();
		visibleGrafico=false;
		listaPaquetesMasVendidos=new ArrayList<CEstadistica_Paquete>();
		/**Obtencion de las etiquetas de la base de datos**/
		/**Asignacion de las etiquetas a la listaEtiquetas**/
		int primerAnio=2016;
		this.listaAnios.add(String.valueOf(primerAnio));
		Calendar fechaActual=Calendar.getInstance();
		int anioActual=fechaActual.get(Calendar.YEAR);
		while(primerAnio<anioActual){
			primerAnio=primerAnio+1;
			this.listaAnios.add(String.valueOf(primerAnio));
		}
		if(anioActual>primerAnio)
		{
			this.listaAnios.add(String.valueOf(anioActual));
		}
	}
	
	
	@Command
	@NotifyChange({"anio","grafica","visibleGrafico"})
	public void AsignarAnio(@BindingParam("anio")String anio)
	{
		visibleGrafico=true;
		this.anio=anio;
		
		procesarPaquetesMasVendidos();
	}
	
	@NotifyChange({"nombrestop1Paquetes","nombrestop2Paquetes","nombrestop3Paquetes","top1Paquetes","top2Paquetes","top3Paquetes","listaPaquetesMasVendidos"})
	public void procesarPaquetesMasVendidos()
	{
		top1Paquetes=new long[12];
		top2Paquetes=new long[12];
		top3Paquetes=new long[12];
		reporteReservaDao=new CReporteReservaDAO();
		reporteReservaDao.asignarPaquetesmasVendidos(reporteReservaDao.recuperarPaquetesMasVendidos(this.anio));
		this.setListaPaquetesMasVendidos(reporteReservaDao.getMasVendidosxMeses());
		if(this.listaPaquetesMasVendidos.isEmpty())
		{
			Clients.showNotification("No hay venta de paquetes");
			return;
		}
		//======meses del anio con inicio y fin=====
		String nombrePaqueteAnterior;
		int factorIncremento;
		int valorActual;
		for(int i=0;i<listaPaquetesMasVendidos.size();i=i+factorIncremento)
		{
			factorIncremento=0;
			valorActual=0;
			nombrePaqueteAnterior="";
			if(df.format(listaPaquetesMasVendidos.get(i).getFechaPago()).equals("01"))
			{
				long auxCambio;
				String auxNombreCambio="";
				int contador=i;
				nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
				while(contador<listaPaquetesMasVendidos.size() && listaPaquetesMasVendidos.get(contador).getNombrePaquete().equals(nombrePaqueteAnterior) && (df.format(listaPaquetesMasVendidos.get(contador).getFechaPago()).equals("01")))
				{
					valorActual+=listaPaquetesMasVendidos.get(i).getNroVentas();
					contador++;
					factorIncremento++;
				}
				if(valorActual>top1Paquetes[0]){
					top1Paquetes[0]=valorActual;
					nombrestop1Paquetes[0]=nombrePaqueteAnterior;
				}
				else if(valorActual>top2Paquetes[0]){
					top2Paquetes[0]=valorActual;
					nombrestop2Paquetes[0]=nombrePaqueteAnterior;
				}else if(valorActual>top3Paquetes[0]){
					top3Paquetes[0]=valorActual;
					nombrestop3Paquetes[0]=nombrePaqueteAnterior;
				}
			}else
				if(df.format(listaPaquetesMasVendidos.get(i).getFechaPago()).equals("02"))
				{
					long auxCambio;
					String auxNombreCambio="";
					int contador=i;
					nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					while(contador<listaPaquetesMasVendidos.size() && listaPaquetesMasVendidos.get(contador).getNombrePaquete().equals(nombrePaqueteAnterior) && (df.format(listaPaquetesMasVendidos.get(contador).getFechaPago()).equals("02")))
					{
						valorActual+=listaPaquetesMasVendidos.get(i).getNroVentas();
						contador++;
						factorIncremento++;
					}
					if(valorActual>top1Paquetes[1]){
						top1Paquetes[1]=valorActual;
						nombrestop1Paquetes[1]=nombrePaqueteAnterior;
					}
					else if(valorActual>top2Paquetes[1]){
						top2Paquetes[1]=valorActual;
						nombrestop2Paquetes[1]=nombrePaqueteAnterior;
					}else if(valorActual>top3Paquetes[1]){
						top3Paquetes[1]=valorActual;
						nombrestop3Paquetes[1]=nombrePaqueteAnterior;
					}
			}else
				if(df.format(listaPaquetesMasVendidos.get(i).getFechaPago()).equals("03"))
				{
					long auxCambio;
					String auxNombreCambio="";
					int contador=i;
					nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					while(contador<listaPaquetesMasVendidos.size() && listaPaquetesMasVendidos.get(contador).getNombrePaquete().equals(nombrePaqueteAnterior) && (df.format(listaPaquetesMasVendidos.get(contador).getFechaPago()).equals("03")))
					{
						valorActual+=listaPaquetesMasVendidos.get(i).getNroVentas();
						contador++;
						factorIncremento++;
					}
					if(valorActual>top1Paquetes[2]){
						top1Paquetes[2]=valorActual;
						nombrestop1Paquetes[2]=nombrePaqueteAnterior;
					}
					else if(valorActual>top2Paquetes[2]){
						top2Paquetes[2]=valorActual;
						nombrestop2Paquetes[2]=nombrePaqueteAnterior;
					}else if(valorActual>top3Paquetes[2]){
						top3Paquetes[2]=valorActual;
						nombrestop3Paquetes[2]=nombrePaqueteAnterior;
					}
			}else
				if(df.format(listaPaquetesMasVendidos.get(i).getFechaPago()).equals("04"))
				{
					long auxCambio;
					String auxNombreCambio="";
					int contador=i;
					nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					while(contador<listaPaquetesMasVendidos.size() && listaPaquetesMasVendidos.get(contador).getNombrePaquete().equals(nombrePaqueteAnterior) && (df.format(listaPaquetesMasVendidos.get(contador).getFechaPago()).equals("04")))
					{
						valorActual+=listaPaquetesMasVendidos.get(i).getNroVentas();
						contador++;
						factorIncremento++;
					}
					if(valorActual>top1Paquetes[3]){
						top1Paquetes[3]=valorActual;
						nombrestop1Paquetes[3]=nombrePaqueteAnterior;
					}
					else if(valorActual>top2Paquetes[3]){
						top2Paquetes[3]=valorActual;
						nombrestop2Paquetes[3]=nombrePaqueteAnterior;
					}else if(valorActual>top3Paquetes[3]){
						top3Paquetes[3]=valorActual;
						nombrestop3Paquetes[3]=nombrePaqueteAnterior;
					}
			}else
				if(df.format(listaPaquetesMasVendidos.get(i).getFechaPago()).equals("05"))
				{
					long auxCambio;
					String auxNombreCambio="";
					int contador=i;
					nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					while(contador<listaPaquetesMasVendidos.size() && listaPaquetesMasVendidos.get(contador).getNombrePaquete().equals(nombrePaqueteAnterior) && (df.format(listaPaquetesMasVendidos.get(contador).getFechaPago()).equals("05")))
					{
						valorActual+=listaPaquetesMasVendidos.get(i).getNroVentas();
						contador++;
						factorIncremento++;
					}
					if(valorActual>top1Paquetes[4]){
						top1Paquetes[4]=valorActual;
						nombrestop1Paquetes[4]=nombrePaqueteAnterior;
					}
					else if(valorActual>top2Paquetes[4]){
						top2Paquetes[4]=valorActual;
						nombrestop2Paquetes[4]=nombrePaqueteAnterior;
					}else if(valorActual>top3Paquetes[4]){
						top3Paquetes[4]=valorActual;
						nombrestop3Paquetes[4]=nombrePaqueteAnterior;
					}
			}else
				if(df.format(listaPaquetesMasVendidos.get(i).getFechaPago()).equals("06"))
				{
					long auxCambio;
					String auxNombreCambio="";
					int contador=i;
					nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					while(contador<listaPaquetesMasVendidos.size() && listaPaquetesMasVendidos.get(contador).getNombrePaquete().equals(nombrePaqueteAnterior) && (df.format(listaPaquetesMasVendidos.get(contador).getFechaPago()).equals("06")))
					{
						valorActual+=listaPaquetesMasVendidos.get(i).getNroVentas();
						contador++;
						factorIncremento++;
					}
					if(valorActual>top1Paquetes[5]){
						top1Paquetes[5]=valorActual;
						nombrestop1Paquetes[5]=nombrePaqueteAnterior;
					}
					else if(valorActual>top2Paquetes[5]){
						top2Paquetes[5]=valorActual;
						nombrestop2Paquetes[5]=nombrePaqueteAnterior;
					}else if(valorActual>top3Paquetes[5]){
						top3Paquetes[5]=valorActual;
						nombrestop3Paquetes[5]=nombrePaqueteAnterior;
					}
			}else
				if(df.format(listaPaquetesMasVendidos.get(i).getFechaPago()).equals("07"))
				{
					long auxCambio;
					String auxNombreCambio="";
					int contador=i;
					nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					while(contador<listaPaquetesMasVendidos.size() && listaPaquetesMasVendidos.get(contador).getNombrePaquete().equals(nombrePaqueteAnterior) && (df.format(listaPaquetesMasVendidos.get(contador).getFechaPago()).equals("07")))
					{
						valorActual+=listaPaquetesMasVendidos.get(i).getNroVentas();
						contador++;
						factorIncremento++;
					}
					if(valorActual>top1Paquetes[6]){
						top1Paquetes[6]=valorActual;
						nombrestop1Paquetes[6]=nombrePaqueteAnterior;
					}
					else if(valorActual>top2Paquetes[6]){
						top2Paquetes[6]=valorActual;
						nombrestop2Paquetes[6]=nombrePaqueteAnterior;
					}else if(valorActual>top3Paquetes[6]){
						top3Paquetes[6]=valorActual;
						nombrestop3Paquetes[6]=nombrePaqueteAnterior;
					}
			}else
				if(df.format(listaPaquetesMasVendidos.get(i).getFechaPago()).equals("08"))
				{
					long auxCambio;
					String auxNombreCambio="";
					int contador=i;
					nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					while(contador<listaPaquetesMasVendidos.size() && listaPaquetesMasVendidos.get(contador).getNombrePaquete().equals(nombrePaqueteAnterior) && (df.format(listaPaquetesMasVendidos.get(contador).getFechaPago()).equals("08")))
					{
						valorActual+=listaPaquetesMasVendidos.get(i).getNroVentas();
						contador++;
						factorIncremento++;
					}
					if(valorActual>top1Paquetes[7]){
						top1Paquetes[7]=valorActual;
						nombrestop1Paquetes[7]=nombrePaqueteAnterior;
					}
					else if(valorActual>top2Paquetes[7]){
						top2Paquetes[7]=valorActual;
						nombrestop2Paquetes[7]=nombrePaqueteAnterior;
					}else if(valorActual>top3Paquetes[7]){
						top3Paquetes[7]=valorActual;
						nombrestop3Paquetes[7]=nombrePaqueteAnterior;
					}
			}else
				if(df.format(listaPaquetesMasVendidos.get(i).getFechaPago()).equals("09"))
				{
					long auxCambio;
					String auxNombreCambio="";
					int contador=i;
					nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					while(contador<listaPaquetesMasVendidos.size() && listaPaquetesMasVendidos.get(contador).getNombrePaquete().equals(nombrePaqueteAnterior) && (df.format(listaPaquetesMasVendidos.get(contador).getFechaPago()).equals("09")))
					{
						valorActual+=listaPaquetesMasVendidos.get(i).getNroVentas();
						contador++;
						factorIncremento++;
					}
					if(valorActual>top1Paquetes[0]){
						top1Paquetes[8]=valorActual;
						nombrestop1Paquetes[8]=nombrePaqueteAnterior;
					}
					else if(valorActual>top2Paquetes[8]){
						top2Paquetes[8]=valorActual;
						nombrestop2Paquetes[8]=nombrePaqueteAnterior;
					}else if(valorActual>top3Paquetes[8]){
						top3Paquetes[8]=valorActual;
						nombrestop3Paquetes[8]=nombrePaqueteAnterior;
					}
			}else
			if(df.format(listaPaquetesMasVendidos.get(i).getFechaPago()).equals("10"))
				{
				long auxCambio;
				String auxNombreCambio="";
				int contador=i;
				nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
				while(contador<listaPaquetesMasVendidos.size() && listaPaquetesMasVendidos.get(contador).getNombrePaquete().equals(nombrePaqueteAnterior) && (df.format(listaPaquetesMasVendidos.get(contador).getFechaPago()).equals("10")))
				{
					valorActual+=listaPaquetesMasVendidos.get(i).getNroVentas();
					contador++;
					factorIncremento++;
				}
				if(valorActual>top1Paquetes[9]){
					top1Paquetes[9]=valorActual;
					nombrestop1Paquetes[9]=nombrePaqueteAnterior;
				}
				else if(valorActual>top2Paquetes[9]){
					top2Paquetes[9]=valorActual;
					nombrestop2Paquetes[9]=nombrePaqueteAnterior;
				}else if(valorActual>top3Paquetes[9]){
					top3Paquetes[9]=valorActual;
					nombrestop3Paquetes[9]=nombrePaqueteAnterior;
				}
			}else
				if(df.format(listaPaquetesMasVendidos.get(i).getFechaPago()).equals("11"))
				{
					long auxCambio;
					String auxNombreCambio="";
					int contador=i;
					nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					while(contador<listaPaquetesMasVendidos.size() && listaPaquetesMasVendidos.get(contador).getNombrePaquete().equals(nombrePaqueteAnterior) && (df.format(listaPaquetesMasVendidos.get(contador).getFechaPago()).equals("11")))
					{
						valorActual+=listaPaquetesMasVendidos.get(i).getNroVentas();
						contador++;
						factorIncremento++;
					}
					if(valorActual>top1Paquetes[10]){
						top1Paquetes[10]=valorActual;
						nombrestop1Paquetes[10]=nombrePaqueteAnterior;
					}
					else if(valorActual>top2Paquetes[10]){
						top2Paquetes[10]=valorActual;
						nombrestop2Paquetes[10]=nombrePaqueteAnterior;
					}else if(valorActual>top3Paquetes[10]){
						top3Paquetes[10]=valorActual;
						nombrestop3Paquetes[10]=nombrePaqueteAnterior;
					}
			}else
				if(df.format(listaPaquetesMasVendidos.get(i).getFechaPago()).equals("12"))
				{
					long auxCambio;
					String auxNombreCambio="";
					int contador=i;
					nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					while(contador<listaPaquetesMasVendidos.size() && listaPaquetesMasVendidos.get(contador).getNombrePaquete().equals(nombrePaqueteAnterior) && (df.format(listaPaquetesMasVendidos.get(contador).getFechaPago()).equals("12")))
					{
						valorActual+=listaPaquetesMasVendidos.get(i).getNroVentas();
						contador++;
						factorIncremento++;
					}
					String valor=yaexistePaquete(nombrestop1Paquetes.toString(),nombrestop2Paquetes.toString(),nombrestop3Paquetes.toString(), nombrePaqueteAnterior);
					if(valorActual>top1Paquetes[11]){
						top1Paquetes[11]=valorActual;
						nombrestop1Paquetes[11]=nombrePaqueteAnterior;
					}
					else if(valorActual>top2Paquetes[11]){
						top2Paquetes[11]=valorActual;
						nombrestop2Paquetes[11]=nombrePaqueteAnterior;
					}else if(valorActual>top3Paquetes[11]){
						top3Paquetes[11]=valorActual;
						nombrestop3Paquetes[11]=nombrePaqueteAnterior;
					}
			}
		}
	}
	
	public String yaexistePaquete(String nombres1,String nombres2,String nombres3,String nombre){
		String retorno="";
			if(nombres1.equals(nombre)){
				retorno=nombres1;
			}else if(nombres2.equals(nombre)){
				retorno=nombres2;
			}else if(nombres3.equals(nombre)){
				retorno=nombres3;
			}
		return retorno;
	}
	
	@NotifyChange({"anio","grafica"})
	public SimpleCategoryModel getGrafica(){
        System.out.println("entra aqui grafica..?");
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", 
                          "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        SimpleCategoryModel demoModel = new SimpleCategoryModel();
            for(int j=0; j<12; j++){               
                if(j==0) {
                    demoModel.setValue(this.nombrestop1Paquetes[j], meses[j], this.top1Paquetes[j]);
                    demoModel.setValue(this.nombrestop3Paquetes[j], meses[j], this.top3Paquetes[j]);
                    demoModel.setValue(this.nombrestop2Paquetes[j], meses[j], this.top2Paquetes[j]);
                }
                if(j==1) {
                    demoModel.setValue(this.nombrestop1Paquetes[j], meses[j], this.top1Paquetes[j]);
                    demoModel.setValue(this.nombrestop3Paquetes[j], meses[j], this.top3Paquetes[j]);
                    demoModel.setValue(this.nombrestop2Paquetes[j], meses[j], this.top2Paquetes[j]);
                }
                if(j==2) {
                    demoModel.setValue(this.nombrestop1Paquetes[j], meses[j], this.top1Paquetes[j]);
                    demoModel.setValue(this.nombrestop3Paquetes[j], meses[j], this.top3Paquetes[j]);
                    demoModel.setValue(this.nombrestop2Paquetes[j], meses[j], this.top2Paquetes[j]);
                }
                if(j==3) {
                    demoModel.setValue(this.nombrestop1Paquetes[j], meses[j], this.top1Paquetes[j]);
                    demoModel.setValue(this.nombrestop3Paquetes[j], meses[j], this.top3Paquetes[j]);
                    demoModel.setValue(this.nombrestop2Paquetes[j], meses[j], this.top2Paquetes[j]);
                }
                if(j==4) {
                    demoModel.setValue(this.nombrestop1Paquetes[j], meses[j], this.top1Paquetes[j]);
                    demoModel.setValue(this.nombrestop3Paquetes[j], meses[j], this.top3Paquetes[j]);
                    demoModel.setValue(this.nombrestop2Paquetes[j], meses[j], this.top2Paquetes[j]);
                }
                if(j==5) {
                    demoModel.setValue(this.nombrestop1Paquetes[j], meses[j], this.top1Paquetes[j]);
                    demoModel.setValue(this.nombrestop3Paquetes[j], meses[j], this.top3Paquetes[j]);
                    demoModel.setValue(this.nombrestop2Paquetes[j], meses[j], this.top2Paquetes[j]);
                }
                if(j==6) {
                    demoModel.setValue(this.nombrestop1Paquetes[j], meses[j], this.top1Paquetes[j]);
                    demoModel.setValue(this.nombrestop3Paquetes[j], meses[j], this.top3Paquetes[j]);
                    demoModel.setValue(this.nombrestop2Paquetes[j], meses[j], this.top2Paquetes[j]);
                }
                if(j==7) {
                    demoModel.setValue(this.nombrestop1Paquetes[j], meses[j], this.top1Paquetes[j]);
                    demoModel.setValue(this.nombrestop3Paquetes[j], meses[j], this.top3Paquetes[j]);
                    demoModel.setValue(this.nombrestop2Paquetes[j], meses[j], this.top2Paquetes[j]);
                }
                if(j==8) {
                    demoModel.setValue(this.nombrestop1Paquetes[j], meses[j], this.top1Paquetes[j]);
                    demoModel.setValue(this.nombrestop3Paquetes[j], meses[j], this.top3Paquetes[j]);
                    demoModel.setValue(this.nombrestop2Paquetes[j], meses[j], this.top2Paquetes[j]);
                }
                if(j==9) {
                    demoModel.setValue(this.nombrestop1Paquetes[j], meses[j], this.top1Paquetes[j]);
                    demoModel.setValue(this.nombrestop3Paquetes[j], meses[j], this.top3Paquetes[j]);
                    demoModel.setValue(this.nombrestop2Paquetes[j], meses[j], this.top2Paquetes[j]);
                }
                if(j==10) {
                    demoModel.setValue(this.nombrestop1Paquetes[j], meses[j], this.top1Paquetes[j]);
                    demoModel.setValue(this.nombrestop3Paquetes[j], meses[j], this.top3Paquetes[j]);
                    demoModel.setValue(this.nombrestop2Paquetes[j], meses[j], this.top2Paquetes[j]);
                }
                if(j==11) {
                    demoModel.setValue(this.nombrestop1Paquetes[j], meses[j], this.top1Paquetes[j]);
                    demoModel.setValue(this.nombrestop3Paquetes[j], meses[j], this.top3Paquetes[j]);
                    demoModel.setValue(this.nombrestop2Paquetes[j], meses[j], this.top2Paquetes[j]);
                }
            }
        return demoModel;
    }
	
}
