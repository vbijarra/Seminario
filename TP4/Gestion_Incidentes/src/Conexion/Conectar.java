package Conexion;
//Nombre: Valeria Bijarra
//Legajo: VINF013412
//DNI: 32494363
import java.sql.Connection;
import java.sql.SQLException; 
import java.sql.DriverManager;
 

public class Conectar {
	static String db = "inc"; 
	static String login = "root"; 
	static String password = "Vb0336305"; 
	static String url = "jdbc:mysql://localhost:3306/" + db +  "?autoReconnect=true&useSSL=false"; 
	
	Connection  connection = null; 
	
	public Conectar() {
		try { 
			connection=DriverManager.getConnection(url, login, password); 
			if (connection != null){ 
			}
			}catch (SQLException ex) {System.out.println(ex.getMessage()); 
		} catch (Exception ex) {System.out.println(ex.getMessage());}
	} 
	
	public Connection getConnection() {
		return connection; 
	}
	 
	public void desconectar() {
		try{
			connection.close();
		} catch (Exception ex) {System.out.println(ex.getMessage());}
	}
}
