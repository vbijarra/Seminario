package Conexion;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import Incidentes.Errores;
import Incidentes.Incidentes; 

public class Controlador_Incidente {
	private Modelo_Incidente modelo; 
	private Vista_Incidente vista; 
	
	public Controlador_Incidente(Conectar conexion) {
		this.modelo = new Modelo_Incidente(conexion); 
		this.vista = new Vista_Incidente(); 
	}
	
	public void Insertar(int usu, int tipo_usu) throws SQLException {
		/*solicita información al usuario */
		int tipo= vista.Solicita_Tipo_Problema(); 
		int sec_inc = modelo.Incr_Id_Incidentes(); 
		int sec_err = modelo.Incr_Id_Errores(); 
	 	LocalDate fecha = LocalDate.now();  //obtiene fecha del sistema;
	 	java.sql.Date fecha_actual = java.sql.Date.valueOf(fecha); 
	 	int prioridad= vista.Solicita_Prioridad(); 
	 	int estado = 1; /*Abierto*/		
		Incidentes nuevo_incidente = new Incidentes(sec_inc,fecha_actual, usu, tipo, prioridad , estado, 0); 
				
		String descripcion=vista.Solicita_Descripcion(); 
		String pasos = vista.Solicita_Pasos(); 
		String codigo = vista.Solicita_Codigo(); 		
		int aplicacion_sel= vista.seleccionar_Apliacion(modelo); 		 
		int modulo_Sel = vista.seleccionar_Modulo(modelo, aplicacion_sel); 
		int proceso_Sel = vista.seleccionar_Proceso(modelo, modulo_Sel); 
		int pantalla_Sel = vista.seleccionar_Pantalla(modelo, proceso_Sel); 
		
		int confirma= vista.Seleccionar_Confirma();  
		if (confirma == 1) {   			
			Errores Nuevo_Error = new Errores(nuevo_incidente.getNro_Incidente(), nuevo_incidente.getFecha(), 
					nuevo_incidente.getUsuario(), nuevo_incidente.getTipo_Problema(),  nuevo_incidente.getPrioridad(), 
					nuevo_incidente.getEstado(), nuevo_incidente.getSolucion() ,  
					codigo, pantalla_Sel,  aplicacion_sel,  modulo_Sel,  proceso_Sel,  descripcion,  pasos, sec_err);
			
			modelo.Insertar_Incidente(Nuevo_Error);  /*realiza insert en la bd*/
			Nuevo_Error.Buscar_Soluciones(Nuevo_Error, tipo_usu);		/*busca soluciones coincidentes según el tipo de usuario*/	
			} else {
					int conf=vista.Seleccionar_Confirma_Reg_Incidente();   
					if (conf == 1) {
						 Insertar(usu, tipo_usu); 
					} else {return;
							}
			}			
	}
	
	public void Cancelar_Incidente(int id_usu, int id_area, int tipo_usu) throws SQLException {	
		int nro = 0; 
		int inc_ok=0; 	
		
		ArrayList<Integer> List_Inc_Abi_Area =  modelo.Buscar_Inc_Usuario_Abiertos(id_usu, id_area, tipo_usu); /*busca incidentes abiertos del usuario y almacena id_incidentte en el arraylist*/
		try {
			if (List_Inc_Abi_Area.size()>0) {
				nro=vista.Seleccionar_Nro_Incidente();   /*solicita al usuario núero de incidente para cancelar*/
				for (int x=0;  (x < List_Inc_Abi_Area.size() && inc_ok==0); x++) {  
					
					if (List_Inc_Abi_Area.get(x).equals(nro)) { /*recorre el array y lo compara con el npumero ingresado por el usuario*/
						modelo.Cancela_Incidente(nro); /*modifica estado del incidente en la bd*/
						System.out.println("Incidente CANCELADO. NRO : " +  nro + ".");
						List_Inc_Abi_Area.remove(x);   //eliminar del array el nro de incidente
						inc_ok = 1;   
						int confirma= vista.Seleccionar_Cancela_Otro_Incidente();   
						
						if (confirma != 1) {
							return; 
						} else 
						{
							Cancelar_Incidente(id_usu, id_area, tipo_usu); 
						}
					}  
				}
				if (inc_ok == 0) {
					int confirma=vista.Seleccionar_mje_nro_Inc_Incorrecto_Cancel(); 
					if (confirma != 1) { 
						return; 
					} else 
					{
						Cancelar_Incidente(id_usu, id_area,  tipo_usu);  //vuelve a llamar al método para cancelar otro incidente
					}
				}
			}
			else {
					System.out.println("No existen incidentes para Cancelar."); 
			       	return; 
			}
	} catch (Exception ext) {System.out.println("Error al Crear_Incidente:" + ext.getMessage());}
	}	
	
	public void Listar_Incidentes(int id_usu, int id_area, int id_tipo_usu) throws SQLException {  
		try {	  
			vista.Listar_Inc(modelo.Listar_Incidentes(id_usu, id_area, id_tipo_usu)); /*busca incidentes y los lista */
			int confirma=vista.Seleccionar_retorna_Menu(); 
			if (confirma != 1) {
				return; 
			}else {Listar_Incidentes (id_usu, id_area, id_tipo_usu); }			
		} catch (Exception ext) {System.out.println("Error Buscar_Inc_Usuario_Abiertos:" + ext.getMessage());}
	}
}