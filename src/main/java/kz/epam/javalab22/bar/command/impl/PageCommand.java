package kz.epam.javalab22.bar.command.impl;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.servlet.ReqWrapper;

import javax.servlet.http.HttpServletRequest;


public class PageCommand implements ActionCommand {

    @Override
    public String execute(ReqWrapper reqWrapper) {

        String chosen = reqWrapper.getParam("chosen");
        if (chosen != null) {
            switch (chosen) {
                case "login":
                    return ConfigurationManager.getProperty(Const.PAGE_LOGIN);
            }
        }

        return ConfigurationManager.getProperty(Const.PAGE_INDEX);
    }

}
