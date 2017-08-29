package kz.epam.javalab22.bar.runner;

import kz.epam.javalab22.bar.pool.ConnectionPool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by erad on 10.07.2017.
 */


public class Runner {

    public static void main(String[] args) throws IOException {

        Connection conn = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;

        /*File file = new File("src/main/webapp/images/cocktails/noimage.jpg");
        FileInputStream fis = new FileInputStream(file);

        try {
            ps = conn.prepareStatement("INSERT INTO images VALUES (?, ?)");
            ps.setString(1, file.getName());
            ps.setBinaryStream(2, fis, (int)file.length());
            ps.executeUpdate();
            ps.close();
            fis.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }*/

        byte[] imgBytes=null;

        try {
            ps = conn.prepareStatement("SELECT img FROM images WHERE imgname = ?");
            ps.setString(1, "noimage.jpg");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                imgBytes = rs.getBytes(1);
                // use the data in some way here
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ConnectionPool.getInstance().returnConnection(conn);

        getImg(imgBytes);
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
}
