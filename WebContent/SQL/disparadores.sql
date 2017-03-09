--**FUNCION QUE RETORNA TRIGGER**--
create or replace function Registrar_MesesCalendario() 
returns trigger as 
$Trigger_RegistroMeses$
declare
	dia int;
	mes int;
	diasMes int;
begin
	mes=1;
	LOOP
	    IF mes > 12 THEN
	        EXIT;  -- exit loop
	    END IF;
	    diasMes=(select nro_Dias_mes(mes));
	    dia=1;
	    loop
	    	if diasMes=0 then
	    		exit;
	    	end if;
	    	insert into tdia values(new.ncalendariocod,mes,dia,0,0);
	    	dia=dia+1;
	    	diasMes=diasMes-1;
	    end loop;
	    mes=mes+1;
	END LOOP;
	return null;
end
$Trigger_RegistroMeses$
language plpgsql;
--**FUNCION QUE RETORNA UN VALOR ENTERO QUE VIENE A SER EL NUMERO DE DIAS DEL MES**--
create or replace function nro_Dias_mes(mes int)
returns int as
$$
declare
	nroDias int;
begin
	if(mes=1)then nroDias=31; end if;
	if(mes=2)then nroDias=29; end if;
	if(mes=3)then nroDias=31; end if;
	if(mes=4)then nroDias=30; end if;
	if(mes=5)then nroDias=31; end if;
	if(mes=6)then nroDias=30; end if;
	if(mes=7)then nroDias=31; end if;
	if(mes=8)then nroDias=31; end if;
	if(mes=9)then nroDias=30; end if;
	if(mes=10)then nroDias=31; end if;
	if(mes=11)then nroDias=30; end if;
	if(mes=12)then nroDias=31; end if;
	return nroDias;
end 
$$
language plpgsql;
--**TRIGGER QUE ES EJECUTADO LUEGO DE QUE UN NUEVO CALENDARIO HAYA SIDO INSERTADO**--
create trigger Trigger_RegistroMeses
after insert on tcalendario for each row
execute procedure Registrar_MesesCalendario();

