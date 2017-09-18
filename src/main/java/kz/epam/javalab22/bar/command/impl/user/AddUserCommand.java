package kz.epam.javalab22.bar.command.impl.user;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.impl.page.PageUserManagerCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.logic.UserLogic;
import kz.epam.javalab22.bar.manager.MessageManager;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddUserCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(AddUserCommand.class);

    @Override
    public String execute(ReqWrapper reqWrapper) {

        UserLogic userLogic = new UserLogic(reqWrapper);
        MessageManager messageManager = new MessageManager(reqWrapper.getLocale());

        if (userLogic.addUser()) {
            reqWrapper.addAttribute(Const.ATTR_ADD_USER_RESULT, messageManager.getProperty("userAdded"));
            log.info(Const.LOG_USER + Const.DIV_SPACE +
                    reqWrapper.getParam(Const.PARAM_LOGIN) + Const.LOG_HAS_BEEN_ADDED);
        } else {
            reqWrapper.addAttribute(Const.ATTR_ERROR, messageManager.getProperty("error"));
        }

        return new PageUserManagerCommand().execute(reqWrapper);
    }

}