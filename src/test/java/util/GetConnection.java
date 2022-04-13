package util;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConnection {
    private final static ISettingsFile connection = new JsonSettingsFile("connection.json");

    private final static String url = connection.getValue("/url").toString();
    private final static String user = connection.getValue("/user").toString();
    private final static String password = connection.getValue("/password").toString();

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

}
