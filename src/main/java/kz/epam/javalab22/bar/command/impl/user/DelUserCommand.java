package kz.epam.javalab22.bar.command.impl.user;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.impl.page.PageUserManagerCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.logic.UserLogic;
import kz.epam.javalab22.bar.manager.MessageManager;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DelUserCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(DelUserCommand.class);

    @Override
    public String execute(ReqWrapper reqWrapper) {

        MessageManager messageManager = new MessageManager(reqWrapper.getLocale());

        if (new UserLogic(reqWrapper).delUser()) {
            reqWrapper.addAttribute(Const.ATTR_DEL_USER_RESULT, messageManager.getProperty("userDeleted"));
            log.info(Const.LOG_USER + Const.DIV_SPACE +
                    reqWrapper.getParam("checkedName") + Const.LOG_HAS_BEEN_DELETED);
        } else {
            reqWrapper.addAttribute("error", messageManager.getProperty("error"));
        }

        return new PageUserManagerCommand().execute(reqWrapper);
    }

}
