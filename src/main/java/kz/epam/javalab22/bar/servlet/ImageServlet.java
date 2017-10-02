package kz.epam.javalab22.bar.servlet;

import kz.epam.javalab22.bar.connectionpool.ConnectionPool;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.ImageDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;

/**
 * @author vten
 */
public class ImageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection connection = ConnectionPool.getInstance().getConnection();
        ImageDao imageDao = new ImageDao(connection);
        int id = Integer.parseInt(request.getParameter(Const.PARAM_ID));
        byte[] bytes = imageDao.getImage(id);
        ConnectionPool.getInstance().returnConnection(connection);

        response.setContentType(Const.RESPONSE_CONTENT_TYPE_IMAGE);
        OutputStream o = response.getOutputStream();
        o.write(bytes);
        o.flush();
        o.close();
    }
}
