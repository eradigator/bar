package kz.epam.javalab22.bar.command.impl;

import kz.epam.javalab22.bar.command.ActionCommand;
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
                    return ConfigurationManager.getProperty(Const.PAGE_LOGIN);
                case "admin":
                    return ConfigurationManager.getProperty(Const.PAGE_MAIN);
                case "cocktailManager":
                    return ConfigurationManager.getProperty(Const.PAGE_COCKTAIL_MANAGER);
                case "componentManager":
                    return ConfigurationManager.getProperty(Const.PAGE_COMPONENT_MANAGER);

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
            }
        }

        return ConfigurationManager.getProperty(Const.PAGE_INDEX);
    }
}
