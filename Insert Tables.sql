insert into aplicaciones
(Id_Aplicacion, Desc_Aplicacion)
values
(1, 'Sys_Admin'), 
(2, 'Production'), 
(3, 'Sales System'), 
(4, 'RH System'), 
(5, 'Hardware'), 
(6, 'Telefonia'); 
commit; 
select * from aplicaciones; 

insert into areas
(Id_Area, Desc_Area)
values
(1, 'Administración'), 
(2, 'Producción'), 
(3, 'Envasado'), 
(4, 'Recursos Humanos'), 
(5, 'Sistemas'), 
(6, 'Ventas'), 
(7, 'Dirección'), 
(8, 'Compras')
; 
commit; 
select * from areas; 

insert into estados	
(Id_Estado,  Desc_Estado)
values
(1, 'Abierto'), 
(2, 'Cancelado'), 
(3, 'Asignado'), 
(4, 'Cerrado')
; 
commit; 
select * from estados; 

INSERT INTO `inc`.`modulos` (`Id_Modulo`, `Desc_Modulo`, `Id_Aplicacion`) VALUES ('1', 'Administración', '1');
INSERT INTO `inc`.`modulos` (`Id_Modulo`, `Desc_Modulo`, `Id_Aplicacion`) VALUES ('2', 'Pagos', '1');
INSERT INTO `inc`.`modulos` (`Id_Modulo`, `Desc_Modulo`, `Id_Aplicacion`) VALUES ('3', 'Producción', '2');
INSERT INTO `inc`.`modulos` (`Id_Modulo`, `Desc_Modulo`, `Id_Aplicacion`) VALUES ('4', 'Empaquetado', '2');
INSERT INTO `inc`.`modulos` (`Id_Modulo`, `Desc_Modulo`, `Id_Aplicacion`) VALUES ('5', 'Pedidos', '3');
INSERT INTO `inc`.`modulos` (`Id_Modulo`, `Desc_Modulo`, `Id_Aplicacion`) VALUES ('6', 'Facturación', '3');
INSERT INTO `inc`.`modulos` (`Id_Modulo`, `Desc_Modulo`, `Id_Aplicacion`) VALUES ('7', 'Empleados', '4');
INSERT INTO `inc`.`modulos` (`Id_Modulo`, `Desc_Modulo`, `Id_Aplicacion`) VALUES ('8', 'Liquidación', '4');
INSERT INTO `inc`.`modulos` (`Id_Modulo`, `Desc_Modulo`, `Id_Aplicacion`) VALUES ('9', 'Beneficios', '4');
INSERT INTO `inc`.`modulos` (`Id_Modulo`, `Desc_Modulo`, `Id_Aplicacion`) VALUES ('10', 'Hardware', '5');
INSERT INTO `inc`.`modulos` (`Id_Modulo`, `Desc_Modulo`, `Id_Aplicacion`) VALUES ('11', 'Telefonía', '6');
commit; 
select * from modulos; 


INSERT INTO `inc`.`procesos` (`Id_Proceso`, `Desc_Proceso`, `Id_Modulo`) VALUES ('1', 'Ingreso Facturas', '1');
INSERT INTO `inc`.`procesos` (`Id_Proceso`, `Desc_Proceso`, `Id_Modulo`) VALUES ('2', 'Proveedores', '1');
INSERT INTO `inc`.`procesos` (`Id_Proceso`, `Desc_Proceso`, `Id_Modulo`) VALUES ('3', 'Ingreso Pagos', '2');
INSERT INTO `inc`.`procesos` (`Id_Proceso`, `Desc_Proceso`, `Id_Modulo`) VALUES ('4', 'Configuración', '2');
INSERT INTO `inc`.`procesos` (`Id_Proceso`, `Desc_Proceso`, `Id_Modulo`) VALUES ('5', 'Ingreso Producción', '3');
INSERT INTO `inc`.`procesos` (`Id_Proceso`, `Desc_Proceso`, `Id_Modulo`) VALUES ('6', 'Configuración', '3');
INSERT INTO `inc`.`procesos` (`Id_Proceso`, `Desc_Proceso`, `Id_Modulo`) VALUES ('7', 'Empaquetar', '4');
INSERT INTO `inc`.`procesos` (`Id_Proceso`, `Desc_Proceso`, `Id_Modulo`) VALUES ('8', 'Ingreso Pedido', '5');
INSERT INTO `inc`.`procesos` (`Id_Proceso`, `Desc_Proceso`, `Id_Modulo`) VALUES ('9', 'Definicr Precios', '5');
INSERT INTO `inc`.`procesos` (`Id_Proceso`, `Desc_Proceso`, `Id_Modulo`) VALUES ('10', 'Despachar', '5');
INSERT INTO `inc`.`procesos` (`Id_Proceso`, `Desc_Proceso`, `Id_Modulo`) VALUES ('11', 'Facturación', '6');
INSERT INTO `inc`.`procesos` (`Id_Proceso`, `Desc_Proceso`, `Id_Modulo`) VALUES ('12', 'Clientes', '6');
INSERT INTO `inc`.`procesos` (`Id_Proceso`, `Desc_Proceso`, `Id_Modulo`) VALUES ('13', 'Cobranza', '1');
INSERT INTO `inc`.`procesos` (`Id_Proceso`, `Desc_Proceso`, `Id_Modulo`) VALUES ('14', 'Alta/Baja Empleado', '7');
INSERT INTO `inc`.`procesos` (`Id_Proceso`, `Desc_Proceso`, `Id_Modulo`) VALUES ('15', 'Promoción', '7');
INSERT INTO `inc`.`procesos` (`Id_Proceso`, `Desc_Proceso`, `Id_Modulo`) VALUES ('16', 'Liquidación', '8');
INSERT INTO `inc`.`procesos` (`Id_Proceso`, `Desc_Proceso`, `Id_Modulo`) VALUES ('17', 'Asistencia', '8');
INSERT INTO `inc`.`procesos` (`Id_Proceso`, `Desc_Proceso`, `Id_Modulo`) VALUES ('18', 'Otorgar Beneficio', '9');
INSERT INTO `inc`.`procesos` (`Id_Proceso`, `Desc_Proceso`, `Id_Modulo`) VALUES ('19', 'Historial de Beneficio', '9');
INSERT INTO `inc`.`procesos` (`Id_Proceso`, `Desc_Proceso`, `Id_Modulo`) VALUES ('20', 'Configuración Beneficio', '9');
INSERT INTO `inc`.`procesos` (`Id_Proceso`, `Desc_Proceso`, `Id_Modulo`) VALUES ('21', 'Asignar Hardware', '10');
INSERT INTO `inc`.`procesos` (`Id_Proceso`, `Desc_Proceso`, `Id_Modulo`) VALUES ('22', 'Cambiar Hardware', '10');
INSERT INTO `inc`.`procesos` (`Id_Proceso`, `Desc_Proceso`, `Id_Modulo`) VALUES ('23', 'Asignar Telefono', '11');
INSERT INTO `inc`.`procesos` (`Id_Proceso`, `Desc_Proceso`, `Id_Modulo`) VALUES ('24', 'Desasignar Telefono', '11');
INSERT INTO `inc`.`procesos` (`Id_Proceso`, `Desc_Proceso`, `Id_Modulo`) VALUES ('25', 'Reparar Telefono ', '11');

commit; 
select * from inc.procesos; 

INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('1', 'Factura', '1');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('2', 'Linea Factura', '1');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('3', 'Proveedor', '2');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('4', 'Dirección de Proveedor', '2');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('5', 'Pago', '3');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('6', 'Bancos', '4');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('7', 'Termino Pago', '4');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('8', 'Tipo de Pago', '4');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('9', 'Producción L1', '5');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('10', 'Producción L2', '5');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('11', 'Producción L3', '5');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('12', 'Items', '6');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('13', 'Formulas', '6');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('14', 'Maquinas', '6');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('15', 'Empaquetado', '7');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('16', 'Pedido', '8');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('17', 'Linea Pedido', '8');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('18', 'Precios', '9');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('19', 'Lineas Precio', '9');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('20', 'Calificador de Precio', '9');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('21', 'Despacho', '10');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('22', 'Transporte', '10');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('23', 'Factura', '11');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('24', 'Linea Factura', '11');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('25', 'Impuesto Factura', '11');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('26', 'Impresor Factura', '11');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('27', 'Cliente', '12');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('28', 'Dirección Cliente', '12');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('29', 'Impuestos Cliente', '12');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('30', 'Comunicación Cliente', '12');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('31', 'Lote de Pago', '13');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('32', 'Aplicación de Pago', '13');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('33', 'Ingreso Pago', '13');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('34', 'Detalle de Cuenta', '13');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('35', 'Persona', '14');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('36', 'Empleado', '14');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('37', 'Asignación de Area', '14');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('38', 'Promociones', '15');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('39', 'Liquidación', '16');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('40', 'Lectura Reloj', '17');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('41', 'Detalle E/S', '17');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('42', 'Inasistencias', '17');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('43', 'Otorgar Beneficios', '18');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('44', 'Historial Beneficio', '19');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('45', 'Alta Beneficios', '20');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('46', 'Impresora', '22');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('47', 'Monitor', '22');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('48', 'Teclado', '22');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('49', 'Mouse', '22');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('50', 'Cpu', '22');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('51', 'Notebook', '22');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('52', 'Impresora', '21');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('53', 'Monitor', '21');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('54', 'Teclado', '21');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('55', 'Mouse', '21');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('56', 'Cpu', '21');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('57', 'Notebook', '21');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('58', 'Analogico', '23');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('59', 'Digital', '23');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('60', 'Celular', '23');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('61', 'Analogico', '24');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('62', 'Digital', '24');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('63', 'Celular', '24');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('64', 'Analogico', '25');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('65', 'Digital', '25');
INSERT INTO `inc`.`pantallas` (`Id_Pantalla`, `Desc_Pantalla`, `Id_Proceso`) VALUES ('66', 'Celular', '25');

commit; 
select * from inc.pantallas; 


INSERT INTO `inc`.`tipo_problema` (`Id_Tipo_Prob`, `Desc_Tipo_Prob`) VALUES ('1', 'Sistemas');
INSERT INTO `inc`.`tipo_problema` (`Id_Tipo_Prob`, `Desc_Tipo_Prob`) VALUES ('2', 'IT');

commit; 
select * from inc.tipo_problema; 


INSERT INTO `inc`.`usuarios` (`Id_Usuario`, `Usuario`, `Clave`, `Estado`, `mail`, `Legajo`) VALUES ('1', 'EBIONDINI', '123', 'Activo', 'ebiondini@gmail.com', '1001');
INSERT INTO `inc`.`usuarios` (`Id_Usuario`, `Usuario`, `Clave`, `Estado`, `mail`, `Legajo`) VALUES ('2', 'SGOMEZ', '123', 'Activo', 'sgomez@gmail.com', '1002');
INSERT INTO `inc`.`usuarios` (`Id_Usuario`, `Usuario`, `Clave`, `Estado`, `mail`, `Legajo`) VALUES ('3', 'JSAOMERO', '123', 'Activo', 'jsaomero@gmail.com', '1003');
INSERT INTO `inc`.`usuarios` (`Id_Usuario`, `Usuario`, `Clave`, `Estado`, `mail`, `Legajo`) VALUES ('4', 'EROMERO', '123', 'Activo', 'eromero@gmail.com', '1004');
INSERT INTO `inc`.`usuarios` (`Id_Usuario`, `Usuario`, `Clave`, `Estado`, `mail`, `Legajo`) VALUES ('5', 'JSOLER', '123', 'Activo', 'jsolr@gmail.com', '1005');
INSERT INTO `inc`.`usuarios` (`Id_Usuario`, `Usuario`, `Clave`, `Estado`, `mail`, `Legajo`) VALUES ('6', 'EDONATI', '123', 'Activo', 'edonati@gmail.com', '1006');
INSERT INTO `inc`.`usuarios` (`Id_Usuario`, `Usuario`, `Clave`, `Estado`, `mail`, `Legajo`) VALUES ('7', 'GLAZARINI', '123', 'Activo', 'glazarini@gmail.com', '1007');
INSERT INTO `inc`.`usuarios` (`Id_Usuario`, `Usuario`, `Clave`, `Estado`, `mail`, `Legajo`) VALUES ('8', 'EMURUA', '123', 'Actvo', 'emurua@gmail.com', '1008');

commit; 
select * from inc.usuarios; 

INSERT INTO `inc`.`resolutores` (`Id_Resolutor`, `Id_Usuario`, `Id_Tipo_Prob`) VALUES ('1', '7', '1');
INSERT INTO `inc`.`resolutores` (`Id_Resolutor`, `Id_Usuario`, `Id_Tipo_Prob`) VALUES ('2', '8', '2');

commit; 
select * from inc.resolutores; 


INSERT INTO inc.errores (Id_Error, Id_Pantalla, codigo, Descripcion, Pasos_Reprod, Id_Tipo_Prob)
values (1, 1, '1002', 
'Problema al registar factura. Se generó error 1002 por inconsistencia tipo de dato al registrar registro.', 
'Luego de seleccionar el proveedor OLEOS DEL SUR, al dar tab, aparece error 1002.', 1), 
(2, 22, '1000', 'Error al registrar nuevo Transporte.', 
'Luego de colocar información de razón social, chasis y choferes aparece error 1000-No Data Found.', 1), 
(3, 5, '1010', 'Error al registrar pago.', 
'Luego de seleccionar el banco CITI cta 9987, aparece error 1010, Código de Banco es NULL.', 1) ,
(4, 5, '1010', 'Error al registrar pago.', 
'Luego de seleccionar el banco CITI cta 9987, aparece error 1010, Código de Banco es NULL.', 1) 
; 
commit; 
select * from inc.errores; 


INSERT INTO inc.incidentes 
(Id_Incidente,  Id_Usuario,  Id_Resolutor,  Fecha ,  Anexo, Id_Error,  Prioridad,Id_Estado, Id_Solucion) 
VALUES  (1, 1, NULL, '2024-05-10', NULL, 1, 2, 1,  NULL), 
		(2, 2, NULL, '2024-05-10', NULL, 2, 2, 1,  NULL), 
		(3, 1, NULL, '2024-05-11', NULL, 3, 2, 1,  NULL), 
        (4, 1, NULL, '2024-05-15', NULL, 3, 2, 1,  NULL);  
commit; 
Select * from inc.incidentes; 

INSERT INTO inc.soluciones
(Id_Solucion, Desc_Corta, Desc_Larga,Id_Resolutor,Fecha,Id_Error, Tipo_Usuario, 
Desc_Path, Permisos, Prerequisitos, Script, Herramienta) 
VALUES ('1', 'Se agregó código de banco. ', 
'Se accede por la configuración de bancos y se agrega código de banco válido.', '1', '2024-05-11', '3', 1, 
'Configuración > Entidades > Bancos', 'Acceso a Modulo de Configuración', null, null, null);

commit; 
select * from inc.soluciones; 


update inc.incidentes 
set Id_Solucion = 1, Id_Estado = 4 /*Cerrado*/, Id_Resolutor = 1 /*GLAZARINI*/
where Id_Incidente = 3; 
commit;
