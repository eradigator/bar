package kz.epam.javalab22.bar.pool;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class ConnectionPoolExample {
    private static ConnectionPoolExample instance;
    private final String DRIVER_NAME;
    private ArrayList<Connection> freeConnections = new ArrayList<Connection>();
    private String URL;
    private String user;
    private String password;
    private int maxConn;

    private ConnectionPoolExample(String DRIVER_NAME, String URL, String user, String password, int maxConn) {
        this.DRIVER_NAME = DRIVER_NAME;
        this.URL = URL;
        this.user = user;
        this.password = password;
        this.maxConn = maxConn;
        //loadDrivers();
    }

   /* private void loadDrivers() {
        try {
            Driver driver = (Driver) Class.forName(DRIVER_NAME).newInstance();
            DriverManager.registerDriver(driver);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Не найден или не зарегистрирован JDBC драйвер");
        }
    }*/

    static synchronized public ConnectionPoolExample getInstance(String DRIVER_NAME, String URL, String user, String password, int maxConn) {
        if (instance == null) {
            instance = new ConnectionPoolExample(DRIVER_NAME, URL, user, password, maxConn);
        }
        return instance;
    }

    public synchronized Connection getConnection() {
        Connection con = null;
        if (!freeConnections.isEmpty()) {
            con = (Connection) freeConnections.get(freeConnections.size() - 1);
            freeConnections.remove(con);
            try {
                if (con.isClosed()) {
                    con = getConnection();
                }
            } catch (SQLException e) {
                con = getConnection();
            }
        } else {
            con = newConnection();
        }
        return con;
    }

    private Connection newConnection() {
        Connection con = null;
        try {
            if (user == null) {
                con = DriverManager.getConnection(URL);
            } else {
                con = DriverManager.getConnection(URL,
                        user, password);
            }
            // "Created a new connection in pool „
        } catch (SQLException e) {
            // "Can't create a new connection for " + URL
            return null;
        }
        return con;
    }

    public synchronized void freeConnection(Connection con) {
        // Put the connection at the end of the List
        if ((con != null) && (freeConnections.size() <= maxConn)) {
            freeConnections.add(con);
        }
    }

    public synchronized void release() {
        Iterator allConnections = freeConnections.iterator();
        while (allConnections.hasNext()) {
            Connection con = (Connection) allConnections.next();
            try {
                con.close();
                // "Closed connection for pool „
            } catch (SQLException e) {
                // "Can't close connection for pool „
            }
        }
        freeConnections.clear();
    }


}
