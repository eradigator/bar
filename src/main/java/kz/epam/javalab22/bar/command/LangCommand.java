package kz.epam.javalab22.bar.command;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;


public class LangCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        String lang = request.getParameter("chosen");
        if (lang != null) {
            switch (lang) {
                case Const.STR_EN:
                    request.getSession().setAttribute("locale", "en_US");
                    break;
                case Const.STR_RU:
                    request.getSession().setAttribute("locale", "ru_RU");
                    break;
            }
        }

        String page = ConfigurationManager.getProperty(Const.PAGE_INDEX);
        //String page = request.getHeader("referer");             //откуда пришли
        request.setAttribute("content", "catalog");          //костылек
        return page;
    }
}
