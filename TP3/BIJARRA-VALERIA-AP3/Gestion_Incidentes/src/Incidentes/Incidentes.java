package Incidentes;
//Nombre: Valeria Bijarra
//Legajo: VINF013412
//DNI: 32494363
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import Conexion.Conectar;
import Soluciones.Soluciones;
import jdk.jfr.Description;

public class Incidentes {
	static Scanner teclado = new Scanner(System.in);
	protected int Nro_Incidente; 
	protected Date Fecha; 
	protected int Usuario; 
	protected int Tipo_Problema; 
	protected int Prioridad; 
	protected int Estado; 
	  	
	public Incidentes (int Nro_Incidente,
						Date  fecha2,    
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
	}; 
	
 
	public Incidentes() {
		// TODO Auto-generated constructor stub
	}


	public void Crear_Incidente1(int usu, int tipo_usu) throws SQLException {
		Scanner teclado = new Scanner (System.in); 
		String descripcion=Ingresa_Descripcion(); 
		String pasos = Ingresa_Pasos(); 
		String codigo = Ingresa_Codigo(); 
		int tipo= Ingresa_Tipo_Problema(); 		
		Aplicaciones apl = new Aplicaciones();
		int aplicacion_sel= apl.seleccionar_Apliacion(apl); 
		Modulos mod= new Modulos(); 
		int modulo_Sel = mod.seleccionar_Modulo(mod, aplicacion_sel); 
		Procesos  prod= new Procesos(); 
		int proceso_Sel = prod.seleccionar_Proceso(prod, modulo_Sel); 
		Pantallas  pant= new Pantallas(); 
		int pantalla_Sel = pant.seleccionar_Pantalla(pant, proceso_Sel); 
		int prioridad= Ingresa_Prioridad(); 
		
		System.out.println("Desea confirmar Incidente?");
		System.out.println("[1]Si    [2]No ");
		int confirma=teclado.nextInt();  
		 
		int sec_inc = id_Incr_Incidentes(); 
		int sec_err = id_Incr_Errores(); 
	 	LocalDate fecha = LocalDate.now();  //obtiene fecha del sistema;
	 	java.sql.Date fecha_actual = java.sql.Date.valueOf(fecha);
		int estado = 1; /*Abierto*/
		 
		if (confirma == 1) {  
				Errores Inc = new Errores(sec_inc, fecha_actual, usu, tipo,  prioridad, estado, ' '  ,  
						codigo, pantalla_Sel,  aplicacion_sel,  modulo_Sel,  proceso_Sel,  descripcion,  pasos, sec_err);  
				Inc.Insertar_Incidente(Inc);
				Inc.Buscar_Soluciones(Inc, tipo_usu);
				
			} else {
				 System.out.println("_________________________________________________________________");
			        System.out.println("[1] Registrar otro Incidente. [2] Salir" );
					int conf=teclado.nextInt();
					if (conf == 1) {
						 Crear_Incidente1(usu, tipo_usu); 
					} else {return;
							}
			}			
	}
	
	 
	public String Ingresa_Descripcion() {
		System.out.println("Ingrese Descripción del problema:");
		String descripcion=teclado.nextLine();
		if (descripcion.length() > 250) {
			System.out.println("Descripción supera limite 250 caracteres. Ingrese nuevamente.");
			Ingresa_Descripcion(); 
		} else {
			if (descripcion.length() == 0) {
				System.out.println("Campo obligatorio. Ingresar valor.");
				Ingresa_Descripcion(); 
				}}
		return 	descripcion; 
	
	}
	
	public String Ingresa_Pasos() {
			System.out.println("Ingrese pasos para reproducir el error:");
			String pasos=teclado.nextLine();   
			if (pasos.length() >= 250) {
				System.out.println("Campo supera limite 250 caracteres. Ingrese nuevamente.");
				Ingresa_Pasos(); 
			} else {
				if (pasos.length() == 0) {
					System.out.println("Campo obligatorio. Ingresar valor.");
					Ingresa_Pasos();  
					}
				}
			return 	pasos; 
	}
	 
	public String Ingresa_Codigo() {
		System.out.println("Ingrese Código de error:");
		String codigo=teclado.nextLine();  
		if (codigo.length() > 10) {
			System.out.println("Campo supera limite 10 caracteres. Ingrese nuevamente.");
			Ingresa_Codigo(); 
		}  else {
				if (codigo.length() == 0) {
					System.out.println("Campo obligatorio. Ingresar valor.");
					Ingresa_Codigo();  
				}}
		return 	codigo;
	}
	
	public int Ingresa_Tipo_Problema() {
		System.out.println("Ingrese tipo de problema:");
		System.out.println("[1] Sistemas [2] IT:");
		int tipo=teclado.nextInt();   
		
		if (tipo !=1) {
			if (tipo !=2) {
				System.out.println("Tipo de problema incorrecto.");
				Ingresa_Tipo_Problema();
			 }} 
		return tipo; 
	}

	public int Ingresa_Prioridad() {
		System.out.println("Seleccione Prioridad.");
		System.out.println("[1] Alta  [2] Media  [3] Baja ");
		int prioridad= teclado.nextInt();

		if (prioridad !=1) {
			if (prioridad !=2) {
				if (prioridad !=3) {
					Ingresa_Prioridad();
				}
			 }} 
		return prioridad; 
	}
	
	
	
	public void Insertar_Incidente(Errores Inc) throws SQLException {
		java.sql.PreparedStatement ps= null; 
		Conectar db= new Conectar(); 
		try {
			ps= db.getConnection().prepareStatement("INSERT INTO Errores (Id_Error, Id_Pantalla, Codigo, Descripcion, "
					+ "Pasos_Reprod, Id_Tipo_Prob) VALUES (?, ?, ?, ?, ?, ?)"); 
			ps.setInt(1, Inc.Sec_Err);
			ps.setInt(2, Inc.Pantalla);
			ps.setString(3,Inc.Nro_Error);
			ps.setString(4, Inc.Descripcion_Corta);   
			ps.setString(5,Inc.Pasos);
			ps.setInt(6, Inc.Tipo_Problema); 
			ps.executeUpdate(); 
			ps.close();
				
			ps = db.getConnection().prepareStatement("INSERT INTO Incidentes (Id_Incidente, Id_Usuario, Fecha, Anexo, "
					+ "Id_Error, Prioridad, Id_Estado) VALUES (?, ?, ?, ?, ?, ?, ?)"); 
			ps.setInt(1, Inc.Nro_Incidente);
			ps.setInt(2, Inc.Usuario);
			ps.setDate(3, (java.sql.Date) Inc.Fecha); 
			ps.setString(4,null);
			ps.setInt(5, Inc.Sec_Err);
			ps.setInt(6, Inc.Prioridad);
			ps.setInt(7, Inc.Estado); 
			ps.executeUpdate(); 
			
			System.out.println("Incidente registrado con Exito en la base de datos. NRO : " + Inc.Nro_Incidente + ".");
			 
		} catch (Exception ext) {System.out.println("Error al Crear_Incidente:" + ext.getMessage());}
	 finally {
		try {
			ps.close();
			db.desconectar();
			}catch (Exception ext) {System.out.println("Error :" + ext.getMessage());}
		}
	}
	
	public void Buscar_Soluciones(Errores err_inc, int tipo_usu) throws SQLException {
		Soluciones sol = new Soluciones(); 
		sol.Obtener_Soluciones(tipo_usu, err_inc); 
	}
	 
	/*public ArrayList<Integer> Buscar_Inc_Usuario(int Id_usu, int Id_area) throws SQLException {		
		ArrayList<Integer> Lista_Inc_Usuario = Buscar_Inc_Usuario_Abiertos (Id_usu, Id_area);
		return Lista_Inc_Usuario; 
	}*/

	public void Cancelar_Inc(ArrayList<Integer> List_Inc_Abi_Area) throws SQLException {		
		int nro = 0; 
		int inc_ok=0; 
		java.sql.PreparedStatement ps= null; 
		Conectar db= new Conectar(); 
		try {
			if (List_Inc_Abi_Area.size()>0) {  
				System.out.println("Seleccione Número de Incidente:");
				nro=teclado.nextInt();
				for (int x=0;  (x < List_Inc_Abi_Area.size() && inc_ok==0); x++) {  
					
					if (List_Inc_Abi_Area.get(x).equals(nro)) { 
						
						ps = db.getConnection().prepareStatement("update incidentes set id_estado = 2 where id_incidente = "+ nro);  
						ps.executeUpdate(); 
						ps.close(); 
						System.out.println("Incidente CANCELADO. NRO : " +  nro + ".");
						List_Inc_Abi_Area.remove(x);   //eliminar del array el nro de incidente
						inc_ok = 1;   
						System.out.println("Desea cancelar otro incidente?");
						System.out.println("_________________________________________________________________");
					    System.out.println("[1] Cancelar Incidente    [2] Salir." );
						int confirma=teclado.nextInt();
						if (confirma != 1) {
							return; 
						} else 
						{
							Cancelar_Inc(List_Inc_Abi_Area); 
						}
					}  
				}
				if (inc_ok == 0) {
					System.out.println("Nro de Incidente incorrecto.Que desea hacer?"); 
					System.out.println("_________________________________________________________________");
				    System.out.println("[1] Cancelar Incidente  [2] Salir." );
					int confirma=teclado.nextInt();
					if (confirma != 1) { 
						db.desconectar();
						return; 
					} else 
					{
						Cancelar_Inc(List_Inc_Abi_Area); 
					}
					
				}
			}
			else {
					System.out.println("No existen incidentes para Cancelar."); 
			       	return; 
			}
		} catch (Exception ext) {System.out.println("Error al Crear_Incidente:" + ext.getMessage());}
	 finally {
		try {
			//ps.close();
			db.desconectar();
			}catch (Exception ext) {System.out.println("Error :" + ext.getMessage());}
		} 
	}
	
	
	
	public  void  Listar_Inc_Area (int Id_usu, int Id_area) throws SQLException {
		java.sql.PreparedStatement ps= null; 
		ResultSet rs = null; 
		Conectar db= new Conectar(); 
		 try {	  
			ps = db.getConnection().prepareStatement("select i.Id_Incidente, i.Fecha , i.prioridad, err.Descripcion, "
					+ " Est.Desc_Estado, ap.desc_aplicacion , mo.Desc_Modulo , pro.Desc_Proceso , pan.Desc_Pantalla,  "
					+ " usu.Usuario , ar.Desc_Area " 
					+ " from   inc.incidentes i "
					+ " inner join inc.errores err on i.Id_Error= err.Id_Error    "
					+ " inner join inc.estados est on i.Id_Estado = est.Id_Estado "
					+ " inner join inc.pantallas pan on err.Id_Pantalla = pan.Id_Pantalla  "
					+ " inner join inc.procesos pro on pan.Id_Proceso = pro.Id_Proceso "
					+ " inner join inc.modulos mo on pro.Id_Modulo = mo.Id_Modulo "
					+ " inner join inc.aplicaciones ap on mo.Id_Aplicacion = ap.Id_Aplicacion  "
					+ " left join inc.resolutores res on res.Id_Resolutor = i.Id_Resolutor "
					+ " inner join inc.usuarios usu on i.Id_Usuario = usu.Id_Usuario  "
					+ " inner join inc.empleados em on usu.legajo = em.legajo  "
					+ " inner join inc.areas ar on em.Id_Area = ar.Id_Area  "  
					+ " where   ar.Id_Area = " + Id_area);  
			rs=ps.executeQuery(); 
			
			 System.out.println("_________________________________________________________________");
		     System.out.println("Incidentes Encontrados");
		     System.out.println("_________________________________________________________________");
		     System.out.println(String.format("%-10s", "Incidente") + " | " +  String.format("%-15s", " Fecha")  + " | " +  
		     String.format("%-10s", " Prior.")  + " | " + String.format("%-90s", "Descripción") + " | " + 
		     String.format("%-20s","Estado")  + "| " + String.format("%-20s","Aplicacion")  + " | " + 
		     String.format("%-20s","Modulo") + " | " + String.format("%-20s","Proceso") + " | " + String.format("%-20s","Pantalla") + " | " + 
		     String.format("%-20s","Usuario") + " | " + String.format("%-20s", "Area"));
	        
			while (rs.next()) { 
				System.out.println( String.format("%-10s",rs.getInt("Id_Incidente"))  + " | " +  
				String.format("%-15s", rs.getDate("Fecha")) + " | " +  
				String.format("%-10s", rs.getInt("Prioridad")) + " | " +  
			 	String.format("%-90s", rs.getString("Descripcion")).substring(0, 90) + " | " +  
			 	String.format("%-20s", rs.getString("Desc_Estado")) + " | " + 
			 	String.format("%-20s", rs.getString("Desc_Aplicacion")) + " | " +  
			 	String.format("%-20s", rs.getString("Desc_Modulo")) + " | " +  
			 	String.format("%-20s", rs.getString("Desc_Proceso")) + " | " + 
			 	String.format("%-20s", rs.getString("Desc_Pantalla")) + " | " + 
			 	String.format("%-20s", rs.getString("Usuario")) + " | " + 
			 	String.format("%-20s", rs.getString("Desc_Area")));  
			}  
			
			System.out.println("Regresar al menú principal?");
			System.out.println("_________________________________________________________________");
		    System.out.println("[1] No    [2] Salir." );
			int confirma=teclado.nextInt();
			if (confirma != 1) {
				return; 
			}else {Listar_Inc_Area (Id_usu, Id_area); }
			
		} catch (Exception ext) {System.out.println("Error Buscar_Inc_Usuario_Abiertos:" + ext.getMessage());}
	 finally {
		try {
			ps.close();
			rs.close();
			db.desconectar();
			}catch (Exception ext) {System.out.println("Error :" + ext.getMessage());}
		} 
	}

	
	
	public  ArrayList<Integer>  Buscar_Inc_Usuario_Abiertos (int Id_usu, int Id_area) throws SQLException {
		java.sql.PreparedStatement ps= null; 
		ResultSet rs = null; 
		Conectar db= new Conectar(); 
		ArrayList<Integer> Incidentes_Usuario = new ArrayList<Integer>(); 
		try {	
			ps = db.getConnection().prepareStatement("select i.Id_Incidente, i.Fecha , i.prioridad, err.Descripcion, "
					+ " Est.Desc_Estado, ap.desc_aplicacion , mo.Desc_Modulo , pro.Desc_Proceso , pan.Desc_Pantalla,  "
					+ " usu.Usuario , ar.Desc_Area " 
					+ " from   inc.incidentes i "
					+ " inner join inc.errores err on i.Id_Error= err.Id_Error    "
					+ " inner join inc.estados est on i.Id_Estado = est.Id_Estado "
					+ " inner join inc.pantallas pan on err.Id_Pantalla = pan.Id_Pantalla  "
					+ " inner join inc.procesos pro on pan.Id_Proceso = pro.Id_Proceso "
					+ " inner join inc.modulos mo on pro.Id_Modulo = mo.Id_Modulo "
					+ " inner join inc.aplicaciones ap on mo.Id_Aplicacion = ap.Id_Aplicacion  "
					+ " left join inc.resolutores res on res.Id_Resolutor = i.Id_Resolutor "
					+ " inner join inc.usuarios usu on i.Id_Usuario = usu.Id_Usuario  "
					+ " inner join inc.empleados em on usu.legajo = em.legajo  "
					+ " inner join inc.areas ar on em.Id_Area = ar.Id_Area  "  
					+ " where i.id_estado not in (2,4) and ar.Id_Area = " + Id_area);  
			rs=ps.executeQuery(); 
			
			 System.out.println("_________________________________________________________________");
		     System.out.println("Incidentes Encontrados");
		     System.out.println("_________________________________________________________________");
		     System.out.println(String.format("%-10s", "Incidente") + " | " +  String.format("%-15s", " Fecha")  
		     + " | " +  String.format("%-10s", " Prior.")  + " | " + String.format("%-90s", "Descripción") 
		     + " | " + String.format("%-20s","Estado")  + "| " + String.format("%-20s","Aplicacion")  
		     + " | " + String.format("%-20s","Modulo") + " | " + String.format("%-20s","Proceso") 
		     + " | " + String.format("%-20s","Pantalla") + " | " + String.format("%-20s","Usuario") 
		     + " | " + String.format("%-20s", "Area"));
	        
			while (rs.next()) { 
				System.out.println( String.format("%-10s",rs.getInt("Id_Incidente"))  + " | " +  
				String.format("%-15s", rs.getDate("Fecha")) + " | " +  
				String.format("%-10s", rs.getInt("Prioridad")) + " | " +  
			 	String.format("%-90s", rs.getString("Descripcion")).substring(0, 90) + " | " +  
			 	String.format("%-20s", rs.getString("Desc_Estado")) + " | " + 
			 	String.format("%-20s", rs.getString("Desc_Aplicacion")) + " | " +  
			 	String.format("%-20s", rs.getString("Desc_Modulo")) + " | " +  
			 	String.format("%-20s", rs.getString("Desc_Proceso")) + " | " + 
			 	String.format("%-20s", rs.getString("Desc_Pantalla")) + " | " + 
			 	String.format("%-20s", rs.getString("Usuario")) + " | " + 
			 	String.format("%-20s", rs.getString("Desc_Area"))); 
				
				Incidentes_Usuario.add(rs.getInt("id_Incidente"));  
			}		
			
		} catch (Exception ext) {System.out.println("Error Buscar_Inc_Usuario_Abiertos:" + ext.getMessage());}
	 finally {
		try {
			ps.close();
			rs.close();
			db.desconectar();
			}catch (Exception ext) {System.out.println("Error :" + ext.getMessage());}
		}
		return Incidentes_Usuario;
	}
	
	
	public int id_Incr_Errores() throws SQLException {
		int id= 1; 
		java.sql.PreparedStatement ps= null; 
		ResultSet rs = null; 
		Conectar db= new Conectar(); 
		try {	
			ps = db.getConnection().prepareStatement("select max(Id_Error) from errores"); 
			rs=ps.executeQuery(); 
			while (rs.next()) {
				id = rs.getInt(1) +1; 
			}			
		} catch (Exception ext) {System.out.println("Error :" + ext.getMessage());}
	 finally {
		try {
			ps.close();
			rs.close();
			db.desconectar();
			}catch (Exception ext) {System.out.println("Error :" + ext.getMessage());}
		}
	return id; 
	}
	
	
	public int id_Incr_Incidentes() throws SQLException {
		int id= 1; 
		java.sql.PreparedStatement ps= null; 
		ResultSet rs = null; 
		Conectar db= new Conectar(); 
		try {	
			ps = db.getConnection().prepareStatement("select max(Id_Incidente) as max_inc from incidentes"); 
			rs=ps.executeQuery(); 
			while (rs.next()) {
				id = rs.getInt(1) +1; 
			}			
		} catch (Exception ext) {System.out.println("Error :" + ext.getMessage());}
	 finally {
		try {
			ps.close();
			rs.close();
			db.desconectar();
			}catch (Exception ext) {System.out.println("Error :" + ext.getMessage());}
		}
	return id; 
	}
 
}
