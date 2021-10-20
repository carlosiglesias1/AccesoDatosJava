public class App {
    public static void main(String[] args) throws Exception {
        // register Oracle thin driver with DriverManager service
        // It is optional for JDBC4.x version
        Empleados emps = new Empleados();
        Departamentos depts = new Departamentos();
        for (Empleado emp : emps.getEmpleados()) {
            System.out.println(emp.getEmpno() + " " + emp.getEname());
        }

        System.out.println();

        for (Departamento dept : depts.getDepartamentos()) {
            System.out.println(dept.toString());
        }
        /*
         * if (emp.insertEmp("Carlos")) { System.out.println("Yupiii"); } else {
         * System.out.println("Doh"); }
         */
    }
}
