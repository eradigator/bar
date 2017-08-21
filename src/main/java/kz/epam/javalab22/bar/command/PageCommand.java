package kz.epam.javalab22.bar.command;

import kz.epam.javalab22.bar.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;


public class PageCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        String chosen = request.getParameter("chosen");
        if (chosen != null) {
            switch (chosen) {
                case "login":
                    return ConfigurationManager.getProperty("path.page.login"); //тест временно
                case "admin":
                    return ConfigurationManager.getProperty("path.page.main"); //тест временно

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

        return ConfigurationManager.getProperty("path.page.index");
    }
}
