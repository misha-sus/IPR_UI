package api.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static api.util.FileReadProperty.*;

/**
 * Класс для подключение к базе данных.
 */
public class JdbcConnection {
    private static final JdbcConnection INSTANCE = new JdbcConnection();

    private JdbcConnection() { }

    public static JdbcConnection getInstanceJdbcConnection() {
        return INSTANCE;
    }

    /**
     * Метод для Подключение к базе данных.
     */
    public Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName("org.postgresql.Driver");
            dbConnection = DriverManager.getConnection(HOST, LOGIN, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
}
