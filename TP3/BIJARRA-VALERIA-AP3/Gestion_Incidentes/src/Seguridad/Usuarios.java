package Seguridad;
//Nombre: Valeria Bijarra
//Legajo: VINF013412
//DNI: 32494363
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import Conexion.Conectar;

public class Usuarios {
	protected int Id_Usuario; 
	protected String Usuario; 
	protected String Estado; 
	protected String Clave; 
	protected int Legajo; 
		
	public Usuarios(int Id_Usuario, 
	 String Usuario, 
	 String Estado,
	 String Clave) {
		this.Id_Usuario= Id_Usuario; 
		this.Usuario=Usuario;
		this.Estado = Estado;
		this.Clave = Clave; 
	}
	
	public Usuarios() {}
	
	public int[][] Solicitar_Datos_loguin() throws SQLException {
		Scanner teclado = new Scanner (System.in);
		
		System.out.println("_________________________________________________________________");
		System.out.println("GESTIÓN DE INCIDENTES - OLEOSIN 			");
		System.out.println("_________________________________________________________________");
		System.out.println("ACCESO 				");
		System.out.println("_________________________________________________________________");

		System.out.println("Usuario:");
		String usuario=teclado.nextLine();   
		System.out.println("Clave:");
		String clave=teclado.nextLine();
		
		int id_u = Validar_Datos(usuario, clave);  
		int tipo_u = Obtener_Tipo_Usuario(id_u);   
		int id_area = Obtener_Area_Usuario(id_u); 
		
		int[][] infoUsuario = new int[1][3]; 
		infoUsuario[0][0]=  id_u; 
		infoUsuario[0][1]= tipo_u; 
		infoUsuario[0][2]=id_area;
		 		
		return infoUsuario; 
	}
	
	public int Validar_Datos(String usuario, String clave) throws SQLException {
		int id_usu=0; 
		if (usuario!= null && clave != null) {
			id_usu=Validar_Usuario(usuario, clave); 
			
			if (id_usu== 0) {
				System.out.println("Datos incorrectos.");
				Solicitar_Datos_loguin(); 
			}
		}
		return id_usu;  
	}
	
	
	
	public  int Validar_Usuario (String usuario, String clave) throws SQLException {
		java.sql.PreparedStatement ps= null; 
		ResultSet rs = null; 
		Conectar db= new Conectar(); 
		int Id_Usuario=0;  
		try {	
			ps = db.getConnection().prepareStatement("select Id_Usuario  from usuarios where usuario =  "  + "'"+ usuario + "'"
														+ " and clave = " + clave
														+ ";");  
			rs=ps.executeQuery(); 
			
			while (rs.next()) {
				
				Id_Usuario = rs.getInt("Id_Usuario"); 
			}		
			
		} catch (Exception ext) {System.out.println("Error Validar Usuario:" + ext.getMessage());}
	 finally {
		try {
			ps.close();
			rs.close();
			db.desconectar();
			}catch (Exception ext) {System.out.println("Error :" + ext.getMessage());}
		}
		return Id_Usuario;
	}
	
	
	public int Obtener_Tipo_Usuario(int id_usu)  throws SQLException {
		java.sql.PreparedStatement ps= null; 
		ResultSet rs = null; 
		Conectar db= new Conectar(); 
		int Tipo_Usuario=0;  
		try {	
			ps = db.getConnection().prepareStatement("select Tipo_Usuario  "
														+ " from usuarios "
														+ " where Id_Usuario = " + id_usu);  
			rs=ps.executeQuery(); 
			
			while (rs.next()) {
				Tipo_Usuario = rs.getInt("Tipo_Usuario"); 
			}		
			
			
		} catch (Exception ext) {System.out.println("Error :Obtener_Tipo_Usuario " + ext.getMessage());}
	 finally {
		try {
			ps.close();
			rs.close();
			db.desconectar();
			}catch (Exception ext) {System.out.println("Error :" + ext.getMessage());}
		}
		return Tipo_Usuario;
	}
	
	
	
	public int Obtener_Area_Usuario(int id_usu)  throws SQLException {
		java.sql.PreparedStatement ps= null; 
		ResultSet rs = null; 
		Conectar db= new Conectar(); 
		int Id_Area=0;  
		try {	
			ps = db.getConnection().prepareStatement("select Id_Area  "
														+ " from usuarios u, empleados e "
														+ " where u.legajo = e.legajo and u.Id_Usuario = " + id_usu);  
			rs=ps.executeQuery(); 
			
			while (rs.next()) {
				Id_Area = rs.getInt("Id_Area"); 
			}		
			
			
		} catch (Exception ext) {System.out.println("Error :Obtener_Area_Usuario " + ext.getMessage());}
	 finally {
		try {
			ps.close();
			rs.close();
			db.desconectar();
			}catch (Exception ext) {System.out.println("Error :" + ext.getMessage());}
		}
		return Id_Area;
	}
}
