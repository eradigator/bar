package kz.epam.javalab22.bar.command.impl.component;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.impl.page.PageComponentManagerCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.entity.Cocktail;
import kz.epam.javalab22.bar.logic.ComponentLogic;
import kz.epam.javalab22.bar.manager.MessageManager;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddComponentCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(AddComponentCommand.class);

    @Override
    public String execute(ReqWrapper reqWrapper) {

        if (new ComponentLogic(reqWrapper).addComponent()) {
            String message = MessageManager.getProperty("message.componentAdded");
            reqWrapper.addAttribute(Const.ATTR_RESULT,message);
            log.info(message);
        } else {
            String message = "error";
            reqWrapper.addAttribute(Const.ATTR_ERROR,message);
        }

        return new PageComponentManagerCommand().execute(reqWrapper);
    }

}
