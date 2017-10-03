package kz.epam.javalab22.bar.servlet;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.factory.ActionFactory;
import kz.epam.javalab22.bar.connectionpool.ConnectionPool;
import kz.epam.javalab22.bar.constant.Const;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig(maxFileSize = Const.N_10MB)
public class Controller extends HttpServlet {

    @Override
    public void init() throws ServletException {
        ConnectionPool.getInstance();
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().closeConnections();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(req);

        ReqWrapper reqWrapper = new ReqWrapper(req);
        String page = command.execute(reqWrapper);
        req = reqWrapper.getRequest();

        if (null != page) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(req, resp);
        }
    }
}
