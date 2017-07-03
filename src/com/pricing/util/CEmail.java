package com.pricing.util;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Transport;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeMultipart;
import javax.activation.DataSource;

import com.lowagie.text.DocumentException;
import com.pricing.dao.CConfigUrlDAO;
import com.pricing.dao.CCorreoSmtpDAO;
import com.pricing.dao.CPaisDAO;
import com.pricing.extras.QRCode;
import com.pricing.model.CActividad;
import com.pricing.model.CCupon;
import com.pricing.model.CDestinoConHoteles;
import com.pricing.model.CHotel;
import com.pricing.model.CImpuesto;
import com.pricing.model.CPagos;
import com.pricing.model.CPaquete;
import com.pricing.model.CPasajero;
import com.pricing.model.CReserva;
import com.pricing.model.CReservaPaqueteCategoriaHotel;
import com.pricing.model.CServicio;

public class CEmail 
{
	//======ATRIBUTOS=========
	private String remitente;
	private String password;
	private DecimalFormat df;
	private DecimalFormatSymbols simbolos;
	private String[] etiqueta;
	//===GETTER AND SETTER=====
	public String getRemitente() {
		return remitente;
	}
	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	//=========CONSTRUTORES====
	public CEmail()
	{
		/**Recuperamos la configuracion de SMTP de la Base de Datos**/
		CCorreoSmtpDAO correoSmtpDao=new CCorreoSmtpDAO();
		correoSmtpDao.asignarConfiguracionCorreoSMTP(correoSmtpDao.recuperarCorreoSmtpDB());
		/*******************/
		// TODO Auto-generated constructor stub
		this.remitente = correoSmtpDao.getoCorreoSmtp().getcSMTPUserName();
        this.password = correoSmtpDao.getoCorreoSmtp().getcSMTPPassword();
        //=================
        simbolos= new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		df=new DecimalFormat("########0.00",simbolos);
	}
	//=====METODOS====
	public void enviarCorreoImages()
	{
		// Nombre del host de correo, es smtp.gmail.com
		/**Recuperamos la configuracion de SMTP de la Base de Datos**/
		CCorreoSmtpDAO correoSmtpDao=new CCorreoSmtpDAO();
		correoSmtpDao.asignarConfiguracionCorreoSMTP(correoSmtpDao.recuperarCorreoSmtpDB());
	      try {
	  		Properties props = new Properties();
			props.put("mail.smtp.host", correoSmtpDao.getoCorreoSmtp().getcSMTPHost());
			// Puerto de gmail para envio de correos
			props.setProperty("mail.smtp.port",""+correoSmtpDao.getoCorreoSmtp().getnSMTPPort());
			// Si requiere o no usuario y password para conectarse.
			props.setProperty("mail.smtp.auth", "true");
			props.setProperty("mail.smtp.ssl.enable", "true");
        	props.setProperty("mail.smtp.socketFactory.port", "465");
        	props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	        // Nombre del usuario
	        props.setProperty("mail.smtp.user", remitente);

	        Session session = Session.getDefaultInstance(props);

	        MimeMessage message = new MimeMessage(session);
			//Luego ponemos el FROM y el TO es decir:
			// Quien envia el correo
			message.setFrom(new InternetAddress(remitente));
			// A quien va dirigido y a quien responder
			InternetAddress address = new InternetAddress("washi1886@gmail.com");
			InternetAddress[] dir={address};
			message.setReplyTo(dir);
			message.reply(true);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(remitente));
			//Ahora llenamos el asunto
			message.setSubject("Testing Images");
			// This mail has 2 part, the BODY and the embedded image
	         MimeMultipart multipart = new MimeMultipart("related");

	         // first part (the html)
	         BodyPart messageBodyPart = new MimeBodyPart();
	         String htmlText = "<H1>Hello</H1><img src=\"cid:cuatro.png\">";
	         messageBodyPart.setContent(htmlText, "text/html");
	         // add it
	         multipart.addBodyPart(messageBodyPart);
	         // second part (the image)
	         messageBodyPart = new MimeBodyPart();
	         DataSource fds = new FileDataSource(
	            "/img/cuatro.png");

	         messageBodyPart.setDataHandler(new DataHandler(fds));
	         messageBodyPart.setHeader("Content-ID", "<image>");

	         // add image to the multipart
	         multipart.addBodyPart(messageBodyPart);

	         // put everything together
	         message.setContent(multipart);

			
			//Enviamos el mensaje
			//Para enviar el mensaje usamos la clase Transport, que se obtiene de Session. El método getTransport() requiere un parámetro String con el nombre del protocolo a usar
			Transport t = session.getTransport("smtp");
			//Ahora debemos establecer la conexion
			t.connect(remitente,password);
			//finalmente enviamos el mensaje
			t.sendMessage(message,message.getAllRecipients());
			//una vez enviado cerramos la conexion
			t.close();
            System.out.println("Sent message successfully....");
	      } catch (Exception e) {
	    	  e.printStackTrace();
	      }
	}
	public boolean sendMailSimple(String mailCliente,String asunto,String mensaje)
	{
		try
		{
			Properties props = new Properties();
			props=System.getProperties();
			configurandoProperties(props);
			
			//Creamos nuestro objeto o instancia session
			Session session = Session.getDefaultInstance(props);
//			session.setDebug(true);
			//Construimos el mensaje para enviar con javamail
			//Para ello instanciamos la clase MimeMessage y le pasamos de parametro session

			MimeMessage message = new MimeMessage(session);
			//Luego ponemos el FROM y el TO es decir:
			// Quien envia el correo
			message.setFrom(new InternetAddress(remitente));
			// A quien va dirigido y a quien responder
			InternetAddress address = new InternetAddress(mailCliente);
			InternetAddress[] dir={address};
			message.setReplyTo(dir);
			message.reply(true);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(remitente));
			//Ahora llenamos el asunto
			message.setSubject(asunto);
			message.setText(mensaje,"utf-8","html");
			
			//Enviamos el mensaje
			//Para enviar el mensaje usamos la clase Transport, que se obtiene de Session. El método getTransport() requiere un parámetro String con el nombre del protocolo a usar
			Transport t = session.getTransport("smtp");
			//Ahora debemos establecer la conexion
			t.connect(remitente,password);
			//finalmente enviamos el mensaje
			t.sendMessage(message,message.getAllRecipients());
			//una vez enviado cerramos la conexion
			t.close();
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
            return false;
		}
	}
	public boolean sendMailNewUser(String destinatario, String asunto,String mensaje)
	{
		try
		{
			Properties props = new Properties();
			props=System.getProperties();
			configurandoProperties(props);
			
			//Creamos nuestra objeto o instancia session
			Session session = Session.getDefaultInstance(props);
//			session.setDebug(true);
			//Construimos el mensaje para enviar con javamail
			//Para ello isntanciamos la clase MimeMessage y le pasamos de parametro session

			MimeMessage message = new MimeMessage(session);
			//Luego ponemos el FROM y el TO es decir:
			// Quien envia el correo
			message.setFrom(new InternetAddress(remitente));
			// A quien va dirigido, con copia.
			// en esta parte hubo conflicto washi
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
			message.addRecipient(Message.RecipientType.CC, new InternetAddress(remitente));
			// y aqui terminaba el conflicto
			//Ahora llenamos el asunto
			message.setSubject(asunto);
			message.setText(mensaje,"utf-8","html");
			
			//Enviamos el mensaje
			//Para enviar el mensaje usamos la clase Transport, que se obtiene de Session. El método getTransport() requiere un parámetro String con el nombre del protocolo a usar
			Transport t = session.getTransport("smtp");
			//Ahora debemos establecer la conexion
			t.connect(remitente,password);
			//finalmente enviamos el mensaje
			t.sendMessage(message,message.getAllRecipients());
			//una vez enviado cerramos la conexion
			t.close();
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
            return false;
		}
	}
	public boolean sendMail(String destinatario, String asunto,String mensaje,String urlPdf,int opcion)
	{
		try
        {
			Properties props = new Properties();
			props=System.getProperties();
			configurandoProperties(props);
            Session session = Session.getDefaultInstance(props,null);
//             session.setDebug(true);

            // Se compone la parte del texto
            BodyPart texto = new MimeBodyPart();
            texto.setContent(mensaje, "text/html");

            // Se compone el adjunto con el pdf
//            BodyPart adjunto = new MimeBodyPart();
//            if(opcion==1)//Hay que adjuntar pdf (eso es que el cliente efectuo un pago)
//            {
//                adjunto.setDataHandler(
//                    new DataHandler(new FileDataSource(urlPdf)));
//                adjunto.setFileName("DatosReserva.pdf");
//            }

            // Una MultiParte para agrupar texto e imagen.
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
//            if(opcion==1)
//            	multiParte.addBodyPart(adjunto);

            // Se compone el correo, dando to, from, subject y el
            // contenido.
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
         // A quien va dirigido, con copia y a quien responder
         	InternetAddress address = new InternetAddress(remitente);
         	InternetAddress[] dir={address};
         	message.setReplyTo(dir);
         	message.reply(true);
            message.addRecipient(
                Message.RecipientType.TO,
                new InternetAddress(destinatario));
            message.setSubject(asunto);
            message.setContent(multiParte);

            // Se envia el correo.
            Transport t = session.getTransport("smtp");
            t.connect(remitente, password);
            t.sendMessage(message, message.getAllRecipients());
            t.close();
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
	}
	public boolean sendMailToEmpresa(String mailCliente,String asunto,String mensaje,ArrayList<String> urlImage)
	{
		try
        {
			Properties props = new Properties();
			props=System.getProperties();
			configurandoProperties(props);
            Session session = Session.getDefaultInstance(props,null);
             session.setDebug(true);

            // Se compone la parte del texto
            BodyPart texto = new MimeBodyPart();
            texto.setContent(mensaje, "text/html");
//            texto.setText(mensaje);

         // Una MultiParte para agrupar texto e imagen.
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            if(urlImage.size()>0)
	            for(int i=0;i<urlImage.size();i++)
	            {
	            	// Se compone el adjunto con la imagen
	                BodyPart adjunto = new MimeBodyPart();
	                System.out.println("Url imagen: "+urlImage.get(i));
	            	adjunto.setDataHandler(
	                new DataHandler(new FileDataSource(urlImage.get(i))));
	            	adjunto.setFileName("Doc_"+(i+1));
	            	multiParte.addBodyPart(adjunto);
	            }
//            adjunto.setFileName("DatosReserva.pdf");


            // Se compone el correo, dando to, from, subject y el
            // contenido.
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
         // A quien va dirigido, con copia y a quien responder
         			InternetAddress address = new InternetAddress(mailCliente);
         			InternetAddress[] dir={address};
         			message.setReplyTo(dir);
         			message.reply(true);
            message.addRecipient(
                Message.RecipientType.TO,
                new InternetAddress(remitente));
         	message.setSubject(asunto);
            message.setContent(multiParte);

            // Se envia el correo.
            Transport t = session.getTransport("smtp");
            t.connect(remitente,password);
            t.sendMessage(message, message.getAllRecipients());
            t.close();
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
	}
	public void configurandoProperties(Properties props)
	{
		/**Recuperamos la configuracion de SMTP de la Base de Datos**/
		CCorreoSmtpDAO correoSmtpDao=new CCorreoSmtpDAO();
		correoSmtpDao.asignarConfiguracionCorreoSMTP(correoSmtpDao.recuperarCorreoSmtpDB());
		/*******************/
		if(correoSmtpDao.getoCorreoSmtp().isbTLS())
		{
			// Nombre del host de correo, es smtp.gmail.com
			props.put("mail.smtp.host", correoSmtpDao.getoCorreoSmtp().getcSMTPHost());
			// TLS si está disponible
			props.setProperty("mail.smtp.starttls.enable", "true");

			// Puerto de gmail para envio de correos
			props.setProperty("mail.smtp.port",""+correoSmtpDao.getoCorreoSmtp().getnSMTPPort());

			// Nombre del usuario
			props.setProperty("mail.smtp.user", remitente);
			// Si requiere o no usuario y password para conectarse.
			props.setProperty("mail.smtp.auth","true");
		}else if(correoSmtpDao.getoCorreoSmtp().isbSSL())
		{
			// Nombre del host de correo, es smtp.gmail.com
			props.put("mail.smtp.host", correoSmtpDao.getoCorreoSmtp().getcSMTPHost());
			// Puerto de gmail para envio de correos
			props.setProperty("mail.smtp.port",""+correoSmtpDao.getoCorreoSmtp().getnSMTPPort());
			// Si requiere o no usuario y password para conectarse.
			props.setProperty("mail.smtp.auth", "true");
			props.setProperty("mail.smtp.ssl.enable", "true");
        	props.setProperty("mail.smtp.socketFactory.port", "465");
        	props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	        // Nombre del usuario
	        props.setProperty("mail.smtp.user", remitente);
		}
	}
	public boolean enviarCorreoConPagoMasterDiners(String asunto,String codAutorizacion,String Monto,String tarjeta)
	{
		String mensajeHTML=
				"<html>"+
					"<head></head>"+
					"<body>"+
						"<div style='width:100%;background:rgb(242, 242, 242);'>"+
							"<table border='0' width='100%' align='center' style='background:rgb(242, 242, 242);'>"+
								"<tr>"+
							      "<td align='center' width='20%' style='padding:8px 20px 8px 20px;'>"+
							      	"<a style='text-decoration:none;'>"+
							      		"<img src='http://www.peruandestop.com/wp-content/uploads/2016/09/logo.png' width='90' height='60' border='0' />"+
							      	"</a>"+
							      "</td>"+
							      "<td style='color:black;font-size:17px;font-weight:bold;' align='left' width='80%'>RESUMEN DE TRANSACCION</td>"+
							    "</tr>"+
							"</table>"+
							"<div style='padding:20px;background:white;border:20px solid rgb(242, 242, 242);'>"+
								"<div style='background:#1A5276;border-radius:5px;width:25%;padding:1px 0 1px 0;' align='center'>"+
									"<p style='font-weight:bold;'>Codigo Autorizacion<strong style='color:white;'>"+codAutorizacion+"</strong></p>"+
								"</div>"+
								"<p>Se efectuó un Pago con "+tarjeta+".</p>"+
								"<p>Total: USD. "+Monto+"</p>"+
							"</div>"+
						"</div>"+
					"</body>"+
				"</html>";
		return sendMailSimple("webmaster@peruandestop.com",asunto, mensajeHTML);
	}
	public boolean enviarCorreoSinPago(String titulo,String language,CImpuesto oImpuesto,String[] etiqueta,CReservaPaqueteCategoriaHotel oReservaPCH,
			String fechaInicio,String fechaFin,String fechaArribo,CReserva reserva,
			ArrayList<String> fechasAlternas,String totalPago,String pagoParcial,
			String urlPdf,ArrayList<String> urlImage,ArrayList<CPasajero> listaPasajeros) throws IOException, DocumentException
	{
		this.etiqueta=etiqueta;
		Calendar cal=Calendar.getInstance();
		/************************/
		/**Obtener fecha actual**/
		String[] fechaActual=obtenerFechaActual();
		/**Se obtiene el impuesto e importe total del totalPago**/
		String impuesto=df.format(Double.parseDouble(totalPago)*(Double.parseDouble(oImpuesto.getImpuestoPaypal())/100));
		String importeTotal=df.format(Double.parseDouble(totalPago)+Double.parseDouble(impuesto));
		/*********************************************************/
		/**Se obtiene el precio unitario del paquete acorde al numero de pasajeros esto debido a que se aplica descuento**/
		String precioUniPaquete=obtenerPrecioUnitarioPaquete(reserva,reserva.getoPaquete());
		/***********************************************/
		/**Se obtienen las fechas alternas para el html**/
		String fechas=obtenerHtmlFechasAlternas(fechasAlternas);
		/***********************************************
		 * Se obtiene la categoria del hotel reservado
		 * *********************************************
		 */
		String hotel[]=obtenerHtmlHotel(oReservaPCH,etiqueta);
		/**********************************************/
		/**Se obtienen los datos de los pasajeros**/
		String pasajeros[]=obtenerHtmlPasajeros(listaPasajeros,etiqueta,reserva);
		/******************************************/
		/**Se obtienen los datos de los servicios**/
		String servicios[]=obtenerHtmlServicios(reserva.getoPaquete().getListaServicios(),etiqueta);
		/******************************************/
		/**Se obtinenen los datos de las actividades**/
		String actividades[]=obtenerHtmlActividades(reserva.getoPaquete().getListaActividades(),reserva);
		/********************************************/
		/**RECUPERAMOS LA CONFIGURACION DE URLs**/
		CConfigUrlDAO configUrlDao=new CConfigUrlDAO();
		configUrlDao.asignarConfigUrl(configUrlDao.recuperarConfigUrlDB());
		/**OBTENEMOS LA FECHA DE ARRIBO**/
		String arribo="";
		if(reserva.getoPaquete().isConFechaArribo())
			arribo="<P>"+etiqueta[243]+": "+fechaArribo+"</P>";
		/****************************************/
		String textoParcial="";
		String textoTotal="";
		if(reserva.getoPaquete().isbModoPorcentual())
		{
			textoParcial=reserva.getoPaquete().getnPorcentajeCobro()+" %";
			textoTotal="100 %";
		}else
		{
			textoParcial=etiqueta[102];
			textoTotal=etiqueta[103];
		}
		/****************************************/
		String btnPagoTotalPaypal="";
		String btnPagoParcialPaypal="";
		if(!reserva.getoPaquete().isbModoPagoTotal())
		{
			btnPagoTotalPaypal="<td style='margin: 0px; text-align: center; border-right: 1px solid black;padding-bottom:15px;font-family: Titillium;'>"+
					etiqueta[128]+"("+textoTotal+")"+
					"<form action='"+configUrlDao.getoConfigUrl().getUrlServletPagoTotal()+"' method='POST'>"+
						"<input type='hidden' name='Monto' value='"+totalPago+"'/>"+
						"<input type='hidden' name='codReserva' value='"+reserva.getcReservaCod()+"'/>"+
						"<input type='hidden' name='codPaquete' value='"+reserva.getoPaquete().getcPaqueteCod()+"'/>"+
						"<input type='hidden' name='namePaquete' value='"+reserva.getoPaquete().getcTituloIdioma1()+"'/>"+
						"<input type='hidden' name='mail' value='"+reserva.getcEmail()+"'/>"+
						"<input type='hidden' name='contacto' value='"+reserva.getcContacto()+"'/>"+
						"<input type='hidden' name='language' value='"+language+"'/>"+
						"<input type='hidden' name='impuestoPaypal' value='"+oImpuesto.getImpuestoPaypal()+"'/>"+
						"<input type='image' name='submit' border='0'"+
		    			"src='https://www.paypal.com/en_US/i/btn/btn_xpressCheckout.gif'/>"+
					"</form>"+
		    		"</td>";
			btnPagoParcialPaypal="<td style='margin: 0px; text-align: center;padding-bottom:15px;font-family: Titillium;'>"+
					etiqueta[128]+"("+textoParcial+")"+
					"<form action='"+configUrlDao.getoConfigUrl().getUrlServletPagoParcial()+"' method='POST'>"+
						"<input type='hidden' name='Monto' value='"+pagoParcial+"'/>"+
						"<input type='hidden' name='codReserva' value='"+reserva.getcReservaCod()+"'/>"+
						"<input type='hidden' name='codPaquete' value='"+reserva.getoPaquete().getcPaqueteCod()+"'/>"+
						"<input type='hidden' name='namePaquete' value='"+reserva.getoPaquete().getcTituloIdioma1()+"'/>"+
						"<input type='hidden' name='mail' value='"+reserva.getcEmail()+"'/>"+
						"<input type='hidden' name='contacto' value='"+reserva.getcContacto()+"'/>"+
						"<input type='hidden' name='language' value='"+language+"'/>"+
						"<input type='hidden' name='impuestoPaypal' value='"+oImpuesto.getImpuestoPaypal()+"'/>"+
						"<input type='image' name='submit' border='0'"+
		    			"src='https://www.paypal.com/en_US/i/btn/btn_xpressCheckout.gif'/>"+
					"</form>"+
		    		"</td>";
		}else
		{
			btnPagoTotalPaypal="<td style='margin: 0px; text-align: center; border-right: 1px solid black;padding-bottom:15px;font-family: Titillium;'>"+
					etiqueta[128]+"("+textoTotal+")"+
					"<form action='"+configUrlDao.getoConfigUrl().getUrlServletPagoTotal()+"' method='POST'>"+
						"<input type='hidden' name='Monto' value='"+totalPago+"'/>"+
						"<input type='hidden' name='codReserva' value='"+reserva.getcReservaCod()+"'/>"+
						"<input type='hidden' name='codPaquete' value='"+reserva.getoPaquete().getcPaqueteCod()+"'/>"+
						"<input type='hidden' name='namePaquete' value='"+reserva.getoPaquete().getcTituloIdioma1()+"'/>"+
						"<input type='hidden' name='mail' value='"+reserva.getcEmail()+"'/>"+
						"<input type='hidden' name='contacto' value='"+reserva.getcContacto()+"'/>"+
						"<input type='hidden' name='language' value='"+language+"'/>"+
						"<input type='hidden' name='impuestoPaypal' value='"+oImpuesto.getImpuestoPaypal()+"'/>"+
						"<input type='image' name='submit' border='0'"+
		    			"src='https://www.paypal.com/en_US/i/btn/btn_xpressCheckout.gif'/>"+
					"</form>"+
		    		"</td>";
		}
		/****************************************/
		String mensajeHTML=
				"<html>"+
					"<head>"+
					"<style type='text/css' media='screen, print'>"+
						"@font-face {"+
							  "font-family: Titillium;"+
							  "font-style: normal;"+
							  "font-weight: 400;"+
							  "src: local('Titillium Web Regular'), local('TitilliumWeb-Regular'), url(https://fonts.gstatic.com/s/titilliumweb/v5/7XUFZ5tgS-tD6QamInJTcSo_WB_cotcEMUw1LsIE8mM.woff2) format('woff2');"+
							  "unicode-range: U+0100-024F, U+1E00-1EFF, U+20A0-20AB, U+20AD-20CF, U+2C60-2C7F, U+A720-A7FF;"+
							"}"+
							/* latin */
							"@font-face {"+
							  "font-family: Titillium;"+
							  "font-style: normal;"+
							  "font-weight: 400;"+
							  "src: local('Titillium Web Regular'), local('TitilliumWeb-Regular'), url(https://fonts.gstatic.com/s/titilliumweb/v5/7XUFZ5tgS-tD6QamInJTcZSnX671uNZIV63UdXh3Mg0.woff2) format('woff2');"+
							  "unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02C6, U+02DA, U+02DC, U+2000-206F, U+2074, U+20AC, U+2212, U+2215;"+
							"}"+
					"</style>"+
					"</head>"+
					"<body>"+
						"<div style='width:600px;background:rgb(242, 242, 242);'>"+
							"<table style='background:white;border:1px solid rgb(204, 204, 204);color:rgb(34, 34, 34);font-family:arial,sans-serif;font-size:12.8px;margin:0px auto;width:600px;border-collapse: collapse;'>"+
								"<tbody>"+
									"<tr>"+
										"<td>"+
											"<table style='background:rgb(242, 242, 242)'>"+
												"<tbody>"+
													"<tr>"+
														"<td>"+
															"<p style='font-family: Titillium;font-weight:bold;font-size:15px;margin:5px 0 6px 0;'>"+fechaActual[0]+"</p>"+
														"</td>"+
													"</tr>"+
													"<tr>"+
														"<td align='left' width='20%' style='padding:8px 20px 8px 20px;'>"+
															"<a href='"+etiqueta[212]+"' style='text-decoration:none;font-family:Titillium;'>"+
																"<img src='"+etiqueta[211]+"' width='100' height='80' border='0' />"+
															"</a>"+
														"</td>"+
														"<td style='font-family: Titillium;color:black;font-size:17px;font-weight:bold;' align='center' width='80%'>"+reserva.getoPaquete().getTitulo()+"</td>"+
														"<td align='right' width='20%' style='padding:8px 20px 8px 20px;'>"+
															"<a href='"+etiqueta[212]+"' style='text-decoration:none;'>"+
																"<img src='"+etiqueta[211]+"' width='100' height='80' border='0' />"+
															"</a>"+
														"</td>"+
													"</tr>"+
												"</tbody>"+
											"</table>"+
										"</td>"+
									"</tr>"+
								"</tbody>"+
							"</table>"+
						"<div style='background:white;width:600px;'>"+
							"<table style='background:white;color:rgb(34, 34, 34);font-family: Titillium;font-size:12.8px;width:100%;border-collapse: collapse;'>"+
								"<tbody>"+
								"<tr>"+
									"<td style='font-family: Titillium; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial; font-weight: bold; font-stretch: normal; font-size: 15px; line-height: normal; color: rgb(65, 88, 132);'>"+
										"<div align='center'>"+
											"<img border='0' src='https://www.e-ranti.com/pricing_demo/img/negocios.jpg' style='cursor:pointer; height:208px; outline:0px; width:600px' tabindex='0'/>"+
										"</div>"+
									"</td>"+
								"</tr>"+
								"</tbody>"+
							"</table>"+
							"<br />"+
							"<table width='100%' style='border:1px solid rgba(0,0,0,0.1);border-collapse: collapse;margin:0;'>"+
										"<thead style='background:rgba(0,0,0,0.1);font-weight: bold;'>"+etiqueta[129]+"</thead>"+
											"<tr style='border:1px solid black;'>"+
												"<td style='margin: 0px; text-align: center; border-right: 1px solid black;padding-bottom:15px;' width='240'>"+
													"<p style='color: #1a5276; margin-bottom: 30px;font-family: Titillium;'>"+
														"<strong style='text-decoration: underline;'>"+etiqueta[118]+"</strong>"+
													"</p>"+
													"<span style='font-family: Titillium;background-color:rgb(204, 204, 204); border-radius:3px; border:1px solid rgb(217, 217, 217); color:rgb(47, 115, 186); font-family:arial,helvetica,sans-serif; font-size:24px; font-stretch:normal; line-height:24px; padding:10px'>"+
														"<strong>"+reserva.getcReservaCod()+"</strong>"+
													"</span>"+
												"</td>"+
												"<td style='margin: 0px;padding:0px 15px;' width='308'>"+
													"<p>"+
														"<strong style='text-decoration: underline;font-family: Titillium;'>"+etiqueta[125]+"</strong>"+
													"</p>"+
													"<span style='color:rgb(102, 102, 102);font-family: Titillium;'>"+
														"<strong>"+reserva.getcContacto()+"</strong>"+
													"</span>"+
													"<p>"+
														"<strong style='text-decoration: underline;font-family: Titillium;'>"+etiqueta[253]+"</strong>"+
													"</p>"+
													"<span>"+
														"<strong><span style='color:rgb(102, 102, 102);font-family: Titillium;'>"+fechaInicio+"</span></strong>"+
														"<br />"+
														"<strong><span style='color:rgb(102, 102, 102);font-family: Titillium;'>"+fechaFin+"</span></strong>"+
														"<br />"+
														"<strong>"+etiqueta[249]+"</strong><strong><span style='color:rgb(102, 102, 102);font-family: Titillium;'>"+fechaArribo+"</span></strong>"+
													"</span>"+
													"<p>"+
														"<strong style='text-decoration: underline;font-family: Titillium;'>"+etiqueta[81]+"</strong>"+
													"</p>"+
													"<span style='color:rgb(102, 102, 102);font-family: Titillium;'>"+
														"<strong>USD($) "+totalPago+"</strong>"+
													"</span>"+
												"</td>"+
											"</tr>"+
									"</table>"+
//							"<div align='center'>"+
//								"<p style='color:#F7653A;font-size:18px;font-weight:bold;'>"+etiqueta[126]+"</p>"+
//							"</div>"+
//							"<p style='font-weight:bold;'>"+etiqueta[127]+"</p>"+
							"<div align='center'>"+
							"<br />"+
							"<table width='100%' style='border:1px solid rgba(0,0,0,0.1);border-collapse: collapse;margin:0;'>"+
								"<thead style='background:rgba(0,0,0,0.1);font-weight: bold;font-family: Titillium;'>"+etiqueta[78]+"</thead>"+
								"<tr style='border:1px solid black;'>"+
									btnPagoTotalPaypal+
						    		btnPagoParcialPaypal+
								"</tr>"+
							"</table>"+
							"</div>"+
							"<br />"+
							"<div>"+
							"<table width='100%' style='border:1px solid rgba(0,0,0,0.1);border-collapse: collapse;margin:0;'>"+
								"<thead style='background:rgba(0,0,0,0.1);font-weight: bold;font-family: Titillium;'>"+etiqueta[79]+"</thead>"+
								"<tr style='border:1px solid black;'>"+
									"<td style='margin: 0px; border-right: 1px solid black;padding:8px;font-family: Titillium;'>"+
										"<p>"+etiqueta[130]+" <strong style='color:#F7653A;font-family: Titillium;'>"+reserva.getoPaquete().getTitulo()+"</strong>"+ 
										"</strong> "+etiqueta[133]+" <strong> "+reserva.getnNroPersonas()+" </strong> "+etiqueta[134]+"<strong> "+etiqueta[135]+" </strong>"+
										fechas+
										"<br />"+
										"<p><strong style='text-decoration: underline;font-family: Titillium;'>"+etiqueta[252]+"</strong><br />"+
										"<p style='font-family: Titillium;'>"+reserva.getcInformacionAdicional()+"</p>"+
										"<p><strong style='text-decoration: underline;font-family: Titillium;'>"+etiqueta[250]+"</strong><br />"+
										"<p style='font-family: Titillium;'>"+reserva.getoPaquete().getDescripcion()+"</p>"+
					
										"<p><strong><span style='color:rgb(255, 0, 0);'><strong><span style='color:rgb(0, 0, 0);text-decoration: underline;font-family: Titillium;'>"+etiqueta[251]+"</span></strong><br />"+
										"<a href='"+reserva.getoPaquete().getcUrlReferenciaPaquete()+"' target='_blank'>"+reserva.getoPaquete().getcUrlReferenciaPaquete()+"</a></span></strong></p>"+
									"</td>"+
								"</tr>"+
							"</table>"+
							"<br/>"+
						    "<p style='font-family: Titillium;'> "+etiqueta[138]+" </p>"+
						    pasajeros[0]+
						    hotel[0]+
						    "<br />"+
						    "<table width='100%' style='border:1px solid rgba(0,0,0,0.1);border-collapse: collapse;font-family: Titillium;'>"+
						    	"<thead style='background:rgba(0,0,0,0.1);font-weight: bold;font-family: Titillium;'>"+etiqueta[80]+"</thead>"+
						    	"<tr style='background:rgba(0,0,0,0.1);border:1px solid black;font-family: Titillium;'>"+
						    		"<td style='border:1px solid black;font-family: Titillium;' align='center'>"+etiqueta[141]+"</td>"+
						    		"<td style='border:1px solid black;font-family: Titillium;' align='center'>"+etiqueta[142]+"</td>"+
						    		"<td style='border:1px solid black;font-family: Titillium;' align='center'>"+etiqueta[143]+"</td>"+
						    		"<td style='border:1px solid black;font-family: Titillium;' align='center'>"+etiqueta[144]+"</td>"+
						    		"<td style='border:1px solid black;font-family: Titillium;' align='center'>"+etiqueta[145]+"</td>"+
						    	"</tr>"+
						    	"<tr style='border:1px solid black;'>"+
						    		"<td style='border:1px solid black;font-family: Titillium;' align='left'>"+reserva.getoPaquete().getTitulo()+"</td>"+
						    		"<td style='border:1px solid black;font-family: Titillium;' align='center'>-</td>"+
						    		"<td style='border:1px solid black;font-family: Titillium;' align='right'>"+precioUniPaquete+"</td>"+
						    		"<td style='border:1px solid black;font-family: Titillium;' align='center'>"+reserva.getnNroPersonas()+"</td>"+
						    		"<td style='border:1px solid black;color:#1A5276;font-family: Titillium;' align='right'>"+df.format(reserva.getnPrecioPaquetePersona())+"</td>"+
						    	"</tr>"+
						    	servicios[0]+
						    	actividades[0]+
						    "</table>"+
						    "<table width='100%' style='background:rgba(0,0,0,0.02);border:1px solid black;font-family: Titillium;'>"+
						    	"<tr>"+
						    		"<td width='60%'></td>"+
						    		"<td width='40%'>"+
						    			"<table width='100%'>"+
						    				"<tr><td>"+etiqueta[99]+"</td><td style='color:#1A5276;margin-right:0;font-family: Titillium;' align='right'>"+totalPago+"</td></tr>"+
						    				"<tr><td>"+etiqueta[100]+"("+oImpuesto.getImpuestoPaypal()+"%)</td><td style='color:#1A5276;margin-right:0;font-family: Titillium;' align='right'>"+impuesto+"</td></tr>"+
						    				"<tr><td></td><td style='color:#1A5276;font-family: Titillium;' align='right'>-------------</td></tr>"+
						    				"<tr><td>"+etiqueta[101]+"</td><td style='color:#1A5276;margin-right:0;font-family: Titillium;' align='right'>"+importeTotal+"</td></tr>"+
						    			"</table>"+
						    		"</td>"+
						    	"</tr>"+
						    "</table>"+
						    "<br/>"+
						    "<div style='font-family:Titillium;'>"+etiqueta[146]+"</div>"+
						    "<div style='font-family:Titillium;'>"+etiqueta[147]+"</div>"+
						    "<div align='left' width='100%'>"+
							    "<table width='80%'>"+
								    "<tr align='left'>"+
							    		"<td><a href='"+etiqueta[214]+"'><img width='80' height='80' src='https://www.e-ranti.com/pricing_info/img/logo_facebook.png'/></a></td>"+
							    		"<td><a href='"+etiqueta[215]+"'><img width='60' height='60' src='https://www.e-ranti.com/pricing_info/img/youtube.png'/></a></td>"+
							    		"<td><a href='"+etiqueta[216]+"'><img width='60' height='60' src='https://www.e-ranti.com/pricing_info/img/logo_twitter.png'/></a></td>"+
							    		"<td style='font-family:Titillium;display:flex;padding-top:17px;box-sizing:border-box;'><img width='50' height='50' src='https://www.e-ranti.com/pricing_info/img/wathsapp.png'/><p>"+etiqueta[151]+"</p></td>"+
							    	"</tr>"+
							    "</table>"+
						    "</div>"+
						"</div>"+
//						  "<p style='font-size:11px;'>"+etiqueta[153]+" <strong>"+etiqueta[154]+"</strong>"+etiqueta[155]+"</p>"+
						  "<p style='font-size:18px;color:red;font-weight:bold;font-family: Titillium;'>"+etiqueta[112]+"</p>"+
					        "<strong style='font-family: Titillium;'>"+etiqueta[213]+"</strong>"+
						"</div>"+
						"<div align='center' style='background:gray;"+
							"color:white;"+
							"font-size:15px;font-weight:bold;width:100%;height:60px;padding:8px 0;'>"+
							"<p style='font-family: Titillium;'>Copyright © "+cal.get(Calendar.YEAR)+" "+etiqueta[157]+"</p>"+
						"</div>"+
					  "</div>"+
				"</body>"+
			"</html>";
		boolean b=enviarCorreoSinPagoAEmpresa("Datos de Nueva Reserva", hotel[1], oImpuesto, reserva.getoPaquete(), pasajeros[1], servicios[1], fechaInicio, fechaFin,fechaArribo, reserva, precioUniPaquete, fechaActual[1], fechas, totalPago, urlImage,actividades[1]);
		return sendMail(reserva.getcEmail(),titulo,mensajeHTML,urlPdf,0);
	}
	public boolean enviarCorreoSinPagoAEmpresa(String titulo,String htmlHotel,CImpuesto oImpuesto,CPaquete paquete,String htmlPasajeros,
			String htmlServicios,String fechaInicio,String fechaFin,String fechaArribo,CReserva reserva,String precioUniPaquete,String fechaActual,
			String htmlFechasAlternas,String totalPago,ArrayList<String> urlImage,String htmlActividades) throws IOException, DocumentException
	{
		Calendar cal=Calendar.getInstance();
		/**Se obtiene el impuesto e importe total del totalPago**/
		String impuesto=df.format(Double.parseDouble(totalPago)*(Double.parseDouble(oImpuesto.getImpuestoPaypal())/100));
		String importeTotal=df.format(Double.parseDouble(totalPago)+Double.parseDouble(impuesto));
		/**OBTENEMOS LA FECHA DE ARRIBO**/
		String arribo="";
		if(reserva.getoPaquete().isConFechaArribo())
			arribo="<p style='font-family:Titillium;'>"+etiqueta[243]+": "+fechaArribo+"</p>";
		/*********************************************************/
		String mensajeHTML=
				"<html>"+
						"<head>"+
							"<style type='text/css' media='screen, print'>"+
								"@font-face {"+
									  "font-family: Titillium;"+
									  "font-style: normal;"+
									  "font-weight: 400;"+
									  "src: local('Titillium Web Regular'), local('TitilliumWeb-Regular'), url(https://fonts.gstatic.com/s/titilliumweb/v5/7XUFZ5tgS-tD6QamInJTcSo_WB_cotcEMUw1LsIE8mM.woff2) format('woff2');"+
									  "unicode-range: U+0100-024F, U+1E00-1EFF, U+20A0-20AB, U+20AD-20CF, U+2C60-2C7F, U+A720-A7FF;"+
									"}"+
									/* latin */
									"@font-face {"+
									  "font-family: Titillium;"+
									  "font-style: normal;"+
									  "font-weight: 400;"+
									  "src: local('Titillium Web Regular'), local('TitilliumWeb-Regular'), url(https://fonts.gstatic.com/s/titilliumweb/v5/7XUFZ5tgS-tD6QamInJTcZSnX671uNZIV63UdXh3Mg0.woff2) format('woff2');"+
									  "unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02C6, U+02DA, U+02DC, U+2000-206F, U+2074, U+20AC, U+2212, U+2215;"+
									"}"+
							"</style>"+
						"</head>"+
					"<body>"+
						"<div style='width:600px;background:rgb(242, 242, 242);'>"+
							"<table style='background:white;border:1px solid rgb(204, 204, 204);color:rgb(34, 34, 34);font-family:arial,sans-serif;font-size:12.8px;margin:0px auto;width:600px;border-collapse: collapse;'>"+
								"<tbody>"+
									"<tr>"+
										"<td>"+
											"<table style='background:rgb(242, 242, 242)'>"+
												"<tbody>"+
													"<tr>"+
														"<td>"+
															"<p style='font-family: Titillium;font-weight:bold;font-size:15px;margin:5px 0 6px 0;'>"+fechaActual+"</p>"+
														"</td>"+
													"</tr>"+
													"<tr>"+
														"<td align='left' width='20%' style='padding:8px 20px 8px 20px;'>"+
															"<a href='"+etiqueta[212]+"' style='text-decoration:none;font-family:Titillium;'>"+
																"<img src='"+etiqueta[211]+"' width='100' height='80' border='0' />"+
															"</a>"+
														"</td>"+
														"<td style='font-family: Titillium;color:black;font-size:17px;font-weight:bold;' align='center' width='80%'>"+reserva.getoPaquete().getTitulo()+"</td>"+
														"<td align='right' width='20%' style='padding:8px 20px 8px 20px;'>"+
															"<a href='"+etiqueta[212]+"' style='text-decoration:none;'>"+
																"<img src='"+etiqueta[211]+"' width='100' height='80' border='0' />"+
															"</a>"+
														"</td>"+
													"</tr>"+
												"</tbody>"+
											"</table>"+
										"</td>"+
									"</tr>"+
								"</tbody>"+
							"</table>"+
							"<div style='background:white;width:600px;'>"+
								"<table style='background:white;color:rgb(34, 34, 34);font-family: Titillium;font-size:12.8px;width:100%;border-collapse: collapse;'>"+
									"<tbody>"+
									"<tr>"+
										"<td style='font-family: Titillium; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial; font-weight: bold; font-stretch: normal; font-size: 15px; line-height: normal; color: rgb(65, 88, 132);'>"+
											"<div align='center'>"+
												"<img border='0' src='https://www.e-ranti.com/pricing_demo/img/negocios.jpg' style='cursor:pointer; height:208px; outline:0px; width:600px' tabindex='0'/>"+
											"</div>"+
										"</td>"+
									"</tr>"+
									"</tbody>"+
								"</table>"+
								"<br />"+
								"<table width='100%' style='border:1px solid rgba(0,0,0,0.1);border-collapse: collapse;margin:0;'>"+
											"<thead style='background:rgba(0,0,0,0.1);font-weight: bold;'>DATOS DE RESERVA</thead>"+
												"<tr style='border:1px solid black;'>"+
													"<td style='margin: 0px; text-align: center; border-right: 1px solid black;padding-bottom:15px;' width='240'>"+
														"<p style='color: #1a5276; margin-bottom: 30px;font-family: Titillium;'>"+
															"<strong style='text-decoration: underline;'>CODIGO DE RESERVA</strong>"+
														"</p>"+
														"<span style='font-family: Titillium;background-color:rgb(204, 204, 204); border-radius:3px; border:1px solid rgb(217, 217, 217); color:rgb(47, 115, 186); font-family:arial,helvetica,sans-serif; font-size:24px; font-stretch:normal; line-height:24px; padding:10px'>"+
															"<strong>"+reserva.getcReservaCod()+"</strong>"+
														"</span>"+
													"</td>"+
													"<td style='margin: 0px;padding:0px 15px;' width='308'>"+
														"<p>"+
															"<strong style='text-decoration: underline;font-family: Titillium;'>RESPONSABLE DE LA RESERVA</strong>"+
														"</p>"+
														"<span style='color:rgb(102, 102, 102);font-family: Titillium;'>"+
															"<strong>"+reserva.getcContacto()+"</strong>"+
															"<br/>"+
															"<strong>E-mail: "+reserva.getcEmail()+"</strong>"+
															"<br/>"+
															"<strong>WhatsApp: "+reserva.getcTelefono()+"</strong>"+
														"</span>"+
														"<p>"+
															"<strong style='text-decoration: underline;font-family: Titillium;'>FECHAS DEL TOUR/SERVICIO</strong>"+
														"</p>"+
														"<span>"+
															"<strong><span style='color:rgb(102, 102, 102);font-family: Titillium;'>"+fechaInicio+"</span></strong>"+
															"<br />"+
															"<strong><span style='color:rgb(102, 102, 102);font-family: Titillium;'>"+fechaFin+"</span></strong>"+
															"<br />"+
															"<strong>Estadia en Cusco hasta: </strong><strong><span style='color:rgb(102, 102, 102);font-family: Titillium;'>"+fechaArribo+"</span></strong>"+
														"</span>"+
														"<p>"+
															"<strong style='text-decoration: underline;font-family: Titillium;'>TOTAL PAGO</strong>"+
														"</p>"+
														"<span style='color:rgb(102, 102, 102);font-family: Titillium;'>"+
															"<strong>USD($) "+totalPago+"</strong>"+
														"</span>"+
													"</td>"+
												"</tr>"+
										"</table>"+
									"<br />"+
								"<div>"+
									"<table width='100%' style='border:1px solid rgba(0,0,0,0.1);border-collapse: collapse;margin:0;'>"+
										"<thead style='background:rgba(0,0,0,0.1);font-weight: bold;font-family: Titillium;'>DETALLES DEL PAQUETE</thead>"+
										"<tr style='border:1px solid black;'>"+
											"<td style='margin: 0px; border-right: 1px solid black;padding:8px;font-family: Titillium;'>"+
												"<p>Se efectuo una reserva de <strong style='color:#F7653A;font-family: Titillium;'>"+reserva.getoPaquete().getTitulo()+"</strong>"+ 
												"</strong> para <strong> "+reserva.getnNroPersonas()+" </strong> persona(s)<strong></strong>"+
												htmlFechasAlternas+
												"<br />"+
												"<p><strong style='text-decoration: underline;font-family: Titillium;'>INFORMACION ADICIONAL:</strong><br />"+
												"<p style='font-family: Titillium;'>"+reserva.getcInformacionAdicional()+"</p>"+
												"<p><strong style='text-decoration: underline;font-family: Titillium;'>DESCRIPCION DEL PAQUETE:</strong><br />"+
												"<p style='font-family: Titillium;'>"+reserva.getoPaquete().getDescripcion()+"</p>"+
												"<p><strong><span style='color:rgb(255, 0, 0);'><strong><span style='color:rgb(0, 0, 0);text-decoration: underline;font-family: Titillium;'>URL DE REFERENCIA DEL PAQUETE:</span></strong><br />"+
												"<a href='"+reserva.getoPaquete().getcUrlReferenciaPaquete()+"' target='_blank'>"+reserva.getoPaquete().getcUrlReferenciaPaquete()+"</a></span></strong></p>"+
											"</td>"+
										"</tr>"+
									"</table>"+
									"<br/>"+
								    "<p style='font-family: Titillium;'>Las siguientes tablas muestran datos de la reserva:</p>"+
								    htmlPasajeros+
								    "<br/>"+
								    htmlHotel+
								    "<table width='100%' style='border:1px solid rgba(0,0,0,0.1);border-collapse: collapse;'>"+
								    	"<thead style='background:rgba(0,0,0,0.1);font-weight: bold;font-family: Titillium;'>COSTOS DEL PAQUETE/SERVICIO</thead>"+
								    	"<tr style='background:rgba(0,0,0,0.1);border:1px solid black;'>"+
								    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>Descripción</td>"+
								    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>Sub-Servicio</td>"+
								    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>Precio Unitario (USD)</td>"+
								    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>Cantidad</td>"+
								    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>Precio Total (USD)</td>"+
								    	"</tr>"+
								    	"<tr style='border:1px solid black;'>"+
								    		"<td style='font-family: Titillium;border:1px solid black;' align='left'>"+paquete.getcTituloIdioma1()+"</td>"+
								    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>-</td>"+
								    		"<td style='font-family: Titillium;border:1px solid black;' align='right'>"+precioUniPaquete+"</td>"+
								    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+reserva.getnNroPersonas()+"</td>"+
								    		"<td style='font-family: Titillium;border:1px solid black;color:#1A5276;' align='right'>"+df.format(reserva.getnPrecioPaquetePersona().doubleValue())+"</td>"+
								    	"</tr>"+
								    	htmlServicios+
								    	htmlActividades+
								    "</table>"+
								    "<table width='100%' style='background:rgba(0,0,0,0.02);border:1px solid black;'>"+
								    	"<tr>"+
								    		"<td width='60%'></td>"+
								    		"<td width='40%'>"+
								    			"<table width='100%'>"+
								    				"<tr><td style='font-family: Titillium;'>Subtotal</td><td style='font-family: Titillium;color:#1A5276;' align='right'>"+totalPago+"</td></tr>"+
								    				"<tr><td style='font-family: Titillium;'>Impuesto ("+oImpuesto.getImpuestoPaypal()+"%)</td><td style='font-family: Titillium;color:#1A5276;' align='right'>"+impuesto+"</td></tr>"+
								    				"<tr><td></td><td style='font-family: Titillium;color:#1A5276;' align='right'>--------------</td></tr>"+
								    				"<tr><td style='font-family: Titillium;'>Importe Total</td><td style='font-family: Titillium;color:#1A5276;' align='right'>"+importeTotal+"</td></tr>"+
								    			"</table>"+
								    		"</td>"+
								    	"</tr>"+
								    "</table>"+
								    "<p style='font-family:Titillium;color:red;font-size:25px;'>PENDIENTE DE PAGO</p>"+
								"</div>"+
							        "<strong style='font-family: Titillium;'>"+etiqueta[213]+"</strong>"+
								"</div>"+
								"<div align='center' style='background:gray;"+
									"color:white;"+
									"font-size:15px;font-weight:bold;width:100%;height:60px;padding:8px 0;'>"+
									"<p style='font-family: Titillium'>Copyright © "+cal.get(Calendar.YEAR)+" eddyonsoft.com - Todos los Derechos Reservados</p>"+
								"</div>"+
							  "</div>"+
						"</body>"+
				"</html>";
		return sendMailToEmpresa(reserva.getcEmail(),titulo,mensajeHTML,urlImage);
	}
	public boolean enviarCorreoConPago(String titulo,String[] etiqueta,CImpuesto oImpuesto,CReservaPaqueteCategoriaHotel oReservaPCH,
			String fechaInicio,String fechaFin,String fechaArribo,CReserva reserva,
			ArrayList<String> fechasAlternas,String totalPago,String montoPagar,String urlPdf,String codTransaccion,
			String porcentajePago,ArrayList<CPasajero> listaPasajeros,
			CPagos pagos) throws IOException, DocumentException
	{
		this.etiqueta=etiqueta;
		//==Obtener nombre del pais de origen del cliente==
				String paisPasajero="";
				if(reserva.getoPaquete().isbLlenarDatosUnPax())
				{
					paisPasajero="<strong>"+reserva.getoPasajeroReservante().getNombrePais()+"</strong>"+
							"<br/>";
				}
				//=================================================
		/****************************************/
		String textoParcial="";
		String textoTotal="";
		if(reserva.getoPaquete().isbModoPorcentual())
		{
			textoParcial=reserva.getoPaquete().getnPorcentajeCobro()+" %";
			textoTotal="100 %";
		}else
		{
			textoParcial=etiqueta[102];
			textoTotal=etiqueta[103];
		}
		/*******************/
		String pagoAl="";
		if(porcentajePago.equals("1"))//pago parcial
		{
			String auxImpuesto="";
			if(pagos.isSelectPaypal())
				auxImpuesto=df.format(Double.parseDouble(montoPagar)*(Double.parseDouble(oImpuesto.getImpuestoPaypal())/100));
			else if(pagos.isSelectMasterCard())
				auxImpuesto=df.format(Double.parseDouble(montoPagar)*(Double.parseDouble(oImpuesto.getImpuestoMasterCard())/100));
			else if(pagos.isSelectDinersClub())
				auxImpuesto=df.format(Double.parseDouble(montoPagar)*(Double.parseDouble(oImpuesto.getImpuestoDinnersClub())/100));
			String auxImporteTotal=df.format(Double.parseDouble(montoPagar)+Double.parseDouble(auxImpuesto));
			pagoAl= "<table width='100%' style='border:1px solid rgba(0,0,0,0.5);border-collapse: collapse;'>"+
						"<thead style='font-family: Titillium;font-weight: bold;font-size:20px;'>"+etiqueta[197]+"</thead>"+
				    	"<tr style='background:rgba(0,0,0,0.1)'>"+
							"<td width='45%' align='center'><h1 style='font-family: Titillium;color:#F7653A;font-weight:bold;'>"+textoParcial+"</h1></td>"+
				    		"<td width='55%'>"+
				    			"<table width='100%'>"+
				    				"<tr><td style='font-family: Titillium;'>"+etiqueta[99]+" ("+textoParcial+"): USD"+"</td><td style='font-family: Titillium;color:#1A5276;' align='right'>"+montoPagar+"</td></tr>"+
				    				"<tr><td style='font-family: Titillium;'>"+etiqueta[100]+"("+oImpuesto.getImpuestoPaypal()+"%): USD</td><td style='font-family: Titillium;color:#1A5276;' align='right'>"+auxImpuesto+"</td></tr>"+
				    				"<tr><td></td><td style='color:#1A5276;' align='right'>--------------</td></tr>"+
				    				"<tr><td style='font-family: Titillium;'>"+etiqueta[101]+" ("+textoParcial+")"+"</td><td align='right' style='font-family: Titillium;background:#75BE5C;font-weight:bold;'>"+"USD "+auxImporteTotal+"</td></tr>"+
				    				"<tr><td style='font-family: Titillium;'>"+etiqueta[161]+"</td><td align='right' style='font-family: Titillium;background:red;font-weight:bold;'>"+"USD "+df.format(Double.parseDouble(totalPago)-Double.parseDouble(montoPagar))+"</td></tr>"+
				    			"</table>"+
				    		"</td>"+
				    	"</tr>"+
				    "</table>"+
				    "<p style='font-family: Titillium;'>"+etiqueta[159]+" <strong style='font-family: Titillium;color:#F7653A;'> "+textoParcial+" </strong><br/> "+etiqueta[160]+"</p>";
		}
		else//pago total
		{
			/**Se obtiene el impuesto e importe total del totalPago**/
			String impuesto="";
			if(pagos.isSelectPaypal())
				impuesto=df.format(Double.parseDouble(totalPago)*(Double.parseDouble(oImpuesto.getImpuestoPaypal())/100));
			else if(pagos.isSelectMasterCard())
				impuesto=df.format(Double.parseDouble(totalPago)*(Double.parseDouble(oImpuesto.getImpuestoMasterCard())/100));
			else if(pagos.isSelectDinersClub())
				impuesto=df.format(Double.parseDouble(totalPago)*(Double.parseDouble(oImpuesto.getImpuestoDinnersClub())/100));
			String importeTotal=df.format(Double.parseDouble(totalPago)+Double.parseDouble(impuesto));
			/*********************************************************/
			pagoAl="<table width='100%' style='border:1px solid rgba(0,0,0,0.5);border-collapse: collapse;'>"+
					"<thead style='font-family: Titillium;font-weight: bold;font-size:20px;'>"+etiqueta[197]+"</thead>"+
			    	"<tr style='background:rgba(0,0,0,0.1)'>"+
			    		"<td width='45%' align='center'><h1 style='font-family: Titillium;color:#F7653A;font-weight:bold;'>"+textoTotal+"</h1></td>"+
			    		"<td width='55%'>"+
			    			"<table width='100%'>"+
			    				"<tr><td style='font-family: Titillium;'>"+etiqueta[99]+" ( "+textoTotal+" ): USD"+"</td><td style='font-family: Titillium;color:#1A5276;' align='right'> "+totalPago+" </td></tr>"+
			    				"<tr><td style='font-family: Titillium;'>"+etiqueta[100]+"("+oImpuesto.getImpuestoPaypal()+"%): USD</td><td style='font-family: Titillium;color:#1A5276;' align='right'> "+impuesto+" </td></tr>"+
			    				"<tr><td></td><td style='color:#1A5276;' align='right'>--------------</td></tr>"+
			    				"<tr><td style='font-family: Titillium;'>"+etiqueta[101]+" ( "+textoTotal+" )"+"</td><td align='right'style='font-family: Titillium;background:#75BE5C;font-weight:bold;'>"+"USD "+importeTotal+"</td></tr>"+
			    			"</table>"+
			    		"</td>"+
			    	"</tr>"+
			    "</table>";
		}
		
		Calendar cal=Calendar.getInstance();
		String dia = Integer.toString(cal.get(Calendar.DATE));
		int auxMes = cal.get(Calendar.MONTH)+1;
		String annio = Integer.toString(cal.get(Calendar.YEAR));
		String mes=obtenerTxtMes(auxMes);
		
		String fechaActual=dia+" "+etiqueta[158]+" "+mes+", "+annio;
		/**Se obtiene el precio unitario del paquete acorde al numero de pasajeros esto debido a que se aplica descuento**/
		String precioUniPaquete="";
		int numPas=reserva.getnNroPersonas();
		if(numPas==1)precioUniPaquete=reserva.getoPaquete().getnPrecioUno().toString();
		if(numPas==2)precioUniPaquete=reserva.getoPaquete().getnPrecioDos().toString();
		if(numPas==3)precioUniPaquete=reserva.getoPaquete().getnPrecioTres().toString();
		if(numPas==4)precioUniPaquete=reserva.getoPaquete().getnPrecioCuatro().toString();
		if(numPas==5)precioUniPaquete=reserva.getoPaquete().getnPrecioCinco().toString();
		/***********************************************
		 * Se obtiene la categoria del hotel reservado
		 * *********************************************
		 */
		String hotel="";
		if(!oReservaPCH.isConCamaAdicional())
			oReservaPCH.setPrecioCamaAdicional("0.00");
		if(oReservaPCH!=null)
			if(oReservaPCH.isConHotel())
			{	hotel="<table width='100%' style='border:1px solid rgba(0,0,0,0.1);border-collapse: collapse;'>"+
			    	"<thead style='font-family: Titillium;font-weight: bold;'>"+etiqueta[52]+"</thead>"+
			    	"<tr style='background:rgba(0,0,0,0.1);border:1px solid black;'>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+etiqueta[51]+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+etiqueta[201]+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+etiqueta[202]+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+etiqueta[203]+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+etiqueta[204]+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+etiqueta[205]+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+etiqueta[206]+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+etiqueta[233]+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+etiqueta[145]+"</td>"+
			    	"</tr>"+
			    	"<tr style='border:1px solid black;'>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+oReservaPCH.getCategoria()+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+oReservaPCH.getnNroPersonasSimple()+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+df.format(oReservaPCH.getnPrecioTotalSimple().doubleValue()/oReservaPCH.getnNroPersonasSimple())+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+oReservaPCH.getnNroPersonasDoble()/2+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+df.format(oReservaPCH.getnPrecioTotalDoble().doubleValue()/(oReservaPCH.getnNroPersonasDoble()/2))+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+oReservaPCH.getnNroPersonasTriple()/3+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+df.format(oReservaPCH.getnPrecioTotalTriple().doubleValue()/(oReservaPCH.getnNroPersonasTriple()/3))+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+oReservaPCH.getPrecioCamaAdicional()+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;color:#1A5276;' align='right'>"+df.format(oReservaPCH.getnPrecioTotalSimple().doubleValue()+oReservaPCH.getnPrecioTotalDoble().doubleValue()+oReservaPCH.getnPrecioTotalTriple().doubleValue())+"</td>"+
			    	"</tr>"+
			    "</table>"+
			    "<br/>";
				for(CDestinoConHoteles DCH:oReservaPCH.getListaCategoriaDestinosHoteles())
				{
					hotel+="<table width='40%' style='border:1px solid rgba(0,0,0,0.1);border-collapse: collapse;'>"+
					    	"<thead style='font-family: Titillium;font-weight: bold;'>"+DCH.getoDestino().getcDestino()+"</thead>";
					for(CHotel hoteles:DCH.getListaDestinosHoteles())
					{
						hotel+="<tr style='background:rgba(0,0,0,0.1);border:1px solid black;'>"+
						    		"<td style='font-family: Titillium;border:1px solid black;' align='left'>"+
						    			"<a href='"+hoteles.getcUrl()+"'>"+hoteles.getcHotel()+"</a>"+
						    		"</td>"+
						    	"</tr>";
					}
					hotel+="</table>"+
						    "<br/>";
				}
			}
			/***********************************************/
		/**Se obtienen las fechas alternas para el html**/
		String fechas="";
		if(fechasAlternas!=null && !fechasAlternas.isEmpty())
		{
			for(int i=0;i<fechasAlternas.size();i++)
			{
				fechas+=
						"<tr align='center'>"+
				           "<td style='font-family: Titillium;color:#F7653A;font-weight:bold;'>"+fechasAlternas.get(i)+"</td>"+
				        "</tr>";
			}
			fechas="<p style='font-family: Titillium;'>"+etiqueta[137]+"</p>"+
					"<table width='40%'>"+
						fechas+
				    "</table>";
		}
		/******************************************/
		/**Se obtienen los datos de los pasajeros**/
		String pasajeros[]=obtenerHtmlPasajeros(listaPasajeros,etiqueta,reserva);
		/******************************************/
		/**Se obtienen los datos de los servicios**/
		String servicios="";
		if(reserva.getoPaquete().getListaServicios()!=null)
			for(CServicio servicio:reserva.getoPaquete().getListaServicios())
			{
				if(!servicio.getOpcionValue().equals("0"))
				{
					servicios+=
							"<tr style='border:1px solid black;'>"+
						    		"<td style='font-family: Titillium;border:1px solid black;' align='left'>"+servicio.getServicio()+"</td>";
							if(servicio.getcRestriccionNum()==0 && servicio.getcRestriccionYesNo()==0)
						    		servicios+="<td style='font-family: Titillium;border:1px solid black;' align='center'>"+servicio.getSelectOpcion()+"</td>";
							else
								servicios+="<td style='font-family: Titillium;border:1px solid black;' align='center'>-</td>";
						    servicios+="<td style='font-family: Titillium;border:1px solid black;' align='right'>"+servicio.getnPrecioServicio().toString()+"</td>"+
						    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+servicio.getOpcionValue()+"</td>"+
						    		"<td style='font-family: Titillium;border:1px solid black;color:#1A5276;' align='right'>"+servicio.getPrecioTotalServicio()+"</td>"+
						    	"</tr>";
				}
			}
		String[] actividades=obtenerHtmlActividades(reserva.getoPaquete().getListaActividades(), reserva);
		/**OBTENEMOS LA FECHA DE ARRIBO**/
		String arribo="";
		if(reserva.getoPaquete().isConFechaArribo())
			arribo="<p style='font-family: Titillium;'>"+etiqueta[243]+": "+fechaArribo+"</p>";
		//Generando codigo QR
		QRCode qr = new QRCode();
		String nameImgQR=generarNombreQR();
        File f = new File(ScannUtil.getPathImagenQR()+nameImgQR);
        //FPP=yuri vladimir huallpa vargas=camino inka=01/06/2017=05/06/2017=5=300=931896923=yurihuallpavargas@gmail.com
        String text ="FPP="+reserva.getcContacto()+"="+reserva.getoPaquete().getTitulo()+"="+
        			fechaInicio+"="+fechaFin+"="+reserva.getnNroPersonas()+"="+montoPagar+"="+
        			reserva.getcTelefono()+"="+reserva.getcEmail();
 
        try {
 
            qr.generateQR(f, text, 300, 300);
 
        } catch (Exception e) {
            e.printStackTrace();
        }
		/******************************************/
		String mensajeHTML=
				"<html>"+
					"<head>"+
						"<style type='text/css' media='screen, print'>"+
							"@font-face {"+
								  "font-family: Titillium;"+
								  "font-style: normal;"+
								  "font-weight: 400;"+
								  "src: local('Titillium Web Regular'), local('TitilliumWeb-Regular'), url(https://fonts.gstatic.com/s/titilliumweb/v5/7XUFZ5tgS-tD6QamInJTcSo_WB_cotcEMUw1LsIE8mM.woff2) format('woff2');"+
								  "unicode-range: U+0100-024F, U+1E00-1EFF, U+20A0-20AB, U+20AD-20CF, U+2C60-2C7F, U+A720-A7FF;"+
								"}"+
								/* latin */
								"@font-face {"+
								  "font-family: Titillium;"+
								  "font-style: normal;"+
								  "font-weight: 400;"+
								  "src: local('Titillium Web Regular'), local('TitilliumWeb-Regular'), url(https://fonts.gstatic.com/s/titilliumweb/v5/7XUFZ5tgS-tD6QamInJTcZSnX671uNZIV63UdXh3Mg0.woff2) format('woff2');"+
								  "unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02C6, U+02DA, U+02DC, U+2000-206F, U+2074, U+20AC, U+2212, U+2215;"+
								"}"+
						"</style>"+
					"</head>"+
					"<body>"+
						"<div style='width:600px;background:rgb(242, 242, 242);'>"+
							"<table style='background:white;border:1px solid rgb(204, 204, 204);color:rgb(34, 34, 34);font-family:arial,sans-serif;font-size:12.8px;margin:0px auto;width:600px;border-collapse: collapse;'>"+
								"<tbody>"+
									"<tr>"+
										"<td>"+
											"<table style='background:rgb(242, 242, 242)'>"+
												"<tbody>"+
													"<tr>"+
														"<td>"+
															"<p style='font-family: Titillium;font-weight:bold;font-size:15px;margin:5px 0 6px 0;'>"+fechaActual+"</p>"+
														"</td>"+
													"</tr>"+
													"<tr>"+
														"<td align='left' width='20%' style='padding:8px 20px 8px 20px;'>"+
															"<a href='"+etiqueta[212]+"' style='text-decoration:none;'>"+
																"<img src='"+etiqueta[211]+"' width='100' height='80' border='0' />"+
															"</a>"+
														"</td>"+
														"<td style='font-family: Titillium;color:black;font-size:17px;font-weight:bold;' align='center' width='80%'>"+reserva.getoPaquete().getTitulo()+"</td>"+
														"<td align='right' width='20%' style='padding:8px 20px 8px 20px;'>"+
															"<a style='text-decoration:none;'>"+
																"<img src='http://localhost:8080/pricing/img/QR/"+nameImgQR+"' width='100' height='80' border='0' />"+
															"</a>"+
														"</td>"+
													"</tr>"+
												"</tbody>"+
											"</table>"+
										"</td>"+
									"</tr>"+
								"</tbody>"+
							"</table>"+
							"<div style='background:white;width:600px;'>"+
								"<table style='background:white;color:rgb(34, 34, 34);font-family: Titillium;font-size:12.8px;width:100%;border-collapse: collapse;'>"+
									"<tbody>"+
									"<tr>"+
										"<td style='font-family: Titillium; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial; font-weight: bold; font-stretch: normal; font-size: 15px; line-height: normal; color: rgb(65, 88, 132);'>"+
											"<div align='center'>"+
												"<img border='0' src='https://www.e-ranti.com/pricing_demo/img/negocios.jpg' style='cursor:pointer; height:208px; outline:0px; width:600px' tabindex='0'/>"+
											"</div>"+
										"</td>"+
									"</tr>"+
									"</tbody>"+
								"</table>"+
								"<br />"+
								"<table width='100%' style='border:1px solid rgba(0,0,0,0.1);border-collapse: collapse;margin:0;'>"+
								"<thead style='background:rgba(0,0,0,0.1);font-weight: bold;'>DATOS DE RESERVA</thead>"+
									"<tr style='border:1px solid black;'>"+
										"<td style='margin: 0px; text-align: center; border-right: 1px solid black;padding-bottom:15px;' width='260'>"+
											"<p style='color: #1a5276; margin-bottom: 30px;font-family: Titillium;'>"+
												"<strong style='text-decoration: underline;'>"+etiqueta[118]+"</strong>"+
											"</p>"+
											"<span style='font-family: Titillium;background-color:rgb(204, 204, 204); border-radius:3px; border:1px solid rgb(217, 217, 217); color:rgb(47, 115, 186); font-family:arial,helvetica,sans-serif; font-size:24px; font-stretch:normal; line-height:24px; padding:10px'>"+
												"<strong>"+reserva.getcReservaCod()+"</strong>"+
											"</span>"+
											"<p style='color: #1a5276; margin-bottom: 30px;font-family: Titillium;'>"+
												"<strong style='text-decoration: underline;'>"+etiqueta[123]+"</strong>"+
											"</p>"+
											"<span style='font-family: Titillium;background-color:rgb(204, 204, 204); border-radius:3px; border:1px solid rgb(217, 217, 217); color:rgb(47, 115, 186); font-family:arial,helvetica,sans-serif; font-size:24px; font-stretch:normal; line-height:24px; padding:10px'>"+
												"<strong>"+codTransaccion+"</strong>"+
											"</span>"+
										"</td>"+
										"<td style='margin: 0px;padding:0px 15px;' width='300'>"+
											"<p>"+
												"<strong style='text-decoration: underline;font-family: Titillium;'>"+etiqueta[125]+"</strong>"+
											"</p>"+
											"<span style='color:rgb(102, 102, 102);font-family: Titillium;'>"+
												"<strong>"+reserva.getcContacto()+"</strong><br/>"+
												paisPasajero+
											"</span>"+
											"<p>"+
												"<strong style='text-decoration: underline;font-family: Titillium;'>"+etiqueta[253]+"</strong>"+
											"</p>"+
											"<span>"+
												"<strong><span style='color:rgb(102, 102, 102);font-family: Titillium;'>"+fechaInicio+"</span></strong>"+
												"<br />"+
												"<strong><span style='color:rgb(102, 102, 102);font-family: Titillium;'>"+fechaFin+"</span></strong>"+
												"<br />"+
												"<strong>"+etiqueta[249]+"</strong><strong><span style='color:rgb(102, 102, 102);font-family: Titillium;'>"+fechaArribo+"</span></strong>"+
											"</span>"+
											"<p>"+
												"<strong style='text-decoration: underline;font-family: Titillium;'>"+etiqueta[81]+"</strong>"+
											"</p>"+
											"<span style='color:rgb(102, 102, 102);font-family: Titillium;'>"+
												"<strong>USD($) "+totalPago+"</strong>"+
											"</span>"+
										"</td>"+
									"</tr>"+
							"</table>"+
							"<br />"+
							"<div>"+
								"<table width='100%' style='border:1px solid rgba(0,0,0,0.1);border-collapse: collapse;margin:0;'>"+
									"<thead style='background:rgba(0,0,0,0.1);font-weight: bold;font-family: Titillium;'>"+etiqueta[79]+"</thead>"+
									"<tr style='border:1px solid black;'>"+
										"<td style='margin: 0px; border-right: 1px solid black;padding:8px;font-family: Titillium;'>"+
											"<p>"+etiqueta[130]+" <strong style='color:#F7653A;font-family: Titillium;'>"+reserva.getoPaquete().getTitulo()+"</strong>"+ 
											"<strong> "+etiqueta[133]+" </strong> "+reserva.getnNroPersonas()+" <strong> "+etiqueta[134]+"</strong> "+etiqueta[135]+"</p>"+
											fechas+
											"<p style='font-family: Titillium;'><strong style='text-decoration: underline;font-family: Titillium;'>"+etiqueta[252]+"</strong><br/>"+
											reserva.getcInformacionAdicional()+"</p>"+
											"<p style='font-family: Titillium;'><strong style='text-decoration: underline;font-family: Titillium;'>"+etiqueta[250]+"</strong><br />"+
											reserva.getoPaquete().getDescripcion()+"</p>"+
						
											"<p><strong><span style='color:rgb(255, 0, 0);'><strong><span style='color:rgb(0, 0, 0);text-decoration: underline;font-family: Titillium;'>"+etiqueta[251]+"</span></strong><br />"+
											"<a href='"+reserva.getoPaquete().getcUrlReferenciaPaquete()+"' target='_blank'>"+reserva.getoPaquete().getcUrlReferenciaPaquete()+"</a></span></strong></p>"+
										"</td>"+
									"</tr>"+
								"</table>"+
								"<br/>"+
								    "<p>"+etiqueta[138]+"</p>"+
								    pasajeros[0]+
								    "<br/>"+
								    hotel+
								    "<table width='100%' style='border:1px solid rgba(0,0,0,0.1);border-collapse: collapse;'>"+
								    	"<thead style='background:rgba(0,0,0,0.1);font-weight: bold;font-family: Titillium;'>"+etiqueta[80]+"</thead>"+
								    	"<tr style='background:rgba(0,0,0,0.1);border:1px solid black;'>"+
								    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+etiqueta[141]+"</td>"+
								    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+etiqueta[142]+"</td>"+
								    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+etiqueta[143]+"</td>"+
								    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+etiqueta[144]+"</td>"+
								    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+etiqueta[145]+"</td>"+
								    	"</tr>"+
								    	"<tr style='border:1px solid black;'>"+
								    		"<td style='font-family: Titillium;border:1px solid black;' align='left'>"+reserva.getoPaquete().getTitulo()+"</td>"+
								    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>-</td>"+
								    		"<td style='font-family: Titillium;border:1px solid black;' align='right'>"+precioUniPaquete+"</td>"+
								    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+reserva.getnNroPersonas()+"</td>"+
								    		"<td style='font-family: Titillium;border:1px solid black;color:#1A5276;' align='right'>"+df.format(reserva.getnPrecioPaquetePersona().doubleValue())+"</td>"+
								    	"</tr>"+
								    	servicios+
								    	actividades[0]+
								    "</table>"+
								    "<table width='100%' style='background:rgba(0,0,0,0.02);border:1px solid black;'>"+
								    	"<tr>"+
								    		"<td width='60%'></td>"+
								    		"<td width='40%'>"+
								    			"<table width='100%'>"+
								    				"<tr><td style='font-family: Titillium;'>"+etiqueta[107]+"</td><td style='font-family: Titillium;color:#1A5276;' align='right'>"+totalPago+"</td></tr>"+
								    			"</table>"+
								    		"</td>"+
								    	"</tr>"+
								    "</table>"+
								    "<br/>"+
								    pagoAl+
								    "<br/>"+
								    "<p style='font-family: Titillium;'>"+etiqueta[146]+"</p>"+
								    "<p style='font-family: Titillium;'>"+etiqueta[147]+"</p>"+
								    "<div align='left' width='100%'>"+
									    "<table width='80%'>"+
										    "<tr align='left'>"+
									    		"<td><a href='"+etiqueta[214]+"'><img width='80' height='80' src='https://www.e-ranti.com/pricing_info/img/logo_facebook.png'/></a></td>"+
									    		"<td><a href='"+etiqueta[215]+"'><img width='60' height='60' src='https://www.e-ranti.com/pricing_info/img/youtube.png'/></a></td>"+
									    		"<td><a href='"+etiqueta[216]+"'><img width='60' height='60' src='https://www.e-ranti.com/pricing_info/img/logo_twitter.png'/></a></td>"+
									    		"<td style='font-family:Titillium;display:flex;padding-top:17px;box-sizing:border-box;'><img width='50' height='50' src='https://www.e-ranti.com/pricing_info/img/wathsapp.png'/><p>"+etiqueta[151]+"</p></td>"+
									    	"</tr>"+
									    "</table>"+
								    "</div>"+
								"</div>"+
							        "<strong style='font-family: Titillium;'>"+etiqueta[213]+"</strong>"+
								"</div>"+
								"<div align='center' style='background:gray;"+
									"color:white;"+
									"font-size:15px;font-weight:bold;width:100%;height:60px;padding:8px 0;'>"+
									"<p style='font-family: Titillium;'>Copyright © "+cal.get(Calendar.YEAR)+" "+etiqueta[157]+"</p>"+
								"</div>"+
							  "</div>"+
						"</body>"+
				"</html>";
		convertHTML_PDF.convertirHtml2Pdf(mensajeHTML,urlPdf);
		return sendMail(reserva.getcEmail(),titulo,mensajeHTML,urlPdf,1);
	}
	public boolean enviarCorreoConPagoAEmpresa(String titulo,CImpuesto oImpuesto,CReservaPaqueteCategoriaHotel oReservaPCH,
			String fechaInicio,String fechaFin,String fechaArribo,CReserva reserva,
			ArrayList<String> fechasAlternas,String totalPago,String montoPagar,ArrayList<String> imagenes,String codTransaccion,
			String porcentajePago,ArrayList<CPasajero> listaPasajeros,
			CPagos pagos) throws IOException, DocumentException
	{
		//==Obtener nombre del pais de origen del cliente==
		String paisPasajero="";
		if(reserva.getoPaquete().isbLlenarDatosUnPax())
		{
			paisPasajero="<strong>Pais: "+reserva.getoPasajeroReservante().getNombrePais()+"</strong>"+
					"<br/>";
		}
		//=================================================
		String pagoAl="";
		if(porcentajePago.equals("1"))//pago al 40%
		{
			String textoPorcentaje="";
			if(reserva.getoPaquete().isbModoPorcentual())
				textoPorcentaje=reserva.getoPaquete().getnPorcentajeCobro()+" %";
			else
				textoPorcentaje=etiqueta[102];
			String auxImpuesto="";
			if(pagos.isSelectPaypal())
				auxImpuesto=df.format(Double.parseDouble(montoPagar)*(Double.parseDouble(oImpuesto.getImpuestoPaypal())/100));
			else if(pagos.isSelectMasterCard())
				auxImpuesto=df.format(Double.parseDouble(montoPagar)*(Double.parseDouble(oImpuesto.getImpuestoMasterCard())/100));
			else if(pagos.isSelectDinersClub())
				auxImpuesto=df.format(Double.parseDouble(montoPagar)*(Double.parseDouble(oImpuesto.getImpuestoDinnersClub())/100));
			String auxImporteTotal=df.format(Double.parseDouble(montoPagar)+Double.parseDouble(auxImpuesto));
			pagoAl= "<table width='100%' style='border:1px solid rgba(0,0,0,0.5);border-collapse: collapse;'>"+
						"<thead style='font-family: Titillium;font-weight: bold;font-size:20px;'>PAGO EFECTUADO</thead>"+
				    	"<tr style='background:rgba(0,0,0,0.1)'>"+
							"<td width='60%' align='center'><h1 style='font-family: Titillium;color:#F7653A;font-weight:bold;'>"+textoPorcentaje+"</h1></td>"+
				    		"<td width='40%'>"+
				    			"<table width='100%'>"+
				    				"<tr><td style='font-family: Titillium;'>SubTotal"+textoPorcentaje+": USD</td><td style='font-family: Titillium;color:#1A5276;' align='right'>"+montoPagar+"</td></tr>"+
				    				"<tr><td style='font-family: Titillium;'>Impuesto"+oImpuesto.getImpuestoPaypal()+"%: USD</td><td style='font-family: Titillium;color:#1A5276;' align='right'>"+auxImpuesto+"</td></tr>"+
				    				"<tr><td></td><td style='font-family: Titillium;color:#1A5276;' align='right'>--------------</td></tr>"+
				    				"<tr><td style='font-family: Titillium;'>Importe Total"+textoPorcentaje+":</td><td align='right' style='font-family: Titillium;background:#75BE5C;font-weight:bold;'>"+"USD "+auxImporteTotal+"</td></tr>"+
				    				"<tr><td style='font-family: Titillium;'>Restante:</td><td align='right' style='font-family: Titillium;background:red;font-weight:bold;'>"+"USD "+df.format(Double.parseDouble(totalPago)-Double.parseDouble(montoPagar))+"</td></tr>"+
				    			"</table>"+
				    		"</td>"+
				    	"</tr>"+
				    "</table>";
		}
		else//pago total
		{
			String textoPorcentaje="";
			if(reserva.getoPaquete().isbModoPorcentual())
				textoPorcentaje="100 %";
			else
				textoPorcentaje=etiqueta[103];
			/**Se obtiene el impuesto e importe total del totalPago**/
			String impuesto="";
			if(pagos.isSelectPaypal())
				impuesto=df.format(Double.parseDouble(totalPago)*(Double.parseDouble(oImpuesto.getImpuestoPaypal())/100));
			else if(pagos.isSelectMasterCard())
				impuesto=df.format(Double.parseDouble(totalPago)*(Double.parseDouble(oImpuesto.getImpuestoMasterCard())/100));
			else if(pagos.isSelectDinersClub())
				impuesto=df.format(Double.parseDouble(totalPago)*(Double.parseDouble(oImpuesto.getImpuestoDinnersClub())/100));
			String importeTotal=df.format(Double.parseDouble(totalPago)+Double.parseDouble(impuesto));
			/*********************************************************/
			pagoAl="<table width='100%' style='border:1px solid rgba(0,0,0,0.5);border-collapse: collapse;'>"+
					"<thead style='font-family: Titillium;font-weight: bold;font-size:20px;'>PAGO EFECTUADO</thead>"+
			    	"<tr style='background:rgba(0,0,0,0.1)'>"+
			    		"<td width='60%' align='center'><h1 style='font-family: Titillium;color:#F7653A;font-weight:bold;'>"+textoPorcentaje+"</h1></td>"+
			    		"<td width='40%'>"+
			    			"<table width='100%'>"+
			    				"<tr><td style='font-family: Titillium;'>SubTotal"+textoPorcentaje+": USD</td><td align='right' style='font-family: Titillium;'>"+totalPago+"</td></tr>"+
			    				"<tr><td style='font-family: Titillium;'>Impuesto"+oImpuesto.getImpuestoPaypal()+"%: USD</td><td align='right' style='font-family: Titillium;'>"+impuesto+"</td></tr>"+
			    				"<tr><td></td><td align='right' style='font-family: Titillium;'>--------------</td></tr>"+
			    				"<tr><td style='font-family: Titillium;'>Importe Total"+textoPorcentaje+":</td><td align='right'style='font-family: Titillium;background:#75BE5C;font-weight:bold;'>"+"USD "+importeTotal+"</td></tr>"+
			    			"</table>"+
			    		"</td>"+
			    	"</tr>"+
			    "</table>";
		}
		Calendar cal=Calendar.getInstance();
		String dia = Integer.toString(cal.get(Calendar.DATE));
		int auxMes = cal.get(Calendar.MONTH)+1;
		String annio = Integer.toString(cal.get(Calendar.YEAR));
		String mes=obtenerTxtMesES(auxMes);
		
		String fechaActual=dia+" de "+mes+", "+annio;
		/**Se obtiene el precio unitario del paquete acorde al numero de pasajeros esto debido a que se aplica descuento**/
		String precioUniPaquete="";
		int numPas=reserva.getnNroPersonas();
		if(numPas==1)precioUniPaquete=reserva.getoPaquete().getnPrecioUno().toString();
		if(numPas==2)precioUniPaquete=reserva.getoPaquete().getnPrecioDos().toString();
		if(numPas==3)precioUniPaquete=reserva.getoPaquete().getnPrecioTres().toString();
		if(numPas==4)precioUniPaquete=reserva.getoPaquete().getnPrecioCuatro().toString();
		if(numPas==5)precioUniPaquete=reserva.getoPaquete().getnPrecioCinco().toString();
		/***********************************************
		 * Se obtiene la categoria del hotel reservado
		 * *********************************************
		 */
		String hotel="";
		if(!oReservaPCH.isConCamaAdicional())
			oReservaPCH.setPrecioCamaAdicional("0.00");
		if(oReservaPCH!=null)
			if(oReservaPCH.isConHotel())
			{	hotel="<table width='100%' style='border:1px solid rgba(0,0,0,0.1);border-collapse: collapse;'>"+
			    	"<thead style='font-family: Titillium;font-weight: bold;'>HOTEL</thead>"+
			    	"<tr style='background:rgba(0,0,0,0.1);border:1px solid black;'>"+
				    	"<td style='font-family: Titillium;border:1px solid black;' align='center'>Categoria</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>Simples </td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>P.U. Simple</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>Dobles</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>P.U. Doble</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>Triples</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>P.U. Triple</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>Cama Adicional</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>Total</td>"+
			    	"</tr>"+
			    	"<tr style='border:1px solid black;'>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+oReservaPCH.getCategoria()+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+oReservaPCH.getnNroPersonasSimple()+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+df.format(oReservaPCH.getnPrecioTotalSimple().doubleValue()/oReservaPCH.getnNroPersonasSimple())+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+oReservaPCH.getnNroPersonasDoble()/2+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+df.format(oReservaPCH.getnPrecioTotalDoble().doubleValue()/(oReservaPCH.getnNroPersonasDoble()/2))+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+oReservaPCH.getnNroPersonasTriple()/3+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+df.format(oReservaPCH.getnPrecioTotalTriple().doubleValue()/(oReservaPCH.getnNroPersonasTriple()/3))+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+oReservaPCH.getPrecioCamaAdicional()+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;color:#1A5276;' align='right'>"+df.format(oReservaPCH.getnPrecioTotalSimple().doubleValue()+oReservaPCH.getnPrecioTotalDoble().doubleValue()+oReservaPCH.getnPrecioTotalTriple().doubleValue())+"</td>"+
			    	"</tr>"+
			    "</table>"+
			    "<br/>";
				for(CDestinoConHoteles DCH:oReservaPCH.getListaCategoriaDestinosHoteles())
				{
					hotel+="<table width='40%' style='border:1px solid rgba(0,0,0,0.1);border-collapse: collapse;'>"+
					    	"<thead style='font-family: Titillium;font-weight: bold;'>"+DCH.getoDestino().getcDestino()+"</thead>";
					for(CHotel hoteles:DCH.getListaDestinosHoteles())
					{
						hotel+="<tr style='background:rgba(0,0,0,0.1);border:1px solid black;'>"+
						    		"<td style='font-family: Titillium;border:1px solid black;' align='left'>"+
						    			"<a href='"+hoteles.getcUrl()+"'>"+hoteles.getcHotel()+"</a>"+
						    		"</td>"+
						    	"</tr>";
					}
					hotel+="</table>"+
						    "<br/>";
				}
			}
			/***********************************************/
		/**Se obtienen las fechas alternas para el html**/
		String fechas="";
		if(fechasAlternas!=null)
		{
			for(int i=0;i<fechasAlternas.size();i++)
			{
				fechas+=
						"<tr align='center'>"+
				           "<td style='font-family: Titillium;color:#F7653A;font-weight:bold;'>"+fechasAlternas.get(i)+"</td>"+
				        "</tr>";
			}
			
			fechas="<p style='font-family: Titillium;'>Con la siguiente fecha alterna:</p>"+
					"<table width='40%'>"+
						fechas+
				    "</table>";
		}
		/**********************************************/
		/**Se obtienen los datos de los pasajeros**/
		String pasajeros[]=obtenerHtmlPasajeros(listaPasajeros,etiqueta,reserva);
		/******************************************/
		/**Se obtienen los datos de los servicios**/
		String servicios="";
		if(reserva.getoPaquete().getListaServicios()!=null)
			for(CServicio servicio:reserva.getoPaquete().getListaServicios())
			{
				if(!servicio.getOpcionValue().equals("0"))
				{
					servicios+=
							"<tr style='border:1px solid black;'>"+
						    		"<td style='font-family: Titillium;border:1px solid black;' align='left'>"+servicio.getcServicioIndioma1()+"</td>";
							if(servicio.getcRestriccionNum()==0 && servicio.getcRestriccionYesNo()==0)
						    		servicios+="<td style='font-family: Titillium;border:1px solid black;' align='center'>"+servicio.getSelectOpcion()+"</td>";
							else
								servicios+="<td style='font-family: Titillium;border:1px solid black;' align='center'>-</td>";
						    servicios+="<td style='font-family: Titillium;border:1px solid black;' align='right'>"+servicio.getnPrecioServicio().toString()+"</td>"+
						    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+servicio.getOpcionValue()+"</td>"+
						    		"<td style='font-family: Titillium;border:1px solid black;color:#1A5276;' align='right'>"+servicio.getPrecioTotalServicio()+"</td>"+
						    	"</tr>";
				}
			}
		String[] actividades=obtenerHtmlActividades(reserva.getoPaquete().getListaActividades(), reserva);
		/******************************************/
		String cuponHtml="";
		if(reserva.getoCupon().isOkCupon())
			cuponHtml=obtenerHtmlCupon(reserva.getoCupon());
		/**OBTENEMOS LA FECHA DE ARRIBO**/
		String arribo="";
		if(reserva.getoPaquete().isConFechaArribo())
			arribo="<P>"+etiqueta[243]+": "+fechaArribo+"</P>";
		//Generando codigo QR
				QRCode qr = new QRCode();
				String nameImgQR=generarNombreQR();
		        File f = new File(ScannUtil.getPathImagenQR()+nameImgQR);
		        //FPP=yuri vladimir huallpa vargas=camino inka=01/06/2017=05/06/2017=5=300=931896923=yurihuallpavargas@gmail.com
		        String text ="FPP="+reserva.getcContacto()+"="+reserva.getoPaquete().getTitulo()+"="+
		        			fechaInicio+"="+fechaFin+"="+reserva.getnNroPersonas()+"="+montoPagar+"="+
		        			reserva.getcTelefono()+"="+reserva.getcEmail();
		 
		        try {
		 
		            qr.generateQR(f, text, 300, 300);
		 
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		/*****************************************/
		String mensajeHTML=
				"<html>"+
						"<head>"+
							"<style type='text/css' media='screen, print'>"+
								"@font-face {"+
									  "font-family: Titillium;"+
									  "font-style: normal;"+
									  "font-weight: 400;"+
									  "src: local('Titillium Web Regular'), local('TitilliumWeb-Regular'), url(https://fonts.gstatic.com/s/titilliumweb/v5/7XUFZ5tgS-tD6QamInJTcSo_WB_cotcEMUw1LsIE8mM.woff2) format('woff2');"+
									  "unicode-range: U+0100-024F, U+1E00-1EFF, U+20A0-20AB, U+20AD-20CF, U+2C60-2C7F, U+A720-A7FF;"+
									"}"+
									/* latin */
									"@font-face {"+
									  "font-family: Titillium;"+
									  "font-style: normal;"+
									  "font-weight: 400;"+
									  "src: local('Titillium Web Regular'), local('TitilliumWeb-Regular'), url(https://fonts.gstatic.com/s/titilliumweb/v5/7XUFZ5tgS-tD6QamInJTcZSnX671uNZIV63UdXh3Mg0.woff2) format('woff2');"+
									  "unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02C6, U+02DA, U+02DC, U+2000-206F, U+2074, U+20AC, U+2212, U+2215;"+
									"}"+
							"</style>"+
						"</head>"+
					"<body>"+
						"<div style='width:600px;background:rgb(242, 242, 242);'>"+
							"<table style='background:white;border:1px solid rgb(204, 204, 204);color:rgb(34, 34, 34);font-family:arial,sans-serif;font-size:12.8px;margin:0px auto;width:600px;border-collapse: collapse;'>"+
								"<tbody>"+
									"<tr>"+
										"<td>"+
											"<table style='background:rgb(242, 242, 242)'>"+
												"<tbody>"+
													"<tr>"+
														"<td>"+
															"<p style='font-family: Titillium;font-weight:bold;font-size:15px;margin:5px 0 6px 0;'>"+fechaActual+"</p>"+
														"</td>"+
													"</tr>"+
													"<tr>"+
														"<td align='left' width='20%' style='padding:8px 20px 8px 20px;'>"+
															"<a href='"+etiqueta[212]+"' style='text-decoration:none;font-family:Titillium;'>"+
																"<img src='"+etiqueta[211]+"' width='100' height='80' border='0' />"+
															"</a>"+
														"</td>"+
														"<td style='font-family: Titillium;color:black;font-size:17px;font-weight:bold;' align='center' width='80%'>"+reserva.getoPaquete().getTitulo()+"</td>"+
														"<td align='right' width='20%' style='padding:8px 20px 8px 20px;'>"+
															"<a href='"+etiqueta[212]+"' style='text-decoration:none;'>"+
																"<img src='http://localhost:8080/pricing/img/QR/"+nameImgQR+"' width='100' height='80' border='0' />"+
															"</a>"+
														"</td>"+
													"</tr>"+
												"</tbody>"+
											"</table>"+
										"</td>"+
									"</tr>"+
								"</tbody>"+
							"</table>"+
							"<div style='background:white;width:600px;'>"+
								"<table style='background:white;color:rgb(34, 34, 34);font-family: Titillium;font-size:12.8px;width:100%;border-collapse: collapse;'>"+
									"<tbody>"+
									"<tr>"+
										"<td style='font-family: Titillium; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial; font-weight: bold; font-stretch: normal; font-size: 15px; line-height: normal; color: rgb(65, 88, 132);'>"+
											"<div align='center'>"+
												"<img border='0' src='https://www.e-ranti.com/pricing_demo/img/negocios.jpg' style='cursor:pointer; height:208px; outline:0px; width:600px' tabindex='0'/>"+
											"</div>"+
										"</td>"+
									"</tr>"+
									"</tbody>"+
								"</table>"+
								"<br />"+
								"<table width='100%' style='border:1px solid rgba(0,0,0,0.1);border-collapse: collapse;margin:0;'>"+
											"<thead style='background:rgba(0,0,0,0.1);font-weight: bold;'>DATOS DE RESERVA</thead>"+
												"<tr style='border:1px solid black;'>"+
													"<td style='margin: 0px; text-align: center; border-right: 1px solid black;padding-bottom:15px;' width='240'>"+
														"<p style='color: #1a5276; margin-bottom: 30px;font-family: Titillium;'>"+
															"<strong style='text-decoration: underline;'>CODIGO DE RESERVA</strong>"+
														"</p>"+
														"<span style='font-family: Titillium;background-color:rgb(204, 204, 204); border-radius:3px; border:1px solid rgb(217, 217, 217); color:rgb(47, 115, 186); font-family:arial,helvetica,sans-serif; font-size:24px; font-stretch:normal; line-height:24px; padding:10px'>"+
															"<strong>"+reserva.getcReservaCod()+"</strong>"+
														"</span>"+
														"<p style='color: #1a5276; margin-bottom: 30px;font-family: Titillium;'>"+
															"<strong style='text-decoration: underline;'>CODIGO DE TRANSACCION</strong>"+
														"</p>"+
														"<span style='font-family: Titillium;background-color:rgb(204, 204, 204); border-radius:3px; border:1px solid rgb(217, 217, 217); color:rgb(47, 115, 186); font-family:arial,helvetica,sans-serif; font-size:24px; font-stretch:normal; line-height:24px; padding:10px'>"+
															"<strong>"+codTransaccion+"</strong>"+
														"</span>"+
													"</td>"+
													"<td style='margin: 0px;padding:0px 15px;' width='308'>"+
														"<p>"+
															"<strong style='text-decoration: underline;font-family: Titillium;'>RESPONSABLE DE LA RESERVA</strong>"+
														"</p>"+
														"<span style='color:rgb(102, 102, 102);font-family: Titillium;'>"+
															"<strong>"+reserva.getcContacto()+"</strong>"+
															"<br/>"+
															paisPasajero+
															"<strong>E-mail: "+reserva.getcEmail()+"</strong>"+
															"<br/>"+
															"<strong>WhatsApp: "+reserva.getcTelefono()+"</strong>"+
														"</span>"+
														"<p>"+
															"<strong style='text-decoration: underline;font-family: Titillium;'>FECHAS DEL TOUR/SERVICIO</strong>"+
														"</p>"+
														"<span>"+
															"<strong><span style='color:rgb(102, 102, 102);font-family: Titillium;'>"+fechaInicio+"</span></strong>"+
															"<br />"+
															"<strong><span style='color:rgb(102, 102, 102);font-family: Titillium;'>"+fechaFin+"</span></strong>"+
															"<br />"+
															"<strong>Estadia en Cusco hasta: </strong><strong><span style='color:rgb(102, 102, 102);font-family: Titillium;'>"+fechaArribo+"</span></strong>"+
														"</span>"+
														"<p>"+
															"<strong style='text-decoration: underline;font-family: Titillium;'>COSTO TOTAL DEL TOUR</strong>"+
														"</p>"+
														"<span style='color:rgb(102, 102, 102);font-family: Titillium;'>"+
															"<strong>USD($) "+totalPago+"</strong>"+
														"</span>"+
													"</td>"+
												"</tr>"+
										"</table>"+
								    "<br/>"+
									"<table width='100%' style='border:1px solid rgba(0,0,0,0.1);border-collapse: collapse;margin:0;'>"+
										"<thead style='background:rgba(0,0,0,0.1);font-weight: bold;font-family: Titillium;'>DETALLES DEL PAQUETE</thead>"+
										"<tr style='border:1px solid black;'>"+
											"<td style='margin: 0px; border-right: 1px solid black;padding:8px;font-family: Titillium;'>"+
												"<p>Se efectuo una reserva de <strong style='color:#F7653A;font-family: Titillium;'>"+reserva.getoPaquete().getTitulo()+"</strong>"+ 
												"</strong> para <strong> "+reserva.getnNroPersonas()+" </strong> persona(s)<strong></strong>"+
												fechas+
												"<p><strong style='text-decoration: underline;font-family: Titillium;'>INFORMACION ADICIONAL:</strong><br />"+
												"<p style='font-family: Titillium;'>"+reserva.getcInformacionAdicional()+"</p>"+
												"<p><strong><span style='color:rgb(255, 0, 0);'><strong><span style='color:rgb(0, 0, 0);text-decoration: underline;font-family: Titillium;'>URL DE REFERENCIA DEL PAQUETE:</span></strong><br />"+
												"<a href='"+reserva.getoPaquete().getcUrlReferenciaPaquete()+"' target='_blank'>"+reserva.getoPaquete().getcUrlReferenciaPaquete()+"</a></span></strong></p>"+
											"</td>"+
										"</tr>"+
									"</table>"+
									"<br/>"+
								    "<p style='font-family: Titillium;'>Las siguientes tablas muestran informacion de la reserva:</p>"+
								    pasajeros[1]+
								    "<br/>"+
								    hotel+
								    "<table width='100%' style='border:1px solid rgba(0,0,0,0.1);border-collapse: collapse;'>"+
								    	"<thead style='background:rgba(0,0,0,0.1);font-weight: bold;font-family: Titillium;'>COSTOS DEL PAQUETE/SERVICIO</thead>"+
								    	"<tr style='background:rgba(0,0,0,0.1);border:1px solid black;'>"+
								    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>Descripcion</td>"+
								    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>Sub-Servicio</td>"+
								    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>Precio Unitario (USD)</td>"+
								    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>Cantidad</td>"+
								    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>Precio Total (USD)</td>"+
								    	"</tr>"+
								    	"<tr style='border:1px solid black;'>"+
								    		"<td style='font-family: Titillium;border:1px solid black;' align='left'>"+reserva.getoPaquete().getcTituloIdioma1()+"</td>"+
								    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>-</td>"+
								    		"<td style='font-family: Titillium;border:1px solid black;' align='right'>"+precioUniPaquete+"</td>"+
								    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+reserva.getnNroPersonas()+"</td>"+
								    		"<td style='font-family: Titillium;border:1px solid black;color:#1A5276;' align='right'>"+df.format(reserva.getnPrecioPaquetePersona().doubleValue())+"</td>"+
								    	"</tr>"+
								    	servicios+
								    	actividades[1]+
								    "</table>"+
								    "<table width='100%' style='background:rgba(0,0,0,0.02);border:1px solid black;'>"+
								    	"<tr>"+
								    		"<td width='60%'></td>"+
								    		"<td width='40%'>"+
								    			"<table width='100%'>"+
								    				"<tr><td style='font-family: Titillium;'>Total</td><td style='font-family: Titillium;color:#1A5276;' align='right'>"+totalPago+"</td></tr>"+
								    			"</table>"+
								    		"</td>"+
								    	"</tr>"+
								    "</table>"+
								    "<br/>"+
								    pagoAl+
								    "<br/>"+
								    cuponHtml+
								    "<br/>"+
								"</div>"+
								  "<br/>"+
							        "<strong style='font-family: Titillium;'>"+etiqueta[213]+"</strong>"+
								"</div>"+
								"<div align='center' style='background:gray;width:600px;"+
									"color:white;"+
									"font-size:15px;font-weight:bold;height:60px;padding:8px 0;'>"+
									"<p style='font-family: Titillium'>Copyright © "+cal.get(Calendar.YEAR)+" eddyonsoft.com - Todos los Derechos Reservados</p>"+
								"</div>"+
							  "</div>"+
						"</body>"+
				"</html>";
		return sendMailToEmpresa(reserva.getcEmail(),titulo,mensajeHTML,imagenes);
	}
	public boolean enviarCorreoPagoReserva(String titulo,String[] etiqueta,String namePaquete,String mail,String contacto,
			String codReserva,String porcentaje,String transac,String urlPdf,String textoParcial) throws IOException, DocumentException
	{
		this.etiqueta=etiqueta;
		/**************************/
		Calendar cal=Calendar.getInstance();
		String dia = Integer.toString(cal.get(Calendar.DATE));
		int auxMes = cal.get(Calendar.MONTH)+1;
		String annio = Integer.toString(cal.get(Calendar.YEAR));
		String mes=obtenerTxtMes(auxMes);
		
		String fechaActual=dia+" "+etiqueta[158]+" "+mes+", "+annio;
		/**Se obtiene el impuesto e importe total del totalPago**/
		String mensaje="";
		if(porcentaje.equals(textoParcial))
			mensaje="<p style='font-family: Titillium;'>"+etiqueta[160]+"</p>";
		String mensajeHTML=
				"<html>"+
						"<head>"+
							"<style type='text/css' media='screen, print'>"+
								"@font-face {"+
									  "font-family: Titillium;"+
									  "font-style: normal;"+
									  "font-weight: 400;"+
									  "src: local('Titillium Web Regular'), local('TitilliumWeb-Regular'), url(https://fonts.gstatic.com/s/titilliumweb/v5/7XUFZ5tgS-tD6QamInJTcSo_WB_cotcEMUw1LsIE8mM.woff2) format('woff2');"+
									  "unicode-range: U+0100-024F, U+1E00-1EFF, U+20A0-20AB, U+20AD-20CF, U+2C60-2C7F, U+A720-A7FF;"+
									"}"+
									/* latin */
									"@font-face {"+
									  "font-family: Titillium;"+
									  "font-style: normal;"+
									  "font-weight: 400;"+
									  "src: local('Titillium Web Regular'), local('TitilliumWeb-Regular'), url(https://fonts.gstatic.com/s/titilliumweb/v5/7XUFZ5tgS-tD6QamInJTcZSnX671uNZIV63UdXh3Mg0.woff2) format('woff2');"+
									  "unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02C6, U+02DA, U+02DC, U+2000-206F, U+2074, U+20AC, U+2212, U+2215;"+
									"}"+
							"</style>"+
						"</head>"+
					"<body>"+
						"<div style='width:600px;background:rgb(242, 242, 242);'>"+
							"<table style='background:white;border:1px solid rgb(204, 204, 204);color:rgb(34, 34, 34);font-family:arial,sans-serif;font-size:12.8px;margin:0px auto;width:600px;border-collapse: collapse;'>"+
								"<tbody>"+
									"<tr>"+
										"<td>"+
											"<table style='background:rgb(242, 242, 242)'>"+
												"<tbody>"+
													"<tr>"+
														"<td>"+
															"<p style='font-family: Titillium;font-weight:bold;font-size:15px;margin:5px 0 6px 0;'>"+fechaActual+"</p>"+
														"</td>"+
													"</tr>"+
													"<tr>"+
														"<td align='left' width='20%' style='padding:8px 20px 8px 20px;'>"+
															"<a href='"+etiqueta[212]+"' style='text-decoration:none;font-family:Titillium;'>"+
																"<img src='"+etiqueta[211]+"' width='100' height='80' border='0' />"+
															"</a>"+
														"</td>"+
														"<td style='font-family: Titillium;color:black;font-size:17px;font-weight:bold;' align='center' width='80%'>"+namePaquete+"</td>"+
														"<td align='right' width='20%' style='padding:8px 20px 8px 20px;'>"+
															"<a href='"+etiqueta[212]+"' style='text-decoration:none;'>"+
																"<img src='"+etiqueta[211]+"' width='100' height='80' border='0' />"+
															"</a>"+
														"</td>"+
													"</tr>"+
												"</tbody>"+
											"</table>"+
										"</td>"+
									"</tr>"+
								"</tbody>"+
							"</table>"+
							"<div style='background:white;width:600px'>"+
								"<table style='background:white;color:rgb(34, 34, 34);font-family: Titillium;font-size:12.8px;width:100%;border-collapse: collapse;'>"+
									"<tbody>"+
									"<tr>"+
										"<td style='font-family: Titillium; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial; font-weight: bold; font-stretch: normal; font-size: 15px; line-height: normal; color: rgb(65, 88, 132);'>"+
											"<div align='center'>"+
												"<img border='0' src='https://www.e-ranti.com/pricing_demo/img/negocios.jpg' style='cursor:pointer; height:208px; outline:0px; width:600px' tabindex='0'/>"+
											"</div>"+
										"</td>"+
									"</tr>"+
									"</tbody>"+
								"</table>"+
								"<br />"+
								"<table width='100%' style='border:1px solid rgba(0,0,0,0.1);border-collapse: collapse;margin:0;'>"+
											"<thead style='background:rgba(0,0,0,0.1);font-weight: bold;'>DATOS DE RESERVA</thead>"+
												"<tr style='border:1px solid black;'>"+
													"<td style='margin: 0px; text-align: center; border-right: 1px solid black;padding-bottom:15px;' width='240'>"+
														"<p style='color: #1a5276; margin-bottom: 30px;font-family: Titillium;'>"+
															"<strong style='text-decoration: underline;'>"+etiqueta[118]+"</strong>"+
														"</p>"+
														"<span style='font-family: Titillium;background-color:rgb(204, 204, 204); border-radius:3px; border:1px solid rgb(217, 217, 217); color:rgb(47, 115, 186); font-family:arial,helvetica,sans-serif; font-size:24px; font-stretch:normal; line-height:24px; padding:10px'>"+
															"<strong>"+codReserva+"</strong>"+
														"</span>"+
														"<p style='color: #1a5276; margin-bottom: 30px;font-family: Titillium;'>"+
															"<strong style='text-decoration: underline;'>"+etiqueta[123]+"</strong>"+
														"</p>"+
														"<span style='font-family: Titillium;background-color:rgb(204, 204, 204); border-radius:3px; border:1px solid rgb(217, 217, 217); color:rgb(47, 115, 186); font-family:arial,helvetica,sans-serif; font-size:24px; font-stretch:normal; line-height:24px; padding:10px'>"+
															"<strong>"+transac+"</strong>"+
														"</span>"+
													"</td>"+
													"<td style='margin: 0px;padding:0px 15px;' width='308'>"+
														"<p>"+
															"<strong style='text-decoration: underline;font-family: Titillium;'>"+etiqueta[125]+"</strong>"+
														"</p>"+
														"<span style='color:rgb(102, 102, 102);font-family: Titillium;'>"+
															"<strong>"+contacto+"</strong>"+
															"<br/>"+
															"<p style='font-family: Titillium;font-size:18px;font-weight:bold;'>"+etiqueta[198]+" <strong>"+porcentaje+".</strong></p>"+
														"</span>"+
													"</td>"+
												"</tr>"+
										"</table>"+
								    "<br/>"+
									mensaje+
								    "<p style='font-family: Titillium;'>"+etiqueta[146]+"</p>"+
								    "<p style='font-family: Titillium;'>"+etiqueta[147]+"</p>"+
								    "<div align='left' width='100%'>"+
									    "<table width='80%'>"+
										    "<tr align='left'>"+
									    		"<td><a href='"+etiqueta[214]+"'><img width='80' height='80' src='https://www.e-ranti.com/pricing_info/img/logo_facebook.png'/></a></td>"+
									    		"<td><a href='"+etiqueta[215]+"'><img width='60' height='60' src='https://www.e-ranti.com/pricing_info/img/youtube.png'/></a></td>"+
									    		"<td><a href='"+etiqueta[216]+"'><img width='60' height='60' src='https://www.e-ranti.com/pricing_info/img/logo_twitter.png'/></a></td>"+
									    		"<td style='font-family:Titillium;display:flex;padding-top:17px;box-sizing:border-box;'><img width='50' height='50' src='https://www.e-ranti.com/pricing_info/img/wathsapp.png'/><p>"+etiqueta[151]+"</p></td>"+
									    	"</tr>"+
									    "</table>"+
								    "</div>"+
								"</div>"+
								  "<p style='font-family: Titillium;font-size:11px;'>"+etiqueta[153]+" <strong>"+etiqueta[154]+"</strong>"+etiqueta[155]+"</p>"+
								  "<p style='font-family: Titillium;font-size:11px;'>"+etiqueta[196]+"</p>"+
								  "<br/>"+
							        "<strong style='font-family: Titillium;'>"+etiqueta[213]+"</strong>"+
								"</div>"+
								"<div align='center' style='background:gray;"+
									"color:white;"+
									"font-size:15px;font-weight:bold;width:600px;height:60px;padding:8px 0;'>"+
									"<p style='font-family: Titillium;'>Copyright © "+cal.get(Calendar.YEAR)+" "+etiqueta[157]+"</p>"+
								"</div>"+
							  "</div>"+
						"</body>"+
				"</html>";
		convertHTML_PDF.convertirHtml2Pdf(mensajeHTML,urlPdf);
		return sendMail(mail,titulo,mensajeHTML,urlPdf,1);
	}
	public boolean enviarCorreoPagoReservaAEmpresa(String mailCliente,String titulo,String namePaquete,String contacto,
			String codReserva,String porcentaje,String transac) throws IOException, DocumentException
	{
		Calendar cal=Calendar.getInstance();
		String dia = Integer.toString(cal.get(Calendar.DATE));
		int auxMes = cal.get(Calendar.MONTH)+1;
		String annio = Integer.toString(cal.get(Calendar.YEAR));
		String mes=obtenerTxtMesES(auxMes);
		
		String fechaActual=dia+" de "+mes+", "+annio;
		/**Se obtiene el impuesto e importe total del totalPago**/
		
		String mensajeHTML=
				"<html>"+
						"<head>"+
							"<style type='text/css' media='screen, print'>"+
								"@font-face {"+
									  "font-family: Titillium;"+
									  "font-style: normal;"+
									  "font-weight: 400;"+
									  "src: local('Titillium Web Regular'), local('TitilliumWeb-Regular'), url(https://fonts.gstatic.com/s/titilliumweb/v5/7XUFZ5tgS-tD6QamInJTcSo_WB_cotcEMUw1LsIE8mM.woff2) format('woff2');"+
									  "unicode-range: U+0100-024F, U+1E00-1EFF, U+20A0-20AB, U+20AD-20CF, U+2C60-2C7F, U+A720-A7FF;"+
									"}"+
									/* latin */
									"@font-face {"+
									  "font-family: Titillium;"+
									  "font-style: normal;"+
									  "font-weight: 400;"+
									  "src: local('Titillium Web Regular'), local('TitilliumWeb-Regular'), url(https://fonts.gstatic.com/s/titilliumweb/v5/7XUFZ5tgS-tD6QamInJTcZSnX671uNZIV63UdXh3Mg0.woff2) format('woff2');"+
									  "unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02C6, U+02DA, U+02DC, U+2000-206F, U+2074, U+20AC, U+2212, U+2215;"+
									"}"+
							"</style>"+
						"</head>"+
					"<body>"+
						"<div style='width:600px;background:rgb(242, 242, 242);'>"+
							"<table style='background:white;border:1px solid rgb(204, 204, 204);color:rgb(34, 34, 34);font-family:arial,sans-serif;font-size:12.8px;margin:0px auto;width:600px;border-collapse: collapse;'>"+
								"<tbody>"+
									"<tr>"+
										"<td>"+
											"<table style='background:rgb(242, 242, 242)'>"+
												"<tbody>"+
													"<tr>"+
														"<td>"+
															"<p style='font-family: Titillium;font-weight:bold;font-size:15px;margin:5px 0 6px 0;'>"+fechaActual+"</p>"+
														"</td>"+
													"</tr>"+
													"<tr>"+
														"<td align='left' width='20%' style='padding:8px 20px 8px 20px;'>"+
															"<a href='"+etiqueta[212]+"' style='text-decoration:none;font-family:Titillium;'>"+
																"<img src='"+etiqueta[211]+"' width='100' height='80' border='0' />"+
															"</a>"+
														"</td>"+
														"<td style='font-family: Titillium;color:black;font-size:17px;font-weight:bold;' align='center' width='80%'>"+namePaquete+"</td>"+
														"<td align='right' width='20%' style='padding:8px 20px 8px 20px;'>"+
															"<a href='"+etiqueta[212]+"' style='text-decoration:none;'>"+
																"<img src='"+etiqueta[211]+"' width='100' height='80' border='0' />"+
															"</a>"+
														"</td>"+
													"</tr>"+
												"</tbody>"+
											"</table>"+
										"</td>"+
									"</tr>"+
								"</tbody>"+
							"</table>"+
							"<div style='background:white;width:600px;'>"+
								"<table style='background:white;color:rgb(34, 34, 34);font-family: Titillium;font-size:12.8px;width:100%;border-collapse: collapse;'>"+
									"<tbody>"+
									"<tr>"+
										"<td style='font-family: Titillium; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial; font-weight: bold; font-stretch: normal; font-size: 15px; line-height: normal; color: rgb(65, 88, 132);'>"+
											"<div align='center'>"+
												"<img border='0' src='https://www.e-ranti.com/pricing_demo/img/negocios.jpg' style='cursor:pointer; height:208px; outline:0px; width:600px' tabindex='0'/>"+
											"</div>"+
										"</td>"+
									"</tr>"+
									"</tbody>"+
								"</table>"+
								"<br />"+
								"<table width='100%' style='border:1px solid rgba(0,0,0,0.1);border-collapse: collapse;margin:0;'>"+
											"<thead style='background:rgba(0,0,0,0.1);font-weight: bold;'>DATOS DE RESERVA</thead>"+
												"<tr style='border:1px solid black;'>"+
													"<td style='margin: 0px; text-align: center; border-right: 1px solid black;padding-bottom:15px;' width='240'>"+
														"<p style='color: #1a5276; margin-bottom: 30px;font-family: Titillium;'>"+
															"<strong style='text-decoration: underline;'>CODIGO DE RESERVA</strong>"+
														"</p>"+
														"<span style='font-family: Titillium;background-color:rgb(204, 204, 204); border-radius:3px; border:1px solid rgb(217, 217, 217); color:rgb(47, 115, 186); font-family:arial,helvetica,sans-serif; font-size:24px; font-stretch:normal; line-height:24px; padding:10px'>"+
															"<strong>"+codReserva+"</strong>"+
														"</span>"+
														"<p style='color: #1a5276; margin-bottom: 30px;font-family: Titillium;'>"+
															"<strong style='text-decoration: underline;'>CODIGO DE TRANSACCION</strong>"+
														"</p>"+
														"<span style='font-family: Titillium;background-color:rgb(204, 204, 204); border-radius:3px; border:1px solid rgb(217, 217, 217); color:rgb(47, 115, 186); font-family:arial,helvetica,sans-serif; font-size:24px; font-stretch:normal; line-height:24px; padding:10px'>"+
															"<strong>"+transac+"</strong>"+
														"</span>"+
													"</td>"+
													"<td style='margin: 0px;padding:0px 15px;' width='308'>"+
														"<p>"+
															"<strong style='text-decoration: underline;font-family: Titillium;'>RESPONSABLE DE LA RESERVA</strong>"+
														"</p>"+
														"<span style='color:rgb(102, 102, 102);font-family: Titillium;'>"+
															"<strong>"+contacto+"</strong>"+
															"<br/>"+
															"<p style='font-family: Titillium;font-size:18px;font-weight:bold;'>EFECTUO SU PAGO CORRECTAMENTE AL <strong>"+porcentaje+".</strong></p>"+
														"</span>"+
													"</td>"+
												"</tr>"+
										"</table>"+
								    "<br/>"+
								"<div>"+
								  "<br/>"+
							        "<strong style='font-family: Titillium;'>"+etiqueta[213]+"</strong>"+
								"</div>"+
								"<div align='center' style='background:gray;"+
									"color:white;"+
									"font-size:15px;font-weight:bold;width:600px;height:60px;padding:8px 0;'>"+
									"<p style='font-family: Titillium;'>Copyright © "+annio+" eddyonsoft.com - Todos los Derechos Reservados</p>"+
								"</div>"+
							  "</div>"+
						"</body>"+
				"</html>";
		return sendMailSimple(mailCliente,titulo,mensajeHTML);
	}
	public boolean enviarCorreoNuevoUser(String destinatario,String usuario,String contrasenia)
	{
		String mensajeHTMl=
				"<html>"+
					"<head></head>"+
					"<body>"+
						"<div>"+
							"<table border='0' width='320px'>"+
								"<tr>"+
									"<td style='font-family: Titillium;'>USUARIO: </td>"+
									"<td style='font-family: Titillium;'>"+usuario+"</td>"+
								"</tr>"+
								"<tr>"+
									"<td style='font-family: Titillium;'>PASSWORD: </td>"+
									"<td style='font-family: Titillium;'>"+contrasenia+"</td>"+
								"</tr>"+
								"<tr>"+
									"<td style='font-family: Titillium;'>URL DEL PANEL DE ADMINISTRACION: </td>"+
									"<td style='font-family: Titillium;'>https://www.e-ranti.com/pricing_demo/panelAdmin.zul</td>"+
								"</tr>"+
							"</table>"+
						"</div>"+
					"</body>"+
				"</html>";
		return sendMailNewUser(destinatario,"USUARIO Y CONTRASEÑA PARA EL ACCESO AL PANEL DE ADMINISTRACION", mensajeHTMl);
	}
	public String[] obtenerFechaActual()
	{
		String[] fa=new String[2];
		Calendar cal=Calendar.getInstance();
		String dia = Integer.toString(cal.get(Calendar.DATE));
		int auxMes = cal.get(Calendar.MONTH)+1;
		String annio = Integer.toString(cal.get(Calendar.YEAR));
		String mes=obtenerTxtMes(auxMes);
		
		fa[0]=dia+" "+etiqueta[158]+" "+mes+", "+annio;
		fa[1]=dia+" de "+mes+", "+annio;
		return fa;
	}
	public String obtenerPrecioUnitarioPaquete(CReserva reserva,CPaquete paquete)
	{
		String pu="";
		int numPas=reserva.getnNroPersonas();
		if(numPas==1)pu=paquete.getnPrecioUno().toString();
		else if(numPas==2)pu=paquete.getnPrecioDos().toString();
		else if(numPas==3)pu=paquete.getnPrecioTres().toString();
		else if(numPas==4)pu=paquete.getnPrecioCuatro().toString();
		else pu=paquete.getnPrecioCinco().toString();
		return pu;
	}
	public String obtenerHtmlFechasAlternas(ArrayList<String> fechasAlternas)
	{
		String fechas="";
		if(fechasAlternas!=null && !fechasAlternas.isEmpty())
		{
			for(int i=0;i<fechasAlternas.size();i++)
			{
				fechas+=
						"<tr align='center'>"+
				           "<td style='font-family: Titillium;color:#F7653A;font-weight:bold;'>"+fechasAlternas.get(i)+"</td>"+
				        "</tr>";
			}
			
			fechas="<br/>"+etiqueta[137]+"</p>"+
					"<table width='40%'>"+
						fechas+
				    "</table>";
		}
		return fechas;
	}
	public String[] obtenerHtmlPasajeros(ArrayList<CPasajero> listaPasajeros,String[] etiqueta,CReserva reserva)
	{
		String[] pasajeros=new String[2];
		pasajeros[0]="";
		pasajeros[1]="";
    	boolean hayDatos=hayDatosPasajeros(listaPasajeros,reserva);
    	if(hayDatos)
    	{
    		pasajeros[0]="<table width='100%' style='border:1px solid rgba(0,0,0,0.1);border-collapse: collapse;'>"+
    		    	"<thead style='background:rgba(0,0,0,0.1);font-weight: bold;'>"+etiqueta[88]+"</thead>"+
    		    	"<tr style='background:rgba(0,0,0,0.1);border:1px solid black;'>"+
    		    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+etiqueta[139]+"</td>"+
    		    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+etiqueta[140]+"</td>"+
    		    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+etiqueta[72]+"</td>"+
    		    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+etiqueta[71]+"</td>"+
    		    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+etiqueta[73]+"</td>"+
    		    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+etiqueta[74]+"</td>"+
    		    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+etiqueta[75]+"</td>"+
    		    	"</tr>";
    		pasajeros[1]="<table width='100%' style='border:1px solid rgba(0,0,0,0.1);border-collapse: collapse;'>"+
			    	"<thead style='background:rgba(0,0,0,0.1);font-weight: bold;font-family: Titillium;'>LISTA DE PASAJEROS</thead>"+
			    	"<tr style='border:1px solid black;'>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>Doc.</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>Nro</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>Nombre</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>Apellidos</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>Sexo</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>Edad</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>País</td>"+
			    	"</tr>";
    	}
		for(CPasajero p:listaPasajeros)
		{
			if(!reserva.getoPaquete().isbSubirDoc_Y_LlenarDatosPax())
			{
				if(!p.isSelectPasajero() || p.isEsEdit())break;
			}else
				if(!p.isSelectPasajero())break;
			
			pasajeros[0]+=
						"<tr style='border:1px solid black;'>"+
				    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+p.getTipoDocumento()+"</td>"+
				    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+p.getcNroDoc()+"</td>"+
				    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+p.getcNombres()+"</td>"+
				    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+p.getcApellidos()+"</td>"+
				    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+p.getcSexo()+"</td>"+
				    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+p.getnEdad()+"</td>"+
				    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+p.getNombrePais()+"</td>"+
				    	"</tr>";
			pasajeros[1]+="<tr style='border:1px solid black;'>"+
					    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+p.getTipoDocumento()+"</td>"+
					    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+p.getcNroDoc()+"</td>"+
					    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+p.getcNombres()+"</td>"+
					    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+p.getcApellidos()+"</td>"+
					    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+p.getcSexo()+"</td>"+
					    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+p.getnEdad()+"</td>"+
					    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+p.getNombrePais()+"</td>"+
					    	"</tr>";
		}
		if(hayDatos)
		{
			pasajeros[0]+="</table>"+
					"<br/>";
			pasajeros[1]+="</table>"+
					"<br/>";
		}
		return pasajeros;
	}
	public boolean hayDatosPasajeros(ArrayList<CPasajero> listaPasajeros,CReserva reserva)
	{
		boolean hayDatos=false;
		for(CPasajero p:listaPasajeros)
		{
			if(!reserva.getoPaquete().isbSubirDoc_Y_LlenarDatosPax())
			{
				//Si el pasajero no a sido seleccionado o se subio documento
				if(p.isSelectPasajero() && !p.isEsEdit()){hayDatos=true;break;}
			}else
				if(p.isSelectPasajero()){hayDatos=true;break;}
		}
		return hayDatos;
	}
	public String[] obtenerHtmlServicios(ArrayList<CServicio> listaServicios,String[] etiqueta)
	{
		String[] servicios=new String[2];
		servicios[0]="";
		servicios[1]="";
		if(listaServicios!=null)
			for(CServicio s:listaServicios)
			{
				if(!s.getOpcionValue().equals("0"))
				{
					servicios[0]+=
							"<tr style='border:1px solid black;'>"+
						    		"<td style='font-family: Titillium;border:1px solid black;' align='left'>"+s.getServicio()+"</td>";
					servicios[1]+=
							"<tr style='border:1px solid black;'>"+
						    		"<td style='font-family: Titillium;border:1px solid black;' align='left'>"+s.getcServicioIndioma1()+"</td>";
							if(s.getcRestriccionNum()==0 && s.getcRestriccionYesNo()==0)
							{
						    	servicios[0]+="<td style='font-family: Titillium;border:1px solid black;' align='center'>"+s.getSelectOpcion()+"</td>";
						    	servicios[1]+="<td style='font-family: Titillium;border:1px solid black;' align='center'>"+s.getSelectOpcion()+"</td>";
							}
						    else
							{
						    	servicios[0]+="<td style='font-family: Titillium;border:1px solid black;' align='center'>-</td>";
						    	servicios[1]+="<td style='font-family: Titillium;border:1px solid black;' align='center'>-</td>";
							}
						    servicios[0]+="<td style='font-family: Titillium;border:1px solid black;' align='right'>"+s.getnPrecioServicio().toString()+"</td>"+
						    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+s.getOpcionValue()+"</td>"+
						    		"<td style='font-family: Titillium;border:1px solid black;color:#1A5276;' align='right'>"+s.getPrecioTotalServicio()+"</td>"+
						    	"</tr>";
						    servicios[1]+="<td style='font-family: Titillium;border:1px solid black;' align='right'>"+s.getnPrecioServicio().toString()+"</td>"+
						    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+s.getOpcionValue()+"</td>"+
						    		"<td style='font-family: Titillium;border:1px solid black;color:#1A5276;' align='right'>"+s.getPrecioTotalServicio()+"</td>"+
						    	"</tr>";
				}
			}
		return servicios;
	}
	public String[] obtenerHtmlActividades(ArrayList<CActividad> listaActividades,CReserva reserva)
	{
		String[] actividad=new String[2];
		actividad[0]="";
		actividad[1]="";
		if(!listaActividades.isEmpty())
		{
			for(CActividad acti:listaActividades)
			{
				if(acti.isComprado())
				{
					actividad[0]+=
							"<tr style='border:1px solid black;'>"+
						    		"<td style='font-family: Titillium;border:1px solid black;' align='left'>"+acti.getNombreActividad()+"</td>"+
						    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>-</td>"+
						    		"<td style='font-family: Titillium;border:1px solid black;' align='right'>"+df.format(acti.getnPrecioActividad().doubleValue())+"</td>"+
						    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+reserva.getnNroPersonas()+"</td>"+
						    		"<td style='font-family: Titillium;border:1px solid black;color:#1A5276;' align='right'>"+df.format((acti.getnPrecioActividad().doubleValue()*reserva.getnNroPersonas()))+"</td>"+
						    	"</tr>";
					actividad[1]+=
							"<tr style='border:1px solid black;'>"+
						    		"<td style='font-family: Titillium;border:1px solid black;' align='left'>"+acti.getcActividadIdioma1()+"</td>"+
						    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>-</td>"+
						    		"<td style='font-family: Titillium;border:1px solid black;' align='right'>"+df.format(acti.getnPrecioActividad().doubleValue())+"</td>"+
						    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+reserva.getnNroPersonas()+"</td>"+
						    		"<td style='font-family: Titillium;border:1px solid black;color:#1A5276;' align='right'>"+df.format((acti.getnPrecioActividad().doubleValue()*reserva.getnNroPersonas()))+"</td>"+
						    	"</tr>";
				}
			}
		}
		return actividad;
	}
	public String[] obtenerHtmlHotel(CReservaPaqueteCategoriaHotel oReservaPCH,String[] etiqueta)
	{
		String[] hotel=new String[2];
		hotel[0]="";
		hotel[1]="";
		if(!oReservaPCH.isConCamaAdicional())
			oReservaPCH.setPrecioCamaAdicional("0.00");
		if(oReservaPCH!=null)
			if(oReservaPCH.isConHotel())
			{	hotel[0]="<table width='100%' style='border:1px solid rgba(0,0,0,0.1);border-collapse: collapse;'>"+
			    	"<thead style='background:rgba(0,0,0,0.1);font-weight: bold;'>"+etiqueta[52]+"</thead>"+
			    	"<tr style='background:rgba(0,0,0,0.1);border:1px solid black;'>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+etiqueta[51]+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+etiqueta[201]+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+etiqueta[202]+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+etiqueta[203]+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+etiqueta[204]+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+etiqueta[205]+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+etiqueta[206]+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+etiqueta[233]+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+etiqueta[145]+"</td>"+
			    	"</tr>"+
			    	"<tr style='border:1px solid black;'>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+oReservaPCH.getCategoria()+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+oReservaPCH.getnNroPersonasSimple()+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+df.format(oReservaPCH.getnPrecioTotalSimple().doubleValue()/oReservaPCH.getnNroPersonasSimple())+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+oReservaPCH.getnNroPersonasDoble()/2+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+df.format(oReservaPCH.getnPrecioTotalDoble().doubleValue()/(oReservaPCH.getnNroPersonasDoble()/2))+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+oReservaPCH.getnNroPersonasTriple()/3+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+df.format(oReservaPCH.getnPrecioTotalTriple().doubleValue()/(oReservaPCH.getnNroPersonasTriple()/3))+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+oReservaPCH.getPrecioCamaAdicional()+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;color:#1A5276;' align='right'>"+df.format(oReservaPCH.getnPrecioTotalSimple().doubleValue()+oReservaPCH.getnPrecioTotalDoble().doubleValue()+oReservaPCH.getnPrecioTotalTriple().doubleValue())+"</td>"+
			    	"</tr>"+
			    "</table>"+
			    "<br/>";
			hotel[1]="<table width='100%' style='border:1px solid rgba(0,0,0,0.1);border-collapse: collapse;'>"+
			    	"<thead style='background:rgba(0,0,0,0.1);font-weight: bold;'>HOTELES</thead>"+
			    	"<tr style='background:rgba(0,0,0,0.1);border:1px solid black;'>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>Categoria</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>Simples </td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>P.U. Simple</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>Dobles</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>P.U. Doble</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>Triples</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>P.U. Triple</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>Cama Adicional</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>Total</td>"+
			    	"</tr>"+
			    	"<tr style='border:1px solid black;'>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+oReservaPCH.getCategoria()+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+oReservaPCH.getnNroPersonasSimple()+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+df.format(oReservaPCH.getnPrecioTotalSimple().doubleValue()/oReservaPCH.getnNroPersonasSimple())+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+oReservaPCH.getnNroPersonasDoble()/2+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+df.format(oReservaPCH.getnPrecioTotalDoble().doubleValue()/(oReservaPCH.getnNroPersonasDoble()/2))+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+oReservaPCH.getnNroPersonasTriple()/3+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+df.format(oReservaPCH.getnPrecioTotalTriple().doubleValue()/(oReservaPCH.getnNroPersonasTriple()/3))+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;' align='center'>"+oReservaPCH.getPrecioCamaAdicional()+"</td>"+
			    		"<td style='font-family: Titillium;border:1px solid black;color:#1A5276;' align='right'>"+df.format(oReservaPCH.getnPrecioTotalSimple().doubleValue()+oReservaPCH.getnPrecioTotalDoble().doubleValue()+oReservaPCH.getnPrecioTotalTriple().doubleValue())+"</td>"+
			    	"</tr>"+
			    "</table>"+
			    "<br/>";
				for(CDestinoConHoteles DCH:oReservaPCH.getListaCategoriaDestinosHoteles())
				{
					hotel[0]+="<table width='40%' style='border:1px solid rgba(0,0,0,0.1);border-collapse: collapse;'>"+
					    	"<thead style='font-family:Titillium;background:rgba(0,0,0,0.1);font-weight: bold;'>"+DCH.getoDestino().getcDestino()+"</thead>";
					hotel[1]+="<table width='40%' style='font-family:Titillium;border:1px solid rgba(0,0,0,0.1);border-collapse: collapse;'>"+
					    	"<thead style='font-family:Titillium;background:rgba(0,0,0,0.1);font-weight: bold;'>"+DCH.getoDestino().getcDestino()+"</thead>";
					for(CHotel hoteles:DCH.getListaDestinosHoteles())
					{
						hotel[0]+="<tr style='border:1px solid black;'>"+
						    		"<td style='font-family:Titillium;border:1px solid black;' align='left'>"+
						    			"<a href='"+hoteles.getcUrl()+"'>"+hoteles.getcHotel()+"</a>"+
						    		"</td>"+
						    	"</tr>";
						hotel[1]+="<tr style='border:1px solid black;'>"+
					    		"<td style='font-family:Titillium;border:1px solid black;' align='left'>"+
					    			"<a href='"+hoteles.getcUrl()+"'>"+hoteles.getcHotel()+"</a>"+
					    		"</td>"+
					    	"</tr>";
					}
					hotel[0]+="</table>"+
						    "<br/>";
					hotel[1]+="</table>"+
						    "<br/>";
				}
			}
		return hotel;
	}
	public String obtenerHtmlCupon(CCupon cupon)
	{
		String cuponHTml="<div style='background:#1A5276;border-radius:5px;padding:8px;width:200px;'>"+
							"<div style='color:rgb(242, 242, 242);font-size:13px;font-weight:bold;'>Se aplicó descuento:</div>"+
							"<table border='0' style='background:#1A5276;'>"+
								"<tr border='0'>"+
									"<td border='0' style='font-family:Titillium;color:rgb(242, 242, 242);font-size:12px;font-weight:bold;'>"+
										"Código Cupon:"+
									"</td>"+
									"<td border='0'>"+
										"<strong style='font-family:Titillium;color:white;font-size:12px;'>"+cupon.getcCupon()+"</strong>"+
									"</td>"+
								"</tr>"+
								"<tr border='0'>"+
									"<td border='0' style='font-family:Titillium;color:rgb(242, 242, 242);font-size:12px;font-weight:bold;'>"+
										"Descuento:"+
									"</td>"+
									"<td border='0'>"+
										"<strong style='font-family:Titillium;color:white;font-size:12px;'>"+cupon.getnPorcentajeDcto()+" %</strong>"+
									"</td>"+
								"</tr>"+
							"</table>"+
						"</div>";
		
		return cuponHTml;
	}
	public String obtenerTxtMes(int auxMes)
	{
		String mes="";
		switch(auxMes)
		{
			case 1:mes=etiqueta[24].toLowerCase();break;
			case 2:mes=etiqueta[25].toLowerCase();break;
			case 3:mes=etiqueta[26].toLowerCase();break;
			case 4:mes=etiqueta[27].toLowerCase();break;
			case 5:mes=etiqueta[28].toLowerCase();break;
			case 6:mes=etiqueta[29].toLowerCase();break;
			case 7:mes=etiqueta[30].toLowerCase();break;
			case 8:mes=etiqueta[31].toLowerCase();break;
			case 9:mes=etiqueta[32].toLowerCase();break;
			case 10:mes=etiqueta[33].toLowerCase();break;
			case 11:mes=etiqueta[34].toLowerCase();break;
			case 12:mes=etiqueta[35].toLowerCase();break;
		}
		return mes;
	}
	public String obtenerTxtMesES(int auxMes)
	{
		String mes="";
		switch(auxMes)
		{
			case 1:mes="Enero";break;
			case 2:mes="Febrero";break;
			case 3:mes="Marzo";break;
			case 4:mes="Abril";break;
			case 5:mes="Mayo";break;
			case 6:mes="Junio";break;
			case 7:mes="Julio";break;
			case 8:mes="Agosto";break;
			case 9:mes="Setiembre";break;
			case 10:mes="Octubre";break;
			case 11:mes="Noviembre";break;
			case 12:mes="Diciembre";break;
		}
		return mes;
	}
	public String generarNombreQR()
	{
		String name="";
		//=========================
				File directorio=new File(ScannUtil.getPath());
				String[] imagenes=directorio.list();
				//=================================
				if(imagenes!=null)
					name="qrCode"+imagenes.length+".png";
				else
					name="qrCode.png";
		return name;
	}
}
