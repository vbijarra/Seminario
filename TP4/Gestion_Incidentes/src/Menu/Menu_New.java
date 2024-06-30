package Menu;
//Nombre: Valeria Bijarra
//Legajo: VINF013412
//DNI: 32494363

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import Conexion.Conectar;
import Conexion.Controlador_Incidente;
import Seguridad.Usuarios;
import Soluciones.Soluciones;
import Soluciones.Tipo_Tecnica;
import Soluciones.Tipo_Usuario;

	public class Menu_New { 		
		static Scanner teclado = new Scanner(System.in);
		public static void main(String args[]) throws SQLException {
		{
			int Id_usu = 0; 
			int Tipo_usu=0;
			int Id_area=0; 			
			try {
		    	Usuarios usu  = new Usuarios(); 
				int[][] datos_usuario = new int[1][3]; 
				datos_usuario= usu.Solicitar_Datos_loguin(); 				
				Id_usu= datos_usuario[0][0];				
				Tipo_usu = datos_usuario[0][1]; 
				Id_area = datos_usuario[0][2]; 				
				Seleccionar_Menu(Id_usu, Tipo_usu, Id_area); 
				
			} catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
		}}}

		public static int Menu() {
			//se imprimen opciones de menú
			System.out.println("_________________________________________________________________");
			System.out.println("GESTIÓN DE INCIDENTES - OLEOSIN 			");
			System.out.println("_________________________________________________________________");
			System.out.println("MENU PRINCIPAL 				");
			System.out.println("_________________________________________________________________");
			System.out.println("[1] Crear Incidente");
			System.out.println("[2] Modificar Incidente-sin desarrollar");
			System.out.println("[3] Cancelar Incidente");
			System.out.println("[4] Resolver Incidente-sin desarrollar");
			System.out.println("[5] Listar Incidentes");
			System.out.println("[6] Listar Soluciones");
			System.out.println("[0] Salir");
			System.out.println("_________________________________________________________________");
			System.out.println("Seleccione una opción.");
			return teclado.nextInt();
			
			}
		
		public static void Seleccionar_Menu(int id_usu, int tipo_usu, int id_area) throws SQLException {
			boolean salir = false; 
			
			    while  (!salir) 
			    {
			    	try {
			    		int opcion = Menu();
					    	switch (opcion) {
							case 1:					
								System.out.println("_________________________________________________________________");
								System.out.println("INGRESAR INCIDENTE"); 
								System.out.println("_________________________________________________________________");
								try { /*se realilza conexión a la base de datos y se nstancia a Controlador_Incidente para insertar un nuevo incidente*/
										Conectar conexion= new Conectar(); 
										Controlador_Incidente controlador = new Controlador_Incidente(conexion);
										controlador.Insertar(id_usu, tipo_usu);
										conexion.desconectar();										
								}  catch (SQLException e) {
										System.err.println("Error de conexión a la base de datos: " + e.getMessage());
										}
								break; 
							case 2:
								System.out.println("_________________________________________________________________");
								System.out.println("MODIFICAR INCIDENTE-SIN DESARROLLAR"); 
								System.out.println("_________________________________________________________________");
								break; 
							case 3:
								System.out.println("_________________________________________________________________");
								System.out.println("CANCELAR INCIDENTE");
								System.out.println("_________________________________________________________________");
								
								try {/*se realilza conexión a la base de datos y se instancia a Controlador_Incidente para cancelar un incidente*/
										Conectar conexion= new Conectar(); 
										Controlador_Incidente controlador = new Controlador_Incidente(conexion);
										controlador.Cancelar_Incidente(id_usu, id_area, tipo_usu);
										conexion.desconectar();
					    		}  catch (SQLException e) {
					    			System.err.println("Error de conexión a la base de datos: " + e.getMessage());
								}
								break;
							case 4: 
								System.out.println("_________________________________________________________________");
								System.out.println("RESOLVER INCIDENTE-SIN DESARROLLAR");
								System.out.println("_________________________________________________________________");
								break;								
							case 5: 
								System.out.println("_________________________________________________________________");
								System.out.println("LISTAR INCIDENTES");
								System.out.println("_________________________________________________________________"); 
							
								try {/*se realilza conexión a la base de datos y se instancia a Controlador_Incidente para listar incidentes*/
										Conectar conexion= new Conectar(); 
										Controlador_Incidente controlador = new Controlador_Incidente(conexion);
										controlador.Listar_Incidentes(id_usu, id_area, tipo_usu);   /*PASAR A WORD VB*/
										conexion.desconectar();
								    }catch (SQLException e) {
									System.err.println("Error de conexión a la base de datos: " + e.getMessage());
									}
								break;
							case 6:
								System.out.println("_________________________________________________________________");
								System.out.println("LISTAR SOLUCIONES");
								System.out.println("_________________________________________________________________"); 
								
								if (tipo_usu == 1) {
									Soluciones solu= new Tipo_Usuario();  
									solu.List_Soluciones(id_usu, id_area);
								} else {
									Soluciones solu= new Tipo_Tecnica();
									solu.List_Soluciones(id_usu, id_area);									
								}  
								
								break;
							case 7:
								System.out.println("_________________________________________________________________");
								System.out.println("CONFIGURACIONES");
								System.out.println("_________________________________________________________________");
								break;
							case 0:
								System.out.println("Saliendo de la aplicación.");
								salir=true; 
								break;
							default:
								System.out.println("Opción no encontrada.");
								}
			    	}catch (InputMismatchException ex) {
			            System.out.println("Debe ingresar obligatoriamente un número entero."); 
			            teclado.next(); 
			            salir=false; 
					}
			   }
			teclado.next(); 
		}
}
		
		
		
		
		 
