package kz.epam.javalab22.bar.command;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;


public class PageCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        String chosen = request.getParameter("chosen");
        if (chosen != null) {
            switch (chosen) {
                case "login":
                    return ConfigurationManager.getProperty(Const.PAGE_LOGIN); //тест временно
                case "admin":
                    return ConfigurationManager.getProperty(Const.PAGE_MAIN); //тест временно
                case "cocktailManager":
                    return ConfigurationManager.getProperty(Const.PAGE_COCKTAIL_MANAGER); //тест временно
                case "componentManager":
                    return ConfigurationManager.getProperty(Const.PAGE_COMPONENT_MANAGER); //тест временно

                case "catalog":
                    request.setAttribute("content", "catalog");
                    break;
                case "alcoholic":
                    request.setAttribute("content", "alcoholic");
                    break;
                case "nonalcoholic":
                    request.setAttribute("content", "nonalcoholic");
                    break;
                case "calculator":
                    request.setAttribute("content", "calculator");
                    break;
                case "contacts":
                    request.setAttribute("content", "contacts");
                    break;

            }
        }

        return ConfigurationManager.getProperty(Const.PAGE_INDEX);
    }
}
