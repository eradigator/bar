package kz.epam.javalab22.bar.command.impl;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.CocktailDao;
import kz.epam.javalab22.bar.logic.DeleteCocktailLogic;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class RemoveCocktailCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(RemoveCocktailCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        String page;

        String name = request.getParameter("cocktailToDelete");

        if (new DeleteCocktailLogic().deleteCocktail(name)) {
            request.setAttribute("removeCocktailResult", "Коктейль удален");
            log.info("Коктейль: " + name + " удален");
        } else {
            request.setAttribute("removeCocktailResult", "Коктейль не удален");
        }

        page = ConfigurationManager.getProperty(Const.PAGE_COCKTAIL_MANAGER);
        return page;
    }
}
