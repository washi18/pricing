package com.pricing.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Executions;

public class Util {
	
	public static final String separator = System.getProperty("file.separator");//Get de system separator
	
	public static boolean uploadFile(Media media) {
		System.out.println("aqui estoy="+separator);
		System.out.println("Esta es la ruta=>"+getPathDispActual());
		//return saveFile(media, "E:/SoftwareDevelopment/workspace/GPS/WebContent/image/unidades/");
		return saveFile(media,getPathDispActual());
	}
	public static boolean uploadFileAux(Media media)
	{
		return saveFile(media,getPathAuxDisp());
	}
	//Gets the path of the current web application
	public static String getPathDispActual(){
		return Executions.getCurrent().getDesktop().getWebApp().getRealPath(separator)+separator+"DisponibilidadActual"+separator;
	}
	public static String getPathDispSig(){
		return Executions.getCurrent().getDesktop().getWebApp().getRealPath(separator)+separator+"DisponibilidadSig"+separator;
	}
	//Obtiene la ruta de las imagenes
	public static String getPathIMG()
	{
		return Executions.getCurrent().getDesktop().getWebApp().getRealPath(separator)+separator+"img"+separator;
	}
	//Obtiene la ruta del archivo de la carpeta auxiliar
	public static String getPathAuxDisp()
	{
		return Executions.getCurrent().getDesktop().getWebApp().getRealPath(separator)+separator+"auxDisp"+separator;
	}
	//Obtiene la ruta de la carpeta donde estan los pdfs de reserva
	public static String getPathReservas()
	{
		return Executions.getCurrent().getDesktop().getWebApp().getRealPath(separator)+separator+"Reservas"+separator;
	}
	//save file PDF
	public static boolean saveFile(Media media, String path)
	{
		boolean uploaded = false;
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try {
			InputStream ins = media.getStreamData();
			in = new BufferedInputStream(ins);

			String fileName = media.getName();
			File arc = new File(path + fileName);
			OutputStream aout = new FileOutputStream(arc);
			out = new BufferedOutputStream(aout);

			byte buffer[] = new byte[1024];
			int ch = in.read(buffer);
			while(ch != -1){
				out.write(buffer, 0, ch);
				ch = in.read(buffer);
			}
			uploaded = true;
			if(uploaded)System.out.println("Se llego a cargar el PDF");
		}catch (IOException ie) {
			throw new RuntimeException(ie);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
				try {
					if(out != null)
						out.close();
					if(in != null)
						in.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
		}
		return uploaded;
	}
}

