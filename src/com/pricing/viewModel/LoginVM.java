package com.pricing.viewModel;


import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pricing.extras.ServicioAutentificacion;

import pe.com.erp.crypto.Encryptar;
/////imagenes
public class LoginVM {

	private String tbNombreUsuario;
	private String tbContrasenia;
	private String lblMensaje;
	ServicioAutentificacion auth;
	HttpSession seshttp;
	boolean falloAutenticasion=false;
	@Wire("#tbUserName")
	Textbox tbUserName;
	@Wire("#tbUserPass")
	Textbox tbUserPass;
	
	public ServicioAutentificacion getAuth() {
		return auth;
	}

	public void setAuth(ServicioAutentificacion auth) {
		this.auth = auth;
	}

	private static final long serialVersionUID = 1L;

	BufferedImage foto;
	
	public BufferedImage getfoto() {
		return foto;
	}

	public void setfoto(BufferedImage foto) {
		this.foto = foto;
	}
	public String getTbNombreUsuario() {
		return tbNombreUsuario;
	}

	public void setTbNombreUsuario(String tbNombreUsuario) {
		this.tbNombreUsuario = tbNombreUsuario;
	}

	public String getTbContrasenia() {
		return tbContrasenia;
	}

	public void setTbContrasenia(String tbContrasenia) {
		this.tbContrasenia = tbContrasenia;
	}	
	public boolean isFalloAutenticasion() {
		return falloAutenticasion;
	}

	public void setFalloAutenticasion(boolean falloAutenticasion) {
		this.falloAutenticasion = falloAutenticasion;
	}
	public String getLblMensaje() {
		return lblMensaje;
	}

	public void setLblMensaje(String lblMensaje) {
		this.lblMensaje = lblMensaje;
	}

	@Init
	public void Autentificacion()
	{
		auth=new ServicioAutentificacion();
		System.out.println("imprime este parte");
	}
	@AfterCompose
	public void doAfterCompose(@ContextParam(ContextType.VIEW) Component view){
		Selectors.wireComponents(view, this, false);
		tbUserName.setFocus(true);
	}

	@Command
	@NotifyChange({"falloAutenticasion","lblMensaje"})
	public void doLogin(@BindingParam("componente")Component comp)
	{		
		System.out.println("debe imprimir esto");
		try
		{
			Encryptar encrip= new Encryptar();
			tbContrasenia=encrip.encrypt(tbContrasenia);
//			System.out.println("Aqui esta la contraseña desencriptada-->"+encrip.decrypt("oUwltZST1Ur3u1KPhCZkSg=="));
//			Object[] ResultadosLogin=auth.login(tbNombreUsuario,tbContrasenia);
			//System.out.println("necesito la contrasenia sin encriptar"+encrip.encrypt(tbContrasenia));
			Object[] ResultadosLogin=auth.login(tbNombreUsuario,tbContrasenia);
			if((boolean)ResultadosLogin[0])
			{
				System.out.println("Entre----->");
				Execution exec = Executions.getCurrent();
				seshttp = (HttpSession)Sessions.getCurrent().getNativeSession();
				//String user=encrip.encrypt( tbNombreUsuario);
				//String clave=encrip.encrypt(tbContrasenia);
				int codPerfil=(int)ResultadosLogin[3];
				seshttp.setAttribute("usuario", tbNombreUsuario);
				seshttp.setAttribute("clave", tbContrasenia);
				seshttp.setAttribute("perfil", codPerfil);
			    
				Executions.getCurrent().sendRedirect("/panelAdmin.zul");
//				exec.sendRedirect("../panel_admin/?var3="+codPerfil,false);
//			    exec.setVoided(true);
		    }else{				
				tbUserName.setFocus(true);
				falloAutenticasion=true;
				lblMensaje=ResultadosLogin[2].toString();
				Clients.showNotification(lblMensaje, comp, true);
			}
		 }
		 catch (Exception e){
			e.printStackTrace();
//			Clients.showNotification("TIENE PROBLEMAS DE CONEXION : "+e.getMessage(),comp, true);
			Clients.showNotification("INGRESE USUARIO Y CONTRASEÑA",comp, true);
		 }
	}
	@Command
	public void onfocusTxtUsuario()
    {
		limpiarPlaceTxtUsuario();	
    }
	@Command
	public void limpiarPlaceTxtUsuario()
	{
		tbUserName.setPlaceholder("");
		tbUserPass.setPlaceholder("");
	}
	@Command
	public void setPlaceTxtUsuario()
	{
		tbUserName.setPlaceholder("USUARIO");
		tbUserPass.setPlaceholder("CONTRASEÑA");
	}
	@Command
	public void onChangeTxtUsuario(@BindingParam("usuario")String usr)
	{
		tbNombreUsuario=usr;
	}
}

