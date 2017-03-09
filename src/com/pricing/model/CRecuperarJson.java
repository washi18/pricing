package com.pricing.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class CRecuperarJson {
	private CPaquete paqueteJson;
	
	private static String leerUrl(String urlString) throws Exception {
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
	public void enviarJson(){
		
	}
	public void traerJson(){
		try {

		Gson migs=new Gson();
		String jsoncadena=leerUrl("http://www.infocusco.com.pe/");

		//creamos una propiedad del gson jsonParser

		JsonParser jsonParser = new JsonParser();

		//Obtenemos el string json en un arreglo json

		JsonArray userArray = jsonParser.parse(jsoncadena).getAsJsonArray();

		      //

		      for ( JsonElement aUser : userArray )

		      {
		    	  	System.out.println(" "+aUser);
//		        CProyecto aPe = migs.fromJson(aUser, CProyecto.class);
//
//		        ListProy.add(aPe);

		      }

		     

		} catch (Exception e)

		{

		// TODO: handle exception

		}
//		return ListProy;
	}
	
	public void Main(String []args){
		traerJson();
	}
}
