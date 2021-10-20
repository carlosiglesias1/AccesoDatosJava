import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conectar {
    private Connection conect;

    public Conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // variables
            final String url = "jdbc:mysql:///carlos";
            final String user = "root";
            final String password = "YourPass";
            // establish the connection

            this.conect = DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getStackTrace());
        }
    }

    public Connection getConect() {
        return conect;
    }

    public void close() {
        try {
            this.conect.close();    
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
}
