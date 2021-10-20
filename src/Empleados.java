import java.util.ArrayList;

public class Empleados {
    private ArrayList<Empleado> empleados;

    public Empleados(){
        Conectar conexion = new Conectar();
        
        conexion.close();
    }
}
