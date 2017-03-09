package com.pricing.util;

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
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeMultipart;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

import com.lowagie.text.DocumentException;
import com.pricing.dao.CConfigUrlDAO;
import com.pricing.dao.CCorreoSmtpDAO;
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
            BodyPart adjunto = new MimeBodyPart();
            if(opcion==1)//Hay que adjuntar pdf (eso es que el cliente efectuo un pago)
            {
                adjunto.setDataHandler(
                    new DataHandler(new FileDataSource(urlPdf)));
                adjunto.setFileName("DatosReserva.pdf");
            }

            // Una MultiParte para agrupar texto e imagen.
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            if(opcion==1)
            	multiParte.addBodyPart(adjunto);

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
	            	adjunto.setDataHandler(
	                new DataHandler(new FileDataSource(urlImage.get(i))));
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
			String fechaInicio,String fechaFin,CReserva reserva,
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
		String pasajeros[]=obtenerHtmlPasajeros(listaPasajeros,etiqueta);
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
		/****************************************/
		String mensajeHTML=
				"<html>"+
						"<head></head>"+
						"<body>"+
							"<div style='width:100%;background:rgb(242, 242, 242);'>"+
								"<table border='0' width='100%' align='center' style='background:rgb(242, 242, 242);'>"+
									"<tr>"+
								      "<td align='center' width='20%' style='padding:8px 20px 8px 20px;'>"+
								      	"<a href='"+etiqueta[212]+"' style='text-decoration:none;'>"+
								      		"<img src='"+etiqueta[211]+"' width='90' height='60' border='0' />"+
								      	"</a>"+
								      "</td>"+
								      "<td style='color:black;font-size:17px;font-weight:bold;' align='left' width='80%'>"+reserva.getoPaquete().getTitulo()+"</td>"+
								    "</tr>"+
								"</table>"+
							"<div style='padding:20px;background:white;border:20px solid rgb(242, 242, 242);'>"+
								"<p style='font-weight:bold;font-size:18px;margin:5px 0 6px 0;'>"+fechaActual[0]+"</p>"+
								"<div style='background:#1A5276;border-radius:5px;width:25%;padding:1px 0 1px 0;' align='center'>"+
									"<p style='font-weight:bold;'>"+etiqueta[118]+" <strong style='color:white;'>"+reserva.getcReservaCod()+"</strong></p>"+
								"</div>"+
								"<br/>"+
								"<table border='0'>"+
									"<tr>"+
								      "<td><p><strong>"+etiqueta[125]+"</strong>"+reserva.getcContacto()+"."+
								      "</p></td>"+
								    "</tr>"+
								"</table>"+
								"<div align='center'>"+
									"<p style='color:#F7653A;font-size:18px;font-weight:bold;'>"+etiqueta[126]+"</p>"+
								"</div>"+
								"<p style='font-weight:bold;'>"+etiqueta[127]+"</p>"+
								"<div align='center'>"+
									etiqueta[128]+
									"<form action='"+configUrlDao.getoConfigUrl().getUrlServletPagoTotal()+"' method='POST'>"+
										"<input type='hidden' name='Monto' value='"+totalPago+"'/>"+
										"<input type='hidden' name='codReserva' value='"+reserva.getcReservaCod()+"'/>"+
										"<input type='hidden' name='namePaquete' value='"+reserva.getoPaquete().getcTituloIdioma1()+"'/>"+
										"<input type='hidden' name='mail' value='"+reserva.getcEmail()+"'/>"+
										"<input type='hidden' name='contacto' value='"+reserva.getcContacto()+"'/>"+
										"<input type='hidden' name='language' value='"+language+"'/>"+
										"<input type='hidden' name='impuestoPaypal' value='"+oImpuesto.getImpuestoPaypal()+"'/>"+
										"<input type='image' name='submit' border='0'"+
						    			"src='https://www.paypal.com/en_US/i/btn/btn_xpressCheckout.gif'/>"+
									"</form>"+
									etiqueta[129]+
									"<form action='"+configUrlDao.getoConfigUrl().getUrlServletPagoParcial()+"' method='POST'>"+
										"<input type='hidden' name='Monto' value='"+pagoParcial+"'/>"+
										"<input type='hidden' name='codReserva' value='"+reserva.getcReservaCod()+"'/>"+
										"<input type='hidden' name='namePaquete' value='"+reserva.getoPaquete().getcTituloIdioma1()+"'/>"+
										"<input type='hidden' name='mail' value='"+reserva.getcEmail()+"'/>"+
										"<input type='hidden' name='contacto' value='"+reserva.getcContacto()+"'/>"+
										"<input type='hidden' name='language' value='"+language+"'/>"+
										"<input type='hidden' name='impuestoPaypal' value='"+oImpuesto.getImpuestoPaypal()+"'/>"+
										"<input type='image' name='submit' border='0'"+
						    			"src='https://www.paypal.com/en_US/i/btn/btn_xpressCheckout.gif'/>"+
									"</form>"+
								"</div>"+
								"<div>"+
								"<p>"+etiqueta[130]+" <strong style='color:#F7653A;text-transform: lowercase;'>"+reserva.getoPaquete().getTitulo()+"</strong>"+ 
								etiqueta[131]+" <strong style='color:#F7653A;'>"+fechaInicio+"</strong> "+etiqueta[132]+ 
								"<strong style='color:#F7653A;'>"+fechaFin+"</strong> "+etiqueta[133]+" <strong> "+reserva.getnNroPersonas()+" </strong> "+etiqueta[134]+
								"<strong style='color:#F7653A;'> "+etiqueta[135]+" </strong> "+etiqueta[136]+" <br/> "+etiqueta[137]+" </p>"+
								
								"<table width='40%'>"+
									fechas+
							    "</table>"+
							    "<br/>"+
							    "<p> "+etiqueta[138]+" </p>"+
							    "<table width='100%' style='border:1px solid rgba(0,0,0,0.1);border-collapse: collapse;'>"+
							    	"<thead style='font-weight: bold;'>"+etiqueta[88]+"</thead>"+
							    	"<tr style='background:rgba(0,0,0,0.1);border:1px solid black;'>"+
							    		"<td style='border:1px solid black;' align='center'>"+etiqueta[139]+"</td>"+
							    		"<td style='border:1px solid black;' align='center'>"+etiqueta[140]+"</td>"+
							    		"<td style='border:1px solid black;' align='center'>"+etiqueta[72]+"</td>"+
							    		"<td style='border:1px solid black;' align='center'>"+etiqueta[71]+"</td>"+
							    		"<td style='border:1px solid black;' align='center'>"+etiqueta[73]+"</td>"+
							    		"<td style='border:1px solid black;' align='center'>"+etiqueta[74]+"</td>"+
							    		"<td style='border:1px solid black;' align='center'>"+etiqueta[75]+"</td>"+
							    	"</tr>"+
							    	pasajeros[0]+
							    "</table>"+
							    "<br/>"+
							    hotel[0]+
							    "<table width='100%' style='border:1px solid rgba(0,0,0,0.1);border-collapse: collapse;'>"+
							    	"<tr style='background:rgba(0,0,0,0.1);border:1px solid black;'>"+
							    		"<td style='border:1px solid black;' align='center'>"+etiqueta[141]+"</td>"+
							    		"<td style='border:1px solid black;' align='center'>"+etiqueta[142]+"</td>"+
							    		"<td style='border:1px solid black;' align='center'>"+etiqueta[143]+"</td>"+
							    		"<td style='border:1px solid black;' align='center'>"+etiqueta[144]+"</td>"+
							    		"<td style='border:1px solid black;' align='center'>"+etiqueta[145]+"</td>"+
							    	"</tr>"+
							    	"<tr style='border:1px solid black;'>"+
							    		"<td style='border:1px solid black;' align='left'>"+reserva.getoPaquete().getTitulo()+"</td>"+
							    		"<td style='border:1px solid black;' align='center'>-</td>"+
							    		"<td style='border:1px solid black;' align='right'>"+precioUniPaquete+"</td>"+
							    		"<td style='border:1px solid black;' align='center'>"+reserva.getnNroPersonas()+"</td>"+
							    		"<td style='border:1px solid black;color:#1A5276;' align='right'>"+reserva.getnPrecioPaquetePersona().toString()+"</td>"+
							    	"</tr>"+
							    	servicios[0]+
							    	actividades[0]+
							    "</table>"+
							    "<table width='100%' style='background:rgba(0,0,0,0.02);border:1px solid black;'>"+
							    	"<tr>"+
							    		"<td width='80%'></td>"+
							    		"<td width='20%'>"+
							    			"<table width='100%'>"+
							    				"<tr><td>"+etiqueta[99]+"</td><td style='color:#1A5276;' align='right'>"+totalPago+"</td></tr>"+
							    				"<tr><td>"+etiqueta[100]+"</td><td style='color:#1A5276;' align='right'>"+impuesto+"</td></tr>"+
							    				"<tr><td></td><td style='color:#1A5276;' align='right'>--------------</td></tr>"+
							    				"<tr><td>"+etiqueta[101]+"</td><td style='color:#1A5276;' align='right'>"+importeTotal+"</td></tr>"+
							    			"</table>"+
							    		"</td>"+
							    	"</tr>"+
							    "</table>"+
							    "<br/>"+
							    "<p>"+etiqueta[146]+"</p>"+
							    "<p>"+etiqueta[147]+"</p>"+
							    "<table width='80%'>"+
							    	"<tr align='center'>"+
							    		"<td><img width='60' height='60' src='http://www.panesolution.com/images/widget/1438789785062.png'/><p>"+etiqueta[148]+" <strong><a href='"+etiqueta[214]+"'>"+etiqueta[152]+"</a></strong></p></td>"+
							    		"<td><img width='60' height='60' src='http://img.webme.com/pic/s/simutrans-hispano/youtubeglass.png'/><p>"+etiqueta[149]+" <strong><a href='"+etiqueta[215]+"'>"+etiqueta[152]+"</a></strong></p></td>"+
							    	"</tr>"+
							    	"<tr align='center'>"+
							    		"<td><img width='60' height='60' src='http://www.asm.wisc.edu/wp-content/uploads/2009/08/twitter-icon2-150x150.jpg'/><p>"+etiqueta[150]+" <strong><a href='"+etiqueta[216]+"'>"+etiqueta[152]+"</a></strong></p></td>"+
							    		"<td><img width='60' height='60' src='http://androidspain.es/wp-content/uploads/2013/03/Icono-Whatsapp.png'/><p>"+etiqueta[151]+"</p></td>"+
							    	"</tr>"+
							    "</table>"+
							"</div>"+
							  "<p style='font-size:11px;'>"+etiqueta[153]+" <strong>"+etiqueta[154]+"</strong>"+etiqueta[155]+"</p>"+
							  "<p style='font-size:11px;'>"+etiqueta[196]+"</p>"+
							  "<br/>"+
						        "<strong>"+etiqueta[213]+"</strong>"+
							"</div>"+
							"<div style='background:gray;"+
								"color:white;"+
								"font-size:15px;font-weight:bold;width:100%;height:60px;'>"+
								"<p style='padding:20px 0 0 30%;'>Copyright © "+cal.get(Calendar.YEAR)+" "+etiqueta[157]+"</p>"+
							"</div>"+
						  "</div>"+
					"</body>"+
			"</html>";
		boolean b=enviarCorreoSinPagoAEmpresa("Datos de Nueva Reserva", hotel[1], oImpuesto, reserva.getoPaquete(), pasajeros[1], servicios[1], fechaInicio, fechaFin, reserva, precioUniPaquete, fechaActual[1], fechas, totalPago, urlImage,actividades[1]);
		return sendMail(reserva.getcEmail(),titulo,mensajeHTML,urlPdf,0);
	}
	public boolean enviarCorreoSinPagoAEmpresa(String titulo,String htmlHotel,CImpuesto oImpuesto,CPaquete paquete,String htmlPasajeros,
			String htmlServicios,String fechaInicio,String fechaFin,CReserva reserva,String precioUniPaquete,String fechaActual,
			String htmlFechasAlternas,String totalPago,ArrayList<String> urlImage,String htmlActividades) throws IOException, DocumentException
	{
		Calendar cal=Calendar.getInstance();
		/**Se obtiene el impuesto e importe total del totalPago**/
		String impuesto=df.format(Double.parseDouble(totalPago)*(Double.parseDouble(oImpuesto.getImpuestoPaypal())/100));
		String importeTotal=df.format(Double.parseDouble(totalPago)+Double.parseDouble(impuesto));
		/*********************************************************/
		String mensajeHTML=
				"<html>"+
					"<head></head>"+
					"<body>"+
						"<div style='width:100%;background:rgb(242, 242, 242);'>"+
							"<table border='0' width='100%' align='center' style='background:rgb(242, 242, 242);'>"+
								"<tr>"+
							      "<td align='center' width='20%' style='padding:8px 20px 8px 20px;'>"+
							      	"<a href='"+etiqueta[212]+"' style='text-decoration:none;'>"+
							      		"<img src='"+etiqueta[211]+"' width='90' height='60' border='0' />"+
							      	"</a>"+
							      "</td>"+
							      "<td style='color:black;font-size:17px;font-weight:bold;' align='left' width='80%'>"+paquete.getcTituloIdioma1()+"</td>"+
							    "</tr>"+
							"</table>"+
							"<div style='padding:20px;background:white;border:20px solid rgb(242, 242, 242);'>"+
								"<p style='font-weight:bold;font-size:18px;margin:5px 0 6px 0;'>"+fechaActual+"</p>"+
								"<div style='background:#1A5276;border-radius:5px;width:25%;padding:1px 0 1px 0;' align='center'>"+
									"<p style='font-weight:bold;'>Codigo de Reserva: <strong style='color:white;'>"+reserva.getcReservaCod()+"</strong></p>"+
								"</div>"+
								"<br/>"+
								"<table border='0'>"+
									"<tr>"+
								      "<td><p><strong>"+reserva.getcContacto()+"."+"</strong> Realizó una reserva."+
								      "</p></td>"+
								    "</tr>"+
								"</table>"+
								"<div>"+
									"<p>Su fecha Principal de reserva de <strong style='color:#F7653A;'>"+paquete.getcTituloIdioma1()+"</strong>"+ 
									" es: <strong style='color:#F7653A;'>"+fechaInicio+"</strong> y "+ 
									"<strong style='color:#F7653A;'>"+fechaFin+"</strong> Con:<br/>Las Siguientes Fechas Alternas:</p>"+
									
									"<table width='40%'>"+
									htmlFechasAlternas+
								    "</table>"+
								    "<br/>"+
								    "<p>Las siguientes tablas muestran datos de su reserva:</p>"+
								    "<table width='100%' style='border:1px solid rgba(0,0,0,0.1);border-collapse: collapse;'>"+
								    	"<thead style='font-weight: bold;'>Lista de Pasajeros</thead>"+
								    	"<tr style='background:rgba(0,0,0,0.1);border:1px solid black;'>"+
								    		"<td style='border:1px solid black;' align='center'>Doc.</td>"+
								    		"<td style='border:1px solid black;' align='center'>Nro</td>"+
								    		"<td style='border:1px solid black;' align='center'>Nombre</td>"+
								    		"<td style='border:1px solid black;' align='center'>Apellidos</td>"+
								    		"<td style='border:1px solid black;' align='center'>Sexo</td>"+
								    		"<td style='border:1px solid black;' align='center'>Edad</td>"+
								    		"<td style='border:1px solid black;' align='center'>País</td>"+
								    	"</tr>"+
								    	htmlPasajeros+
								    "</table>"+
								    "<br/>"+
								    htmlHotel+
								    "<table width='100%' style='border:1px solid rgba(0,0,0,0.1);border-collapse: collapse;'>"+
								    	"<tr style='background:rgba(0,0,0,0.1);border:1px solid black;'>"+
								    		"<td style='border:1px solid black;' align='center'>Descripción</td>"+
								    		"<td style='border:1px solid black;' align='center'>Sub-Servicio</td>"+
								    		"<td style='border:1px solid black;' align='center'>Precio Unitario (USD)</td>"+
								    		"<td style='border:1px solid black;' align='center'>Cantidad</td>"+
								    		"<td style='border:1px solid black;' align='center'>Precio Total (USD)</td>"+
								    	"</tr>"+
								    	"<tr style='border:1px solid black;'>"+
								    		"<td style='border:1px solid black;' align='left'>"+paquete.getcTituloIdioma1()+"</td>"+
								    		"<td style='border:1px solid black;' align='center'>-</td>"+
								    		"<td style='border:1px solid black;' align='right'>"+precioUniPaquete+"</td>"+
								    		"<td style='border:1px solid black;' align='center'>"+reserva.getnNroPersonas()+"</td>"+
								    		"<td style='border:1px solid black;color:#1A5276;' align='right'>"+reserva.getnPrecioPaquetePersona().toString()+"</td>"+
								    	"</tr>"+
								    	htmlServicios+
								    	htmlActividades+
								    "</table>"+
								    "<table width='100%' style='background:rgba(0,0,0,0.02);border:1px solid black;'>"+
								    	"<tr>"+
								    		"<td width='80%'></td>"+
								    		"<td width='20%'>"+
								    			"<table width='100%'>"+
								    				"<tr><td>Subtotal</td><td style='color:#1A5276;' align='right'>"+totalPago+"</td></tr>"+
								    				"<tr><td>Impuesto (6.9%)</td><td style='color:#1A5276;' align='right'>"+impuesto+"</td></tr>"+
								    				"<tr><td></td><td style='color:#1A5276;' align='right'>--------------</td></tr>"+
								    				"<tr><td>Importe Total</td><td style='color:#1A5276;' align='right'>"+importeTotal+"</td></tr>"+
								    			"</table>"+
								    		"</td>"+
								    	"</tr>"+
								    "</table>"+
								    "<br/>"+
								    "<p style='color:red;font-size:25px;'>PENDIENTE DE PAGO</p>"+
								"</div>"+
								  "<br/>"+
							        "<strong>"+etiqueta[213]+"</strong>"+
								"</div>"+
								"<div style='background:gray;"+
									"color:white;"+
									"font-size:15px;font-weight:bold;width:100%;height:60px;'>"+
									"<p style='padding:20px 0 0 30%;'>Copyright © "+cal.get(Calendar.YEAR)+" eddyonsoft.com - Todos los Derechos Reservados</p>"+
								"</div>"+
							  "</div>"+
						"</body>"+
				"</html>";
		return sendMailToEmpresa(reserva.getcEmail(),titulo,mensajeHTML,urlImage);
	}
	public boolean enviarCorreoConPago(String titulo,String[] etiqueta,CImpuesto oImpuesto,CReservaPaqueteCategoriaHotel oReservaPCH,
			String fechaInicio,String fechaFin,CReserva reserva,
			ArrayList<String> fechasAlternas,String totalPago,String montoPagar,String urlPdf,String codTransaccion,
			String porcentajePago,ArrayList<CPasajero> listaPasajeros,
			CPagos pagos) throws IOException, DocumentException
	{
		this.etiqueta=etiqueta;
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
						"<thead style='font-weight: bold;font-size:20px;'>"+etiqueta[197]+"</thead>"+
				    	"<tr style='background:rgba(0,0,0,0.1)'>"+
							"<td width='60%' align='center'><h1 style='color:#F7653A;font-weight:bold;'>"+etiqueta[102]+"</h1></td>"+
				    		"<td width='40%'>"+
				    			"<table width='100%'>"+
				    				"<tr><td>"+etiqueta[99]+" ("+etiqueta[102]+"): USD"+"</td><td style='color:#1A5276;' align='right'>"+montoPagar+"</td></tr>"+
				    				"<tr><td>"+etiqueta[100]+": USD</td><td style='color:#1A5276;' align='right'>"+auxImpuesto+"</td></tr>"+
				    				"<tr><td></td><td style='color:#1A5276;' align='right'>--------------</td></tr>"+
				    				"<tr><td>"+etiqueta[101]+" ("+etiqueta[102]+"): USD"+"</td><td align='right' style='background:#75BE5C;font-weight:bold;'>"+"USD "+auxImporteTotal+"</td></tr>"+
				    			"</table>"+
				    		"</td>"+
				    	"</tr>"+
				    "</table>"+
				    "<p>"+etiqueta[159]+" <strong style='color:#F7653A;'>"+etiqueta[102]+"</strong>"+etiqueta[161]+
				    "<strong style='color:#F7653A;'>USD "+totalPago+"</strong><br/> "+etiqueta[160]+"</p>";
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
					"<thead style='font-weight: bold;font-size:20px;'>"+etiqueta[197]+"</thead>"+
			    	"<tr style='background:rgba(0,0,0,0.1)'>"+
			    		"<td width='60%' align='center'><h1 style='color:#F7653A;font-weight:bold;'>"+etiqueta[103]+"</h1></td>"+
			    		"<td width='40%'>"+
			    			"<table width='100%'>"+
			    				"<tr><td>"+etiqueta[99]+" ("+etiqueta[103]+"): USD"+"</td><td style='color:#1A5276;' align='right'>"+totalPago+"</td></tr>"+
			    				"<tr><td>"+etiqueta[100]+": USD</td><td style='color:#1A5276;' align='right'>"+impuesto+"</td></tr>"+
			    				"<tr><td></td><td style='color:#1A5276;' align='right'>--------------</td></tr>"+
			    				"<tr><td>"+etiqueta[101]+" ("+etiqueta[103]+"): USD"+"</td><td align='right'style='background:#75BE5C;font-weight:bold;'>"+"USD "+importeTotal+"</td></tr>"+
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
			    	"<thead style='font-weight: bold;'>"+etiqueta[52]+"</thead>"+
			    	"<tr style='background:rgba(0,0,0,0.1);border:1px solid black;'>"+
			    		"<td style='border:1px solid black;' align='center'>"+etiqueta[51]+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+etiqueta[201]+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+etiqueta[202]+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+etiqueta[203]+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+etiqueta[204]+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+etiqueta[205]+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+etiqueta[206]+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+etiqueta[233]+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+etiqueta[145]+"</td>"+
			    	"</tr>"+
			    	"<tr style='border:1px solid black;'>"+
			    		"<td style='border:1px solid black;' align='center'>"+oReservaPCH.getCategoria()+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+oReservaPCH.getnNroPersonasSimple()+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+df.format(oReservaPCH.getnPrecioTotalSimple().doubleValue()/oReservaPCH.getnNroPersonasSimple())+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+oReservaPCH.getnNroPersonasDoble()/2+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+df.format(oReservaPCH.getnPrecioTotalDoble().doubleValue()/(oReservaPCH.getnNroPersonasDoble()/2))+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+oReservaPCH.getnNroPersonasTriple()/3+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+df.format(oReservaPCH.getnPrecioTotalTriple().doubleValue()/(oReservaPCH.getnNroPersonasTriple()/3))+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+oReservaPCH.getPrecioCamaAdicional()+"</td>"+
			    		"<td style='border:1px solid black;color:#1A5276;' align='right'>"+df.format(oReservaPCH.getnPrecioTotalSimple().doubleValue()+oReservaPCH.getnPrecioTotalDoble().doubleValue()+oReservaPCH.getnPrecioTotalTriple().doubleValue())+"</td>"+
			    	"</tr>"+
			    "</table>"+
			    "<br/>";
				for(CDestinoConHoteles DCH:oReservaPCH.getListaCategoriaDestinosHoteles())
				{
					hotel+="<table width='40%' style='border:1px solid rgba(0,0,0,0.1);border-collapse: collapse;'>"+
					    	"<thead style='font-weight: bold;'>"+DCH.getoDestino().getcDestino()+"</thead>";
					for(CHotel hoteles:DCH.getListaDestinosHoteles())
					{
						hotel+="<tr style='background:rgba(0,0,0,0.1);border:1px solid black;'>"+
						    		"<td style='border:1px solid black;' align='left'>"+
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
			for(int i=0;i<fechasAlternas.size();i++)
			{
				fechas+=
						"<tr align='center'>"+
				           "<td style='color:#F7653A;font-weight:bold;'>"+fechasAlternas.get(i)+"</td>"+
				        "</tr>";
			}
		/**********************************************/
		/**Se obtienen los datos de los pasajeros**/
		String pasajeros="";
		for(CPasajero pax:listaPasajeros)
		{
			if(!pax.isSelectPasajero() || pax.isEsEdit())break;
			pasajeros+=
						"<tr style='border:1px solid black;'>"+
				    		"<td style='border:1px solid black;' align='center'>"+pax.getTipoDocumento()+"</td>"+
				    		"<td style='border:1px solid black;' align='center'>"+pax.getcNroDoc()+"</td>"+
				    		"<td style='border:1px solid black;' align='center'>"+pax.getcNombres()+"</td>"+
				    		"<td style='border:1px solid black;' align='center'>"+pax.getcApellidos()+"</td>"+
				    		"<td style='border:1px solid black;' align='center'>"+pax.getcSexo()+"</td>"+
				    		"<td style='border:1px solid black;' align='center'>"+pax.getnEdad()+"</td>"+
				    		"<td style='border:1px solid black;' align='center'>"+pax.getNombrePais()+"</td>"+
				    	"</tr>";
		}
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
						    		"<td style='border:1px solid black;' align='left'>"+servicio.getServicio()+"</td>";
							if(servicio.getcRestriccionNum()==0 && servicio.getcRestriccionYesNo()==0)
						    		servicios+="<td style='border:1px solid black;' align='center'>"+servicio.getSelectOpcion()+"</td>";
							else
								servicios+="<td style='border:1px solid black;' align='center'>-</td>";
						    servicios+="<td style='border:1px solid black;' align='right'>"+servicio.getnPrecioServicio().toString()+"</td>"+
						    		"<td style='border:1px solid black;' align='center'>"+servicio.getOpcionValue()+"</td>"+
						    		"<td style='border:1px solid black;color:#1A5276;' align='right'>"+servicio.getPrecioTotalServicio()+"</td>"+
						    	"</tr>";
				}
			}
		String[] actividades=obtenerHtmlActividades(reserva.getoPaquete().getListaActividades(), reserva);
		/******************************************/
		
		String mensajeHTML=
				"<html>"+
					"<head></head>"+
					"<body>"+
						"<div style='width:100%;background:rgb(242, 242, 242);'>"+
							"<table border='0' width='100%' align='center' style='background:rgb(242, 242, 242);'>"+
								"<tr>"+
							      "<td align='center' width='20%' style='padding:8px 20px 8px 20px;'>"+
							      	"<a href='"+etiqueta[212]+"' style='text-decoration:none;'>"+
							      		"<img src='"+etiqueta[211]+"' width='90' height='60' border='0' />"+
							      	"</a>"+
							      "</td>"+
							      "<td style='color:black;font-size:17px;font-weight:bold;' align='left' width='80%'>"+reserva.getoPaquete().getTitulo()+"</td>"+
							    "</tr>"+
							"</table>"+
							"<div style='padding:20px;background:white;border:20px solid rgb(242, 242, 242);'>"+
								"<p style='font-weight:bold;font-size:18px;margin:5px 0 6px 0;'>"+fechaActual+"</p>"+
								"<div style='background:#1A5276;border-radius:5px;width:25%;padding:1px 0 1px 0;' align='center'>"+
									"<p style='font-weight:bold;'>"+etiqueta[118]+" <strong style='color:white;'>"+reserva.getcReservaCod()+"</strong></p>"+
								"</div>"+
								"<br/>"+
								"<div>"+
									"<p><strong>"+etiqueta[125]+" </strong>"+reserva.getcContacto()+"."+"<br/> "+etiqueta[130]+" <strong style='color:#F7653A;text-transform: lowercase;'>"+reserva.getoPaquete().getTitulo()+"</strong>"+ 
									etiqueta[131]+" <strong style='color:#F7653A;'>"+fechaInicio+"</strong>"+etiqueta[132]+ 
									"<strong style='color:#F7653A;'>"+fechaFin+"</strong> "+etiqueta[133]+" "+reserva.getnNroPersonas()+" "+etiqueta[134]+ 
									"<strong style='color:#F7653A;'> "+etiqueta[135]+" </strong> "+etiqueta[136]+"<br/>"+etiqueta[137]+" </p>"+
									
									"<table width='40%'>"+
										fechas+
								    "</table>"+
								    "<br/>"+
								    "<div style='background:#1A5276;border-radius:5px;width:25%;padding:1px 0 1px 0;' align='center'>"+
										"<p style='font-weight:bold;'>"+etiqueta[123]+" <strong style='color:white;'>"+codTransaccion+"</strong></p>"+
									"</div>"+
								    "<br/>"+
								    "<p>"+etiqueta[138]+"</p>"+
								    "<table width='100%' style='border:1px solid rgba(0,0,0,0.1);border-collapse: collapse;'>"+
								    	"<thead style='font-weight: bold;'>"+etiqueta[88]+"</thead>"+
								    	"<tr style='background:rgba(0,0,0,0.1);border:1px solid black;'>"+
								    		"<td style='border:1px solid black;' align='center'>"+etiqueta[139]+"</td>"+
								    		"<td style='border:1px solid black;' align='center'>"+etiqueta[140]+"</td>"+
								    		"<td style='border:1px solid black;' align='center'>"+etiqueta[72]+"</td>"+
								    		"<td style='border:1px solid black;' align='center'>"+etiqueta[71]+"</td>"+
								    		"<td style='border:1px solid black;' align='center'>"+etiqueta[73]+"</td>"+
								    		"<td style='border:1px solid black;' align='center'>"+etiqueta[74]+"</td>"+
								    		"<td style='border:1px solid black;' align='center'>"+etiqueta[75]+"</td>"+
								    	"</tr>"+
								    	pasajeros+
								    "</table>"+
								    "<br/>"+
								    hotel+
								    "<table width='100%' style='border:1px solid rgba(0,0,0,0.1);border-collapse: collapse;'>"+
								    	"<tr style='background:rgba(0,0,0,0.1);border:1px solid black;'>"+
								    		"<td style='border:1px solid black;' align='center'>"+etiqueta[141]+"</td>"+
								    		"<td style='border:1px solid black;' align='center'>"+etiqueta[142]+"</td>"+
								    		"<td style='border:1px solid black;' align='center'>"+etiqueta[143]+"</td>"+
								    		"<td style='border:1px solid black;' align='center'>"+etiqueta[144]+"</td>"+
								    		"<td style='border:1px solid black;' align='center'>"+etiqueta[145]+"</td>"+
								    	"</tr>"+
								    	"<tr style='border:1px solid black;'>"+
								    		"<td style='border:1px solid black;' align='left'>"+reserva.getoPaquete().getTitulo()+"</td>"+
								    		"<td style='border:1px solid black;' align='center'>-</td>"+
								    		"<td style='border:1px solid black;' align='right'>"+precioUniPaquete+"</td>"+
								    		"<td style='border:1px solid black;' align='center'>"+reserva.getnNroPersonas()+"</td>"+
								    		"<td style='border:1px solid black;color:#1A5276;' align='right'>"+reserva.getnPrecioPaquetePersona().toString()+"</td>"+
								    	"</tr>"+
								    	servicios+
								    	actividades[0]+
								    "</table>"+
								    "<table width='100%' style='background:rgba(0,0,0,0.02);border:1px solid black;'>"+
								    	"<tr>"+
								    		"<td width='80%'></td>"+
								    		"<td width='20%'>"+
								    			"<table width='100%'>"+
								    				"<tr><td>"+etiqueta[107]+"</td><td style='color:#1A5276;' align='right'>"+totalPago+"</td></tr>"+
								    			"</table>"+
								    		"</td>"+
								    	"</tr>"+
								    "</table>"+
								    "<br/>"+
								    pagoAl+
								    "<br/>"+
								    "<p>"+etiqueta[146]+"</p>"+
								    "<p>"+etiqueta[147]+"</p>"+
								    "<table width='80%'>"+
								    	"<tr align='center'>"+
								    		"<td><img width='60' height='60' src='http://www.panesolution.com/images/widget/1438789785062.png'/><p>"+etiqueta[148]+" <strong><a href='"+etiqueta[214]+"'>"+etiqueta[152]+"</a></strong></p></td>"+
								    		"<td><img width='60' height='60' src='http://img.webme.com/pic/s/simutrans-hispano/youtubeglass.png'/><p>"+etiqueta[149]+" <strong><a href='"+etiqueta[215]+"'>"+etiqueta[152]+"</a></strong></p></td>"+
								    	"</tr>"+
								    	"<tr align='center'>"+
								    		"<td><img width='60' height='60' src='http://www.asm.wisc.edu/wp-content/uploads/2009/08/twitter-icon2-150x150.jpg'/><p>"+etiqueta[150]+" <strong><a href='"+etiqueta[216]+"'>"+etiqueta[152]+"</a></strong></p></td>"+
								    		"<td><img width='60' height='60' src='http://androidspain.es/wp-content/uploads/2013/03/Icono-Whatsapp.png'/><p>"+etiqueta[151]+"</p></td>"+
								    	"</tr>"+
								    "</table>"+
								"</div>"+
								  "<p style='font-size:11px;'>"+etiqueta[153]+" <strong>"+etiqueta[154]+"</strong>"+etiqueta[155]+"</p>"+
								  "<p style='font-size:11px;'>"+etiqueta[196]+"</p>"+
								  "<br/>"+
							        "<strong>"+etiqueta[213]+"</strong>"+
								"</div>"+
								"<div style='background:gray;"+
									"color:white;"+
									"font-size:15px;font-weight:bold;width:100%;height:60px;'>"+
									"<p style='padding:20px 0 0 30%;'>Copyright © "+annio+" "+etiqueta[157]+"</p>"+
								"</div>"+
							  "</div>"+
						"</body>"+
				"</html>";
		convertHTML_PDF.convertirHtml2Pdf(mensajeHTML,urlPdf);
		return sendMail(reserva.getcEmail(),titulo,mensajeHTML,urlPdf,1);
	}
	public boolean enviarCorreoConPagoAEmpresa(String titulo,CImpuesto oImpuesto,CReservaPaqueteCategoriaHotel oReservaPCH,
			String fechaInicio,String fechaFin,CReserva reserva,
			ArrayList<String> fechasAlternas,String totalPago,String montoPagar,ArrayList<String> imagenes,String codTransaccion,
			String porcentajePago,ArrayList<CPasajero> listaPasajeros,
			CPagos pagos) throws IOException, DocumentException
	{
		String pagoAl="";
		if(porcentajePago.equals("1"))//pago al 40%
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
						"<thead style='font-weight: bold;font-size:20px;'>PAGO EFECTUADO</thead>"+
				    	"<tr style='background:rgba(0,0,0,0.1)'>"+
							"<td width='60%' align='center'><h1 style='color:#F7653A;font-weight:bold;'>40%</h1></td>"+
				    		"<td width='40%'>"+
				    			"<table width='100%'>"+
				    				"<tr><td>SubTotal:</td><td style='color:#1A5276;' align='right'>"+montoPagar+"</td></tr>"+
				    				"<tr><td>Impuesto:</td><td style='color:#1A5276;' align='right'>"+auxImpuesto+"</td></tr>"+
				    				"<tr><td></td><td style='color:#1A5276;' align='right'>--------------</td></tr>"+
				    				"<tr><td>Importe Total:</td><td align='right' style='background:#75BE5C;font-weight:bold;'>"+"USD "+auxImporteTotal+"</td></tr>"+
				    			"</table>"+
				    		"</td>"+
				    	"</tr>"+
				    "</table>";
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
					"<thead style='font-weight: bold;font-size:20px;'>PAGO EFECTUADO</thead>"+
			    	"<tr style='background:rgba(0,0,0,0.1)'>"+
			    		"<td width='60%' align='center'><h1 style='color:#F7653A;font-weight:bold;'>100%</h1></td>"+
			    		"<td width='40%'>"+
			    			"<table width='100%'>"+
			    				"<tr><td>SubTotal:</td><td align='right'>"+totalPago+"</td></tr>"+
			    				"<tr><td>Impuesto:</td><td align='right'>"+impuesto+"</td></tr>"+
			    				"<tr><td></td><td align='right'>--------------</td></tr>"+
			    				"<tr><td>Importe Total:</td><td align='right'style='background:#75BE5C;font-weight:bold;'>"+"USD "+importeTotal+"</td></tr>"+
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
			    	"<thead style='font-weight: bold;'>"+etiqueta[52]+"</thead>"+
			    	"<tr style='background:rgba(0,0,0,0.1);border:1px solid black;'>"+
				    	"<td style='border:1px solid black;' align='center'>Categoria</td>"+
			    		"<td style='border:1px solid black;' align='center'>Simples </td>"+
			    		"<td style='border:1px solid black;' align='center'>P.U. Simple</td>"+
			    		"<td style='border:1px solid black;' align='center'>Dobles</td>"+
			    		"<td style='border:1px solid black;' align='center'>P.U. Doble</td>"+
			    		"<td style='border:1px solid black;' align='center'>Triples</td>"+
			    		"<td style='border:1px solid black;' align='center'>P.U. Triple</td>"+
			    		"<td style='border:1px solid black;' align='center'>Cama Adicional</td>"+
			    		"<td style='border:1px solid black;' align='center'>Total</td>"+
			    	"</tr>"+
			    	"<tr style='border:1px solid black;'>"+
			    		"<td style='border:1px solid black;' align='center'>"+oReservaPCH.getCategoria()+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+oReservaPCH.getnNroPersonasSimple()+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+df.format(oReservaPCH.getnPrecioTotalSimple().doubleValue()/oReservaPCH.getnNroPersonasSimple())+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+oReservaPCH.getnNroPersonasDoble()/2+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+df.format(oReservaPCH.getnPrecioTotalDoble().doubleValue()/(oReservaPCH.getnNroPersonasDoble()/2))+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+oReservaPCH.getnNroPersonasTriple()/3+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+df.format(oReservaPCH.getnPrecioTotalTriple().doubleValue()/(oReservaPCH.getnNroPersonasTriple()/3))+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+oReservaPCH.getPrecioCamaAdicional()+"</td>"+
			    		"<td style='border:1px solid black;color:#1A5276;' align='right'>"+df.format(oReservaPCH.getnPrecioTotalSimple().doubleValue()+oReservaPCH.getnPrecioTotalDoble().doubleValue()+oReservaPCH.getnPrecioTotalTriple().doubleValue())+"</td>"+
			    	"</tr>"+
			    "</table>"+
			    "<br/>";
				for(CDestinoConHoteles DCH:oReservaPCH.getListaCategoriaDestinosHoteles())
				{
					hotel+="<table width='40%' style='border:1px solid rgba(0,0,0,0.1);border-collapse: collapse;'>"+
					    	"<thead style='font-weight: bold;'>"+DCH.getoDestino().getcDestino()+"</thead>";
					for(CHotel hoteles:DCH.getListaDestinosHoteles())
					{
						hotel+="<tr style='background:rgba(0,0,0,0.1);border:1px solid black;'>"+
						    		"<td style='border:1px solid black;' align='left'>"+
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
			for(int i=0;i<fechasAlternas.size();i++)
			{
				fechas+=
						"<tr align='center'>"+
				           "<td style='color:#F7653A;font-weight:bold;'>"+fechasAlternas.get(i)+"</td>"+
				        "</tr>";
			}
		/**********************************************/
		/**Se obtienen los datos de los pasajeros**/
		String pasajeros="";
		for(CPasajero pax:listaPasajeros)
		{
			if(!pax.isSelectPasajero() || pax.isEsEdit())break;
			pasajeros+=
						"<tr style='border:1px solid black;'>"+
				    		"<td style='border:1px solid black;' align='center'>"+pax.getTipoDocumento()+"</td>"+
				    		"<td style='border:1px solid black;' align='center'>"+pax.getcNroDoc()+"</td>"+
				    		"<td style='border:1px solid black;' align='center'>"+pax.getcNombres()+"</td>"+
				    		"<td style='border:1px solid black;' align='center'>"+pax.getcApellidos()+"</td>"+
				    		"<td style='border:1px solid black;' align='center'>"+pax.getcSexo()+"</td>"+
				    		"<td style='border:1px solid black;' align='center'>"+pax.getnEdad()+"</td>"+
				    		"<td style='border:1px solid black;' align='center'>"+pax.getNombrePais()+"</td>"+
				    	"</tr>";
		}
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
						    		"<td style='border:1px solid black;' align='left'>"+servicio.getcServicioIndioma1()+"</td>";
							if(servicio.getcRestriccionNum()==0 && servicio.getcRestriccionYesNo()==0)
						    		servicios+="<td style='border:1px solid black;' align='center'>"+servicio.getSelectOpcion()+"</td>";
							else
								servicios+="<td style='border:1px solid black;' align='center'>-</td>";
						    servicios+="<td style='border:1px solid black;' align='right'>"+servicio.getnPrecioServicio().toString()+"</td>"+
						    		"<td style='border:1px solid black;' align='center'>"+servicio.getOpcionValue()+"</td>"+
						    		"<td style='border:1px solid black;color:#1A5276;' align='right'>"+servicio.getPrecioTotalServicio()+"</td>"+
						    	"</tr>";
				}
			}
		String[] actividades=obtenerHtmlActividades(reserva.getoPaquete().getListaActividades(), reserva);
		/******************************************/
		String cuponHtml="";
		if(reserva.getoCupon().isOkCupon())
			cuponHtml=obtenerHtmlCupon(reserva.getoCupon());
		/*****************************************/
		String mensajeHTML=
				"<html>"+
					"<head></head>"+
					"<body>"+
						"<div style='width:100%;background:rgb(242, 242, 242);'>"+
							"<table border='0' width='100%' align='center' style='background:rgb(242, 242, 242);'>"+
								"<tr>"+
							      "<td align='center' width='20%' style='padding:8px 20px 8px 20px;'>"+
							      	"<a href='"+etiqueta[212]+"' style='text-decoration:none;'>"+
							      		"<img src='"+etiqueta[211]+"' width='90' height='60' border='0' />"+
							      	"</a>"+
							      "</td>"+
							      "<td style='color:black;font-size:17px;font-weight:bold;' align='left' width='80%'>"+reserva.getoPaquete().getcTituloIdioma1()+"</td>"+
							    "</tr>"+
							"</table>"+
							"<div style='padding:20px;background:white;border:20px solid rgb(242, 242, 242);'>"+
								"<p style='font-weight:bold;font-size:18px;margin:5px 0 6px 0;'>"+fechaActual+"</p>"+
								"<div style='background:#1A5276;border-radius:5px;width:25%;padding:1px 0 1px 0;' align='center'>"+
									"<p style='font-weight:bold;'>Codigo de Reserva: <strong style='color:white;'>"+reserva.getcReservaCod()+"</strong></p>"+
								"</div>"+
								"<br/>"+
								"<div>"+
									"<p><strong>"+reserva.getcContacto()+"."+"</strong> Efectuo un pago.<br/>"+"El pago fue efectuado por la reserva del paquete <strong style='color:#F7653A;text-transform: lowercase;'>"+reserva.getoPaquete().getcTituloIdioma1()+"</strong>"+ 
									" con la siguiente fecha principal: <strong style='color:#F7653A;'>"+fechaInicio+"</strong>, y "+ 
									"<strong style='color:#F7653A;'>"+fechaFin+"</strong>  Con:<br/>Las siguientes fechas alternas:</p>"+
									
									"<table width='40%'>"+
										fechas+
								    "</table>"+
								    "<br/>"+
								    "<div style='background:#1A5276;border-radius:5px;width:25%;padding:1px 0 1px 0;' align='center'>"+
										"<p style='font-weight:bold;'>Id Transaccion: <strong style='color:white;'>"+codTransaccion+"</strong></p>"+
									"</div>"+
								    "<br/>"+
								    "<p>Las siguientes tablas muestran informacion de su reserva:</p>"+
								    "<table width='100%' style='border:1px solid rgba(0,0,0,0.1);border-collapse: collapse;'>"+
								    	"<thead style='font-weight: bold;'>Lista de Pasajeros</thead>"+
								    	"<tr style='background:rgba(0,0,0,0.1);border:1px solid black;'>"+
								    		"<td style='border:1px solid black;' align='center'>Doc.</td>"+
								    		"<td style='border:1px solid black;' align='center'>Nro</td>"+
								    		"<td style='border:1px solid black;' align='center'>Nombre</td>"+
								    		"<td style='border:1px solid black;' align='center'>Apellido</td>"+
								    		"<td style='border:1px solid black;' align='center'>Sexo</td>"+
								    		"<td style='border:1px solid black;' align='center'>Edad</td>"+
								    		"<td style='border:1px solid black;' align='center'>País</td>"+
								    	"</tr>"+
								    	pasajeros+
								    "</table>"+
								    "<br/>"+
								    hotel+
								    "<table width='100%' style='border:1px solid rgba(0,0,0,0.1);border-collapse: collapse;'>"+
								    	"<tr style='background:rgba(0,0,0,0.1);border:1px solid black;'>"+
								    		"<td style='border:1px solid black;' align='center'>Descripcion</td>"+
								    		"<td style='border:1px solid black;' align='center'>Sub-Servicio</td>"+
								    		"<td style='border:1px solid black;' align='center'>Precio Unitario (USD)</td>"+
								    		"<td style='border:1px solid black;' align='center'>Cantidad</td>"+
								    		"<td style='border:1px solid black;' align='center'>Precio Total (USD)</td>"+
								    	"</tr>"+
								    	"<tr style='border:1px solid black;'>"+
								    		"<td style='border:1px solid black;' align='left'>"+reserva.getoPaquete().getcTituloIdioma1()+"</td>"+
								    		"<td style='border:1px solid black;' align='center'>-</td>"+
								    		"<td style='border:1px solid black;' align='right'>"+precioUniPaquete+"</td>"+
								    		"<td style='border:1px solid black;' align='center'>"+reserva.getnNroPersonas()+"</td>"+
								    		"<td style='border:1px solid black;color:#1A5276;' align='right'>"+reserva.getnPrecioPaquetePersona().toString()+"</td>"+
								    	"</tr>"+
								    	servicios+
								    	actividades[1]+
								    "</table>"+
								    "<table width='100%' style='background:rgba(0,0,0,0.02);border:1px solid black;'>"+
								    	"<tr>"+
								    		"<td width='80%'></td>"+
								    		"<td width='20%'>"+
								    			"<table width='100%'>"+
								    				"<tr><td>Total</td><td style='color:#1A5276;' align='right'>"+totalPago+"</td></tr>"+
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
							        "<strong>"+etiqueta[213]+"</strong>"+
								"</div>"+
								"<div style='background:gray;"+
									"color:white;"+
									"font-size:15px;font-weight:bold;width:100%;height:60px;'>"+
									"<p style='padding:20px 0 0 30%;'>Copyright © "+annio+" eddyonsoft.com - Todos los Derechos Reservados</p>"+
								"</div>"+
							  "</div>"+
						"</body>"+
				"</html>";
		return sendMailToEmpresa(reserva.getcEmail(),titulo,mensajeHTML,imagenes);
	}
	public boolean enviarCorreoPagoReserva(String titulo,String[] etiqueta,String namePaquete,String mail,String contacto,
			String codReserva,String porcentaje,String transac,String urlPdf) throws IOException, DocumentException
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
		if(porcentaje.equals(etiqueta[102]))
			mensaje="<p>"+etiqueta[160]+"</p>";
		String mensajeHTML=
				"<html>"+
					"<head></head>"+
					"<body>"+
						"<div style='width:100%;background:rgb(242, 242, 242);'>"+
							"<table border='0' width='100%' align='center' style='background:rgb(242, 242, 242);'>"+
								"<tr>"+
							      "<td align='center' width='20%' style='padding:8px 20px 8px 20px;'>"+
							      	"<a href='"+etiqueta[212]+"' style='text-decoration:none;'>"+
							      		"<img src='"+etiqueta[211]+"' width='90' height='60' border='0' />"+
							      	"</a>"+
							      "</td>"+
							      "<td style='color:black;font-size:17px;font-weight:bold;' align='left' width='80%'>"+namePaquete+"</td>"+
							    "</tr>"+
							"</table>"+
							"<div style='padding:20px;background:white;border:20px solid rgb(242, 242, 242);'>"+
								"<p style='font-weight:bold;font-size:18px;margin:5px 0 6px 0;'>"+fechaActual+"</p>"+
								"<div style='background:#1A5276;border-radius:5px;width:25%;padding:1px 0 1px 0;' align='center'>"+
									"<p style='font-weight:bold;'>"+etiqueta[118]+" <strong style='color:white;'>"+codReserva+"</strong></p>"+
								"</div>"+
								"<br/>"+
								"<table border='0'>"+
									"<tr>"+
								      "<td><p><strong>"+etiqueta[125]+" </strong>"+contacto+"."+
								      "</p></td>"+
								    "</tr>"+
								"</table>"+
								"<div align='center'>"+
									"<p style='font-size:18px;font-weight:bold;'>"+etiqueta[198]+" <strong>"+porcentaje+".</strong></p>"+
								"</div>"+
								"<div style='background:#F7653A;border-radius:20px;width:25%;' align='center'>"+
									"<p style='font-weight:bold;'>"+etiqueta[123]+" <strong style='color:white;'>"+transac+"</strong></p>"+
								"</div>"+
									mensaje+
								    "<p>"+etiqueta[146]+"</p>"+
								    "<p>"+etiqueta[147]+"</p>"+
								    "<table width='80%'>"+
								    	"<tr align='center'>"+
								    		"<td><img width='60' height='60' src='http://www.panesolution.com/images/widget/1438789785062.png'/><p>"+etiqueta[148]+" <strong><a href='"+etiqueta[214]+"'>"+etiqueta[152]+"</a></strong></p></td>"+
								    		"<td><img width='60' height='60' src='http://img.webme.com/pic/s/simutrans-hispano/youtubeglass.png'/><p>"+etiqueta[149]+" <strong><a href='"+etiqueta[215]+"'>"+etiqueta[152]+"</a></strong></p></td>"+
								    	"</tr>"+
								    	"<tr align='center'>"+
								    		"<td><img width='60' height='60' src='http://www.asm.wisc.edu/wp-content/uploads/2009/08/twitter-icon2-150x150.jpg'/><p>"+etiqueta[150]+" <strong><a href='"+etiqueta[216]+"'>"+etiqueta[152]+"</a></strong></p></td>"+
								    		"<td><img width='60' height='60' src='http://androidspain.es/wp-content/uploads/2013/03/Icono-Whatsapp.png'/><p>"+etiqueta[151]+"</p></td>"+
								    	"</tr>"+
								    "</table>"+
								"</div>"+
								  "<p style='font-size:11px;'>"+etiqueta[153]+" <strong>"+etiqueta[154]+"</strong>"+etiqueta[155]+"</p>"+
								  "<p style='font-size:11px;'>"+etiqueta[196]+"</p>"+
								  "<br/>"+
							        "<strong>"+etiqueta[213]+"</strong>"+
								"</div>"+
								"<div style='background:gray;"+
									"color:white;"+
									"font-size:15px;font-weight:bold;width:100%;height:60px;'>"+
									"<p style='padding:20px 0 0 30%;'>Copyright © "+annio+" "+etiqueta[157]+"</p>"+
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
					"<head></head>"+
					"<body>"+
						"<div style='width:100%;background:rgb(242, 242, 242);'>"+
							"<table border='0' width='100%' align='center' style='background:rgb(242, 242, 242);'>"+
								"<tr>"+
							      "<td align='center' width='20%' style='padding:8px 20px 8px 20px;'>"+
							      	"<a href='"+etiqueta[212]+"' style='text-decoration:none;'>"+
							      		"<img src='"+etiqueta[211]+"' width='90' height='60' border='0' />"+
							      	"</a>"+
							      "</td>"+
							      "<td style='color:black;font-size:17px;font-weight:bold;' align='left' width='80%'>"+namePaquete+"</td>"+
							    "</tr>"+
							"</table>"+
							"<div style='padding:20px;background:white;border:20px solid rgb(242, 242, 242);'>"+
								"<p style='font-weight:bold;font-size:18px;margin:5px 0 6px 0;'>"+fechaActual+"</p>"+
								"<div style='background:#1A5276;border-radius:5px;width:25%;padding:1px 0 1px 0;' align='center'>"+
									"<p style='font-weight:bold;'>Codigo de Reserva: <strong style='color:white;'>"+codReserva+"</strong></p>"+
								"</div>"+
								"<br/>"+
								"<table border='0'>"+
									"<tr>"+
								      "<td><p><strong>El Pasajero, </strong>"+contacto+"."+
								      "</p></td>"+
								    "</tr>"+
								"</table>"+
								"<div align='center'>"+
									"<p style='font-size:18px;font-weight:bold;'>Efectuo su pago correctamente al <strong style='color:blue;'>"+porcentaje+".</strong><br/>Su codigo de trasaccion es:<strong style='color:blue;'>"+transac+"</strong><br/></p>"+
								"</div>"+
								"<div>"+
								  "<br/>"+
							        "<strong>"+etiqueta[213]+"</strong>"+
								"</div>"+
								"<div style='background:gray;"+
									"color:white;"+
									"font-size:15px;font-weight:bold;width:100%;height:60px;'>"+
									"<p style='padding:20px 0 0 30%;'>Copyright © "+annio+" eddyonsoft.com - Todos los Derechos Reservados</p>"+
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
									"<td>USUARIO: </td>"+
									"<td>"+usuario+"</td>"+
								"</tr>"+
								"<tr>"+
									"<td>PASSWORD: </td>"+
									"<td>"+contrasenia+"</td>"+
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
		if(fechasAlternas!=null)
		{
			for(String fecha:fechasAlternas)
			{
				fechas+=
						"<tr align='center'>"+
				           "<td style='color:#F7653A;font-weight:bold;'>"+fecha+"</td>"+
				        "</tr>";
			}
		}
		return fechas;
	}
	public String[] obtenerHtmlPasajeros(ArrayList<CPasajero> listaPasajeros,String[] etiqueta)
	{
		String[] pasajeros=new String[2];
		pasajeros[0]="";
		pasajeros[1]="";
		for(CPasajero p:listaPasajeros)
		{
			if(!p.isSelectPasajero() || p.isEsEdit())break;
			pasajeros[0]+=
						"<tr style='border:1px solid black;'>"+
				    		"<td style='border:1px solid black;' align='center'>"+p.getTipoDocumento()+"</td>"+
				    		"<td style='border:1px solid black;' align='center'>"+p.getcNroDoc()+"</td>"+
				    		"<td style='border:1px solid black;' align='center'>"+p.getcNombres()+"</td>"+
				    		"<td style='border:1px solid black;' align='center'>"+p.getcApellidos()+"</td>"+
				    		"<td style='border:1px solid black;' align='center'>"+p.getcSexo()+"</td>"+
				    		"<td style='border:1px solid black;' align='center'>"+p.getnEdad()+"</td>"+
				    		"<td style='border:1px solid black;' align='center'>"+p.getNombrePais()+"</td>"+
				    	"</tr>";
			pasajeros[1]+=
					"<tr style='border:1px solid black;'>"+
			    		"<td style='border:1px solid black;' align='center'>"+p.getTipoDocumento()+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+p.getcNroDoc()+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+p.getcNombres()+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+p.getcApellidos()+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+p.getcSexo()+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+p.getnEdad()+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+p.getNombrePais()+"</td>"+
			    	"</tr>";
		}
		return pasajeros;
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
						    		"<td style='border:1px solid black;' align='left'>"+s.getServicio()+"</td>";
					servicios[1]+=
							"<tr style='border:1px solid black;'>"+
						    		"<td style='border:1px solid black;' align='left'>"+s.getcServicioIndioma1()+"</td>";
							if(s.getcRestriccionNum()==0 && s.getcRestriccionYesNo()==0)
							{
						    	servicios[0]+="<td style='border:1px solid black;' align='center'>"+s.getSelectOpcion()+"</td>";
						    	servicios[1]+="<td style='border:1px solid black;' align='center'>"+s.getSelectOpcion()+"</td>";
							}
						    else
							{
						    	servicios[0]+="<td style='border:1px solid black;' align='center'>-</td>";
						    	servicios[1]+="<td style='border:1px solid black;' align='center'>-</td>";
							}
						    servicios[0]+="<td style='border:1px solid black;' align='right'>"+s.getnPrecioServicio().toString()+"</td>"+
						    		"<td style='border:1px solid black;' align='center'>"+s.getOpcionValue()+"</td>"+
						    		"<td style='border:1px solid black;color:#1A5276;' align='right'>"+s.getPrecioTotalServicio()+"</td>"+
						    	"</tr>";
						    servicios[1]+="<td style='border:1px solid black;' align='right'>"+s.getnPrecioServicio().toString()+"</td>"+
						    		"<td style='border:1px solid black;' align='center'>"+s.getOpcionValue()+"</td>"+
						    		"<td style='border:1px solid black;color:#1A5276;' align='right'>"+s.getPrecioTotalServicio()+"</td>"+
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
						    		"<td style='border:1px solid black;' align='left'>"+acti.getNombreActividad()+"</td>"+
						    		"<td style='border:1px solid black;' align='center'>-</td>"+
						    		"<td style='border:1px solid black;' align='right'>"+df.format(acti.getnPrecioActividad().doubleValue())+"</td>"+
						    		"<td style='border:1px solid black;' align='center'>"+reserva.getnNroPersonas()+"</td>"+
						    		"<td style='border:1px solid black;color:#1A5276;' align='right'>"+df.format((acti.getnPrecioActividad().doubleValue()*reserva.getnNroPersonas()))+"</td>"+
						    	"</tr>";
					actividad[1]+=
							"<tr style='border:1px solid black;'>"+
						    		"<td style='border:1px solid black;' align='left'>"+acti.getcActividadIdioma1()+"</td>"+
						    		"<td style='border:1px solid black;' align='center'>-</td>"+
						    		"<td style='border:1px solid black;' align='right'>"+df.format(acti.getnPrecioActividad().doubleValue())+"</td>"+
						    		"<td style='border:1px solid black;' align='center'>"+reserva.getnNroPersonas()+"</td>"+
						    		"<td style='border:1px solid black;color:#1A5276;' align='right'>"+df.format((acti.getnPrecioActividad().doubleValue()*reserva.getnNroPersonas()))+"</td>"+
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
			    	"<thead style='font-weight: bold;'>"+etiqueta[52]+"</thead>"+
			    	"<tr style='background:rgba(0,0,0,0.1);border:1px solid black;'>"+
			    		"<td style='border:1px solid black;' align='center'>"+etiqueta[51]+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+etiqueta[201]+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+etiqueta[202]+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+etiqueta[203]+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+etiqueta[204]+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+etiqueta[205]+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+etiqueta[206]+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+etiqueta[233]+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+etiqueta[145]+"</td>"+
			    	"</tr>"+
			    	"<tr style='border:1px solid black;'>"+
			    		"<td style='border:1px solid black;' align='center'>"+oReservaPCH.getCategoria()+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+oReservaPCH.getnNroPersonasSimple()+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+df.format(oReservaPCH.getnPrecioTotalSimple().doubleValue()/oReservaPCH.getnNroPersonasSimple())+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+oReservaPCH.getnNroPersonasDoble()/2+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+df.format(oReservaPCH.getnPrecioTotalDoble().doubleValue()/(oReservaPCH.getnNroPersonasDoble()/2))+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+oReservaPCH.getnNroPersonasTriple()/3+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+df.format(oReservaPCH.getnPrecioTotalTriple().doubleValue()/(oReservaPCH.getnNroPersonasTriple()/3))+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+oReservaPCH.getPrecioCamaAdicional()+"</td>"+
			    		"<td style='border:1px solid black;color:#1A5276;' align='right'>"+df.format(oReservaPCH.getnPrecioTotalSimple().doubleValue()+oReservaPCH.getnPrecioTotalDoble().doubleValue()+oReservaPCH.getnPrecioTotalTriple().doubleValue())+"</td>"+
			    	"</tr>"+
			    "</table>"+
			    "<br/>";
			hotel[1]="<table width='100%' style='border:1px solid rgba(0,0,0,0.1);border-collapse: collapse;'>"+
			    	"<thead style='font-weight: bold;'></thead>"+
			    	"<tr style='background:rgba(0,0,0,0.1);border:1px solid black;'>"+
			    		"<td style='border:1px solid black;' align='center'>Categoria</td>"+
			    		"<td style='border:1px solid black;' align='center'>Simples </td>"+
			    		"<td style='border:1px solid black;' align='center'>P.U. Simple</td>"+
			    		"<td style='border:1px solid black;' align='center'>Dobles</td>"+
			    		"<td style='border:1px solid black;' align='center'>P.U. Doble</td>"+
			    		"<td style='border:1px solid black;' align='center'>Triples</td>"+
			    		"<td style='border:1px solid black;' align='center'>P.U. Triple</td>"+
			    		"<td style='border:1px solid black;' align='center'>Cama Adicional</td>"+
			    		"<td style='border:1px solid black;' align='center'>Total</td>"+
			    	"</tr>"+
			    	"<tr style='border:1px solid black;'>"+
			    		"<td style='border:1px solid black;' align='center'>"+oReservaPCH.getCategoria()+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+oReservaPCH.getnNroPersonasSimple()+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+df.format(oReservaPCH.getnPrecioTotalSimple().doubleValue()/oReservaPCH.getnNroPersonasSimple())+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+oReservaPCH.getnNroPersonasDoble()/2+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+df.format(oReservaPCH.getnPrecioTotalDoble().doubleValue()/(oReservaPCH.getnNroPersonasDoble()/2))+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+oReservaPCH.getnNroPersonasTriple()/3+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+df.format(oReservaPCH.getnPrecioTotalTriple().doubleValue()/(oReservaPCH.getnNroPersonasTriple()/3))+"</td>"+
			    		"<td style='border:1px solid black;' align='center'>"+oReservaPCH.getPrecioCamaAdicional()+"</td>"+
			    		"<td style='border:1px solid black;color:#1A5276;' align='right'>"+df.format(oReservaPCH.getnPrecioTotalSimple().doubleValue()+oReservaPCH.getnPrecioTotalDoble().doubleValue()+oReservaPCH.getnPrecioTotalTriple().doubleValue())+"</td>"+
			    	"</tr>"+
			    "</table>"+
			    "<br/>";
				for(CDestinoConHoteles DCH:oReservaPCH.getListaCategoriaDestinosHoteles())
				{
					hotel[0]+="<table width='40%' style='border:1px solid rgba(0,0,0,0.1);border-collapse: collapse;'>"+
					    	"<thead style='font-weight: bold;'>"+DCH.getoDestino().getcDestino()+"</thead>";
					hotel[1]+="<table width='40%' style='border:1px solid rgba(0,0,0,0.1);border-collapse: collapse;'>"+
					    	"<thead style='font-weight: bold;'>"+DCH.getoDestino().getcDestino()+"</thead>";
					for(CHotel hoteles:DCH.getListaDestinosHoteles())
					{
						hotel[0]+="<tr style='background:rgba(0,0,0,0.1);border:1px solid black;'>"+
						    		"<td style='border:1px solid black;' align='left'>"+
						    			"<a href='"+hoteles.getcUrl()+"'>"+hoteles.getcHotel()+"</a>"+
						    		"</td>"+
						    	"</tr>";
						hotel[1]+="<tr style='background:rgba(0,0,0,0.1);border:1px solid black;'>"+
					    		"<td style='border:1px solid black;' align='left'>"+
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
									"<td border='0' style='color:rgb(242, 242, 242);font-size:12px;font-weight:bold;'>"+
										"Código Cupon:"+
									"</td>"+
									"<td border='0'>"+
										"<strong style='color:white;font-size:12px;'>"+cupon.getcCupon()+"</strong>"+
									"</td>"+
								"</tr>"+
								"<tr border='0'>"+
									"<td border='0' style='color:rgb(242, 242, 242);font-size:12px;font-weight:bold;'>"+
										"Descuento:"+
									"</td>"+
									"<td border='0'>"+
										"<strong style='color:white;font-size:12px;'>"+cupon.getnPorcentajeDcto()+" %</strong>"+
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
}
