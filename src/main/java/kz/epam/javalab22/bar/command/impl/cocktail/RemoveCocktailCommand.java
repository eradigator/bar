package kz.epam.javalab22.bar.command.impl.cocktail;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.impl.page.PageCocktailManagerCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.logic.CocktailLogic;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class RemoveCocktailCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(RemoveCocktailCommand.class);

    @Override
    public String execute(ReqWrapper reqWrapper) {

        if (new CocktailLogic(reqWrapper).deleteCocktail()) {
            log.info(Const.LOG_COCKTAIL + Const.DIV_SPACE + reqWrapper.getParam(Const.PARAM_COCKTAIL_TO_DELETE) +
                    Const.DIV_SPACE + Const.LOG_HAS_BEEN_DELETED);

            String message = "Коктейль удален";
            reqWrapper.addAttribute(Const.ATTR_DEL_COCKTAIL_RESULT, message);

        } else {
            String message = "Коктейль не удален";
            reqWrapper.addAttribute(Const.ATTR_DEL_COCKTAIL_RESULT, message);
        }

        return new PageCocktailManagerCommand().execute(reqWrapper);
    }
}
