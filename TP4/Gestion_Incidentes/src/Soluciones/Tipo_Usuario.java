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
import Incidentes.Incidentes;
import Incidentes.Modulos;
import Incidentes.Pantallas;
import Incidentes.Procesos;

public class Tipo_Usuario extends Soluciones{
	String Path; 
	String Permisos; 
	String Prerequisitos; 
	
	public Tipo_Usuario(int Nro_Solucion, String Desc_Corta, String Desc_Larga, int Error, Date Fecha, int Resolutor, 
			String path, String Permisos, String Prerequisitos) {
		
		super(Nro_Solucion, Desc_Corta, Desc_Larga, Error, Fecha, Resolutor); 
		this.Path=path; 
		this.Permisos=Permisos; 
		this.Prerequisitos=Prerequisitos; 
	}
	
	
	public Tipo_Usuario() {}
	
	public  ArrayList<Tipo_Usuario> List_Sol (Errores err) throws SQLException {
		java.sql.PreparedStatement ps= null; 
		ResultSet rs = null; 
		Conectar db= new Conectar(); 
		ArrayList<Tipo_Usuario> soluciones = new ArrayList<Tipo_Usuario>(); 
		try {	
			ps = db.getConnection().prepareStatement("select Id_Solucion,  Desc_Corta, Desc_Larga, Id_Resolutor, Fecha, "
					+ " s.Id_Error as Id_Error, Desc_Path,	Permisos, Prerequisitos"
					+ " from soluciones s, errores e "
					+ " where s.Id_Error = e.Id_Error and s.tipo_usuario  = 1  and e.Id_Pantalla = " + err.getPantalla() + ";");  
			rs=ps.executeQuery(); 
			
			
			while (rs.next()) {
				int Id_Solucion = rs.getInt("Id_Solucion"); 
			    String Desc_Corta = rs.getString("Desc_Corta"); 
			    String Desc_Larga = rs.getString("Desc_Larga");
			    int Id_Resolutor= rs.getInt("Id_Resolutor"); 
			    Date Fecha=rs.getDate("Fecha"); 
			    int Id_Error=rs.getInt("Id_Error"); 
			    String Desc_Path=rs.getString("Desc_Path"); 
			    String Permisos=rs.getString("Permisos"); 
			    String Prerequisitos= rs.getString("Prerequisitos"); 
			    
				Tipo_Usuario sol = new Tipo_Usuario(Id_Solucion, Desc_Corta, Desc_Larga, Id_Error,  Fecha,  Id_Resolutor, 
									Desc_Path, 	Permisos, Prerequisitos); 
				soluciones.add(sol);  
			}		
			
		} catch (Exception ext) {System.out.println("Error List_Soluciones_Usuario:" + ext.getMessage());}
	 finally {
		try {
			ps.close();
			rs.close();
			db.desconectar();
			}catch (Exception ext) {System.out.println("Error :" + ext.getMessage());}
		}
		return soluciones;
	}


	@Override
	public void List_Soluciones(Errores err_inc) {
		try {	
		ArrayList<Tipo_Usuario> Soluciones_U_Encontradas =  List_Sol(err_inc);   //List_Soluciones_Usuario
		if (Soluciones_U_Encontradas.size()>0) { 
		    System.out.println("_________________________________________________________________");
	        System.out.println("Soluciones de Usuario Encontradas");
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
		} catch (Exception ext) {System.out.println("Error List_Soluciones_Usuario:" + ext.getMessage());}
	}


	@Override
	public void List_Soluciones(int tipo_usu, int id_area)   {
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
				     System.out.println("Soluciones de Usuario Encontradas");
				     System.out.println("_________________________________________________________________");

				     System.out.println(String.format("%-10s", "Solucion") + " | " +  String.format("%-30s", " Descripción")    + 
				    			 " | " + String.format("%-20s", "Aplicacion") + " | " + String.format("%-20s","Modulo")  + 
				    			 " | " + String.format("%-20s","Proceso") + " | " + String.format("%-20s","Pantalla") + 
				    			 " | " + String.format("%-20s","Desc_Area") + " | " + String.format("%-90s","Descripción") + 
				    			 " | " + String.format("%-50s","Path") + " | " + String.format("%-50s","Permisos") + 
				    			 " | " + String.format("%-50s","Prerequisitos"));
				    
					
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
				rs=ps.executeQuery(); 
				 
				while (rs.next()) { 
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
