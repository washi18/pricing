package com.pricing.model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

import com.pricing.dao.CDestinoDAO;
import com.pricing.dao.CGaleriaHotelDAO;

public class CHotel 
{
	//====================
		private DecimalFormat df;
		private DecimalFormatSymbols simbolos;
	//====================
	private int nHotelCod;// int,					--codigo del hotel
	private String cHotel;// varchar(200),				--nombre del hotel
	private String cDescripcionIdioma1;// text,			--descripcion del hotel en el idioma 1
	private String cDescripcionIdioma2;// text,			--descripcion del hotel en el idioma 2
	private String cDescripcionIdioma3;// text,			--descripcion del hotel en el idioma 3
	private String cDescripcionIdioma4;// text,			--descripcion del hotel en el idioma 4
	private String cDescripcionIdioma5;// text,			--descripcion del hotel en el idioma 5
	private String cUrl;// varchar(200),				--url de la pagina web del hotel o contenido en nuestra pagina
	private int categoriaHotelCod;// int,				--codigo de la categoria al que pertenece el hotel
	private Number nPrecioSimple;// decimal(10,2),			--precio del hotel con habitacion simple
	private Number nPrecioDoble;// decimal(10,2),			--precio del hotel con habitacion doble
	private Number nPrecioTriple;// decimal(10,2),			--precio del hotel con habitacion triple
	private boolean bEstado;// boolean,				--estado del hotel (si trabaja o no con nuestros paquetes)
	private Number nPrecioCamaAdicional;//decimal(10,2),
	private int nDestinoCod;//int
	private String cFoto1;//varchar
	private String cFoto2;//varchar
	private String cFoto3;//varchar
	private String cFoto4;//varchar
	private String cFoto5;//varchar
	private String cImagenUbicacion;//varchar
	private String imagenHotel;
	private String descripcion;
	private boolean conCamaAdicional;
	private String nombreDestino;
	private boolean editable;
	private String categoria;
	private boolean visibleEspanol;
	private boolean visibleIngles;
	private boolean visiblePortugues;
	private String nPrecioSimple_text;
	private String nPrecioDoble_text;
	private String nPrecioTriple_text;
	private String nPrecioCamaAdicional_text;
	private boolean select_eco;
	private boolean select_tur;
	private boolean select_turSup;
	private boolean select_pri;
	private boolean select_priSup;
	private boolean select_lujo;
	private boolean select_lujoSup;
	private String color_btn_activo;
	private String color_btn_desactivo;
	public String COLOR_ACTIVO="background:#3BA420;";
	public String COLOR_DESACTIVO="background:#DA0613;";
	public String COLOR_TRANSPARENT="background:transparent;";
	private boolean estado_activo;
	private boolean estado_desactivo;
	private boolean abrirEditor;
	private CDestinoDAO destinoDao;
	private ArrayList<CDestino> listaDestinos;
	private ArrayList<CGaleriaHotel> listaImagenes;
	//================================
	public int getnHotelCod() {
		return nHotelCod;
	}
	public void setnHotelCod(int nHotelCod) {
		this.nHotelCod = nHotelCod;
	}
	public String getcHotel() {
		return cHotel;
	}
	public void setcHotel(String cHotel) {
		this.cHotel = cHotel;
	}
	public String getcDescripcionIdioma1() {
		return cDescripcionIdioma1;
	}
	public void setcDescripcionIdioma1(String cDescripcionIdioma1) {
		this.cDescripcionIdioma1 = cDescripcionIdioma1;
	}
	public String getcDescripcionIdioma2() {
		return cDescripcionIdioma2;
	}
	public void setcDescripcionIdioma2(String cDescripcionIdioma2) {
		this.cDescripcionIdioma2 = cDescripcionIdioma2;
	}
	public String getcDescripcionIdioma3() {
		return cDescripcionIdioma3;
	}
	public void setcDescripcionIdioma3(String cDescripcionIdioma3) {
		this.cDescripcionIdioma3 = cDescripcionIdioma3;
	}
	public String getcDescripcionIdioma4() {
		return cDescripcionIdioma4;
	}
	public void setcDescripcionIdioma4(String cDescripcionIdioma4) {
		this.cDescripcionIdioma4 = cDescripcionIdioma4;
	}
	public String getcDescripcionIdioma5() {
		return cDescripcionIdioma5;
	}
	public void setcDescripcionIdioma5(String cDescripcionIdioma5) {
		this.cDescripcionIdioma5 = cDescripcionIdioma5;
	}
	public String getcUrl() {
		return cUrl;
	}
	public void setcUrl(String cUrl) {
		this.cUrl = cUrl;
	}
	public int getCategoriaHotelCod() {
		return categoriaHotelCod;
	}
	public void setCategoriaHotelCod(int categoriaHotelCod) {
		this.categoriaHotelCod = categoriaHotelCod;
	}
	public Number getnPrecioSimple() {
		return nPrecioSimple;
	}
	public void setnPrecioSimple(Number nPrecioSimple) {
		this.nPrecioSimple = nPrecioSimple;
	}
	public Number getnPrecioDoble() {
		return nPrecioDoble;
	}
	public void setnPrecioDoble(Number nPrecioDoble) {
		this.nPrecioDoble = nPrecioDoble;
	}
	public Number getnPrecioTriple() {
		return nPrecioTriple;
	}
	public void setnPrecioTriple(Number nPrecioTriple) {
		this.nPrecioTriple = nPrecioTriple;
	}
	public boolean isbEstado() {
		return bEstado;
	}
	public void setbEstado(boolean bEstado) {
		this.bEstado = bEstado;
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public boolean isVisibleEspanol() {
		return visibleEspanol;
	}
	public void setVisibleEspanol(boolean visibleEspanol) {
		this.visibleEspanol = visibleEspanol;
	}
	public boolean isVisibleIngles() {
		return visibleIngles;
	}
	public void setVisibleIngles(boolean visibleIngles) {
		this.visibleIngles = visibleIngles;
	}
	public boolean isVisiblePortugues() {
		return visiblePortugues;
	}
	public void setVisiblePortugues(boolean visiblePortugues) {
		this.visiblePortugues = visiblePortugues;
	}
	public String getnPrecioSimple_text() {
		return nPrecioSimple_text;
	}
	public void setnPrecioSimple_text(String nPrecioSimple_text) {
		this.nPrecioSimple_text = nPrecioSimple_text;
	}
	public String getnPrecioDoble_text() {
		return nPrecioDoble_text;
	}
	public void setnPrecioDoble_text(String nPrecioDoble_text) {
		this.nPrecioDoble_text = nPrecioDoble_text;
	}
	public String getnPrecioTriple_text() {
		return nPrecioTriple_text;
	}
	public void setnPrecioTriple_text(String nPrecioTriple_text) {
		this.nPrecioTriple_text = nPrecioTriple_text;
	}
	public boolean isSelect_eco() {
		return select_eco;
	}
	public void setSelect_eco(boolean select_eco) {
		this.select_eco = select_eco;
	}
	public boolean isSelect_tur() {
		return select_tur;
	}
	public void setSelect_tur(boolean select_tur) {
		this.select_tur = select_tur;
	}
	public boolean isSelect_turSup() {
		return select_turSup;
	}
	public void setSelect_turSup(boolean select_turSup) {
		this.select_turSup = select_turSup;
	}
	public boolean isSelect_pri() {
		return select_pri;
	}
	public void setSelect_pri(boolean select_pri) {
		this.select_pri = select_pri;
	}
	public boolean isSelect_priSup() {
		return select_priSup;
	}
	public void setSelect_priSup(boolean select_priSup) {
		this.select_priSup = select_priSup;
	}
	public boolean isSelect_lujo() {
		return select_lujo;
	}
	public void setSelect_lujo(boolean select_lujo) {
		this.select_lujo = select_lujo;
	}
	public boolean isSelect_lujoSup() {
		return select_lujoSup;
	}
	public void setSelect_lujoSup(boolean select_lujoSup) {
		this.select_lujoSup = select_lujoSup;
	}
	public String getColor_btn_activo() {
		return color_btn_activo;
	}
	public void setColor_btn_activo(String color_btn_activo) {
		this.color_btn_activo = color_btn_activo;
	}
	public String getColor_btn_desactivo() {
		return color_btn_desactivo;
	}
	public void setColor_btn_desactivo(String color_btn_desactivo) {
		this.color_btn_desactivo = color_btn_desactivo;
	}
	public boolean isEstado_activo() {
		return estado_activo;
	}
	public void setEstado_activo(boolean estado_activo) {
		this.estado_activo = estado_activo;
	}
	public boolean isEstado_desactivo() {
		return estado_desactivo;
	}
	public void setEstado_desactivo(boolean estado_desactivo) {
		this.estado_desactivo = estado_desactivo;
	}
	public int getnDestinoCod() {
		return nDestinoCod;
	}
	public void setnDestinoCod(int nDestinoCod) {
		this.nDestinoCod = nDestinoCod;
	}
	
	public String getNombreDestino() {
		return nombreDestino;
	}
	public void setNombreDestino(String nombreDestino) {
		this.nombreDestino = nombreDestino;
	}
	public ArrayList<CDestino> getListaDestinos() {
		return listaDestinos;
	}
	public void setListaDestinos(ArrayList<CDestino> listaDestinos) {
		this.listaDestinos = listaDestinos;
	}
	public Number getnPrecioCamaAdicional() {
		return nPrecioCamaAdicional;
	}
	public void setnPrecioCamaAdicional(Number nPrecioCamaAdicional) {
		this.nPrecioCamaAdicional = nPrecioCamaAdicional;
	}
	public boolean isConCamaAdicional() {
		return conCamaAdicional;
	}
	public void setConCamaAdicional(boolean conCamaAdicional) {
		this.conCamaAdicional = conCamaAdicional;
	}
	public String getnPrecioCamaAdicional_text() {
		return nPrecioCamaAdicional_text;
	}
	public void setnPrecioCamaAdicional_text(String nPrecioCamaAdicional_text) {
		this.nPrecioCamaAdicional_text = nPrecioCamaAdicional_text;
	}
	public String getcFoto1() {
		return cFoto1;
	}
	public void setcFoto1(String cFoto1) {
		this.cFoto1 = cFoto1;
	}
	public String getcFoto2() {
		return cFoto2;
	}
	public void setcFoto2(String cFoto2) {
		this.cFoto2 = cFoto2;
	}
	public String getcFoto3() {
		return cFoto3;
	}
	public void setcFoto3(String cFoto3) {
		this.cFoto3 = cFoto3;
	}
	public String getcFoto4() {
		return cFoto4;
	}
	public void setcFoto4(String cFoto4) {
		this.cFoto4 = cFoto4;
	}
	public String getcFoto5() {
		return cFoto5;
	}
	public void setcFoto5(String cFoto5) {
		this.cFoto5 = cFoto5;
	}
	public String getcImagenUbicacion() {
		return cImagenUbicacion;
	}
	public void setcImagenUbicacion(String cImagenUbicacion) {
		this.cImagenUbicacion = cImagenUbicacion;
	}
	public String getImagenHotel() {
		return imagenHotel;
	}
	public void setImagenHotel(String imagenHotel) {
		this.imagenHotel = imagenHotel;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public boolean isAbrirEditor() {
		return abrirEditor;
	}
	public void setAbrirEditor(boolean abrirEditor) {
		this.abrirEditor = abrirEditor;
	}
	public ArrayList<CGaleriaHotel> getListaImagenes() {
		return listaImagenes;
	}
	public void setListaImagenes(ArrayList<CGaleriaHotel> listaImagenes) {
		this.listaImagenes = listaImagenes;
	}
	//======================================
	public CHotel() {
		// TODO Auto-generated constructor stub
		this.cHotel ="";
		this.cDescripcionIdioma1 ="";
		this.cDescripcionIdioma2 ="";
		this.cDescripcionIdioma3 ="";
		this.cDescripcionIdioma4 ="";
		this.cDescripcionIdioma5 ="";
		this.cUrl ="";
		this.categoriaHotelCod =0;
		this.nPrecioSimple =0;
		this.nPrecioDoble =0;
		this.nPrecioTriple =0;
		this.nPrecioCamaAdicional=0;
		this.nPrecioSimple_text="0.00";
		this.nPrecioDoble_text="0.00";
		this.nPrecioTriple_text="0.00";
		this.nPrecioCamaAdicional_text="0.00";
		this.nDestinoCod=0;
		this.nombreDestino="";
		this.cFoto1="img/hoteles/hotelxdefecto.jpg";
		this.cFoto2="img/hoteles/hotelxdefecto.jpg";
		this.cFoto3="img/hoteles/hotelxdefecto.jpg";
		this.cFoto4="img/hoteles/hotelxdefecto.jpg";
		this.cFoto5="img/hoteles/hotelxdefecto.jpg";
		this.cImagenUbicacion="";
		this.abrirEditor=false;
		this.listaImagenes=new ArrayList<CGaleriaHotel>();
	}
	public CHotel(String cHotel){
		this.cHotel=cHotel;
	}
	public CHotel(String cHotel,Number precioSimple)
	{
		this.cHotel=cHotel;
		this.nPrecioSimple=precioSimple;
	}
	public CHotel(String cHotel,Number precioSimple,Number precioDoble,Number precioTriple, String cDestino)
	{
		this.cHotel=cHotel;
		this.nPrecioSimple=precioSimple;
		this.nPrecioDoble=precioDoble;
		this.nPrecioTriple=precioTriple;
		this.nombreDestino=cDestino;
	}
	public CHotel(int nHotelCod, String cHotel, String cDescripcionIdioma1,
			String cDescripcionIdioma2, String cDescripcionIdioma3,
			String cDescripcionIdioma4, String cDescripcionIdioma5,
			String cUrl, String categoriaHotel, Number nPrecioSimple,
			Number nPrecioDoble, Number nPrecioTriple, boolean bEstado,String cdestino) {
		this.nHotelCod = nHotelCod;
		this.cHotel = cHotel;
		this.cDescripcionIdioma1 = cDescripcionIdioma1;
		this.cDescripcionIdioma2 = cDescripcionIdioma2;
		this.cDescripcionIdioma3 = cDescripcionIdioma3;
		this.cDescripcionIdioma4 = cDescripcionIdioma4;
		this.cDescripcionIdioma5 = cDescripcionIdioma5;
		this.cUrl = cUrl;
		this.categoria = categoriaHotel;
		this.nPrecioSimple = nPrecioSimple;
		this.nPrecioDoble = nPrecioDoble;
		this.nPrecioTriple = nPrecioTriple;
		this.bEstado = bEstado;
		this.nombreDestino=cdestino;
	}
	public CHotel(int nHotelCod, String cHotel, String cDescripcionIdioma1,
			String cDescripcionIdioma2, String cDescripcionIdioma3,
			String cDescripcionIdioma4, String cDescripcionIdioma5,
			String cUrl, int categoriaHotelCod, Number nPrecioSimple,
			Number nPrecioDoble, Number nPrecioTriple, boolean bEstado,
			Number nPrecioCamaAdicional,int nDestinoCod,String cFoto1,
			String cFoto2,String cFoto3,String cFoto4,String cFoto5,
			String cImagenUbicacion) {
		
		/*******************************/
		simbolos= new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		df=new DecimalFormat("########0.00",simbolos);
		/*******************************/
		this.nHotelCod = nHotelCod;
		this.cHotel = cHotel;
		this.cDescripcionIdioma1 = cDescripcionIdioma1;
		this.cDescripcionIdioma2 = cDescripcionIdioma2;
		this.cDescripcionIdioma3 = cDescripcionIdioma3;
		this.cDescripcionIdioma4 = cDescripcionIdioma4;
		this.cDescripcionIdioma5 = cDescripcionIdioma5;
		this.cUrl = cUrl;
		this.categoriaHotelCod = categoriaHotelCod;
		this.nPrecioSimple = nPrecioSimple;
		this.nPrecioDoble = nPrecioDoble;
		this.nPrecioTriple = nPrecioTriple;
		this.bEstado = bEstado;
		this.nPrecioCamaAdicional = nPrecioCamaAdicional;
		this.cFoto1=cFoto1;
		this.cFoto2=cFoto2;
		this.cFoto3=cFoto3;
		this.cFoto4=cFoto4;
		this.cFoto5=cFoto5;
		this.cImagenUbicacion=cImagenUbicacion;
		//==Datos no obtenidos directamente de la base de datos==
		this.abrirEditor=false;
		this.imagenHotel=cFoto1;
		this.descripcion=cDescripcionIdioma1;
		this.conCamaAdicional=false;
		this.editable=false;
		this.categoria=obtenerNombreCategoria(categoriaHotelCod);
		this.visibleEspanol=true;
		this.visibleIngles=false;
		this.visiblePortugues=false;
		this.nPrecioSimple_text=df.format(nPrecioSimple.doubleValue());
		this.nPrecioDoble_text=df.format(nPrecioDoble.doubleValue());
		this.nPrecioTriple_text=df.format(nPrecioTriple.doubleValue());
		this.nPrecioCamaAdicional_text=df.format(nPrecioCamaAdicional.doubleValue());
		this.estado_activo=bEstado;
		this.estado_desactivo=!bEstado;
		this.nDestinoCod=nDestinoCod;
		this.destinoDao=new CDestinoDAO();
		this.listaDestinos=new ArrayList<CDestino>();
		this.listaImagenes=new ArrayList<CGaleriaHotel>();
		/**Recuperando la lista de destinos para este hotel**/
		destinoDao.asignarListaDestinos(destinoDao.recuperarListaDestinosBD());
		setListaDestinos(destinoDao.getListaDestinos());
		this.nombreDestino=recuperarElNombreDeDestino(nDestinoCod);
		/**Recuperando la lista de imagenes del hotel**/
		CGaleriaHotelDAO galeriaHotelDao=new CGaleriaHotelDAO();
		galeriaHotelDao.asignarListaImagenesHotel(galeriaHotelDao.recuperarImagenesHotelBD(nHotelCod));
		setListaImagenes(galeriaHotelDao.getListaImagenesHotel());
		/*******Activar la categoria seleccionada*********/
		darColor_estado_hotel();
		activarCategoria();
	}
	public void darColor_estado_hotel()
	{
		if(bEstado)
		{
			color_btn_activo=COLOR_ACTIVO;
			color_btn_desactivo=COLOR_TRANSPARENT;
		}
		else{
			color_btn_activo=COLOR_TRANSPARENT;
			color_btn_desactivo=COLOR_DESACTIVO;
		}
	}
	public void activarCategoria()
	{	select_eco=select_tur=select_turSup=select_pri=select_priSup=select_lujo=select_lujoSup=false;
		if(categoriaHotelCod==1)
			select_eco=true;
		else if(categoriaHotelCod==2)
			select_tur=true;
		else if(categoriaHotelCod==3)
			select_turSup=true;
		else if(categoriaHotelCod==4)
			select_pri=true;
		else if(categoriaHotelCod==5)
			select_priSup=true;
		else if(categoriaHotelCod==6)
			select_lujo=true;
		else if(categoriaHotelCod==7)
			select_lujoSup=true;
	}
	public String obtenerNombreCategoria(int codCat)
	{
		String cat="";
		switch(codCat)
		{
			case 1:cat="ECONOMICO";break;
			case 2:cat="TURISTICO";break;
			case 3:cat="TURISTICO SUPERIOR";break;
			case 4:cat="PRIMERA";break;
			case 5:cat="PRIMERA SUPERIOR";break;
			case 6:cat="LUJO";break;
			case 7:cat="LUJO SUPERIOR";break;
		}
		return cat;
	}
	public String recuperarElNombreDeDestino(int nDestinoCod)
	{
		String nombre="";
		for(CDestino destino:listaDestinos)
		{
			if(destino.getnDestinoCod()==nDestinoCod)
			{
				nombre=destino.getcDestino();
				break;
			}
		}
		return nombre;
	}
}
