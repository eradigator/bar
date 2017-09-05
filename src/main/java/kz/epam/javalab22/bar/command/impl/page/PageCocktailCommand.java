package kz.epam.javalab22.bar.command.impl.page;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.CocktailDao;
import kz.epam.javalab22.bar.dao.ComponentDao;
import kz.epam.javalab22.bar.dao.ComponentTypeDao;
import kz.epam.javalab22.bar.entity.Cocktail;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.servlet.ReqHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PageCocktailCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        ReqHandler reqHandler = new ReqHandler(request);

        List<String> cocktailNames = new ArrayList<>();
        for (Cocktail cocktail : new CocktailDao().getCocktailsList()) {
            cocktailNames.add(cocktail.getName());
        }

        Map<Integer,String> components = new ComponentDao().getComponents();



        reqHandler.addAttribute("cocktailNames", cocktailNames);
        reqHandler.addAttribute("components", components);

        return ConfigurationManager.getProperty(Const.PAGE_COCKTAIL_MANAGER);
    }
}
