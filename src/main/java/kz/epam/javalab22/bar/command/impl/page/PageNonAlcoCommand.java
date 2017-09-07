package kz.epam.javalab22.bar.command.impl.page;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.CocktailDao;
import kz.epam.javalab22.bar.entity.Cocktail;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.servlet.ReqWrapper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class PageNonAlcoCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        ReqWrapper reqWrapper = new ReqWrapper(request);

        List<Cocktail> cocktailList = new CocktailDao().getNonAlcoList();

        reqWrapper.addAttribute("cocktailList", cocktailList);

        reqWrapper.addAttribute("content", "nonalcoholic");
        return ConfigurationManager.getProperty(Const.PAGE_INDEX);
    }
}
