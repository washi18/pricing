package com.pricing.paypal;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.zkoss.zk.ui.Sessions;

import com.pricing.dao.CEtiquetaDAO;
import com.pricing.dao.CPaqueteDAO;

public class ExpressCheckoutParcialServlet extends HttpServlet implements javax.servlet.Servlet
{
	private DecimalFormat df;
	private DecimalFormatSymbols simbolos;
	private ExpressCheckout paypal;
	/***************************/
	private CEtiquetaDAO etiquetaDao;
	private String[] etiqueta;
	/***************************/
	HttpSession seshttp;
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	 protected void processRequest(HttpServletRequest request,HttpServletResponse response)
	 	throws ServletException,IOException
	 {
		 paypal=new ExpressCheckout();
		 seshttp=request.getSession();
		 /*******************************/
			simbolos= new DecimalFormatSymbols();
			simbolos.setDecimalSeparator('.');
			df=new DecimalFormat("########0.00",simbolos);
		/********************************/
		 response.setContentType("text/html;charset=UTF-8");
		 String montoParcial=request.getParameter("Monto");
		 String codReserva=request.getParameter("codReserva");
		 String codPaquete=request.getParameter("codPaquete");
		 String namePaquete=request.getParameter("namePaquete");
		 String mail=request.getParameter("mail");
		 String contacto=request.getParameter("contacto");
		 String language=request.getParameter("language");
		 String impuestoPaypal=request.getParameter("impuestoPaypal");
		 //==OBTENER EL PAQUETE==
		 CPaqueteDAO paqueteDao=new CPaqueteDAO();
		 paqueteDao.asignarPaquete(paqueteDao.recuperarPaqueteBD(codPaquete));
		 //======================
		 iniciarEtiquetas(language);
		 String textoParcial="";
		 if(paqueteDao.getoPaquete().isbModoPorcentual())
			 textoParcial=paqueteDao.getoPaquete().getnPorcentajeCobro()+" %";
		 else
			 textoParcial=etiqueta[102];
		 
		 String tax=df.format((Double.parseDouble(montoParcial)*(Double.parseDouble(impuestoPaypal)/100)));
		 String montoParcialConImpuestoPaypal=df.format(Double.parseDouble(montoParcial)+Double.parseDouble(tax));
		 
		 String[] SECResult=paypal.setExpressCheckoutTest(montoParcialConImpuestoPaypal,namePaquete);
		 
		 String url=SECResult[0];
		 seshttp.setAttribute("token",SECResult[1]);
		 seshttp.setAttribute("formaPago",1);
		 seshttp.setAttribute("codReserva", codReserva);
		 seshttp.setAttribute("codPaquete", codPaquete);
		 seshttp.setAttribute("porcentajePago","1");
		 seshttp.setAttribute("mail", mail);
		 seshttp.setAttribute("contacto", contacto);
		 seshttp.setAttribute("namePaquete",namePaquete);
		 seshttp.setAttribute("etiqueta",etiqueta);
		 try(PrintWriter out=response.getWriter())
		 {
			 out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>");
			 out.println("<html>");
			 out.println("<head>");
			 out.println("<meta http-equiv='Content-Type' content='text/html; charset=ISO-8859-1'>");
			 out.println("<title>PAGO RESERVA</title>");
			 out.println("</head>");
			 out.println("<body>");
			 	out.println("<div style='height:100vh;width:100%;");
			 				out.println("display: -webkit-flex;");
			 				out.println("display: -moz-flex;");
			 				out.println("display: -ms-flex;");
			 				out.println("display: -o-flex;");
			 				out.println("display: flex;");
			 				out.println("-webkit-justify-content:center;");
			 				out.println("-moz-justify-content:center;");
			 				out.println("-ms-justify-content:center;");
			 				out.println("-o-justify-content:center;");
			 				out.println("justify-content:center;");
			 				out.println("-webkit-align-items:center;");
			 				out.println("-moz-align-items:center;");
			 				out.println("-ms-align-items:center;");
			 				out.println("-o-align-items:center;");
			 				out.println("align-items:center;'>");
			 		out.println("<div align='center' style='height:55%;width:40%;");
			 								   out.println("border-radius:20px;");
			 								   out.println("padding:10px;");
			 								   out.println("border:2px solid #3BB710;'>");
			 			out.println("<br/>");
			 			out.println("<div style='background:#3BB710;border-radius:10px;padding:5px;'>");
			 				out.println("<h1 style='color:white;font-size:20px;'>"+namePaquete+"</h1>");
			 			out.println("</div>");
			 			out.println("<br/>");
			 			out.println("<p style='font-weight:bold;font-size:21px;background:#F7653A;width:50%;border-radius:5px;'>"+etiqueta[118]+" <strong style='font-weight:bold;color:white;'>"+codReserva+"</strong></p>");
			 			out.println("<div style='width:80%;");
			 						out.println("display: -webkit-flex;");
			 						out.println("display: -moz-flex;");
			 						out.println("display: -ms-flex;");
			 						out.println("display: -o-flex;");
			 						out.println("display: flex;'>");
			 				out.println("<div style='width:70%;font-weight:bold;font-size:20px;' align='right'>");
			 					out.println(etiqueta[99]+" ("+textoParcial+"): USD");
			 				out.println("</div>");
			 				out.println("<div style='width:30%;font-weight:bold;font-size:20px;' align='right'>");
			 					out.println(montoParcial);
			 				out.println("</div>");
			 			out.println("</div>");
			 			out.println("<div style='width:80%;");
			 						out.println("display: -webkit-flex;");
			 						out.println("display: -moz-flex;");
			 						out.println("display: -ms-flex;");
			 						out.println("display: -o-flex;");
			 						out.println("display: flex;'>");
			 				out.println("<div style='width:70%;font-weight:bold;font-size:20px;' align='right'>");
			 					out.println(etiqueta[100]+": USD");
			 				out.println("</div>");
			 				out.println("<div style='width:30%;font-weight:bold;font-size:20px;' align='right'>");
			 					out.println(tax);
			 				out.println("</div>");
			 			out.println("</div>");
			 			out.println("<div align='right' style='width:80%;'>");
			 				out.println("----------------------------------------------------------------");
			 			out.println("</div>");
			 			out.println("<div style='width:80%;");
			 						out.println("display: -webkit-flex;");
			 						out.println("display: -moz-flex;");
			 						out.println("display: -ms-flex;");
			 						out.println("display: -o-flex;");
			 						out.println("display: flex;'>");
			 				out.println("<div style='width:70%;font-weight:bold;color:blue;font-size:20px;' align='right'>");
			 					out.println(etiqueta[101]+" ("+textoParcial+"): USD");
			 				out.println("</div>");
			 				out.println("<div style='width:30%;font-weight:bold;font-size:20px;color:blue;' align='right'>");
			 					out.println(montoParcialConImpuestoPaypal);
			 				out.println("</div>");
			 			out.println("</div>");
			 			out.println("<br/>");
			 			out.println("<form action='"+url+"' method='POST'>");
			 				out.println("<input type='image' name='submit' border='0'");
			  				out.println("src='https://www.paypal.com/en_US/i/btn/btn_xpressCheckout.gif'/>");
			 			out.println("</form>");
			 		out.println("</div>");	
			 	out.println("</div>");
			 out.println("</body>");
			 out.println("</html>");
		 }
	 }
	 @Override
	 public void doGet(HttpServletRequest request,HttpServletResponse response)
			 throws ServletException,IOException
	 {
		 processRequest(request, response);
	 }
	 @Override
	 public void doPost(HttpServletRequest request,HttpServletResponse response)
			 throws ServletException,IOException
	 {
		 processRequest(request, response);
	 }
	 public void iniciarEtiquetas(String language)
	{
			etiquetaDao=new CEtiquetaDAO();
			etiquetaDao.asignarEtiquetaIdiomas(etiquetaDao.recuperarEtiquetasBD());
			etiqueta=new String[etiquetaDao.getIdioma().getIdioma1().length];//Se asigna el tamaño de etiqueta
			
			if(language.equals("es-ES"))
				etiqueta=etiquetaDao.getIdioma().getIdioma1();
			else if(language.equals("pt-BR") || language.equals("pt-PT"))
				etiqueta=etiquetaDao.getIdioma().getIdioma3();
			else
				etiqueta=etiquetaDao.getIdioma().getIdioma2();
	}
}
