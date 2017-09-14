package kz.epam.javalab22.bar.command.impl.cocktail;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.impl.page.PageCocktailManagerCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.logic.CocktailLogic;
import kz.epam.javalab22.bar.manager.MessageManager;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class RemoveCocktailCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(RemoveCocktailCommand.class);

    @Override
    public String execute(ReqWrapper reqWrapper) {

        MessageManager messageManager = new MessageManager(reqWrapper.getLocale());

        if (new CocktailLogic(reqWrapper).deleteCocktail()) {
            reqWrapper.addAttribute(Const.ATTR_DEL_COCKTAIL_RESULT, messageManager.getProperty("cocktailDeleted"));
            log.info(Const.LOG_COCKTAIL + Const.DIV_SPACE + reqWrapper.getParam(Const.PARAM_COCKTAIL_ID_TO_DELETE) +
                    Const.DIV_SPACE + Const.LOG_HAS_BEEN_DELETED);
        } else {
            reqWrapper.addAttribute(Const.ATTR_ERROR, messageManager.getProperty("error"));
        }

        return new PageCocktailManagerCommand().execute(reqWrapper);
    }
}
