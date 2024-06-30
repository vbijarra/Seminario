package Incidentes;
//Nombre: Valeria Bijarra
//Legajo: VINF013412
//DNI: 32494363
import java.sql.SQLException;
import java.util.Scanner;

import Soluciones.Soluciones;
import Soluciones.Tipo_Tecnica;
import Soluciones.Tipo_Usuario;

public class Incidentes {
	protected int Nro_Incidente; 
	protected  java.sql.Date  Fecha; 
	protected int Usuario; 
	protected int Tipo_Problema; 
	protected int Prioridad; 
	protected int Estado; 
	protected int Solucion; 
	
	public Incidentes (int Nro_Incidente,
			 java.sql.Date  fecha2,    
						int Usuario,
						int Tipo_Problema, 
						int Prioridad,
						int Estado,
						int Solucion
						) {
		this.Nro_Incidente= Nro_Incidente; 
		this.Fecha= fecha2; 
		this.Usuario= Usuario; 
		this.Tipo_Problema= Tipo_Problema; 
		this.Prioridad= Prioridad; 
		this.Estado= Estado;
		this.Solucion = Solucion; 
	}; 
	
	static Scanner teclado = new Scanner(System.in);

	public int getSolucion() {
		return Solucion;
	}

	public void setSolucion(int solucion) {
		Solucion = solucion;
	}	

	public static void setTeclado(Scanner teclado) {
		Incidentes.teclado = teclado;
	}

	public int getNro_Incidente() {
		return Nro_Incidente;
	}

	public void setNro_Incidente(int nro_Incidente) {
		Nro_Incidente = nro_Incidente;
	}

	public java.sql.Date getFecha() {
		return Fecha;
	}

	public void setFecha( java.sql.Date fecha) {
		Fecha = fecha;
	}

	public int getUsuario() {
		return Usuario;
	}

	public void setUsuario(int usuario) {
		Usuario = usuario;
	}

	public int getTipo_Problema() {
		return Tipo_Problema;
	}


	public void setTipo_Problema(int tipo_Problema) {
		Tipo_Problema = tipo_Problema;
	}

	public int getPrioridad() {
		return Prioridad;
	}

	public void setPrioridad(int prioridad) {
		Prioridad = prioridad;
	}

	public int getEstado() {
		return Estado;
	}

	public void setEstado(int estado) {
		Estado = estado;
	}


	
	public void Buscar_Soluciones(Errores err_inc, int tipo_usu) throws SQLException {		
		if (tipo_usu == 1) {
			Soluciones solu= new Tipo_Usuario(); 
			solu.List_Soluciones(err_inc);			
		} else {
			Soluciones solu= new Tipo_Tecnica();
			solu.List_Soluciones(err_inc);
		}  
	}

 
}
