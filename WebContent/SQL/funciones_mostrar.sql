 /****** mostrar timpuesto****/
 create or replace function Pricing_sp_MostrarImpuesto()
	returns setof TImpuesto as
$$
	select * from TImpuesto;
$$
language sql;
 /****** mostrar reservaspagados****/
 create or replace function Pricing_sp_BuscarReservasPagados
(
	fechaInicio varchar(12),
	fechaFin varchar(12),
	estadoPago varchar(10)
)
RETURNS table (creservacod varchar(12),dfechainicio Date,dfechafin Date,dfecha timestamp,categoriahotelcod int,npreciopaquetepersona numeric,nnropersonas int,ctituloidioma1 varchar(200),
cmetodopago varchar(20),ccodtransaccion varchar(20),cestado varchar(20)) AS
$$
		
		select r.creservacod,r.dfechainicio,r.dfechafin,r.dfecha,COALESCE( ch.categoriahotelcod, 0 ),r.npreciopaquetepersona,r.nnropersonas,paq.ctituloidioma1,
		r.cmetodopago,r.ccodtransaccion,r.cestado
				from treserva as r 
				left join treservapaquete as rp on(r.creservacod=rp.creservacod)
				left join tpaquete as paq on(rp.cpaquetecod=paq.cpaquetecod)
				left join treservapaquetecategoriahotel as rpch on(rpch.nreservapaquetecod=rp.nreservapaquetecod)
				left join tpaquetecategoriahotel as pch on(pch.codpaquetecategoriah=rpch.codpaquetecategoriah)
				left join tcategoriahotel as ch on(ch.categoriahotelcod=pch.categoriahotelcod)
				where (to_date($1,'yyyy-MM-dd')<=r.dfecha and r.dfecha<=(to_date($2,'yyyy-MM-dd'))+'1 day'::interval) and r.cestado=$3
				group by r.creservacod,r.dfechainicio,r.dfechafin,r.dfecha,ch.categoriahotelcod,r.npreciopaquetepersona,r.nnropersonas,paq.ctituloidioma1,r.cmetodopago,
					 r.ccodtransaccion,r.cestado
				order by r.dfecha desc;
$$
  LANGUAGE sql;
  
  /****** mostrar reservaspagados****/
 create or replace function Pricing_sp_BuscarReservasPagadosAmbos
(
	fechaInicio varchar(12),
	fechaFin varchar(12)
)
RETURNS table (creservacod varchar(12),dfechainicio Date,dfechafin Date,dfecha timestamp,categoriahotelcod int,npreciopaquetepersona numeric,nnropersonas int,ctituloidioma1 varchar(200),
cmetodopago varchar(20),ccodtransaccion varchar(20),cestado varchar(20)) AS
$$
		
		select r.creservacod,r.dfechainicio,r.dfechafin,r.dfecha,COALESCE( ch.categoriahotelcod, 0 ),r.npreciopaquetepersona,r.nnropersonas,paq.ctituloidioma1,
		r.cmetodopago,r.ccodtransaccion,r.cestado
				from treserva as r 
				left join treservapaquete as rp on(r.creservacod=rp.creservacod)
				left join tpaquete as paq on(rp.cpaquetecod=paq.cpaquetecod)
				left join treservapaquetecategoriahotel as rpch on(rpch.nreservapaquetecod=rp.nreservapaquetecod)
				left join tpaquetecategoriahotel as pch on(pch.codpaquetecategoriah=rpch.codpaquetecategoriah)
				left join tcategoriahotel as ch on(ch.categoriahotelcod=pch.categoriahotelcod)
				where (to_date($1,'yyyy-MM-dd')<=r.dfecha and r.dfecha<=(to_date($2,'yyyy-MM-dd'))+'1 day'::interval) and (r.cestado='PAGO PARCIAL' or r.cestado='PAGO TOTAL')
				group by r.creservacod,r.dfechainicio,r.dfechafin,r.dfecha,ch.categoriahotelcod,r.npreciopaquetepersona,r.nnropersonas,paq.ctituloidioma1,r.cmetodopago,
					 r.ccodtransaccion,r.cestado
				order by r.dfecha desc;
$$
  LANGUAGE sql;

   /****** mostrar reservaspagados iniciales****/
  create or replace function Pricing_sp_BuscarReservasPagadosInicial
(
	fechaActual varchar(12)
)
RETURNS table (creservacod varchar(12),dfechainicio Date,dfechafin Date,dfecha timestamp,categoriahotelcod int,npreciopaquetepersona numeric,nnropersonas int,ctituloidioma1 varchar(200),
cmetodopago varchar(20),ccodtransaccion varchar(20),cestado varchar(20)) AS
$$
		
		select r.creservacod,r.dfechainicio,r.dfechafin,r.dfecha,COALESCE( ch.categoriahotelcod, 0 ),r.npreciopaquetepersona,r.nnropersonas,paq.ctituloidioma1,
		r.cmetodopago,r.ccodtransaccion,r.cestado
				from treserva as r 
				left join treservapaquete as rp on(r.creservacod=rp.creservacod)
				left join tpaquete as paq on(rp.cpaquetecod=paq.cpaquetecod)
				left join treservapaquetecategoriahotel as rpch on(rpch.nreservapaquetecod=rp.nreservapaquetecod)
				left join tpaquetecategoriahotel as pch on(pch.codpaquetecategoriah=rpch.codpaquetecategoriah)
				left join tcategoriahotel as ch on(ch.categoriahotelcod=pch.categoriahotelcod)
				where (r.dfecha<=(to_date($1,'yyyy-MM-dd'))+'1 day'::interval) and (r.cestado='PAGO PARCIAL' or r.cestado='PAGO TOTAL')
				group by r.creservacod,r.dfechainicio,r.dfechafin,r.dfecha,ch.categoriahotelcod,r.npreciopaquetepersona,r.nnropersonas,paq.ctituloidioma1,r.cmetodopago,
					 r.ccodtransaccion,r.cestado
				order by r.dfecha desc
				limit 10;
$$
  LANGUAGE sql;
/****** mostrar configuraciones de alto nivel****/
CREATE OR REPLACE FUNCTION Pricing_sp_MostrarConfAltoNivel
(
	nombreEntidad varchar(30)
)
returns setof Tconfigaltonivel as
$$
	select * from Tconfigaltonivel where cnombreentidad=$1;
$$
language sql;
/**MOSTRAR LISTA DE IMAGENES DEL PAQUETE**/
CREATE OR REPLACE FUNCTION Pricing_sp_MostrarImagenesPaquete
(
	codPaquete varchar(10)
)
returns setof TGaleriaPaquete as
$$
	select * from TGaleriaPaquete where cpaquetecod=$1;
$$
language sql;
/**MOSTRAR LISTA DE IMAGENES DEL HOTEL**/
CREATE OR REPLACE FUNCTION Pricing_sp_MostrarImagenesHotel
(
	codHotel int
)
returns setof TGaleriaHotel as
$$
	select * from TGaleriaHotel where nHotelCod=$1;
$$
language sql;
/**MOSTRAR CUPON EXISTENTE**/
CREATE OR REPLACE FUNCTION Pricing_sp_MostrarCuponExistente
(
	cupon varchar(10)
)
returns setof TCupon as
$$
	select * from TCupon
		where ccupon=$1 and now()<=dfechafin;
$$
language sql;
/**MOSTRAR CUPON**/
CREATE OR REPLACE FUNCTION Pricing_sp_MostrarCupon
(
	cupon varchar(10)
)
returns setof TCupon as
$$
	select * from TCupon
		where ccupon=$1 and (now()>=dfechainicio and now()<=dfechafin);
$$
language sql;
/**MOSTRAR LISTA DE CUPONES**/
CREATE OR REPLACE FUNCTION Pricing_sp_MostrarCupones()
returns setof TCupon as
$$
	select * from TCupon
		where now()<=dfechafin;
$$
language sql;
/*++MOSTRAR LOS PAISES++*/
CREATE OR REPLACE FUNCTION Pricing_sp_MostrarPaises()
	returns setof TPais as
$$
	select * from tpais;
$$
language sql;
/*++MOSTRAR LOS DATOS QUE HAY EN LA TABLA TCorreoSMTP++*/
CREATE OR REPLACE FUNCTION Pricing_sp_MostrarCorreoSMTP()
	returns setof TCorreoSMTP as
$$
	select * from tcorreosmtp;
$$
language sql;
/*++MOSTRAR LOS DATOS QUE HAY EN LA TABLA TConfigURL++*/
create or replace function Pricing_sp_MostrarConfigURL()
	returns setof TConfigURL as
$$
	select * from tconfigurl;
$$
language sql;
/*++MOSTRAR LOS DATOS DE CONFIGURACION DE LA INTERFAZ++*/
create or replace function Pricing_sp_MostrarInterfaz()
	returns setof TInterfaz as
$$
	select * from TInterfaz;
$$
language sql;
/*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_MostrarPaquete
Utilizado en	:Aplicacion Web FootPathPeru
Usuario		:
Fecha Creacion	:05/19/2016
Ejecucion	:SELECT * FROM AG_sp_RegistrarReserva('','20150410','20150430','43027528','luis@gmail.com','984289670',3,'informacion')
Eliminacion	:DROP FUNCTION AG_sp_RegistrarReserva(varchar(10),date,date,varchar(12),varchar(100),varchar(50),int,varchar(300))
Comentario	:Registrar una reserva
Modificacion	:
+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE FUNCTION Pricing_sp_MostrarPaquete(codPaquete varchar(10))
  RETURNS SETOF tpaquete AS
$$
	select * from tpaquete where cpaquetecod=$1 and bestado=true;
$$
  LANGUAGE sql;
/*+++++++++++++++++++++++++++++++++++++++++++++++*/
  create or replace function Pricing_sp_MostrarPaqueteActividades(codPaquete varchar(10))
  RETURNS SETOF tpaqueteactividad AS
$$
	select * from tpaqueteactividad where cpaquetecod=$1;
$$
  LANGUAGE sql;
/*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_MostrarImpuesto
Utilizado en	:Aplicacion Web FootPathPeru
Usuario		:
Fecha Creacion	:08/04/2016
Ejecucion	:SELECT * FROM AG_sp_RegistrarReserva('','20150410','20150430','43027528','luis@gmail.com','984289670',3,'informacion')
Eliminacion	:DROP FUNCTION AG_sp_RegistrarReserva(varchar(10),date,date,varchar(12),varchar(100),varchar(50),int,varchar(300))
Comentario	:Mostrar los impuesto
Modificacion	:
+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE FUNCTION Pricing_sp_MostrarImpuesto()
  RETURNS SETOF timpuesto AS
$$
	select * from timpuesto;
$$
  LANGUAGE sql;
/*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_MostrarEtiquetas
Utilizado en	:Pagina Web PGT
Usuario		:PGT
Fecha Creacion	:6/07/2016
Ejecucion	:SELECT * FROM Pricing_sp_MostrarEtiquetas()
Eliminacion	:DROP FUNCTION Pricing_sp_MostrarEtiquetas()
Comentario	:Retorna todas las etiquetas del Pricing
Modificacion	:
+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE function Pricing_sp_MostrarEtiquetas
()
returns setof tetiqueta as 
$$
	select * from tetiqueta
	order by codEtiqueta;
$$ 
LANGUAGE SQL;
/*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_MostrarServicios
Utilizado en	:Pagina Web PGT
Usuario		:PGT
Fecha Creacion	:6/07/2016
Ejecucion	:SELECT * FROM Pricing_sp_MostrarEtiquetas()
Eliminacion	:DROP FUNCTION Pricing_sp_MostrarEtiquetas()
Comentario	:Retorna todas las etiquetas del Pricing
Modificacion	:
+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE function Pricing_sp_MostrarServicios
()
returns setof tservicio as 
$$
	select * from tservicio where bestado=true
	order by nserviciocod;
$$ 
LANGUAGE SQL;
/*+++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE FUNCTION Pricing_sp_MostrarTodosServicios()
  RETURNS SETOF tservicio AS
$$
	select * from tservicio
	order by nserviciocod;
$$
  LANGUAGE sql;
/*+++++++++++++++++++++++++++++++++++++++++++++++*/
create or replace function Pricing_sp_MostrarTodasActividades()
RETURNS SETOF tactividad AS
$$
	select * from tactividad
	order by nactividadcod;
$$
  LANGUAGE sql;
 /*++++++++++++++++++++++++++++++++++++++++++++++*/
  create or replace function Pricing_sp_MostrarAniosCalendario
  (
  	codPaquete varchar(10)
  )
  returns setof tcalendario as
  $$
  	select * from tcalendario where cpaquetecod=$1
  	order by nanio;
  $$
  language sql;
 /*++++++++++++++++++++++++++++++++++*/
  create or replace function Pricing_sp_MostrarDiasAnioCalendario
  (
  	codCalendario int
  )
  returns setof tdia as
  $$
  	select * from tdia where ncalendariocod=$1 or ncalendariocod=($1+1)
  	order by ncalendariocod,nmes;
  $$
  language sql;
 /*++++++++++++++++++++++++++++++++++*/
  create or replace function Pricing_sp_MostrarAcceso(codPerfil int)
  returns setof tacceso as
  $$
  	select * from tacceso
  	where nperfilcod=$1;
  $$
  language sql;
/*+++++++++++++++++++++++++++++++++++++*/
  create or replace function Pricing_sp_MostrarTodosAccesos()
  returns setof tacceso as
  $$
  	select * from tacceso
  	order by naccesocod;
  $$
  language sql;
/*+++++++++++++++++++++++++++++++++++++*/
 create or replace function Pricing_sp_MostrarActividades()
RETURNS SETOF tactividad AS
$$
	select * from tactividad
	where bestado=true
	order by nactividadcod;
$$
  LANGUAGE sql;
 /*+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE function Pricing_sp_MostrarServiciosconSubServicios
()
returns setof tservicio as 
$$
	select * from tservicio where crestriccionyesno=0 and crestriccionnum=0
	order by nserviciocod;
$$ 
LANGUAGE SQL;
/*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_MostrarPaquetes
Utilizado en	:Pagina Web PGT
Usuario		:PGT
Fecha Creacion	:6/07/2016
Ejecucion	:SELECT * FROM Pricing_sp_MostrarEtiquetas()
Eliminacion	:DROP FUNCTION Pricing_sp_MostrarEtiquetas()
Comentario	:Retorna todas las etiquetas del Pricing
Modificacion	:
+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE function Pricing_sp_MostrarPaquetes
()
returns setof tpaquete as 
$$
	select * from tpaquete where bestado=true
	order by cpaquetecod;
$$ 
LANGUAGE SQL;
/*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_MostrarSubServicios
Utilizado en	:Pagina Web PGT
Usuario		:PGT
Fecha Creacion	:6/07/2016
Ejecucion	:SELECT * FROM Pricing_sp_MostrarEtiquetas()
Eliminacion	:DROP FUNCTION Pricing_sp_MostrarEtiquetas()
Comentario	:Retorna todas las etiquetas del Pricing
Modificacion	:
+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE function Pricing_sp_MostrarSubServicios
()
returns setof tsubservicio as 
$$
	select * from tsubservicio where bestado=true
	order by nsubserviciocod;
$$ 
LANGUAGE SQL;
/*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_MostrarTodosHoteles
Utilizado en	:Pagina Web PGT
Usuario		:PGT
Fecha Creacion	:6/07/2016
Ejecucion	:SELECT * FROM Pricing_sp_MostrarEtiquetas()
Eliminacion	:DROP FUNCTION Pricing_sp_MostrarEtiquetas()
Comentario	:Retorna todas las etiquetas del Pricing
Modificacion	:
+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE function Pricing_sp_MostrarTodosHoteles
()
returns setof thotel as 
$$
	select * from thotel
	order by nhotelcod;
$$ 
LANGUAGE SQL;
/*+++++++++++++++++++++++++++++++*/
create or replace function Pricing_sp_MostrarTodosSuServicios
()
returns setof tsubservicio as
$$
	select * from tsubservicio
	order by nsubserviciocod;
$$ 
LANGUAGE SQL;
/*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_MostrarImpuesto
Utilizado en	:Pagina Web PGT
Usuario		:PGT
Fecha Creacion	:6/07/2016
Ejecucion	:SELECT * FROM Pricing_sp_MostrarEtiquetas()
Eliminacion	:DROP FUNCTION Pricing_sp_MostrarEtiquetas()
Comentario	:Retorna todas las etiquetas del Pricing
Modificacion	:
+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE function Pricing_sp_MostrarImpuesto
()
returns setof timpuesto as 
$$
	select * from timpuesto;
$$ 
LANGUAGE SQL;
/*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_MostrarDestino
Utilizado en	:Pagina Web PGT
Usuario		:PGT
Fecha Creacion	:6/07/2016
Ejecucion	:SELECT * FROM Pricing_sp_MostrarEtiquetas()
Eliminacion	:DROP FUNCTION Pricing_sp_MostrarEtiquetas()
Comentario	:Retorna todas las etiquetas del Pricing
Modificacion	:
+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE FUNCTION pricing_sp_mostrardestinos()
  RETURNS SETOF tdestino AS
$$
	select * from tdestino where bestado=true;
$$
  LANGUAGE sql;
  /*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_MostrarTodosDestino
Utilizado en	:Pagina Web PGT
Usuario		:PGT
Fecha Creacion	:6/07/2016
Ejecucion	:SELECT * FROM Pricing_sp_MostrarEtiquetas()
Eliminacion	:DROP FUNCTION Pricing_sp_MostrarEtiquetas()
Comentario	:Retorna todas las etiquetas del Pricing
Modificacion	:
+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE FUNCTION pricing_sp_mostrarTodosdestinos()
  RETURNS SETOF tdestino AS
$$
	select * from tdestino
	order by ndestinocod;
$$
  LANGUAGE sql;
/*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_MostrarHotelesDestino
+++++++++++++++++++++++++++++++++++++++++++++++++*/ 
  create or replace function Pricing_sp_MostrarHotelesDestino
  (
  	codDestino int
  )
  returns setof thotel as
  $$
  	select * from thotel
  	where ndestinocod=$1;
  $$
  language sql;
  /*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_MostrarDestino
+++++++++++++++++++++++++++++++++++++++++++++++++*/ 
  create or replace function Pricing_sp_MostrarDestino
  (
  	codDestino int
  )
  returns setof tdestino as
  $$
  	select * from tdestino
  	where ndestinocod=$1;
  $$
  language sql;
 /*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_MostrarTodasReservas
+++++++++++++++++++++++++++++++++++++++++++++++++*/ 
create or replace function Pricing_sp_MostrarTodasReservas()
returns setof treserva as
$$
	select * from treserva;
$$
LANGUAGE SQL;
/*+++++++++++++++++++++++++++++++++++++++++++++++*/
create or replace function Pricing_sp_MostrarPerfiles()
returns setof tperfil as
$$
	select * from tperfil order by nperfilcod;
$$
LANGUAGE SQL;
/*+++++++++++++++++++++++++++++++++++++++++++++++*/
create or replace function Pricing_sp_MostrarAcceso
(
	codPerfil int
)
returns setof tacceso as
$$
	select * from tacceso where nperfilcod=$1;
$$
LANGUAGE SQL;
/*++++++++++++++++++++++++++++++++++++++++++++++++*/
create or replace function Pricing_sp_MostrarUser
(
	usuario varchar(150),
	pass varchar(128)
)
returns setof tusuario as
$$
	select * from tusuario where cusuariocod=$1 and cclave=$2;
$$
LANGUAGE SQL;
 /*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_BuscarReservasEntreFechas
Detalle     : Esta fue la primera version que recupera todo en golpe pero fue reemplazada
+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE FUNCTION Pricing_sp_BuscarReservas(
	fechaInicio varchar(12),
	fechaFin varchar(12)
)
RETURNS table (creservacod varchar(12),dfechainicio Date,dfechafin Date,dfecha timestamp,categoriaHotelcod int,ccontacto varchar(12),
cemail varchar(100),ctelefono varchar(50),nnropersonas int,npreciopaquetepersona numeric,ctituloidioma1 varchar(200),
ccategoriaidioma1 varchar(200),cestado varchar(20),porcentajecobro varchar(5),pagominimo varchar(5),modoporcentual boolean) AS
$$
	select r.creservacod,r.dfechainicio,r.dfechafin,r.dfecha,COALESCE( c.categoriahotelcod, 0 ),r.ccontacto,r.cemail,r.ctelefono,r.nnropersonas,r.npreciopaquetepersona,
		p.ctituloidioma1,c.ccategoriaidioma1,r.cestado,ti.porcentajecobro,ti.pagominimo,ti.modoporcentual
			from treserva as r 
			left join treservapaquete as rp on(r.creservacod=rp.creservacod)
			left join treservapaquetecategoriahotel as rpch on(rp.nreservapaquetecod=rpch.nreservapaquetecod)
			left join tpaquetecategoriahotel as pch on(rpch.codpaquetecategoriah=pch.codpaquetecategoriah)
			left join tcategoriahotel as c on(pch.categoriahotelcod=c.categoriahotelcod)
			left join tpaquete as p on(p.cpaquetecod=rp.cpaquetecod),timpuesto as ti
			where (to_date($1,'yyyy-MM-dd')<=r.dfecha and r.dfecha<=(to_date($2,'yyyy-MM-dd'))+'1 day'::interval) and r.cestado='PENDIENTE DE PAGO'
			group by r.creservacod,r.dfechainicio,r.dfechafin,r.dfecha,c.categoriahotelcod,r.ccontacto,r.cemail,r.ctelefono,r.nnropersonas,r.npreciopaquetepersona,
					p.ctituloidioma1,c.ccategoriaidioma1,r.cestado,ti.porcentajecobro,ti.pagominimo,ti.modoporcentual
			order by r.dfecha desc;
$$
  LANGUAGE sql;
  /*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_BuscarReservasInicial
Detalle     : buscar reserva para muestra inicial
+++++++++++++++++++++++++++++++++++++++++++++++++*/
  CREATE OR REPLACE FUNCTION Pricing_sp_BuscarReservasInicial(
		fechaActual varchar(12)
)
RETURNS table (creservacod varchar(12),dfechainicio Date,dfechafin Date,dfecha timestamp,categoriaHotelcod int,ccontacto varchar(12),
cemail varchar(100),ctelefono varchar(50),nnropersonas int,npreciopaquetepersona numeric,ctituloidioma1 varchar(200),
ccategoriaidioma1 varchar(200),cestado varchar(20),porcentajecobro varchar(5),pagominimo varchar(5),modoporcentual boolean) AS
$$
	select r.creservacod,r.dfechainicio,r.dfechafin,r.dfecha,COALESCE( c.categoriahotelcod, 0 ),r.ccontacto,r.cemail,r.ctelefono,r.nnropersonas,r.npreciopaquetepersona,
		p.ctituloidioma1,c.ccategoriaidioma1,r.cestado,ti.porcentajecobro,ti.pagominimo,ti.modoporcentual
			from treserva as r 
			left join treservapaquete as rp on(r.creservacod=rp.creservacod)
			left join treservapaquetecategoriahotel as rpch on(rp.nreservapaquetecod=rpch.nreservapaquetecod)
			left join tpaquetecategoriahotel as pch on(rpch.codpaquetecategoriah=pch.codpaquetecategoriah)
			left join tcategoriahotel as c on(pch.categoriahotelcod=c.categoriahotelcod)
			left join tpaquete as p on(p.cpaquetecod=rp.cpaquetecod),timpuesto as ti
			where (r.dfecha<=(to_date($1,'yyyy-MM-dd'))+'1 day'::interval) and r.cestado='PENDIENTE DE PAGO'
			group by r.creservacod,r.dfechainicio,r.dfechafin,r.dfecha,c.categoriahotelcod,r.ccontacto,r.cemail,r.ctelefono,r.nnropersonas,r.npreciopaquetepersona,
					p.ctituloidioma1,c.ccategoriaidioma1,r.cestado,ti.porcentajecobro,ti.pagominimo,ti.modoporcentual
			order by r.dfecha desc
			limit 10;
$$
  LANGUAGE sql;
 /*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_BuscarPagosEntreFechasBD
Detalle     :La busqueda se realiza en los distintos formas de pagos(VISA, PAYPAL) 
+++++++++++++++++++++++++++++++++++++++++++++++++*/
select Pricing_sp_BuscarPagosPaypalEntreFechasBD('2016-07-20','2016-08-28','PENDIENTE DE PAGO');
  select Pricing_sp_BuscarPagosPaypalEntreFechasBD('2016-07-20','2016-08-12','PENDIENTE DE PAGO');
 /*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_BuscarDestinosReserva
Detalle     :Realiza la busqueda de destinos de acuerdo ala reserva donde se encuentre
+++++++++++++++++++++++++++++++++++++++++++++++++*/
create or replace function Pricing_sp_BuscarDestinosReserva
(
  codReserva varchar(12)
)
RETURNS table (cdestino varchar(100),ncodpostal int,ndestinocod int) AS
$$
	select d.cdestino,d.ncodpostal,d.ndestinocod
		  from treserva as r 
		inner join treservapaquete as rp on(r.creservacod=rp.creservacod)
		inner join tpaquetedestino as pd on(rp.cpaquetecod=pd.cpaquetecod)
	      inner join tdestino as d on(pd.ndestinocod=d.ndestinocod)
		  where (r.creservacod=$1 and d.bestado=true)
		  group by d.cdestino, d.ncodpostal,d.ndestinocod
		  order by d.cdestino;
$$
  LANGUAGE sql;
 
  /*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_BuscarServiciosReserva
Detalle     :Realiza la busqueda de servicios de acuerdo ala reserva donde se encuentre
+++++++++++++++++++++++++++++++++++++++++++++++++*/

 create or replace function Pricing_sp_BuscarServiciosReserva
(
  codReserva varchar(12)
)
RETURNS table (cservicioindioma1 varchar(200),nprecioservicio decimal(10,2)) AS
$$
	select s.cservicioindioma1, s.nprecioservicio 
			from treserva as r
			inner join treservapaquete as rp on(r.creservacod=rp.creservacod)
			inner join treservapaqueteservicio as rps on(rp.nreservapaquetecod=rps.nreservapaquetecod)
			inner join tpaqueteservicio as ps on(rps.codpaqueteservicio=ps.codpaqueteservicio)
			inner join tservicio as s on(ps.nserviciocod=s.nserviciocod)
			where (r.creservacod=$1 and s.bestado=true)
			group by s.cservicioindioma1, s.nprecioservicio 
			order by s.cservicioindioma1;
$$
  LANGUAGE sql;

  /*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_BuscarServiciosReserva
Detalle     :Realiza la busqueda de servicios de acuerdo ala reserva donde se encuentre
+++++++++++++++++++++++++++++++++++++++++++++++++*/
    create or replace function Pricing_sp_BuscarsubServiciosReserva
(
  codReserva varchar(12)
)
RETURNS table (csubservicioindioma1 varchar(200),nprecioservicio decimal(10,2),cservicioindioma1 varchar(200)) AS
$$
	select ss.csubservicioindioma1, ss.nprecioservicio ,s.cservicioindioma1
			from treserva as r
			inner join treservapaquete as rp on(r.creservacod=rp.creservacod)
			inner join treservapaqueteservicio as rps on(rp.nreservapaquetecod=rps.nreservapaquetecod)
			inner join tpaqueteservicio as ps on(rps.codpaqueteservicio=ps.codpaqueteservicio)
			inner join tservicio as s on(ps.nserviciocod=s.nserviciocod)
			inner join tsubservicio as ss on(ss.nsubserviciocod=rps.nsubserviciocod)
			where (r.creservacod=$1 and ss.bestado=true)
			group by ss.csubservicioindioma1, ss.nprecioservicio, s.cservicioindioma1 
			order by s.cservicioindioma1;
$$
  LANGUAGE sql;
 /*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_BuscarHotelesReserva
Detalle     :Realiza la busqueda de hoteles de acuerdo ala reserva donde se encuentre
+++++++++++++++++++++++++++++++++++++++++++++++++*/
create or replace function Pricing_sp_BuscarHotelesReserva
(
  codReserva varchar(12),
  categoriaHotel int
)
RETURNS table (chotel varchar(200),npreciosimple decimal(10,2),npreciodoble decimal(10,2),npreciotriple decimal(10,2),cdestino varchar(100)) AS
$$
	select h.chotel, h.npreciosimple,h.npreciodoble,h.npreciotriple,d.cdestino 
		from treserva as r 
		inner join treservapaquete as rp on(r.creservacod=rp.creservacod)
		inner join tpaquetedestino as pd on(rp.cpaquetecod=pd.cpaquetecod)
		inner join tdestino as d on(pd.ndestinocod=d.ndestinocod)
		inner join thotel as h on(h.ndestinocod=d.ndestinocod)
		where (r.creservacod=$1 and h.bestado=true)
		group by h.chotel, h.npreciosimple,h.npreciodoble,h.npreciotriple,d.cdestino
		order by d.cdestino;
$$
  LANGUAGE sql;
 /*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_validar login
Detalle     :Realiza la validacion de login
+++++++++++++++++++++++++++++++++++++++++++++++++*/  
  /*****martes****/
  CREATE OR REPLACE function Pricing_sp_ValidarLogin
(
	pcUsuarioCod varchar(150),
	pcClave varchar(128)
)
RETURNS TABLE (resultado varchar(20),
		mensaje varchar(200),
		cUsuarioCod varchar(150),
		cClave varchar(128),
		imgUsuario varchar(200),
		nPerfilCod int,
		cPerfilIdioma1 varchar(100),
		cNroDoc varchar(12),
		cNombres varchar(150),
		cSexo char(1),
		dFechaNac date,
		cCelular varchar(50),
		dFechaSistema date) as		
$$
begin
	if (select count (1) from tusuario u where u.cusuariocod =$1 and bestado=true)>0 then
	begin
		if (select count (1) from tusuario u where u.cusuariocod =$1 and u.cclave=$2)>0 then
		begin
			resultado='correcto';
			mensaje='Acceso Autorizado';
			dFechaSistema=now();
			return Query select resultado,mensaje,u.cusuariocod,u.cclave,u.imgusuario,u.nperfilcod,
				p.cperfilidioma1,
				u.cnrodoc,u.cnombres,u.csexo,u.dfechanac,u.ccelular,dFechaSistema
			from tusuario u
			inner join tperfil p on u.nperfilcod=p.nperfilcod
			where u.cusuariocod =$1 and u.cClave=$2 and u.bestado=true;
		end;
		else
		begin
			resultado='error';
			mensaje='Clave Incorrecta';
			nperfilcod =0;
			cperfilidioma1='';
			cnrodoc='';
			cnombres='';
			csexo='';
			ccelular='';
			dFechaSistema=now();
			return Query select resultado,mensaje,$1 as cusuariocod,$2 as cclave,imgusuario,nperfilcod,
					cperfilidioma1,cnrodoc,cnombres,csexo,to_date(to_char(now(), 'YYYY-MM-DD'), 'YYYY-MM-DD') as dfechanac ,ccelular,
					dFechaSistema;
		end;
		end if;
	end;
	else
	begin
		resultado='error';
		mensaje='Usuario No Registrado';
		nperfilcod =0;
		cperfilidioma1='';
		cnrodoc='';
		cnombres='';
		csexo='';
		ccelular='';
		dFechaSistema=now();
		return Query select resultado,mensaje,$1 as cusuariocod,$2 as cclave,imgusuario,nperfilcod,
				cperfilidioma1,
				cnrodoc,cnombres,csexo,to_date(to_char(now(), 'YYYY-MM-DD'), 'YYYY-MM-DD') as dfechanac ,ccelular,
				dFechaSistema;
	end;
	end if;
end
$$ 
LANGUAGE plpgsql;


select count (1) from tusuario u where u.cusuariocod ='73077306' and u.cClave='veamos'




 create or replace function Pricing_sp_BuscarPagosVisaEntreFechasBD
(
	fechaInicio varchar(12),
	fechaFin varchar(12),
	estadoPago varchar(10)
)
RETURNS table (creservacod varchar(12),dfechainicio Date,dfechafin Date,dfecha timestamp,categoriahotelcod int,npreciopaquetepersona numeric,nnropersonas int,ctituloidioma1 varchar(200),nimporte numeric,nporcentaje numeric,
formapago varchar(20),estado varchar(10),fechayhora_initx timestamp ,nro_tarjeta varchar(20),
codtransaccion varchar(20),nom_th varchar(100),cestado varchar(20),impuesto varchar(5)) AS
$$
		select r.creservacod,r.dfechainicio,r.dfechafin,r.dfecha,COALESCE( ch.categoriahotelcod, 0 ),r.npreciopaquetepersona,r.nnropersonas,paq.ctituloidioma1,pv.nimporte,pv.nporcentaje,
		pv.formapago,pv.estado,pv.fechayhora_initx,pv.nro_tarjeta,pv.cod_autorizacion,pv.nom_th,r.cestado,ti.impuestovisa
				from treserva as r 
				left join tpagovisa as pv on(r.creservacod=pv.creservacod)
				left join treservapaqueteservicio as rps on(r.creservacod=rps.creservacod)
				left join tpaqueteservicio as ps on(rps.codpaqueteservicio=ps.codpaqueteservicio)
				left join tpaquete as paq on(ps.cpaquetecod=paq.cpaquetecod)
				left join treservapaquetecategoriahotel as rpch on(rpch.creservacod=r.creservacod)
				left join tpaquetecategoriahotel as pch on(pch.codpaquetecategoriah=rpch.codpaquetecategoriah)
				left join tcategoriahotel as ch on(ch.categoriahotelcod=pch.categoriahotelcod), timpuesto as ti
				where (pv.fechayhora_initx between to_date($1,'yyyy-MM-dd') and to_date($2,'yyyy-MM-dd')) and r.cestado=$3
				group by r.creservacod,r.dfechainicio,r.dfechafin,r.dfecha,ch.categoriahotelcod,r.npreciopaquetepersona,r.nnropersonas,paq.ctituloidioma1,pv.nimporte,pv.nporcentaje,pv.formapago,pv.estado,pv.fechayhora_initx,pv.nro_tarjeta,
					 pv.cod_autorizacion,pv.nom_th,r.cestado,ti.impuestovisa
				order by r.creservacod;
$$
  LANGUAGE sql;
create or replace function Pricing_sp_BuscarPagosPaypalEntreFechasBD
(
	fechaInicio varchar(12),
	fechaFin varchar(12),
	estadoPago varchar(10)
)
RETURNS table (creservacod varchar(12),dfechainicio Date,dfechafin Date,dfecha timestamp,categoriahotelcod int,npreciopaquetepersona numeric,nnropersonas int,ctituloidioma1 varchar(200),nimporte numeric,nporcentaje numeric,
formapago varchar(20),estado varchar(10),fechayhora_initx timestamp ,nro_tarjeta varchar(20),
codtransaccion varchar(20),nom_th varchar(100),cestado varchar(20),impuesto varchar(5)) AS
$$
		select r.creservacod,r.dfechainicio,r.dfechafin,r.dfecha,COALESCE( ch.categoriahotelcod, 0 ),r.npreciopaquetepersona,r.nnropersonas,paq.ctituloidioma1,pp.nimporte,pp.nporcentaje,
		pp.formapago,pp.estado,pp.fechayhora_initx,pp.nro_tarjeta,pp.cod_autorizacion,pp.nom_th,r.cestado,ti.impuestopaypal
				from treserva as r 
				left join tpagopaypal as pp on(r.creservacod=pp.creservacod)
				left join treservapaqueteservicio as rps on(r.creservacod=rps.creservacod)
				left join tpaqueteservicio as ps on(rps.codpaqueteservicio=ps.codpaqueteservicio)
				left join tpaquete as paq on(ps.cpaquetecod=paq.cpaquetecod)
				left join treservapaquetecategoriahotel as rpch on(rpch.creservacod=r.creservacod)
				left join tpaquetecategoriahotel as pch on(pch.codpaquetecategoriah=rpch.codpaquetecategoriah)
				left join tcategoriahotel as ch on(ch.categoriahotelcod=pch.categoriahotelcod), timpuesto as ti
				where (pp.fechayhora_initx between to_date($1,'yyyy-MM-dd') and to_date($2,'yyyy-MM-dd')) and r.cestado=$3
				group by r.creservacod,r.dfechainicio,r.dfechafin,r.dfecha,ch.categoriahotelcod,r.npreciopaquetepersona,r.nnropersonas,paq.ctituloidioma1,pp.nimporte,pp.nporcentaje,pp.formapago,pp.estado,pp.fechayhora_initx,pp.nro_tarjeta,
					 pp.cod_autorizacion,pp.nom_th,r.cestado,ti.impuestopaypal
				order by r.creservacod;
$$
  LANGUAGE sql;

  create or replace function Pricing_sp_BuscarPasajerosReserva(
	codReserva varchar(12)
  )
  returns table (capellidos varchar(100),cnombres varchar(100),csexo char(1),nedad int,cabrevtipodoc varchar(20),cnrodoc varchar(12),cnombreesp varchar(60)) as
  $$
		select pa.capellidos,pa.cnombres, pa.csexo,pa.nedad,tp.cabrevtipodoc,pa.cnrodoc,pais.cnombreidioma1
			from tpasajero as pa
			 inner join ttipodocumento as tp on(pa.ntipodoc=tp.ntipodoc)
			 inner join tpais as pais on(pa.npaiscod=pais.npaiscod)
			 where (pa.creservacod=$1)
			 group by pa.capellidos,pa.cnombres, pa.csexo,pa.nedad,tp.cabrevtipodoc,pa.cnrodoc,pais.cnombreidioma1
			 order by pa.capellidos
 $$
 LANGUAGE sql;




  select Pricing_sp_BuscarPasajerosReserva('R000000002');
  
   /*======estadistica======*/
  
  /*hoy viernes*
   */
create or replace function Pricing_sp_BuscarPaquetesMasVendidos
(
	fechaanio varchar(12)
)
RETURNS table (ctituloidioma1 varchar(200),nrovendidos int,fecha timestamp) AS
$$
	SELECT p.ctituloidioma1,1,r.dfecha
               from treserva as r 
			inner join treservapaquete as rp on(r.creservacod=rp.creservacod) 
			inner join tpaquete as p on(rp.cpaquetecod=p.cpaquetecod)
			where (to_date($1||''||'-01-01','yyyy-MM-dd') <= r.dfecha AND r.dfecha <=  to_date($1||''||'-12-31','yyyy-MM-dd')) and r.cestado='PAGO TOTAL'
			group by p.ctituloidioma1,p.cpaquetecod,r.dfecha
			order by p.ctituloidioma1 asc
$$
  LANGUAGE sql;

  
  insert into tpasajero values(144,'R000000012',2,'73077307',3,'quispe ttito','Juan',11,'M',19,'Doc_36_1.jpeg'),
				(145,'R000000013',2,'73077307',3,'mamani ttito','Jose',4,'F',23,'Doc_37_1.jpeg'),
				(146,'R000000014',2,'73077307',3,'guzman ttito','Oscar',1,'M',29,'Doc_37_1.jpeg'),
				(147,'R000000018',2,'73077307',3,'blas ttito','Juan',2,'F',22,'Doc_36_1.jpeg'),
				(148,'R000000019',2,'73077307',3,'aimituma ttito','Pedro',11,'M',19,'Doc_38_1.jpeg');




codreservapservicio bigint NOT NULL DEFAULT nextval('seq_reservaps'::regclass),
  codpaqueteservicio integer,
  creservacod character varying(12),
  nroprestacionservicio numeric(10,2),
  precioprestacionservicio numeric(10,2),

  insert into treservapaqueteservicio values(128,1,'R000000021',5.00,70),(129,8,'R000000022',5.00,70),(130,2,'R000000023',5.00,70),(131,4,'R000000024',5.00,70),
						(132,6,'R000000025',5.00,70),(133,10,'R000000026',5.00,70),(134,14,'R000000027',5.00,70),(135,16,'R000000028',5.00,70);

  126	51	R000000020	4.00	60.00


    insert into treserva values('R000000021','2016-08-12 09:38:37','2016-11-03','2016-11-06','jason','julian@gmail.com','98756433',567.00,3,'nada x el momento','PAGO PARCIAL'),
			     ('R000000022','2016-03-20 09:38:37','2016-11-03','2016-11-06','pedro','juan@gmail.com','98756433',3.00,3,'nada x el momento','PAGO PARCIAL'),
			     ('R000000023','2016-03-23 09:38:37','2016-11-03','2016-11-06','jose','jose@gmail.com','98756433',567.00,3,'nada x el momento','PAGO PARCIAL'),
			     ('R000000024','2016-03-25 09:38:37','2016-11-03','2016-11-06','oscar','oscar@gmail.com','98756433',267.00,3,'nada x el momento','PAGO PARCIAL'),
			     ('R000000025','2016-01-20 09:38:37','2016-11-03','2016-11-06','pedro','pedro@gmail.com','98756433',567.00,3,'nada x el momento','PAGO PARCIAL'),
			     ('R000000026','2016-01-23 09:38:37','2016-11-03','2016-11-06','bran','bran@gmail.com','98756433',667.00,3,'nada x el momento','PAGO TOTAL'),
			     ('R000000027','2016-11-12 09:38:37','2016-11-03','2016-11-06','franklin','frank@gmail.com','98756433',267.00,3,'nada x el momento','PAGO TOTAL'),
			     ('R000000028','2016-11-14 09:38:37','2016-11-03','2016-11-06','cesar','cesar@gmail.com','98756433',767.00,3,'nada x el momento','PAGO TOTAL'),
			     ('R000000030','2016-01-22 09:38:37','2016-11-03','2016-11-06','reinaldo','roanldo@gmail.com','98756433',167.00,3,'nada x el momento','PAGO TOTAL')


delete from treservapaqueteservicio where codreservapservicio=121;

 /*======estadistica======*/

  create or replace function Pricing_sp_listaFormaPagos
  (
	anio varchar(4)
  )
  returns table(formapago varchar(20),fechaPago timestamp) as
  $$
	select  formapago,fechayhora_initx
	   from tpagovisa
  	   where to_date($1||''||'-01-01','yyy-MM-dd') <= fechayhora_initx AND fechayhora_initx <=  to_date($1||''||'-21-31','yyy-MM-dd')
  	   
	union all
	   select  formapago,fechayhora_initx
  	   from tpagopaypal
  	   where to_date($1||''||'-01-01','yyy-MM-dd') <= fechayhora_initx AND fechayhora_initx <=  to_date($1||''||'-21-31','yyy-MM-dd')
  $$
  language sql;

 /*======registro usuarios======*/
CREATE OR REPLACE FUNCTION Pricing_sp_MostrarTodosUsuarios()
  RETURNS table (cusuariocod varchar(150),cperfilidioma1 varchar(100),imgusuario varchar(200),cnrodoc varchar(12),
		cnombres varchar(150),csexo char(1),dfechanac Date,ccelular varchar(50),ccorreo varchar(100),bestado boolean) AS
$$
	select u.cusuariocod,p.cperfilidioma1,u.imgusuario,u.cnrodoc,u.cnombres,u.csexo,u.dfechanac,u.ccelular,u.ccorreo,u.bestado
	 from tusuario as u inner join tperfil as p on(u.nperfilcod=p.nperfilcod)
	 order by u.cnombres;
$$
  LANGUAGE sql;

select * from tusuario;
/***********************************/
/**MOSTRAR CONFIGURACION DE PAYPAL**/
/***********************************/
CREATE OR REPLACE function Pricing_sp_MostrarPaypalConfig
()
returns setof tpaypalconfig as 
$$
	select * from tpaypalconfig
	order by npaypalcod;
$$ 
LANGUAGE SQL;
/************************************************/
/**MOSTRAR CONFIGURACION DE MASTERCARD Y DINERS**/
/************************************************/
CREATE OR REPLACE FUNCTION Pricing_sp_MostrarMasterDinersConfig
()
returns setof tmasterdinersconfig as
$$
	select * from tmasterdinersconfig order by nmasterdinerscod;
$$
language sql;
/********************/
/**BUSCAR ETIQUETAS**/
/********************/
CREATE OR REPLACE FUNCTION Pricing_sp_BuscarEtiquetas(
	nombre text
)
RETURNS setof tetiqueta AS
$$
	select * from tetiqueta as e 
		where e.cidioma1 ILIKE '%'||$1||'%'
		order by e.cidioma1;
$$
  LANGUAGE sql;
  
  
/*+++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE FUNCTION Pricing_sp_MostrarTodasCategoria()
  RETURNS SETOF tcategoriahotel AS
$$
	select * from tcategoriahotel
	order by ccategoriaidioma1;
$$
  LANGUAGE sql;
  
/*=================================*/
CREATE OR REPLACE FUNCTION Pricing_sp_BuscarHotelesxDestinoxCategoria(
	 hotel varchar(200),
	 destino varchar(100),
	 categoria varchar(200)
)
RETURNS table(nhotelcod int,chotel varchar(200),cdescripcionidioma1 text,cdescripcionidioma2 text,cdescripcionidioma3 text,cdescripcionidioma4 text,cdescripcionidioma5 text,
		curl varchar(200),categoriaHotelCod int,npreciosimple numeric(10,2),npreciodoble numeric(10,2),npreciotriple numeric(10,2),bestado boolean,
		nPrecioCamaAdicional numeric(10,2),nDestinoCod int,cFoto1 varchar(100),cFoto2 varchar(100),cFoto3 varchar(100),cFoto4 varchar(100),cFoto5 varchar(100),cImagenUbicacion varchar(100)) AS
$$
begin
		if((select count(1) from tdestino as td where td.cdestino=$2)>0) then
		 begin
			if((select count(1) from tcategoriahotel as tch where tch.ccategoriaidioma1=$3)>0) then
			begin
			    return query select h.nhotelcod,h.chotel,h.cdescripcionidioma1,h.cdescripcionidioma2,h.cdescripcionidioma3,h.cdescripcionidioma4,h.cdescripcionidioma5,
				h.curl,ch.categoriahotelcod,h.npreciosimple,h.npreciodoble,h.npreciotriple,h.bestado,h.npreciocamaadicional,h.ndestinocod,h.cfoto1,h.cfoto2
				,h.cfoto3,h.cfoto4,h.cfoto5,h.cimagenubicacion
				from thotel as h 
				inner join tdestino as d on(h.ndestinocod=d.ndestinocod)
				inner join tcategoriahotel as ch on(ch.categoriahotelcod=h.categoriahotelcod)
				where (d.cdestino=$2) and (ch.ccategoriaidioma1=$3) and (h.chotel ILIKE '%'||$1||'%')
				order by h.chotel;
			end;
			else
			begin
			    return query select h.nhotelcod,h.chotel,h.cdescripcionidioma1,h.cdescripcionidioma2,h.cdescripcionidioma3,h.cdescripcionidioma4,h.cdescripcionidioma5,
				h.curl,ch.categoriahotelcod,h.npreciosimple,h.npreciodoble,h.npreciotriple,h.bestado,h.npreciocamaadicional,h.ndestinocod,h.cfoto1,h.cfoto2
				,h.cfoto3,h.cfoto4,h.cfoto5,h.cimagenubicacion
				from thotel as h 
				inner join tdestino as d on(h.ndestinocod=d.ndestinocod)
				inner join tcategoriahotel as ch on(ch.categoriahotelcod=h.categoriahotelcod)
				where (d.cdestino=$2) and (h.chotel ILIKE '%'||$1||'%')
				order by h.chotel;
			end;
			end if;
		 end;
		else
		begin
		  if((select count(1) from tcategoriahotel as tch where tch.ccategoriaidioma1=$3)>0) then
		  begin
		      return query select h.nhotelcod,h.chotel,h.cdescripcionidioma1,h.cdescripcionidioma2,h.cdescripcionidioma3,h.cdescripcionidioma4,h.cdescripcionidioma5,
				h.curl,ch.categoriahotelcod,h.npreciosimple,h.npreciodoble,h.npreciotriple,h.bestado,h.npreciocamaadicional,h.ndestinocod,h.cfoto1,h.cfoto2
				,h.cfoto3,h.cfoto4,h.cfoto5,h.cimagenubicacion
			from thotel as h 
			inner join tdestino as d on(h.ndestinocod=d.ndestinocod)
			inner join tcategoriahotel as ch on(ch.categoriahotelcod=h.categoriahotelcod)
			where (ch.ccategoriaidioma1=$3) and (h.chotel ILIKE '%'||$1||'%')
			order by h.chotel;
		  end;
		  else
		  begin
		      return query select h.nhotelcod,h.chotel,h.cdescripcionidioma1,h.cdescripcionidioma2,h.cdescripcionidioma3,h.cdescripcionidioma4,h.cdescripcionidioma5,
				h.curl,ch.categoriahotelcod,h.npreciosimple,h.npreciodoble,h.npreciotriple,h.bestado,h.npreciocamaadicional,h.ndestinocod,h.cfoto1,h.cfoto2
				,h.cfoto3,h.cfoto4,h.cfoto5,h.cimagenubicacion
			from thotel as h 
			inner join tdestino as d on(h.ndestinocod=d.ndestinocod)
			inner join tcategoriahotel as ch on(ch.categoriahotelcod=h.categoriahotelcod)
			where h.chotel ILIKE '%'||$1||'%'
			order by h.chotel;
		   end;
		  end if;
		 end;
	end if;	
end;
$$
  LANGUAGE plpgsql;
   
  CREATE OR REPLACE FUNCTION Pricing_sp_BuscarDestinos(
	nombre text
)
RETURNS setof tdestino AS
$$
	select * from tdestino as d 
		where d.cdestino ILIKE '%'||$1||'%'
		order by d.cdestino;
$$
  LANGUAGE sql;
  /**BUSCAR PAQUETES**/
  CREATE OR REPLACE FUNCTION Pricing_sp_BuscarPaquetes(
	nombre text
)
RETURNS setof tpaquete AS
$$
	select * from tpaquete as p 
		where p.ctituloidioma1 ILIKE '%'||$1||'%'
		order by p.ctituloidioma1;
$$
  LANGUAGE sql;
/**BUSCAR SERVICIOS**/
  CREATE OR REPLACE FUNCTION Pricing_sp_BuscarServicios(
	nombre text
)
RETURNS setof tservicio AS
$$
	select * from tservicio as s 
		where s.cservicioindioma1 ILIKE '%'||$1||'%'
		order by s.cservicioindioma1;
$$
  LANGUAGE sql;
/**BUSCAR SUBSERVICIOS**/
  CREATE OR REPLACE FUNCTION Pricing_sp_BuscarSubServicios(
	nombre text
)
RETURNS setof tsubservicio AS
$$
	select * from tsubservicio as s 
		where s.csubservicioindioma1 ILIKE '%'||$1||'%'
		order by s.csubservicioindioma1;
$$
  LANGUAGE sql;
 /**BUSCAR actividades reserva**/
  
  CREATE OR REPLACE FUNCTION Pricing_sp_BuscarActividadesReserva(
	codReserva varchar(12)
)
RETURNS TABLE(cactividadidioma1 varchar(200),nprecioactividad numeric(10,2)) as
$$
	select a.cactividadidioma1,a.nprecioactividad
		from treserva as r
		inner join treservapaquete as rp on(r.creservacod=rp.creservacod)
		inner join tpaqueteactividad as pa on(rp.cpaquetecod=pa.cpaquetecod)
		inner join tactividad as a on(pa.nactividadcod=a.nactividadcod)
		where (r.creservacod=$1 and a.bestado=true)
		group by a.cactividadidioma1,a.nprecioactividad
		order by a.cactividadidioma1
$$
language sql;
 /**BUSCAR ACTIVIDADES**/
  CREATE OR REPLACE FUNCTION Pricing_sp_BuscarActividades(
	nombre text
)
RETURNS setof tactividad AS
$$
	select * from tactividad as a 
		where a.cactividadidioma1 ILIKE '%'||$1||'%'
		order by a.cactividadidioma1;
$$
  LANGUAGE sql; 
/*******************************/
/**MOSTRAR CALENDARIO YOURSELF**/
/*******************************/
 create or replace function Pricing_sp_RecuperarDisponibilidad_yourself
  (
  	cDisponibilidad int	
  )
  returns setof tcalendario_yourself as
  $$
	select * from tcalendario_yourself where cDisponibilidad=$1 order by nmes;
  $$
  language sql;
/***********************************/
/**MOSTRAR MES CALENDARIO YOURSELF**/
/***********************************/
 CREATE OR REPLACE FUNCTION Pricing_sp_MostrarMesDisponibilidad_yourself
 (
 	anio int,
 	mes int,
 	codDisponibilidad int
 )
 returns setof tcalendario_yourself as
  $$
	select * from tcalendario_yourself where cdisponibilidad=$3 and nanio=$1 and nmes=$2;
  $$
  language sql;
/***********************************/
  insert into tdestino values(3,'AREQUIPA',true,43),(4,'PUNO',true,42),(5,'JULIACA',true,40);

insert into thotel values(3,'VELLAS ARTES','','','','','','WWW.VELLAS.COM',1,50.00,45.00,30.00,true,3),(4,'LOS ANDENES','','','','','','WWW.ANDENES.COM',3,50.00,45.00,30.00,true,2),
			(5,'LOS INKAS','','','','','','WWW.INKAS.COM',4,50.00,45.00,30.00,true,3),(6,'LOS CANCHIS','','','','','','WWW.CANCHIS.COM',2,50.00,45.00,30.00,true,4);
			
			
insert into tpaqueteservicio values(7,'P-02',1),(8,'P-03',1),(9,'P-04',1),(10,'P-01',1),(11,'P-02',1),(12,'P-03',1),(13,'P-04',1),(14,'P-04',1);




      insert into treserva values('R000000006','2016-08-12 09:38:37','2016-11-03','2016-11-06','jason','julian@gmail.com','98756433',567.00,3,'nada x el momento','PAGO TOTAL'),
			     ('R000000007','2016-03-20 09:38:37','2016-11-03','2016-11-06','pedro','juan@gmail.com','98756433',3.00,3,'nada x el momento','PAGO TOTAL'),
			     ('R000000008','2016-03-23 09:38:37','2016-11-03','2016-11-06','jose','jose@gmail.com','98756433',567.00,3,'nada x el momento','PAGO TOTAL'),
			     ('R000000009','2016-03-25 09:38:37','2016-11-03','2016-11-06','oscar','oscar@gmail.com','98756433',267.00,3,'nada x el momento','PAGO TOTAL'),
			     ('R000000010','2016-01-20 09:38:37','2016-11-03','2016-11-06','pedro','pedro@gmail.com','98756433',567.00,3,'nada x el momento','PAGO TOTAL'),
			     ('R000000011','2016-01-23 09:38:37','2016-11-03','2016-11-06','bran','bran@gmail.com','98756433',667.00,3,'nada x el momento','PAGO TOTAL'),
			     ('R000000012','2016-11-12 09:38:37','2016-11-03','2016-11-06','franklin','frank@gmail.com','98756433',267.00,3,'nada x el momento','PAGO TOTAL'),
			     ('R000000013','2016-11-14 09:38:37','2016-11-03','2016-11-06','cesar','cesar@gmail.com','98756433',767.00,3,'nada x el momento','PAGO TOTAL'),
			     ('R000000014','2016-01-22 09:38:37','2016-11-03','2016-11-06','reinaldo','roanldo@gmail.com','98756433',167.00,3,'nada x el momento','PAGO TOTAL')


insert into treservapaqueteservicio values(128,7,'R000000006',5.00,70),(129,8,'R000000007',5.00,70),(130,9,'R000000008',5.00,70),(131,10,'R000000009',5.00,70),
						(132,11,'R000000010',5.00,70),(133,12,'R000000011',5.00,70),(134,13,'R000000012',5.00,70),(135,14,'R000000013',5.00,70);



select * from treserva;




insert into TPagoVisa values(1002,'R000000002',125.50,0.3,'VISA','AUTORIZADO','2016-07-22 13:29:44','E002',12344,'T002','A02','codigo de accion1','TAR0002',
			'JULIO GOMEZ','AA','CONTINENTAL','E0002','eci enviado 2','C00002','123',0.18,'2016-07-22 14:29:44','2016-07-22 14:34:44','2016-07-22 15:29:44','comercio2'),
			(1003,'R000000003',125.50,0.3,'VISA','AUTORIZADO','2016-07-22 13:29:44','E003',12344,'T003','A03','codigo de accion1','TAR0003',
			'RAUL GOMEZ','AA','CONTINENTAL','E0002','eci enviado 3','C00002','123',0.18,'2016-07-22 14:29:44','2016-07-22 14:34:44','2016-07-22 15:29:44','comercio2'),
			(1004,'R000000004',125.50,0.3,'VISA','AUTORIZADO','2016-07-22 13:29:44','E004',12344,'T004','A04','codigo de accion1','TAR0003',
			'PEDRO GOMEZ','AA','CONTINENTAL','E0002','eci enviado 4','C00002','123',0.18,'2016-07-22 14:29:44','2016-07-22 14:34:44','2016-07-22 15:29:44','comercio2'),
			(1005,'R000000005',125.50,0.3,'VISA','AUTORIZADO','2016-07-22 13:29:44','E005',12344,'T005','A05','codigo de accion1','TAR0003',
			'OSCAR GOMEZ','AA','CONTINENTAL','E0002','eci enviado 5','C00002','123',0.18,'2016-07-22 14:29:44','2016-07-22 14:34:44','2016-07-22 15:29:44','comercio2');

select * from tpagovisa;


insert into tpagopaypal values(20006,40001,'C00001',0.40,'R000000011','PAYPAL','INICIADO',237.00,'2016-01-22 13:29:44','LEONARDO CRUZ','T01938373',
								'trabaja en agencia','72353532','PER','ESTADO1','impuesto sera 1','av.collasuyo A-15','leo@gmail.com'),
								(20007,40002,'C00002',0.40,'R000000012','PAYPAL','INICIADO',257.00,'2016-02-22 13:29:44','JUAN CRUZ','T019123373',
								'trabaja en agencia','72353532','PER','ESTADO1','impuesto sera 1','av.collasuyo A-15','juan@gmail.com'),
								(20008,40003,'C00003',0.40,'R000000013','PAYPAL','INICIADO',187.00,'2016-02-23 13:29:44','MARIO CRUZ','T01924373',
								'trabaja en agencia','72353532','COL','ESTADO1','impuesto sera 1','av.collasuyo A-15','mario@gmail.com'),
								(20009,40004,'C00004',0.40,'R000000014','PAYPAL','INICIADO',297.00,'2016-03-24 13:29:44','CARMEN CRUZ','T01758373',
								'trabaja en agencia','72353532','USA','ESTADO1','impuesto sera 1','av.collasuyo A-15','carmen@gmail.com'),
								(20010,40005,'C00005',0.40,'R000000001','PAYPAL','INICIADO',183.00,'2016-07-25 13:29:44','JOSE CRUZ','T01468373',
								'trabaja en agencia','72353532','ECU','ESTADO1','impuesto sera 1','av.collasuyo A-15','jose@gmail.com');
