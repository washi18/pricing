package com.pricing.viewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.SimpleCategoryModel;

import com.pricing.dao.CReportePagosDAO;
import com.pricing.dao.CReporteReservaDAO;
import com.pricing.model.CEstadistica_Pagos;

public class CEstadisticaPagosVM {
	//=====atributos=====
	SimpleDateFormat df=new SimpleDateFormat("MM");
   private ArrayList<String> listaAnios;
   private boolean visibleGrafico;
   private String anio="";
   private CReportePagosDAO reportePagosDAO;
   private ArrayList<CEstadistica_Pagos> listaFormasPago;
   private long [] nroTransaccionesVisa=new long[12];
   private long []  nroTransaccionesPaypal=new long[12];
   //=======gettter and setter======
	public ArrayList<String> getListaAnios() {
		return listaAnios;
	}
	public void setListaAnios(ArrayList<String> listaAnios) {
		this.listaAnios = listaAnios;
	}
	public boolean isVisibleGrafico() {
		return visibleGrafico;
	}
	public void setVisibleGrafico(boolean visibleGrafico) {
		this.visibleGrafico = visibleGrafico;
	}
	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}
	public CReportePagosDAO getReportePagosDAO() {
		return reportePagosDAO;
	}
	public void setReportePagosDAO(CReportePagosDAO reportePagosDAO) {
		this.reportePagosDAO = reportePagosDAO;
	}
	public ArrayList<CEstadistica_Pagos> getListaFormasPago() {
		return listaFormasPago;
	}
	public void setListaFormasPago(ArrayList<CEstadistica_Pagos> listaFormasPago) {
		this.listaFormasPago = listaFormasPago;
	}
	
   public long[] getNroTransaccionesVisa() {
		return nroTransaccionesVisa;
	}
	public void setNroTransaccionesVisa(long[] nroTransaccionesVisa) {
		this.nroTransaccionesVisa = nroTransaccionesVisa;
	}
	public long[] getNroTransaccionesPaypal() {
		return nroTransaccionesPaypal;
	}
	public void setNroTransaccionesPaypal(long[] nroTransaccionesPaypal) {
		this.nroTransaccionesPaypal = nroTransaccionesPaypal;
	}
	//===========metodos==========
	@Init
	public void initVM()
	{
		/**Inicializando los objetos**/
		listaAnios=new ArrayList<String>();
		visibleGrafico=false;
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
		procesarFormaPago();
	}
	
	@NotifyChange({"nroTransaccionesPaypal","nroTransaccionesVisa"})
	public void procesarFormaPago()
	{
		reportePagosDAO=new CReportePagosDAO();
		System.out.println("el anio es:"+this.anio);
		reportePagosDAO.asignarListaPagos(reportePagosDAO.recuperarListaFormasPagosBD(this.anio));
		this.setListaFormasPago(reportePagosDAO.getListaformasPagos());
		if(this.listaFormasPago.isEmpty()){
			Clients.showNotification("No hay pagos");
			return;
		}
		String nombreFormaPagoAnterior=listaFormasPago.get(0).getFormaPago();
		int factorIncremento;
		for (int i = 0; i < listaFormasPago.size(); i = i + factorIncremento) {
			factorIncremento = 0;
			if (df.format(listaFormasPago.get(i).getFechaPago()).equals("01")) {
				System.out.println("Estamos en enero");
				int contador = i;
				nombreFormaPagoAnterior = listaFormasPago.get(i).getFormaPago();
				while (contador < listaFormasPago.size()
						&& listaFormasPago.get(contador).getFormaPago()
								.equals(nombreFormaPagoAnterior)
						&& (df.format(listaFormasPago.get(contador)
								.getFechaPago()).equals("01"))) {
					if (listaFormasPago.get(contador).getFormaPago()
							.equals("PAYPAL")) {
						nroTransaccionesPaypal[0]++;
					} else {
						nroTransaccionesVisa[0]++;
					}
					contador++;
					factorIncremento++;
				}
				System.out.println("valor de contador" + contador);
				System.out.println("valor de factor incrmento"
						+ factorIncremento);
			} else if (df.format(listaFormasPago.get(i).getFechaPago()).equals(
					"02")) {
				System.out.println("Estamos en enero");
				int contador = i;
				nombreFormaPagoAnterior = listaFormasPago.get(i).getFormaPago();
				while (contador < listaFormasPago.size()
						&& listaFormasPago.get(contador).getFormaPago()
								.equals(nombreFormaPagoAnterior)
						&& (df.format(listaFormasPago.get(contador)
								.getFechaPago()).equals("02"))) {
					if (listaFormasPago.get(contador).getFormaPago()
							.equals("PAYPAL")) {
						nroTransaccionesPaypal[1]++;
					} else {
						nroTransaccionesVisa[1]++;
					}
					contador++;
					factorIncremento++;
				}
				System.out.println("valor de contador" + contador);
				System.out.println("valor de factor incrmento"
						+ factorIncremento);
			} else if (df.format(listaFormasPago.get(i).getFechaPago()).equals(
					"03")) {
				System.out.println("Estamos en enero");
				int contador = i;
				nombreFormaPagoAnterior = listaFormasPago.get(i).getFormaPago();
				while (contador < listaFormasPago.size()
						&& listaFormasPago.get(contador).getFormaPago()
								.equals(nombreFormaPagoAnterior)
						&& (df.format(listaFormasPago.get(contador)
								.getFechaPago()).equals("03"))) {
					if (listaFormasPago.get(contador).getFormaPago()
							.equals("PAYPAL")) {
						nroTransaccionesPaypal[2]++;
					} else {
						nroTransaccionesVisa[2]++;
					}
					contador++;
					factorIncremento++;
				}
				System.out.println("valor de contador" + contador);
				System.out.println("valor de factor incrmento"
						+ factorIncremento);
			} else if (df.format(listaFormasPago.get(i).getFechaPago()).equals(
					"04")) {
				System.out.println("Estamos en enero");
				int contador = i;
				nombreFormaPagoAnterior = listaFormasPago.get(i).getFormaPago();
				while (contador < listaFormasPago.size()
						&& listaFormasPago.get(contador).getFormaPago()
								.equals(nombreFormaPagoAnterior)
						&& (df.format(listaFormasPago.get(contador)
								.getFechaPago()).equals("04"))) {
					if (listaFormasPago.get(contador).getFormaPago()
							.equals("PAYPAL")) {
						nroTransaccionesPaypal[3]++;
					} else {
						nroTransaccionesVisa[3]++;
					}
					contador++;
					factorIncremento++;
				}
				System.out.println("valor de contador" + contador);
				System.out.println("valor de factor incrmento"
						+ factorIncremento);
			} else if (df.format(listaFormasPago.get(i).getFechaPago()).equals(
					"05")) {
				System.out.println("Estamos en enero");
				int contador = i;
				nombreFormaPagoAnterior = listaFormasPago.get(i).getFormaPago();
				while (contador < listaFormasPago.size()
						&& listaFormasPago.get(contador).getFormaPago()
								.equals(nombreFormaPagoAnterior)
						&& (df.format(listaFormasPago.get(contador)
								.getFechaPago()).equals("05"))) {
					if (listaFormasPago.get(contador).getFormaPago()
							.equals("PAYPAL")) {
						nroTransaccionesPaypal[4]++;
					} else {
						nroTransaccionesVisa[4]++;
					}
					contador++;
					factorIncremento++;
				}
				System.out.println("valor de contador" + contador);
				System.out.println("valor de factor incrmento"
						+ factorIncremento);
			} else if (df.format(listaFormasPago.get(i).getFechaPago()).equals(
					"06")) {
				System.out.println("Estamos en enero");
				int contador = i;
				nombreFormaPagoAnterior = listaFormasPago.get(i).getFormaPago();
				while (contador < listaFormasPago.size()
						&& listaFormasPago.get(contador).getFormaPago()
								.equals(nombreFormaPagoAnterior)
						&& (df.format(listaFormasPago.get(contador)
								.getFechaPago()).equals("06"))) {
					if (listaFormasPago.get(contador).getFormaPago()
							.equals("PAYPAL")) {
						nroTransaccionesPaypal[5]++;
					} else {
						nroTransaccionesVisa[5]++;
					}
					contador++;
					factorIncremento++;
				}
				System.out.println("valor de contador" + contador);
				System.out.println("valor de factor incrmento"
						+ factorIncremento);
			} else if (df.format(listaFormasPago.get(i).getFechaPago()).equals(
					"07")) {
				System.out.println("Estamos en enero");
				int contador = i;
				nombreFormaPagoAnterior = listaFormasPago.get(i).getFormaPago();
				while (contador < listaFormasPago.size()
						&& listaFormasPago.get(contador).getFormaPago()
								.equals(nombreFormaPagoAnterior)
						&& (df.format(listaFormasPago.get(contador)
								.getFechaPago()).equals("07"))) {
					if (listaFormasPago.get(contador).getFormaPago()
							.equals("PAYPAL")) {
						nroTransaccionesPaypal[6]++;
					} else {
						nroTransaccionesVisa[6]++;
					}
					contador++;
					factorIncremento++;
				}
				System.out.println("valor de contador" + contador);
				System.out.println("valor de factor incrmento"
						+ factorIncremento);
			} else if (df.format(listaFormasPago.get(i).getFechaPago()).equals(
					"08")) {
				System.out.println("Estamos en enero");
				int contador = i;
				nombreFormaPagoAnterior = listaFormasPago.get(i).getFormaPago();
				while (contador < listaFormasPago.size()
						&& listaFormasPago.get(contador).getFormaPago()
								.equals(nombreFormaPagoAnterior)
						&& (df.format(listaFormasPago.get(contador)
								.getFechaPago()).equals("08"))) {
					if (listaFormasPago.get(contador).getFormaPago()
							.equals("PAYPAL")) {
						nroTransaccionesPaypal[7]++;
					} else {
						nroTransaccionesVisa[7]++;
					}
					contador++;
					factorIncremento++;
				}
				System.out.println("valor de contador" + contador);
				System.out.println("valor de factor incrmento"
						+ factorIncremento);
			} else if (df.format(listaFormasPago.get(i).getFechaPago()).equals(
					"09")) {
				System.out.println("Estamos en enero");
				int contador = i;
				nombreFormaPagoAnterior = listaFormasPago.get(i).getFormaPago();
				while (contador < listaFormasPago.size()
						&& listaFormasPago.get(contador).getFormaPago()
								.equals(nombreFormaPagoAnterior)
						&& (df.format(listaFormasPago.get(contador)
								.getFechaPago()).equals("09"))) {
					if (listaFormasPago.get(contador).getFormaPago()
							.equals("PAYPAL")) {
						nroTransaccionesPaypal[8]++;
					} else {
						nroTransaccionesVisa[8]++;
					}
					contador++;
					factorIncremento++;
				}
				System.out.println("valor de contador" + contador);
				System.out.println("valor de factor incrmento"
						+ factorIncremento);
			} else if (df.format(listaFormasPago.get(i).getFechaPago()).equals(
					"10")) {
				System.out.println("Estamos en enero");
				int contador = i;
				nombreFormaPagoAnterior = listaFormasPago.get(i).getFormaPago();
				while (contador < listaFormasPago.size()
						&& listaFormasPago.get(contador).getFormaPago()
								.equals(nombreFormaPagoAnterior)
						&& (df.format(listaFormasPago.get(contador)
								.getFechaPago()).equals("10"))) {
					if (listaFormasPago.get(contador).getFormaPago()
							.equals("PAYPAL")) {
						nroTransaccionesPaypal[9]++;
					} else {
						nroTransaccionesVisa[9]++;
					}
					contador++;
					factorIncremento++;
				}
				System.out.println("valor de contador" + contador);
				System.out.println("valor de factor incrmento"
						+ factorIncremento);
			} else if (df.format(listaFormasPago.get(i).getFechaPago()).equals(
					"11")) {
				System.out.println("Estamos en enero");
				int contador = i;
				nombreFormaPagoAnterior = listaFormasPago.get(i).getFormaPago();
				while (contador < listaFormasPago.size()
						&& listaFormasPago.get(contador).getFormaPago()
								.equals(nombreFormaPagoAnterior)
						&& (df.format(listaFormasPago.get(contador)
								.getFechaPago()).equals("11"))) {
					if (listaFormasPago.get(contador).getFormaPago()
							.equals("PAYPAL")) {
						nroTransaccionesPaypal[10]++;
					} else {
						nroTransaccionesVisa[10]++;
					}
					contador++;
					factorIncremento++;
				}
				System.out.println("valor de contador" + contador);
				System.out.println("valor de factor incrmento"
						+ factorIncremento);
			} else if (df.format(listaFormasPago.get(i).getFechaPago()).equals(
					"12")) {
				System.out.println("Estamos en enero");
				int contador = i;
				nombreFormaPagoAnterior = listaFormasPago.get(i).getFormaPago();
				while (contador < listaFormasPago.size()
						&& listaFormasPago.get(contador).getFormaPago()
								.equals(nombreFormaPagoAnterior)
						&& (df.format(listaFormasPago.get(contador)
								.getFechaPago()).equals("12"))) {
					if (listaFormasPago.get(contador).getFormaPago()
							.equals("PAYPAL")) {
						nroTransaccionesPaypal[11]++;
					} else {
						nroTransaccionesVisa[11]++;
					}
					contador++;
					factorIncremento++;
				}
				System.out.println("valor de contador" + contador);
				System.out.println("valor de factor incrmento"
						+ factorIncremento);
			}
		}
	}
	
	@NotifyChange({"anio","grafica"})
	public SimpleCategoryModel getGrafica(){
        System.out.println("entra aqui grafica..?");
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", 
                          "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        SimpleCategoryModel demoModel = new SimpleCategoryModel();
            for(int j=0; j<12; j++){               
                if(j==0) {
                    demoModel.setValue("VISA", meses[j], this.nroTransaccionesVisa[0]);
                    demoModel.setValue("PAYPAL", meses[j], this.nroTransaccionesPaypal[0]);
                }
                if(j==1) {
                	demoModel.setValue("VISA", meses[j], this.nroTransaccionesVisa[1]);
                    demoModel.setValue("PAYPAL", meses[j], this.nroTransaccionesPaypal[1]);
                }
                if(j==2) {
                	demoModel.setValue("VISA", meses[j], this.nroTransaccionesVisa[2]);
                    demoModel.setValue("PAYPAL", meses[j], this.nroTransaccionesPaypal[2]);
                }
                if(j==3) {
                	demoModel.setValue("VISA", meses[j], this.nroTransaccionesVisa[3]);
                    demoModel.setValue("PAYPAL", meses[j], this.nroTransaccionesPaypal[3]);
                }
                if(j==4) {
                	demoModel.setValue("VISA", meses[j], this.nroTransaccionesVisa[4]);
                    demoModel.setValue("PAYPAL", meses[j], this.nroTransaccionesPaypal[4]);
                }
                if(j==5) {
                	demoModel.setValue("VISA", meses[j], this.nroTransaccionesVisa[5]);
                    demoModel.setValue("PAYPAL", meses[j], this.nroTransaccionesPaypal[5]);
                }
                if(j==6) {
                	demoModel.setValue("VISA", meses[j], this.nroTransaccionesVisa[6]);
                    demoModel.setValue("PAYPAL", meses[j], this.nroTransaccionesPaypal[6]);
                }
                if(j==7) {
                	demoModel.setValue("VISA", meses[j], this.nroTransaccionesVisa[7]);
                    demoModel.setValue("PAYPAL", meses[j], this.nroTransaccionesPaypal[7]);
                }
                if(j==8) {
                	demoModel.setValue("VISA", meses[j], this.nroTransaccionesVisa[8]);
                    demoModel.setValue("PAYPAL", meses[j], this.nroTransaccionesPaypal[8]);
                }
                if(j==9) {
                	demoModel.setValue("VISA", meses[j], this.nroTransaccionesVisa[9]);
                    demoModel.setValue("PAYPAL", meses[j], this.nroTransaccionesPaypal[9]);
                }
                if(j==10) {
                	demoModel.setValue("VISA", meses[j], this.nroTransaccionesVisa[10]);
                    demoModel.setValue("PAYPAL", meses[j], this.nroTransaccionesPaypal[10]);
                }
                if(j==11) {
                	demoModel.setValue("VISA", meses[j], this.nroTransaccionesVisa[11]);
                    demoModel.setValue("PAYPAL", meses[j], this.nroTransaccionesPaypal[11]);
                }
                System.out.println("termina grafica..?");
            }
        return demoModel;
    }
}
