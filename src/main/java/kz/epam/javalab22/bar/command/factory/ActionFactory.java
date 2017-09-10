package kz.epam.javalab22.bar.command.factory;

import kz.epam.javalab22.bar.command.CommandEnum;
import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.impl.EmptyCommand;
import kz.epam.javalab22.bar.command.impl.LoginCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.manager.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {

    private static final Logger log = Logger.getLogger(ActionFactory.class);

    public ActionCommand defineCommand(HttpServletRequest request) {

        String action = request.getParameter(Const.PARAM_COMMAND);
        ActionCommand actionCommand = new EmptyCommand();

        if (!(action == null || action.isEmpty())) {

            try {
                CommandEnum commandEnum = CommandEnum.valueOf(action.toUpperCase());
                actionCommand = commandEnum.getCurrentCommand();
            } catch (IllegalArgumentException e) {
                log.info(Const.LOG_WRONG_ACTION);
            }
        }

        return actionCommand;
    }
}
