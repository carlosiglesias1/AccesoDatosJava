import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

public class Departamentos {
    private ArrayList<Departamento> departamentos;
    private final String TABLE = "DEPT";

    public Departamentos() {
        Conectar conexion = new Conectar();
        this.departamentos = new ArrayList<Departamento>();
        try {
            Statement s = conexion.getConect().createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS DEPT (DEPTNO DECIMAL (2,0), DNAME VARCHAR(14), LOC VARCHAR(13));";
            s.execute(sql);
            sql = "SELECT * FROM DEPT;";
            ResultSet resultSet = s.executeQuery(sql);
            while (resultSet.next()) {
                departamentos
                        .add(new Departamento(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
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

    public ArrayList<Departamento> getDepartamentos() {
        return departamentos;
    }

    public boolean insertDept(String deptname) throws SQLException, SQLTimeoutException {
        Conectar conexion = new Conectar();
        Random random = new Random();
        int deptno = random.nextInt(60) + 40;
        this.departamentos.add(new Departamento(deptno, deptname, "A CORUÑA"));
        Statement s = conexion.getConect().createStatement();
        String sql = "INSERT INTO " + this.TABLE + "VALUES (" + Integer.toString(deptno) + ", '" + deptname
                + "', 'A CORUÑA');";
        int rowCount = s.executeUpdate(sql);
        conexion.close();
        if (rowCount == 1) {
            return true;
        } else {
            return false;
        }
    }

    public int deleteDept(String nombre) throws SQLException, IndexOutOfBoundsException {
        Conectar conexion = new Conectar();
        Statement statement = conexion.getConect().createStatement();
        String sql = "DELETE FROM " + TABLE + " WHERE ENAME = '" + nombre + "';";
        for (Departamento departamento : departamentos) {
            if (departamento.getDname().equals(nombre)) {
                if (!departamentos.remove(departamento))
                    throw new IndexOutOfBoundsException();
                else
                    break;
            }
        }
        int r = statement.executeUpdate(sql);
        conexion.close();
        return r;
    }

    public int updateDept(String oldName, String newName) throws SQLException {
        Conectar conexion = new Conectar();
        Statement statement = conexion.getConect().createStatement();
        String sql = "UPDATE " + TABLE + " SET NOMBRE = " + newName + " WHERE ENAME = '" + oldName + "';";
        for (Departamento departamento : departamentos) {
            if (departamento.getDname().equals(oldName)) {
                departamento.setDname(newName);
            }
        }
        int r = statement.executeUpdate(sql);
        conexion.close();
        return r;
    }
}
