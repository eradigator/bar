package kz.epam.javalab22.bar.connectionpool;

import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;

public class ConnectionPool {

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("dbConnection");
    private static final Logger log = org.apache.log4j.Logger.getLogger(ConnectionPool.class);

    private static int CONNECTIONS_COUNT = Integer.parseInt(resourceBundle.getString("connectionsCount"));
    private static ConnectionPool instance;
    private static ArrayBlockingQueue<Connection> connections = new ArrayBlockingQueue<>(CONNECTIONS_COUNT);


    private ConnectionPool() {
        initialize();
        log.info("Connection Pool has been initialized");
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
            Class.forName(resourceBundle.getString("driver"));
            Connection connection = DriverManager.getConnection(
                    resourceBundle.getString("url"),
                    resourceBundle.getString("user"),
                    resourceBundle.getString("pass"));
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
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
