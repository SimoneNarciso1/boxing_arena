package com.example.boxingarena.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbConnection {

    private DbConnection() {
        throw new IllegalStateException("Utility class");
    }

    private static Connection connection;

    static {
        try (InputStream input = new FileInputStream("src/main/java/com/example/boxingarena/database/config.properties")) {

            Properties properties = new Properties();
            properties.load(input);

            String connectionUrl = properties.getProperty("dbUrl");
            String user = properties.getProperty("username");
            String pass = properties.getProperty("password");

            connection = DriverManager.getConnection(connectionUrl, user, pass);
        } catch (IOException | SQLException e) {
            Logger logger = Logger.getLogger(DbConnection.class.getName());
            logger.log(Level.WARNING, e.getMessage());
        }
    }

    public static Connection getConnection() {
        return connection;
    }

}
