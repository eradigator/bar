package kz.epam.javalab22.bar.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;

public class ConnectionPool {

    private static final String DB_DRIVER_NAME = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/bar";
    private static final String DB_USER = "postgres";
    private static final String DB_PASS = "pass123";
    private static final int MAX_CONNECTIONS = 10;

    private static ConnectionPool instance;
    private static ArrayBlockingQueue<Connection> connections = new ArrayBlockingQueue<>(MAX_CONNECTIONS);

    private ConnectionPool() {
        initialize();
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public void initialize() {
        while (connections.size() < MAX_CONNECTIONS) {
            connections.add(createNewConnectionForPool());
        }
    }

    private Connection createNewConnectionForPool() {
        try {
            Class.forName(DB_DRIVER_NAME);
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = connections.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public synchronized void returnConnection(Connection connection) {
        connections.add(connection);
    }

    public synchronized void closeConnections(){
        for(Connection connection : connections){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
