/********************************************************/
/**FUNCION QUE RETORNA EL CODIGO DE RESERVA A REGISTRAR**/
/********************************************************/
CREATE OR REPLACE FUNCTION Pricing_sp_RetornarCodigoReserva
/****************************/
/**GENERAR ORDEN Y REGISTRO**/
/****************************/
CREATE OR REPLACE FUNCTION Pricing_sp_GenerarOrdenReg()
returns table(orden varchar(15),registro  varchar(20),codGeneraOrdenReg bigint)as
$$
begin
	orden=(select concat('ORD',right(concat('0000000000',count(r.cOrden)+1),10)) from TGeneraOrdenReg r where left(r.cOrden,3)='ORD');
	registro=(select concat('REG',right(concat('000000000000000',count(r.cRegistro)+1),15)) from TGeneraOrdenReg r where left(r.cRegistro,3)='REG');
	codGeneraOrdenReg=(select max(nGeneraOrdenRegCod) from TGeneraOrdenReg);
	if(codGeneraOrdenReg is null)then
		codGeneraOrdenReg=1;
	else
		codGeneraOrdenReg=codGeneraOrdenReg+1;
	end if;
	insert into TGeneraOrdenReg values(codGeneraOrdenReg,orden,registro);
	return Query select orden,registro,codGeneraOrdenReg;
end 
$$
language plpgsql;