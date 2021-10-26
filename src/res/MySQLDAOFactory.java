package res;

import java.sql.Connection;
import model.EmpleadoDAO;
import java.sql.SQLException;

public class MySQLDAOFactory extends DAOFactory {
    static final String url = "jdbc:mysql:///carlos";
    static final String user = "root";
    static final String password = "T3ng0gafas";
    static BasicConnectionPool bcp;

    public MySQLDAOFactory() {
        try {
            bcp = BasicConnectionPool.create(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return bcp.getConnection();
    }

    public boolean releaseConnection(Connection connection) {
        return bcp.releaseConnection(connection);
    }

    @Override
    public int getSize() {
        return bcp.getSize();
    }

    // add getUser, getURL....
    @Override
    public void shutDown() throws SQLException {
        bcp.shutdown();
    }

    @Override
    public EmpleadoDAO getEmpleadoDAO() {
        return new EmpleadoDAO();
    }

    /*
     * @Override public DepartamentoDAO getDepartamentoDAO(){ return new
     * DepartamentoDAO(); }
     * 
     * @Override public ProyectoDAO getProyectoDAO(){ return new ProyectoDAO(); }
     */
}
