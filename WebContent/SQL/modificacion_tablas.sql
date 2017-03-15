--TABLA TPAQUETE--
ALTER TABLE tpaquete ADD COLUMN citinerarioidioma1 text DEFAULT 'DAY 1: CUSCO - PISCACUCHO - HUAYLLABAMABA

The Classic Inca Trail tour starts very early in the morning, we will depart from your hotel and then move by bus, going by the Sacred Valley, the towns of Urubamba and Ollantaytambo. We make a short stop here, you can visit the local market and buy some supplies for the trek to this place named Piscacucho, located at km 82, to start the walk along the Urubamba river. Continuing the section through a green area, it is likely to see the giant hummingbird beak. About 2 hours, we reach the community of Wayllabamba to 3,000 m, the first night we camp, the walk of the first day will be quiet. This first day is usually hot during the dry season (May to October), it is recommended to wear light clothing, a good sunscreen and a good hat or cap.

INCLUDE: Lunch, dinner and overnight at camp.

DAY 2: HUAYLLABANBA - PACAYMAYO (The Abra)
On this day , we begin the difficult but not impossible walking, we walk until the Abra of Warmihuañuska, located 4,200 m.a.s.l., you will get all the encouragement and physical support of the enthusiastic team and our professional guide. You will see an incredible panoramic view around; we will pass through a cloud forest to Llulluchapampa, a good place to meet and relax before boarding the latter part of Warmihuañuska which means “step of the dead woman pass”, it has a distance of 11 km. You will appreciate how nature changes, once we have conquered the summit, we will be offset by a fascinating view of the snow. After this experience we will stop for lunch. During the trip you can observe sparrows and the spectacled bear. Following the route we descend into the valley of river Pacaymayo located at 3,500 m.a.s.l., where we have our second camp.

Includes: Lunch, dinner and overnight at camp.

DAY 3: PACAYAMAYO - WIÑAYWAYNA
For many visitors, it’s one of the longest but the most fascinating day, because of the presence of archaeological sites along the trail days. We head toward the Runkuracay (3,800 m.a.s.l.) and then the complex Sayacmarca (3,600 m.a.s.l.), called “The dominant people” this ever came to be a temple and astronomic observatory. After a guided tour, we are passing through Phuyupatamarca (3,600 masl), called “Village above the clouds” and is certainly one of the original cities, here we can see terraced fields and ceremonial sources. After a short break lunch will be served. About 2,000 cobblestone stairs we descend to Wiñaywayna (2,700 m.a.s.l.), which means “Forever Young” because of the abundance of red, yellow and violet orchid flowers, they bloom almost throughout the year. After exploring every corner, we turn to the last camp. Here, in the evening a small party is held to thank everyone for joining the group at this beautiful experience.

Includes: Breakfast, lunch, dinner and camping.

DAY 4: WIÑAYWAYNA - MACHUPICCHU - CUSCO
On this last day, we wake up early, heading to the famous Inti Punku or “Sun Gate” at 2,700 m.a.s.l to arrive on time and watch the sunrise. This center was one gateway to Machu Picchu, used as a control. It is an excellent opportunity to take panoramic pictures of the citadel of Machu Picchu. Camping is prohibited into Inti Punko (Sun Gate). You must leave your luggage at the control input and enjoy, you will reach the longed citadel, where after a short break, immersed in the energy of the place, will begin a guided tour. You will have time to walk around Machu Picchu, visit its squares, fountains and others. You’ll have free time to take photos. After a few hours, we descend to Aguas Calientes (2,160 m.a.s.l.) where you can visit the thermal baths by yourself (optional). Return to the city of Cusco. We recommend that you check the departure times of the train to return to Cusco.

Includes: Breakfast.';
ALTER TABLE tpaquete alter COLUMN citinerarioidioma1 drop DEFAULT;

ALTER TABLE tpaquete ADD COLUMN citinerarioidioma2 text DEFAULT '';
ALTER TABLE tpaquete alter COLUMN citinerarioidioma2 drop DEFAULT;

ALTER TABLE tpaquete ADD COLUMN citinerarioidioma3 text DEFAULT '';
ALTER TABLE tpaquete alter COLUMN citinerarioidioma3 drop DEFAULT;

ALTER TABLE tpaquete ADD COLUMN citinerarioidioma4 text DEFAULT '';
ALTER TABLE tpaquete alter COLUMN citinerarioidioma4 drop DEFAULT;

ALTER TABLE tpaquete ADD COLUMN citinerarioidioma5 text DEFAULT '';
ALTER TABLE tpaquete alter COLUMN citinerarioidioma5 drop DEFAULT;
--TABLA TDESTINO--
ALTER TABLE tdestino ADD COLUMN cimagen varchar(100) DEFAULT 'img/destinos/destinoxdefecto.png'
ALTER TABLE tdestino alter COLUMN cimagen drop DEFAULT
--TABLA TPAQUETE--
ALTER TABLE tpaquete DROP COLUMN imgPaquete

ALTER TABLE tpaquete ADD COLUMN nDiaCaminoInka int DEFAULT 0
ALTER TABLE tpaquete alter COLUMN nDiaCaminoInka drop DEFAULT

ALTER TABLE tpaquete ADD COLUMN cFoto1 varchar(100) DEFAULT 'img/tours/tourxdefecto.png'
ALTER TABLE tpaquete alter COLUMN cFoto1 drop DEFAULT

ALTER TABLE tpaquete ADD COLUMN cFoto2 varchar(100) DEFAULT 'img/tours/tourxdefecto.png'
ALTER TABLE tpaquete alter COLUMN cFoto2 drop DEFAULT

ALTER TABLE tpaquete ADD COLUMN cFoto3 varchar(100) DEFAULT 'img/tours/tourxdefecto.png'
ALTER TABLE tpaquete alter COLUMN cFoto3 drop DEFAULT

ALTER TABLE tpaquete ADD COLUMN cFoto4 varchar(100) DEFAULT 'img/tours/tourxdefecto.png'
ALTER TABLE tpaquete alter COLUMN cFoto4 drop DEFAULT

ALTER TABLE tpaquete ADD COLUMN cFoto5 varchar(100) DEFAULT 'img/tours/tourxdefecto.png'
ALTER TABLE tpaquete alter COLUMN cFoto5 drop DEFAULT
--TABLA HOTEL--
ALTER TABLE thotel DROP COLUMN nDestinoCod;

ALTER TABLE thotel  ADD COLUMN nPrecioCamaAdicional decimal(10,2) DEFAULT 0
ALTER TABLE thotel alter COLUMN nPrecioCamaAdicional drop DEFAULT

ALTER TABLE thotel  ADD COLUMN nDestinoCod int DEFAULT 1
ALTER TABLE thotel alter COLUMN nDestinoCod drop DEFAULT
ALTER TABLE thotel ADD FOREIGN KEY(nDestinoCod) REFERENCES tdestino(nDestinoCod);

ALTER TABLE thotel  ADD COLUMN cFoto1 varchar(100) DEFAULT 'img/hoteles/hotelxdefecto.jpg'
ALTER TABLE thotel alter COLUMN cFoto1 drop DEFAULT

ALTER TABLE thotel  ADD COLUMN cFoto2 varchar(100) DEFAULT 'img/hoteles/hotelxdefecto.jpg'
ALTER TABLE thotel alter COLUMN cFoto2 drop DEFAULT

ALTER TABLE thotel  ADD COLUMN cFoto3 varchar(100) DEFAULT 'img/hoteles/hotelxdefecto.jpg'
ALTER TABLE thotel alter COLUMN cFoto3 drop DEFAULT

ALTER TABLE thotel  ADD COLUMN cFoto4 varchar(100) DEFAULT 'img/hoteles/hotelxdefecto.jpg'
ALTER TABLE thotel alter COLUMN cFoto4 drop DEFAULT

ALTER TABLE thotel  ADD COLUMN cFoto5 varchar(100) DEFAULT 'img/hoteles/hotelxdefecto.jpg'
ALTER TABLE thotel alter COLUMN cFoto5 drop DEFAULT

ALTER TABLE thotel  ADD COLUMN cImagenUbicacion varchar(100) DEFAULT ''
ALTER TABLE thotel alter COLUMN cImagenUbicacion drop DEFAULT
--TABLE INTERFAZ--
ALTER TABLE tinterfaz DROP COLUMN bHotelesConCamaAdicional

ALTER TABLE tinterfaz ADD COLUMN bLLenarDatosUnPax boolean DEFAULT false
ALTER TABLE tinterfaz alter COLUMN bLLenarDatosUnPax drop DEFAULT

ALTER TABLE tinterfaz ADD COLUMN bHotelesConCamaAdicional boolean DEFAULT false
ALTER TABLE tinterfaz alter COLUMN bHotelesConCamaAdicional drop DEFAULT
--TABLA TPAQUETEDESTINO--
ALTER TABLE tpaquetedestino ADD COLUMN nNoches int DEFAULT 1
ALTER TABLE tpaquetedestino alter COLUMN nNoches drop DEFAULT

ALTER TABLE tpaquetedestino ADD COLUMN nOrdenItinerario int DEFAULT 1
ALTER TABLE tpaquetedestino alter COLUMN nOrdenItinerario drop DEFAULT

ALTER TABLE tpaquetedestino ADD COLUMN bConCaminoInka boolean DEFAULT false
ALTER TABLE tpaquetedestino alter COLUMN bConCaminoInka drop DEFAULT
--TABLA IMPUESTOS--
alter table timpuesto add column impuestoMasterCard varchar(5) default 0;
alter table timpuesto alter column impuestoMasterCard drop default;

alter table timpuesto add column impuestoDinnersClub varchar(5) default 0;
alter table timpuesto alter column impuestoDinnersClub drop default;

alter table timpuesto add column porcentajeCobro varchar(5) default 0;
alter table timpuesto alter column porcentajeCobro drop default;

ALTER TABLE timpuesto ADD COLUMN pagoMinimo varchar(5) DEFAULT '0'
ALTER TABLE timpuesto alter COLUMN pagoMinimo drop DEFAULT

ALTER TABLE timpuesto ADD COLUMN modoPorcentual boolean DEFAULT true
ALTER TABLE timpuesto alter COLUMN modoPorcentual drop DEFAULT

--TABLA TDESTINO--
ALTER TABLE tdestino ADD COLUMN nCodPostal int DEFAULT 84
ALTER TABLE tdestino alter COLUMN nCodPostal drop DEFAULT
--TABLA TRESERVAPAQUETESERVICIO--
ALTER TABLE TReservaPaqueteServicio ADD COLUMN nSubServicioCod int DEFAULT 0
ALTER TABLE TReservaPaqueteServicio alter COLUMN nSubServicioCod drop DEFAULT
--TABLA TSERVICIO--
ALTER TABLE tservicio ADD COLUMN cLink text DEFAULT ''
ALTER TABLE tservicio alter COLUMN cLink drop DEFAULT
--TABLA TUSUARIO--
ALTER TABLE tusuario ADD COLUMN dFechaInicio date default now()
ALTER TABLE tusuario alter COLUMN dFechaInicio drop DEFAULT

ALTER TABLE tusuario ADD COLUMN cCorreo varchar(100) default 'desarrollo@eddyonsoft.com'
ALTER TABLE tusuario alter COLUMN cCorreo drop DEFAULT

ALTER TABLE tusuario ADD COLUMN bEstado boolean default true
ALTER TABLE tusuario alter COLUMN bEstado drop DEFAULT
--MODIFICAR TABLA TRESERVAPAQUETESERVICIO, TRESERVAPAQUETEACTIVIDAD, TRESERVAPAQUETECATEGORIAHOTEL--
ALTER TABLE treservapaqueteservicio DROP CONSTRAINT treservapaqueteservicio_creservacod_fkey;
ALTER TABLE treservapaqueteactividad DROP CONSTRAINT treservapaqueteactividad_creservacod_fkey;
ALTER TABLE treservapaquetecategoriahotel DROP CONSTRAINT treservapaquetecategoriahotel_creservacod_fkey;

alter table treservapaqueteservicio rename column creservacod to nreservapaquetecod;
alter table treservapaqueteactividad rename column creservacod to nreservapaquetecod;
alter table treservapaquetecategoriahotel rename column creservacod to nreservapaquetecod;

alter table treservapaqueteservicio alter nreservapaquetecod type bigint using nreservapaquetecod::bigint;
alter table treservapaqueteactividad alter nreservapaquetecod type bigint using nreservapaquetecod::bigint;
alter table treservapaquetecategoriahotel alter nreservapaquetecod type bigint using nreservapaquetecod::bigint;

ALTER TABLE treservapaqueteservicio ADD FOREIGN KEY(nreservapaquetecod) REFERENCES treservapaquete(nreservapaquetecod);
ALTER TABLE treservapaqueteactividad ADD FOREIGN KEY(nreservapaquetecod) REFERENCES treservapaquete(nreservapaquetecod);
ALTER TABLE treservapaquetecategoriahotel ADD FOREIGN KEY(nreservapaquetecod) REFERENCES treservapaquete(nreservapaquetecod);

--MODIFICAR TABLA TPAQUETESERVICIO Y TRESERVAPAQUETESERVICIO--
--Modificar paso a paso
alter table treservapaqueteservicio drop constraint treservapaqueteservicio_codpaqueteservicio_fkey;
alter table tpaqueteservicio drop constraint tpaqueteservicio_pkey;
--==========================
	update tpaqueteservicio set codpaqueteservicio='1' where codpaqueteservicio='PS-001';
	update tpaqueteservicio set codpaqueteservicio='2' where codpaqueteservicio='PS-002';
	update tpaqueteservicio set codpaqueteservicio='3' where codpaqueteservicio='PS-003';
	update tpaqueteservicio set codpaqueteservicio='4' where codpaqueteservicio='PS-004';
	update tpaqueteservicio set codpaqueteservicio='5' where codpaqueteservicio='PS-005';
	update tpaqueteservicio set codpaqueteservicio='6' where codpaqueteservicio='PS-006';
	update tpaqueteservicio set codpaqueteservicio='7' where codpaqueteservicio='PS-007';
	update tpaqueteservicio set codpaqueteservicio='8' where codpaqueteservicio='PS-008';
	update tpaqueteservicio set codpaqueteservicio='9' where codpaqueteservicio='PS-009';
	update tpaqueteservicio set codpaqueteservicio='10' where codpaqueteservicio='PS-010';
	update tpaqueteservicio set codpaqueteservicio='11' where codpaqueteservicio='PS-011';
	update tpaqueteservicio set codpaqueteservicio='12' where codpaqueteservicio='PS-012';
	update tpaqueteservicio set codpaqueteservicio='13' where codpaqueteservicio='PS-013';
	update tpaqueteservicio set codpaqueteservicio='14' where codpaqueteservicio='PS-014';
	update tpaqueteservicio set codpaqueteservicio='15' where codpaqueteservicio='PS-015';
	update tpaqueteservicio set codpaqueteservicio='16' where codpaqueteservicio='PS-016';
	update tpaqueteservicio set codpaqueteservicio='17' where codpaqueteservicio='PS-017';
	update tpaqueteservicio set codpaqueteservicio='18' where codpaqueteservicio='PS-018';
	update tpaqueteservicio set codpaqueteservicio='19' where codpaqueteservicio='PS-019';
	update tpaqueteservicio set codpaqueteservicio='20' where codpaqueteservicio='PS-020';
	update tpaqueteservicio set codpaqueteservicio='21' where codpaqueteservicio='PS-021';
	update tpaqueteservicio set codpaqueteservicio='22' where codpaqueteservicio='PS-022';
	update tpaqueteservicio set codpaqueteservicio='23' where codpaqueteservicio='PS-023';
	update tpaqueteservicio set codpaqueteservicio='24' where codpaqueteservicio='PS-024';
	update tpaqueteservicio set codpaqueteservicio='25' where codpaqueteservicio='PS-025';
	update tpaqueteservicio set codpaqueteservicio='26' where codpaqueteservicio='PS-026';
	update tpaqueteservicio set codpaqueteservicio='27' where codpaqueteservicio='PS-027';
	update tpaqueteservicio set codpaqueteservicio='28' where codpaqueteservicio='PS-028';
	update tpaqueteservicio set codpaqueteservicio='29' where codpaqueteservicio='PS-029';
	update tpaqueteservicio set codpaqueteservicio='30' where codpaqueteservicio='PS-030';
	update tpaqueteservicio set codpaqueteservicio='31' where codpaqueteservicio='PS-031';
	update tpaqueteservicio set codpaqueteservicio='32' where codpaqueteservicio='PS-032';
	update tpaqueteservicio set codpaqueteservicio='33' where codpaqueteservicio='PS-033';
	update tpaqueteservicio set codpaqueteservicio='34' where codpaqueteservicio='PS-034';
	update tpaqueteservicio set codpaqueteservicio='35' where codpaqueteservicio='PS-035';
	update tpaqueteservicio set codpaqueteservicio='36' where codpaqueteservicio='PS-036';
	update tpaqueteservicio set codpaqueteservicio='37' where codpaqueteservicio='PS-037';
	update tpaqueteservicio set codpaqueteservicio='38' where codpaqueteservicio='PS-038';
	update tpaqueteservicio set codpaqueteservicio='39' where codpaqueteservicio='PS-039';
	update tpaqueteservicio set codpaqueteservicio='40' where codpaqueteservicio='PS-040';
	update tpaqueteservicio set codpaqueteservicio='41' where codpaqueteservicio='PS-041';
	update tpaqueteservicio set codpaqueteservicio='42' where codpaqueteservicio='PS-042';
	update tpaqueteservicio set codpaqueteservicio='43' where codpaqueteservicio='PS-043';
	update tpaqueteservicio set codpaqueteservicio='44' where codpaqueteservicio='PS-044';
	update tpaqueteservicio set codpaqueteservicio='45' where codpaqueteservicio='PS-045';
	update tpaqueteservicio set codpaqueteservicio='46' where codpaqueteservicio='PS-046';
	update tpaqueteservicio set codpaqueteservicio='47' where codpaqueteservicio='PS-047';
	update tpaqueteservicio set codpaqueteservicio='48' where codpaqueteservicio='PS-048';
	update tpaqueteservicio set codpaqueteservicio='49' where codpaqueteservicio='PS-049';
	update tpaqueteservicio set codpaqueteservicio='50' where codpaqueteservicio='PS-050';
	update tpaqueteservicio set codpaqueteservicio='51' where codpaqueteservicio='PS-051';
	update tpaqueteservicio set codpaqueteservicio='52' where codpaqueteservicio='PS-052';
	update tpaqueteservicio set codpaqueteservicio='53' where codpaqueteservicio='PS-053';
	update tpaqueteservicio set codpaqueteservicio='54' where codpaqueteservicio='PS-054';
	update tpaqueteservicio set codpaqueteservicio='55' where codpaqueteservicio='PS-055';
	update tpaqueteservicio set codpaqueteservicio='56' where codpaqueteservicio='PS-056';
	update tpaqueteservicio set codpaqueteservicio='57' where codpaqueteservicio='PS-057';
	update tpaqueteservicio set codpaqueteservicio='58' where codpaqueteservicio='PS-058';
	update tpaqueteservicio set codpaqueteservicio='59' where codpaqueteservicio='PS-059';
	update tpaqueteservicio set codpaqueteservicio='60' where codpaqueteservicio='PS-060';
	update tpaqueteservicio set codpaqueteservicio='61' where codpaqueteservicio='PS-061';
	update tpaqueteservicio set codpaqueteservicio='62' where codpaqueteservicio='PS-062';
	update tpaqueteservicio set codpaqueteservicio='63' where codpaqueteservicio='PS-063';
	update tpaqueteservicio set codpaqueteservicio='64' where codpaqueteservicio='PS-064';
	update tpaqueteservicio set codpaqueteservicio='65' where codpaqueteservicio='PS-065';
	update tpaqueteservicio set codpaqueteservicio='66' where codpaqueteservicio='PS-066';
	update tpaqueteservicio set codpaqueteservicio='67' where codpaqueteservicio='PS-067';
	update tpaqueteservicio set codpaqueteservicio='68' where codpaqueteservicio='PS-068';
	update tpaqueteservicio set codpaqueteservicio='69' where codpaqueteservicio='PS-069';
	update tpaqueteservicio set codpaqueteservicio='70' where codpaqueteservicio='PS-070';
	update tpaqueteservicio set codpaqueteservicio='71' where codpaqueteservicio='PS-071';
	update tpaqueteservicio set codpaqueteservicio='72' where codpaqueteservicio='PS-072';
	update tpaqueteservicio set codpaqueteservicio='73' where codpaqueteservicio='PS-073';
	update tpaqueteservicio set codpaqueteservicio='74' where codpaqueteservicio='PS-074';
	update tpaqueteservicio set codpaqueteservicio='75' where codpaqueteservicio='PS-075';
	update tpaqueteservicio set codpaqueteservicio='76' where codpaqueteservicio='PS-076';
	update tpaqueteservicio set codpaqueteservicio='77' where codpaqueteservicio='PS-077';
	update tpaqueteservicio set codpaqueteservicio='78' where codpaqueteservicio='PS-078';
	update tpaqueteservicio set codpaqueteservicio='79' where codpaqueteservicio='PS-079';
	update tpaqueteservicio set codpaqueteservicio='80' where codpaqueteservicio='PS-080';
	update tpaqueteservicio set codpaqueteservicio='81' where codpaqueteservicio='PS-081';
	update tpaqueteservicio set codpaqueteservicio='82' where codpaqueteservicio='PS-082';
	update tpaqueteservicio set codpaqueteservicio='83' where codpaqueteservicio='PS-083';
	update tpaqueteservicio set codpaqueteservicio='84' where codpaqueteservicio='PS-084';
	update tpaqueteservicio set codpaqueteservicio='85' where codpaqueteservicio='PS-085';
--==========================
alter table tpaqueteservicio alter codpaqueteservicio type int using codpaqueteservicio::int;
--==========================
	update treservapaqueteservicio set codpaqueteservicio='50' where codpaqueteservicio='PS-050';
	update treservapaqueteservicio set codpaqueteservicio='51' where codpaqueteservicio='PS-051';
	update treservapaqueteservicio set codpaqueteservicio='49' where codpaqueteservicio='PS-049';
	update treservapaqueteservicio set codpaqueteservicio='49' where codpaqueteservicio='PS-049';
	update treservapaqueteservicio set codpaqueteservicio='49' where codpaqueteservicio='PS-049';
	update treservapaqueteservicio set codpaqueteservicio='50' where codpaqueteservicio='PS-050';
	update treservapaqueteservicio set codpaqueteservicio='49' where codpaqueteservicio='PS-049';
	update treservapaqueteservicio set codpaqueteservicio='29' where codpaqueteservicio='PS-029';
	update treservapaqueteservicio set codpaqueteservicio='29' where codpaqueteservicio='PS-029';
	update treservapaqueteservicio set codpaqueteservicio='30' where codpaqueteservicio='PS-030';
--==========================
alter table treservapaqueteservicio alter codpaqueteservicio type int using codpaqueteservicio::int;
--==========================
ALTER TABLE tpaqueteservicio ADD PRIMARY KEY(codpaqueteservicio);
ALTER TABLE treservapaqueteservicio ADD FOREIGN KEY(codpaqueteservicio) REFERENCES tpaqueteservicio(codpaqueteservicio);

--=====MODIFICAR CODIGO DE PAQUETE P-05
alter table tpaqueteservicio drop constraint tpaqueteservicio_cpaquetecod_fkey;
alter table tpaquetedestino drop constraint tpaquetedestino_cpaquetecod_fkey;
alter table tpaquetecategoriahotel drop constraint tpaquetecategoriahotel_cpaquetecod_fkey;
alter table tcalendario drop constraint tcalendario_cpaquetecod_fkey;
alter table tpaqueteactividad drop constraint tpaqueteactividad_cpaquetecod_fkey;
alter table tpaquete drop constraint tpaquete_pkey;

update tpaquete set cpaquetecod='P-01' where cpaquetecod='P-05';
update tpaqueteservicio set cpaquetecod='P-01' where cpaquetecod='P-05';
select * from tpaquete;
select * from tpaqueteservicio;
select * from tpaquetecategoriahotel;

ALTER TABLE tpaquete ADD PRIMARY KEY(cpaquetecod);
ALTER TABLE tpaqueteservicio ADD FOREIGN KEY(cpaquetecod) REFERENCES tpaquete(cpaquetecod);
ALTER TABLE tpaquetedestino ADD FOREIGN KEY(cpaquetecod) REFERENCES tpaquete(cpaquetecod);
ALTER TABLE tpaquetecategoriahotel ADD FOREIGN KEY(cpaquetecod) REFERENCES tpaquete(cpaquetecod);
ALTER TABLE tcalendario ADD FOREIGN KEY(cpaquetecod) REFERENCES tpaquete(cpaquetecod);
ALTER TABLE tpaqueteactividad ADD FOREIGN KEY(cpaquetecod) REFERENCES tpaquete(cpaquetecod);