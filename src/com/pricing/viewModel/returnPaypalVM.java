package com.pricing.viewModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.Clients;

import urn.ebay.api.PayPalAPI.GetExpressCheckoutDetailsResponseType;
import urn.ebay.apis.CoreComponentTypes.BasicAmountType;

import com.lowagie.text.DocumentException;
import com.pricing.dao.CConfigUrlDAO;
import com.pricing.dao.CEtiquetaDAO;
import com.pricing.dao.CFechaAlternaDAO;
import com.pricing.dao.CInterfazDAO;
import com.pricing.dao.CPaqueteDAO;
import com.pricing.dao.CPasajeroDAO;
import com.pricing.dao.CReservaCuponDAO;
import com.pricing.dao.CReservaDAO;
import com.pricing.dao.CReservaPaqueteActividadDAO;
import com.pricing.dao.CReservaPaqueteCategoriaHotelDAO;
import com.pricing.dao.CReservaPaqueteDAO;
import com.pricing.dao.CReservaPaqueteServicioDAO;
import com.pricing.extras.QRCode;
import com.pricing.model.CActividad;
import com.pricing.model.CImpuesto;
import com.pricing.model.CInterfaz;
import com.pricing.model.CPagos;
import com.pricing.model.CPaquete;
import com.pricing.model.CPasajero;
import com.pricing.model.CReserva;
import com.pricing.model.CReservaCupon;
import com.pricing.model.CReservaPaquete;
import com.pricing.model.CReservaPaqueteActividad;
import com.pricing.model.CReservaPaqueteCategoriaHotel;
import com.pricing.model.CReservaPaqueteServicio;
import com.pricing.model.CServicio;
import com.pricing.paypal.ExpressCheckout;
import com.pricing.util.CEmail;
import com.pricing.util.ScannUtil;
import com.pricing.util.Util;

public class returnPaypalVM 
{
	private String codTransac;
	private ExpressCheckout EC;
	private GetExpressCheckoutDetailsResponseType GECResponse;
	private CReserva oReserva;
	private CReservaDAO reservaDao;
	private CReservaPaqueteCategoriaHotel oReservaPaqueteCategoriaHotel;
	private CReservaPaqueteCategoriaHotelDAO reservaPaqueteCategoriaHotelDao;
	private CImpuesto oImpuesto;
	private CPasajeroDAO pasajeroDao;
	private ArrayList<String> listacFechasAlternas;
	private ArrayList<String[]> listaSeparadaFechasAlternas;
	private CFechaAlternaDAO fechaAlternaDao;
	private ArrayList<CReservaPaqueteServicio> listaReservaPaqServ;
	private ArrayList<CReservaPaqueteActividad> listaReservaPaqActi;
	private ArrayList<CPasajero> listaPasajeros;
	private CReservaPaqueteServicioDAO reservaPaqServDao;
	private String fechaInicio;
	private String fechaFin;
	private String fechaArribo;
	private String porcentajePago;
	private String montoPagarSinImpuesto;
	private String montoTotalSinImpuesto;
	private boolean mostrarCodReserva;
	private String codReserva;
	private CConfigUrlDAO configUrlDao;
	private CPagos pagos;
	HttpSession seshttp;
	/***************************/
	private String[] etiqueta;
	/***************************/
	//=============
	public String getCodTransac() {
		return codTransac;
	}
	public void setCodTransac(String codTransac) {
		this.codTransac = codTransac;
	}
	public boolean isMostrarCodReserva() {
		return mostrarCodReserva;
	}
	public void setMostrarCodReserva(boolean mostrarCodReserva) {
		this.mostrarCodReserva = mostrarCodReserva;
	}
	public String getCodReserva() {
		return codReserva;
	}
	public void setCodReserva(String codReserva) {
		this.codReserva = codReserva;
	}
	public String[] getEtiqueta() {
		return etiqueta;
	}
	public void setEtiqueta(String[] etiqueta) {
		this.etiqueta = etiqueta;
	}
	public CConfigUrlDAO getConfigUrlDao() {
		return configUrlDao;
	}
	public void setConfigUrlDao(CConfigUrlDAO configUrlDao) {
		this.configUrlDao = configUrlDao;
	}
	@Init
	public void initVm() throws IOException, DocumentException
	{
		seshttp=(HttpSession)Sessions.getCurrent().getNativeSession();
		this.etiqueta=(String[])seshttp.getAttribute("etiqueta");
		/**RECUPERAMOS LA CONFIGURACION DE LAS URLs**/
		configUrlDao=new CConfigUrlDAO();
		configUrlDao.asignarConfigUrl(configUrlDao.recuperarConfigUrlDB());
		/*************************/
		inicializarObjetos();
		//========Recupero el token de la session
		String token=(String)seshttp.getAttribute("token");
		GECResponse=new GetExpressCheckoutDetailsResponseType();
		//recuperar los datos
		EC=new ExpressCheckout();
		GECResponse=EC.getExpressCheckoutTest(token);
		String payerId=GECResponse.getGetExpressCheckoutDetailsResponseDetails().getPayerInfo().getPayerID();
		BasicAmountType amount=GECResponse.getGetExpressCheckoutDetailsResponseDetails().getPaymentDetails().get(0).getOrderTotal();
		//--------Se finaliza la transaccion----------
		codTransac=EC.doExpressCheckoutTest(payerId, token,amount);
		/**Se recupera el valor de la forma de pago 
		 * (1: El pago efectuado es desde el correo de reserva)
		 * (0: El pago efectuado es desde la pagina)
		 * **/
		mostrarCodReserva=false;
		int formaPago=(int) seshttp.getAttribute("formaPago");
		if(formaPago==1)
		{
			mostrarCodReserva=true;
			porcentajePago=(String) seshttp.getAttribute("porcentajePago");
			codReserva=(String)seshttp.getAttribute("codReserva");
			String codPaquete=(String)seshttp.getAttribute("codPaquete");
			String namePaquete=(String)seshttp.getAttribute("namePaquete");
			String email=(String)seshttp.getAttribute("mail");
			String contacto=(String)seshttp.getAttribute("contacto");
			String porcentaje="";
			String estado="";
			//==RECUPERANDO EL PAQUETE==
			CPaqueteDAO paqueteDao=new CPaqueteDAO();
			paqueteDao.asignarPaquete(paqueteDao.recuperarPaqueteBD(codPaquete));
			//==========================
			String textoParcial="";
			if(porcentajePago.equals("1"))
			{
				estado="PAGO PARCIAL";
				if(paqueteDao.getoPaquete().isbModoPorcentual())
					porcentaje=textoParcial=paqueteDao.getoPaquete().getnPorcentajeCobro()+" %";
				else
					porcentaje=textoParcial=etiqueta[102];
			}
			else//100%
			{
				estado="PAGO TOTAL";
				if(paqueteDao.getoPaquete().isbModoPorcentual())
					porcentaje="100 %";
				else
					porcentaje=etiqueta[103];
			}
			boolean b=reservaDao.isCorrectOperation(reservaDao.modificarMetodoPago(codReserva, estado,"PAYPAL",codTransac));
			String pdf=Util.getPathReservas()+"reservas.pdf";
			CEmail mail=new CEmail();
			boolean correct=mail.enviarCorreoPagoReserva(etiqueta[199],etiqueta,namePaquete, email, contacto, codReserva, porcentaje, codTransac, pdf,textoParcial);
			/**Enviamos un correo a la empresa**/
			boolean flag=mail.enviarCorreoPagoReservaAEmpresa(email,"Pago Efectuado", namePaquete, contacto, codReserva, porcentaje, codTransac);
			/******************************************************************/
		}
		else
		{
			/**Finalmente se procede a reservar **/
			pagos=(CPagos)seshttp.getAttribute("pagos");
			oReserva=(CReserva) seshttp.getAttribute("reserva");
			oReservaPaqueteCategoriaHotel=(CReservaPaqueteCategoriaHotel)seshttp.getAttribute("oReservaPaqCatHotel");
			oImpuesto=(CImpuesto)seshttp.getAttribute("oImpuesto");
			listacFechasAlternas=(ArrayList<String>) seshttp.getAttribute("listaFechasAlternas");
			listaSeparadaFechasAlternas=(ArrayList<String[]>) seshttp.getAttribute("listaSeparadaFechasAlternas");
			fechaInicio=(String) seshttp.getAttribute("fechaInicio");
			fechaFin=(String) seshttp.getAttribute("fechaFin");
			fechaArribo=(String) seshttp.getAttribute("fechaArribo");
			montoPagarSinImpuesto=(String) seshttp.getAttribute("montoPagarSinImpuesto");
			montoTotalSinImpuesto=(String)seshttp.getAttribute("montoTotalSinImpuesto");
			porcentajePago=(String) seshttp.getAttribute("porcentajePago");
			listaReservaPaqServ=(ArrayList<CReservaPaqueteServicio>) seshttp.getAttribute("listaReservaPaqServ");
			listaReservaPaqActi=(ArrayList<CReservaPaqueteActividad>)seshttp.getAttribute("listaReservaPaqActi");
			listaPasajeros=(ArrayList<CPasajero>)seshttp.getAttribute("listaPasajeros");
			reservar();
		}
	}
	public void inicializarObjetos()
	{
		pagos=new CPagos();
		oReserva=new CReserva();
		oImpuesto=new CImpuesto();
		listacFechasAlternas=new ArrayList<String>();
		listaSeparadaFechasAlternas=new ArrayList<String[]>();
		reservaDao=new CReservaDAO();
		pasajeroDao=new CPasajeroDAO();
		fechaAlternaDao=new CFechaAlternaDAO();
		listaReservaPaqServ=new ArrayList<CReservaPaqueteServicio>();
		listaReservaPaqActi=new ArrayList<CReservaPaqueteActividad>();
		listaPasajeros=new ArrayList<CPasajero>();
		reservaPaqServDao=new CReservaPaqueteServicioDAO();
		oReservaPaqueteCategoriaHotel=new CReservaPaqueteCategoriaHotel();
		reservaPaqueteCategoriaHotelDao=new CReservaPaqueteCategoriaHotelDAO();
	}
	public void reservar() throws IOException, DocumentException
	{
		String[] resultado=reservaDao.recuperarResultados(reservaDao.registrarReserva(oReserva));
		if(resultado[0].equals("correcto"))
		{
			String estado="";
			if(porcentajePago.equals("1"))
				estado="PAGO PARCIAL";
			else//100%
				estado="PAGO TOTAL";
			boolean b=reservaDao.isCorrectOperation(reservaDao.modificarEstadoDePago(resultado[1], estado));
			if(oReserva.getoCupon().isOkCupon())
			{
				boolean a=reservaDao.isCorrectOperation(reservaDao.insertarCupon(oReserva.getcReservaCod(), oReserva.getoCupon().getnCuponCod()));
			}
		}
		else
		{
			Clients.showNotification("La reserva no se efectuo correctamente",Clients.NOTIFICATION_TYPE_INFO,null,"top_center",2700);
			return;
		}
		//Se asigna el codigo de reserva al objeto oReserva
		oReserva.setcReservaCod(resultado[1]);
		//===================
		String pdf=Util.getPathReservas()+"reservas.pdf";
		CEmail mail=new CEmail();
		boolean b=mail.enviarCorreoConPago(etiqueta[200],etiqueta,oImpuesto,oReservaPaqueteCategoriaHotel,fechaInicio,fechaFin,fechaArribo,oReserva,listacFechasAlternas,montoTotalSinImpuesto,montoPagarSinImpuesto,pdf,codTransac,porcentajePago,listaPasajeros,pagos);
		/**REGISTRAR CUPON**/
		if(oReserva.getoCupon().isOkCupon())
		{
			CReservaCupon rc=new CReservaCupon();
			rc.setcReservaCod(oReserva.getcReservaCod());
			rc.setnCuponCod(oReserva.getoCupon().getnCuponCod());
			CReservaCuponDAO rcDao=new CReservaCuponDAO();
			b=rcDao.isOperationCorrect(rcDao.insertarReservaCupon(rc));
			
		}
		/**Enviamos las imagenes de los documentos escaneados a la empresa**/
		ArrayList<String> listaUrlImages=new ArrayList<String>();
		for(CPasajero pax:listaPasajeros)
		{
			if(pax.isEsEdit() && pax.isSelectPasajero())
			{
				listaUrlImages.add(ScannUtil.getPath()+pax.getcUrlDocumento());
			}
		}
		boolean flag=mail.enviarCorreoConPagoAEmpresa("Datos de Nueva Reserva y Pago",oImpuesto,oReservaPaqueteCategoriaHotel,fechaInicio,fechaFin,fechaArribo,oReserva,listacFechasAlternas,montoTotalSinImpuesto,montoPagarSinImpuesto,listaUrlImages,codTransac,porcentajePago,listaPasajeros,pagos);
		/********Insertamos la ReservaPaquete*********/
		CReservaPaquete oReservaPaquete=new CReservaPaquete();
		oReservaPaquete.setcReservaCod(oReserva.getcReservaCod());
		oReservaPaquete.setcPaqueteCod((String)seshttp.getAttribute("codigoPaquete"));
		oReservaPaquete.setNroPasajerosPaquete(oReserva.getnNroPersonas());
		oReservaPaquete.setnMontoTotalPaquete((Double)seshttp.getAttribute("TotalPaquete"));
		
		CReservaPaqueteDAO reservaPaqueteDao=new CReservaPaqueteDAO();
		Number codRP=reservaPaqueteDao.obtenerCodigoReservaPaquete(reservaPaqueteDao.insertarReservaPaquete(oReservaPaquete));
		/******************************************************************/
		if(oReservaPaqueteCategoriaHotel!=null)
			if(oReservaPaqueteCategoriaHotel.isConHotel())
			{
				if(!oReservaPaqueteCategoriaHotel.isConCamaAdicional())
					oReservaPaqueteCategoriaHotel.setPrecioCamaAdicional("0.00");
				oReservaPaqueteCategoriaHotel.setnReservaPaqueteCod(codRP.longValue());
				flag=reservaPaqueteCategoriaHotelDao.isCorrectOperation(reservaPaqueteCategoriaHotelDao.insertarReservaPaqueteCatHotel(oReservaPaqueteCategoriaHotel));
			}
		if(!listaReservaPaqServ.isEmpty())
		{
			for(int i=0;i<listaReservaPaqServ.size();i++)
				listaReservaPaqServ.get(i).setnReservaPaqueteCod(codRP.longValue());
			for(int i=0;i<listaReservaPaqServ.size();i++)
			{
				boolean correct=reservaPaqServDao.isCorrectOperation(reservaPaqServDao.insertarReservaPaqueteServicio(listaReservaPaqServ.get(i)));
			}
		}
		if(!listaReservaPaqActi.isEmpty())
		{
			for(CReservaPaqueteActividad rpa:listaReservaPaqActi)
			{
				CReservaPaqueteActividadDAO rpaDao=new CReservaPaqueteActividadDAO();
				rpa.setnReservaPaqueteCod(codRP.longValue());
				boolean correcto=rpaDao.isCorrectOperation(rpaDao.insertarReservaPaqueteActividad(rpa));
			}
		}
		CPasajeroDAO pasajeroDao=new CPasajeroDAO();
		CInterfazDAO interfazDao=new CInterfazDAO();
		interfazDao.asignarConfigInterfaz(interfazDao.recuperarConfigInterfazDB());
		if(!interfazDao.getoInterfaz().isbLlenarDatosUnPax())
		{
			int i=1;
			for(CPasajero p:listaPasajeros)
			{
				if(i>oReserva.getnNroPersonas())break;
				p.setcReservaCod(oReserva.getcReservaCod());
				boolean f=pasajeroDao.isOperationCorrect(pasajeroDao.insertarPasajero(p));
				i++;
			}
		}else{
			oReserva.getoPasajeroReservante().setcNombres(oReserva.getcContacto());
			oReserva.getoPasajeroReservante().setcReservaCod(oReserva.getcReservaCod());
			boolean f=pasajeroDao.isOperationCorrect(pasajeroDao.insertarPasajero(oReserva.getoPasajeroReservante()));
		}
		if(!listaSeparadaFechasAlternas.isEmpty())
		{
			for(int i=1;i<listaSeparadaFechasAlternas.size();i++)
			{
				if(!listaSeparadaFechasAlternas.get(i)[0].equals(""))
				{
					Calendar calendar=Calendar.getInstance();
					calendar.set(Integer.parseInt(listaSeparadaFechasAlternas.get(i)[2]),Integer.parseInt(obtenerNroMes(listaSeparadaFechasAlternas.get(i)[1]))-1,Integer.parseInt(listaSeparadaFechasAlternas.get(i)[0]));
					//Reservar
					fechaAlternaDao.isOperationCorrect(fechaAlternaDao.insertarFechaAlterna(oReserva.getcReservaCod(), calendar.getTime(),calendar.getTime()));
				}
			}
		}
	}
	@Command
	public void regresarPaginaPrincipal()
	{
		try
		{
			Execution exec = Executions.getCurrent();
			exec.sendRedirect(configUrlDao.getoConfigUrl().getUrlPaginaWeb(),false);
		    exec.setVoided(true);
		}
		catch(Exception e)
		{
			
		}
	}
	public String obtenerNroMes(String mes)
	{
		String nuevo="";
		switch(mes)
		{
			case "Enero":nuevo="01";break;
			case "Febrero":nuevo="02";break;
			case "Marzo":nuevo="03";break;
			case "Abril":nuevo="04";break;
			case "Mayo":nuevo="05";break;
			case "Junio":nuevo="06";break;
			case "Julio":nuevo="07";break;
			case "Agosto":nuevo="08";break;
			case "Setiembre":nuevo="09";break;
			case "Octubre":nuevo="10";break;
			case "Noviembre":nuevo="11";break;
			case "Diciembre":nuevo="12";break;
		}
		return nuevo;
	}
}
