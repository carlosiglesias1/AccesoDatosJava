import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Empleados {
    private static final String TABLE = "EMP";
    private Random random = new Random();
    private ArrayList<Empleado> empList;

    public Empleados() {
        Connection conexion = Conectar.getConect();
        this.empList = new ArrayList<>();
        try (Statement s = conexion.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS EMP (EMPNO DECIMAL(4,0) NOT NULL, ENAME VARCHAR(10) NOT NULL, JOB VARCHAR(9), MGR DECIMAL (4, 0) REFERENCES EMP(EMPNO), HIREDATE DATE, SAL DECIMAL(7,2), COMM DECIMAL(7,2), DEPTNO DECIMAL(2,0) REFERENCES DEPT(DEPTNO), PRIMARY KEY(EMPNO));";
            s.execute(sql);
            sql = "SELECT * FROM EMP;";
            ResultSet resultSet = s.executeQuery(sql);
            while (resultSet.next()) {
                empList.add(new Empleado(resultSet.getInt(1), resultSet.getInt(4), resultSet.getInt(8),
                        resultSet.getString(2), resultSet.getString(3), resultSet.getFloat(6), resultSet.getFloat(7),
                        LocalDate.parse(resultSet.getDate(5).toString())));
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getStackTrace());
        } catch (SQLException ex) {
            System.out.println(ex.getSQLState());
        }
    }

    public List<Empleado> getEmpleados() {
        return empList;
    }

    public boolean insertEmp(String nombre) throws SQLException {
        Connection conexion = Conectar.getConect();
        Statement statement = conexion.createStatement();
        int empno = (random.nextInt(4000) + 2000);
        this.empList.add(new Empleado(empno, 7782, 30, nombre, "SALESMAN", 7742F, 22F, LocalDate.parse("2020-10-20")));
        String sql = "INSERT INTO " + TABLE + " VALUES(" + empno + ", '" + nombre
                + "', 'SALESMAN', 7782, '2021-10-20', 7742, 22, 30);";
        int result = statement.executeUpdate(sql);
        if (result == 1)
            return true;
        else
            throw new SQLException();
    }

    public int deleteEmp(String nombre) throws SQLException, IndexOutOfBoundsException {
        Connection conexion = Conectar.getConect();
        Statement statement = conexion.createStatement();
        String sql = "DELETE FROM " + TABLE + " WHERE ENAME = '" + nombre + "';";
        for (Empleado empleado : empList) {
            if (empleado.getEname().equals(nombre)) {
                if (!empList.remove(empleado))
                    throw new IndexOutOfBoundsException();
                else
                    break;
            }
        }
        return statement.executeUpdate(sql);
    }

    public int updateEmp(String oldName, String newName) throws SQLException {
        Connection conexion = Conectar.getConect();
        Statement statement = conexion.createStatement();
        String sql = "UPDATE " + TABLE + " SET ENAME = '" + newName + "' WHERE ENAME = '" + oldName + "';";
        for (Empleado empleado : empList) {
            if (empleado.getEname().equals(oldName)) {
                empleado.setEname(newName);
            }
        }
        return statement.executeUpdate(sql);
    }
}
