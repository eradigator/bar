package kz.epam.javalab22.bar.command.impl;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.CocktailDao;
import kz.epam.javalab22.bar.dao.ImageDao;
import kz.epam.javalab22.bar.dao.MixDao;
import kz.epam.javalab22.bar.entity.*;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.pool.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;

public class AddCocktailCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(AddCocktailCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        String page;
        Cocktail cocktail;

        String name = request.getParameter("name");
        BuildMethod buildMethod = BuildMethod.valueOf(request.getParameter("buildMethod"));
        Glass glass = Glass.valueOf(request.getParameter("glass"));

        String[] components = request.getParameterValues("ingredient");
        String[] amounts = request.getParameterValues("amountOfIngredient");

        Connection connection = ConnectionPool.getInstance().getConnection();

        /*-----------------------------------------------------------------------*/

        InputStream inputStream = null;
        long length=0;

        try {
            Part filePart = request.getPart("image");

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

        /*--------------------------------------------------------------------------*/


        Map<Integer, Double> mix = new LinkedHashMap<>();
        for (int i = 0; i < components.length; i++) {
            mix.put(Integer.parseInt(components[i]), Double.parseDouble(amounts[i]));
        }

        cocktail = new Cocktail(name, buildMethod, glass, image);
        new CocktailDao().create(cocktail);

        int cocktailId = new CocktailDao().getId(name);

        Mix cocktailMix = new Mix(mix);
        new MixDao().add(cocktailMix, cocktailId);

        request.setAttribute("addCocktailResult", "Коктейль добавлен");
        log.info("Коктейль: " + name + " добавлен");

        /*} else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
        }*/

        page = ConfigurationManager.getProperty(Const.PAGE_COCKTAIL_MANAGER);
        return page;
    }
}
