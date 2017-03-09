package com.pricing.viewModel;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import javax.servlet.http.HttpSession;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Messagebox;

import pe.com.erp.crypto.Encryptar;

import pe.com.erp.crypto.Encryptar;

import com.pricing.dao.CAccesoDAO;
import com.pricing.dao.CPerfilDAO;
import com.pricing.dao.CUsuarioLoginDAO;
import com.pricing.model.CAcceso;
import com.pricing.model.CPerfil;
import com.pricing.model.CUsuario;
import com.pricing.model.CUsuarioLogin;
import com.pricing.util.CEmail;
import com.pricing.util.ScannUtil;

public class crearUsuarioVM 
{
	private CUsuarioLogin oUsuarioNuevo;
	private CAcceso oAccesoNuevo;
	private CPerfil oPerfilNuevo;
	private CPerfilDAO perfilDao;
	private CUsuarioLoginDAO usuarioDao;
	private CAccesoDAO accesoDao;
	private ArrayList<CAcceso> listaAccesos;
	private ArrayList<CPerfil> listaPerfiles;
	private boolean mostrarAccesosPerfil;
	private boolean estadoMasculino;
	private boolean estadoFemenino;
	private HttpSession ses;
	/*************/
	private char[] caracteres={'0','1','2','3','4','5','6','8','9',
							   'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
							   '@','#','$','%',
							   'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	/*************/
	public CUsuarioLogin getoUsuarioNuevo() {
		return oUsuarioNuevo;
	}
	public void setoUsuarioNuevo(CUsuarioLogin oUsuarioNuevo) {
		this.oUsuarioNuevo = oUsuarioNuevo;
	}
	public CAcceso getoAccesoNuevo() {
		return oAccesoNuevo;
	}
	public void setoAccesoNuevo(CAcceso oAccesoNuevo) {
		this.oAccesoNuevo = oAccesoNuevo;
	}
	public ArrayList<CPerfil> getListaPerfiles() {
		return listaPerfiles;
	}
	public void setListaPerfiles(ArrayList<CPerfil> listaPerfiles) {
		this.listaPerfiles = listaPerfiles;
	}
	public ArrayList<CAcceso> getListaAccesos() {
		return listaAccesos;
	}
	public void setListaAccesos(ArrayList<CAcceso> listaAccesos) {
		this.listaAccesos = listaAccesos;
	}
	public CPerfil getoPerfilNuevo() {
		return oPerfilNuevo;
	}
	public void setoPerfilNuevo(CPerfil oPerfilNuevo) {
		this.oPerfilNuevo = oPerfilNuevo;
	}
	public boolean isMostrarAccesosPerfil() {
		return mostrarAccesosPerfil;
	}
	public void setMostrarAccesosPerfil(boolean mostrarAccesosPerfil) {
		this.mostrarAccesosPerfil = mostrarAccesosPerfil;
	}
	
	public boolean isEstadoMasculino() {
		return estadoMasculino;
	}
	public void setEstadoMasculino(boolean estadoMasculino) {
		this.estadoMasculino = estadoMasculino;
	}
	public boolean isEstadoFemenino() {
		return estadoFemenino;
	}
	public void setEstadoFemenino(boolean estadoFemenino) {
		this.estadoFemenino = estadoFemenino;
	}
	/******************************/
	@Init
	public void iniVM()
	{
		oUsuarioNuevo=new CUsuarioLogin();
		oAccesoNuevo=new CAcceso();
		oPerfilNuevo=new CPerfil();
		usuarioDao=new CUsuarioLoginDAO();
		accesoDao=new CAccesoDAO();
		perfilDao=new CPerfilDAO();
		listaPerfiles=new ArrayList<CPerfil>();
		listaAccesos=new ArrayList<CAcceso>();
		mostrarAccesosPerfil=false;
		Encryptar encrip= new Encryptar();
//		System.out.println("Aqui esta la contraseña desencriptada-->"+encrip.decrypt("cyS249O3OHZTsG0ww1rYrw=="));
		Execution exec = Executions.getCurrent();
		HttpSession ses = (HttpSession)Sessions.getCurrent().getNativeSession();
	    String user=(String)ses.getAttribute("usuario");
	    String pas=(String)ses.getAttribute("clave");
		//===============
		perfilDao.asignarListaPerfiles(perfilDao.recuperarPerfilesBD());
		setListaPerfiles(perfilDao.getListaPerfiles());
		//===============
		accesoDao.asignarListaAccesos(accesoDao.RecuperarTodosAccesos());
		setListaAccesos(accesoDao.getListaAccesos());
	
	}	
	
	@Command
	@NotifyChange({"estadoMasculino","estadoFemenino"})
	public void seleccionRadio(@BindingParam("radio")String idRadio)
	{
		if(idRadio.equals("rdMasculino")){
			estadoMasculino=true;
			estadoFemenino=false;
			oUsuarioNuevo.setcSexo("M");
			System.out.println("el sexo del masculino es:"+oUsuarioNuevo.getcSexo());
		}else if(idRadio.equals("rdFemenino")){
			estadoFemenino=true;
			estadoMasculino=false;
			oUsuarioNuevo.setcSexo("F");
		}
	  BindUtils.postNotifyChange(null, null, oUsuarioNuevo, "cSexo");
	}
	
	@Command
	public void insertarUsuario(@BindingParam("componente")Component comp) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException
	{
		if(!validoParaInsertar(comp))
			return;
		oUsuarioNuevo.setImgUsuario("/img/usuarios/user.jpg");
		oUsuarioNuevo.setcNombres(oUsuarioNuevo.getcNombres().toUpperCase());
		oUsuarioNuevo.setcNroDoc(oUsuarioNuevo.getcUsuarioCod());
		/**GENERAR PASSWORD DE MANERA DINAMICA**/
		String contrasenia="";
		for(int i=0;i<8;i++)
		{
			Random rdn=new Random();
			int n=rdn.nextInt(65);
			contrasenia+=caracteres[n];
		}
		Encryptar encrip= new Encryptar();
		String auxContrasenia=contrasenia;
		System.out.println("la contrasenia sin encryptar es:"+auxContrasenia);
		contrasenia=encrip.encrypt(contrasenia);
		/**ASIGNACION DE CONTRASEÑA**/
		oUsuarioNuevo.setcClave(contrasenia);
		if(oUsuarioNuevo.isConPerfilExistente())
		{
			boolean correcto=usuarioDao.isOperationCorrect(usuarioDao.insertarUsuario(oUsuarioNuevo));
			if(correcto)
			{
				CEmail mail=new CEmail();
				boolean enviado=mail.enviarCorreoNuevoUser(oUsuarioNuevo.getcCorreo(), oUsuarioNuevo.getcUsuarioCod(),auxContrasenia);
				oUsuarioNuevo=new CUsuarioLogin();
				Clients.showNotification("El Usuario se inserto correctamente",Clients.NOTIFICATION_TYPE_INFO,comp,"before_start",2700);
			}
			else
				Clients.showNotification("El usuario no se inserto",Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",2700);
		}else
		{
			oPerfilNuevo.setCperfil(oPerfilNuevo.getCperfil().toUpperCase());
			int codPerfil=perfilDao.obtenerCodPerfil(perfilDao.insertarPerfil(oPerfilNuevo.getCperfil()));
			oAccesoNuevo.setnPerfilCod(codPerfil);
			oUsuarioNuevo.setnPerfilCod(codPerfil);
			boolean correcto=usuarioDao.isOperationCorrect(usuarioDao.insertarUsuario(oUsuarioNuevo));
			if(correcto)
			{
				CEmail mail=new CEmail();
				boolean enviado=mail.enviarCorreoNuevoUser(oUsuarioNuevo.getcCorreo(), oUsuarioNuevo.getcUsuarioCod(),auxContrasenia);
				oUsuarioNuevo=new CUsuarioLogin();
				Clients.showNotification("El Usuario se inserto correctamente",Clients.NOTIFICATION_TYPE_INFO,comp,"before_start",2700);
			}
			else
				Clients.showNotification("El usuario no se inserto",Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",2700);
			correcto=accesoDao.isOperationCorrect(accesoDao.insertarAcceso(oAccesoNuevo));
		}
		/**RECUPERAR LOS PERFILES**/
		perfilDao.asignarListaPerfiles(perfilDao.recuperarPerfilesBD());
		setListaPerfiles(perfilDao.getListaPerfiles());
		
		accesoDao.asignarListaAccesos(accesoDao.RecuperarTodosAccesos());
		setListaAccesos(accesoDao.getListaAccesos());
		if(oUsuarioNuevo.isConPerfilNuevo())
		{
			/**RECUPERAR LOS PERFILES**/
			perfilDao.asignarListaPerfiles(perfilDao.recuperarPerfilesBD());
			setListaPerfiles(perfilDao.getListaPerfiles());
			/**RECUPERAR LOS ACCESOS**/
			accesoDao.asignarListaAccesos(accesoDao.RecuperarTodosAccesos());
			setListaAccesos(accesoDao.getListaAccesos());
			BindUtils.postNotifyChange(null, null, this,"listaPerfiles");
			BindUtils.postNotifyChange(null, null, this,"listaAccesos");
		}
		/**inicilaizar datos**/
		oAccesoNuevo=new CAcceso();
		oPerfilNuevo=new CPerfil();
		oUsuarioNuevo=new CUsuarioLogin();
		BindUtils.postNotifyChange(null, null, this,"listaPerfiles");
		BindUtils.postNotifyChange(null, null, this,"oAccesoNuevo");
		BindUtils.postNotifyChange(null, null, this,"oPerfilNuevo");
		BindUtils.postNotifyChange(null, null, this,"oUsuarioNuevo");
		BindUtils.postNotifyChange(null, null, this,"listaAccesos");
	}
	
	@Command
	public void actualizarAcceso(@BindingParam("acceso")CAcceso acceso,@BindingParam("componente")Component comp)
	{
		boolean correcto=accesoDao.isOperationCorrect(accesoDao.actualizarAcceso(acceso));
		if(correcto)
			Clients.showNotification("El Acceso se actualizo correctamente",Clients.NOTIFICATION_TYPE_INFO,comp,"before_start",2700);
	}
	public boolean validoParaInsertar(Component comp)
	{
		boolean valido=true;
			if(oUsuarioNuevo.getcUsuarioCod().equals("") || !isInteger(oUsuarioNuevo.getcUsuarioCod()))
			{
				valido=false;
				Clients.showNotification("Necesita ingresar un DNI",Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",2700);
			}else if(oUsuarioNuevo.getcCorreo().equals("") || !mailValido(oUsuarioNuevo.getcCorreo())){
				valido=false;
				Clients.showNotification("Necesita ingresar un correo valido para el nuevo usuario",Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",2700);
//			}else if(oUsuarioNuevo.getcCelular().equals("") || !isInteger(oUsuarioNuevo.getcCelular())){
//				valido=false;
//				Clients.showNotification("El campo celular debe contener solo numeros ",Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",2700);
			}else if(oUsuarioNuevo.getdFechaNac().equals("")){
				valido=false;
				Clients.showNotification("Necesita ingresar la fecha de nacimiento ",Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",2700);
			}else if(oUsuarioNuevo.getcSexo().equals("")){
				valido=false;
				Clients.showNotification("Necesita ingresar el sexo del usuario ",Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",2700);
			}else if(oUsuarioNuevo.isConPerfilNuevo())
			{
				if(oPerfilNuevo.getCperfil().equals(""))
				{
					valido=false;
					Clients.showNotification("Se necesita un nombre de perfil",Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",2700);
				}
			}else if(oUsuarioNuevo.isConPerfilExistente())
			{
				if(oUsuarioNuevo.getnPerfilCod()==0)
				{
					valido=false;
					Clients.showNotification("Se debe asignar un perfil al nuevo usuario",Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",2700);
				}
			}
		return valido;
	}
	
	@Command
	public void asignarPerfil(@BindingParam("perfil")Object perfil)
	{
		if(!mostrarAccesosPerfil)
		{
			mostrarAccesosPerfil=true;
			BindUtils.postNotifyChange(null, null, this,"mostrarAccesosPerfil");
		}
		int codPerfil=Integer.parseInt(perfil.toString());
		oUsuarioNuevo.setnPerfilCod(codPerfil);
		oAccesoNuevo.setnPerfilCod(codPerfil);
		/************/
		for(CAcceso a:listaAccesos)
		{
			if(a.getnPerfilCod()==codPerfil)
				a.setVisibleAcceso(true);
			else
				a.setVisibleAcceso(false);
			BindUtils.postNotifyChange(null, null, a, "visibleAcceso");
		}
	}
	@Command
	@NotifyChange({"oUsuarioNuevo"})
	public void selectTipoPerfil(@BindingParam("opcion")String opcion)
	{
		if(opcion.toString().equals("perfil_exist"))
		{
			oUsuarioNuevo.setConPerfilExistente(true);
			oUsuarioNuevo.setConPerfilNuevo(false);
		}else
		{
			oUsuarioNuevo.setConPerfilExistente(false);
			oUsuarioNuevo.setConPerfilNuevo(true);
		}
	}
	@Command
	public void CambioVisibilidadAccesos(@BindingParam("acceso")CAcceso acceso,@BindingParam("cod")int codRef)
	{
		if(codRef==1)//significa que hicimos click en CONFIGURACION
		{
			if(acceso.isVisibleConfig())
				acceso.setVisibleConfig(false);
			else
			{
				acceso.setVisibleConfig(true);
				acceso.setVisibleUser(false);
				acceso.setVisiblePaquetes(false);
				acceso.setVisibleReportes(false);
			}
		}else if(codRef==2)//significa que hicimos click en USUARIOS
		{
			if(acceso.isVisibleUser())
				acceso.setVisibleUser(false);
			else
			{
				acceso.setVisibleConfig(false);
				acceso.setVisibleUser(true);
				acceso.setVisiblePaquetes(false);
				acceso.setVisibleReportes(false);
			}
		}else if(codRef==3)//significa que hicimos click en PAQUETES
		{
			if(acceso.isVisiblePaquetes())
				acceso.setVisiblePaquetes(false);
			else
			{
				acceso.setVisibleConfig(false);
				acceso.setVisibleUser(false);
				acceso.setVisiblePaquetes(true);
				acceso.setVisibleReportes(false);
			}
		}else if(codRef==4)//significa que hicimos click en REPORTES
		{
			if(acceso.isVisibleReportes())
				acceso.setVisibleReportes(false);
			else
			{
				acceso.setVisibleConfig(false);
				acceso.setVisibleUser(false);
				acceso.setVisiblePaquetes(false);
				acceso.setVisibleReportes(true);
			}
		}
		BindUtils.postNotifyChange(null, null, acceso,"visibleConfig");
		BindUtils.postNotifyChange(null, null, acceso,"visibleReportes");
		BindUtils.postNotifyChange(null, null, acceso,"visiblePaquetes");
		BindUtils.postNotifyChange(null, null, acceso,"visibleUser");
	}
	@Command
	public void selectAcceso(@BindingParam("opcion")int opcion)
	{
		if(opcion==1)
		{
			if(oAccesoNuevo.isAccesoIdiomas())oAccesoNuevo.setAccesoIdiomas(false);
			else oAccesoNuevo.setAccesoIdiomas(true);
		}else if(opcion==2)
		{
			if(oAccesoNuevo.isAccesoUpdateDispo()) oAccesoNuevo.setAccesoUpdateDispo(false);
			else oAccesoNuevo.setAccesoUpdateDispo(true);
		}else if(opcion==3)
		{
			if(oAccesoNuevo.isAccesoEtiqueta()) oAccesoNuevo.setAccesoEtiqueta(false);
			else oAccesoNuevo.setAccesoEtiqueta(true);
		}else if(opcion==4)
		{
			if(oAccesoNuevo.isAccesoImpuesto()) oAccesoNuevo.setAccesoImpuesto(false);
			else oAccesoNuevo.setAccesoImpuesto(true);
		}else if(opcion==5)
		{
			if(oAccesoNuevo.isAccesoVisa())oAccesoNuevo.setAccesoVisa(false);
			else oAccesoNuevo.setAccesoVisa(true);
		}else if(opcion==6)
		{
			if(oAccesoNuevo.isAccesoPaypal())oAccesoNuevo.setAccesoPaypal(false);
			else oAccesoNuevo.setAccesoPaypal(true);
		}else if(opcion==7)
		{
			if(oAccesoNuevo.isAccesoMasterdCard())oAccesoNuevo.setAccesoMasterdCard(false);
			else oAccesoNuevo.setAccesoMasterdCard(true);
		}else if(opcion==8)
		{
			if(oAccesoNuevo.isAccesoWesternUnion())oAccesoNuevo.setAccesoWesternUnion(false);
			else oAccesoNuevo.setAccesoWesternUnion(true);
		}else if(opcion==9)
		{
			if(oAccesoNuevo.isAccesoRegUsuarios())oAccesoNuevo.setAccesoRegUsuarios(false);
			else oAccesoNuevo.setAccesoRegUsuarios(true);
		}else if(opcion==10)
		{
			if(oAccesoNuevo.isAccesoCrearNuevoUser())oAccesoNuevo.setAccesoCrearNuevoUser(false);
			else oAccesoNuevo.setAccesoCrearNuevoUser(true);
		}else if(opcion==11)
		{
			if(oAccesoNuevo.isAccesoPaquetes())oAccesoNuevo.setAccesoPaquetes(false);
			else oAccesoNuevo.setAccesoPaquetes(true);
		}else if(opcion==12)
		{
			if(oAccesoNuevo.isAccesoServicios())oAccesoNuevo.setAccesoServicios(false);
			else oAccesoNuevo.setAccesoServicios(true);
		}else if(opcion==13)
		{
			if(oAccesoNuevo.isAccesoSubServicios())oAccesoNuevo.setAccesoSubServicios(false);
			else oAccesoNuevo.setAccesoSubServicios(true);
		}else if(opcion==14)
		{
			if(oAccesoNuevo.isAccesoActividades())oAccesoNuevo.setAccesoActividades(false);
			else oAccesoNuevo.setAccesoActividades(true);
		}else if(opcion==15)
		{
			if(oAccesoNuevo.isAccesoHoteles())oAccesoNuevo.setAccesoHoteles(false);
			else oAccesoNuevo.setAccesoHoteles(true);
		}else if(opcion==16)
		{
			if(oAccesoNuevo.isAccesoDestinos())oAccesoNuevo.setAccesoDestinos(false);
			else oAccesoNuevo.setAccesoDestinos(true);
		}else if(opcion==17)
		{
			if(oAccesoNuevo.isAccesoReporPagos())oAccesoNuevo.setAccesoReporPagos(false);
			else oAccesoNuevo.setAccesoReporPagos(true);
		}else if(opcion==18)
		{
			if(oAccesoNuevo.isAccesoReporReservas())oAccesoNuevo.setAccesoReporReservas(false);
			else oAccesoNuevo.setAccesoReporReservas(true);
		}else if(opcion==19)
		{
			if(oAccesoNuevo.isAccesoEstadPagos())oAccesoNuevo.setAccesoEstadPagos(false);
			else oAccesoNuevo.setAccesoEstadPagos(true);
		}else if(opcion==20)
		{
			if(oAccesoNuevo.isAccesoEstadPaquMasVendidos())oAccesoNuevo.setAccesoEstadPaquMasVendidos(false);
			else oAccesoNuevo.setAccesoEstadPaquMasVendidos(true);
		}
	}
	@Command
	public void selectAcceso_update(@BindingParam("opcion")int opcion,@BindingParam("acceso")CAcceso oAccesoUpdate)
	{
		if(opcion==1)
		{
			if(oAccesoUpdate.isAccesoIdiomas())oAccesoUpdate.setAccesoIdiomas(false);
			else oAccesoUpdate.setAccesoIdiomas(true);
		}else if(opcion==2)
		{
			if(oAccesoUpdate.isAccesoUpdateDispo()) oAccesoUpdate.setAccesoUpdateDispo(false);
			else oAccesoUpdate.setAccesoUpdateDispo(true);
		}else if(opcion==3)
		{
			if(oAccesoUpdate.isAccesoEtiqueta()) oAccesoUpdate.setAccesoEtiqueta(false);
			else oAccesoUpdate.setAccesoEtiqueta(true);
		}else if(opcion==4)
		{
			if(oAccesoUpdate.isAccesoImpuesto()) oAccesoUpdate.setAccesoImpuesto(false);
			else oAccesoUpdate.setAccesoImpuesto(true);
		}else if(opcion==5)
		{
			if(oAccesoUpdate.isAccesoVisa())oAccesoUpdate.setAccesoVisa(false);
			else oAccesoUpdate.setAccesoVisa(true);
		}else if(opcion==6)
		{
			if(oAccesoUpdate.isAccesoPaypal())oAccesoUpdate.setAccesoPaypal(false);
			else oAccesoUpdate.setAccesoPaypal(true);
		}else if(opcion==7)
		{
			if(oAccesoUpdate.isAccesoMasterdCard())oAccesoUpdate.setAccesoMasterdCard(false);
			else oAccesoUpdate.setAccesoMasterdCard(true);
		}else if(opcion==8)
		{
			if(oAccesoUpdate.isAccesoWesternUnion())oAccesoUpdate.setAccesoWesternUnion(false);
			else oAccesoUpdate.setAccesoWesternUnion(true);
		}else if(opcion==9)
		{
			if(oAccesoUpdate.isAccesoRegUsuarios())oAccesoUpdate.setAccesoRegUsuarios(false);
			else oAccesoUpdate.setAccesoRegUsuarios(true);
		}else if(opcion==10)
		{
			if(oAccesoUpdate.isAccesoCrearNuevoUser())oAccesoUpdate.setAccesoCrearNuevoUser(false);
			else oAccesoUpdate.setAccesoCrearNuevoUser(true);
		}else if(opcion==11)
		{
			if(oAccesoUpdate.isAccesoPaquetes())oAccesoUpdate.setAccesoPaquetes(false);
			else oAccesoUpdate.setAccesoPaquetes(true);
		}else if(opcion==12)
		{
			if(oAccesoUpdate.isAccesoServicios())oAccesoUpdate.setAccesoServicios(false);
			else oAccesoUpdate.setAccesoServicios(true);
		}else if(opcion==13)
		{
			if(oAccesoUpdate.isAccesoSubServicios())oAccesoUpdate.setAccesoSubServicios(false);
			else oAccesoUpdate.setAccesoSubServicios(true);
		}else if(opcion==14)
		{
			if(oAccesoUpdate.isAccesoActividades())oAccesoUpdate.setAccesoActividades(false);
			else oAccesoUpdate.setAccesoActividades(true);
		}else if(opcion==15)
		{
			if(oAccesoUpdate.isAccesoHoteles())oAccesoUpdate.setAccesoHoteles(false);
			else oAccesoUpdate.setAccesoHoteles(true);
		}else if(opcion==16)
		{
			if(oAccesoUpdate.isAccesoDestinos())oAccesoUpdate.setAccesoDestinos(false);
			else oAccesoUpdate.setAccesoDestinos(true);
		}else if(opcion==17)
		{
			if(oAccesoUpdate.isAccesoReporPagos())oAccesoUpdate.setAccesoReporPagos(false);
			else oAccesoUpdate.setAccesoReporPagos(true);
		}else if(opcion==18)
		{
			if(oAccesoUpdate.isAccesoReporReservas())oAccesoUpdate.setAccesoReporReservas(false);
			else oAccesoUpdate.setAccesoReporReservas(true);
		}else if(opcion==19)
		{
			if(oAccesoUpdate.isAccesoEstadPagos())oAccesoUpdate.setAccesoEstadPagos(false);
			else oAccesoUpdate.setAccesoEstadPagos(true);
		}else if(opcion==20)
		{
			if(oAccesoUpdate.isAccesoEstadPaquMasVendidos())oAccesoUpdate.setAccesoEstadPaquMasVendidos(false);
			else oAccesoUpdate.setAccesoEstadPaquMasVendidos(true);
		}
	}
	//=================metodos de modificaciones en crear usuario======
	@Command
	public void changeDNI(@BindingParam("nroDoc")String nroDoc,@BindingParam("componente")Component comp)
	{
		if(!isInteger(nroDoc))
		{
			Clients.showNotification("El DNI debe contener sólo números", Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
		}
	}
	@Command
	public void changeCelular(@BindingParam("celular")String nroCel,@BindingParam("componente")Component comp)
	{
		if(!isInteger(nroCel)){
			oUsuarioNuevo.setcCelular("");
			Clients.showNotification("El Celular debe contener sólo números", Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
		}
	}
	@Command
	public void changeMail(@BindingParam("mail")String mail,@BindingParam("componente")Component comp)
	{
		if(!mailValido(mail))
		{
			oUsuarioNuevo.setcCorreo("");
			Clients.showNotification("El formato de correo no es valido",Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",2500);	
		}
	}
	
	public boolean mailValido(String mail)
	{
		 Pattern pat = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	       Matcher mat = pat.matcher(mail);
	       if(mat.find()){
	          return true;
	       }else
	          return false;
	}
	public boolean isInteger(String n)
	{
		try {
			Integer.parseInt(n);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
//	@Command
//	public void detFechaNac(@BindingParam("fecha")Date fecha)
//	{
//		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
//		String Fecha=sdf.format(fecha);
//		System.out.println("fecha es:"+Fecha);
//		String dia=Fecha.substring(0,2);
//		String mes=Fecha.substring(3,5);
//		String anio=Fecha.substring(6,10);
//		/*************Fecha Nac*******************/
//		Calendar calIni=Calendar.getInstance();
//		calIni.set(Integer.parseInt(anio),Integer.parseInt(mes)-1,Integer.parseInt(dia));
//		oUsuarioNuevo.setdFechaNac(calIni.getTime());
//	}
}
