package kz.epam.javalab22.bar.command.impl.cocktail;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.impl.page.PageCocktailManagerCommand;
import kz.epam.javalab22.bar.logic.CocktailLogic;
import kz.epam.javalab22.bar.manager.MessageManager;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

public class AddCocktailCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(AddCocktailCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        ReqWrapper reqWrapper = new ReqWrapper(request);
        CocktailLogic cocktailLogic = new CocktailLogic(reqWrapper);

        if (cocktailLogic.addCocktail()) {
            request.setAttribute("addCocktailResult", "Коктейль добавлен");
            log.info("Коктейль: " + reqWrapper.getParam("name") + " добавлен");
        } else {
            request.setAttribute("addCocktailResult", MessageManager.getProperty("message.loginerror"));
        }

        return new PageCocktailManagerCommand().execute(request);
    }
}
