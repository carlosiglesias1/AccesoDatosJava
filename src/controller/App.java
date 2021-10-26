package controller;

import java.util.ArrayList;
import java.util.List;

import model.Empleado;
import model.EmpleadoDAO;
import res.DAOFactory;
import view.Vista;

public class App {
    public static void main(String[] args) {

        List<Empleado> empleados = new ArrayList<>();

        // Create factory
        DAOFactory mySQLFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
        // Create dAO
        EmpleadoDAO empDAO = mySQLFactory.getEmpleadoDAO();
        // CargarEmpleados
        try {
            empleados = empDAO.getAll(mySQLFactory.getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Inicializar todo
        // ...
        // Vista
        Vista v = new Vista();
        int opcion = v.mostrarMenu();
        switch (opcion) {
        case 1:
            String output = new String();
            for (int i = 0; i < empleados.size(); i++) {
                output += " empno=" + empleados.get(i).getEmpno() + ",ename=" + empleados.get(i).getEname() + ",deptno="
                        + empleados.get(i).getDeptno() + "\n";
            }
            v.showMessage(output);
            break;
        case 2:
            break;
        default:
            System.out.println("A ver a que andamos");
        }
    }
}
