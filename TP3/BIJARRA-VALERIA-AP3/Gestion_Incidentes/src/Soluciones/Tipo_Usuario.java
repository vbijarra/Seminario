package Soluciones;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//Nombre: Valeria Bijarra
//Legajo: VINF013412
//DNI: 32494363
import java.util.Date;

import Conexion.Conectar;
import Incidentes.Errores;
import Incidentes.Incidentes;

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
	
	public  ArrayList<Tipo_Usuario> List_Soluciones_Usuario (Errores err) throws SQLException {
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
	
}
