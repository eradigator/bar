package kz.epam.javalab22.bar.command.impl.page;

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

public class PageMainCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        ReqWrapper reqWrapper = new ReqWrapper(request);


        UIText uiText = new UITextDao().get(Integer.parseInt(ConfigurationManager.getProperty("uiTextIdForMainPage")));
        reqWrapper.addAttribute("uiText",uiText);

        reqWrapper.addAttribute("content", "main");
        return ConfigurationManager.getProperty(Const.PAGE_INDEX);
    }
}
