select i.Id_Incidente "Nro_Incidente", i.Fecha "Fecha_Incidente", i.prioridad "Prioridad", err.Descripcion "Error", 
Est.Desc_Estado "Estado", Sol.Desc_Larga "Solucion", 
case when sol.Tipo_Usuario is null then  "" 	when sol.Tipo_Usuario is true then  "De_Usuario"  else 'Tecnica' end "Tipo_Solucion", 
ap.desc_aplicacion "Aplicación", mo.Desc_Modulo "Modulo", pro.Desc_Proceso "Proceso", pan.Desc_Pantalla "Pantalla", 
usu.Usuario "Usuario", ar.Desc_Area "Area" 
from   inc.incidentes i
inner join inc.errores err on i.Id_Error= err.Id_Error   
inner join inc.estados est on i.Id_Estado = est.Id_Estado
left join  inc.Soluciones sol on sol.Id_Solucion = i.Id_Solucion
inner join inc.pantallas pan on err.Id_Pantalla = pan.Id_Pantalla 
inner join inc.procesos pro on pan.Id_Proceso = pro.Id_Proceso
inner join inc.modulos mo on pro.Id_Modulo = mo.Id_Modulo
inner join inc.aplicaciones ap on mo.Id_Aplicacion = ap.Id_Aplicacion 
left join inc.resolutores res on res.Id_Resolutor = i.Id_Resolutor
inner join inc.usuarios usu on i.Id_Usuario = usu.Id_Usuario 
inner join inc.empleados em on usu.legajo = em.legajo 
inner join inc.areas ar on em.Id_Area = ar.Id_Area; 


/*drop table incidentes; 
drop table soluciones; 
drop table errores; */

delete from inc.incidentes WHERE (Id_Incidente = '1'); 
delete from inc.errores where (Id_Error = '1');
delete from inc.incidentes where  (Id_Incidente = '2');
delete from inc.errores where (Id_Error = '2');
delete from inc.soluciones where (Id_Solucion = '1');
delete from inc.incidentes where (Id_Incidente = '3');
delete from inc.errores where (Id_Error = '3');
delete from inc.incidentes where (Id_Incidente = '4');
delete from inc.errores where (Id_Error = '4');

commit; 

select * from inc.incidentes; 
select * from inc.soluciones; 
select * from inc.errores;

