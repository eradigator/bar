package kz.epam.javalab22.bar.pool;

import kz.epam.javalab22.bar.manager.DatabaseManager;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;

public class ConnectionPool {

    private static final Logger log = org.apache.log4j.Logger.getLogger(ConnectionPool.class);
    private static final String DB_DRIVER_NAME = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/bar";
    private static final String DB_USER = "postgres";
    private static final String DB_PASS = "pass123";
    private static int CONNECTIONS_COUNT = 10;
    private static ConnectionPool instance;
    private static ArrayBlockingQueue<Connection> connections = new ArrayBlockingQueue<>(CONNECTIONS_COUNT);


    private ConnectionPool() {
        initialize();
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    private void initialize() {
        while (connections.size() < CONNECTIONS_COUNT) {
            connections.add(createNewConnection());
        }
    }

    private Connection createNewConnection() {
        try {
            Class.forName(DB_DRIVER_NAME);
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            log.info("new connection created");
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            log.info("SQLException on new connection creation");
        }
        return null;
    }

    public Connection getConnection() {
        Connection connection = null;
        if (!connections.isEmpty()) {
            try {
                connection = connections.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return connection;
        } else {
            CONNECTIONS_COUNT++;
            return createNewConnection();
        }

    }

    public synchronized void returnConnection(Connection connection) {
        if (connection != null && connections.size() < CONNECTIONS_COUNT) {
            connections.add(connection);
        }
    }

    public synchronized void closeConnections() {
        for (Connection connection : connections) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        connections.clear();
    }

}
