package kz.epam.javalab22.bar.command.impl;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.impl.page.PageMainCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import org.apache.log4j.Logger;

public class EmptyCommand implements ActionCommand {

    private static final Logger log = org.apache.log4j.Logger.getLogger(EmptyCommand.class);

    @Override
    public String execute(ReqWrapper reqWrapper) {
        log.warn(Const.LOG_EMPTY_COMMAND);
        reqWrapper.addAttribute(Const.ATTR_CONTENT, Const.VAL_MAIN);
        return new PageMainCommand().execute(reqWrapper);
    }

}
