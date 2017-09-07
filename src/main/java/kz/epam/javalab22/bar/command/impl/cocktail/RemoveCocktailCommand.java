package kz.epam.javalab22.bar.command.impl.cocktail;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.impl.page.PageCocktailManagerCommand;
import kz.epam.javalab22.bar.logic.CocktailLogic;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class RemoveCocktailCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(RemoveCocktailCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        ReqWrapper reqWrapper = new ReqWrapper(request);

        if (new CocktailLogic(reqWrapper).deleteCocktail()) {
            request.setAttribute("removeCocktailResult", "Коктейль удален");
            log.info("Коктейль: " + reqWrapper.getParam("cocktailToDelete") + " удален");
        } else {
            request.setAttribute("removeCocktailResult", "Коктейль не удален");
        }

        return new PageCocktailManagerCommand().execute(request);
    }
}
