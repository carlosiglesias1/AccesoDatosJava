import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Departamentos {
    private ArrayList<Departamento> departamentos;

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

    public boolean insertDept(){
        
    }
}
