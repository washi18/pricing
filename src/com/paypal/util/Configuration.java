package com.paypal.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.pricing.dao.CConfiguracionPaypalDAO;
import com.pricing.model.CConfiguracionPaypal;
import com.pricing.model.CServicio;

/**
 *  For a full list of configuration parameters refer in wiki page.(https://github.com/paypal/sdk-core-java/wiki/SDK-Configuration-Parameters) 
 */
public class Configuration {
	// Creates a configuration map containing credentials and other required configuration parameters.
	public static final Map<String,String> getAcctAndConfig() throws IOException{
		/**RECUPERACION DE DATOS DE CONFIGURACION PARA PAYPAL**/
		CConfiguracionPaypal datosConfigPaypal=new CConfiguracionPaypal();
		CConfiguracionPaypalDAO configPaypalDAO=new CConfiguracionPaypalDAO();
		configPaypalDAO.asignarConfigPaypal(configPaypalDAO.recuperarConfigPaypalBD());
		datosConfigPaypal=configPaypalDAO.getDatosConfigPaypal();
		/*****************************************/
		Map<String,String> configMap = new HashMap<String,String>();
		configMap.putAll(getConfig());
//		/*****************************/
		if(datosConfigPaypal.getcSignature().equals("")){
			URL resource = Configuration.class.getResource("/com/pricing/resources/"+datosConfigPaypal.getcCertName());
	        File file = null;
	        if ( resource != null && (file = new File(resource.getFile())).exists() ) {
	            System.out.println("Archivo existe--->"+file.getAbsolutePath());
	        } else {
	            System.out.println("Archivo NO existe");
	        } 
	    /*****************************************************/
		// Sample Certificate credential
	        System.out.println("Me configure como certificado");
	     configMap.put("acct1.UserName", datosConfigPaypal.getcUserName());
		 configMap.put("acct1.Password", datosConfigPaypal.getcPassword());
		 configMap.put("acct1.CertKey", datosConfigPaypal.getcCertKey());
		 configMap.put("acct1.CertPath",file.getAbsolutePath());
		 configMap.put("acct1.AppId", "APP-80W284485P519543T");
		}
		else{
			System.out.println("Me configure como signature");
			System.out.println(datosConfigPaypal.getcUserName());
			System.out.println(datosConfigPaypal.getcPassword());
			System.out.println(datosConfigPaypal.getcSignature());
			configMap.put("acct1.UserName", datosConfigPaypal.getcUserName());
			configMap.put("acct1.Password", datosConfigPaypal.getcPassword());
			configMap.put("acct1.Signature", datosConfigPaypal.getcSignature());
			configMap.put("acct1.AppId", "APP-80W284485P519543T");
		}
		return configMap;
	}
	
	public static final Map<String,String> getConfig(){
		Map<String,String> configMap = new HashMap<String,String>();
		
		// Endpoints are varied depending on whether sandbox OR live is chosen for mode
		configMap.put("mode", "sandbox");
//		configMap.put("mode", "live");
		

		// These values are defaulted in SDK. If you want to override default values, uncomment it and add your value.
		// configMap.put("http.ConnectionTimeOut", "5000");
		// configMap.put("http.Retry", "2");
		// configMap.put("http.ReadTimeOut", "30000");
		// configMap.put("http.MaxConnection", "100");
		return configMap;
	}
}