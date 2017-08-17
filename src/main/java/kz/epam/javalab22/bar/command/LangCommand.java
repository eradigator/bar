package kz.epam.javalab22.bar.command;

import kz.epam.javalab22.bar.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;


public class LangCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        String lang = request.getParameter("chosen");
        if (lang != null) {
            switch (lang) {
                case "EN":
                    request.getSession().setAttribute("locale", "en_US");
                    break;
                case "RU":
                    request.getSession().setAttribute("locale", "ru_RU");
                    break;
            }
        }

        String page = ConfigurationManager.getProperty("path.page.index");
        return page;
    }
}
