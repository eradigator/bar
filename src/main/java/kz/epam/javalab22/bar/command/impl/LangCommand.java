package kz.epam.javalab22.bar.command.impl;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.impl.page.PageMainCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.servlet.ReqWrapper;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;


public class LangCommand implements ActionCommand {

    @Override
    public String execute(ReqWrapper reqWrapper) {

        String lang = reqWrapper.getParam(Const.PARAM_CHOSEN);
        if (null != lang) {
            switch (lang) {
                case Const.STR_EN:
                    reqWrapper.getRequest().getSession().setAttribute(Const.ATTR_LOCALE, Const.LOC_EN_US);
                    break;
                case Const.STR_RU:
                    reqWrapper.getRequest().getSession().setAttribute(Const.ATTR_LOCALE, Const.LOC_RU_RU);
                    break;
            }
        }

        return ConfigurationManager.getProperty(Const.PAGE_REFFERER);
    }

}
