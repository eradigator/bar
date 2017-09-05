package kz.epam.javalab22.bar.command.impl;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.impl.page.PageCocktailCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.logic.CocktailLogic;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.servlet.ReqHandler;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class RemoveCocktailCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(RemoveCocktailCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        ReqHandler reqHandler = new ReqHandler(request);

        if (new CocktailLogic(reqHandler).deleteCocktail()) {
            request.setAttribute("removeCocktailResult", "Коктейль удален");
            log.info("Коктейль: " + reqHandler.getParam("cocktailToDelete") + " удален");
        } else {
            request.setAttribute("removeCocktailResult", "Коктейль не удален");
        }

        return new PageCocktailCommand().execute(request);
    }
}
