package kz.epam.javalab22.bar.command.impl.page;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.servlet.ReqWrapper;

public class PageLoginCommand implements ActionCommand {

    @Override
    public String execute(ReqWrapper reqWrapper) {
        return ConfigurationManager.getProperty(Const.PAGE_LOGIN);
    }

}
