package Conexion;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class Vista_Incidente {
    Scanner teclado = new Scanner(System.in); 
	
	public int Solicita_Tipo_Problema() { 
			System.out.println("Ingrese tipo de problema:");
			System.out.println("[1] Sistemas [2] IT:");
			int tipo=Integer.parseInt(teclado.nextLine());    
			if (tipo !=1) {
				if (tipo !=2) {
					System.out.println("Tipo de problema incorrecto.");
					Solicita_Tipo_Problema();
				 }} 
			return tipo;  
	}
	
	public int Solicita_Prioridad() {
		System.out.println("Seleccione Prioridad.");
		System.out.println("[1] Alta  [2] Media  [3] Baja ");
		int prioridad= Integer.parseInt(teclado.nextLine());  

		if (prioridad !=1) {
			if (prioridad !=2) {
				if (prioridad !=3) {
					Solicita_Prioridad();
				}
			 }} 
		return prioridad; 
	}
	
	public String Solicita_Descripcion() {
		System.out.println("Ingrese Descripción del problema:");
		String desc=teclado.nextLine(); 
		if (desc.length() >= 250) {
			System.out.println("Descripción supera limite 250 caracteres. Ingrese nuevamente.");
			Solicita_Descripcion(); 
		} else {
			if (desc.length() == 0) {
				System.out.println("Campo obligatorio. Ingresar valor.");
				Solicita_Descripcion(); 
				}
			}
		return 	desc; 	
	}
	
	public String Solicita_Pasos() {
			System.out.println("Ingrese pasos para reproducir el error:");
			String pasos=teclado.nextLine();   
			if (pasos.length() >= 250) {
				System.out.println("Campo supera limite 250 caracteres. Ingrese nuevamente.");
				Solicita_Pasos(); 
			} else {
				if (pasos.length() == 0) {
					System.out.println("Campo obligatorio. Ingresar valor.");
					Solicita_Pasos();  
					}
				}
			return 	pasos; 
	}
	 
	public String Solicita_Codigo() {
		System.out.println("Ingrese Código de error:");
		String codigo=teclado.nextLine();  
		if (codigo.length() > 10) {
			System.out.println("Campo supera limite 10 caracteres. Ingrese nuevamente.");
			Solicita_Codigo(); 
		}  else {
				if (codigo.length() == 0) {
					System.out.println("Campo obligatorio. Ingresar valor.");
					Solicita_Codigo();  
				}}
		return 	codigo;
	}
	
	public int Seleccionar_Confirma() {
		System.out.println("Desea confirmar?");
		System.out.println("[1]Si    [2]No ");
		return teclado.nextInt();  
	}
	
	
	public int Seleccionar_Confirma_Reg_Incidente() {
		System.out.println("_________________________________________________________________");
        System.out.println("[1] Registrar otro Incidente. [2] Salir" );
		return teclado.nextInt();
	}

	
	public int Seleccionar_Cancela_Otro_Incidente() {
		System.out.println("Desea cancelar otro incidente?");
		System.out.println("_________________________________________________________________");
	    System.out.println("[1] Cancelar Incidente    [2] Salir." );
		return teclado.nextInt();
	}
	
	public int Seleccionar_mje_nro_Inc_Incorrecto_Cancel() {
		System.out.println("Nro de Incidente incorrecto.Que desea hacer?"); 
		System.out.println("_________________________________________________________________");
		System.out.println("[1] Cancelar Incidente  [2] Salir." );
		return teclado.nextInt();
	}
	
	
	public int Seleccionar_retorna_Menu() {
		System.out.println("Regresar al menú principal?");
		System.out.println("_________________________________________________________________");
	    System.out.println("[1] No    [2] Salir." );
		return teclado.nextInt();
	}
	
	
	public int  seleccionar_Apliacion(Modelo_Incidente modelo) throws SQLException
	{ 
		System.out.println("Seleccione aplicación:");
		ArrayList<Integer> Lst_Aplicaciones = modelo.List_Aplicaciones();   //se almacena en un array list la lista de aplicaciones
			int aplic=teclado.nextInt();
			int apl_ok = 0; 
			 
			for (int x=0;  x < Lst_Aplicaciones.size(); x++) {  
				
				if (Lst_Aplicaciones.get(x) == aplic) {
					apl_ok = 1;  
				}  
			}
			if (apl_ok == 0) {
				System.out.println("Aplicación seleccionada no existe."); 
				seleccionar_Apliacion(modelo);
			}
			return aplic;
	}
	
	public int  seleccionar_Modulo(Modelo_Incidente modelo, int aplic) throws SQLException
	{ 
		System.out.println("Seleccione Modulo:");
		ArrayList<Integer> Lst_Modulos = modelo.List_Modulos(aplic);   //se almacena en un array list la lista de modulos
			int modul=teclado.nextInt();
			int mod_ok = 0; 
			 
			for (int x=0;  x < Lst_Modulos.size(); x++) {  
				
				if (Lst_Modulos.get(x) == modul) {
					mod_ok = 1;  
				}  
			}
			if (mod_ok == 0) {
				System.out.println("Moulo seleccionado no existe."); 
				seleccionar_Modulo(modelo, aplic);
			}
			return modul;
	}
	
	public int  seleccionar_Proceso(Modelo_Incidente modelo, int modu) throws SQLException
	{ 
		System.out.println("Seleccione Proceso:");
		ArrayList<Integer> Lst_Procesos =  modelo.List_Procesos(modu);   //se almacena en un array list la lista de procesos
			int pro=teclado.nextInt();
			int pro_ok = 0; 
			 
			for (int x=0;  x < Lst_Procesos.size(); x++) {  
				
				if (Lst_Procesos.get(x) == pro) {
					pro_ok = 1;  
				}  
			}
			if (pro_ok == 0) {
				System.out.println("Proceso seleccionado no existe."); 
				seleccionar_Proceso(modelo, modu);
			}
			return pro;
	}
	
	
	public int  seleccionar_Pantalla(Modelo_Incidente modelo, int proc) throws SQLException
	{ 
		System.out.println("Seleccione Pantalla:");
		ArrayList<Integer> Lst_Pantallas = modelo.List_Pantallas(proc);   //se almacena en un array list la lista de pantallas
			int pan=teclado.nextInt();
			int pan_ok = 0; 
			 
			for (int x=0;  x < Lst_Pantallas.size(); x++) {  
				
				if (Lst_Pantallas.get(x) == pan) {
					pan_ok = 1;  
				}  
			}
			if (pan_ok == 0) {
				System.out.println("Pantalla seleccionada no existe."); 
				seleccionar_Pantalla(modelo, proc);
			}
			return pan;
	}
	
	public int Seleccionar_Nro_Incidente() {
		System.out.println("Seleccione Número de Incidente:");
		return teclado.nextInt();
	}


	public void Listar_Inc (ArrayList<String> Data) {		
		 System.out.println("_________________________________________________________________");
	     System.out.println("Incidentes Encontrados");
	     System.out.println("_________________________________________________________________");
	     System.out.println(String.format("%-10s", "Incidente") + " | " +  String.format("%-15s", " Fecha")  
	     + " | " +  String.format("%-10s", " Prior.")  + " | " + String.format("%-90s", "Descripción") 
	     + " | " + String.format("%-20s","Estado")  + "| " + String.format("%-20s","Aplicacion")  
	     + " | " + String.format("%-20s","Modulo") + " | " + String.format("%-20s","Proceso") 
	     + " | " + String.format("%-20s","Pantalla") + " | " + String.format("%-20s","Usuario") 
	     + " | " + String.format("%-20s", "Area"));
	    
	     for (int x=0;  (x < Data.size()); x++) { 
				System.out.println(Data.get(x));
			}	
	}

}
