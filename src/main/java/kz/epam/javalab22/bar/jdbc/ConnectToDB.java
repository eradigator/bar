package kz.epam.javalab22.bar.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToDB {

    private final static String DB_URL = "jdbc:postgresql://127.0.0.1:5432/postgres";
    private final static String USER = "postgres";
    private final static String PASS = "pass123";

    public static void main(String[] args) {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        if (null != connection) {
            System.out.println("------ Подключение установлено ------");
        } else {
            System.out.println("------ Подключение НЕ установлено ------");
        }
    }
}
