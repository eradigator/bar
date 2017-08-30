package kz.epam.javalab22.bar.command;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.CocktailDao;
import kz.epam.javalab22.bar.dao.MixDao;
import kz.epam.javalab22.bar.entity.BuildMethod;
import kz.epam.javalab22.bar.entity.Cocktail;
import kz.epam.javalab22.bar.entity.Glass;
import kz.epam.javalab22.bar.entity.Mix;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.runner.Runner;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
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


        /*-----------------------------------------------------------------------*/

        InputStream inputStream = null;
        int length=0;

        try {
            Part filePart = request.getPart("image");

            if (filePart != null) {
                System.out.println(filePart.getName());
                System.out.println(filePart.getSize());
                length = (int) filePart.getSize();
                System.out.println(filePart.getContentType());

                inputStream = filePart.getInputStream();
            }

        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }

        Runner.addImg(inputStream,length);

        try {
            assert inputStream != null;
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*--------------------------------------------------------------------------*/


        Map<Integer, Double> mix = new LinkedHashMap<>();
        for (int i = 0; i < components.length; i++) {
            mix.put(Integer.parseInt(components[i]), Double.parseDouble(amounts[i]));
        }

        cocktail = new Cocktail(name, buildMethod, glass);
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
