package Soluciones;
//Nombre: Valeria Bijarra
//Legajo: VINF013412
//DNI: 32494363
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Conexion.Conectar;
import Incidentes.Aplicaciones;
import Incidentes.Errores;
import Incidentes.Modulos;
import Incidentes.Pantallas;
import Incidentes.Procesos;

public  class Soluciones_old {
	static Scanner teclado = new Scanner(System.in);
	protected int Nro_Solucion;
	protected String Desc_Corta; 
	protected String Desc_Larga; 
	protected int Error; 
	protected Date Fecha; 
	protected int Resolutor; 
	
	public Soluciones_old (int Nro_Solucion,
	String Desc_Corta, 
	String Desc_Larga,
	int Error, 
	Date Fecha, 
	int Resolutor 
			) {
		this.Nro_Solucion= Nro_Solucion; 
		this.Desc_Corta= Desc_Corta; 
		this.Desc_Larga= Desc_Larga; 
		this.Error= Error; 
		this.Fecha= Fecha; 
		this.Resolutor= Resolutor;
}; 


public Soluciones_old() {}

public void Obtener_Soluciones(int tipo_usu, Errores err_inc) throws SQLException { 
	Scanner teclado = new Scanner (System.in);
	if (tipo_usu==1)  /*Es de tipo usuario*/
	{
		Tipo_Usuario_old tip_usu = new Tipo_Usuario_old(); 
		ArrayList<Tipo_Usuario_old> Soluciones_U_Encontradas = tip_usu.List_Soluciones_Usuario(err_inc);   //List_Soluciones_Usuario
		if (Soluciones_U_Encontradas.size()>0) {
		
		    System.out.println("_________________________________________________________________");
	        System.out.println("Soluciones Encontradas");
	        System.out.println("_________________________________________________________________");
	        System.out.println(String.format("%-10s", "N Solución") + " | " +  String.format("%-30s", " Descripción Corta")  
	        + " | " + String.format("%-90s", "Descripción Larga") + " | " + String.format("%-30s","Permisos") 
	        + "   | " + String.format("%-60s","Path") + " | " +  String.format("%-30s","Prerequisitos"));

	       for (int i = 0; i<Soluciones_U_Encontradas.size(); i++)
	        		System.out.println( String.format("%-10s", Soluciones_U_Encontradas.get(i).Nro_Solucion)  + " | " +  
	        				 			String.format("%-30s", Soluciones_U_Encontradas.get(i).Desc_Corta) + " | " +  
	        				 			 String.format("%-90s", Soluciones_U_Encontradas.get(i).Desc_Larga) + " | " +  
	        				 			 String.format("%-30s", Soluciones_U_Encontradas.get(i).Permisos) + " | " +  
	        				 			 String.format("%-60s", Soluciones_U_Encontradas.get(i).Path) + " | " +  
	        				 			 String.format("%-30s", Soluciones_U_Encontradas.get(i).Prerequisitos)); 	
	        		
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
	    }
	else { 
		Tipo_Tecnica_old tip_tec = new Tipo_Tecnica_old(); 
		ArrayList<Tipo_Tecnica_old> Soluciones_Encontradas =  tip_tec.List_Soluciones_Tecnicas(err_inc);
		if (Soluciones_Encontradas.size()>0) {
		    System.out.println("_________________________________________________________________");
	        System.out.println("Soluciones Encontradas");
	        System.out.println("_________________________________________________________________");
	        System.out.println(String.format("%-10s", "N Solución") + " | " +  String.format("%-30s", " Descripción Corta")  
	        + " | " + String.format("%-90s", "Descripción Larga") + " | " + String.format("%-90s","Script")  
	        + "   | " + String.format("%-30s","Herramiena"));

	       for (int i = 0; i<Soluciones_Encontradas.size(); i++)
	        		System.out.println( String.format("%-10s", Soluciones_Encontradas.get(i).Nro_Solucion)  + " | " +  
	        				 			String.format("%-30s", Soluciones_Encontradas.get(i).Desc_Corta) + " | " +  
	        				 			 String.format("%-90s", Soluciones_Encontradas.get(i).Desc_Larga) + " | " +  
	        				 			 String.format("%-90s", Soluciones_Encontradas.get(i).Script) + " | " + 
	        				 			 String.format("%-30s", Soluciones_Encontradas.get(i).Herramienta) ); 	
	        		
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
	} 
}

 

public  void  Listar_Soluciones ( int tipo_usu, int id_area) throws SQLException {
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
		     System.out.println("Soluciones Encontradas");
		     System.out.println("_________________________________________________________________");

		     if (tipo_usu==2) {
		    	 System.out.println(String.format("%-10s", "Solucion") + " | " +  String.format("%-30s", " Descripción Inc")    + 
		    			 " | " + String.format("%-20s", "Aplicacion") + " | " + String.format("%-20s","Modulo")  + 
		    			 " | " + String.format("%-20s","Proceso") + " | " + String.format("%-20s","Pantalla") + 
		    			 " | " + String.format("%-20s","Desc_Area") + " | " + String.format("%-90s","Descripción Sol") +  
		    			 " | " + String.format("%-50s","Script") + " | " + String.format("%-50s","Herramienta") + 
		    			 " | " + String.format("%-50s","Path") + " | " + String.format("%-50s","Permisos") + 
		    			 " | " + String.format("%-50s","Prerequisitos")); 
		     }  else
		     {
		    	 System.out.println(String.format("%-10s", "Solucion") + " | " +  String.format("%-30s", " Descripción")    + 
		    			 " | " + String.format("%-20s", "Aplicacion") + " | " + String.format("%-20s","Modulo")  + 
		    			 " | " + String.format("%-20s","Proceso") + " | " + String.format("%-20s","Pantalla") + 
		    			 " | " + String.format("%-20s","Desc_Area") + " | " + String.format("%-90s","Descripción") + 
		    			 " | " + String.format("%-50s","Path") + " | " + String.format("%-50s","Permisos") + 
		    			 " | " + String.format("%-50s","Prerequisitos"));
		     }
			if (tipo_usu ==1) {  //Es usuario
				ps = db.getConnection().prepareStatement("select sol.id_solucion, err.Descripcion, "
						+ "   ap.desc_aplicacion , mo.Desc_Modulo , pro.Desc_Proceso , pan.Desc_Pantalla,  "
						+ "   ar.Desc_Area, Sol.Desc_Larga,  sol.desc_path, sol.permisos, sol.Prerequisitos, sol.script, sol.herramienta   "
						+ " from   inc.incidentes i "
						+ " inner join inc.errores err on i.Id_Error= err.Id_Error    "
						+ " inner join inc.estados est on i.Id_Estado = est.Id_Estado "
						+ " inner join inc.pantallas pan on err.Id_Pantalla = pan.Id_Pantalla  "
						+ " inner join inc.procesos pro on pan.Id_Proceso = pro.Id_Proceso "
						+ " inner join inc.modulos mo on pro.Id_Modulo = mo.Id_Modulo "
						+ " inner join inc.aplicaciones ap on mo.Id_Aplicacion = ap.Id_Aplicacion  "
						+ " left join  inc.Soluciones sol on sol.Id_Solucion = i.Id_Solucion "
						+ " left join  inc.resolutores res on res.Id_Resolutor = i.Id_Resolutor "
						+ " inner join inc.usuarios usu on i.Id_Usuario = usu.Id_Usuario  "
						+ " inner join inc.empleados em on usu.legajo = em.legajo  "
						+ " inner join inc.areas ar on em.Id_Area = ar.Id_Area  "  
						+ " where   ar.Id_Area = " + id_area 
						+ " and err.Id_Pantalla =" + pantalla_Sel
						+ " and sol.tipo_usuario = " + tipo_usu + ";");
					} 	else {   // es resolutor
					ps = db.getConnection().prepareStatement("select sol.id_solucion, err.Descripcion, "
							+ "   ap.desc_aplicacion , mo.Desc_Modulo , pro.Desc_Proceso , pan.Desc_Pantalla,  "
							+ "   ar.Desc_Area, Sol.Desc_Larga,  sol.desc_path, sol.permisos, sol.Prerequisitos, sol.script, sol.herramienta   "
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
					} 
		rs=ps.executeQuery(); 
		 
		while (rs.next()) { 
			if (tipo_usu==2) {
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
			}else   //si es usuario no resolutor 
				{
				System.out.println( String.format("%-10s",rs.getInt("id_solucion"))  + " | " +  
						String.format("%-30s", rs.getString("Descripcion")).substring(0, 30) + " | " +  		
					 	String.format("%-20s", rs.getString("Desc_Aplicacion")) + " | " +  
					 	String.format("%-20s", rs.getString("Desc_Modulo")) + " | " +  
					 	String.format("%-20s", rs.getString("Desc_Proceso")) + " | " + 
					 	String.format("%-20s", rs.getString("Desc_Pantalla")) + " | " + 
					 	String.format("%-20s", rs.getString("Desc_Area")) + " | " +   
					 	String.format("%-90s", rs.getString("Desc_Larga")).substring(0, 90) + " | " + 
					 	String.format("%-50s", rs.getString("desc_path")).substring(0, 50) + " | " + 
					 	String.format("%-50s", rs.getString("permisos")).substring(0, 50) + " | " +  
					 	String.format("%-50s", rs.getString("Prerequisitos")).substring(0, 50));  
				}
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
			Listar_Soluciones (tipo_usu, id_area); 
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
