package jm.task.core.jdbc.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    private static String URL = "jdbc:mysql://localhost/users";
    private static String USERNAME = "root";
    private static String PASSWORD = "root";

    public static void setPASSWORD(String PASSWORD) {
        Util.PASSWORD = PASSWORD;
    }

    public static void setUSERNAME(String USERNAME) {
        Util.USERNAME = USERNAME;
    }

    public static void setURL(String URL) {
        Util.URL = URL;
    }

    public static Connection getConnection() {
            try {
                return DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                System.out.println("Соединение не установлено");
            }
            return null;
        }
}
