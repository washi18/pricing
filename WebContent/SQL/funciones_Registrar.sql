/*registrar galeria paquete*/
CREATE OR REPLACE FUNCTION Pricing_sp_RegistrarGaleriaPaquete
(
    PaqueteCod varchar(10),
    Imagen varchar(100),
    Estado boolean
)
returns table(resultado varchar(20),mensaje varchar(200),codGaleria int)as
$$
begin
    codGaleria=(select max(ngaleriapaquetecod) from tgaleriapaquete);
    if(codGaleria is null)then
        codGaleria=1;
    else
        codGaleria=codGaleria+1;
    end if;
    insert into tgaleriapaquete values(codGaleria,$1,$2,$3);
    resultado='correcto';
    mensaje='Datos Registrados Correctamente';
    return Query select resultado,mensaje,codGaleria;
end 
$$
language plpgsql;

/*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_RegistrarPaquete
Utilizado en	:Aplicacion Web FootPathPeru
Usuario		:
Fecha Creacion	:07/14/2016
Ejecucion	:SELECT * FROM AG_sp_RegistrarReserva('','20150410','20150430','43027528','luis@gmail.com','984289670',3,'informacion')
Eliminacion	:DROP FUNCTION AG_sp_RegistrarReserva(varchar(10),date,date,varchar(12),varchar(100),varchar(50),int,varchar(300))
Comentario	:Registrar una reserva
Modificacion	:
+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE FUNCTION Pricing_sp_RegistrarPaquete
(
	tituloPaqueteIdioma_1 varchar(200),
	tituloPaqueteIdioma_2 varchar(200),
	tituloPaqueteIdioma_3 varchar(200),
	tituloPaqueteIdioma_4 varchar(200),
	tituloPaqueteIdioma_5 varchar(200),
	descripcionidioma1 text,
	descripcionidioma2 text,
	descripcionidioma3 text,
	descripcionidioma4 text,
	descripcionidioma5 text,
	nroDias int,
	nroNoches int,
	precio_1 decimal(10,2),
	precio_2 decimal(10,2),
	precio_3 decimal(10,2),
	precio_4 decimal(10,2),
	precio_5 decimal(10,2),
	manejo varchar(100),
	dia_caminoInka int,
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
RETURNS TABLE (resultado varchar(20),
		mensaje varchar(200),
		codPaquete varchar(10)) as
$$
begin
	codPaquete=(select concat('P-',right(concat('00',count(p.cpaquetecod)+1),2)) from tpaquete p where left(p.cpaquetecod,2)='P-');
	insert into tpaquete values(codPaquete,$1,$2,$3,$4,$5,$6,$7,$8,$9,$10,
								$11,$12,$13,$14,$15,$16,$17,$18,true,$19,$20,$21,$22,$23,$24,$25,$26,$27,$28,$29,$30);
	resultado='correcto';
	mensaje='Datos Registrados Correctamente';
	return Query select resultado,mensaje,codPaquete;
end
$$
language plpgsql;
/*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_RegistrarPaqueteServicio
Utilizado en	:Aplicacion Web FootPathPeru
Usuario		:
Fecha Creacion	:07/14/2016
Ejecucion	:SELECT * FROM AG_sp_RegistrarReserva('','20150410','20150430','43027528','luis@gmail.com','984289670',3,'informacion')
Eliminacion	:DROP FUNCTION AG_sp_RegistrarReserva(varchar(10),date,date,varchar(12),varchar(100),varchar(50),int,varchar(300))
Comentario	:Registrar una reserva
Modificacion	:
+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE FUNCTION Pricing_sp_RegistrarPaqueteServicio
(
	codPaquete varchar(10),
	codServicio int
)
RETURNS TABLE (resultado varchar(20),
		mensaje varchar(200),
		codPaqueteServ int) as
$$
begin
	codPaqueteServ=(select max( codpaqueteservicio ) from tpaqueteservicio);
	if(codPaqueteServ is null)then
		codPaqueteServ=1;
	else
		codPaqueteServ=codPaqueteServ+1;
	end if;
	insert into tpaqueteservicio values(codPaqueteServ,$1,$2);
	resultado='correcto';
	mensaje='Datos Registrados Correctamente';
	return Query select resultado,mensaje,codPaqueteServ;
end
$$
language plpgsql;
/********************************/
create or replace function Pricing_sp_RegistrarAcceso
(
	nPerfilCod int,
	accesoIdiomas boolean,
	accesoUpdateDispo boolean,
	accesoEtiqueta boolean,
	accesoImpuesto boolean,
	accesoVisa boolean,
	accesoPaypal boolean,
	accesoMasterdCard boolean,
	accesoWesternUnion boolean,
	accesoRegUsuarios boolean,
	accesoCrearNuevoUser boolean,
	accesoPaquetes boolean,
	accesoServicios boolean,
	accesoSubServicios boolean,
	accesoActividades boolean,
	accesoHoteles boolean,
	accesoDestinos boolean,
	accesoReporReservas boolean,
	accesoReporPagos boolean,
	accesoEstadPagos boolean,
	accesoEstadPaquMasVendidos boolean
)
RETURNS TABLE (resultado varchar(20),
		mensaje varchar(200),
		codAcceso int) as
$$
begin
	codAcceso=(select max( naccesocod ) from tacceso);
	if(codAcceso is null)then
		codAcceso=1;
	else
		codAcceso=codAcceso+1;
	end if;
	insert into tacceso values(codAcceso,$1,$2,$3,$4,$5,$6,$7,$8,$9,$10,$11,$12,$13,$14,$15,
							$16,$17,$18,$19,$20,$21);
	resultado='correcto';
	mensaje='Datos Registrados Correctamente';
	return Query select resultado,mensaje,codAcceso;
end
$$
language plpgsql;
/*++++++++++++++++++++++++++++++++++++++++++++++*/
create or replace function Pricing_sp_RegistrarPerfil
(
	nombrePerfil varchar(100)
)
returns table(resultado varchar(20),mensaje varchar(200),codPerfil int)as
$$
begin
	codPerfil=(select max(nperfilcod)from tperfil);
	if(codPerfil is null)then
		codPerfil=1;
	else
		codPerfil=codPerfil+1;
	end if;
	insert into tperfil values(codPerfil,$1);
	resultado='correcto';
	mensaje='Datos Registrados Correctamente';
	return Query select resultado,mensaje,codPerfil;
end
$$
language plpgsql;
/*+++++++++++++++++++++++++++++++++++++++++++++++*/
create or replace function Pricing_sp_RegistrarUsuario
(
	codUsuario varchar(150),
	contrasenia varchar(128),
	perfilcod int,
    imguser varchar(200),
    nombres varchar(150),
    sexo char(1),
    fechanac date,
    celular varchar(50),
    correo varchar(100)
)
returns table(resultado varchar(20),mensaje varchar(200),codUser varchar(200))as
$$
begin
	codUser=$1;
	if (exists (select cusuariocod from tusuario where cusuariocod = $1))then
		resultado='existe';
		mensaje='Datos No Registrados';
	else
		insert into tusuario values($1,$2,$3,$4,$1,$5,$6,$7,$8,now(),$9,true);
		resultado='correcto';
		mensaje='Datos Registrados Correctamente';
	end if;
	return Query select resultado,mensaje,codUser;
end
$$
language plpgsql;
/*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_RegistrarPaqueteDestino
Utilizado en	:Aplicacion Web FootPathPeru
Usuario		:
Fecha Creacion	:07/14/2016
Ejecucion	:SELECT * FROM AG_sp_RegistrarReserva('','20150410','20150430','43027528','luis@gmail.com','984289670',3,'informacion')
Eliminacion	:DROP FUNCTION AG_sp_RegistrarReserva(varchar(10),date,date,varchar(12),varchar(100),varchar(50),int,varchar(300))
Comentario	:Registrar una reserva
Modificacion	:
+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE FUNCTION Pricing_sp_RegistrarPaqueteDestino
(
	codPaquete varchar(10),
	codDestino int,
	noches int,
	ordenItinerario int,
	conCaminoInka boolean
)
RETURNS TABLE (resultado varchar(20),
		mensaje varchar(200),
		codPaqueteDest int) as
$$
begin
	codPaqueteDest=(select max( codpaquetedestino ) from tpaquetedestino);
	if(codPaqueteDest is null)then
		codPaqueteDest=1;
	else
		codPaqueteDest=codPaqueteDest+1;
	end if;
	insert into tpaquetedestino values(codPaqueteDest,$1,$2,$3,$4,$5);
	resultado='correcto';
	mensaje='Datos Registrados Correctamente';
	return Query select resultado,mensaje,codPaqueteDest;
end
$$
language plpgsql;
/*++++++++++++++++++++++++++++++++++++++++++*/
create or replace function Pricing_sp_RegistrarPaqueteActividad
(
	codpaquete varchar(10),
	codActividad int
)
RETURNS TABLE (resultado varchar(20),
		mensaje varchar(200),
		codPaqueteAct int) as
$$
begin
	codPaqueteAct=(select max( npaqueteactividad ) from tpaqueteactividad);
	if(codPaqueteAct is null)then
		codPaqueteAct=1;
	else
		codPaqueteAct=codPaqueteAct+1;
	end if;
	insert into tpaqueteactividad values(codPaqueteAct,$1,$2);
	resultado='correcto';
	mensaje='Datos Registrados Correctamente';
	return Query select resultado,mensaje,codPaqueteAct;
end
$$
language plpgsql;
/*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_RegistrarPaqueteCatHotel
Utilizado en	:Aplicacion Web FootPathPeru
Usuario		:
Fecha Creacion	:07/14/2016
Ejecucion	:SELECT * FROM AG_sp_RegistrarReserva('','20150410','20150430','43027528','luis@gmail.com','984289670',3,'informacion')
Eliminacion	:DROP FUNCTION AG_sp_RegistrarReserva(varchar(10),date,date,varchar(12),varchar(100),varchar(50),int,varchar(300))
Comentario	:Registrar una reserva
Modificacion	:
+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE FUNCTION Pricing_sp_RegistrarPaqueteCatHotel
(
	codPaquete varchar(10)
)
RETURNS TABLE (resultado varchar(20),
		mensaje varchar(200),
		paqueteCod varchar(10)) as
$$
declare
	cont int;
	codPaqueteCH varchar(10);
begin
	cont=1;
	LOOP
	    IF cont > 7 THEN
	        EXIT;  -- exit loop
	    END IF;
	    codPaqueteCH=(select concat('PCH-',right(concat('0000',count(p.codpaquetecategoriah)+1),4)) from tpaquetecategoriahotel p where left(p.codpaquetecategoriah,4)='PCH-');
	    insert into tpaquetecategoriahotel values(codPaqueteCH,$1,cont);
	    cont=cont+1;
	END LOOP;
	resultado='correcto';
	mensaje='Datos Registrados Correctamente';
	return Query select resultado,mensaje,paqueteCod;
end
$$
language plpgsql;
/*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_RegistrarReserva
Utilizado en	:Aplicacion Web FootPathPeru
Usuario		:
Fecha Creacion	:05/19/2016
Ejecucion	:SELECT * FROM AG_sp_RegistrarReserva('','20150410','20150430','43027528','luis@gmail.com','984289670',3,'informacion')
Eliminacion	:DROP FUNCTION AG_sp_RegistrarReserva(varchar(10),date,date,varchar(12),varchar(100),varchar(50),int,varchar(300))
Comentario	:Registrar una reserva
Modificacion	:
+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE function Pricing_sp_RegistroReserva
(
	dFechaInicio Date,
	dFechaFin Date,
	cContacto varchar(100),
	cEmail varchar(100),
	cTelefono varchar(50),
	cPrecioPaquetePersona decimal(10,2),
	nNroPersonas int,
	cInformacionAdicional varchar(300),
	dFechaArribo Date
)
RETURNS TABLE (resultado varchar(20),
		mensaje varchar(200),
		cReservaCod varchar(12)) as 
$$
begin
	--generar el codigo de la reserva
	cReservaCod = (select concat('R',right(concat('000000000',count(r.cReservaCod)+1),9)) from treserva r where left(r.cReservaCod,1)='R');
	--registrar los datos de la reserva
	insert into TReserva values (cReservaCod,now(),$1,$2,$3,$4,$5,$6,$7,$8,'PENDIENTE DE PAGO','','',$9);
	resultado='correcto';
	mensaje='Datos Registrados Correctamente';
	return Query select resultado,mensaje,cReservaCod;
end
$$ 
LANGUAGE plpgsql;
--++++++++++++++++++++++++++++++++
create or replace function Pricing_sp_RegistrarPasajero
(
  creservacod varchar(12),
  nnro int,
  cnrodoc varchar(12),
  ntipodoc int,
  capellidos varchar(100),
  cnombres varchar(100),
  npaiscod int,
  csexo char(1),
  nedad int,
  curldocumento varchar(200)
)
returns table (resultado varchar(20),mensaje varchar(200),codReserva varchar(12))as
$$
declare
	codPax int;
begin
	codReserva=$1;
	codPax=(select max(codPasajero)from TPasajero);
	if(codPax is null)then
		codPax=1;
	else
		codPax=codPax+1;
	end if;
	insert into TPasajero values(codPax,$1,$2,$3,$4,$5,$6,$7,$8,$9,$10);
	resultado='correcto';
	mensaje='Datos Registrados Correctamente';
	return Query select resultado,mensaje,codReserva;
end
$$
language plpgsql;
--***********************************
create or replace function Pricing_sp_RegistrarReservaPS
(
  codpaqueteservicio bigint,
  reservaPaqueteCod bigint,
  nroprestacionservicio decimal(10,2),
  precioprestacionservicio decimal(10,2),
  codSubServicio int
)
returns table(resultado varchar(20),mensaje varchar(200),codReserva varchar(12))as
$$
declare
	codRPS int;
begin
	codReserva=$2;
	codRPS=(select max(codreservapservicio) from TReservaPaqueteServicio);
	if(codRPS is null)then
		codRPS=1;
	else
		codRPS=codRPS+1;
	end if;
		insert into TReservaPaqueteServicio values(codRPS,$1,$2,$3,$4,$5);
	resultado='correcto';
	mensaje='Datos Registrados Correctamente';
	return Query select resultado,mensaje,codReserva;
end
$$
language plpgsql;
/*******************************************************************/
/**REGISTRAR RESERVA DE LA ACTIVIDAD DEL PAQUETE QUE SE SELECCIONO**/
/*******************************************************************/
CREATE OR REPLACE FUNCTION Pricing_sp_RegistrarReservaPaqueteActividad
(
	codPaqActi bigint,
	reservaPaqueteCod bigint,
	nroPrestacionActividad int,
	precioPrestacionActividad decimal(10,2)
)
returns table(resultado varchar(20),mensaje varchar(200),codRPA int)as
$$
begin
		codRPA=(select max(codreservapactividad)from treservapaqueteactividad);
		if(codRPA is null)then
			codRPA=1;
		else
			codRPA=codRPA+1;
		end if;
		insert into treservapaqueteactividad values(codRPA,$1,$2,$3,$4);
		resultado='correcto';
		mensaje='Datos Registrados Correctamente';
		return Query select resultado,mensaje,codRPA;
end
$$
language plpgsql;
--***********************************
create or replace function Pricing_sp_RegistrarReservaPCH
(
  reservaPaquetecod bigint,
  codpaquetecategoriah varchar(10),
  nnropersonassimple int,
  npreciototalsimple decimal(10,2),
  nnropersonasdoble int,
  npreciototaldoble decimal(10,2),
  nnropersonastriple int,
  npreciototaltriple decimal(10,2)
)
returns table(resultado varchar(20),mensaje varchar(200),codReserva varchar(12))as
$$
declare
	codRPCH int;
begin
	codReserva=$1;
	codRPCH=(select max(codreservapcategoria) from TReservaPaqueteCategoriaHotel);
	if(codRPCH is null)then
		codRPCH=1;
	else
		codRPCH=codRPCH+1;
	end if;
	insert into TReservaPaqueteCategoriaHotel values(codRPCH,$1,$2,$3,$4,$5,$6,$7,$8);
	resultado='correcto';
	mensaje='Datos Registrados Correctamente';
	return Query select resultado,mensaje,codReserva;
end
$$
language plpgsql;
--*****************************************
create or replace function Pricing_sp_RegistrarFechaAlterna
(
  creservacod varchar(12),
  dfechainicio date,
  dfechafin date
)
returns table(resultado varchar(20),mensaje varchar(200),codReserva varchar(12))as
$$
declare
	codFA int;
begin
	codReserva=$1;
	codFA=(select max(codfechaalterna)from TFechaAlterna);
	if(codFA is null)then
		codFA=1;
	else
		codFA=codFA+1;
	end if;
	insert into TFechaAlterna values(codFA,$1,$2,$3);
	resultado='correcto';
	mensaje='Datos Registrados Correctamente';
	return Query select resultado,mensaje,codReserva;
end
$$
language plpgsql;
--********************** regitrar hoteles********************
create or replace function Pricing_sp_RegistrarHotel
(
  hotel varchar(200),
  descripcionIdioma1 text,
  descripcionIdioma2 text,
  descripcionIdioma3 text,
  descripcionIdioma4 text,
  descripcionIdioma5 text,
  url varchar(200),
  categoria int,
  precioSimple decimal(10,2),
  precioDoble decimal(10,2),
  precioTriple decimal(10,2),
  precioCamaAdicional decimal(10,2),
  codDestino int,
  foto1 varchar(100),
  foto2 varchar(100),
  foto3 varchar(100),
  foto4 varchar(100),
  foto5 varchar(100),
  fotoUbicacion varchar(100)
)
returns table(resultado varchar(20),mensaje varchar(200),codHotel int)as
$$
begin
	codHotel=(select max( nhotelcod ) from thotel);
	if(codHotel is null)then
		codHotel=1;
	else
		codHotel=codHotel+1;
	end if;
	insert into THotel values(codHotel,$1,$2,$3,$4,$5,$6,$7,$8,$9,$10,$11,true,
								$12,$13,$14,$15,$16,$17,$18,$19);
	
	update TDestino set bestado=true where ndestinocod=$13;
	resultado='correcto';
	mensaje='Datos Registrados Correctamente';
	return Query select resultado,mensaje,codHotel;
end
$$
language plpgsql;
--******************* registrar sub servicios***********************
create or replace function Pricing_sp_RegistrarSubServicio
(
  codigoServicio int,
  subServicioIdioma1 varchar(200),
  subServicioIdioma2 varchar(200),
  subServicioIdioma3 varchar(200),
  subServicioIdioma4 varchar(200),
  subServicioIdioma5 varchar(200),
  descripcionIdioma1 text,
  descripcionIdioma2 text,
  descripcionIdioma3 text,
  descripcionIdioma4 text,
  descripcionIdioma5 text,
  urlimagen varchar(200),
  link text,
  precioServicio decimal(10,2)
)
returns table(resultado varchar(20),mensaje varchar(200),codSubServicio int)as
$$
begin
	codSubServicio=(select max( nsubserviciocod ) from tsubservicio);
	if(codSubServicio is null)then
		codSubServicio=1;
	else
		codSubServicio=codSubServicio+1;
	end if;
	update tservicio set bestado=true where nserviciocod=$1;
	insert into TSubServicio values(codSubServicio,$1,$2,$3,$4,$5,$6,$7,$8,$9,$10,$11,$12,$13,$14,true);
	resultado='correcto';
	mensaje='Datos Registrados Correctamente';
	return Query select resultado,mensaje,codSubServicio;
end
$$
language plpgsql;
--***Registrar Servicio***
create or replace function Pricing_sp_RegistrarServicio
(
  cservicioindioma1 varchar(200),
  cservicioindioma2 varchar(200),
  cservicioindioma3 varchar(200),
  cservicioindioma4 varchar(200),
  cservicioindioma5 varchar(200),
  cdescripcionidioma1 text,
  cdescripcionidioma2 text,
  cdescripcionidioma3 text,
  cdescripcionidioma4 text,
  cdescripcionidioma5 text,
  crestriccionyesno int,
  crestriccionnum int,
  cincremento int,
  curlimg varchar(200),
  nprecioservicio decimal(10,2),
  bestado boolean,
  link text
)
returns table(resultado varchar(20),mensaje varchar(200),codServicio int)as
$$
begin
        codServicio=(select max( nserviciocod ) from tservicio);
        if(codServicio is null)then
        	codServicio=1;
        else
        	codServicio=codServicio+1;
        end if;
        insert into tservicio values(codServicio,$1,$2,$3,$4,$5,$6,$7,$8,$9,$10,$11,$12,$13,$14,$15,$16,$17);
        resultado='correcto';
        mensaje='Datos Registrados Correctamente';
        return Query select resultado,mensaje,codServicio;
end
$$
language plpgsql;
--**Resgistrar Actividad**--
create or replace function Pricing_sp_RegistrarActividad
(
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
  nprecioact decimal(10,2)
)
returns table(resultado varchar(20),mensaje varchar(200),codActividad int)as
$$
begin
        codActividad=(select max( nactividadcod ) from tactividad);
        if(codActividad is null)then
        	codActividad=1;
        else
        	codActividad=codActividad+1;
        end if;
        insert into tactividad values(codActividad,$1,$2,$3,$4,$5,$6,$7,$8,$9,$10,$11,$12,true);
        resultado='correcto';
        mensaje='Datos Registrados Correctamente';
        return Query select resultado,mensaje,codActividad;
end
$$
language plpgsql;
--**REGISTRAR CALENDARIO PROPIO**--
create or replace function Pricing_sp_RegistrarNuevoCalendarioPropio
(
	codPaquete varchar(10),
	anio int
)
returns table(resultado varchar(20),mensaje varchar(200),codCalendar int)as
$$
begin
	codCalendar=(select max(ncalendariocod) from tcalendario);
	if(codCalendar is null)then
        	codCalendar=1;
    else
        	codCalendar=codCalendar+1;
    end if;
	insert into tcalendario values(codCalendar,codPaquete,anio);
	insert into tcalendario values(codCalendar+1,codPaquete,anio+1);
	resultado='correcto';
    mensaje='Datos Registrados Correctamente';
    return Query select resultado,mensaje,codCalendar;
end 
$$
language plpgsql;
--**Registrar Destino**--
create or replace function Pricing_sp_RegistrarDestino
(
	nameDestino varchar(100),
	codPostal int,
	latitud varchar(20),
	longitud varchar(20),
	imagen varchar(100)
)
returns table(resultado varchar(20),mensaje varchar(200),codDestino int)as
$$
begin
	codDestino=(select max( ndestinocod ) from tdestino);
	if(codDestino is null)then
		codDestino=1;
	else
		codDestino=codDestino+1;
	end if;
        insert into tdestino values(codDestino,$1,false,$2,$3,$4,$5);
        resultado='correcto';
        mensaje='Datos Registrados Correctamente';
        return Query select resultado,mensaje,codDestino;
end
$$
language plpgsql;
--** registar paypal configuracion**--
create or replace function Pricing_sp_RegistrarPaypalConfig
(
  username varchar(200),
  cpassword varchar(100),
  csignature varchar(200),
  ccertkey varchar(100),
  ccertname varchar(100),
  caccountid varchar(100),
  csellerusername varchar(50)
)
returns table(resultado varchar(20),mensaje varchar(200),codPaypal int)as
$$
declare
	codPaypal int;
begin
	codPaypal=(select npaypalcod from tpaypalconfig);
	if(codPaypal is null)then
		insert into tpaypalconfig values(1,$1,$2,$3,$4,$5,$6,$7);
	else
		update tpaypalconfig set cusername=$1,cpassword=$2,csignature=$3,ccertkey=$4,ccertname=$5, caccountid=$6,csellerusername=$7 where npaypalcod=1;
	end if;
	resultado='correcto';
	mensaje='Datos Registrados Correctamente';
	return Query select resultado,mensaje,1;
end
$$
language plpgsql;
/*********************************************/
/**REGISTRAR CONFIG MASTERCARD Y DINERS CLUB**/
/*********************************************/
create or replace function Pricing_sp_RegistrarMasterDinersConfig
(
  codComercio varchar(7),
  keyMerchant varchar(40)
)
returns table(resultado varchar(20),mensaje varchar(200),codMD int)as
$$
begin
	codMD=(select nmasterdinerscod from tmasterdinersconfig);
	if(codMD is null)then
		insert into tmasterdinersconfig values(1,$1,$2);
	else
		update tmasterdinersconfig set ccodcomercio=$1,ckeymerchant=$2 where nmasterdinerscod=1;
	end if;
	resultado='correcto';
	mensaje='Datos Registrados Correctamente';
	return Query select resultado,mensaje,1;
end
$$
language plpgsql;
--** REGISTRAR CONFIGURACION DE CORREO SMTP**--
create or replace function Pricing_sp_RegistrarCorreoSMTP
(
	SMTPHost varchar(200),
	SMTPPort int,
	SSL boolean,
	TLS boolean,
	SMTPUserName varchar(200),
	SMTPPassword varchar(200)
)
returns table(resultado varchar(20),mensaje varchar(200),codCorreoSMTP int)as
$$
begin
	codCorreoSMTP=(select ncorreosmtpcod from TCorreoSMTP);
	if(codCorreoSMTP is null)then
		insert into TCorreoSMTP values(1,$1,$2,$3,$4,$5,$6);
	else
		update TCorreoSMTP set csmtphost=$1,
								nsmtpport=$2,
								bssl=$3,
								btsl=$4,
								csmtpusername=$5,
								csmtppassword=$6 where ncorreosmtpcod=1;
	end if;
	resultado='correcto';
	mensaje='Datos Registrados Correctamente';
	return Query select resultado,mensaje,codCorreoSMTP;
end 
$$
language plpgsql;
  /*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_ModificarImpuesto
Utilizado en	:Aplicacion Web FootPathPeru
Usuario		:
Fecha Creacion	:07/14/2016
Ejecucion	:SELECT * FROM AG_sp_RegistrarReserva('','20150410','20150430','43027528','luis@gmail.com','984289670',3,'informacion')
Eliminacion	:DROP FUNCTION AG_sp_RegistrarReserva(varchar(10),date,date,varchar(12),varchar(100),varchar(50),int,varchar(300))
Comentario	:Registrar una reserva
Modificacion	:
+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE FUNCTION Pricing_sp_RegistrarImpuesto
(
	cImpuestoPaypal varchar(5),
	cImpuestoVisa varchar(5),
	cImpuestoMasterCard varchar(5),
	cImpuestoDinnersClub varchar(5),
	cPorcentajeCobro varchar(5),
	cPagoMinimo varchar(5),
	bModoPorcentual boolean
)
RETURNS TABLE (resultado varchar(20),mensaje varchar(200),codImp int) as
$$
begin
	codImp=(select codimpuesto from timpuesto);
	if(codImp is null)then
		insert into timpuesto values(1,$1,$2,$3,$4,$5,$6,$7);
	else
		update timpuesto set impuestopaypal=$1,
			impuestovisa=$2,impuestomastercard=$3,impuestodinnersclub=$4,
			porcentajecobro=$5, pagominimo=$6,
			modoporcentual=$7 where codimpuesto=1;
	end if;
	resultado='correcto';
	mensaje='Datos Registrados Correctamente';
	return Query select resultado,mensaje,1;
end
$$
LANGUAGE plpgsql;
/*REGISTRAR CONFIGURACION DE URLs*/
create or replace function Pricing_sp_RegistrarConfigURL
(
	cUrlReturnPaypal text,
	cUrlPaginaWeb text,
	cUrlLogoEmpresa text,
	cLogoEmpresa text,
	cUrlServletPagoParcial text,
	cUrlServletPagoTotal text,
	cUrlTerminosYCondiciones text
)
RETURNS TABLE (resultado varchar(20),mensaje varchar(200),codConfigUrl int) as
$$
begin
	codConfigUrl=(select nconfigurlcod from tconfigurl);
	if(codConfigUrl is null)then
		insert into tconfigurl values(1,$1,$2,$3,$4,$5,$6,$7);
	else
		update tconfigurl set urlReturnPaypal=$1,
							urlPaginaWeb=$2,
							urlLogoEmpresa=$3,
							logoEmpresa=$4,
							urlServletPagoParcial=$5,
							urlServletPagoTotal=$6,
							urlTerminosYCondiciones=$7 where nconfigurlcod=1;
	end if;
	resultado='correcto';
	mensaje='Datos Registrados Correctamente';
	return Query select resultado,mensaje,1;
end
$$
LANGUAGE plpgsql;
/**REGISTRAR CONFIGURACION DE ESTILOS EN LA INTERFAZ**/
create or replace function Pricing_sp_RegistrarInterfazColores
(
	color_div_TituloPaquete varchar(200),
	color_lbl_TituloPaquete varchar(200),
	color_caption_FondoPasos varchar(200),
	color_lbl_TextoFondoPasos varchar(200),
	color_lbl_CircleFondoPasos varchar(200),
	color_lbl_CircleBordePasos varchar(200),
	color_lbl_CircleNumPasos varchar(200),
	color_div_ColFondoDatosPax varchar(200),
	color_div_ColBordeDatosPax varchar(200),
	color_lbl_ColDatosPasajeros varchar(200),
	color_listHeader_DatosHotel varchar(200),
	color_lbl_DatosHotel varchar(200),
	color_div_FondoBanderas varchar(200),
	color_caption_RyC varchar(200),
	color_lbl_RyC varchar(200),
	color_div_BordeRyC varchar(200),
	color_div_FondoTituloRyC varchar(200),
	color_lbl_TextoTituloRyC varchar(200),
	color_div_FondoImporte varchar(200),
	color_lbl_TextImporte varchar(200)
)
returns table(resultado varchar(20),mensaje varchar(200),codInterfaz int)as
$$
begin
	codInterfaz=(select ninterfazcod from tinterfaz);
	if(codInterfaz is null)then
		insert into tinterfaz values(1,$1,$2,$3,$4,$5,$6,$7,$8,$9,$10,$11,$12,$13,$14,
									$15,$16,$17,$18,$19,$20,false,true,false,false);
	else
		update tinterfaz set ccolor_div_titulopaquete=$1,
							ccolor_lbl_titulopaquete=$2,
							ccolor_caption_fondopasos=$3,
							ccolor_lbl_textofondopasos=$4,
							ccolor_lbl_circlefondopasos=$5,
							ccolor_lbl_circlebordepasos=$6,
							ccolor_lbl_circlenumpasos=$7,
							ccolor_div_colfondodatospax=$8,
							ccolor_div_colbordedatospax=$9,
							ccolor_lbl_coldatospasajeros=$10,
							ccolor_listheader_datoshotel=$11,
							ccolor_lbl_datoshotel=$12,
							ccolor_div_fondobanderas=$13,
							ccolor_caption_ryc=$14,
							ccolor_lbl_ryc=$15,
							ccolor_div_borderyc=$16,
							ccolor_div_fondotituloryc=$17,
							ccolor_lbl_textotituloryc=$18,
							ccolor_div_fondoimporte=$19,
							ccolor_lbl_textimporte=$20
							where ninterfazcod=1;
	end if;
	resultado='correcto';
	mensaje='Datos Registrados Correctamente';
	return Query select resultado,mensaje,1;
end 
$$
language plpgsql;
/**CONFIGURACION DE MODODE USO DE LA INTERFAZ DE PRICING**/
create or replace function Pricing_sp_RegistrarInterfazFormaPricing
(
	subirDocPax boolean,
	subirDoc_Y_llenarDatosPax boolean,
	subirDoc_O_llenarDatosPax boolean,
	llenarDatosUnPax boolean,
	hotelesConCamaAdicional boolean
)
returns table(resultado varchar(20),mensaje varchar(200),codInterfaz int)as
$$
begin
	codInterfaz=(select ninterfazcod from tinterfaz);
	if(codInterfaz is null)then
		insert into tinterfaz values(1,'','','','','',
									'','','','','',
									'','','','','',
									'','','','','',
									$1,$2,$3,$4,$5);
	else
		update tinterfaz set bsubirdocpax=$1,
							bsubirdoc_y_llenardatospax=$2,
							bsubirdoc_o_llenardatospax=$3,
							bllenardatosunpax=$4,
							bhotelesconcamaadicional=$5
							where ninterfazcod=1;
	end if;
	resultado='correcto';
	mensaje='Datos Registrados Correctamente';
	return Query select resultado,mensaje,1;
end 
$$
language plpgsql;
/**REGISTRAR CUPON**/
create or replace function Pricing_sp_RegistrarCupon
(
	Cupon varchar(10),
	PorcentajeDcto int,
	FechaInicio date,
	FechaFin date
)
returns table(resultado varchar(20),mensaje varchar(200),codCupon int)as
$$
begin
	codCupon=(select max(ncuponcod) from tcupon);
	if(codCupon is null)then
		codCupon=1;
	else
		codCupon=codCupon+1;
	end if;
	insert into tcupon values(codCupon,$1,$2,$3,$4);
	resultado='correcto';
	mensaje='Datos Registrados Correctamente';
	return Query select resultado,mensaje,codCupon;
end 
$$
language plpgsql;
/**REGISTRAR GALERIA HOTEL**/
CREATE OR REPLACE FUNCTION Pricing_sp_RegistrarGaleriaHotel
(
	HotelCod int,
	TipoImagen int,
	RutaImagen varchar(100),
	Estado boolean
)
returns table(resultado varchar(20),mensaje varchar(200),codGaleria int)as
$$
begin
	codGaleria=(select max(ngaleriahotelcod) from tgaleriahotel);
	if(codGaleria is null)then
		codGaleria=1;
	else
		codGaleria=codGaleria+1;
	end if;
	insert into tgaleriahotel values(codGaleria,$1,$2,$3,$4);
	resultado='correcto';
	mensaje='Datos Registrados Correctamente';
	return Query select resultado,mensaje,codGaleria;
end 
$$
language plpgsql;


/**REGISTRAR CUPON franklin**/
create or replace function pricing_sp_registrarcupon(
	creservacod varchar(12),
	ncuponcod int
)
RETURNS TABLE(resultado varchar(20),mensaje varchar(200),codcupon int) AS
$$
begin
	codcupon=(select max(ncuponcod) from treservacupon);
	if(codcupon is null)then 
		codcupon=1;
	else
		codcupon=codcupon+1;
	end if;
	insert into treservacupon values(codcupon,$1,$2);
	resultado='correcto';
	mensaje='Datos Registrados Correctamente';
	return Query select resultado,mensaje,codcupon;
end

/***************************/
/**REGISTRAR RESERVA CUPON washigton**/
/***************************/
CREATE OR REPLACE FUNCTION Pricing_sp_RegistrarReservaCupon
(
	reservaCod varchar(12),
	cuponCod int
)
returns table(resultado varchar(20),mensaje varchar(200),codRC int)as
$$
begin
	codRC=(select max(nreservacuponcod) from treservacupon);
	if(codRC is null)then
		codRC=1;
	else
		codRC=codRC+1;
	end if;
	insert into treservacupon values(codRC,$1,$2);
	resultado='correcto';
	mensaje='Datos Registrados Correctamente';
	return Query select resultado,mensaje,codRC;
end 
$$
language plpgsql;
/*****************************/
/**REGISTRAR RESERVA PAQUETE**/
/*****************************/
CREATE OR REPLACE FUNCTION Pricing_sp_RegistrarReservaPaquete
(
	codReserva varchar(12),
	codPaquete varchar(10),
	nroPasajeros int,
	montoTotalPaquete decimal(10,2)
)
returns table(resultado varchar(20),mensaje varchar(200),codRP bigint)as
$$
begin
		codRP=(select max(nreservapaquetecod) from treservapaquete);
		if(codRP is null)then
			codRP=1;
		else
			codRP=codRP+1;
		end if;
		insert into treservapaquete values(codRP,$1,$2,$3,$4);
		resultado='correcto';
		mensaje='Datos Registrados Correctamente';
		return Query select resultado,mensaje,codRP;
end
$$
language plpgsql;
/*********************************/
/**REGISTRAR CALENDARIO YOURSELF**/
/*********************************/
create or replace function Pricing_sp_CrearDisponibilidad_yourself(
	cDisponibilidad int,
	anioActual int,
	biciestoActual boolean,
	biciestoSig boolean
)
RETURNS TABLE (resultado varchar(20),
		mensaje varchar(200),
		codCalendario int) as
$$
begin
	for i in 1..12 loop
		codCalendario=(select max(nCalendarioCod) from tcalendario_yourself);
		if(codCalendario is null)then
			codCalendario=1;
		else
			codCalendario=codCalendario+1;
		end if;
		if(i=4 or i=6 or i=9 or i=11)then
			insert into tcalendario_yourself values(codCalendario,$1,$2,i,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1);
		end if;
		if(i=1 or i=3 or i=5 or i=7 or i=8 or i=10 or i=12)then
			insert into tcalendario_yourself values(codCalendario,$1,$2,i,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
		end if;
		if(i=2 and biciestoActual=true)then
			insert into tcalendario_yourself values(codCalendario,$1,$2,i,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1,-1);
		end if;
		if(i=2 and biciestoActual=false)then
			insert into tcalendario_yourself values(codCalendario,$1,$2,i,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1,-1,-1);
		end if;
	end loop;
	for j in 1..12 loop
		codCalendario=(select max(nCalendarioCod) from tcalendario_yourself);
		if(codCalendario is null)then
			codCalendario=1;
		else
			codCalendario=codCalendario+1;
		end if;
		if(j=4 or j=6 or j=9 or j=11)then
			insert into tcalendario_yourself values(codCalendario,$1,$2+1,j,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1);
		end if;
		if(j=1 or j=3 or j=5 or j=7 or j=8 or j=10 or j=12)then
			insert into tcalendario_yourself values(codCalendario,$1,$2+1,j,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
		end if;
		if(j=2 and biciestoSig=true)then
			insert into tcalendario_yourself values(codCalendario,$1,$2+1,j,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1,-1);
		end if;
		if(j=2 and biciestoSig=false)then
			insert into tcalendario_yourself values(codCalendario,$1,$2+1,j,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1,-1,-1);
		end if;
	end loop;
	resultado='correcto';
	mensaje='Datos Registrados Correctamente';
	return Query select resultado,mensaje,codCalendario;
end
$$
language plpgsql;
/*********************/
insert into tcalendario_yourself values(1,1,2017,1,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31),
					(2,1,2017,2,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,-1,-1,-1),
					(3,1,2017,3,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31),
					(4,1,2017,4,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,-1),
					(5,1,2017,5,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31),
					(6,1,2017,6,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,-1),
					(7,1,2017,7,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31),
					(8,1,2017,8,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31),
					(9,1,2017,9,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,-1),
					(10,1,2017,10,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31),
					(11,1,2017,11,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,-1),
					(12,1,2017,12,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31),
					(13,1,2018,1,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31),
					(14,1,2018,2,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,-1,-1,-1),
					(15,1,2018,3,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31),
					(16,1,2018,4,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,-1),
					(17,1,2018,5,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31),
					(18,1,2018,6,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,-1),
					(19,1,2018,7,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31),
					(20,1,2018,8,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31),
					(21,1,2018,9,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,-1),
					(22,1,2018,10,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31),
					(23,1,2018,11,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,-1),
					(24,1,2018,12,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31);