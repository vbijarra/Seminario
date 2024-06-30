package Incidentes;
//Nombre: Valeria Bijarra
//Legajo: VINF013412
//DNI: 32494363
import java.sql.Date;

public class Errores extends Incidentes {
    String Nro_Error; 
    int Pantalla;
    int Aplicacion;  
	int Modulo; 
	int Proceso;  
	String Descripcion_Corta; 
	String Pasos;
	int Sec_Err; 

	public Errores(int Nro_Incidente, Date Fecha, int Usuario,	int Tipo_Problema, int Prioridad,int Estado,int Solucion,  
			String NumeroError, int Pantalla, int Aplicacion, int Modulo, int Procceso, String Descripcion_Corta, String Pasos, int Sec_Err) 
	{
        super(Nro_Incidente, Fecha, Usuario, Tipo_Problema, Prioridad, Estado, Solucion);
        this.Nro_Error=NumeroError; 
        this.Pantalla=Pantalla; 
        this.Aplicacion=Aplicacion; 
        this.Modulo=Modulo; 
        this.Proceso=Proceso;
        this.Descripcion_Corta=Descripcion_Corta; 
        this.Pasos=Pasos; 
        this.Sec_Err=Sec_Err; 
        
       
    }

	public String getNro_Error() {
		return Nro_Error;
	}

	public void setNro_Error(String nro_Error) {
		Nro_Error = nro_Error;
	}

	public int getPantalla() {
		return Pantalla;
	}

	public void setPantalla(int pantalla) {
		Pantalla = pantalla;
	}

	public int getAplicacion() {
		return Aplicacion;
	}

	public void setAplicacion(int aplicacion) {
		Aplicacion = aplicacion;
	}

	public int getModulo() {
		return Modulo;
	}

	public void setModulo(int modulo) {
		Modulo = modulo;
	}

	public int getProceso() {
		return Proceso;
	}

	public void setProceso(int proceso) {
		Proceso = proceso;
	}

	public String getDescripcion_Corta() {
		return Descripcion_Corta;
	}

	public void setDescripcion_Corta(String descripcion_Corta) {
		Descripcion_Corta = descripcion_Corta;
	}

	public String getPasos() {
		return Pasos;
	}

	public void setPasos(String pasos) {
		Pasos = pasos;
	}

	public int getSec_Err() {
		return Sec_Err;
	}

	public void setSec_Err(int sec_Err) {
		Sec_Err = sec_Err;
	}
	
	
	
}



 