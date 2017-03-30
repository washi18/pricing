CREATE OR REPLACE FUNCTION Pricing_sp_ModificarImagenesPaquete
(
    codPaquete varchar(10),
    foto1 varchar(100),
    foto2 varchar(100),
    foto3 varchar(100),
    foto4 varchar(100),
    foto5 varchar(100)
)
returns table(resultado varchar(20),mensaje varchar(200),paqueteCod varchar(10))as
$$
begin
    paqueteCod=$1;
    update tpaquete set cfoto1=$2,
                    cfoto2=$3,
                    cfoto3=$4,
                    cfoto4=$5,
                    cfoto5=$6
                    where cpaquetecod=$1;
    resultado='correcto';
    mensaje='Datos Actualizados';
    return Query select resultado,mensaje,paquetecod;
end 
$$
language plpgsql;
/**MODIFICAR ESTADO DE TRESERVA**/
CREATE OR REPLACE FUNCTION Pricing_sp_ModificarEstadoPago
(
    reservaCod  varchar(12),
    estado varchar(20),
    metodoPago varchar(20),
    codTransaccion varchar(20)
)
RETURNS TABLE (resultado varchar(20),mensaje varchar(200),codReserva varchar(12)) as
$$
declare
    codReserva varchar(12);
begin
    codReserva=(select creservacod from treserva where creservacod=$1);
    update Treserva set cestado=$2,cmetodoPago=$3,ccodtransaccion=$4 where creservacod=$1;
    resultado='correcto';
    mensaje='Datos Actualizados Correctamente';
    return Query select resultado,mensaje,codReserva;
end
$$
LANGUAGE plpgsql;
/**MODIFICAR conf alto nivel**/
CREATE OR REPLACE FUNCTION Pricing_sp_ModificarConfAltoNivel
(
    nombreEntidad varchar(30),
    estado boolean
)
RETURNS TABLE (resultado varchar(20),mensaje varchar(200),codEt int) as
$$
declare
    codEt int;
begin
    codEt=(select codaltonivel from tconfigaltonivel where cnombreentidad=$1);
    update Tconfigaltonivel set bestado=$2 where cnombreentidad=$1;
    resultado='correcto';
    mensaje='Datos Actualizados Correctamente';
    return Query select resultado,mensaje,codEt;
end
$$
LANGUAGE plpgsql; 
/**MODIFICAR IMPUESTOS**/
CREATE OR REPLACE FUNCTION Pricing_sp_ModificarImpuesto
(
	nCodImpuesto int,
	cImpuestoPaypal varchar(5),
	cImpuestoVisa varchar(5),
	cImpuestoMasterCard varchar(5),
	cImpuestoDinnersClub varchar(5),
	cPorcentajeCobro varchar(5)
)
RETURNS TABLE (resultado varchar(20),mensaje varchar(200),codImp int) as
$$
begin
	codImp=$1;
	update timpuesto set impuestopaypal=$2,
			impuestovisa=$3,impuestomastercard=$4,impuestodinnersclub=$5,
			porcentajecobro=$6 where codimpuesto=$1;
	resultado='correcto';
	mensaje='Datos Actualizados Correctamente';
	return Query select resultado,mensaje,codImp;
end
$$
LANGUAGE plpgsql;
   /*======modificar datos usuario======*/
   create or replace function Pricing_sp_ModificarDatosUsuario
 (
	clave varchar(128),
	nperfilcod int,
	imgusuario varchar(200),
	nrodoc varchar(12),
	nombres varchar(150),
	sexo varchar(1),
	fechaNacimiento Date,
	celular varchar(50),
	fechaInicio Date,
	correo varchar(100),
	codusuario varchar(150)
 )
 RETURNS table(resultado varchar(20), mensaje varchar(200), usuariocod varchar(150)) as
 $$
declare
	usuariocod varchar(150);
begin
		usuariocod=(select cusuariocod from tusuario where cusuariocod=$11);
		update tusuario set  cclave=$1,nperfilcod=$2,imgusuario=$3,cnrodoc=$4,
		cnombres=$5,csexo=$6,dfechanac=$7,ccelular=$8,dfechainicio=$9,ccorreo=$10,bestado=true where cusuariocod=$11;
		resultado='correcto';
		mensaje='Datos Actualizados Correctamente';
		return Query select resultado,mensaje,usuariocod;
end
$$
LANGUAGE plpgsql;
 /*======modificar datos usuario======*/
   create or replace function Pricing_sp_ModificarDatosUsuarioSuper
 (
	nperfilcod int,
	imgusuario varchar(200),
	nrodoc varchar(12),
	nombres varchar(150),
	correo varchar(100),
	estado boolean,
	codusuario varchar(150)
 )
 RETURNS table(resultado varchar(20), mensaje varchar(200), usuariocod varchar(150)) as
 $$
begin
		update tusuario set  nperfilcod=$1,imgusuario=$2,cnrodoc=$3,
		cnombres=$4,ccorreo=$5,bestado=$6 where cusuariocod=$7;
		resultado='correcto';
		mensaje='Datos Actualizados Correctamente';
		return Query select resultado,mensaje,usuariocod;
end
$$
LANGUAGE plpgsql;
/*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_ModificarServicio
Utilizado en	:Aplicacion Web FootPathPeru
Usuario		:
Fecha Creacion	:07/14/2016
Ejecucion	:SELECT * FROM AG_sp_RegistrarReserva('','20150410','20150430','43027528','luis@gmail.com','984289670',3,'informacion')
Eliminacion	:DROP FUNCTION AG_sp_RegistrarReserva(varchar(10),date,date,varchar(12),varchar(100),varchar(50),int,varchar(300))
Comentario	:Registrar una reserva
Modificacion	:
+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE FUNCTION Pricing_sp_ModificarServicio
(
  Serviciocod int,
  cServicioindioma_1 varchar(200),
  cServicioindioma_2 varchar(200),
  cServicioindioma_3 varchar(200),
  cServicioindioma_4 varchar(200),
  cServicioindioma_5 varchar(200),
  cDescripcionidioma_1 text,
  cDescripcionidioma_2 text,
  cDescripcionidioma_3 text,
  cDescripcionidioma_4 text,
  cDescripcionidioma_5 text,
  Restriccionyesno int,
  Restriccionnum int,
  Incremento int,
  Urlimg varchar(200),
  Precioservicio decimal(10,2),
  estado boolean,
  link text
)
RETURNS TABLE (resultado varchar(20),mensaje varchar(200),codSer int) as
$$
begin
	codSer=$1;
	update tservicio set cservicioindioma1=$2,
			  cservicioindioma2=$3,
			  cservicioindioma3=$4,
			  cservicioindioma4=$5,
			  cservicioindioma5=$6,
			  cdescripcionidioma1=$7,
			  cdescripcionidioma2=$8,
			  cdescripcionidioma3=$9,
			  cdescripcionidioma4=$10,
			  cdescripcionidioma5=$11,
			  crestriccionyesno=$12,
			  crestriccionnum=$13,
			  cincremento=$14,
			  curlimg=$15,
			  nprecioservicio=$16,
			  bestado=$17,
			  clink=$18
			  where nserviciocod=$1;
	resultado='correcto';
	mensaje='Datos Actualizados Correctamente';
	return Query select resultado,mensaje,codSer;
end
$$
LANGUAGE plpgsql;
/*++++++++++++++++++++++++++++++++++++++++*/
create or replace function Pricing_sp_ModificarActividad
(
  codActi int,
  cactidioma1 varchar(200),
  cactidioma2 varchar(200),
  cactidioma3 varchar(200),
  cactidioma4 varchar(200),
  cactidioma5 varchar(200),
  descripcionidioma1 text,
  descripcionidioma2 text,
  descripcionidioma3 text,
  descripcionidioma4 text,
  descripcionidioma5 text,
  urlimg varchar(200),
  nprecioact decimal(10,2),
  estado boolean
)
RETURNS TABLE (resultado varchar(20),mensaje varchar(200),codAct int) as
$$
begin
	codAct=$1;
	update tactividad set cactividadidioma1=$2,
			  cactividadidioma2=$3,
			  cactividadidioma3=$4,
			  cactividadidioma4=$5,
			  cactividadidioma5=$6,
			  cdescripcionidioma1=$7,
			  cdescripcionidioma2=$8,
			  cdescripcionidioma3=$9,
			  cdescripcionidioma4=$10,
			  cdescripcionidioma5=$11,
			  curlimg=$12,
			  nprecioactividad=$13,
			  bestado=$14 where nactividadcod=$1;
	resultado='correcto';
	mensaje='Datos Actualizados Correctamente';
	return Query select resultado,mensaje,codAct;
end
$$
language plpgsql;
  /*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_ModificarEtiqueta
Utilizado en	:Aplicacion Web FootPathPeru
Usuario		:
Fecha Creacion	:07/14/2016
Ejecucion	:SELECT * FROM AG_sp_RegistrarReserva('','20150410','20150430','43027528','luis@gmail.com','984289670',3,'informacion')
Eliminacion	:DROP FUNCTION AG_sp_RegistrarReserva(varchar(10),date,date,varchar(12),varchar(100),varchar(50),int,varchar(300))
Comentario	:Registrar una reserva
Modificacion	:
+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE FUNCTION Pricing_sp_ModificarEtiqueta
(
	CodEtiquet int,
	CIdioma_1 text,
	CIdioma_2 text,
	CIdioma_3 text,
	CIdioma_4 text,
	CIdioma_5 text
)
RETURNS TABLE (resultado varchar(20),mensaje varchar(200),codEt int) as
$$
begin
	codEt=$1;
	update TEtiqueta set cidioma1=$2,cidioma2=$3,cidioma3=$4,cidioma4=$5,cidioma5=$6 where codetiqueta=$1;
	resultado='correcto';
	mensaje='Datos Actualizados Correctamente';
	return Query select resultado,mensaje,codEt;
end
$$
LANGUAGE plpgsql;

  /*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_ModificarEtiqueta
Utilizado en	:Aplicacion Web FootPathPeru
Usuario		:
Fecha Creacion	:07/14/2016
Ejecucion	:SELECT * FROM AG_sp_RegistrarReserva('','20150410','20150430','43027528','luis@gmail.com','984289670',3,'informacion')
Eliminacion	:DROP FUNCTION AG_sp_RegistrarReserva(varchar(10),date,date,varchar(12),varchar(100),varchar(50),int,varchar(300))
Comentario	:Registrar una reserva
Modificacion	:
+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE FUNCTION Pricing_sp_ModificarSubServicio
(
  Subserviciocod int,
  Serviciocod int,
  cSubservicioindioma_1 varchar(200),
  cSubservicioindioma_2 varchar(200),
  cSubservicioindioma_3 varchar(200),
  cSubservicioindioma_4 varchar(200),
  cSubservicioindioma_5 varchar(200),
  cDescripcionidioma_1 text,
  cDescripcionidioma_2 text,
  cDescripcionidioma_3 text,
  cDescripcionidioma_4 text,
  cDescripcionidioma_5 text,
  Urlimg varchar(200),
  Link text,
  Precioservicio decimal(10,2),
  estado boolean
)
RETURNS TABLE (resultado varchar(20),mensaje varchar(200),codSubSer int) as
$$
declare
	antCodServicio int;
begin
	antCodServicio=(select nserviciocod from tsubservicio where nsubserviciocod=$1);
	codSubSer=$1;
	update tsubservicio set nserviciocod=$2,
			  csubservicioindioma1=$3,
			  csubservicioindioma2=$4,
			  csubservicioindioma3=$5,
			  csubservicioindioma4=$6,
			  csubservicioindioma5=$7,
			  cdescripcionidioma1=$8,
			  cdescripcionidioma2=$9,
			  cdescripcionidioma3=$10,
			  cdescripcionidioma4=$11,
			  cdescripcionidioma5=$12,
			  curlimg=$13,
			  clink=$14,
			  nprecioservicio=$15,
			  bestado=$16 where nsubserviciocod=$1;
			  if(estado=false) THEN
				 if((select count(nsubserviciocod) from tsubservicio where nserviciocod=$2 and bestado=true 
					group by nserviciocod) is null) THEN
						update tservicio set bestado=false where nserviciocod=$2;
					END IF;
			  else 
				update tservicio set bestado=true where nserviciocod=$2;
			  END IF;
			  IF((select count(nsubserviciocod) from tsubservicio where nserviciocod=antCodServicio and bestado=true)=0) THEN
				update tservicio set bestado=false where nserviciocod=antCodServicio;
			  END IF;
	resultado='correcto';
	mensaje='Datos Actualizados Correctamente';
	return Query select resultado,mensaje,codSubSer;
end
$$
LANGUAGE plpgsql;
   /*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_ModificarPaquetes
Utilizado en	:Aplicacion Web FootPathPeru
Usuario		:
Fecha Creacion	:07/14/2016
Ejecucion	:SELECT * FROM AG_sp_RegistrarReserva('','20150410','20150430','43027528','luis@gmail.com','984289670',3,'informacion')
Eliminacion	:DROP FUNCTION AG_sp_RegistrarReserva(varchar(10),date,date,varchar(12),varchar(100),varchar(50),int,varchar(300))
Comentario	:Registrar una reserva
Modificacion	:
+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE FUNCTION Pricing_sp_ModificarPaquetes
(
  Paquetecod varchar(10),
  cTituloidioma_1 varchar(200),
  cTituloidioma_2 varchar(200),
  cTituloidioma_3 varchar(200),
  cTituloidioma_4 varchar(200),
  cTituloidioma_5 varchar(200),
  cDescripcionidioma_1 text,
  cDescripcionidioma_2 text,
  cDescripcionidioma_3 text,
  cDescripcionidioma_4 text,
  cDescripcionidioma_5 text,
  Dias int,
  Noches int,
  Preciouno decimal(10,2),
  Preciodos decimal(10,2),
  Preciotres decimal(10,2),
  Preciocuatro decimal(10,2),
  Preciocinco decimal(10,2),
  Disponibilidad varchar(100),
  diaCaminoInka int,
  estado boolean,
  foto1 varchar(100),
  foto2 varchar(100),
  foto3 varchar(100),
  foto4 varchar(100),
  foto5 varchar(100),
  itinerarioidioma1 text,
  itinerarioidioma2 text,
  itinerarioidioma3 text,
  itinerarioidioma4 text,
  itinerarioidioma5 text,
  urlReferenciaPaquete text
)
RETURNS TABLE (resultado varchar(20),mensaje varchar(200),codPaquete varchar(10)) as
$$
begin
	codPaquete=$1;
	update tpaquete set ctituloidioma1=$2,
			  ctituloidioma2=$3,
			  ctituloidioma3=$4,
			  ctituloidioma4=$5,
			  ctituloidioma5=$6,
			  cdescripcionidioma1=$7,
			  cdescripcionidioma2=$8,
			  cdescripcionidioma3=$9,
			  cdescripcionidioma4=$10,
			  cdescripcionidioma5=$11,
			  ndias=$12,
			  nnoches=$13,
			  npreciouno=$14,
			  npreciodos=$15,
			  npreciotres=$16,
			  npreciocuatro=$17,
			  npreciocinco=$18,
			  cdisponibilidad=$19,
			  ndiacaminoinka=$20,
			  bestado=$21,
			  cfoto1=$22,
			  cfoto2=$23,
			  cfoto3=$24,
			  cfoto4=$25,
			  cfoto5=$26,
			  citinerarioidioma1=$27,
			  citinerarioidioma2=$28,
			  citinerarioidioma3=$29,
			  citinerarioidioma4=$30,
			  citinerarioidioma5=$31,
			  curlreferenciapaquete=$32
			  where cpaquetecod=$1;
	resultado='correcto';
	mensaje='Datos Actualizados Correctamente';
	return Query select resultado,mensaje,codPaquete;
end
$$
LANGUAGE plpgsql;
/*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_ModificarImpuesto
Utilizado en	:Aplicacion Web INFO
Usuario		:
Fecha Creacion	:07/14/2016
Ejecucion	:SELECT * FROM AG_sp_RegistrarReserva('','20150410','20150430','43027528','luis@gmail.com','984289670',3,'informacion')
Eliminacion	:DROP FUNCTION AG_sp_RegistrarReserva(varchar(10),date,date,varchar(12),varchar(100),varchar(50),int,varchar(300))
Comentario	:Registrar una reserva
Modificacion	:
+++++++++++++++++++++++++++++++++++++++++++++++++*/
create or replace function Pricing_sp_ModificarHotel
(
  hotelcod int,
  hotel varchar(200),
  descripcionidioma1 text,
  descripcionidioma2 text,
  descripcionidioma3 text,
  descripcionidioma4 text,
  descripcionidioma5 text,
  url varchar(200),
  codcategoriahotel int,
  preciosimple decimal(10,2),
  preciodoble decimal(10,2),
  preciotriple decimal(10,2),
  estado boolean,
  precioCamaAdicional decimal(10,2),
  codDestino int,
  foto1 varchar(100),
  foto2 varchar(100),
  foto3 varchar(100),
  foto4 varchar(100),
  foto5 varchar(100),
  fotoUbicacion varchar(100)
)
returns table(resultado varchar(20),
		mensaje varchar(200),
		codHotel int)as
$$
begin
        codHotel=$1;
        update thotel set chotel=$2,
                          cdescripcionidioma1=$3,
                          cdescripcionidioma2=$4,
                          cdescripcionidioma3=$5,
                          cdescripcionidioma4=$6,
                          cdescripcionidioma5=$7,
                          curl=$8,
                          categoriahotelcod=$9,
                          npreciosimple=$10,
                          npreciodoble=$11,
                          npreciotriple=$12,
                          bestado=$13,
                          npreciocamaadicional=$14,
                          ndestinocod=$15,
                          cfoto1=$16,
                          cfoto2=$17,
                          cfoto3=$18,
                          cfoto4=$19,
                          cfoto5=$20,
                          cimagenubicacion=$21
                      where nhotelcod=$1;
    resultado='correcto';
	mensaje='Datos Actualizados Correctamente';
	return Query select resultado,mensaje,codHotel;
end
$$
language plpgsql;

--******************************************
create or replace function Pricing_sp_ModificarEstadoPagoReserva
(
	reservacod varchar(12),
	estado varchar(20)
)
returns table(resultado varchar(20),mensaje varchar(200),codReserva varchar(12))as
$$
begin
	codReserva=$1;
	update TReserva set cestado=$2 where creservacod=$1;
	resultado='correcto';
	mensaje='Datos Actualizados Correctamente';
	return Query select resultado,mensaje,codReserva;
end
$$
language plpgsql;
--***************************************
create or replace function Pricing_sp_ModificarAcceso
(
	AccesoCod int,
	Idiomas boolean,
	UpdateDispo boolean,
	Etiqueta boolean,
	Impuesto boolean,
	Visa boolean,
	Paypal boolean,
	MasterdCard boolean,
	WesternUnion boolean,
	RegUsuarios boolean,
	CrearNuevoUser boolean,
	Paquetes boolean,
	Servicios boolean,
	SubServicios boolean,
	Actividades boolean,
	Hoteles boolean,
	Destinos boolean,
	ReporReservas boolean,
	ReporPagos boolean,
	EstadPagos boolean,
	EstadPaquMasVendidos boolean
)
returns table(resultado varchar(20),mensaje varchar(200),codAcceso int)as
$$
begin
	codAcceso=$1;
	update tacceso set accesoidiomas=$2,
					  accesoupdatedispo=$3,
					  accesoetiqueta=$4,
					  accesoimpuesto=$5,
					  accesovisa=$6,
					  accesopaypal=$7,
					  accesomasterdcard=$8,
					  accesowesternunion=$9,
					  accesoregusuarios=$10,
					  accesocrearnuevouser=$11,
					  accesopaquetes=$12,
					  accesoservicios=$13,
					  accesosubservicios=$14,
					  accesoactividades=$15,
					  accesohoteles=$16,
					  accesodestinos=$17,
					  accesoreporreservas=$18,
					  accesoreporpagos=$19,
					  accesoestadpagos=$20,
					  accesoestadpaqumasvendidos=$21
					  where naccesocod=$1;
	resultado='correcto';
	mensaje='Datos Actualizados Correctamente';
	return Query select resultado,mensaje,codAcceso;
end 
$$
language plpgsql;
--***************************************
create or replace function Pricing_sp_ModificarDestino
(
	codDestino int,
	nameDestino varchar(100),
	estado boolean,
	codPostal int,
	latitud varchar(20),
	longitud varchar(20),
	imagen varchar(100)
)
returns table(resultado varchar(20),mensaje varchar(200),codDest int)as
$$
begin
	codDest=$1;
	update TDestino set bestado=$3, cdestino=$2, ncodpostal=$4, clatitud=$5,clongitud=$6, cimagen=$7  where ndestinocod=$1;
	resultado='correcto';
	mensaje='Datos Actualizados Correctamente';
	return Query select resultado,mensaje,codDest;
end
$$
language plpgsql;
/*======modificar estado usuario======*/
  CREATE OR REPLACE FUNCTION Pricing_sp_ModificarEstadoUsuario
(
	usuariocod varchar(150),
	estado boolean
)
RETURNS TABLE (resultado varchar(20),mensaje varchar(200),codUsuario varchar(150)) as
$$
begin
	codUsuario=$1;
	update tusuario set bestado=$2 where cusuariocod=$1;
	resultado='correcto';
	mensaje='Datos Actualizados Correctamente';
	return Query select resultado,mensaje,codUsuario;
end
$$
LANGUAGE plpgsql;
/**MODIFICAR IMPUESTOS**/
CREATE OR REPLACE FUNCTION Pricing_sp_ModificarImpuesto
(
	nCodImpuesto int,
	cImpuestoPaypal varchar(5),
	cImpuestoVisa varchar(5),
	cImpuestoMasterCard varchar(5),
	cImpuestoDinnersClub varchar(5),
	cPorcentajeCobro varchar(5)
)
RETURNS TABLE (resultado varchar(20),mensaje varchar(200),codImp int) as
$$
begin
	codImp=$1;
	update timpuesto set impuestopaypal=$2,
			impuestovisa=$3,impuestomastercard=$4,impuestodinnersclub=$5,
			porcentajecobro=$6 where codimpuesto=$1;
	resultado='correcto';
	mensaje='Datos Actualizados Correctamente';
	return Query select resultado,mensaje,codImp;
end
$$
LANGUAGE plpgsql;
 /*======modificar datos usuario======*/
   create or replace function Pricing_sp_ModificarDatosUsuario
 (
	clave varchar(128),
	nperfilcod int,
	imgusuario varchar(200),
	nrodoc varchar(12),
	nombres varchar(150),
	sexo varchar(1),
	fechaNacimiento Date,
	celular varchar(50),
	fechaInicio Date,
	correo varchar(100),
	codusuario varchar(150)
 )
 RETURNS table(resultado varchar(20), mensaje varchar(200), usuariocod varchar(150)) as
 $$
declare
	usuariocod varchar(150);
begin
		usuariocod=(select cusuariocod from tusuario where cusuariocod=$11);
		update tusuario set  cclave=$1,nperfilcod=$2,imgusuario=$3,cnrodoc=$4,
		cnombres=$5,csexo=$6,dfechanac=$7,ccelular=$8,dfechainicio=$9,ccorreo=$10,bestado=true where cusuariocod=$11;
		resultado='correcto';
		mensaje='Datos Actualizados Correctamente';
		return Query select resultado,mensaje,usuariocod;
end
$$
LANGUAGE plpgsql;
--**************************************
create or replace function Pricing_sp_ModificarPaqueteDestino
(
	codpd int,
	noches int,
	itinerario int,
	con_caminoInka boolean
)
returns table(resultado varchar(20),mensaje varchar(200),codPDest int)as
$$
begin
	codPDest=$1;
	update TPaqueteDestino set nnoches=$2, nordenitinerario=$3, bconcaminoinka=$4 where codpd=$1;
	resultado='correcto';
	mensaje='Datos Actualizados Correctamente';
	return Query select resultado,mensaje,codPDest;
end
$$
language plpgsql;
--*********************************
/**MODIFICAR CUPON**/
CREATE OR REPLACE FUNCTION Pricing_sp_ModificarCupon
(
	CuponCod int,
	PorcentajeDcto int,
	FechaInicio date,
	FechaFin date
)
returns table(resultado varchar(20),mensaje varchar(200),codCupon int)as
$$
declare
	codRC int;
begin
	codCupon=$1;
	codRC=(select nreservacuponcod from treservacupon where ncuponcod=$1);
	if(codRC is null)then
		update tcupon set nporcentajedcto=$2,
						dfechainicio=$3,
						dfechafin=$4
						where ncuponcod=$1;
		resultado='correcto';
		mensaje='Datos Actualizados Correctamente';
	else
		resultado='incorrecto';
		mensaje='Datos no Actualizados';
	end if;
	return Query select resultado,mensaje,codCupon;
end 
$$
language plpgsql;
/*******************************/
/**MODIFICAR IMAGENES DE HOTEL**/
/*******************************/
CREATE OR REPLACE FUNCTION Pricing_sp_ModificarImagenesHotel
(
	codHotel int,
	foto1 varchar(100),
	foto2 varchar(100),
	foto3 varchar(100),
	foto4 varchar(100),
	foto5 varchar(100),
	imagenUbic varchar(100)
)
returns table(resultado varchar(20),mensaje varchar(200),hotelCod int)as
$$
begin
	hotelCod=$1;
	update thotel set cfoto1=$2,
					cfoto2=$3,
					cfoto3=$4,
					cfoto4=$5,
					cfoto5=$6,
					cimagenubicacion=$7
					where nhotelcod=$1;
	resultado='correcto';
	mensaje='Datos no Actualizados';
	return Query select resultado,mensaje,hotelcod;
end 
$$
language plpgsql;
/****************************************************************/
/*************************modificar imagenes paquete************/
/****************************************************************/
CREATE OR REPLACE FUNCTION Pricing_sp_ModificarImagenesPaquete
(
	codPaquete varchar(10),
	foto1 varchar(100),
	foto2 varchar(100),
	foto3 varchar(100),
	foto4 varchar(100),
	foto5 varchar(100)
)
returns table(resultado varchar(20),mensaje varchar(200),paqueteCod varchar(10))as
$$
begin
	paqueteCod=$1;
	update tpaquete set cfoto1=$2,
					cfoto2=$3,
					cfoto3=$4,
					cfoto4=$5,
					cfoto5=$6
					where cpaquetecod=$1;
	resultado='correcto';
	mensaje='Datos Actualizados';
	return Query select resultado,mensaje,paquetecod;
end 
$$
language plpgsql;
/**********************************************/
/**MODIFICAR ESTADO DE LAS GALERIAS DEL HOTEL**/
/**********************************************/
CREATE OR REPLACE FUNCTION Pricing_sp_ModificarGaleriaHotel
(
	codGaleria int,
	estado boolean
)
returns table(resultado varchar(20),mensaje varchar(200),galeriaCod int)as
$$
begin
	galeriaCod=$1;
	update tgaleriahotel set bestado=$2 where ngaleriahotelcod=$1;
	resultado='correcto';
	mensaje='Datos no Actualizados';
	return Query select resultado,mensaje,galeriaCod;
end 
$$
language plpgsql;
/**MODIFICAR ESTADO DE LAS GALERIAS DEL PAQUETE**/
/**********************************************/
CREATE OR REPLACE FUNCTION Pricing_sp_ModificarGaleriaPaquete
(
	codGaleria int,
	estado boolean
)
returns table(resultado varchar(20),mensaje varchar(200),galeriaCod int)as
$$
begin
	galeriaCod=$1;
	update tgaleriapaquete set bestado=$2 where ngaleriapaquetecod=$1;
	resultado='correcto';
	mensaje='Datos no Actualizados';
	return Query select resultado,mensaje,galeriaCod;
end 
$$
language plpgsql;
/*********************************/
/**MODIFICAR CALENDARIO YOURSELF**/
/*********************************/
create or replace function Pricing_sp_ModificarDisponibilidad_yourself(
	Disponibilidad int,
	Anio int,
	Mes int,
	Dia1 int,
	Dia2 int,
	Dia3 int,
	Dia4 int,
	Dia5 int,
	Dia6 int,
	Dia7 int,
	Dia8 int,
	Dia9 int,
	Dia10 int,
	Dia11 int,
	Dia12 int,
	Dia13 int,
	Dia14 int,
	Dia15 int,
	Dia16 int,
	Dia17 int,
	Dia18 int,
	Dia19 int,
	Dia20 int,
	Dia21 int,
	Dia22 int,
	Dia23 int,
	Dia24 int,
	Dia25 int,
	Dia26 int,
	Dia27 int,
	Dia28 int,
	Dia29 int,
	Dia30 int,
	Dia31 int
)
returns table(resultado varchar(20),mensaje varchar(200),codDisponibilidad int)as
$$
begin
	codDisponibilidad=$1;
	update tcalendario_yourself set nDia1=$4, nDia2=$5, nDia3=$6,nDia4=$7,nDia5=$8,nDia6=$9,nDia7=$10,nDia8=$11,nDia9=$12,
	nDia10=$13,nDia11=$14,nDia12=$15,nDia13=$16,nDia14=$17,nDia15=$18,nDia16=$19,nDia17=$20,nDia18=$21,nDia19=$22,nDia20=$23,nDia21=$24,nDia22=$25,nDia23=$26
	,nDia24=$27,nDia25=$28,nDia26=$29,nDia27=$30,nDia28=$31,nDia29=$32,nDia30=$33,nDia31=$34 where cDisponibilidad=$1 and nAnio=$2 and nMes=$3;
	resultado='correcto';
	mensaje='Datos Actualizados Correctamente';
	return Query select resultado,mensaje,codDisponibilidad;
end
$$
language plpgsql;