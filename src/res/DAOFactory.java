package res;

import java.sql.Connection;
import java.sql.SQLException;

import model.EmpleadoDAO;

public abstract class DAOFactory {
    public static final int MYSQL = 1;

    public abstract Connection getConnection() throws Exception;

    public abstract EmpleadoDAO getEmpleadoDAO();

    /*
     * public abstract DepartamentoDAO getDepartamentoDAO(); public abstract
     * ProyectoDAO getProyectoDAO(); public abstract FamiliarDAO getFamiliarDAO();
     */
    public static DAOFactory getDaoFactory(int whichFactory) {
        switch (whichFactory) {
        case MYSQL:
            return new MySQLDAOFactory();
        default:
            return null;
        }
    }

    public int getSize() {
        return 0;
    }

    public void shutDown() throws SQLException {

    }

}
