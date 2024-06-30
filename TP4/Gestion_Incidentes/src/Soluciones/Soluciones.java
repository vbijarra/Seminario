package Soluciones;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

import Incidentes.Errores; 

public abstract  class Soluciones {
	protected int Nro_Solucion;
	protected String Desc_Corta; 
	protected String Desc_Larga; 
	protected int Error; 
	protected Date Fecha; 
	protected int Resolutor; 
	
	public Soluciones (int Nro_Solucion,	String Desc_Corta, 	String Desc_Larga,	int Error, 	Date Fecha, int Resolutor) {
		this.Nro_Solucion= Nro_Solucion; 
		this.Desc_Corta= Desc_Corta; 
		this.Desc_Larga= Desc_Larga; 
		this.Error= Error; 
		this.Fecha= Fecha; 
		this.Resolutor= Resolutor;
}; 


static Scanner teclado = new Scanner(System.in);
public Soluciones() {}

public abstract void List_Soluciones(Errores err) throws SQLException;
public abstract void List_Soluciones(int tipo_usu, int id_area) throws SQLException;
 
}
