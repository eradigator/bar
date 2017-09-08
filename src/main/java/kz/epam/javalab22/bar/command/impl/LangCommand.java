package kz.epam.javalab22.bar.command.impl;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.impl.page.PageMainCommand;
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

        //String page = request.getHeader("referer");             //откуда пришли
        /*System.out.println(request.getRequestURL().toString());

        System.out.println(request.getAttribute("javax.servlet.forward.query_string"));*/
        return new PageMainCommand().execute(request);
    }
}
