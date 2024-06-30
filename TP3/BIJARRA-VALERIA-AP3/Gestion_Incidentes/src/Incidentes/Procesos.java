package Incidentes;
//Nombre: Valeria Bijarra
//Legajo: VINF013412
//DNI: 32494363
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner; 
import Conexion.Conectar; 

public class Procesos {
	static Scanner teclado = new Scanner(System.in);
	//atributos
		private int Id_Proceso; 	
	private String Proceso;


 public Procesos() {}

 public Procesos (int Id_Proceso, String Proceso)
 {
	 this.Id_Proceso=Id_Proceso; 
	 this.Proceso=Proceso; 
 }

 public int getIdProceso() {
	 return Id_Proceso;
 }
 public String getProceso() {
	 return Proceso;
 }

 
 public void setIdProceso(int IdProceso) {
	 this.Id_Proceso = IdProceso;
 }
 
 public void setProceso(String Proceso) {
	 this.Proceso = Proceso;
 }

 
public String toString() {
	return   Id_Proceso + "    " + Proceso;  
}	


public int  seleccionar_Proceso(Procesos prod, int modu) throws SQLException
{ 
	System.out.println("Seleccione Proceso:");
	ArrayList<Integer> Lst_Procesos =  List_Procesos(prod, modu);   //se almacena en un array list la lista de aplicaciones
		int pro=teclado.nextInt();
		int pro_ok = 0; 
		 
		for (int x=0;  x < Lst_Procesos.size(); x++) {  
			
			if (Lst_Procesos.get(x) == pro) {
				pro_ok = 1;  
			}  
		}
		if (pro_ok == 0) {
			System.out.println("Proceso seleccionado no existe."); 
			seleccionar_Proceso(prod, modu);
		}
		return pro;
}
public  ArrayList<Integer> List_Procesos (Procesos prod, int mod) throws SQLException {
	java.sql.PreparedStatement ps= null; 
	ResultSet rs = null; 
	Conectar db= new Conectar(); 
	ArrayList<Integer> proc = new ArrayList<Integer>();
	
	try {	
		ps = db.getConnection().prepareStatement("select *  from procesos where Id_modulo = " + mod); 
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
		db.desconectar();
		}catch (Exception ext) {System.out.println("Error :" + ext.getMessage());}
	}
	return proc;
}
}
