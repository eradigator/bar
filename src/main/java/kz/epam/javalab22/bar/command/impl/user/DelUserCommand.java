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

        if (new UserLogic(reqWrapper).delUser()) {
            String message = "Пользователь удален";
            reqWrapper.addAttribute(Const.ATTR_DEL_USER_RESULT, message);
            log.info(Const.LOG_USER + Const.DIV_SPACE +
                    reqWrapper.getParam("checkedName") + Const.LOG_HAS_BEEN_DELETED);
        } else {
            String message = MessageManager.getProperty("message.deleteUserError");
            reqWrapper.addAttribute("error", message);
        }

        return new PageUserManagerCommand().execute(reqWrapper);
    }

}
