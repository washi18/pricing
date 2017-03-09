package com.pricing.extras;

import java.util.Map;

import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

import com.pricing.dao.CUsuarioLoginDAO;
import com.pricing.model.CUsuarioLogin;

public class ServicioAutentificacion {
	CUsuarioLoginDAO usuarioDAO;
	public ServicioAutentificacion(){
		usuarioDAO=new CUsuarioLoginDAO();
	}
	public CUsuarioLogin getCUsuarioLoginDAO(){
		Session sess = Sessions.getCurrent();
		CUsuarioLogin user = (CUsuarioLogin)sess.getAttribute("usuario");
		if(user==null){
			user = new CUsuarioLogin();//new a anonymous user and set to session
			sess.setAttribute("usuario",user.getcUsuarioCod());
		}
		return user;
	}
    public Object[] login(String us,String password)
    {
    	//===Se crea un arreglo de objeto para poder almacenar datos necesarios
    	Object[] Respuesta=new Object[4];
    	//------------------------------
    	usuarioDAO.getUsuario().setcUsuarioCod(us);
    	usuarioDAO.getUsuario().setcClave(password);
    	Map user=(Map)usuarioDAO.validarLogin().get(0);
    	Respuesta[1]=user.get("resultado").toString();
    	Respuesta[2]=user.get("mensaje").toString();
    	Respuesta[3]=Integer.parseInt(user.get("nperfilcod").toString());
    	//=========
    	Session sesion=Sessions.getCurrent();
    	if(Respuesta[1].toString().equals("correcto")){
    		System.out.println("entra en esta parte..");
    		Respuesta[0]=true;
    		return Respuesta;
    	}else{
    		Respuesta[0]=false;
    		return Respuesta;
    	}   	    	
    }
    public void logout(){ 
    	Session sesion=Sessions.getCurrent();
    	sesion.removeAttribute("usuario");
    }
    public CUsuarioLoginDAO retornarUsuario(){
    	return null;
    }
}
