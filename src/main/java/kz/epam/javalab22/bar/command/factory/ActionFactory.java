package kz.epam.javalab22.bar.command.factory;

import kz.epam.javalab22.bar.command.client.CommandEnum;
import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.EmptyCommand;
import kz.epam.javalab22.bar.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {

    private final static String STR_COMMAND = "command";

    public ActionCommand defineCommand(HttpServletRequest request) {

        ActionCommand current = new EmptyCommand();

        // извлечение имени команды из запроса
        String action = request.getParameter(STR_COMMAND);
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
