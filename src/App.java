import static java.lang.System.out;

import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        // register Oracle thin driver with DriverManager service
        // It is optional for JDBC4.x version
        try {
            Empleados emps = new Empleados();
            Departamentos depts = new Departamentos();
            for (Empleado emp : emps.getEmpleados()) {
                out.println(emp.getEmpno() + " " + emp.getEname());
            }

            out.println();

            for (Departamento dept : depts.getDepartamentos()) {
                out.println(dept.toString());
            }

            emps.insertEmp("Carlos");
            for (Empleado emp : emps.getEmpleados()) {
                out.println(emp.getEmpno() + " " + emp.getEname());
            }

            out.println("Rows affected: " + emps.updateEmp("Carlos", "Chencho"));

            out.println("Rows affected: " + emps.deleteEmp("Chencho"));
            for (Empleado emp : emps.getEmpleados()) {
                out.println(emp.getEmpno() + " " + emp.getEname());
            }
            Conectar.getConect().close();
        } catch (SQLException e) {
            out.println(e.getSQLState());
        } catch (IndexOutOfBoundsException e) {
            out.println(e.getMessage());
        }
    }
}
