package Seguridad;
//Nombre: Valeria Bijarra
//Legajo: VINF013412
//DNI: 32494363
import java.util.Date;

public class Empleados extends Usuarios{
	 String Nommbre; 
	 String Apellido; 
	 Date Fecha_Ingreso; 
	 Date Fecha_Egreso; 
	 int Area; 
	 int Legajo; 
	
	public Empleados(int Id_Usuario, String Usuario, String Estado, String Clave, 
			  String Nommbre, String Apellido,  Date Fecha_Ingreso,  Date Fecha_Egreso, int Area, int Legajo) {
		
		super( Id_Usuario,  Usuario,  Estado,  Clave);  
		
		this.Nommbre=Nommbre; 
		this.Apellido=Apellido; 
		this.Fecha_Ingreso=Fecha_Ingreso; 
		this.Fecha_Egreso = Fecha_Egreso; 
		this.Area = Area; 
		this.Legajo = Legajo; 
	}
		
 
	
	public Empleados() {}
}
