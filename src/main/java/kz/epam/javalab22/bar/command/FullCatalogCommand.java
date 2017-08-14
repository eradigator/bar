package kz.epam.javalab22.bar.command;

import kz.epam.javalab22.bar.manager.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class FullCatalogCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {


        String page = ConfigurationManager.getProperty("path.page.index");
        return page;

    }
}
