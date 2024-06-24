package Incidentes;
//Nombre: Valeria Bijarra
//Legajo: VINF013412
//DNI: 32494363
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import Conexion.Conectar;

public class Aplicaciones {
	static Scanner teclado = new Scanner(System.in);
	//atributos
 		private int Id_Aplicacion; 	
		private String Aplicacion;

  
	 public Aplicaciones() {}
	
	 public Aplicaciones (int Id_Aplicacion, String Aplicacion)
	 {
		 this.Id_Aplicacion=Id_Aplicacion; 
		 this.Aplicacion=Aplicacion; 
	 }
	
	 public int getIdAplicacion() {
		 return Id_Aplicacion;
	 }
	 public String getAplicacion() {
		 return Aplicacion;
	 }

	 
	 public void setIdAplicacion(int IdAplicacion) {
		 this.Id_Aplicacion = IdAplicacion;
	 }
	 
	 public void setAplicacion(String Aplicacion) {
		 this.Aplicacion = Aplicacion;
	 }

	 
	public String toString() {
		return   Id_Aplicacion + "    " + Aplicacion;  
	}	
	
	
	public int  seleccionar_Apliacion(Aplicaciones apl) throws SQLException
	{ 
		System.out.println("Seleccione aplicación:");
		ArrayList<Integer> Lst_Aplicaciones = List_Aplicaciones(apl);   //se almacena en un array list la lista de aplicaciones
			int aplic=teclado.nextInt();
			int apl_ok = 0; 
			 
			for (int x=0;  x < Lst_Aplicaciones.size(); x++) {  
				
				if (Lst_Aplicaciones.get(x) == aplic) {
					apl_ok = 1;  
				}  
			}
			if (apl_ok == 0) {
				System.out.println("Aplicación seleccionada no existe."); 
				seleccionar_Apliacion(apl);
			}
			return aplic;
	}
	
	
	public  ArrayList<Integer> List_Aplicaciones (Aplicaciones apl) throws SQLException {
		java.sql.PreparedStatement ps= null; 
		ResultSet rs = null; 
		Conectar db= new Conectar(); 
		ArrayList<Integer> aplic = new ArrayList<Integer>();
		//Aplicaciones ap = new Aplicaciones();
		try {	
			ps = db.getConnection().prepareStatement("select *  from aplicaciones "); 
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
			db.desconectar();
			}catch (Exception ext) {System.out.println("Error :" + ext.getMessage());}
		}
		return aplic;
	}	
}