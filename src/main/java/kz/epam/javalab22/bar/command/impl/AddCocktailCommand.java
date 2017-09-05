package kz.epam.javalab22.bar.command.impl;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.impl.page.PageCocktailCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.logic.CocktailLogic;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.manager.MessageManager;
import kz.epam.javalab22.bar.servlet.ReqHandler;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

public class AddCocktailCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(AddCocktailCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        ReqHandler reqHandler = new ReqHandler(request);
        CocktailLogic cocktailLogic = new CocktailLogic(reqHandler);

        if (cocktailLogic.addCocktail()) {
            request.setAttribute("addCocktailResult", "Коктейль добавлен");
            log.info("Коктейль: " + reqHandler.getParam("name") + " добавлен");
        } else {
            request.setAttribute("addCocktailResult", MessageManager.getProperty("message.loginerror"));
        }

        return new PageCocktailCommand().execute(request);
    }
}
