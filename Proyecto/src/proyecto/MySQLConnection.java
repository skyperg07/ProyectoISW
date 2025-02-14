/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto;

/**
 *
 * @author cesar-rodriguez
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    // Datos de conexión
    private static final String URL = "jdbc:mysql://localhost:3306/proyecto";
    private static final String USER = "root";
    private static final String PASSWORD = "Goli@t2014";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Cargar el driver de MySQL (opcional en versiones modernas de Java)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se encontró el driver de MySQL.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos.");
            e.printStackTrace();
        }
        return connection;
    }

    public static void main(String[] args) {
        // Probar la conexión
        Connection conn = getConnection();
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
