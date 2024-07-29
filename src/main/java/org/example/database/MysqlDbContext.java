package org.example.database;

import org.example.config.AppConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlDbContext {

    private static String url;
    private static String user;
    private static String password;

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        AppConfig config = new AppConfig();
        url = config.getProperty("db.url");
        user = config.getProperty("db.username");
        password = config.getProperty("db.password");
        Connection conn = DriverManager.getConnection(url,user,password);
        return conn;
    }
}
