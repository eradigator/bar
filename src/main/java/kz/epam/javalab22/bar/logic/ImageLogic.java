package kz.epam.javalab22.bar.logic;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.ImageDao;
import kz.epam.javalab22.bar.entity.*;
import kz.epam.javalab22.bar.connectionpool.ConnectionPool;
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

        InputStream inputStream = null;
        long length= Const.N_0;

        try {
            Part filePart = reqWrapper.getRequest().getPart(Const.PARAM_IMAGE);

            if (null != filePart) {
                length = filePart.getSize();
                inputStream = filePart.getInputStream();
            }

        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }

        Image image = new Image(inputStream,length);

        Connection connection = ConnectionPool.getInstance().getConnection();
        new ImageDao(connection).create(image);
        ConnectionPool.getInstance().returnConnection(connection);

        try {
            assert null != inputStream;
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

}
