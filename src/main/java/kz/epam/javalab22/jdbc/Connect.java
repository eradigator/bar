package kz.epam.javalab22.jdbc;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Connect {

    private final static String DB_URL = "jdbc:postgresql://127.0.0.1:5432/bar";
    private final static String DB_USER = "postgres";
    private final static String DB_PASSWORD = "pass123";

    public static void main(String[] args) {

       try {
            createDbUserTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        insertIntoDbuser();

        //removeDbUserData();

        getDbUserData();

        updateDbUserDate();

        getDbUserData();

    }

    private static String getCurrentTimeStamp() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/mm/dd hh:mm:ss");
        Date today = new Date();
        return format.format(today);
    }


    private static Connection getDBConnection() {

        Connection dbConnection;

        try {
            dbConnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("------ Подключение установлено ------");
            return dbConnection;
        } catch (SQLException e) {
            System.out.println("------ Подключение НЕ установлено ------");
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static void createDbUserTable() throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;

        String createTableSQL = "CREATE TABLE DBUSER("
                + "USER_ID NUMERIC(5) NOT NULL, "
                + "USERNAME VARCHAR(20) NOT NULL, "
                + "CREATED_BY VARCHAR(20) NOT NULL, "
                + "CREATED_DATE DATE NOT NULL, "
                + "PRIMARY KEY (USER_ID) "
                + ")";

        try {
            dbConnection = getDBConnection();
            if (dbConnection != null) {
                statement = dbConnection.createStatement();
            }

            // выполнить SQL запрос
            if (statement != null) {
                statement.execute(createTableSQL);
            }
            System.out.println("Table \"mydbuser\" is created!");

            dbConnection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    private static void insertIntoDbuser() {

        Connection dbConnection;
        Statement statement = null;

        dbConnection = getDBConnection();

        try {
            if (dbConnection != null) {
                statement = dbConnection.createStatement();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String insertTableSQL = "INSERT INTO DBUSER"
                + "(USER_ID, USERNAME, CREATED_BY, CREATED_DATE) " + "VALUES"
                + "(2,'erad','system', "
                + "to_date('" + getCurrentTimeStamp() + "', 'yyyy/mm/dd hh24:mi:ss'))";

        try {
            if (statement != null) {
                statement.executeUpdate(insertTableSQL);
            }

            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void getDbUserData() {
        String selectTableSQL = "SELECT USER_ID, USERNAME from DBUSER";

        Connection dbConnection;
        Statement statement = null;

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            // выбираем данные с БД
            ResultSet rs = statement.executeQuery(selectTableSQL);

            // И если что то было получено то цикл while сработает
            while (rs.next()) {
                String userid = rs.getString("USER_ID");
                String username = rs.getString("USERNAME");

                System.out.println("userid : " + userid);
                System.out.println("username : " + username);
            }

            dbConnection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void removeDbUserData() {

        String deleteTableSQL = "DELETE FROM DBUSER WHERE USER_ID = 1";

        Connection dbConnection;
        Statement statement;

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            // выполняем запрос delete SQL
            statement.execute(deleteTableSQL);
            System.out.println("Record is deleted from DBUSER table!");
            dbConnection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void updateDbUserDate() {
        String updateTableSQL = "UPDATE DBUSER SET USERNAME = 'erad_new' WHERE USER_ID = 2";

        Connection dbConnection;
        Statement statement;

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            // выполняем запрос update SQL
            statement.execute(updateTableSQL);

            System.out.println("Record is updated to DBUSER table!");
            dbConnection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
