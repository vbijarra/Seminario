package Conexion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Incidentes.Errores;

public class Modelo_Incidente {
	private Conectar conexion; 
	
	public Modelo_Incidente(Conectar conexion) {
		this.conexion= conexion; 
	} 

	public int  Incr_Id_Errores() throws SQLException {
		int id= 1; 
		java.sql.PreparedStatement ps= null; 
		ResultSet rs = null;  
		try {	
			ps = conexion.getConnection().prepareStatement("select max(Id_Error) from errores"); 
			rs=ps.executeQuery(); 
			while (rs.next()) {
				id = rs.getInt(1) +1; 
			}			
		} catch (Exception ext) {System.out.println("Error :" + ext.getMessage());}
	 finally {
		try {
			ps.close();
			rs.close();
			}catch (Exception ext) {System.out.println("Error :" + ext.getMessage());}
		}
	return id; 
	}
	
	
	public int Incr_Id_Incidentes() throws SQLException {
		int id= 1; 
		java.sql.PreparedStatement ps= null; 
		ResultSet rs = null;  
		try {	
			ps = conexion.getConnection().prepareStatement("select max(Id_Incidente) as max_inc from incidentes"); 
			rs=ps.executeQuery(); 
			while (rs.next()) {
				id = rs.getInt(1) +1; 
			}			
		} catch (Exception ext) {System.out.println("Error :" + ext.getMessage());}
	 finally {
		try {
			ps.close();
			rs.close();
			}catch (Exception ext) {System.out.println("Error :" + ext.getMessage());}
		}
	return id; 
	}
	
	
	
	public  ArrayList<Integer> List_Aplicaciones () throws SQLException {
		java.sql.PreparedStatement ps= null; 
		ResultSet rs = null;  
		ArrayList<Integer> aplic = new ArrayList<Integer>();
		
		try {	
			ps = conexion.getConnection().prepareStatement("select *  from aplicaciones "); 
			rs=ps.executeQuery(); 
			
			while (rs.next()) {
				System.out.println("[" + rs.getInt("Id_Aplicacion") + "]" + " " + rs.getString("Desc_Aplicacion"));
				aplic.add(rs.getInt("Id_Aplicacion"));  
			}		
			
		} catch (Exception ext) {System.out.println("Error List_Aplicaciones:" + ext.getMessage());}
	 finally {
		try {
			ps.close();
			rs.close();
			}catch (Exception ext) {System.out.println("Error :" + ext.getMessage());}
		}
		return aplic;
	}
	
	
	public  ArrayList<Integer> List_Modulos (int aplic) throws SQLException {
		java.sql.PreparedStatement ps= null; 
		ResultSet rs = null; 
		ArrayList<Integer> modul = new ArrayList<Integer>();
		try {	
			ps = conexion.getConnection().prepareStatement("select *  from modulos where Id_Aplicacion = " + aplic); 
			rs=ps.executeQuery(); 
			
			while (rs.next()) {
				System.out.println("[" + rs.getInt("Id_Modulo") + "]" + " " + rs.getString("Desc_Modulo"));
				modul.add(rs.getInt("Id_Modulo"));  
			}		
			
		} catch (Exception ext) {System.out.println("Error List_Modulos:" + ext.getMessage());}
	 finally {
		try {
			ps.close();
			rs.close();
			}catch (Exception ext) {System.out.println("Error :" + ext.getMessage());}
		}
		return modul;
	}
	
	
	public  ArrayList<Integer> List_Procesos (int mod) throws SQLException {
		java.sql.PreparedStatement ps= null; 
		ResultSet rs = null; 
		ArrayList<Integer> proc = new ArrayList<Integer>();
		
		try {	
			ps = conexion.getConnection().prepareStatement("select *  from procesos where Id_modulo = " + mod); 
			rs=ps.executeQuery(); 
			
			while (rs.next()) {
				System.out.println("[" + rs.getInt("Id_Proceso") + "]" + " " + rs.getString("Desc_Proceso"));
				proc.add(rs.getInt("Id_Proceso"));  
			}		
			
		} catch (Exception ext) {System.out.println("Error List_Procesos:" + ext.getMessage());}
	 finally {
		try {
			ps.close();
			rs.close();
			}catch (Exception ext) {System.out.println("Error :" + ext.getMessage());}
		}
		return proc;
	}
	
	
	public  ArrayList<Integer> List_Pantallas (int pro) throws SQLException {
		java.sql.PreparedStatement ps= null; 
		ResultSet rs = null; 
		ArrayList<Integer> pan = new ArrayList<Integer>();
		
		try {	
			ps = conexion.getConnection().prepareStatement("select *  from pantallas where Id_proceso = " + pro); 
			rs=ps.executeQuery(); 
			
			while (rs.next()) {
				System.out.println("[" + rs.getInt("Id_Pantalla") + "]" + " " + rs.getString("Desc_Pantalla"));
				pan.add(rs.getInt("Id_Pantalla"));  
			}		
			
		} catch (Exception ext) {System.out.println("Error List_Pantalla:" + ext.getMessage());}
	 finally {
		try {
			ps.close();
			rs.close();
			}catch (Exception ext) {System.out.println("Error :" + ext.getMessage());}
		}
		return pan;
	}

	
	public void Insertar_Incidente(Errores Inc) throws SQLException {
		java.sql.PreparedStatement ps= null; 	
		try {
			ps= conexion.getConnection().prepareStatement("INSERT INTO Errores (Id_Error, Id_Pantalla, Codigo, Descripcion, "
					+ "Pasos_Reprod, Id_Tipo_Prob) VALUES (?, ?, ?, ?, ?, ?)"); 
			ps.setInt(1, Inc.getSec_Err());
			ps.setInt(2, Inc.getPantalla());
			ps.setString(3,Inc.getNro_Error());
			ps.setString(4, Inc.getDescripcion_Corta());   
			ps.setString(5,Inc.getPasos());
			ps.setInt(6, Inc.getTipo_Problema()); 
			ps.executeUpdate(); 
			ps.close();
				
			ps = conexion.getConnection().prepareStatement("INSERT INTO Incidentes (Id_Incidente, Id_Usuario, Fecha, Anexo, "
					+ "Id_Error, Prioridad, Id_Estado) VALUES (?, ?, ?, ?, ?, ?, ?)"); 
			ps.setInt(1, Inc.getNro_Incidente());
			ps.setInt(2, Inc.getUsuario());
			ps.setDate(3, (java.sql.Date) Inc.getFecha()); 
			ps.setString(4,null);
			ps.setInt(5, Inc.getSec_Err());
			ps.setInt(6, Inc.getPrioridad());
			ps.setInt(7, Inc.getEstado()); 
			ps.executeUpdate(); 
			
			System.out.println("Incidente registrado con Exito en la base de datos. NRO : " + Inc.getNro_Incidente() + ".");
			 
		} catch (Exception ext) {System.out.println("Error al Crear_Incidente:" + ext.getMessage());}
	 finally {
		try {
			ps.close();
			}catch (Exception ext) {System.out.println("Error :" + ext.getMessage());}
		}
	}
	
	
	public ArrayList<Integer>  Buscar_Inc_Usuario_Abiertos (int Id_usu, int Id_area, int tipo_usu) throws SQLException { /*AGREGAR AL WORD*/
		java.sql.PreparedStatement ps= null; 
		ResultSet rs = null; 

		ArrayList<Integer> Incidentes_Usuario = new ArrayList<Integer>(); 
		ArrayList<String> Listado = new ArrayList<String>(); 
		
		String condicion = ""; 
		if (tipo_usu == 2) {
			condicion= " ar.Id_Area= ar.Id_Area "; 
		} else {
			condicion= " ar.Id_Area= " + Id_area; 
		}
		try {	
			ps = conexion.getConnection().prepareStatement("select i.Id_Incidente, i.Fecha , i.prioridad, err.Descripcion, "
					+ " Est.Desc_Estado, ap.desc_aplicacion , mo.Desc_Modulo , pro.Desc_Proceso , pan.Desc_Pantalla,  "
					+ " usu.Usuario , ar.Desc_Area " 
					+ " from   inc.incidentes i "
					+ " inner join inc.errores err on i.Id_Error= err.Id_Error    "
					+ " inner join inc.estados est on i.Id_Estado = est.Id_Estado "
					+ " inner join inc.pantallas pan on err.Id_Pantalla = pan.Id_Pantalla  "
					+ " inner join inc.procesos pro on pan.Id_Proceso = pro.Id_Proceso "
					+ " inner join inc.modulos mo on pro.Id_Modulo = mo.Id_Modulo "
					+ " inner join inc.aplicaciones ap on mo.Id_Aplicacion = ap.Id_Aplicacion  "
					+ " left join inc.resolutores res on res.Id_Resolutor = i.Id_Resolutor "
					+ " inner join inc.usuarios usu on i.Id_Usuario = usu.Id_Usuario  "
					+ " inner join inc.empleados em on usu.legajo = em.legajo  "
					+ " inner join inc.areas ar on em.Id_Area = ar.Id_Area  "  
					+ " where i.id_estado not in (2,4) and " + condicion);  
			rs=ps.executeQuery(); 

			while (rs.next()) { 
				Incidentes_Usuario.add(rs.getInt("id_Incidente")); 
				Listado.add(String.format("%-10s",  rs.getInt("Id_Incidente"))  + " | " +  
				String.format("%-15s", rs.getDate("Fecha")) + " | " +  
				String.format("%-10s", rs.getInt("Prioridad")) + " | " +  
			 	String.format("%-90s", rs.getString("Descripcion")).substring(0, 90) + " | " +  
			 	String.format("%-20s", rs.getString("Desc_Estado")) + " | " + 
			 	String.format("%-20s", rs.getString("Desc_Aplicacion")) + " | " +  
			 	String.format("%-20s", rs.getString("Desc_Modulo")) + " | " +  
			 	String.format("%-20s", rs.getString("Desc_Proceso")) + " | " + 
			 	String.format("%-20s", rs.getString("Desc_Pantalla")) + " | " + 
			 	String.format("%-20s", rs.getString("Usuario")) + " | " + 
			 	String.format("%-20s", rs.getString("Desc_Area"))); 
			}	
			Vista_Incidente vista = new Vista_Incidente(); 
			vista.Listar_Inc (Listado) ; 			
		} catch (Exception ext) {System.out.println("Error Buscar_Inc_Usuario_Abiertos:" + ext.getMessage());}
	 finally {
		try {
			ps.close();
			rs.close();
			}catch (Exception ext) {System.out.println("Error :" + ext.getMessage());}
		}
		return Incidentes_Usuario;		
	}
	
	
	public void Cancela_Incidente(int num) throws SQLException {
		java.sql.PreparedStatement ps= null; 
		try {
		ps = conexion.getConnection().prepareStatement("update incidentes set id_estado = 2 where id_incidente = "+ num);  
		ps.executeUpdate(); 
		ps.close(); 
	} catch (Exception ext) {System.out.println("Error al Crear_Incidente:" + ext.getMessage());}
	 finally {
		try {
			}catch (Exception ext) {System.out.println("Error :" + ext.getMessage());}
		}
	}
	
	public ArrayList<String> Listar_Incidentes(int id_usu, int id_area, int tipo_usu) throws SQLException {   /*PASAR A WORD VB 30/06/2024*/
		java.sql.PreparedStatement ps= null; 
		ResultSet rs = null;  
		ArrayList<String> Listado = new ArrayList<String>(); 
		String condicion = ""; 
		if (tipo_usu == 2) {
			condicion= " ar.Id_Area= ar.Id_Area "; 
		} else {
			condicion= " ar.Id_Area= " + id_area; 
		}
		
		 try {	 
			 
			ps = conexion.getConnection().prepareStatement("select i.Id_Incidente, i.Fecha , i.prioridad, err.Descripcion, "
					+ " Est.Desc_Estado, ap.desc_aplicacion , mo.Desc_Modulo , pro.Desc_Proceso , pan.Desc_Pantalla,  "
					+ " usu.Usuario , ar.Desc_Area " 
					+ " from   inc.incidentes i "
					+ " inner join inc.errores err on i.Id_Error= err.Id_Error    "
					+ " inner join inc.estados est on i.Id_Estado = est.Id_Estado "
					+ " inner join inc.pantallas pan on err.Id_Pantalla = pan.Id_Pantalla  "
					+ " inner join inc.procesos pro on pan.Id_Proceso = pro.Id_Proceso "
					+ " inner join inc.modulos mo on pro.Id_Modulo = mo.Id_Modulo "
					+ " inner join inc.aplicaciones ap on mo.Id_Aplicacion = ap.Id_Aplicacion  "
					+ " left join inc.resolutores res on res.Id_Resolutor = i.Id_Resolutor "
					+ " inner join inc.usuarios usu on i.Id_Usuario = usu.Id_Usuario  "
					+ " inner join inc.empleados em on usu.legajo = em.legajo  "
					+ " inner join inc.areas ar on em.Id_Area = ar.Id_Area  "  
					+ " where  " + condicion  
					+ " and i.Id_Estado  <> 2 "
					+ " order by 1" );  
			rs=ps.executeQuery(); 
			
			while (rs.next()) { 
				Listado.add(String.format("%-10s",  rs.getInt("Id_Incidente"))  + " | " +  
						String.format("%-15s", rs.getDate("Fecha")) + " | " +  
						String.format("%-10s", rs.getInt("Prioridad")) + " | " +  
					 	String.format("%-90s", rs.getString("Descripcion")).substring(0, 90) + " | " +  
					 	String.format("%-20s", rs.getString("Desc_Estado")) + " | " + 
					 	String.format("%-20s", rs.getString("Desc_Aplicacion")) + " | " +  
					 	String.format("%-20s", rs.getString("Desc_Modulo")) + " | " +  
					 	String.format("%-20s", rs.getString("Desc_Proceso")) + " | " + 
					 	String.format("%-20s", rs.getString("Desc_Pantalla")) + " | " + 
					 	String.format("%-20s", rs.getString("Usuario")) + " | " + 
					 	String.format("%-20s", rs.getString("Desc_Area"))); 
			}  
			
		} catch (Exception ext) {System.out.println("Error Listar_Incidentes:" + ext.getMessage());}
	 finally {
		try {
			ps.close();
			rs.close(); 
			}catch (Exception ext) {System.out.println("Error :" + ext.getMessage());}
		}
		 return Listado; 
	}
}
	
