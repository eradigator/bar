package kz.epam.javalab22.bar.command.impl.page;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.CocktailDao;
import kz.epam.javalab22.bar.dao.ComponentDao;
import kz.epam.javalab22.bar.dao.UITextDao;
import kz.epam.javalab22.bar.entity.Cocktail;
import kz.epam.javalab22.bar.entity.UIText;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.servlet.ReqWrapper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class PageAlcoholicCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        ReqWrapper reqWrapper = new ReqWrapper(request);
        List<Cocktail> cocktailList = new CocktailDao().getCocktailsList();

        UIText uiText = new UITextDao().get(Integer.parseInt(ConfigurationManager.getProperty("uiTextIdForAlcoholicPage")));

        reqWrapper.addAttribute("cocktailList", cocktailList);
        reqWrapper.addAttribute("uiText",uiText);
        reqWrapper.addAttribute("content", "alcoholic");

        return ConfigurationManager.getProperty(Const.PAGE_INDEX);
    }
}
