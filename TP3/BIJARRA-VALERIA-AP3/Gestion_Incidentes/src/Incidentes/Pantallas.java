package Incidentes;
//Nombre: Valeria Bijarra
//Legajo: VINF013412
//DNI: 32494363
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner; 
import Conexion.Conectar;

public class Pantallas {
	static Scanner teclado = new Scanner(System.in);
	//atributos
	private int Id_Pantalla; 	
	private String Pantalla;


 public Pantallas() {}

 public Pantallas (int Id_Pantalla, String Pantalla)
 {
	 this.Id_Pantalla=Id_Pantalla;  
	 this.Pantalla=Pantalla; 
 }

 public int getIdPantalla() {
	 return Id_Pantalla;
 }
 public String getPantalla() {
	 return Pantalla;
 }

 
 public void setIdPantalla(int IdPantalla) {
	 this.Id_Pantalla = IdPantalla;
 }
 
 public void setPantalla(String Pantalla) {
	 this.Pantalla = Pantalla;
 }

 
public String toString() {
	return   Id_Pantalla + "    " + Pantalla;  
}


public int  seleccionar_Pantalla(Pantallas pant, int proc) throws SQLException
{ 
	System.out.println("Seleccione Pantalla:");
	ArrayList<Integer> Lst_Pantallas = List_Pantallas(pant, proc);   //se almacena en un array list la lista de aplicaciones
		int pan=teclado.nextInt();
		int pan_ok = 0; 
		 
		for (int x=0;  x < Lst_Pantallas.size(); x++) {  
			
			if (Lst_Pantallas.get(x) == pan) {
				pan_ok = 1;  
			}  
		}
		if (pan_ok == 0) {
			System.out.println("Pantalla seleccionada no existe."); 
			seleccionar_Pantalla(pant, proc);
		}
		return pan;
}

public  ArrayList<Integer> List_Pantallas (Pantallas pant, int pro) throws SQLException {
	java.sql.PreparedStatement ps= null; 
	ResultSet rs = null; 
	Conectar db= new Conectar(); 
	ArrayList<Integer> pan = new ArrayList<Integer>();
	
	try {	
		ps = db.getConnection().prepareStatement("select *  from pantallas where Id_proceso = " + pro); 
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
		db.desconectar();
		}catch (Exception ext) {System.out.println("Error :" + ext.getMessage());}
	}
	return pan;
}

}
