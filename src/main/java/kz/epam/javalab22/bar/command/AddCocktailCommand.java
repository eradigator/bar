package kz.epam.javalab22.bar.command;

import kz.epam.javalab22.bar.manager.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddCocktailCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(AddCocktailCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        String page;

        String name = request.getParameter("name");
        String component = request.getParameter("component");
        String buildMethod = request.getParameter("buildMethod");
        String glass = request.getParameter("glass");

        System.out.println(name + " " + component + " " + buildMethod + " " + glass);

        request.setAttribute("addCocktailResult", "Коктейль добавлен");
        //log.info("Коктейль: " + name + " добавлен");

        /*} else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
        }*/

        page = ConfigurationManager.getProperty("path.page.cocktailManager");
        return page;
    }
}
