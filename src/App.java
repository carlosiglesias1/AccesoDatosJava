
/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;*/
import java.time.LocalDate;

public class App {
    public static void main(String[] args) throws Exception {
        // register Oracle thin driver with DriverManager service
        // It is optional for JDBC4.x version
        Empleado emp = new Empleado(1, 2, "carlos", "salesman", 150, 20, LocalDate.parse("2020-02-05"));
        emp.getEmp();
        if (emp.insertEmp("Carlos")) {
            System.out.println("Yupiii");
        } else {
            System.out.println("Doh");
        }
    }
}
