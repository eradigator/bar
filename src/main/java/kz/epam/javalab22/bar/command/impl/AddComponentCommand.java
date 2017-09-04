package kz.epam.javalab22.bar.command.impl;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.logic.ComponentLogic;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.manager.MessageManager;
import kz.epam.javalab22.bar.servlet.ReqHandler;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddComponentCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(AddComponentCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        ReqHandler reqHandler = new ReqHandler(request);

        if (new ComponentLogic(reqHandler).addComponent()) {

            String message = MessageManager.getProperty("message.componentAdded");
            reqHandler.addAttribute("result",message);
            log.info(message);
        }

        return ConfigurationManager.getProperty(Const.PAGE_COMPONENT_MANAGER);
    }
}
