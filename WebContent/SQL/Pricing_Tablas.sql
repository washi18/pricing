/*****GALERIA PAQUETE***/
CREATE TABLE TGaleriaPaquete
(
	nGaleriaPaqueteCod int not null,
	cPaqueteCod varchar(10),
	cImage varchar(100),
	bEstado boolean,
	primary key(nGaleriaPaqueteCod),
	foreign key(cPaqueteCod)references tpaquete
);
/*****TABLA CONFIGURACION ALTO NIVEL***/
create table tconfigAltoNivel(
 codAltoNivel int,
 nperfilcod int,
 cnombreEntidad varchar(30),
 bEstado boolean,
 primary key(codAltoNivel),
 foreign key(nperfilcod)references tperfil
);
/****************************************/
/**TABLA GENERADOR DE ORDEN Y REGISTROS**/
/****************************************/
CREATE TABLE TGeneraOrdenReg
(
	nGeneraOrdenRegCod bigint,
	cOrden varchar(15),
	cRegistro varchar(20),
	primary key(nGeneraOrdenRegCod)
);
/**************************************/
/**TABLA MASTERCARD   Y DINERS CONFIG**/
/**************************************/
CREATE TABLE TMasterDinersConfig
(
	nMasterDinersCod int,
	cCodComercio varchar(7),
	cKeyMerchant varchar(40),
	primary key(nMasterDinersCod)
);
/*****************************/
/**TABLA CALENDARIO YOURSELF**/
/*****************************/
CREATE TABLE tcalendario_yourself
(
  ncalendariocod int,
  cdisponibilidad int,
  nanio int,
  nmes int,
  ndia1 int,
  ndia2 int,
  ndia3 int,
  ndia4 int,
  ndia5 int,
  ndia6 int,
  ndia7 int,
  ndia8 int,
  ndia9 int,
  ndia10 int,
  ndia11 int,
  ndia12 int,
  ndia13 int,
  ndia14 int,
  ndia15 int,
  ndia16 int,
  ndia17 int,
  ndia18 int,
  ndia19 int,
  ndia20 int,
  ndia21 int,
  ndia22 int,
  ndia23 int,
  ndia24 int,
  ndia25 int,
  ndia26 int,
  ndia27 int,
  ndia28 int,
  ndia29 int,
  ndia30 int,
  ndia31 int,
  PRIMARY KEY (ncalendariocod)
);
/*************************/
/**TABLA PAQUETE GALERIA**/
/*************************/
CREATE TABLE TGaleriaPaquete
(
	nGaleriaPaqueteCod bigint,
	cPaqueteCod varchar(10),
	cImage varchar(100),
	bEstado boolean,
	primary key(nGaleriaPaqueteCod),
	foreign key(cPaqueteCod)references TPaquete
);
/*************************/
/**TABLA RESERVA PAQUETE**/
/*************************/
CREATE TABLE TReservaPaquete
(
	nReservaPaqueteCod bigint,
	cReservaCod varchar(12),
	cPaqueteCod varchar(10),
	nroPasajerosPaquete int,
	nMontoTotalPaquete decimal(10,2),
	primary key (nReservaPaqueteCod),
	foreign key (cReservaCod) references treserva,
	foreign key (cPaqueteCod) references tpaquete
);
/***********************************/
/**TABLA RESERVA PAQUETE ACTIVIDAD**/
/***********************************/
create table TReservaPaqueteActividad(
	codreservapactividad bigint,
	codpaqueteactividad int,
	creservacod varchar(12),
	nroprestacionesactividad int,
	precioprestacionactividad decimal(10,2),
	primary key(codreservapactividad),
	foreign key(codpaqueteactividad) references tpaqueteactividad,
	foreign key(creservacod) references treserva
)
/************************/
/**TABLA TGALERIAHOTELE**/
/************************/
CREATE TABLE TGaleriaHotel
(
	nGaleriaHotelCod int,
	nHotelCod int,
	nTipoImagen int,
	cRutaImagen varchar(100),
	bEstado boolean,
	primary key(nGaleriaHotelCod),
	foreign key(nHotelCod)references thotel
);
/****************/
/**TABLA TCUPON**/
/****************/
CREATE TABLE TCupon
(
	nCuponCod int,
	cCupon varchar(10),
	nPorcentajeDcto int,
	dFechaInicio date,
	dFechaFin date,
	primary key(nCuponCod)
);
/***********************/
/**TABLA TRESERVACUPON**/
/***********************/
CREATE TABLE TReservaCupon
(
	nReservaCuponCod int,
	cReservaCod varchar(12),
	nCuponCod int,
	primary key (nReservaCuponCod),
	foreign key (cReservaCod)references treserva,
	foreign key (nCuponCod)references tcupon
);
/*******************/
/**TABLA TIMPUESTO**/
/*******************/
CREATE TABLE TImpuesto
(
  codImpuesto int,
  impuestoPaypal varchar(5),
  impuestoVisa varchar(5),
  impuestoMasterCard varchar(5),
  impuestoDinnersClub varchar(5),
  porcentajeCobro varchar(5),
  pagoMinimo varchar(5),
  modoPorcentual boolean,
  PRIMARY KEY (codImpuesto)
);
/*********************/
/**TABLA TCORREOSMTP**/
/*********************/
CREATE TABLE TCorreoSMTP
(
	nCorreoSMTPCod int,
	cSMTPHost varchar(200),
	nSMTPPort int,
	bSSL boolean,
	bTLS boolean,
	cSMTPUserName varchar(200),
	cSMTPPassword varchar(200),
	primary key(nCorreoSMTPCod)
);
CREATE TABLE TConfigURL
(
	nConfigUrlCod int,
	urlReturnPaypal text,
	urlPaginaWeb text,
	urlLogoEmpresa text,
	logoEmpresa text,
	urlServletPagoParcial text,
	urlServletPagoTotal text,
	urlTerminosYCondiciones text,
	primary key (nConfigUrlCod)
);
CREATE TABLE TInterfaz
(
	nInterfazCod int,
	cColor_div_TituloPaquete varchar(200),
	cColor_lbl_TituloPaquete varchar(200),
	cColor_caption_FondoPasos varchar(200),
	cColor_lbl_TextoFondoPasos varchar(200),
	cColor_lbl_CircleFondoPasos varchar(200),
	cColor_lbl_CircleBordePasos varchar(200),
	cColor_lbl_CircleNumPasos varchar(200),
	cColor_div_ColFondoDatosPax varchar(200),
	cColor_div_ColBordeDatosPax varchar(200),
	cColor_lbl_ColDatosPasajeros varchar(200),
	cColor_listHeader_DatosHotel varchar(200),
	cColor_lbl_DatosHotel varchar(200),
	cColor_div_FondoBanderas varchar(200),
	cColor_caption_RyC varchar(200),
	cColor_lbl_RyC varchar(200),
	cColor_div_BordeRyC varchar(200),
	cColor_div_FondoTituloRyC varchar(200),
	cColor_lbl_TextoTituloRyC varchar(200),
	cColor_div_FondoImporte varchar(200),
	cColor_lbl_TextImporte varchar(200),
	bSubirDocPax boolean,
	bSubirDoc_Y_llenarDatosPax boolean,
	bSubirDoc_O_llenarDatosPax boolean,
	bHotelesConCamaAdicional boolean,
	primary key(nInterfazCod)
);
CREATE TABLE TCalendario
(
	nCalendarioCod int,
	cPaqueteCod varchar(10),
	nAnio int,
	primary key(nCalendarioCod),
	foreign key(cPaqueteCod)references TPaquete
);
CREATE TABLE TDia
(
	nCalendarioCod int,
	nMes int,
	nDia int,
	nDispo_Pack int,
	nDispo_Inka int,
	foreign key(nCalendarioCod)references TCalendario
);
create table TPaypalConfig
(
   nPaypalCod int,
   cUserName varchar(200),
   cPassword varchar(100),
   cSignature varchar(200),
   cCertKey varchar(100),
   cCertName varchar(100),
   cAccountId varchar(100),  
   cSellerUserName varchar(50),
   primary key (nPaypalCod) 
);
CREATE TABLE TActividad
(
  nActividadCod int NOT NULL,
  cActividadIdioma1 varchar(200),
  cActividadIdioma2 varchar(200),
  cActividadIdioma3 varchar(200),
  cActividadIdioma4 varchar(200),
  cActividadIdioma5 varchar(200),
  cDescripcionIdioma1 text,
  cDescripcionIdioma2 text,
  cDescripcionIdioma3 text,
  cDescripcionIdioma4 text,
  cDescripcionIdioma5 text,
  cUrlImg varchar(200),
  nPrecioActividad decimal(10,2),
  bEstado boolean,
  primary key(nActividadCod)
);
CREATE TABLE TPaqueteActividad
(
	nPaqueteActividad int not null,
	cPaqueteCod varchar(10),
	nActividadCod int,
	primary key(nPaqueteActividad),
	foreign key(cPaqueteCod)references TPaquete,
	foreign key(nActividadCod)references TActividad
);
CREATE TABLE TAcceso
(
	nAccesoCod int,
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
	accesoEstadPaquMasVendidos boolean,
	foreign key (nPerfilCod)references TPerfil,
	primary key (nAccesoCod)
);
CREATE TABLE TPerfil
(
	nPerfilCod int,					--codigo del perfil
	cPerfilIdioma1 varchar(100),			--descripcion del perfil en el primer idioma
	primary key (nPerfilCod)
);
/*
 tabla calendario yourself
 
 */
CREATE TABLE tcalendario_yourself
(
  ncalendariocod int,
  cdisponibilidad varchar(100),
  nanio int,
  nmes int,
  ndia1 int,
  ndia2 int,
  ndia3 int,
  ndia4 int,
  ndia5 int,
  ndia6 int,
  ndia7 int,
  ndia8 int,
  ndia9 int,
  ndia10 int,
  ndia11 int,
  ndia12 int,
  ndia13 int,
  ndia14 int,
  ndia15 int,
  ndia16 int,
  ndia17 int,
  ndia18 int,
  ndia19 int,
  ndia20 int,
  ndia21 int,
  ndia22 int,
  ndia23 int,
  ndia24 int,
  ndia25 int,
  ndia26 int,
  ndia27 int,
  ndia28 int,
  ndia29 int,
  ndia30 int,
  ndia31 int,
  PRIMARY KEY (ncalendariocod)
)
/*
Datos de los usuarios que accederan al modulo de administracion de la pagina web de una agencia
*/
CREATE TABLE TUsuario
(
	cUsuarioCod varchar(150) not null,		--numero del documento del usuario
	cClave varchar(128),				--clave del usuario
	nPerfilCod int,					--codigo del perfil
	imgUsuario varchar(200),			--imagen de perfil del usuario
	cNroDoc varchar(12),				--numero del documento del usuario
	cNombres varchar(150),				--nombres del representante legal
	cSexo char(1),					--sexo del representante legal
	dFechaNac date,					--fecha de nacimiento del representante legal
	cCelular varchar(50),				--numero telefonico del usuario
	dFechaInicio date,
	cCorreo varchar(100),
	bEstado boolean,
	primary key (cUsuarioCod),
	foreign key (nPerfilCod) references TPerfil
);
insert into tusuario values('42714003','Ags2HNDkaPPvvOoF5+BM2Q==',1,'/img/usuarios/user.jpg','42714003','EDDY PONCE DE LEON HUAMAN','M','2016-12-23','926345634',now(),'info@eddyonsoft.com',true);
				
insert into tperfil values(1,'SUPER ADMINISTRADOR');
insert into tacceso values(1,1,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true);
--CREACION DE TABLAS DE PAGOS PAYPAL Y DE VISA E INSERCION DE DATOS ALA TABLA
CREATE TABLE TPagoVisa
(
	nroOrden INT,					--codigo del numero de orden generado para el proceso de pago
	cReservaCod VARCHAR(10),			--codigo de la reserva que se pretende pagar
	nImporte decimal(10,2),				--importe de la reserva que se pretende pagar y/o amortizar
	nPorcentaje decimal(10,2),			--porcentaje de pago del total del importe de la reserva
	formaPago VARCHAR(20),				--forma de pago del cliente (VISA, MASTERCARD,PAYPAL)
	estado VARCHAR(10),				--estado de la transaccion (AUTORIZADO,CANCELADO,DENEGADO,EXTORNADO,INICIADO)
	fechayhora_initx timestamp,			--fecha y hora del inicio de transaccion
	eticket varchar(50),				--codigo de eticket generado por visanet
	respuesta INT,					--codigo de respuesta de visanet
	cod_tienda varchar(20),				--codigo del comercio
	cod_accion VARCHAR(3),				--codigo de accion emitida por visanet
	dsc_cod_accion varchar(150),			--descripcion del codigo de accion emitido por visanet
	nro_tarjeta VARCHAR(20),			--numero de la tarjeta del cliente
	nom_th VARCHAR(100),				--nombre del tarjeta habiente (cliente)
	ori_tarjeta VARCHAR(2),				--origen de la tarjeta
	nom_Emisor VARCHAR(50),				--nombre del banco emisor de la tarjeta
	eci VARCHAR(5),					--codigo eci emitido por visanet
	dsc_eci VARCHAR(100),				--descripcion del codigo ECI emitido por visanet
	cod_autorizacion VARCHAR(6),			--codigo de autorizacion de pago emitido por visanet
	cod_rescvv2 VARCHAR(10),			--codigo de 3 digitos del reverso de la tarjeta
	imp_autorizado DECIMAL(10,2),			--importe autporizado por visanet en la transaccion
	fechayhora_fintx varchar(50),			--fecha y hora de fin de la transaccion
	fechayhora_deposito varchar(50),		--fecha y hora del deposito de pagos de visanet
	fechayhora_devolucion varchar(50),		--fecha y hora de devolucion, anulacion y/o extorno de pago
	dato_comercio VARCHAR(100),			--dato adicional del comercio
	PRIMARY KEY (nroOrden),
	FOREIGN KEY (cReservaCod) REFERENCES TReserva
);


create table TPagoPaypal(
    nroorden integer NOT NULL,   
    codpagador integer,
    cod_autorizacion character varying(20),
    nporcentaje numeric(10,2),
    creservacod character varying(10),
    formapago character varying(20),
    estado character varying(10),
    nimporte numeric(10,2),
    fechayhora_initx timestamp without time zone,
    nom_th character varying(100),
    nro_tarjeta character varying(20),
    adicionalpagador character varying(100),
    telefonopagador character varying(50),
    pais character varying(3),
    estadopagador character varying(100),
    detalleimpuesto character varying(20),
    direccion character varying(50),
    emailpagador character varying(100),
    PRIMARY KEY (nroOrden),
    FOREIGN KEY (cReservaCod) REFERENCES TReserva
);

CREATE TABLE TPagoVisa
(
	nroOrden INT,					--codigo del numero de orden generado para el proceso de pago
	cReservaCod VARCHAR(10),			--codigo de la reserva que se pretende pagar
	nImporte decimal(10,2),				--importe de la reserva que se pretende pagar y/o amortizar
	nPorcentaje decimal(10,2),			--porcentaje de pago del total del importe de la reserva
	formaPago VARCHAR(20),				--forma de pago del cliente (VISA, MASTERCARD,PAYPAL)
	estado VARCHAR(10),				--estado de la transaccion (AUTORIZADO,CANCELADO,DENEGADO,EXTORNADO,INICIADO)
	fechayhora_initx timestamp,			--fecha y hora del inicio de transaccion
	eticket varchar(50),				--codigo de eticket generado por visanet
	respuesta INT,					--codigo de respuesta de visanet
	cod_tienda varchar(20),				--codigo del comercio
	cod_accion VARCHAR(3),				--codigo de accion emitida por visanet
	dsc_cod_accion varchar(150),			--descripcion del codigo de accion emitido por visanet
	nro_tarjeta VARCHAR(20),			--numero de la tarjeta del cliente
	nom_th VARCHAR(100),				--nombre del tarjeta habiente (cliente)
	ori_tarjeta VARCHAR(2),				--origen de la tarjeta
	nom_Emisor VARCHAR(50),				--nombre del banco emisor de la tarjeta
	eci VARCHAR(5),					--codigo eci emitido por visanet
	dsc_eci VARCHAR(100),				--descripcion del codigo ECI emitido por visanet
	cod_autorizacion VARCHAR(6),			--codigo de autorizacion de pago emitido por visanet
	cod_rescvv2 VARCHAR(10),			--codigo de 3 digitos del reverso de la tarjeta
	imp_autorizado DECIMAL(10,2),			--importe autporizado por visanet en la transaccion
	fechayhora_fintx varchar(50),			--fecha y hora de fin de la transaccion
	fechayhora_deposito varchar(50),		--fecha y hora del deposito de pagos de visanet
	fechayhora_devolucion varchar(50),		--fecha y hora de devolucion, anulacion y/o extorno de pago
	dato_comercio VARCHAR(100),			--dato adicional del comercio
	PRIMARY KEY (nroOrden),
	FOREIGN KEY (cReservaCod) REFERENCES TReserva
);


create table TPagoPaypal(
    nroorden integer NOT NULL,   
    codpagador integer,
    cod_autorizacion character varying(20),
    nporcentaje numeric(10,2),
    creservacod character varying(10),
    formapago character varying(20),
    estado character varying(10),
    nimporte numeric(10,2),
    fechayhora_initx timestamp without time zone,
    nom_th character varying(100),
    nro_tarjeta character varying(20),
    adicionalpagador character varying(100),
    telefonopagador character varying(50),
    pais character varying(3),
    estadopagador character varying(100),
    detalleimpuesto character varying(20),
    direccion character varying(50),
    emailpagador character varying(100),
    PRIMARY KEY (nroOrden),
    FOREIGN KEY (cReservaCod) REFERENCES TReserva
);
--CREACION DE TABLAS DE PAGOS PAYPAL Y DE VISA E INSERCION DE DATOS ALA TABLA

insert into TPagoVisa values(1002,'R000000002',125.50,0.3,'VISA','AUTORIZADO','2016-07-22 13:29:44','E002',12344,'T002','A02','codigo de accion1','TAR0002',
			'JULIO GOMEZ','AA','CONTINENTAL','E0002','eci enviado 2','C00002','123',0.18,'2016-07-22 14:29:44','2016-07-22 14:34:44','2016-07-22 15:29:44','comercio2'),
			(1003,'R000000003',125.50,0.3,'VISA','AUTORIZADO','2016-07-22 13:29:44','E003',12344,'T003','A03','codigo de accion1','TAR0003',
			'RAUL GOMEZ','AA','CONTINENTAL','E0002','eci enviado 3','C00002','123',0.18,'2016-07-22 14:29:44','2016-07-22 14:34:44','2016-07-22 15:29:44','comercio2'),
			(1004,'R000000004',125.50,0.3,'VISA','AUTORIZADO','2016-07-22 13:29:44','E004',12344,'T004','A04','codigo de accion1','TAR0003',
			'PEDRO GOMEZ','AA','CONTINENTAL','E0002','eci enviado 4','C00002','123',0.18,'2016-07-22 14:29:44','2016-07-22 14:34:44','2016-07-22 15:29:44','comercio2'),
			(1005,'R000000005',125.50,0.3,'VISA','AUTORIZADO','2016-07-22 13:29:44','E005',12344,'T005','A05','codigo de accion1','TAR0003',
			'OSCAR GOMEZ','AA','CONTINENTAL','E0002','eci enviado 5','C00002','123',0.18,'2016-07-22 14:29:44','2016-07-22 14:34:44','2016-07-22 15:29:44','comercio2');


insert into tpagopaypal values(20006,40001,'C00001',0.40,'R000000006','PAYPAL','AUTORIZADO',237.00,'2016-07-22 13:29:44','LEONARDO CRUZ','T01938373',
								'trabaja en agencia','72353532','PER','ESTADO1','impuesto sera 1','av.collasuyo A-15','leo@gmail.com'),
								(20007,40002,'C00002',0.40,'R000000007','PAYPAL','AUTORIZADO',257.00,'2016-07-22 13:29:44','JUAN CRUZ','T019123373',
								'trabaja en agencia','72353532','PER','ESTADO1','impuesto sera 1','av.collasuyo A-15','juan@gmail.com'),
								(20008,40003,'C00003',0.40,'R000000008','PAYPAL','AUTORIZADO',187.00,'2016-07-23 13:29:44','MARIO CRUZ','T01924373',
								'trabaja en agencia','72353532','COL','ESTADO1','impuesto sera 1','av.collasuyo A-15','mario@gmail.com'),
								(20009,40004,'C00004',0.40,'R000000009','PAYPAL','INICIADO',297.00,'2016-07-24 13:29:44','CARMEN CRUZ','T01758373',
								'trabaja en agencia','72353532','USA','ESTADO1','impuesto sera 1','av.collasuyo A-15','carmen@gmail.com'),
								(20010,40005,'C00005',0.40,'R000000010','PAYPAL','INICIADO',183.00,'2016-07-25 13:29:44','JOSE CRUZ','T01468373',
								'trabaja en agencia','72353532','ECU','ESTADO1','impuesto sera 1','av.collasuyo A-15','jose@gmail.com');
insert into TPagoVisa values(1006,'R0000000012',125.50,0.3,'VISA','AUTORIZADO','2016-07-22 13:29:44','E002',12344,'T002','A02','codigo de accion1','TAR0002',
			'JULIO GOMEZ','AA','CONTINENTAL','E0002','eci enviado 2','C00002','123',0.18,'2016-07-22 14:29:44','2016-07-22 14:34:44','2016-07-22 15:29:44','comercio2'),
			(1007,'R000000013',225.50,0.2,'VISA','AUTORIZADO','2016-07-22 13:29:44','E003',12344,'T003','A03','codigo de accion1','TAR0003',
			'RAUL GOMEZ','AA','CONTINENTAL','E0002','eci enviado 3','C00002','123',0.18,'2016-07-22 14:29:44','2016-07-22 14:34:44','2016-07-22 15:29:44','comercio2'),
			(1008,'R000000014',155.50,0.4,'VISA','AUTORIZADO','2016-07-22 13:29:44','E004',12344,'T004','A04','codigo de accion1','TAR0003',
			'PEDRO GOMEZ','AA','CONTINENTAL','E0002','eci enviado 4','C00002','123',0.18,'2016-07-22 14:29:44','2016-07-22 14:34:44','2016-07-22 15:29:44','comercio2'),
			(1009,'R000000015',185.50,0.3,'VISA','AUTORIZADO','2016-07-22 13:29:44','E005',12344,'T005','A05','codigo de accion1','TAR0003',
			'OSCAR GOMEZ','AA','CONTINENTAL','E0002','eci enviado 5','C00002','123',0.18,'2016-07-22 14:29:44','2016-07-22 14:34:44','2016-07-22 15:29:44','comercio2');


insert into tpagopaypal values(20010,40001,'C00001',0.40,'R000000017','PAYPAL','AUTORIZADO',237.00,'2016-07-22 13:29:44','LEONARDO CRUZ','T01938373',
								'trabaja en agencia','72353532','PER','ESTADO1','impuesto sera 1','av.collasuyo A-15','leo@gmail.com'),
								(20011,40002,'C00002',0.40,'R000000018','PAYPAL','AUTORIZADO',257.00,'2016-07-22 13:29:44','JUAN CRUZ','T019123373',
								'trabaja en agencia','72353532','PER','ESTADO1','impuesto sera 1','av.collasuyo A-15','juan@gmail.com'),
								(20012,40003,'C00003',0.40,'R000000019','PAYPAL','AUTORIZADO',187.00,'2016-07-23 13:29:44','MARIO CRUZ','T01924373',
								'trabaja en agencia','72353532','COL','ESTADO1','impuesto sera 1','av.collasuyo A-15','mario@gmail.com'),
								(20013,40004,'C00004',0.40,'R000000020','PAYPAL','INICIADO',297.00,'2016-07-24 13:29:44','CARMEN CRUZ','T01758373',
								'trabaja en agencia','72353532','USA','ESTADO1','impuesto sera 1','av.collasuyo A-15','carmen@gmail.com'),
select * from tpagopaypal;

insert into TPagoVisa values(1002,'R000000002',125.50,0.3,'VISA','AUTORIZADO','2016-07-22 13:29:44','E002',12344,'T002','A02','codigo de accion1','TAR0002',
			'JULIO GOMEZ','AA','CONTINENTAL','E0002','eci enviado 2','C00002','123',0.18,'2016-07-22 14:29:44','2016-07-22 14:34:44','2016-07-22 15:29:44','comercio2'),
			(1003,'R000000003',125.50,0.3,'VISA','AUTORIZADO','2016-07-22 13:29:44','E003',12344,'T003','A03','codigo de accion1','TAR0003',
			'RAUL GOMEZ','AA','CONTINENTAL','E0002','eci enviado 3','C00002','123',0.18,'2016-07-22 14:29:44','2016-07-22 14:34:44','2016-07-22 15:29:44','comercio2'),
			(1004,'R000000004',125.50,0.3,'VISA','AUTORIZADO','2016-07-22 13:29:44','E004',12344,'T004','A04','codigo de accion1','TAR0003',
			'PEDRO GOMEZ','AA','CONTINENTAL','E0002','eci enviado 4','C00002','123',0.18,'2016-07-22 14:29:44','2016-07-22 14:34:44','2016-07-22 15:29:44','comercio2'),
			(1005,'R000000005',125.50,0.3,'VISA','AUTORIZADO','2016-07-22 13:29:44','E005',12344,'T005','A05','codigo de accion1','TAR0003',
			'OSCAR GOMEZ','AA','CONTINENTAL','E0002','eci enviado 5','C00002','123',0.18,'2016-07-22 14:29:44','2016-07-22 14:34:44','2016-07-22 15:29:44','comercio2');


insert into tpagopaypal values(20006,40001,'C00001',0.40,'R000000006','PAYPAL','AUTORIZADO',237.00,'2016-07-22 13:29:44','LEONARDO CRUZ','T01938373',
								'trabaja en agencia','72353532','PER','ESTADO1','impuesto sera 1','av.collasuyo A-15','leo@gmail.com'),
								(20007,40002,'C00002',0.40,'R000000007','PAYPAL','AUTORIZADO',257.00,'2016-07-22 13:29:44','JUAN CRUZ','T019123373',
								'trabaja en agencia','72353532','PER','ESTADO1','impuesto sera 1','av.collasuyo A-15','juan@gmail.com'),
								(20008,40003,'C00003',0.40,'R000000008','PAYPAL','AUTORIZADO',187.00,'2016-07-23 13:29:44','MARIO CRUZ','T01924373',
								'trabaja en agencia','72353532','COL','ESTADO1','impuesto sera 1','av.collasuyo A-15','mario@gmail.com'),
								(20009,40004,'C00004',0.40,'R000000009','PAYPAL','INICIADO',297.00,'2016-07-24 13:29:44','CARMEN CRUZ','T01758373',
								'trabaja en agencia','72353532','USA','ESTADO1','impuesto sera 1','av.collasuyo A-15','carmen@gmail.com'),
								(20010,40005,'C00005',0.40,'R000000010','PAYPAL','INICIADO',183.00,'2016-07-25 13:29:44','JOSE CRUZ','T01468373',
								'trabaja en agencia','72353532','ECU','ESTADO1','impuesto sera 1','av.collasuyo A-15','jose@gmail.com');
insert into TPagoVisa values(1006,'R0000000012',125.50,0.3,'VISA','AUTORIZADO','2016-07-22 13:29:44','E002',12344,'T002','A02','codigo de accion1','TAR0002',
			'JULIO GOMEZ','AA','CONTINENTAL','E0002','eci enviado 2','C00002','123',0.18,'2016-07-22 14:29:44','2016-07-22 14:34:44','2016-07-22 15:29:44','comercio2'),
			(1007,'R000000013',225.50,0.2,'VISA','AUTORIZADO','2016-07-22 13:29:44','E003',12344,'T003','A03','codigo de accion1','TAR0003',
			'RAUL GOMEZ','AA','CONTINENTAL','E0002','eci enviado 3','C00002','123',0.18,'2016-07-22 14:29:44','2016-07-22 14:34:44','2016-07-22 15:29:44','comercio2'),
			(1008,'R000000014',155.50,0.4,'VISA','AUTORIZADO','2016-07-22 13:29:44','E004',12344,'T004','A04','codigo de accion1','TAR0003',
			'PEDRO GOMEZ','AA','CONTINENTAL','E0002','eci enviado 4','C00002','123',0.18,'2016-07-22 14:29:44','2016-07-22 14:34:44','2016-07-22 15:29:44','comercio2'),
			(1009,'R000000015',185.50,0.3,'VISA','AUTORIZADO','2016-07-22 13:29:44','E005',12344,'T005','A05','codigo de accion1','TAR0003',
			'OSCAR GOMEZ','AA','CONTINENTAL','E0002','eci enviado 5','C00002','123',0.18,'2016-07-22 14:29:44','2016-07-22 14:34:44','2016-07-22 15:29:44','comercio2');


insert into tpagopaypal values(20010,40001,'C00001',0.40,'R000000017','PAYPAL','AUTORIZADO',237.00,'2016-07-22 13:29:44','LEONARDO CRUZ','T01938373',
								'trabaja en agencia','72353532','PER','ESTADO1','impuesto sera 1','av.collasuyo A-15','leo@gmail.com'),
								(20011,40002,'C00002',0.40,'R000000018','PAYPAL','AUTORIZADO',257.00,'2016-07-22 13:29:44','JUAN CRUZ','T019123373',
								'trabaja en agencia','72353532','PER','ESTADO1','impuesto sera 1','av.collasuyo A-15','juan@gmail.com'),
								(20012,40003,'C00003',0.40,'R000000019','PAYPAL','AUTORIZADO',187.00,'2016-07-23 13:29:44','MARIO CRUZ','T01924373',
								'trabaja en agencia','72353532','COL','ESTADO1','impuesto sera 1','av.collasuyo A-15','mario@gmail.com'),
								(20013,40004,'C00004',0.40,'R000000020','PAYPAL','INICIADO',297.00,'2016-07-24 13:29:44','CARMEN CRUZ','T01758373',
								'trabaja en agencia','72353532','USA','ESTADO1','impuesto sera 1','av.collasuyo A-15','carmen@gmail.com'),
select * from tpagopaypal;
