package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public Connection getConnection() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/meu_gestor_financeiro?useSSL=false", "root", "root");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
