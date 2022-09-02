package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        Connection userConnection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres", "sql2022");
        return userConnection;
    }
}
