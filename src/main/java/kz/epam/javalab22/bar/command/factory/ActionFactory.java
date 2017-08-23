package kz.epam.javalab22.bar.command.factory;

import kz.epam.javalab22.bar.command.client.CommandEnum;
import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.EmptyCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {

    public ActionCommand defineCommand(HttpServletRequest request) {

        ActionCommand current = new EmptyCommand();

        String action = request.getParameter(Const.PARAM_COMMAND);
        if (action == null || action.isEmpty()) {
            return current;
        }

        // получение объекта, соответствующего команде
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", action + MessageManager.getProperty("message.wrongaction"));
        }
        return current;
    }
}
