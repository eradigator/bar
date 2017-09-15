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

    @Override
    public String execute(ReqWrapper reqWrapper) {

        MessageManager messageManager = new MessageManager(reqWrapper.getLocale());

        if (new ComponentLogic(reqWrapper).addComponent()) {
            reqWrapper.addAttribute(Const.ATTR_RESULT, messageManager.getProperty("componentAdded"));
        } else {
            reqWrapper.addAttribute(Const.ATTR_ERROR, messageManager.getProperty("error"));
        }

        return new PageComponentManagerCommand().execute(reqWrapper);
    }

}
