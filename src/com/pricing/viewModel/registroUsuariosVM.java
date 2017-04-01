package com.pricing.viewModel;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.Clients;

import com.pricing.dao.CUsuarioLoginDAO;
import com.pricing.model.CUsuario;

public class registroUsuariosVM {
	//=========atributos=======
	private ArrayList<CUsuario> listaUsuarios;
	private CUsuarioLoginDAO usuarioDao;
	//=====getter an setter=====
	public ArrayList<CUsuario> getListaUsuarios() {
		return listaUsuarios;
	}
	public void setListaUsuarios(ArrayList<CUsuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
	public CUsuarioLoginDAO getUsuarioDao() {
		return usuarioDao;
	}
	public void setUsuarioDao(CUsuarioLoginDAO usuarioDao) {
		this.usuarioDao = usuarioDao;
	}
	//====constructores=====
	@Init
	public void InitVM(){
		listaUsuarios=new ArrayList<CUsuario>();
		usuarioDao=new CUsuarioLoginDAO();
		Execution exec = Executions.getCurrent();
	}
	@GlobalCommand
	public void cargarDatosUsuarios()
	{
		HttpSession ses = (HttpSession)Sessions.getCurrent().getNativeSession();
	    String user=(String)ses.getAttribute("usuario");
	    String pas=(String)ses.getAttribute("clave");
		usuarioDao.asignarListaUsuarios(usuarioDao.recuperarUsuariosBD());
		setListaUsuarios(usuarioDao.getListaUsuarios());
		
		BindUtils.postNotifyChange(null, null, this, "listaUsuarios");
	}
	@GlobalCommand
	public void actualizarDatos(){
		usuarioDao.asignarListaUsuarios(usuarioDao.recuperarUsuariosBD());
		setListaUsuarios(usuarioDao.getListaUsuarios());
		BindUtils.postNotifyChange(null, null, this,"listaUsuarios");
	}
	//======otros metodos=======
	@Command
	public void activar_desactivar_usuarios(@BindingParam("usuario")CUsuario usuario,@BindingParam("texto")String texto,@BindingParam("componente")Component comp)
	{
		if(texto.equals("activar")){
			usuario.setColor_activo("background:#3BA420;");
			usuario.setColor_desactivo("background:#transparent;");
			usuario.setEstadoActivo(true);
			usuario.setEstadoDesactivo(false);
			usuario.setBestado(true);
			boolean correcto=usuarioDao.isOperationCorrect(usuarioDao.modificarEstadoUsuario(usuario.getCusuariocod(), usuario.isBestado()));
			if(correcto)
				Clients.showNotification("Estado de usuario activado satisfactoriamente", Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",2700);
			else
				Clients.showNotification("Error al actualizar estado", Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",2700);
		}else if(texto.equals("desactivar")){
			usuario.setColor_activo("background:transparent;");
			usuario.setColor_desactivo("background:#DA0613;");
			usuario.setEstadoActivo(false);
			usuario.setEstadoDesactivo(true);
			usuario.setBestado(false);
			boolean correcto=usuarioDao.isOperationCorrect(usuarioDao.modificarEstadoUsuario(usuario.getCusuariocod(), usuario.isBestado()));
			if(correcto)
				Clients.showNotification("Estado de usuario desactivado satisfactoriamente", Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",2700);
			else
				Clients.showNotification("Error al actualizar estado", Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",2700);
		}
		BindUtils.postNotifyChange(null, null, usuario, "bestado");
		BindUtils.postNotifyChange(null, null, usuario, "color_activo");
		BindUtils.postNotifyChange(null, null, usuario, "color_desactivo");
		BindUtils.postNotifyChange(null, null, usuario, "estadoActivo");
		BindUtils.postNotifyChange(null, null, usuario, "estadoDesactivo");
	}
}
