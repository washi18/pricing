--Eliminar imagen galeria hotel---
create or replace function Pricing_sp_EliminarImagenGaleriaHotel
(
	codGaleriaHotel bigint
)
RETURNS TABLE (resultado varchar(20),
		mensaje varchar(200),
		galeriaHotelCod bigint) as
$$
begin
	galeriaHotelCod=$1;
	DELETE FROM tgaleriahotel WHERE ngaleriahotelcod=$1;
	resultado='correcto';
	mensaje='Datos Eliminados Correctamente';
	return Query select resultado,mensaje,galeriaHotelCod;
end
$$
language plpgsql;
--Eliminar imagen galeria paquete---
create or replace function Pricing_sp_EliminarImagenGaleriaPaquete
(
	codGaleriaPaquete bigint
)
RETURNS TABLE (resultado varchar(20),
		mensaje varchar(200),
		galeriaPaqueteCod bigint) as
$$
begin
	galeriaPaqueteCod=$1;
	DELETE FROM tgaleriapaquete WHERE ngaleriapaquetecod=$1;
	resultado='correcto';
	mensaje='Datos Eliminados Correctamente';
	return Query select resultado,mensaje,galeriaPaqueteCod;
end
$$
language plpgsql;
--Eliminar PAQUETE SERVICIO
create or replace function Pricing_sp_EliminarPaqueteServicio
(
	codps int
)
RETURNS TABLE (resultado varchar(20),
		mensaje varchar(200),
		codPaqueteServ int) as
$$
begin
	codPaqueteServ=$1;
	DELETE FROM tpaqueteservicio WHERE codpaqueteservicio=$1;
	resultado='correcto';
	mensaje='Datos Eliminados Correctamente';
	return Query select resultado,mensaje,codPaqueteServ;
end
$$
language plpgsql;
--eliminar usuario
create or replace function Pricing_sp_EliminarUsuario
(
	usuarioCod varchar(150)
)
RETURNS TABLE (resultado varchar(20),
		mensaje varchar(200),
		codUsuario varchar(150)) as
$$
begin
	codUsuario=$1;
	DELETE FROM tusuario WHERE cusuariocod=$1;
	resultado='correcto';
	mensaje='Datos Eliminados Correctamente';
	return Query select resultado,mensaje,codUsuario;
end
$$
language plpgsql;
--Eliminar PAQUETE DESTINO
create or replace function Pricing_sp_EliminarPaqueteDestino
(
	codpd int
)
RETURNS TABLE (resultado varchar(20),
		mensaje varchar(200),
		codPaqueteDest int) as
$$
begin
	codPaqueteDest=$1;
	DELETE FROM tpaquetedestino WHERE codpaquetedestino=$1;
	resultado='correcto';
	mensaje='Datos Eliminados Correctamente';
	return Query select resultado,mensaje,codPaqueteDest;
end
$$
language plpgsql;
--Eliminar PAQUETE ACTIVIDAD
create or replace function Pricing_sp_EliminarPaqueteActividad
(
	codpa int
)
returns table (resultado varchar(20),mensaje varchar(200),codPaqueteAct int)as
$$
begin
	codPaqueteAct=$1;
	delete from tpaqueteactividad where npaqueteactividad=$1;
	resultado='correcto';
	mensaje='Datos Eliminados Correctamente';
	return Query select resultado,mensaje,codPaqueteAct;
end 
$$
language plpgsql;