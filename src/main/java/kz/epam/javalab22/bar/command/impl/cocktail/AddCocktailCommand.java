package kz.epam.javalab22.bar.command.impl.cocktail;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.impl.page.PageCocktailManagerCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.logic.CocktailLogic;
import kz.epam.javalab22.bar.manager.MessageManager;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddCocktailCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(AddCocktailCommand.class);

    @Override
    public String execute(ReqWrapper reqWrapper) {

        CocktailLogic cocktailLogic = new CocktailLogic(reqWrapper);

        if (cocktailLogic.addCocktail()) {
            log.info(Const.LOG_COCKTAIL + Const.DIV_SPACE + reqWrapper.getParam(Const.PARAM_NAME) +
                    Const.DIV_SPACE + Const.LOG_HAS_BEEN_ADDED);

            reqWrapper.addAttribute(Const.ATTR_ADD_COCKTAIL_RESULT, "Коктейль добавлен");
        } else {
            String message = MessageManager.getProperty("message.loginerror");
            reqWrapper.addAttribute(Const.ATTR_ADD_COCKTAIL_RESULT, message);
        }

        return new PageCocktailManagerCommand().execute(reqWrapper);
    }


}
