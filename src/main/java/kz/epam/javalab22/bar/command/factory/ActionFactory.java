package kz.epam.javalab22.bar.command.factory;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.Command;
import kz.epam.javalab22.bar.command.CommandProvider;
import kz.epam.javalab22.bar.command.impl.EmptyCommand;
import kz.epam.javalab22.bar.constant.Const;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {

    private static final Logger log = Logger.getLogger(ActionFactory.class);

    public ActionCommand defineCommand(HttpServletRequest request) {

        String action = request.getParameter(Const.PARAM_COMMAND);
        ActionCommand actionCommand = new EmptyCommand();

        if (!(null == action || action.isEmpty())) {

            try {
                Command command = new CommandProvider();
                actionCommand = command.getCurrentCommand(action);
            } catch (IllegalArgumentException e) {
                log.warn(Const.LOG_WRONG_ACTION);
            }
        }

        return actionCommand;
    }
}
