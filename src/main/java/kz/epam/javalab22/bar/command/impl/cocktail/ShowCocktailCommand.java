package kz.epam.javalab22.bar.command.impl.cocktail;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.CocktailDao;
import kz.epam.javalab22.bar.dao.UITextDao;
import kz.epam.javalab22.bar.entity.Cocktail;
import kz.epam.javalab22.bar.entity.UIText;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.servlet.ReqWrapper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowCocktailCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        ReqWrapper reqWrapper = new ReqWrapper(request);

        int cocktailId = Integer.parseInt(request.getParameter("cocktailId"));
        Cocktail cocktail = new CocktailDao().get(cocktailId);

        reqWrapper.addAttribute("cocktail", cocktail);
        reqWrapper.addAttribute("content", "cocktail");

        return ConfigurationManager.getProperty(Const.PAGE_INDEX);
    }
}
