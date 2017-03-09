package com.pricing.dao;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.pricing.model.CAcceso;
import com.pricing.model.CUsuario;
import com.pricing.model.CUsuarioLogin;

public class CUsuarioLoginDAO extends CConexion implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CUsuarioLogin usuario;
	private CUsuarioLogin oUsuario;
	private ArrayList<CUsuario> listaUsuarios;
	CAcceso oAcceso;
	//====CONSTRUCTORES=====
	public CUsuarioLoginDAO(){
		super();
		usuario=new CUsuarioLogin();
		oAcceso=new CAcceso();
		oUsuario=new CUsuarioLogin();
	}
	//====GETTER AND SETTER====
	public CUsuarioLogin getUsuario() {
		return usuario;
	}
	public void setUsuario(CUsuarioLogin usuario) {
		this.usuario = usuario;
	}
	public CAcceso getoAcceso() {
		return oAcceso;
	}
	public void setoAcceso(CAcceso oAcceso) {
		this.oAcceso = oAcceso;
	}
	public CUsuarioLogin getoUsuario() {
		return oUsuario;
	}
	public void setoUsuario(CUsuarioLogin oUsuario) {
		this.oUsuario = oUsuario;
	}
	
	public ArrayList<CUsuario> getListaUsuarios() {
		return listaUsuarios;
	}
	public void setListaUsuarios(ArrayList<CUsuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
	//--------------------------
	//=======METODOS==========
	public List validarLogin()
	{
		Object[] user={usuario.getcUsuarioCod(),usuario.getcClave()};
		return ejecutarProcedimiento("Pricing_sp_ValidarLogin", user);
	}
	public List recuperarUsuario(String usuario,String password)
	{
		Object[] values={usuario,password};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarUser", values);
	}
	public List obtenerSistemasPerfil(String subsistema)
	{
		return ejecutarProcedimiento("CP_sp_SistemasPerfil_Listar", new String[]{Integer.toString(usuario.getnPerfilCod()),subsistema});
	}
	public List recuperarAccesosUsuario(int codPerfil)
	{
		Object[] values={codPerfil};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarAcceso", values);
	}
	public List recuperarUsuariosBD()
	{
		System.out.println("esta entrando aqui..?");
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarTodosUsuarios");
	}
	public List insertarUsuario(CUsuarioLogin user)
	{
		Object[] values={user.getcUsuarioCod(),user.getcClave(),user.getnPerfilCod(),
				user.getImgUsuario(),user.getcNombres(),user.getcSexo(),
				user.getdFechaNac(),user.getcCelular(),user.getcCorreo()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_RegistrarUsuario", values);
	}
	public List modoficarUsuario(CUsuarioLogin usuario)
	{
		Object[] values={usuario.getcClave(),usuario.getnPerfilCod(),usuario.getImgUsuario(),usuario.getcNroDoc(),usuario.getcNombres(),
		usuario.getcSexo(),usuario.getdFechaNac(),usuario.getcCelular(),usuario.getdFechaInicio(),usuario.getcCorreo(),usuario.getcUsuarioCod()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_ModificarDatosUsuario", values);
	}
	public List modificarEstadoUsuario(String codUsuario,boolean estado){
		Object[]values={codUsuario,estado};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_ModificarEstadoUsuario",values);
	}
	public void asignarListaUsuarios(List lista){
		System.out.println("entra en lista usuariosDAO..");
		listaUsuarios=new ArrayList<CUsuario>();
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaUsuarios.add(new CUsuario((String)row.get("cusuariocod"), (String)row.get("cperfilidioma1"),
					(String)row.get("imgusuario"),(String)row.get("cnrodoc"),(String)row.get("cnombres"),(String)row.get("csexo")
					,(Date)row.get("dfechanac"),(String)row.get("ccelular"),(String)row.get("ccorreo"),(boolean)row.get("bestado")));
		}
	}
	
	public void asignarUsuario(List lista)
	{
		Map row=(Map)lista.get(0);
		  oUsuario.setcUsuarioCod((String)row.get("cusuariocod"));
		  oUsuario.setcClave((String)row.get("cclave"));
		  oUsuario.setnPerfilCod((int)row.get("nperfilcod"));
		  oUsuario.setImgUsuario((String)row.get("imgusuario"));
		  oUsuario.setcNroDoc((String)row.get("cnrodoc"));
		  oUsuario.setcNombres((String)row.get("cnombres"));
		  oUsuario.setcSexo((String)row.get("csexo"));
		  oUsuario.setcCelular((String)row.get("ccelular"));
		  oUsuario.setcCorreo((String)row.get("ccorreo"));
		  oUsuario.setbEstado((boolean)row.get("bestado"));
		  oUsuario.setdFechaInicio((Date)row.get("dfechainicio"));
		  oUsuario.setdFechaNac((Date)row.get("dfechanac"));
	}
	public void asignarAccesosUsuario(List lista)
	{
		Map row=(Map)lista.get(0);
		oAcceso=new CAcceso((int)row.get("naccesocod"),
				(int)row.get("nperfilcod"),
				(boolean)row.get("accesoidiomas"),
				(boolean)row.get("accesoupdatedispo"),
				(boolean)row.get("accesoetiqueta"),
				(boolean)row.get("accesoimpuesto"),
				(boolean)row.get("accesovisa"),
				(boolean)row.get("accesopaypal"),
				(boolean)row.get("accesomasterdcard"),
				(boolean)row.get("accesowesternunion"),
				(boolean)row.get("accesoregusuarios"),
				(boolean)row.get("accesocrearnuevouser"),
				(boolean)row.get("accesopaquetes"),
				(boolean)row.get("accesoservicios"),
				(boolean)row.get("accesosubservicios"),
				(boolean)row.get("accesoactividades"),
				(boolean)row.get("accesohoteles"),
				(boolean)row.get("accesodestinos"),
				(boolean)row.get("accesoreporreservas"),
				(boolean)row.get("accesoreporpagos"),
				(boolean)row.get("accesoestadpagos"),
				(boolean)row.get("accesoestadpaqumasvendidos"));
	}
	public boolean isOperationCorrect(List lista)
	{
		Map row=(Map)lista.get(0);
		boolean correcto=row.get("resultado").toString().equals("correcto");
		if(correcto)return true;
		else return false;
	}
    /*METODOS REDEFINIDOS*/
    public List ejecutarProcedimiento(String procedimiento){
    	return getEjecutorSQL().ejecutarProcedimiento(procedimiento);
    }
    public List ejecutarProcedimiento(String procedimiento,Object[] values){
    	return getEjecutorSQL().ejecutarProcedimiento(procedimiento, values);
    }
}