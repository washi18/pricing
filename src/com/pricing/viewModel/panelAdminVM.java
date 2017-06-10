package com.pricing.viewModel;

import javax.servlet.http.HttpSession;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;

import pe.com.erp.crypto.Encryptar;

import com.pricing.dao.CUsuarioLoginDAO;
import com.pricing.model.CAcceso;
import com.pricing.model.CUsuarioLogin;

public class panelAdminVM
{
	private boolean cargarAdmin;
	private boolean visibleConfiguracion;
	//==============VARIABLES INTERFACES==============
	private boolean visibleEtiqueta;
	private boolean visiblePaquetes;
	private boolean visibleServicios;
	private boolean visibleSubServicios;
	private boolean visibleActividades;
	private boolean visibleImpuestos;
	private boolean visibleDisponibilidad;
	private boolean visibleHoteles;
	private boolean visibleDestinos;
	private boolean visibleReportReservas;
	private boolean visibleReportPagos;
	private boolean visibleEstadisticaPagos;
	private boolean visibleEstadisticaPaquetesmasVendidos;
	private boolean visibleCrearUser;
	private boolean visibleActualizarUsuario;
	private boolean visibleRegistroUsuarios;
	private boolean visibleConfPaypal;
	private boolean visibleConfExtras;
	private boolean visibleCupones;
	private boolean visibleConfAltoNivel;
	private boolean visibleMenuAndroid;
	private boolean visibleAgencia;
	//======================================
	private crearUsuarioVM editarUsuario;
	private CUsuarioLoginDAO usuarioDao;
	private CAcceso oAcceso;
	private CUsuarioLogin oUsuario;
	private HttpSession ses;
	//=================GET Y SET SELECCION TABS=======
	
	public boolean isVisibleEstadisticaPagos() {
		return visibleEstadisticaPagos;
	}
	public boolean isVisibleConfAltoNivel() {
		return visibleConfAltoNivel;
	}
	public void setVisibleConfAltoNivel(boolean visibleConfAltoNivel) {
		this.visibleConfAltoNivel = visibleConfAltoNivel;
	}
	public void setVisibleEstadisticaPagos(boolean visibleEstadisticaPagos) {
		this.visibleEstadisticaPagos = visibleEstadisticaPagos;
	}
	public boolean isVisibleEstadisticaPaquetesmasVendidos() {
		return visibleEstadisticaPaquetesmasVendidos;
	}
	public void setVisibleEstadisticaPaquetesmasVendidos(
			boolean visibleEstadisticaPaquetesmasVendidos) {
		this.visibleEstadisticaPaquetesmasVendidos = visibleEstadisticaPaquetesmasVendidos;
	}
	public boolean isVisibleActividades() {
		return visibleActividades;
	}
	public void setVisibleActividades(boolean visibleActividades) {
		this.visibleActividades = visibleActividades;
	}
	
	//================RESPONSIVE======================
	
	public boolean isVisibleConfPaypal() {
		return visibleConfPaypal;
	}
	public void setVisibleConfPaypal(boolean visibleConfPaypal) {
		this.visibleConfPaypal = visibleConfPaypal;
	}
	public boolean isVisibleRegistroUsuarios() {
		return visibleRegistroUsuarios;
	}
	public void setVisibleRegistroUsuarios(boolean visibleRegistroUsuarios) {
		this.visibleRegistroUsuarios = visibleRegistroUsuarios;
	}
	public crearUsuarioVM getEditarUsuario() {
		return editarUsuario;
	}
	public void setEditarUsuario(crearUsuarioVM editarUsuario) {
		this.editarUsuario = editarUsuario;
	}
	public boolean isVisibleHoteles() {
		return visibleHoteles;
	}
	public void setVisibleHoteles(boolean visibleHoteles) {
		this.visibleHoteles = visibleHoteles;
	}
	//===============CAMBIO INTERFACES==============
	public boolean isVisibleDisponibilidad() {
		return visibleDisponibilidad;
	}

	public boolean isVisibleConfiguracion() {
		return visibleConfiguracion;
	}
	public void setVisibleConfiguracion(boolean visibleConfiguracion) {
		this.visibleConfiguracion = visibleConfiguracion;
	}
	public void setVisibleDisponibilidad(boolean visibleDisponibilidad) {
		this.visibleDisponibilidad = visibleDisponibilidad;
	}
	
	public boolean isVisibleEtiqueta() {
		return visibleEtiqueta;
	}

	public void setVisibleEtiqueta(boolean visibleEtiqueta) {
		this.visibleEtiqueta = visibleEtiqueta;
	}

	public boolean isVisiblePaquetes() {
		return visiblePaquetes;
	}

	public void setVisiblePaquetes(boolean visiblePaquetes) {
		this.visiblePaquetes = visiblePaquetes;
	}

	public boolean isVisibleServicios() {
		return visibleServicios;
	}

	public void setVisibleServicios(boolean visibleServicios) {
		this.visibleServicios = visibleServicios;
	}

	public boolean isVisibleSubServicios() {
		return visibleSubServicios;
	}

	public void setVisibleSubServicios(boolean visibleSubServicios) {
		this.visibleSubServicios = visibleSubServicios;
	}

	public boolean isVisibleImpuestos() {
		return visibleImpuestos;
	}

	public void setVisibleImpuestos(boolean visibleImpuestos) {
		this.visibleImpuestos = visibleImpuestos;
	}
	
	public boolean isVisibleDestinos() {
		return visibleDestinos;
	}
	public void setVisibleDestinos(boolean visibleDestinos) {
		this.visibleDestinos = visibleDestinos;
	}
	public boolean isVisibleReportReservas() {
		return visibleReportReservas;
	}
	public void setVisibleReportReservas(boolean visibleReportReservas) {
		this.visibleReportReservas = visibleReportReservas;
	}
	public boolean isVisibleReportPagos() {
		return visibleReportPagos;
	}
	public void setVisibleReportPagos(boolean visibleReportPagos) {
		this.visibleReportPagos = visibleReportPagos;
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
	public boolean isCargarAdmin() {
		return cargarAdmin;
	}
	public void setCargarAdmin(boolean cargarAdmin) {
		this.cargarAdmin = cargarAdmin;
	}
	public boolean isVisibleCrearUser() {
		return visibleCrearUser;
	}
	public void setVisibleCrearUser(boolean visibleCrearUser) {
		this.visibleCrearUser = visibleCrearUser;
	}
	
	public boolean isVisibleActualizarUsuario() {
		return visibleActualizarUsuario;
	}
	public void setVisibleActualizarUsuario(boolean visibleActualizarUsuario) {
		this.visibleActualizarUsuario = visibleActualizarUsuario;
	}
	public boolean isVisibleConfExtras() {
		return visibleConfExtras;
	}
	public void setVisibleConfExtras(boolean visibleConfExtras) {
		this.visibleConfExtras = visibleConfExtras;
	}
	public boolean isVisibleCupones() {
		return visibleCupones;
	}
	public void setVisibleCupones(boolean visibleCupones) {
		this.visibleCupones = visibleCupones;
	}
	public boolean isVisibleMenuAndroid() {
		return visibleMenuAndroid;
	}
	public void setVisibleMenuAndroid(boolean visibleMenuAndroid) {
		this.visibleMenuAndroid = visibleMenuAndroid;
	}
	public boolean isVisibleAgencia() {
		return visibleAgencia;
	}
	public void setVisibleAgencia(boolean visibleAgencia) {
		this.visibleAgencia = visibleAgencia;
	}
	@Init
	public void Inicializar() {
		cargarAdmin=false;
		try
		{
			Encryptar encrip= new Encryptar();
//			System.out.println("Aqui esta la contraseña desencriptada-->"+encrip.decrypt("cyS249O3OHZTsG0ww1rYrw=="));
			Execution exec = Executions.getCurrent();
			ses = (HttpSession)Sessions.getCurrent().getNativeSession();
		    String user=(String)ses.getAttribute("usuario");
		    String pas=(String)ses.getAttribute("clave");
		    int perfil=(int)ses.getAttribute("perfil");
		    /******************************************/
		    iniciarPanelAdministrador(user,pas,perfil);
		}
		catch(Exception e)
		{
			irALogin();
		}
	}
	public void irALogin()
	{
		Executions.getCurrent().sendRedirect("/login.zul");
	}
	@Command
	public void cerrarSession()
	{
		ses.removeAttribute("usuario");
		ses.removeAttribute("clave");
		ses.removeAttribute("perfil");
		irALogin();
	}
	public void iniciarPanelAdministrador(String usuario,String password,int codPerfil)
	{
		cargarAdmin=true;
		usuarioDao=new CUsuarioLoginDAO();
		oAcceso=new CAcceso();
		oUsuario=new CUsuarioLogin();
		usuarioDao.asignarAccesosUsuario(usuarioDao.recuperarAccesosUsuario(codPerfil));
		setoAcceso(usuarioDao.getoAcceso());
		
		usuarioDao.asignarUsuario(usuarioDao.recuperarUsuario(usuario, password));
		setoUsuario(usuarioDao.getoUsuario());
		
		ses.setAttribute("usuarioActual",this.oUsuario);
		/********************************/
		visibleDisponibilidad=visibleEtiqueta = visiblePaquetes = visibleServicios = visibleSubServicios = visibleImpuestos =false;
		visibleActividades=visibleHoteles=visibleEstadisticaPagos=visibleEstadisticaPaquetesmasVendidos=false;
		visibleCrearUser=visibleDestinos=visibleReportReservas=visibleReportPagos=visibleActualizarUsuario=visibleConfPaypal=false;
		visibleConfExtras=visibleCupones=visibleConfAltoNivel=false;
		//=======Android========
		visibleMenuAndroid=false;
		visibleAgencia=false;
	}
	//================CAMBIO DE VISIBILIDAD========
	@Command
	@NotifyChange({ "visibleEtiqueta", "visiblePaquetes","visibleDestinos","visibleServicios", "visibleSubServicios",
		"visibleActividades","visibleImpuestos", "visibleMenu", "visibleDisponibilidad","visibleReportReservas","visibleReportPagos",
		"seleccionDisponibilidad","seleccionEtiquetas", "seleccionPaquetes", "seleccionServicios", "seleccionSubServicios", "seleccionImpuestos",
		"visibleConfiguracion","visibleHoteles","seleccionHoteles","seleccionDestinos","seleccionReportReservas",
		"seleccionActividades","seleccionReportPagos","visibleEstadisticaPaquetesmasVendidos","visibleEstadisticaPagos",
		"visibleCrearUser","visibleActualizarUsuario","visibleRegistroUsuarios","visibleConfPaypal","visibleConfExtras",
		"visibleCupones","visibleConfAltoNivel","visibleMenuAndroid","visibleAgencia"})
	public void Cambio(@BindingParam("cambioInterfaz") String cambios) {
		System.out.println("entra a esta parte de mektodo cambio");
		visibleConfiguracion=true;
		if (cambios.equals("itemDisponibilidad") || cambios.equals("btnDisponibilidad") || cambios.equals("tabDisponibilidad") ) {
				visibleEstadisticaPaquetesmasVendidos=visibleRegistroUsuarios=false;
				visibleEstadisticaPagos=visibleActualizarUsuario=visibleConfAltoNivel=false;
				visibleCrearUser=false;
				visibleDisponibilidad=true;
				visibleActividades=visibleConfPaypal=false;
				visibleEtiqueta=visiblePaquetes=visibleServicios=visibleSubServicios=visibleReportReservas=visibleHoteles=false;
				visibleSubServicios=visibleImpuestos=visibleDestinos=visibleReportPagos=false;
				visibleConfExtras=false;
				visibleCupones=false;
				visibleMenuAndroid=false;
				visibleAgencia=false;
			} else if (cambios.equals("itemEtiqueta") || cambios.equals("btnEtiquetas") || cambios.equals("tabEtiqueta") ) {
				visibleEstadisticaPaquetesmasVendidos=visibleRegistroUsuarios=false;
				visibleEstadisticaPagos=visibleActualizarUsuario=visibleConfAltoNivel=false;
				visibleCrearUser=visibleConfPaypal=false;
				visibleEtiqueta = true;
				visibleDisponibilidad=visiblePaquetes=visibleServicios=visibleSubServicios=visibleReportReservas=visibleHoteles=false;
				visibleImpuestos=visibleDestinos=visibleReportPagos=false;
				visibleActividades=false;
				visibleConfExtras=false;
				visibleCupones=false;
				visibleMenuAndroid=false;
				visibleAgencia=false;
			} else if (cambios.equals("itemPaquete") || cambios.equals("btnPaquetes") || cambios.equals("tabPaquete")) {
				visibleEstadisticaPaquetesmasVendidos=visibleRegistroUsuarios=false;
				visibleEstadisticaPagos=visibleActualizarUsuario=visibleConfAltoNivel=false;
				visibleCrearUser=visibleConfPaypal=false;
				visiblePaquetes=true;
				visibleDisponibilidad=visibleEtiqueta=visibleServicios=visibleReportReservas=visibleHoteles=false;
				visibleSubServicios=visibleImpuestos=visibleDestinos=visibleReportPagos=false;
				visibleActividades=false;
				visibleConfExtras=false;
				visibleCupones=false;
				visibleMenuAndroid=false;
				visibleAgencia=false;
			} else if (cambios.equals("itemServicio") || cambios.equals("btnServicios") || cambios.equals("tabServicio")) {
				visibleEstadisticaPaquetesmasVendidos=visibleRegistroUsuarios=false;
				visibleEstadisticaPagos=visibleActualizarUsuario=visibleConfAltoNivel=false;
				visibleCrearUser=visibleConfPaypal=false;
				visibleServicios=true;
				visibleDisponibilidad=visibleEtiqueta=visiblePaquetes=visibleReportReservas=visibleHoteles=false;
				visibleSubServicios=visibleImpuestos=visibleDestinos=visibleReportPagos=false;
				visibleActividades=false;
				visibleConfExtras=false;
				visibleCupones=false;
				visibleMenuAndroid=false;
				visibleAgencia=false;
			} else if (cambios.equals("itemSubServicio") || cambios.equals("btnSubServicios") || cambios.equals("tabSubServicio")) {
				visibleEstadisticaPaquetesmasVendidos=visibleRegistroUsuarios=false;
				visibleEstadisticaPagos=visibleActualizarUsuario=visibleConfAltoNivel=false;
				visibleCrearUser=visibleConfPaypal=false;
				visibleSubServicios =true;
				visibleDisponibilidad=visibleEtiqueta=visiblePaquetes=visibleServicios=visibleReportReservas=visibleHoteles=false;
				visibleImpuestos=visibleDestinos=visibleReportPagos=false;
				visibleActividades=false;
				visibleConfExtras=false;
				visibleCupones=false;
				visibleMenuAndroid=false;
				visibleAgencia=false;
			} else if(cambios.equals("itemActividad")){
				visibleEstadisticaPaquetesmasVendidos=visibleRegistroUsuarios=false;
				visibleEstadisticaPagos=visibleActualizarUsuario=visibleConfAltoNivel=false;
				visibleCrearUser=visibleConfPaypal=false;
				visibleEstadisticaPaquetesmasVendidos=false;
				visibleHoteles=visibleImpuestos=visibleDestinos=visibleReportPagos=visibleEstadisticaPagos=false;
				visibleDisponibilidad=visibleEtiqueta=visiblePaquetes=visibleServicios=visibleReportReservas=false;
				visibleActividades=true;
				visibleSubServicios=false;
				visibleConfExtras=false;
				visibleCupones=false;
				visibleMenuAndroid=false;
				visibleAgencia=false;
			}else if (cambios.equals("itemImpuesto") || cambios.equals("btnImpuestos") || cambios.equals("tabImpuesto")) {
				visibleEstadisticaPaquetesmasVendidos=visibleRegistroUsuarios=false;
				visibleEstadisticaPagos=visibleActualizarUsuario=visibleConfAltoNivel=false;
				visibleCrearUser=visibleConfPaypal=false;
				visibleImpuestos=true;
				visibleDisponibilidad=visibleEtiqueta=visiblePaquetes=visibleServicios=visibleReportReservas=visibleHoteles=false;
				visibleSubServicios=visibleDestinos=visibleReportPagos=false;
				visibleActividades=false;
				visibleConfExtras=false;
				visibleCupones=false;
				visibleMenuAndroid=false;
				visibleAgencia=false;
			}else if (cambios.equals("itemHoteles") || cambios.equals("btnHoteles") || cambios.equals("tabHotel")) {
				visibleEstadisticaPaquetesmasVendidos=visibleRegistroUsuarios=false;
				visibleEstadisticaPagos=visibleActualizarUsuario=visibleConfAltoNivel=false;
				visibleCrearUser=visibleConfPaypal=false;
				visibleHoteles=true;
				visibleImpuestos=false;
				visibleDisponibilidad=visibleEtiqueta=visiblePaquetes=visibleServicios=visibleReportReservas=visibleReportPagos=false;
				visibleSubServicios=visibleDestinos=false;
				visibleActividades=false;
				visibleConfExtras=false;
				visibleCupones=false;
				visibleMenuAndroid=false;
				visibleAgencia=false;
			}else if (cambios.equals("itemDestinos") || cambios.equals("btnDestinos") || cambios.equals("tabDestino")) {
				visibleEstadisticaPaquetesmasVendidos=visibleRegistroUsuarios=false;
				visibleEstadisticaPagos=visibleConfPaypal=visibleConfAltoNivel=false;
				visibleCrearUser=visibleActualizarUsuario=false;
				visibleDestinos=true;
				visibleHoteles=visibleImpuestos=false;
				visibleDisponibilidad=visibleEtiqueta=visiblePaquetes=visibleServicios=visibleReportReservas=false;
				visibleSubServicios=visibleReportPagos=false;
				visibleActividades=false;
				visibleConfExtras=false;
				visibleCupones=false;
				visibleMenuAndroid=false;
				visibleAgencia=false;
			}else if (cambios.equals("itemReporteReservas") || cambios.equals("btnReporteReservas") || cambios.equals("tabReporteReserva")) {
				visibleEstadisticaPaquetesmasVendidos=visibleRegistroUsuarios=false;
				visibleEstadisticaPagos=visibleActualizarUsuario=false;
				visibleCrearUser=visibleConfPaypal=visibleConfAltoNivel=false;
				visibleReportReservas=true;
				visibleHoteles=visibleImpuestos=visibleDestinos=visibleReportPagos=false;
				visibleDisponibilidad=visibleEtiqueta=visiblePaquetes=visibleServicios=visibleSubServicios=false;
				visibleActividades=false;
				visibleConfExtras=false;
				visibleCupones=false;
				visibleMenuAndroid=false;
				visibleAgencia=false;
			}else if (cambios.equals("itemReportePagos") || cambios.equals("btnReportePagos") || cambios.equals("tabReportePagos")) {
				visibleEstadisticaPaquetesmasVendidos=visibleRegistroUsuarios=false;
				visibleEstadisticaPagos=visibleActualizarUsuario=visibleConfAltoNivel=false;
				visibleCrearUser=visibleConfPaypal=false;
				visibleReportPagos=true;
				visibleHoteles=visibleImpuestos=visibleDestinos=false;
				visibleDisponibilidad=visibleEtiqueta=visiblePaquetes=visibleServicios=visibleReportReservas=false;
				visibleActividades=false;
				visibleSubServicios=false;
				visibleConfExtras=false;
				visibleCupones=false;
				visibleMenuAndroid=false;
				visibleAgencia=false;
			}else if (cambios.equals("itemEstadisticaPagos")) {
				visibleEstadisticaPaquetesmasVendidos=visibleRegistroUsuarios=false;
				visibleEstadisticaPagos=visibleActualizarUsuario=visibleConfAltoNivel=false;
				visibleCrearUser=visibleConfPaypal=false;
				visibleEstadisticaPagos=true;
				visibleEstadisticaPaquetesmasVendidos=false;
				visibleHoteles=visibleImpuestos=visibleDestinos=visibleReportPagos=visibleEstadisticaPaquetesmasVendidos=false;
				visibleDisponibilidad=visibleEtiqueta=visiblePaquetes=visibleServicios=visibleReportReservas=false;
				visibleActividades=false;
				visibleSubServicios=false;
				visibleConfExtras=false;
				visibleCupones=false;
				visibleMenuAndroid=false;
				visibleAgencia=false;
			}else if (cambios.equals("itemEstadisticaPaquetesmasVendidos")) {
					visibleCrearUser=visibleActualizarUsuario=visibleRegistroUsuarios=false;
					visibleEstadisticaPaquetesmasVendidos=true;
					visibleEstadisticaPagos=visibleConfPaypal=visibleConfAltoNivel=false;
					visibleHoteles=visibleImpuestos=visibleDestinos=visibleReportPagos=visibleEstadisticaPagos=false;
					visibleDisponibilidad=visibleEtiqueta=visiblePaquetes=visibleServicios=visibleReportReservas=false;
					visibleActividades=false;
					visibleSubServicios=false;
					visibleConfExtras=false;
					visibleCupones=false;
					visibleMenuAndroid=false;
					visibleAgencia=false;
			}else if(cambios.equals("itemCrearUser1")||cambios.equals("itemCrearUser2"))
			{
				visibleEstadisticaPaquetesmasVendidos=visibleActualizarUsuario=visibleRegistroUsuarios=false;
				visibleEstadisticaPagos=visibleConfPaypal=visibleConfAltoNivel=false;
				visibleCrearUser=true;
				visibleEstadisticaPaquetesmasVendidos=false;
				visibleHoteles=visibleImpuestos=visibleDestinos=visibleReportPagos=visibleEstadisticaPagos=false;
				visibleDisponibilidad=visibleEtiqueta=visiblePaquetes=visibleServicios=visibleReportReservas=false;
				visibleActividades=false;
				visibleSubServicios=false;
				visibleConfExtras=false;
				visibleCupones=false;
				visibleMenuAndroid=false;
				visibleAgencia=false;
			}else if(cambios.equals("itemUpdateUsuario2") || cambios.equals("itemUpdateUsuario"))
			{ 
				visibleActualizarUsuario=true;
				visibleEstadisticaPaquetesmasVendidos=visibleRegistroUsuarios=false;
				visibleEstadisticaPagos=visibleConfPaypal=visibleConfAltoNivel=false;
				visibleCrearUser=false;
				visibleEstadisticaPaquetesmasVendidos=false;
				visibleHoteles=visibleImpuestos=visibleDestinos=visibleReportPagos=visibleEstadisticaPagos=false;
				visibleDisponibilidad=visibleEtiqueta=visiblePaquetes=visibleServicios=visibleReportReservas=false;
				visibleActividades=false;
				visibleSubServicios=false;
				visibleConfExtras=false;
				visibleCupones=false;
				visibleMenuAndroid=false;
				visibleAgencia=false;
			}else if(cambios.equals("itemUsuario") || cambios.equals("itemUsuario2"))
			{ 
				visibleRegistroUsuarios=true;
				visibleActualizarUsuario=visibleConfAltoNivel=false;
				visibleEstadisticaPaquetesmasVendidos=false;
				visibleEstadisticaPagos=false;
				visibleCrearUser=visibleConfPaypal=false;
				visibleEstadisticaPaquetesmasVendidos=false;
				visibleHoteles=visibleImpuestos=visibleDestinos=visibleReportPagos=visibleEstadisticaPagos=false;
				visibleDisponibilidad=visibleEtiqueta=visiblePaquetes=visibleServicios=visibleReportReservas=false;
				visibleActividades=false;
				visibleSubServicios=false;
				visibleConfExtras=false;
				visibleCupones=false;
				visibleMenuAndroid=false;
				visibleAgencia=false;
			}else if(cambios.equals("itemPaypal"))
			{ 
				visibleConfPaypal=true;
				visibleRegistroUsuarios=false;
				visibleActualizarUsuario=false;
				visibleEstadisticaPaquetesmasVendidos=false;
				visibleEstadisticaPagos=false;
				visibleCrearUser=false;
				visibleEstadisticaPaquetesmasVendidos=false;
				visibleHoteles=visibleImpuestos=visibleDestinos=visibleReportPagos=visibleEstadisticaPagos=false;
				visibleDisponibilidad=visibleEtiqueta=visiblePaquetes=visibleServicios=visibleReportReservas=false;
				visibleActividades=false;
				visibleSubServicios=false;
				visibleConfExtras=false;
				visibleCupones=false;
				visibleMenuAndroid=false;
				visibleAgencia=false;
			}else if(cambios.equals("itemExtras"))
			{ 
				visibleConfPaypal=visibleConfAltoNivel=false;
				visibleRegistroUsuarios=false;
				visibleActualizarUsuario=false;
				visibleEstadisticaPaquetesmasVendidos=false;
				visibleEstadisticaPagos=false;
				visibleCrearUser=false;
				visibleEstadisticaPaquetesmasVendidos=false;
				visibleHoteles=visibleImpuestos=visibleDestinos=visibleReportPagos=visibleEstadisticaPagos=false;
				visibleDisponibilidad=visibleEtiqueta=visiblePaquetes=visibleServicios=visibleReportReservas=false;
				visibleActividades=false;
				visibleSubServicios=false;
				visibleConfExtras=true;
				visibleCupones=false;
				visibleMenuAndroid=false;
				visibleAgencia=false;
			}else if(cambios.equals("itemCupones"))
			{
				visibleConfPaypal=visibleConfAltoNivel=false;
				visibleRegistroUsuarios=false;
				visibleActualizarUsuario=false;
				visibleEstadisticaPaquetesmasVendidos=false;
				visibleEstadisticaPagos=false;
				visibleCrearUser=false;
				visibleEstadisticaPaquetesmasVendidos=false;
				visibleHoteles=visibleImpuestos=visibleDestinos=visibleReportPagos=visibleEstadisticaPagos=false;
				visibleDisponibilidad=visibleEtiqueta=visiblePaquetes=visibleServicios=visibleReportReservas=false;
				visibleActividades=false;
				visibleSubServicios=false;
				visibleConfExtras=false;
				visibleCupones=true;
				visibleMenuAndroid=false;
				visibleAgencia=false;
			}
			else if(cambios.equals("itemConfSuper"))
			{
				visibleConfPaypal=false;
				visibleRegistroUsuarios=false;
				visibleActualizarUsuario=false;
				visibleEstadisticaPaquetesmasVendidos=false;
				visibleEstadisticaPagos=false;
				visibleCrearUser=false;
				visibleEstadisticaPaquetesmasVendidos=false;
				visibleHoteles=visibleImpuestos=visibleDestinos=visibleReportPagos=visibleEstadisticaPagos=false;
				visibleDisponibilidad=visibleEtiqueta=visiblePaquetes=visibleServicios=visibleReportReservas=false;
				visibleActividades=false;
				visibleSubServicios=false;
				visibleConfExtras=false;
				visibleCupones=false;
				visibleConfAltoNivel=true;
				visibleMenuAndroid=false;
				visibleAgencia=false;
			}else if(cambios.equals("itemMenuAndroid"))
			{
				visibleConfPaypal=false;
				visibleRegistroUsuarios=false;
				visibleActualizarUsuario=false;
				visibleEstadisticaPaquetesmasVendidos=false;
				visibleEstadisticaPagos=false;
				visibleCrearUser=false;
				visibleEstadisticaPaquetesmasVendidos=false;
				visibleHoteles=visibleImpuestos=visibleDestinos=visibleReportPagos=visibleEstadisticaPagos=false;
				visibleDisponibilidad=visibleEtiqueta=visiblePaquetes=visibleServicios=visibleReportReservas=false;
				visibleActividades=false;
				visibleSubServicios=false;
				visibleConfExtras=false;
				visibleCupones=false;
				visibleConfAltoNivel=false;
				visibleMenuAndroid=true;
				visibleAgencia=false;
			}else if(cambios.equals("itemAgencia"))
			{
				visibleConfPaypal=false;
				visibleRegistroUsuarios=false;
				visibleActualizarUsuario=false;
				visibleEstadisticaPaquetesmasVendidos=false;
				visibleEstadisticaPagos=false;
				visibleCrearUser=false;
				visibleEstadisticaPaquetesmasVendidos=false;
				visibleHoteles=visibleImpuestos=visibleDestinos=visibleReportPagos=visibleEstadisticaPagos=false;
				visibleDisponibilidad=visibleEtiqueta=visiblePaquetes=visibleServicios=visibleReportReservas=false;
				visibleActividades=false;
				visibleSubServicios=false;
				visibleConfExtras=false;
				visibleCupones=false;
				visibleConfAltoNivel=false;
				visibleMenuAndroid=false;
				visibleAgencia=true;
			}
	}
}
