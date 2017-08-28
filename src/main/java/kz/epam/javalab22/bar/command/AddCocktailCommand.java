package kz.epam.javalab22.bar.command;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.CocktailDao;
import kz.epam.javalab22.bar.entity.BuildMethod;
import kz.epam.javalab22.bar.entity.Cocktail;
import kz.epam.javalab22.bar.entity.Glass;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
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

        Map<String,Integer> mix = new LinkedHashMap<>();
        for (int i=0; i<components.length; i++) {
            mix.put(components[i],Integer.parseInt(amounts[i]));
        }

        for (Map.Entry<String,Integer> pair : mix.entrySet()) {
            System.out.println(pair.getKey() +": "+ pair.getValue());
        }

        cocktail = new Cocktail(name, buildMethod, glass);
        new CocktailDao().create(cocktail);

        request.setAttribute("addCocktailResult", "Коктейль добавлен");
        log.info("Коктейль: " + name + " добавлен");

        /*} else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
        }*/

        page = ConfigurationManager.getProperty(Const.PAGE_COCKTAIL_MANAGER);
        return page;
    }
}
