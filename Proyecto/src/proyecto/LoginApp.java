/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto;

/**
 *
 * @author cesar-rodriguez
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginApp {
    // Datos de conexión a la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/proyecto";
    private static final String USER = "root";
    private static final String PASSWORD = "Goli@t2014";

    // Método para obtener conexión
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // Método para verificar credenciales
    public static boolean verificarCredenciales(String usuario, String contraseña) {
        String sql = "SELECT * FROM user WHERE user = ? AND password = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, usuario);
            pstmt.setString(2, contraseña);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // Si hay un resultado, las credenciales son correctas
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese usuario: ");
        String usuario = scanner.nextLine();

        System.out.print("Ingrese contraseña: ");
        String contraseña = scanner.nextLine();

        if (verificarCredenciales(usuario, contraseña)) {
            System.out.println("✅ Inicio de sesión exitoso. ¡Bienvenido " + usuario + "!");
        } else {
            System.out.println("❌ Usuario o contraseña incorrectos.");
        }
        
        scanner.close();
    }
}

