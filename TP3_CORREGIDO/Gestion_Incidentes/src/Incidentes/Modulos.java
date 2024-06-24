package Incidentes;
//Nombre: Valeria Bijarra
//Legajo: VINF013412
//DNI: 32494363
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner; 
import Conexion.Conectar; 

public class Modulos {
	static Scanner teclado = new Scanner(System.in);
	//atributos
 		private int Id_Modulo; 	
		private String Modulo;

  
	 public Modulos() {}
	
	 public Modulos (int Id_Modulo, String Modulo)
	 {
		 this.Id_Modulo=Id_Modulo; 
		 this.Modulo=Modulo; 
	 }
	
	 public int getIdModulo() {
		 return Id_Modulo;
	 }
	 public String getModulo() {
		 return Modulo;
	 }

	 
	 public void setIdModulo(int IdModulo) {
		 this.Id_Modulo = IdModulo;
	 }
	 
	 public void setModulo(String Modulo) {
		 this.Modulo = Modulo;
	 }

	 
	public String toString() {
		return   Id_Modulo + "    " + Modulo;  
	}	
	
	
	public int  seleccionar_Modulo(Modulos mod, int aplic) throws SQLException
	{ 
		System.out.println("Seleccione Modulo:");
		ArrayList<Integer> Lst_Modulos = List_Modulos(mod, aplic);   //se almacena en un array list la lista de aplicaciones
			int modul=teclado.nextInt();
			int mod_ok = 0; 
			 
			for (int x=0;  x < Lst_Modulos.size(); x++) {  
				
				if (Lst_Modulos.get(x) == modul) {
					mod_ok = 1;  
				}  
			}
			if (mod_ok == 0) {
				System.out.println("Moulo seleccionado no existe."); 
				seleccionar_Modulo(mod, aplic);
			}
			return modul;
	}
	
	
	public  ArrayList<Integer> List_Modulos (Modulos apl, int aplic) throws SQLException {
		java.sql.PreparedStatement ps= null; 
		ResultSet rs = null; 
		Conectar db= new Conectar(); 
		ArrayList<Integer> modul = new ArrayList<Integer>();
		//Aplicaciones ap = new Aplicaciones();
		try {	
			ps = db.getConnection().prepareStatement("select *  from modulos where Id_Aplicacion = " + aplic); 
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
			db.desconectar();
			}catch (Exception ext) {System.out.println("Error :" + ext.getMessage());}
		}
		return modul;
	}
	
}