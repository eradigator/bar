package kz.epam.javalab22.bar.pool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        ConnectionPool connectionPool;
        Connection connection;
        Statement statement = null;
        ResultSet resultSet = null;
        try {

            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM dbuser");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " " +
                        resultSet.getString(2) + " " +
                        resultSet.getString(3) + " " +
                        resultSet.getDate(4));
            }

            connection.close();
            connectionPool.returnConnection(connection);
            connectionPool.closeConnections();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                if (resultSet != null) resultSet.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

}

