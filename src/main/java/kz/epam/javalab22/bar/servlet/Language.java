package kz.epam.javalab22.bar.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Language extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lang = request.getParameter("command");
        switch (lang) {
            case "EN":
                request.getSession().setAttribute("locale","en_US");
                break;
            case "RU":
                request.getSession().setAttribute("locale","ru_RU");
                break;
        }
        response.sendRedirect("http://localhost:8080/bar/");

    }
}
