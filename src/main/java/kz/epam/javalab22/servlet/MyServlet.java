package kz.epam.javalab22.servlet;

import kz.epam.javalab22.bar.entity.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        final String COCKTAIL_NAME_WHITE_RUSSIAN = "Белый Русский";
        Cocktail whiteRussian = new Cocktail(COCKTAIL_NAME_WHITE_RUSSIAN);
        whiteRussian.setBuildMethod(BuildMethod.BUILD);
        whiteRussian.setGlass(Glass.OLD_FASHIONED);

        Component vodka = new AlcoholicComponent();
        vodka.setStrength(40);
        whiteRussian.addComponent(vodka,25);

        Component kalua = new AlcoholicComponent();
        kalua.setStrength(20);
        whiteRussian.addComponent(kalua,25);

        Component cream = new NonAlcoholicComponent();
        whiteRussian.addComponent(cream,30);


        out.write("HA-HA-HA! Servlet 3 web.xml example configuration" +
                whiteRussian);
    }
}
