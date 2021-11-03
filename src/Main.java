import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static final String password = "1234";

    public static void main(String[] args) {
        // variables
        final String url = "jdbc:mysql:///carlos";
        final String user = "usuario";
        Random random = new Random();
        Scanner tecaldo = new Scanner(System.in);
        try (Connection conexion = DriverManager.getConnection(url, user, password)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // establish the connection
            PreparedStatement st = conexion.prepareStatement("SELECT * FROM EMP WHERE ENAME = ?;");
            st.setString(1, tecaldo.nextLine());
            ResultSet result = st.executeQuery();
            while (result.next()) {
                System.out.println(result.getString(2).toString());
            }
            
            //Ejercicio 15
            CallableStatement cs = conexion.prepareCall("{call getEMP(?)}");
            cs.setInt(1, Integer.parseInt(tecaldo.nextLine()));
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }

            // Ejercicio 16
            st = conexion.prepareStatement("INSERT INTO EMP (EMPNO, ENAME, MGR) VALUES (?, ?, ?)");
            for (int i = 0; i < Integer.parseInt(tecaldo.nextLine()); i++) {
                st.setInt(1, random.nextInt(5000) + 1500);
                st.setString(2, tecaldo.nextLine());
                st.setInt(3, 7902);
                st.addBatch();
            }
            st.executeBatch();
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        tecaldo.close();
    }
}
