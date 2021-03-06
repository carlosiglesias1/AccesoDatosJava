import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import res.Conectar;

public class Departamentos {
    private ArrayList<Departamento> deptList;
    private static final String TABLE = "DEPT";

    public Departamentos() {
        Connection conexion = Conectar.getConect();
        this.deptList = new ArrayList<>();
        try {
            Statement s = conexion.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS DEPT (DEPTNO DECIMAL (2,0), DNAME VARCHAR(14), LOC VARCHAR(13));";
            s.execute(sql);
            sql = "SELECT * FROM DEPT;";
            ResultSet resultSet = s.executeQuery(sql);
            while (resultSet.next()) {
                deptList.add(new Departamento(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
            }
        } catch (IndexOutOfBoundsException e) {
            System.err.println(e.getStackTrace());
        } catch (SQLException e) {
            System.err.println(e.getSQLState());
        }
    }

    public List<Departamento> getDepartamentos() {
        return deptList;
    }

    public boolean insertDept(String deptname) throws SQLException {
        Connection conexion = Conectar.getConect();
        Random random = new Random();
        int deptno = random.nextInt(60) + 40;
        this.deptList.add(new Departamento(deptno, deptname, "A CORUÑA"));
        Statement s = conexion.createStatement();
        String sql = "INSERT INTO " + Departamentos.TABLE + "VALUES (" + Integer.toString(deptno) + ", '" + deptname
                + "', 'A CORUÑA');";
        return Integer.toString(s.executeUpdate(sql)).equals("1");
    }

    public int deleteDept(String nombre) throws SQLException, IndexOutOfBoundsException {
        Connection conexion = Conectar.getConect();
        Statement statement = conexion.createStatement();
        String sql = "DELETE FROM " + TABLE + " WHERE ENAME = '" + nombre + "';";
        for (Departamento departamento : deptList) {
            if (departamento.getDname().equals(nombre)) {
                if (!deptList.remove(departamento))
                    throw new IndexOutOfBoundsException();
                else
                    break;
            }
        }
        return statement.executeUpdate(sql);
    }

    public int updateDept(String oldName, String newName) throws SQLException {
        Connection conexion = Conectar.getConect();
        Statement statement = conexion.createStatement();
        String sql = "UPDATE " + TABLE + " SET NOMBRE = " + newName + " WHERE ENAME = '" + oldName + "';";
        for (Departamento departamento : deptList) {
            if (departamento.getDname().equals(oldName)) {
                departamento.setDname(newName);
            }
        }
        return statement.executeUpdate(sql);
    }
}
