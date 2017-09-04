package kz.epam.javalab22.bar.runner;

import kz.epam.javalab22.bar.dao.CocktailDao;
import kz.epam.javalab22.bar.pool.ConnectionPool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by erad on 10.07.2017.
 */


public class Runner {

    public static void main(String[] args) throws IOException {

        Connection connection = ConnectionPool.getInstance().getConnection();
        System.out.println(new CocktailDao(connection).getId("b54"));
        ConnectionPool.getInstance().returnConnection(connection);

    }

    public static void getImg(byte[] bytes) {
        OutputStream out = null;

        try {
            try {
                out = new BufferedOutputStream(new FileOutputStream("src/main/webapp/images/cocktails/test.jpg"));
                out.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } finally {
            if (out != null) try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void addImg(InputStream inputStream, int length) {
        Connection conn = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;

        /*File file = new File("src/main/webapp/images/cocktails/noimage.jpg");
        FileInputStream fis = new FileInputStream(file);*/

        try {
            ps = conn.prepareStatement("INSERT INTO images VALUES (?, ?)");
            ps.setString(1, "test");
            //ps.setBinaryStream(2, fis, (int)file.length())
            ps.setBinaryStream(2, inputStream, length);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ConnectionPool.getInstance().returnConnection(conn);
    }
}
