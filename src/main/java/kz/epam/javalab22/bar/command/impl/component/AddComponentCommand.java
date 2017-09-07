package kz.epam.javalab22.bar.command.impl.component;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.impl.page.PageComponentCommand;
import kz.epam.javalab22.bar.logic.ComponentLogic;
import kz.epam.javalab22.bar.manager.MessageManager;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddComponentCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(AddComponentCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        ReqWrapper reqWrapper = new ReqWrapper(request);

        if (new ComponentLogic(reqWrapper).addComponent()) {
            String message = MessageManager.getProperty("message.componentAdded");
            reqWrapper.addAttribute("result",message);
            log.info(message);
        } else {
            reqWrapper.addAttribute("error","error");
        }

        return new PageComponentCommand().execute(request);
    }
}
