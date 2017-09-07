package kz.epam.javalab22.bar.logic;

import kz.epam.javalab22.bar.dao.ImageDao;
import kz.epam.javalab22.bar.entity.*;
import kz.epam.javalab22.bar.pool.ConnectionPool;
import kz.epam.javalab22.bar.servlet.ReqWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

/**
 * Created by vten on 31.08.2017.
 */
public class ImageLogic {

    private ReqWrapper reqWrapper;

    public ImageLogic(ReqWrapper reqWrapper) {
        this.reqWrapper = reqWrapper;
    }

    public Image addImage() {

        Connection connection = ConnectionPool.getInstance().getConnection();

        InputStream inputStream = null;
        long length=0;

        try {
            Part filePart = reqWrapper.getRequest().getPart("image");

            if (filePart != null) {
                //System.out.println(filePart.getName());
                //System.out.println(filePart.getSize());
                length = filePart.getSize();
                //System.out.println(filePart.getContentType());
                inputStream = filePart.getInputStream();
            }

        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }

        Image image = new Image(inputStream,length);
        new ImageDao(connection).add(image);

        try {
            assert inputStream != null;
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ConnectionPool.getInstance().returnConnection(connection);

        return image;
    }

}
