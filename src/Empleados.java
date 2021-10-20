import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Empleados {
    private final String TABLE = "EMP";

    private ArrayList<Empleado> empleados;

    public Empleados() {
        Conectar conexion = new Conectar();
        this.empleados = new ArrayList<Empleado>();
        try {
            Statement s = conexion.getConect().createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS EMP (EMPNO DECIMAL(4,0) NOT NULL, ENAME VARCHAR(10) NOT NULL, JOB VARCHAR(9), MGR DECIMAL (4, 0) REFERENCES EMP(EMPNO), HIREDATE DATE, SAL DECIMAL(7,2), COMM DECIMAL(7,2), DEPTNO DECIMAL(2,0) REFERENCES DEPT(DEPTNO), PRIMARY KEY(EMPNO));";
            s.execute(sql);
            sql = "SELECT * FROM EMP;";
            ResultSet resultSet = s.executeQuery(sql);
            while (resultSet.next()) {
                empleados.add(new Empleado(resultSet.getInt(1), resultSet.getInt(4), resultSet.getInt(8),
                        resultSet.getString(2), resultSet.getString(3), resultSet.getFloat(6), resultSet.getFloat(7),
                        LocalDate.parse(resultSet.getDate(5).toString())));
            }
        } catch (IndexOutOfBoundsException | SQLException e) {
            if (e instanceof SQLException) {
                SQLException exception = (SQLException) e;
                System.out.println(exception.getSQLState());
            } else {
                System.out.println(e.getStackTrace());
            }
        }
        conexion.close();
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    public boolean insertEmp(String nombre) {
        Conectar conexion = new Conectar();
        try {
            Random empno = new Random();
            Statement statement = conexion.getConect().createStatement();
            String sql = "INSERT INTO " + TABLE + " VALUES(" + (empno.nextInt(4000) + 2000) + ", '" + nombre
                    + "', 'SALESMAN', 7782, '2021-10-20', 7742, 22, 30);";
            int result = statement.executeUpdate(sql);
            conexion.close();
            if (result == 1)
                return true;
            else
                return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void deleteEmp(String nombre){
        
    }
}
