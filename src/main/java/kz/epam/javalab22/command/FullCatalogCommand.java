package kz.epam.javalab22.command;

import kz.epam.javalab22.resourse.ConfigurationManager;
import kz.epam.javalab22.resourse.MessageManager;

import javax.servlet.http.HttpServletRequest;


public class FullCatalogCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        String page = ConfigurationManager.getProperty("path.page.index");
        request.setAttribute("bodyContent", MessageManager.getProperty("message.bodyContent"));
        return page;

    }
}
