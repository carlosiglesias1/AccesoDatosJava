import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Random;

public class Empleado {
    private final String TABLE = "EMP";

    public Empleado(int mgr, int deptno, String ename, String job, float sal, float comm, LocalDate hiredDate) {
        Conectar conexion = new Conectar();
        try {
            Statement s = conexion.getConect().createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE
                    + " (EMPNO DECIMAL(4,0) NOT NULL, ENAME VARCHAR(10) NOT NULL, JOB VARCHAR(9), MGR DECIMAL (4, 0) REFERENCES EMP(EMPNO), HIREDATE DATE, SAL DECIMAL(7,2), COMM DECIMAL(7,2), DEPTNO DECIMAL(2,0) REFERENCES DEPT(DEPTNO), PRIMARY KEY(EMPNO));";
            s.execute(sql);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getSQLState() + " " + e.getNextException() + " " + e.getMessage());
        }
    }

    public String getTABLE() {
        return TABLE;
    }

    public void getEmp() {
        Conectar conexion = new Conectar();
        try {
            Statement s = conexion.getConect().createStatement();
            String sql = "SELECT * FROM EMP;";
            ResultSet resultSet = s.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));
                resultSet.getArray(1);
            }
        } catch (SQLException e) {
            e.getSQLState();
        }
        conexion.close();
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

}
