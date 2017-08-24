package kz.epam.javalab22.bar.command;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.CocktailDao;
import kz.epam.javalab22.bar.entity.BuildMethod;
import kz.epam.javalab22.bar.entity.Cocktail;
import kz.epam.javalab22.bar.entity.Glass;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddCocktailCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(AddCocktailCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        String page;
        Cocktail cocktail;

        String name = request.getParameter("name");
        //String component = request.getParameter("component");
        BuildMethod buildMethod = BuildMethod.valueOf(request.getParameter("buildMethod"));
        Glass glass = Glass.valueOf(request.getParameter("glass"));

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
