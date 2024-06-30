package Soluciones;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//Nombre: Valeria Bijarra
//Legajo: VINF013412
//DNI: 32494363
import java.util.Date;

import Conexion.Conectar;
import Incidentes.Aplicaciones;
import Incidentes.Errores;
import Incidentes.Modulos;
import Incidentes.Pantallas;
import Incidentes.Procesos;

public class Tipo_Tecnica extends Soluciones{ 
		String Script; 
		String Herramienta;  
		
		public Tipo_Tecnica(int Nro_Solucion, String Desc_Corta, String Desc_Larga, int Error, Date Fecha, int Resolutor, 
				String Script, String Herramienta) {
			
			super(Nro_Solucion, Desc_Corta, Desc_Larga, Error, Fecha, Resolutor); 
			this.Script=Script; 
			this.Herramienta=Herramienta;  
		} 
		
		public Tipo_Tecnica() {}
		
		
		public  ArrayList<Tipo_Tecnica> List_Sol (Errores err) throws SQLException {
			java.sql.PreparedStatement ps= null; 
			ResultSet rs = null; 
			Conectar db= new Conectar(); 
			ArrayList<Tipo_Tecnica> soluciones = new ArrayList<Tipo_Tecnica>(); 
			try {	 
				ps = db.getConnection().prepareStatement("select Id_Solucion,  Desc_Corta, Desc_Larga, Id_Resolutor, Fecha, "
						+ " s.Id_Error as Id_Error,  script, herramienta"
						+ " from soluciones s, errores e "
						+ " where s.Id_Error = e.Id_Error and s.tipo_usuario = 2  and e.Id_Pantalla = " + err.getPantalla() + ";");  
				rs=ps.executeQuery(); 				
				while (rs.next()) {
					int Id_Solucion = rs.getInt("Id_Solucion"); 
				    String Desc_Corta = rs.getString("Desc_Corta"); 
				    String Desc_Larga = rs.getString("Desc_Larga");
				    int Id_Resolutor= rs.getInt("Id_Resolutor"); 
				    Date Fecha=rs.getDate("Fecha"); 
				    int Id_Error=rs.getInt("Id_Error"); 
				    String Script=rs.getString("Script"); 
				    String Herramienta=rs.getString("herramienta");  	
					Tipo_Tecnica sol = new Tipo_Tecnica(Id_Solucion, Desc_Corta, Desc_Larga, Id_Error,  Fecha,  Id_Resolutor, 
														Script, Herramienta); 
					soluciones.add(sol);  
				}	
			} catch (Exception ext) {System.out.println("Error List_Soluciones_Usuario:" + ext.getMessage());}
		 finally {
			try {
				ps.close();
				rs.close();
				db.desconectar(); }catch (Exception ext) {System.out.println("Error :" + ext.getMessage());}
			}
			return soluciones;
		}



		@Override
		public void List_Soluciones(Errores err_inc) {
			try {	
			ArrayList<Tipo_Tecnica> Soluciones_T_Encontradas =  List_Sol(err_inc);  
			if (Soluciones_T_Encontradas.size()>0) {
			    System.out.println("_________________________________________________________________");
		        System.out.println("Soluciones Tecnicas Encontradas");
		        System.out.println("_________________________________________________________________");
		        System.out.println(String.format("%-10s", "N Solución") + " | " +  String.format("%-30s", " Descripción Corta")  
		        + " | " + String.format("%-90s", "Descripción Larga") + " | " + String.format("%-90s","Script")  
		        + "   | " + String.format("%-30s","Herramiena"));

		       for (int i = 0; i<Soluciones_T_Encontradas.size(); i++)
		        		System.out.println( String.format("%-10s", Soluciones_T_Encontradas.get(i).Nro_Solucion)  + " | " +  
		        				 			String.format("%-30s", Soluciones_T_Encontradas.get(i).Desc_Corta) + " | " +  
		        				 			 String.format("%-90s", Soluciones_T_Encontradas.get(i).Desc_Larga) + " | " +  
		        				 			 String.format("%-90s", Soluciones_T_Encontradas.get(i).Script) + " | " + 
		        				 			 String.format("%-30s", Soluciones_T_Encontradas.get(i).Herramienta) ); 	
		        		
		       System.out.println("_________________________________________________________________");
		        System.out.println("[1] Regresar al Menú.  [2] Salir." );
				int confirma=teclado.nextInt();
				if (confirma != 1) {
					return; 
				}
			} else {System.out.println("No Se encontraron Soluciones.");
			System.out.println("[1] Regresar al menú.  [2] Salir." );
			int confirma=teclado.nextInt();
			if (confirma != 1) {
				return; 
			}
			}  
		} catch (Exception ext) {System.out.println("Error List_Soluciones_Usuario:" + ext.getMessage());}
		}

		@Override
		public void List_Soluciones(int tipo_usu, int id_area) throws SQLException {
			java.sql.PreparedStatement ps= null; 
			ResultSet rs = null; 
			Conectar db= new Conectar(); 
			 
			 try {	  
					Aplicaciones apl = new Aplicaciones();
					int aplicacion_sel= apl.seleccionar_Apliacion(apl); 				
					Modulos mod= new Modulos(); 
					int modulo_Sel = mod.seleccionar_Modulo(mod, aplicacion_sel); 					
					Procesos  prod= new Procesos(); 
					int proceso_Sel = prod.seleccionar_Proceso(prod, modulo_Sel); 			
					Pantallas  pant= new Pantallas(); 
					int pantalla_Sel = pant.seleccionar_Pantalla(pant, proceso_Sel); 			
					
					 System.out.println("_________________________________________________________________");
				     System.out.println("Soluciones Tecnicas Encontradas");
				     System.out.println("_________________________________________________________________");

				     
				     System.out.println(String.format("%-10s", "Solucion") + " | " +  String.format("%-30s", " Descripción Inc")    + 
			    			 " | " + String.format("%-20s", "Aplicacion") + " | " + String.format("%-20s","Modulo")  + 
			    			 " | " + String.format("%-20s","Proceso") + " | " + String.format("%-20s","Pantalla") + 
			    			 " | " + String.format("%-20s","Desc_Area") + " | " + String.format("%-50s","Descripción Sol") +  
			    			 " | " + String.format("%-50s","Script") + " | " + String.format("%-50s","Herramienta") + 
			    			 " | " + String.format("%-50s","Path") + " | " + String.format("%-50s","Permisos") + 
			    			 " | " + String.format("%-50s","Prerequisitos")); 
				        // es resolutor
							ps = db.getConnection().prepareStatement("select sol.id_solucion, err.Descripcion, "
									+ "   ap.desc_aplicacion , mo.Desc_Modulo , pro.Desc_Proceso , pan.Desc_Pantalla,  "
									+ "   ar.Desc_Area, Sol.Desc_Larga,  sol.desc_path, sol.permisos, sol.Prerequisitos, sol.script, "
									+ "   sol.herramienta   "
									+ " from   inc.incidentes i "
									+ " inner join inc.errores err on i.Id_Error= err.Id_Error    "
									+ " inner join inc.estados est on i.Id_Estado = est.Id_Estado "
									+ " inner join inc.pantallas pan on err.Id_Pantalla = pan.Id_Pantalla  "
									+ " inner join inc.procesos pro on pan.Id_Proceso = pro.Id_Proceso "
									+ " inner join inc.modulos mo on pro.Id_Modulo = mo.Id_Modulo "
									+ " inner join inc.aplicaciones ap on mo.Id_Aplicacion = ap.Id_Aplicacion  "
									+ " inner join  inc.Soluciones sol on sol.Id_Solucion = i.Id_Solucion "
									+ " inner join  inc.resolutores res on res.Id_Resolutor = i.Id_Resolutor "
									+ " inner join inc.usuarios usu on i.Id_Usuario = usu.Id_Usuario  "
									+ " inner join inc.empleados em on usu.legajo = em.legajo  "
									+ " inner join inc.areas ar on em.Id_Area = ar.Id_Area  "  
									+ " where     err.Id_Pantalla =" + pantalla_Sel );  	
							 
				rs=ps.executeQuery(); 
				 
				while (rs.next()) { 
					 
						System.out.println( String.format("%-10s",rs.getInt("id_solucion"))  + " | " +  
								String.format("%-30s", rs.getString("Descripcion")).substring(0, 30) + " | " +  		
							 	String.format("%-20s", rs.getString("Desc_Aplicacion")) + " | " +  
							 	String.format("%-20s", rs.getString("Desc_Modulo")) + " | " +  
							 	String.format("%-20s", rs.getString("Desc_Proceso")) + " | " + 
							 	String.format("%-20s", rs.getString("Desc_Pantalla")) + " | " + 
							 	String.format("%-20s", rs.getString("Desc_Area")) + " | " +   
							 	String.format("%-50s", rs.getString("Desc_Larga")).substring(0, 50) + " | " + 
							 	String.format("%-50s", rs.getString("script")).substring(0, 50) + " | " + 
							 	String.format("%-30s", rs.getString("herramienta")).substring(0, 30) + " | " + 
								String.format("%-30s", rs.getString("desc_path")).substring(0, 30) + " | " + 
							 	String.format("%-30s", rs.getString("permisos")).substring(0, 30) + " | " +  
							 	String.format("%-30s", rs.getString("Prerequisitos")).substring(0, 30)); 
				} 
				
				System.out.println(""); 
				System.out.println("_________________________________________________________________");
			    System.out.println("[1] Consultar Soluciones nuevamente  [2] Salir." );
				int confirma=teclado.nextInt();
				if (confirma != 1) { 
					db.desconectar();
					return; 
				} else 
				{
					List_Soluciones (tipo_usu, id_area); 
				}
				
				
			} catch (Exception ext) {System.out.println("Error en Listar_Soluciones:" + ext.getMessage());}
		finally {
			try {
				ps.close();
				rs.close();
				db.desconectar();
				}catch (Exception ext) {System.out.println("Error :" + ext.getMessage());}
			} 
			
		}
		}

