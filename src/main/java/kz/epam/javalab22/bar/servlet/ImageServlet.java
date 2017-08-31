package kz.epam.javalab22.bar.servlet;

import kz.epam.javalab22.bar.dao.ImageDao;
import kz.epam.javalab22.bar.pool.ConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;

/**
 * Created by vten on 31.08.2017.
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
        int id = Integer.parseInt(request.getParameter("id"));
        byte[] bytes = imageDao.getImage(id);
        ConnectionPool.getInstance().returnConnection(connection);

        response.setContentType("image/jpeg");
        OutputStream o = response.getOutputStream();
        o.write(bytes);
        o.flush();
        o.close();
    }
}
