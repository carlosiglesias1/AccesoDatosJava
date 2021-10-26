package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO implements DAO<Empleado> {
    
    public EmpleadoDAO(){
        
    }
    
    public Empleado get(long id) {
        return new Empleado();
    }

    @Override
    public List<Empleado> getAll(Connection conn) {
        List<Empleado> lista = null;
        try (Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = s.executeQuery("SELECT * FROM EMP;");
            int totalRows = 0;
            rs.last();
            totalRows = rs.getRow();
            rs.beforeFirst();
            lista = new ArrayList<>(totalRows);
            while (rs.next()) {
                int empno = rs.getInt(1);
                String ename = rs.getString(2);
                String job = rs.getString(3);
                int mgr = rs.getInt(4);
                LocalDate hiredate = LocalDate.parse(rs.getDate(5).toString());
                float sal = rs.getFloat(6);
                float comm = rs.getFloat(7);
                int deptno = rs.getInt(8);
                lista.add(new Empleado(empno, mgr, deptno, ename, job, sal, comm, hiredate));
            }
        } catch (SQLException | DateTimeParseException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
