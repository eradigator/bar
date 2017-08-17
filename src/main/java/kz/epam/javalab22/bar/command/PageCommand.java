package kz.epam.javalab22.bar.command;

import kz.epam.javalab22.bar.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;


public class PageCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        String chosen = request.getParameter("chosen");
        if (chosen != null) {
            switch (chosen) {
                case "alcoholic":
                    request.setAttribute("content","alcoholic");
                    break;
                case "non_alcoholic":
                    request.setAttribute("content","non_alcoholic");
                    break;
                case "calculator":
                    request.setAttribute("content","calculator");
                    break;
                case "contact":
                    request.setAttribute("content","contact");
                    break;
            }
        }

        return ConfigurationManager.getProperty("path.page.index");
    }
}
