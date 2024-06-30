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
		
		
		public  ArrayList<Tipo_Tecnica> List_Soluciones_Tecnicas (Errores err) throws SQLException {
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
				   
					Tipo_Tecnica sol = new Tipo_Tecnica(Id_Solucion, Desc_Corta, Desc_Larga, Id_Error,  Fecha,  Id_Resolutor, Script, Herramienta); 
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
